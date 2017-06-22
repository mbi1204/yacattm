package com.sinergitec.yacattm.model.ct;

import java.util.Arrays;
import java.util.Vector;

/**
 * Autor: Aestrada Fecha: 22 de junio de 2017 
 * Descripcion: Modelo de la tabla ctAutoModelo
 * 
 **/ 

public class MarcaAuto {
	
	private String compania;
	private String marca;
	private Boolean activo;
	private byte[] rowid;
	
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
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
		return "MarcaAuto [compania=" + compania + ", marca=" + marca + ", activo=" + activo + ", rowid="
				+ Arrays.toString(rowid) + "]";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getLista(){
		Vector vector = new Vector();
		vector.add(this.getCompania());
		vector.add(this.getMarca());
		vector.add(this.getActivo());
		vector.add(this.getRowid());
		return vector;		
	}
	
}
