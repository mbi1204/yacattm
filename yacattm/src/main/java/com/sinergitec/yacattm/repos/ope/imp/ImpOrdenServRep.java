package com.sinergitec.yacattm.repos.ope.imp;

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
import com.sinergitec.yacattm.model.ct.Vehiculo;
import com.sinergitec.yacattm.model.ope.AutosCliente;
import com.sinergitec.yacattm.model.ope.OrdenServList;
import com.sinergitec.yacattm.model.ope.OrdenServicio;
import com.sinergitec.yacattm.progress.ConexionApp;
import com.sinergitec.yacattm.progress.VectorResultSet;
import com.sinergitec.yacattm.repos.ope.OrdenServRep;

import yacattm.app;

@Repository
public class ImpOrdenServRep implements OrdenServRep {

	private Boolean Resultado;
	private String Mensaje;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void agregar(String cUsuario, OrdenServicio nuevos) {
		
		Connection conexion = null;
		try {
			conexion = ConexionApp.iniConexion();
			
			AutosCliente autosCliente = new AutosCliente();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vector = new Vector();
			vector.add(nuevos.getRegistro());
			
			Vector vectorInvVeh = new Vector();
			vectorInvVeh.add(autosCliente.getRegistro());
			
			ResultSetHolder tt_Nuevos = new ResultSetHolder(new VectorResultSet(vector));
			ResultSetHolder tt_NuevosInvVeh = new ResultSetHolder(new VectorResultSet(vectorInvVeh));
			
			app.as_OpeOrdenSer_Inserta2(cUsuario, tt_Nuevos, tt_NuevosInvVeh, lhResultado, chTexto);
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
	public void eliminar(String cUsuario, OrdenServicio viejos) {

	}

	@Override
	public void actulizar(String cUsuario, OrdenServicio viejos, OrdenServicio nuevos) {

	}

	@Override
	public OrdenServicio getOrdenServ(int iModo, String cQuery) {

		return null;
	}

	@Override
	public List<OrdenServicio> listaOrdenServ(int iModo, String cQuery) {

		return null;
	}

	@Override
	public OrdenServList listaAutosCliente(String cCompania, String cNombre, String cMatricula, String cMarca,
			String cModelo, String cColor) {

		Connection conexion = null;
		
		OrdenServList ordenServList = new OrdenServList();
		
		ArrayList<AutosCliente> lista = new ArrayList<AutosCliente>();
		ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
		ArrayList<Vehiculo> listaVehiculo = new ArrayList<Vehiculo>();
		
		ResultSetHolder tt_VehiCliente = new ResultSetHolder();
		ResultSetHolder tt_ctCliente = new ResultSetHolder();
		ResultSetHolder tt_ctVehiculo = new ResultSetHolder();

		try {

			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			app.as_vehiculosCliente_Carga(cCompania, cNombre, cMatricula, cMarca, cModelo, cColor, tt_VehiCliente,
					tt_ctCliente, tt_ctVehiculo, lhResultado, chTexto);
			
			ResultSet rs_ctCliente = tt_ctCliente.getResultSetValue();
			ResultSet rs_ctVehiculo = tt_ctVehiculo.getResultSetValue();
			ResultSet rs_VehiCliente = tt_VehiCliente.getResultSetValue();
			
			while (rs_VehiCliente.next()) {

				AutosCliente autosCliente = new AutosCliente();
				autosCliente.setCliente(rs_VehiCliente.getInt("iCliente"));
				autosCliente.setNombre(rs_VehiCliente.getString("cNombre"));
				autosCliente.setVehiculo(rs_VehiCliente.getInt("iVehiculo"));
				autosCliente.setMatricula(rs_VehiCliente.getString("cMatricula"));
				autosCliente.setMarca(rs_VehiCliente.getString("cMarca"));
				autosCliente.setModelo(rs_VehiCliente.getString("cModelo"));
				autosCliente.setAnio(rs_VehiCliente.getInt("iAnio"));
				autosCliente.setMotor(rs_VehiCliente.getString("cMotor"));
				autosCliente.setColor(rs_VehiCliente.getString("cColor"));
				
				lista.add(autosCliente);				
			}
			
			while (rs_ctCliente.next()) {

				Cliente cliente = new Cliente();
				cliente.setCompania(rs_ctCliente.getString("cCveCia"));
				cliente.setRfc(rs_ctCliente.getString("cRFC"));
				cliente.setCalle(rs_ctCliente.getString("cCalle"));
				cliente.setNumExterior(rs_ctCliente.getString("cNExterior"));
				cliente.setNumInterior(rs_ctCliente.getString("cNInterior"));
				cliente.setColonia(rs_ctCliente.getString("cColonia"));
				cliente.setMpioDeleg(rs_ctCliente.getString("cMpioDeleg"));
				cliente.setCp(rs_ctCliente.getInt("iCP"));
				cliente.setCiudad(rs_ctCliente.getString("cCiudad"));
				cliente.setEstado(rs_ctCliente.getString("cEstado"));
				cliente.setTelefono1(rs_ctCliente.getString("cTelefono1"));
				cliente.setEmail(rs_ctCliente.getString("cEmail"));
				cliente.setContacto(rs_ctCliente.getString("cContacto"));
				cliente.setPais(rs_ctCliente.getString("cPais"));
				cliente.setActivo(rs_ctCliente.getBoolean("lActivo"));
				cliente.setCliente(rs_ctCliente.getInt("iCliente"));
				cliente.setNombre(rs_ctCliente.getString("cNombre"));
				cliente.setTelefono2(rs_ctCliente.getString("cTelefono2"));
				cliente.setObs(rs_ctCliente.getString("cObs"));
				//cliente.setFecha(rs_ctCliente.getTimestamp("dtFecha").toString());
				//System.out.println("Llego hasta aqui clie");
				cliente.setRowid(rs_ctCliente.getBytes("Id"));

				listaCliente.add(cliente);

			}

			while (rs_ctVehiculo.next()) {

				Vehiculo vehiculo = new Vehiculo();
				vehiculo.setCompania(rs_ctVehiculo.getString("cCveCia"));
				vehiculo.setVehiculo(rs_ctVehiculo.getInt("iVehiculo"));
				vehiculo.setMatricula(rs_ctVehiculo.getString("cMatricula"));
				vehiculo.setModelo(rs_ctVehiculo.getString("cModelo"));
				vehiculo.setMarca(rs_ctVehiculo.getString("cMarca"));
				vehiculo.setAnio(rs_ctVehiculo.getInt("iAnio"));
				vehiculo.setMotor(rs_ctVehiculo.getString("cMotor"));
				vehiculo.setNumSerie(rs_ctVehiculo.getString("cNumeroSerie"));
				vehiculo.setObs(rs_ctVehiculo.getString("cObservaciones"));
				vehiculo.setEngomado(rs_ctVehiculo.getString("cEngomado"));
				vehiculo.setCalcomaniaI(rs_ctVehiculo.getInt("iCalcomania"));
				vehiculo.setUso(rs_ctVehiculo.getString("cUso"));
				vehiculo.setDireccion(rs_ctVehiculo.getString("cDireccion"));
				vehiculo.setTransmision(rs_ctVehiculo.getString("cTrasmision"));
				vehiculo.setSistema(rs_ctVehiculo.getString("cSistema"));
				vehiculo.setTipo(rs_ctVehiculo.getString("cTipo"));
				vehiculo.setColor(rs_ctVehiculo.getString("cColor"));
				vehiculo.setPais(rs_ctVehiculo.getString("cPais"));
				vehiculo.setAireAC(rs_ctVehiculo.getBoolean("lAireAc"));
				vehiculo.setActivo(rs_ctVehiculo.getBoolean("lActivo"));
				//vehiculo.setFecha(rs_ctVehiculo.getTimestamp("dtFecha").toString());
				vehiculo.setCliente(rs_ctVehiculo.getInt("iCliente"));
				vehiculo.setCalcomaniaC(rs_ctVehiculo.getString("cCalcomania"));

				listaVehiculo.add(vehiculo);

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
		
		ordenServList.setListAutosCliente(lista);
		ordenServList.setListCliente(listaCliente);
		ordenServList.setListVehiculo(listaVehiculo);

		return ordenServList;

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
