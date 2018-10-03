package modelo_vo;

import java.sql.Date;
import java.sql.Time;

public class VentasVO {

	private int n_venta;
	private int n_pedido;
	private String plan;
	private double credito;
	private short id_vendedor;
	private short id_usuario;
	private Date fecha_registro;
	private Time hora_registro;
	
	public void setN_venta(int n_venta){
		
		this.n_venta = n_venta;
	}
	
	public int getN_venta(){
		
		return n_venta;
	}
	
	public void setN_pedido(int n_pedido){
		
		this.n_pedido = n_pedido;
	}
	
	public int getN_pedido(){
		
		return n_pedido;
	}

	public void setPlan(String plan){
		
		this.plan = plan;
	}
	
	public String getPlan(){
		
		return plan;
	}
	
	public void setCredito(double credito){
		
		this.credito = credito;
	}
	
	public double getCredito(){
		
		return credito;
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
