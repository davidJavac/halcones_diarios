package modelo_vo;

public class ComercioVO {

	private int codigo;
	private String descripcion;
	
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
}
