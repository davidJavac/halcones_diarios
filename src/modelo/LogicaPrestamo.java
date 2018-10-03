package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;

import controlador.ControladorArticulo;
import controlador.ControladorZona;
import modelo_dao.ArticuloDAO;
import modelo_dao.ComboDAO;
import modelo_dao.PrestamoDAO;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import vista.BusquedaEntities;

public class LogicaPrestamo {

	ControladorArticulo _controladorPrestamo;
	BusquedaEntities busqueda_entities;
	private int numero;
	private double numeroD;
	public static boolean validarNuevoUsuario;
	public static boolean vacio;
	public static boolean excede_caracteres;
	public static boolean no_entero;
	
	public ArticulosPVO validarBusquedaUsuario(String codigo){
		
		PrestamoDAO _prestamoDAO = new PrestamoDAO();

		if(validarInt(codigo)){
			
			try {
				ArticulosPVO prestamoVO = _prestamoDAO.buscarPrestamo(numero);
				return prestamoVO;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		return null;
				
	}
	
	public void validarNuevoUsuario(ArrayList<JTextField> ar){
		
		boolean val_num = false;
		boolean val_double = false;
		vacio = false;
		excede_caracteres = false;
		no_entero = false;
		
		validarNuevoUsuario = true;
	
		for(JTextField tf : ar){
			
			if(tf.getText().equals("")){
				
				vacio = true;
				validarNuevoUsuario = false;
			}
		
		}
		
		if(!vacio){
			
			String codigo = "";
			
			for(JTextField tf : ar){
					
				if(tf.getText().length() > 30){
							
					excede_caracteres = true;
							
					validarNuevoUsuario = false;
				}
			}
			
			int i = 1;
			
			for(JTextField tf : ar){
				
				if(!tf.getText().equals("")){
					
					if(i<=2){
						
						val_num = validarInt(tf.getText());
						
						if(!val_num || numero < 0 || numero > 100000000)  no_entero = true; 
					}
					else{
						
						val_double = validarDouble(tf.getText());
						
						if(!val_double || numeroD < 0 || numeroD > 100000000)  no_entero = true; 
					}
					
				}
				
				i++;	
			}
					
			if(no_entero) validarNuevoUsuario = false;
		}
		
	}
	
	public void validarBusquedaPersonalizada(String busqueda){
		
		PrestamoDAO _prestamoDAO = new PrestamoDAO();
		
		busqueda_entities.limpiar();
			
			ArrayList<Object[]> ar = new ArrayList<Object[]>();
			try {
				ar = _prestamoDAO.buscarPrestamoPersonalizada(busqueda);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(ar != null){
					
					for(Object [] o : ar){
						
						busqueda_entities.getTabla().addRow(o);
					}
			}			

		if(busqueda_entities.getTabla().getRowCount() > 0)
			busqueda_entities.setTablaModel();
	}
	
	public int validarNuevo(ArticulosPVO _prestamoVO){
		
		PrestamoDAO _prestamoDAO = new PrestamoDAO();
		
		try {
			return _prestamoDAO.insertarNuevoPrestamo(_prestamoVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public boolean validarInt(String val_string){
		
		try{
			
			numero = Integer.parseInt(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public boolean validarNuevo_prestamoUsuario(ArrayList<JTextField> monto){
		
		for(JTextField tf : monto){
			
			if(!validarDouble(tf.getText())) return false;
			if(Double.parseDouble(tf.getText()) <= 0) return false;
			if(tf.getText().length() > 10) return false;
		}
	
		if(numeroD <= 0 || numeroD > 100000) return false;
		
		return true;
	}
	
	public boolean validarDouble(String val_string){
		
		try{
			
			numeroD = Double.parseDouble(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public int validarEncriptacion(ArticulosPVO _prestamoVO, String key){
		
		PrestamoDAO _prestamoDAO = new PrestamoDAO();
		
		try {
			return _prestamoDAO.encriptar(_prestamoVO, key);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int validar_encriptar(String key){
		
		PrestamoDAO _prestamoDAO = new PrestamoDAO();
		ArrayList<ArticulosPVO> ar = new ArrayList<ArticulosPVO>();
		try { 
			ar = _prestamoDAO.buscarPrestamoAll();
			
			int res = 0;
			
			for(ArticulosPVO pVO : ar){
				
				res += _prestamoDAO.update_encriptar(pVO, key);
			}
			
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public int validar_desencriptar(String key){
		
		PrestamoDAO _prestamoDAO = new PrestamoDAO();
		ArrayList<ArticulosPVO> ar = new ArrayList<ArticulosPVO>();
		try { 
			ar = _prestamoDAO.buscarPrestamoAlldes(key);

			int res = 0;
			
			for(ArticulosPVO pVO : ar){
				
				res += _prestamoDAO.update_desencriptar(pVO, key);
			}
			
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
		
	}

}
