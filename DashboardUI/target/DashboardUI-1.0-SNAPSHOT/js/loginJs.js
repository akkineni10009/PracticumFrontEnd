/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function test()
{
  if(typeof(Storage)=='undefined')
  {
    alert("your browser does not support local storage system");
  }
  else {
    alert("supports local storage");
  }
}  
$(document).ready(function(){
    $("#submit").click(function(){
     
         var u=document.getElementById("username").value;
  var p=document.getElementById("password").value;        
  try{
    localStorage.clear();
  localStorage.setItem("userId",u);
   var empid=localStorage.getItem("userId");
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
        $.post("http://localhost:8080/DashboardServices/webresources/LoginService?username="+u+"&password="+p,
        function(data,status){   
            if(data=="sucess")
            {
                window.open("http://localhost:8080/DashboardUI/home.html","_self");
            }
            else
            {
                alert("login status"+data);
            }
        });
    });
});


