package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javafx.util.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controlador.ControladorArticulo;
import controlador.ControladorCaja;
import controlador.ControladorCajaZona;
import controlador.ControladorCambio_plan;
import controlador.ControladorCliente;
import controlador.ControladorCobrador;
import controlador.ControladorCombo;
import controlador.ControladorDespacho_diario;
import controlador.ControladorJefeCalle;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorPremios;
import controlador.ControladorPrestamo;
import controlador.ControladorVentas;
import controlador.Principal;
import modelo.LogicaCaja;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Caja_zonasVO;
import modelo_vo.Cambio_planVO;
import modelo_vo.ClienteVO;
import modelo_vo.Despacho_diarioVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.LocalidadVO;
import modelo_vo.Motivo_generalVO;
import modelo_vo.ObservacionesVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.UsuariosVO;
import modelo_vo.VentasVO;
import vista.VistaDespacho_diario.CustomJToolTip;

public class VistaPremiosComisiones extends JInternalFrame implements ActionListener {
	
	
	private JTable tabla_premios;
	private JTable tabla_ventas;
	private JTable tabla_retiros;
	private JTable tabla_cambioPlan;
	private JTable tabla_comisiones;
	private DefaultTableModel t_model_premios;
	private DefaultTableModel t_model_ventas;
	private DefaultTableModel t_model_retiros;
	private DefaultTableModel t_model_cambioPlan;
	private DefaultTableModel t_model_comisiones;
	private JTable tabla_impresion = new JTable();
	private DefaultTableModel t_model_impresion;
	private Object objeto_ar [] = new Object[9];
	private JScrollPane scr_premios;
	private JScrollPane scr_ventas;
	private JScrollPane scr_retiros;
	private JScrollPane scr_cambioPlan;
	private JScrollPane scr_comisiones;
	private JPanel pIntegra = new JPanel();
	//private JPanel pIntegra_premios = new PanelGraduado(new Color(245, 245, 245), new Color(200, 200, 200));
	//private JPanel pIntegra_comisiones = new PanelGraduado(new Color(245, 245, 245), new Color(200, 200, 200));
	private JPanel pIntegra_premios = new JPanel();
	private JPanel pIntegra_comisiones = new JPanel();
	private JPanel pTabla_premios = new JPanel();
	private JPanel pTabla_ventas = new JPanel();
	private JPanel pTabla_retiros = new JPanel();
	private JPanel pTabla_cambioPlan = new JPanel();
	private JPanel pTabla_comisiones = new JPanel();
	private JPanel pComandos =  new PanelGraduado(new Color(234, 234, 234), new Color(150, 150, 150));
	private JPanel pComandos_com =  new PanelGraduado(new Color(234, 234, 234), new Color(150, 150, 150));

	
	private JDatePickerImpl fecha_hasta;
	private UtilDateModel modelFH = new UtilDateModel();
	private JFormattedTextField fh;
	private JDatePickerImpl fecha_desde;
	private UtilDateModel modelFD = new UtilDateModel();
	private JFormattedTextField fd;
	private JButton ver_fb = new JButton("Ver");

	private JDatePickerImpl fecha_hasta_com;
	private UtilDateModel modelFH_com = new UtilDateModel();
	private JFormattedTextField fh_com;
	private JDatePickerImpl fecha_desde_com;
	private UtilDateModel modelFD_com = new UtilDateModel();
	private JFormattedTextField fd_com;
	private JButton ver_fb_com = new JButton("Ver");
	
	private JButton actualizar_venta = new JButton("Actualizar venta");
	private JButton actualizar_retiro = new JButton("Actualizar");
	private JButton rechazar_cp = new JButton("Rechazar cambio de plan");
	
	private JLabel fechaL, fechaLcom;
	
	private VistaBuscarPedidos_porClientes _vista_buscarPedido_cliente;
	private ControladorPagoDiario _controladorPagoDiario;
	private ControladorArticulo _controladorArticulo;
	private ControladorCombo _controladorCombo;
	private ControladorPrestamo _controladorPrestamo;
	private ControladorCliente _controladorCliente;
	private ControladorDespacho_diario cdp;
	private ControladorPedidos _controladorPedido;
	private ControladorJefeCalle controladorJefe_calle;
	private ControladorCobrador controladorCobrador;
	private ControladorPremios controladorPremio;
	private ControladorCajaZona controladorCajaZona;
	private ControladorVentas controladorVentas;
	private ControladorCambio_plan controlador_cp;
	private BusquedaEntities be;
	private static VistaPrincipal _vista_principal;
	
	private int cantidad;
	
	private ArrayList<JTextField> ar = new ArrayList<JTextField>();
	
	private VistaBuscarCliente vista_buscar_cliente;
	
	 /* private JButton imprimir = new JButton(){
	        //override the JButtons createToolTip method
	        @Override
	        public JToolTip createToolTip() {
	            return (new CustomJToolTip(this));
	        }
	    };
	    
	    private JButton historial = new JButton(){
	        //override the JButtons createToolTip method
	        @Override
	        public JToolTip createToolTip() {
	            return (new CustomJToolTip(this));
	        }
	    };*/
	    
