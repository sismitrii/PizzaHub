const headerOrderTitle = document.querySelector('.headerOrder h1');
const footerOrderParagraphe = document.querySelector('.footerOrder p');
const validateButton = document.querySelector('.validateButton');

init();
validateButton.addEventListener('click', event =>{
    validate(findPageAndOrderId().orderId)
});

function findPageAndOrderId(){
    const pageUrl = new URL(window.location.href);
    let pageData = {
        online :pageUrl.href.includes("OnlineOrder"),
        orderId : pageUrl.searchParams.get('orderId')
    }
    return pageData;
}

function init(){
    let pageData = findPageAndOrderId();
    if (pageData.online){
        initOnlineOrder(pageData.orderId);
    } else {
        initRestaurantOrder(pageData.orderId);
    }
}

async function initOnlineOrder(orderId){
    const orderList = document.querySelector('.orderList');
    let order = await getOrder(orderId, true);
    headerOrderTitle.textContent = `Commande pour ${order.customerName}`

    let menuItems = groupMenuItemsByMenuItemId(order.menuItems);
    menuItems.forEach(item => {
        let element = document.createElement('li');
        element.textContent = `${item.name} ${item.size} ${item.count > 1 ? "*"+item.count : ""}`;

        orderList.appendChild(element);
    })
}

async function initRestaurantOrder(orderId){

    const stepContainerBlock = document.querySelector(".stepContainer");
    // faire  la requete
    let order = await getOrder(orderId, false);
    console.log(order);
    headerOrderTitle.textContent = `Table ${order.tableNumber}`

    let categoryTab = ["ENTREE", "PLAT", "PIZZA", "DESSERT"];

    categoryTab.forEach(category => {
        let element = order.menuItems.filter(item => item.menuItemCategory == category);
        if (element != null && element.length > 0){
            let block = document.createElement('div');
            let subTitle = document.createElement('h2');
            subTitle.textContent = category.toLowerCase().charAt(0).toUpperCase() + category.toLowerCase().slice(1);; // mettre le 1er char en maj
            block.appendChild(subTitle);

            let elementList = document.createElement('ul');
            let menuItems = groupMenuItemsByMenuItemId(element);
            menuItems.forEach( item=> {

                let menuItemLi = document.createElement('li');
                menuItemLi.textContent = `${item.name}${item.count > 1 ? "*"+item.count : ""}`;
                elementList.appendChild(menuItemLi);
            });

            block.appendChild(elementList);

            stepContainerBlock.appendChild(block);
        }
    })
}

async function validate(orderId){
    // faire la requete
    try {
        let res = await fetch(`http://localhost:8080/order/validate`, {
            method :"POST",
            headers:{
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify(orderId)
        });
        if (res.ok){
            window.location.href = "/views/kitchen.html"
        }
    } catch (error){
        console.error(`Error : ${error}`);
    }
    
    // redirect
}


async function getOrder(orderId, isOnlineOrder){
    let orderRequest = {
        orderId:orderId,
        isOnlineOrder :isOnlineOrder
    }
    try {
        let res = await fetch(`http://localhost:8080/order/byOrderId`, {
            method :"POST",
            headers:{
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify(orderRequest)
        });
        if (res.ok){
            return await res.json();
        }
    } catch (error){
        console.error(`Error : ${error}`);
    }
}

function groupMenuItemsByMenuItemId(data) {
    const groupedMenuItems = {};
    // Loop through the menu items in the data
    data.forEach(item => {
      const menuItemId = item.menuItemIId;
  
      // If the menuItemId is not already in the groupedMenuItems object, create a new entry
      if (!groupedMenuItems[menuItemId]) {
        groupedMenuItems[menuItemId] = {  
          menuItemIId: menuItemId,
          name: item.name,
          count: 1,
          size: item.size
        };
      } else {
        // If the menuItemId already exists, increment the count
        groupedMenuItems[menuItemId].count++;
      }
    });

    return Object.values(groupedMenuItems);
}

