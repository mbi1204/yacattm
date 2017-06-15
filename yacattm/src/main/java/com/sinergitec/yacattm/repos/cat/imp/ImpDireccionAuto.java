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
import com.sinergitec.yacattm.model.ct.DireccionAuto;
import com.sinergitec.yacattm.progress.ConexionApp;
import com.sinergitec.yacattm.progress.VectorResultSet;
import com.sinergitec.yacattm.repos.cat.DireccionAutoRep;
import yacattm.app;

@Repository
public class ImpDireccionAuto implements DireccionAutoRep {
	private Boolean Resultado;
	private String Mensaje;

	@Override
	public List<DireccionAuto> Lista(int iModo, String cQuery) {
		// TODO Auto-generated method stub

		Connection conexion = null;
		ArrayList<DireccionAuto> lista = new ArrayList<DireccionAuto>();

		try {
			conexion = ConexionApp.iniConexion();

			ResultSetHolder tt_DireccionAuto = new ResultSetHolder();
			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);
			app.as_ctDireccionAuto_Carga(iModo, cQuery, tt_DireccionAuto, lhResultado, chTexto);

			ResultSet rs_DireccionAuto = tt_DireccionAuto.getResultSetValue();

			while (rs_DireccionAuto.next()) {
				DireccionAuto obj = new DireccionAuto();
				obj.setCompania(rs_DireccionAuto.getString("cCveCia"));
				obj.setDireccion(rs_DireccionAuto.getString("cDireccion"));
				obj.setActivo(rs_DireccionAuto.getBoolean("lActivo"));
				obj.setRowid(rs_DireccionAuto.getBytes("Id"));
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
	public void agregar(String cUsuario, DireccionAuto nuevos) {
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

			app.as_ctDireccionAuto_Inserta(cUsuario, tt_Nuevos, lhResultado, chTexto);

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void actulizar(String cUsuario, DireccionAuto viejos, DireccionAuto nuevos) {
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

			app.as_ctDireccionAuto_Actualiza(cUsuario, tt_Viejos, tt_Nuevos, lhResultado, chTexto);

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
	public DireccionAuto getDireccionAuto(int iModo, String cQuery) {
		// TODO Auto-generated method stub
		Connection conexion = null;
		DireccionAuto obj = new DireccionAuto();

		try {

			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_DireccionAuto = new ResultSetHolder();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			app.as_ctDireccionAuto_Carga(iModo, cQuery, tt_DireccionAuto, lhResultado, chTexto);
			

			ResultSet rs_DireccionAuto = tt_DireccionAuto.getResultSetValue();			

			if (rs_DireccionAuto.next()) {
			
				obj.setCompania(rs_DireccionAuto.getString("cCveCia"));
				obj.setDireccion(rs_DireccionAuto.getString("cDireccion"));
				obj.setActivo(rs_DireccionAuto.getBoolean("lActivo"));
				obj.setRowid(rs_DireccionAuto.getBytes("Id"));

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

		return obj;
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
	public void eliminar(String cUsuario, DireccionAuto viejos) {
		// TODO Auto-generated method stub
		Connection conexion = null;
		try {
			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vector = new Vector();
			vector.add(viejos.getRegistro());

			ResultSet tt_Viejos = new VectorResultSet(vector);
						
			app.as_ctDireccionAuto_Borra(cUsuario, tt_Viejos, lhResultado, chTexto);		

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
