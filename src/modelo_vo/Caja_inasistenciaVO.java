package modelo_vo;

import java.sql.Date;
import java.sql.Time;

public class Caja_inasistenciaVO {

	private short id_empleado;
	private short id_motivo;
	private short id_usuario;
	private Date fecha_registro;
	private Time hora_registro;
	
	public void setId_empleado(short id_empleado){
		
		this.id_empleado = id_empleado;
	}
	
	public short getId_empleado(){
		
		return id_empleado;
	}
	
	public void setId_motivo(short id_motivo){
		
		this.id_motivo = id_motivo;
	}
	
	public short getId_motivo(){
		
		return id_motivo;
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
