/**
 * Autor: Aestrada 
 * Fecha: 23 de junio de 2017 
 * Descripcion: Script para pantalla de Tipos de Marcas de Vehiculos
 */

function eliminar(cMarca) {

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
					$("#marcaSelect option[value='"+cMarca+"']").remove();
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

function getValue(){
	
	$("#edit").attr('href','/ope/ctMarcaVehiculo/getMarcaVehiculo?cMarca='+ $( "#marcaSelect" ).val() + '');
	$("#btnEliminar").attr('onclick','eliminar(\''+ $( "#marcaSelect" ).val() + '\')');
	$("#edit").attr('style','display:block');
	$("#btnEliminar").attr('style','display:block');
	
	cargaLista($( "#marcaSelect" ).val());
}

