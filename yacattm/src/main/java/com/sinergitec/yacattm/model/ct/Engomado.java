package com.sinergitec.yacattm.model.ct;

import java.util.Arrays;
import java.util.Vector;

public class Engomado {
	private String compania;
	private String engomado;
	private String placas;
	private String periodo1;
	private String periodo2;
	private Boolean activo;
	private String dia;
	private Integer orden;
	private  byte[] rowid; 

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getEngomado() {
		return engomado;
	}

	public void setEngomado(String emgomado) {
		this.engomado = emgomado;
	}

	public String getPlacas() {
		return placas;
	}
 
	public void setPlacas(String placas) {
		this.placas = placas;
	}

	public String getPeriodo1() {
		return periodo1;
	}

	public void setPeriodo1(String periodo1) {
		this.periodo1 = periodo1;
	}

	public String getPeriodo2() {
		return periodo2;
	}

	public void setPeriodo2(String periodo2) {
		this.periodo2 = periodo2;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
	

	public byte[] getRowid() {
		return rowid;
	}

	public void setRowid(byte[] rowid) {
		this.rowid = rowid;
	}
	
	@Override
	public String toString() {
		return "Engomado [compania=" + compania + ", engomado=" + engomado + ", placas=" + placas + ", periodo1="
				+ periodo1 + ", periodo2=" + periodo2 + ", activo=" + activo + ", dia=" + dia + ", orden=" + orden
				+ ", rowid=" + Arrays.toString(rowid) + "]";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getRegistro(){
		Vector vector = new Vector();
		vector.add(this.getCompania());
		vector.add(this.getEngomado());
		vector.add(this.getPlacas());		
		vector.add(this.getPeriodo1());
		vector.add(this.getPeriodo2());
		vector.add(this.getActivo());
		vector.add(this.getDia());
		vector.add(this.getOrden());
		vector.add(this.getRowid());
		
		return vector;
	}
}
