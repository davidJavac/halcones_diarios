package modelo_vo;

import java.sql.Date;

public class ProveedoresVO {

	private short id_proveedor;
	private String nombre;
	private String razon_social;
	private String domicilio;
	private String ciudad;
	private String cuit;
	private String telefono;
	private String email;
	private Date fecha_alta;
	
	public void setId_proveedores(short id_proveedor){
		
		this.id_proveedor = id_proveedor;
	}
	
	public short getId_proveedor(){
		
		return id_proveedor;
	}
	
	public void setNombre(String nombre){
		
		this.nombre = nombre;
	}
	
	public String getNombre(){
		
		return nombre;
	}
	
	public void setRazon_social(String razon_social){
		
		this.razon_social = razon_social;
	}
	
	public String getRazon_social(){
		
		return razon_social;
	}
	
	public void setDomicilio(String domicilio){
		
		this.domicilio = domicilio;
	}
	
	public String getDomicilio(){
		
		return domicilio;
	}
	
	public void setCiudad(String ciudad){
		
		this.ciudad = ciudad;
	}
	
	public String getCiudad(){
		
		return ciudad;
	}
	
	public void setCuit(String cuit){
		
		this.cuit = cuit;
		
	}
	
	public String getCuit(){
		
		return cuit;
	}
	
	public void setTelefono(String telefono){
		
		this.telefono = telefono;
		
	}
	
	public String getTelefono(){
		
		return telefono;
	}
	
	public void setEmail(String email){
		
		this.email = email;
		
	}
	
	public String getEmail(){
		
		return email;
	}
	
	public void setFecha_alta(Date fecha_alta){
		
		this.fecha_alta = fecha_alta;
		
	}
	
	public Date getFecha_alta(){
		
		return fecha_alta;
	}
	
}
