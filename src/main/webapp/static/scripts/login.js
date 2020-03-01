console.log('%c Welcome to V\'s console', 'background: #cc9900; color: white; display: block;');

// const urlAuthoEmployee = "http://localhost:8080/EmployMe-0.0/api/employees";
const urlAuthoManager = "http://localhost:8080/EmployMe-0.0/api/authmanager";

const onSubmit = (e) => {
    e.preventDefault();
    let email, password;
    email = document.querySelector('#email').value
    password = document.querySelector('#password').value
    let body = {
        email: email,
        password: password
    };
    let user = document.getElementById('toggle-user').checked;
    user ? user = "manager" : user = "employee";
    if (user == "manager") {
        sendMethod(urlAuthoManager, body)
    } else {
        sendMethod(urlEmployees, body)
    }
}

const sendMethod = (url, body) => {

    let request = new XMLHttpRequest();
    request.open("POST", url);
    request.setRequestHeader('content-type', 'application/json');

    request.onreadystatechange = function () {
        if (request.status == 200) {
            let currentUser = request.responseText;
            console.log(typeof(currentUser));
            console.info('%c Form was submitted succesfully!!', 'background: green; color: white; display: block;');
            currentUser = JSON.parse(currentUser);
            console.log(currentUser);  
        } else {
            console.error("Form was not submitted succesfully...");
        }
    }
    request.send(JSON.stringify(body));
}


