package modelo_vo;

import com.mysql.jdbc.Blob;

public class ArticulosPVO {

	private int codigo;
	private double monto;
	
	public void setCodigo(int codigo){
		
		this.codigo = codigo;

	}
	
	public int getCodigo(){
		
		return codigo;
	}
	
	public void setMonto(double monto){
		
		this.monto = monto;
	}
	
	public double getMonto(){
		
		return monto;
	}

	
}
