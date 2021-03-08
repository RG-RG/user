const btnNavClose = document.querySelector(".btn_nav_close"); //nav bar 닫는 버튼
const btnNavOpen = document.querySelector(".btn_nav_open"); //nav bar 여는 버튼

const navBar = document.querySelector(".nav_bar");

let leftPosition = navBar.getBoundingClientRect().left;
const first_leftPosition = leftPosition;

let itv;
let count = 0;


function controlNavBar(){
    // console.log("left"+leftPosition);

    if (count == 0) {
        clearInterval(itv);
        itv = setInterval(sliding, 1);
        function sliding() {
            if (leftPosition == 0) {
                clearInterval(itv);
            }else {
                leftPosition = leftPosition + 6;
                navBar.style.left = leftPosition+"px";
                count = 1;
            }
        }
    } else {
        clearInterval(itv);
        itv = setInterval(sliding, 1);
        function sliding() {
            if (leftPosition == first_leftPosition) {
                clearInterval(itv);
            }else {
                leftPosition = leftPosition - 6;
                navBar.style.left = leftPosition+"px";
                count = 0;
            }
        }
    }
}

function init(){
    btnNavClose.addEventListener("click", controlNavBar);
    btnNavOpen.addEventListener("click", controlNavBar);
}

init();