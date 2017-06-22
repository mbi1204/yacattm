/**
 * Autor: Aestrada 
 * Fecha: 21 de junio de 2017 
 * Descripcion: Script para pantalla de Tipos de Usos de Vehiculos
 */

function eliminar(cUso) {
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
			url : '/ope/ctUsosVehiculo/eliminar',
			data : {
				cUso : cUso
			},
			type : 'GET',
			success : function(data) {

				if (data == "success") {
					row.remove();
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