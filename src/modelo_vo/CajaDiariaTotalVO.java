package modelo_vo;

import java.sql.Date;
import java.sql.Time;

public class CajaDiariaTotalVO {

	private double ingresos;
	private double egresos;
	private double sobrante;
	private double faltante;
	private String observaciones;
	private short _1000;
	private short _500;
	private short _200;
	private short _100;
	private short _50;
	private short _20;
	private short _10;
	private short _5;
	private short _2;
	private short _1;
	private short _0_50;
	private short _0_25;
	private short id_usuario;
	private Date fecha_registro;
	private Time hora_registro;
	
	public void setFecha_registro(Date fecha){
		
		this.fecha_registro = fecha;
	}
	
	public Date getFecha_registro(){
		
		return fecha_registro;
	}
	
	public void setHora_registro(Time hora){
		
		this.hora_registro = hora;
	}
	
	public Time getHora_registro(){
		
		return hora_registro;
	}
	
	
	public void setId_usuario(short id_usuario){
		
		this.id_usuario = id_usuario;
		
	}
	
	public short getId_usuario(){
		
		return id_usuario;
	}
	
	
	public void setIngresos(double ingresos){
		
		this.ingresos = ingresos;
	}
	
	public double getIngresos(){
		
		return ingresos;
	}
	
	public void setEgresos(double egresos){
		
		this.egresos = egresos;
	}
	
	public double getEgresos(){
		
		return egresos;
	}
	
	public void setSobrante(double sobrante){
		
		this.sobrante = sobrante;
	}
	
	public double getSobrante(){
		
		return sobrante;
	}
	
	public void setFaltante(double faltante){
		
		this.faltante = faltante;
	}
	
	public double getFaltante(){
		
		return faltante;
	}
	
	public void setObservaciones(String observaciones){
		
		this.observaciones = observaciones;
	}
	
	public String getObservaciones(){
		
		return observaciones;
	}
	
	public void set_1000(short _1000){
		
		this._1000 = _1000;
	}
	
	public short get_1000(){
		
		return _1000;
	}
	
	public void set_500(short _500){
		
		this._500 = _500;
	}
	
	public short get_500(){
		
		return _500;
	}
	
	public void set_200(short _200){
		
		this._200 = _200;
	}
	
	public short get_200(){
		
		return _200;
	}
	
	public void set_100(short _100){
		
		this._100 = _100;
	}
	
	public short get_100(){
		
		return _100;
	}
	
	public void set_50(short _50){
		
		this._50 = _50;
	}
	
	public short get_50(){
		
		return _50;
	}
	
	public void set_20(short _20){
		
		this._20 = _20;
	}
	
	public short get_20(){
		
		return _20;
	}
	
	public void set_10(short _10){
		
		this._10 = _10;
	}
	
	public short get_10(){
		
		return _10;
	}
	
	public void set_5(short _5){
		
		this._5 = _5;
	}
	
	public short get_5(){
		
		return _5;
	}
	
	public void set_2(short _2){
		
		this._2 = _2;
	}
	
	public short get_2(){
		
		return _2;
	}
	
	public void set_1(short _1){
		
		this._1 = _1;
	}
	
	public short get_1(){
		
		return _1;
	}
	
	public void set_0_50(short _0_50){
		
		this._0_50 = _0_50;
	}
	
	public short get_0_50(){
		
		return _0_50;
	}
	
	public void set_0_25(short _0_25){
		
		this._0_25 = _0_25;
	}
	
	public short get_0_25(){
		
		return _0_25;
	}
	
}
