/*
** ENI Project 2023
** PizzaHub
** Paul Tanguy, Florian Guérin
** menu.js
*/

import Lib from "/scripts/lib.js"
import {_foodTypes, _menuItems} from "/scripts/apiSpoofer.js"

window.addEventListener("load", () => {
	let q = document.querySelector.bind(document);
	console.log("menu.html loaded!");
	let foodTypes = _foodTypes.filter(_foodType => _foodType != "PIZZA");

	q("#menu").appendChild(Lib.createTag("div", {
		class: "grid"}));
	q("#menu div").appendChild(Lib.createTag("h1", {
		class: "underline"}, "La Carte"));
	foodTypes.forEach(foodType => {
		let menuItems = _menuItems.filter(_menuItem => _menuItem.itemCategory == foodType);

		q("#menu div").appendChild(Lib.createTag("div", {
			class: "container"},
			Lib.createTag("h2", {
				class: "inline underline"},
				foodType)));
		menuItems.forEach(menuItem => {
			q("#menu div").appendChild(
				Lib.createTag("div", {}, [
					Lib.createTag("div", {
						class: "align-left"},
						`- ${menuItem.name}`),
					Lib.createTag("div", {
						class: "align-right bold"},
						`${menuItem.price}€`)])
			)
		});
	});
});
