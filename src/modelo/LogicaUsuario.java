package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.ControladorUsuario;
import modelo_dao.ClienteDAO;
import modelo_dao.UsuarioDAO;
import modelo_vo.ClienteVO;
import modelo_vo.ContrasenasVO;
import modelo_vo.UsuariosVO;
import vista.VistaBuscarUsuario;

public class LogicaUsuario {

	public static boolean validarBusqueda;
	public static boolean vacioUsuario;
	public static boolean excede_caracteresUsuario;
	
	private ControladorUsuario _controladorUsuario;
	
	public boolean validarBusquedaUsuario(VistaBuscarUsuario _vista_buscar_usuario){
		
		
		boolean val_num;
		boolean val_cant;
		vacioUsuario = false;
		excede_caracteresUsuario = false;
		
		validarBusqueda = true;
		
		for(JTextField tf : _vista_buscar_usuario.getArrayTF()){
			
			if(tf.getText().equals("")){
				
				vacioUsuario = true;
				
				validarBusqueda = false;
			}
		}
			
		for(JTextField tf : _vista_buscar_usuario.getArrayTF()){
				
			if(tf.getText().length() > 30){
						
				excede_caracteresUsuario = true;
						
				validarBusqueda = false;
			}
		}
		
		if(vacioUsuario){
			
			JOptionPane.showMessageDialog(null, "No puedes dejar el campo vacio", "Error",
					JOptionPane.ERROR_MESSAGE, null);
			
			validarBusqueda = false;
			
			return false;
		}
						
		if(excede_caracteresUsuario){
			
			JOptionPane.showMessageDialog(null, "Has excedido el limite de caracteres",
					"Error",JOptionPane.ERROR_MESSAGE, null);
			
			validarBusqueda = false;
			return false;
		}
		System.out.println("validar busqueda " + validarBusqueda);
		return true;
				
	}
	
	public UsuariosVO validarBusqueda(UsuariosVO _usuarioVO, String key){
		
		UsuarioDAO _usuarioDAO = new UsuarioDAO();
		
		try {
			return _usuarioDAO.buscarUsuario(_usuarioVO, key);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	public boolean validarBusquedaContrasena(ContrasenasVO cVO, String key){
		
		UsuarioDAO _usuarioDAO = new UsuarioDAO();
		
		try {
			return _usuarioDAO.buscarContrasena(cVO, key);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	
	public boolean validarNuevaContrasena(ArrayList<JTextField> ar, ArrayList<JTextField> ar_in,
			UsuariosVO uVO){
		
		for(int i = 1; i < ar.size(); i++){
			
			if(ar.get(i).isEnabled()){
				
				if(!ar.get(i).getText().equals(ar.get(i-1).getText())) return false;
				if(ar.get(i).getText().equals(""))return false;
				if(ar.get(i-1).getText().equals(""))return false;
				
			}
			
			
		}
		
		if(uVO.getPermiso()==1){
			
			for(int i = 1; i < ar_in.size(); i++){
				
				if(ar_in.get(i).isEnabled()){
					
					if(!ar_in.get(i).getText().equals(ar_in.get(i-1).getText())) return false;
					if(ar_in.get(i).getText().equals(""))return false;
					if(ar_in.get(i-1).getText().equals(""))return false;
					
				}
				
			}
			
		}
		
		return true;
	}
	
	public int nueva_contrasena(UsuariosVO uVO,String contrasena, String key) throws SQLException{
		
		UsuarioDAO _usuarioDAO = new UsuarioDAO();
		
		try {
			return _usuarioDAO.nueva_contrasena(uVO, contrasena, key);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return 0;
		}
		
	}
	public int nueva_contrasena_interna(String contrasena, String key) throws SQLException{
		
		UsuarioDAO _usuarioDAO = new UsuarioDAO();
		
		try {
			return _usuarioDAO.nueva_contrasena_interna(contrasena, key);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return 0;
		}
		
	}
	
	public UsuariosVO validarBusqueda_porID(short id){
		
		UsuarioDAO _usuarioDAO = new UsuarioDAO();
		
		try {
			return _usuarioDAO.buscarUsuario_porID(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	public void setControladorUsuario(ControladorUsuario _controladorUsuario){
		
		this._controladorUsuario = _controladorUsuario;
	}
	
}
