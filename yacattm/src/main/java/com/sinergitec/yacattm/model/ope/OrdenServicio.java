package com.sinergitec.yacattm.model.ope;

import java.util.Arrays;
import java.util.Vector;

import com.sinergitec.yacattm.util.Funcion;

public class OrdenServicio {
	
	private String  compania;
	private Integer orden;
	private String  fecha;
	private String  falla;
	private String  diagnostico;
	private String  obs;
	private String  referencia;
	private String  estatus;
	private String kilometraje;
	private Integer cliente;
	private Integer vehiculo;
	private Integer nivelCombustible;
	private String  reparacion;
	private byte[]  rowid;
	
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getFalla() {
		return falla;
	}
	public void setFalla(String falla) {
		this.falla = falla;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(String kilometraje) {
		this.kilometraje = kilometraje;
	}
	public Integer getCliente() {
		return cliente;
	}
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	public Integer getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Integer vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Integer getNivelCombustible() {
		return nivelCombustible;
	}
	public void setNivelCombustible(Integer nivelCombustible) {
		this.nivelCombustible = nivelCombustible;
	}
	public String getReparacion() {
		return reparacion;
	}
	public void setReparacion(String reparacion) {
		this.reparacion = reparacion;
	}
	public byte[] getRowid() {
		return rowid;
	}
	public void setRowid(byte[] rowid) {
		this.rowid = rowid;
	}

	@Override
	public String toString() {
		return "OrdenServicio [compania=" + compania + ", orden=" + orden + ", fecha=" + fecha + ", falla=" + falla
				+ ", diagnostico=" + diagnostico + ", obs=" + obs + ", referencia=" + referencia + ", estatus="
				+ estatus + ", kilometraje=" + kilometraje + ", cliente=" + cliente + ", vehiculo=" + vehiculo
				+ ", nivelCombustible=" + nivelCombustible + ", reparacion=" + reparacion + ", rowid="
				+ Arrays.toString(rowid) + "]";
	}	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getRegistro(){
		
		Funcion funcion = new Funcion();
		
		Vector vector = new Vector();
		vector.add(this.getCompania());
		vector.add(this.getOrden());
		vector.add(funcion.dateConvertDT(this.getFecha()));
		vector.add(this.getFalla());
		vector.add(this.getDiagnostico());
		vector.add(this.getObs());
		vector.add(this.getReferencia());
		vector.add(this.getEstatus());
		vector.add(funcion.strConvertInt(this.getKilometraje()));
		vector.add(this.getCliente());
		vector.add(this.getVehiculo());
		vector.add(this.getNivelCombustible());
		vector.add(this.getReparacion());
		vector.add(this.getRowid());
		
		return vector;
	}
	
}
