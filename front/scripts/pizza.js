/*
** ENI Project 2023
** PizzaHub
** Paul Tanguy, Florian Guérin
** pizza.js
*/

import Lib from "/scripts/lib.js"
import Api from "/scripts/apiSpoofer.js"
import Nav from "/scripts/nav.js"

Nav.acceuil;
Nav.panier;

window.addEventListener("load", async () => {
    let q = document.querySelector.bind(document);;

    let [seasonPizza, ...pizzas] = await Api["menuItem/PIZZA"]

    Lib.Cookie.cart = undefined; // ToDo: DEV

    function addToCart(pizza) {
        console.log("pizza", pizza)
        let cart = JSON.parse(Lib.Cookie.cart || "{}")
        let id = `${pizza.name}_${pizza.size}`

        console.log("cart", cart, id)
        cart[id] = {amount: (cart[id]?.amount || 0) + 1, price: pizza.price}
        Lib.Cookie.cart = JSON.stringify(cart)
    }

    const PRICE_MODS = {
        "M": 0,
        "L": 3
    }

    function pizzaTemplate(pizza) {
        let pizzaForm = (
            Lib.createTag("form", {
                class: "pizza-template grid-2"}, [
                Lib.createTag("img", {
                    id: "pizzaImage",
                    alt: `Photo de notre ${pizza.name}`,
                    src: pizza.imageUrl || "/assets/pizza.png"}),
                Lib.createTag("div", {
                    class: "grid-1"}, [
                    Lib.createTag("h2", {
                        class: "bold",
                        id: "name"},
                        pizza.name),
                    Lib.createTag("div", {
                        class: "input"}, [
                        Lib.createTag("label", {
                            for: "size"},
                            "Taille:"),
                        Lib.createTag("select", {
                            id: "size",
                            name: "size"}, [
                            Lib.createTag("option", {
                                value: "M"},
                                "M"),
                            Lib.createTag("option", {
                                value: "L"},
                                "L"),
                            ]),
                    ]),
                    Lib.createTag("div", {
                        class: "grid-2"}, [
                        Lib.createTag("button", {
                            class: "grid bold",
                            id: "price",
                            disabled: "true",
                            name: "price",
                            value: pizza.price},
                            `${pizza.price.toLocaleString(undefined, {minimumFractionDigits: 2})}€`),
                        Lib.createTag("button", {
                            class: "grid button",
                            id: "submit",
                            type: "button"},
                            "Ajouter"
                        )])])]));

        pizzaForm.querySelector("#size").addEventListener("change", (event) => {
            let priceNode = event.target.form.querySelector("#price");

            priceNode.innerHTML = (pizza.price + PRICE_MODS[event.target.value]).toLocaleString(undefined, {minimumFractionDigits: 2}) + "€"
            priceNode.value = pizza.price + PRICE_MODS[event.target.value];
        });

        pizzaForm.querySelector("#submit").addEventListener("click", (event) => {
            console.log("DBG", Lib.map(event.target.form.elements, (element) => element.name?.length > 0 ? [element.name, element.value] : []))
            let item = new Lib.Hash(pizza, new Lib.Hash(Lib.map(event.target.form.elements, (element) => element.name?.length > 0 ? [element.name, element.value] : [])))
            console.log("adding", item, "to the cart", item.length)
            addToCart(item)
        });

        return pizzaForm;
    }

    q("#season-pizza").appendChild(Lib.createTag("div", {
        class: "grid-1"}, [
        Lib.createTag("h2", {
            class: "grid underline"},
            "La pizza du moment"),
        pizzaTemplate(seasonPizza)]));
    q("#all-pizza").appendChild(Lib.createTag("div", {
        class: "grid-1 halfgap"}, [
        Lib.createTag("h2", {
            class: "grid underline"},
            "Les classiques"),
        ...pizzas.map(pizza => pizzaTemplate(pizza))]));
})