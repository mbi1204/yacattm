package com.sinergitec.yacattm.repos.seg;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinergitec.yacattm.model.ct.ColorAuto;

/**
 * 
 * @author mendoza
 *
 */

public interface ColorAutoRep {
	/**
	 * busca la lista de la tabla getColorAuto
	 * 
	 * @return
	 */
	
	public void agregar(String cUusario, ColorAuto nuevos);
	public void eliminar(ColorAuto viejos);
	public void actulizar(ColorAuto viejos , ColorAuto nuevos);
	public ColorAuto getColorAuto(int iModo, String cQuery);
	public List<ColorAuto> ListaColorAuto(int iModo, String cQuery);
	public Boolean getResultado();
	public String  getMensaje();
	
	
}
