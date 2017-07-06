package com.sinergitec.yacattm.model.ope;

import java.util.List;

import com.sinergitec.yacattm.model.ct.Cliente;
import com.sinergitec.yacattm.model.ct.Vehiculo;

public class OrdenServList {
	
	private List<AutosCliente> listAutosCliente;
	private List<Cliente> listCliente;
	private List<Vehiculo> listVehiculo;
	
	public List<AutosCliente> getListAutosCliente() {
		return listAutosCliente;
	}
	public void setListAutosCliente(List<AutosCliente> listAutosCliente) {
		this.listAutosCliente = listAutosCliente;
	}
	public List<Cliente> getListCliente() {
		return listCliente;
	}
	public void setListCliente(List<Cliente> listCliente) {
		this.listCliente = listCliente;
	}
	public List<Vehiculo> getListVehiculo() {
		return listVehiculo;
	}
	public void setListVehiculo(List<Vehiculo> listVehiculo) {
		this.listVehiculo = listVehiculo;
	}
	
	@Override
	public String toString() {
		return "OrdenServList [listAutosCliente=" + listAutosCliente + ", listCliente=" + listCliente
				+ ", listVehiculo=" + listVehiculo + "]";
	}

}
