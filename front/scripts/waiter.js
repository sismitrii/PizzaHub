/*============================================================*/
/* ---------------------- Variables --------------------------*/
/*============================================================*/

const tableSelect = document.getElementById("tableSelect");
const formButtons = document.getElementById("formButtons");
const orderList = document.getElementById("orderList");
const inputSeats = document.getElementById("inputSeats");
const buttonReturnArrow = document.getElementById("buttonReturnArrow")
const sendOrder = document.getElementById("sendOrder");
const editReceipt = document.getElementById("editReceipt");


init();
tableSelect.addEventListener('change', event => {
    setOrderView(event.target.value);
})
inputSeats.addEventListener('change', event => {
    setSeatsNumber(tableSelect.value, event.target.value);
})
editReceipt.addEventListener('click', event => {
    location.href = `waiter-receipt.html?tableNumber=${tableSelect.value}`;
})

sendOrder.addEventListener('click', event => {
    setOrderToBePrepared(tableSelect.value);
})

/*============================================================*/
/* ------------------------- Init ----------------------------*/
/*============================================================*/
async function init(){
    initAddOptionToSelect();
    initButtons(await getMenuItemCategory(), true);
}

async function initAddOptionToSelect(){
    let tableNumber;
    try {
        let res = await fetch("http://localhost:8080/onsite/allTableNumber");
        if (res.ok){
            tableNumber = await res.json()
        }

        tableNumber.forEach(number => {
            let option = document.createElement("option");
            option.value = number;
            option.textContent = `${number}`;
            tableSelect.appendChild(option);
        });

    } catch (error){
        console.error(`Error : ${error}`)
        return "error"; // throw an error ?
    }
}

async function getMenuItemCategory(){
    let menuItemCategory = [];
    try {
        let res = await fetch("http://localhost:8080/menuItem/allCategory");
        if (res.ok){
            let category = await res.json()
            category.forEach(cat => {
                menuItemCategory.push({
                    name: cat,
                    value: cat
                })
            })
            return menuItemCategory;
        }
    } catch (error){
        console.error(`Error : ${error}`);
    }

}

async function initButtons(categoryButton, isCategory){
        while(formButtons.firstChild){
            formButtons.removeChild(formButtons.firstChild)
        }
        categoryButton.forEach(catName => {
            let button = document.createElement("button");
            button.type = "button";
            button.value = catName.value;
            button.textContent = catName.name;
            if (isCategory){
                buttonReturnArrow.style.opacity = "0%";
                button.addEventListener('click', event => {
                    changeButtons(event.target.value);
                });
            } else {
                buttonReturnArrow.style.opacity = "100%";
                buttonReturnArrow.addEventListener('click', async event => {
                    initButtons(await getMenuItemCategory(), true);
                });
                /*** Event Listener Add To Order ***/
                    button.addEventListener('click', event => {
                    addToOrder(event.target.value);
                })
            }
            formButtons.appendChild(button);
        })
}

/*============================================================*/
/* ------------------------- Event ---------------------------*/
/*============================================================*/

// to add to a add eventListener
async function setOrderView(tableNumber = tableSelect.value){
    let order;
    try {
        while(orderList.firstChild){
            orderList.removeChild(orderList.firstChild)
        }
        let res = await fetch(`http://localhost:8080/onsite/order/table/${tableNumber}`);
        if (res.ok && res.status != 204){
            order = await res.json();
            let orderNameAndCount = groupMenuItemsByMenuItemId(order);
            if (orderNameAndCount.length > 0){
                orderNameAndCount.forEach(x => {
                    let li = document.createElement("li");
                    li.textContent = x.name +( x.count >= 1 ? `*${x.count}` : "")
                    orderList.appendChild(li);
                    });
                editReceipt.removeAttribute('disabled');
                sendOrder.removeAttribute('disabled');
            } else {
                editReceipt.setAttribute('disabled', "true");
                sendOrder.setAttribute('disabled', "true");
            }

        } else {
            editReceipt.setAttribute('disabled', "true");
            sendOrder.setAttribute('disabled', "true");
        }
            inputSeats.removeAttribute('disabled');

            inputSeats.value = (order != undefined && order.seats != null )? order.seats : undefined;

    } catch (error){
        console.error(`Error : ${error}`)
        return "error"; // throw an error ?
    }
}

async function setSeatsNumber(tableNumber, seatNumber){
    let onSiteOrderRequest = {
        tableNumber:  tableNumber,
        seatNumber: seatNumber
    }

    try {
        let res = await fetch("http://localhost:8080/onsite/order/setSeatsNumber",{
            method:"POST",
            headers:{
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify(onSiteOrderRequest)
        });
        inputSeats.style.backgroundColor = "white";
        if (res.status == 201){
            console.log(`An order have been created in DB for table : ${tableNumber} for ${seatNumber} people`)
        } else if (res.status == 200) {
            console.log(`Order of table :${tableNumber} is now for ${seatNumber}people`)
        }

    } catch (error){
        console.error(`Error : ${error}`)
        return "error"; // throw an error ?
    }
}


async function changeButtons(menuItemCategory){
    let menuiItemssList = [];
    try {
        let res = await fetch(`http://localhost:8080/menuItem/${menuItemCategory}`);
        if (res.ok){
            let menuItems = await res.json();
            menuItems.forEach(item => {
                menuiItemssList.push({
                    name: item.name,
                    value: item.menuItemIId
                })
            });
            initButtons(menuiItemssList);
        }
    } catch (error){
        console.error(`Error : ${error}`);
    }
}

async function addToOrder(menuItemId){
    if (tableSelect.value != "--" && inputSeats.value != "" && inputSeats != 0){
        //faire  la requete
        let onSiteOrderRequest = {
            tableNumber: tableSelect.value,
            menuItemToAdd: menuItemId
        }
        try {
            let res = await fetch("http://localhost:8080/onsite/order/addMenuItem",{
                method:"POST",
                headers:{
                    'Accept': 'application/json',
                    'Content-type': 'application/json'
                },
                body: JSON.stringify(onSiteOrderRequest)
            });
            if (res.status == 201){
                console.log(`An item have been to the  order`)
            }
    
        } catch (error){
            console.error(`Error : ${error}`)
            return "error"; // throw an error ?
        }
        // appeller la methode  d'affichage setOrderView
        setOrderView()
    }else{
        inputSeats.style.backgroundColor = "red";
        console.log("Pas de table ou de couvert définis")
    }
}

async function setOrderToBePrepared(tableNumber){
    try {
        let res = await fetch(`http://localhost:8080/onsite/order/table/toPrepare`,{
            method:"POST",
            headers:{
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify(tableNumber)
            });
        if (res.ok){
            console.log("L'order a été envoyé en cuisine");
            alert(`La commande de la table ${tableNumber} a été envoyé en cuisine`)
        }
    } catch (error){
        console.error(`Error : ${error}`);
    }
}


/*============================================================*/
/* ----------------------- Function --------------------------*/
/*============================================================*/

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
}