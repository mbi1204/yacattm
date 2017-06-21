package com.sinergitec.yacattm.model.ct;

import java.util.Arrays;
import java.util.Vector;

/**
 * Autor: Aestrada Fecha: 21 de junio de 2017 
 * Descripcion: Modelo de la tabla ctTrasmAuto
 * 
 **/

public class Transmision {

	private String compania;
	private String transmision;
	private Boolean activo;
	private byte[] rowid;

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getTransmision() {
		return transmision;
	}

	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public byte[] getRowid() {
		return rowid;
	}

	public void setRowid(byte[] rowid) {
		this.rowid = rowid;
	}

	@Override
	public String toString() {
		return "Transmision [compania=" + compania + ", transmision=" + transmision + ", activo=" + activo + ", rowid="
				+ Arrays.toString(rowid) + "]";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getLista(){
		Vector vector = new Vector();
		vector.add(this.getCompania());
		vector.add(this.getTransmision());
		vector.add(this.getActivo());
		vector.add(this.getRowid());
		return vector;		
	}
	
}
