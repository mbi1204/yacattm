/**
 * Autor: Aestrada 
 * Fecha: 29 de junio de 2017 
 * Descripcion: Script para pantalla de Alta de Orden de Servicio
 */

function busqueda(){
	
	var cNombre    = $('#nombreCliente').val();
	var cMatricula = $('#matricula').val();
	var cMarca     = $('#marca').val();
	var cModelo    = $('#modelo').val();
	var cColor     = $('#color').val();
	
	$.ajax({
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
			if(data.length <= 0){
				swal('No se encontraron registros!', data, 'info');
			}
			if (data.indexOf('--- Ejecutando')) {
				$("#Lista > tbody").empty();
				for ( var item in data) {
					var activo = data[item].activo ? 'SI' : 'NO';
					$('#Lista > tbody')
							.append(
									'<tr class="text-center">'
											+ '<td class="text-center">' + data[item].nombre    + '</td>'
											+ '<td class="text-center">' + data[item].matricula + '</td>'
											+ '<td class="text-center">' + data[item].marca     + '</td>'
											+ '<td class="text-center">' + data[item].modelo    + '</td>'
											+ '<td class="text-center">' + data[item].anio      + '</td>'
											+ '<td class="text-center">' + data[item].color     + '</td>'
									+ '</tr>');
				}
				// swal.close();
			} else {
				swal('Error!', data, 'error');
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
	});
	
}