package vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.ControladorCliente;

public class Cartas extends JPanel{

	CardLayout card;
	
	Panel_fondo panel_pres;
	
	JPanel  pIntegra, pBarra;
	ControladorCliente _controladorCliente;
	VistaAltaCliente vista_alta_cliente;
	
	/*ActualizarProducto nombre_art = new ActualizarProducto("Actualizar nombre");
	
	ActualizarProducto cambio_prec = new ActualizarProducto("Actualizar precio");
	
	ActualizarProducto cambio_desc = new ActualizarProducto("Actualizar descripcion");
	
	ActualizarCliente cambio_dir = new ActualizarCliente("Actualizar direccion");
	
	ActualizarCliente cambio_tel = new ActualizarCliente("Actualizar telefono");
	
	ListaCompleta lista_compl = new ListaCompleta();
	
	Faltante _faltante = new Faltante();
	
	ConsultaCliente cons_dni = new ConsultaCliente("Consulta por DNI");
	
	ConsultaCliente cons_nombre = new ConsultaCliente("Consulta por nombre y apellido");
	
	ConsultaCliente cons_direccion = new ConsultaCliente("Consulta por direccion");
	
	ListaCompletaClientes lista_compl_clientes = new ListaCompletaClientes();
	
	ConsultaProducto art = new ConsultaProducto();
	
	ConsultaPedido num_pedido = new ConsultaPedido();
	
	Efectivo efectivo;
	
	Tarjeta tarjeta;
	
	AltaProducto alta_prod;*/
	
	/*BajaProducto baja_prod;
	
	BajaCliente baja_clien;
		
	ConsultaFecha cons_fecha;*/
	
	public Cartas(){
		
		card = new CardLayout();
		
		setLayout(card);
		
		/*efectivo = new Efectivo();
		
		tarjeta = new Tarjeta();
		
		alta_prod = new AltaProducto();*/
		
		/*baja_prod = new BajaProducto();
		
		baja_clien = new BajaCliente();
			
		cons_fecha = new ConsultaFecha();*/
		
		pIntegra = new JPanel();
		
		pIntegra.setLayout(new BorderLayout());
		
		pBarra = new JPanel();
		
		pBarra.setLayout(new BorderLayout());
		
		pBarra.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
		pBarra.setBackground(new Color(234, 242, 248));
		
		Font fuenteB = new Font("Verdana", Font.PLAIN, 20);
		
		JLabel titulo = new JLabel("Inicio");
		
		titulo.setFont(fuenteB);
		
		titulo.setForeground(Color.BLACK);
		
		pBarra.add(titulo);
		
		panel_pres = new Panel_fondo();
		
		//panel_pres.setBackground(new Color(234, 242, 248));
		
		pIntegra.add(pBarra, BorderLayout.NORTH);
		
		pIntegra.add(panel_pres, BorderLayout.CENTER);
			
		add(pIntegra, "inicio");
		
		/*add(alta_prod, "alta_prod");*/
		
		//add(vista_alta_cliente, "alta_clien");
		
		/*add(baja_prod, "baja_prod");
		
		add(baja_clien, "baja_clien");
		
		add(cons_fecha, "cons_fecha");*/
				
	}
	
	public void getCarta(String s) throws SQLException{
		
		setVisible(true);
		
		switch(s){
		
			/*case "alta_clien": add(vista_alta_cliente, "alta_clien");
		
				
	

			break;
		
			case "_busqueda_cliente": add(efectivo, "_busqueda_cliente");
			
							efectivo.cargar_array_tf();
			
							efectivo.habilitar_boton();
						
		
			break;
			
			case "tarjeta": add(tarjeta, "tarjeta");
			
							 tarjeta.cargar_array_tf();

							 tarjeta.habilitar_boton();
		

			break;
		
			case "nombre_art": add(nombre_art, "nombre_art");
								
			break;
			
			case "cambio_prec": add(cambio_prec, "cambio_prec");
	
			break;
			
			case "cambio_desc": add(cambio_desc, "cambio_desc");
			
			break;
			
			case "cambio_dir": add(cambio_dir, "cambio_dir");
			
			break;
			
			case "cambio_tel": add(cambio_tel, "cambio_tel");

			break;
			
			case "lista_compl": add(lista_compl, "lista_compl");
								
								try {
									lista_compl.setFilas();
								} catch (SQLException e1) {
										// TODO Auto-generated catch block
									e1.printStackTrace();
								}	

			break;
			
			case "_faltante": add(_faltante, "_faltante");
			
				_faltante.setFilas();

			break;
			
			case "cons_dni": add(cons_dni, "cons_dni");

			break;
			
			case "cons_nombre": add(cons_nombre, "cons_nombre");

			break;
			
			case "lista_compl_clientes": add(lista_compl_clientes, "lista_compl_clientes");
			
			try {
				lista_compl_clientes.setFilas();
			} catch (SQLException e1) {
					// TODO Auto-generated catch block
				e1.printStackTrace();
			}	

			break;
		
			case "cons_direccion": add(cons_direccion, "cons_direccion");

			break;
			
			case "art": add(art, "art");

			break;
			
			case "num_pedido": add(num_pedido, "num_pedido");

			break;*/
		}
		
		card.show(this, s);
		
		/*nombre_art.limpiarPanel();
		
		cambio_prec.limpiarPanel();
		
		cambio_desc.limpiarPanel();
		
		cambio_dir.limpiarPanel();
		
		cambio_tel.limpiarPanel();
		
		cons_dni.limpiarPanel();
		
		cons_nombre.limpiarPanel();
		
		cons_direccion.limpiarPanel();
		
		art.limpiarPanel();
		
		num_pedido.limpiarPanel();
		
		efectivo.limpiarPanel();
		
		tarjeta.limpiarPanel();
		
		alta_prod.limpiarPanel();
		
		alta_clien.limpiarPanel();
		
		baja_prod.limpiarPanel();
		
		baja_clien.limpiarPanel();
			
		cons_fecha.limpiarPanel();*/
		
	}
	
	public void setControladorCliente(ControladorCliente _controladorCliente){
		
		this._controladorCliente = _controladorCliente;
	}
	
	public void setVistaAltaCliente(VistaAltaCliente vista_alta_cliente){
		
		this.vista_alta_cliente = vista_alta_cliente;
		//add(vista_buscar_cliente);
	}
	
}
