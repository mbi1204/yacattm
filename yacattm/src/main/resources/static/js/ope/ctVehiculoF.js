/**
 * 
 */

$(document).ready(function() {
	$("#anio").mask("9999");
	
	console.log("antes del if ");
	console.log($( "#marca" ).val());
	
	/*console.log (document.getElementById("modelo").innerHTML);
	
	
	if  ( $( "#marca" ).val() != "null"){
		console.log("entro al ready");
		getModeloVeh();
	}*/
	
	
});


function getModeloVeh(){
	
	console.log("aqui entro");
	 var vcMarcaVeh = $( "#marca" ).val();
	 
	 console.log("marca->"  +  "	" + vcMarcaVeh);
	 
	 if (vcMarcaVeh == "null"){
			swal('Error!', "seleccione una marca valida", 'error');
	 } else {
		 $.ajax(	 
		 { url         : '/ope/ctModeloVehiculo/lista',
		   dataType    : "json",
		   contentType : "application/json",
		   data        : {	cMarca : vcMarcaVeh	},
		   type        : 'GET',
		   success     : 
		   function(data) {	

			   
			   $("#modelo").empty(); // <<<<<< No more issue here
			   
			   for ( var item in data) {
				   
				   var option = $(document.createElement('option'));
	               option.text(data[item].modelo);
	               option.val(data[item].modelo);               

	               $("#modelo").append(option);
				   
			   }
			   
			   
			   
		   }
		 });
	 }
	
}


