/**
 * Autor: Aestrada 
 * Fecha: 17 de mayo de 2015 
 * Descripcion: Se encarga de acciones antes de contactar al servidor
 */

function segValidaForm(){
	
	if($('#compania').val() == ""){
		
		$('#companiaD').addClass('has-danger');
		$('#compania').addClass('form-control-danger');
		document.getElementById("errorC").innerHTML = "Ingresa la compañia a la que perteneces";		
	}
	
	if($('#usuario').val() == ""){
		
		$('#usuarioD').addClass('has-danger');
		$('#usuario').addClass('form-control-danger');
		document.getElementById("errorU").innerHTML = "Ingresa tu usuario";
	}
	
	if($('#password').val() == ""){
		
		$('#passwordD').addClass('has-danger');
		$('#password').addClass('form-control-danger');
		document.getElementById("errorP").innerHTML = "Ingresa tu contraseña";
		
	}
	
}

$(document.ready(function(){
	
}));