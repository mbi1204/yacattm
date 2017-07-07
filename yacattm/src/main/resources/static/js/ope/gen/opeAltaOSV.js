/**
 * Autor: Aestrada 
 * Fecha: 29 de junio de 2017 
 * Descripcion: Script para pantalla de Alta de Orden de Servicio
 */

var listCliente;
var listVehiculo;

function busqueda(){
	
	table = $('#Lista').DataTable( {
	    retrieve: true,
	    paging: false
	} );
	
	table.destroy();
	
	var cNombre    = $('#nombreCliente').val();
	var cMatricula = $('#matricula').val();
	var cMarca     = $('#marca').val();
	var cModelo    = $('#modelo').val();
	var cColor     = $('#color').val();
	
	$('#Lista').DataTable({
		"ajax":{
			method :"GET",
			url : "/ope/gnOrdenServicio/consulta",
			data : {
				cNombre: cNombre,
				cMatricula: cMatricula,
				cMarca: cMarca,
				cModelo: cModelo,
				cColor: cColor
			},
			"dataSrc": "listAutosCliente"
		},
		"columns" : [
			{ "data" : "nombre" },
            { "data" : "matricula" },
            { "data" : "marca" },
            { "data" : "modelo" },
            { "data" : "anio" },
            { "data" : "color" }
		],
		"pageLength": 5,
		 "lengthMenu": [ 5 , 10 ],
	
		 "language": {

				"sProcessing":     "Procesando...",
				"sLengthMenu":     "Mostrar _MENU_ registros",
				"sZeroRecords":    "No se encontraron resultados",
				"sEmptyTable":     "Ningún dato disponible en esta tabla",
				"sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
				"sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
				"sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
				"sInfoPostFix":    "",
				"sSearch":         "Buscar:",
				"sUrl":            "",
				"sInfoThousands":  ",",
				"sLoadingRecords": "Cargando...",
				"oPaginate": {
					"sFirst":    "Primero",
					"sLast":     "Último",
					"sNext":     "Siguiente",
					"sPrevious": "Anterior"
				},
				"oAria": {
					"sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
					"sSortDescending": ": Activar para ordenar la columna de manera descendente"
				}
		 }
	});
	
	
	/*$.ajax({
		url : '/ope/gnOrdenServicio/consulta',
		dataType : "json",
		contentType : "application/json",
		data : {
			cNombre    : cNombre,
			cMatricula : cMatricula,
			cMarca     : cMarca,
			cModelo    : cModelo,
			cColor     : cColor
		},
		type : 'GET',
		success : function(data) {

			listCliente = data.listCliente;
			listVehiculo = data.listVehiculo;

			if(data.listAutosCliente.length <= 0){
				swal('No se encontraron registros!', data, 'info');
			}else {
				//$("#Lista > tbody").empty();
				
				$('#Lista').DataTable( {
				    paging: false,
				    searching: false
				} );
				
				for ( var item in data.listAutosCliente) {
					var activo = data.listAutosCliente[item].activo ? 'SI' : 'NO';

					$('#Lista > tbody')
							.append(
									'<tr class="text-center" ondblclick="selecciona('+item+');" id="registro">'
											+ '<td class="text-center" style="display:none;" >' + data.listAutosCliente[item].cliente    + '</td>'
											+ '<td class="text-center">' + data.listAutosCliente[item].nombre    + '</td>'
											+ '<td class="text-center">' + data.listAutosCliente[item].matricula + '</td>'
											+ '<td class="text-center">' + data.listAutosCliente[item].marca     + '</td>'
											+ '<td class="text-center">' + data.listAutosCliente[item].modelo    + '</td>'
											+ '<td class="text-center">' + data.listAutosCliente[item].anio      + '</td>'
											+ '<td class="text-center">' + data.listAutosCliente[item].color     + '</td>'
											+ '<td class="text-center" style="display:none;" >' + data.listAutosCliente[item].vehiculo    + '</td>'
									+ '</tr>');
				}
			}			
			
		},
		error : function(xhr, status) {
			swal('Disculpe, existió un problema');
		},
		// código a ejecutar sin importar si la petición
		// falló o no
		complete : function(xhr, status) {
			//alert('Petición realizada');
		}
	});*/
	
}

function selecciona(fila){
	
	//Recupera la fila seleccionada
	var value = $("#Lista tr:eq('"+ Number(fila+1) +"')");
	
	/*Lectura y Acomodo de la informacion*/
	
	//Busqueda de la informacion del cliente
	for (var item in listCliente) {
		if(value["0"].cells["0"].innerHTML == listCliente[item].cliente){
			llenaCliente(listCliente[item]);
		}
	}
	
	//Busqueda de la informacion del vehiculo
	for (var item in listVehiculo) {
		if(value["0"].cells["0"].innerHTML == listVehiculo[item].cliente
				&& value["0"].cells[7].innerHTML == listVehiculo[item].vehiculo){
			llenaVehiculo(listVehiculo[item]);
		}
	}

	$('#gridSystemModal').modal('hide');
	limpieza();
	
}

function llenaCliente(cliente){
	
	//Datos del Cliente en la vista
	$('#IDClienteV').val(cliente.cliente);
	$('#clienteV').val(cliente.nombre);
	$('#telefono1V').val(cliente.telefono1);
	$('#telefono2V').val(cliente.telefono2);
	
	//Detalle del Cliente
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

function llenaVehiculo(vehiculo){
	
	//Datos del Vehiculo en la vista
	$('#IDVehiculoV').val(vehiculo.vehiculo);
	$('#matriculaV').val(vehiculo.matricula);
	$('#marcaV').val(vehiculo.marca);
	$('#modeloV').val(vehiculo.modelo);
	$('#anioV').val(vehiculo.anio);
	
	//Detalle del Vehiculo
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

function abreModal(cModal){
	$(cModal).modal('show');	
}

function limpieza(){
	table
    .clear();
	$('#nombreCliente').val("");
	$('#matricula').val("");
	$('#marca').val("");
	$('#modelo').val("");
	$('#color').val("");
	table = $('#Lista').DataTable( {
	    retrieve: true,
	    paging: false
	} );	
	table.destroy();
	$("#Lista > tbody").empty();
}

function guardar(){
	
	//Guardar
	$('#fecha').val();
	$('#referenciaV').val();
	$('#kilometrajeV').val();
	$('#ex13').val();
	
}

function language(){
	table = $('#Lista').DataTable({
		 "pageLength": 5,
		 "lengthMenu": [ 5 , 10 ],
	
		 "language": {

				"sProcessing":     "Procesando...",
				"sLengthMenu":     "Mostrar _MENU_ registros",
				"sZeroRecords":    "No se encontraron resultados",
				"sEmptyTable":     "Ningún dato disponible en esta tabla",
				"sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
				"sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
				"sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
				"sInfoPostFix":    "",
				"sSearch":         "Buscar:",
				"sUrl":            "",
				"sInfoThousands":  ",",
				"sLoadingRecords": "Cargando...",
				"oPaginate": {
					"sFirst":    "Primero",
					"sLast":     "Último",
					"sNext":     "Siguiente",
					"sPrevious": "Anterior"
				},
				"oAria": {
					"sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
					"sSortDescending": ": Activar para ordenar la columna de manera descendente"
				}
		 }
		
		    			
	});
}

$(document).ready(function() {
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
	
});
