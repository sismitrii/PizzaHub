/*
** ENI Project 2023
** PizzaHub
** Paul Tanguy, Florian Guérin
** main.js
*/

// makes data unavailable by wrapping everything inside a self-called anonymous function
(() => {
	console.log("main.js loaded!");

	function displayHeaderAndFooter() {
		let header = document.querySelector("header");
		let footer = document.querySelector("footer");

		header.innerHTML = "I'm the mad header"
		footer.innerHTML = "Footer? You mean Soccerer, right?"
	}

	window.addEventListener("load", () => {
		displayHeaderAndFooter();
	});
})();