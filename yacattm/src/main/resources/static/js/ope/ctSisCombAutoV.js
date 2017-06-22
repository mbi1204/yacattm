/**
 * Autor: Aestrada 
 * Fecha: 13 de junio de 2017 
 * Descripcion: Script para pantalla de Sistemas de Combustion
 */

function eliminar(cSistema){
	
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
				url : '/ope/ctSisCombAuto/eliminar',
				data : {
					cSistema : cSistema
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