console.log('%c Welcome to V\'s console', 'background: #cc9900; color: white; display: block;');

const urlEmployees = "http://localhost:8080/EmployMe-0.0/api/employees";
const urlManagers = "http://localhost:8080/EmployMe-0.0/api/managers";

const onSubmit = (e) => {
    e.preventDefault();
    let firstName, lastName, email, password;
    firstName = document.querySelector('#firstName').value;
    lastName = document.querySelector('#lastName').value
    email = document.querySelector('#email').value
    password = document.querySelector('#password').value
    let body = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        password: password
    };
    let user = document.getElementById('toggle-user').checked;
    user ? user = "manager" : user = "employee";
    if (user == "manager"){
        sendMethod(urlManagers, body)
        document.querySelector('#sign-up-form').reset();
        setTimeout(() => {
            window.location.href = "http://localhost:8080/EmployMe-0.0/login";
        }, 2000)
    } else {
        sendMethod(urlEmployees, body)
        document.querySelector('#sign-up-form').reset();
        setTimeout( () => {
            window.location.href = "http://localhost:8080/EmployMe-0.0/login";
        }, 2000)
    }
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