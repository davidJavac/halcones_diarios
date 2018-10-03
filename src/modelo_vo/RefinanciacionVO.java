package modelo_vo;

public class RefinanciacionVO {

	private int n_ref;
	private int n_pedido;
	private short dias;
	private double cuota_diaria;
	private boolean estado;
	
	public void setN_ref(int n_ref){
		
		this.n_ref = n_ref;
	}

	public int getN_ref(){
		
		return n_ref;
	}
	
	public void setN_pedido(int n_pedido){
		
		this.n_pedido = n_pedido;
	}

	public int getN_pedido(){
		
		return n_pedido;
	}
	
	public void setDias(short dias){
		
		this.dias = dias;
	}

	public short getDias(){
		
		return dias;
	}
	
	public void setCuota_diaria(double cuota_diaria){
		
		this.cuota_diaria = cuota_diaria;
	}

	public double getCuota_diaria(){
		
		return cuota_diaria;
	}
	
	public void setEstado(boolean estado){
		
		this.estado = estado;
	}

	public boolean getEstado(){
		
		return estado;
	}
	
}
