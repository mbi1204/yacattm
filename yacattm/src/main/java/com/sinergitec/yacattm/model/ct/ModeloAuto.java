package com.sinergitec.yacattm.model.ct;

import java.util.Arrays;
import java.util.Vector;

public class ModeloAuto {
	
	private String modelo;
	private String marca;
	private String compania;
	private Boolean activo;
	private byte[] rowid;
	
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
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
		return "ModeloAuto [modelo=" + modelo + ", marca=" + marca + ", compania=" + compania + ", activo=" + activo
				+ ", rowid=" + Arrays.toString(rowid) + "]";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getLista(){
		Vector vector = new Vector();
		vector.add(this.getModelo());
		vector.add(this.getMarca());
		vector.add(this.getCompania());
		vector.add(this.getActivo());
		vector.add(this.getRowid());
		return vector;		
	}

}
