package com.sinergitec.yacattm.repos.cat;

import java.util.List;

import com.sinergitec.yacattm.model.ct.Vehiculo;;

public interface VehiculoRep {
	
	public void agregar(String cUsuario , Vehiculo nuevos );
	public void eliminar(String cUsuario, Vehiculo viejos);
	public void actualizar (String cUsuario, Vehiculo viejos, Vehiculo nuevos);
	public Vehiculo getVehiculo (int iModo, String cQuery);
	public List<Vehiculo> Lista (int iModo, String cQuery);
	public Boolean getResultado();
	public String getMensaje();
	

}
