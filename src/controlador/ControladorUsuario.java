package controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;

import modelo.LogicaUsuario;
import modelo_vo.ContrasenasVO;
import modelo_vo.UsuariosVO;
import vista.VistaBuscarUsuario;
import vista.VistaIngresoUsuario;

public class ControladorUsuario {

	private LogicaUsuario _logica_usuario = new LogicaUsuario();
	/*private VistaAltaUsuario _vista_alta_usuario;
	private VistaEditarUsuario _vista_editar_usuario;*/
	private VistaBuscarUsuario _vista_buscar_usuario;
	

	
	public void setLogicaUsuario(LogicaUsuario _logica_usuario){
		
		this._logica_usuario = _logica_usuario;
	}
	
	public LogicaUsuario getLogicaArticulo(){
		
		return _logica_usuario;
	}
	
	/*public void setVistaAltaUsuario(VistaAltaUsuario _vista_alta_usuario){
		
		this._vista__alta_usuario = _vista_alta_usuario;
	}
	
	public VistaAltaUsuario getVistaAltaUsuario(){
		
		return _vista_alta_usuario;
	}
	
	public void setVistaEditarUsuario(VistaEditarUsuario _vista_editar_usuario){
		
		this._vista_editar_usuario = _vista_editar_usuario;
	}
	
	public VistaEditarUsuario getVistaEditarUsuario(){
		
		return _vista_editar_usuario;
	}*/
	
	public void setVistaBuscarUsuario(VistaBuscarUsuario _vista_buscar_usuario){
		
		this._vista_buscar_usuario = _vista_buscar_usuario;
	}
	
	public VistaBuscarUsuario getVistaBuscarUsuario(){
		
		return _vista_buscar_usuario;
	}
	
	/*public void altaUsuario(UsuariosVO _usuario){
		
		_logica_usuario.validarAlta(_usuario);
	}
	
	public void editarUsuario(UsuariosVO _usuario){
		
		_logica_usuario.validarEditar(_usuario);
	}
	
	public void bajaUsuario(UsuariosVO _usuario){
		
		_logica_usuario.validarBaja(_usuario);
	}*/

	public boolean buscarUsuario(VistaBuscarUsuario vista_buscar_usuario){
	
		return _logica_usuario.validarBusquedaUsuario(vista_buscar_usuario);
	}
	public boolean buscarContrasena(ContrasenasVO cVO, String key){
		
		return _logica_usuario.validarBusquedaContrasena(cVO, key);
	}
	
	public boolean validarNuevaContrasena(ArrayList<JTextField> ar,
			ArrayList<JTextField> ar_in, UsuariosVO uVO){
		
		return _logica_usuario.validarNuevaContrasena(ar, ar_in, uVO);
	}
	
	public int nueva_contrasena(UsuariosVO uVO,String contrasena, String key) throws SQLException{
		
		return _logica_usuario.nueva_contrasena(uVO, contrasena, key);
					
	}
	public int nueva_contrasena_interna(String contrasena, String key) throws SQLException{
		
		return _logica_usuario.nueva_contrasena_interna(contrasena, key);
		
	}
	
	public UsuariosVO buscarUsuario_porID(short id){
		
		return _logica_usuario.validarBusqueda_porID(id);
	}
	
	public UsuariosVO buscar(UsuariosVO _usuarioVO, String key){
		
		return _logica_usuario.validarBusqueda(_usuarioVO, key);
	}
	
}
