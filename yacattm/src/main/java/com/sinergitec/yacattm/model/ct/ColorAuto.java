package com.sinergitec.yacattm.model.ct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;


/**
 * modelo de la tabla ctAutoModelo
 * @author mendoza
 *
 */
public class ColorAuto {
	private String compania;
	private String color;
	private Boolean activo;
	private byte[] rowid; 
	


	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
		return "ColorAuto [compania=" + compania + ", color=" + color + ", activo=" + activo + ", rowid="
				+ Arrays.toString(rowid) + "]";
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getLista(){
		Vector vector = new Vector();
		vector.add(this.getCompania());
		vector.add(this.getColor());
		vector.add(this.getActivo());
		vector.add(this.getRowid());
		return vector;		
	}
	
	
	
	



}
