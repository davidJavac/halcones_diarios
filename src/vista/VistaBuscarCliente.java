package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdesktop.swingx.border.DropShadowBorder;

import controlador.ControladorArticulo;
import controlador.ControladorCliente;
import controlador.ControladorCombo;
import controlador.ControladorDA;
import controlador.ControladorDomicilioComercial;
import controlador.ControladorDomicilioParticular;
import controlador.ControladorEmpleado;
import controlador.ControladorLocalidad;
import controlador.ControladorMonto_trasladado;
import controlador.ControladorObservaciones;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorPlan;
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
import modelo.LogicaCliente;
import modelo.LogicaCombo;
import modelo.LogicaDA;
import modelo.LogicaDomicilioComercial;
import modelo.LogicaDomicilioParticular;
import modelo.LogicaEmpleado;
import modelo.LogicaLocalidad;
import modelo.LogicaMonto_trasladado;
import modelo.LogicaObservaciones;
import modelo.LogicaPagoDiario;
import modelo.LogicaPedido;
import modelo.LogicaPrestamo;
import modelo.LogicaRefinanciacion_ex;
import modelo.LogicaRefinanciacion_in;
import modelo.LogicaUsuario;
import modelo.LogicaVendedor;
import modelo.LogicaZona;
import modelo.Logica_plan;
import modelo.Mensajes;
import modelo_dao.Monto_trasladadoDAO;
import modelo_dao.Refinanciacion_exDAO;
import modelo_dao.Refinanciacion_inDAO;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.ComercioVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.Monto_trasladadoVO;
import modelo_vo.Observaciones_clienteVO;
import modelo_vo.PedidosVO;
import modelo_vo.Refinanciacion_exVO;
import modelo_vo.Refinanciacion_inVO;
import modelo_vo.UsuariosVO;
import modelo_vo.VerazVO;
import modelo_vo.ZonaVO;
import vista.BH_pedidosGeneral.CustomJToolTip;

public class VistaBuscarCliente extends JPanel implements ActionListener{

	private int dni_anterior;
	
	private JTextField dniTF;
	private JTextField nombreTF;
	private JTextField apellidoTF;
	private JTextField nacionalidadTF;
	private JTextField dni_conyugueTF;
	private JTextField nombre_conyugueTF;
	private JTextField apellido_conyugueTF;
	private JTextField telefono_conyugueTF;
	private JTextField email_conyugueTF;
	private JRadioButton soltero;
	private JRadioButton casado;
	private JDatePickerImpl fecha_nacimiento;
	private ButtonGroup grupoEstadoCivil;
	private JTextField telefono_movilTF;
	private JTextField telefono_lineaTF;
	private JTextField emailTF;
	private JTextField id_vendedorTF;
	private JTextField id_zonaTF;
	
	private JTextField domicilio_partTF;
	private JTextField entre_calle1TF;
	private JTextField entre_calle2TF;
	private JRadioButton siDpto;
	private JRadioButton noDpto;
	private ButtonGroup grupoDpto;
	private JTextField pisoTF;
	private JTextField barrioTF;
	private JTextField cpTF;
	private JTextField id_localidadTF;
	private JDatePickerImpl antiguedadTF;
	private JRadioButton propio;
	private JRadioButton alquila;
	private ButtonGroup grupoPropioAlquila;
	
	private JTextField domicilio_comTF;
	private JTextField entre_calle1ComercialTF;
	private JTextField entre_calle2ComercialTF;
	private JTextField barrioComercialTF;
	private JTextField cpComercialTF;
	private JTextField id_localidadComercialTF;
	private JDatePickerImpl antiguedadComercialTF;
	private JTextField comercioTF;
	private JRadioButton propioComercial;
	private JRadioButton alquilaComercial;
	
	private ButtonGroup grupoPropioAlquilaComercial;
	
	private JComboBox idcCB = new JComboBox();
	private DefaultComboBoxModel cb_model = new DefaultComboBoxModel();
	
	private JSpinner spinnerDesde;
	private JSpinner spinnerHasta;
	private  JSpinner.DateEditor deD;
	private JSpinner.DateEditor deH;
	private Horario desdeH;
	private Horario hastaH;
	ButtonsInTextField bitf;
	
	private ClienteVO cliente_orVO;
	private DomicilioParticularVO dom_part_orVO;
	private ArrayList<DomicilioComercialVO> dom_com_orVO;
	
	private ArrayList<JTextField> stringTF;
	private ArrayList<JTextField> intTF;
	private ArrayList<JTextField> stringTFDomPart;
	private ArrayList<JTextField> intTFDomPart;
	private ArrayList<JTextField> stringTFDomCom;
	private ArrayList<JTextField> intTFDomCom;
	private ArrayList<JTextField> array_conyugue;
	
	private ArrayList<JDatePickerImpl> array_datepicker;
	
	private JButton modificar = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
	private JButton buscar = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
	private JButton guardar = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
	private JButton cancelar = new JButton("Cancelar");
    private JButton nueva_consulta = new JButton(){
    	//override the JButtons createToolTip method
    	@Override
    	public JToolTip createToolTip() {
    		return (new CustomJToolTip(this));
    	}
    };;
	private JButton baja= new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
    private JButton habilitar= new JButton(){
    	//override the JButtons createToolTip method
    	@Override
    	public JToolTip createToolTip() {
    		return (new CustomJToolTip(this));
    	}
    };;
    private JButton observaciones= new JButton(){
    	//override the JButtons createToolTip method
    	@Override
    	public JToolTip createToolTip() {
    		return (new CustomJToolTip(this));
    	}
    };;
    private JButton nuevo_idc = new JButton(){
    	//override the JButtons createToolTip method
    	@Override
    	public JToolTip createToolTip() {
    		return (new CustomJToolTip(this));
    	}
    };;
    private JButton actualizar= new JButton(){
    	//override the JButtons createToolTip method
    	@Override
    	public JToolTip createToolTip() {
    		return (new CustomJToolTip(this));
    	}
    };;
	
	private ControladorCliente _controladorCliente;
	private ControladorDomicilioParticular _controladorDomPart;
	private ControladorDomicilioComercial _controladorDomCom;
	
	private JButton buscar_cliente = new JButton("...");
	private JButton buscar_zona = new JButton("...");
	private JButton buscar_localidadPart = new JButton("...");
	private JButton buscar_localidadCom = new JButton("...");
	private JButton buscar_vendedor = new JButton("...");
	private JButton buscar_comercio = new JButton("...");

	private JButton nuevo_pedidoB = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
    private JButton historial_pedidosB = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
	
    private  BH_pedidosGeneral bh;
    
	private ControladorVendedor _controladorVendedor;
	private ControladorZona _controladorZona;
	private ControladorLocalidad _controladorLocalidad;
	private ControladorPedidos _controladorPedido;
	private BusquedaEntities _busqueda_entities;
	private VistaBuscarPedidos_porClientes vp;
	private VistaPrincipal _vista_principal;
	
	private ControladorRefinanciacion_ex _controladorRef_ex;
	
	private ControladorRefinanciacion_in _controladorRef_in;
	//vpc.setControladorRefinanciacion_in(_controladorRef_in);
	//----------------------------------------------------------------------
	private ControladorPagoDiario _controladorPagoDiario;
	private ControladorArticulo _controladorArticulo;
	private ControladorPrestamo _controladorPrestamo;
	private ControladorEmpleado controladorEmpleado;
	private VistaPrincipal _vistaPrincipal;
	private VistaNewObjetoVenta vista_combo;
	private VistaPrestamo vista_prestamo;
	private ControladorCombo _controladorCombo;
	private ControladorMonto_trasladado _controladorMonto_t;
	private LogicaMonto_trasladado _logicaMonto_t;
	private VistaRefinanciacion vf;
	private VistaRefinanciacion_in vf_in;
	private VistaMonto_t mt;
	private VistaObservacionesPedido obser;
	private Vista_pagos_porPedido vpp;
	private VistaAltaPedido vap;
	
	  static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      static double width = screenSize.getWidth();
      double height = screenSize.getHeight();
	
	//---------------------------------------------------------------------
	
	public void setControladorEmpleado (ControladorEmpleado _controladorEmpleado){
		
		this.controladorEmpleado = _controladorEmpleado;
	}
	
	public void setControladorPagoDiario (ControladorPagoDiario _controladorPagoDiario){
		
		this._controladorPagoDiario = _controladorPagoDiario;
	}
	
	public void setControladorArticulo (ControladorArticulo _controladorArticulo){
		
		this._controladorArticulo = _controladorArticulo;
	}
	
	public void setControladorPrestamo (ControladorPrestamo _controladorPrestamo){
		
		this._controladorPrestamo = _controladorPrestamo;
	}
	
	public void setVistaCombo (VistaNewObjetoVenta vista_combo){
		
		this.vista_combo = vista_combo;
	}
	
	public void setVistaPrestamo (VistaPrestamo vista_prestamo){
		
		this.vista_prestamo = vista_prestamo;
	}

	public void setControladorCombo (ControladorCombo _controladorCombo){
		
		this._controladorCombo = _controladorCombo;
	}
	
	public void setVistaRefinanciacion (VistaRefinanciacion vf){
		
		this.vf = vf;
	}
	
	public void setVistaRefinanciacion_in (VistaRefinanciacion_in vf_in){
		
		this.vf_in = vf_in;
	}
	
	public void setVistaMonto_t (VistaMonto_t mt){
		
		this.mt =mt;
	}
	
	public void setVistaObservaciones (VistaObservacionesPedido obser){
		
		this.obser =obser;
	}
	
	public void setVista_pagosPedido (Vista_pagos_porPedido vpp){
		
		this.vpp =vpp;
	}
	
	public void setControladorRefinanciacion_ex (ControladorRefinanciacion_ex  _controladorRef_ex){
		
		this._controladorRef_ex =_controladorRef_ex;
	}
	
	public void setControladorRefinanciacion_in (ControladorRefinanciacion_in  _controladorRef_in){
		
		this._controladorRef_in =_controladorRef_in;
	}	
	
	private VistaPrincipal vista_principal;
	
	private Cartas panel_cartas;
	
	private JPanel pBarra;
	private JPanel pDatospersonales;
	private JPanel pDomPart;
	private JPanel pDomCom;
	private JPanel pIntegra;
	
	private JPanel pEstadocivil;
	private JPanel pDpto;
	private JPanel pAlquilaPart;
	private JPanel pAlquilaCom;
	private JPanel pHorario_atencion;
	private JPanel pId_dni;
	private JPanel pId_zona;
	private JPanel pId_localidadPart;
	private JPanel pId_localidadCom;
	private JPanel pId_vendedor;
	private JPanel pId_comercio;
	
	private JPanel pPedidos;
	private JPanel pHistorial_pedidos;
	private JPanel pIntegra_pedidos;
	
	private JLabel titulo_barra;
	
	private JLabel lLocalidadPart, lLocalidadCom, lCobradorZona, lVendedor, lComercio;
	private JLabel lEstado;
	private static int contador_panel;
	
	UtilDateModel modelFN = new UtilDateModel();
	UtilDateModel modelAP = new UtilDateModel();
	UtilDateModel modelAC = new UtilDateModel();

	private ClienteVO aux_cliente;
	
	public static boolean open = false;
	
	public boolean encriptado = false;
	
