package modelo_vo;

import java.sql.Date;
import java.sql.Time;

public class ObservacionesVO {

	private int id;
	private int id_observacion;
	private String observacion;
	private short id_usuario;
	private Date fecha_registro;
	private Time hora_registro;
	
	public void setId(int id){
		
		this.id = id;
	}
	
	public int getId(){
		
		return id;
	}
	
	public void setId_observacion(int id_observacion){
		
		this.id_observacion = id_observacion;
	}
	
	public int getId_observacion(){
		
		return id_observacion;
	}
	
	public void setObservacion(String observacion){
		
		this.observacion = observacion; 
	}
	
	public String getObservacion(){
		
		return observacion;
	}
	
	public void setId_usuario(short id_usuario){
		
		this.id_usuario = id_usuario;
	}
	
	public short getId_usuario(){
		
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
