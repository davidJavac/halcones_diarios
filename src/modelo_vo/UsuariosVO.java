package modelo_vo;

public class UsuariosVO {

	private short id_usuario;
	private String nombre;
	private String contrase�a;
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
	
	public void setContrase�a(String contrase�a){
		
		this.contrase�a = contrase�a;
	}
	
	public String getContrase�a(){
		
		return contrase�a;
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
