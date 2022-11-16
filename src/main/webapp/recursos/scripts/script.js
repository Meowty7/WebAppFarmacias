let menu = document.querySelector('.menu');
let adminTab = document.querySelector('.admin-tab');

adminTab.addEventListener('click', desplegarMenu);

function desplegarMenu()
{
    menu.classList.toggle('no-ver');
}

const form = document.getElementById('popupContact');
if(form){
    form.addEventListener("submit", function (){
        setTimeout(function (){
            location.reload()
        },10)
    });

    const cancel = document.getElementById('cancel')
    cancel.addEventListener("click", (e)=>{
        document.getElementById('operations-container').style.display = 'none'
    })
}