	public VistaBuscarCliente(){
		
		guardar.addActionListener(this);
		modificar.addActionListener(this);
		cancelar.addActionListener(this);
		buscar.addActionListener(this);
		nueva_consulta.addActionListener(this);
		baja.addActionListener(this);
		habilitar.addActionListener(this);
		observaciones.addActionListener(this);
		nuevo_idc.addActionListener(this);
		actualizar.addActionListener(this);
		nuevo_pedidoB.addActionListener(this);
		historial_pedidosB.addActionListener(this);
		
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
  		
  		id_zonaTF = new JTextField(5);
  		id_zonaTF.setFont(new Font("Arial", Font.PLAIN, 16));
  		lCobradorZona = new JLabel();
  		
  		id_zonaTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				id_zonaTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(_controladorZona.buscarZonaUsuario(id_zonaTF.getText())!=null){
					
					ZonaVO zVO = _controladorZona.buscarZonaUsuario(id_zonaTF.getText());
					
					EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.
							toString(zVO.getId_cobrador()));
					
					lCobradorZona.setText(eVO.getNombre() + " " + eVO.getApellido());
				}
				else{
					lCobradorZona.setText("");
					id_zonaTF.setText("");
				}
					
				
				id_zonaTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
  		pId_zona = new JPanel();
		pId_localidadPart = new JPanel();
		pId_localidadCom = new JPanel();
		pId_vendedor = new JPanel();
  		//buscar_zona.setPreferredSize(new Dimension(2, 2));
  		pId_zona.setLayout(gl);
  		pId_zona.add(id_zonaTF);
  		pId_zona.add(buscar_zona);
  		
  		lLocalidadPart = new JLabel();
  		lLocalidadCom = new JLabel();
  		
  		id_localidadTF = new JTextField(5);
  		id_localidadTF.setFont(new Font("Arial", Font.PLAIN, 16));
  		
  		id_localidadTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				id_localidadTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(_controladorLocalidad.buscarLocalidadUsuario(id_localidadTF.getText())!=null)
					
					lLocalidadPart.setText(_controladorLocalidad.buscarLocalidadUsuario(id_localidadTF.getText()));
				else{
					
					lLocalidadPart.setText("");
					id_localidadTF.setText("");
				}
					
				
				id_localidadTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
  		
  		//buscar_zona.setPreferredSize(new Dimension(2, 2));
  		pId_localidadPart.setLayout(gl);
  		pId_localidadPart.add(id_localidadTF);
  		pId_localidadPart.add(buscar_localidadPart);
  		//pId_localidadPart.add(lLocalidadPart);
  		
  		id_localidadComercialTF = new JTextField(5);
  		id_localidadComercialTF.setFont(new Font("Arial", Font.PLAIN, 16));
  		
  		id_localidadComercialTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				id_localidadComercialTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(_controladorLocalidad.buscarLocalidadUsuario(id_localidadComercialTF.getText())!=null)
					
					lLocalidadCom.setText(_controladorLocalidad.buscarLocalidadUsuario
							(id_localidadComercialTF.getText()));
				else{
					
					lLocalidadCom.setText("");
					id_localidadComercialTF.setText("");
				}
					
				
				id_localidadComercialTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
  		//buscar_zona.setPreferredSize(new Dimension(2, 2));
  		
  		//pId_localidadCom.add(lLocalidadCom);
  		pId_localidadCom.setLayout(gl);
  		pId_localidadCom.add(id_localidadComercialTF);
  		pId_localidadCom.add(buscar_localidadCom);
  		
  		lVendedor = new JLabel();
  		
  		id_vendedorTF = new JTextField(5);
  		id_vendedorTF .setFont(new Font("Arial", Font.PLAIN, 16));
  		
  		id_vendedorTF .addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				id_vendedorTF .setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(_controladorVendedor.buscarVendedorUsuario(id_vendedorTF.getText())!=null)
					
					lVendedor.setText(_controladorVendedor.buscarVendedorUsuario
							(id_vendedorTF.getText()));
				else{
					
					lVendedor.setText("");
					id_vendedorTF.setText("");
				}
					
				
				id_vendedorTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
  		//buscar_zona.setPreferredSize(new Dimension(2, 2));
  		pId_vendedor.setLayout(gl);
  		pId_vendedor.add(id_vendedorTF);
  		pId_vendedor.add(buscar_vendedor);
  		
  		
  		pId_comercio = new JPanel();
  		lComercio = new JLabel();
  		
  		comercioTF = new JTextField(5);
  		comercioTF.setFont(new Font("Arial", Font.PLAIN, 16));
  		comercioTF .addFocusListener(new FocusListener(){
  			
  			@Override
  			public void focusGained(FocusEvent e) {
  				// TODO Auto-generated method stub
  				comercioTF .setBackground(new Color(183, 242, 113));
  			}
  			
  			@Override
  			public void focusLost(FocusEvent e) {
  				// TODO Auto-generated method stub
  				if(_controladorDomCom.buscarComercio(comercioTF.getText())!=null){
  					
  					ComercioVO cVO = _controladorDomCom.buscarComercio(comercioTF.getText());
  					lComercio.setText(cVO.getDescripcion());
  					
  				}
  					
  				else{
  					
  					lComercio.setText("");
  					comercioTF.setText("");
  				}
  				
  				
  				comercioTF.setBackground(new Color(255, 255, 255));
  			}
  			
  			
  		});
  		pId_comercio.setLayout(gl);
  		pId_comercio.add(comercioTF);
  		pId_comercio.add(buscar_comercio);
  		
  		pId_dni = new JPanel();
  		dniTF = new JTextField(7);
  		dniTF .setFont(new Font("Arial", Font.PLAIN, 16));
  		dniTF.setBackground(new Color(183, 242, 113));
  		dniTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				dniTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				//if(_controladorLocalidad.buscarLocalidadUsuario(dniTF.getText())!=null)
					
					//lLocalidadCom.setText(_controladorCliente.buscarLocalidadUsuario
							//(dniTF.getText()));
				//else{
					
