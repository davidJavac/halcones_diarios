package modelo_vo;

public class DomicilioParticularVO extends Datos_DomicilioVO{

	private boolean dpto;
	private short piso;
	
	public void setDpto(boolean dpto){
		
		this.dpto = dpto;
	}
	
	public boolean getDpto(){
		
		return dpto;
	}
	
	public void setPiso(short piso){
		
		this.piso = piso;
	}
	
	public short getPiso(){
		
		return piso;
	}
	
}
