package com.sinergitec.yacattm.repos.ope.imp;

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
import com.sinergitec.yacattm.model.ct.Cliente;
import com.sinergitec.yacattm.model.ct.Vehiculo;
import com.sinergitec.yacattm.model.ope.AutosCliente;
import com.sinergitec.yacattm.model.ope.OrdenServicio;
import com.sinergitec.yacattm.progress.ConexionApp;
import com.sinergitec.yacattm.repos.ope.OrdenServRep;

import yacattm.app;

@Repository
public class ImpOrdenServRep implements OrdenServRep {

	private Boolean Resultado;
	private String Mensaje;

	@Override
	public void agregar(String cUsuario, OrdenServicio nuevos) {

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
	public List<AutosCliente> listaAutosCliente(String cCompania, String cNombre, String cMatricula, String cMarca,
			String cModelo, String cColor) {

		Connection conexion = null;
		
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
				autosCliente.setListCliente(listaCliente);
				autosCliente.setListVehiculo(listaVehiculo);
				
				lista.add(autosCliente);				
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
