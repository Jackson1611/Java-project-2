function checkFunction(i){
	 if (confirm("Are you sure?") == true) {
        deleteStudent(i);
    } else {
        alert("Cancelled")
    }
}

function deleteStudent(i) {
	let url = "http://localhost:8080/First_WebApp_Examples/deleteStudent";
	var form = document.forms["formStudent"];
	var requestParameters = "id=" + i;
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
		.then(status => process(status))
		
}
function process(status) {
	console.log(status.errorCode);
	if (status.errorCode === 0) {
		alert("Student added.");
	}
	else if (status.errorCode === 1) {
		alert("Nothing deleted. The student id is already in use");
	} else {
		alert("Nothing deleted. An unknown error occurred.");
	}
}

deleteStudent();

function toMain() {
	location.href("http://localhost:8080/First_WebApp_Examples/studentListImproved.html");
}


