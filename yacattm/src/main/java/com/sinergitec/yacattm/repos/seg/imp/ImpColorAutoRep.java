package com.sinergitec.yacattm.repos.seg.imp;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Repository;

import com.progress.open4gl.BooleanHolder;
import com.progress.open4gl.Open4GLException;
import com.progress.open4gl.ResultSetHolder;
import com.progress.open4gl.StringHolder;
import com.progress.open4gl.javaproxy.Connection;
import com.sinergitec.yacattm.model.ct.ColorAuto;
import com.sinergitec.yacattm.progress.ConexionApp;
import com.sinergitec.yacattm.progress.VectorResultSet;
import com.sinergitec.yacattm.repos.seg.ColorAutoRep;

import yacattm.app;

/**
 * 
 * @author mendoza
 *
 */
@Repository
public class ImpColorAutoRep implements ColorAutoRep {

	private boolean lResultado;
	private String cMensaje;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void AgregarColorAuto(String cUsuario, ColorAuto obj) {
		// TODO Auto-generated method stub

		Connection conexion = null;
		try {
			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vector = new Vector();
			vector.add(obj.getLista());

			ResultSetHolder tt_Nuevos = new ResultSetHolder(new VectorResultSet(vector));
			app.as_ctColorAuto_Inserta(cUsuario, tt_Nuevos, lhResultado, chTexto);

			this.setlResultado(lhResultado.getBooleanValue());
			this.setcMensaje(this.getClass().getName() + Thread.currentThread().getStackTrace()[1].getMethodName() + " "
					+ chTexto.getStringValue());

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

	@Override
	public ArrayList<ColorAuto> ListaColorAuto(String cCveCia, Boolean lCancelado) {
		// TODO Auto-generated method stub
		

		Connection conexion = null;
		ArrayList<ColorAuto> lista = new ArrayList<ColorAuto>();
		try {
			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_ColorAuto = new ResultSetHolder();
			

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			app.as_ctColorAuto_Carga(cCveCia, lCancelado, tt_ColorAuto, lhResultado, chTexto);

			ResultSet rs_ColorAuto = tt_ColorAuto.getResultSetValue();
			
			

			while (rs_ColorAuto.next()) {
				
				ColorAuto colorAuto = new ColorAuto();
				
				colorAuto.setCompania( rs_ColorAuto.getString("cCveCia"));
				colorAuto.setColor( rs_ColorAuto.getString("cColor"));
				colorAuto.setActivo( rs_ColorAuto.getBoolean("lActivo"));
				colorAuto.setRowid( rs_ColorAuto.getBytes("id"));
				lista.add(colorAuto);

			}

			this.setlResultado(lhResultado.getBooleanValue());
			this.setcMensaje(this.getClass().getName() + Thread.currentThread().getStackTrace()[1].getMethodName() + " "
					+ chTexto.getStringValue());

			app._release();

		} catch (Open4GLException | IOException | SQLException e) {
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

		return lista;
	}

	public boolean islResultado() {
		return lResultado;
	}

	public void setlResultado(boolean lResultado) {
		this.lResultado = lResultado;
	}

	public String getcMensaje() {
		return cMensaje;
	}

	public void setcMensaje(String cMensaje) {
		this.cMensaje = cMensaje;
	}

}
