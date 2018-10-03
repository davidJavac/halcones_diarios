package modelo_vo;

import java.sql.Date;

public class Monedas_egresosVO {

	private Date fecha_registro;
	private double monto_egreso;
	
	public void setFecha_registro(Date fecha_registro){
		
		this.fecha_registro = fecha_registro;
	}
	
	public Date getFecha_registro(){
		
		return fecha_registro;
	}
	
	public void setMonto_egreso(double monto_egreso){
		
		this.monto_egreso = monto_egreso;
	}
	
	public double getMonto_egreso(){
		
		return monto_egreso;
	}
}
