package com.sinergitec.yacattm.repos.cat.imp;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.progress.open4gl.BooleanHolder;
import com.progress.open4gl.Open4GLException;
import com.progress.open4gl.ResultSetHolder;
import com.progress.open4gl.StringHolder;
import com.progress.open4gl.javaproxy.Connection;
import com.sinergitec.yacattm.model.ct.Vehiculo;
import com.sinergitec.yacattm.progress.ConexionApp;
import com.sinergitec.yacattm.repos.cat.VehiculoRep;

import yacattm.app;

@Repository
public class ImpVehiculoRep implements VehiculoRep {
	
	private Boolean Resultado;
	private String Mensaje;
	
	

	@Override
	public void agregar(String cUsuario, Vehiculo nuevos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(String cUsuario, Vehiculo viejos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(String cUsuario, Vehiculo viejos, Vehiculo nuevos) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vehiculo getCliente(int iModo, String cQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehiculo> Lista(int iModo, String cQuery) {
		// TODO Auto-generated method stub
		
		Connection conexion = null;
		ArrayList<Vehiculo> lista = new ArrayList<Vehiculo>();
		try {
			conexion = ConexionApp.iniConexion();
			ResultSetHolder tt_ctVehiculo = new ResultSetHolder();

			BooleanHolder lhResultado = new BooleanHolder();
			StringHolder chTexto = new StringHolder();
			app app = new app(conexion);

			app.as_ctVehiculo_Carga(iModo, cQuery, tt_ctVehiculo, lhResultado, chTexto);
			ResultSet rs_ctVehiculo = tt_ctVehiculo.getResultSetValue();
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
				// vehiculo.setFecha(rs_ctVehiculo.getTimestamp("dtFecha").toString());
				vehiculo.setCliente(rs_ctVehiculo.getInt("iCliente"));
				vehiculo.setCalcomaniaC(rs_ctVehiculo.getString("cCalcomania"));

				lista.add(vehiculo);

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
