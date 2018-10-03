package modelo_vo;

import java.sql.Date;
import java.sql.Time;

public class VerazVO{

	private int  n_registro;
	private int dni;
	private String observacion;
	private int id_usuario;
	private Date fecha_registro;
	private Time hora_registro;
	
	public void setNregistro(int n_registro){
		
		this.n_registro =n_registro;
	}
	
	public int getNregistro(){
		
		return n_registro;
	}
	public void setDni(int dni){
		
		this.dni =dni;
	}
	
	public int getDni(){
		
		return dni;
	}
	public void setObservacion(String observacion){
		
		this.observacion =observacion;
	}
	
	public String getObservacion(){
		
		return observacion;
	}
	public void setId_usuario(int id_usuario){
		
		this.id_usuario =id_usuario;
	}
	
	public int getId_usuario(){
		
		return id_usuario;
	}
	
	public void setFecha_registro(Date fecha_registro){
		
		this.fecha_registro = fecha_registro;
	}
	
	public Date getFecha_registro(){
		
		return fecha_registro;
	}
	public void setHora_registro(Time hora_registro){
		
		this.hora_registro = hora_registro;
	}
	
	public Time getHora_registro(){
		
		return hora_registro;
	}

	
}
