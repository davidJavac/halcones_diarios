package modelo_vo;

import java.sql.Date;
import java.sql.Time;

public class Monto_trasladadoVO {

	private int n_pedido_origen;
	private int n_pedido_destino;
	private double monto;
	private String observaciones;
	private String estado;
	private short id_usuario;
	private Date fecha;
	private Time hora;
	
	public void setN_pedido_origen(int n_pedido_origen){
		
		this.n_pedido_origen = n_pedido_origen;
	}
	
	public int getN_pedido_origen(){
		
		return n_pedido_origen;
	}
	
	public void setN_pedido_destino(int n_pedido_destino){
		
		this.n_pedido_destino = n_pedido_destino;
	}
	
	public int getN_pedido_destino(){
		
		return n_pedido_destino;
	}
	
	public void setMonto(double monto){
		
		this.monto = monto;
	}
	
	public double getMonto(){
		
		return monto;
	}
	
	public void setObservaciones(String observaciones){
		
		this.observaciones = observaciones;
	}
	
	public String getObservaciones(){
		
		return observaciones;
	}
	
	public void setEstado(String estado){
		
		this.estado = estado;
	}
	
	public String getEstado(){
		
		return estado;
	}
	
	public void setId_usuario(short id_usuario){
		
		this.id_usuario = id_usuario;
	}
	
	public short getId_usuario(){
		
		return id_usuario;
	}
	
	public void setFecha(Date fecha){
		
		this.fecha = fecha;
	}
	
	public Date getFecha(){
		
		return fecha;
	}
	
	public void setHora(Time hora){
		
		this.hora = hora;
	}
	
	public Time getHora(){
		
		return hora;
	}
}
