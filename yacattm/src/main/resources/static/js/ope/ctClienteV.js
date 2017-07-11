/**
 * Autor: IMendoza 
 * Fecha: 07 de julio del 2017 
 * Descripcion: Script para pantalla catalogo de Clientes
 */

var table;
$(document).ready(function() {
	table = $('#TClientes').DataTable({
		"select": true,	
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
	
	
	 $('#TClientes tbody').on( 'click', 'tr', function () {
		 
		 table = $('#TVehiculos').DataTable( {
			    retrieve: true,
			    paging: false
			} );
			
		table.destroy();
		 
		 var iCliente =  $(this).find('td').first().text();
		 
		
        $.ajax({
        	url : '/ope/ctCliente/getVehiculos',
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
                  //  $(nTd).html("<a href='tel1:"+oData.vehiculo+"'>"+oData.vehiculo+"</a>");
                    
                	  $(nTd).html("<a href='/ope/ctCliente/getVehiculo?iVehiculo="  +  oData.vehiculo  + "'> <button type='button' class='btn btn-info'> <span class='fa fa-pencil' aria-hidden='true'></span>	</button> </a>");
                	  
                  
                   
                }
            },
            {   "data": null,
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                  //  $(nTd).html("<a href='tel2:"+oData.vehiculo+"'>"+oData.vehiculo+"</a>");
                	
                	$(nTd).html(" <button type='button' id='btnEliminar' class='btn btn-danger' >" + "<span class='fa fa-trash'  aria-hidden='true'></span> </button>");
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


function eliminar(){
	alert("eliminar");
}

	



		
		
		

		
		
		  
		  
