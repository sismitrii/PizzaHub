package fr.eni.pizzaHub.bll;

import java.util.Comparator;

import fr.eni.pizzaHub.bo.MenuItem;
import fr.eni.pizzaHub.bo.MenuItemCategory;

public class MenuItemComparator implements Comparator<MenuItem> {
    @Override
    public int compare(MenuItem item1, MenuItem item2) {
        // Define the order of categories: ENTREE, PLAT, PIZZA, DESSERT
        String[] categoryOrder = {"ENTREE", "PLAT", "PIZZA", "DESSERT"};
        
        int index1 = getCategoryIndex(item1.getMenuItemCategory(), categoryOrder);
        int index2 = getCategoryIndex(item2.getMenuItemCategory(), categoryOrder);

        return Integer.compare(index1, index2);
    }

    // Helper method to get the index of a category in the specified order
    private int getCategoryIndex(MenuItemCategory category, String[] categoryOrder) {
        for (int i = 0; i < categoryOrder.length; i++) {
            if (categoryOrder[i].equals(category.toString())) {
                return i;
            }
        }
        // If the category is not found in the specified order, return a large value
        // to place it at the end of the sorted list.
        return categoryOrder.length;
    }
}
