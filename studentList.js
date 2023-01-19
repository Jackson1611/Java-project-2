function main() {

	// Send a request to the server
	fetch("http://localhost:8080/First_WebApp_Examples/students")
		// Convert the server's JSON response to a JavaScript object
		.then(response => response.json())
		// Call the method which will display the data
		.then(studentList => printStudent(studentList));
}

function printStudent(studentList) {
for (var i = 0; i < studentList.length; i++) {
	console.log(studentList[i].lastname)
}

	var table = document.getElementById("showData");
	for (var i = 0; i < studentList.length; i++) {
		var row = `
		<tr>
        	<td>${studentList[i].id}</td>
        	<td>${studentList[i].firstname} </td>
        	<td>${studentList[i].lastname}</td>
        	<td>${studentList[i].streetaddress}</td>
        	<td>${studentList[i].postcode}</td>
        	<td>${studentList[i].postoffice}</td>
      	</tr>
      `;
		table.innerHTML += row;
	}
}

main(); 
