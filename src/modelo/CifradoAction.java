package modelo;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import controlador.ControladorPedidos;
import modelo_vo.ArticulosPVO;
import vista.VistaBuscarCliente;
import vista.VistaBuscarPedidos_porClientes;
import vista.VistaPrincipal;


public class CifradoAction extends AbstractAction{

		private ControladorPedidos _controladorPedido;
		private VistaBuscarPedidos_porClientes vpc;
		private VistaBuscarCliente vista_buscar_cliente;
		private VistaPrincipal vista_principal;
		private String key = "sdfdgbdfv23342{´";
		
	    public CifradoAction(/*String text, ImageIcon icon,
	                      String desc, Integer mnemonic*/) {
	       // super(text, icon);
	        /*putValue(SHORT_DESCRIPTION, desc);
	        putValue(MNEMONIC_KEY, mnemonic);*/
	    }
	    public void actionPerformed(ActionEvent e) {

	    	int res = 0;
	    	 res = _controladorPedido.encriptar(key);
	    	
	    	if(res>0){
	    		
	    		Mensajes.getMensaje_encriptacionExitosa();
	    		
	    		vista_buscar_cliente.getpPedidos().removeAll();
				
	    		vista_buscar_cliente.buscar_cliente();
	    		
				vista_buscar_cliente.updateUI();
	   
	    		vpc.dispose();
	    		
	    	}
	    	else Mensajes.getMensaje_encriptacionError();
	    }
	    
	    public void setVistaBuscarPedidos_porCliente(VistaBuscarPedidos_porClientes vpc){
	    	
	    	this.vpc = vpc;
	    }
	    
	    public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
	    	
	    	this.vista_buscar_cliente = vista_buscar_cliente;
	    }
	    
	    public void setVistaPrincipal(VistaPrincipal vista_principal){
	    	
	    	this.vista_principal = vista_principal;
	    }
	    
	    public void setControladorPedidos(ControladorPedidos _controladorPedido){
	    	
	    	this._controladorPedido = _controladorPedido;
	    }
	
}
