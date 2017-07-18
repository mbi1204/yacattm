package com.sinergitec.yacattm.util;

import java.sql.Timestamp;
import java.text.DateFormat;
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

	/**
	 * Realiza conversion de fecha string a datatime anexando hora, minuto y
	 * segundo del servidor
	 * 
	 * @param fecha
	 * @return fecha HH:mm:sss 
	 */
	public Timestamp strConvertDT(String fecha) {
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

	/**
	 * Realiza la conversion de una fecha string en formato dd/MM/yyyy a date
	 * calendario gregoriano en formato dd/MM/yyyy
	 * 
	 * @param fecha
	 * @return
	 */
	public GregorianCalendar strConvertGC(String fecha) {

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		try {
			date = format.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error" + " " + "ParseException" + " " + this.getClass().getEnclosingMethod().getName()
					+ "/n" + e.getMessage());
		}
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		return calendar;
	}

	/**
	 * Realiza la conversion de un string a numero eliminando el caracter ','
	 * 
	 * @param kilometraje
	 * @return
	 */
	public Integer strConvertInt(String kilometraje) {
		kilometraje = kilometraje.replace(",", "");
		kilometraje = kilometraje.trim();
		return Integer.parseInt(kilometraje);
	}
	
	/**
	 * Realiza la conversion de una fecha Timestamp a string en formato
	 * dd/MM/yyyy
	 * 
	 * @param fecha
	 * @return
	 */
	public String dtConvertStr(Timestamp fecha){
		if(fecha == null){
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String fechaStr  = dateFormat.format(fecha);
		return fechaStr;
	}
	
	public String dtConvertStrWTime(Timestamp fecha){
		if(fecha == null){
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:sss");
		String fechaStr  = dateFormat.format(fecha);
		return fechaStr;
	}

}
