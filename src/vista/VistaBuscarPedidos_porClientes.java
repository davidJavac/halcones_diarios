package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JToolTip;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controlador.ControladorArticulo;
import controlador.ControladorCambio_plan;
import controlador.ControladorCliente;
import controlador.ControladorCombo;
import controlador.ControladorDA;
import controlador.ControladorDespacho_diario;
import controlador.ControladorDomicilioComercial;
import controlador.ControladorDomicilioParticular;
import controlador.ControladorLocalidad;
import controlador.ControladorMonto_trasladado;
import controlador.ControladorObservaciones;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorPlan;
import controlador.ControladorPrestamo;
import controlador.ControladorRefinanciacion_ex;
import controlador.ControladorRefinanciacion_in;
import controlador.ControladorRetiros;
import controlador.ControladorUsuario;
import controlador.ControladorVendedor;
import controlador.ControladorZona;
import controlador.Principal;
import modelo.CifradoAction;
import modelo.DescifradoAction;
import modelo.LogicaArticulo;
import modelo.LogicaCambio_plan;
import modelo.LogicaCliente;
import modelo.LogicaCombo;
import modelo.LogicaDA;
import modelo.LogicaDespacho;
import modelo.LogicaMonto_trasladado;
import modelo.LogicaObservaciones;
import modelo.LogicaPagoDiario;
import modelo.LogicaPedido;
import modelo.LogicaRetiro;
import modelo.Logica_plan;
import modelo.Mensajes;
import modelo_dao.Monto_trasladadoDAO;
import modelo_dao.Refinanciacion_exDAO;
import modelo_dao.Refinanciacion_inDAO;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Cambio_planVO;
import modelo_vo.ClienteVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.Monto_trasladadoVO;
import modelo_vo.ObservacionesVO;
import modelo_vo.Pago_diarioVO;
import modelo_vo.PedidosVO;
import modelo_vo.Pedidos_diasVO;
import modelo_vo.Refinanciacion_exVO;
import modelo_vo.Refinanciacion_inVO;
import modelo_vo.UsuariosVO;
import vista.Barra_herramientasPedidos.CustomJToolTip;
import modelo_vo.Combinacion_diasVO;
import modelo_vo.Pedido_articuloVO;

public class VistaBuscarPedidos_porClientes extends JDialog implements ActionListener{

	private DefaultTableModel tModel;
	private JTable tabla;
	private JScrollPane scr;
	
	private VistaBuscarCliente vista_buscar_cliente;
	
	private int dni_anterior;
	
	private JComboBox idcCB;
	private DefaultComboBoxModel cb_model = new DefaultComboBoxModel();
	private JTextField n_pedidoTF;
	private JPanel pArticulo;
	private JTextField articuloTF;
	private JTextField cantidadTF;
	private JTextField dias_desde_inicioTF;
	private JTextField dias_moraTF;
	private JTextField dias_cobranzaTF;
	private JTextField resto_dias_moraTF;
	private JTextField estado_deudaTF;
	private JTextField estado_pedidoTF;
	private JTextField diasTF;
	private JTextField cuotaTF;
	private JTextField facturacionTF;
	private JTextField creditoTF;
	private JTextField saldoTF;
	private JTextField interesTF;
	private JPanel pDias_cobranza;
	private JDatePickerImpl fecha_inicio;
	private JDatePickerImpl fecha_termino;
	private JDatePickerImpl fecha_termino_ideal;
	
	private JTextField ref_cuota;
	private JTextField ref_in_cuota;
	private JTextField ref_dias;
	private JTextField ref_in_dias;
	  
	private JPanel pRef;
	private JPanel pRef_in;
	
	private JPanel pIntegra_monto;
	private JPanel pMonto;
	
	JComponent labelsAndFields;
	
	 private JDesktopPane desktop;
	
	private JLabel lUsuario;
	private JLabel lFecha_registro;
	private JLabel lHora_registro;
	private JLabel lArticulo, lDias_cobranza, lDescripcion;
	private JLabel lEstado_pedido, lFacturacion;
	private JLabel lRef;
	private  JLabel refinanciacionL;
	private  JLabel refinanciacion_inL;
	private JLabel mtL;
	private JLabel descuento_admL;
	
	private ArrayList<JTextField> stringTF;
	private ArrayList<JTextField> int_doubleTF;
	private ArrayList<JTextField> arRef;

	private ArrayList<JDatePickerImpl> array_datepicker;
	
	private JButton baja = new JButton("Dar baja");
	
	private ControladorCliente _controladorCliente;
	private ControladorDomicilioParticular _controladorDomPart;
	private ControladorDomicilioComercial _controladorDomCom;
	
	
	private JButton cargar = new JButton("Guardar");
	private JButton buscar_articulo = new JButton("...");
	private JButton crear_articulo = new JButton("Crear");
	private JButton buscar_dias_cobranza = new JButton("...");
	private JButton anular_ref_ex = new JButton("Anular");
	private JButton anular_ref_in = new JButton("Anular");
	
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
	private ControladorUsuario _controladorUsuario;
	private BusquedaEntities _busqueda_entities;
	private VistaBuscarPedidos_porClientes vp;
	private static  VistaPrincipal _vista_principal;
	private VistaPrestamo vista_prestamo;
	private CifradoAction ca;
	private DescifradoAction da;
	//private Barra_herramientasPedidos bhp2;
	//Barra_herramientasPedidos_interna bhp = new Barra_herramientasPedidos_interna();
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
	private JButton cancelar = new JButton();
	private JButton salir = new JButton();
	private JButton ref_in = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
	private JButton ref_ex = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
    
    private JButton observaciones = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
    private JButton pagos = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
    private JButton retiros = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
    private JButton cambio_plan = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
    private JButton anular = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
	private JButton monto_trasladado = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
    
    private JCheckBox ch_lunes = new JCheckBox("l");
	private JCheckBox ch_martes = new JCheckBox("m");
	private JCheckBox ch_miercoles = new JCheckBox("M");
	private JCheckBox ch_jueves = new JCheckBox("j");
	private JCheckBox ch_viernes = new JCheckBox("v");
	private JCheckBox ch_sabado = new JCheckBox("s");
	private JCheckBox ch_todos = new JCheckBox("Todos");
	private JPanel pDias_check = new JPanel();
	private ArrayList<JCheckBox> ar_check = new ArrayList<JCheckBox>();
	private Border borde_dias;
	
	private JButton guardar_acu = new JButton("Guardar");
	private JPanel pAcu = new JPanel();
	private JTextField acuTF = new JTextField(5);
	
	private JPanel pBarra;
	private JPanel pDatospersonales;
	private JPanel pDomPart;
	private JPanel pDomCom;
	private JPanel pIntegra;
	private JPanel pNorte;
	private  JComponent panel; 
	private JPanel pPedidos;
	private JPanel pHistorial_pedidos;
	private JPanel pIntegra_pedidos;
	
	private JLabel titulo_barra;
	
	private static int contador_panel;
	
	private String cliente;
	
	private PedidosVO _pedidoVO;
	private ArticulosVO _articulosVO;
	private UsuariosVO _usuariosVO;
	private Refinanciacion_exVO _refVO;
	private Refinanciacion_inVO _ref_inVO;
	
	
	private ArticulosVO aux_articulo;
	private ClienteVO aux_cliente;

	private Refinanciacion_exVO aux_ref;
	private Refinanciacion_inVO aux_ref_in;
	
	public  boolean refinanciacion_ex = false;
	public  boolean refinanciacion_in = false;
	
	public boolean encriptado = false;
	
	private VistaRefinanciacion vf;
	private VistaRefinanciacion_in vf_in;
	private VistaMonto_t mt;
	
	private Vista_pagos_porPedido v_pagos;
	private VistaNewObjetoVenta vnov;
	
	private PedidosVO pedido_originalVO = new PedidosVO();
	private String plan_original;
	
	public Barra_herramientasPedidos bhp2;
	
	//public static String key = "sdfdgbdfv23342{´";
	
	UtilDateModel modelFinicio = new UtilDateModel();
	UtilDateModel modelFtermino = new UtilDateModel();
	UtilDateModel modelFtermino_ideal = new UtilDateModel();
	/*Barra_herramientasPedidos bhp2 = new Barra_herramientasPedidos(guardar, modificar, salir, ref_in, ref_ex,
			cancelar, anular);*/
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double width = screenSize.getWidth();
    double height = screenSize.getHeight();
	
