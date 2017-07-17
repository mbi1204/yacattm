/**
 * Autor: Aestrada Fecha: 11 de julio de 2017 Descripcion: Script para pantalla
 * de Consulta de Orden de Servicio
 */

var listCliente;
var listVehiculo;
var listOrdenServicio;

function today() {
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth() + 1;
	var curr_year = d.getFullYear();
	$('#fechaAntes').val(
			curr_date + '/' + '0' + curr_month + '/' + curr_year);
	$('#fechaDespues').val(
			curr_date + '/' + '0' + curr_month + '/' + curr_year);
}

function iniciaCalendario(){
	$('#datetimepicker').datepicker({
		language : 'es',
		todayHighlight : true,
		defaultViewDate : today()
	});
	$('#datetimepicker2').datepicker({
		language : 'es',
		todayHighlight : true,
		defaultViewDate : today()
	});
}

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
					+ "<div class='input-group date col-md-6 mb-1 mb-sm-0' id='datetimepicker2'>"
					+ "<span class='input-group-addon'>"  
					+ "<span class='fa fa-calendar' aria-hidden='true'></span>"
					+ "</span>"
					+ "<input id='fechaDespues' type='text' class='form-control' placeholder='Hasta:' />" 
					+ "</div>"						
				    + "</div>" );
			
			$('#buscar').attr("value","1");
			iniciaCalendario();
			$('#fechaAntes').mask('00/00/0000');
			$('#fechaDespues').mask('00/00/0000');
			
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
	case "1":
		
		cParam1 = $('#fechaAntes').val() ;
		cParam2 = $('#fechaDespues').val() ;
		
		if(cParam2 == "" || cParam2 == null){
			swal("Atención!!!","No has elegido fecha limite para la búsqueda","error");
			return;
		}
		
	break;
	
	case "2":
		
		cParam1 = $('#matriculaB').val() ;
		
    break;
    
	case "3":
		
		cParam1 = $('#nombreB').val() ;
		
    break;
    
	case "4":

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
			listCliente       = data.listCliente  ;
			listVehiculo      = data.listVehiculo ;
			listOrdenServicio = data.listOrdenServicio;
			
			var relacion = [];
			
			for ( var item in listOrdenServicio) {
				for ( var item2 in listVehiculo ){
					for ( var item3 in listCliente ){
						
						if (listOrdenServicio[item].cliente == listCliente[item3].cliente
								&& listOrdenServicio[item].vehiculo == listVehiculo[item2].vehiculo) {
							
							var elemento = {};
							
							elemento.orden      = listOrdenServicio[item].orden;
							elemento.fecha      = listOrdenServicio[item].fecha;
							elemento.matricula  = listVehiculo[item3].matricula;
							elemento.nombre     = listCliente[item2].nombre;
							elemento.referencia = listOrdenServicio[item].referencia;
							elemento.estatus    = listOrdenServicio[item].estatus;
							elemento.cliente    = listOrdenServicio[item].cliente;
							elemento.vehiculo   = listOrdenServicio[item].vehiculo;

							relacion.push(elemento);

							
						}
						
					}
					
				}
				
			}

			construyeTabla(relacion);
			
		},
		error : function(xhr, status) {
			swal('Disculpe, existió un problema');
		}

	});
	
}

function construyeTabla(dataSet) {
	
	$('#Lista')	.DataTable({
		"destroy" : true,
		data : dataSet,
		"columns" : [ {"data" : "orden"}, 
					  {"data" : "fecha"}, 
					  {"data" : "matricula"},
					  {"data" : "nombre"},
					  {"data" : "referencia"},
					  {"data" : "estatus"},
					  {"data" : "cliente"},
					  {"data" : "vehiculo"} ],
		"pageLength" : 5,
		"lengthMenu" : [ 5, 10 ],
		"language"   : {
			
			"sProcessing" : "Procesando...",
			"sLengthMenu" : "Mostrar _MENU_ registros",
		    "sZeroRecords" : "No se encontraron resultados",
			"sEmptyTable" : "Ningún dato disponible en esta tabla",
			"sInfo" : "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
			"sInfoEmpty" : "Mostrando registros del 0 al 0 de un total de 0 registros",
			"sInfoFiltered" : "(filtrado de un total de _MAX_ registros)",
			"sInfoPostFix" : "",
			"sSearch" : "Buscar:",
			"sUrl" : "",
			"sInfoThousands" : ",",
			"sLoadingRecords" : "Cargando...",
			"oPaginate" : {
				"sFirst" : "Primero",		
				"sLast" : "Último",
				"sNext" : "Siguiente",
				"sPrevious" : "Anterior"},
			"oAria" : {
				"sSortAscending" : ": Activar para ordenar la columna de manera ascendente",
				"sSortDescending" : ": Activar para ordenar la columna de manera descendente"}
				}
	});
	
}

function registro(){
	selecciona($('#Lista > tbody > tr.selected'));
}

function selecciona(registro) {

	/* Lectura y Acomodo de la informacion */

	// Busqueda de la informacion del cliente
	for ( var item in listCliente) {
		if (registro["0"].cells[6].innerHTML == listCliente[item].cliente) {
			alert("Entro");
			console.log($(this));
		}
	}

	// Busqueda de la informacion del vehiculo
	for ( var item in listVehiculo) {
		if (registro["0"].cells[6].innerHTML == listVehiculo[item].cliente
				&& registro["0"].cells[7].innerHTML == listVehiculo[item].vehiculo) {
			alert("Entro");
			console.log($(this));
		}
	}

	$('#gridSystemModal').modal('hide');
	//limpieza();

}

$(document).ready(function(){	
	
	$( 'input:checkbox' ).on( 'click', function() {
	    filtro($(this));
	});
	
	$( '#buscar' ).on( 'click', function() {
	    buscar($(this)["0"].value);
	});
	
	$( '#Lista > tbody' ).on('click', 'tr', function () {	
        $('#Lista > tbody > tr.selected').removeClass('selected');
        $(this).addClass('selected');
    });
	
	$( '#Lista > tbody' ).on('dblclick', 'tr', function() {
		selecciona($(this));
	});
	
	$( '#gridSystemModal' ).modal('show');
	
});