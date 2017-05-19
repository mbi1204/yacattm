
import java.awt.Color;

import com.sinergitec.yacattm.model.ct.ColorAuto;
import com.sinergitec.yacattm.repos.seg.imp.ImpLoginRep;
import com.sinergitec.yacattm.repos.seg.imp.ImpColorAutoRep;

public class Ejecuta {
	
	public static void main(String [] args){
		/*ImpLogin obj = new ImpLogin();
		
		obj.getAcceso("AUTOTEC", "SISIM", "SISIMB");
		
		System.out.println(obj.islResultado()  + " " +    obj.getcMensaje() );
		*/
		
		ImpColorAutoRep obj = new ImpColorAutoRep();
		
		ColorAuto colorauto = new ColorAuto();
		
		colorauto.setActivo(false);
		colorauto.setColor("achul");
		colorauto.setCompania("SINER");
		colorauto.setRowid(null);
		
		
	//	obj.AgregarColorAuto("SISIMB", colorauto);
	//	System.out.println("-error------>" + " " + obj.getcMensaje());		
		
		/*obj.getColorAuto("autotec", false);*/
		
		
		
		
		
		
	}

}
