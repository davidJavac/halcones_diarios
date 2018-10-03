package modelo_vo;

import java.sql.Date;
import java.time.LocalDate;

public class Datos_DomicilioVO {

	private int dni;
	private String domicilio;
	private String entre_calle1;
	private String entre_calle2;
	private String barrio;
	private int cp;
	private short id_localidad;
	private Date antiguedad;
	private boolean propio;
	
	public void setDni(int dni){
		
		this.dni = dni;
	}
	
	public int getDni(){
		
		return dni;
	}
	
	public void setDomicilio(String domicilio){
		
		this.domicilio = domicilio;
	}
	
	public String getDomicilio(){
		
		return domicilio;
	}
	
	public void setEntre_calle1(String entre_calle1){
		
		this.entre_calle1 = entre_calle1;
	}
	
	public String getEntre_calle1(){
		
		return entre_calle1;
	}
	
	public void setEntre_calle2(String entre_calle1){
		
		this.entre_calle2 = entre_calle1;
	}
	
	public String getEntre_calle2(){
		
		return entre_calle2;
	}
	
	public void setBarrio(String barrio){
		
		this.barrio = barrio;
	}
	
	public String getBarrio(){
		
		return barrio;
	}
	
	public void setCp(int cp){
		
		this.cp = cp;
	}
	
	public int getCp(){
		
		return cp;
	}
	
	public void setId_localidad(short id_localidad){
		
		this.id_localidad = id_localidad;
	}
	
	public short getId_localidad(){
		
		return id_localidad;
	}
	
	public void setAntiguedad(Date antiguedad){
		
		this.antiguedad = antiguedad;
	}

	public Date getAntiguedad(){
		
		return antiguedad;
	}
	
	public void setPropio(boolean propio){
		
		this.propio = propio;
	}

	public boolean getPropio(){
		
		return propio;
	}


}
