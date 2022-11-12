let menu = document.querySelector('.menu');
let adminTab = document.querySelector('.admin-tab');

adminTab.addEventListener('click', desplegarMenu);

function desplegarMenu()
{
    menu.classList.toggle('no-ver');
}

$('#popupContact').submit(function (){
    setTimeout(function (){
        location.reload()
    },10)
});