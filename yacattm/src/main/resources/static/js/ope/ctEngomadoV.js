/**
 * 
 */



function eliminar(cEngomado) {
	$("#Lista").on('click', '#btnEliminar', function() {
		var row = $(this).closest('tr');

		swal({
			title : 'eliminar',
			text : "¿Desea Eliminar el registro seleccionado?",
			type : 'warning',
			showCancelButton : true,
			confirmButtonColor : '#3085d6',
			cancelButtonColor : '#d33',
			confirmButtonText : 'SI',
			cancelButtonText : 'NO',
			closeOnConfirm : false

		}, function() {
			$.ajax({
				url : '/ope/ctEngomado/eliminar',
				data : {
					cEngomado : cEngomado
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

					alert(xhr);

				},
				complete : function(xhr, status) {

				}
			});

		});

	});

}
