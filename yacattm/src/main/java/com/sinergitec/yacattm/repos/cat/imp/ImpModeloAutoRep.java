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
import com.sinergitec.yacattm.model.ct.ModeloAuto;
import com.sinergitec.yacattm.progress.ConexionApp;
import com.sinergitec.yacattm.progress.VectorResultSet;
import com.sinergitec.yacattm.repos.cat.ModeloAutoRep;

import yacattm.app;

@Repository
public class ImpModeloAutoRep implements ModeloAutoRep {
	
	private Boolean Resultado;
	private String Mensaje;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void agregar(String cUsuario, ModeloAuto nuevos) {

		Connection conexion = null;
		try {

			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vector = new Vector();
			vector.add(nuevos.getLista());

			ResultSetHolder tt_Nuevos = new ResultSetHolder(new VectorResultSet(vector));
			app.as_ctAutoModelo_Inserta(cUsuario, tt_Nuevos, lhResultado, chTexto);

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
	public void eliminar(String cUsuario, ModeloAuto viejos) {
		
		Connection conexion = null;

		try {

			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vector = new Vector();
			vector.add(viejos.getLista());

			ResultSet tt_Viejos = new VectorResultSet(vector);

			app.as_ctAutoModelo_Borra(cUsuario, tt_Viejos, lhResultado, chTexto);

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
	public void actulizar(String cUsuario, ModeloAuto viejos, ModeloAuto nuevos) {

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

			app.as_ctAutoModelo_Actualiza(cUsuario, tt_Viejos, tt_Nuevos, lhResultado, chTexto);

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
	public ModeloAuto getModeloAuto(int iModo, String cQuery) {

		Connection conexion = null;
		ModeloAuto modeloAuto = new ModeloAuto();

		try {

			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_ModeloAuto = new ResultSetHolder();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			app.as_ctUsoAuto_Carga(iModo, cQuery, tt_ModeloAuto, lhResultado, chTexto);

			ResultSet rs_ModeloAuto = tt_ModeloAuto.getResultSetValue();

			if (rs_ModeloAuto.next()) {
				modeloAuto.setModelo(rs_ModeloAuto.getString("cModelo"));
				modeloAuto.setMarca(rs_ModeloAuto.getString("cMarca"));
				modeloAuto.setCompania(rs_ModeloAuto.getString("cCveCia"));
				modeloAuto.setActivo(rs_ModeloAuto.getBoolean("lActivo"));
				modeloAuto.setRowid(rs_ModeloAuto.getBytes("id"));
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

		return modeloAuto;
		
	}

	@Override
	public List<ModeloAuto> listaModeloAuto(int iModo, String cQuery) {

		Connection conexion = null;
		ArrayList<ModeloAuto> lista = new ArrayList<ModeloAuto>();

		try {

			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_ModeloAuto = new ResultSetHolder();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			app.as_ctUsoAuto_Carga(iModo, cQuery, tt_ModeloAuto, lhResultado, chTexto);

			ResultSet rs_ModeloAuto = tt_ModeloAuto.getResultSetValue();

			while (rs_ModeloAuto.next()) {

				ModeloAuto modeloAuto = new ModeloAuto();
				modeloAuto.setModelo(rs_ModeloAuto.getString("cModelo"));
				modeloAuto.setMarca(rs_ModeloAuto.getString("cMarca"));
				modeloAuto.setCompania(rs_ModeloAuto.getString("cCveCia"));
				modeloAuto.setActivo(rs_ModeloAuto.getBoolean("lActivo"));
				modeloAuto.setRowid(rs_ModeloAuto.getBytes("id"));

				lista.add(modeloAuto);

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
