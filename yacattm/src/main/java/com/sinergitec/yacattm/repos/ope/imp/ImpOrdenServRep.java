package com.sinergitec.yacattm.repos.ope.imp;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.sinergitec.yacattm.util.Funcion;

import yacattm.app;

@Repository
public class ImpOrdenServRep implements OrdenServRep {

	private Boolean Resultado;
	private String Mensaje;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public OrdenServicio agregar(String cUsuario, OrdenServicio nuevos) {
		
		OrdenServicio ordenServicio = new OrdenServicio();
		
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
			
			ResultSet rs_Nuevos = tt_Nuevos.getResultSetValue();
			if(rs_Nuevos.next()){
				ordenServicio.setOrden(rs_Nuevos.getInt("iOrden"));
			}

			app._release();

		} catch (Open4GLException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			this.setResultado(true);
			this.setMensaje("error" + " " + "Open4GLException | IOException e | SQLException " + " "
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
		
		return ordenServicio;

	}

	@Override
	public void eliminar(String cUsuario, OrdenServicio viejos) {

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void actulizar(String cUsuario, OrdenServicio viejos, OrdenServicio nuevos) {
		
		Connection conexion = null;
		
		try{
			
			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			Vector vViejos = new Vector();
			vViejos.add(viejos.getRegistro());

			Vector vNuevos = new Vector();
			vNuevos.add(nuevos.getRegistro());
			
			Vector vOpeOSInvVeh = new Vector();
			Vector vNueOpeOSInvVeh = new Vector();

			ResultSet tt_Viejos = new VectorResultSet(vViejos);
			ResultSet tt_Nuevos = new VectorResultSet(vNuevos);
			ResultSet tt_OpeOSInvVeh = new VectorResultSet(vOpeOSInvVeh);
			ResultSet tt_NueOpeOSInvVeh = new VectorResultSet(vNueOpeOSInvVeh);

			app.as_OrdenServicio_Actualiza(cUsuario, tt_Viejos, tt_Nuevos, tt_OpeOSInvVeh, tt_NueOpeOSInvVeh, lhResultado, chTexto);

			this.setResultado(lhResultado.getBooleanValue());
			this.setMensaje(chTexto.getStringValue());

			app._release();
			
		} catch(Open4GLException | IOException e){
			
			e.printStackTrace();

			this.setResultado(true);
			this.setMensaje("error" + " " + "Open4GLException | IOException | SQLException e" + " "
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
	public OrdenServicio getOrdenServ(int iModo, String cQuery) {
		
		Connection conexion = null;
		
		OrdenServicio ordenServicio = new OrdenServicio();
		ResultSetHolder tt_OrdenServ    = new ResultSetHolder();
		Funcion funcion = new Funcion();
		
		try{
			
			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);
			
			app.as_opeOrdenSer_Carga(iModo, cQuery, tt_OrdenServ, lhResultado, chTexto);
			
			this.setResultado(lhResultado.getBooleanValue());
			this.setMensaje(chTexto.getStringValue());
			
			ResultSet rs_OrdenServ  = tt_OrdenServ.getResultSetValue();
			
			while(rs_OrdenServ.next()){
				ordenServicio.setCompania(rs_OrdenServ.getString("cCveCia"));
				ordenServicio.setOrden(rs_OrdenServ.getInt("iOrden"));
				ordenServicio.setFecha(funcion.dtConvertStrWTime(rs_OrdenServ.getTimestamp("dtFecha")));
				ordenServicio.setFalla(rs_OrdenServ.getString("cFalla"));
				ordenServicio.setDiagnostico(rs_OrdenServ.getString("cDiagnostico"));
				ordenServicio.setObs(rs_OrdenServ.getString("cObs"));
				ordenServicio.setReferencia(rs_OrdenServ.getString("cReferecia"));
				ordenServicio.setEstatus(rs_OrdenServ.getString("cEstatus"));
				ordenServicio.setKilometraje(String.valueOf(rs_OrdenServ.getInt("iKilometraje")));
				ordenServicio.setCliente(rs_OrdenServ.getInt("iCliente"));
				ordenServicio.setVehiculo(rs_OrdenServ.getInt("iVehiculo"));
				ordenServicio.setNivelCombustible(rs_OrdenServ.getInt("iNivelCombustible"));
				ordenServicio.setReparacion(rs_OrdenServ.getString("cReparacion"));
				ordenServicio.setRowid(rs_OrdenServ.getBytes("Id"));
				
			}
			
			app._release();
			
		} catch(Open4GLException | IOException | SQLException e){
			
			e.printStackTrace();

			this.setResultado(true);
			this.setMensaje("error" + " " + "Open4GLException | IOException | SQLException e" + " "
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

		return ordenServicio;
	}

	@Override
	public OrdenServList listaOrdenServ(String cCompania, Integer iFiltro, String cParam1, String cParam2) {
		
		Connection conexion = null;
		
		OrdenServList ordenServList = new OrdenServList();
		
		ArrayList<OrdenServicio> lista = new ArrayList<OrdenServicio>();
		ArrayList<Cliente> listaCliente = new ArrayList<Cliente>();
		ArrayList<Vehiculo> listaVehiculo = new ArrayList<Vehiculo>();
		
		ResultSetHolder tt_OrdenServ    = new ResultSetHolder();
		ResultSetHolder tt_ctCliente    = new ResultSetHolder();
		ResultSetHolder tt_ctVehiculo   = new ResultSetHolder();
		ResultSetHolder tt_OpeOSInvVeh  = new ResultSetHolder();
		ResultSetHolder tt_OpeOrdenFile = new ResultSetHolder();
		
		try{
			
			conexion = ConexionApp.iniConexion();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);
			
			Funcion funcion = new Funcion();
			
			if(iFiltro == 1){
				app.as_OrdenServicio_Carga(cCompania, iFiltro, funcion.strConvertGC(cParam1), funcion.strConvertGC(cParam2),
						cParam1, cParam1, cParam1, tt_OrdenServ, tt_ctCliente, tt_ctVehiculo, tt_OpeOSInvVeh,
						tt_OpeOrdenFile, lhResultado, chTexto);
			} else {
				app.as_OrdenServicio_Carga(cCompania, iFiltro, funcion.strConvertGC("00/00/0000"), funcion.strConvertGC("00/00/0000"),
						cParam1, cParam1, cParam1, tt_OrdenServ, tt_ctCliente, tt_ctVehiculo, tt_OpeOSInvVeh,
						tt_OpeOrdenFile, lhResultado, chTexto);
			}
			
			this.setResultado(lhResultado.getBooleanValue());
			this.setMensaje(chTexto.getStringValue());
			
			ResultSet rs_ctCliente  = tt_ctCliente.getResultSetValue();
			ResultSet rs_ctVehiculo = tt_ctVehiculo.getResultSetValue();
			ResultSet rs_OrdenServ  = tt_OrdenServ.getResultSetValue();
			
			while(rs_OrdenServ.next()){
				OrdenServicio ordenServicio = new OrdenServicio();
				ordenServicio.setCompania(rs_OrdenServ.getString("cCveCia"));
				ordenServicio.setOrden(rs_OrdenServ.getInt("iOrden"));
				ordenServicio.setFecha(funcion.dtConvertStrWTime(rs_OrdenServ.getTimestamp("dtFecha")));
				ordenServicio.setFalla(rs_OrdenServ.getString("cFalla"));
				ordenServicio.setDiagnostico(rs_OrdenServ.getString("cDiagnostico"));
				ordenServicio.setObs(rs_OrdenServ.getString("cObs"));
				ordenServicio.setReferencia(rs_OrdenServ.getString("cReferecia"));
				ordenServicio.setEstatus(rs_OrdenServ.getString("cEstatus"));
				ordenServicio.setKilometraje(String.valueOf(rs_OrdenServ.getInt("iKilometraje")));
				ordenServicio.setCliente(rs_OrdenServ.getInt("iCliente"));
				ordenServicio.setVehiculo(rs_OrdenServ.getInt("iVehiculo"));
				ordenServicio.setNivelCombustible(rs_OrdenServ.getInt("iNivelCombustible"));
				ordenServicio.setReparacion(rs_OrdenServ.getString("cReparacion"));
				ordenServicio.setRowid(rs_OrdenServ.getBytes("Id"));
				
				lista.add(ordenServicio);
				
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
				vehiculo.setRowid(rs_ctVehiculo.getBytes("Id"));

				listaVehiculo.add(vehiculo);

			}
			
			app._release();
			
		}catch (Exception e) {
			
			e.printStackTrace();

			this.setResultado(true);
			this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
					+ this.getClass().getEnclosingMethod().getName());

			
		}finally {
			
			try {
				ConexionApp.finConexion(conexion);

			} catch (Open4GLException | IOException e) {
				e.printStackTrace();
				this.setResultado(true);
				this.setMensaje("error" + " " + "Open4GLException | IOException e" + " "
						+ this.getClass().getEnclosingMethod().getName());
			}
			
		}
		
		ordenServList.setListOrdenServicio(lista);
		ordenServList.setListCliente(listaCliente);
		ordenServList.setListVehiculo(listaVehiculo);

		return ordenServList;
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
