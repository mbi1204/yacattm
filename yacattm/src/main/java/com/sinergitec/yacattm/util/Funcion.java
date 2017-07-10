package com.sinergitec.yacattm.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Autor: Aestrada Fecha: 06 de julio de 2017 
 * Descripcion: Clase que contiene funciones comunes
 * para el sistema
 * 
 **/

public class Funcion {

	public Timestamp dateConvertDT(String fecha) {
		Timestamp timestamp = null;
		Calendar fechaYHora = new GregorianCalendar();
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:sss")
					.parse(fecha + " " + fechaYHora.get(Calendar.HOUR_OF_DAY) + ":" + fechaYHora.get(Calendar.MINUTE)
							+ ":" + fechaYHora.get(Calendar.SECOND));
			timestamp = new Timestamp(date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error" + " " + "ParseException" + " " + this.getClass().getEnclosingMethod().getName()
					+ "/n" + e.getMessage());
		}
		return timestamp;
	}
	
	public Integer strConvertInt(String kilometraje){
		
		kilometraje = kilometraje.replace(",",""); 

		kilometraje = kilometraje.trim();
		
		return Integer.parseInt(kilometraje);
	}
	
}
