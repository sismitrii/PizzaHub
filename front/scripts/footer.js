/*
 * ENI Project 2023
 * PizzaHub
 * Paul TANGUY
 * footer.js
 */

import Lib from "/scripts/lib.js"

document.body.appendChild(
    Lib.createTag("footer", {
        class: "center-children"},
        Lib.createTag("div", {
            class: "grid-2"}, [
            Lib.createTag("div", {}, "'tis I, the footer"),
            Lib.createTag("div", {}, "'tis I, the footer"),
            Lib.createTag("div", {}, "'tis I, the footer"),
            Lib.createTag("div", {}, "'tis I, the footer")
        ])));