package modelo;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo_dao.Despacho_diarioDAO;
import modelo_dao.Pago_diarioDAO;
import modelo_vo.Despacho_diarioVO;
import modelo_vo.Pago_diarioVO;

public class LogicaDespacho {

	private int numero;
	private double numero_double;
	
	public ArrayList<Despacho_diarioVO> validarBusqueda_porFecha(java.sql.Date fecha){
		
		Despacho_diarioDAO dpDAO = new Despacho_diarioDAO();
		
		try {
			return dpDAO.buscarDespachoDiario_porFecha(fecha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Despacho_diarioVO> buscarDespacho_entreFechas(java.sql.Date desde, java.sql.Date hasta){
	
		Despacho_diarioDAO dpDAO = new Despacho_diarioDAO();
		
		try {
			return dpDAO.buscarDespacho_entreFechas(desde, hasta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Despacho_diarioVO> validarBuscarDespachoAll(){
		
		Despacho_diarioDAO dpDAO = new Despacho_diarioDAO();
		
		try {
			return dpDAO.buscarDespachoDiarioAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public boolean validar_ingresoMonto(String monto){
		
		if(!validarDouble(monto)) return false;
		if(numero_double < 0) return false;
		if(monto.length() > 10) return false;
		if(monto.equals("")) return false;
		
		return true;
	}
	
	public boolean validarEntrega(Despacho_diarioVO dpVO, java.sql.Date hoy){
		
		Despacho_diarioDAO dpDAO = new Despacho_diarioDAO();
		
		try {
			return dpDAO.validacionEntrega(dpVO, hoy);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Despacho_diarioVO validarBusqueda_porPedido(int n_pedido){
		
		Despacho_diarioDAO dpDAO = new Despacho_diarioDAO();
		
		try {
			return dpDAO.buscarDespachoDiario_porPedido(n_pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Object []> validar_seguimientoPagos(int n_pedido){
		
		Pago_diarioDAO _pago_diarioDAO = new Pago_diarioDAO();
		
		try {
			return _pago_diarioDAO.seguirPagos(n_pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean validar_ingresoUsuario(String fi, String id_zona){
		
		Date date = new Date();
		java.sql.Date hoy = new java.sql.Date(date.getTime());
		 
		if(id_zona.equals("")) return false;
		
		 SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	     Date parsed=null;
		try {
			parsed = format.parse(fi);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     java.sql.Date sql = new java.sql.Date(parsed.getTime());
		 
	     CharSequence castfecha = sql.toString();
	     LocalDate no_domingo = LocalDate.parse(castfecha);

				// TODO Auto-generated method stub
		//if(sql.after(hoy)) return false;
		if(no_domingo.getDayOfWeek().name().equals("SUNDAY")) return false;
				
		return true;	
	}
	
	public boolean validar_despachoDuplicado(int n_pedido, java.sql.Date hoy){
		
		Despacho_diarioDAO dpDAO = new Despacho_diarioDAO();
		
			try {
				return dpDAO.despacho_duplicado(n_pedido, hoy);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return false;
	}
	
	public int validarUpdate_despacho(Despacho_diarioVO dpVO){
		
		Despacho_diarioDAO dpDAO = new Despacho_diarioDAO();
		
		try {
			return dpDAO.updateDespacho(dpVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return 0;
		
	}
	
	public boolean validarIngresosUsuario(JTable tabla){
			
		int contFila = tabla.getRowCount();
		
		for(int i = 0; i < contFila; i++){
			
			//if(!validarInt(tabla.getValueAt(i, 5).toString())) return false;
			if(!validarDouble(tabla.getValueAt(i, 5).toString())) return false;
			if(tabla.getValueAt(i, 5).toString().length()>30) return false;
			//if(numero < 0) return false;
			if(numero_double < 0) return false;
		}
		
		return true;
	}
	
	public boolean validarObservaciones(JRadioButton r, JRadioButton d, JTextArea observaciones){
		
		if(r.isSelected() || d.isSelected()){
			
			if(observaciones.getText().length() > 200 || 
					observaciones.getText().equals("")) return false;
				
		}
		
		return true;
	}
	
	public boolean validarIngresoUsuario(ArrayList<JTextField> ar){
		
		int cont = 0;
		
		for(JTextField tf : ar){
			
			if(tf.getText().equals("")) cont++;
		}
		
		if(cont > 1){
			
			Mensajes.getMensaje_altaErrorGenerico();
			return false;
		}
		
		return true;
	}
	
	public int validaringreso(Despacho_diarioVO dpVO){
		
		Despacho_diarioDAO dpDAO = new Despacho_diarioDAO();
		
		try {
			return dpDAO .ingresos(dpVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public double validarCalculoIngresos(ArrayList<JTextField> ar){
		
		double total = 0;
		
		for(JTextField tf : ar){
			
			if(validarInt(tf.getText()))
				
				switch(tf.getName()){
				
				case"_1000TF": total += 1000 * Double.parseDouble(tf.getText());
				break;
				case"_500TF": total += 500 * Double.parseDouble(tf.getText());
				break;
				case"_200TF": total += 200 * Double.parseDouble(tf.getText());
				break;
				case"_100TF": total += 100 * Double.parseDouble(tf.getText());
				break;
				case"_50TF": total += 50 * Double.parseDouble(tf.getText());
				break;
				case"_20TF": total += 20 * Double.parseDouble(tf.getText());
				break;
				case"_10TF": total += 10 * Double.parseDouble(tf.getText());
				break;
				case"_5TF": total += 5 * Double.parseDouble(tf.getText());
				break;
				case"_2TF": total += 2 * Double.parseDouble(tf.getText());
				break;
				case"_1TF": total += 1 * Double.parseDouble(tf.getText());
				break;
				case"_050TF": total += 0.5 * Double.parseDouble(tf.getText());
				break;
				case"_025TF": total += 0.25 * Double.parseDouble(tf.getText());
				break;
				}
			
			else tf.setText("");	
				
		}
		
		return total;
	}
	
	public double validarCalculoIngresos_planilla(JTable tabla){
		
		double total = 0;
		
		for(int i = 0; i < tabla.getRowCount(); i++){
			
			if(validarDouble(tabla.getModel().getValueAt(i, 5).toString()))
			
					total += Double.parseDouble(tabla.getModel().getValueAt(i, 5).toString());
			
		}
		
		return total;
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
	
	
}
