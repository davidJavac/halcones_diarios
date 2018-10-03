package controlador;

import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.LogicaArticulo;
import modelo.Logica_plan;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Historial_planesVO;
import modelo_vo.PlanesVO;
import modelo_vo.SeccionVO;
import vista.BusquedaEntities;
import vista.VistaAltaArticulo;
import vista.VistaBuscarArticulo;
import vista.VistaEditarArticulo;
import vista.VistaResultadoArticulo;

public class ControladorPlan {

	private Logica_plan _logica_plan;
	private BusquedaEntities _busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setLogicaPlan(Logica_plan _logica_plan){
		
		this._logica_plan = _logica_plan;
	}
	
	
	public int insertPlan(PlanesVO pVO){
		
		return _logica_plan.insertPlan(pVO);
	}

	public int insertHistorial_planes(Historial_planesVO hVO){
		
		return _logica_plan.insertHistorial_planes(hVO);
	}

	public int deletePlan(int id){
		
		return _logica_plan.deletePlan(id);
	}
	
	public int deleteHistorial_planes(int id){
		
		return _logica_plan.deleteHistorial_planes(id);
	}
	
	public ArrayList<PlanesVO> buscarPlanes_porPedido(int n_pedido){
		
		return _logica_plan.buscarPlanes_porPedido(n_pedido);
		
	}
	public ArrayList<Historial_planesVO> buscarHistorial_planes_porNplan(int n_plan){
		
		return _logica_plan.buscarHistorial_planes_porNplan(n_plan);
		
	}
	
}
