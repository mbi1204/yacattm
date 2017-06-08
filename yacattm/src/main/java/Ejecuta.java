import java.util.ArrayList;
import java.util.List;

import com.sinergitec.yacattm.model.ct.Engomado;
import com.sinergitec.yacattm.repos.cat.EngomadoRep;
import com.sinergitec.yacattm.repos.cat.imp.ImpEmgomado;

public class Ejecuta {
	
	public static void main(String [] args){
		
	/*Engomado obj1 = new Engomado();
	
	obj1.setCompania("sinergitec");
	obj1.setEmgomado("azul");
	obj1.setPlacas("5y6");
	
	System.out.println("compañia->" + obj1.getCompania());
	System.out.println("emgomado->" + obj1.getEmgomado());
	System.out.println("placas->" + obj1.getPlacas());
	System.out.println("Dia->" + obj1.getDia());*/
		
		EngomadoRep obj = new ImpEmgomado();
		List<Engomado> lista = new ArrayList<Engomado>();
		
		lista = obj.Lista(0, "FOR EACH ctEngomado where ctEngomado.cCveCia = 'AUTOTEC' NO-LOCK");
		
		for (Engomado row : lista){
			System.out.println(row.toString());
			
			
		}
		
		System.out.println("elementos->" +  lista.size());
		
		System.out.println("Mensaje" +  obj.getMensaje());
		System.out.println("Eror "   +  obj.getResultado());
		
		
		
		
		
		
		
	}

}
