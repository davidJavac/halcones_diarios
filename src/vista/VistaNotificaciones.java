package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorArticulo;
import controlador.ControladorCliente;
import controlador.ControladorEmpleado;
import controlador.ControladorLocalidad;
import controlador.ControladorMonto_trasladado;
import controlador.ControladorObservaciones;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorPrestamo;
import controlador.ControladorRefinanciacion_ex;
import controlador.ControladorRefinanciacion_in;
import controlador.ControladorUsuario;
import controlador.ControladorVendedor;
import controlador.ControladorZona;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.LocalidadVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.UsuariosVO;
import modelo_vo.VerazVO;
import vista.VistaClientesAtrasados.CustomJToolTip;

public class VistaNotificaciones extends JInternalFrame{
	
	private ControladorCliente controladorCliente;
	
	private ControladorUsuario _controladorUsuario;
	private ControladorEmpleado _controladorEmpleado;
	
	private static  VistaPrincipal _vista_principal;
	
	private JPanel pNotificacion = new JPanel();
	private JScrollPane scr = new JScrollPane();
	
	 private JButton imprimir = new JButton(){
	        //override the JButtons createToolTip method
	        @Override
	        public JToolTip createToolTip() {
	            return (new CustomJToolTip(this));
	        }
	    };;
	
	private JTable tabla = new JTable();    
	private DefaultTableModel t_model;    
	
	private boolean[] canEdit_clientes= new boolean[]{
            false, false, false, false, false, false
    };
	
	private static VistaPrincipal vp;
	
	public VistaNotificaciones(){
		//super(_vista_principal, "Clientes en veraz", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		super("Notificaciones", true, true, true, true);
		 this.setLayout(new BorderLayout());
		
		 
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     setSize(600, 300);
	    // setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	     this.setResizable(false);
		 //this.setTitle("Notificaciones");
	     
	     tabla.getTableHeader().setReorderingAllowed(false);
		    tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
	     
	     t_model = new DefaultTableModel(null, getColumnas()){
			 
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit_clientes[columnIndex];
	            }
		};
		tabla.setModel(t_model);
	     
	     pNotificacion.add(tabla);
	     
	    // pClientes_atrasados.setPreferredSize(new Dimension(850, 600));
	    // scr.setPreferredSize(new Dimension(850, 600));
	     scr.setViewportView(tabla);
	    // scr.add(pPedidos_historicos);
	     
	     add(scr, BorderLayout.CENTER);
	}
	
	public void iniciar(){
		
		java.util.Date d = new java.util.Date();
		java.sql.Date hoy = new java.sql.Date(d.getTime());	
		
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		String fecha = f.format(hoy);
		
		ArrayList<ClienteVO> ar_cliente = controladorCliente.comprobarFecha_nacimiento(hoy);
		ArrayList<EmpleadoVO> ar_emp = _controladorEmpleado.comprobarFecha_nacimiento(hoy);
		
		int cantidad = 0;
		
		if(ar_cliente!=null){
			
			for(ClienteVO cVO : ar_cliente){
				
				if(!cVO.getEstado().equals("baja")){
					
					Object [] o = new Object[6];
					
					String nombre = cVO.getNombre() + " " + cVO.getApellido();
					
					o[0] = nombre;
					o[1] = "Cumpleaños";
					o[2] = "Cliente";
					o[3] = cVO.getTelefono_movil();
					o[4] = cVO.getDni();
					
					SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
					
					String fn = ft.format(cVO.getFecha_nacimiento());
					
					o[5] = fn;
					
					t_model.addRow(o);
					cantidad++;
				}
			}
			
		}
		if(ar_emp!=null){
			
			for(EmpleadoVO eVO : ar_emp){
				
				if(eVO.getId_usuario()!=33 && eVO.getEstado()){
					
					Object [] o = new Object[6];
					
					String nombre = eVO.getNombre() + " " + eVO.getApellido();
					
					o[0] = nombre;
					o[1] = "Cumpleaños";
					o[2] = "Empleado";
					o[3] = eVO.getTelefono();
					o[4] = eVO.getId_usuario();
					
					SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
					
					String fn = ft.format(eVO.getFecha_nacimiento());
					
					o[5] = fn;
					
					t_model.addRow(o);
					cantidad++;
			
				}
				
			}
		}
			
			
			//Mensajes.getMensaje_cumpleaños(nombre, fecha);
//			Mensajes.getMensaje_cumpleaños(nombre, fecha);
			
		
		
		//alto_filas();
		tabla.getPreferredScrollableViewportSize().setSize(500,
				tabla.getRowHeight() * cantidad);
		
		 centrar_columnas(tabla);
		 //ancho_columnas();
		pNotificacion.updateUI();
		
		
	}
	
	private void ancho_columnas(){
		
		for(int i = 0; i < tabla.getColumnCount(); i++){
			
			if(i == 0) tabla.getColumnModel().getColumn(i).setPreferredWidth(150);
			else tabla.getColumnModel().getColumn(i).setPreferredWidth(100);
			
		}
	
	}
	
	
	private void centrar_columnas(JTable tabla){
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla.getColumnCount(); i ++)
			
			tabla.getColumnModel().getColumn(i).setCellRenderer(cent);
		
	}	
	
	    
	    public void setVistaPrincipal(VistaPrincipal _vista_principal){
			
			this._vista_principal = _vista_principal;
		}
	    
		
		public void setControladorCliente(ControladorCliente controladorCliente){
			
			this.controladorCliente = controladorCliente;
		}
		public void setControladorEmpleado(ControladorEmpleado controladorEmpleado){
			
			this._controladorEmpleado = controladorEmpleado;
		}
		
		public void setControladorUsuario(ControladorUsuario _controladorUsuario){
			
			this._controladorUsuario = _controladorUsuario;
		}
		
		private String [] getColumnas(){
			
			String columna [] = new String []{ "Nombre", "Detalle","Categoría","Telefono","ID", "FN"};
			
			return columna;
		}
		
		class CustomJToolTip extends JToolTip {

		    public CustomJToolTip(JComponent component) {
		        super();
		        setComponent(component);
		        setBackground(Color.white);
		        setForeground(Color.BLACK);
		    }
		}
}

