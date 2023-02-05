$(document).ready(function() {

    const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
    const form = $(".registrationForm")[0];

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        if ($('div').hasClass('errorContainer')) {
            $('.errorContainer').remove();
        }
        const formData = new FormData(e.target);
        const formDataObj = Object.fromEntries(formData.entries());
        fetch('/api/users/registration', {
            method: 'POST',
            credentials: "same-origin",
            headers: {
                'X-XSRF-TOKEN': csrfToken,
                'Content-type': "application/json"
            },
            body: JSON.stringify(formDataObj)
        })
            .then(response => {
                if (response.status === 200) {
                    document.location = '/login';
                } else {
                    response.json().then(data => {
                        const errorDiv = createErrorDiv(data);
                        $(".outerContainer").append(errorDiv);
                    });
                }
            })
    })

    function createErrorDiv(data) {
        const errors = data['message'];
        let errorDiv = "<div class=\"errorContainer\">";
        for (let i = 0; i < errors.length; i++) {
            errorDiv = errorDiv + "<p>" +  errors[i] + "</p>";
        }
        errorDiv = errorDiv + "</div>";
        return errorDiv;
    }


});