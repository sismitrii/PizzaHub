const takewaySection = document.querySelector("section.takeway .scroller");
const atRestaurantSection = document.querySelector("section.atRestaurant .scroller");

init();

async function init(){
    if (sessionStorage.getItem('token') == null){
        window.location.href="/views/authentification.html";
    }
    //Faire le get
    let orders = await getOrders();
    console.log(orders)

    //init  takewaySection
    initSection(orders.onlineOrders, true);

    //init restaurantSection
    initSection(orders.onSiteOrders, false);
}

async function getOrders(){
    let bearer = 'Bearer ' +sessionStorage.getItem('token');
    console.log(bearer);
    try {
        let res = await fetch(`http://127.0.0.1:8080/order/toPrepare`, {
            headers: 
            {
                'Authorization' : bearer,
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Method' : '*',
                'Access-Control-Allow-Headers' : "*",
                'Origin': 'http://localhost:5500/' 
            }
        });
        if (res.ok){
            return await res.json();
        }
    } catch (error){
        console.error(`Error : ${error}`);
    }
}

function initSection(onlineOrders, isOnline){
    onlineOrders.forEach(order => {
        let block = document.createElement('a');
        block.classList.add("orderBlock");
        block.href = `/views/${isOnline ? "kitchenOnlineOrder" : "kitchenRestaurantOrder"}.html?orderId=${order.orderId}`

        let title = document.createElement('h3');
        title.textContent = isOnline ? `Commmande pour ${order.customerName}` : `Table ${order.tableNumber}`;
        block.appendChild(title);

        let list = document.createElement('ul');;
        
        let orderList = groupMenuItemsByMenuItemId(order);

        orderList.forEach(item => {
            let element = document.createElement('ul');
            element.textContent = `${item.name} ${isOnline ? item.size : ""}${item.count > 1 ? "*"+item.count : ""}`;

            list.appendChild(element);
        });
        block.appendChild(list);

        let p = document.createElement('p');
        
        p.innerHTML = isOnline ? `SLOT : <span>${order.timeSlot}</span>` : order.actualStep //pour le moment nombre voir si on peut faire une enum ppour deal ça

        block.appendChild(p);

        if (isOnline){
            takewaySection.appendChild(block);
        } else {
            atRestaurantSection.appendChild(block);
        }
    });
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
          size : item.size, 
        };
      } else {
        // If the menuItemId already exists, increment the count
        groupedMenuItems[menuItemId].count++;
      }
    });

    return Object.values(groupedMenuItems);
}


function getCookie(name) {
    if (!document.cookie) {
      return null;
    }
  
    const xsrfCookies = document.cookie.split(';')
      .map(c => c.trim())
      .filter(c => c.startsWith(name + '='));
  
    if (xsrfCookies.length === 0) {
      return null;
    }
    return decodeURIComponent(xsrfCookies[0].split('=')[1]);
}