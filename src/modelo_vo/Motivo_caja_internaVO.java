package modelo_vo;

public class Motivo_caja_internaVO {

	private short id_motivo;
	private boolean ingreso;
	private String descripcion;
	
	public void setId_motivo(short id_motivo){
		
		this.id_motivo = id_motivo;
	}

	public short getId_motivo(){
		
		return id_motivo;
	}
	
	public void setDescripcion(String descripcion){
		
		this.descripcion = descripcion;
			
	} 

	public String getDescripcion(){
		
		return descripcion;
	}
	
	public void setIngreso(boolean ingreso){
		
		this.ingreso = ingreso;
	}
	
	public boolean getIngreso(){
		
		return ingreso;
	}
}
