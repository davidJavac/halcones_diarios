package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextArea;

import controlador.ControladorArticulo;
import controlador.ControladorObservaciones;
import controlador.ControladorZona;
import modelo_dao.ArticuloDAO;
import modelo_dao.ObservacionesDAO;
import modelo_vo.ArticulosVO;
import modelo_vo.ObservacionesVO;
import modelo_vo.Observaciones_clienteVO;
import vista.BusquedaEntities;

public class LogicaObservaciones {

	ControladorObservaciones _controladorObservaciones;
	BusquedaEntities busqueda_entities;
	private int numero;
	
	public ArrayList<ObservacionesVO> validarBuscarObservacionesPedido(int n_pedido){
		
		ObservacionesDAO obDAO = new ObservacionesDAO();
		
		try {
			return obDAO.buscarObservacionPedido(n_pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public ArrayList<Observaciones_clienteVO> validarBuscarObservacionesCliente(int dni){
		
		ObservacionesDAO obDAO = new ObservacionesDAO();
		
		try {
			return obDAO.buscarObservacionCliente(dni);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int 	modificarObservacionPedido(int id, ObservacionesVO obVO){
		
		ObservacionesDAO obDAO = new ObservacionesDAO();
		
		try {
			return obDAO.modificarObservacionPedido(id, obVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	public int 	modificarObservacionCliente(int id, Observaciones_clienteVO obVO){
		
		ObservacionesDAO obDAO = new ObservacionesDAO();
		
		try {
			return obDAO.modificarObservacionCliente(id, obVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public int validarAltaObservacionesPedido(ObservacionesVO obVO){
		
		ObservacionesDAO obDAO = new ObservacionesDAO();
		
		try {
			return obDAO.nuevaObservacionPedido(obVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	public int validarAltaObservacionesCliente(Observaciones_clienteVO obVO){
		
		ObservacionesDAO obDAO = new ObservacionesDAO();
		
		try {
			return obDAO.nuevaObservacionCliente(obVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public ArticulosVO validarBusquedaUsuario(String codigo){
		
		ArticuloDAO _articuloDAO = new ArticuloDAO();

		if(validarInt(codigo)){
			
			try {
				ArticulosVO articuloVO = _articuloDAO .buscarArticulo(numero);
				return articuloVO;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		return null;
				
	}
	
	public void validarBusquedaAll(){
		
		ArticuloDAO _articulosDAO = new ArticuloDAO();
		
		busqueda_entities.setTipoBusqueda(6);
		busqueda_entities.limpiar();
		
		ArrayList<ArticulosVO> ar = new ArrayList<ArticulosVO>();
		try {
			ar = _articulosDAO.buscarArticuloAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ar!=null){
			
			for(ArticulosVO o : ar){
				
				Object [] datos = new Object[2];
				
				datos[0] = o.getCodigo();
				datos[1] = o.getNombre() + ": " + o.getDescripcion();
				
				busqueda_entities.getTabla().addRow(datos);
					
			}
			
			busqueda_entities.setTablaModel();
			
		
		if(busqueda_entities.getTabla().getRowCount() > 0)
			busqueda_entities.setTablaModel();
		}
		
	}
	
	public boolean validarObservacionesBajaCliente(JTextArea observaciones){
		
		if(observaciones.getText().length() < 30 || observaciones.getText().length() > 200){
			
			Mensajes.getMensaje_observaciones();
			
			return false;
		}
		
		return true;
	}
	public boolean validarObservaciones(JTextArea observaciones){
		
		if(observaciones.getText().length() < 30 || observaciones.getText().length() > 200){
			
			Mensajes.getMensaje_observaciones();
			
			return false;
		}
		
		return true;
	}
	
	public boolean validarInt(String val_string){
		
		try{
			
			numero = Integer.parseInt(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
		
	}
	
}
