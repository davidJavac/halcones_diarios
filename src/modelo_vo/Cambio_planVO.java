package modelo_vo;

import java.sql.Date;
import java.sql.Time;

public class Cambio_planVO {

	private int n_cambio;
	private int n_pedido;
	private short id_vendedor;
	private String plan_anterior;
	private String plan_posterior;
	private double credito_anterior;
	private double credito_posterior;
	private short id_usuario;
	private Date fecha_registro;
	private Time hora_registro;
	
	public void setN_cambio(int n_cambio){
		
		this.n_cambio = n_cambio;
	}
	
	public int getN_cambio(){
		
		return n_cambio;
	}
	
	public void setN_pedido(int n_pedido){
		
		this.n_pedido = n_pedido;
	}
	
	public int getN_pedido(){
		
		return n_pedido;
	}

	public void setPlan_anterior(String plan_anterior){
		
		this.plan_anterior = plan_anterior;
	}
	
	public String getPlan_anterior(){
		
		return plan_anterior;
	}
	
	public void setPlan_posterior(String plan_posterior){
		
		this.plan_posterior = plan_posterior;
	}
	
	public String getPlan_posterior(){
		
		return plan_posterior;
	}
	
	public void setCredito_anterior(double credito_anterior){
		
		this.credito_anterior = credito_anterior;
	}
	
	public double getCredito_anterior(){
		
		return credito_anterior;
	}
	
	public void setCredito_posterior(double credito_posterior){
		
		this.credito_posterior = credito_posterior;
	}
	
	public double getCredito_posterior(){
		
		return credito_posterior;
	}
	
	public void setId_vendedor(short id_vendedor){
		
		this.id_vendedor = id_vendedor;
	}
	
	public short getId_vendedor(){
		
		return id_vendedor;
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
