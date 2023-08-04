/*
 * ENI Project 2023
 * PizzaHub
 * Paul TANGUY
 * header.js
 */

import Lib from "/scripts/lib.js"

let lastPreHeader = document.querySelectorAll("preheader")?.[document.querySelectorAll("preheader").length-1]
let headerTag = (
    Lib.createTag("header", {
        class: "center-children",
        id: "header"},
        Lib.createTag("div", {
            class: "grid-3"}, [
            Lib.createTag("div", {
                class: "center-children"},
                "(>^w^)>"),
            Lib.createTag("div", {},
                Lib.createTag("img", {
                    class: "",
                    src: "/assets/test 1.png"})),
            Lib.createTag("div", {
                class: "center-children"},
                "<(^w^<)")])));

if (lastPreHeader) lastPreHeader.after(headerTag);
else document.body.firstChild.before(headerTag);