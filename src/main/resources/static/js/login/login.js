$(document).ready(function() {

    const csrfToken = Cookies.get('XSRF-TOKEN');
    const form = $(".authorizationForm")[0];
    const errorDiv = `
        <div class="errorContainer">
            <p>Неверный логин или пароль</p>
        </div>
    `;

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        fetch('/login', {
            method: 'POST',
            credentials: "same-origin",
            headers: {'X-XSRF-TOKEN': csrfToken},
            body: new FormData(form)
        })
            .then(response => {
                if (response.redirected) {
                    document.location = response.url;
                }
                if (response.status === 400 && !$('div').hasClass("errorContainer")) {
                    $(".outerContainer").append(errorDiv);
                }
            })
    })
});
