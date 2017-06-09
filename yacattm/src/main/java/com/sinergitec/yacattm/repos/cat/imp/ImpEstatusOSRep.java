package com.sinergitec.yacattm.repos.cat.imp;

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
import com.sinergitec.yacattm.model.ct.EstatusOS;
import com.sinergitec.yacattm.progress.ConexionApp;
import com.sinergitec.yacattm.progress.VectorResultSet;
import com.sinergitec.yacattm.repos.cat.EstatusOSRep;

import yacattm.app;

/**
 * Autor: Aestrada Fecha: 07 de junio de 2017 Descripcion: Implementacion de la
 * interfaz de estatus OS
 * 
 **/

@Repository
public class ImpEstatusOSRep implements EstatusOSRep {

	private Boolean Resultado;
	private String Mensaje;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void agregar(String cUsuario, EstatusOS nuevos) {

		Connection conexion = null;
		try {

			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vector = new Vector();
			vector.add(nuevos.getLista());

			ResultSetHolder tt_Nuevos = new ResultSetHolder(new VectorResultSet(vector));
			app.as_ctEstatusOS_Inserta(cUsuario, tt_Nuevos, lhResultado, chTexto);

			this.setResultado(lhResultado.getBooleanValue());
			this.setMensaje(chTexto.getStringValue());

			app._release();

		} catch (Open4GLException | IOException e) {
			e.printStackTrace();
			this.setResultado(true);
			this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
					+ this.getClass().getEnclosingMethod().getName());

		} finally {
			try {
				ConexionApp.finConexion(conexion);

			} catch (Open4GLException | IOException e) {
				e.printStackTrace();
				this.setResultado(true);
				this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
						+ this.getClass().getEnclosingMethod().getName());
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void actulizar(String cUsuario, EstatusOS viejos, EstatusOS nuevos) {

		Connection conexion = null;

		try {
			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vViejos = new Vector();
			vViejos.add(viejos.getLista());

			Vector vNuevos = new Vector();
			vNuevos.add(nuevos.getLista());

			ResultSet tt_Viejos = new VectorResultSet(vViejos);
			ResultSet tt_Nuevos = new VectorResultSet(vNuevos);

			app.as_ctEstatusOS_Actualiza(cUsuario, tt_Viejos, tt_Nuevos, lhResultado, chTexto);

			this.setResultado(lhResultado.getBooleanValue());
			this.setMensaje(chTexto.getStringValue());

			app._release();

		} catch (Open4GLException | IOException e) {
			e.printStackTrace();

			this.setResultado(true);
			this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
					+ this.getClass().getEnclosingMethod().getName());

		} finally {
			try {
				ConexionApp.finConexion(conexion);

			} catch (Open4GLException | IOException e) {
				e.printStackTrace();
				this.setResultado(true);
				this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
						+ this.getClass().getEnclosingMethod().getName());
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void eliminar(String cUsuario, EstatusOS viejos) {

		Connection conexion = null;

		try {

			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vector = new Vector();
			vector.add(viejos.getLista());

			ResultSet tt_Viejos = new VectorResultSet(vector);

			app.as_ctEstatusOS_Borra(cUsuario, tt_Viejos, lhResultado, chTexto);

			this.setResultado(lhResultado.getBooleanValue());
			this.setMensaje(chTexto.getStringValue());

			app._release();

		} catch (Open4GLException | IOException e) {
			e.printStackTrace();

			this.setResultado(true);
			this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
					+ this.getClass().getEnclosingMethod().getName());
		} finally {
			try {
				ConexionApp.finConexion(conexion);

			} catch (Open4GLException | IOException e) {
				e.printStackTrace();
				this.setResultado(true);
				this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
						+ this.getClass().getEnclosingMethod().getName());
			}
		}
	}

	@Override
	public EstatusOS getEstatusOS(int iModo, String cQuery) {

		Connection conexion = null;
		EstatusOS estatusOS = new EstatusOS();

		try {

			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_EstatusOS = new ResultSetHolder();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			app.as_ctEngomado_Carga(iModo, cQuery, tt_EstatusOS, lhResultado, chTexto);

			ResultSet rs_EstatusOS = tt_EstatusOS.getResultSetValue();

			if (rs_EstatusOS.next()) {
				estatusOS.setCompania(rs_EstatusOS.getString("cCveCia"));
				estatusOS.setEstatus(rs_EstatusOS.getString("cEstatus"));
				estatusOS.setObs(rs_EstatusOS.getString("cObservacion"));
				estatusOS.setActivo(rs_EstatusOS.getBoolean("lActivo"));
				estatusOS.setRowid(rs_EstatusOS.getBytes("id"));
			}

			this.setResultado(lhResultado.getBooleanValue());
			this.setMensaje(chTexto.getStringValue());

			app._release();

		} catch (Open4GLException | IOException | SQLException e) {
			e.printStackTrace();

			this.setResultado(true);
			this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
					+ this.getClass().getEnclosingMethod().getName());
		} finally {
			try {
				ConexionApp.finConexion(conexion);

			} catch (Open4GLException | IOException e) {
				e.printStackTrace();
				this.setResultado(true);
				this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
						+ this.getClass().getEnclosingMethod().getName());
			}
		}

		return estatusOS;
	}

	@Override
	public List<EstatusOS> listaEstatusOS(int iModo, String cQuery) {
		
		Connection conexion = null;
		ArrayList<EstatusOS> lista = new ArrayList<EstatusOS>();

		try {

			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_EstatusOS = new ResultSetHolder();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			app.as_ctEstatusOS_Carga(iModo, cQuery, tt_EstatusOS, lhResultado, chTexto);

			ResultSet rs_EstatusOS = tt_EstatusOS.getResultSetValue();

			while (rs_EstatusOS.next()) {

				EstatusOS estatusOS = new EstatusOS();
				estatusOS.setCompania(rs_EstatusOS.getString("cCveCia"));
				estatusOS.setEstatus(rs_EstatusOS.getString("cEstatus"));
				estatusOS.setObs(rs_EstatusOS.getString("cObservacion"));
				estatusOS.setActivo(rs_EstatusOS.getBoolean("lActivo"));
				estatusOS.setRowid(rs_EstatusOS.getBytes("id"));

				lista.add(estatusOS);

			}

			this.setResultado(lhResultado.getBooleanValue());
			this.setMensaje(chTexto.getStringValue());

			app._release();

		} catch (Open4GLException | IOException | SQLException e) {

			e.printStackTrace();

			this.setResultado(true);
			this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
					+ this.getClass().getEnclosingMethod().getName());

		} finally {
			try {
				ConexionApp.finConexion(conexion);

			} catch (Open4GLException | IOException e) {
				e.printStackTrace();
				this.setResultado(true);
				this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
						+ this.getClass().getEnclosingMethod().getName());
			}
		}

		return lista;
	}

	@Override
	public Boolean getResultado() {

		return Resultado;
	}

	public void setResultado(Boolean resultado) {
		Resultado = resultado;
	}

	@Override
	public String getMensaje() {

		return Mensaje;
	}

	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}

}
