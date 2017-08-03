/**
 * Autor: IMendoza 
 * Fecha: 07 de julio del 2017 
 * Descripcion: Script para pantalla catalogo de Clientes
 */

var tClientes , tVehiculos ;

$(document).ready(function() {
	tClientes = $('#TClientes').DataTable({
		//"select": true,		
		"processing": true,	   
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
    		//"sLoadingRecords": "Cargando...",
    		"oPaginate": {
    			"sFirst":    "Primero",
    			"sLast":     "Último",
    			"sNext":     "Siguiente",
    			"sPrevious": "Anterior"
    		},
    		"oAria": {
    			"sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
    			"sSortDescending": ": Activar para ordenar la columna de manera descendente"
    		},
    		"sLoadingRecords" : '<span style="width:100%;"><img src="http://www.snacklocal.com/images/ajaxload.gif"></span>'
    	}
    			
	});
	
	
	$('#TClientes tbody').on( 'dblclick', 'tr', function () {
		
		 tVehiculos = $('#TVehiculos').DataTable( {
			 retrieve: true,
			 paging: false
		 } );
		 
		 tVehiculos.destroy();
		
		
		
		
		 if ( $(this).hasClass('selected') ) {			 
			 $(this).removeClass('selected');			 
	     }else {
	    	 var iCliente =  $(this).find('td').first().text();
			 
	 		
	         $.ajax({
	         	url : '/ope/ctVehiculo/getVehiculos',
	             dataType : "json",
	 		 	contentType : "application/json",
	 		 	data : {
	 		 			iCliente    : iCliente
	 		 	},
	 		 	type : 'GET',
	 		 	success : function(data) {	 		 		 		 		
	 		 		ListVehiculos(data);	 		 			
	 		 	},
	 		 	error : function(xhr, status) {
	 		 		
	 		 
	 		 		swal('Disculpe, existió un problema');
	 		 		
	 		 	}

	 		});	        
	    	 
	    	 
	    	 
	    	 tClientes.$('tr.selected').removeClass('selected');
	         $(this).addClass('selected');
	         
	     }	
    } );
	
} );




function ListVehiculos(dataSet){
	
	
	$('#TVehiculos').DataTable( {
        data: dataSet,
        "columns" : [
        	{ "data" : "vehiculo" },
            { "data" : "matricula" },
            { "data" : "marca" },
            { "data" : "modelo" },
            { "data" : "anio" },
            { "data" : "color" },
            {   "data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                	$(nTd).html("<a href='/ope/ctVehiculo/getVehiculo?iVehiculo="  +  oData.vehiculo  + "'> <button type='button' class='btn btn-info'> <span class='fa fa-pencil' aria-hidden='true'></span>	</button> </a>");               	  
                                     
                }
            },
            {   "data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {                                  	
                	$(nTd).html(" <button type='button' id='btnEliminarCli' class='btn btn-danger'  onclick='eliminaVehiculo(" + oData.vehiculo +  ")' >" + "<span class='fa fa-trash'  aria-hidden='true'></span> </button>");
                }
            },
			
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
    } );	
}



function eliminarCliente(iCliente){
	var row ;
	
				
	$('#TClientes tbody').on('click', 'tr', function () {
		//row = tClientes.row(this).data();    
		console.log(this);
		row =(this);
		
    } );
	
	
		
	swal({
		title : 'cliente: ' + ' ' + iCliente    ,
		text : "¿Desea Eliminar el cliente seleccionado?"   ,
		type : 'warning',
		showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'SI',
		  cancelButtonText:  'NO',
		  closeOnConfirm : false ,
	},function (){			  
		$.ajax({
			url : '/ope/ctCliente/eliminar',
			data : {
				iCliente : iCliente
			},
			type : 'GET',
			success : function(data) {	
			

				if (data == "success") {				
					tClientes.row(row).remove().draw( false );				
					
					swal.close();
					
				} else {
					
					swal ('Error!', data, 'error');
					//swal('Error!', data, 'error');
				}
			},
			error : function(xhr, status) {
				
				alert(xhr ) ;
				alert(status);
				
				alert('Disculpe, existió un problema');
			},
			// código a ejecutar sin importar si la petición
			// falló o no
			complete : function(xhr, status) {
				//alert('Petición realizada');
			}
		});
			  
	});
}




function ValidaCliente(){
	
	var iCliente =  tClientes.$('tr.selected').find('td').first().text();
	
	if (iCliente == 0 || iCliente == null || iCliente == "") {
		swal('Error!', "seleccione un cliente", 'error');
		return;

	}else{		
		location.href = "/ope/ctVehiculo/nuevo?iCliente=" + iCliente;		
	}
	
}

function eliminaVehiculo(iVehiculo){
//	console.log("entro en elimina vehiculo");
//	console.log(iVehiculo);
	
	swal({
		title : 'vehiculo: ' + ' ' + iVehiculo    ,
		text : "¿Desea Eliminar el vehiculo seleccionado?"   ,
		type : 'warning',
		showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'SI',
		  cancelButtonText:  'NO',
		  closeOnConfirm : false ,
	},function (){			  
		$.ajax({
			url : '/ope/ctVehiculo/eliminar',
			data : {
				iVehiculo : iVehiculo
			},
			type : 'GET',
			success : function(data) {	
			

				if (data == "success") {				
					//tClientes.row(row).remove().draw( false );				
					
					//swal.close();
					
				} else {
					
					//swal ('Error!', data, 'error');
					//swal('Error!', data, 'error');
				}
			},
			error : function(xhr, status) {
				
				alert(xhr ) ;
				alert(status);
				
				alert('Disculpe, existió un problema');
			},
			// código a ejecutar sin importar si la petición
			// falló o no
			complete : function(xhr, status) {
				//alert('Petición realizada');
			}
		});
			  
	});
	
	
}

	



		
		
		

		
		
		  
		  
