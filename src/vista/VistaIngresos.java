package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.JTextComponent;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import components.Barra_progreso;
import controlador.ControladorArticulo;
import controlador.ControladorCaja;
import controlador.ControladorCajaZona;
import controlador.ControladorCliente;
import controlador.ControladorCombo;
import controlador.ControladorDespacho_diario;
import controlador.ControladorDomicilioComercial;
import controlador.ControladorDomicilioParticular;
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
import controlador.Principal;
import modelo.CifradoAction;
import modelo.DescifradoAction;
import modelo.LogicaArticulo;
import modelo.LogicaCaja;
import modelo.LogicaCajaZona;
import modelo.LogicaCliente;
import modelo.LogicaCombo;
import modelo.LogicaDespacho;
import modelo.LogicaDomicilioComercial;
import modelo.LogicaDomicilioParticular;
import modelo.LogicaEmpleado;
import modelo.LogicaLocalidad;
import modelo.LogicaMonto_trasladado;
import modelo.LogicaPagoDiario;
import modelo.LogicaPedido;
import modelo.LogicaPrestamo;
import modelo.LogicaRefinanciacion_ex;
import modelo.LogicaRefinanciacion_in;
import modelo.LogicaUsuario;
import modelo.LogicaVendedor;
import modelo.LogicaZona;
import modelo.Mensajes;
import modelo_dao.Refinanciacion_inDAO;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.CobradorVO;
import modelo_vo.Combinacion_diasVO;
import modelo_vo.ComercioVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.Pedidos_diasVO;
import modelo_vo.Refinanciacion_exVO;
import modelo_vo.Refinanciacion_inVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.LocalidadVO;
import modelo_vo.Pago_diarioVO;
import modelo_vo.UsuariosVO;
import modelo_vo.ZonaVO;
import vista.VistaBuscarPedidos_porClientes.CustomJToolTip;
import vista.VistaClientesAtrasados.TableSwingWorker;


