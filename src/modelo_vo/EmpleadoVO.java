package modelo_vo;

import java.sql.Date;

public class EmpleadoVO {

	private short id_usuario;
	private int dni;
	private enum cargo {COBRADOR, VENDEDOR, ADMINISTRATIVO, JEFE_CALLE};
	private String puesto;
	private String nombre;
	private String apellido;
	private double salario_semanal;
	private String calle;
	private int numero;
	private String localidad;
	private String telefono;
	private String email;
	private boolean estado;
	private Date fecha_nacimiento;
	private Date fecha_alta;
	
	public void setId_usuario(short id_usuario){
		
		this.id_usuario = id_usuario;
	}
	
	public short getId_usuario(){
		
		return id_usuario;
	}
	
	public void setDni(int dni){
		
		this.dni = dni;
	}
	
	public int getDni(){
		
		return dni;
	}
	
	public void setPuesto(String puesto){
		
		this.puesto = puesto;
	}
	
	public String getPuesto(){
		
		return puesto;
	}
	
	
	public void setNombre(String nombre){
		
		this.nombre = nombre;
	}
	
	public String getNombre(){
		
		return nombre;
	}
	
	public void setApellido(String apellido){
		
		this.apellido = apellido;
	}
	
	public String getApellido(){
		
		return apellido;
	}
	
	public void setSalario_semanal(double salario_semanal){
		
		this.salario_semanal = salario_semanal;
	}
	
	public double getSalario_semanal(){
		
		return salario_semanal;
	}
	
	public void setCalle(String calle){
		
		this.calle = calle;
	}
	
	public String getCalle(){
		
		return calle;
	}
	
	public void setNumero(int numero){
		
		this.numero = numero;
	}
	
	public int getNumero(){
		
		return numero;
	}
	
	public void setLocalidad(String localidad){
		
		this.localidad = localidad;
	}
	
	public String getLocalidad(){
		
		return localidad;
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
	
	public void setEstado(boolean estado){
		
		this.estado = estado;
	}
	
	public boolean getEstado(){
		
		return estado;
	}
	
	public void setFecha_nacimiento(Date fecha_nacimiento){
		
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
	public Date getFecha_nacimiento(){
		
		return fecha_nacimiento;
	}
	
	public void setFecha_alta(Date fecha_alta){
		
		this.fecha_alta = fecha_alta;
	}
	
	public Date getFecha_alta(){
		
		return fecha_alta;
	}
	
	
}
