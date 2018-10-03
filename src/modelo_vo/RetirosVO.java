package modelo_vo;

import java.sql.Date;
import java.sql.Time;

public class RetirosVO {

	private int n_retiro;
	private int id_pedido_articulo;
	private int n_pedido;
	private int codigo;
	private short id_vendedor;
	private String observaciones;
	private short id_usuario;
	private Date fecha_registro;
	private Time hora_registro;
	
	public void setN_retiro(int n_retiro){
		
		this.n_retiro = n_retiro;
	}
	
	public int getN_retiro(){
		
		return n_retiro;
	}
	
	public void setId_pedido_articulo(int id_pedido_articulo){
		
		this.id_pedido_articulo = id_pedido_articulo;
	}
	
	public int getId_pedido_articulo(){
		
		return id_pedido_articulo;
	}
	
	public void setN_pedido(int n_pedido){
		
		this.n_pedido = n_pedido;
	}
	
	public int getN_pedido(){
		
		return n_pedido;
	}

	public void setCodigo(int codigo){
		
		this.codigo = codigo;
	}
	
	public int getCodigo(){
		
		return codigo;
	}
	
	public void setId_vendedor(short id_vendedor){
		
		this.id_vendedor = id_vendedor;
	}
	
	public short getId_vendedor(){
		
		return id_vendedor;
	}
	
	public void setObservaciones(String observaciones){
		
		this.observaciones = observaciones;
	}
	
	public String getObservaciones(){
		
		return observaciones;
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
