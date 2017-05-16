package com.sinergitec.yacattm.repos.seg;

import java.util.List;

import com.sinergitec.yacattm.model.ct.ColorAuto;

/**
 * 
 * @author mendoza
 *
 */
public interface RepColorAuto {
/**
 * busca la lista de la tabla  getColorAuto
 * @return
 */
	public void AgregarColorAuto(String cUusario, ColorAuto obj);  
	public List<ColorAuto> getColorAuto(String cCveCia , Boolean lCancelado);
	

}