	    private boolean[] canEdit_premios= new boolean[]{
	            false, false, false, false, false
	    };
	    
	    private boolean[] canEdit_ventas= new boolean[]{
	            false, false, false, false,false, false, false
	    };
	    
	    private boolean[] canEdit_retiros= new boolean[]{
	            false, false, false, false,false, false, false, false, false
	    };
	    
	    private boolean[] canEdit_cambioPlan= new boolean[]{
	            false, false, false, false, false, false, false, false
	            , false, false	    };
	    
	    private boolean[] canEdit_comisiones= new boolean[]{
	            false, false, false, false, false, false, false
	    };
	    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	public VistaPremiosComisiones(){
		//super(_vista_principal, "Premios y comisiones", Dialog.ModalityType.APPLICATION_MODAL);
		//this.setAlwaysOnTop(true);
	 	//this.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
	 	super("Premios y comisiones", true, true, true, true);
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		//this.setLayout(new BorderLayout());
		
		ver_fb.addActionListener(this);
		ver_fb_com.addActionListener(this);
		actualizar_venta.addActionListener(this);
		actualizar_retiro.addActionListener(this);
		rechazar_cp.addActionListener(this);
		
		
		/*imprimir.addActionListener(this);
		historial.addActionListener(this);*/
		
		pIntegra.setLayout(new BoxLayout(pIntegra, BoxLayout.Y_AXIS));
		
		//pIntegra_premios.setLayout(new BorderLayout());
		pIntegra_comisiones.setLayout(new BoxLayout(pIntegra_comisiones, BoxLayout.Y_AXIS));
		
		//pIntegra.setBackground(Color.WHITE);
		pIntegra_premios.setBackground(Color.WHITE);
		pIntegra_comisiones.setBackground(Color.WHITE);
		
		pTabla_premios.setBackground(Color.WHITE);
		pTabla_ventas.setBackground(Color.WHITE);
		pTabla_cambioPlan.setBackground(Color.WHITE);
		pTabla_retiros.setBackground(Color.WHITE);
		pTabla_comisiones.setBackground(Color.WHITE);
		
		pIntegra.setPreferredSize(new Dimension(dim.width*75/100,1000));
		
		pIntegra_premios.setPreferredSize(new Dimension(dim.width*75/100,300));
		pTabla_premios.setPreferredSize(new Dimension(dim.width*75/100,150));
		
		pIntegra_comisiones.setPreferredSize(new Dimension(dim.width*75/100,800));
		pTabla_ventas.setPreferredSize(new Dimension(dim.width*65/100,150));
		pTabla_cambioPlan.setPreferredSize(new Dimension(dim.width*65/100,150));
		pTabla_retiros.setPreferredSize(new Dimension(dim.width*65/100,150));
		pTabla_comisiones.setPreferredSize(new Dimension(dim.width*65/100,150));
		
		Border borde_premios = BorderFactory.createTitledBorder(null, "Premios por cobranza", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,16), Color.BLACK);
		pIntegra_premios.setBorder(borde_premios);
		
