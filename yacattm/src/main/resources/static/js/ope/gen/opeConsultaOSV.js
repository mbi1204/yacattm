/**
 * Autor: Aestrada Fecha: 11 de julio de 2017 Descripcion: Script para pantalla
 * de Consulta de Orden de Servicio
 */


function filtro(filtro){
	
	$("input:checkbox").each(
			function() {
				if(filtro["0"].id == $(this)["0"].id){
					//Muestra el input
					muestraCampo(filtro["0"].id);
				}else{
					$(this).prop('checked' , false);
				}
		    }
	);
	
}

function muestraCampo(tipo){
	
	switch(tipo) {
		case "fecha":
			$( "#pmtsBus" ).html(
					"<div class='row'>"
					+ "<div class='input-group date col-md-6 mb-1 mb-sm-0' id='datetimepicker'>"
					+ "<span class='input-group-addon'>"  
					+ "<span class='fa fa-calendar' aria-hidden='true'></span>"
					+ "</span>"
					+ "<input id='fechaAntes' type='text' class='form-control' placeholder='De:' />" 
					+ "</div>"					
					+ "<div class='input-group date col-md-6 mb-1 mb-sm-0' id='datetimepicker'>"
					+ "<span class='input-group-addon'>"  
					+ "<span class='fa fa-calendar' aria-hidden='true'></span>"
					+ "</span>"
					+ "<input id='fechaDespues' type='text' class='form-control' placeholder='Hasta:' />" 
					+ "</div>"						
				    + "</div>" );
			
			$('#buscar').attr("value","1");
		break;
		
		case "matricula":
			$( "#pmtsBus" ).html(
					"<div class='row'>"
					+ "<div class='input-group col-md-12 mb-1 mb-sm-0'>"
					+ "<span class='input-group-addon' aria-hidden='true' id='basic-addon1'></span>"
					+ "<input type='text' class='form-control' id='matriculaB' placeholder='Matr&iacute;cula' aria-describedby='basic-addon1' />"
					+ "</div>"
					+ "</div>" );
			$('#buscar').attr("value","2");
        break;
        
		case "nombre":
			$( "#pmtsBus" ).html(
					"<div class='row'>"
					+ "<div class='input-group col-md-12 mb-1 mb-sm-0'>"
					+ "<span class='input-group-addon' aria-hidden='true' id='basic-addon1'></span>"
					+ "<input type='text' class='form-control' id='nombreB' placeholder='Nombre' aria-describedby='basic-addon1' />"
					+ "</div>"
					+ "</div>" );
			$('#buscar').attr("value","3");
        break;
        
		case "referencia":
			$( "#pmtsBus" ).html(
					"<div class='row'>"
					+ "<div class='input-group col-md-12 mb-1 mb-sm-0'>"
					+ "<span class='input-group-addon' aria-hidden='true' id='basic-addon1'></span>"
					+ "<input type='text' class='form-control' id='referenciaB' placeholder='Referencia' aria-describedby='basic-addon1' />"
					+ "</div>"
					+ "</div>" );
			$('#buscar').attr("value","4");
        break;
        
		default:
			swal('Atención!','Ha ocurrido algo inesperado','warning');
        
        }
}

function buscar(busqueda){
	var cParam1;
	var cParam2;
	
	switch(busqueda) {
	case 1:
		
		cParam1 = $('#fechaAntes').val() ;
		cParam2 = $('#fechaDespues').val() ;
		
		if(cParam2 == "" || cParam2 == null){
			swal("Atención!!!","No has elegido fecha limite para la búsqueda","error");
			return;
		}
		
	break;
	
	case 2:
		
		cParam1 = $('#matriculaB').val() ;
		
    break;
    
	case 3:
		
		cParam1 = $('#nombreB').val() ;
		
    break;
    
	case 4:

		cParam1 = $('#referenciaB').val() ;
		
    break;
    
	default:
		swal('Atención!','Ha ocurrido algo inesperado','warning');
    
    }
	
	if(cParam1 == "" || cParam1 == null){
		swal('Atención!','No indicado un criterio de búsqueda','error');
		return;
	}
	
	$.ajax({
		url : '/ope/gnOrdenServicio/buscaOS',
		dataType : "json",
		contentType : "application/json",
		data : {
			busqueda : busqueda,
			cParam1  : cParam1,
			cParam2  : cParam2
		},
		type : 'GET',
		success : function(data) {
			console.log(data);
		},
		error : function(xhr, status) {
			swal('Disculpe, existió un problema');
		}

	});
	
}


$(document).ready(function(){
	$('#gridSystemModal').modal('show');
	$( 'input:checkbox' ).on( 'click', function() {
	    filtro($(this));
	});
	$( '#buscar' ).on( 'click', function() {
	    buscar($(this)["0"].value);
	});
});