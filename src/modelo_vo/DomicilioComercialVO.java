package modelo_vo;

import java.sql.Time;

public class DomicilioComercialVO extends Datos_DomicilioVO{

	private int idc;
	private String horario_atencion;
	private int comercio;
	private int id_zona;
	private int n_orden_planilla;
	
	public void setIdc(int idc){
		
		this.idc = idc;
	}
	
	public int getIdc(){
		
		return idc;
	}
	
	public void setId_zona(int id_zona){
		
		this.id_zona = id_zona;
	}
	
	public int getId_zona(){
		
		return id_zona;
	}
	
	public void setN_orden_planilla(int n_orden_planilla){
		
		this.n_orden_planilla = n_orden_planilla;
	}
	
	public int getN_orden_planilla(){
		
		return n_orden_planilla;
	}
	
	public void setComercio(int comercio){
		
		this.comercio = comercio;
	}
	
	public int getComercio(){
		
		return comercio;
	}
	public void setHorario_atencion(String horario_atencion){
		
		this.horario_atencion = horario_atencion;
	}
	
	public String getHorario_atencion(){
		
		return horario_atencion;
	}
	
}
