$(function(){
	var operation = "A"; //"A"=Adding; "E"=Editing

	var selected_index = -1; //Index of the selected list item

	var tbClients = localStorage.getItem("tbClients");//Retrieve the stored data

	tbClients = JSON.parse(tbClients); //Converts string to object

	if(tbClients == null) //If there is no data, initialize an empty array
		tbClients = [];

	function Add(){
		var client = JSON.stringify({
			RoomNo   : $("#txtRoomNo").val(),
			ReserveTime   : $("#txtReserveTime").val(),
			Name  : $("#txtName").val(),
			Phone : $("#txtContact").val(),
			Email : $("#txtEmail").val()
		});
		tbClients.push(client);
		localStorage.setItem("tbClients", JSON.stringify(tbClients));
		alert("The data was saved.");
		return true;
	}

	function Edit(){
		tbClients[selected_index] = JSON.stringify({
				RoomNo    : $("#txtRoomNo").val(),
				ReserveTime   : $("#txtReserveTime").val(),
				Name  : $("#txtName").val(),
				Phone : $("#txtContact").val(),
				Email : $("#txtEmail").val()
			});//Alter the selected item on the table
		localStorage.setItem("tbClients", JSON.stringify(tbClients));
		alert("The data was edited.")
		operation = "A"; //Return to default value
		return true;
	}

	function Delete(){
		tbClients.splice(selected_index, 1);
		localStorage.setItem("tbClients", JSON.stringify(tbClients));
		alert("Client deleted.");
	}

	function List(){		
		$("#tblList").html("");
		$("#tblList").html(
			"<thead>"+
			"	<tr>"+
			"	<th></th>"+
			"	<th>Rooms</th>"+
			"	<th>ReserveTime</th>"+
			"	<th>Name</th>"+
			"	<th>Contact No.</th>"+
			"	<th>Email</th>"+
			"	</tr>"+
			"</thead>"+
			"<tbody>"+
			"</tbody>"
			);
		for(var i in tbClients){
			var cli = JSON.parse(tbClients[i]);
		  	$("#tblList tbody").append("<tr>"+
									 	 "	<td><img src='edit.png' alt='Edit"+i+"' class='btnEdit'/><img src='delete.png' alt='Delete"+i+"' class='btnDelete'/></td>" + 
										 "	<td>"+cli.RoomNo+"</td>" + 
										"	<td>"+cli.ReserveTime+"</td>" + 
										 "	<td>"+cli.Name+"</td>" + 
										 "	<td>"+cli.Contact+"</td>" + 
										 "	<td>"+cli.Email+"</td>" + 
		  								 "</tr>");
		}
	}

	$("#frmCadastre").bind("submit",function(){		
		if(operation == "A")
			return Add();
		else
			return Edit();
	});

	List();

	$(".btnEdit").bind("click", function(){

		operation = "E";
		selected_index = parseInt($(this).attr("alt").replace("Edit", ""));
		
		var cli = JSON.parse(tbClients[selected_index]);
		$("#txtRoomNo").val(cli.RoomNo);
		$("#txtReserveTime").val(cli.ReserveTime);
		$("#txtName").val(cli.Name);
		$("txtContact").val(cli.Contact);
		$("#txtEmail").val(cli.Email);
		$("#txtRoomNo").attr("readonly","readonly");
		$("#txtName").focus();
	});

	$(".btnDelete").bind("click", function(){
		selected_index = parseInt($(this).attr("alt").replace("Delete", ""));
		Delete();
		List();
	});
});
