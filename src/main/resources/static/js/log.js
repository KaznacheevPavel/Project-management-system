$(document).ready(
    function () {
        const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');

        const button = $('.logout')[0];

        button.addEventListener('click', function (e) {
            e.preventDefault();
            fetch('/logout', {
                method: 'POST',
                credentials: "same-origin",
                headers: {'X-XSRF-TOKEN': csrfToken}
            })
        })
    }
)