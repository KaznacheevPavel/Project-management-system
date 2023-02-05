$(document).ready(async function () {
    const editProfileButtons = `
        <div class="buttonsContainer">
            <div class="buttonContainer">
                <button class="button" id="editProfileButton">Редактировать</button>
            </div>
            <div class="buttonContainer">
                <button class="button" id="editPasswordButton">Сменить пароль</button>
            </div>
        </div>
    `;
    const editProfileScripts = `
        <script src="/js/profile/editProfile.js"></script>
        <script src="/js/profile/editPassword.js"></script>
    `;
    const response = await fetch("/api/users/".concat(getUsernameFromUrl(window.location.href)));

    if (response.ok) {
        const userJson = await response.json();
        setValues(userJson);
        if (Cookies.get('USERNAME') === userJson['username']) {
            $('.profile').append(editProfileButtons);
            $('head').append(editProfileScripts);
        }
    } else if (response.status === 404) {
        console.log("Такого пользователя нет!");
        // TODO: СДЕЛАТЬ СТРАНИЦУ С ПОЛЬЗОВАТЕЛЕМ - 404!
    }

    function getUsernameFromUrl(url) {
        const lastSlash = url.lastIndexOf("/");
        const username = url.slice(lastSlash - url.length + 1);
        return username;
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