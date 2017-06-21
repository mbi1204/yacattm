package com.sinergitec.yacattm.model.ct;

import java.util.Arrays;
import java.util.Vector;

/**
 * Autor: Aestrada Fecha: 21 de junio de 2017 
 * Descripcion: Modelo de la tabla ctTrasmAuto
 * 
 **/

public class UsoVeh {
	
	private String compania;
	private String uso;
	private Boolean activo;
	private byte[] rowid;
	
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public String getUso() {
		return uso;
	}
	public void setUso(String uso) {
		this.uso = uso;
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
		return "UsosVeh [compania=" + compania + ", uso=" + uso + ", activo=" + activo + ", rowid="
				+ Arrays.toString(rowid) + "]";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getLista(){
		Vector vector = new Vector();
		vector.add(this.getCompania());
		vector.add(this.getUso());
		vector.add(this.getActivo());
		vector.add(this.getRowid());
		return vector;		
	}
	
}
