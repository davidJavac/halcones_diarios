package modelo_vo;

public class Detalle_proveedoresVO {

	private short id_detalle;
	private short id_proveedor;
	private String descripcion;
	
	public void setId_detalle(short id_detalle){
		
		this.id_detalle = id_detalle;
	}

	public short getId_detalle(){
		
		return id_detalle;
	}
	
	public void setId_proveedor(short id_proveedor){
		
		this.id_proveedor = id_proveedor;
	}

	public short getId_proveedor(){
		
		return id_proveedor;
	}
	
	public void setDescripcion(String descripcion){
		
		this.descripcion = descripcion;
			
	} 

	public String getDescripcion(){
		
		return descripcion;
	}
}
