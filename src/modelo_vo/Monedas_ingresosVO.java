package modelo_vo;

import java.sql.Date;
import java.sql.Time;

public class Monedas_ingresosVO {

	private Date fecha_registro;
	private short id_zona;
	private double monto_ingreso;
	
	public void setFecha_registro(Date fecha_registro){
		
		this.fecha_registro = fecha_registro;
	}
	
	public Date getFecha_registro(){
		
		return fecha_registro;
	}
	
	public void setId_zona(short id_zona){
		
		this.id_zona = id_zona;
	}
	
	public short getId_zona(){
		
		return id_zona;
	}
	
	public void setMonto_ingreso(double monto_ingreso){
		
		this.monto_ingreso = monto_ingreso;
	}
	
	public double getMonto_ingreso(){
		
		return monto_ingreso;
	}
	
}
