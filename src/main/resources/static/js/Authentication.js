$(document).ready(function() {

    const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
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
            .then(res => {
                if (!res.ok) {
                    throw new Error(res.status);
                }
                if (res.redirected) {
                    document.location = res.url;
                }
            })
            .catch((err) => {
                if (err.message === '401' && !$('div').hasClass("errorContainer")) {
                    $(".outerContainer").append(errorDiv);
                }
            })
    })
});
