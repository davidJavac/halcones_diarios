package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.ControladorArticulo;
import controlador.ControladorPedidos;
import controlador.ControladorPlan;
import modelo_dao.ArticuloDAO;
import modelo_dao.PlanDAO;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Historial_planesVO;
import modelo_vo.PlanesVO;
import modelo_vo.SeccionVO;
import vista.BusquedaEntities;

public class Logica_plan {

	
	ControladorPlan _controladorPlan;
	private ControladorPedidos controladorPedido = new ControladorPedidos();
	private LogicaPedido logica_pedido = new LogicaPedido();
	BusquedaEntities busqueda_entities;
	private int numero;
	private double numero_double;
	
	public ArticulosVO validarBusquedaUsuario(String codigo){

		
		System.out.println("en rama master");
		System.out.println("problema resuelto en rama hotfix y merge con testing");

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
	public ArrayList<Historial_planesVO> buscarHistorial_planes_porNplan(int n_plan){
	
		PlanDAO pDAO = new PlanDAO();
		
		
		try {
			return pDAO .buscarHistorial_planes_porNplan(n_plan);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
		
	}
	public ArrayList<PlanesVO> buscarPlanes_porPedido(int n_pedido){
		
		PlanDAO pDAO = new PlanDAO();
		
		
		try {
			return pDAO .buscarPlanes_porPedido(n_pedido);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		return null;
		
	}
	
	public int insertPlan(PlanesVO pVO){
		 
		PlanDAO _planDAO = new PlanDAO();
	
			try {
				return _planDAO .insertPlan(pVO);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return 0;
				
	}
	public int deletePlan(int id){
		
		PlanDAO _planDAO = new PlanDAO();
		
		try {
			return _planDAO .deletePlan(id);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return 0;
		
	}
	public int deleteHistorial_planes(int id){
		
		PlanDAO _planDAO = new PlanDAO();
		
		try {
			return _planDAO .deleteHistorial_planes(id);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return 0;
		
	}
	public int insertHistorial_planes(Historial_planesVO hVO){
		
		PlanDAO _planDAO = new PlanDAO();
		
		try {
			return _planDAO .insertHistorial_planes(hVO);
			
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
	
}
