package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;

import modelo_dao.ArticuloDAO;
import modelo_dao.ComboDAO;
import modelo_dao.LocalidadDAO;
import modelo_vo.ArticulosVO;
import modelo_vo.Pedido_articuloVO;
import vista.BusquedaEntities;

public class LogicaCombo {

	public static boolean vacio;
	public static boolean excede_caracteres;
	public static boolean no_entero;
	public static boolean no_double;
	public static boolean repite;
	private int numero;
	private double numeroD;
	public static boolean validarNuevoUsuario;
	private BusquedaEntities busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
	}
	
	public boolean validarNuevoUsuario(ArrayList<JTextField> ar,JTextField diasTF, JTextField cuotaTF){
		
		boolean val_num = false;
		vacio = false;
		excede_caracteres = false;
		no_entero = false;
		no_double = false;
		repite = false;
		
		validarNuevoUsuario = true;
		
		if(!validarInt(diasTF.getText())) return false;
		if(Integer.parseInt(diasTF.getText()) <= 0) return false;
		if(diasTF.getText().length() > 10) return false;
		
		if(!validarDouble(cuotaTF.getText())) return false;
		if(Double.parseDouble(cuotaTF.getText()) <= 0) return false;
		if(cuotaTF.getText().length() > 10) return false;
		
		int cont_vacio = 0;
		
		for(JTextField tf : ar){
			
			if(tf.getText().equals("")) cont_vacio++;		
		
		}
		
		if(cont_vacio > 3) return false;
		
		if(!vacio){
			
			String codigo = "";
			
			int cont_repite;
			
			int i = 0;
			
			for(JTextField tf : ar){
				
				cont_repite = 0;
				
				codigo = tf.getText();
				
				for(JTextField tf1 : ar){
					
					if(tf1.getText().equals(codigo) && !tf1.getText().equals("") &&
							!tf1.getText().equals("0")) cont_repite++;	
				}
				
				if(cont_repite > 1) return false;
				
				i++;
			}
			
				
			for(JTextField tf : ar){
					
				if(tf.getText().length() > 10) return false;
			}
					
			for(JTextField tf : ar){
				
				if(!tf.getText().equals("")){
					
					val_num = validarInt(tf.getText());
					
					if(!val_num || numero < 0 || numero > 100000000)  return false; 
				}
				//else tf.setText("0");	
			}
					
			
		}
		return true;
	}

	
	public void validarBusquedaPersonalizada(String busqueda){
		
		ComboDAO _comboDAO = new ComboDAO();
		
		busqueda_entities.limpiar();
			
			ArrayList<Object[]> ar = new ArrayList<Object[]>();
			try {
				ar = _comboDAO.buscarComboPersonalizada(busqueda);
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
	
	public void validarBusquedaAll(){
		
		ComboDAO _comboDAO = new ComboDAO();
	
		busqueda_entities.limpiar();
		
		ArrayList<Object []> ar = new ArrayList<Object []>();
		try {
			ar = _comboDAO.buscarComboAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ar!=null){
			
			for(Object [] o : ar){
				
				busqueda_entities.getTabla().addRow(o);
					
			}
			
			busqueda_entities.setTablaModel();
			
		
		if(busqueda_entities.getTabla().getRowCount() > 0)
			busqueda_entities.setTablaModel();
		}
				
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
			
			numeroD = Double.parseDouble(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}

	
}
