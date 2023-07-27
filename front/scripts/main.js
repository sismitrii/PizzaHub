// makes data unavailable by wrapping everything inside a self-called anonymous function
(() => {
	console.log("main.js loaded!");

	function displayHeaderAndFooter() {
		let header = document.getElementById("header");
		let footer = document.getElementById("footer");

		header.innerHTML = "I'm the mad header"
		footer.innerHTML = "Footer? You mean Soccerer, right?"
	}

	window.addEventListener("load", () => {
		displayHeaderAndFooter();
	});
})();