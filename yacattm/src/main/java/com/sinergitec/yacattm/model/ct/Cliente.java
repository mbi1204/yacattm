package com.sinergitec.yacattm.model.ct;

import java.util.Arrays;
import java.util.Vector;

public class Cliente {

	private String compania;
	private String rfc;
	private String calle;
	private String numExterior;
	private String numInterior;
	private String colonia;
	private String mpioDeleg;
	private Integer cp;
	private String ciudad;
	private String estado;
	private String telefono1;
	private String email;
	private String contacto;
	private String pais;
	private Boolean activo;
	private Integer cliente;
	private String nombre;
	private String telefono2;
	private String obs;
	private String fecha;
	private byte[] rowid;

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumExterior() {
		return numExterior;
	}

	public void setNumExterior(String numExterior) {
		this.numExterior = numExterior;
	}

	public String getNumInterior() {
		return numInterior;
	}

	public void setNumInterior(String numInterior) {
		this.numInterior = numInterior;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getMpioDeleg() {
		return mpioDeleg;
	}

	public void setMpioDeleg(String mpioDeleg) {
		this.mpioDeleg = mpioDeleg;
	}

	public Integer getCp() {
		return cp;
	}

	public void setCp(Integer cp) {
		this.cp = cp;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public byte[] getRowid() {
		return rowid;
	}

	public void setRowid(byte[] rowid) {
		this.rowid = rowid;
	}

	@Override
	public String toString() {
		return "Cliente [compania=" + compania + ", rfc=" + rfc + ", calle=" + calle + ", numExterior=" + numExterior
				+ ", numInterior=" + numInterior + ", colonia=" + colonia + ", mpioDeleg=" + mpioDeleg + ", cp=" + cp
				+ ", ciudad=" + ciudad + ", estado=" + estado + ", telefono1=" + telefono1 + ", email=" + email
				+ ", contacto=" + contacto + ", pais=" + pais + ", activo=" + activo + ", cliente=" + cliente
				+ ", nombre=" + nombre + ", telefono2=" + telefono2 + ", obs=" + obs + ", fecha=" + fecha + ", rowid="
				+ Arrays.toString(rowid) + "]";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getRegistro(){
		Vector vector = new Vector();
		vector.add(this.getCompania());
		vector.add(this.getRfc());
		vector.add(this.getCalle());
		vector.add(this.getNumExterior());
		vector.add(this.getNumInterior());
		vector.add(this.getColonia());
		vector.add(this.getMpioDeleg());
		vector.add((this.getCp() == null) ? 0 : this.getCp());
		vector.add(this.getCiudad());
		vector.add(this.getEstado());
		vector.add(this.getTelefono1());
		vector.add(this.getEmail());
		vector.add(this.getContacto());
		vector.add(this.getPais());
		vector.add(this.getActivo());
		vector.add((this.getCliente() == null ) ? 0 : this.getCliente() );
		vector.add(this.getNombre());
		vector.add(this.getTelefono2());
		vector.add(this.getObs());
		vector.add(this.getFecha());
		vector.add(this.getRowid());
		
		return vector;
	}

	
	
	
	
}
