package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo_dao.Monto_trasladadoDAO;
import modelo_vo.Monto_trasladadoVO;
import vista.BusquedaEntities;

public class LogicaMonedas {

	
	public static boolean vacio;
	public static boolean excede_caracteres;
	public static boolean no_entero;
	private int numero;
	public static boolean validarNuevoMt_Usuario;
	private BusquedaEntities busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
	}
	
	public void validarAltaMonto_t_usuario(ArrayList<JTextField> ar, JTextArea observaciones){
	
		boolean val_num = false;
		vacio = false;
		excede_caracteres = false;
		no_entero = false;
		
		validarNuevoMt_Usuario = true;
		
		int cont_vacio = 0;
		
		for(JTextField tf : ar){
			
			if(tf.getText().equals("")) vacio = true;		
		
		}
		
		if(vacio){
			
			Mensajes.getMensaje_vacio();
			validarNuevoMt_Usuario = false;
			
		}
		
		if(!vacio){
			
				
			for(JTextField tf : ar){
					
				if(tf.getText().length() > 30){
							
					excede_caracteres = true;
							
					validarNuevoMt_Usuario = false;
				}
			}
			
			if(observaciones.getText().length() > 100){
				
				excede_caracteres = true;	
						
				validarNuevoMt_Usuario = false;
			}
			
			if(excede_caracteres) Mensajes.getMensaje_excede();
			
			for(JTextField tf : ar){
				
				if(!tf.getText().equals("")){
					
					val_num = validarInt(tf.getText());
					
					if(!val_num || numero < 0 || numero > 100000000)  no_entero = true; 
				}
				//else tf.setText("0");	
			}
					
			if(no_entero){
				
				Mensajes.getMensaje_no_entero();
				validarNuevoMt_Usuario = false;
			}
		}
		
	}
	
	/*public int validarSetPedidosMonto(int n_pedido, boolean estado){
		
		Monto_trasladadoDAO _mtDAO = new Monto_trasladadoDAO();
		
		try {
			return _mtDAO.updatePedidosMonto(n_pedido, estado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}*/
	
	public boolean validarMonto_t(Monto_trasladadoVO mtVO){
		
		Monto_trasladadoDAO mtDAO = new Monto_trasladadoDAO();
				
			try {
				return mtDAO.buscarMonto(mtVO);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		return false;
				
	}

	
	public ArrayList<Monto_trasladadoVO> validar_busqueda_pedido(int n_pedido){
		
		Monto_trasladadoDAO mtDAO = new Monto_trasladadoDAO();
		
		ArrayList<Monto_trasladadoVO> ar;
		try {
			return ar = mtDAO.buscar_pedido(n_pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int validarNuevoMonto(Monto_trasladadoVO mtVO){
		
		Monto_trasladadoDAO _mtDAO = new Monto_trasladadoDAO();

		try {
			return _mtDAO.insertarNuevoMt(mtVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public int validarBaja(Monto_trasladadoVO mt){
		
		Monto_trasladadoDAO _mtDAO = new Monto_trasladadoDAO();
		
		try {
			
			return _mtDAO.anularMonto(mt);
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
	
}
