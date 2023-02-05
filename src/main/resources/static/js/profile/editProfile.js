$(document).ready(
    function () {
        const editForm = `
            <div class="editProfilePopupBack">
                <form class="editProfilePopup">
                    <div class="editFieldContainer">
                        <p class="editFieldLabel">Имя и фамилия</p>
                        <input class="editSmallField" id="editNameField" type="text" name="name">
                        <input class="editSmallField" id="editSurnameField" type="text" name="surname">
                    </div>
                    <div class="editFieldContainer">
                        <p class="editFieldLabel">Email</p>
                        <input class="editBigField" id="editEmailField" type="email" name="email">
                    </div>
                    <div class="editFieldContainer">
                        <p class="editFieldLabel">Логин</p>
                        <input class="editBigField" id="editUsernameField" type="text" name="username">
                    </div>
                    <input type="submit" class="saveUserEditButton" value="Изменить">
                </form>
            </div>
        `;
        const editProfileButton = $('#editProfileButton')[0];
        const csrfToken = Cookies.get('XSRF-TOKEN');
        const userId = Cookies.get('USER_ID');

        document.addEventListener('click', (e) => {
            e.preventDefault();
            if(e.target === $('.editProfilePopupBack')[0]) {
                $('.editProfilePopupBack').remove();
            }
        });

        editProfileButton.addEventListener('click', (e) => {
            e.preventDefault();
            $("body").append(editForm);
            fillEditForm();
            eventListenerOnChangeButton();
        });

        function eventListenerOnChangeButton() {
            $('.saveUserEditButton').on('click', function(e) {
                e.preventDefault();
                if ($('div').hasClass('errorContainer')) {
                    $('.errorContainer').remove();
                }
                const formData = new FormData($('.editProfilePopup')[0]);
                formData.append('id', userId);
                sendRequest(formData);
            })
        }

        function fillEditForm() {
            $('#editNameField').val($('#nameField').val());
            $('#editSurnameField').val($('#surnameField').val());
            $('#editEmailField').val($('#emailField').val());
            $('#editUsernameField').val($('#usernameField').val());
        }

        function sendRequest(formData) {
            fetch('/api/users/'.concat(userId), {
                method: 'PATCH',
                credentials: "same-origin",
                headers: {
                    'X-XSRF-TOKEN': csrfToken,
                    'Content-type': "application/json"
                },
                body: JSON.stringify(Object.fromEntries(formData.entries()))
            })
                .then(response => {
                    if (response.status === 200) {
                        if (Cookies.get('USERNAME') !== formData.get('username')) {
                            logout();
                        } else {
                            document.location.reload();
                        }
                    } else {
                        response.json().then(data => {
                            const errorDiv = createErrorDiv(data);
                            $('.editProfilePopupBack').append(errorDiv);
                        })
                    }
                })
        }

        function logout() {
            fetch('/logout', {
                method: 'POST',
                credentials: "same-origin",
                headers: {'X-XSRF-TOKEN': csrfToken}
            })
                .then(response => {
                    if (response.redirected) {
                        document.location = response.url;
                    }
                })
        }

        function createErrorDiv(data) {
            const errors = data['message'];
            let errorDiv = "<div class=\"errorContainer\">";
            for (let i = 0; i < errors.length; i++) {
                errorDiv = errorDiv + "<p class='error'>" +  errors[i] + "</p>";
            }
            errorDiv = errorDiv + "</div>";
            return errorDiv;
        }
    }
)
