package modelo_vo;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class ClienteVO {

	private int dni;
	
	private String nombre;
	private String apellido;
	private String nacionalidad;
	private int dni_conyugue;
	private String nombre_conyugue;
	private String apellido_conyugue;
	private String telefono_conyugue;
	private String email_conyugue;
	private boolean estado_civil;
	private String telefono_movil;
	private String telefono_linea;
	private String email;
	private short id_vendedor;
	private short n_orden_planilla;
	private String estado;
	private Date fecha_nacimiento;
	private Date fecha_alta;
	private short id_usuario;
	private Date fecha_registro;
	private Time hora_registro;
	
	private double efectividad;
	
	public void setDni(int dni){
		
		this.dni = dni;
	}
	
	public int getDni(){
		
		return dni;
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
	
	public void setNacionalidad(String nacionalidad){
		
		this.nacionalidad = nacionalidad;
	}
	
	public String getNacionalidad(){
		
		return nacionalidad;
	}
	
	public void setDni_conyugue(int dni_conyugue){
		
		this.dni_conyugue = dni_conyugue;
	}
	
	public int getDni_conyugue(){
		
		return dni_conyugue;
	}
	
	public void setNombre_conyugue(String nombre_conyugue){
		
		this.nombre_conyugue = nombre_conyugue;
	}
	
	public String getNombre_conyugue(){
		
		return nombre_conyugue;
	}
	
	public void setApellido_conyugue(String apellido_conyugue){
		
		this.apellido_conyugue = apellido_conyugue;
	}
	
	public String getApellido_conyugue(){
		
		return apellido_conyugue;
	}
	public void setTelefono_conyugue(String telefono_conyugue){
		
		this.telefono_conyugue = telefono_conyugue;
	}
	
	public String getTelefono_conyugue(){
		
		return telefono_conyugue;
	}
	public void setEmail_conyugue(String email_conyugue){
		
		this.email_conyugue = email_conyugue;
	}
	
	public String getEmail_conyugue(){
		
		return email_conyugue;
	}
	
	public void setEstado_civil(boolean estado_civil){
		
		this.estado_civil = estado_civil;
	}
	
	public boolean getEstado_civil(){
		
		return estado_civil;
	}
	
	public void setTelefono_movil(String telefono_movil){
		
		this.telefono_movil = telefono_movil;
	}
	
	public String getTelefono_movil(){
		
		return telefono_movil;
	}
	
	public void setTelefono_linea(String telefono_linea){
		
		this.telefono_linea = telefono_linea;
	}
	
	public String getTelefono_linea(){
		
		return telefono_linea;
	}
	
	public void setEmail(String email){
		
		this.email = email;
	}
	
	public String getEmail(){
		
		return email;
	}
	
	public void setId_vendedor(short id_vendedor){
		
		this.id_vendedor = id_vendedor;
	}
	
	public short getId_vendedor(){
		
		return id_vendedor;
	}
	
	public void setN_orden_planilla(short n_orden_planilla){
		
		this.n_orden_planilla = n_orden_planilla;
	}
	
	public short getN_orden_planilla(){
		
		return n_orden_planilla;
	}
	
	public void setEstado(String estado){
		
		this.estado = estado;
	}
	
	public String getEstado(){
		
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
	
	public void setId_usuario(short id_usuario){
		
		this.id_usuario = id_usuario;
	}
	
	public short getId_usuario(){
		
		return id_usuario;
	}
	
	public void setFecha_registro(Date fecha_registro){
		
		this.fecha_registro = fecha_registro;
	}
	
	public Date getFecha_registro(){
		
		return fecha_registro;
	}
	
	public void setHora_registro(Time hora_registro){
		
		this.hora_registro = hora_registro;
	}
	
	public Time getHora_registro(){
		
		return hora_registro;
	}
	
	public void setEfectividad(double efectividad){
		
		this.efectividad = efectividad;
	}
	
	public double getEfectividad(){
		
		return efectividad;
	}
}
