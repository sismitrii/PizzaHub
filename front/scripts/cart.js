/*
** ENI Project 2023
** PizzaHub
** Paul Tanguy, Florian Guérin
** cart.js
*/

import Lib from "/scripts/lib.js"

window.addEventListener("load", () => {
    let pizzas = Lib.toHash(JSON.parse(Lib.Cookie.cart || "{}"))
    console.log("JSON", pizzas)
    let pizzaDiv = document.querySelector("#pizzas")

    function pizzaTemplate(pizza) {
        let form = (
            Lib.createTag("form", {
                class: "grid-3 border"}, [
                Lib.createTag("h2", {
                    class: "bold"},
                    pizza.name),
                Lib.createTag("h3", {},
                    "QTE"),
                Lib.createTag("h3", {},
                    "PRIX"),
                Lib.createTag("p", {},
                    "Taille " + pizza.size),
                Lib.createTag("div", {},
                    "(-) 1 (+)"),
                Lib.createTag("p", {},
                    `${pizza.price}€`)
                ]))
        let priceTag = document.querySelector("#totalPrice")
        let totalPrice = parseFloat(priceTag.innerHTML.slice(0,-1)) + parseFloat(pizza.price)
        priceTag.innerHTML = totalPrice.toString() + "€"
        return form
    }

    pizzas.forEach(pizza => {
        pizzaDiv.appendChild(pizzaTemplate(pizza))
    });
});