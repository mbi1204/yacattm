/**
 * Autor: Aestrada Fecha: 24 de junio de 2017 Descripcion: Script para pantalla
 * de Tipos de Modelos de Vehiculos
 */

function cargaLista(cMarca) {

	$.ajax({
		url : '/ope/ctModeloVehiculo/lista',
		dataType : "json",
		contentType : "application/json",
		data : {
			cMarca : cMarca
		},
		type : 'GET',
		success : function(data) {
			if (data.indexOf('--- Ejecutando')) {
				console.log(data);
				$("#Lista > tbody").empty();
				for ( var item in data) {
					var activo = data[item].activo ? 'SI' : 'NO';
					$('#Lista > tbody').append(
							'<tr class="text-center">' + 
								'<td class="text-center">' + data[item].modelo   + '</td>' +
								'<td class="text-center">' + activo              + '</td>' + 
								'<td class="text-center"> <a href="/ope/ctModeloVehiculo/getModeloVehiculo?cModelo='
													+ data[item].modelo
													+ '"><button type="button" class="btn btn-info"><span class="fa fa-pencil" aria-hidden="true"></span></button></a> <button type="button" id="btnEliminar" class="btn btn-danger" onclick="eliminar('
													+ data[item].modelo
													+ ');"><span class="fa fa-trash" aria-hidden="true"></span></button> </td>' +
							'</tr>');
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
			// alert('Petición realizada');
		}
	});

}