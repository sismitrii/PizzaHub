/*
** ENI Project 2023
** PizzaHub
** Paul Tanguy, Florian Guérin
** cart.js
*/

import Lib from "/scripts/lib.js"

window.addEventListener("load", () => {
    console.log(Lib.Cookie.test)
    Lib.Cookie.test = "feur"
    console.log(Lib.Cookie.test)
    Lib.Cookie.remove("test")
    console.log(Lib.Cookie.test)
});