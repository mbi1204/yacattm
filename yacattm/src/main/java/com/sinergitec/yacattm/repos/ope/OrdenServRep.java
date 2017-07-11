package com.sinergitec.yacattm.repos.ope;

import java.util.List;

import com.sinergitec.yacattm.model.ope.OrdenServList;
import com.sinergitec.yacattm.model.ope.OrdenServicio;

public interface OrdenServRep {

	public OrdenServicio agregar(String cUsuario, OrdenServicio nuevos);
	public void eliminar(String cUsuario, OrdenServicio viejos);
	public void actulizar(String cUsuario, OrdenServicio viejos, OrdenServicio nuevos);
	public OrdenServicio getOrdenServ(int iModo, String cQuery);
	public List<OrdenServicio> listaOrdenServ(String cCompania, String cParam1, String cParam2);
	public Boolean getResultado();
	public String getMensaje();
	public OrdenServList listaAutosCliente(String cCompania, String cNombre, String cMatricula, String cMarca,
			String cModelo, String cColor);

}
