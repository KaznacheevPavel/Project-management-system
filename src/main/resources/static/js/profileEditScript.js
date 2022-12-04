window.onload=function() {
    let editProfilePopupBack = document.querySelector('.editProfilePopupBack');
    let editPasswordPopupBack = document.querySelector('.editPasswordPopupBack');

    let editProfilePopup = document.querySelector('.editProfilePopup');
    let editPasswordPopup = document.querySelector('.editPasswordPopup');

    let editProfileButton = document.getElementById("editProfileButton");
    let editPasswordButton = document.getElementById("editPasswordButton")
    
    editProfileButton.addEventListener('click', (e) => {
        e.preventDefault();
        editProfilePopupBack.classList.add('active'); 
        editProfilePopup.classList.add('active');
    }) 

    editPasswordButton.addEventListener('click', (e) => {
        e.preventDefault();
        editPasswordPopupBack.classList.add('active'); 
        editPasswordPopup.classList.add('active');
    }) 


    document.addEventListener('click', (e) => {
        if(e.target === editProfilePopupBack) {
            editProfilePopupBack.classList.remove('active'); 
            editProfilePopup.classList.remove('active');
        }
    });

    document.addEventListener('click', (e) => {
        if(e.target === editPasswordPopupBack) {
            editPasswordPopupBack.classList.remove('active'); 
            editPasswordPopup.classList.remove('active');
        }
    });
}