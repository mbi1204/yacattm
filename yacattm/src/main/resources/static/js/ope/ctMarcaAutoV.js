/**
 * Autor: Aestrada 
 * Fecha: 23 de junio de 2017 
 * Descripcion: Script para pantalla de Tipos de Marcas de Vehiculos
 */

function eliminar(cMarca) {
	var row;
	$("#Lista").on('click', '#btnEliminar', function() {
		row = $(this).closest('tr');
	});

	swal({
		title : "Eliminar",
		text : "¿Desea Eliminar el registro seleccionado?",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		cancelButtonColor: '#d33',
		confirmButtonText : "SI",
		cancelButtonText:  'NO',
		closeOnConfirm : false
	}, function() {

		$.ajax({
			url : '/ope/ctMarcaVehiculo/eliminar',
			data : {
				cMarca : cMarca
			},
			type : 'GET',
			success : function(data) {

				if (data == "success") {
					row.remove();
					swal.close();
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

	});
}