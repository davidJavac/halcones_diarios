package modelo_vo;

public class LocalidadVO {

	private short id_localidad;
	private String localidad;
	private boolean estado;
	
	public void setId_localidad(short id_localidad){
		
		this.id_localidad = id_localidad;
	}
	
	public short getId_localidad(){
		
		return id_localidad;
	}
	
	public void setLocalidad(String localidad){
		
		this.localidad = localidad;
	}
	
	public String getLocalidad(){
		
		return localidad;
	}
	
	public void setEstado(boolean estado){
		
		this.estado = estado;
	}
	
	public boolean getEstado(){
		
		return estado;
	}
}
