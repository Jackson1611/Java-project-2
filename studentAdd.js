function toMain() {
	location.href("http://localhost:8080/First_WebApp_Examples/studentListImproved.html");
}

function addStudent() {
	let url = "http://localhost:8080/First_WebApp_Examples/addStudent";
	var form = document.forms["formStudent"];
	var requestParameters =
		"id=" + form["id"].value +
		"&firstname=" + form["firstname"].value +
		"&lastname=" + form["lastname"].value +
		"&streetaddress=" + form["streetaddress"].value +
		"&postcode=" + form["postcode"].value +
		"&postoffice=" + form["postoffice"].value;
	let requestOptions = {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded" },
		body: requestParameters
	};
	fetch(url, requestOptions)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw "HTTP status code is " + response.status;
			}
		})
		.then(status => processResponseStatus(status))
		
		
}

function processResponseStatus(status) {
	console.log(status.errorCode);
	if (status.errorCode === 0) {
		alert("Student added.");
	}
	else if (status.errorCode === 1) {
		alert("Nothing added. The student id is already in use");
	} else {
		alert("Nothing added. An unknown error occurred.");
	}
}
addStudent()

