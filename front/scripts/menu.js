(() => {
	console.log("menu.js loaded!");

	const appetizersResponse = {
		status: 200,
		body: [
			{
				name: "Salade de chèvre chaud",
				price: 12.50
			},
			{
				name: "Assiette de Charcuterie Fine Italienne",
				price: 16.80
			},
			{
				name: "Mozzarella di Bufala",
				price: 6.90
			},
			{
				name: "Salade de roquette, peccorino et artichauds de Sicile",
				price: 15.20
			}
		]
	}

	window.addEventListener("load", () => {
		(function notfetch() {
			let appetizersTag = document.getElementById("appetizers");

			appetizersTag.append(document.createElement("table"));
			appetizersResponse.body.forEach((appetizer) => {
				let tr = document.createElement("tr");
				let td1 = document.createElement("td");
				let td2 = document.createElement("td");

				td1.innerHTML = appetizer.name;
				td2.innerHTML = appetizer.price + "€";
				tr.append(td1, td2);
				appetizersTag.firstChild.append(tr);
			});
		})();
	});
})();