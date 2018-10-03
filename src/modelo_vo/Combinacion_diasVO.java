package modelo_vo;

public class Combinacion_diasVO {

	private short id_combinacion;
	private short cantidad_dias;
	private boolean lunes;
	private boolean martes;
	private boolean miercoles;
	private boolean jueves;
	private boolean viernes;
	private boolean sabado;
	
	public void setId_combinacion(short id_combinacion){
		
		this.id_combinacion = id_combinacion; 
	}
	
	public short getId_combinacion(){
		
		return id_combinacion; 
	}
	
	public void setCantidad_dias(short cantidad_dias){
		
		this.cantidad_dias= cantidad_dias; 
	}
	
	public short getCantidad_dias(){
		
		return cantidad_dias; 
	}
	
	public void setLunes(boolean lunes){
		
		this.lunes = lunes;
	}
	
	public boolean getLunes(){
		
		return lunes;
	}
	
	public void setMartes(boolean martes){
		
		this.martes = martes;
	}

	public boolean getMartes(){
		
		return martes;
	}
	
	public void setMiercoles(boolean miercoles){
	
		this.miercoles = miercoles;
	}
	
	public boolean getMiercoles(){
		
		return miercoles;
	}

	public void setJueves(boolean jueves){
	
		this.jueves = jueves;
	}
	
	public boolean getJueves(){
		
		return jueves;
	}

	public void setViernes(boolean viernes){
	
		this.viernes = viernes;
	}

	public boolean getViernes(){
		
		return viernes;
	}
	
	public void setSabado(boolean sabado){
	
		this.sabado = sabado;
	}
	
	public boolean getSabado(){
		
		return sabado;
	}
}
