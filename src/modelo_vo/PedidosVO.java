package modelo_vo;

import java.sql.Date;
import java.sql.Time;

public class PedidosVO {

	private int n_pedido;
	private int dni;
	private int idc;
	private int dias;
	private double cuota_diaria;
	private int dias_desde_inicio;
	private int dias_mora;
	private double resto_dias_mora;
	private double estado_deuda;
	private double credito;
	private double saldo;
	private double facturacion;
	private String estado_pedido;
	private boolean refinanciacion_in;
	private boolean refinanciacion_ex;
	private boolean monto_trasladado;
	private short id_combinacion;
	private Date fecha_inicio;
	private Date fecha_termino;
	private Date fecha_termino_ideal;
	private short id_usuario;
	private Date fecha_registro;
	private Time hora_registro;
	
	public void setN_pedido(int n_pedido){
		
		this.n_pedido = n_pedido;
	}
	
	public int getN_pedido(){
		
		return n_pedido;
	}

	
	public void setDni(int dni){
		
		this.dni = dni;
	}
	
	public int getDni(){
		
		return dni;
	}
	public void setIdc(int idc){
		
		this.idc = idc;
	}
	
	public int getIdc(){
		
		return idc;
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
	
	public void setDias_desde_inicio(int dias_desde_inicio){
		
		this.dias_desde_inicio = dias_desde_inicio;
	}
	
	public int getDias_desde_inicio(){
		
		return dias_desde_inicio;
	}

	public void setDias_mora(int dias_mora){
		
		this.dias_mora = dias_mora;
	}
	
	public int getDias_mora(){
		
		return dias_mora;
	}
	
	public void setResto_dias_mora(double resto_dias_mora){
		
		this.resto_dias_mora = resto_dias_mora;
	}
	
	public double getResto_dias_mora(){
		
		return resto_dias_mora;
	}
	
	public void setEstado_deuda(double estado_deuda){
		
		this.estado_deuda = estado_deuda;
	}
	
	public double getEstado_deuda(){
		
		return estado_deuda;
	}
	
	public void setSaldo(double saldo){
		
		this.saldo = saldo;
	}
	
	public double getSaldo(){
		
		return saldo;
	}
	
	public void setFacturacion(double facturacion){
		
		this.facturacion = facturacion;
	}
	
	public double getFacturacion(){
		
		return facturacion;
	}
	
	public void setCredito(double credito){
		
		this.credito = credito;
	}
	
	public double getCredito(){
		
		return credito;
	}	
	
	public void setEstado_pedido(String estado_pedido){
		
		this.estado_pedido = estado_pedido;
	}
	
	public String getEstado_pedido(){
		
		return estado_pedido;
	}
	
	public void setRefinanciacion_in(boolean refinanciacion_in){
		
		this.refinanciacion_in = refinanciacion_in;
	}
	
	public boolean getRefinanciacion_in(){
		
		return refinanciacion_in;
	}
	
	public void setRefinanciacion_ex(boolean refinanciacion_ex){
		
		this.refinanciacion_ex = refinanciacion_ex;
	}
	
	public boolean getRefinanciacion_ex(){
		
		return refinanciacion_ex;
	}
	
	/*public void setId_combinacion(short id_combinacion){
		
		this.id_combinacion = id_combinacion;
	}
	
	public short getId_combinacion(){
		
		return id_combinacion;
	}*/
	
	public void setFecha_inicio(Date fecha_inicio){
		
		this.fecha_inicio = fecha_inicio;
	}
	
	public Date getFecha_inicio(){
		
		return fecha_inicio;
	}
	
	public void setFecha_terminoIdeal(Date fecha_termino_ideal){
		
		this.fecha_termino_ideal = fecha_termino_ideal;
	}
	
	public Date getFecha_terminoIdeal(){
		
		return fecha_termino_ideal;
	}
	
	public void setFecha_termino(Date fecha_termino){
		
		this.fecha_termino = fecha_termino;
	}
	
	public Date getFecha_termino(){
		
		return fecha_termino;
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
	

	
}
