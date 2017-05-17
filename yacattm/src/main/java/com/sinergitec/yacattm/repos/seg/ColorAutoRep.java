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
	public void AgregarColorAuto(String cUusario, ColorAuto obj);

	public List<ColorAuto> ListaColorAuto(String cCveCia, Boolean lCancelado);

}
