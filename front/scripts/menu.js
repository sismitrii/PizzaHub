
	console.log("menu.js loaded!");

	const qS = document.querySelector.bind(document);
    const cE = document.createElement.bind(document);

	const c_menuItems = [{
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
	}]
	const c_itemCategories = [
		"ENTREE",
		"PLAT",
		"DESSERT",
		"BEVERAGE",
		"PIZZA"
	]

	function appendNodeRecursive(rootNode, nodes) {
		if (nodes.constructor.name != "Array" ) return rootNode;
		if (nodes.length <= 0) return rootNode;

		rootNode.append(appendNodeRecursive(nodes.shift(), nodes));
		return rootNode;
	}

	window.addEventListener("load", () => {
		(function notfetch() {
			let itemCategories = c_itemCategories.filter(category => category != "PIZZA");
			let menuItems = c_menuItems.filter(menuItem => menuItem.type in itemCategories);

			qS("#menu").append(cE("table"));
			qS("#menu table").append(cE("thead"));
			qS("#menu table").append(cE("tbody"));
			qS("#menu table thead").append(cE("tr"));
			qS("#menu table thead tr").append(cE("th"));
			qS("#menu table thead tr th").innerHTML = "La Carte"

			itemCategories.forEach((foodType) => {
				
				let foodTypeRow = ce("tr");
				foodTypeRow.append(ce("td"));
				foodTypeRow.firstchild.append(ce(`div id="${foodType}"`));

				let menuItemTable = ce("table");
				let menuItemTableHead = ce("thead");
				let menuItemTableHeadRow = ce("th");
				qs(`#${foodType}`).append(ce("table"));
				qs(`#${foodType}.table`).append(ce("thead"));
				qs(`#${foodType}.table.thead`).append(ce("tr"));
				qs(`#${foodType}.table.thead.tr`).append(ce("th"));
				qs(`#${foodType}.table.thead.tr.th`).innerHTML = foodType;
				qs(`#${foodType}.table`).append(ce("tbody"));


				menuItems.filter(menuItem => menuItem.type == foodType).forEach((menuItem) => {
					let menuItemRow = cr("tr");
					let menuItemName = cr("td");
					let menuItemPrice = cr("td");
					
					menuItemName.innerHTML = menuItem.name;
					menuItemPrice.innerHTML = menuItem.price + '€';
					menuItemRow.append(menuItemName, menuItemPrice);
					menuItemTableBody.append(menuItemRow);
				});
				qs("#menu.table.tbody").append(row);
			});
		})();
	});
