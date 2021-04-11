const btnNavClose = document.querySelector(".btn_nav_close"); //nav bar 닫는 버튼
const btnNavOpen = document.querySelector(".btn_nav_open"); //nav bar 여는 버튼

const navBar = document.querySelector(".nav_bar");

const close_bar = document.querySelector(".close_bar");


let leftPosition = navBar.getBoundingClientRect().left;
const first_leftPosition = leftPosition;

let itv;
let count = 0;

let delta = 5;
let st = 0

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
            close_bar.classList.remove("hidden");
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
        close_bar.classList.add("hidden");
    }
}

function scrollNavBar(){
    st = document.documentElement.scrollTop;
    console.log("scrolling..."+ st);

    if (delta < st && count == 1) {
        close_bar.classList.add("hidden");
        controlNavBar();
    }
}

function init(){
    btnNavClose.addEventListener("click", controlNavBar);
    btnNavOpen.addEventListener("click", controlNavBar);
    close_bar.addEventListener("click", controlNavBar);
    document.addEventListener("scroll", scrollNavBar);
}

init();