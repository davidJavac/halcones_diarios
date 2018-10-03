package modelo_vo;

public class Ingresos_internosVO {
	
		private short id_ingreso_in;
		private String descripcion;
		
		public void setId_gasto_in(short id_ingreso_in){
			
			this.id_ingreso_in = id_ingreso_in;
		}

		public short getId_ingreso_in(){
			
			return id_ingreso_in;
		}
		
		public void setId_ingreso_in(String descripcion){
			
			this.descripcion = descripcion;
				
		}

		public String getDescripcion(){
			
			return descripcion;
		}
	
}