	public VistaBuscarPedidos_porClientes(){
		super(_vista_principal, "Gestión de pedido", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
	     //add(pIntegra_pedidos, BorderLayout.SOUTH);
	     
		
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     setSize((int)width*90/100,(int) height*90/100);
	     setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	     this.setResizable(false);
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	     
		
		modificar.addActionListener(this);
		guardar.addActionListener(this);
		guardar_acu.addActionListener(this);
		anular.addActionListener(this);
		ref_in.addActionListener(this);
		ref_ex.addActionListener(this);
		monto_trasladado.addActionListener(this);
		observaciones.addActionListener(this);
		pagos.addActionListener(this);
		retiros.addActionListener(this);
		cambio_plan.addActionListener(this);
		cancelar.addActionListener(this);
		//baja.addActionListener(this);
		salir.addActionListener(this);
		anular_ref_ex.addActionListener(this);
		anular_ref_in.addActionListener(this);
		
		anular_ref_ex.setEnabled(false);
		anular_ref_in.setEnabled(false);
		
		ref_ex.setEnabled(true);
		ref_in.setEnabled(true);
		
		setLayout(new BorderLayout());
		
		pBarra = new JPanel();
		pDatospersonales = new JPanel();
		pDomPart = new JPanel();
		pDomCom = new JPanel();
		pBarra.setLayout(new BorderLayout());
		pBarra.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		pBarra.setBackground(new Color(234, 242, 248));
		pDatospersonales.setLayout(new BorderLayout());
	
		
		GridLayout gl = new GridLayout(0,2);
  		
  		articuloTF = new JTextField(5);
  		articuloTF.setFont(new Font("Arial", Font.PLAIN, (int)width/100));
  		lArticulo = new JLabel();
  		lDescripcion = new JLabel();
  		
  		articuloTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				articuloTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				/*if(_controladorArticulo.buscarArticuloUsuario(articuloTF.getText())!=null){
					
					ArticulosVO _articulosVO = _controladorArticulo.buscarArticuloUsuario(articuloTF.getText());
					
					lArticulo.setText(_articulosVO.getNombre());
					
					ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(Integer.parseInt
							(articuloTF.getText()));
					
					ArrayList<Pedido_articuloVO> ar_combo = _controladorArticulo.buscarArticulo_enCombo(Integer.parseInt
							(articuloTF.getText()));
					
					if(apVO!=null) lDescripcion.setText("$" + Double.toString(apVO.getMonto()));
					
					else if(ar_combo!=null){
						
						String descripcion_combo = "";
						
						for(Pedido_articuloVO cVO : ar_combo){
							
							ArticulosVO aVO = _controladorArticulo.
									buscarArticuloUsuario(Integer.toString(cVO.getCodigo_articulo()));
							
							
								descripcion_combo = descripcion_combo + " " + aVO.getNombre();
						}
						
						lDescripcion.setText(descripcion_combo);
					}
					else
						lDescripcion.setText(_articulosVO.getDescripcion());
						
					aux_articulo = _articulosVO;
					
					
				}
						
				else{
					lArticulo.setText("");
					lDescripcion.setText("");
					articuloTF.setText("");
				}
					
				
				articuloTF.setBackground(new Color(255, 255, 255));*/
				
			}
  			
  			
  		});
  		
  		
  		pArticulo = new JPanel();
		pDias_cobranza = new JPanel();
		
  		pArticulo.setLayout(gl);
  		pArticulo.add(articuloTF);
  		buscar_articulo.setFocusable(false);
  		JPanel pArt2 = new JPanel();
  		pArt2.setLayout(gl);
  		pArt2.add(buscar_articulo);
  		pArt2.add(crear_articulo);
  		pArticulo.add(pArt2);
  		
  		try {
			  
		    Image comb = ImageIO.read(new FileInputStream("imagenes/combinacion2.png"));
		    
		    //combo.setIcon(new ImageIcon(comb));
		    
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
  		
  		try {
			  
		    Image busc = ImageIO.read(new FileInputStream("imagenes/lupa1.png"));
		    
		    //buscar_combo.setIcon(new ImageIcon(busc));
		    
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
  		
  		
  		
		pIntegra = new PanelGraduado(new Color(234, 250, 241), new Color(212, 239, 223  )  );
		pNorte = new JPanel();
		//pNorte.setLayout(new BorderLayout());
		pIntegra.setLayout(new BoxLayout(pIntegra, 	BoxLayout.Y_AXIS));
		
		//_controladorZona
		
		cargar.addActionListener(this);
		buscar_articulo.addActionListener(this);
		crear_articulo.addActionListener(this);
		buscar_dias_cobranza.addActionListener(this);
		
		lUsuario = new JLabel();
		lFecha_registro = new JLabel();
		lHora_registro = new JLabel();
		lArticulo = new JLabel();
		lDescripcion = new JLabel();
		lDias_cobranza = new JLabel();
	
		lEstado_pedido = new JLabel();
		lFacturacion = new JLabel();
		
		dias_cobranzaTF = new JTextField();
		pDias_cobranza.setLayout(gl);
  		pDias_cobranza.add(dias_cobranzaTF);
  		pDias_cobranza.add(buscar_dias_cobranza);
  		
  		dias_cobranzaTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				dias_cobranzaTF.setBackground(new Color(183, 242, 113));
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(_controladorPedido.buscarCombinacion(dias_cobranzaTF.getText())!=null){
					
					Combinacion_diasVO _combinacion_diasVO = _controladorPedido.buscarCombinacion
							(dias_cobranzaTF.getText());
					
					String dias_cobranzaS = "";
					
					if(_combinacion_diasVO.getLunes()) dias_cobranzaS = dias_cobranzaS + " Lunes "; 
					if(_combinacion_diasVO.getMartes()) dias_cobranzaS = dias_cobranzaS + " Martes ";
					if(_combinacion_diasVO.getMiercoles()) dias_cobranzaS = dias_cobranzaS + " Miércoles ";
					if(_combinacion_diasVO.getJueves()) dias_cobranzaS = dias_cobranzaS + " Jueves ";
					if(_combinacion_diasVO.getViernes()) dias_cobranzaS = dias_cobranzaS + " Viernes ";
					if(_combinacion_diasVO.getSabado()) dias_cobranzaS = dias_cobranzaS + " Sábado ";
					
					lDias_cobranza.setText(dias_cobranzaS);
				}
					
					
				else{
					lDias_cobranza.setText("");
					dias_cobranzaTF.setText("");
				}
					
				
				dias_cobranzaTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
  		
        //model.setDate(20,04,2014);
  		Properties p = new Properties();
  		p.put("text.today", "Today");
  		p.put("text.month", "Month");
  		p.put("text.year", "Year");
        JDatePanelImpl datePanelFinicio = new JDatePanelImpl(modelFinicio, p);
        JDatePanelImpl datePanelFtermino = new JDatePanelImpl(modelFtermino, p);
        JDatePanelImpl datePanelFtermino_ideal = new JDatePanelImpl(modelFtermino_ideal, p);
     
        fecha_inicio = new JDatePickerImpl(datePanelFinicio, new DateLabelFormatter());
        
       JPanel pPlanC = new JPanel();
       JPanel pPlanD = new JPanel();
       
       lRef = new JLabel();
      
       pRef = new JPanel();
       pRef_in = new JPanel();
       
       pRef.setLayout(gl);
       pRef_in.setLayout(gl);
       
       ref_cuota = new JTextField(5);
       ref_in_cuota = new JTextField(5);
       ref_dias = new JTextField(5);
       ref_in_dias = new JTextField(5);
       
       ref_cuota.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				ref_cuota.setBackground(new Color(183, 242, 113));
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				
				ref_cuota.setBackground(new Color(255, 255, 255));
			}
			
 		});
       
       ref_in_cuota.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				ref_in_cuota.setBackground(new Color(183, 242, 113));
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				
				ref_in_cuota.setBackground(new Color(255, 255, 255));
			}
			
		});
       
       ref_dias.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				ref_dias.setBackground(new Color(183, 242, 113));
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				
				ref_dias.setBackground(new Color(255, 255, 255));
			}
			
		});
       
       ref_in_dias.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				ref_in_dias.setBackground(new Color(183, 242, 113));
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				
				ref_in_dias.setBackground(new Color(255, 255, 255));
			}
			
		});
       
       JLabel refLC = new JLabel("Cuota");
       JLabel refL_D = new JLabel("Días");
       JLabel ref_inLC = new JLabel("Cuota");
       JLabel ref_inL_D = new JLabel("Días");
       
       refLC.setFont(new Font("Arial",Font.BOLD,(int)width/100));
       refL_D.setFont(new Font("Arial",Font.BOLD,(int)width/100));
       ref_inLC.setFont(new Font("Arial",Font.BOLD,(int)width/100));
       ref_inL_D.setFont(new Font("Arial",Font.BOLD,(int)width/100));
       
       ref_cuota.setFont(new Font("Arial",Font.BOLD,(int)width/100));
       ref_dias.setFont(new Font("Arial",Font.BOLD,(int)width/100));
       ref_in_cuota.setFont(new Font("Arial",Font.BOLD,(int)width/100));
       ref_in_dias.setFont(new Font("Arial",Font.BOLD,(int)width/100));
       
      //pRef.setBackground(Color.white);
       //pRef_in.setBackground(Color.white);
       
       pRef.setOpaque(false);
       pRef_in.setOpaque(false);
       
       JPanel pIntegraRef = new JPanel();
       //pIntegraRef.setBackground(Color.white);
       pIntegraRef.setOpaque(false);
       JPanel pIntegraRef_in = new JPanel();
       //pIntegraRef_in.setBackground(Color.white);
       pIntegraRef_in.setOpaque(false);
       
       pRef.add(refL_D);    
       pRef.add(ref_dias);
       pRef.add(refLC);  
       pRef.add(ref_cuota);
       //pRef.add(anular_ref_ex);
       pRef_in.add(ref_inL_D); 
       pRef_in.add(ref_in_dias);
       pRef_in.add(ref_inLC);
       pRef_in.add(ref_in_cuota);
       
       pIntegraRef.add(pRef);
       pIntegraRef.add(anular_ref_ex);
       
       pIntegraRef_in.add(pRef_in);
       pIntegraRef_in.add(anular_ref_in);

       
       JPanel pPlan = new JPanel();
       JPanel pCuota = new JPanel();
       JPanel pPlanIntegra = new JPanel();
       
       pPlan.setLayout(gl);
      // pPlan.setBackground(Color.WHITE);
       
       JLabel diasL = new JLabel("Días");
       JLabel cuotaL = new JLabel("Cuota");
       diasL.setFont(new Font("Arial", Font.BOLD, (int)width/100));
       cuotaL.setFont(new Font("Arial", Font.BOLD, (int)width/100));
       
       diasTF = new JTextField(5);
       cuotaTF = new JTextField(5);
       diasTF.setFont(new Font("Arial",Font.BOLD,(int)width/100));
       cuotaTF.setFont(new Font("Arial",Font.BOLD,(int)width/100));
          
       pPlan.add(diasL);
       pPlan.add(diasTF);
       pPlan.add(cuotaL);
       pPlan.add(cuotaTF);
      
       pPlan.setOpaque(false);
       pPlanIntegra.setOpaque(false);
       
       refinanciacionL = new JLabel();
       refinanciacion_inL = new JLabel();
      
       pPlanIntegra.add(pPlan);
       pPlanIntegra.add(refinanciacionL);
       pPlanIntegra.add(refinanciacion_inL);
       //pPlanIntegra.setBackground(Color.white);
       
       mtL = new JLabel();
       descuento_admL = new JLabel();
       
       JPanel pFacturacion = new JPanel();
       pFacturacion.setLayout(gl);
       
       facturacionTF = new JTextField(10);
       facturacionTF.setFont(new Font("Arial",Font.BOLD,(int)width/100));
       pFacturacion.add(facturacionTF);
       pFacturacion.add(mtL);
       pFacturacion.add(descuento_admL);
      // pFacturacion.setBackground(Color.WHITE);
       
       pFacturacion.setOpaque(false);
       
       pIntegra_monto = new JPanel();
       pIntegra_monto.setLayout(new BoxLayout(pIntegra_monto, BoxLayout.Y_AXIS));
       
   		Border borde_monto = BorderFactory.createTitledBorder(null, "Detalle", 
			TitledBorder.CENTER, TitledBorder.TOP,
			new Font("Arial",Font.BOLD,(int)width/100), Color.BLACK);
   		pIntegra_monto.setBorder(borde_monto);
        //pIntegra_monto.setBackground(Color.white);
       pIntegra_monto.setOpaque(false);
   		
   		
        pMonto = new JPanel();	
    	pMonto.setLayout(gl);
    	
       JComponent[] components = {
               
               n_pedidoTF = new JTextField(10),
               idcCB = new JComboBox(),
        		//pArticulo,
        		//lArticulo,
        		lDescripcion,
        		pPlanIntegra,
        		
        		dias_desde_inicioTF = new JTextField(15),
        		dias_moraTF = new JTextField(15),
        		resto_dias_moraTF = new JTextField(15),
        		estado_deudaTF = new JTextField(15),
        		lEstado_pedido,  		
        	
        		pFacturacion,
        		creditoTF = new JTextField(15),
        		saldoTF = new JTextField(15),
               interesTF = new JTextField(15),
               /*pDias_cobranza,
               lDias_cobranza,*/
        		fecha_inicio,
        		fecha_termino_ideal = new JDatePickerImpl(datePanelFtermino_ideal, new DateLabelFormatter()),
        		fecha_termino = new JDatePickerImpl(datePanelFtermino, new DateLabelFormatter()),
        		  		
        		lUsuario,
        		lFecha_registro,
        		lHora_registro,	
        		pIntegraRef,
        		pIntegraRef_in,
        		pIntegra_monto
        		
            };
		
       idcCB.setModel(cb_model);
       
		 String labels [] = {
				 
				 "N° Pedido","Idc", "Descripción", "Plan", "Dias desde inicio", "Dias mora",
					"Resto dias mora", "Estado deuda", "Estado pedido",
					"Facturacion", "Crédito", "Saldo", "Interes",
					"Fecha inicio","Fecha término estimado", "Fecha término",
					"Usuario", "Fecha registro", "Hora registro", "Ref.", "Ref.interna", "Monto trasladado"
		 };
		 
		 
		 
		 JFormattedTextField fn = fecha_inicio.getJFormattedTextField();
		 
		 fn.setFont(new Font("Arial", Font.PLAIN, (int)width/100));
		 JFormattedTextField antPart = fecha_termino.getJFormattedTextField();
		 antPart.setFont(new Font("Arial", Font.PLAIN, (int)width/100));
		 JFormattedTextField fti = fecha_termino_ideal.getJFormattedTextField();
		 fti.setFont(new Font("Arial", Font.PLAIN, (int)width/100));
	
		stringTF = new ArrayList<JTextField>();
		
		stringTF.add(fecha_inicio.getJFormattedTextField());
		stringTF.add(fecha_termino.getJFormattedTextField());
		stringTF.add(fecha_termino_ideal.getJFormattedTextField());
		
		int_doubleTF = new ArrayList<JTextField>();
		
		
		int_doubleTF.add(n_pedidoTF);
		int_doubleTF.add(articuloTF);
		int_doubleTF.add(dias_desde_inicioTF);
		int_doubleTF.add(dias_cobranzaTF);
		int_doubleTF.add(dias_moraTF);
		int_doubleTF.add(resto_dias_moraTF);
		int_doubleTF.add(estado_deudaTF);
		int_doubleTF.add(resto_dias_moraTF);
		int_doubleTF.add(diasTF);
		int_doubleTF.add(cuotaTF);
		int_doubleTF.add(facturacionTF);
		int_doubleTF.add(creditoTF);
		int_doubleTF.add(saldoTF);
		int_doubleTF.add(interesTF);
		//int_doubleTF.add(prestamoTF);
		
		arRef = new ArrayList<JTextField>();
		
		arRef.add(ref_cuota);
		arRef.add(ref_dias);
		arRef.add(ref_in_cuota);
		arRef.add(ref_in_dias);
		
		array_datepicker = new ArrayList<JDatePickerImpl>();
		
		array_datepicker.add(fecha_inicio);
		array_datepicker.add(fecha_termino);
		array_datepicker.add(fecha_termino_ideal);
		
		titulo_barra = new JLabel("Pedidos activos");
		
		Font fuenteB = new Font("Verdana", Font.PLAIN, (int)width/100);
		
		titulo_barra.setFont(fuenteB);
		
		pBarra.add(titulo_barra);
		
		pNorte.add(guardar);
		pNorte.add(modificar);
		pNorte.add(baja);
		pNorte.add(cancelar);
		//pNorte.add(salir);

		
		Container contentPane = this.getContentPane();
		
		bhp2 = new Barra_herramientasPedidos(guardar, modificar, salir, ref_in, ref_ex,
				cancelar, anular, monto_trasladado, observaciones, pagos, retiros, cambio_plan);
		
		if(Principal._usuario.getPermiso()==2){
			
			bhp2.habilitaBotones(true, true, true, false, false, 
					true, false, false, true, true, false, false);
			ref_dias.setEnabled(false);
			ref_cuota.setEnabled(false);
			ref_in_dias.setEnabled(false);
			ref_in_cuota.setEnabled(false);
			
			
		}
		
		/*JScrollPane scrbh = new JScrollPane();
		scrbh.setViewportView(bhp2);*/
		
	    contentPane.add(bhp2, BorderLayout.NORTH);
	  
		labelsAndFields = getTwoColumnLayout(labels,components, cliente);
	    lDias_cobranza.setFont(new Font("Arial", Font.BOLD, (int)width/100));
	    lDescripcion.setFont(new Font("Arial", Font.BOLD, (int)width/100));
     
	    
	    ch_lunes.setOpaque(false);
	       ch_martes.setOpaque(false);
	       ch_miercoles.setOpaque(false);
	       ch_jueves.setOpaque(false);
	       ch_viernes.setOpaque(false);
	       ch_sabado.setOpaque(false);
	       ch_todos.setOpaque(false);
	       
           //ch_lunes.setIcon(new ImageIcon(ImageIO.read(...)));
	       
	       ar_check.add(ch_lunes);
	       ar_check.add(ch_martes);
	       ar_check.add(ch_miercoles);
	       ar_check.add(ch_jueves);
	       ar_check.add(ch_viernes);
	       ar_check.add(ch_sabado);
	       
	       ch_todos.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				
				Object source = e.getItemSelectable();
				if(ch_todos.isSelected()){
					ch_lunes.setSelected(true);
					ch_martes.setSelected(true);
					ch_miercoles.setSelected(true);
					ch_jueves.setSelected(true);
					ch_viernes.setSelected(true);
					ch_sabado.setSelected(true);
					
					
				}
				else{
					
					ch_lunes.setSelected(false);
					ch_martes.setSelected(false);
					ch_miercoles.setSelected(false);
					ch_jueves.setSelected(false);
					ch_viernes.setSelected(false);
					ch_sabado.setSelected(false);
					
				}
			}
	    	   
	    	  
	       });
	       
	       pDias_check.setOpaque(false);
	       
	       pDias_check.add(ch_lunes);
	       pDias_check.add(ch_martes);
	       pDias_check.add(ch_miercoles);
	       pDias_check.add(ch_jueves);
	       pDias_check.add(ch_viernes);
	       pDias_check.add(ch_sabado);
	       pDias_check.add(ch_todos);
			
	       borde_dias = BorderFactory.createTitledBorder(null, "Días de cobranza", 
	   	    	TitledBorder.CENTER, TitledBorder.TOP,
	   	    	new Font("Arial",Font.PLAIN,14), Color.BLACK);
	       pDias_check.setBorder(borde_dias);
	    
	       JLabel acuL = new JLabel();
	       acuL.setText("acu");
	       
	       pAcu.add(acuL);
	    pAcu.add(acuTF);
	    pAcu.add(guardar_acu);
	       
	    pIntegra.setBackground(Color.WHITE);
	   // pIntegra.add(pAcu);
	    pIntegra.add(labelsAndFields);
	    pIntegra.add(pDias_check);
	    
		
	    scr = new JScrollPane(pIntegra,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	    		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	   
	    desktop = new JDesktopPane();
	    
	    desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	    
	    JScrollPane scr_desktop = new JScrollPane();
	    //scr_desktop.setViewportView(desktop);
	    
	    JSplitPane pane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, 
                scr, desktop);
	    
	    pane.setMinimumSize(new Dimension(500,800));
		pane.setMaximumSize(new Dimension(800,800));
		//pane.setPreferredSize(getPreferredSize());
		//pane.setDividerLocation(270);
	    
	    this.add(pane);
	    
	    addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	System.out.println("ventana cerrandose */*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*" );
            	/*vista_buscar_cliente.getpPedidos().removeAll();
				vista_buscar_cliente.buscar_cliente();
				vista_buscar_cliente.updateUI();*/
                //e.getWindow().dispose();
				
            }
        });
	    
	    limpiar();
		
	}
	

	public void setPanelCliente(String cliente){
		
		Border borde0 = BorderFactory.createTitledBorder(null, cliente, 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,(int)width/100), Color.BLACK);
    			//panel.setBorder(borde0);
    			
    			panel.setBorder(BorderFactory.createCompoundBorder(
    					new EmptyBorder(10, 10, 10, 10), borde0));
    			//panel.setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
	public  JComponent getTwoColumnLayout(
            JLabel[] labels,
            JComponent[] fields,
            boolean addMnemonics, String cliente) {
        if (labels.length != fields.length) {
            String s = labels.length + " labels supplied for "
                    + fields.length + " fields!";
            throw new IllegalArgumentException(s);
        }
        panel = new JPanel();
        //panel = new PanelGraduado(new Color(234, 250, 241), new Color(212, 239, 223  )  );
        panel.setOpaque(false);
        Font fuente_titulo = new Font("Arial", Font.BOLD, (int)width/100);
        
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        panel.setBackground(new Color(255,255,255));
        // Turn on automatically adding gaps between components
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true); 
        // Create a sequential group for the horizontal axis.
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        
        hGroup.addContainerGap(20, 70);
       
        GroupLayout.Group yLabelGroup = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
        hGroup.addGroup(yLabelGroup);
        GroupLayout.Group yFieldGroup = layout.createParallelGroup();
        hGroup.addGroup(yFieldGroup);
        layout.setHorizontalGroup(hGroup);
        
        hGroup.addContainerGap(20, 70);
        // Create a sequential group for the vertical axis.
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        layout.setVerticalGroup(vGroup);

        int p = GroupLayout.PREFERRED_SIZE;
        // add the components to the groups
        
        for (JLabel label : labels) {
        	
        	label.setFont(new Font("Arial", Font.PLAIN, (int)width/100));
            yLabelGroup.addComponent(label);
           
        }
        
        for (Component field : fields) {
        	field.setFont(new Font("Arial", Font.PLAIN, (int)width/100));
            yFieldGroup.addComponent(field, p, p, p);
            
            field.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if (e.getClickCount() == 2 && !e.isConsumed()) {
				
					     //handle double click event.
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
            	
            	
            });
            
            field.addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					field.setBackground(new Color(183, 242, 113));
				
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					field.setBackground(new Color(255,255,255));
					
				}
            	
            	
            });
        }
        for (int ii = 0; ii < labels.length; ii++) {
            vGroup.addGroup(layout.createParallelGroup().
                    addComponent(labels[ii]).
                    addComponent(fields[ii], p, p, p));
        }

        if (addMnemonics) {
            addMnemonics(labels, fields);
        }

        return panel;
    }

    private final static void addMnemonics(
            JLabel[] labels,
            JComponent[] fields) {
        Map<Character, Object> m = new HashMap<Character, Object>();
        for (int ii = 0; ii < labels.length; ii++) {
            labels[ii].setLabelFor(fields[ii]);
            String lwr = labels[ii].getText().toLowerCase();
            for (int jj = 0; jj < lwr.length(); jj++) {
                char ch = lwr.charAt(jj);
                if (m.get(ch) == null && Character.isLetterOrDigit(ch)) {
                    m.put(ch, ch);
                    labels[ii].setDisplayedMnemonic(ch);
                    break;
                }
            }
        }
    }

    /**
     * Provides a JPanel with two columns (labels & fields) laid out using
     * GroupLayout. The arrays must be of equal size.
     *
     * @param labelStrings Strings that will be used for labels.
     * @param fields The corresponding fields.
     * @return JComponent A JPanel with two columns of the components provided.
     */
    public  JComponent getTwoColumnLayout(
            String[] labelStrings,
            JComponent[] fields, String cliente) {
        JLabel[] labels = new JLabel[labelStrings.length];
        for (int ii = 0; ii < labels.length; ii++) {
            labels[ii] = new JLabel(labelStrings[ii]);
        }
        return getTwoColumnLayout(labels, fields, cliente);
    }

    /**
     * Provides a JPanel with two columns (labels & fields) laid out using
     * GroupLayout. The arrays must be of equal size.
     *
     * @param labels The first column contains labels.
     * @param fields The last column contains fields.
     * @return JComponent A JPanel with two columns of the components provided.
     */
    public  JComponent getTwoColumnLayout(
            JLabel[] labels,
            JComponent[] fields, String cliente) {
        return getTwoColumnLayout(labels, fields, true, cliente);
    }

    public static String getProperty(String name) {
        return name + ": \t"
                + System.getProperty(name)
                + System.getProperty("line.separator");
    }
    
    public void setPedidosVO(PedidosVO _pedidosVO){
    	
    	this._pedidoVO = _pedidosVO;
    }
    
    public void setArticulosVO(ArticulosVO _articulosVO){
    	
    	this._articulosVO = _articulosVO;
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
    
    public PedidosVO getPedido_originalVO(){
    	
    	return pedido_originalVO;
    }
    
    public String getPlan_original(){
    	
    	return plan_original;
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
    
    
    public void setpPedidos(JPanel pPedidos){
    	
    	this.pPedidos = pPedidos;
    }
    
    public void setAuxArticulosVO(ArticulosVO aux_articulo){
    	
    	this.aux_articulo = aux_articulo;
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

	public void setAuxRef_in(Refinanciacion_inVO _ref_inVO){
    	
    	this.aux_ref_in = _ref_inVO;
    }
    
    public ArticulosVO getAuxArticulosVO(){
    	
    	return aux_articulo;
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
	
	public void setCifradoAction(CifradoAction ca){
		
		this.ca = ca;
	}
	
	public void setDescifradoAction(DescifradoAction da){
		
		this.da = da;
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
	
	public void setControladorUsuario(ControladorUsuario _controladorUsuario){
		
		this._controladorUsuario = _controladorUsuario;
	}
	
	public void setVistaNewObjetoVenta(VistaNewObjetoVenta vnov){
		
		this.vnov = vnov;
	}
	
	public void setVistaPrestamo(VistaPrestamo vista_prestamo){
		
		this.vista_prestamo = vista_prestamo;
	}
	
	public JTextField getCombinacion_diasTF(){
		
		return dias_cobranzaTF;
	}
	
	public JLabel getCombinacion_diasL(){
		
		return lDias_cobranza;
	}
	
	public JTextField getArticuloTF(){
		
		return articuloTF;
	}
	
	public JLabel getArticuloL(){
		
		return lArticulo;
	}
	
	public JLabel getMtL(){
		
		 return mtL;
	}
	
	public void setMtL(JLabel mt){
		
		mtL = mt;
	}
	
	public JLabel getDAL(){
		
		 return descuento_admL;
	}
	
	public void setDAL(JLabel da){
		
		descuento_admL = da;
	}
	
	public ArrayList<JTextField> getArrayString(){
		
		return stringTF;
	}
	
	public ArrayList<JDatePickerImpl> getArrayDatePicker(){
		
		return array_datepicker;
	}


	public ArrayList<JTextField> getArrayInt(){
		
		return int_doubleTF;
	}
	
	public JButton getJButtonRef_ex(){
		
		return ref_ex;
	}
	
	public JButton getJButtoObservaciones(){
		
		return observaciones;
	}
	
	public JButton getJButtonPagos(){
		
		return pagos;
	}
	public JButton getJButtonRetiros(){
		
		return retiros;
	}
	
	public JButton getJButtonCambio_plan(){
		
		return cambio_plan;
	}
	/*public DefaultTableModel getTabla(){
		
		return zonaCombo;
	}*/
	
	public JTextField getCuotaTF(){
		
		return cuotaTF;
	}
	
	public JTextField getDiasTF(){
		
		return diasTF;
	}
	
	public int getDni_anterior(){
		
		return dni_anterior;
	}
	
	public  JButton getAnularRef(){
		
		return anular_ref_ex;
	}
	
	public  JButton getAnularRef_in(){
		
		return anular_ref_in;
	}
	
	public JLabel getRefinanciacionL(){
		
		return refinanciacionL;
	}
	
	public JLabel getRefinanciacion_inL(){
		
		return refinanciacion_inL;
	}
	
	public void setRef_ex_boolean(boolean r){
		
		 refinanciacion_ex = r;
	}
	
	public void setRef_in_boolean(boolean r){
		
		 refinanciacion_in = r;
	}
	
	public JButton getRef_ex(){
		
		return ref_ex;
	}
	
	public JButton getRef_in(){
		
		return ref_in;
	}
	
	public JTextField getRefD(){
		
		return ref_dias;
	}
	
	public JTextField getRefC(){
		
		return ref_cuota;
	}

	public JTextField getRef_inD(){
	
		return ref_in_dias;
	}

	public JTextField getRef_inC(){
	
		return ref_in_dias;
	}
	
	public ArrayList<JTextField> getArrayRef(){
		
		return arRef;
	}
	
	public JPanel getpIntegra_monto(){
		
		return pIntegra_monto;
	}
	public Barra_herramientasPedidos getBh(){
		
		return bhp2;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
	    _busqueda_entities.setPanel("vista_buscarPedido_cliente");	
		
	    if(e.getSource()==guardar_acu){
	    	
	    	int res = _controladorPedido.updateAcumuladoMigracion(this.getPedidosVO().getN_pedido(), 
	    			acuTF);
	    	
	    	if(res > 0) Mensajes.getMensaje_altaExitosoGenerico();
	    	else Mensajes.getMensaje_altaErrorGenerico();
	    }
	    
		if(e.getSource() == buscar_articulo){
			articuloTF.requestFocus();
			_controladorArticulo.buscarArticuloAll();
			_controladorArticulo.mostrarBusquedaEntities();
			_busqueda_entities.setTipoBusqueda(7);
			//articuloTF.requestFocus();
			//comboTF.requestFocus();
			
		}
		
		if(e.getSource()==crear_articulo){
			
			//_busqueda_entities.setVistaNewObjetoVenta(vnov);
			
			vnov.setVisible(true);
		}
		
		if(e.getSource() == buscar_dias_cobranza){
			
			_controladorPedido.buscarCombinacionAll();
			_controladorPedido.mostrarBusquedaEntities();
			_busqueda_entities.setTipoBusqueda(6);
		}
		
		if(e.getSource() == modificar){
			
			habilita_datosPedidos(false, true,true,false,false,false, false,false,false, false, false, false, false,
					false, true,false,false,false,true,true,true,true, true,true,false, true, true, false);
		
			
			
			articuloTF.requestFocus();
		}
		
		if(e.getSource() == guardar){
			
			_controladorPedido.modificarPedidoUsuario(ar_check, arRef);
			
			
			if(LogicaPedido.vacio) Mensajes.getMensaje_vacio();
			if(LogicaPedido.excede_caracteres) Mensajes.getMensaje_excede();
			if(LogicaPedido.no_entero) Mensajes.getMensaje_no_entero();
			
			if(!LogicaPedido.validarModificacionUsuario){
				
				System.out.println("Error de usuario, no accede a bd");
					
			}
			else{
				
				System.out.println("Sin error, accede a validacion sistema");
				
				PedidosVO _pedidoVO = new PedidosVO();
				
				Refinanciacion_exVO refVO = new Refinanciacion_exVO();
				Refinanciacion_inVO ref_inVO = new Refinanciacion_inVO();
			
				if(refinanciacion_ex){
					
					refVO.setN_pedido(getPedidosVO().getN_pedido());
					refVO.setDias(Short.parseShort(ref_dias.getText()));
					refVO.setCuota_diaria(Double.parseDouble(ref_cuota.getText()));
					
					
					int res_cambio = _controladorRef_ex.modificarRefinanciacion_ex(refVO);
					
					if(res_cambio > 0){
								
							_controladorPedido.logicaGeneral(this._pedidoVO);
						
							mostrarPedido(this._pedidoVO);
							
					}
				}
				
				if(refinanciacion_in){
					
					ref_inVO.setN_pedido(getPedidosVO().getN_pedido());
					ref_inVO.setDias(Short.parseShort(ref_in_dias.getText()));
					ref_inVO.setCuota_diaria(Double.parseDouble(ref_in_cuota.getText()));
					
					
					int res_cambio = _controladorRef_in.modificarRefinanciacion_in(ref_inVO);
					
					if(res_cambio > 0){
							
						_controladorPedido.logicaGeneral(this._pedidoVO);
							mostrarPedido(this._pedidoVO);
							
					}
				}
				
				_pedidoVO.setN_pedido(Integer.parseInt(n_pedidoTF.getText()));
				_pedidoVO.setIdc(Integer.parseInt(idcCB.getSelectedItem().toString()));
				
				_controladorPedido.modificarIdc(_pedidoVO);
				
				double dDias = Double.parseDouble(diasTF.getText());
				double cCuota = Double.parseDouble(cuotaTF.getText());
				
				_pedidoVO.setEstado_pedido(lEstado_pedido.getText());
				_pedidoVO.setCredito(dDias * cCuota);
				//_pedidoVO.setId_combinacion(Short.parseShort(dias_cobranzaTF.getText()));
				
				String fecha_terminoS =fecha_termino.getJFormattedTextField().getText();
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
				Date date = new Date();
				try {
					date = format.parse(fecha_terminoS);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date fecha_terminoDate = new java.sql.Date(date.getTime());
				
				_pedidoVO.setFecha_termino(fecha_terminoDate);
				
//				int res = _controladorPedido.modificarPedido(_pedidoVO);
				
				int lunes = 0;
				int martes = 0;
				int miercoles = 0;
				int jueves = 0;
				int viernes = 0;
				int sabado = 0;
				
				if(ch_lunes.isSelected()) lunes = 1;
				else lunes = 0;
				if(ch_martes.isSelected()) martes = 2;
				else martes = 0;
				if(ch_miercoles.isSelected()) miercoles = 3;
				else miercoles = 0;
				if(ch_jueves.isSelected()) jueves = 4;
				else jueves = 0;
				if(ch_viernes.isSelected()) viernes = 5;
				else viernes = 0;
				if(ch_sabado.isSelected()) sabado = 6;
				else sabado = 0;
				
				int res = _controladorPedido.modificarPedido_diasCobranza(_pedidoVO.getN_pedido(), lunes,
						martes, miercoles, jueves, viernes, sabado);
				
				if(res > 0){
					
					Mensajes.getMensaje_modificacionExitosa();
					
					habilita_datosPedidos(false,false,false,false, false,false, false,false,false,false, false, false, false,
							false,false,false,false,false, false,false,false,false,
							false,false,true, false, false, true);
					
					/*vista_buscar_cliente.getpPedidos().removeAll();
					
					vista_buscar_cliente.buscarPedido(vista_buscar_cliente.getClienteVO(),
							vista_buscar_cliente.getpPedidos());
					
					vista_buscar_cliente.updateUI();*/
				}
				else Mensajes.getMensaje_modificacion_sinExito();
			
			}
			
			
		}
		
		if(e.getSource() == anular_ref_ex){
			
			
			int opcion = Mensajes.getMensaje_confirmacion_anulacion_generico();
			
			if(opcion == JOptionPane.YES_OPTION){
				
				int res = _controladorRef_ex.anularRef_ex(_pedidoVO.getN_pedido());
				
				if(res > 0){
					
					anular_ref_ex.setEnabled(false);
					Mensajes.getMensaje_anulacionExitosa();
					
					refinanciacion_ex = false;
						
					_controladorPedido.logicaGeneral(this._pedidoVO);
					mostrarPedido(this._pedidoVO);
						
					refinanciacionL.setText("");
					
				}
				else Mensajes.getMensaje_anulacionError();
			}
						
		}
		
		if(e.getSource() == anular_ref_in){
			
			
			int opcion = Mensajes.getMensaje_confirmacion_anulacion_generico();
			
			if(opcion == JOptionPane.YES_OPTION){
				
				int res = _controladorRef_in.anularRef_in(_pedidoVO.getN_pedido());
				
				if(res > 0){
					
					anular_ref_in.setEnabled(false);
					Mensajes.getMensaje_anulacionExitosa();
					
					refinanciacion_in = false;

					_controladorPedido.logicaGeneral(this._pedidoVO);
							
					mostrarPedido(this._pedidoVO);
						
					
					refinanciacion_inL.setText("");
					
					
				}
				else Mensajes.getMensaje_anulacionError();
			}
						
		}
		
		if(e.getSource() == ref_in){
			
			//this.setEnabled(false);
			
			//vf_in.setLocacion(ref_in);
			desktop.add(vf_in);
			vf_in.setVisible(true);
			vf_in.limpiar();
		}
		
		if(e.getSource() == ref_ex){
			
			//this.setEnabled(false);
			
			//vf.setLocacion(ref_ex);
			
			desktop.add(vf);
			vf.setVisible(true);
			vf.limpiar();
		}
		
		if(e.getSource() == monto_trasladado){
			
			desktop.add(mt);
			mt.setVisible(true);
			mt.limpiar();
		}
		
		if(e.getSource() == observaciones){
			
			ControladorObservaciones co = new ControladorObservaciones();
			LogicaObservaciones lo = new LogicaObservaciones();
			
			co.setLogicaObservaciones(lo);
			
			ArrayList<ObservacionesVO> ar = co.buscarObservacionesPedido
					(getPedidosVO().getN_pedido());
				
			
			VistaObservacionesPedido obser = new VistaObservacionesPedido();
			
			desktop.add(obser);
			obser.setVistaBuscarPedidos_porClientes(this);
			obser.setControladorObservaciones(co);
			obser.setControladorUsuario(_controladorUsuario);
			obser.iniciar(ar);
			obser.setVisible(true);
		
		}
		
		if(e.getSource() == pagos){
			
			
			ArrayList<Object []> ar = _controladorPagoDiario.seguimientoPagos
					(getPedidosVO().getN_pedido());
				desktop.add(v_pagos);
				v_pagos.limpiar();
				v_pagos.iniciar(ar);

		}
		
		if(e.getSource() == retiros){
	
			VistaContrasenas vc = new VistaContrasenas("pedido");
			vc.setControladorUsuario(_controladorUsuario);
			vc.setVisible(true);
			
			if(vc.getPermiso()){
				
				ControladorRetiros controladorRetiro = new ControladorRetiros();
				ControladorDA controladorDA = new ControladorDA();
				LogicaDA logDA = new LogicaDA();
				logDA.setControladorArticulo(_controladorArticulo);
				controladorDA.setLogicaDA(logDA);
				LogicaRetiro logica_retiro = new LogicaRetiro();
				controladorRetiro.setLogicaRetiro(logica_retiro);
					VistaRetiros v_retiros = new VistaRetiros();
					v_retiros.setControladorObservaciones(_controladorObservaciones);
					v_retiros.setControladorRetiros(controladorRetiro);
					v_retiros.setVistaBuscarPedidos_porClientes(this);
					v_retiros.setControladorArticulo(_controladorArticulo);
					v_retiros.setControladorCombo(_controladorCombo);
					v_retiros.setControladorPedidos(_controladorPedido);
					v_retiros.setVistaBuscarCliente(vista_buscar_cliente);
					v_retiros.setBusquedaEntities(_busqueda_entities);
					v_retiros.setControladorDA(controladorDA);
					v_retiros.limpiar();
					v_retiros.iniciar(_pedidoVO);
					desktop.add(v_retiros);
				
			}

		}
		
		if(e.getSource() == cambio_plan){
				
			VistaContrasenas vc = new VistaContrasenas("pedido");
			vc.setControladorUsuario(_controladorUsuario);
			vc.setVisible(true);
			
			if(vc.getPermiso()){
				
				VistaCambioArticulos_pedido vcap = new VistaCambioArticulos_pedido();
				
				Date d = new Date();
				java.sql.Date hoy = new java.sql.Date(d.getTime());
				java.sql.Time hora = new java.sql.Time(d.getTime());
			
				BusquedaEntities be = new BusquedaEntities();
				be.setVistaCambioArticulos_pedido(vcap);
				ControladorArticulo _controladorArticulo = new ControladorArticulo();
				ControladorDespacho_diario _controladorDP = new ControladorDespacho_diario();
				ControladorPagoDiario _controladorPG = new ControladorPagoDiario();
				ControladorCambio_plan ccp = new ControladorCambio_plan();
				ControladorDA cda= new ControladorDA();
				ControladorPedidos controladorPedido = new ControladorPedidos();
				ControladorPlan controladorPlan = new ControladorPlan();
				Logica_plan logicaPlan = new Logica_plan();
				LogicaPedido logicaPedido = new LogicaPedido();
				LogicaDespacho logica_despacho= new LogicaDespacho();
				LogicaPagoDiario logicaPago_diario = new LogicaPagoDiario();
				LogicaArticulo la = new LogicaArticulo();
				LogicaDA lda = new LogicaDA();
				LogicaCambio_plan _logica_cambioPlan = new LogicaCambio_plan();
				LogicaCombo lc = new LogicaCombo();

				/*vnp.setVistaBuscarPedidos_porClientes(this);
				vnp.setBusquedaEntities(be);
				vnp.setControladorArticulo(_controladorArticulo);
				vnp.setControladorCambio_plan(ccp);
				vnp.setControladorPedido(_controladorPedido);
				vnp.setVistaBuscarCliente(vista_buscar_cliente);
				
				vnp.iniciar(this._pedidoVO.getN_pedido());
				vnp.setVisible(true);*/
				
				ccp.setLogicaCambio_plan(_logica_cambioPlan);
				_controladorArticulo.setLogicaArticulo(la);
				_controladorArticulo.setBusquedaEntities(be);
				controladorPlan.setLogicaPlan(logicaPlan);
				controladorPedido.setLogicaPedido(logicaPedido);
				cda.setLogicaDA(lda);
				_controladorDP.setLogicaDespacho(logica_despacho);
				_controladorPG.setLogicaPagoDiario(logicaPago_diario);
				lda.setControladorArticulo(_controladorArticulo);
				
				la.setBusquedaEntities(be);
				la.setControladorArticulo(_controladorArticulo);
				logicaPedido.setControladorArticulo(_controladorArticulo);
				logicaPedido.setControladorPedido(controladorPedido);
				logicaPedido.setControladorDA(cda);
				logicaPedido.setControladorPagoDiario(_controladorPG);
				logicaPedido.setVistaBuscarPedidos_porClientes(this);
				
				vcap.setBusquedaEntities(be);
				vcap.setControladorArticulo(_controladorArticulo);
				vcap.setVistaBuscarPedidos_porCliente(this);
				vcap.setControladorPlan(controladorPlan);
				vcap.setControladorPedido(controladorPedido);
				vcap.setControladorCliente(_controladorCliente);
				vcap.setControladorCambio_plan(ccp);
				vcap.setControladorDA(cda);
				vcap.setVistaBuscarCliente(vista_buscar_cliente);
				
				vcap.limpiar();
				vcap.iniciar(_pedidoVO);
				//vcap.setVisible(true);
				desktop.add(vcap);
			}
			
		}
		
		if(e.getSource() == salir){
			//_vista_principal.setEnabled(true);
			this.dispose();
			
		}
		
		if(e.getSource() == cancelar){
			
			 mostrarPedido(_pedidoVO);
			 
		}
		
		if(e.getSource() == anular){
		
			VistaContrasenas vc = new VistaContrasenas("pedido");
			vc.setControladorUsuario(_controladorUsuario);
			vc.setVisible(true);
			
			if(vc.getPermiso()){
				
				int opcion = Mensajes.getMensaje_confirmacion_anulacion();
				
				if(opcion == JOptionPane.YES_OPTION){
					
					int res = _controladorPedido.update_estadoPedido(_pedidoVO.getN_pedido(), "baja");
					
					java.util.Date d = new java.util.Date();
					java.sql.Date hoy = new java.sql.Date(d.getTime());
					
					int res_f = _controladorPedido.update_fechatermino(_pedidoVO.getN_pedido(), hoy);
					
					if(res > 0){
						
						Mensajes.getMensaje_anulacionExitosa();
						
						this.dispose();
					}
					else Mensajes.getMensaje_anulacionError();
				}
				
			}	
			
		}
		
	}
	
	public void mostrarPedido(PedidosVO _pedidoVO){
		limpiar();
		 labelsAndFields.getInputMap(labelsAndFields.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke(KeyEvent.VK_O,
		                java.awt.event.InputEvent.CTRL_DOWN_MASK
		                | java.awt.event.InputEvent.SHIFT_DOWN_MASK),
		        "actionMapKey1");
		 
			    labelsAndFields.getActionMap().put("actionMapKey1",
		         da);
		   
			    labelsAndFields.getInputMap(labelsAndFields.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_H,
			                java.awt.event.InputEvent.CTRL_DOWN_MASK
			                | java.awt.event.InputEvent.SHIFT_DOWN_MASK),
			        "actionMapKey2");
				    labelsAndFields.getActionMap().put("actionMapKey2",
			         ca);
				    
		PedidosVO pVO = _controladorPedido.buscarPedido_porNpedido(_pedidoVO.getN_pedido());		    
				    
		diasTF.setText(Integer.toString(pVO.getDias()));
		cuotaTF.setText(Double.toString(pVO.getCuota_diaria()));
		 
		_controladorPedido.mostrarDetalleMonto(_pedidoVO);
		_controladorPedido.mostrarDetalleDA(_pedidoVO);
		//_controladorPedido.actualizarLabelMonto_t(_pedidoVO);
		
		Refinanciacion_exDAO refDAO = new Refinanciacion_exDAO();
		
		try {
			Refinanciacion_exVO rVO = refDAO.buscarRef(_pedidoVO.getN_pedido());
				
			if(rVO!=null && rVO.getEstado()){
				
				ref_cuota.setText(Double.toString(_pedidoVO.getCuota_diaria()));
				ref_dias.setText(Integer.toString(_pedidoVO.getDias()));
				if(Principal._usuario.getPermiso()==1){
					
					anular_ref_ex.setEnabled(true);
					ref_ex.setEnabled(false);
					
				}
				this.refinanciacionL.setText("Refinanciado");
				
			}
			else{
				
				ref_cuota.setText("0");
				ref_dias.setText("0");
				if(Principal._usuario.getPermiso()==1){
					
					anular_ref_ex.setEnabled(false);
					ref_ex.setEnabled(true);
					
				}
				this.refinanciacionL.setText("");
				
			}
					
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		Refinanciacion_inDAO ref_inDAO = new Refinanciacion_inDAO();
		
		try {
			Refinanciacion_inVO rVO = ref_inDAO.buscarRef(_pedidoVO.getN_pedido());
				
			if(rVO!=null && rVO.getEstado()){
				
				ref_in_cuota.setText(Double.toString(rVO.getCuota_diaria()));
				ref_in_dias.setText(Integer.toString(rVO.getDias()));
				if(Principal._usuario.getPermiso()==1){
					
					anular_ref_in.setEnabled(true);
					ref_in.setEnabled(false);
					
				}
				this.refinanciacion_inL.setText("Ref interna");
				
			}
			else{
				
				ref_in_cuota.setText("0");
				ref_in_dias.setText("0");
				if(Principal._usuario.getPermiso()==1){
					
					anular_ref_in.setEnabled(false);
					ref_in.setEnabled(true);
					
				}
				this.refinanciacion_inL.setText("");
			}
			
					
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		 
		n_pedidoTF.setText(Integer.toString(_pedidoVO.getN_pedido()));
		
		ArrayList<DomicilioComercialVO> ar_dc = _controladorDomCom.buscarDomicilioComercial2(
				Integer.toString(_pedidoVO.getDni()));
		
		cb_model.removeAllElements();
		
		if(ar_dc!=null){
			
			for(DomicilioComercialVO dc : ar_dc){
				
				idcCB.addItem(dc.getIdc());
			}
			int index = 0;
			for(int i = 0; i < idcCB.getItemCount(); i++){
				
				if(Integer.parseInt(idcCB.getItemAt(i).toString())==_pedidoVO.getIdc()){
					
					idcCB.setSelectedIndex(i);
				}
				
			}
		}
		
		//articuloTF.setText(Integer.toString(_articuloVO.getCodigo()));
		//lArticulo.setText(_articuloVO.getNombre());
	
		//ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(Integer.parseInt
		//		(articuloTF.getText()));
		
		ArrayList<Pedido_articuloVO> ar_pedido = _controladorPedido.
				buscarArticulos_porPedido(_pedidoVO.getN_pedido(), true);
		
		//if(apVO!=null) lDescripcion.setText("$" + Double.toString(apVO.getMonto()));
		
		if(ar_pedido!=null){
			
			String descripcion = "<html>";
			
			for(Pedido_articuloVO paVO : ar_pedido){
				
				ArticulosVO aVO = _controladorArticulo.
						buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
				
				ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
				
				if(apVO!=null){
					
						descripcion = descripcion + "<br/>" + 
								"(" + apVO.getCodigo() + 
								")" + aVO.getNombre() + " $" + Double.toString(apVO.getMonto());
						
				}
				/*else if(paVO.getCantidad()>1)
					descripcion = descripcion + "<br/>" + aVO.getNombre() +
					"(" + aVO.getCodigo() + ")" + "x" + paVO.getCantidad();*/
				else
					descripcion = descripcion + "<br/>" +
							"(" + aVO.getCodigo() + ")" + aVO.getNombre()+ " " + aVO.getDescripcion();
			}
			
				descripcion = descripcion + "</html>";
			
				
			lDescripcion.setText(descripcion);
		}
		
		dias_desde_inicioTF.setText(Integer.toString(_pedidoVO.getDias_desde_inicio()));
		
		lEstado_pedido.setText(_pedidoVO.getEstado_pedido());
		lEstado_pedido.setFont(new Font("Arial", Font.BOLD, (int)width/100));
		
		switch(lEstado_pedido.getText()){
		
			case "baja": lEstado_pedido.setForeground(Color.red);
			break;
			case "finalizado": lEstado_pedido.setForeground(Color.BLUE);
			break;	
			case "pendiente entrega": lEstado_pedido.setForeground(Color.ORANGE);
			break;
			case "activo": lEstado_pedido.setForeground(Color.GREEN);
			break;	
		}
		
		estado_deudaTF.setText(Double.toString(_pedidoVO.getEstado_deuda()));
			
		dias_moraTF.setText(Integer.toString(_pedidoVO.getDias_mora()));
		resto_dias_moraTF.setText(Double.toString(_pedidoVO.getResto_dias_mora()));
		facturacionTF.setText(Double.toString(_pedidoVO.getFacturacion()));
		creditoTF.setText(Double.toString(_pedidoVO.getCredito()));	
		saldoTF.setText(Double.toString(_pedidoVO.getCredito() - _pedidoVO.getFacturacion()));
		//interesTF.setText(Double.toString(_pedidoVO.getInteres()));
		modelFinicio.setValue(_pedidoVO.getFecha_inicio());
		modelFinicio.setSelected(true);
		modelFtermino.setValue(_pedidoVO.getFecha_termino());
		modelFtermino.setSelected(true);
		modelFtermino_ideal.setValue(_pedidoVO.getFecha_terminoIdeal());
		modelFtermino_ideal.setSelected(true);
		//fecha_nacimiento
		//dias_cobranzaTF.setText(Integer.toString(_pedidoVO.getId_combinacion()));
		
		/*if(_controladorPedido.buscarCombinacion(dias_cobranzaTF.getText())!=null){
			
			Combinacion_diasVO _combinacion_diasVO = _controladorPedido.buscarCombinacion
					(dias_cobranzaTF.getText());
			
			String dias_cobranzaS = "";
			
			if(_combinacion_diasVO.getLunes()) dias_cobranzaS = dias_cobranzaS + " Lunes "; 
			if(_combinacion_diasVO.getMartes()) dias_cobranzaS = dias_cobranzaS + " Martes ";
			if(_combinacion_diasVO.getMiercoles()) dias_cobranzaS = dias_cobranzaS + " Miércoles ";
			if(_combinacion_diasVO.getJueves()) dias_cobranzaS = dias_cobranzaS + " Jueves ";
			if(_combinacion_diasVO.getViernes()) dias_cobranzaS = dias_cobranzaS + " Viernes ";
			if(_combinacion_diasVO.getSabado()) dias_cobranzaS = dias_cobranzaS + " Sábado ";
			
			lDias_cobranza.setText(dias_cobranzaS);
		}
			
			
		else{
			lDias_cobranza.setText("");
			dias_cobranzaTF.setText("");
		}*/
		
		ArrayList<Pedidos_diasVO> arPa = _controladorPedido.buscarPedido_dias(_pedidoVO.getN_pedido());
		
		if(arPa != null){
			
			for(Pedidos_diasVO pdVO : arPa){
				
				switch(pdVO.getN_dia()){
				
				case 1: ch_lunes.setSelected(true);
				break;
				case 2: ch_martes.setSelected(true);
				break;
				case 3: ch_miercoles.setSelected(true);
				break;
				case 4: ch_jueves.setSelected(true);
				break;
				case 5: ch_viernes.setSelected(true);
				break;
				case 6: ch_sabado.setSelected(true);
				break;
				}
			}
		}
		
		UsuariosVO uVO = _controladorUsuario.buscarUsuario_porID(_pedidoVO.getId_usuario());
		
		lUsuario.setText(uVO.getNombre());
		lFecha_registro.setText(_pedidoVO.getFecha_registro().toString());
		lHora_registro.setText(_pedidoVO.getHora_registro().toString());
		
		ref_cuota.setEnabled(false);
	    ref_dias.setEnabled(false);
	    ref_in_cuota.setEnabled(false);
	    ref_in_dias.setEnabled(false);
		
	    this.labelsAndFields.updateUI();  
	       
		formato_disable();
		
		habilita_datosPedidos(false,false,false, false,false, false, false,false,false,false, false, false, false,
				false,false,false,false,false, false,false,false,false,
				false,false,true, false, false, true);
		
	}
	
	
	public void limpiar(){
		
		for(JTextField tf : getArrayString()){
			
			tf.setText("");
		}
		
		for(JTextField tf : getArrayInt()){
			
			tf.setText("");
		}
		
		for(JCheckBox ch : ar_check){
			
			ch.setSelected(false);
		}
		ch_todos.setSelected(false);
		lUsuario.setText("");
		lArticulo.setText(""); 
		lDias_cobranza.setText(""); 	
		lFecha_registro.setText("");
		lHora_registro.setText("");
	
		pIntegra_monto.removeAll();
		
		habilita_datosPedidos(false,false,false, false,false, false, false,false,false,false, false, false, false,
				false,false,false,false,false,false, false, false,false,
				false, false,true, false, false, true);
		
		
	}
	
	public void formato_disable(){
		
		for(JTextField tf : getArrayString()){
			
			tf.setDisabledTextColor(new Color(113,125,126));
			//tf.setFont(new Font("Arial", Font.BOLD, 24));
		}
		
		for(JTextField tf : getArrayInt()){
			
			tf.setDisabledTextColor(new Color(113,125,126));
			//tf.setFont(new Font("Arial", Font.BOLD, 24));
		}
		
		for(JTextField tf : arRef){
			
			tf.setDisabledTextColor(new Color(113,125,126));
			//tf.setFont(new Font("Arial", Font.BOLD, 24));
		}
		
	}
	
	public void setControladorPedidos(ControladorPedidos _controladorPedido){
		
		this._controladorPedido = _controladorPedido;
	}
	
	public void habilita_datosPedidos(boolean n_pedido, boolean idc,boolean articulo,boolean cantidad,
			boolean ddi,boolean dm,
			boolean rdm,
			boolean estado_deuda,boolean dias,boolean cuota,boolean facturacion, boolean credito, boolean saldo,boolean interes,
			boolean dias_cobranza, boolean fecha_inicio,boolean fecha_termino,boolean fecha_termino_ideal,
			boolean buscar_articulo, 
			boolean bdc, boolean rfc, boolean rfd, boolean rf_inc, boolean rf_ind, 
			boolean b_modificar, boolean b_guardar,/* boolean ref_in,
			boolean ref_ex,*/ boolean b_cancelar, boolean b_eliminar){
		
		n_pedidoTF.setEnabled(n_pedido);
		this.idcCB.setEnabled(idc);
		articuloTF.setEnabled(articulo);
	
		//cantidadTF.setEnabled(cantidad);
		dias_desde_inicioTF.setEnabled(ddi);
		dias_moraTF.setEnabled(dm);
		resto_dias_moraTF.setEnabled(rdm);
		estado_deudaTF.setEnabled(estado_deuda);
		diasTF.setEnabled(dias);
		cuotaTF.setEnabled(cuota);
		facturacionTF.setEnabled(facturacion);
		creditoTF.setEnabled(credito);
		saldoTF.setEnabled(saldo);
		interesTF.setEnabled(interes);
		//dias_cobranzaTF.setEnabled(dias_cobranza);
		setPanelEnabled(pDias_check, dias_cobranza);
		this.fecha_inicio.getComponent(1).setEnabled(fecha_inicio);
		this.fecha_termino.getComponent(1).setEnabled(fecha_termino);
		this.fecha_termino_ideal.getComponent(1).setEnabled(fecha_termino_ideal);
		
		this.buscar_articulo.setEnabled(buscar_articulo);
		buscar_dias_cobranza.setEnabled(bdc);
		
		if(refinanciacion_ex && Principal._usuario.getPermiso()==1){
			
			ref_cuota.setEnabled(rfc);
			ref_dias.setEnabled(rfd);
			
		}
		else System.out.println("false");
		
		if(refinanciacion_in && Principal._usuario.getPermiso()==1){
			
			ref_in_cuota.setEnabled(rf_inc);
			ref_in_dias.setEnabled(rf_ind);
		}
		
		guardar.setEnabled(b_guardar);
		modificar.setEnabled(b_modificar);
		/*this.ref_in.setEnabled(ref_in);
		this.ref_ex.setEnabled(ref_ex);*/
		cancelar.setEnabled(b_cancelar);
		
		if(Principal._usuario.getPermiso()==1)
			anular.setEnabled(b_eliminar);
	}

	public void setPlan_original(PedidosVO _pedidoVO){
		
		pedido_originalVO.setN_pedido(_pedidoVO.getN_pedido());
		pedido_originalVO.setDni(_pedidoVO.getDni());
		pedido_originalVO.setDias(_pedidoVO.getDias());
		pedido_originalVO.setCuota_diaria(_pedidoVO.getCuota_diaria());
		pedido_originalVO.setEstado_pedido(_pedidoVO.getEstado_pedido());
		//pedido_originalVO.setId_combinacion(_pedidoVO.getId_combinacion());
		pedido_originalVO.setFacturacion(_pedidoVO.getFacturacion());
		pedido_originalVO.setFecha_inicio(_pedidoVO.getFecha_inicio());
		pedido_originalVO.setFecha_termino(_pedidoVO.getFecha_termino());
		pedido_originalVO.setId_usuario(_pedidoVO.getId_usuario());
		pedido_originalVO.setFecha_registro(_pedidoVO.getFecha_registro());
		pedido_originalVO.setHora_registro(_pedidoVO.getHora_registro());
		
		plan_original = " ";
	
			ArrayList<Pedido_articuloVO> ar_pedidos = _controladorPedido.
					buscarArticulos_porPedido(getPedidosVO().getN_pedido(), true);
			
			if(ar_pedidos!=null){
				
				for(Pedido_articuloVO paVO : ar_pedidos){
					
					
					Object [] datos = new Object[2];
										
					ArticulosVO aVO = _controladorArticulo.
							buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
			
					datos [0] = aVO.getCodigo();
					
					if(aVO.getNombre().equals("Prestamo")){
						
						ArticulosPVO apVO = _controladorArticulo.
								buscarArticulo_enPrestamo(paVO.getCodigo_articulo());
					
						if(apVO!=null)
						
							datos [1] = aVO.getNombre() + "$ "+ Double.toString(apVO.getMonto());
					}
					else
						datos [1] = aVO.getNombre();
					
					plan_original = plan_original + " " + datos[1] + "(" + aVO.getCodigo() + ")" + 
					 "[" + paVO.getDias() + "x" + paVO.getCuota() +"]";
					
					/*if(paVO.getCantidad()>1){
						
						plan_original = plan_original + "x" + paVO.getCantidad(); 
						
						for(int i = 1; i < paVO.getCantidad(); i++){
							
							Object [] datos2 = new Object[2];
							
							datos2 [0] = aVO.getCodigo();
							datos2 [1] = aVO.getNombre();
							
						}
							
					}*/
				}
				
				
			}
			
			plan_original = plan_original + " #" + pedido_originalVO.getDias() + "x" +
			pedido_originalVO.getCuota_diaria();
			
			System.out.println(plan_original);
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
	
	public void setVista_pagos_porPedido(Vista_pagos_porPedido vpp){
		
		this.v_pagos = vpp;
	}
	
	void setPanelEnabled(JPanel panel, Boolean isEnabled) {
	    panel.setEnabled(isEnabled);

	    Component[] components = panel.getComponents();

	    for(int i = 0; i < components.length; i++) {
	        if(components[i].getClass().getName() == "javax.swing.JPanel") {
	            setPanelEnabled((JPanel) components[i], isEnabled);
	        }

	        components[i].setEnabled(isEnabled);
	    }
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
