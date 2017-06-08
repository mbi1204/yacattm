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
import com.sinergitec.yacattm.model.ct.Engomado;
import com.sinergitec.yacattm.progress.ConexionApp;
import com.sinergitec.yacattm.progress.VectorResultSet;
import com.sinergitec.yacattm.repos.cat.EngomadoRep;

import yacattm.app;

@Repository
public class ImpEmgomado implements EngomadoRep {

	private Boolean Resultado;
	private String Mensaje;

	@Override
	public List<Engomado> Lista(int iModo, String cQuery) {
		// TODO Auto-generated method stub

		Connection conexion = null;
		ArrayList<Engomado> lista = new ArrayList<Engomado>();

		try {
			conexion = ConexionApp.iniConexion();

			ResultSetHolder tt_Engomado = new ResultSetHolder();
			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);
			app.as_ctEngomado_Carga(iModo, cQuery, tt_Engomado, lhResultado, chTexto);

			ResultSet rs_Engomado = tt_Engomado.getResultSetValue();

			while (rs_Engomado.next()) {
				Engomado obj = new Engomado();
				obj.setCompania(rs_Engomado.getString("cCveCia"));
				obj.setEngomado(rs_Engomado.getString("cEngomado"));
				obj.setPlacas(rs_Engomado.getString("cPlacas"));
				obj.setPeriodo1(rs_Engomado.getString("cPeriodo1"));
				obj.setPeriodo2(rs_Engomado.getString("cPeriodo2"));
				obj.setActivo(rs_Engomado.getBoolean("lActivo"));
				obj.setDia(rs_Engomado.getString("cDia"));
				obj.setOrden(rs_Engomado.getInt("iOrden"));
				obj.setRowid(rs_Engomado.getBytes("Id"));
				lista.add(obj);
			}

			this.setResultado(lhResultado.getBooleanValue());
			this.setMensaje(chTexto.getStringValue());

			app._release();

		} catch (Open4GLException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			this.setResultado(true);
			this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
					+ this.getClass().getEnclosingMethod().getName());
		} finally {
			try {
				ConexionApp.finConexion(conexion);

			} catch (Open4GLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.setResultado(true);
				this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
						+ this.getClass().getEnclosingMethod().getName());
			}
		}

		return lista;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void actulizar(String cUsuario, Engomado viejos, Engomado nuevos) {
		// TODO Auto-generated method stub
		Connection conexion = null;
		
		try {
			
			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vViejos = new Vector();
			vViejos.add(viejos.getRegistro());
						
			Vector vNuevos = new Vector();
			vNuevos.add(nuevos.getRegistro());
			
			ResultSet tt_Viejos = new VectorResultSet(vViejos);
			ResultSet tt_Nuevos = new VectorResultSet(vNuevos);
			
			app.as_ctEngomado_Actualiza(cUsuario, tt_Viejos, tt_Nuevos, lhResultado, chTexto);
			
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
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.setResultado(true);
				this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
						+ this.getClass().getEnclosingMethod().getName());
			}
		}		
	}

	@Override
	public Engomado getEngomado(int iModo, String cQuery) {
		
		Connection conexion = null;
		Engomado engomado = new Engomado();
		
		try {
			
			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_Engomado = new ResultSetHolder();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);
			
			app.as_ctEngomado_Carga(iModo, cQuery, tt_Engomado, lhResultado, chTexto);
			
			ResultSet rs_Engomado = tt_Engomado.getResultSetValue();
			
			if(rs_Engomado.next()){
				engomado.setCompania(rs_Engomado.getString("cCveCia"));
				engomado.setEngomado(rs_Engomado.getString("cEngomado"));
				engomado.setPlacas(rs_Engomado.getString("cPlacas"));
				engomado.setPeriodo1(rs_Engomado.getString("cPeriodo1"));
				engomado.setPeriodo2(rs_Engomado.getString("cPeriodo2"));
				engomado.setActivo(rs_Engomado.getBoolean("lActivo"));
				engomado.setDia(rs_Engomado.getString("cDia"));
				engomado.setOrden(rs_Engomado.getInt("iOrden"));
				engomado.setRowid(rs_Engomado.getBytes("Id"));
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
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.setResultado(true);
				this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
						+ this.getClass().getEnclosingMethod().getName());
			}
		}
		
		return engomado;
	}

	public Boolean getResultado() {
		return Resultado;
	}

	public void setResultado(Boolean resultado) {
		Resultado = resultado;
	}

	public String getMensaje() {
		return Mensaje;
	}

	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void agregar(String cUsuario, Engomado nuevos) {
		// TODO Auto-generated method stub

		Connection conexion = null;

		try {
			conexion = ConexionApp.iniConexion();
			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vector = new Vector();

			vector.add(nuevos.getRegistro());

			ResultSetHolder tt_Nuevos = new ResultSetHolder(new VectorResultSet(vector));

			app.as_ctEngomado_Inserta(cUsuario, tt_Nuevos, lhResultado, chTexto);

			this.setResultado(lhResultado.getBooleanValue());
			this.setMensaje(chTexto.getStringValue());

			app._release();

		} catch (Open4GLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.setResultado(true);
			this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
					+ this.getClass().getEnclosingMethod().getName());
		} finally {
			try {
				ConexionApp.finConexion(conexion);

			} catch (Open4GLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.setResultado(true);
				this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
						+ this.getClass().getEnclosingMethod().getName());
			}
		}

	}

}
