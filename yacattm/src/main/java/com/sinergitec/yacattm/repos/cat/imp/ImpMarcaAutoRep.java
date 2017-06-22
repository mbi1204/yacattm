package com.sinergitec.yacattm.repos.cat.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinergitec.yacattm.model.ct.MarcaAuto;
import com.sinergitec.yacattm.repos.cat.MarcaAutoRep;

@Repository
public class ImpMarcaAutoRep implements MarcaAutoRep {
	
	private Boolean Resultado;
	private String Mensaje;

	@Override
	public void agregar(String cUsuario, MarcaAuto nuevos) {


	}

	@Override
	public void eliminar(String cUsuario, MarcaAuto viejos) {


	}

	@Override
	public void actulizar(String cUsuario, MarcaAuto viejos, MarcaAuto nuevos) {


	}

	@Override
	public MarcaAuto getMarcaAuto(int iModo, String cQuery) {

		return null;
	}

	@Override
	public List<MarcaAuto> listaMarcasAuto(int iModo, String cQuery) {

		return null;
	}

	@Override
	public Boolean getResultado() {

		return null;
	}

	@Override
	public String getMensaje() {

		return null;
	}

}
