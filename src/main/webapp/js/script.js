// fix header
window.onscroll = function() {myHeader()};

var header = document.getElementById("myHeader");
var sticky = header.offsetTop;

function myHeader() {
  if (window.pageYOffset > sticky) {
    header.classList.add("sticky");
  } else {
    header.classList.remove("sticky");
  }
}

// function to show menu
var menu1 = document.getElementById('menu1');
function showMenu() {
  menu1.classList.toggle('show-mobile');
}

// login modal
$(function () {
  var $overlay = $(".overlay");
  var $mainPopUp = $("#mainPopup");
  var $login = $("#login");
  var $register = $("#register");
  var $formLogin = $("form.login");
  var $formRegister = $("form.register");
  var $loginModal = $("#loginModal");
  var $mainPopUpChange = $("#mainPopupChange");
  var $formChangePwd = $("form.changePwd");
  var $changePwd = $("#changePassword");

  $loginModal.on("click", function () {
	  console.log('clicked');
    $overlay.addClass("visible");
    $mainPopUp.addClass("visible");
    $login.addClass("active");
    $register.removeClass("active");
    $formRegister.removeClass("move-left");
    $formLogin.removeClass("move-left");
  });

  $overlay.on("click", function () {
    $(this).removeClass("visible");
    $mainPopUp.removeClass("visible");
  });

  $(".popup-close-button a").on("click", function (e) {
    e.preventDefault();
    $overlay.removeClass("visible");
    $mainPopUp.removeClass("visible");
    $mainPopUpChange.removeClass("visible");
  });

  $login.on("click", function () {
    $login.addClass("active");
    $register.removeClass("active");
    $formLogin.removeClass("move-left");
    $formRegister.removeClass("move-left");
  });

  $register.on("click", function () {
    $login.removeClass("active");
    $register.addClass("active");
    $formLogin.addClass("move-left");
    $formRegister.addClass("move-left");
  });

  $changePwd.on("click", function () {
    $overlay.addClass("visible");
    $mainPopUpChange.addClass("visible");
    $formChangePwd.addClass("active");
  });
});

// script to close error pop-up

var ok = document.getElementById('ok');

if (ok != null) {
	ok.onclick = function() {
	  this.parentElement.style.display = 'none';
	}
}

$(function() {
	setTimeout(function() { $("#error").fadeOut(1500); }, 3000)
})

// script for chatbot

var help = document.getElementById("help");

if (help) {
	help.addEventListener("mouseover", function() {
		this.setAttribute("src", "images/help2.jpg");
		this.parentNode.children[0].style.display = 'inline';
	})
}

if (help) {
	help.addEventListener("mouseout", function() {
		this.setAttribute("src", "images/help1.jpg");
		this.parentNode.children[0].style.display = 'none';
	})
}

// Page Loading
var preloader = document.getElementsByClassName("preloading");

setTimeout(function preload(){
    preloader[0].style.display = 'none';
},500);