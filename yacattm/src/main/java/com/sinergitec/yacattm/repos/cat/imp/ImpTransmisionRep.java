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
import com.sinergitec.yacattm.model.ct.Transmision;
import com.sinergitec.yacattm.progress.ConexionApp;
import com.sinergitec.yacattm.progress.VectorResultSet;
import com.sinergitec.yacattm.repos.cat.TransmisionRep;

import yacattm.app;

/**
 * Autor: Aestrada Fecha: 07 de junio de 2017 Descripcion: Implementacion de la
 * interfaz de transmision
 * 
 **/

@Repository
public class ImpTransmisionRep implements TransmisionRep {

	private Boolean Resultado;
	private String Mensaje;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void agregar(String cUsuario, Transmision nuevos) {

		Connection conexion = null;
		try {

			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vector = new Vector();
			vector.add(nuevos.getLista());

			ResultSetHolder tt_Nuevos = new ResultSetHolder(new VectorResultSet(vector));
			app.as_ctTrasmAuto_Inserta(cUsuario, tt_Nuevos, lhResultado, chTexto);

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
	public void eliminar(String cUsuario, Transmision viejos) {

		Connection conexion = null;

		try {

			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vector = new Vector();
			vector.add(viejos.getLista());

			ResultSet tt_Viejos = new VectorResultSet(vector);

			app.as_ctTrasmAuto_Borra(cUsuario, tt_Viejos, lhResultado, chTexto);

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
	public void actulizar(String cUsuario, Transmision viejos, Transmision nuevos) {

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

			app.as_ctTrasmAuto_Actualiza(cUsuario, tt_Viejos, tt_Nuevos, lhResultado, chTexto);

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
	public Transmision getTransmision(int iModo, String cQuery) {

		Connection conexion = null;
		Transmision transmision = new Transmision();

		try {

			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_Transmision = new ResultSetHolder();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			app.as_ctTrasmAuto_Carga(iModo, cQuery, tt_Transmision, lhResultado, chTexto);

			ResultSet rs_Transmision = tt_Transmision.getResultSetValue();

			if (rs_Transmision.next()) {
				transmision.setCompania(rs_Transmision.getString("cCveCia"));
				transmision.setTransmision(rs_Transmision.getString("cTrasmision"));
				transmision.setActivo(rs_Transmision.getBoolean("lActivo"));
				transmision.setRowid(rs_Transmision.getBytes("id"));
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

		return transmision;

	}

	@Override
	public List<Transmision> listaTransmision(int iModo, String cQuery) {

		Connection conexion = null;
		ArrayList<Transmision> lista = new ArrayList<Transmision>();

		try {

			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_Transmision = new ResultSetHolder();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			app.as_ctTrasmAuto_Carga(iModo, cQuery, tt_Transmision, lhResultado, chTexto);

			ResultSet rs_Transmision = tt_Transmision.getResultSetValue();

			while (rs_Transmision.next()) {

				Transmision tipoAuto = new Transmision();
				tipoAuto.setCompania(rs_Transmision.getString("cCveCia"));
				tipoAuto.setTransmision(rs_Transmision.getString("cTrasmision"));
				tipoAuto.setActivo(rs_Transmision.getBoolean("lActivo"));
				tipoAuto.setRowid(rs_Transmision.getBytes("id"));

				lista.add(tipoAuto);

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
