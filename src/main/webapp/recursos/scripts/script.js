let menu = document.querySelector('.menu');
let adminTab = document.querySelector('.admin-tab');

adminTab.addEventListener('click', desplegarMenu);

function desplegarMenu()
{
    menu.classList.toggle('no-ver');
}

const form = document.getElementById('popupContact');

if(form){
    function reloadChanges(){
        setTimeout(function (){
            location.reload()
        },10)
    }

    const del = document.getElementById('delete');
    const popup_del = document.querySelector('.popup-delete_wrap');
    const cancel_del = document.getElementById('bd-pop-cancelar');
    del.addEventListener('click', e =>{
        popup_del.style.display = 'block'
    });
    cancel_del.addEventListener("click", e=>{
        popup_del.style.display = 'none'
    });

    const cancel = document.getElementById('cancel')
    cancel.addEventListener("click", (e)=>{
        document.getElementById('operations-container').style.display = 'none'
    })
}


