package modelo_vo;

import java.sql.Date;
import java.sql.Time;

public class SueldosVO {

	private int codigo_sueldo;
	private short id_empleado;
	private double monto;
	private String concepto;
	private short id_usuario;
	private Date fecha_registro;
	private Time hora_registro;
	
	public void setCodigo_sueldo(int codigo_sueldo){
		
		this.codigo_sueldo = codigo_sueldo;
	}
	
	public int getCodig_sueldo(){
		
		return codigo_sueldo;
	}
	
	public void setId_empleado(short id_empleado){
		
		this.id_empleado = id_empleado;
	}
	
	public short getId_empleado(){
		
		return id_empleado;
	}
	
	public void setMonto(double monto){
		
		this.monto = monto;
	}
	
	public double getMonto(){
		
		return monto;
	}
	
	public void setConcepto(String concepto){
		
		this.concepto = concepto;
	}
	
	public String getConcepto(){
		
		return concepto;
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
