/**
 * Autor: Aestrada 
 * Fecha: 17 de mayo de 2015 
 * Descripcion: Se encarga de acciones antes de contactar al servidor
 */

function segValidaForm(){
	
	//Recupera los valores de las cajas de texto
	var cCompania = $('#compania').val();
	var cUsuario  = $('#usuario').val();
	var cPassword = $('#password').val();
	
	if(cCompania == "" && cUsuario == "" && cPassword == ""){
		
		$('#companiaD').addClass('has-danger');
		$('#compania').addClass('form-control-danger');
		document.getElementById("errorC").innerHTML = "Ingresa la compañia a la que perteneces";
		
		$('#usuarioD').addClass('has-danger');
		$('#usuario').addClass('form-control-danger');
		document.getElementById("errorU").innerHTML = "Ingresa tu usuario";
		
		$('#passwordD').addClass('has-danger');
		$('#password').addClass('form-control-danger');
		document.getElementById("errorP").innerHTML = "Ingresa tu contraseña";
		
		return;
		
	} else if($('#compania').val() == ""){
		
		$('#companiaD').addClass('has-danger');
		$('#compania').addClass('form-control-danger');
		document.getElementById("errorC").innerHTML = "Ingresa la compañia a la que perteneces";
		
		return;
		
	} else if($('#usuario').val() == ""){
		
		$('#usuarioD').addClass('has-danger');
		$('#usuario').addClass('form-control-danger');
		document.getElementById("errorU").innerHTML = "Ingresa tu usuario";
		
		return;
		
	} else if($('#password').val() == ""){
		
		$('#passwordD').addClass('has-danger');
		$('#password').addClass('form-control-danger');
		document.getElementById("errorP").innerHTML = "Ingresa tu contraseña";
		
		return;
		
	}
	
	$('#formLogin').submit();
	
}

$( document ).ready(function() {
    console.log(document.getElementById("message").attribute);
    
    if(document.getElementById("message") != ""){
		swal('Oops...',$('#message').val(),'error');
	}
});
