
var selectedProject; //stores the selected project
var selectedRole; //stores the selected role
var selectedEnity; //stores the selected entity
var selectedInstance; //stores the selected instance

//This function is used in  Home page - body tag
function onEntry(){
	//Assumption the response of the fetch Project query
	var projects="Project1,Project2,Project3,Joy";
	var splitProjects=projects.split(",");
	for(i=0;i<splitProjects.length;i++) {
		 //appends the projects to the dropdown box
		 var choiceSelection=document.createElement('option');
		 choiceSelection.setAttribute('value', splitProjects[i]);
     		choiceSelection.innerHTML=splitProjects[i];
		 document.getElementById('projects').appendChild(choiceSelection);
	 }
}
//This function is used in  Home page - body tag
function onSelectProject(){
	var tempProject = document.getElementById("projects");
	selectedProject = tempProject.options[tempProject.selectedIndex].text;
	//alert(selectedProject);
	//stores the selected value in the browser storage
	localStorage.setItem("selectedProject",selectedProject);
	//enable the select role button
	document.getElementById("selectrole").disabled = false;
	//Assumption the response of the fetch Project query
	var roles="Roles,R2,Joy";
	var splitRoles=roles.split(",");
	for(i=0;i<splitRoles.length;i++) {
		 //appends the projects to the dropdown box
		 var choiceSelection=document.createElement('option');
		 choiceSelection.setAttribute('value', splitRoles[i]);
     		choiceSelection.innerHTML=splitRoles[i];
		 document.getElementById('roles').appendChild(choiceSelection);
	 }
}

//This function is used in  Home page - body tag
function onSelectRole(){
	var tempRole = document.getElementById("roles");
	selectedRole = tempRole.options[tempRole.selectedIndex].text;
	//alert(selectedRole);
	//stores the selected value in the browser storage
	localStorage.setItem("selectedRole",selectedRole);
	
}
//This function is used in CreateInstances in the body tag

function getEntities() {

	selectedRole=localStorage.getItem("selectedRole");
	selectedProject=localStorage.getItem("selectedProject");
	//alert(selectedProject+selectedRole);
	//Assumption the response of fetch entity query
	var selectEntity="Entity1,Entity2,Entity2,Entity3,Entity2,Entity3,Entity2,Entity3,Entity2,Entity3,Entity2,Entity3,Entity2,Entity3";
    var splitEnity=selectEntity.split(",");

    for(i=0;i<splitEnity.length;i++) { //creates a radio button

    var choiceSelection = document.createElement('input');
    var choiceLabel = document.createElement('label');
    var linebreak = document.createElement('br');
    choiceSelection.setAttribute('type', 'radio');
    choiceSelection.setAttribute('name', 'entity');
		choiceSelection.setAttribute('value', splitEnity[i]);

    choiceLabel.innerHTML=splitEnity[i];
    choiceLabel.setAttribute('for', splitEnity[i]);

    document.getElementById('entityName').appendChild(choiceSelection);
    document.getElementById('entityName').appendChild(choiceLabel);
    document.getElementById('entityName').appendChild(linebreak);
    }
}

//This function is used in CreateInstances in the submit button

function entityOnClick() {


var entityNames = document.getElementsByName('entity');
var flag =1;
for(var i = 0; i < entityNames.length || flag==1; i++){

    if(entityNames[i].checked){
        selectedEnity = entityNames[i].value;
        alert("Selected Entity:" +selectedEnity);
				  flag=0;
				//stores the selected value in the browser storage
				localStorage.setItem("selectedEnity",selectedEnity );
				//window.location="ShowInstances.html";
    }
		//No option is selected
    if((entityNames.length -1 ==i) && (flag==1)){
    alert("Please select an entity and then press submit");
    }


}
// moves to the next page
if(flag==0)	{
    window.location="ShowInstances.html";
		alert("Selected Enti");
	}

}

//This function is used in ShowInstances to enable the edit button

function enableEdit() {
document.getElementById("editInstance").disabled = false;
}

