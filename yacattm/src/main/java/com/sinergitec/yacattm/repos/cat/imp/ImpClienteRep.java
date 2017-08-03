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
import com.sinergitec.yacattm.model.ct.Cliente;
import com.sinergitec.yacattm.progress.ConexionApp;
import com.sinergitec.yacattm.progress.VectorResultSet;
import com.sinergitec.yacattm.repos.cat.ClienteRep;

import yacattm.app;

@Repository
public class ImpClienteRep implements ClienteRep {
	private Boolean Resultado;
	private String Mensaje;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void agregar(String cUsuario, Cliente nuevos) {
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
			app.as_ctCliente_Inserta(cUsuario, tt_Nuevos, lhResultado, chTexto);
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
	public void eliminar(String cUsuario, Cliente viejos) {
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
			app.as_ctCliente_Borra(cUsuario, tt_Viejos, lhResultado, chTexto);
			

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
	public void actualizar(String cUsuario, Cliente viejos, Cliente nuevos) {
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

			app.as_ctCliente_Actualiza(cUsuario, tt_Viejos, tt_Nuevos, lhResultado, chTexto);
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
	public Cliente getCliente(int iModo, String cQuery) {
		// TODO Auto-generated method stub

		Connection conexion = null;

		Cliente obj = new Cliente();
		try {
			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_ctCliente = new ResultSetHolder();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);
			app.as_ctCliente_Carga(iModo, cQuery, tt_ctCliente, lhResultado, chTexto);

			ResultSet rs_ctCliente = tt_ctCliente.getResultSetValue();

			if (rs_ctCliente.next()) {

				obj.setCompania(rs_ctCliente.getString("cCveCia"));
				obj.setRfc(rs_ctCliente.getString("cRFC"));
				obj.setCalle(rs_ctCliente.getString("cCalle"));
				obj.setNumExterior(rs_ctCliente.getString("cNExterior"));
				obj.setNumInterior(rs_ctCliente.getString("cNInterior"));
				obj.setColonia(rs_ctCliente.getString("cColonia"));
				obj.setMpioDeleg(rs_ctCliente.getString("cMpioDeleg"));
				obj.setCp(rs_ctCliente.getInt("iCP"));
				obj.setCiudad(rs_ctCliente.getString("cCiudad"));
				obj.setEstado(rs_ctCliente.getString("cEstado"));
				obj.setTelefono1(rs_ctCliente.getString("cTelefono1"));
				obj.setEmail(rs_ctCliente.getString("cEmail"));
				obj.setContacto(rs_ctCliente.getString("cContacto"));
				obj.setPais(rs_ctCliente.getString("cPais"));
				obj.setActivo(rs_ctCliente.getBoolean("lActivo"));
				obj.setCliente(rs_ctCliente.getInt("iCliente"));
				obj.setNombre(rs_ctCliente.getString("cNombre"));
				obj.setTelefono2(rs_ctCliente.getString("cTelefono2"));
				obj.setObs(rs_ctCliente.getString("cObs"));
				obj.setFecha(rs_ctCliente.getString("dtFecha"));
				obj.setRowid(rs_ctCliente.getBytes("Id"));
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

		return obj;

	}

	@Override
	public List<Cliente> Lista(int iModo, String cQuery) {
		// TODO Auto-generated method stub
		Connection conexion = null;
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		try {
			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_ctCliente = new ResultSetHolder();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			app.as_ctCliente_Carga(iModo, cQuery, tt_ctCliente, lhResultado, chTexto);

			ResultSet rs_ctCliente = tt_ctCliente.getResultSetValue();

			while (rs_ctCliente.next()) {

				Cliente obj = new Cliente();
				
				
				

				obj.setCompania(rs_ctCliente.getString("cCveCia"));
				obj.setRfc(rs_ctCliente.getString("cRFC"));
				obj.setCalle(rs_ctCliente.getString("cCalle"));
				obj.setNumExterior(rs_ctCliente.getString("cNExterior"));
				obj.setNumInterior(rs_ctCliente.getString("cNInterior"));
				obj.setColonia(rs_ctCliente.getString("cColonia"));
				obj.setMpioDeleg(rs_ctCliente.getString("cMpioDeleg"));
				obj.setCp(rs_ctCliente.getInt("iCP"));
				obj.setCiudad(rs_ctCliente.getString("cCiudad"));
				obj.setEstado(rs_ctCliente.getString("cEstado"));
				obj.setTelefono1(rs_ctCliente.getString("cTelefono1"));
				obj.setEmail(rs_ctCliente.getString("cEmail"));
				obj.setContacto(rs_ctCliente.getString("cContacto"));
				obj.setPais(rs_ctCliente.getString("cPais"));
				obj.setActivo(rs_ctCliente.getBoolean("lActivo"));
				obj.setCliente(rs_ctCliente.getInt("iCliente"));
				obj.setNombre(rs_ctCliente.getString("cNombre"));
				obj.setTelefono2(rs_ctCliente.getString("cTelefono2"));
				obj.setObs(rs_ctCliente.getString("cObs"));
				obj.setFecha(rs_ctCliente.getString("dtFecha"));
				obj.setRowid(rs_ctCliente.getBytes("Id"));
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
