const takewaySection = document.querySelector("section.takeway");
const atRestaurantSection = document.querySelector("section.atRestaurant");

init();

function init(){
    //Faire le get
    let orders = getOrders();

    //init  takewaySection
    initTakewaySection(orders.onlineOrders);

    //init restaurantSection
    initRestaurantSection(orders.onSiteOrders);
}

async function getOrders(){
    
    try {
        let res = await fetch(`http://localhost:8080/order/toPrepare`);
        if (res.ok){
            return await res.json();
        }
    } catch (error){
        console.error(`Error : ${error}`);
    }
}

function initTakewaySection(onlineOrders){
    onlineOrders.forEach(order => {
        let block = document.createElement('a');
        block.classList.add("orderBlock");
        block.href = `/views/kitchenOnlineOrder.html?orderId=${order.orderId}` // TO SET

        let title = document.createElement('h3');
        title.textContent = `Table ${order.tableNumber}`;
        block.appendChild(title);

        let list = document.createElement('ul');;
        
        let orderList = groupMenuItemsByMenuItemId(order);

        orderList.forEach(item => {
            let element = document.createElement('ul');
            element.textContent = `${item.name}${item.count > 1 ? "*"+item.count : ""}`;

            list.appendChild(element);
        });
        block.appendChild(list);

        let slot = document.createElement('p');
        slot.innerHTML = `SLOT : <span>${order.timeSlot}</span>`

        block.appendChild(slot);

        takewaySection.appendChild(block);
    });
}

function initRestaurantSection(restaurantOrders){

}


function groupMenuItemsByMenuItemId(data) {
    const groupedMenuItems = {};
  
    // Loop through the menu items in the data
    data.menuItems.forEach(item => {
      const menuItemId = item.menuItemIId;
  
      // If the menuItemId is not already in the groupedMenuItems object, create a new entry
      if (!groupedMenuItems[menuItemId]) {
        groupedMenuItems[menuItemId] = {
          menuItemIId: menuItemId,
          name: item.name,
          count: 1,
        };
      } else {
        // If the menuItemId already exists, increment the count
        groupedMenuItems[menuItemId].count++;
      }
    });

    return Object.values(groupedMenuItems);