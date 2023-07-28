/*
** ENI Project 2023
** PizzaHub
** Paul Tanguy, Florian Guérin
** lib.js
*/

export default {
    createTag(tagName, attributes={}, children=null) {
        let tag = document.createElement(tagName);

        Object.entries(attributes).forEach(([attribute, value]) => {
            tag.setAttribute(attribute, value)
        });
        if (children) {
            if (children.constructor.name === "String") tag.innerHTML = children;
            if (children.constructor.name === "Array") children.forEach(child => { tag.appendChild(child); });
            if (children.constructor.name.match(/^HTML.*Element$/)) tag.appendChild(children);
        }
        return tag
    }
}
