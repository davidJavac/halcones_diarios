package modelo_vo;

public class UsuariosVO {

	private short id_usuario;
	private String nombre;
	private String contraseña;
	private short permiso;
	private boolean estado;
	
	public void setId_usuario(short id_usuario){
		
		this.id_usuario = id_usuario;
	}
	
	public short getId_usuario(){
		
		return id_usuario;
	}
	
	public void setNombre(String nombre){
		
		this.nombre = nombre;
	}
	
	public String getNombre(){
		
		return nombre;
	}
	
	public void setContraseña(String contraseña){
		
		this.contraseña = contraseña;
	}
	
	public String getContraseña(){
		
		return contraseña;
	}
	
	public void setPermiso(short permiso){
		
		this.permiso = permiso;
	}
	
	public short getPermiso(){
		
		return permiso;
	}
	
	public void setEstado(boolean estado){
		
		this.estado = estado;
	}
	
	public boolean getEstado(){
		
		return estado;
	}
	
}
