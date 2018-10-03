package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.ControladorArticulo;
import controlador.ControladorZona;
import modelo_dao.ArticuloDAO;
import modelo_dao.Vendedor_phDAO;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.Vendedores_padre_hijoVO;
import vista.BusquedaEntities;

public class LogicaVendedor_ph {

	ControladorArticulo _controladorArticulo;
	BusquedaEntities busqueda_entities;
	private int numero;
	private double numero_double;
	
	public boolean validarModificacionUsuario(JTextField nombreTF,JTextArea descripcionTF,
			JTextField diasTF,JTextField cuotaTF){
		
		if(nombreTF.getText().equals("")) return false;
		if(descripcionTF.getText().equals("")) return false;
		if(!validarInt(diasTF.getText())) return false;
		if(!validarDouble(cuotaTF.getText())) return false;
		if(nombreTF.getText().length()>30) return false;
		if(descripcionTF.getText().length()>100) return false;
		if(numero <= 0) return false;
		if(numero_double <= 0) return false;
		
		return true;
	}
	
	public boolean nuevaDependenciaUsuario(JTextField vendedorTF){
		
		if(!validarInt(vendedorTF.getText())) return false;
		else return true;
	}
	
	public int nuevaDependencia(Vendedores_padre_hijoVO vphVO){
		
		Vendedor_phDAO vDAO = new Vendedor_phDAO();
		
		if(vphVO.getId_padre()==vphVO.getId_hijo()) return 0;
		
		try {
			return vDAO.insertVendedores_ph(vphVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int deleteVendedor_ph(Vendedores_padre_hijoVO vphVO){
		
		Vendedor_phDAO vDAO = new Vendedor_phDAO();
		
		try {
			return vDAO.deleteVendedores_ph(vphVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public ArrayList<Vendedores_padre_hijoVO> buscarVendedoresHijos_porIdPadre(int id){
	
		Vendedor_phDAO vDAO = new Vendedor_phDAO();
		
		try {
			return vDAO.buscarVendedoresHijos_porIdPadre(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
	}	

	public ArrayList<Vendedores_padre_hijoVO> buscarVendedoresPadres_porIdHijo(int id){
		
		Vendedor_phDAO vDAO = new Vendedor_phDAO();
		
		try {
			return vDAO.buscarVendedoresPadres_porIdHijo(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
	}	
	
	public ArticulosVO validarBusqueda(int codigo){
		
		ArticuloDAO _articuloDAO = new ArticuloDAO();
		
		try {
			return _articuloDAO.buscarArticulo(codigo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
		
	}
	
	public void setControladorArticulo(ControladorArticulo _controladorArticulo){
		
		this._controladorArticulo = _controladorArticulo;
		
	}

}
