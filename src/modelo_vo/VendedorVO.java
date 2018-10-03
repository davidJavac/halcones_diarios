package modelo_vo;

public class VendedorVO extends EmpleadoVO{

	private double comision;
	private double comision_prestamo;
	private int id_vendedor;
	
	public void setId_vendedor(int id_vendedor){
		
		this.id_vendedor = id_vendedor;
	}
	
	public int getId_vendedor(){
		
		return id_vendedor;
	}
	
	public void setComision(double comision){
		
		this.comision = comision;
	}
	
	public double getComision(){
		
		return comision;
	}
	public void setComision_prestamo(double comision_prestamo){
		
		this.comision_prestamo = comision_prestamo;
	}
	
	public double getComision_prestamo(){
		
		return comision_prestamo;
	}
	
}
