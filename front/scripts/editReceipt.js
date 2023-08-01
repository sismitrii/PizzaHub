const elementList = document.querySelector('.orderResume .item');
const priceList = document.querySelector('.orderResume .price');
const totalReceipt = document.querySelector('.totalReceipt span');
const editButton = document.querySelector('.editButton');

init();

editButton.addEventListener('click', event => {
    editOrder();
});

function findtableNumberOfPage(){
    let pageUrl = new URL(window.location.href);
    return pageUrl.searchParams.get('tableNumber');
}

async function init(){
    let tableNumber = findtableNumberOfPage();
    // recuperer l'order de la table
    let order = await getOrder(tableNumber);
    //Afficher tout les elements
    printElement(order.menuItems);
    //afficher tout les prix

    //afficher le  prix total
}

async function getOrder(tableNumber){
    try {
        let res = await fetch(`http://localhost:8080/onsite/order/table/${tableNumber}`);
        if (res.ok){
            return await res.json();
        }
    } catch (error){
        console.error(`Error : ${error}`);
    }
}

function printElement(orderItems){
    let totalPrice = 0;
    orderItems = groupMenuItemsByMenuItemId(orderItems);
    orderItems.forEach(item => {
        let itemName = document.createElement('li');
        itemName.textContent = `${item.name}${item.count > 1 ? "*"+item.count : ""}`;
        elementList.appendChild(itemName);

        let itemPrice = document.createElement('li');
        itemPrice.textContent = `${item.price.toLocaleString(undefined,{minimumFractionDigits : 2})}€`;
        priceList.appendChild(itemPrice);

        totalPrice += item.price;
    });
    totalReceipt.textContent = `${totalPrice.toLocaleString(undefined,{minimumFractionDigits : 2})}€`;
}


function groupMenuItemsByMenuItemId(orderItems) {
    const groupedMenuItems = {};
  
    orderItems.forEach(item => {
      const menuItemId = item.menuItemIId;
  
      if (!groupedMenuItems[menuItemId]) {
        groupedMenuItems[menuItemId] = {
          menuItemIId: menuItemId,
          name: item.name,
          price : item.price,
          count: 1,
        };
      } else {
        groupedMenuItems[menuItemId].count++;
        groupedMenuItems[menuItemId].price += item.price;
      }
    });

    return Object.values(groupedMenuItems);
}

async function editOrder(){
    try {
        let tableNumber = findtableNumberOfPage();
        let res = await fetch(`http://localhost:8080/onsite/order/table/edit`,{
            method:"POST",
            headers:{
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify(tableNumber)
            });
        if (res.ok){
            console.log("L'order a été supprimé")
            window.location.href = "/views/waiter.html";
        }
    } catch (error){
        console.error(`Error : ${error}`);
    }
}