					//dniTF.setText("");
				//}
					
				
				dniTF.setBackground(new Color(255, 255, 255));
			}
  			
  		
  		});
  		
  		pId_dni.setLayout(gl);
  		pId_dni.add(dniTF);
  		
  		pId_dni.add(buscar_cliente);
  		
  		BH_cliente bh_cliente = new BH_cliente(guardar, modificar, cancelar, buscar, nueva_consulta,
  				baja, habilitar, observaciones, nuevo_idc, actualizar);
  		
  		
  		this.add(bh_cliente, BorderLayout.PAGE_START);
  		
		pIntegra = new JPanel();
		
		pIntegra.setLayout(new BoxLayout(pIntegra, 	BoxLayout.Y_AXIS));
		
		buscar_zona.addActionListener(this);
		buscar_localidadPart.addActionListener(this);
		buscar_localidadCom.addActionListener(this);
		buscar_vendedor.addActionListener(this);
		buscar_comercio.addActionListener(this);
		buscar_cliente.addActionListener(this);
		
		
        //model.setDate(20,04,2014);
  		Properties p = new Properties();
  		p.put("text.today", "Today");
  		p.put("text.month", "Month");
  		p.put("text.year", "Year");
        JDatePanelImpl datePanelFN = new JDatePanelImpl(modelFN, p);
        JDatePanelImpl datePanelAP = new JDatePanelImpl(modelAP, p);
        JDatePanelImpl datePanelAC = new JDatePanelImpl(modelAC, p);
		
        Date date = new Date();
        SpinnerDateModel smD = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        SpinnerDateModel smH = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        
        desdeH = new Horario(smD, "Desde");
        hastaH = new Horario(smH, "Hasta");
        
        lEstado = new JLabel();
        
		 JComponent[] components = {
              
                 pId_dni,
         		nombreTF = new JTextField(15),
         		apellidoTF = new JTextField(15),
         		nacionalidadTF = new JTextField(15),
         		dni_conyugueTF = new JTextField(15),
         		nombre_conyugueTF = new JTextField(15),
         		apellido_conyugueTF = new JTextField(15),
         				telefono_conyugueTF = new JTextField(15),
                 		email_conyugueTF = new JTextField(15),
         		pEstadocivil = new JPanel(),
         		fecha_nacimiento = new JDatePickerImpl(datePanelFN, new DateLabelFormatter()),
         		  		
         		telefono_movilTF = new JTextField(15),
         		telefono_lineaTF = new JTextField(15),
         		emailTF = new JTextField(15),
         		pId_vendedor,
         		lVendedor,
         		lEstado
         		
         		
         		
             };
		
		 String labels [] = {
				 
				"DNI", "Nombre", "Apellido", "Nacionalidad",
				"DNI conyugue", "Nombre coyugue", "Apellido conyugue","Teléfono conyugue",
				"Email conyugue",
				"Estado civil","Fecha de nacimiento",
				 "Telefono 1", "Teléfono 2",
				"Email", "Vendedor","", "Estado"
		 };
		 
		 JComponent[] dom_part_components = {
	              
				 	domicilio_partTF = new JTextField(15),
			  		entre_calle1TF = new JTextField(15),
			  		entre_calle2TF = new JTextField(15),
			  		pDpto = new JPanel(),
			  		pisoTF = new JTextField(15),
			  		barrioTF = new JTextField(15),
			  		cpTF = new JTextField(15),
			  		pId_localidadPart,
			  		lLocalidadPart,
			  		antiguedadTF = new JDatePickerImpl(datePanelAP, new DateLabelFormatter()),
			  		pAlquilaPart = new JPanel()
         		
         		
             };
		
		 String dom_part_labels [] = {
				 
				"Domicilio", "Entre calle1", "Entre calle2",
				"Dpto",
				"Piso", "Barrio", "CP",
				 "Localidad","", "Antiguedad", "Propio"	
		 };
		 
		 bitf = new ButtonsInTextField(2);
		 
		 idcCB.setModel(cb_model);
		 
		 idcCB.addItemListener(new ItemListener(){

		/*	@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}*/

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				
				 if (e.getStateChange() == ItemEvent.SELECTED) {
					 String idc = String.valueOf(idcCB.getSelectedItem());
					 /*ArrayList<DomicilioComercialVO> ar_dc = _controladorDomCom.
						buscarDomicilioComercial(dniTF.getText());
			
				dom_com_orVO = ar_dc;*/
					 
					 if(dom_com_orVO!=null){
						 
						 for(DomicilioComercialVO dc : dom_com_orVO){
							 
							 if(dc.getIdc()==Integer.parseInt(idc)){
								 
								 cargar_dc(dc);
							 }
						 }
						 
					 }
					 
				 }
				
			}
			 
			 
		 });
		 
		 JComponent[] dom_com_components = {
	              
				 	idcCB,
				 	pId_zona,
				 	lCobradorZona,
				 	domicilio_comTF = new JTextField(15),
			  		entre_calle1ComercialTF = new JTextField(15),
			  		entre_calle2ComercialTF = new JTextField(15),
			  		barrioComercialTF = new JTextField(15),
			  		cpComercialTF = new JTextField(15),
			  		pId_localidadCom,
			  		lLocalidadCom,
			  		antiguedadComercialTF =  new JDatePickerImpl(datePanelAC, new DateLabelFormatter()),
			  		pId_comercio,
			  		lComercio,
			  		pAlquilaCom = new JPanel(),
			  		pHorario_atencion = new JPanel(),
			  		//bitf.getGui()
         		  		
             };
		
		 String dom_com_labels [] = {
				 
				"IDC", "Zona", "Cobrador", "Domicilio", "Entre calle1", "Entre calle2",
				 "Barrio", "CP",
				 "Localidad","", "Antiguedad", "Comercio","", "Propio", "Hr atención"	
		 };
		 
		 JFormattedTextField fn = fecha_nacimiento.getJFormattedTextField();
		 fn.setFont(new Font("Arial", Font.PLAIN, (int)width/90));
		 JFormattedTextField antPart = antiguedadTF.getJFormattedTextField();
		 antPart.setFont(new Font("Arial", Font.PLAIN, (int)width/90));
		 JFormattedTextField antCom = antiguedadComercialTF.getJFormattedTextField();
		 antCom.setFont(new Font("Arial", Font.PLAIN, (int)width/90));
		 
		 
	     siDpto = new JRadioButton("Si");
	     noDpto = new JRadioButton("No");
	     siDpto.setFont(new Font("Arial", Font.PLAIN, 16));
	     noDpto.setFont(new Font("Arial", Font.PLAIN, 16));
	     noDpto.setSelected(true);
	     grupoDpto = new ButtonGroup();
	     grupoDpto.add(noDpto);
	     grupoDpto.add(siDpto);
	     pDpto.add(noDpto);
	     pDpto.add(siDpto);
	     
  		 propio = new JRadioButton("Si");
  		 alquila = new JRadioButton("No");
  		 propio.setFont(new Font("Arial", Font.PLAIN, 16));
	     alquila.setFont(new Font("Arial", Font.PLAIN, 16));
	     propio.setSelected(true);
  		 grupoPropioAlquila = new ButtonGroup();
  		 grupoPropioAlquila.add(propio);
  		 grupoPropioAlquila.add(alquila);
  		 pAlquilaPart.add(propio);
  		 pAlquilaPart.add(alquila);
  		
  		
  		propioComercial = new JRadioButton("Si");
  		alquilaComercial = new JRadioButton("No");
  		propioComercial.setFont(new Font("Arial", Font.PLAIN, 16));
	    alquilaComercial.setFont(new Font("Arial", Font.PLAIN, 16));
	    propioComercial.setSelected(true);
		grupoPropioAlquilaComercial = new ButtonGroup();
		grupoPropioAlquilaComercial.add(propioComercial);
		grupoPropioAlquilaComercial.add(alquilaComercial);
		pAlquilaCom.add(propioComercial);
		pAlquilaCom.add(alquilaComercial);

  		soltero = new JRadioButton("Soltero");
        casado = new JRadioButton("Casado");
        soltero.setFont(new Font("Arial", Font.PLAIN, 16));
        //soltero.setSelected(true);
        casado.setFont(new Font("Arial", Font.PLAIN, 16));
        grupoEstadoCivil = new ButtonGroup();
  		grupoEstadoCivil.add(soltero);
  		grupoEstadoCivil.add(casado);
  		pEstadocivil.add(soltero);
  		pEstadocivil.add(casado);
  		
  		dni_conyugueTF.setEnabled(false);
  		nombre_conyugueTF.setEnabled(false);
  		apellido_conyugueTF.setEnabled(false);
  		telefono_conyugueTF.setEnabled(false);
  		email_conyugueTF.setEnabled(false);
  		
  		soltero.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(soltero.isSelected()){
					
					dni_conyugueTF.setEnabled(false);
					nombre_conyugueTF.setEnabled(false);
					apellido_conyugueTF.setEnabled(false);
					telefono_conyugueTF.setEnabled(false);
			  		email_conyugueTF.setEnabled(false);
				}
			}
  			
  		});
  		casado.addActionListener(new ActionListener(){
  			
  			@Override
  			public void actionPerformed(ActionEvent e) {
  				// TODO Auto-generated method stub
  				
  				if(casado.isSelected()){
  					
  					dni_conyugueTF.setEnabled(true);
  					nombre_conyugueTF.setEnabled(true);
  					apellido_conyugueTF.setEnabled(true);
  					telefono_conyugueTF.setEnabled(true);
  			  		email_conyugueTF.setEnabled(true);
  				}
  			}
  			
  		});
  		
  		pHorario_atencion.add(desdeH);
  		pHorario_atencion.add(hastaH);
  		
  		
  		
  		pisoTF.setEditable(false);
  		siDpto.addActionListener(this);
		noDpto.addActionListener(this);
        
		stringTF = new ArrayList<JTextField>();
		
		stringTF.add(nombreTF);
		stringTF.add(apellidoTF);
		stringTF.add(nacionalidadTF);
		//stringTF.add(nombre_conyugueTF);
		//stringTF.add(apellido_conyugueTF);
		stringTF.add(telefono_movilTF);
		stringTF.add(telefono_lineaTF);
		stringTF.add(emailTF);
		stringTF.add(fecha_nacimiento.getJFormattedTextField());
		stringTF.add(antiguedadTF.getJFormattedTextField());
		stringTF.add(antiguedadComercialTF.getJFormattedTextField());
		
		intTF = new ArrayList<JTextField>();
		
		intTF.add(dniTF);
		//intTF.add(dni_conyugueTF);
		intTF.add(id_zonaTF);
		intTF.add(id_vendedorTF);
		intTF.add(comercioTF);
		
		stringTFDomPart = new ArrayList<JTextField>();
		
		stringTFDomPart.add(domicilio_partTF);
		stringTFDomPart.add(entre_calle1TF);
		stringTFDomPart.add(entre_calle2TF);
		stringTFDomPart.add(barrioTF);
		
		intTFDomPart = new ArrayList<JTextField>();
	
		//intTFDomPart.add(pisoTF);
		intTFDomPart.add(id_localidadTF);
		intTFDomPart.add(cpTF);
		
		stringTFDomCom = new ArrayList<JTextField>();
		
		stringTFDomCom.add(domicilio_comTF);
		stringTFDomCom.add(entre_calle1ComercialTF);
		stringTFDomCom.add(entre_calle2ComercialTF);
		stringTFDomCom.add(barrioComercialTF);
		//stringTFDomCom.add(comercioTF);
		
		intTFDomCom = new ArrayList<JTextField>();
	
		intTFDomCom.add(id_localidadComercialTF);
		intTFDomCom.add(cpComercialTF);
		
		array_conyugue = new ArrayList<JTextField>();
		
		array_conyugue.add(dni_conyugueTF);
		array_conyugue.add(nombre_conyugueTF);
		array_conyugue.add(apellido_conyugueTF);
		array_conyugue.add(telefono_conyugueTF);
		array_conyugue.add(email_conyugueTF);
		
		array_datepicker = new ArrayList<JDatePickerImpl>();
		
		array_datepicker.add(fecha_nacimiento);
		array_datepicker.add(antiguedadTF);
		array_datepicker.add(antiguedadComercialTF);
		
		titulo_barra = new JLabel("Buscar cliente");
		
		Font fuenteB = new Font("Verdana", Font.PLAIN, 20);
		
		titulo_barra.setFont(fuenteB);
		
		pBarra.add(titulo_barra);
		
		contador_panel = 0;
		
	    JComponent labelsAndFields = getTwoColumnLayout(labels,components);
		
		//add(new JLabel("Datos personales", SwingConstants.CENTER)/*, BorderLayout.PAGE_START*/);
		
		//add(labelsAndFields, BorderLayout.WEST);
		
		 JComponent labelsAndFields_dom_part = getTwoColumnLayout(dom_part_labels, dom_part_components);
		
		 //add(labelsAndFields_dom_part, BorderLayout.CENTER);
		 
		 JComponent labelsAndFields_dom_com = getTwoColumnLayout(dom_com_labels, dom_com_components);
		 
		 JPanel pDatos = new JPanel();
		 pDatos.setLayout(new GridLayout(1, 3));
	
		 
		 pDatos.add(labelsAndFields);
		 pDatos.add(labelsAndFields_dom_part);
		 pDatos.add(labelsAndFields_dom_com);
		 pDatos.setBackground(Color.WHITE);
		 pDatos.setBorder(new EmptyBorder(20,5,20,5));
		 pDatos.setPreferredSize(new Dimension((int)width*80/100,(int)height*80/100));
		 
		 JScrollPane scrDatos = new JScrollPane();
		 scrDatos.setPreferredSize(new Dimension((int)width*60/100,(int)height*40/100));
		 scrDatos.setViewportView(pDatos);
		 
		 add(scrDatos, BorderLayout.CENTER);
		 
		 pIntegra.add(pBarra);
		 
		 add(pIntegra, BorderLayout.NORTH);
			
		 pPedidos = new JPanel();
		 
		 pPedidos.setName("pPedidos");
		 
		 pPedidos.setPreferredSize(new Dimension(800, 800));
		 
		 Border bPedido = BorderFactory.createTitledBorder(null, "Pedidos", 
	     			TitledBorder.CENTER, TitledBorder.TOP,
	     			new Font("Arial",Font.BOLD,(int) width/95), Color.BLACK);
	     			pPedidos.setBorder(bPedido);
	     pPedidos.setBackground(new Color(255,255,255));
	     
	    // JScrollPane scr = new JScrollPane(pPedidos, JScrollPane.VERTICAL_SCROLLBAR_NEVER, 
	    		// JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	     
	     JScrollPane scr = new JScrollPane();
	     
	     scr.setViewportView(pPedidos);
	     
	     scr.setPreferredSize(new Dimension(600, 100));
	     
	     pHistorial_pedidos = new JPanel();
		 
		 Border bHPedido = BorderFactory.createTitledBorder(null, "Historial de pedidos", 
	     			TitledBorder.CENTER, TitledBorder.TOP,
	     			new Font("Arial",Font.BOLD,20), Color.BLACK);
		 pHistorial_pedidos.setBorder(bHPedido);
		 pHistorial_pedidos.setBackground(new Color(255,255,255));
	     
		 pIntegra_pedidos = new JPanel();
		 
		 pIntegra_pedidos.setLayout(new BoxLayout(pIntegra_pedidos, BoxLayout.Y_AXIS));
		  
		 bh = new BH_pedidosGeneral(nuevo_pedidoB, historial_pedidosB);
		 
		 bh.getNuevo_pedidoB().setEnabled(false);
		 bh.getHistorial_pedidos().setEnabled(false);
		 
		 pIntegra_pedidos.add(bh);
		 pIntegra_pedidos.add(scr);
		// pIntegra_pedidos.add(pHistorial_pedidos);
		 
		
		 
	     add(pIntegra_pedidos, BorderLayout.SOUTH);
	     
	     
		 limpiar();
	}

	
	
	public static JComponent getTwoColumnLayout(
            JLabel[] labels,
            JComponent[] fields,
            boolean addMnemonics) {
        if (labels.length != fields.length) {
            String s = labels.length + " labels supplied for "
                    + fields.length + " fields!";
            throw new IllegalArgumentException(s);
        }
        JComponent panel = new JPanel();
        
        Font fuente_titulo = new Font("Arial", Font.BOLD, 20);
  
      
        
        switch(contador_panel){
        
        	case 0: Border borde0 = BorderFactory.createTitledBorder(null, "Datos personales", 
        			TitledBorder.CENTER, TitledBorder.TOP,
        			new Font("Arial",Font.BOLD,(int) width/90), Color.BLACK);
        			panel.setBorder(borde0);
        			
        	break;		
        			
        	case 1: Border borde1 = BorderFactory.createTitledBorder(null, "Domicilio particular", 
        			TitledBorder.CENTER, TitledBorder.TOP,
        			new Font("Arial",Font.BOLD,(int) width/90), Color.BLACK);
			panel.setBorder(borde1);
			
			break;
			
        	case 2: Border borde2 = BorderFactory.createTitledBorder(null, "Domicilio comercial", 
        			TitledBorder.CENTER, TitledBorder.TOP,
        			new Font("Arial",Font.BOLD,(int) width/90), Color.BLACK);
			panel.setBorder(borde2);
			
			break;
        }
        
        contador_panel++;
        
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
        	
        	label.setFont(new Font("Arial", Font.PLAIN, (int) width/90));
        	//label.setFont(new Font("Arial", Font.PLAIN, 20));
            yLabelGroup.addComponent(label);
        }
        
        for (Component field : fields) {
        	field.setFont(new Font("Arial", Font.PLAIN, (int) width/90));
        	//field.setFont(new Font("Arial", Font.PLAIN, 20));
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
    public static JComponent getTwoColumnLayout(
            String[] labelStrings,
            JComponent[] fields) {
        JLabel[] labels = new JLabel[labelStrings.length];
        for (int ii = 0; ii < labels.length; ii++) {
            labels[ii] = new JLabel(labelStrings[ii]);
        }
        return getTwoColumnLayout(labels, fields);
    }

    /**
     * Provides a JPanel with two columns (labels & fields) laid out using
     * GroupLayout. The arrays must be of equal size.
     *
     * @param labels The first column contains labels.
     * @param fields The last column contains fields.
     * @return JComponent A JPanel with two columns of the components provided.
     */
    public static JComponent getTwoColumnLayout(
            JLabel[] labels,
            JComponent[] fields) {
        return getTwoColumnLayout(labels, fields, true);
    }

    public static String getProperty(String name) {
        return name + ": \t"
                + System.getProperty(name)
                + System.getProperty("line.separator");
    }
	
    public void setCarta(Cartas panel_cartas){
		
		this.panel_cartas = panel_cartas;
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
	
	public void setClienteVO(ClienteVO cVO){
		
		aux_cliente = cVO;
	}
	
	public ClienteVO getClienteVO(){
		
		return aux_cliente;
	}
	public DomicilioParticularVO getDomPVO(){
		
		return dom_part_orVO;
	}
	public ArrayList<DomicilioComercialVO> getDomCVO(){
		
		return dom_com_orVO;
	}
	
	
	public JPanel getpPedidos(){
		
		return pPedidos;
	}
	
	public JTextField getEmail(){
		
		return emailTF;
	}
	
	public ArrayList<JDatePickerImpl> getArrayDatePicker(){
		
		return array_datepicker;
	}
	
	public ArrayList<JTextField> getArrayString(){
		
		return stringTF;
	}
	
	public ArrayList<JTextField> getArrayInt(){
		
		return intTF;
	}
	
	public ArrayList<JTextField> getArrayStringDomPart(){
		
		return stringTFDomPart;
	}
	
	public ArrayList<JTextField> getArrayIntDomPart(){
		
		return intTFDomPart;
	}
	
	public ArrayList<JTextField> getArrayStringDomCom(){
		
		return stringTFDomCom;
	}
	
	public ArrayList<JTextField> getArrayIntDomCom(){
		
		return intTFDomCom;
	}
	public ArrayList<JTextField> getArrayConyugue(){
		
		return array_conyugue;
	}
	
	public JRadioButton getCasado(){
		
		return casado;
	}
	
	/*public DefaultTableModel getTabla(){
		
		return zonaCombo;
	}*/
	
	public JTextField getZonaTF(){
		
		return id_zonaTF;
	}
	
	public JTextField getLocalidadPartTF(){
		
		return id_localidadTF;
	}
	
	public JTextField getLocalidadComTF(){
	
		return id_localidadComercialTF;
	}
	
	public JLabel getLocalidadPartL(){
		
		return lLocalidadPart;
	}
	
	public JLabel getLocalidadComL(){
		
		return lLocalidadCom;
	}
	
	public JLabel getCobradorZonaL(){
		
		return lCobradorZona;
	}
	
	public JTextField getClienteTF(){
		
		return dniTF;
	}
	
	public JTextField getVendedorTF(){
		
		return id_vendedorTF;
	}
	
	public JLabel getVendedorL(){
		
		return lVendedor;
	}
	
	public JTextField getComercioTF(){
		
		return comercioTF;
	}
	
	public JLabel getComercioL(){
		
		return lComercio;
	}
	
	public int getDni_anterior(){
		
		return dni_anterior;
	}
	
	public boolean getEncriptado(){
    	
    	return encriptado;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
	_busqueda_entities.setPanel("vista_buscar_cliente");	
		
	if(e.getSource() == siDpto){
			
			pisoTF.setEditable(true);
			intTFDomPart.add(pisoTF);
		}
		
		if(e.getSource() == noDpto){
			
			pisoTF.setText("");
			pisoTF.setEditable(false);
			intTFDomPart.remove(pisoTF);
		}
		
		if(e.getSource() == buscar_zona){
			
			_controladorZona.buscarZonaAll();
			_controladorZona.mostrarBusquedaEntities();
		}
		
		if(e.getSource() == buscar_localidadPart){
			
			_busqueda_entities.setTipoBusqueda(2);
			_controladorLocalidad.buscarLocalidadAll();
			_controladorLocalidad.mostrarBusquedaEntities();
		}
		
		if(e.getSource() == buscar_localidadCom){
			
			_busqueda_entities.setTipoBusqueda(3);
			_controladorLocalidad.buscarLocalidadAll();
			_controladorLocalidad.mostrarBusquedaEntities();
		}
		
		if(e.getSource() == buscar_vendedor){
			
			_controladorVendedor.buscarVendedorAll();
			_controladorVendedor.mostrarBusquedaEntities();
			_busqueda_entities.setTipoBusqueda(4);
		}
		if(e.getSource() == buscar_comercio){
			
			_controladorDomCom.buscarComercioAll();
			_controladorDomCom.mostrarBusquedaEntities("Buscar comercio");
			_busqueda_entities.setTipoBusqueda(21);
		}
		
		if(e.getSource() == buscar_cliente){
			_busqueda_entities.setTablaModel();
			//_controladorCliente.buscarClienteAll();
			_controladorCliente.mostrarBusquedaEntities();
			_busqueda_entities.setTipoBusqueda(5);
			_busqueda_entities.limpiar();
		}
		
		if(e.getSource() == nueva_consulta){

			limpiar();
			cb_model.removeAllElements();
			pPedidos.removeAll();
			pPedidos.updateUI();
			bh.getNuevo_pedidoB().setEnabled(false);
			bh.getHistorial_pedidos().setEnabled(false);
			if(this.getClienteVO().getEstado().equals("baja")) habilitar.setEnabled(false);

		}
		
		if(e.getSource() == buscar){

			buscar_cliente();
		}
		
		if(e.getSource() == modificar){
			
			habilita_datosPersonales(true, true, true, true,true, true, true,true,true, true, true,
					true, true, true, true, true,true,true, true,true,
					true, false, true, false, false, false, false, false,false, false);
			
			habilita_datosDomPart(
					true, true,true,true,true, true,true, true, true, true, true,
					true,true, true);
			
			habilita_datosDomCom(
					true, true,true,true, true,true, true, true, true, true, true,
					true, true,true, true);
		}
		
		if(e.getSource()==cancelar){
			
			limpiar();
			habilita_datosPersonales(false, false, false, false,false, false,false, false, false, false, false,false,
					false, false,false, false, false, false,false,false,
					false, true, false, false, true, true,false, true,true, true);
			pPedidos.removeAll();
			mostrarCliente(cliente_orVO, dom_part_orVO);
			cargar_dc(dom_com_orVO.get(0));
			
		}
		
		if(e.getSource() == guardar){
			
			_controladorCliente.modificarClienteUsuario(this);
			_controladorDomPart.modificarDomicilioParticularUsuario(this);
			_controladorDomCom.modificarDomicilioComercialUsuario(this);
			
			if(LogicaCliente.vacio) Mensajes.getMensaje_vacio();
			else{		
				if(LogicaCliente.no_entero) Mensajes.getMensaje_no_entero();
				if(LogicaCliente.excede_caracteres) Mensajes.getMensaje_excede();
				if(LogicaCliente.error_email) Mensajes.getMensaje_error_email();
			}
			
			
			if(!LogicaCliente.validarmodificacionUsuario){
				
				System.out.println("Error de usuario, no accede a bd");
					
			}
			else{
				
				System.out.println("Sin error, accede a validacion sistema");
				
				ClienteVO _clienteVO = new ClienteVO();
				DomicilioParticularVO dpVO = new DomicilioParticularVO();
				DomicilioComercialVO dcVO = new DomicilioComercialVO();
				
				_clienteVO.setDni(Integer.parseInt(dniTF.getText()));
				_clienteVO.setNombre(nombreTF.getText());
				_clienteVO.setApellido(apellidoTF.getText());
				_clienteVO.setNacionalidad(nacionalidadTF.getText());
				_clienteVO.setDni_conyugue(Integer.parseInt(dni_conyugueTF.getText()));
				_clienteVO.setNombre_conyugue(nombre_conyugueTF.getText());
				_clienteVO.setApellido_conyugue(apellido_conyugueTF.getText());

				if(soltero.isSelected()) {
					
					_clienteVO.setEstado_civil(true);
		
				}
				else{
					
					_clienteVO.setEstado_civil(false);		
					_clienteVO.setDni_conyugue(Integer.parseInt(dni_conyugueTF.getText()));
					_clienteVO.setNombre_conyugue(nombre_conyugueTF.getText());
					_clienteVO.setApellido_conyugue(apellido_conyugueTF.getText());
					_clienteVO.setTelefono_conyugue(telefono_conyugueTF.getText());
					_clienteVO.setEmail_conyugue(email_conyugueTF.getText());
				}
				
				
				_clienteVO.setTelefono_movil(telefono_movilTF.getText());
				_clienteVO.setTelefono_linea(telefono_lineaTF.getText());
				_clienteVO.setEmail(emailTF.getText());
				_clienteVO.setId_vendedor(Short.parseShort(id_vendedorTF.getText()));
				//_clienteVO.setN_orden_planilla((short) 1);
				//_clienteVO.setEstado("Revision");
				
				String fecha_nacimientoS =fecha_nacimiento.getJFormattedTextField().getText();
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
				Date dateFN = new Date();
				try {
					dateFN = format.parse(fecha_nacimientoS);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date fecha_nacimientoDate = new java.sql.Date(dateFN.getTime());
				
				_clienteVO.setFecha_nacimiento(fecha_nacimientoDate);
				
				Date d = new java.util.Date();
				java.sql.Date f = new java.sql.Date(d.getTime());
				java.sql.Time h = new java.sql.Time(d.getTime());
				
				//_clienteVO.setFecha_alta(f);
				//_clienteVO.setId_usuario(id_usuario);
				//_clienteVO.setFecha_registro(f);
				//_clienteVO.setHora_registro(h);
				
				dpVO.setDni(Integer.parseInt(dniTF.getText()));
				dpVO.setDomicilio(domicilio_partTF.getText());
				dpVO.setEntre_calle1(entre_calle1TF.getText());
				dpVO.setEntre_calle2(entre_calle2TF.getText());
				
				if(siDpto.isSelected())
					
					dpVO.setDpto(true);
				else
					dpVO.setDpto(false);
				
				dpVO.setPiso(Short.parseShort(pisoTF.getText()));
				dpVO.setBarrio(barrioTF.getText());
				dpVO.setCp(Integer.parseInt(cpTF.getText()));
				dpVO.setId_localidad(Short.parseShort(id_localidadTF.getText()));
				
				
				String antiguedadPartS =antiguedadTF.getJFormattedTextField().getText();
				DateFormat formatPart = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
				Date datePart = new Date();
				try {
					datePart = formatPart.parse(antiguedadPartS);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date antiguedadPartDate = new java.sql.Date(datePart.getTime());
				
				
				dpVO.setAntiguedad(antiguedadPartDate);
				
				if(propio.isSelected())
					
					dpVO.setPropio(true);
				else
					dpVO.setPropio(false);
				
				
				int idc = Integer.parseInt(String.valueOf(idcCB.getSelectedItem()));
				
				dcVO.setIdc(idc);
				dcVO.setId_zona(Short.parseShort(id_zonaTF.getText()));
				dcVO.setDomicilio(domicilio_comTF.getText());
				dcVO.setEntre_calle1(entre_calle1ComercialTF.getText());
				dcVO.setEntre_calle2(entre_calle2ComercialTF.getText());
				dcVO.setBarrio(barrioComercialTF.getText());
				dcVO.setId_localidad(Short.parseShort(id_localidadComercialTF.getText()));
				dcVO.setCp(Integer.parseInt(cpComercialTF.getText()));
				
				String antiguedadComS =antiguedadComercialTF.getJFormattedTextField().getText();
				DateFormat formatCom = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
				Date dateCom = new Date();
				try {
					dateCom = formatCom.parse(antiguedadComS);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date antiguedadComDate = new java.sql.Date(dateCom.getTime());
				
				dcVO.setAntiguedad(antiguedadComDate);
				dcVO.setComercio(Integer.parseInt(comercioTF.getText()));
				
				if(propioComercial.isSelected())
					
					dcVO.setPropio(true);
				else
					dcVO.setPropio(false);
				
				
				
				
				dcVO.setHorario_atencion(Horario.getHorario(desdeH, hastaH));
							
				/*List a = new ArrayList();
				
				a = _controladorCliente.altaClienteVeraz(_clienteVO, dpVO, dcVO);*/
						
				
				//if(LogicaCliente.validaraltaVeraz){
					
					System.out.println("cliente no figura en veraz continua validacion...");
					
					int res = 0;
					
					res = _controladorCliente.modificarCliente(_clienteVO);
					
					boolean error = false;
					
					if(res > 0){
							
						habilita_datosPersonales(false, false, false, false,false, false, false, false,
								false,false, false,false,
								 false, false, false,false,false,false, false,false,
								 false,true, false, false, true, true, false, true,true, true);
						
					
					}
					else error = true;
					
					res = 0;
					
					res = _controladorDomPart.modificarDomicilioParticular(dpVO, _clienteVO.getDni());
					
					if(res > 0){
						
						habilita_datosDomPart(
								false, false,false,false,false, false,
								false, false, false,false, false, false,false, false);
					}
					else error = true;
					
					res = 0;
					
					res = _controladorDomCom.modificarDomicilioComercial(dcVO, _clienteVO.getDni());
					
					if(res > 0){
		
						habilita_datosDomCom(
								false, false,false,false,
								false, false, false,false, false, false, false, false,false, false, false);
						
					}
					else error = true;
					
					if(!error) Mensajes.getMensaje_modificacionExitosa();
					else Mensajes.getMensaje_modificacionError();
					
				//}
				//else Mensajes.getMensaje_relacionVeraz();	
					
			}
		}
		
		if(e.getSource() == baja){

			if(cliente_orVO != null){
				
				int opcion = Mensajes.getMensaje_confirmacion_baja();
				
				if(opcion == JOptionPane.YES_OPTION){
					
					Date d = new Date();
					
					java.sql.Date hoy = new java.sql.Date(d.getTime());
					java.sql.Time hora = new java.sql.Time(d.getTime());
					
					ArrayList<PedidosVO> ar = _controladorPedido.
							buscarPedidos_porCliente(cliente_orVO.getDni());
					
					if(ar!=null){
						
						for(PedidosVO pVO : ar){
							
							if(!pVO.getEstado_pedido().equals("baja") &&
									!pVO.getEstado_pedido().equals("finalizado"))
								_controladorPedido.update_fechatermino(pVO.getN_pedido(), hoy);
							
							_controladorPedido.update_estadoPedido(pVO.getN_pedido(), "baja");
						}
					}
					
					VistaObservacionesBaja vob = new VistaObservacionesBaja();
					vob.setVistaPrincipal(_vista_principal);
					vob.setVisible(true);
					Observaciones_clienteVO ocVO = vob.getObservacionesClienteVO();
					
					VerazVO vVO = new VerazVO();
					vVO.setDni(cliente_orVO.getDni());
					vVO.setId_usuario(Principal._usuario.getId_usuario());
					vVO.setFecha_registro(hoy);
					vVO.setHora_registro(hora);
					
					int res = _controladorCliente.bajaCliente(cliente_orVO, vVO, ocVO);
					
					if(res > 0){
						
						Mensajes.getMensaje_bajaExitosa();
						limpiar();
						nuevo_pedidoB.setEnabled(false);
						historial_pedidosB.setEnabled(false);
						pPedidos.removeAll();
						
						//mostrarCliente(_clienteVO, dp, dc);
					}
					else Mensajes.getMensaje_bajaError();
				}
			}
			else if(LogicaCliente.validarbusquedaUsuario) Mensajes.getMensaje_clienteNoExiste();
			
			
		}
		
		if(e.getSource()==habilitar){
			
			int opcion = 0;
			int res = 0;
			
			opcion = Mensajes.getMensaje_confirmacion_habilitacionCliente();
			
			if(opcion == JOptionPane.YES_OPTION){
				
				res = _controladorCliente.updateEstado("ex", cliente_orVO.getDni());
				
				if(res > 0){
					
					pPedidos.removeAll();
					Mensajes.getMensaje_modificacionExitosa();
					buscar_cliente();
					
				}
			}
			
		}
		if(e.getSource()==observaciones){
			
			ControladorObservaciones co = new ControladorObservaciones();
			LogicaObservaciones lo = new LogicaObservaciones();
			ControladorUsuario cu = new ControladorUsuario();
			LogicaUsuario lu = new LogicaUsuario();
			cu.setLogicaUsuario(lu);
			
			co.setLogicaObservaciones(lo);
			
			 VistaObservacionesCliente voc = new VistaObservacionesCliente();
			 
			 voc.setVistaPrincipal(_vista_principal);
			 voc.setControladorObservaciones(co);
			 voc.setVistaBuscarCliente(this);
			 voc.setControladorUsuario(cu);
			 voc.iniciar(co.buscarObservacionesCliente(this.getClienteVO().getDni()));
			 voc.setVisible(true);
			
		}
		
		if(e.getSource()==nuevo_idc){
			
			VistaNuevoDC ndc = new VistaNuevoDC();
			BusquedaEntities be = new BusquedaEntities();
			ControladorDomicilioComercial cdc = new ControladorDomicilioComercial();
			ControladorLocalidad cl = new ControladorLocalidad();
			ControladorEmpleado ce = new ControladorEmpleado();
			ControladorZona cz = new ControladorZona();
			
			LogicaDomicilioComercial ldc = new LogicaDomicilioComercial();
			LogicaLocalidad ll = new LogicaLocalidad();
			LogicaEmpleado le = new LogicaEmpleado();
			LogicaZona lz = new LogicaZona();
			
			cdc.setLogicaDomicilioComercial(ldc);
			cl.setLogicaLocalidad(ll);
			ce.setLogicaEmpleado(le);
			cz.setLogicaZona(lz);
			cdc.setBusquedaEntities(be);
			cl.setBusquedaEntities(be);
			ce.setBusquedaEntities(be);
			cz.setBusquedaEntities(be);
			
			ldc.setBusquedaEntities(be);
			ll.setBusquedaEntities(be);
			le.setBusquedaEntities(be);
			lz.setBusquedaEntities(be);
			lz.setControladorEmpleado(ce);
			
			ndc.setControladorDomicilioComercial(cdc);
			ndc.setControladorEmpleado(ce);
			ndc.setControladorLocalidad(cl);
			ndc.setControladorZona(cz);
			ndc.setVistaBuscarCliente(this);
			ndc.setVistaPrincipal(_vista_principal);
			ndc.setBusquedaEntities(be);
			be.setVistaNuevoDC(ndc);
			be.setControladorDomicilioComercial(cdc);
			be.setControladorLocalidad(cl);
			be.setControladorZona(cz);
			
			ndc.setVisible(true);
			
		}
		
		if(e.getSource()==actualizar){
			
			pPedidos.removeAll();
			buscar_cliente();
			this.updateUI();
		}
		
		if(e.getSource()==nuevo_pedidoB){
			
			VistaAltaPedido vap = new VistaAltaPedido();
			ControladorArticulo ca = new ControladorArticulo();
			ControladorPedidos cp = new ControladorPedidos();
			LogicaPedido lp = new LogicaPedido(); 
			ControladorPlan cplan = new ControladorPlan();
			Logica_plan lplan = new Logica_plan(); 
			LogicaArticulo la = new LogicaArticulo();
			BusquedaEntities be = new BusquedaEntities();
			
			cplan.setLogicaPlan(lplan);
			ca.setLogicaArticulo(la);
			ca.setBusquedaEntities(be);
			cp.setLogicaPedido(lp);
			cp.setBusquedaEntities(be);
			lp.setControladorArticulo(ca);
			lp.setControladorPedido(cp);
			lp.setBusquedaEntities(be);
			la.setControladorArticulo(ca);
			la.setBusquedaEntities(be);
			be.setVistaAltaPedido(vap);
			
			vap.setBusquedaEntities(be);
			vap.setControladorArticulo(ca);
			vap.setVistaBuscarCliente(this);
			vap.setControladorPlan(cplan);
			vap.setControladorPedido(cp);
			vap.setControladorCliente(_controladorCliente);
			
			vap.limpiar();
			vap.cargar_idcCB(dom_com_orVO);
			vap.setVistaPrincipal(_vista_principal);
			vap.setVisible(true);
		}
		if(e.getSource()==historial_pedidosB){
			
			VistaPedidosHistoricos vph = new VistaPedidosHistoricos();
			
			vph.setVistaBuscarCliente(this);
			
			vph.iniciar(this.getClienteVO());
			vph.setVisible(true);
		}
		
	}
	
	public JComboBox getIdcCB(){
		
		return idcCB;
	}
	
	public void cargarIdc(ArrayList<DomicilioComercialVO> ardc){
		
		for(DomicilioComercialVO dc : ardc){
			
			idcCB.addItem(dc.getIdc());
		}
		idcCB.setSelectedIndex(0);
		
	}
	
	public void buscar_cliente(){
		
		ClienteVO _clienteVO = _controladorCliente.buscarCliente(dniTF.getText());
		DomicilioParticularVO dp = _controladorDomPart.buscarDomicilioParticular(dniTF.getText());
		ArrayList<DomicilioComercialVO> ar_dc = _controladorDomCom.buscarDomicilioComercial(dniTF.getText());
		
		
		cliente_orVO = _clienteVO;
		dom_part_orVO = dp;
		dom_com_orVO = ar_dc;
		
		if(_clienteVO != null && dp != null && ar_dc != null){
			
			cb_model.removeAllElements();
			
			comprobarEncriptado();
			
			_controladorCliente.actualizarEstado(_clienteVO);
			
			cargarIdc(ar_dc);
			
			mostrarCliente(_clienteVO, dp);
			
			cargar_dc(ar_dc.get(0));
			
			
			habilita_datosPersonales(false, false, false, false,false, false,false, false, false, false, false,false,
					false, false,false, false, false, false,false,false,
					false, true, false, false, true, true,false, true,true, true);
			
			aux_cliente = _clienteVO;
			
			VistaAltaPedido vap = new VistaAltaPedido();
			 ControladorCliente _controladorCliente = new ControladorCliente();
				
				ControladorPedidos _controladorPedido = new ControladorPedidos();
				ControladorArticulo _controladorArticulo = new ControladorArticulo();
				ControladorCombo _controladorCombo = new ControladorCombo();
				ControladorPagoDiario _controladorPagoDiario = new ControladorPagoDiario();
				ControladorPrestamo _controladorPrestamo = new ControladorPrestamo();
				ControladorEmpleado _controladorEmpleado = new ControladorEmpleado();
				LogicaEmpleado logica_empleado = new LogicaEmpleado();
				LogicaCliente _logica_cliente = new LogicaCliente();
				LogicaPedido _logica_pedido = new LogicaPedido();
				LogicaArticulo _logica_articulo = new LogicaArticulo();
				LogicaCombo _logica_combo = new LogicaCombo();
				LogicaPrestamo _logica_prestamo = new LogicaPrestamo();
				
				BusquedaEntities busqueda_entities = new BusquedaEntities();
				
				//VistaNewObjetoVenta vnov = new VistaNewObjetoVenta();
				VistaPrestamo vista_prestamo = new VistaPrestamo();
				
				_logica_cliente.setControladorCliente(_controladorCliente);
				_logica_cliente.setBusquedaEntities(busqueda_entities);
				_logica_cliente.setVistaBuscarCliente(this);
		
				_logica_articulo.setBusquedaEntities(busqueda_entities);
				_logica_articulo.setControladorArticulo(_controladorArticulo);
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
				
				_controladorCliente.setLogicaCliente(_logica_cliente);
				_controladorCliente.setVistaPrincipal(_vista_principal);
				
				_controladorCliente.setBusquedaEntities(busqueda_entities);
				_controladorEmpleado.setLogicaEmpleado(logica_empleado);
				
				_controladorPedido.setLogicaPedido(_logica_pedido);
				_controladorPedido.setBusquedaEntities(busqueda_entities);
				_controladorArticulo.setBusquedaEntities(busqueda_entities);
				_controladorArticulo.setLogicaArticulo(_logica_articulo);
				_controladorPrestamo.setLogicaPrestamo(_logica_prestamo);
				_controladorCombo.setLogicaCombo(_logica_combo);
				_controladorCombo.setBusquedaEntities(busqueda_entities);
				_controladorPrestamo.setBusquedaEntities(busqueda_entities);
				_logica_pedido.setControladorPedido(_controladorPedido);
				_logica_pedido.setBusquedaEntities(busqueda_entities);
				_logica_pedido.setVistaBuscarCliente(this);
				_logica_pedido.setControladorPagoDiario(_controladorPagoDiario);
				
				busqueda_entities.setVistaPrincipal(_vista_principal);
				//busqueda_entities.setVistaBuscarPedidos_porCliente(vp);
				busqueda_entities.setControladorCliente(_controladorCliente);
				busqueda_entities.setControladorArticulo(_controladorArticulo);
				busqueda_entities.setVistaBuscarCliente(this);
				//busqueda_entities.setVistaNewObjetoVenta(vnov);
				busqueda_entities.setVistaPrestamo(vista_prestamo);
				
				
				busqueda_entities.setControladorPedido(_controladorPedido);
				busqueda_entities.setControladorCombo(_controladorCombo);
				busqueda_entities.setControladorPrestamo(_controladorPrestamo);
				busqueda_entities.setControladorEmpleado(_controladorEmpleado);
				
				this.setVistaPrincipal(_vista_principal);
				
				vista_prestamo.setControladorPrestamo(_controladorPrestamo);
				vista_prestamo.setBusquedaEntities(busqueda_entities);
				//vista_prestamo.setVistaBuscarPedidos_porClientes(vp);

			
			vap.setVistaBuscarCliente(this);
			vap.setBusquedaEntities(busqueda_entities);
			vap.setControladorArticulo(_controladorArticulo);
			vap.setControladorCliente(_controladorCliente);
			vap.setControladorPrestamo(_controladorPrestamo);
			//vap.setVistaCombo(vnov);
			vap.setVistaPrestamo(vista_prestamo);
			vap.setControladorPedido(_controladorPedido);
			busqueda_entities.setVistaAltaPedido(vap);
			bh.setVistaAltaPedido(vap);
			bh.setVistaBuscarCliente(this);
			
			 bh.getNuevo_pedidoB().setEnabled(true);
			 bh.getHistorial_pedidos().setEnabled(true);
			 if(cliente_orVO.getEstado().equals("baja")) nuevo_pedidoB.setEnabled(false);
		}
		else if(LogicaCliente.validarbusquedaUsuario) Mensajes.getMensaje_clienteNoExiste();
	}
	
	public void mostrarCliente(ClienteVO _clienteVO, DomicilioParticularVO dp){
		

		dniTF.setBackground(new Color(183, 242, 113));
		
		dni_anterior = _clienteVO.getDni();
		dniTF.setText(Integer.toString(_clienteVO.getDni()));
		nombreTF.setText(_clienteVO.getNombre());
		//nombreTF.setDisabledTextColor(new Color(113,125,126));
		//nombreTF.setFont(new Font("Arial", Font.BOLD, 24));
		//nombreTF.setSize(150, 50);
		apellidoTF.setText(_clienteVO.getApellido());
		nacionalidadTF.setText(_clienteVO.getNacionalidad());
		dni_conyugueTF.setText(Integer.toString(_clienteVO.getDni_conyugue()));
		nombre_conyugueTF.setText(_clienteVO.getNombre_conyugue());
		apellido_conyugueTF.setText(_clienteVO.getApellido_conyugue());
		telefono_conyugueTF.setText(_clienteVO.getTelefono_conyugue());
		email_conyugueTF.setText(_clienteVO.getEmail_conyugue());
		
		
		if(_clienteVO.getEstado_civil()){
			
			soltero.setSelected(true);
		}
		else{
			
			casado.setSelected(true);
		}
	
			
		telefono_movilTF.setText(_clienteVO.getTelefono_movil());
		telefono_lineaTF.setText(_clienteVO.getTelefono_linea());
		modelFN.setValue(_clienteVO.getFecha_nacimiento());
		modelFN.setSelected(true);
		//fecha_nacimiento
		emailTF.setText(_clienteVO.getEmail());
		id_vendedorTF.setText(Integer.toString(_clienteVO.getId_vendedor()));
		
		if(_controladorVendedor.buscarVendedorUsuario(id_vendedorTF.getText())!=null)
			
			lVendedor.setText(_controladorVendedor.buscarVendedorUsuario
					(id_vendedorTF.getText()));
		
		else lVendedor.setText("");
		
		
		if(_controladorZona.buscarZonaUsuario(id_zonaTF.getText())!=null){
			
			ZonaVO zVO = _controladorZona.buscarZonaUsuario(id_zonaTF.getText());
			
			EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.
					toString(zVO.getId_cobrador()));
			
			lCobradorZona.setText(eVO.getNombre() + " " + eVO.getApellido());
		}
		
		else lCobradorZona.setText("");

		lEstado.setFont(new Font("Arial", Font.BOLD, (int)width/90));
		
		lEstado.setText(_clienteVO.getEstado());
		
		switch(lEstado.getText()){
		
			case "baja": lEstado.setForeground(Color.red);
			break;
			case "nuevo": lEstado.setForeground(Color.GREEN);
			break;	
			case "operando": lEstado.setForeground(Color.BLUE);
			break;
			case "ex": lEstado.setForeground(Color.PINK);
			break;
		}
		 domicilio_partTF.setText(dp.getDomicilio());
		 entre_calle1TF.setText(dp.getEntre_calle1());
		 entre_calle2TF.setText(dp.getEntre_calle2());

		 if(dp.getDpto())
			 siDpto.setSelected(true);
		 else
			 noDpto.setSelected(true);
		 
		 pisoTF.setText(Integer.toString(dp.getPiso()));
		 barrioTF.setText(dp.getBarrio());
		 cpTF.setText(Integer.toString(dp.getCp()));
		 id_localidadTF.setText(Integer.toString(dp.getId_localidad()));
		 
		 if(_controladorLocalidad.buscarLocalidadUsuario(id_localidadTF.getText())!=null)
				
				lLocalidadPart.setText(_controladorLocalidad.buscarLocalidadUsuario(id_localidadTF.getText()));
		 
			else lLocalidadPart.setText("");
 
		 modelAP.setValue(dp.getAntiguedad());
		 modelAP.setSelected(true);
		 
		 if(dp.getPropio())
			 propio.setSelected(true);
		 else
			 alquila.setSelected(true);
		 
		 buscarPedido(_clienteVO, pPedidos);
		 
		formato_disable();
		
	}
	
	public void cargar_dc(DomicilioComercialVO dc){
		
		
		 id_zonaTF.setText(Integer.toString(dc.getId_zona()));
		 
		 ZonaVO zVO = _controladorZona.buscarZonaUsuario(id_zonaTF.getText());
		 
		 EmpleadoVO eVO = null;
		 
		 if(zVO!=null){
			 
			 eVO = controladorEmpleado.buscarEmpleado(Short.toString(zVO.getId_cobrador()));
			 if(eVO!=null){
				 
				 lCobradorZona.setText(eVO.getNombre() + " " + eVO.getApellido());
			 }
		 }
		
		 domicilio_comTF.setText(dc.getDomicilio());
		 entre_calle1ComercialTF.setText(dc.getEntre_calle1());
		 entre_calle2ComercialTF.setText(dc.getEntre_calle2());
		 
		 barrioComercialTF.setText(dc.getBarrio());
		 cpComercialTF.setText(Integer.toString(dc.getCp()));
		 id_localidadComercialTF.setText(Integer.toString(dc.getId_localidad()));
		 comercioTF.setText(Integer.toString(dc.getComercio()));
		 
		 if(_controladorDomCom.buscarComercio(comercioTF.getText())!=null){
				
				ComercioVO cVO = _controladorDomCom.buscarComercio(comercioTF.getText());
				lComercio.setText(cVO.getDescripcion());
				
			}
				
			else{
				
				lComercio.setText("");
			
			}
		 
		 if(_controladorLocalidad.buscarLocalidadUsuario(id_localidadComercialTF.getText())!=null)
				
				lLocalidadCom.setText(_controladorLocalidad.buscarLocalidadUsuario(id_localidadComercialTF.getText()));
		 
			else lLocalidadCom.setText("");

				 
		 modelAC.setValue(dc.getAntiguedad());
		 modelAC.setSelected(true);
		 
		 if(dc.getPropio())
			 propioComercial.setSelected(true);
		 else
			 alquilaComercial.setSelected(true); 
		 
		 desdeH.setHorarioDesde(dc.getHorario_atencion());
		
		 hastaH.setHorarioHasta(dc.getHorario_atencion());
		
	}
	
	public void limpiar(){
		
		
		
		for(JTextField tf : getArrayString()){
			
			tf.setText("");
		}
		
		for(JTextField tf : getArrayInt()){
			
			tf.setText("");
		}
		
		for(JTextField tf : getArrayStringDomPart()){
			
			tf.setText("");
		}
		
		for(JTextField tf : getArrayIntDomPart()){
			
			tf.setText("");
		}
		
		for(JTextField tf : getArrayStringDomCom()){
			
			tf.setText("");
		}
		
		for(JTextField tf : getArrayIntDomCom()){
			
			tf.setText("");
		}
		for(JTextField tf : getArrayConyugue()){
			
			tf.setText("");
		}
		
		
		lLocalidadPart.setText("");
		lLocalidadCom.setText(""); 
		lCobradorZona.setText(""); 
		lVendedor.setText("");
		lEstado.setText("");
		
		habilita_datosPersonales(true, false, false, false,false, false, false,false, false, false, false,
				false, false,false,false,false,false,false, false,true,		
				false, false, false, true, false, false, false, false, false,false);
		
		habilita_datosDomPart(
				false, false,false,false,false, false,
				false, false, false,false, false, false,false, false);
		
		habilita_datosDomCom(
				false, false,false,false,
				false, false, false,false, false, false, false,false,false, false, false);
		
		
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
		
		for(JTextField tf : getArrayIntDomPart()){
			
			tf.setDisabledTextColor(new Color(113,125,126));
			//tf.setFont(new Font("Arial", Font.BOLD, 24));
		}
		
		for(JTextField tf : getArrayStringDomPart()){
			
			tf.setDisabledTextColor(new Color(113,125,126));
			//tf.setFont(new Font("Arial", Font.BOLD, 24));
		}
		
		for(JTextField tf : getArrayIntDomCom()){
			
			tf.setDisabledTextColor(new Color(113,125,126));
			//tf.setFont(new Font("Arial", Font.BOLD, 24));
		}
		
		for(JTextField tf : getArrayStringDomCom()){
			
			tf.setDisabledTextColor(new Color(113,125,126));
			//tf.setFont(new Font("Arial", Font.BOLD, 24));
		}
		for(JTextField tf : getArrayConyugue()){
			
			tf.setDisabledTextColor(new Color(113,125,126));
			//tf.setFont(new Font("Arial", Font.BOLD, 24));
		}
		
	
        desdeH.setDisableText(Color.black);
       
        hastaH.setDisableText(Color.black);
	}
	
	public void habilita_datosPersonales(boolean dni, boolean nombre, boolean apellido,boolean nacionalidad,
			boolean dni_conyugue,
			boolean n_conyugue,boolean ape_conyugue,boolean tel_con, boolean mail_con, 
			boolean soltero, boolean casado,boolean fecha_nacimiento,
			boolean telefono_movil, boolean telefono_linea,boolean email,
			boolean id_vendedor,
			boolean id_zona,boolean buscar_zona, boolean buscar_vendedor, boolean buscar_cliente, 
			boolean b_guardar,  boolean b_modificar,  boolean b_cancelar,
			boolean b_buscar, boolean b_nueva_consulta,
			boolean b_eliminar, boolean habilitar, boolean observaciones, boolean ndc,boolean actualizar){
		
		dniTF.setEnabled(dni);
		nombreTF.setEnabled(nombre);
		apellidoTF.setEnabled(apellido);
		nacionalidadTF.setEnabled(nacionalidad);
		
		if(this.casado.isSelected()){
			
			dni_conyugueTF.setEnabled(dni_conyugue);
			nombre_conyugueTF.setEnabled(n_conyugue);
			apellido_conyugueTF.setEnabled(ape_conyugue);
			telefono_conyugueTF.setEnabled(tel_con);
			email_conyugueTF.setEnabled(mail_con);
			
		}
		else{
			
			dni_conyugueTF.setEnabled(false);
			nombre_conyugueTF.setEnabled(false);
			apellido_conyugueTF.setEnabled(false);
			telefono_conyugueTF.setEnabled(false);
			email_conyugueTF.setEnabled(false);
			
		}
		this.soltero.setEnabled(soltero);
		this.casado.setEnabled(casado);
		this.fecha_nacimiento.getComponent(1).setEnabled(fecha_nacimiento);
		telefono_movilTF.setEnabled(telefono_movil);
		telefono_lineaTF.setEnabled(telefono_linea);
		emailTF.setEnabled(email);
		
		if(Principal._usuario.getPermiso()!=1){
			
			id_vendedorTF.setEnabled(false);
			id_zonaTF.setEnabled(false);
			this.buscar_zona.setEnabled(false);
			this.buscar_vendedor.setEnabled(false);
		}	
		else{
			
			id_vendedorTF.setEnabled(id_vendedor);
			id_zonaTF.setEnabled(id_zona);
			this.buscar_zona.setEnabled(buscar_zona);
			this.buscar_vendedor.setEnabled(buscar_vendedor);
			
		}
		this.buscar_cliente.setEnabled(buscar_cliente);
		
		guardar.setEnabled(b_guardar);
		modificar.setEnabled(b_modificar);
		cancelar.setEnabled(b_cancelar);
		buscar.setEnabled(b_buscar);
		nueva_consulta.setEnabled(b_nueva_consulta);
		
		if(Principal._usuario.getPermiso()==1)
			baja.setEnabled(b_eliminar);
		else baja.setEnabled(false);
		
		this.habilitar.setEnabled(habilitar);
		this.observaciones.setEnabled(observaciones);
		this.nuevo_idc.setEnabled(ndc);
		this.actualizar.setEnabled(actualizar);
		
		if(cliente_orVO!=null){
			
			if(cliente_orVO.getEstado().equals("baja")){

				guardar.setEnabled(false);
				modificar.setEnabled(false);
				cancelar.setEnabled(false);
				buscar.setEnabled(b_buscar);
				nueva_consulta.setEnabled(b_nueva_consulta);
				baja.setEnabled(false);
				
				if(Principal._usuario.getPermiso()==1)
					this.habilitar.setEnabled(true);
			
			}
		}

	}
	
	public void habilita_datosDomPart(
			boolean calle_part, boolean numero_part,boolean entrecalle1_part,boolean entrecalle2_part,
			boolean sidpto, boolean nodpto,
			boolean piso_part, boolean barrio_part, boolean cp_part, boolean id_localidad, boolean antiguedad_part,
			boolean buscar_locPart,
			boolean propio_part, 
			boolean alquila_part){
			
		domicilio_partTF.setEnabled(calle_part);
		entre_calle1TF.setEnabled(entrecalle1_part);
		entre_calle2TF.setEnabled(entrecalle2_part);
		siDpto.setEnabled(sidpto);
		noDpto.setEnabled(nodpto);
		pisoTF.setEnabled(piso_part);
		barrioTF.setEnabled(barrio_part);
		cpTF.setEnabled(cp_part);
		id_localidadTF.setEnabled(id_localidad);
		antiguedadTF.getComponent(1).setEnabled(antiguedad_part);
		buscar_localidadPart.setEnabled(buscar_locPart);
		propio.setEnabled(propio_part);
		alquila.setEnabled(alquila_part);
		
	}
	
	public void habilita_datosDomCom(
			boolean calle_com, boolean numero_com,boolean entrecalle1_com,boolean entrecalle2_com,
		    boolean barrio_com, boolean cp_com, boolean id_localidad_com, boolean antiguedad_com,
		    boolean buscar_locCom,
		    boolean propio_com, boolean alquila_com, boolean comercio,boolean buscar_comercio,
		    boolean desde, boolean hasta){
			
		domicilio_comTF.setEnabled(calle_com);
		entre_calle1ComercialTF.setEnabled(entrecalle1_com);
		entre_calle2ComercialTF.setEnabled(entrecalle2_com);
		barrioComercialTF.setEnabled(barrio_com);
		cpComercialTF.setEnabled(cp_com);
		id_localidadComercialTF.setEnabled(id_localidad_com);
		antiguedadComercialTF.getComponent(1).setEnabled(antiguedad_com);
		buscar_localidadCom.setEnabled(buscar_locCom);
		propioComercial.setEnabled(propio_com);
		alquilaComercial.setEnabled(alquila_com);
		comercioTF.setEnabled(comercio);
		this.buscar_comercio.setEnabled(buscar_comercio);
		desdeH.setEnabled(desde);
		hastaH.setEnabled(hasta);
		
	}

	public void comprobarEncriptado(){
		
		ArrayList<ArticulosVO> ar = _controladorArticulo.buscarArticulos_enPrestamoAll();
		
		encriptado = false;
		
		if(ar!=null){
			
			for(ArticulosVO artVO : ar){
				
				System.out.println("encriptado " + encriptado + " art " + artVO.getNombre());
				
				if(!artVO.getNombre().equals("ArticuloP"))
					encriptado = true;
				
			}
			
			
		}
	}
	
	public void buscarPedido(ClienteVO _clienteVO, JPanel panel){
		
		//ArrayList<PedidosVO> resultado_pedido = _controladorPedido.buscarPedidos_porCliente(_clienteVO.getDni());
		ArrayList<PedidosVO> resultado_pedido = _controladorPedido.
				buscarPedidos_porClienteTodos_estados(_clienteVO.getDni());
		
		 if(resultado_pedido!=null){
			 
			 for(PedidosVO pVO : resultado_pedido){
				 
				 ControladorCliente _controladorCliente = new ControladorCliente();
				 ControladorDomicilioComercial _controladorDc = new ControladorDomicilioComercial();
				 ControladorLocalidad _controladorLocalidad = new ControladorLocalidad();
					ControladorPedidos _controladorPedido = new ControladorPedidos();
					ControladorPagoDiario _controladorPagoDiario = new ControladorPagoDiario();
					ControladorArticulo _controladorArticulo = new ControladorArticulo();
					ControladorCombo _controladorCombo = new ControladorCombo();
					ControladorPrestamo _controladorPrestamo = new ControladorPrestamo();
					ControladorRefinanciacion_ex _controladorRef_ex = new ControladorRefinanciacion_ex();
					ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
					ControladorMonto_trasladado _controladorMonto_t = new ControladorMonto_trasladado();
					ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
					ControladorObservaciones controladorObservaciones = new ControladorObservaciones();
					ControladorUsuario controladorUsuario = new ControladorUsuario();
					LogicaDomicilioComercial ldc = new LogicaDomicilioComercial();
					LogicaUsuario logica_usuario = new LogicaUsuario();
					LogicaLocalidad _logica_localidad = new LogicaLocalidad();
					LogicaEmpleado logica_empleado = new LogicaEmpleado();
					ControladorDA controladorDA = new ControladorDA();
					LogicaDA logicaDA = new LogicaDA();
					LogicaMonto_trasladado _logicaMonto_t = new LogicaMonto_trasladado();
					//ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
					LogicaCliente _logica_cliente = new LogicaCliente();
					LogicaPedido _logica_pedido = new LogicaPedido();
					LogicaPagoDiario _logicaPagoDiario = new LogicaPagoDiario();
					LogicaArticulo _logica_articulo = new LogicaArticulo();
					LogicaCombo _logica_combo = new LogicaCombo();
					LogicaPrestamo _logica_prestamo = new LogicaPrestamo();
					LogicaRefinanciacion_ex _logica_ref_ex = new LogicaRefinanciacion_ex();
					LogicaRefinanciacion_in _logica_ref_in = new LogicaRefinanciacion_in();
					LogicaObservaciones _logica_observaciones = new LogicaObservaciones();
					BusquedaEntities busqueda_entities = new BusquedaEntities();
					//Barra_herramientasPedidos bhp = new Barra_herramientasPedidos();
					VistaBuscarPedidos_porClientes vp = new VistaBuscarPedidos_porClientes();
					VistaNewObjetoVenta vnov = new VistaNewObjetoVenta(/*vp, "Nuevo artículo"*/);
					VistaPrestamo vista_prestamo = new VistaPrestamo();
					VistaRefinanciacion vf = new VistaRefinanciacion();
					VistaRefinanciacion_in vf_in = new VistaRefinanciacion_in();
					VistaMonto_t mt = new VistaMonto_t();
					Vista_pagos_porPedido vpp = new Vista_pagos_porPedido();
					
					CifradoAction ca = new CifradoAction();
					DescifradoAction da = new DescifradoAction();
					
					_controladorDc.setLogicaDomicilioComercial(ldc);
					_controladorLocalidad.setLogicaLocalidad(_logica_localidad);
					_controladorLocalidad.setBusquedaEntities(busqueda_entities);
					_logica_localidad.setBusquedaEntities(busqueda_entities);
					
					controladorObservaciones.setLogicaObservaciones(_logica_observaciones);
					controladorUsuario.setLogicaUsuario(logica_usuario);
					
					controladorDA.setLogicaDA(logicaDA);
					logicaDA.setBusquedaEntities(busqueda_entities);
					logicaDA.setControladorArticulo(_controladorArticulo);
					
					ca.setControladorPedidos(_controladorPedido);
					ca.setVistaBuscarPedidos_porCliente(vp);
					ca.setVistaBuscarCliente(this);
					ca.setVistaPrincipal(_vista_principal);
					da.setControladorPedidos(_controladorPedido);
					da.setVistaBuscarCliente(this);
					da.setVistaBuscarPedidos_porCliente(vp);
					da.setVistaPrincipal(_vista_principal);
					da.setVistaBuscarPedidos_porCliente(vp);
					vp.setCifradoAction(ca);
					vp.setDescifradoAction(da);
					
					_logica_cliente.setControladorCliente(_controladorCliente);
					_logica_cliente.setBusquedaEntities(busqueda_entities);
					_logica_cliente.setVistaBuscarCliente(this);
			
					_logica_articulo.setBusquedaEntities(busqueda_entities);
					_logica_articulo.setControladorArticulo(_controladorArticulo);
					_logica_combo.setBusquedaEntities(busqueda_entities);
					_logica_prestamo.setBusquedaEntities(busqueda_entities);
					
					controladorEmpleado.setLogicaEmpleado(logica_empleado);
					this.setControladorCliente(_controladorCliente);	
					this.setControladorLocalidad(_controladorLocalidad);	
					this.setBusquedaEntities(busqueda_entities);		
					this.setControladorPedido(_controladorPedido);
					this.setVistaBuscarPedidos_porClientes(vp);
					
					this.setControladorRefinanciacion_ex(_controladorRef_ex);
					this.setControladorRefinanciacion_in(_controladorRef_in);
						//vpc.setControladorRefinanciacion_in(_controladorRef_in);
					
					this.setControladorPagoDiario(_controladorPagoDiario);
					this.setControladorArticulo(_controladorArticulo);
					this.setVistaCombo(vista_combo);
					this.setVistaPrestamo(vista_prestamo);
					this.setControladorCombo(_controladorCombo);
					this.setControladorPrestamo(_controladorPrestamo);
					this.setVistaRefinanciacion(vf);
					this.setVistaRefinanciacion_in(vf_in);
					this.setVistaMonto_t(mt);
					this.setVistaObservaciones(obser);
					this.setVista_pagosPedido(vpp);
					
					_controladorCliente.setLogicaCliente(_logica_cliente);
					_controladorCliente.setVistaPrincipal(_vista_principal);
					
					_controladorCliente.setBusquedaEntities(busqueda_entities);
					
					_controladorPedido.setLogicaPedido(_logica_pedido);
					_controladorPedido.setBusquedaEntities(busqueda_entities);
					_controladorPagoDiario.setLogicaPagoDiario(_logicaPagoDiario);
					_controladorArticulo.setBusquedaEntities(busqueda_entities);
					_controladorArticulo.setLogicaArticulo(_logica_articulo);
					_controladorPrestamo.setLogicaPrestamo(_logica_prestamo);
					_controladorCombo.setLogicaCombo(_logica_combo);
					_controladorCombo.setBusquedaEntities(busqueda_entities);
					_controladorPrestamo.setBusquedaEntities(busqueda_entities);
					_logica_pedido.setControladorPedido(_controladorPedido);
					_logica_pedido.setControladorPagoDiario(_controladorPagoDiario);
					_logica_pedido.setControladorMonto_trasladado(_controladorMonto_t);
					_logica_pedido.setControladorArticulo(_controladorArticulo);
					_logica_pedido.setControladorPrestamo(_controladorPrestamo);
					_logica_pedido.setBusquedaEntities(busqueda_entities);
					_logica_pedido.setVistaBuscarPedidos_porClientes(vp);
					_logica_pedido.setVistaBuscarCliente(this);
					_logica_pedido.setControladorDA(controladorDA);
					
					busqueda_entities.setVistaPrincipal(_vista_principal);
					busqueda_entities.setControladorCliente(_controladorCliente);
					busqueda_entities.setControladorArticulo(_controladorArticulo);
					busqueda_entities.setControladorLocalidad(_controladorLocalidad);
					busqueda_entities.setVistaBuscarCliente(this);
					busqueda_entities.setVistaBuscarPedidos_porCliente(vp);
					busqueda_entities.setVistaNewObjetoVenta(vnov);
					busqueda_entities.setVistaPrestamo(vista_prestamo);
					busqueda_entities.setVistaMonto_t(mt);
		
					busqueda_entities.setControladorPedido(_controladorPedido);
					busqueda_entities.setControladorCombo(_controladorCombo);
					busqueda_entities.setControladorPrestamo(_controladorPrestamo);
					busqueda_entities.setControladorEmpleado(controladorEmpleado);
					this.setVistaPrincipal(_vista_principal);
					
					vp.setControladorDomicilioComercial(_controladorDc);
					vp.setControladorPedidos(_controladorPedido);
					vp.setControladorRefinanciacion_ex(_controladorRef_ex);
					vp.setControladorRefinanciacion_in(_controladorRef_in);
					//vpc.setControladorRefinanciacion_in(_controladorRef_in);
					vp.setVistaBuscarCliente(this);
					vp.setControladorPagoDiario(_controladorPagoDiario);
					vp.setBusquedaEntities(busqueda_entities);
					vp.setControladorArticulo(_controladorArticulo);
					vp.setControladorPrestamo(_controladorPrestamo);
					vp.setVistaPrincipal(_vista_principal);
					vp.setVistaNewObjetoVenta(vnov);
					vp.setVistaPrestamo(vista_prestamo);
					vp.setControladorCombo(_controladorCombo);
					vp.setVistaRefinanciacion(vf);
					vp.setVistaRefinanciacion_in(vf_in);
					vp.setVistaMonto_t(mt);
					vp.setControladorMonto_trasladado(_controladorMonto_t);
					vp.setControladorUsuario(controladorUsuario);
					vp.setVista_pagos_porPedido(vpp);
					vp.setControladorObservaciones(controladorObservaciones);
					
					vf.setControladorRefinanciacion_ex(_controladorRef_ex);
					vf.setVistaBuscarPedidos_porClientes(vp);
					vf.setControladorPedidos(_controladorPedido);
					vf.setVistaBuscarCliente(this);
					
					vf_in.setControladorRefinanciacion_in(_controladorRef_in);
					vf_in.setVistaBuscarPedidos_porClientes(vp);
					vf_in.setControladorPedidos(_controladorPedido);
					vf_in.setVistaBuscarCliente(this);
					
					vnov.setControladorArticulo(_controladorArticulo);
					vnov.setBusquedaEntities(busqueda_entities);
					vnov.setVistaBuscarPedidos_porClientes(vp);
					vnov.setControladorPrestamo(_controladorPrestamo);
					
					vista_prestamo.setControladorPrestamo(_controladorPrestamo);
					vista_prestamo.setBusquedaEntities(busqueda_entities);
					vista_prestamo.setVistaBuscarPedidos_porClientes(vp);
					
					mt.setVistaPrincipal(_vista_principal);
					mt.setVistaBuscarPedidos_porClientes(vp);
					mt.setControladorPedidos(_controladorPedido);
					mt.setBusquedaEntities(busqueda_entities);
					mt.setVistaBuscarCliente(this);
					mt.setControladorMonto_trasladado(_controladorMonto_t);
					mt.setLogicaMonto_trasladado(_logicaMonto_t);
					
					vpp.setVistaBuscarPedidos_porClientes(vp);
				
					vpp.setVistaBuscarCliente(this);
				
					vpp.setControladorPagoDiario(_controladorPagoDiario);
					
					_controladorRef_ex.setLogicaRefinanciacion_ex(_logica_ref_ex);
					_controladorRef_in.setLogicaRefinanciacion_in(_logica_ref_in);
					_controladorMonto_t.setLogicaMonto_trasladado(_logicaMonto_t);
					
				
			
				 String pedido = "";
				 
				 pedido = "N° " + pVO.getN_pedido();
				 boolean prestamo = false;
				 
				 Refinanciacion_exDAO refDAO = new Refinanciacion_exDAO();
					
				 	boolean rVO_ex = false;
				 	boolean rVO_in = false;
				 
					try {
						Refinanciacion_exVO rVO = refDAO.buscarRef(pVO.getN_pedido());
							
						if(rVO!=null && rVO.getEstado()){
							
							rVO_ex = true;
							//pedido = "<html>Refinanciado" + "<br/>" + "N° pedido:" + pVO.getN_pedido();
							
							 vp.setRef_ex_boolean(true);
							 vp.getAnularRef().setEnabled(true);
							 vp.getRefinanciacionL().setText("Refinanciado");
							 vp.getRef_ex().setEnabled(false);
						
						}
						else{
							
							vp.setRef_ex_boolean(false);
							vp.getAnularRef().setEnabled(false);
							vp.getRefinanciacionL().setText("");
							vp.getRef_ex().setEnabled(true);
							
							//pedido = "<html>N° pedido:" + pVO.getN_pedido();
							
						}	  
							
					} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
					
					Refinanciacion_inDAO ref_inDAO = new Refinanciacion_inDAO();
					
					try {
						Refinanciacion_inVO rVO = ref_inDAO.buscarRef(pVO.getN_pedido());
							
						if(rVO!=null && rVO.getEstado()){
							
							rVO_in = true;
							// pedido = "<html>Ref.interna" + "<br/>" + "N° pedido:" + pVO.getN_pedido();
							 
							
							 vp.setRef_in_boolean(true);
							 vp.getAnularRef_in().setEnabled(true);
							 vp.getRefinanciacion_inL().setText("Ref.interna");
							 vp.getRef_in().setEnabled(false);
						
						}
						else{
							
							vp.setRef_in_boolean(false);
							vp.getAnularRef_in().setEnabled(false);
							vp.getRefinanciacion_inL().setText("");
							vp.getRef_in().setEnabled(true);
							
						}
							  
							
					} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
				 
				 
				 if(rVO_ex && rVO_in){
					 
					/* pedido = "<html>Refinanciado" + "<br/>" +
					 "Ref.interna" + "<br/>" + "N° pedido:" + pVO.getN_pedido();*/
					 
					 vp.setRef_in_boolean(false);
					 vp.getAnularRef_in().setEnabled(true);
					 vp.getRefinanciacion_inL().setText("Ref.interna");
					 vp.getRef_in().setEnabled(false);
				 }
				 
				 ArrayList<Pedido_articuloVO> ar_pedido = _controladorPedido.
						 buscarArticulos_porPedido(pVO.getN_pedido(), true);
				 
				 
				 if(ar_pedido!=null){
					 
					 for(Pedido_articuloVO paVO : ar_pedido){
						 
						ArticulosVO artVO = _controladorArticulo.
								buscarArticulo_porCodigo(paVO.getCodigo_articulo());
						
						
							/*pedido = pedido + "<br/>" + artVO.getNombre() + "(" +
						paVO.getCodigo_articulo() + ")";*/
						
						ArticulosPVO arpVO = _controladorArticulo.buscarArticulo_enPrestamo(artVO.getCodigo());
						
						if(arpVO!=null) prestamo = true;
						
					 }
					 
				 }
	
				// pedido = pedido + "</html>";
				 
				 JLabel nombre_articulo = new JLabel(pedido);
			  
				 try {
					 Image nuev = null;
					 
					 if(panel.getName().equals("pPedidos_historicos")){
						 
						 nuev = ImageIO.read(this.getClass().getResource("/pedido_historico3.png"));
					 }
					 else nuev = ImageIO.read(this.getClass().getResource("/pedido3.png"));
					
					JButton nuevo_pedido = new JButton();
					 
					//nuevo_pedido.setPreferredSize(new Dimension((int)width/10, (int)height/15));
					 nuevo_pedido.setText(nombre_articulo.getText());
					 nuevo_pedido.setIcon(new ImageIcon(nuev));
					// nuevo_pedido.setHorizontalTextPosition(SwingConstants.CENTER);
					 nuevo_pedido.setFont(new Font("Arial", Font.PLAIN, (int) width/115));
					 nuevo_pedido.setForeground(new Color(14, 98, 81));
					 
					 nuevo_pedido.addActionListener(new ActionListener(){

						 
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							//vp.setTabla(_clienteVO);
							//_vista_principal.setEnabled(false);
							
							//vp.iniciar();
							
							vp.setPanelCliente(_clienteVO.getNombre() + " " + _clienteVO.getApellido());
							vp.setPedidosVO(pVO);
							vp.setPlan_original(pVO);
							vp.mostrarPedido(pVO);
							if(panel.getName().equals("pPedidos_historicos")){
								
								vp.getBh().habilitaBotones(false, false, false,
										false, false, false, false, false, 
										true, true, false, false);
								
								
							}
								
							vp.setVisible(true);
						}
						 
						 
					 });
					 
					 if(!pVO.getEstado_pedido().equals("baja")/* &&
						 !pVO.getEstado_pedido().equals("finalizado")*/){
						 
						if(panel.getName().equals("pPedidos")){
							
							panel.add(nuevo_pedido);
							pPedidos.updateUI();
						}
					
						 
					 }
					 else if(pVO.getEstado_pedido().equals("baja") /*||
							 pVO.getEstado_pedido().equals("finalizado")*/){
						 
						 SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
						 String fecha = "Fecha de término:";
							
						 if(pVO.getFecha_termino()!=null){
							 
							 fecha = fecha + ft.format(pVO.getFecha_termino());
							 
						 }
						 
						 DropShadowBorder shadow = new DropShadowBorder();
					    	
					    	shadow.setShadowSize(7);
					        shadow.setShadowColor(new Color(41, 128, 185));
					        shadow.setShowLeftShadow(true);
					        shadow.setShowRightShadow(true);
					        shadow.setShowBottomShadow(true);
					        shadow.setShowTopShadow(true);
						 
						 JPanel pNuevo_historico = new 
								 PanelGraduado(new Color(213, 245, 227), new Color(169, 223, 191));
						 
						 pNuevo_historico.setBorder(shadow);
						 JLabel fechaL = new JLabel();
						 
						 fechaL.setText(fecha);
						 
						 pNuevo_historico.add(fechaL);
						 pNuevo_historico.add(nuevo_pedido);
						 
						 pNuevo_historico.setMaximumSize
						 (new Dimension(Integer.MAX_VALUE, pNuevo_historico.getMinimumSize().height));
						 
						 ArrayList<PedidosVO> ar = new ArrayList<PedidosVO>();
						 
						 ar.add(pVO);
						 
						 ar.stream().sorted
						 ((o1,o2) -> o1.getFecha_termino().compareTo(o2.getFecha_termino()));
						 
						 if(panel.getName().equals("pPedidos_historicos"))
							 	panel.add(pNuevo_historico);
						 
					 }
					 
					 if(prestamo && encriptado) panel.remove(nuevo_pedido);
					 
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				    //boton1.setIcon(new ImageIcon(nuev));
				 
				 
				
			 }
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
