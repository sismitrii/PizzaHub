const loginButton = document.getElementById('loginButton');
const usernameInput = document.getElementById('username');
const passwordInput = document.getElementById('password');

loginButton.addEventListener("click", event => {
    getToken();;
})

async function getToken(){
    let credential = {
        username: usernameInput.value,
        password: passwordInput.value
    }
    try {
        let res = await fetch(`http://localhost:8080/auth/token`,{
            method:"POST",
            headers:{
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify(credential)
            });
        if (res.ok){
            let token = await res.json();
            console.log(token.token);
            sessionStorage.setItem("token", token.token);
            window.location.href ="/views/choose.html";
        }
    } catch (error){
        console.error(`Error : ${error}`);
    }
}