public class VistaIngresos extends JPanel implements ActionListener, 
	PropertyChangeListener{

	private JProgressBar progressBar;
	 
    private TableSwingWorker task;
	
	private int clickedRow=-1,clickedCol=-1;
	
	private JPanel pIntegra = new JPanel();
	private JPanel pDatos;
	private JPanel pDatos_impresion;
	private JPanel pBarra = new JPanel();
	private JPanel pImpresion = new JPanel();
	private JPanel pTabla = new JPanel();
	
	JPanel pplanilla = new JPanel();
	
	private JLabel cobradorL = new JLabel();
	private JLabel fechaL = new JLabel();
	private JLabel fecha_impresion;
	private JLabel datosImpresion = new JLabel();
	
	private JLabel total_planillaL = new JLabel();
	
	private JFormattedTextField fi; 
	private JFormattedTextField fb;
	
	private VistaBuscarCliente vista_buscar_cliente;
	
	private ControladorCliente _controladorCliente;
	private ControladorDomicilioParticular _controladorDomPart;
	private ControladorDomicilioComercial _controladorDomCom;
	
	private ControladorVendedor _controladorVendedor;
	private ControladorZona _controladorZona;
	private ControladorLocalidad _controladorLocalidad;
	private ControladorPedidos _controladorPedido;
	private ControladorArticulo _controladorArticulo;
	private ControladorPagoDiario _controladorPagoDiario;
	private ControladorCombo _controladorCombo;
	private ControladorRefinanciacion_ex _controladorRef_ex;
	private ControladorRefinanciacion_in _controladorRef_in;
	private ControladorMonto_trasladado _controladorMonto_t;
	private ControladorObservaciones _controladorObservaciones;
	private ControladorPrestamo _controladorPrestamo;
	private ControladorEmpleado controladorEmpleado;
	private ControladorUsuario _controladorUsuario;
	private BusquedaEntities _busqueda_entities;
	private VistaBuscarPedidos_porClientes vp;
	private VistaPrincipal _vista_principal;
	private VistaNewObjetoVenta vista_combo;
	private VistaPrestamo vista_prestamo;
	
	private JDatePickerImpl fecha_ingreso;
	private JDatePickerImpl fecha_busqueda;
	UtilDateModel modelFI = new UtilDateModel();
	UtilDateModel modelFB = new UtilDateModel();
	
	private PedidosVO _pedidoVO;
	private ArticulosVO _articulosVO;
	private Pedido_articuloVO _combosVO;
	private ArticulosPVO _prestamoVO;
	private UsuariosVO _usuariosVO;
	private Refinanciacion_exVO _refVO;
	private Refinanciacion_inVO _ref_inVO;
	private Pago_diarioVO _pgVO = new Pago_diarioVO();

	private boolean opcion_articulo = false;
	private boolean opcion_prestamo = false;
	
	private Pedido_articuloVO aux_combo;
	private ArticulosVO aux_articulo;
	private ClienteVO aux_cliente;
	private ArticulosPVO aux_prestamo;
	private Refinanciacion_exVO aux_ref;
	private Refinanciacion_inVO aux_ref_in;
	private EmpleadoVO empleadoVO;
	
	public  boolean refinanciacion_ex = false;
	public  boolean refinanciacion_in = false;
	
	private ArrayList<String> ar_nombres = new ArrayList<String>();
	private ArrayList<Short> ar_idcomb = new ArrayList<Short>();
	
	private VistaRefinanciacion vf;
	private VistaRefinanciacion_in vf_in;
	private VistaMonto_t mt;
	private VistaObservacionesPedido obser;
	private Vista_pagos_porPedido v_pagos;
	
	private JTable tabla ;//= new JTable();
	private DefaultTableModel t_model;// = new DefaultTableModel(null, getColumnas());;
	
	private JScrollPane scr;
	
	double cobranza_ideal;
	
	private float[] columnWidthPercentage = {22.5f, 20.0f, 7.5f, 10.0f, 5.0f,7.5f, 5.0f, 7.5f, 5.0f, 10.0f};
	
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private JButton guardar = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
	private JButton modificar = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
    
    private JButton cancelar = new JButton("Cancelar");
    
    private JButton cambiar_orden = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };
    
    private JButton imprimir = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };
    
    private JButton nuevo_comando = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
    
    
    private JButton ver_fi = new JButton("Ver");
    private JButton ver_fb = new JButton("Ver");
    
    private JTextField zonaTF = new JTextField(2);
	private JButton buscar_zona = new JButton("...");
	
	private JLabel lCobradorZona = new JLabel();
	
	private ZonaVO zonaVO = new ZonaVO();
	private String zonaS = new String();
	private String zona_descripcion = new String();
	
	private boolean editar_planilla = false;
	private boolean consulta = false;
	
	private boolean[] canEdit= new boolean[]{
            false, false, false, false, false,false,false,false,true, false
    };
	
	private boolean pedido_finalizado = false;
	private int contador_pedidos = -1;
	int contp = 0;
	
	private ArrayList<PedidosVO> ar_pedidos;
	public VistaIngresos(){
		
		pDatos = new JPanel();
		pDatos.setLayout(new BorderLayout());
		pDatos_impresion = new JPanel();
		pDatos_impresion.setLayout(new BorderLayout());
		
		pTabla.setLayout(new BorderLayout());
		
		
		t_model = new DefaultTableModel(null, getColumnas()){
			 
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit[columnIndex];
	            }
		};
		
		tabla  = new JTable(){
			
			 public void changeSelection( int row, int col, boolean toggle, boolean expand ) {
			        if( col  == 8) { // here you set your own rules
			            super.changeSelection( row, col, toggle, expand );
			        }
			    }
			 
			 public void focusEditedCell()
			   {
			       Component c = tabla.getEditorComponent();
			       if (c != null)
			       {
			          c.requestFocusInWindow();
			       }
			   }

		};
		
		//tabla.setDefaultRenderer(Object.class, new MonCellRenderer());
		
		tabla.getTableHeader().setReorderingAllowed(false);
		
		tabla.setFont(new Font("Arial", Font.PLAIN, dim.height * 2/100));
		
		tabla.setModel(t_model);
		
		tabla.setDefaultRenderer(Object.class, new FilaRenderer(9));
		
		//this.resizeColumnWidth(tabla);
		//tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resizeColumns();
	
		
		tabla.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		
		//tabla.setColumnSelectionAllowed(false);
	   // tabla.setRowSelectionAllowed(false);
	    
		tabla.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				 
					 
					 System.out.println(e.getKeyText(e.getKeyCode()));
					 
					// if(e.getKeyCode() <37 && e.getKeyCode() >40 &&
							 //e.getKeyCode()!=9 && e.getKeyCode()!=10) 
						
					   if(!e.getKeyText(e.getKeyCode()).equals("Intro") &&
							   !e.getKeyText(e.getKeyCode()).equals("Arriba") &&
							   !e.getKeyText(e.getKeyCode()).equals("Derecha") &&
							   !e.getKeyText(e.getKeyCode()).equals("Izquierda") &&
							   !e.getKeyText(e.getKeyCode()).equals("Abajo") &&
							   !e.getKeyText(e.getKeyCode()).equals("Tabulador")&&
							   !e.getKeyText(e.getKeyCode()).equals("Alt"))	
					 
						 if(!consulta) tabla.getModel().setValueAt("", tabla.getSelectedRow(), tabla.getSelectedColumn());
						 
				 
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				 
					//System.out.println("tecla presionada " + e.getKeyText(e.getKeyCode()));
					 if(e.getKeyText(e.getKeyCode()).equals("Intro") ||
							   e.getKeyText(e.getKeyCode()).equals("Arriba") ||
							   e.getKeyText(e.getKeyCode()).equals("Derecha") ||
							   e.getKeyText(e.getKeyCode()).equals("Izquierda") ||
							   e.getKeyText(e.getKeyCode()).equals("Abajo") ||
							   e.getKeyText(e.getKeyCode()).equals("Tabulador")){	
						// System.out.println("tecla presionada");
						   total_planillaL.setText("Total $" + Double.toString(
								   _controladorPagoDiario.calcular_ingresos_planilla(tabla)));
						   pIntegra.updateUI();
					   }
				 
				
					 
			}
        	
        	
        });
		
		
		tabla.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
		
		
		guardar.addActionListener(this);
		modificar.addActionListener(this);
		cancelar.addActionListener(this);
		cambiar_orden.addActionListener(this);
		imprimir.addActionListener(this);
		nuevo_comando.addActionListener(this);
		ver_fi.addActionListener(this);
		ver_fb.addActionListener(this);
		buscar_zona.addActionListener(this);
		
		
		
		this.setLayout(new BorderLayout());
		
		zonaTF.setFocusable(true);
		zonaTF.setBackground(new Color(183, 242, 113));
		
		zonaTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				zonaTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(_controladorZona.buscarZonaUsuario(zonaTF.getText())!=null){
					
					ZonaVO zVO = _controladorZona.buscarZonaUsuario(zonaTF.getText());
					
					EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.
							toString(zVO.getId_cobrador()));
					
					lCobradorZona.setText(eVO.getNombre() + " " + eVO.getApellido());
				}
				else{
					lCobradorZona.setText("");
					zonaTF.setText("");
				}
					
				
				zonaTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
		
		BH_ingresos bh = new BH_ingresos(guardar, modificar, cancelar, cambiar_orden,imprimir,nuevo_comando);
		
		pBarra.setLayout(new BorderLayout());
		//pBarra.add(bh);
		
		GridLayout gl = new GridLayout(0,2);
		
		JPanel p = new JPanel();
		p.setLayout(gl);
		JPanel pIntegraBarra = new JPanel();
		JLabel buscarL = new JLabel();
		buscarL.setText("Buscar zona");
		JLabel fecha_ingresoL = new JLabel();
		fecha_ingresoL.setText("Fecha de ingreso");
		JLabel fecha_busquedaL = new JLabel();
		fecha_busquedaL.setText("Fecha de consulta");
		JPanel p_fi = new JPanel();
		JPanel p_fb = new JPanel();
		
		Border bordefi = BorderFactory.createTitledBorder(null, "Fecha de ingreso", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.PLAIN,16), Color.BLACK);
		p_fi.setBorder(bordefi);
    	Border bordefb = BorderFactory.createTitledBorder(null, "Fecha de consulta", 
    	    	TitledBorder.CENTER, TitledBorder.TOP,
    	    	new Font("Arial",Font.PLAIN,16), Color.BLACK);
    	p_fb.setBorder(bordefb);
		
		p.add(zonaTF);
		p.add(buscar_zona);
		
		modelFI.setSelected(true);
		modelFB.setSelected(true);
		
        //model.setDate(20,04,2014);
  		Properties pr = new Properties();
  		pr.put("text.today", "Today");
  		pr.put("text.month", "Month");
  		pr.put("text.year", "Year");
        JDatePanelImpl datePanelFI = new JDatePanelImpl(modelFI, pr);
        JDatePanelImpl datePanelFB = new JDatePanelImpl(modelFB, pr);
       
        fecha_ingreso = new JDatePickerImpl(datePanelFI, new DateLabelFormatter());
        fecha_busqueda = new JDatePickerImpl(datePanelFB, new DateLabelFormatter());
		
        p_fi.add(fecha_ingreso);
        p_fi.add(ver_fi);
        p_fb.add(fecha_busqueda);
        p_fb.add(ver_fb);
        
		pIntegraBarra.add(buscarL);
		pIntegraBarra.add(p);
		pIntegraBarra.add(lCobradorZona);	
		pIntegraBarra.add(p_fi);
		pIntegraBarra.add(p_fb);
		
		 fi = fecha_ingreso.getJFormattedTextField();
		 fi.setFont(new Font("Arial", Font.PLAIN, 18));
		  fb= fecha_busqueda.getJFormattedTextField();
		 fb.setFont(new Font("Arial", Font.PLAIN, 18));
		
		pBarra.add(pIntegraBarra, BorderLayout.WEST);
		JPanel pProgress = new JPanel();
		
		 progressBar = new  JProgressBar(0,100);
	     progressBar.setValue(0);
	        progressBar.setStringPainted(true);
	    pProgress.add(progressBar);    
		pBarra.add(pProgress);
		pIntegra.setLayout(new BoxLayout(pIntegra, BoxLayout.Y_AXIS));
		//pImpresion.setLayout(new BoxLayout(pImpresion, BoxLayout.Y_AXIS));
		pImpresion.setLayout(new BorderLayout());
		pImpresion.setBackground(Color.white);
		
		/*pImpresion.add(pDatos);
		pImpresion.add(tabla);
		pImpresion.add(pplanilla);*/
		
		//scr = new JScrollPane(pImpresion, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						//JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		scr = new JScrollPane();
		//scr.setViewportView(pImpresion);
		Border borde0 = BorderFactory.createTitledBorder(null, "", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,20), Color.BLACK);
    			this.setBorder(borde0);
    			
    	this.setBackground(Color.white);
		
    	pIntegra.add(bh);
    	pIntegra.add(pBarra);
    	
    	pDatos.setBorder(new EmptyBorder(10,10,10,10));
		pDatos.setBackground(Color.WHITE);
		pDatos.add(cobradorL, BorderLayout.WEST);
		pDatos.add(fechaL, BorderLayout.EAST);
    	
    	total_planillaL.setFont(new Font("Arial", Font.BOLD, 16));
    	pplanilla.add(total_planillaL);
		pplanilla.setBackground(Color.WHITE);
		pImpresion.add(pDatos, BorderLayout.NORTH);
		
		pImpresion.add(scr, BorderLayout.CENTER);
		pImpresion.add(pplanilla, BorderLayout.SOUTH);
		
		/*pImpresion.setPreferredSize(new Dimension(800,1600));
		scr.setPreferredSize(new Dimension(800,800));*/
		scr.setViewportView(tabla);
		scr.getVerticalScrollBar().setUnitIncrement(16);
		//pIntegra.updateUI();
    	
    	
		/*this.add(pIntegra, BorderLayout.PAGE_START);
		this.add(pImpresion, BorderLayout.CENTER);
		this.add(pplanilla, BorderLayout.PAGE_END);*/
		
		JPanel pIntegraAll = new JPanel();
		pIntegraAll.setLayout(new BorderLayout());
		pIntegraAll.add(pIntegra, BorderLayout.PAGE_START);
		pIntegraAll.add(pImpresion, BorderLayout.CENTER);
		//pIntegraAll.add(pplanilla, BorderLayout.PAGE_END);
		pIntegraAll.setPreferredSize(new Dimension(800,800));
		
		this.add(pIntegraAll);
		//this.add(total_planillaL, BorderLayout.SOUTH);
		//this.fecha_ingreso.getComponent(1).setEnabled(false);
		alto_filas();
		habilita_datos(false, false, false, false, false,false,false, false,false,true,false, true);
	}
	
	public int getContador_pedidos(){
		
		return contador_pedidos;
	}
	
	public void iniciar(ArrayList<Object []> ar){
		 
		/*limpiar();
		
		int cantidad = 0;
		boolean first_show_cliente;
		
		cobranza_ideal = 0;
	
		if(ar!=null){
			
			for(Object [] o: ar){
				
				first_show_cliente= true;
				
				EmpleadoVO eVO = (EmpleadoVO)o[0];
				CobradorVO coVO = (CobradorVO)o[1];
				empleadoVO = eVO;
				ClienteVO cVO = (ClienteVO)o[2];
				zonaVO.setId_zona(cVO.getId_zona());
				DomicilioParticularVO dpVO = (DomicilioParticularVO)o[3];
				DomicilioComercialVO dcVO = (DomicilioComercialVO) o[4];
				LocalidadVO lVO = (LocalidadVO)o[5];
				
				
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

				String date = fecha_ingreso.getJFormattedTextField().getText();
				
				
				LocalDate localDate = LocalDate.parse(date, formatter);
				
				SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
				
				
				String fechaS = fecha_ingreso.getJFormattedTextField().getText();
				SimpleDateFormat dia = new SimpleDateFormat("EEEE");
				
				String diaS = "";
				
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
				
				
				
				
				
				JLabel cobrador_impresion = new JLabel();
				JLabel fecha_impresion = new JLabel();	
				cobrador_impresion.setText("Cobrador: " + eVO.getNombre() + " " + eVO.getApellido());
				fecha_impresion.setText(fechaS + " " + diaS);
				
				
				ArrayList<PedidosVO> ar_pedidos = _controladorPedido.buscarPedidos_porClienteTodos_estados(cVO.getDni());

			
				if(ar_pedidos!=null){
					this.ar_pedidos = ar_pedidos;
					for(PedidosVO pVO : ar_pedidos){
				
						
						if(pVO.getEstado_pedido().equals("activo") ||
								pVO.getEstado_pedido().equals("finalizado")){
							
							ArrayList<Pedido_articuloVO> ar_pedido_ar = _controladorPedido.
									buscarArticulos_porPedido(pVO.getN_pedido(), true);
							
							String plan = "";
							
							if(ar_pedido_ar!=null){
								
								
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
								
							}
							
				
							this.ar_nombres.add(plan);
							
							Object [] d = new Object [9];
							
				
							
							if(!pVO.getEstado_pedido().equals("pendiente entrega")){
								
								if(first_show_cliente){
									
									d[0] = cVO.getApellido() + " " + cVO.getNombre();
									d[1] = dcVO.getDomicilio() + " " + lVO.getLocalidad();
									first_show_cliente = false;
								}	
								else{
									
									d[0] = "";
									d[1] = "";
								}
								
								d[2] = dcVO.getHorario_atencion();
								
								d[3] = pVO.getN_pedido();
								
								String dias_cobranza_comp = "";
				
									
									String dias_cobranzaS = "";
									
									ArrayList<Pedidos_diasVO> arPa = _controladorPedido.
											buscarPedido_dias(pVO.getN_pedido());
									int cont = 0;
									
									if(arPa != null){
										
										for(Pedidos_diasVO pdVO : arPa){
											
											switch(pdVO.getN_dia()){
											
											case 1: cont++;
													dias_cobranzaS = dias_cobranzaS + " lun "; 
													dias_cobranza_comp = dias_cobranza_comp + "lunes";
											break;
											case 2: cont++;
													dias_cobranzaS = dias_cobranzaS + " mar ";
													dias_cobranza_comp = dias_cobranza_comp + ";martes";
											break;
											case 3: cont++;
													dias_cobranzaS = dias_cobranzaS + " mie";
													dias_cobranza_comp = dias_cobranza_comp + ";miércoles";
											break;
											case 4: cont++;
													dias_cobranzaS = dias_cobranzaS + " jue ";
													dias_cobranza_comp = dias_cobranza_comp + ";jueves";
											break;
											case 5: cont++;
													dias_cobranzaS = dias_cobranzaS + " vie";
													dias_cobranza_comp = dias_cobranza_comp + ";viernes";
											break;
											case 6: cont++;
													dias_cobranzaS = dias_cobranzaS + " sab";
													dias_cobranza_comp = dias_cobranza_comp + ";sábado";
											break;
											}
										}
									}
									
									
									if(cont==6) dias_cobranzaS="";
									
									d[4] = dias_cobranzaS;
							
								
								Refinanciacion_inDAO ref_inDAO = new Refinanciacion_inDAO();
								
								try {
									Refinanciacion_inVO rVO = ref_inDAO.buscarRef(pVO.getN_pedido());
										
									if(rVO!=null && rVO.getEstado())
										
										d[5] = rVO.getCuota_diaria();
										
									else
											
										d[5] = pVO.getCuota_diaria();
				
												
								} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
								}
						
								if(pVO.getEstado_deuda()<0)
								
									d[6] = pVO.getEstado_deuda();
								else
									d[6] = "";
								
								d[7] = "";
								d[8] = "";
								String dias_comp_array [] = dias_cobranza_comp.split(";");
								
								for(int i = 0; i < dias_comp_array.length; i++){
									
									if(dias_comp_array[i].equals(diaS)){
										cobranza_ideal += 
											Double.parseDouble(d[5].toString()) * 6/ arPa.size();
										break;
										
									}
								}
								
								if(pVO.getSaldo()<=pVO.getCuota_diaria()*6){
									
									d[8] = "saldo " + pVO.getSaldo();
									
								}
								
								if(pVO.getEstado_pedido().equals("finalizado")){
									
									d[8] = "finalizado";
								}
								
								t_model.addRow(d);
								cantidad += 1;
							}
						}
						
					}
				}
				
				cobradorL.setText("Zona " + cVO.getId_zona() + "      Cobrador: " + eVO.getNombre() + " " +
						eVO.getApellido() + " ID " + eVO.getId_usuario()+ " Monto ideal:$" + cobranza_ideal);
				fechaL.setText("Fecha:" + fechaS + " " + diaS );
				Font f = new Font("Arial", Font.PLAIN, 16);
				cobradorL.setFont(f);
				fechaL.setFont(f);
				
				pDatos_impresion.setBackground(Color.WHITE);
				pDatos_impresion.add(cobrador_impresion, BorderLayout.WEST);
				pDatos_impresion.add(fecha_impresion, BorderLayout.EAST);
				
				double cobr_round = round(cobranza_ideal,2);
				
				System.out.println("cobranza round" + cobr_round);
				
				datosImpresion.setText("Zona " + cVO.getId_zona() + "      Cobrador: " + eVO.getNombre() + " " +
						eVO.getApellido() + " ID " + eVO.getId_usuario()+ "            Monto ideal:$" + 
						cobr_round +
						"   Fecha:" + fechaS + " " + diaS );
				
				datosImpresion.setFont(new Font("Arial", Font.PLAIN, 10));
				
			}
			
			
		}
		
	
		
		for(int i = 0; i < tabla.getRowCount(); i++){
			
			t_model.isCellEditable(i, 7);
				
			tabla.getModel().setValueAt("", i, 7);
			
		}
		
		alto_filas();
		//tabla.setDefaultRenderer(Object.class, new FilaRenderer(8));
		total_planillaL.setText("Total $" + 
				Double.toString(_controladorPagoDiario.calcular_ingresos_planilla(tabla)));
    	
		
		tabla.getPreferredScrollableViewportSize().setSize(750,
				tabla.getRowHeight() * cantidad);
		
		//ancho_columnas();
		resizeColumns();
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);*/
		
		
		
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	class TableSwingWorker extends
	SwingWorker</*DefaultTableModel*/Void, Object[] > {
		//private final DefaultTableModel tableModel;
		private ArrayList<PedidosVO> ar;
		public TableSwingWorker(/*DefaultTableModel tableModel, ArrayList<PedidosVO> ar*/) {
			//this.tableModel = tableModel;
			//this.ar = ar;
		}

		@Override
		protected Void doInBackground() throws Exception {
			
			habilita_datos(false, false, false, false, false, false,false,false,false,false,false, false);
			
			_vista_principal.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			
			 Random random = new Random();
	          int progress = 0;
	            //Initialize progress property.
	         setProgress(0);
			
	         int cantidad = 0;
	 		boolean first_show_cliente;
	 		
	 		cobranza_ideal = 0;
	        
	 		for(int i = 0; i < tabla.getRowCount(); i++){
 				
 				t_model.isCellEditable(i, 8);
 					
 				tabla.getModel().setValueAt("", i, 8);
 				
 			}
	 		
	 		short id_zona = Short.parseShort(zonaTF.getText());
			
			//ArrayList<ClienteVO> ar =_controladorCliente.buscarClientes_porZona2(id_zona);
			ArrayList<DomicilioComercialVO> ar = _controladorDomCom.
					buscarDomicilio_comercial_porZona(id_zona);
	 		
	         if(ar!=null){
	 			
	        	ZonaVO zVO = _controladorZona.buscarZonaUsuario(zonaTF.getText());
	        	EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.toString(zVO.getId_cobrador()));
	        	zonaVO.setId_zona(zVO.getId_zona());
	        	 
	        	System.out.println("tamaño array " + ar.size());
	        	
	 			for(DomicilioComercialVO dc: ar){
	 				
	
	 				
	 				//ArrayList<DomicilioComercialVO> ar_dc = _controladorDomCom.
	 					//	buscarDomicilioComercial2(Integer.toString(cVO.getDni()));
	 				
	 				first_show_cliente= true;
	 				empleadoVO = eVO;
	 				
	 				
	 				//Date dt = new java.util.Date();
	 				//java.sql.Date fecha = new java.sql.Date(dt.getTime());
	 				
	 				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	 				String date = fecha_ingreso.getJFormattedTextField().getText();
	 				
	 				//convert String to LocalDate
	 				LocalDate localDate = LocalDate.parse(date, formatter);
	 				
	 				SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
	 				
	 				//String fechaS = new String(ft.format(fecha_ingreso.getJFormattedTextField().getText()));
	 				String fechaS = fecha_ingreso.getJFormattedTextField().getText();
	 				SimpleDateFormat dia = new SimpleDateFormat("EEEE");
	 				//String diaS = new String(dia.format(fecha_ingreso.getJFormattedTextField().getText()));
	 				String diaS = "";
	 				
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
	 				
	 				JLabel cobrador_impresion = new JLabel();
	 				fecha_impresion = new JLabel();	
	 				cobrador_impresion.setText("Cobrador: " + eVO.getNombre() + " " + eVO.getApellido());
	 				fecha_impresion.setText(fechaS + " " + diaS);
	 				
	 				ArrayList<PedidosVO> ar_pedidos_estados = 
	 						_controladorPedido.buscarPedidos_porClienteTodos_estados(dc.getDni());

	 			
	 				if(ar_pedidos_estados!=null){
	 					ar_pedidos = ar_pedidos_estados;
	 					for(PedidosVO pVO : ar_pedidos){
	 						//_controladorPedido.
	 						if(dc.getIdc()==pVO.getIdc() &&
	 								dc.getId_zona() == zVO.getId_zona()){
	 							
	 							if(pVO.getEstado_pedido().equals("activo") ||
		 								pVO.getEstado_pedido().equals("finalizado")){
		 							
		 							ArrayList<Pedido_articuloVO> ar_pedido_ar = _controladorPedido.
		 									buscarArticulos_porPedido(pVO.getN_pedido(), true);
		 							
		 							String plan = "";
		 							
		 						   if(ar_pedido_ar!=null){
		 								
		 								
		 								for(Pedido_articuloVO paVO : ar_pedido_ar){
		 									
		 									ArticulosVO aVO = _controladorArticulo.
		 											buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
		 									
		 									ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
		 									
		 									if(apVO!=null){
		 										
		 										plan = plan + "" + "Prestamo(" + apVO.getCodigo() + 
		 												")$" + Double.toString(apVO.getMonto());
		 									}
		 									/*else if(paVO.getCantidad()>1)
		 										plan = plan + " " + aVO.getNombre() +
		 										"(" + aVO.getCodigo() + ")" + "x" + paVO.getCantidad();*/
		 									else
		 										plan = plan + " " + aVO.getNombre()+
		 										"(" + aVO.getCodigo() + ")";
		 								}
		 								
		 							}
		 							
		 							//ar_idcomb.add(pVO.getId_combinacion());
		 							ar_nombres.add(plan);
		 							
		 							Object [] d = new Object [10];
		 							
		 							//SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		 							//String date =  new String(sf.format(datos[4]));
		 							
		 							if(!pVO.getEstado_pedido().equals("pendiente entrega")){
		 								
		 								if(first_show_cliente){
		 									
		 									ClienteVO cVO = _controladorCliente.buscarCliente(
		 											Integer.toString(dc.getDni()));
		 									
		 									d[0] = cVO.getApellido() + " " + cVO.getNombre();
		 									
		 									
		 					 				//if(ar_dc!=null){
		 					 					
		 					 					//for(DomicilioComercialVO dc : ar_dc){
		 					 						
		 					 						LocalidadVO lVO = _controladorLocalidad.buscarLocalidad
		 					 								(Integer.toString(dc.getId_localidad()));
		 					 						
		 					 							
		 					 							String loc ="";
		 					 							String ar_loc [] = lVO.getLocalidad().split(" ");
		 					 						
		 					 							for(int i = 0; i < ar_loc.length;i++){
		 					 								
		 					 								loc = loc + ar_loc[i].charAt(0);
		 					 							}
		 					 							
		 					 							d[1] = dc.getDomicilio() + " " + loc;// lVO.getLocalidad();
		 					 							
		 					 							ComercioVO comVO = _controladorDomCom
		 					 									.buscarComercio(Integer.toString(dc.getComercio()));
		 					 							
		 					 							if(comVO!=null)
		 					 							
		 					 								d[2] = comVO.getDescripcion()/*.substring(0, 4)*/;
		 					 							
		 					 							d[3] = dc.getHorario_atencion();
		 					 						
		 					 							
		 					 						//}
		 					 						
		 					 					//}
		 					 				//}
		 									
		 									
		 									first_show_cliente = false;
		 								}	
		 								else{
		 									
		 									d[0] = "";
		 									d[1] = "";
		 									d[2] = "";
		 									d[3] = "";
		 								}
		 								
		 								
		 								d[4] = pVO.getN_pedido();
		 								
		 								String dias_cobranza_comp = "";
		 									
		 									String dias_cobranzaS = "";
		 									
		 									ArrayList<Pedidos_diasVO> arPa = _controladorPedido.
		 											buscarPedido_dias(pVO.getN_pedido());
		 									int cont = 0;
		 									
		 									if(arPa != null){
		 										
		 										for(Pedidos_diasVO pdVO : arPa){
		 											
		 											switch(pdVO.getN_dia()){
		 											
		 											case 1: cont++;
		 													dias_cobranzaS = dias_cobranzaS + " l "; 
		 													dias_cobranza_comp = dias_cobranza_comp + "lunes";
		 											break;
		 											case 2: cont++;
		 													dias_cobranzaS = dias_cobranzaS + " m ";
		 													dias_cobranza_comp = dias_cobranza_comp + ";martes";
		 											break;
		 											case 3: cont++;
		 													dias_cobranzaS = dias_cobranzaS + " M ";
		 													dias_cobranza_comp = dias_cobranza_comp + ";miércoles";
		 											break;
		 											case 4: cont++;
		 													dias_cobranzaS = dias_cobranzaS + " j ";
		 													dias_cobranza_comp = dias_cobranza_comp + ";jueves";
		 											break;
		 											case 5: cont++;
		 													dias_cobranzaS = dias_cobranzaS + " v";
		 													dias_cobranza_comp = dias_cobranza_comp + ";viernes";
		 											break;
		 											case 6: cont++;
		 													dias_cobranzaS = dias_cobranzaS + " s";
		 													dias_cobranza_comp = dias_cobranza_comp + ";sábado";
		 											break;
		 											}
		 										}
		 									}
		 									
		 									if(cont==6) dias_cobranzaS="";
		 									
		 									d[5] = dias_cobranzaS;
		 								//}
		 								
		 								Refinanciacion_inDAO ref_inDAO = new Refinanciacion_inDAO();
		 								
		 								try {
		 									Refinanciacion_inVO rVO = ref_inDAO.buscarRef(pVO.getN_pedido());
		 										
		 									if(rVO!=null && rVO.getEstado())
		 										
		 										d[6] = rVO.getCuota_diaria();
		 										
		 									else
		 											
		 										d[6] = pVO.getCuota_diaria();
		 				
		 												
		 								} catch (SQLException e) {
		 										// TODO Auto-generated catch block
		 										e.printStackTrace();
		 								}
		 						
		 								if(pVO.getEstado_deuda()<0)
		 								
		 									d[7] = pVO.getEstado_deuda();
		 								else
		 									d[7] = "";
		 								
		 								d[8] = "";
		 								d[9] = "";
		 								String dias_comp_array [] = dias_cobranza_comp.split(";");
		 								
		 								for(int i = 0; i < dias_comp_array.length; i++){
		 									
		 									if(dias_comp_array[i].equals(diaS)){
		 										cobranza_ideal += 
		 											Double.parseDouble(d[6].toString()) * 6/ arPa.size();
		 										break;
		 										
		 									}
		 								}
		 								
		 								if(pVO.getSaldo()<=pVO.getCuota_diaria()*6){
		 									
		 									d[9] = "saldo " + pVO.getSaldo();
		 									
		 								}
		 								
		 								if(pVO.getEstado_pedido().equals("finalizado")){
		 									
		 									d[9] = "control";
		 								}
		 								publish((Object[]) d);
		 								//}
		 								try {
		 									Thread.sleep(random.nextInt(1000));
		 								} catch (InterruptedException ignore) {}
		 								//Make random progress.
		 								progress += random.nextInt(10);
		 								setProgress(Math.min(progress, 100));	
		 							}
		 						}
	 							
	 						}
	 						
	 						
	 						
	 					}
	 					
	 				}
	 				
	 				double cobranza_round = round(cobranza_ideal,2);
	 				
	 				cobradorL.setText("Zona " + zVO.getId_zona() + "      Cobrador: " + eVO.getNombre() + " " +
	 						eVO.getApellido() + " ID " + eVO.getId_usuario()+ " Monto ideal:$" + cobranza_round);
	 				fechaL.setText("Fecha:" + fechaS + " " + diaS );
	 				Font f = new Font("Arial", Font.PLAIN, 16);
	 				cobradorL.setFont(f);
	 				fechaL.setFont(f);
	 				
	 				pDatos_impresion.setBackground(Color.WHITE);
	 				pDatos_impresion.add(cobrador_impresion, BorderLayout.WEST);
	 				pDatos_impresion.add(fecha_impresion, BorderLayout.EAST);
	 				
	 				datosImpresion.setText("Zona " + zVO.getId_zona() + "      Cobrador: " + eVO.getNombre() + " " +
	 						eVO.getApellido() + " ID " + eVO.getId_usuario()+ "            Monto ideal:$" + cobranza_round +
	 						"   Fecha:" + fechaS + " " + diaS );
	 				
	 				datosImpresion.setFont(new Font("Arial", Font.PLAIN, 10));
	 				//Sleep for up to one second.
	 				zonaS = "Zona " + zVO.getId_zona();
	 				zona_descripcion =  "Cobrador: " + eVO.getNombre() + " " +
 						eVO.getApellido() + " ID " + eVO.getId_usuario()+ "            Monto ideal:$" + cobranza_round +
 						"   Fecha:" + fechaS + " " + diaS;
	 					
	 			}
	 			
	 			
	 			//tabla.setDefaultRenderer(Object.class, new FilaRenderer(8));
	 			total_planillaL.setText("Total $" + 
	 					Double.toString(_controladorPagoDiario.calcular_ingresos_planilla(tabla)));
	 	    	
	 			
	 			/*tabla.getPreferredScrollableViewportSize().setSize(750,
	 					tabla.getRowHeight() * cantidad);*/
	 			
	 			//ancho_columnas();
	 			resizeColumns();
	 			//tabla.setModel(tableModel);
	    
	        }	
	         _vista_principal.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	         habilita_datos(false, false, false, false, false, false,true,false,false,false,true, true);
	         progressBar.setValue(100);
			return null;
			
		}

		@Override
		protected void process(java.util.List<Object []> chunks) {
			for (Object[] row : chunks) {
				t_model.addRow(row);
				
				tabla.setRowHeight(40);
			}
		}
	}
	
	public void iniciar_busqueda(ArrayList<Pago_diarioVO> ar){
		 
		limpiar();
		
		int cantidad = 0;
		boolean first_show_cliente;
		
		cobranza_ideal = 0;
		
		Object [] dat = new Object [9];
		if(ar!=null){
			first_show_cliente= true;
			int aux_dni;
			
			int dni=0;
			int zona = 0;
			
			short id_zona = Short.parseShort(zonaTF.getText());
	 			
	        	ZonaVO zVO = _controladorZona.buscarZonaUsuario(zonaTF.getText());
	        	EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.toString(zVO.getId_cobrador()));
	        	zonaVO.setId_zona(zVO.getId_zona());
	        	empleadoVO = eVO;
	        	zonaVO.setId_zona(zVO.getId_zona());
	        	int zona_aux = 0;	
			
			for(Pago_diarioVO pgVO: ar){
				
				ClienteVO cVO = _controladorCliente.buscarCliente_porNPedido(pgVO.getN_pedido());
				
				PedidosVO pVO = _controladorPedido.buscarPedido_porNpedido(pgVO.getN_pedido());
				
				ArrayList<DomicilioComercialVO> ar_dc = _controladorDomCom.
 						buscarDomicilioComercial2(Integer.toString(cVO.getDni()));
				
				if(cantidad==0) zona = zVO.getId_zona();
				
				//Date dt = new java.util.Date();
				//java.sql.Date fecha = new java.sql.Date(dt.getTime());
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

				String date = fecha_busqueda.getJFormattedTextField().getText();
				
				//convert String to LocalDate
				LocalDate localDate = LocalDate.parse(date, formatter);
				
				SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
				
				//String fechaS = new String(ft.format(fecha_ingreso.getJFormattedTextField().getText()));
				String fechaS = fecha_busqueda.getJFormattedTextField().getText();
				SimpleDateFormat dia = new SimpleDateFormat("EEEE");
				//String diaS = new String(dia.format(fecha_ingreso.getJFormattedTextField().getText()));
				String diaS = "";
				
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
				
				
				JLabel cobrador_impresion = new JLabel();
				JLabel fecha_impresion = new JLabel();	
				cobrador_impresion.setText("Cobrador: " + eVO.getNombre() + " " + eVO.getApellido());
				fecha_impresion.setText(fechaS + " " + diaS);
				cobrador_impresion.setFont(new Font("Arial", Font.PLAIN, 12));
				fecha_impresion.setFont(new Font("Arial", Font.PLAIN, 12));	
				//pDatos_impresion.setPreferredSize(new Dimension(100,50));
				pDatos_impresion.setBorder(new EmptyBorder(10,10,10,10));
				pDatos_impresion.setForeground(Color.black);
				//pDatos_impresion.setBackground(Color.WHITE);
				pDatos_impresion.add(cobrador_impresion, BorderLayout.WEST);
				pDatos_impresion.add(fecha_impresion, BorderLayout.EAST);
				zonaS = "Zona " + zVO.getId_zona();	
				datosImpresion.setText("Zona " + zVO.getId_zona() + "      Cobrador: " + eVO.getNombre() + " " +
							eVO.getApellido() + " ID " + eVO.getId_usuario() + 
				  "            Fecha:" +
							fechaS + " " + diaS);
				datosImpresion.setFont(new Font("Arial", Font.PLAIN, 10));
						//_controladorPedido.
						Object [] d = new Object [10];
						
						//SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
						//String date =  new String(sf.format(datos[4]));
						
						//if(!_pedidosVO.getEstado_pedido().equals("pendiente entrega")){
							
							
							if(cVO.getDni()!=dni) first_show_cliente = true;
							
						
							if(first_show_cliente){
								
								dni = cVO.getDni();
								
								d[0] = cVO.getApellido() + " " + cVO.getNombre();
								
								if(ar_dc!=null){
					 					
					 					for(DomicilioComercialVO dc : ar_dc){
					 						
					 						LocalidadVO lVO = _controladorLocalidad.buscarLocalidad
					 								(Integer.toString(dc.getId_localidad()));
					 						
					 						if(dc.getIdc()==pVO.getIdc() &&
					 								dc.getId_zona() == zVO.getId_zona()){
					 							
					 							zona_aux = dc.getId_zona();
					 							
					 							String loc ="";
 					 							String ar_loc [] = lVO.getLocalidad().split(" ");
 					 						
 					 							for(int i = 0; i < ar_loc.length;i++){
 					 								
 					 								loc = loc + ar_loc[i].charAt(0);
 					 							}
					 							
					 							d[1] = dc.getDomicilio() + " " + loc/*lVO.getLocalidad()*/;
					 							
					 							ComercioVO comVO = _controladorDomCom
 					 									.buscarComercio(Integer.toString(dc.getComercio()));
 					 							
 					 							if(comVO!=null)
 					 							
 					 								d[2] = comVO.getDescripcion();
					 							
					 							d[3] = dc.getHorario_atencion();
					 							
					 						}
					 						
					 					}
					 				}
								
								
								first_show_cliente = false;
							}	
							else{
								
								//zona_aux = zVO.getId_zona();
								
								d[0] = "";
								d[1] = "";
								d[2] = "";
							}
									
							d[4] = pgVO.getN_pedido();
							
							d[6] = pgVO.getCuota();
							
							d[7] = "";
							
							d[8] = pgVO.getImporte();
							
							ArrayList<Pedidos_diasVO> arPa = _controladorPedido.
									buscarPedido_dias(pgVO.getN_pedido());
							int cont = 0;
							String dias_cobranza_comp = "";
						
							String dias_cobranzaS = "";
							if(arPa != null){
								
								for(Pedidos_diasVO pdVO : arPa){
									
									switch(pdVO.getN_dia()){
									
									case 1: cont++;
											dias_cobranzaS = dias_cobranzaS + " l "; 
											dias_cobranza_comp = dias_cobranza_comp + "lunes";
											
									break;
									case 2: cont++;
											dias_cobranzaS = dias_cobranzaS + " m ";
											dias_cobranza_comp = dias_cobranza_comp + ";martes";
											
									break;
									case 3: cont++;
											dias_cobranzaS = dias_cobranzaS + " M ";
											dias_cobranza_comp = dias_cobranza_comp + ";miércoles";
											
									break;
									case 4: cont++;
											dias_cobranzaS = dias_cobranzaS + " j ";
											dias_cobranza_comp = dias_cobranza_comp + ";jueves";
											
									break;
									case 5: cont++;
											dias_cobranzaS = dias_cobranzaS + " v";
											dias_cobranza_comp = dias_cobranza_comp + ";viernes";
											
									break;
									case 6: cont++;
											dias_cobranzaS = dias_cobranzaS + " s";
											dias_cobranza_comp = dias_cobranza_comp + ";sábado";
											
									break;
									}
								}
							}
							
							
							/*if(_controladorPedido.buscarCombinacion(Integer.toString(pgVO.getId_combinacion()))!=null){
								
								Combinacion_diasVO _combinacion_diasVO =_controladorPedido.buscarCombinacion
										(Integer.toString(pgVO.getId_combinacion()));
								
								
								if(_combinacion_diasVO.getLunes()){
									cont++;
									dias_cobranzaS = dias_cobranzaS + " l "; 
								}
								if(_combinacion_diasVO.getMartes()){
									cont++;
									dias_cobranzaS = dias_cobranzaS + " m ";
								}
								if(_combinacion_diasVO.getMiercoles()){
									cont++;
									dias_cobranzaS = dias_cobranzaS + " M";
								}
								if(_combinacion_diasVO.getJueves()){
									cont++;
									dias_cobranzaS = dias_cobranzaS + " j ";
								}
								if(_combinacion_diasVO.getViernes()){
									cont++;
									dias_cobranzaS = dias_cobranzaS + " v";
								}
								if(_combinacion_diasVO.getSabado()){
									cont++;
									dias_cobranzaS = dias_cobranzaS + " s";
								}
								
								if(cont==6) dias_cobranzaS="";
								
								d[3] = dias_cobranzaS;
							}*/
							
							if(cont==6) dias_cobranzaS="";
							
							d[5] = dias_cobranzaS;
					
							
							
							String dias_comp_array [] = dias_cobranza_comp.split(";");
							
							for(int i = 0; i < dias_comp_array.length; i++){
								
								if(dias_comp_array[i].equals(diaS)){
									cobranza_ideal += 
										Double.parseDouble(d[6].toString()) * 6/ arPa.size();
									break;
									
								}
							}
							
							d[9] = "";
							
							if(zona_aux==zVO.getId_zona())
							
								t_model.addRow(d);
							
							
							cantidad += 1;
							
						//}
						dat=d;
						
						cobradorL.setText("Zona " + zona/*cVO.getId_zona()*/ + "      Cobrador: " + eVO.getNombre() + " " +
								eVO.getApellido() + " ID " + eVO.getId_usuario() + 
								"Monto ideal:$" + cobranza_ideal);
						fechaL.setText("Fecha:" + fechaS + " " + diaS);
						Font f = new Font("Arial", Font.PLAIN, 16);
						cobradorL.setFont(f);
						fechaL.setFont(f);
					}	
			
			
		}
		
		
		//tabla.setModel(t_model);
	
		for(int i = 0; i < tabla.getRowCount(); i++){
			
			t_model.isCellEditable(i, 8);
				
			//tabla.getModel().setValueAt("", i, 5);
			
		}
		
		alto_filas();
		
		total_planillaL.setText("Total $" + 
				Double.toString(_controladorPagoDiario.calcular_ingresos_planilla(tabla)));
		
		tabla.getPreferredScrollableViewportSize().setSize(750,
				tabla.getRowHeight() * cantidad);
		
		//ancho_columnas();
		resizeColumns();
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		//for(int i = 0; i < tabla.getColumnCount(); i ++)
			
			//tabla.getColumnModel().getColumn(i).setCellRenderer(cent);
		
		
		/*pplanilla.add(total_planillaL);
		pplanilla.setBackground(Color.WHITE);
		pImpresion.add(pDatos, BorderLayout.NORTH);
		
		pImpresion.add(scr, BorderLayout.CENTER);
		pImpresion.add(pplanilla, BorderLayout.SOUTH);
		
		scr.setViewportView(tabla);
		
		pIntegra.updateUI();*/
		
		
		
	}
	
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 15; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}

	private void resizeColumns() {
	    int tW = 750;
	    TableColumn column;
	    TableColumnModel jTableColumnModel = tabla.getColumnModel();
	    int cantCols = jTableColumnModel.getColumnCount();
	    for (int i = 0; i < cantCols; i++) {
	        column = jTableColumnModel.getColumn(i);
	        int pWidth = Math.round(columnWidthPercentage[i] * tW);
	        column.setPreferredWidth(pWidth);
	    }
	}
	
	public static void addDeletePreviousOnEditBehavior(final JComponent field) {

    field.addFocusListener(new FocusListener() {

        @Override
        public void focusGained(FocusEvent fe) {
            //field.putClientProperty(DELETE_ON_EDIT, true);
 
        }

        @Override
        public void focusLost(FocusEvent fe) {
        }
    });

    field.addKeyListener(new KeyListener() {

        @Override
        public void keyTyped(KeyEvent ke) {
        }

        
       public void keyPressed(KeyEvent ke) {
           /* if ((!(ke.isActionKey()
                    || isSpecial(ke.getKeyCode())))
                    && ((Boolean) field.getClientProperty(DELETE_ON_EDIT))) {
                System.out.println("Key:" + ke.getKeyCode() + "/" + ke.getKeyChar());
                field.putClientProperty(DELETE_ON_EDIT, false);
                if (field instanceof JFormattedTextField) {
                    ((JFormattedTextField) field).setValue(null);
                }
                if (field instanceof JTextComponent) {
                    ((JTextComponent) field).setText(null);
                }

            }*/
        }

        @Override
        public void keyReleased(KeyEvent ke) {
           // do nothing
        }
    });
}
	
	 public void setPedidosVO(PedidosVO _pedidosVO){
	    	
	    	this._pedidoVO = _pedidosVO;
	    }
	    
	    public void setArticulosVO(ArticulosVO _articulosVO){
	    	
	    	//this._articulosVO = _articulosVO;
	    }

	    public void setComboVO(Pedido_articuloVO _combosVO){
		
	    	this._combosVO = _combosVO;
	    }
	    
	    public void setRefinanciacion_exVO(Refinanciacion_exVO _refVO){
	    	
	    	this._refVO = _refVO;
	    }
	    
	    public void setRefinanciacion_inVO(Refinanciacion_inVO _ref_inVO){
	    	
	    	this._ref_inVO = _ref_inVO;
	    }
	    
	    public PedidosVO getPedidosVO(){
	    	
	    	return _pedidoVO;
	    }
	    
	    public ArticulosVO getArticulosVO(){
	    	
	    	return _articulosVO;
	    }
	    
	    public Refinanciacion_exVO getRefinanciacion_exVO(){
	    	
	    	return _refVO;
	    }
	    
	    public Refinanciacion_inVO getRefinanciacion_inVO(){
	    	
	    	return _ref_inVO;
	    }
	    
	    public void setUsuarioVO(UsuariosVO uVO){
	    	
	    	this._usuariosVO = uVO;
	    }
	    
	    public UsuariosVO getUsuarioVO(){
	    	
	    	return this._usuariosVO;
	    }
	    
	    public void setAuxArticulosVO(ArticulosVO aux_articulo){
	    	
	    	this.aux_articulo = aux_articulo;
	    }
	    

	    public void setAuxComboVO(Pedido_articuloVO _combosVO){
		
	    	this.aux_combo = _combosVO;
	    }
	    
	    public Refinanciacion_exVO getAuxRef(){
	    	
	    	return aux_ref;
	    }
	    
	    public void setAuxRef(Refinanciacion_exVO _refVO){
	    	
	    	this.aux_ref = _refVO;
	    }
	    
	    public Refinanciacion_inVO getAuxRef_in(){
	    	
	    	return aux_ref_in;
	    }
	    
	    public Pedido_articuloVO getAuxComboVO(){
			
			return aux_combo;
		}


		public void setAuxRef_in(Refinanciacion_inVO _ref_inVO){
	    	
	    	this.aux_ref_in = _ref_inVO;
	    }
	    
	    public ArticulosVO getAuxArticulosVO(){
	    	
	    	return aux_articulo;
	    }

	    public Pedido_articuloVO getComboVO(){
		
	    	return _combosVO;
	    }
	    
	    public void setPrestamoVO(ArticulosPVO _prestamoVO){
	    	
	    	this._prestamoVO = _prestamoVO;
	    }
	    
	    public ArticulosPVO getPrestamoVO(){
	    	
	    	return _prestamoVO;
	    }
	    
	    public void setAuxPrestamoVO(ArticulosPVO aux_prestamo){
	    	
	    	this.aux_prestamo = aux_prestamo;
	    }
	    
	    public ArticulosPVO getAuxPrestamoVO(){
	    	
	    	return aux_prestamo;
	    }
	    
	    public void setUsuariosVO(UsuariosVO _usuariosVO){
		
	    	this._usuariosVO = _usuariosVO;
	    }
	    
	    public void setBarra_herramientasPedidos(Barra_herramientasPedidos bhp2){
			
			//this.bhp2 = bhp2;
		}
	    
	    public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vp){
			
			this.vp = vp;
		}
	    
	    public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
			
			this._busqueda_entities = _busqueda_entities;
		}
	    
	    
	    public void setVistaPrincipal(VistaPrincipal _vista_principal){
			
			this._vista_principal = _vista_principal;
		}
	    
		public void setControladorCliente(ControladorCliente _controladorCliente){
			
			this._controladorCliente = _controladorCliente;
		}
		
		public void setControladorDomicilioParticular(ControladorDomicilioParticular _controladorDomPart){
			
			this._controladorDomPart = _controladorDomPart;
		}
		
		public void setControladorDomicilioComercial(ControladorDomicilioComercial _controladorDomCom){
			
			this._controladorDomCom = _controladorDomCom;
		}
		
		public void setControladorZona(ControladorZona _controladorZona){
			
			this._controladorZona = _controladorZona;
		}

		public boolean getOpcionArticulo(){
			
			return opcion_articulo;
		}

		public void setControladorLocalidad(ControladorLocalidad _controladorLocalidad){
			
			this._controladorLocalidad = _controladorLocalidad;
		}
		
		public void setControladorVendedor(ControladorVendedor _controladorVendedor){
		
			this._controladorVendedor = _controladorVendedor;
		}
		
		public void setControladorPedido(ControladorPedidos _controladorPedido){
			
			this._controladorPedido = _controladorPedido;
		}
		
		public void setControladorPagoDiario(ControladorPagoDiario _controladorPagoDiario){
			
			this._controladorPagoDiario = _controladorPagoDiario;
		}
		
		public void setControladorArticulo(ControladorArticulo _controladorArticulo){
			
			this._controladorArticulo = _controladorArticulo;
		}
		
		public void setControladorPrestamo(ControladorPrestamo _controladorPrestamo){
			
			this._controladorPrestamo = _controladorPrestamo;
		}
	
		
		public void setControladorPrest(ControladorArticulo _controladorArticulo){
			
			this._controladorArticulo = _controladorArticulo;
		}
		
		public void setControladorCombo(ControladorCombo _controladorCombo){
			
			this._controladorCombo = _controladorCombo;
		}
		
		public void setControladorRefinanciacion_ex(ControladorRefinanciacion_ex _controladorRef_ex){
			
			this._controladorRef_ex = _controladorRef_ex;
		}
		
		public void setControladorRefinanciacion_in(ControladorRefinanciacion_in _controladorRef_in){
			
			this._controladorRef_in = _controladorRef_in;
		}
		
		public void setControladorMonto_trasladado(ControladorMonto_trasladado _controladorMonto_t){
			
			this._controladorMonto_t = _controladorMonto_t;
		}
		
		public void setControladorObservaciones(ControladorObservaciones _controladorObservaciones){
			
			this._controladorObservaciones = _controladorObservaciones;
		}
		
		public void setControladorEmpleado(ControladorEmpleado _controladorEmpleado){
			
			this.controladorEmpleado = _controladorEmpleado;
		}
		
		public void setVistaCombo(VistaNewObjetoVenta vista_combo){
			
			this.vista_combo = vista_combo;
		}
		
		public void setVistaPrestamo(VistaPrestamo vista_prestamo){
			
			this.vista_prestamo = vista_prestamo;
		}
		
		
		
		public void setRef_ex_boolean(boolean r){
			
			 refinanciacion_ex = r;
		}
		
		public void setRef_in_boolean(boolean r){
			
			 refinanciacion_in = r;
		}
		
		
		public boolean getOpcionPrestamo(){
			
			return opcion_prestamo;
		}
		
		public JDatePickerImpl getFecha_ingreso(){
			
			return fecha_ingreso;
		}
		
		public void setControladorPedidos(ControladorPedidos _controladorPedido){
			
			this._controladorPedido = _controladorPedido;
		}
		public void setControladorUsuario(ControladorUsuario _controladorUsuario){
			
			this._controladorUsuario = _controladorUsuario;
		}
		
		public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
			
			this.vista_buscar_cliente = vista_buscar_cliente;
		}
		
		public void setVistaRefinanciacion(VistaRefinanciacion vf){
			
			this.vf = vf;
		}
		
		public void setVistaRefinanciacion_in(VistaRefinanciacion_in vf_in){
			
			this.vf_in = vf_in;
		}
		
		public void setVistaMonto_t(VistaMonto_t mt){
			
			this.mt = mt;
		}
		
		public void setVistaBuscarObservaciones(VistaObservacionesPedido obser){
			
			this.obser = obser;
		}
		
		public void setVista_pagos_porPedido(Vista_pagos_porPedido vpp){
			
			this.v_pagos = vpp;
		}
		
		public JTextField getZonaTF(){
			
			return zonaTF;
		}
		
		public JLabel getCobradorZonaL(){
			
			return this.lCobradorZona;
		}
		
		public double  getMonto_ideal(){
			
			return this.cobranza_ideal;
		}
		
		public JTable getTabla(){
			
			return tabla;
		}
		
		public ZonaVO getZonaVO(){
			
			return zonaVO;
		}
		
		public Pago_diarioVO getPago_diarioVO(){
			
			return _pgVO;
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		this._busqueda_entities.setPanel("vista_ingresos");
		
		if(e.getSource()==guardar){
			
			if(!_controladorPagoDiario.ingresarPagosUsuario(tabla)){
				
				Mensajes.getMensaje_importeError();
			}
			else{
				
				if(!editar_planilla){
					
					String fecha_ingresoS =fecha_ingreso.getJFormattedTextField().getText();
					DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
					Date dateFN = new Date();
					try {
						dateFN = format.parse(fecha_ingresoS);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					java.sql.Date fecha_ingresoDate = new java.sql.Date(dateFN.getTime());
					
					java.util.Date d_hoy = new java.util.Date();
					java.sql.Date hoy = new java.sql.Date(d_hoy.getTime());
					
					System.out.println(fecha_ingresoDate + " " + hoy);
					
					LocalDate l1 = fecha_ingresoDate.toLocalDate();
					LocalDate l2 = hoy.toLocalDate();
					if(l1.isEqual(l2)) {
						
						_pgVO.setFecha(fecha_ingresoDate);
						
						instanciar();
					}
					else {
						
						Mensajes.getMensaje_fecha_ingreso();
						
					}	
					
				}
				else {
					
					cargaPagoDiarioVO(fecha_busqueda);
				}
				
				
			}
				System.out.println("sin error ingreso importe");
		}
		
		if(e.getSource()==cambiar_orden){
			
			VistaReordenarPlanilla vrp = new VistaReordenarPlanilla();
			 ControladorCliente _controladorCliente = new ControladorCliente();
				ControladorDomicilioParticular _controladorDomPart = new ControladorDomicilioParticular();
				ControladorDomicilioComercial _controladorDomCom = new ControladorDomicilioComercial();
				ControladorUsuario _controladorUsuario = new ControladorUsuario();
				ControladorZona _controladorZona = new ControladorZona();
				ControladorLocalidad _controladorLocalidad = new ControladorLocalidad();
				ControladorVendedor _controladorVendedor = new ControladorVendedor();
				ControladorPedidos _controladorPedido = new ControladorPedidos();
				ControladorPagoDiario _controladorPagoDiario = new ControladorPagoDiario();
				ControladorArticulo _controladorArticulo = new ControladorArticulo();
				 ControladorPrestamo _controladorPrestamo = new ControladorPrestamo();
				 LogicaPrestamo logica_prestamo = new LogicaPrestamo();
				ControladorCombo _controladorCombo = new ControladorCombo();
				ControladorRefinanciacion_ex _controladorRef_ex = new ControladorRefinanciacion_ex();
				ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
				ControladorMonto_trasladado _controladorMonto_t = new ControladorMonto_trasladado();
				ControladorDespacho_diario cdp = new ControladorDespacho_diario();
				LogicaMonto_trasladado _logicaMonto_t = new LogicaMonto_trasladado();
				//ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
				LogicaCliente _logica_cliente = new LogicaCliente();
				LogicaDomicilioParticular _logica_dom_part = new LogicaDomicilioParticular();
				LogicaDomicilioComercial _logica_dom_com = new LogicaDomicilioComercial();
				LogicaUsuario _logicaUsuario = new LogicaUsuario();
				LogicaZona _logica_zona = new LogicaZona();
				LogicaLocalidad _logica_localidad = new LogicaLocalidad();
				LogicaVendedor _logica_vendedor = new LogicaVendedor();
				LogicaPedido _logica_pedido = new LogicaPedido();
				LogicaPagoDiario _logicaPagoDiario = new LogicaPagoDiario();
				LogicaArticulo _logica_articulo = new LogicaArticulo();
				LogicaCombo _logica_combo = new LogicaCombo();
				LogicaRefinanciacion_ex _logica_ref_ex = new LogicaRefinanciacion_ex();
				LogicaRefinanciacion_in _logica_ref_in = new LogicaRefinanciacion_in();
				LogicaDespacho log_despacho = new LogicaDespacho();
				VistaAltaCliente vista_alta_cliente = new VistaAltaCliente();
				VistaBuscarCliente vista_buscar_cliente = new VistaBuscarCliente();
				VistaIngresos vista_ingresos = new VistaIngresos();
				VistaBuscarUsuario _vista_buscar_usuario = new VistaBuscarUsuario();
				BusquedaEntities busqueda_entities = new BusquedaEntities();
				//Barra_herramientasPedidos bhp = new Barra_herramientasPedidos();
				VistaBuscarPedidos_porClientes vpc = new VistaBuscarPedidos_porClientes();
				//VistaNewObjetoVenta vista_combo = new VistaNewObjetoVenta();
				VistaRefinanciacion vf = new VistaRefinanciacion();
				VistaRefinanciacion_in vf_in = new VistaRefinanciacion_in();
				VistaMonto_t mt = new VistaMonto_t();
				
				_controladorPrestamo.setLogicaPrestamo(logica_prestamo);
		
				_vista_buscar_usuario.setControladorUsuario(_controladorUsuario);
				_controladorUsuario.setVistaBuscarUsuario(_vista_buscar_usuario);
				_controladorUsuario.setLogicaUsuario(_logicaUsuario);
				_logicaUsuario.setControladorUsuario(_controladorUsuario);
				
				vista_alta_cliente.setControladorCliente(_controladorCliente);
				vista_alta_cliente.setControladorDomicilioParticular(_controladorDomPart);
				vista_alta_cliente.setControladorDomicilioComercial(_controladorDomCom);
				vista_alta_cliente.setControladorZona(_controladorZona);
				vista_alta_cliente.setControladorLocalidad(_controladorLocalidad);
				vista_alta_cliente.setControladorVendedor(_controladorVendedor);
				vista_alta_cliente.setBusquedaEntities(busqueda_entities);
				
				_logica_cliente.setControladorCliente(_controladorCliente);
				_logica_cliente.setBusquedaEntities(busqueda_entities);
				_logica_cliente.setVistaBuscarCliente(vista_buscar_cliente);
				_logica_dom_part.setControladorDomicilioParticular(_controladorDomPart);
				_logica_dom_part.setVistaBuscarCliente(vista_buscar_cliente);
				_logica_dom_com.setControladorDomicilioComercial(_controladorDomCom);
				_logica_dom_com.setVistaBuscarCliente(vista_buscar_cliente);
				_logica_articulo.setBusquedaEntities(busqueda_entities);
				_logica_combo.setBusquedaEntities(busqueda_entities);
				
				vista_buscar_cliente.setControladorCliente(_controladorCliente);
				vista_buscar_cliente.setControladorDomicilioParticular(_controladorDomPart);
				vista_buscar_cliente.setControladorDomicilioComercial(_controladorDomCom);
				vista_buscar_cliente.setControladorZona(_controladorZona);
				vista_buscar_cliente.setControladorVendedor(_controladorVendedor);
				vista_buscar_cliente.setBusquedaEntities(busqueda_entities);
				vista_buscar_cliente.setControladorLocalidad(_controladorLocalidad);
				vista_buscar_cliente.setControladorPedido(_controladorPedido);
				vista_buscar_cliente.setVistaBuscarPedidos_porClientes(vpc);
				
				vista_buscar_cliente.setControladorRefinanciacion_ex(_controladorRef_ex);
				vista_buscar_cliente.setControladorRefinanciacion_in(_controladorRef_in);
					//vpc.setControladorRefinanciacion_in(_controladorRef_in);
				
				vista_buscar_cliente.setControladorPagoDiario(_controladorPagoDiario);
				vista_buscar_cliente.setControladorArticulo(_controladorArticulo);
				//vista_buscar_cliente.setVistaCombo(vista_combo);
				vista_buscar_cliente.setControladorCombo(_controladorCombo);
				vista_buscar_cliente.setVistaRefinanciacion(vf);
				vista_buscar_cliente.setVistaRefinanciacion_in(vf_in);
				vista_buscar_cliente.setVistaMonto_t(mt);
				
				_controladorCliente.setVistaBuscarCliente(vista_buscar_cliente);
				_controladorDomPart.setVistaBuscarCliente(vista_buscar_cliente);
				_controladorDomCom.setVistaBuscarCliente(vista_buscar_cliente);
				
				_controladorCliente.setLogicaCliente(_logica_cliente);
				_controladorCliente.setVistaAltaCliente(vista_alta_cliente);
				_controladorCliente.setBusquedaEntities(busqueda_entities);
				_controladorDomPart.setLogicaDomicilioParticular(_logica_dom_part);
				_controladorDomPart.setVistaAltaCliente(vista_alta_cliente);
				_controladorDomCom.setLogicaDomicilioComercial(_logica_dom_com);
				_controladorDomCom.setVistaAltaCliente(vista_alta_cliente);
				_controladorPedido.setLogicaPedido(_logica_pedido);
				_controladorPedido.setBusquedaEntities(busqueda_entities);
				_controladorPagoDiario.setLogicaPagoDiario(_logicaPagoDiario);
				_controladorArticulo.setBusquedaEntities(busqueda_entities);
				_controladorArticulo.setLogicaArticulo(_logica_articulo);
				_controladorCombo.setLogicaCombo(_logica_combo);
				_controladorCombo.setBusquedaEntities(busqueda_entities);
				cdp.setLogicaDespacho(log_despacho);
				_logica_pedido.setControladorPedido(_controladorPedido);
				_logica_pedido.setControladorPagoDiario(_controladorPagoDiario);
				_logica_pedido.setBusquedaEntities(busqueda_entities);
				_logica_pedido.setVistaBuscarPedidos_porClientes(vpc);
				_logica_pedido.setControladorDespacho_diario(cdp);
				_logica_pedido.setControladorArticulo(_controladorArticulo);
				_logica_pedido.setControladorPrestamo(_controladorPrestamo);
				vrp.setVistaPrincipal(_vista_principal);
				vrp.setControladorPedidos(_controladorPedido);
				vrp.setControladorCombo(_controladorCombo);
				vrp.setControladorArticulo(_controladorArticulo);
				vrp.setControladorPrestamo(_controladorPrestamo);
				vrp.setControladorDespacho_diario(cdp);
				vrp.setControladorPagoDiario(_controladorPagoDiario);
				vrp.setControladorCliente(_controladorCliente);
				
				int z = 0;
				boolean val = false;
				
				try{
					z=Integer.parseInt(zonaTF.getText());
					val = true;
				}
				catch(NumberFormatException es){
					
					val = false;
				}
				
				if(val){
					
					try {
						vrp.iniciar(_controladorDomCom.buscarDomicilio_comercial_porZona(z));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					vrp.setVisible(true);	
				}
				
				
			
		}
		
		if(e.getSource()==imprimir){
			
			JLabel di = new JLabel();
			di.setText(datosImpresion.getText());
			di.setFont(new Font("Arial", Font.PLAIN, 8));
			
			MessageFormat mf = new MessageFormat(datosImpresion.getText());
			
			try {
				
				String pie_pagina = zonaS + " " + fecha_impresion.getText() + "   Pag - {0}";
				
				PrinterJob job = PrinterJob.getPrinterJob();
				PageFormat pf = job.defaultPage();
				Paper paper = pf.getPaper();
				double margin = 20.;
				paper.setImageableArea(margin, 
				        paper.getImageableY(), 
				        paper.getWidth() - 2* margin, paper.getImageableHeight());
				pf.setPaper(paper);
				job.setPrintable(tabla.getPrintable(JTable.PrintMode.FIT_WIDTH, /*new MessageFormat(zonaS)*/null,
						new MessageFormat(pie_pagina)), 
				        job.validatePage(pf));
				if(job.printDialog()){
					
					job.print();
					
				}
				
				/*boolean completo =  tabla.print(JTable.PrintMode.FIT_WIDTH, null,
						new MessageFormat(zonaS + " " + fecha_impresion.getText() + "   Pag - {0}"),
								true, null, true,null);
				
				if(completo){
					
					Mensajes.getMensajeImpresion_completa();
				}
				else{
					
					Mensajes.getMensajeImpresion_cancelada();
					
				}*/
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			 /*try {
                 PrintPane printPane = new PrintPane(t_model, datosImpresion);

                 PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
                 aset.add(MediaSizeName.ISO_A4);
                 aset.add(new PrinterResolution(300, 300, PrinterResolution.DPI));
                 aset.add(DialogTypeSelection.NATIVE);
                 
                 PrinterJob pj = PrinterJob.getPrinterJob();
                 pj.setPrintable(printPane);

                 PageFormat pf = pj.defaultPage();
                 
                 pf.setOrientation(PageFormat.PORTRAIT);

                 if (pj.printDialog(aset)) {
                     try {
                         pj.print(aset);
                     } catch (PrinterException ex) {
                         ex.printStackTrace();
                     }
                 }
             } catch (IOException ex) {
                 ex.printStackTrace();
             }*/

			
			/*Paper paper = new Paper();
		      
	        paper.setSize(8.3 * 72, 11.7 * 72);
	        paper.setImageableArea(30, 30, 550, 500);
	        
	        PageFormat pf = new PageFormat();
	        pf.setPaper(paper);
	        //pf.setOrientation(PageFormat.LANDSCAPE);
	        pf.setOrientation(PageFormat.PORTRAIT);
			
			PrinterJob printJob = PrinterJob.getPrinterJob();
			Book libro = new Book();
			libro.append(new Impresion(tabla, 0.38, 0.38),
					printJob.pageDialog(pf));
			
			printJob.setPageable(libro);
			if (printJob.printDialog()) {
			try {
			printJob.print();
			} catch (Exception PrinterException) {
			javax.swing.JOptionPane.showMessageDialog(null, PrinterException.getMessage());
			}
			}*/
			
		}
		
		if(e.getSource()==nuevo_comando){
			
			//tabla.transferFocus();
			limpiar();
			habilita_datos(true, true, true, true, true, true,false,false, false,true,false, false);
			ar_nombres.clear();
			//pDatos.removeAll();
			cobradorL.setText("");
			fechaL.setText("");
			//pImpresion.remove(scr);
			//pImpresion.remove(total_planillaL);
			//pplanilla.remove(total_planillaL);
			total_planillaL.setText("");
			pImpresion.updateUI();
			pDatos.updateUI();
			pplanilla.updateUI();
			
		}
		
		if(e.getSource() == buscar_zona){
			
			_controladorZona.buscarZonaAll();
			_controladorZona.mostrarBusquedaEntities();
		}
		
		if(e.getSource()== ver_fi){
			
			/*Barra_progreso bp = new Barra_progreso();
			bp.setVistaIngresos(this);
			bp.actionPerformed(e);*/
			 /*javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		            		
		                bp.createAndShowGUI();
		            }
		        });
			 javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		            	cargar_planilla_ingreso();	
		               
		            }
		        });*/
			 /*Runnable runner = new Runnable()
			    {
			        public void run() {
			        	bp.createAndShowGUI();
//			        	cargar_planilla_ingreso();	
			        //Your original code with the loop here.
			        }
			    };
			    Thread t = new Thread(runner, "Code Executer");
			    t.start();
			    Runnable runner2 = new Runnable()
			    {
			    	public void run() {
			    		//bp.createAndShowGUI();
			    		//Your original code with the loop here.
			    	}
			    };
			    Thread t2 = new Thread(runner2, "Code Executer");
			    t2.start();*/
			cargar_planilla_ingreso();	
		}
		
		if(e.getSource()== ver_fb){
			
			
				cargar_planilla_busqueda();
			
		}
		
		if(e.getSource()==modificar){
			
			if(Principal._usuario.getPermiso()==1){
				
				consulta = false;
				habilita_datos(false, false, false, false, false, false,true,false,true,false,true, true);
				canEdit [8]= true;
				t_model.isCellEditable(tabla.getSelectedRow(), 8);
				
			}
			else{
				
				VistaContrasenas vc = new VistaContrasenas("pedido");
				vc.setControladorUsuario(_controladorUsuario);
				vc.setVisible(true);
				
				if(vc.getPermiso()){
					
					consulta = false;
					habilita_datos(false, false, false, false, false, false,true,false,true,false,true, true);
					canEdit [8]= true;
					t_model.isCellEditable(tabla.getSelectedRow(), 8);
				}
				
			}
			
		}
		
		if(e.getSource()==cancelar){
			
			/*consulta = true;
			 canEdit [5]= false;
			 t_model.isCellEditable(tabla.getSelectedRow(), 5);
			habilita_datos(false, false, false, false, false, false,false,true,false,false, true);*/
			cargar_planilla_busqueda();
		}
	}
	
	public void cargar_planilla_ingreso(){
		
		consulta = false;
		
		editar_planilla = false;
		
		canEdit [8]= true;
		t_model.isCellEditable(tabla.getSelectedRow(), 8);
		
		habilita_datos(false, false, false, false, false, false,false,false,false,false,false, true);
		
		if(!_controladorPagoDiario.buscarPagoDiario(fecha_ingreso.getJFormattedTextField().getText()
				, zonaTF.getText())){
			
			Mensajes.getMensaje_altaErrorGenerico();
			habilita_datos(false, false, false, false, false, false,false,false,false,false,false, true);
			//modelFI.setSelected(true);
			
		}
			
		else if(!_controladorPagoDiario.comprobar_pagoDuplicado(fecha_ingreso.getJFormattedTextField().getText()
				, zonaTF.getText())){
			
			/*short id_zona = Short.parseShort(zonaTF.getText());
			
			
			ArrayList<Object []> ar =_controladorCliente.buscarClientes_porZona(id_zona);
			
			if(ar!=null){*/
			
			task = new TableSwingWorker(/*t_model, ar*/);
		       task.addPropertyChangeListener(this);
		       task.execute();
				//iniciar(/*ar*/);
				
				
			
			//else System.out.println("array nulo");
		}
		else{
			Mensajes.getMensaje_pagoduplicadoError();
			habilita_datos(false, false, false, false, false, false,false,false,false,false,false, true);
			//pDatos.removeAll();
			cobradorL.setText("");
			fechaL.setText("");
			//pIntegra.remove(scr);
			pIntegra.updateUI();
			pDatos.updateUI();
		}
	}
	
	public void cargar_planilla_busqueda(){
		
		limpiar();
		
		consulta = true;	
		
		editar_planilla = true;
		
		 canEdit [8]= false;
		 t_model.isCellEditable(tabla.getSelectedRow(), 8);
		
		habilita_datos(false, false, false, false, false, false,false,false,false,false,false, true);
		
		if(!_controladorPagoDiario.buscarPagoDiario(fecha_busqueda.getJFormattedTextField().getText()
				, zonaTF.getText())){
			
			Mensajes.getMensaje_altaErrorGenerico();
			habilita_datos(false, false, false, false, false, false,false,false,false,false,false, true);
			//modelFI.setSelected(true);
			
		}			
		else if(_controladorPagoDiario.comprobar_pagoDuplicado(fecha_busqueda.getJFormattedTextField().getText()
				, zonaTF.getText())){
			
			short id_zona = Short.parseShort(zonaTF.getText());
			
			SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
			Date d=null;
			try {
				d = ft.parse(fecha_busqueda.getJFormattedTextField().getText());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			java.sql.Date fecha = new java.sql.Date(d.getTime());
			
			if(_controladorPagoDiario.buscarPagos_porFecha_zona(id_zona,fecha)!=null){
				ArrayList<Pago_diarioVO> ar =_controladorPagoDiario.buscarPagos_porFecha_zona(id_zona,fecha);
				
				iniciar_busqueda(ar);
				habilita_datos(false, false, false, false, false, false,false,true,false,false,false, true);
				
			}
			else System.out.println("array nulo");
		}
		else{
			Mensajes.getMensaje_pago_noregistradoError();
			habilita_datos(false, false, false, false, false, false,false,false,false,false,false, true);
			//pDatos.removeAll();
			cobradorL.setText("");
			fechaL.setText("");
			//pIntegra.remove(scr);
			pIntegra.updateUI();
			pDatos.updateUI();
		}
		
	}
	
	public void cargaPagoDiarioVO(JDatePickerImpl f){
		
		String fecha_ingresoS =f.getJFormattedTextField().getText();
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		Date dateFN = new Date();
		try {
			dateFN = format.parse(fecha_ingresoS);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.sql.Date fecha_ingresoDate = new java.sql.Date(dateFN.getTime());
		
		Pago_diarioVO _pago_diarioVO = new Pago_diarioVO();
		
		boolean resultado = true;
		
		for(int i = 0; i < tabla.getRowCount(); i++){
			Date d = new Date();
			
			java.sql.Date fecha_registro = new java.sql.Date(d.getTime());
			java.sql.Time hora_registro = new java.sql.Time(d.getTime());
			
			_pago_diarioVO.setFecha_registro(fecha_ingresoDate);
			_pago_diarioVO.setHora_registro(hora_registro);
			
			if(!editar_planilla){
				
				_pago_diarioVO.setN_pedido(Integer.parseInt(tabla.getValueAt(i, 4).toString()));
				
				ArrayList<Pedido_articuloVO> ar_pedido = _controladorPedido.
						buscarArticulos_porPedido(_pago_diarioVO.getN_pedido(), true);
				
				//if(apVO!=null) lDescripcion.setText("$" + Double.toString(apVO.getMonto()));
				
				String descripcion = "";
				if(ar_pedido!=null){
					
					for(Pedido_articuloVO paVO : ar_pedido){
						
						ArticulosVO aVO = _controladorArticulo.
								buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
						
						ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
						
						if(apVO!=null){
							
								descripcion = descripcion + " " + aVO.getNombre() + 
										"(" + apVO.getCodigo() + 
										")$" + Double.toString(apVO.getMonto());
								
						}
						/*else if(paVO.getCantidad()>1)
							descripcion = descripcion + " " + aVO.getNombre() +
							"(" + aVO.getCodigo() + ")" + "x" + paVO.getCantidad();*/
						else
							descripcion = descripcion + " " + aVO.getNombre()+
									"(" + aVO.getCodigo() + ")";
					}

					
				}
				
				_pago_diarioVO.setnombre_producto(descripcion);
				_pago_diarioVO.setImporte(Double.parseDouble(tabla.getValueAt(i, 8).toString()));
				_pago_diarioVO.setCuota(Double.parseDouble(tabla.getValueAt(i, 6).toString()));
				//_pago_diarioVO.setId_combinacion(ar_idcomb.get(i));
				_pago_diarioVO.setId_cobrador(empleadoVO.getId_usuario());
				_pago_diarioVO.setId_usuario(Principal._usuario.getId_usuario());
						
				_pago_diarioVO.setFecha(fecha_ingresoDate);
				
				
			}
			else{
				_pago_diarioVO.setN_pedido(Integer.parseInt(tabla.getValueAt(i, 4).toString()));
				_pago_diarioVO.setImporte(Double.parseDouble(tabla.getValueAt(i, 8).toString()));
			}
			
			int res = 0;
			
			if(!editar_planilla) res = _controladorPagoDiario.ingresarPagos(_pago_diarioVO);
			else res = _controladorPagoDiario.updatePagos(_pago_diarioVO);
			
			if(res == 0) resultado = false;
				
		}
		
		if(!editar_planilla){
			
			//if(resultado) Mensajes.getMensaje_altaExitosoGenerico();
			//else Mensajes.getMensaje_altaErrorGenerico();
			
		}
		else{
			
			if(resultado) Mensajes.getMensaje_modificacionExitosa();
			else Mensajes.getMensaje_modificacionError();
		}
			
	}
	
	public void instanciar(){
		
		VistaVerificacionIngresos vap = new VistaVerificacionIngresos("caja_zona");
		//vap.setPanelCliente("Zona " + Short.toString(zonaVO.getId_zona()));
		
		 ControladorCliente _controladorCliente = new ControladorCliente();
			
			ControladorPedidos _controladorPedido = new ControladorPedidos();
			ControladorArticulo _controladorArticulo = new ControladorArticulo();
			ControladorCombo _controladorCombo = new ControladorCombo();
			ControladorPagoDiario _controladorPagoDiario = new ControladorPagoDiario();
			ControladorPrestamo _controladorPrestamo = new ControladorPrestamo();
			ControladorCajaZona _controladorCaja_zona = new ControladorCajaZona();
			ControladorCaja _controladorCaja = new ControladorCaja();
			ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
			LogicaEmpleado logica_empleado = new LogicaEmpleado();
			LogicaCliente _logica_cliente = new LogicaCliente();
			LogicaPedido _logica_pedido = new LogicaPedido();
			LogicaArticulo _logica_articulo = new LogicaArticulo();
			LogicaCombo _logica_combo = new LogicaCombo();
			LogicaPrestamo _logica_prestamo = new LogicaPrestamo();
			LogicaPagoDiario _logica_pagoDiario = new LogicaPagoDiario();
			LogicaCajaZona _logicaCaja_zona = new LogicaCajaZona();
			LogicaCaja _logicaCaja = new LogicaCaja();
			BusquedaEntities busqueda_entities = new BusquedaEntities();
			
			//VistaNewObjetoVenta vista_combo = new VistaNewObjetoVenta();
			VistaPrestamo vista_prestamo = new VistaPrestamo();
			
			_logica_cliente.setControladorCliente(_controladorCliente);
			_logica_cliente.setBusquedaEntities(busqueda_entities);
			//_logica_cliente.setVistaBuscarCliente(this);
	
			_logica_articulo.setBusquedaEntities(busqueda_entities);
			_logica_combo.setBusquedaEntities(busqueda_entities);
			_logica_prestamo.setBusquedaEntities(busqueda_entities);
			
			/*this.setControladorCliente(_controladorCliente);	
			this.setBusquedaEntities(busqueda_entities);		
			this.setControladorPedido(_controladorPedido);
	
			this.setControladorArticulo(_controladorArticulo);
			this.setVistaCombo(vista_combo);
			this.setVistaPrestamo(vista_prestamo);
			this.setControladorCombo(_controladorCombo);
			this.setControladorPrestamo(_controladorPrestamo);*/
			controladorEmpleado.setLogicaEmpleado(logica_empleado);
			_controladorCliente.setLogicaCliente(_logica_cliente);
			_controladorCliente.setVistaPrincipal(_vista_principal);
			
			_controladorCliente.setBusquedaEntities(busqueda_entities);
			
			_controladorPedido.setLogicaPedido(_logica_pedido);
			_controladorPedido.setBusquedaEntities(busqueda_entities);
			_controladorArticulo.setBusquedaEntities(busqueda_entities);
			_controladorArticulo.setLogicaArticulo(_logica_articulo);
			_controladorPrestamo.setLogicaPrestamo(_logica_prestamo);
			_controladorCombo.setLogicaCombo(_logica_combo);
			_controladorCombo.setBusquedaEntities(busqueda_entities);
			_controladorPrestamo.setBusquedaEntities(busqueda_entities);
			_controladorPagoDiario.setLogicaPagoDiario(_logica_pagoDiario);
			_controladorCaja_zona.setLogicaCaja(_logicaCaja_zona);
			_controladorCaja.setLogicaCaja(_logicaCaja);
			_logica_pedido.setControladorPedido(_controladorPedido);
			_logica_pedido.setBusquedaEntities(busqueda_entities);
			//_logica_pedido.setVistaBuscarCliente(this);
			_logica_pedido.setControladorPagoDiario(_controladorPagoDiario);
			
			busqueda_entities.setVistaPrincipal(_vista_principal);
			//busqueda_entities.setVistaBuscarPedidos_porCliente(vp);
			busqueda_entities.setControladorCliente(_controladorCliente);
			busqueda_entities.setControladorArticulo(_controladorArticulo);
			//busqueda_entities.setVistaBuscarCliente(this);
			//busqueda_entities.setVistaNewObjetoVenta(vista_combo);
			busqueda_entities.setVistaPrestamo(vista_prestamo);
			
			busqueda_entities.setControladorEmpleado(controladorEmpleado);
			busqueda_entities.setControladorPedido(_controladorPedido);
			busqueda_entities.setControladorCombo(_controladorCombo);
			busqueda_entities.setControladorPrestamo(_controladorPrestamo);
			
			this.setVistaPrincipal(_vista_principal);
			
			/*vista_combo.setControladorArticulo(_controladorArticulo);
			vista_combo.setBusquedaEntities(busqueda_entities);
			vista_combo.setControladorCombo(_controladorCombo);*/
			//vista_combo.setVistaBuscarPedidos_porClientes(vp);
			
			vista_prestamo.setControladorPrestamo(_controladorPrestamo);
			vista_prestamo.setBusquedaEntities(busqueda_entities);
			//vista_prestamo.setVistaBuscarPedidos_porClientes(vp);

		
		//vap.setVistaBuscarCliente(this);
		//vap.setPanelCliente(_clienteVO.getNombre() + " " + _clienteVO.getApellido());
		vap.setBusquedaEntities(busqueda_entities);
		vap.setControladorArticulo(_controladorArticulo);
		vap.setControladorCombo(_controladorCombo);
		vap.setControladorPrestamo(_controladorPrestamo);
		vap.setControladorPagoDiario(_controladorPagoDiario);
		//vap.setVistaCombo(vista_combo);
		vap.setVistaPrestamo(vista_prestamo);
		vap.setControladorPedido(_controladorPedido);
		vap.setVistaIngresos(this);
		vap.setControladorCajaZona(_controladorCaja_zona);
		vap.setControladorCaja(_controladorCaja);
		//busqueda_entities.setVistaAltaPedido(vap);
		//bh.setVistaAltaPedido(vap);
		//bh.setVistaBuscarCliente(this);
		
		vap.setVisible(true);
		vap.getMil().requestFocusInWindow();
	}
	
	private void ancho_columnas(){
		
		for(int i = 0; i < tabla.getColumnCount(); i++){
			
			if(i == 2) tabla.getColumnModel().getColumn(i).setPreferredWidth(250);
			else tabla.getColumnModel().getColumn(i).setPreferredWidth(50);
		}
	}
	
	private void alto_filas(){
		
		for(int i = 0; i < tabla.getRowCount(); i++){
			
			tabla.setRowHeight(i, 30);
		}
	}
	
	/*private String [] getColumnas(){
		
		String columna [] = new String []{"Cliente", "Direccion comercial", "N°P", "Días", "Cuota",
										"Importe"};
		
		return columna;
	}*/
	private String [] getColumnas(){
		
		String columna [] = new String []{"Cliente", "Direccion comercial", 
				"Comercio", "Horario", "N°P", "Días", "Cuota",
		"Mora","Importe", "Observaciones"};
		
		return columna;
	}
	
	public void habilita_datos(boolean zonaTF, boolean buscar_zona, boolean fecha_ingreso, 
			boolean fecha_busqueda, boolean ver_fi, boolean ver_fb, boolean guardar,
			boolean modificar, boolean cancelar, boolean cambiar_orden, boolean imprimir, boolean nuevo_comando){
		
		this.zonaTF.setEnabled(zonaTF);
		this.buscar_zona.setEnabled(buscar_zona);
		//this.fecha_ingreso.getComponent(1).setEnabled(fecha_ingreso);
		this.fecha_busqueda.getComponent(1).setEnabled(fecha_busqueda);
		this.ver_fi.setEnabled(ver_fi);
		this.ver_fb.setEnabled(ver_fb);
		
		this.guardar.setEnabled(guardar);
		this.modificar.setEnabled(modificar);
		this.cancelar.setEnabled(cancelar);
		this.cambiar_orden.setEnabled(cambiar_orden);
		this.imprimir.setEnabled(imprimir);
		this.nuevo_comando.setEnabled(nuevo_comando);
	}
	
	 public void limpiar(){
	    	
	    	if(tabla.getRowCount() > 0){
				
				DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
				
				int contFila = tabla.getRowCount();
				
				for(int i = 0; i < contFila; i++)
				
					modelo.removeRow(0);
			}
	    	//obL.setText("");
	    	//observaciones.setText("");
	    }
	
	class CustomJToolTip extends JToolTip {

	    public CustomJToolTip(JComponent component) {
	        super();
	        setComponent(component);
	        setBackground(Color.white);
	        setForeground(Color.BLACK);
	    }
	}
	
	public class PriorityCellRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
		        boolean isSelected, boolean hasFocus, int row, int column) {
		    super.getTableCellRendererComponent(
		            table, value, isSelected, hasFocus, row, column);

		    //if (Integer.valueOf(1).equals(table.getValueAt(row, 1)) && 0 == column)   && 9 < column)
		    System.out.println("columna " + column);
		    if(column==5){
		    	
		    	setBackground(Color.green);
		    }
		    	
		    
		    if (String.valueOf(1).equals(table.getValueAt(row, 0))) {
		        setForeground(Color.BLUE);  // or background
		    }
		    if (String.valueOf(2).equals(table.getValueAt(row, 0))) {
		        setForeground(Color.GREEN);  // or background
		    }
		    if (String.valueOf(3).equals(table.getValueAt(row, 0))) {
		        setForeground(Color.YELLOW);  // or background
		    }
		    if (String.valueOf(4).equals(table.getValueAt(row, 0))) {
		        setForeground(Color.RED);  // or background
		    }
		    return this;
		} 
		}
	
	public class ColorRenderer extends DefaultTableCellRenderer {
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)  {
	       // get the DefaultCellRenderer to give you the basic component
	       Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
	       // apply your rules
	       
	       if( clickedRow == row && clickedCol == col){
	    	    c.setBackground(Color.GREEN);
	    	    System.out.println("hola cell");
	    	}
	       if(col==6)c.setBackground(Color.GREEN);
	       /*if(table.isRowSelected(row) && table.isColumnSelected(col))
	          c.setBackground(Color.GREEN);*/
	       else{    
	           c.setBackground(table.getBackground());
	       }

	       return c;
	    }
	}
	
	public class FilaRenderer extends DefaultTableCellRenderer {

		private int col;
		private String fin = new String();
		public FilaRenderer(int col){
			
			this.col = col;
			
		}
		
        public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
        	JLabel c = (JLabel)  super.getTableCellRendererComponent(table, value,
                            isSelected, hasFocus, row, column);
       
            
        	c.setHorizontalAlignment(JLabel.CENTER);
           fin = table.getModel().getValueAt(row, col).toString();
            //if(/*contador_pedidos==row==getContador_pedidos()*/tabla.getModel().getValueAt(row, col).toString()
            	//	.equals("finalizado")){*/
           
           if(column==table.getSelectedColumn() && row ==table.getSelectedRow()){
              	
              	//if (isColumnSelected(column)){ //When A row is selected
                      setBackground(new Color(183, 242, 113));//Set Background
                      JTextField textField = new JTextField();
                     textField.setFont(new Font("Arial", Font.PLAIN, 18));
                      textField.setBorder(new LineBorder(Color.BLACK));
                      DefaultCellEditor dce = new DefaultCellEditor( textField );
                      table.getColumnModel().getColumn(column).setCellEditor(dce);
                     // c.setForeground(Color.BLUE);
              	//}
              }
             /* else{    
   	           setBackground(table.getBackground());*/
   	       
           
        else if(fin.equals("control")){
            
            	setBackground(new Color(214, 234, 248));

            	//System.out.println("finalizado4545454545454545454545454545");
            	
            }
        else if(!fin.equals("")){
        	
        	setBackground(new Color(212, 239, 223));
        	
        }
            else{    
            	setBackground(tabla.getBackground());
            	//System.out.println("no finalizado*/*/*/*/*/*/*/*/*/*/*/**/*/*");
            	
            }
            
           
            return this;
        }
        
       
    }
	
	class myDataModel extends DefaultTableModel implements TableCellRenderer {
        
		public Component getTableCellRendererComponent(JTable tabla, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		            DefaultTableCellRenderer f = new DefaultTableCellRenderer();
		 
		if (row==tabla.getSelectedRow() && column==tabla.getSelectedColumn()) {
		         
		             f.setBackground(Color.red);
		             //f.setForeground(b.f);
		             //f.setText(b.s);
		         return (Component)f;
		} else if (hasFocus) {
		           /*CellData b = (CellData)super.getValueAt(row, column);
		           Color t =new Color(255 - b.b.getRed(), 255 - b.b.getGreen(), 255 - b.b.getBlue());
		            f.setBackground(t);
		            f.setForeground(new Color(255 - t.getRed(), 255 - t.getGreen(),255 - t.getBlue()));
		            f.setText(b.s);*/
		          return (Component)f;
		} else {
		            return f.getTableCellRendererComponent(tabla, value, isSelected,
		            hasFocus, row, column);
		     }
		   }
		}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if ("progress" == evt.getPropertyName()) {
			 
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
           
           
        } 
	}


	
	

}
