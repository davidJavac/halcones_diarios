package modelo_vo;

public class Pedido_articuloVO {
	
	private int id;
	private int n_pedido;
	private int codigo_articulo;
	private int dias;
	private double cuota;
	private int cantidad;
	private boolean estado;
	
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
	
	public void setCodigo_articulo(int codigo_articulo){
		
		this.codigo_articulo = codigo_articulo;
	}
	
	public int getCodigo_articulo(){
		
		return codigo_articulo;
	}
	public void setDias(int dias){
		
		this.dias = dias;
	}
	
	public int getDias(){
		
		return dias;
	}
	
	public void setCuota(double cuota){
		
		this.cuota = cuota;
	}
	
	public double getCuota(){
		
		return cuota;
	}
	
	public void setEstado(boolean estado){
		
		this.estado = estado;
	}
	
	public boolean getEstado(){
		
		return estado;
	}
	
	/*public void setCantidad(int cantidad){
		
		this.cantidad = cantidad;
	}
	
	public int getCantidad(){
		
		return cantidad;
	}*/
	
}