		Border borde_comisiones = BorderFactory.createTitledBorder(null, "Comisiones de ventas", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,16), Color.BLACK);
		pIntegra_comisiones.setBorder(borde_comisiones);
		
		Border borde_tcomisiones = BorderFactory.createTitledBorder(null, "Resumen de comisiones", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.PLAIN,14), Color.BLACK);
		pTabla_comisiones.setBorder(borde_tcomisiones);
		
		Border borde_tventas = BorderFactory.createTitledBorder(null, "Ventas", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.PLAIN,14), Color.BLACK);
		pTabla_ventas.setBorder(borde_tventas);
		
		Border borde_tretiros = BorderFactory.createTitledBorder(null, "Retiros", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.PLAIN,14), Color.BLACK);
		pTabla_retiros.setBorder(borde_tretiros);
		
		Border borde_tcambioPlan = BorderFactory.createTitledBorder(null, "Cambio de plan", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.PLAIN,14), Color.BLACK);
		pTabla_cambioPlan.setBorder(borde_tcambioPlan);
	        
		pIntegra.setBorder(new EmptyBorder(10,10,10,10));
	        //Display the window.
		
	        //this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	        this.setSize(dim.width*80/100, dim.height*80/100);
	       // frame.getContentPane().add(scr); 
	    
	        	
	       // if(_vista_buscarPedido_cliente.isShowing()) _vista_buscarPedido_cliente.setEnabled(false);
	        
	        //scr.setViewportView(tabla);
	        this.setFocusable(true);
	        this.setResizable(false);
	        //this.setLocationRelativeTo(null);
	        
	        setClosable(true); 
	        setIconifiable(true); 
	        setMaximizable(true); 
	        setResizable(true); 
	        
	        t_model_premios = new DefaultTableModel(null, getColumnas_premios()){
				 
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit_premios[columnIndex];
	            }
		    };
		
		    tabla_premios = new JTable();
		    tabla_premios.getTableHeader().setReorderingAllowed(false);
		    tabla_premios.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
		    tabla_premios.setFont(new Font("Arial", Font.PLAIN, 12));	
		    tabla_premios.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		   
		    scr_premios = new JScrollPane();
		   //scr_premios = new JScrollPane(tabla_premios, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			//			   JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		   scr_premios.setViewportView(tabla_premios);
		   scr_premios.setPreferredSize(new Dimension(dim.width*70/100, 120));	
		   
		   t_model_ventas = new DefaultTableModel(null, getColumnas_ventas()){
				 
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit_ventas[columnIndex];
	            }
		    };
		
		    tabla_ventas = new JTable();
		    tabla_ventas.getTableHeader().setReorderingAllowed(false);
		    tabla_ventas.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
		    tabla_ventas.setFont(new Font("Arial", Font.PLAIN, 12));	
		    tabla_ventas.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

		   scr_ventas = new JScrollPane();
		   scr_ventas.setViewportView(tabla_ventas);
		   scr_ventas.setPreferredSize(new Dimension(dim.width*70/100, 120));	
		   
		   t_model_retiros = new DefaultTableModel(null, getColumnas_retiros()){
				 
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit_retiros[columnIndex];
	            }
		    };
		
		    tabla_retiros = new JTable();
		    tabla_retiros.getTableHeader().setReorderingAllowed(false);
		    tabla_retiros.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
		    tabla_retiros.setFont(new Font("Arial", Font.PLAIN, 12));	
		    tabla_retiros.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

		   scr_retiros = new JScrollPane();
		   scr_retiros.setViewportView(tabla_retiros);
		   scr_retiros.setPreferredSize(new Dimension(dim.width*70/100, 120));	
		   
		   t_model_cambioPlan = new DefaultTableModel(null, getColumnas_cambioPlan()){
				 
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit_cambioPlan[columnIndex];
	            }
		    };
		
		    tabla_cambioPlan = new JTable();
		    tabla_cambioPlan.getTableHeader().setReorderingAllowed(false);
		    tabla_cambioPlan.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
		    tabla_cambioPlan.setFont(new Font("Arial", Font.PLAIN, 12));	
		    tabla_cambioPlan.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

		   scr_cambioPlan = new JScrollPane();
		   scr_cambioPlan.setViewportView(tabla_cambioPlan);
		   scr_cambioPlan.setPreferredSize(new Dimension(dim.width*70/100, 120));	
		   
		   t_model_comisiones = new DefaultTableModel(null, getColumnas_comisiones()){
				 
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit_comisiones[columnIndex];
	            }
		    };
		
		    tabla_comisiones = new JTable();
		    tabla_comisiones.getTableHeader().setReorderingAllowed(false);
		    tabla_comisiones.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
		    tabla_comisiones.setFont(new Font("Arial", Font.PLAIN, 12));	
		    tabla_comisiones.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

		   scr_comisiones = new JScrollPane();
		   scr_comisiones.setViewportView(tabla_comisiones);
		   scr_comisiones.setPreferredSize(new Dimension(dim.width*70/100, 120));
		   
		   
		 
		   pTabla_premios.add(scr_premios);
		   pTabla_ventas.add(scr_ventas, BorderLayout.CENTER);
		   pTabla_retiros.add(scr_retiros);
		   pTabla_cambioPlan.add(scr_cambioPlan);
		   pTabla_cambioPlan.add(rechazar_cp);
		   pTabla_comisiones.add(scr_comisiones);
		   
		   Date d = new Date();
			java.sql.Date hoy = new java.sql.Date(d.getTime());
			
			fechaL = new JLabel();
			fechaLcom = new JLabel();
			
			setLabelFecha(hoy, fechaL);
			setLabelFecha(hoy, fechaLcom);
			
			JLabel fecha_busquedaL = new JLabel();
			fecha_busquedaL.setText("Fecha de consulta");
			JPanel p_fb = new PanelGraduado(new Color(234, 234, 234), new Color(150, 150, 150));
		
	    	Border bordefb = BorderFactory.createTitledBorder(null, "Fecha de consulta", 
	    	    	TitledBorder.CENTER, TitledBorder.TOP,
	    	    	new Font("Arial",Font.PLAIN,16), Color.BLACK);
	    	p_fb.setBorder(bordefb);
	    	JPanel p_fbcom = new PanelGraduado(new Color(234, 234, 234), new Color(150, 150, 150));
	    	
	    	Border bordefbcom = BorderFactory.createTitledBorder(null, "Fecha de consulta", 
	    			TitledBorder.CENTER, TitledBorder.TOP,
	    			new Font("Arial",Font.PLAIN,16), Color.BLACK);
	    	p_fbcom.setBorder(bordefbcom);
			
	    	modelFH.setSelected(true);
	    	modelFD.setValue(this.getUltimo_lunes(hoy));
	    	modelFD.setSelected(true);
	    	modelFH_com.setSelected(true);
	    	modelFD_com.setValue(this.getUltimo_sabado(hoy));
	    	modelFD_com.setSelected(true);
			
	        //model.setDate(20,04,2014);
	  		Properties pr = new Properties();
	  		pr.put("text.today", "Today");
	  		pr.put("text.month", "Month");
	  		pr.put("text.year", "Year");
	        JDatePanelImpl datePanelFH= new JDatePanelImpl(modelFH, pr);
	        JDatePanelImpl datePanelFD = new JDatePanelImpl(modelFD, pr);
	        JDatePanelImpl datePanelFHcom= new JDatePanelImpl(modelFH_com, pr);
	        JDatePanelImpl datePanelFDcom = new JDatePanelImpl(modelFD_com, pr);
	       
	        fecha_hasta = new JDatePickerImpl(datePanelFH, new DateLabelFormatter());
	        fecha_desde = new JDatePickerImpl(datePanelFD, new DateLabelFormatter());
			
	        fecha_hasta_com = new JDatePickerImpl(datePanelFHcom, new DateLabelFormatter());
	        fecha_desde_com = new JDatePickerImpl(datePanelFDcom, new DateLabelFormatter());
	        
	        p_fb.add(fecha_desde);
	        p_fb.add(fecha_hasta);
	        p_fb.add(ver_fb);
			
			 fh= fecha_hasta.getJFormattedTextField();
			 fh.setFont(new Font("Arial", Font.PLAIN, 18));
		   
			 fd= fecha_desde.getJFormattedTextField();
			 fd.setFont(new Font("Arial", Font.PLAIN, 18));
			 
		   //pComandos.add(fechaL);
		   pComandos.add(p_fb);

		   p_fbcom.add(fecha_desde_com);
		   p_fbcom.add(fecha_hasta_com);
		   p_fbcom.add(ver_fb_com);
		   
		   fh_com= fecha_hasta_com.getJFormattedTextField();
		   fh_com.setFont(new Font("Arial", Font.PLAIN, 18));
		   
		   fd_com= fecha_desde_com.getJFormattedTextField();
		   fd_com.setFont(new Font("Arial", Font.PLAIN, 18));
		   
		  // pComandos_com.add(fechaLcom);
		   pComandos_com.add(p_fbcom);
			
		   //pIntegra_premios.setPreferredSize(new Dimension(800,100));
		   pIntegra_premios.add(pTabla_premios);
		   
		  // JPanel pIntegra = new JPanel();
		   pIntegra.setLayout(new BoxLayout(pIntegra, BoxLayout.Y_AXIS));
		   
		   pIntegra.add(pComandos_com);

		   /*JPanel pComando_venta = new JPanel();
		   pComando_venta.add(actualizar_venta);
		   
		   JPanel pIntegra_venta = new JPanel();
		   pIntegra_venta.setLayout(new BorderLayout());
		   pIntegra_venta.setBackground(Color.WHITE); 
		   pIntegra_venta.setPreferredSize(new Dimension(dim.width*65/100,150));
		   
		   pIntegra_venta.add(pComando_venta, BorderLayout.PAGE_START);
		   pIntegra_venta.add(pTabla_ventas, BorderLayout.CENTER);*/
		   
		   pTabla_ventas.add(actualizar_venta);
		   pIntegra_comisiones.add(pTabla_ventas);
		   pIntegra_comisiones.add(pTabla_retiros);
		   pIntegra_comisiones.add(pTabla_cambioPlan);
		   pIntegra_comisiones.add(pTabla_comisiones);
			
		   JPanel pFecha = new JPanel();
		   pFecha.setBackground(Color.white);
		   pFecha.add(fechaL);
		   
		   this.add(pFecha);
		   
			this.add(pComandos);
			this.add(pIntegra_premios);
			
			this.add(pComandos_com);
			
			pIntegra.add(pIntegra_comisiones);
			//frame.pack();
			
			JScrollPane scr = new JScrollPane();
			scr.setViewportView(pIntegra);
			scr.setPreferredSize(new Dimension(dim.width*80/100, dim.height*80/100));
			
			scr.getVerticalScrollBar().setUnitIncrement(16);
			
	       this.add(scr);
	       
	       if(Principal._usuario.getId_usuario()==1 ||
	    		   Principal._usuario.getId_usuario()==2 ||
	    		   Principal._usuario.getId_usuario()==6){
	    	   
	    	   actualizar_venta.setEnabled(true);
	    	   rechazar_cp.setEnabled(true);
	       }
	       else{
	    	   
	    	   actualizar_venta.setEnabled(false);
	    	   rechazar_cp.setEnabled(false);
	    	   
	       }
	       
	        //_vista_principal.setEnabled(false);
	}
	
	public void iniciar_premio(java.sql.Date desde, java.sql.Date hasta){
		 
		limpiar(tabla_premios);
		
		int cantidad = 0;
	
		ArrayList<Object []> ar_ob = controladorPremio.calcularPremio(desde, hasta);
		
		if(ar_ob!=null){
			
			for(Object [] d: ar_ob){
					
					t_model_premios.addRow(d);
					cantidad += 1;
									
			}
					
		}
		
		tabla_premios.setModel(t_model_premios);
		
		/*tabla_premios.getPreferredScrollableViewportSize().setSize(dim.width*75/100,
				tabla_premios.getRowHeight() * cantidad);*/
		
		 centrar_columnas(tabla_premios);
		
		pTabla_premios.updateUI();
		
		pIntegra.updateUI();
		
	}
	
	public void iniciar_ventas(java.sql.Date desde, java.sql.Date hasta){
		 
		limpiar(tabla_ventas);
		
		int cantidad = 0;
	
		ArrayList<Object []> ar_ob = controladorPremio.buscarVentas(desde, hasta);
		
		if(ar_ob!=null){
			
			for(Object [] d: ar_ob){
					
					t_model_ventas.addRow(d);
					cantidad += 1;
									
			}
					
		}
		
		tabla_ventas.setModel(t_model_ventas);
		
		/*tabla_ventas.getPreferredScrollableViewportSize().setSize(dim.width*75/100,
				tabla_ventas.getRowHeight() * cantidad);*/
		
		 centrar_columnas(tabla_ventas);
		
		pTabla_ventas.updateUI();
		
		pIntegra.updateUI();
		
	}
	
	public void iniciar_retiros(java.sql.Date desde, java.sql.Date hasta){
		 
		limpiar(tabla_retiros);
		
		int cantidad = 0;
	
		ArrayList<Object []> ar_ob = controladorPremio.buscarRetiros(desde, hasta);
		
		if(ar_ob!=null){
			
			for(Object [] d: ar_ob){
					
					t_model_retiros.addRow(d);
					cantidad += 1;
									
			}
					
		}
		
		tabla_retiros.setModel(t_model_retiros);
		
		/*tabla_retiros.getPreferredScrollableViewportSize().setSize(1000,
				tabla_retiros.getRowHeight() * cantidad);*/
		
		 centrar_columnas(tabla_retiros);
		
		pTabla_retiros.updateUI();
		
		pIntegra.updateUI();
		
	}
	
	public void iniciar_cambioPlan(java.sql.Date desde, java.sql.Date hasta){
		 
		limpiar(tabla_cambioPlan);
		
		int cantidad = 0;
	
		ArrayList<Object []> ar_ob = controladorPremio.buscarCambioPlan(desde, hasta);
		
		if(ar_ob!=null){
			
			for(Object [] d: ar_ob){
					
					t_model_cambioPlan.addRow(d);
					cantidad += 1;
									
			}
					
		}
		
		tabla_cambioPlan.setModel(t_model_cambioPlan);
		
		/*tabla_cambioPlan.getPreferredScrollableViewportSize().setSize(1000,
				tabla_cambioPlan.getRowHeight() * cantidad);*/
		
		 centrar_columnas(tabla_cambioPlan);
		
		pTabla_cambioPlan.updateUI();
		
		pIntegra.updateUI();
		
	}
	
	public void iniciar_comisiones(java.sql.Date desde, java.sql.Date hasta){
		 
		limpiar(tabla_comisiones);
		
		int cantidad = 0;
	
		ArrayList<Object []> ar_ob = controladorPremio.calcularComision(desde, hasta);
		
		if(ar_ob!=null){
			
			for(Object [] d: ar_ob){
					
					t_model_comisiones.addRow(d);
					cantidad += 1;
									
			}
					
		}
		
		tabla_comisiones.setModel(t_model_comisiones);
		
		/*tabla_comisiones.getPreferredScrollableViewportSize().setSize(1000,
				tabla_comisiones.getRowHeight() * cantidad);*/
		
		 centrar_columnas(tabla_comisiones);
		
		pTabla_comisiones.updateUI();
		
		pIntegra.updateUI();
		
	}
	
	private void centrar_columnas(JTable tabla){
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla.getColumnCount(); i ++)
			
			tabla.getColumnModel().getColumn(i).setCellRenderer(cent);
		
	}	
	
	private void ancho_columnas(JTable t){
		
		for(int i = 0; i < t.getColumnCount(); i++){
			
			if(i == 2) t.getColumnModel().getColumn(i).setPreferredWidth(250);
			else t.getColumnModel().getColumn(i).setPreferredWidth(50);
		}
	
	}
	
	private String [] getColumnas_premios(){
		
		String columna [] = new String []{"Cobrador", "Monto ideal", "Cobranza efectiva",
										"Efectividad", "Premio"};
		
		return columna;
	}
	
	private String [] getColumnas_ventas(){
		
		String columna [] = new String []{"N° venta", "N° pedido", "Artículo", "Credito", "Vendedor", "Usuario",
										 "Fecha registro", "Hora registro"};
		
		return columna;
	}
	
	private String [] getColumnas_retiros(){
		
		String columna [] = new String []{"N° retiro", "N° pedido", "Artículo", "Credito", "Vendedor", "Observaciones", 
				"Usuario",
				 "Fecha registro", "Hora registro"};
		
		return columna;
	}
	
	private String [] getColumnas_cambioPlan(){
		
		String columna [] = new String []{"N° cambio", "N° pedido", "Vendedor", "Plan anterior",
				"Plan posterior","Credito anterior",
				"Credito posterior",
				"Usuario",
				 "Fecha registro", "Hora registro"};
		
		return columna;
	}
	
	private String [] getColumnas_comisiones(){
		
		String columna [] = new String []{"Vendedor", "N° ventas", "N° retiros", "Total ventas", "Comisión", 
				"D/I", "Total comisión"};
		
		return columna;
	}
	
    public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vpc){
    	
    	this._vista_buscarPedido_cliente = vpc;
    }
    
    public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
    	
    	this.vista_buscar_cliente = vista_buscar_cliente;
    }
    
    public void setVistaPrincipal(VistaPrincipal vista_principal){
    	
    	this._vista_principal = vista_principal;
    }
    
    public void setControladorPagoDiario(ControladorPagoDiario _controladorPagoDiario){
    	
    	this._controladorPagoDiario = _controladorPagoDiario;
    }
    
    public void setControladorPremios(ControladorPremios _controladorPremio){
    	
    	this.controladorPremio = _controladorPremio;
    }
    
    public void setControladorArticulo(ControladorArticulo _controladorArticulo){
    	
    	this._controladorArticulo = _controladorArticulo;
    }
 
    public void setControladorCombo(ControladorCombo _controladorCombo){
 	
    	this._controladorCombo = _controladorCombo;
    }
 
    public void setControladorPrestamo(ControladorPrestamo _controladorPrestamo){
 	
    	this._controladorPrestamo = _controladorPrestamo;
    }
    
    public void setControladorCobrador(ControladorCobrador controladorCobrador){
     	
    	this.controladorCobrador = controladorCobrador;
    }
    
    public void setControladorVentas(ControladorVentas controladorVentas){
     	
    	this.controladorVentas = controladorVentas;
    }
    public void setControladorCambio_plan(ControladorCambio_plan controlador_cp){
    	
    	this.controlador_cp = controlador_cp;
    }
    
    public void setControladorJefe_calle(ControladorJefeCalle controladorJefe_calle){
     	
    	this.controladorJefe_calle = controladorJefe_calle;
    }
    
    public void setControladorDespacho_diario(ControladorDespacho_diario cdp){
     	
    	this.cdp = cdp;
    }
    
    public void setControladorCajaZona(ControladorCajaZona controladorCajaZona){
     	
    	this.controladorCajaZona = controladorCajaZona;
    }
   
    public void setControladorPedidos(ControladorPedidos controladorPedidos){
     	
    	this._controladorPedido = controladorPedidos;
    }
    
	public void setControladorCliente(ControladorCliente controladorCliente){
     	
    	this._controladorCliente = controladorCliente;
    }
	
	public void setBusquedaEntities(BusquedaEntities be){
		
		this.be = be;
	}
    
    public void limpiar(JTable t){
    	
    	if(t.getRowCount() > 0){
			
			DefaultTableModel modelo = (DefaultTableModel) t.getModel();
			
			int contFila = t.getRowCount();
			
			for(int i = 0; i < contFila; i++)
			
				modelo.removeRow(0);
		}
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//this.be.setPanel("vista_despacho");
		
		if(e.getSource()==actualizar_venta){
			
			int [] ar_selec = tabla_ventas.getSelectedRows();
			
			if(ar_selec.length > 0){
				
				for(int i = 0; i < ar_selec.length; i++){
					
					VentasVO vVO = new VentasVO();
					
					vVO.setN_venta(Integer.parseInt(tabla_ventas.getModel()
							.getValueAt(ar_selec[i],0).toString()));
					
					PedidosVO pVO = _controladorPedido.
								buscarPedido_porNpedido(Integer.parseInt(tabla_ventas.getModel()
										.getValueAt(ar_selec[i],1).toString()));
				
					vVO.setN_pedido(pVO.getN_pedido());
					
					ArrayList<Pedido_articuloVO> ar_pedido_ar = _controladorPedido.
							buscarArticulos_porPedido(pVO.getN_pedido(), true);
					
					String plan = "";
						
						for(Pedido_articuloVO paVO : ar_pedido_ar){
					
							
							ArticulosVO aVO = _controladorArticulo.
									buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
							
							ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
							
							if(apVO!=null){
								
								plan = plan + "" + "Prestamo(" + apVO.getCodigo() + 
										")$" + Double.toString(apVO.getMonto());
							}
							else
								plan = plan + " " + aVO.getNombre()+
										"(" + aVO.getCodigo() + ")";
						}
					
					vVO.setPlan(plan);
					
					vVO.setCredito(pVO.getDias() * pVO.getCuota_diaria());
					
					ClienteVO cVO = _controladorCliente.buscarCliente_porNPedido(pVO.getN_pedido());
					
					if(cVO!=null){
						
						vVO.setId_vendedor(cVO.getId_vendedor());
						
					}
						
					int res = controladorVentas.updateVentas(vVO);
					
					if(res > 0){
						
						Mensajes.getMensaje_modificacionExitosa();
						
						ActionEvent event;
						long when;

						when  = System.currentTimeMillis();
						event = new ActionEvent(ver_fb_com,
								ActionEvent.ACTION_PERFORMED, "Anything", when, 0);
						
						for (ActionListener listener : ver_fb_com.getActionListeners()) {
						    listener.actionPerformed(event);
						}
					}
					else Mensajes.getMensaje_modificacion_sinExito();
				}
			}
			else Mensajes.getMensaje_sinFilasSeleccionadas();
		}
		if(e.getSource()==rechazar_cp){
			
			int [] ar_selec = tabla_cambioPlan.getSelectedRows();
			
			if(ar_selec.length > 0){
				
				for(int i = 0; i < ar_selec.length; i++){
					
					Cambio_planVO cVO = new Cambio_planVO();
					
					cVO.setN_cambio(Integer.parseInt(tabla_cambioPlan.getModel()
							.getValueAt(ar_selec[i],0).toString()));
					
					
					int opcion = Mensajes.getMensaje_confirmacion_rechazar_cambioPlan(cVO.getN_cambio());
					
					if(opcion==JOptionPane.YES_OPTION){
						
						int res = controlador_cp.deleteCambio_plan(cVO);
						
						if(res > 0){
							
							Mensajes.getMensaje_anulacionExitosa();
							
							ActionEvent event;
							long when;

							when  = System.currentTimeMillis();
							event = new ActionEvent(ver_fb_com,
									ActionEvent.ACTION_PERFORMED, "Anything", when, 0);
							
							for (ActionListener listener : ver_fb_com.getActionListeners()) {
							    listener.actionPerformed(event);
							}
							
						}
						else Mensajes.getMensaje_anulacionError();
						
					}
					
				}
			}
			else Mensajes.getMensaje_sinFilasSeleccionadas();
		}
		
		if(e.getSource()== ver_fb){
			
			ControladorCaja controladorCaja = new ControladorCaja();
			LogicaCaja logica_caja = new LogicaCaja();
			controladorCaja.setLogicaCaja(logica_caja);
			
			
			if(controladorCaja.ingresoFecha_usuario(fecha_desde.getJFormattedTextField().getText()) &&
					controladorCaja.ingresoFecha_usuario(
							fecha_hasta.getJFormattedTextField().getText())){
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				SimpleDateFormat ft_desde = new SimpleDateFormat("dd-MM-yyyy");
				Date d_desde=null;
				try {
					d_desde = ft_desde.parse(fecha_desde.getJFormattedTextField().getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date fecha_desde = new java.sql.Date(d_desde.getTime());

				String date = ft_desde.format(fecha_desde).toString();
				String diaS = "";
				//convert String to LocalDate
				LocalDate localDate = LocalDate.parse(date, formatter);
				
				switch(localDate.getDayOfWeek().name()){
				
				case "MONDAY": diaS = "lunes";
				break;
				case"TUESDAY": diaS = "martes";
				break;
				case"WEDNESDAY": diaS = "miércoles";
				break;
				case"THURSDAY": diaS = "jueves";
				break;
				case"FRIDAY": diaS = "viernes";
				break;
				case"SATURDAY": diaS = "sábado";
				break;
				}

				System.out.println(diaS);
				
				SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
				Date d=null;
				try {
					d = ft.parse(fecha_hasta.getJFormattedTextField().getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date fecha = new java.sql.Date(d.getTime());
				
				//setLabelFecha(fecha);
					
				if(diaS.equals("lunes"))
				
					this.iniciar_premio(fecha_desde, fecha);
				else
					Mensajes.getMensaje_error_fecha_premios();
				
			}
				
		}
		if(e.getSource()== ver_fb_com){
			
			ControladorCaja controladorCaja = new ControladorCaja();
			LogicaCaja logica_caja = new LogicaCaja();
			controladorCaja.setLogicaCaja(logica_caja);
			
			
			if(controladorCaja.ingresoFecha_usuario(fecha_desde_com.getJFormattedTextField().getText()) &&
					controladorCaja.ingresoFecha_usuario(
							fecha_hasta_com.getJFormattedTextField().getText())){
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				SimpleDateFormat ft_desde = new SimpleDateFormat("dd-MM-yyyy");
				Date d_desde=null;
				try {
					d_desde = ft_desde.parse(fecha_desde_com.getJFormattedTextField().getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date fecha_desde = new java.sql.Date(d_desde.getTime());
				
				SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
				Date d=null;
				try {
					d = ft.parse(fecha_hasta_com.getJFormattedTextField().getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date fecha = new java.sql.Date(d.getTime());
				
				//setLabelFecha(fecha);
				
				String date = ft_desde.format(fecha_desde).toString();
				String diaS = "";
				//convert String to LocalDate
				LocalDate localDate = LocalDate.parse(date, formatter);
				
				switch(localDate.getDayOfWeek().name()){
				
				case "MONDAY": diaS = "lunes";
				break;
				case"TUESDAY": diaS = "martes";
				break;
				case"WEDNESDAY": diaS = "miércoles";
				break;
				case"THURSDAY": diaS = "jueves";
				break;
				case"FRIDAY": diaS = "viernes";
				break;
				case"SATURDAY": diaS = "sábado";
				break;
				}

				if(diaS.equals("sábado")){
					
					this.iniciar_ventas(fecha_desde,fecha);
					this.iniciar_retiros(fecha_desde,fecha);
					this.iniciar_cambioPlan(fecha_desde,fecha);
					this.iniciar_comisiones(fecha_desde,fecha);
					
				}
				else
					Mensajes.getMensaje_error_fecha_comision();
				
			}
			
		}
		
	}
	
	public void setLabelFecha(java.sql.Date fecha, JLabel fechaL){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		
		String date = ft.format(fecha).toString();
		String diaS = "";
		//convert String to LocalDate
		LocalDate localDate = LocalDate.parse(date, formatter);
		
		switch(localDate.getDayOfWeek().name()){
		
		case "MONDAY": diaS = "lunes";
		break;
		case"TUESDAY": diaS = "martes";
		break;
		case"WEDNESDAY": diaS = "miércoles";
		break;
		case"THURSDAY": diaS = "jueves";
		break;
		case"FRIDAY": diaS = "viernes";
		break;
		case"SATURDAY": diaS = "sábado";
		break;
		}
	
		fechaL.setFont(new Font("Arial",Font.BOLD,18));
		fechaL.setText("Fecha " + date + " " + diaS);
	}
	
	public static java.sql.Date getUltimo_lunes(java.sql.Date fecha){
		
		CharSequence castDesde = "";
		
		castDesde = fecha.toString();
		
		LocalDate hasta = LocalDate.parse(castDesde);
		
		switch(hasta.getDayOfWeek().name()){
			
			case "SATURDAY": hasta = hasta.plusDays(-5);
			break;
			case "FRIDAY": hasta = hasta.plusDays(-4);
			break;
			case "THURSDAY": hasta = hasta.plusDays(-3);
			break;
			case "WEDNESDAY": hasta = hasta.plusDays(-2);
			break;
			case "TUESDAY": hasta = hasta.plusDays(-1);
			break;
		}
		
		java.sql.Date lunes = java.sql.Date.valueOf(hasta);
		
		return lunes;
		
	}
	public static java.sql.Date getUltimo_sabado(java.sql.Date fecha){
		
		CharSequence castDesde = "";
		
		castDesde = fecha.toString();
		
		LocalDate hasta = LocalDate.parse(castDesde);
		
		switch(hasta.getDayOfWeek().name()){
				
			case "FRIDAY": hasta = hasta.plusDays(-6);
			break;
			case "THURSDAY": hasta = hasta.plusDays(-5);
			break;
			case "WEDNESDAY": hasta = hasta.plusDays(-4);
			break;
			case "TUESDAY": hasta = hasta.plusDays(-3);
			break;
			case "MONDAY": hasta = hasta.plusDays(-2);
			break;
		}
		
		java.sql.Date sabado = java.sql.Date.valueOf(hasta);
		
		return sabado;
	}
	
	public void tabla_impresa(ArrayList<Object[]> ar){
		
		int cantidad = 0;
		
		
		
		
	}
	
	public void convertirDatePicker_soloLunes(){
		
		/* StringConverter<LocalDate> defaultConverter = fecha_desde.getConverter();

	        datePicker.setConverter(new StringConverter<LocalDate>() {

	            @Override
	            public String toString(LocalDate object) {
	                return defaultConverter.toString(object);
	            }

	            @Override
	            public LocalDate fromString(String string) {
	                LocalDate date = defaultConverter.fromString(string);
	                if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
	                    return date ;
	                } else {
	                    // not a Monday. Revert to previous value.
	                    // You could also, e.g., return the closest Monday here.
	                    return datePicker.getValue();
	                }
	            }

	        });

	        datePicker.setDayCellFactory(dp -> new DateCell() {
	            @Override
	            public void updateItem(LocalDate item, boolean empty) {
	                super.updateItem(item, empty);
	                setDisable(empty || item.getDayOfWeek() != DayOfWeek.MONDAY);               
	            }
	        });

	        // Just for debugging: make sure we only see Mondays:
	        datePicker.valueProperty().addListener((obs, oldDate, newDate) -> {
	            if (newDate.getDayOfWeek() != DayOfWeek.MONDAY) {
	                System.out.println("WARNING: date chosen was not a Monday");
	            }
	            System.out.println(newDate + " (" + newDate.getDayOfWeek()+")");
	        });
	}
	
	class CustomJToolTip extends JToolTip {

	    public CustomJToolTip(JComponent component) {
	        super();
	        setComponent(component);
	        setBackground(Color.white);
	        setForeground(Color.BLACK);
	    }*/
	}
	
}
