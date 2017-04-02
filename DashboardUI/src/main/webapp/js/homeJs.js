/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$( document ).ready(function() {
       
    });
 
    $( window ).on( "load", function() {
        try{
 var empid=localStorage.getItem("userId");
 document.getElementById("empid").innerHTML=empid;

}
  catch(e)
  {
    if(e== QUOTA_EXCEEDED_ERR)
    {
      alert("Local storage is full");
    }
    else {
      alert("Error in saving the data");
    }
  }
         $.post("http://localhost:8080/DashboardServices/webresources/FetchProject?userId="+empid,
        function(data,status){      
        var obj = JSON.parse(data);
        var projects=obj._id.toString();
	var splitProjects=projects.split(",");
	for(i=0;i<splitProjects.length;i++) {
		 //appends the projects to the dropdown box
		 var choiceSelection=document.createElement('option');
		 choiceSelection.setAttribute('value', splitProjects[i]);
     		choiceSelection.innerHTML=splitProjects[i];
		 document.getElementById('projects').appendChild(choiceSelection);
	 }                
        });
        console.log( "window loaded" );
    });
    $(document).ready(function(){
    $("#selectproject").click(function(){
  var projectname=document.getElementById("projects").value;   
  try{
   var empid=localStorage.getItem("userId");
   localstorage.setItem("projectname",projectname);
}
catch(e)
{
  if(e== QUOTA_EXCEEDED_ERR)
  {
    console.log("Local storage is full");
  }
  else {
    console.log("Error in saving the data");
  }
}      
        $.post("http://localhost:8080/DashboardServices/webresources/fetchrole?userId="+empid+"&projectname="+projectname,
        function(data,status){   
            var obj = JSON.parse(data);
        var roles=obj._id.toString();
	var splitRoles=roles.split(",");
	for(i=0;i<splitRoles.length;i++) {
		 //appends the projects to the dropdown box
		 var choiceSelection=document.createElement('option');
		 choiceSelection.setAttribute('value', splitRoles[i]);
     		choiceSelection.innerHTML=splitRoles[i];
		 document.getElementById('roles').appendChild(choiceSelection);
	 } 
        });
    });
});
$(document).ready(function(){
    $("#selectrole").click(function(){
  var rolename=document.getElementById("roles").value;   
  
  try{
      localStorage.setItem("rolename",rolename);
}
catch(e)
{
  if(e== QUOTA_EXCEEDED_ERR)
  {
    console.log("Local storage is full");
  }
  else {
    console.log("Error in saving the data");
  }
}      
    alert(rolename);
    });
});
    
