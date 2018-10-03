package modelo_vo;

import java.sql.Date;
import java.sql.Time;

public class Caja_internaVO {

	private int numero;
	private short id_motivo;
	private short id_detalle;
	private String detalle;
	private double monto_ingreso;
	private double monto_egreso;
	private short id_usuario;
	private Date fecha_registro;
	private Time hora_registro;
	
	public void setNumero(int numero){
		
		this.numero = numero;
	}
	
	public int getNumero(){
		
		return numero;
	}
	
	public void setId_motivo(short id_motivo){
		
		this.id_motivo = id_motivo;
	}
	
	public short getId_motivo(){
		
		return id_motivo;
	}
	
	/*public void setId_detalle(short id_detalle){
		
		this.id_detalle = id_detalle;
	}
	
	public short getId_detalle(){
		
		return id_detalle;
	}*/
	public void setDetalle(String detalle){
		
		this.detalle = detalle;
	}
	
	public String getDetalle(){
		
		return detalle;
	}
	
	public void setMonto_ingreso(double monto_ingreso){
		
		this.monto_ingreso = monto_ingreso;
	}
	
	public double getMonto_ingreso(){
		
		return monto_ingreso;
	}
	
	public void setMonto_egreso(double monto_egreso){
		
		this.monto_egreso = monto_egreso;
	}
	
	public double getMonto_egreso(){
		
		return monto_egreso;
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
