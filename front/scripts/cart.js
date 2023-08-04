/*
** ENI Project 2023
** PizzaHub
** Paul Tanguy, Florian Guérin
** cart.js
*/

import Lib from "/scripts/lib.js"
import Api from "/scripts/apiSpoofer.js"
import Nav from "/scripts/nav.js"

Nav.acceuil;
Nav.$pizzas;

window.addEventListener("load", async () => {
    let pizzas = new Lib.Hash(JSON.parse(Lib.Cookie.cart || "{}"))
    let pizzaDiv = document.querySelector("#pizzas")
    let slots = await Api["timeSlot/"];

    for (let slot of slots)
        document.querySelector("select#slot").append(
            Lib.createTag("option", {}, slot.toString()));

    function pizzaTemplate(name, size, amount, price) {
        let form = (
            Lib.createTag("form", {
                class: "grid-3 border"}, [
                Lib.createTag("h2", {
                    class: "bold"},
                    name),
                Lib.createTag("h3", {},
                    "QTE"),
                Lib.createTag("h3", {},
                    "PRIX"),
                Lib.createTag("p", {},
                    "Taille " + size),
                Lib.createTag("div", {},
                    `(-) ${amount} (+)`),
                Lib.createTag("p", {},
                    `${price * amount}€`)
                ]))
        let priceTag = document.querySelector("#totalPrice")
        let totalPrice = parseFloat(priceTag.innerHTML.slice(0,-1)) + parseFloat(price) * parseInt(amount)
        priceTag.innerHTML = totalPrice.toString() + "€"
        return form
    }

    if (pizzas.length == 0)
        pizzaDiv.appendChild(Lib.createTag("h2", {}, "Votre pannier est vide, commandez!"))

    pizzas.forEach(([name_size, {amount, price}]) => {
        let [_, name, size] = name_size.match(/^(.+)_(.+)$/)
        pizzaDiv.appendChild(pizzaTemplate(name, size, amount, price))
    });

    document.querySelector("#submit").form.addEventListener("submit", (event) => {
        if (event.preventDefault) event.preventDefault()

        let formatedPizzas = Lib.map(pizzas, ([name_size, {amount, price}]) => {
            let [_, name, size] = name_size.match(/^(.+)_(.+)$/)
            return {name: name, size: size}
        });

        let postBody = {
            pizzas: formatedPizzas,
            customerName: event.target.elements.name.value,
            timeSlot: `1970-01-01T${event.target.elements.slot.value}`
        }

        console.log(postBody)

        Api["onlineOrder/create"] = JSON.parse(JSON.stringify(postBody));

        // console.log(new Lib.Hash({
        //     clientName: event.target.elements.name.value,
        //     timeSlot: event.target.elements.slot.value,
        //     totalPrice: parseFloat(event.target.querySelector("#totalPrice").innerHTML.slice(0, -1)),
        //     order: pizzas
        // }));
        //document.location.href = "/views/orderConfirmation.html"
        return false
    })
});