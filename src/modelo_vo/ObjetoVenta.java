package modelo_vo;

public class ObjetoVenta {

	private int codigo;
	private String seccion;
	private String nombre;
	private String descripcion;
	private int dias;
	private double cuota_diaria;
	private short stock;	
	
	public void setCodigo(int codigo){
		
		this.codigo = codigo;

	}
	
	public int getCodigo(){
		
		return codigo;
	}
	
	public void setNombre(String nombre){
		
		this.nombre = nombre;
	}
	
	public String getNombre(){
		
		return nombre;
	}
	
	public void setDescripcion(String descripcion){
		
		this.descripcion = descripcion;
	}
	
	public String getDescripcion(){
		
		return descripcion;
	}

	public void setDias(int dias){
		
		this.dias = dias;
	}
	
	public int getDias(){
		
		return dias;
	}
	
	public void setCuota_diaria(double cuota_diaria){
		
		this.cuota_diaria = cuota_diaria;
	}
	
	public double getCuota_diaria(){
		
		return cuota_diaria;
	}


}
