package modelo_vo;

public class ArticulosVO {

	private int codigo;
	private String codigo_prefijo;
	private int seccion;
	private String nombre;
	private String descripcion;
	private int dias;
	private double cuota;
	private short stock;	
	
	public void setCodigo(int codigo){
		
		this.codigo = codigo;

	}
	
	public int getCodigo(){
		
		return codigo;
	}
	
	public void setSeccion(int seccion){
		
		this.seccion = seccion;
	}
	
	public int getSeccion(){
		
		return seccion;
	}
	
	public void setCodigo_prefijo(String codigo_prefijo){
		
		this.codigo_prefijo = codigo_prefijo;
	}
	
	public String getCodigo_prefijo(){
		
		return codigo_prefijo;
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
	
	/*public void setDias(int dias){
		
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
	}*/

	public void setStock(short stock){
		
		this.stock = stock;
	}
	
	public short getStock(){
		
		return stock;
	}


	
}
