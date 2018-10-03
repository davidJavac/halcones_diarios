package modelo_vo;

import java.sql.Date;
import java.sql.Time;

public class Pago_diarioVO {

	private int n_pedido;
	private String nombre_producto;
	private int combo;
	private int prestamo;
	private double importe;
	private double cuota;
	private short id_combinacion;
	private short id_cobrador;
	private short id_usuario;
	private Date fecha;
	private Date fecha_registro;
	private Time hora_registro;
	
	public void setN_pedido(int n_pedido){
		
		this.n_pedido = n_pedido;
	}
	
	public int getN_pedido(){
		
		return n_pedido;
	}
	
	public void setnombre_producto(String nombre_producto){
		
		this.nombre_producto = nombre_producto;
	}
	
	public String getnombre_producto(){
		
		return nombre_producto;
	}
	
	public void setCombo(int combo){
		
		this.combo = combo;
	}
	
	public int getCombo(){
		
		return combo;
	}
	
	public void setPrestamo(int prestamo){
		
		this.prestamo = prestamo;
	}
	
	public int getPrestamo(){
		
		return prestamo;
	}
	
	public void setImporte(double importe){
		
		this.importe = importe;
	}
	
	public double getImporte(){
		
		return importe;
	}
	
	public void setCuota(double cuota){
		
		this.cuota = cuota;
	}
	
	public double getCuota(){
		
		return cuota;
	}
	
	/*public void setId_combinacion(short id_combinacion){
		
		this.id_combinacion = id_combinacion;
	}
	
	public int getId_combinacion(){
		
		return id_combinacion;
	}*/
	
	public void setId_cobrador(short id_cobrador){
		
		this.id_cobrador = id_cobrador;
	}
	
	public int getId_cobrador(){
		
		return id_cobrador;
	}
	
	
	public void setId_usuario(short id_usuario){
		
		this.id_usuario = id_usuario;
	}
	
	public int getId_usuario(){
		
		return id_usuario;
	}
	
	public void setFecha(Date fecha){
		
		this.fecha = fecha;
	}
	
	public Date getIFecha(){
		
		return fecha;
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
