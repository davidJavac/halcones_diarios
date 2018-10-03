package modelo_vo;

public class CobradorVO extends EmpleadoVO {

	private String auto_modelo;
	private String patente;
	private int id_cobrador;
	private double premio;
	
	public void setId_cobrador(int id_cobrador){
		
		this.id_cobrador = id_cobrador;
	}
	
	public int getId_cobrador(){
		
		return id_cobrador;
	}
	
	public void setAuto_modelo(String auto_modelo){
		
		this.auto_modelo = auto_modelo;
	}
	
	public String getAuto_modelo(){
		
		return auto_modelo;
	}
	
	public void setPatente(String patente){
		
		this.patente = patente;
	}
	
	public String getPatente(){
		
		return patente;
	}
	
	public void setPremio(double premio){
		
		this.premio = premio;
	}
	
	public double getPremio(){
		
		return premio;
	}
}
