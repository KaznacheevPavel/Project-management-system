$(document).ready(
    function () {
        const editForm = `
            <div class="editPasswordPopupBack">
                <form class="editPasswordPopup">
                    <div class="fieldContainer">
                        <p class="fieldLabel">Старый пароль</p>
                        <input class="bigField" type="password" name="oldPassword">
                    </div>
                    <div class="fieldContainer">
                        <p class="fieldLabel">Новый пароль</p>
                        <input class="bigField" type="password" name="newPassword">
                    </div>
                    <button type="submit" class="savePasswordEditButton">Изменить</button>
                </form>
            </div>
        `;
        const editProfileButton = $('#editPasswordButton')[0];
        const csrfToken = Cookies.get('XSRF-TOKEN');
        const userId = Cookies.get('USER_ID');

        document.addEventListener('click', (e) => {
            e.preventDefault();
            if(e.target === $('.editPasswordPopupBack')[0]) {
                $('.editPasswordPopupBack').remove();
            }
        });

        editProfileButton.addEventListener('click', (e) => {
            e.preventDefault();
            $("body").append(editForm);
            eventListenerOnChangeButton();
        });

        function eventListenerOnChangeButton() {
            $('.savePasswordEditButton').on('click', function(e) {
                e.preventDefault();
                if ($('div').hasClass('errorContainer')) {
                    $('.errorContainer').remove();
                }
                const formData = new FormData($('.editPasswordPopup')[0]);
                formData.append('id', userId);
                const formDataObj = Object.fromEntries(formData.entries());
                sendRequest(formDataObj);
            })
        }

        function sendRequest(formDataObj) {
            fetch('/api/users/'.concat(userId).concat('/changePassword'), {
                method: 'PATCH',
                credentials: "same-origin",
                headers: {
                    'X-XSRF-TOKEN': csrfToken,
                    'Content-type': "application/json"
                },
                body: JSON.stringify(formDataObj)
            })
                .then(response => {
                    if (response.status === 200) {
                        logout();
                    } else {
                        response.json().then(data => {
                            const errorDiv = createErrorDiv(data);
                            $('.editPasswordPopupBack').append(errorDiv);
                        });
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
    }
)