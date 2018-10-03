package modelo_vo;

public class Historial_planesVO {

	private int id;
	private int n_plan;
	private int n_pedido;
	private int codigo_articulo;
	private int dias;
	private double cuota;

	
	public void setId(int id){
		
		this.id = id;
	}
	
	public int getId(){
		
		return id;
	}
	public void setN_plan(int n_plan){
		
		this.n_plan = n_plan;
	}
	
	public int getN_plan(){
		
		return n_plan;
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

}
