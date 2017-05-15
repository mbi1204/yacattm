package com.sinergitec.yacattm.progress;

import java.io.IOException;

import com.progress.open4gl.Open4GLException;
import com.progress.open4gl.javaproxy.Connection;

public class ConexionApp {
	
	static String cURL = "AppServer://192.168.2.5:5163/asyacattm";	
	
	 
	static Connection conexion;
	
	/**
	 * conexion app server de progress pasando como parametro  AppServer://192.168.2.5:5163/asalvaky
	 * @return
	 * @throws Open4GLException
	 * @throws IOException
	 */
	public static Connection iniConexion() throws Open4GLException, IOException {
		conexion = new Connection(cURL,"", "",null);
		return conexion;
			
	}
	
	/**
	 * desconexion app server pasando como parametro la conexion de app
	 * @param conexion
	 * @throws Open4GLException
	 * @throws IOException
	 */
	public static void finConexion(Connection conexion) throws Open4GLException, IOException {
		conexion.finalize();
		conexion.releaseConnection();		
	}

}
