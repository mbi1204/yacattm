package com.sinergitec.yacattm.model.ct;

import java.util.Arrays;
import java.util.Vector;

public class Vehiculo {

	private String compania;
	private Integer vehiculo;
	private String matricula;
	private String modelo;
	private String marca;
	private Integer anio;
	private String motor;
	private String numSerie;
	private String obs;
	private String engomado;
	private Integer calcomaniaI;
	private String uso;
	private String direccion;
	private String transmision;
	private String sistema;
	private String tipo;
	private String color;
	private String pais;
	private Boolean aireAC;
	private Boolean activo;
	private String fecha;
	private Integer cliente;
	private String calcomaniaC;
	private byte[] rowid;
	
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public Integer getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Integer vehiculo) {
		this.vehiculo = vehiculo;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
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
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public String getMotor() {
		return motor;
	}
	public void setMotor(String motor) {
		this.motor = motor;
	}
	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getEngomado() {
		return engomado;
	}
	public void setEngomado(String engomado) {
		this.engomado = engomado;
	}
	public Integer getCalcomaniaI() {
		return calcomaniaI;
	}
	public void setCalcomaniaI(Integer calcomaniaI) {
		this.calcomaniaI = calcomaniaI;
	}
	public String getUso() {
		return uso;
	}
	public void setUso(String uso) {
		this.uso = uso;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTransmision() {
		return transmision;
	}
	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}
	public String getSistema() {
		return sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public Boolean getAireAC() {
		return aireAC;
	}
	public void setAireAC(Boolean aireAC) {
		this.aireAC = aireAC;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Integer getCliente() {
		return cliente;
	}
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	public String getCalcomaniaC() {
		return calcomaniaC;
	}
	public void setCalcomaniaC(String calcomaniaC) {
		this.calcomaniaC = calcomaniaC;
	}
	public byte[] getRowid() {
		return rowid;
	}
	public void setRowid(byte[] rowid) {
		this.rowid = rowid;
	}
	
	@Override
	public String toString() {
		return "Vehiculo [compania=" + compania + ", vehiculo=" + vehiculo + ", matricula=" + matricula + ", modelo="
				+ modelo + ", marca=" + marca + ", anio=" + anio + ", motor=" + motor + ", numSerie=" + numSerie
				+ ", obs=" + obs + ", engomado=" + engomado + ", calcomaniaI=" + calcomaniaI + ", uso=" + uso
				+ ", direccion=" + direccion + ", transmision=" + transmision + ", sistema=" + sistema + ", tipo="
				+ tipo + ", color=" + color + ", pais=" + pais + ", aireAC=" + aireAC + ", activo=" + activo
				+ ", fecha=" + fecha + ", cliente=" + cliente + ", calcomaniaC=" + calcomaniaC + ", rowid="
				+ Arrays.toString(rowid) + "]";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getRegistro(){
		
		Vector vector = new Vector();
		vector.add(this.getCompania());
		vector.add(this.getVehiculo());
		vector.add(this.getMatricula());
		vector.add(this.getModelo());
		vector.add(this.getMarca());
		vector.add(this.getAnio());
		vector.add(this.getMotor());
		vector.add(this.getNumSerie());
		vector.add(this.getObs());
		vector.add(this.getEngomado());
		vector.add(this.getCalcomaniaI());
		vector.add(this.getUso());
		vector.add(this.getDireccion());
		vector.add(this.getTransmision());
		vector.add(this.getSistema());
		vector.add(this.getTipo());
		vector.add(this.getColor());
		vector.add(this.getPais());
		vector.add(this.getAireAC());
		vector.add(this.getActivo());
		vector.add(this.getFecha());
		vector.add(this.getCliente());
		vector.add(this.getCalcomaniaI());
		vector.add(this.getRowid());
		
		return vector;
	}
}
