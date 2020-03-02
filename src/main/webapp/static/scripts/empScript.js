console.log('%c Welcome to V\'s console', 'background: #cc9900; color: white; display: block;');
// const currentUser = JSON.parse(localStorage.getItem("session"));
const url = "http://localhost:8080/EmployMe-0.0/api/reimbursements";
const currentUser = { 
    id: 1,
    firstname: "Vytautas",
    lastname: "Klimavicius",
    email: "vyklimavicius@gmail.com",
}

// window.onload = () => {
//    if (localStorage.getItem("session") === null){
//         window.location.href = "http://localhost:8080/EmployMe-0.0/login";
//    }
//    let currentUser = JSON.parse(localStorage.getItem("session"));
//    let name = document.querySelector('#user-name');
//    name.innerText = "Hello, " + currentUser.firstname + "!";
//    console.log('%c Load success!', 'background: green; color: white; display: block;');
// }

window.onload = () => {
// if (localStorage.getItem("session") === null){
//     window.location.href = "http://localhost:8080/EmployMe-0.0/login";
// }
//    let currentUser = {
//        firstname: "Vytautas",
//        lastname: "Klimavicius",
//        email: "vyklimavicius@gmail.com",
//    }
   let name = document.querySelector('#user-name');
   name.innerText = "Hello, " + currentUser.firstname + "!";
   console.log('%c Load success!', 'background: green; color: white; display: block;');
}

const showProfile = () => {
    // let currentUser = {
    //     firstname: "Vytautas",
    //     lastname: "Klimavicius",
    //     email: "vyklimavicius@gmail.com",
    // }
    let mainCard = document.querySelector('#main-card');
    mainCard.innerHTML = "";
    mainCard.innerHTML = `<div class="card" style="height: 80vh; border: 1px solid #cc9900; box-shadow: 5px 10px antiquewhite;">
            <h3 style="margin-top: 2vh; text-align: center; font-size: 3vw; color:#cc9900;">Profile</h3>
            <div class="card-body">
                <h5 class="card-title" style="text-align: center; font-size: 2vw; color:#cc9900;">Firstname:</h5>
                <h6 class="card-subtitle mb-2 text-muted" style="text-align: center;">${currentUser.firstname}</h6>
                <h5 class="card-title" style="text-align: center; font-size: 2vw; color:#cc9900;">Lastname:</h5>
                <h6 class="card-subtitle mb-2 text-muted" style="text-align: center;">${currentUser.lastname}</h6>
                <h5 class="card-title" style="text-align: center; font-size: 2vw; color:#cc9900;">Email:</h5>
                <h6 class="card-subtitle mb-2 text-muted" style="text-align: center;">${currentUser.email}</h6>
                <h5 class="card-title" style="text-align: center; font-size: 2vw; color:#cc9900;">Role:</h5>
                <h6 class="card-subtitle mb-2 text-muted" style="text-align: center;">Employee</h6>
                <div style="margin-top: 7vh; text-align: center;">
                    <button type="button" class="btn btn-warning" style="color: #cc9900; font-size: 2vw; background-color: antiquewhite; border-color: antiquewhite;">Update</button>
                    <button type="button" class="btn btn-warning" style="color: #cc9900; font-size: 2vw; background-color: antiquewhite; border-color: antiquewhite;">Delete</button>
                </div>
            </div>
        </div>`

}

const addReimbursement = () => {
    let mainCard = document.querySelector('#main-card');
    mainCard.innerHTML = "";
    mainCard.innerHTML = `<form id="reimbursement-form" onsubmit="onSubmit(event)" style="height: 80vh; border: 1px solid #cc9900; box-shadow: 5px 10px antiquewhite;">
            <h3 style="margin-top: 2vh; text-align: center; font-size: 3vw; color:#cc9900;">Add reimbursement</h3>
            <br>
            <div class="card-title" style="text-align: center;">
                <label class="card-title" style="margin-top: 6vh; width: 20vw; height: 2vh; font-size: 2vw; color:#cc9900;" for="reimbursement">Please type Amount $:</label>
                <br>
                <input style="margin-top: 3vh; width: 20vw; height: 4vh; font-size: 1vw; border-color: antiquewhite;" class="form-control-sm"
                    id="reimbursement" type="number" name="reimbursement" step="0.01" placeholder="two decimal places expected" required>
            </div>
            <div class="form-group" style="text-align: center;">
                <input
                    style="margin-top: 10px; width: 10vw; height: 8vh; font-size: 2vw; color: #cc9900; background-color: antiquewhite; border-color: antiquewhite;"
                    class="btn btn-primary" type="submit">
            </div>
            <div class="card-title" style="text-align: center;">
                <label class="card-title" style="margin-top: 6vh; width: 20vw; height: 2vh; font-size: 2vw; color:#cc9900;"
                    for="reimbursement">Status:</label>
                <br>
                <h3 style="margin-top: 2vh; text-align: center; font-size: 3vw; color:#cc9900;">Pending</h3>
            </div>
        </form>`

}

const viewReimbursements = () => {
    let mainCard = document.querySelector('#main-card');
    mainCard.innerHTML = "";
    mainCard.innerHTML = `<div class="card" style="height: 80vh; border: 1px solid #cc9900; box-shadow: 5px 10px antiquewhite;">
    <h3 style="margin-top: 2vh; text-align: center; font-size: 3vw; color:#cc9900;">Reimbursements</h3>
    </div>`
}

const logOut = () => {
    localStorage.clear();
    window.location.href = "http://localhost:8080/EmployMe-0.0/login";
}

// controller functions 

const onSubmit = (e) => {
    e.preventDefault();
    let reimbursement = document.querySelector('#reimbursement').value;
    let body = {
        employeeId: currentUser.id,
        reimbursement: reimbursement,
        status: "pending",
        managerId: 1
    }
    document.querySelector('#reimbursement').reset();
    sendMethod(url, body)
}

const sendMethod = (url, body) => {

    let request = new XMLHttpRequest();

    request.onreadystatechange = function () {
        if (request.status == 201) {
            console.log(request.responseText);
            console.info('%c Form was submitted succesfully!!', 'background: green; color: white; display: block;');
        } else {
            console.error("Form was not submitted succesfully...");
        }
    }
    request.open("POST", url);
    request.setRequestHeader('content-type', 'application/json');
    request.send(JSON.stringify(body));
}