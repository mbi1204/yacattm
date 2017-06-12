package com.sinergitec.yacattm.model.ct;

public class Combustion {
	
	private String compania;
	private String sistema;
	private Boolean activo;
	private byte[] rowid;
	
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public String getSistema() {
		return sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
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
}
