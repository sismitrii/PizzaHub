/*
 * ENI Project 2023
 *   PizzaHub
 * Paul TANGUY
 *   nav.js
 */

import Lib from "/scripts/lib.js"

const routes = {
    menu: ["menu", "home", "homePage", "index", "acceuil"],
    pizza: ["pizzas"],
    cart: ["cart", "pannier", "basket", "commande", "order"]
}

const q = document.querySelector.bind(document);

export default new Proxy({}, {
    get(target, key, receiver) {
        let displayName = Lib.pretty(key);

        if (key[0] === "$") {
            key = key.slice(1);
            displayName = "←";
        }
        console.log("key", key)
        let route = `/views/${Object.entries(routes).filter(([k, v]) => v.includes(key))?.[0]?.[0]}.html`

        console.log(route)
        if (route === "/views/undefined.html") return false;
        if (!q("nav"))
            document.body.firstChild.before(
                Lib.createTag("nav", {
                    class: "center-children"},
                    Lib.createTag("div", {
                        class: "grid-3"})))

        let linkTag = (
            Lib.createTag("a", {
                id: `${key}NavButton`,
                class: "button",
                href: route},
                displayName));
        
        q("nav>div").appendChild(linkTag)
        if (q("nav>div").children.length %3 == 1)
        q("nav>div").appendChild(Lib.createTag("div"));
        return true;
    },

    set(target, key, value, receiver) {}
});