package modelo_vo;

import java.sql.Date;
import java.sql.Time;

public class Despacho_diarioVO {

	private int id;
	private int n_pedido;
	private String entrega;
	private String nombre;
	private String estado;
	private double monto;
	private String observaciones;
	private short id_usuario;
	private Date fecha_registro;
	private Time hora_registro;
	
	public void setId(int id){
		
		this.id = id;
	}
	
	public int getId(){
		
		return id;
	}
	
	public void setN_pedido(int n_pedido){
		
		this.n_pedido = n_pedido;
	}
	
	public int getN_pedido(){
		
		return n_pedido;
	}
	
	public void setObservaciones(String observaciones){
		
		this.observaciones = observaciones;
	}
	
	public String getObservaciones(){
		
		return observaciones;
	}
	
	public void setEntrega(String entrega){
		
		this.entrega = entrega;
	}
	
	public String getEntrega(){
		
		return entrega;
	}
	
	public void setNombre(String nombre){
		
		this.nombre = nombre;
	}
	
	public String getNombre(){
		
		return nombre;
	}
	
	public void setEstado(String estado){
		
		this.estado = estado;
	}
	
	public String getEstado(){
		
		return estado;
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
	
	public int getId_usuario(){
		
		return id_usuario;
	}
	
	public void setFecha_registro(Date fecha){
		
		this.fecha_registro = fecha;
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
