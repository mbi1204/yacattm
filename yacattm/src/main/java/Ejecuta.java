
import com.sinergitec.yacattm.repos.imp.ImpLogin;

public class Ejecuta {
	
	public static void main(String [] args){
		ImpLogin obj = new ImpLogin();
		
		obj.getAcceso("AUTOTEC", "SISIM", "SISIMB");
		
		System.out.println(obj.islResultado()  + " " +    obj.getcMensaje() );
		
		
		
	}

}
