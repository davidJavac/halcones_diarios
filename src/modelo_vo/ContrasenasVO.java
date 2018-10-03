package modelo_vo;

public class ContrasenasVO {

	private int codigo;
	private String descripcion;
	private String contrasena;
	
	public void setCodigo(int codigo){
		
		this.codigo = codigo;
	}
	
	public int getCodigo(){
		
		return codigo;
	}
	
	public void setDescripcion(String descripcion){
		
		this.descripcion = descripcion;
	}
	
	public String getDescripcion(){
		
		return descripcion;
	}
	public void setContrasena(String contrasena){
		
		this.contrasena = contrasena;
	}
	
	public String getContrasena(){
		
		return contrasena;
	}
}
