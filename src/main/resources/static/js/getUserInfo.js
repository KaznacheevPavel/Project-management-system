$(document).ready(async function () {

    const url = window.location.href;
    const lastSlash = url.lastIndexOf("/");
    const userLogin = url.slice(lastSlash - url.length + 1);

    const response = await fetch("/api/users/".concat(userLogin));
    if (response.ok) {
        const userJson = await response.json();
        setValues(userJson);
    }

    function setValues(json) {
        const nameField = document.getElementById('nameField');
        nameField.value = json['name'];
        const surnameField = document.getElementById('surnameField');
        surnameField.value = json['surname'];
        const emailField = document.getElementById('emailField');
        emailField.value = json['email'];
        const usernameField = document.getElementById('usernameField');
        usernameField.value = json['username'];
    }

});