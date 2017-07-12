/**
 * Autor: Aestrada Fecha: 29 de junio de 2017 Descripcion: Script para pantalla
 * de Alta de Orden de Servicio
 */

var listCliente;
var listVehiculo;
var listAutosCliente;

function busqueda() {

	var cNombre = $('#nombreCliente').val();
	var cMatricula = $('#matricula').val();
	var cMarca = $('#marca').val();
	var cModelo = $('#modelo').val();
	var cColor = $('#color').val();

	$.ajax({
		url : '/ope/gnOrdenServicio/consulta',
		dataType : "json",
		contentType : "application/json",
		data : {
			cNombre : cNombre,
			cMatricula : cMatricula,
			cMarca : cMarca,
			cModelo : cModelo,
			cColor : cColor
		},
		type : 'GET',
		success : function(data) {
			listCliente = data.listCliente;
			listVehiculo = data.listVehiculo;
			construyeTabla(data.listAutosCliente);
		},
		error : function(xhr, status) {
			swal('Disculpe, existió un problema');
		}

	});

	// Limpieza de campos de busqueda
	$('#nombreCliente').val("");
	$('#matricula').val("");
	$('#marca').val("");
	$('#modelo').val("");
	$('#color').val("");

}

function construyeTabla(dataSet) {
	
	$('#Lista')	.DataTable({
		"destroy" : true,
		data : dataSet,
		"columns" : [ {"data" : "nombre"}, 
					  {"data" : "matricula"}, 
					  {"data" : "marca"},
					  {"data" : "modelo"},
					  {"data" : "anio"},
					  {"data" : "color"},
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

function selecciona(registro) {

	/* Lectura y Acomodo de la informacion */

	// Busqueda de la informacion del cliente
	for ( var item in listCliente) {
		if (registro["0"].cells[6].innerHTML == listCliente[item].cliente) {
			llenaCliente(listCliente[item]);
		}
	}

	// Busqueda de la informacion del vehiculo
	for ( var item in listVehiculo) {
		if (registro["0"].cells[6].innerHTML == listVehiculo[item].cliente
				&& registro["0"].cells[7].innerHTML == listVehiculo[item].vehiculo) {
			llenaVehiculo(listVehiculo[item]);
		}
	}

	$('#gridSystemModal').modal('hide');
	limpieza();

}

function llenaCliente(cliente) {

	// Datos del Cliente en la vista
	$('#IDClienteV').val(cliente.cliente);
	$('#clienteV').val(cliente.nombre);
	$('#telefono1V').val(cliente.telefono1);
	$('#telefono2V').val(cliente.telefono2);

	// Detalle del Cliente
	$('#IDCliente').val(cliente.cliente);
	$('#nombreF').val(cliente.nombre);
	$('#emailF').val(cliente.email);
	$('#rfcF').val(cliente.rfc);
	$('#contactoF').val(cliente.contacto);
	$('#telefono1F').val(cliente.telefono1);
	$('#telefono2F').val(cliente.telefono2);
	$('#calleF').val(cliente.calle);
	$('#numExtF').val(cliente.numExterior);
	$('#numIntF').val(cliente.numInterior);
	$('#colF').val(cliente.colonia);
	$('#mpioDelF').val(cliente.mpioDeleg);
	$('#estadoF').val(cliente.estado);
	$('#paisF').val(cliente.pais);
	$('#cpF').val(cliente.cp);

}

function llenaVehiculo(vehiculo) {

	// Datos del Vehiculo en la vista
	$('#IDVehiculoV').val(vehiculo.vehiculo);
	$('#matriculaV').val(vehiculo.matricula);
	$('#marcaV').val(vehiculo.marca);
	$('#modeloV').val(vehiculo.modelo);
	$('#anioV').val(vehiculo.anio);

	// Detalle del Vehiculo
	$('#IDVehiculo').val(vehiculo.vehiculo);
	$('#matriculaF').val(vehiculo.matricula);
	$('#marcaF').val(vehiculo.marca);
	$('#modeloF').val(vehiculo.modelo);
	$('#anioF').val(vehiculo.anio);
	$('#paisVF').val(vehiculo.pais);
	$('#engomadoF').val(vehiculo.engomado);
	$('#calcomaniaF').val(vehiculo.calcomaniaI);
	$('#serieF').val(vehiculo.numSerie);
	$('#colorF').val(vehiculo.color);
	$('#acF').val(vehiculo.aireAC);
	$('#motorF').val(vehiculo.motor);
	$('#direccionF').val(vehiculo.direccion);
	$('#sistemaF').val(vehiculo.sistema);
	$('#tipoF').val(vehiculo.tipo);
	$('#transmisionF').val(vehiculo.transmision);
	$('#usoF').val(vehiculo.uso);

}

function abreModal(cModal) {
	$(cModal).modal('show');
}

function limpieza() {
	$('#nombreCliente').val("");
	$('#matricula').val("");
	$('#marca').val("");
	$('#modelo').val("");
	$('#color').val("");
	table = $('#Lista').DataTable({
		retrieve : true,
		paging : false,
		destroy : true
	});
	table.clear();

	$("#Lista > tbody").empty();
}

function validaOS() {

	// Realiza validacion de datos, si pasa filtros realiza submit

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
		swal('Error!!!', 'Selecciona un cliente', 'error');
		bandera = false;
	}

	if ($('#IDClienteV').val() == "" || $('#IDClienteV').val() == null) {
		swal('Error!!!', 'Selecciona un cliente', 'error');
		bandera = false;
	}

	if ($('#falla').val() == "" || $('#falla').val() == null) {
		$('#falla').addClass('alert alert-danger');
		bandera = false;
	}

	return bandera;

}

function language() {
	$('#Lista').DataTable({
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

function registro(){
	selecciona($('#Lista > tbody > tr.selected'));
}

$(document).ready(function() {

	var ordenServ = getParameterByName("iOrdenServ");
	
	if (ordenServ != "" && ordenServ != null) {
		//swal('N° Orden de Servicio', ordenServ, 'success');
		swal({
			  title: 'Orden de Servicio: ' + ordenServ ,
			  type: 'success'
			});
	}
	

	$('.money').mask('000,000,000,000,000', {
		reverse : true
	});
	
	$('.close').on('click', function() {
		limpieza();
	});
	
	$(document).keyup(function(e) {
		if (e.keyCode == 27) { // escape key maps to keycode `27`
			limpieza();
		}
	});
	
	
	$('[data-toggle="tooltip"]').tooltip();

	language();
	
	$('#Lista > tbody').on( 'click', 'tr', function () {
		
        $('#Lista > tbody > tr.selected').removeClass('selected');
        $(this).addClass('selected');
        
    } );

	$('#Lista > tbody').on('dblclick', 'tr', function() {
		selecciona($(this));

	});

	$('#referenciaV').focusout(function() {
		if ($('#referenciaV').val().length > 0) {
			$('#referenciaV').removeClass('alert alert-danger');
		}
	});

	$('#kilometrajeV').focusout(function() {
		if ($('#kilometrajeV').val().length > 0) {
			$('#kilometrajeV').removeClass('alert alert-danger');
		}
	});

	$('#falla').focusout(function() {
		if ($('#falla').val().length > 0) {
			$('#falla').removeClass('alert alert-danger');
		}
	});

});
