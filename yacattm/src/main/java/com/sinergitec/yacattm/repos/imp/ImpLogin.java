package com.sinergitec.yacattm.repos.imp;

import java.io.IOException;

import com.progress.open4gl.BooleanHolder;
import com.progress.open4gl.Open4GLException;
import com.progress.open4gl.StringHolder;
import com.progress.open4gl.javaproxy.Connection;
import com.sinergitec.yacattm.progress.ConexionApp;
import com.sinergitec.yacattm.repos.Login;
import yacattm.*;

public class ImpLogin implements Login {

	private boolean lResultado;
	private String cMensaje;

	@Override
	public void getAcceso(String cCompania, String cUsuario, String cPassword) {
		// TODO Auto-generated method stub
		
		

		Connection conexion = null;
		try {
			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			app.as_Acceso_Carga(cCompania, cUsuario, cPassword, lhResultado, chTexto);

			this.setlResultado(lhResultado.getBooleanValue());
			this.setcMensaje(this.getClass().getName() + Thread.currentThread().getStackTrace()[1].getMethodName()  + " " +   chTexto.getStringValue());

			app._release();

		} catch (Open4GLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			this.setlResultado(true);
			this.setcMensaje("error" + " " + "Open4GLException | IOException e" + " "
					+ this.getClass().getEnclosingMethod().getName());

		} finally {
			try {
				ConexionApp.finConexion(conexion);
				
			} catch (Open4GLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.setlResultado(true);
				this.setcMensaje("error" + " " + "Open4GLException | IOException e" + " "
						+ this.getClass().getEnclosingMethod().getName());
			}
		}

	}

	/**
	 * Regresa el resultado true error, false no hay error
	 * 
	 * @return
	 */
	public boolean islResultado() {
		return lResultado;
	}

	/**
	 * Modifica el resultado
	 * 
	 * @param lResultado
	 */

	public void setlResultado(boolean lResultado) {
		this.lResultado = lResultado;
	}

	/**
	 * regresa el mensaje del resultado en teoria blanco es sin error
	 * 
	 * @return
	 */

	public String getcMensaje() {
		return cMensaje;
	}

	/**
	 * cambia el mensaje del resultado
	 * 
	 * @param cMensaje
	 */

	public void setcMensaje(String cMensaje) {
		this.cMensaje = cMensaje;
	}

}
