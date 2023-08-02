/*
** ENI Project 2023
** PizzaHub
** Paul Tanguy, Florian Guérin
** menu.js
*/

import Lib from "/scripts/lib.js"
import Api from "/scripts/apiSpoofer.js"

window.addEventListener("load", async () => {
	let q = document.querySelector.bind(document);
	let foodTypes = (await Api["menuItem/allCategory"]).filter(_foodType => _foodType != "PIZZA");

	q("#menu").appendChild(Lib.createTag("div", {
		class: "grid"}));
	q("#menu div").appendChild(Lib.createTag("h1", {
		class: "underline"}, "La Carte"));
	await foodTypes.forEach(async foodType => {
		let menuItems = await Api["menuItem/"+foodType]

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
