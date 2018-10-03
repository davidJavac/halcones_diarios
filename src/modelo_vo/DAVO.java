package modelo_vo;

public class DAVO {

	private int n_descuento;
	private int n_pedido;
	private double monto;
	
	public void setNdescuento(int n_descuento){
		
		this.n_descuento = n_descuento;
	}
	
	public int getNdescuento(){
		
		return n_descuento;
	}
	
	public void setNpedido(int n_pedido){
		
		this.n_pedido = n_pedido;
	}
	
	public int getNpedido(){
		
		return n_pedido;
	}
	
	public void setMonto(double monto){
		
		this.monto = monto;
	}
	
	public double getMonto(){
		
		return monto;
	}
}
