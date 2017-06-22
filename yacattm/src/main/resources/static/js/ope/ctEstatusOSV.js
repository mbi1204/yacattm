/**
 * Autor: Aestrada 
 * Fecha: 12 de junio de 2017 
 * Descripcion: Script para pantalla de Estatus Orden de Servicio
 */

function eliminar(cEstatus){
	
	$("#Lista").on('click', '#btnEliminar', function() {
		var row = $(this).closest('tr');
		swal({
			title : 'eliminar',
			text : "¿Desea Eliminar el registro seleccionado?",
			type : 'warning',
			showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'SI',
			  cancelButtonText:  'NO', 
			  closeOnConfirm : false	  
		},function() {

			$.ajax({
				url : '/ope/ctEstatusOS/eliminar',
				data : {
					cEstatus : cEstatus
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

	});
	
}