//This function is used in ShowInstances in the body tag

function getInstances() {
	selectedEnity=localStorage.getItem("selectedEnity");

//assumption that the response object will be in this format
var instances = [
		{
				"ID": "1",
				"Name": "I1",
				"Category": "x",
		},
		{
			"ID": "2",
			"Name": "I2",
			"Category": "Y",
		},
		{
			"ID": "3",
			"Name": "I3",
			"Category": "z",
		}
		,
		{
			"ID": "2",
			"Name": "I2",
			"Category": "Y",
		},
		{
			"ID": "3",
			"Name": "I3",
			"Category": "z",
		},
		{
			"ID": "2",
			"Name": "I2",
			"Category": "Y",
		},
		{
			"ID": "3",
			"Name": "I3",
			"Category": "z",
		}
		,
		{
			"ID": "2",
			"Name": "I2",
			"Category": "Y",
		},
		{
			"ID": "3",
			"Name": "I3",
			"Category": "z",
		},
		{
			"ID": "2",
			"Name": "I2",
			"Category": "Y",
		},
		{
			"ID": "3",
			"Name": "I3",
			"Category": "z",
		}
		,
		{
			"ID": "2",
			"Name": "I2",
			"Category": "Y",
		},
		{
			"ID": "3",
			"Name": "I3",
			"Category": "z",
		}
]

// EXTRACT VALUE FOR HTML HEADER.
var col = [];
for (var i = 0; i < instances.length; i++) {
		for (var key in instances[i]) {
				if (col.indexOf(key) === -1) {
						col.push(key);
				}
		}
}

// CREATE DYNAMIC TABLE.
var table = document.createElement("table");

// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

var tr = table.insertRow(-1);                   // TABLE ROW.

for (var i = 0; i < col.length; i++) {
		var th = document.createElement("th");      // TABLE HEADER.
		if(i==-1)
		{ th.innerHTML = "Select";}
		else
		{th.innerHTML = col[i];}
		tr.appendChild(th);
}

// ADD JSON DATA TO THE TABLE AS ROWS.
for (var i = 0; i < instances.length; i++) {

		tr = table.insertRow(-1);

		for (var j = 0; j < col.length; j++) {
				if(j==0) {
					
					var tabCell = tr.insertCell(-1);
					//var instanceID="instanceName"+i+j;
					var radioSelection = document.createElement('input');	
					var radioLabel = document.createElement('label');
					
					radioSelection.setAttribute('type', 'radio');
   				 	radioSelection.setAttribute('name', 'instances');
   				 	radioSelection.setAttribute('onclick', 'enableEdit();');
   				 	//radioSelection.setAttribute('id','instanceID');
  					radioSelection.setAttribute('value',instances[i][col[j]]);
  					
					tabCell.appendChild(radioSelection);
					radioLabel.innerHTML = instances[i][col[j]];
					tabCell.appendChild(radioLabel);
				}
				else {
				var tabCell = tr.insertCell(-1);
				tabCell.innerHTML = instances[i][col[j]];
				}
				
		}
}

// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
var divContainer = document.getElementById("showData");
divContainer.innerHTML = "";
divContainer.appendChild(table);
}

//This function is used in ShowInstances in the EditInstance button

function onEditInstance() {

	
var instanceNames = document.getElementsByName('instances');
for(var i = 0; i < instanceNames.length; i++){

    if(instanceNames[i].checked){
        selectedInstance = instanceNames[i].value;
        alert("Selected Instance:" +selectedInstance);
	selectedProject=localStorage.getItem("selectedProject");
	alert(selectedProject);
	selectedRole=localStorage.getItem("selectedRole");
	alert(selectedRole);
	selectedEnity=localStorage.getItem("selectedEnity");
	alert(selectedEnity);
	//stores the selected value in the browser storage
	localStorage.setItem("selectedInstance",selectedInstance );
	//should move to EditInstance.html
	window.location="basic.html";
    }

  }

}
