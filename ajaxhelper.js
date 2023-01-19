function getDataFromServer(url, printStudent) {
	fetch(url)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw "HTTP status code is " + response.status;
			}
		})

		.then(studentList => printStudent(studentList))
		.catch(errorText => alert("getDataFromServer failed: " + errorText));


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
        	<td >
        	<button onClick='checkFunction(${studentList[i].id});'>Delete</button>
        	</td>
      	</tr>
      `;
		table.innerHTML += row;
	}
}

