package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.ControladorArticulo;
import controlador.ControladorPedidos;
import modelo_dao.ArticuloDAO;
import modelo_dao.DaDAO;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.DAVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.RetirosVO;
import vista.BusquedaEntities;

public class LogicaDA {

	ControladorArticulo _controladorArticulo;
	private ControladorPedidos controladorPedido = new ControladorPedidos();
	BusquedaEntities busqueda_entities;
	private int numero;
	private double numero_double;
	
	public boolean validarNuevo_descuentoUsuario(JTextField descuentoTF){
		
		if(!validarDouble(descuentoTF.getText())) return false;
		if(numero_double <= 0 || numero_double > 100) return false;
		
		return true;
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
	
	public ArrayList<DAVO> buscarDA_porNpedido(PedidosVO _pedidoVO){
		
		DaDAO  dDAO = new DaDAO();
		
		try {
			return dDAO.buscarDA_porNpedido (_pedidoVO.getN_pedido());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int insertDA(DAVO dVO){

		DaDAO  dDAO = new DaDAO();
		
		try {
			return dDAO.insertDA(dVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	
	}
	
	public double calculoDescuento(ArrayList<Pedido_articuloVO> arPa,PedidosVO p_orVO,
			double porcentaje_desc){
		
		double acu = 0;
		System.out.println("Facturacion original " + p_orVO.getFacturacion() + 
				" cuota original " + p_orVO.getCuota_diaria());
		for(Pedido_articuloVO pVO: arPa){
			System.out.println("Pedido cambiado " + pVO.getCodigo_articulo() + " " + pVO.getDias() + " " +
		pVO.getCuota());
			//ArticulosVO artVO = _controladorArticulo.buscarArticulo_porCodigo(cod);
				
			//if(artVO!=null){
				
				acu += pVO.getCuota() * p_orVO.getFacturacion() / p_orVO.getCuota_diaria();
			//}
			
		}
		
		double monto_descuento = 0;
		
		monto_descuento = porcentaje_desc * acu / 100;
		
		return monto_descuento;
	}

	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
		
	}
	
	public void setControladorArticulo(ControladorArticulo _controladorArticulo){
		
		this._controladorArticulo = _controladorArticulo;
		
	}

}
