package modelo;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import controlador.ControladorPedidos;
import controlador.ControladorPrestamo;
import vista.VistaBuscarCliente;
import vista.VistaBuscarPedidos_porClientes;
import vista.VistaPrincipal;

public class DescifradoAction extends AbstractAction{

	private ControladorPedidos _controladorPedido;
	private VistaBuscarPedidos_porClientes vpc;
	private VistaBuscarCliente vista_buscar_cliente;
	private VistaPrincipal vista_principal;
	private String key = "sdfdgbdfv23342{´";
	
    public DescifradoAction(/*String text, ImageIcon icon,
                      String desc, Integer mnemonic*/) {
       // super(text, icon);
        /*putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);*/
    }
    public void actionPerformed(ActionEvent e) {

    	int res = 0;
    	
    	if(vista_buscar_cliente.getEncriptado())
    		res = _controladorPedido.desencriptar(key);
    	
    	System.out.println("encriptado " + vista_buscar_cliente.getEncriptado()+ 
    			"/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*");
    	
    	if(res>0) {
    		
    		Mensajes.getMensaje_desencriptacionExitosa();
    		
    		vista_buscar_cliente.getpPedidos().removeAll();
			
    		vista_buscar_cliente.buscar_cliente();
    		
			vista_buscar_cliente.updateUI();
    	}
    	else Mensajes.getMensaje_desencriptacionError();
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
