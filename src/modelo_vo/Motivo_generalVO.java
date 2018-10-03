package modelo_vo;

public class Motivo_generalVO {

	private short id_motivo;
	private String categoria;
	
	public void setId_motivo(short id_motivo){
		
		this.id_motivo = id_motivo;
	}
	
	public short getId_motivo(){
		
		return id_motivo;
	}
	
	public void setCategoria(String categoria){
		
		this.categoria = categoria;
	}
	
	public String getCategoria(){
		
		return categoria;
	}
	
}
