package com.sinergitec.yacattm.repos.ope;

import com.sinergitec.yacattm.model.ope.OrdenServList;
import com.sinergitec.yacattm.model.ope.OrdenServicio;

public interface OrdenServRep {

	public OrdenServicio agregar(String cUsuario, OrdenServicio nuevos);
	public void eliminar(String cUsuario, OrdenServicio viejos);
	public void actulizar(String cUsuario, OrdenServicio viejos, OrdenServicio nuevos);
	public OrdenServicio getOrdenServ(int iModo, String cQuery);
	public OrdenServList listaOrdenServ(String cCompania, Integer iFiltro, String cParam1, String cParam2);
	public OrdenServList listaAutosCliente(String cCompania, String cNombre, String cMatricula, String cMarca,
			String cModelo, String cColor);
	public Boolean getResultado();
	public String getMensaje();

}
