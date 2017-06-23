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
import com.sinergitec.yacattm.model.ct.MarcaAuto;
import com.sinergitec.yacattm.progress.ConexionApp;
import com.sinergitec.yacattm.progress.VectorResultSet;
import com.sinergitec.yacattm.repos.cat.MarcaAutoRep;

import yacattm.app;

@Repository
public class ImpMarcaAutoRep implements MarcaAutoRep {

	private Boolean Resultado;
	private String Mensaje;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void agregar(String cUsuario, MarcaAuto nuevos) {

		Connection conexion = null;
		try {

			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vector = new Vector();
			vector.add(nuevos.getLista());

			ResultSetHolder tt_Nuevos = new ResultSetHolder(new VectorResultSet(vector));
			app.as_ctAutoMarca_Inserta(cUsuario, tt_Nuevos, lhResultado, chTexto);

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
	public void eliminar(String cUsuario, MarcaAuto viejos) {

		Connection conexion = null;

		try {

			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vector = new Vector();
			vector.add(viejos.getLista());

			ResultSet tt_Viejos = new VectorResultSet(vector);

			app.as_ctAutoMarca_Borra(cUsuario, tt_Viejos, lhResultado, chTexto);

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
	public void actulizar(String cUsuario, MarcaAuto viejos, MarcaAuto nuevos) {

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

			app.as_ctAutoMarca_Actualiza(cUsuario, tt_Viejos, tt_Nuevos, lhResultado, chTexto);

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
	public MarcaAuto getMarcaAuto(int iModo, String cQuery) {

		Connection conexion = null;
		MarcaAuto marcaAuto = new MarcaAuto();

		try {

			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_MarcaAuto = new ResultSetHolder();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			app.as_ctAutoMarca_Carga(iModo, cQuery, tt_MarcaAuto, lhResultado, chTexto);

			ResultSet rs_MarcaAuto = tt_MarcaAuto.getResultSetValue();

			if (rs_MarcaAuto.next()) {
				marcaAuto.setMarca(rs_MarcaAuto.getString("cMarca"));
				marcaAuto.setCompania(rs_MarcaAuto.getString("cCveCia"));
				marcaAuto.setActivo(rs_MarcaAuto.getBoolean("lActiva"));
				marcaAuto.setRowid(rs_MarcaAuto.getBytes("id"));
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

		return marcaAuto;

	}

	@Override
	public List<MarcaAuto> listaMarcasAuto(int iModo, String cQuery) {

		Connection conexion = null;
		ArrayList<MarcaAuto> lista = new ArrayList<MarcaAuto>();

		try {

			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_MarcaAuto = new ResultSetHolder();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			app.as_ctAutoMarca_Carga(iModo, cQuery, tt_MarcaAuto, lhResultado, chTexto);

			ResultSet rs_MarcaAuto = tt_MarcaAuto.getResultSetValue();

			while (rs_MarcaAuto.next()) {

				MarcaAuto marcaAuto = new MarcaAuto();
				marcaAuto.setMarca(rs_MarcaAuto.getString("cMarca"));
				marcaAuto.setCompania(rs_MarcaAuto.getString("cCveCia"));
				marcaAuto.setActivo(rs_MarcaAuto.getBoolean("lActiva"));
				marcaAuto.setRowid(rs_MarcaAuto.getBytes("id"));

				lista.add(marcaAuto);

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
