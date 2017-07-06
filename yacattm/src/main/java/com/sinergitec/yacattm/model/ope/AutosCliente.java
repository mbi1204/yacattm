package com.sinergitec.yacattm.model.ope;

import java.util.Vector;

public class AutosCliente {
	
	private Integer cliente;
	private String  nombre;
	private Integer vehiculo;
	private String  matricula;
	private String  marca;
	private String  modelo;
	private String  color;
	private String  motor;
	private Integer anio;
	private byte[]  rowid;
	
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
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getMotor() {
		return motor;
	}
	public void setMotor(String motor) {
		this.motor = motor;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public byte[] getRowid() {
		return rowid;
	}
	public void setRowid(byte[] rowid) {
		this.rowid = rowid;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getRegistro(){
		
		Vector vector = new Vector();
		vector.add(this.getCliente());
		vector.add(this.getNombre());
		vector.add(this.getVehiculo());
		vector.add(this.getMatricula());
		vector.add(this.getMarca());
		vector.add(this.getModelo());
		vector.add(this.getColor());
		vector.add(this.getMotor());
		vector.add(this.getAnio());
		vector.add(this.getRowid());
		
		return vector;
	}

}
