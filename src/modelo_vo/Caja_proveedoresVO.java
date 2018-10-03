package modelo_vo;

import java.sql.Date;
import java.sql.Time;

public class Caja_proveedoresVO {

	private int numero;
	private short id_proveedor;
	private String detalle;
	private double monto;
	private short id_usuario;
	private Date fecha_registro;
	private Time hora_registro;
	
	public void setNumero(int numero){
		
		this.numero = numero;
	}
	
	public int getNumero(){
		
		return numero;
	}
	
	public void setDetalle(String detalle){
		
		this.detalle = detalle;
	}
	
	public String getDetalle(){
		
		return detalle;
	}
	
	public void setId_proveedor(short id_proveedor){
		
		this.id_proveedor = id_proveedor;
	}
	
	public short getId_proveedor(){
		
		return id_proveedor;
	}
	
	public void setMonto(double monto){
		
		this.monto = monto;
	}
	
	public double getMonto(){
		
		return monto;
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
