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
	$('#datetimepicker2').datepicker({
		language : 'es',
		todayHighlight : true,
		defaultViewDate : today()
	});
	$('#datetimepicker3').datepicker({
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
		case "fechaC":
			$( "#pmtsBus" ).html(
					"<div class='row'>"
					+ "<div class='input-group date col-md-6 mb-1 mb-sm-0' id='datetimepicker2'>"
					+ "<span class='input-group-addon'>"  
					+ "<span class='fa fa-calendar' aria-hidden='true'></span>"
					+ "</span>"
					+ "<input id='fechaAntes' type='text' class='form-control' placeholder='De:' />" 
					+ "</div>"					
					+ "<div class='input-group date col-md-6 mb-1 mb-sm-0' id='datetimepicker3'>"
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
		cParam2 = "";
		
    break;
    
	case "3":
		
		cParam1 = $('#nombreB').val() ;
		cParam2 = "";
		
    break;
    
	case "4":

		cParam1 = $('#referenciaB').val() ;
		cParam2 = "";
		
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
							elemento.matricula  = listVehiculo[item2].matricula;
							elemento.nombre     = listCliente[item3].nombre;
							elemento.referencia = listOrdenServicio[item].referencia;
							elemento.estatus    = listOrdenServicio[item].estatus == null ? "" : listOrdenServicio[item].estatus;
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
	var OS , cliente , vehiculo;
	
	for ( var item in listOrdenServicio ){
		if(registro["0"].cells["0"].innerHTML == listOrdenServicio[item].orden){
			OS = listOrdenServicio[item];
		}
	}

	// Busqueda de la informacion del cliente
	for ( var item in listCliente) {
		if (registro["0"].cells[6].innerHTML == listCliente[item].cliente) {
			cliente = listCliente[item];
		}
	}

	// Busqueda de la informacion del vehiculo
	for ( var item in listVehiculo) {
		if (registro["0"].cells[6].innerHTML == listVehiculo[item].cliente
				&& registro["0"].cells[7].innerHTML == listVehiculo[item].vehiculo) {
			vehiculo = listVehiculo[item];
		}
	}

	$('#gridSystemModal').modal('hide');
	//limpieza();
	llenaRegistro(OS, cliente, vehiculo);

}

function llenaRegistro(oServicio, clienteInfo, vehiculoInfo){
	
	//Orden de Servicio
	
	$('#compania').val(oServicio.compania);
	$('#ordenV').val(oServicio.orden);
	//$('#estatusV option:eq(2)').prop('selected', true)
	$('#estatusV').val(oServicio.estatus);
	$('#fecha').val(oServicio.fecha);
	$('#datetimepicker').val(oServicio.fecha)
	$('#referenciaV').val(oServicio.referencia);
	$('#kilometrajeV').val(oServicio.kilometraje);
	
	$('#ex13').val(oServicio.nivelCombustible);
	
	var slider = new Slider("#ex13", {
		ticks : [ 0, 25, 50, 75, 100 ],	
		ticks_labels : [ 'E', '1/4', '1/2', '3/4', 'F' ],
		ticks_snap_bounds : 25,
		value : oServicio.nivelCombustible
	});
	
	$('#falla').val(oServicio.falla);
	$('#diagnostico').val(oServicio.diagnostico);
	$('#observacion').val(oServicio.obs);
	$('#reparacion').val(oServicio.reparacion);
	
	$('#rowid').val(oServicio.rowid);
	
	// Datos del Cliente en la vista
	$('#IDClienteV').val(clienteInfo.cliente);
	$('#clienteV').val(clienteInfo.nombre);
	$('#telefono1V').val(clienteInfo.telefono1);
	$('#telefono2V').val(clienteInfo.telefono2);
	
	// Detalle del Cliente
	$('#IDCliente').val(clienteInfo.cliente);
	$('#nombreF').val(clienteInfo.nombre);
	$('#emailF').val(clienteInfo.email);
	$('#rfcF').val(clienteInfo.rfc);
	$('#contactoF').val(clienteInfo.contacto);
	$('#telefono1F').val(clienteInfo.telefono1);
	$('#telefono2F').val(clienteInfo.telefono2);
	$('#calleF').val(clienteInfo.calle);
	$('#numExtF').val(clienteInfo.numExterior);
	$('#numIntF').val(clienteInfo.numInterior);
	$('#colF').val(clienteInfo.colonia);
	$('#mpioDelF').val(clienteInfo.mpioDeleg);
	$('#estadoF').val(clienteInfo.estado);
	$('#paisF').val(clienteInfo.pais);
	$('#cpF').val(clienteInfo.cp);
	
	// Datos del Vehiculo en la vista
	$('#IDVehiculoV').val(vehiculoInfo.vehiculo);
	$('#matriculaV').val(vehiculoInfo.matricula);
	$('#marcaV').val(vehiculoInfo.marca);
	$('#modeloV').val(vehiculoInfo.modelo);
	$('#anioV').val(vehiculoInfo.anio);
	
	// Detalle del Vehiculo
	$('#IDVehiculo').val(vehiculoInfo.vehiculo);
	$('#matriculaF').val(vehiculoInfo.matricula);
	$('#marcaF').val(vehiculoInfo.marca);
	$('#modeloF').val(vehiculoInfo.modelo);
	$('#anioF').val(vehiculoInfo.anio);
	$('#paisVF').val(vehiculoInfo.pais);
	$('#engomadoF').val(vehiculoInfo.engomado);
	$('#calcomaniaF').val(vehiculoInfo.calcomaniaI);
	$('#serieF').val(vehiculoInfo.numSerie);
	$('#colorF').val(vehiculoInfo.color);
	$('#acF').val(vehiculoInfo.aireAC);
	$('#motorF').val(vehiculoInfo.motor);
	$('#direccionF').val(vehiculoInfo.direccion);
	$('#sistemaF').val(vehiculoInfo.sistema);
	$('#tipoF').val(vehiculoInfo.tipo);
	$('#transmisionF').val(vehiculoInfo.transmision);
	$('#usoF').val(vehiculoInfo.uso);
	
}

function soloLectura(){
	$('#ordenV').attr('readonly', true);
	$('#fecha').attr('readonly', true);
	$('#referenciaV').attr('readonly', true);
	$('#kilometrajeV').attr('readonly', true);
	$('#IDVehiculoV').attr('readonly', true);
	$('#IDClienteV').attr('readonly', true);
	$('#falla').attr('readonly', true);
}

function abreModal(cModal) {
	$(cModal).modal('show');
}

function getParameterByName(name, url) {
	if (!url)
		url = window.location.href;
	name = name.replace(/[\[\]]/g, "\\$&");
	var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex
			.exec(url);
	if (!results)
		return null;
	if (!results[2])
		return '';
	return decodeURIComponent(results[2].replace(/\+/g, " "));
}

function validaOS(){
	var bandera = true;
	if ($('#fecha').val() == "" || $('#fecha').val() == null) {
		$('#fecha').addClass('alert alert-danger');
		bandera = false;
	}

	if ($('#referenciaV').val() == "" || $('#referenciaV').val() == null) {
		$('#referenciaV').addClass('alert alert-danger');
		bandera = false;
	}

	if ($('#kilometrajeV').val() == "" || $('#kilometrajeV').val() == null) {
		$('#kilometrajeV').addClass('alert alert-danger');
		bandera = false;
	}

	if ($('#IDVehiculoV').val() == "" || $('#IDVehiculoV').val() == null) {
		swal('Error!!!', 'Selecciona una orden de servicio', 'error');
		bandera = false;
	}

	if ($('#IDClienteV').val() == "" || $('#IDClienteV').val() == null) {
		swal('Error!!!', 'Selecciona una orden de servicio', 'error');
		bandera = false;
	}

	if ($('#falla').val() == "" || $('#falla').val() == null) {
		$('#falla').addClass('alert alert-danger');
		bandera = false;
	}
	return bandera;
}

$(document).ready(function(){
	
	$('div#datetimepicker').removeAttr('id');
	
	soloLectura();
	
	$('#fecha').mask('00/00/0000');
	
	$('.money').mask('000,000,000,000,000', {
		reverse : true
	});
	
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
	
	var ordenServ = getParameterByName("iOrdenServ");
	
	if (ordenServ != "" && ordenServ != null) {
		//swal('N° Orden de Servicio', ordenServ, 'success');
		swal({
			  title: 'Orden de Servicio Actualizada: ' + ordenServ ,
			  type: 'success'
			});
	}
	
	$( '#gridSystemModal' ).modal('show');
	
});