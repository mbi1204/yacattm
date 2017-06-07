package com.sinergitec.yacattm.model.ct;

/**
 * Autor: Aestrada
 * Fecha: 07 de junio de 2017
 * Descripcion: Modelo de la tabla ctEstatusOS 
 * 
 **/

public class EstatusOS {
	
	private String cCveCia;
	private String cEstatus;
	private String cObs;
	private String lActivo;
	
	public String getcCveCia() {
		return cCveCia;
	}
	public void setcCveCia(String cCveCia) {
		this.cCveCia = cCveCia;
	}
	public String getcEstatus() {
		return cEstatus;
	}
	public void setcEstatus(String cEstatus) {
		this.cEstatus = cEstatus;
	}
	public String getcObs() {
		return cObs;
	}
	public void setcObs(String cObs) {
		this.cObs = cObs;
	}
	public String getlActivo() {
		return lActivo;
	}
	public void setlActivo(String lActivo) {
		this.lActivo = lActivo;
	}
}
