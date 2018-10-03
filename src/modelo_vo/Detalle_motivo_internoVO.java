package modelo_vo;

public class Detalle_motivo_internoVO {

	private short id_detalle;
	private short id_motivo;
	private String descripcion;
	
	public void setId_detalle(short id_detalle){
		
		this.id_detalle = id_detalle;
	}

	public short getId_detalle(){
		
		return id_detalle;
	}
	
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
	
}
