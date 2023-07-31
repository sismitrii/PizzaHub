/*
** ENI Project 2023
** PizzaHub
** Paul Tanguy, Florian Guérin
** pizza.js
*/

import Lib from "/scripts/lib.js"
import { _seasonPizza, _menuItems } from "/scripts/apiSpoofer.js"

window.addEventListener("load", () => {
    let q = document.querySelector.bind(document);;
    
    function updatePrice(e) {
        console.log(e);
        console.log("feur");
    }

    const PRICE_MODS = {
        "M": 0,
        "L": 3,
        "XL": 5
    }

    function pizzaTemplate(pizza) {
        let pizzaForm = (
            Lib.createTag("form", {
                class: "pizza-template grid-2"}, [
                Lib.createTag("img", {
                    id: "pizzaImage",
                    alt: `Photo de notre ${pizza.name}`,
                    src: pizza.imageUrl}),
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
                            Lib.createTag("option", {
                                value: "XL"},
                                "XL"),
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
                            `${pizza.price}€`),
                        Lib.createTag("button", {
                            class: "grid button",
                            id: "submit",
                            type: "button"},
                            "Ajouter"
                        )])])]));

        pizzaForm.querySelector("#size").addEventListener("change", (event) => {
            let priceNode = event.target.form.querySelector("#price");

            priceNode.innerHTML = pizza.price + PRICE_MODS[event.target.value] + "€"
            priceNode.value = pizza.price + PRICE_MODS[event.target.value];
        });

        pizzaForm.querySelector("#submit").addEventListener("click", (event) => {
            // let updatedPizza = new Map([...Object.entries(pizza), ...[...e.target.form.elements].filter(x => x != e.target).map(x => [x.name, x.value])])
            
            let newPizza = Lib.forceArray(Lib.map(event.target.form.elements, (element) => [element.name, element.value]), pizza);

            console.log(JSON.stringify(newPizza))
            console.log(newPizza)
        });

        return pizzaForm;
    }

    let pizzas = _menuItems.filter(menuItem => menuItem.itemCategory === "PIZZA");
    let seasonPizza = pizzas.find(pizza => pizza.name === _seasonPizza);

    pizzas = pizzas.filter(pizza => pizza !== seasonPizza);
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