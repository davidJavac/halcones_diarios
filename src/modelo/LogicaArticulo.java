package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.ControladorArticulo;
import controlador.ControladorPedidos;
import controlador.ControladorZona;
import modelo_dao.ArticuloDAO;
import modelo_dao.PedidoDAO;
import modelo_dao.VendedorDAO;
import modelo_dao.ZonaDAO;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Combinacion_diasVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.SeccionVO;
import vista.BusquedaEntities;

public class LogicaArticulo {

	ControladorArticulo _controladorArticulo;
	private ControladorPedidos controladorPedido = new ControladorPedidos();
	private LogicaPedido logica_pedido = new LogicaPedido();
	BusquedaEntities busqueda_entities;
	private int numero;
	private double numero_double;
	
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
	public ArticulosVO buscarArticulo_porCodigo(int codigo){
		
		ArticuloDAO _articuloDAO = new ArticuloDAO();

			
			try {
				ArticulosVO articuloVO = _articuloDAO .buscarArticulo(codigo);
				return articuloVO;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	
		
		return null;
				
	}
	public SeccionVO validarBusquedaSeccion(String codigo){
		
		ArticuloDAO _articuloDAO = new ArticuloDAO();
		
		if(validarInt(codigo)){
			
			try {
				SeccionVO sVO = _articuloDAO .buscarSeccion(numero);
				return sVO;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		return null;
		
	}
	
	public ArrayList<ArticulosVO> buscarArticulos_enPrestamoAll(){
		
		ArticuloDAO _articuloDAO = new ArticuloDAO();

		try {
			return _articuloDAO .buscarArt_enPrestamosAll();
	
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		return null;
			
	}
	public ArticulosPVO buscarArticulo_enPrestamo(int codigo){
		
		ArticuloDAO _articuloDAO = new ArticuloDAO();

			
			try {
				ArticulosPVO prestamoVO = _articuloDAO .buscarArt_enPrestamo(codigo);
				return prestamoVO;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	
		
		return null;
				
	}
	
	public int validarInsert_nuevoArticulo(String tipo, String nombre, String descripcion,
			 int seccion, double monto){
		 
		ArticuloDAO _articuloDAO = new ArticuloDAO();
	
			try {
				return _articuloDAO .insertNuevo_articulo(tipo, nombre,descripcion,
						seccion, monto);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return 0;
				
	}
	
	public int validarModificacion(ArticulosVO artVO){
		
		ArticuloDAO _articuloDAO = new ArticuloDAO();
	
			try {
				return _articuloDAO .modificar_articulo(artVO);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return 0;
				
	}
	
	public boolean validarNuevo_articuloUsuario(ArrayList<JTextField> carac, JTextField seccionTF){
		
		for(JTextField tf : carac){
			
			if(tf.getText().equals("")) return false;
			if(tf.getText().length() > 100) return false;
		}
	
		if(!validarInt(seccionTF.getText())) return false;
		if(numero <= 0 || numero > 1000) return false;
		
		return true;
	}
	
	public void validarBusquedaAll(){
		
		ArticuloDAO _articulosDAO = new ArticuloDAO();
		
		busqueda_entities.setTipoBusqueda(7);
		busqueda_entities.limpiar();
		
		ArrayList<ArticulosVO> ar = new ArrayList<ArticulosVO>();
		try {
			ar = _articulosDAO.buscarArticuloAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		controladorPedido.setLogicaPedido(logica_pedido);
		
		if(ar!=null){
			
			for(ArticulosVO o : ar){
				
				Object [] datos = new Object[2];
				
				datos[0] = o.getCodigo();
				
				String descripcion = "";
				
				ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(o.getCodigo());
				
				if(apVO!=null){
					
					if(o.getNombre().equals("ArticuloP")){
						
						descripcion = o.getNombre() + " "
								+ Double.toString(apVO.getMonto());
					}
				}
				else
					
					descripcion =  o.getNombre()+ " " + o.getDescripcion();
				
				datos[1] = descripcion;
				
				if(!datos[1].toString().equals(""))
				
					busqueda_entities.getTabla().addRow(datos);
					
			}
			
			busqueda_entities.setTablaModel();
			
		
			if(busqueda_entities.getTabla().getRowCount() > 0)
				busqueda_entities.setTablaModel();
		}
		
	}
	public void validarBusquedaSeccionAll(){
		
		ArticuloDAO _articulosDAO = new ArticuloDAO();
		
		busqueda_entities.setTipoBusqueda(22);
		busqueda_entities.limpiar();
		
		ArrayList<SeccionVO> ar = new ArrayList<SeccionVO>();
		try {
			ar = _articulosDAO.buscarSeccionAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ar!=null){
			
			for(SeccionVO o : ar){
				
				Object [] datos = new Object[2];
				
				datos[0] = o.getCodigo();
				
				datos[1] = o.getDescripcion();
				
				if(!datos[1].toString().equals(""))
					
					busqueda_entities.getTabla().addRow(datos);
				
			}
			
			busqueda_entities.setTablaModel();
			
			
			if(busqueda_entities.getTabla().getRowCount() > 0)
				busqueda_entities.setTablaModel();
		}
		
	}
	
	public void validarBusquedaPersonalizada(String busqueda){
		
		ArticuloDAO _articuloDAO = new ArticuloDAO();
		
		busqueda_entities.limpiar();
			
			ArrayList<ArticulosVO> ar = new ArrayList<ArticulosVO>();
			try {
				ar = _articuloDAO.buscarArticuloPersonalizada(busqueda);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(ar != null){
					
				
				for(ArticulosVO o : ar){
					System.out.println("hola");
					
					Object [] datos = new Object[2];
					
					datos[0] = o.getCodigo();
					
					String descripcion = "";
					
					ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(o.getCodigo());
					
					if(apVO!=null) descripcion = o.getNombre() + "(" + apVO.getCodigo() + ")"
									+ Double.toString(apVO.getMonto());
					else
						
						descripcion =  o.getNombre() + "(" + o.getCodigo() + ") " + o.getDescripcion();
					
					datos[1] = descripcion;
					
					busqueda_entities.getTabla().addRow(datos);
						
				}
				
				//busqueda_entities.setTablaModel();
				
			
				if(busqueda_entities.getTabla().getRowCount() > 0)
					busqueda_entities.setTablaModel();
				
			}	
	}
	
	public boolean validarModificacionUsuario(JTextField nombreTF,JTextArea descripcionTF, 
			JTextField seccionTF){
		
		if(nombreTF.getText().equals("")) return false;
		if(descripcionTF.getText().equals("")) return false;
		if(seccionTF.getText().equals("")) return false;
		if(nombreTF.getText().length()>30) return false;
		if(descripcionTF.getText().length()>100) return false;
		
		
		if(!validarInt(seccionTF.getText())) return false;
		if(numero <= 0 || numero > 1000) return false;
		
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
	
	public boolean validarDouble(String val_string){
		
		try{
			
			numero_double = Double.parseDouble(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	
	public ArticulosVO validarBusqueda(int codigo){
		
		ArticuloDAO _articuloDAO = new ArticuloDAO();
		
		try {
			return _articuloDAO.buscarArticulo(codigo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
		
	}
	
	public void setControladorArticulo(ControladorArticulo _controladorArticulo){
		
		this._controladorArticulo = _controladorArticulo;
		
	}

	
}
