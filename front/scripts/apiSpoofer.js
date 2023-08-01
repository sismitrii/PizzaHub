/*
** ENI Project 2023
** PizzaHub
** Paul Tanguy, Florian Guérin
** apiSpoofer.js
*/

export const _foodTypes = [
	"ENTREE",
	"PLAT",
	"DESSERT",
	"BEVERAGE",
	"PIZZA"
];

export const _menuItems = [
	{
		name: "Margarita",
		price: 9.90,
		itemCategory: "PIZZA",
		imageUrl: "/assets/pizza.png",
		base: "Tomate"
	},
	{
		name: "Hawaienne",
		price: 13.90,
		itemCategory: "PIZZA",
		imageUrl: "/assets/pizza.png",
		base: "Crème"
	},
	{
		name: "Indienne",
		price: 14.40,
		itemCategory: "PIZZA",
		imageUrl: "/assets/pizza.png",
		base: "Curry"
	},
	{
		name: "4 Fromages",
		price: 10.50,
		itemCategory: "PIZZA",
		imageUrl: "/assets/pizza.png",
		base: "Crème"
	},
	{
		name: "Escargots de bourgogne",
		price: 14.50,
		itemCategory: "ENTREE"
	},
	{
		name: "Crème brulée",
		price: 6.00,
		itemCategory: "DESSERT"
	},
	{
		name: "Poulet accompagné de frites",
		price: 15.75,
		itemCategory: "PLAT"
	},
	{
		name: "Coca",
		price: 3.00,
		itemCategory: "BEVERAGE"
	},
	{
		name: "Ice tea",
		price: 3.00,
		itemCategory: "BEVERAGE"
	}
];

export const _seasonPizza = "Hawaienne";