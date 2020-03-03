console.log('%c Welcome to V\'s console', 'background: #cc9900; color: white; display: block;');

const urlAuthoEmployee = "http://localhost:8080/EmployMe-0.0/api/authemployee";
const urlAuthoManager = "http://localhost:8080/EmployMe-0.0/api/authmanager";

const onSubmit = (e) => {
    let user = document.getElementById('toggle-user').checked;
    e.preventDefault();
    let email, password;
    email = document.querySelector('#email').value.toLowerCase();
    password = document.querySelector('#password').value
    let body = {
        email: email,
        password: password
    };
    user ? user = "manager" : user = "employee";
    if (user == "manager") {
        sendMethod(urlAuthoManager, body, setSession, user);
    } else {
        sendMethod(urlAuthoEmployee, body, setSession, user);
    }
}

const sendMethod = (url, body, callback, user) => {

    let request = new XMLHttpRequest();
    // request.setRequestHeader('content-type', 'application/json');
    
    request.onreadystatechange = function () {
        if (request.readyState == 4 && request.status == 200) {
            console.info('%c Form was submitted succesfully!!', 'background: green; color: white; display: block;'); 
            callback(request.response, user);
        } else if(request.status == 401){
            alert("password don't match!");
            console.error("401...");
        } else if (request.status == 404){
            alert("That email doesn't exists!")
            console.error("404...");
        }
    }
    
    request.open("POST", url);
    request.send(JSON.stringify(body));
}

const setSession = (response, user) => {
    let currentUser = JSON.parse(response);

    if (user == "manager"){
        let session = {
            id: currentUser.managerId,
            firstname: currentUser.firstName,
            lastname: currentUser.lastName,
            email: currentUser.email
        }
        localStorage.setItem("session", JSON.stringify(session));
    } else {
        let session = {
            id: currentUser.employeeId,
            firstname: currentUser.firstName,
            lastname: currentUser.lastName,
            email: currentUser.email
        }
        localStorage.setItem("session", JSON.stringify(session));
    }

    if (user == "manager") {
        window.location.href = "http://localhost:8080/EmployMe-0.0/dashboardmanager";
    } else {
        window.location.href = "http://localhost:8080/EmployMe-0.0/dashboardemployee";
    }
    
}
