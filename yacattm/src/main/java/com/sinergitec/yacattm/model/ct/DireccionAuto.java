package com.sinergitec.yacattm.model.ct;

import java.util.Arrays;
import java.util.Vector;

public class DireccionAuto {
	private String compania;
	private String direccion;
	private Boolean activo;
	private byte[] rowid;

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
		return "DireccionAuto [compania=" + compania + ", direccion=" + direccion + ", activo=" + activo + ", rowid="
				+ Arrays.toString(rowid) + "]";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getRegistro() {
		Vector vector = new Vector();
		vector.add(this.getCompania());
		vector.add(this.getDireccion());
		vector.add(this.getActivo());
		vector.add(this.getRowid());

		return vector;
	}

}
