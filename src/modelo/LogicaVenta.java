package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;

import controlador.ControladorArticulo;
import controlador.ControladorZona;
import modelo_dao.ArticuloDAO;
import modelo_dao.VentaDAO;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.VentasVO;
import vista.BusquedaEntities;

public class LogicaVenta {

	ControladorArticulo _controladorArticulo;
	BusquedaEntities busqueda_entities;
	private int numero;
	private double numero_double;

	
	public int validarInsert_nuevaVenta(VentasVO vVO){
		
		VentaDAO ventaDAO = new VentaDAO();
	
			try {
				return ventaDAO .insertNueva_venta(vVO);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return 0;
				
	}
	public int deleteVenta_porNpedido(int n_pedido){
		
		VentaDAO ventaDAO = new VentaDAO();
		
		try {
			return ventaDAO .deleteVenta_porNpedido(n_pedido);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return 0;
		
	}
	public int updateVentas(VentasVO vVO){
		
		VentaDAO ventaDAO = new VentaDAO();
		
		try {
			return ventaDAO .updateVentas(vVO);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return 0;
		
	}
	
	public boolean validarInt(String val_string){
		
		try{
			
			numero = Integer.parseInt(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public boolean validarDouble(String val_string){
		
		try{
			
			numero_double = Double.parseDouble(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
		
	}
	
	public void setControladorArticulo(ControladorArticulo _controladorArticulo){
		
		this._controladorArticulo = _controladorArticulo;
		
	}

}
