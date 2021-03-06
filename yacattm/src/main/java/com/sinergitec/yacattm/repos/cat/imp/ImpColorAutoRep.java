package com.sinergitec.yacattm.repos.cat.imp;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.codehaus.groovy.control.messages.Message;
import org.springframework.stereotype.Repository;

import com.progress.open4gl.BooleanHolder;
import com.progress.open4gl.Open4GLException;
import com.progress.open4gl.ResultSetHolder;
import com.progress.open4gl.StringHolder;
import com.progress.open4gl.javaproxy.Connection;
import com.sinergitec.yacattm.model.ct.ColorAuto;
import com.sinergitec.yacattm.progress.ConexionApp;
import com.sinergitec.yacattm.progress.VectorResultSet;
import com.sinergitec.yacattm.repos.cat.ColorAutoRep;

import yacattm.app;

/**
 * 
 * @author mendoza
 *
 */
@Repository
public class ImpColorAutoRep implements ColorAutoRep {
    private Boolean Resultado ;
    private String  Mensaje;


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void agregar(String cUsuario, ColorAuto obj) {
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

	@Override
	public ArrayList<ColorAuto> ListaColorAuto(int iModo, String cQuery) {
		// TODO Auto-generated method stub

		Connection conexion = null;
		ArrayList<ColorAuto> lista = new ArrayList<ColorAuto>();
		try {
			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_ColorAuto = new ResultSetHolder();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			app.as_ctColorAuto_Carga(iModo, cQuery, tt_ColorAuto, lhResultado, chTexto);

			System.out.println(chTexto.getStringValue());

			ResultSet rs_ColorAuto = tt_ColorAuto.getResultSetValue();

			while (rs_ColorAuto.next()) {

				ColorAuto colorAuto = new ColorAuto();

				colorAuto.setCompania(rs_ColorAuto.getString("cCveCia"));
				colorAuto.setColor(rs_ColorAuto.getString("cColor"));
				colorAuto.setActivo(rs_ColorAuto.getBoolean("lActivo"));
				colorAuto.setRowid(rs_ColorAuto.getBytes("id"));
				lista.add(colorAuto);

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

	public ColorAuto getColorAuto(int iModo, String cQuery) {
		Connection conexion = null;
		
		ColorAuto colorAuto = new ColorAuto();
		try {
			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_ColorAuto = new ResultSetHolder();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);
			
			
			app.as_ctColorAuto_Carga(iModo, cQuery, tt_ColorAuto, lhResultado, chTexto);

			
			ResultSet rs_ColorAuto = tt_ColorAuto.getResultSetValue();
			
			
			if (rs_ColorAuto.next()) {			

				colorAuto.setCompania(rs_ColorAuto.getString("cCveCia"));
				colorAuto.setColor(rs_ColorAuto.getString("cColor"));
				colorAuto.setActivo(rs_ColorAuto.getBoolean("lActivo"));
				colorAuto.setRowid(rs_ColorAuto.getBytes("id"));
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
		
		return colorAuto;

	}

	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void eliminar(String cUsuario,ColorAuto viejos) {
		// TODO Auto-generated method stub
		Connection conexion = null;
		try {
			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vector = new Vector();
			vector.add(viejos.getLista());

			ResultSet tt_Viejos = new VectorResultSet(vector);
						
			app.as_ctColorAuto_Borra(cUsuario, tt_Viejos, lhResultado, chTexto);		

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void actulizar(String cUsuario, ColorAuto viejos, ColorAuto nuevos) {
		// TODO Auto-generated method stub
		
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
						
			app.as_ctColorAuto_Actualiza(cUsuario, tt_Viejos, tt_Nuevos, lhResultado, chTexto);			
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

	
	


}
