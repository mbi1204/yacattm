package com.sinergitec.yacattm.model.ct;

import java.util.Vector;

/**
 * Autor: Aestrada
 * Fecha: 07 de junio de 2017
 * Descripcion: Modelo de la tabla ctEstatusOS 
 * 
 **/

public class EstatusOS {
	
	private String compania;
	private String estatus;
	private String obs;
	private Boolean activo;
	private byte[] rowid;
	
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
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
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getLista(){
		Vector vector = new Vector();
		vector.add(this.getCompania());
		vector.add(this.getEstatus());
		vector.add(this.getObs());
		vector.add(this.getActivo());
		vector.add(this.getRowid());
		return vector;		
	}	
	
}
