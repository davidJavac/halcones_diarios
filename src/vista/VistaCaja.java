package vista;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.RenderingHints;
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
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
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
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.border.DropShadowBorder;

import controlador.ControladorAdministrativo;
import controlador.ControladorArticulo;
import controlador.ControladorCaja;
import controlador.ControladorCajaInasistencia;
import controlador.ControladorCajaZona;
import controlador.ControladorCliente;
import controlador.ControladorCobrador;
import controlador.ControladorCombo;
import controlador.ControladorDespacho_diario;
import controlador.ControladorDetalle_interno;
import controlador.ControladorDetalle_proveedor;
import controlador.ControladorDomicilioComercial;
import controlador.ControladorDomicilioParticular;
import controlador.ControladorJefeCalle;
import controlador.ControladorLocalidad;
import controlador.ControladorMonedas;
import controlador.ControladorMonto_trasladado;
import controlador.ControladorMotivoCaja;
import controlador.ControladorObservaciones;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorPrestamo;
import controlador.ControladorProveedores;
import controlador.ControladorRefinanciacion_ex;
import controlador.ControladorRefinanciacion_in;
import controlador.ControladorSueldos;
import controlador.ControladorUsuario;
import controlador.ControladorVendedor;
import controlador.ControladorZona;
import controlador.Principal;
import controlador.ControladorEmpleado;
import modelo.CifradoAction;
import modelo.DescifradoAction;
import modelo.LogicaArticulo;
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
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.CajaDiariaTotalVO;
import modelo_vo.Caja_inasistenciaVO;
import modelo_vo.Caja_internaVO;
import modelo_vo.Caja_proveedoresVO;
import modelo_vo.Caja_zonasVO;
import modelo_vo.ClienteVO;
import modelo_vo.CobradorVO;
import modelo_vo.Combinacion_diasVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.Despacho_diarioVO;
import modelo_vo.Detalle_motivo_internoVO;
import modelo_vo.Detalle_proveedoresVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.PedidosVO;
import modelo_vo.ProveedoresVO;
import modelo_vo.Refinanciacion_exVO;
import modelo_vo.Refinanciacion_inVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.LocalidadVO;
import modelo_vo.Monedas_egresosVO;
import modelo_vo.Monedas_ingresosVO;
import modelo_vo.Motivo_caja_internaVO;
import modelo_vo.Motivo_generalVO;
import modelo_vo.Pago_diarioVO;
import modelo_vo.SueldosVO;
import modelo_vo.UsuariosVO;
import modelo_vo.ZonaVO;
import vista.VistaBuscarPedidos_porClientes.CustomJToolTip;

public class VistaCaja extends JPanel implements ActionListener{

	private int clickedRow=-1,clickedCol=-1;
	
	private JPanel pIntegra = new JPanel();
	JPanel pIntegra_scr = new JPanel();
	private JPanel pBarra = new PanelGraduado(new Color(214, 234, 248), new Color(93, 173, 226));
	private JXPanel pCaja_inasistencia = new JXPanel();
	private JPanel pCaja_zonas = new JPanel();
	private JPanel pCaja_despacho = new JPanel();
	private JPanel pCaja_sueldos = new JPanel();
	private JPanel pCaja_internos = new JPanel();
	private JPanel pCaja_proveedores = new JPanel();
	private JPanel pCaja_altasbajas = new JPanel();
	private JPanel pResultado_altasbajas = new JPanel();
	private JLabel resultado_altasbajas = new JLabel();
	private JLabel diferencia_altasbajas = new JLabel();
	private JPanel pCaja_controlBilletes = new JPanel();
	private JPanel pCaja_prestamos = new JPanel();
	private JPanel pCaja_monedas = new JPanel();
	private JPanel pResumen_cierre = new JPanel();
	
	private JLabel fechaL = new JLabel();
	
	private JPanel pSaldo_dia = new PanelGraduado(new Color(214, 234, 248), new Color(93, 173, 226));
	private JPanel pComandos_asistencia = new JPanel();
	private JPanel pComandos_sueldos = new JPanel();
	private JPanel pComandos_interno = new JPanel();
	private JPanel pComandos_proveedores = new JPanel();
	/*private JRadioButton cobradorOP = new JRadioButton("Cobrador");
	private JRadioButton vendedorOP = new JRadioButton("Vendedor");*/
	private JRadioButton backupOP = new JRadioButton("Backup");
	/*private JRadioButton administrativoOP = new JRadioButton("Administrativo");
	private JRadioButton jefe_calleOP = new JRadioButton("Jefe de calle");*/
	//private ButtonGroup grupo_empleados = new ButtonGroup();
	private JRadioButton salarioOP = new JRadioButton("Salario");
	private JRadioButton valeOP = new JRadioButton("Vale");
	private JRadioButton comisionOP = new JRadioButton("Comisión");
	private JRadioButton premio_efecOP = new JRadioButton("Premio");
	private JRadioButton feriadoOP = new JRadioButton("Feriado");
	private ButtonGroup grupo_sueldos = new ButtonGroup();
	private JTextField buscar_empleadoTF = new JTextField();
	private JButton buscar_empleadoB = new JButton("...");
	private JTextField buscar_empleado_sueldoTF = new JTextField();
	private JButton buscar_empleado_sueldoB = new JButton("...");
	private JTextField montoTF = new JTextField(5);
	private JTextField buscar_motivoTF = new JTextField();
	private JButton buscar_motivoB = new JButton("...");
	private JLabel empleadoL = new JLabel();
	private JLabel empleado_sueldoL = new JLabel();
	private JLabel motivoL = new JLabel();
	private JLabel motivo_inL = new JLabel();
	private JRadioButton ingresoOP = new JRadioButton("Ingreso");
	private JRadioButton egresoOP = new JRadioButton("Egreso");
	private JTextField buscar_motivo_inTF = new JTextField();
	private JButton buscar_motivo_inB = new JButton("...");
	private JTextField buscar_detalleTF = new JTextField(20);
	private JButton buscar_detalleB = new JButton("...");
	private JLabel motivo_internoL = new JLabel();
	private JLabel detalleL = new JLabel();
	private JTextField monto_internoTF = new JTextField(5);
	private JTextField buscar_proveedorTF = new JTextField();
	private JButton buscar_proveedorB = new JButton("...");
	private JTextField buscar_detalle_provTF = new JTextField();
	private JButton buscar_detalle_provB = new JButton("...");
	private JLabel proveedorL = new JLabel();
	private JLabel detalle_proveedoresL = new JLabel();
	private JTextField detalle_proveedoresTF = new JTextField(20);
	private JTextField monto_proveedorTF = new JTextField(5);
	
	private ButtonGroup grupo_ingreso_egreso = new ButtonGroup();
	private ArrayList<JTextField> ar = new ArrayList<JTextField>();
	private ArrayList<JTextField> ar_interno = new ArrayList<JTextField>();
	private ArrayList<JTextField> ar_sueldo = new ArrayList<JTextField>();
	private JFormattedTextField fi; 
	private JFormattedTextField fb;
	
	private VistaBuscarCliente vista_buscar_cliente;
	
	private ControladorCliente _controladorCliente;
	private ControladorDespacho_diario _controlador_dd;
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
	private ControladorCobrador controladorCobrador;
	private ControladorVendedor controladorVendedor;
	private ControladorJefeCalle controladorJefe_calle;
	private ControladorAdministrativo controladorAdministrativo;
	private ControladorMotivoCaja controladorMotivoCaja;
	private ControladorUsuario controladorUsuario;
	private ControladorPedidos controladorPedido;
	private ControladorSueldos controladorSueldos;
	private ControladorEmpleado controladorEmpleado;
	private ControladorDetalle_interno controladorDetalle_in;
	private ControladorProveedores controladorProveedores;
	private ControladorDetalle_proveedor controladorDetalle_prov;
	private ControladorMonedas _controladorMonedas;
	
	private ControladorCaja controladorCaja;
	
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
	
	private double ingresos;
	private double egresos;
	private double saldo_dia;
	
	private JLabel ingresosL;
	private JLabel egresosL;
	private JLabel saldo_diaL;
	private JLabel saldo_dia_doubleL;
	
	private Pedido_articuloVO aux_combo;
	private ArticulosVO aux_articulo;
	private ClienteVO aux_cliente;
	private ArticulosPVO aux_prestamo;
	private Refinanciacion_exVO aux_ref;
	private Refinanciacion_inVO aux_ref_in;
	private CobradorVO _cobradorVO;
	
	private VistaRefinanciacion vf;
	private VistaRefinanciacion_in vf_in;
	private VistaMonto_t mt;
	private VistaObservacionesPedido obser;
	private Vista_pagos_porPedido v_pagos;
	
	private JTable tabla_asistencia;
	private JTable tabla_asistencia_impresion;
	private DefaultTableModel t_model_asistencia;
	private DefaultTableModel t_model_asistencia_impresion;
	private JScrollPane scr_asistencia;
	
	private JTable tabla_zonas;
	private JTable tabla_zonas_impresion;
	private DefaultTableModel t_model_zonas;
	private DefaultTableModel t_model_zonas_impresion;
	private JScrollPane scr_zonas;
	
	private JTable tabla_despacho;
	private DefaultTableModel t_model_despacho;
	private JScrollPane scr_despacho;
	
	private JTable tabla_interno;
	private DefaultTableModel t_model_interno;
	private JScrollPane scr_interno;
	
	private JTable tabla_sueldos;
	private DefaultTableModel t_model_sueldos;
	private JScrollPane scr_sueldos;
	
	private JTable tabla_prestamos;
	private DefaultTableModel t_model_prestamos;
	private JScrollPane scr_prestamos;
	
	private JTable tabla_proveedores;
	private DefaultTableModel t_model_proveedores;
	private JScrollPane scr_proveedores;
	
	private JTable tabla_altasbajas;
	private DefaultTableModel t_model_altasbajas;
	private JScrollPane scr_altasbajas;
	
	private JTable tabla_billetes;
	private DefaultTableModel t_model_billetes;
	private JScrollPane scr_billetes;
	
	private JTable tabla_monedas;
	private DefaultTableModel t_model_monedas;
	private JScrollPane scr_monedas;

	private JTable tabla_resumen;
	private JTable tabla_resumen_impresion;
	private DefaultTableModel t_model_resumen;
	private DefaultTableModel t_model_resumen_impresion;
	private JScrollPane scr_resumen;
	
	private float[] columnWidthPercentage = {20.0f, 40.0f, 10.0f, 10.0f, 10.0f, 10.0f};
	
	private JButton guardar = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
    private JButton actualizar = new JButton(){
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
    private JButton agregar_inasistencia = new JButton("Agregar");
    private JButton agregar_interno = new JButton("Agregar");
    private JButton agregar_sueldo = new JButton("Agregar");
    private JButton agregar_pago_proveedor = new JButton("Agregar");
    private JButton eliminar_inasistencia = new JButton("Eliminar registro");
    private JButton eliminar_interno = new JButton("Eliminar registro");
    private JButton eliminar_sueldo = new JButton("Eliminar registro");
    private JButton eliminar_pago_proveedor = new JButton("Eliminar registro");
   
    private JButton imprimir = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };
    
    private JButton ver_fi = new JButton("Ver");
    private JButton ver_fb = new JButton("Ver");
	
	private boolean[] canEdit_asistencia= new boolean[]{
            false, false, false, false
    };
	private boolean[] canEdit_zonas= new boolean[]{
            false, false, false, false, false, false,
            false, false, false, false, false, false,
            false, false, false, false, false, false,
            false, false, false, false, false, false
    };
	private boolean[] canEdit_despacho= new boolean[]{
            false, false, false, false, false, false,
            false, false, false, false
    };
	private boolean[] canEdit_sueldos= new boolean[]{
            false, false, false, false, false, false,
            false
    };
	private boolean[] canEdit_internos= new boolean[]{
            false, false, false, false, false, false,
            false, false
    };
	private boolean[] canEdit_proveedores= new boolean[]{
			false, false, false, false, false, false,
            false
    };
	private boolean[] canEdit_altasbajas= new boolean[]{
            false, false, false, false
    };
	private boolean[] canEdit_billetes= new boolean[]{
            false, false, false, false, false, false
    };
	
	private boolean[] canEditprestamos= new boolean[]{
			false, false, false, false, false, false,
            false, false, false, false
    };
	
	private boolean[] canEdit_monedas= new boolean[]{
            false, false, false, false
    };

	private boolean[] canEdit_resumen= new boolean[]{
			false, false, false, false, false,
			false, false, false, false, false,
			false, false, false, false, false, 
			false, false, false, false, false
	};
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static double width = screenSize.getWidth();
    double height = screenSize.getHeight();
	
	public VistaCaja(){
		
		this.setLayout(new BorderLayout());
		
		guardar.addActionListener(this);
		actualizar.addActionListener(this);
		agregar_inasistencia.addActionListener(this);
		this.buscar_empleadoB.addActionListener(this);
		buscar_motivoB.addActionListener(this);
		buscar_motivo_inB.addActionListener(this);
		buscar_detalleB.addActionListener(this);
		buscar_proveedorB.addActionListener(this);
		buscar_detalle_provB.addActionListener(this);
		eliminar_inasistencia.addActionListener(this);
		eliminar_interno.addActionListener(this);
		eliminar_sueldo.addActionListener(this);
		eliminar_pago_proveedor.addActionListener(this);
		agregar_sueldo.addActionListener(this);
		agregar_pago_proveedor.addActionListener(this);
		buscar_empleado_sueldoB.addActionListener(this);
		agregar_interno.addActionListener(this);
		imprimir.addActionListener(this);
		ver_fb.addActionListener(this);
		
		buscar_empleadoB.setFocusable(false);
		buscar_motivoB.setFocusable(false);
		buscar_motivo_inB.setFocusable(false);
		buscar_detalleB.setFocusable(false);
		buscar_empleado_sueldoB.setFocusable(false);
		agregar_interno.setFocusable(false);
		
		
		buscar_empleadoTF.setFocusable(true);
		//buscar_empleadoTF.setBackground(new Color(183, 242, 113));
		
		buscar_empleadoTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				buscar_empleadoTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
				if(controladorEmpleado.buscarEmpleado(buscar_empleadoTF.getText())!=null){
					
					EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(buscar_empleadoTF.getText());
					
					empleadoL.setText(eVO.getNombre() + " " + eVO.getApellido());
				}
					
				else{
					empleadoL.setText("");
					buscar_empleadoTF.setText("");
				}
					
				
				buscar_empleadoTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
		
		buscar_motivoTF.setFocusable(true);
		
		buscar_motivoTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				buscar_motivoTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(controladorCaja.buscarMotivo_generalUsuario(buscar_motivoTF.getText())!=null)
					
					motivoL.setText(controladorCaja.buscarMotivo_generalUsuario(buscar_motivoTF.getText()));
				else{
					motivoL.setText("");
					buscar_motivoTF.setText("");
				}
					
				
				buscar_motivoTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
		
		
		
		ar.add(buscar_empleadoTF);
		ar.add(buscar_motivoTF);
		
		t_model_despacho = new DefaultTableModel(null, getColumnas_despacho()){
			 
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit_despacho[columnIndex];
	            }
		};
		
		tabla_despacho = new JTable();
		tabla_despacho.getTableHeader().setReorderingAllowed(false);
		tabla_despacho.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
		tabla_despacho.setFont(new Font("Arial", Font.PLAIN, 12));	
		tabla_despacho.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		
	
	    scr_despacho = new JScrollPane();
	    scr_despacho.setViewportView(tabla_despacho);
		
	    t_model_asistencia = new DefaultTableModel(null, getColumnas_asistencia()){
			 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit_asistencia[columnIndex];
            }
	    };
	    t_model_asistencia_impresion = new DefaultTableModel(null, getColumnas_asistencia()){
	   
	    };
	
	    tabla_asistencia = new JTable();
	    tabla_asistencia_impresion = new JTable();
	    tabla_asistencia.getTableHeader().setReorderingAllowed(false);
	    tabla_asistencia.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
	    tabla_asistencia.setFont(new Font("Arial", Font.PLAIN, 12));	
	    tabla_asistencia.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
	    tabla_asistencia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    scr_asistencia = new JScrollPane();
	    scr_asistencia.setViewportView(tabla_asistencia);
	    
	    t_model_zonas = new DefaultTableModel(null, getColumnas_zonas()){
			 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit_zonas[columnIndex];
            }
	    };
	    t_model_zonas_impresion = new DefaultTableModel(null, getColumnas_zonas_impresion()){
	    	
	    };
	    
	    tabla_zonas = new JTable();
	    tabla_zonas_impresion = new JTable();
	    tabla_zonas.getTableHeader().setReorderingAllowed(false);
	    tabla_zonas.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
	    tabla_zonas.setFont(new Font("Arial", Font.PLAIN, 12));	
	    tabla_zonas.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
	    tabla_zonas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    
	   // scr_zonas = new JScrollPane(tabla_zonas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	    //		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scr_zonas = new JScrollPane();
	    scr_zonas.setViewportView(tabla_zonas);
	    //scr_zonas.setPreferredSize(scr_zonas.getPreferredSize());
	   
	    
	    tabla_prestamos = new JTable();
	    tabla_prestamos.getTableHeader().setReorderingAllowed(false);
	    tabla_prestamos.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
	    tabla_prestamos.setFont(new Font("Arial", Font.PLAIN, 12));	
	    tabla_prestamos.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

	    scr_prestamos = new JScrollPane();
	    scr_prestamos.setViewportView(tabla_prestamos);
	    
	    t_model_prestamos = new DefaultTableModel(null, getColumnas_prestamos()){
			 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEditprestamos[columnIndex];
            }
	    };
	
	    buscar_motivo_inTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				buscar_motivo_inTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(controladorMotivoCaja.buscarMotivo_internaUsuario(buscar_motivo_inTF.getText())!=null){
					
					String motivo = controladorMotivoCaja.
							buscarMotivo_internaUsuario(buscar_motivo_inTF.getText()).getDescripcion();
					motivo_inL.setText(motivo);
				}

					
				else{
					motivo_inL.setText("");
					buscar_motivo_inTF.setText("");
				}
					
				detalleL.setText("");
				buscar_detalleTF.setText("");
				buscar_motivo_inTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
		
		buscar_detalleTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				buscar_detalleTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				/*if(controladorDetalle_in.buscarDetalle_internoUsuario(buscar_detalleTF.getText())!=null &&
						controladorDetalle_in.
						buscarDetalle_internoUsuario(buscar_detalleTF.getText()).getId_motivo()== 
						Short.parseShort(buscar_motivo_inTF.getText()))
					
					detalleL.setText(controladorDetalle_in.
							buscarDetalle_internoUsuario(buscar_detalleTF.getText()).getDescripcion());
				else{
					detalleL.setText("");
					buscar_detalleTF.setText("");
				}*/
					
				
				buscar_detalleTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
		
		buscar_proveedorTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				buscar_proveedorTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(controladorProveedores.buscarProveedorUsuario(buscar_proveedorTF.getText())!=null){
					
					String proveedor = controladorProveedores.
							buscarProveedorUsuario(buscar_proveedorTF.getText()).getNombre();
					proveedorL.setText(proveedor);
				}

					
				else{
					proveedorL.setText("");
					buscar_proveedorTF.setText("");
				}
					
				buscar_proveedorTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
		
		buscar_detalle_provTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				buscar_detalle_provTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(controladorDetalle_in.buscarDetalle_internoUsuario(buscar_detalle_provTF.getText())!=null &&
						controladorDetalle_in.
						buscarDetalle_internoUsuario(buscar_detalle_provTF.getText()).getId_motivo()== 
						Short.parseShort(buscar_motivo_inTF.getText()))
					
					detalleL.setText(controladorDetalle_in.
							buscarDetalle_internoUsuario(buscar_detalle_provTF.getText()).getDescripcion());
				else{
					detalle_proveedoresL.setText("");
					buscar_detalle_provTF.setText("");
				}
					
				
				buscar_detalle_provTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
		
		buscar_empleado_sueldoTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				buscar_empleado_sueldoTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String puesto = "";
				
				if(controladorEmpleado.buscarEmpleadoUsuario(
						buscar_empleado_sueldoTF.getText())!=null)
					
					empleado_sueldoL.setText(controladorEmpleado.buscarEmpleadoUsuario(
							buscar_empleado_sueldoTF.getText()));
				else{
					empleado_sueldoL.setText("");
					buscar_empleado_sueldoTF.setText("");
				}
					
				
				buscar_empleado_sueldoTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
		
		monto_proveedorTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				monto_proveedorTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
				monto_proveedorTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
		
		detalle_proveedoresTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				detalle_proveedoresTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
				detalle_proveedoresTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
		
		montoTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				montoTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
				montoTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
		
		monto_internoTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				monto_internoTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
				monto_internoTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
		
		t_model_sueldos = new DefaultTableModel(null, getColumnas_sueldos()){
			 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit_sueldos[columnIndex];
            }
	    };
	
	    tabla_sueldos = new JTable();
	    tabla_sueldos.getTableHeader().setReorderingAllowed(false);
	    tabla_sueldos.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
	    tabla_sueldos.setFont(new Font("Arial", Font.PLAIN, 12));	
	    tabla_sueldos.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

	    scr_sueldos = new JScrollPane();
	    scr_sueldos.setViewportView(tabla_sueldos);
	    
	    ar_sueldo.add(buscar_empleado_sueldoTF);
	    ar_sueldo.add(montoTF);
	    
		ar_interno.add(buscar_motivo_inTF);
		ar_interno.add(buscar_detalleTF);
		ar_interno.add(monto_internoTF);
		
	    t_model_interno = new DefaultTableModel(null, getColumnas_interna()){
			 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit_internos[columnIndex];
            }
	    };
	
	    tabla_interno = new JTable();
	    tabla_interno.getTableHeader().setReorderingAllowed(false);
	    tabla_interno.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
	    tabla_interno.setFont(new Font("Arial", Font.PLAIN, 12));	
	    tabla_interno.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

	    scr_interno = new JScrollPane();
	   scr_interno.setViewportView(tabla_interno);
	    
	    t_model_proveedores = new DefaultTableModel(null, getColumnas_proveedores()){
			 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit_proveedores[columnIndex];
            }
	    };
	
	    tabla_proveedores = new JTable();
	    tabla_proveedores.getTableHeader().setReorderingAllowed(false);
	    tabla_proveedores.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
	    tabla_proveedores.setFont(new Font("Arial", Font.PLAIN, 12));	
	    tabla_proveedores.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

	    scr_proveedores = new JScrollPane();
	    scr_proveedores.setViewportView(tabla_proveedores);
	    
	    t_model_altasbajas = new DefaultTableModel(null, getColumnas_altasbajas()){
			 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit_altasbajas[columnIndex];
            }
	    };
	
	    tabla_altasbajas = new JTable();
	    tabla_altasbajas.getTableHeader().setReorderingAllowed(false);
	    tabla_altasbajas.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
	    tabla_altasbajas.setFont(new Font("Arial", Font.PLAIN, 12));	
	    tabla_altasbajas.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

	    scr_altasbajas = new JScrollPane();
	    scr_altasbajas.setViewportView(tabla_altasbajas);
	    
	    t_model_billetes = new DefaultTableModel(null, getColumnas()){
			 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit_billetes[columnIndex];
            }
	    };
	
	    tabla_billetes = new JTable();
	    tabla_billetes.getTableHeader().setReorderingAllowed(false);
	    tabla_billetes.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
	    tabla_billetes.setFont(new Font("Arial", Font.PLAIN, 12));	
	    tabla_billetes.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

	    scr_billetes = new JScrollPane();
	    scr_billetes.setViewportView(tabla_billetes);
	    
	    t_model_monedas = new DefaultTableModel(null, getColumnas_monedas()){
			 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit_monedas[columnIndex];
            }
	    };
	
	    tabla_monedas = new JTable();
	    tabla_monedas.getTableHeader().setReorderingAllowed(false);
	    tabla_monedas.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
	    tabla_monedas.setFont(new Font("Arial", Font.PLAIN, 12));	
	    tabla_monedas.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

	    scr_monedas = new JScrollPane();
	    scr_monedas.setViewportView(tabla_monedas);

	    t_model_resumen = new DefaultTableModel(null, getColumnas_resumen()){
	    	
	    	public boolean isCellEditable(int rowIndex, int columnIndex) {
	    		return canEdit_resumen[columnIndex];
	    	}
	    };
	    t_model_resumen_impresion = new DefaultTableModel(null, getColumnas_resumen_impresion()){
	    	
	    };
	    
	    tabla_resumen = new JTable();
	    tabla_resumen_impresion = new JTable();
	    tabla_resumen.setModel(t_model_resumen);
	    tabla_resumen_impresion.setModel(t_model_resumen_impresion);
	    tabla_resumen.getTableHeader().setReorderingAllowed(false);
	    tabla_resumen.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
	    tabla_resumen.setFont(new Font("Arial", Font.PLAIN, 12));	
	    tabla_resumen.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
	    
	    scr_resumen = new JScrollPane();
	    scr_resumen.setViewportView(tabla_resumen);
	    
	    centrar_columnas(tabla_despacho);
	    centrar_columnas(tabla_zonas);
	    centrar_columnas(tabla_asistencia);
	    centrar_columnas(tabla_interno);
	    centrar_columnas(tabla_proveedores);
	    centrar_columnas(tabla_altasbajas);
	    centrar_columnas(tabla_billetes);
	    centrar_columnas(tabla_monedas);
	    centrar_columnas(tabla_resumen);
	      
		BH_caja bh = new BH_caja(guardar, imprimir, actualizar);
		
		pBarra.setLayout(new BorderLayout());
		
		GridLayout gl = new GridLayout(0,2);
		
		JPanel p = new JPanel();
		p.setLayout(gl);
		JPanel pIntegraBarra = new PanelGraduado(new Color(214, 234, 248), new Color(93, 173, 226));
		
		Date d = new Date();
		java.sql.Date hoy = new java.sql.Date(d.getTime());
		
		fechaL = new JLabel();
		
		setLabelFecha(hoy);
		
		JLabel fecha_busquedaL = new JLabel();
		fecha_busquedaL.setText("Fecha de consulta");
		JPanel p_fb = new PanelGraduado(new Color(214, 234, 248), new Color(93, 173, 226));
	
    	Border bordefb = BorderFactory.createTitledBorder(null, "Fecha de consulta", 
    	    	TitledBorder.CENTER, TitledBorder.TOP,
    	    	new Font("Arial",Font.PLAIN,16), Color.BLACK);
    	p_fb.setBorder(bordefb);
		
		modelFB.setSelected(true);
		
        //model.setDate(20,04,2014);
  		Properties pr = new Properties();
  		pr.put("text.today", "Today");
  		pr.put("text.month", "Month");
  		pr.put("text.year", "Year");
        JDatePanelImpl datePanelFB = new JDatePanelImpl(modelFB, pr);
       
        fecha_busqueda = new JDatePickerImpl(datePanelFB, new DateLabelFormatter());
		
        p_fb.add(fecha_busqueda);
        p_fb.add(ver_fb);
        
		pIntegraBarra.add(fechaL);
		pIntegraBarra.add(p_fb);
		
		 //fi = fecha_ingreso.getJFormattedTextField();
		 //fi.setFont(new Font("Arial", Font.PLAIN, 18));
		  fb= fecha_busqueda.getJFormattedTextField();
		 fb.setFont(new Font("Arial", Font.PLAIN, 18));
		 
		ingresosL = new JLabel();
		egresosL = new JLabel();
		saldo_diaL = new JLabel();
		saldo_dia_doubleL = new JLabel();
		
		ingresos = 0;
		egresos = 0;
		saldo_dia = 0;
		
		ingresosL.setFont(new Font("Arial",Font.BOLD,22));
		egresosL.setFont(new Font("Arial",Font.BOLD,22));
		saldo_diaL.setFont(new Font("Arial",Font.BOLD,22));
		saldo_dia_doubleL.setFont(new Font("Arial",Font.BOLD,22)); 
		
		pSaldo_dia.add(ingresosL);
		pSaldo_dia.add(egresosL);
		pSaldo_dia.add(saldo_diaL);
		pSaldo_dia.add(saldo_dia_doubleL);
		
		pBarra.add(pIntegraBarra, BorderLayout.WEST);
		pBarra.add(pSaldo_dia, BorderLayout.EAST);
		pIntegra.setLayout(new BorderLayout());
		
		Border borde0 = BorderFactory.createTitledBorder(null, "Caja diaria", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,20), Color.BLACK);
		
		pIntegra.setBorder(borde0);
		pIntegra.setBackground(Color.WHITE);
    			
    	this.setBackground(Color.white);
		
    	this.add(bh, BorderLayout.PAGE_START);
    	
    	pIntegra.add(pBarra, BorderLayout.PAGE_START);
    	
    	/*cobradorOP.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cobradorOP.isSelected()){
					
					empleadoL.setText("");
					buscar_empleadoTF.setText("");
					motivoL.setText("");
					buscar_motivoTF.setText("");
				}
			}
    				
    	});
    	vendedorOP.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(vendedorOP.isSelected()){
					
					empleadoL.setText("");
					buscar_empleadoTF.setText("");
					motivoL.setText("");
					buscar_motivoTF.setText("");
				}
			}
    				
    	});
    	administrativoOP.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(administrativoOP.isSelected()){
					
					empleadoL.setText("");
					buscar_empleadoTF.setText("");
					motivoL.setText("");
					buscar_motivoTF.setText("");
				}
			}
    				
    	});
    	jefe_calleOP.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jefe_calleOP.isSelected()){
					
					empleadoL.setText("");
					buscar_empleadoTF.setText("");
					motivoL.setText("");
					buscar_motivoTF.setText("");
				}
			}
    				
    	});
    	
    	grupo_empleados.add(cobradorOP);
    	grupo_empleados.add(vendedorOP);
    	grupo_empleados.add(backupOP);
    	grupo_empleados.add(jefe_calleOP);
    	grupo_empleados.add(administrativoOP);
    	
    	cobradorOP.setSelected(true);*/
    	
    	//pCaja_inasistencia.setLayout(new BorderLayout());
    	pCaja_altasbajas.setLayout(new BorderLayout());
    	
    	JPanel pNueva_inasistencia = new JPanel();
    	
    	/*pNueva_inasistencia.add(cobradorOP);
    	pNueva_inasistencia.add(vendedorOP);
    	//pNueva_inasistencia.add(backupOP);
    	pNueva_inasistencia.add(administrativoOP);
    	pNueva_inasistencia.add(jefe_calleOP);*/
    	
    	JPanel pBus = new JPanel();
    	JPanel pMotivo = new JPanel();
    	pBus.setLayout(gl);
    	pMotivo.setLayout(gl);
    	
    	pBus.add(buscar_empleadoTF);
    	pBus.add(buscar_empleadoB);
    	pMotivo.add(buscar_motivoTF);
    	pMotivo.add(buscar_motivoB);
    	
    	pNueva_inasistencia.add(pBus);
    	pNueva_inasistencia.add(empleadoL);
    	JLabel motL = new JLabel();
    	motL.setText("Motivo");
    	pNueva_inasistencia.add(motL);
    	pNueva_inasistencia.add(pMotivo);
    	pNueva_inasistencia.add(motivoL);
    	pNueva_inasistencia.add(agregar_inasistencia);
    	Border bordeNa = BorderFactory.createTitledBorder(null, "Agregar inasistencia", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.PLAIN,14), Color.BLACK);
    	pNueva_inasistencia.setBorder(bordeNa);
		pIntegra.setBackground(Color.WHITE);
		
    	pComandos_asistencia.add(pNueva_inasistencia);
    		
    	pComandos_asistencia.add(eliminar_inasistencia);
    	
    	pCaja_inasistencia.setBackground(Color.white);
    	pCaja_zonas.setBackground(Color.white);
    	pCaja_despacho.setBackground(Color.white);
    	pCaja_prestamos.setBackground(Color.white);
    
    	
    	JPanel pNuevo_sueldo = new JPanel();
    	
    	this.grupo_sueldos.add(salarioOP);
    	grupo_sueldos.add(valeOP);
    	this.grupo_sueldos.add(comisionOP);
    	grupo_sueldos.add(premio_efecOP);
    	grupo_sueldos.add(feriadoOP);
    	
    	salarioOP.setSelected(true);
    	pNuevo_sueldo.add(salarioOP);
    	pNuevo_sueldo.add(valeOP);
    	pNuevo_sueldo.add(comisionOP);
    	pNuevo_sueldo.add(premio_efecOP);
    	pNuevo_sueldo.add(feriadoOP);

    	
    	JPanel pEmpleado = new JPanel();
    	pEmpleado.setLayout(gl);
    	
    	JLabel empleadoL = new JLabel();
    	empleadoL.setText("Empleado");
    	JLabel montoL = new JLabel();
    	montoL.setText("Monto");
    	pNuevo_sueldo.add(empleadoL);
    	
    	pEmpleado.add(buscar_empleado_sueldoTF);
    	pEmpleado.add(buscar_empleado_sueldoB);
    	
    	pNuevo_sueldo.add(pEmpleado);
    	pNuevo_sueldo.add(empleado_sueldoL);
    	pNuevo_sueldo.add(montoL);
    	pNuevo_sueldo.add(montoTF);
    	pNuevo_sueldo.add(agregar_sueldo);
    	Border bordeNa_sueldo = BorderFactory.createTitledBorder(null, "Agregar remuneración", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.PLAIN,14), Color.BLACK);
    	pNuevo_sueldo.setBorder(bordeNa_sueldo);
    	
    	pComandos_sueldos.add(pNuevo_sueldo);
    	pComandos_sueldos.add(eliminar_sueldo);
    	
    	egresoOP.setSelected(true);
    	
    	this.grupo_ingreso_egreso.add(ingresoOP);
    	this.grupo_ingreso_egreso.add(egresoOP);
    	
    	JPanel pNueva_interna = new JPanel();
    	
    	//pNueva_interna.add(ingresoOP);
    	//pNueva_interna.add(egresoOP);

    	
    	JPanel pDetalle_in = new JPanel();
    	JPanel pMotivo_in = new JPanel();
    	pMotivo_in.setLayout(gl);
    	pDetalle_in.setLayout(gl);
    	JLabel mot_inL = new JLabel();
    	mot_inL.setText("Motivo");
    	pNueva_interna.add(mot_inL);	
    	pMotivo_in.add(buscar_motivo_inTF);
    	pMotivo_in.add(buscar_motivo_inB);
    	//pDetalle_in.add(buscar_detalleTF);
    	//pDetalle_in.add(buscar_detalleB); 	
    	pNueva_interna.add(pMotivo_in);
    	pNueva_interna.add(motivo_inL);	
    	JLabel detL = new JLabel();
    	detL.setText("Detalle");
    	pNueva_interna.add(detL);
    	pNueva_interna.add(buscar_detalleTF);		
    	//pNueva_interna.add(detalleL);	
    	
    	JLabel monto_internoL = new JLabel();
    	monto_internoL.setText("Monto");
    	pNueva_interna.add(monto_internoL);
    	pNueva_interna.add(monto_internoTF);
    	pNueva_interna.add(agregar_interno);
    	Border bordeNa_in = BorderFactory.createTitledBorder(null, "Agregar movimiento interno", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.PLAIN,14), Color.BLACK);
    	pNueva_interna.setBorder(bordeNa_in);	
    	pComandos_interno.add(pNueva_interna);
    	pComandos_interno.add(eliminar_interno);
    	
    	pCaja_internos.setBackground(Color.white);
    	pCaja_sueldos.setBackground(Color.white);
    	pCaja_proveedores.setBackground(Color.white);
    	pCaja_altasbajas.setBackground(Color.white);
    	pCaja_controlBilletes.setBackground(Color.white);
    	
    	pCaja_inasistencia.setLayout(new BoxLayout(pCaja_inasistencia, BoxLayout.PAGE_AXIS));
    	JLabel titulo_inasistencia = new JLabel();
    	titulo_inasistencia.setText("Inasistencias");
    	titulo_inasistencia.setFont(new Font("Arial",Font.BOLD,16));
    	titulo_inasistencia.setForeground(new Color(36, 113, 163));
    	JPanel pTitulo_inasistencia = new PanelGraduado(new Color(213, 245, 227), new Color(169, 223, 191));
    	pTitulo_inasistencia.setBackground(new Color(212, 239, 223));
    	pTitulo_inasistencia.add(titulo_inasistencia);
    	pCaja_inasistencia.add(pTitulo_inasistencia);
    	pCaja_inasistencia.add(pComandos_asistencia);
    	scr_asistencia.setPreferredSize(new Dimension(800, 220));
    	pCaja_inasistencia.add(scr_asistencia);
    
    	pCaja_zonas.setLayout(new BoxLayout(pCaja_zonas, BoxLayout.PAGE_AXIS));
    	
    	JLabel titulo_zonas = new JLabel();
    	titulo_zonas.setText("Recaudación por zonas");
    	titulo_zonas.setFont(new Font("Arial",Font.BOLD,16));
    	titulo_zonas.setForeground(new Color(36, 113, 163));
    	JPanel pTitulo_zonas = new PanelGraduado(new Color(213, 245, 227), new Color(169, 223, 191));
    	pTitulo_zonas.setBackground(new Color(212, 239, 223));
    	pTitulo_zonas.add(titulo_zonas);
    	pCaja_zonas.add(pTitulo_zonas);
    	scr_zonas.setPreferredSize(new Dimension(800, 220));
    	pCaja_zonas.add(scr_zonas);
    	
    	resultado_altasbajas.setFont(new Font("Arial", Font.BOLD, 16));
    	diferencia_altasbajas.setFont(new Font("Arial", Font.BOLD, 16));
    	pResultado_altasbajas.add(resultado_altasbajas);
    	pResultado_altasbajas.add(diferencia_altasbajas);
    	
    	JLabel titulo_altasbajas = new JLabel();
    	titulo_altasbajas.setText("Altas/Bajas de pedidos");
    	titulo_altasbajas.setFont(new Font("Arial",Font.BOLD,16));
    	titulo_altasbajas.setForeground(new Color(36, 113, 163));
    	JPanel pTitulo_altasbajas = new PanelGraduado(new Color(213, 245, 227), new Color(169, 223, 191));
    	pTitulo_altasbajas.setBackground(new Color(212, 239, 223));
    	pTitulo_altasbajas.add(titulo_altasbajas);
    	pCaja_altasbajas.add(pTitulo_altasbajas, BorderLayout.PAGE_START);
    	scr_altasbajas.setPreferredSize(new Dimension(800, 200));
    	pCaja_altasbajas.add(scr_altasbajas, BorderLayout.CENTER);
    	pCaja_altasbajas.add(pResultado_altasbajas, BorderLayout.PAGE_END);
    	
    	pCaja_despacho.setLayout(new BoxLayout(pCaja_despacho, BoxLayout.PAGE_AXIS));
    	JLabel titulo_despacho = new JLabel();
    	titulo_despacho.setText("Ingresos por despacho");
    	titulo_despacho.setFont(new Font("Arial",Font.BOLD,16));
    	titulo_despacho.setForeground(new Color(36, 113, 163));
    	JPanel pTitulo_despacho = new PanelGraduado(new Color(213, 245, 227), new Color(169, 223, 191));
    	pTitulo_despacho.setBackground(new Color(212, 239, 223));
    	pTitulo_despacho.add(titulo_despacho);
    	pCaja_despacho.add(pTitulo_despacho);
    	scr_despacho.setPreferredSize(new Dimension(800, 220));
    	pCaja_despacho.add(scr_despacho);
    	
    	pCaja_prestamos.setLayout(new BoxLayout(pCaja_prestamos, BoxLayout.PAGE_AXIS));
    	JLabel titulo_prestamos = new JLabel();
    	titulo_prestamos.setText("Entrega de préstamos");
    	titulo_prestamos.setFont(new Font("Arial",Font.BOLD,16));
    	titulo_prestamos.setForeground(new Color(36, 113, 163));
    	JPanel pTitulo_prestamos = new PanelGraduado(new Color(213, 245, 227), new Color(169, 223, 191));
    	pTitulo_prestamos.setBackground(new Color(212, 239, 223));
    	pTitulo_prestamos.add(titulo_prestamos);
    	pCaja_prestamos.add(pTitulo_prestamos);
    	scr_prestamos.setPreferredSize(new Dimension(800, 220));
    	pCaja_prestamos.add(scr_prestamos);
    	
    	//pCaja_sueldos.setLayout(new BorderLayout());
    	pCaja_sueldos.setLayout(new BoxLayout(pCaja_sueldos, BoxLayout.PAGE_AXIS));
    	
    	/*pCaja_sueldos.add(pComandos_sueldos, BorderLayout.PAGE_START);
    	pCaja_sueldos.add(scr_sueldos, BorderLayout.CENTER);	*/
    	//pCaja_sueldos.setLayout(new BoxLayout(pCaja_sueldos, BoxLayout.PAGE_AXIS));
    	JLabel titulo_sueldos = new JLabel();
    	titulo_sueldos.setText("Remuneraciones");
    	titulo_sueldos.setFont(new Font("Arial",Font.BOLD,16));
    	titulo_sueldos.setForeground(new Color(36, 113, 163));
    	JPanel pTitulo_sueldos = new PanelGraduado(new Color(213, 245, 227), new Color(169, 223, 191));
    	pTitulo_sueldos.setBackground(new Color(212, 239, 223));
    	pTitulo_sueldos.add(titulo_sueldos);
    	pCaja_sueldos.add(pTitulo_sueldos);
    	pCaja_sueldos.add(pComandos_sueldos);
    	scr_sueldos.setPreferredSize(new Dimension(800, 220));
    	pCaja_sueldos.add(scr_sueldos);	
    	
    	
    	pCaja_internos.setLayout(new BoxLayout(pCaja_internos, BoxLayout.PAGE_AXIS));
    	
    	JLabel titulo_internos = new JLabel();
    	titulo_internos.setText("Movimientos internos");
    	titulo_internos.setFont(new Font("Arial",Font.BOLD,16));
    	titulo_internos.setForeground(new Color(36, 113, 163));
    	
    	DropShadowBorder shadowt = new DropShadowBorder();
    	
        shadowt.setShadowColor(new Color(82, 190, 128));
        shadowt.setShowTopShadow(true);
        //shadowt.setShadowSize(10);
    	JPanel pTitulo_internos = new PanelGraduado(new Color(213, 245, 227), new Color(169, 223, 191));
    	pTitulo_internos.setBackground(new Color(212, 239, 223));
    	//pTitulo_internos.setBorder(shadowt);
    	pTitulo_internos.add(titulo_internos);
    	pCaja_internos.add(pTitulo_internos);
    	pCaja_internos.add(pComandos_interno);
    	scr_interno.setPreferredSize(new Dimension(800, 220));
    	pCaja_internos.add(scr_interno);
    	
    	
    	JPanel pNueva_proveedores = new JPanel();
    	
    	//pNueva_interna.add(ingresoOP);
    	//pNueva_interna.add(egresoOP);

    	
    	JPanel pDetalle_proveedores = new JPanel();
    	JPanel pProveedores = new JPanel();
    	pProveedores.setLayout(gl);
    	pDetalle_proveedores.setLayout(gl);
    	JLabel proveedoresL = new JLabel();
    	proveedoresL.setText("Proveedor");
    	Border bordeNa_prov = BorderFactory.createTitledBorder(null, "Agregar pago a proveedor", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.PLAIN,14), Color.BLACK);
    	pNueva_proveedores.setBorder(bordeNa_prov);
    	pNueva_proveedores.add(proveedoresL);	
    	pProveedores.add(buscar_proveedorTF);
    	pProveedores.add(buscar_proveedorB);
    	pDetalle_proveedores.add(buscar_detalle_provTF);
    	pDetalle_proveedores.add(buscar_detalle_provB); 	
    	pNueva_proveedores.add(pProveedores);
    	pNueva_proveedores.add(proveedorL);	
    	JLabel detalle_provL = new JLabel();
    	detalle_provL.setText("Detalle");
    	pNueva_proveedores.add(detalle_provL);
    	pNueva_proveedores.add(detalle_proveedoresTF);		
    	//pNueva_proveedores.add(detalle_proveedoresL);	
    	
    	JLabel monto_proveedoresL = new JLabel();
    	monto_proveedoresL.setText("Monto");
    	pNueva_proveedores.add(monto_proveedoresL);
    	pNueva_proveedores.add(monto_proveedorTF);
    	pNueva_proveedores.add(agregar_pago_proveedor);
    	pComandos_proveedores.add(pNueva_proveedores);
    	pComandos_proveedores.add(eliminar_pago_proveedor);
    	pCaja_proveedores.setLayout(new BoxLayout(pCaja_proveedores, BoxLayout.PAGE_AXIS));
    	
    	JLabel titulo_proveedores = new JLabel();
    	titulo_proveedores.setText("Gastos por proveedores");
    	titulo_proveedores.setFont(new Font("Arial",Font.BOLD,16));
    	titulo_proveedores.setForeground(new Color(36, 113, 163));
    	JPanel pTitulo_proveedores = new PanelGraduado(new Color(213, 245, 227), new Color(169, 223, 191));
    	pTitulo_proveedores.setBackground(new Color(212, 239, 223));
    	//pTitulo_internos.setBorder(shadowt);
    	pTitulo_proveedores.add(titulo_proveedores);
    	pCaja_proveedores.add(pTitulo_proveedores);
    	pCaja_proveedores.add(pComandos_proveedores);
    	scr_proveedores.setPreferredSize(new Dimension(800, 220));
    	pCaja_proveedores.add(scr_proveedores);
    	
    	pCaja_controlBilletes.add(scr_billetes);
    	
    	pCaja_monedas.setLayout(new BoxLayout(pCaja_monedas, BoxLayout.PAGE_AXIS));
    	pResumen_cierre.setLayout(new BoxLayout(pResumen_cierre, BoxLayout.PAGE_AXIS));
    	
    	JLabel titulo_monedas = new JLabel();
    	titulo_monedas.setText("Monedas");
    	titulo_monedas.setFont(new Font("Arial",Font.BOLD,16));
    	titulo_monedas.setForeground(new Color(36, 113, 163));
    	JPanel pTitulo_monedas = new PanelGraduado(new Color(213, 245, 227), new Color(169, 223, 191));
    	pTitulo_monedas.setBackground(new Color(212, 239, 223));
    	pTitulo_monedas.add(titulo_monedas);
    	pCaja_monedas.add(pTitulo_monedas);
    	scr_monedas.setPreferredSize(new Dimension(800, 220));
    	pCaja_monedas.add(scr_monedas);

    	JLabel titulo_resumen = new JLabel();
    	titulo_resumen.setText("Resumen de cierre de caja");
    	titulo_resumen.setFont(new Font("Arial",Font.BOLD,16));
    	titulo_resumen.setForeground(new Color(36, 113, 163));
    	JPanel pTitulo_resumen = new PanelGraduado(new Color(213, 245, 227), new Color(169, 223, 191));
    	pTitulo_resumen.setBackground(new Color(212, 239, 223));
    	pTitulo_resumen.add(titulo_resumen);
    	pResumen_cierre.add(pTitulo_resumen);
    	scr_resumen.setPreferredSize(new Dimension(800, 220));
    	pResumen_cierre.add(scr_resumen);
    	
    	Border borde1 = BorderFactory.createTitledBorder(null, "Asistencias", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,16), new Color(36, 113, 163));
    	//pCaja_inasistencia.setBorder(borde1);
    	//pCaja_inasistencia.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
    	DropShadowBorder shadow = new DropShadowBorder();
    	
    	shadow.setShadowSize(7);
        shadow.setShadowColor(new Color(41, 128, 185));
        shadow.setShowLeftShadow(true);
        shadow.setShowRightShadow(true);
        shadow.setShowBottomShadow(true);
        shadow.setShowTopShadow(true);
        pCaja_inasistencia.setBorder(shadow);
    
    	pCaja_despacho.setBorder(shadow);
   
    	pCaja_internos.setBorder(shadow);
    	
    	pCaja_sueldos.setBorder(shadow);
    	
    	pCaja_proveedores.setBorder(shadow);
    	
    	pCaja_altasbajas.setBorder(shadow);
    	
    	pCaja_zonas.setBorder(shadow);
    	
    	pCaja_zonas.setBorder(shadow);
    	
    	//pCaja_controlBilletes.setBorder(borde8);
    	
    	pCaja_prestamos.setBorder(shadow);
    	
    	pCaja_monedas.setBorder(shadow);
    	pResumen_cierre.setBorder(shadow);
    	
    	pIntegra_scr.setLayout(new BoxLayout(pIntegra_scr, BoxLayout.Y_AXIS));
    	
    	pIntegra_scr.add(Box.createRigidArea(new Dimension(0,10)));
    	pIntegra_scr.add(pCaja_inasistencia);
    	pIntegra_scr.add(Box.createRigidArea(new Dimension(0,10)));
    	pIntegra_scr.add(pCaja_altasbajas);
    	pIntegra_scr.add(Box.createRigidArea(new Dimension(0,10)));
    	pIntegra_scr.add(pCaja_zonas);
    	pIntegra_scr.add(Box.createRigidArea(new Dimension(0,10)));
    	pIntegra_scr.add(pCaja_despacho);
    	//pIntegra_scr.add(Box.createRigidArea(new Dimension(0,10)));
    	//pIntegra_scr.add(pCaja_prestamos);
    	pIntegra_scr.add(Box.createRigidArea(new Dimension(0,10)));
    	pIntegra_scr.add(pCaja_sueldos);
    	pIntegra_scr.add(Box.createRigidArea(new Dimension(0,10)));
    	pIntegra_scr.add(pCaja_internos);
    	pIntegra_scr.add(Box.createRigidArea(new Dimension(0,10)));
    	pIntegra_scr.add(pCaja_proveedores);
    	pIntegra_scr.add(Box.createRigidArea(new Dimension(0,10)));
    	pIntegra_scr.add(pCaja_monedas);
    	pIntegra_scr.add(Box.createRigidArea(new Dimension(0,10)));
    	pIntegra_scr.add(pResumen_cierre);
    	pIntegra_scr.add(Box.createRigidArea(new Dimension(0,10)));
    	//pIntegra_scr.add(pCaja_controlBilletes);
    	//pIntegra_scr.add(Box.createRigidArea(new Dimension(0,10)));
    	
    	pIntegra_scr.setBackground(Color.WHITE);
    	pIntegra_scr.setPreferredSize(new Dimension(800,1800));
    	JScrollPane scrIntegra = new JScrollPane();
    	scrIntegra.setPreferredSize(new Dimension(800,400));
    	scrIntegra.setViewportView(pIntegra_scr);
    	
    	scrIntegra.getVerticalScrollBar().setUnitIncrement(16);
    	
    	pIntegra.add(scrIntegra, BorderLayout.CENTER);
    	
		this.add(pIntegra, BorderLayout.CENTER);
		
		//habilita_datos(false, false, false, false, false,false,false, false,false,true,false, true);
		
	}
	
	public void iniciarC_asistencia(java.sql.Date hoy){
		 
		limpiar(tabla_asistencia);
		limpiar(tabla_asistencia_impresion);
		
		int cantidad = 0;
	
		ArrayList<Caja_inasistenciaVO > ar = controladorCaja.buscarCaja_inasistencia(hoy);
		
		if(ar!=null){
			
			for(Caja_inasistenciaVO ciVO: ar){
			
					Object d [] = new Object [4];
				
					d[0]=ciVO.getId_empleado();
					
					EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.toString(ciVO.getId_empleado()));
					
					if(eVO!=null){
						
						d[1]=eVO.getPuesto();
						d[2]=eVO.getNombre() + " " + eVO.getApellido();
						
					}
					
					Motivo_generalVO mVO = controladorMotivoCaja.buscarMotivo_general(ciVO.getId_motivo());
					
					if(mVO!=null)
					
						d[3]= mVO.getCategoria();
							
					t_model_asistencia.addRow(d);
					cantidad += 1;

					Object d_impresion [] = new Object [4];
					d_impresion[0] = d[0];
					d_impresion[1] = d[1];
					d_impresion[2] = d[2];
					d_impresion[3] = d[3];
					
					t_model_asistencia_impresion.addRow(d_impresion);
			}
					
		}
		else{
			
			Object d_impresion [] = new Object [4];
			
			d_impresion[0] = "-";
			d_impresion[1] = "-";
			d_impresion[2] = "-";
			d_impresion[3] = "-";
			t_model_asistencia_impresion.addRow(d_impresion);
				System.out.println();		
		}
		tabla_asistencia.setModel(t_model_asistencia);
		tabla_asistencia_impresion.setModel(t_model_asistencia_impresion);
		//alto_filas();
		System.out.println(tabla_asistencia.getRowCount() + "////////////////////////////////////////////"); 
		tabla_asistencia.getPreferredScrollableViewportSize().setSize(750,
				tabla_asistencia.getRowHeight() * cantidad);
		 centrar_columnas(tabla_asistencia);
		//resizeColumns();
		/*pCaja_inasistencia.add(scr_asistencia, BorderLayout.CENTER);
		scr_asistencia.setViewportView(tabla_asistencia);*/
		pCaja_inasistencia.updateUI();
		 //scr_asistencia.setViewportView(tabla_asistencia);
		pIntegra.updateUI();
		
	}
	
	public void iniciarC_AltasBajas(java.sql.Date hoy){
		 
		limpiar(tabla_altasbajas);
		
		int cantidad = 0;
	
		ArrayList<PedidosVO > ar = controladorCaja.buscarAltasBajasHoy(hoy);
		
		int cont_altas = 0;
		int cont_bajas = 0;
		int diferencia = 0;
		
		if(ar!=null){
			
			for(PedidosVO pVO: ar){
				
					Object o [] = new Object [4];
				
					o[0]=pVO.getN_pedido();
					
					String detalle = "";
					String cargo = "";
					
					ArrayList<Pedido_articuloVO> ar_pedido = _controladorPedido.
							buscarArticulos_porPedido(pVO.getN_pedido(), true);
					
					//if(apVO!=null) lDescripcion.setText("$" + Double.toString(apVO.getMonto()));
					
					if(ar_pedido!=null){
						
						for(Pedido_articuloVO paVO : ar_pedido){
							
							ArticulosVO aVO = _controladorArticulo.
									buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
							
							ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
							
							if(apVO!=null){
								
								detalle = "Prestamo(" + apVO.getCodigo() + 
										")$" + Double.toString(apVO.getMonto());
							}
							/*else if(paVO.getCantidad()>1)
								detalle = detalle + " " + aVO.getNombre() +
								"(" + aVO.getCodigo() + ")" + "x" + paVO.getCantidad();*/
							else
								detalle = detalle + " " + aVO.getNombre()+
										"(" + aVO.getCodigo() + ")";
						}
						

					}
	
					o[1] = detalle;
					
					ClienteVO cVO = _controladorCliente.buscarCliente_porNPedido(pVO.getN_pedido());
					
					if(cVO!=null){
						
							o[2] = cVO.getNombre() + " " + cVO.getApellido();
						
					}
				
					if(pVO.getFecha_termino()!= null){
						
					/*	LocalDate localDate = pVO.getFecha_termino().toLocalDate();
						LocalDate today = hoy.toLocalDate(); // Pass a time zone to get current date in UTC for fair comparison.
						boolean localDateIsToday = localDate.isEqual( today );
						
						if(localDate.isEqual( today )){	*/
							cont_bajas++;
							o[3]= "finalizado";
						//}	
					}
					else/* if(pVO.getFecha_inicio()!= null)*/{
				
						/*LocalDate localDate = pVO.getFecha_inicio().toLocalDate();
						LocalDate today = hoy.toLocalDate(); // Pass a time zone to get current date in UTC for fair comparison.
						boolean localDateIsToday = localDate.isEqual( today );
				
						if(localDate.isEqual( today ))	*/
							cont_altas++;
						o[3]= "alta";
					}	
						
					t_model_altasbajas.addRow(o);
					cantidad += 1;
					
					
			}
					
		}
		
		diferencia = cont_altas - cont_bajas;

		this.resultado_altasbajas.setText("Altas: " + cont_altas + "     Bajas: " + cont_bajas+ 
				"     Diferencia: ");
		this.diferencia_altasbajas.setText(Integer.toString(diferencia));
		
		if(diferencia > 0 ) diferencia_altasbajas.setForeground(new Color(0,153,0));
		if(diferencia < 0 ) diferencia_altasbajas.setForeground(new Color(204,0,0));
		
		tabla_altasbajas.setModel(t_model_altasbajas);

		tabla_altasbajas.getPreferredScrollableViewportSize().setSize(750,
				tabla_altasbajas.getRowHeight() * cantidad);
		 centrar_columnas(tabla_altasbajas);
		//resizeColumns();
		/*pCaja_inasistencia.add(scr_asistencia, BorderLayout.CENTER);
		scr_asistencia.setViewportView(tabla_asistencia);*/
		pCaja_altasbajas.updateUI();
		 //scr_asistencia.setViewportView(tabla_asistencia);
		pIntegra.updateUI();
		
	}
	
	/**
	 * <p>Inicia la recaudación del dia de las zonas</p>
	 * 
	 * @author david
	 * @since 23/06/2018
	 * @param java.sql.Date hoy
	 */
	
	public void iniciarC_zonas(java.sql.Date hoy){
		 
		limpiar(tabla_zonas);
		limpiar(tabla_zonas_impresion);
		
		int cantidad = 0;
	
		ArrayList<Caja_zonasVO > ar = controladorCaja.buscarCaja_zona(hoy);
		
		if(ar!=null){
			
			for(Caja_zonasVO _caja_zonaVO: ar){
			
					Object d [] = new Object [24];
					d[0] =_caja_zonaVO.getId_zona();
					
					/*Motivo_generalVO mVO = controladorMotivoCaja.
							buscarMotivo_general(_caja_zonaVO.getId_motivo());
					
					if(mVO!=null)
					
						d[1]= mVO.getCategoria();*/
					
					d[1] = _caja_zonaVO.getDetalle();
					
					//d[1] = _caja_zonaVO.getId_motivo();
					d[2] =  _caja_zonaVO.getIngresos();
					d[3] =  _caja_zonaVO.getFaltante();
					d[4] =  _caja_zonaVO.getSobrante();
					d[5]= _caja_zonaVO.getMonto_ideal();
					d[6]= _caja_zonaVO.getEfectividad();
					d[7] = _caja_zonaVO.getObservaciones();
					d[8] =  _caja_zonaVO.get_1000();
					d[9] =  _caja_zonaVO.get_500();
					d[10] =  _caja_zonaVO.get_200();
					d[11] =  _caja_zonaVO.get_100();
					d[12] = _caja_zonaVO.get_50();
					d[13] =  _caja_zonaVO.get_20();
					d[14] =  _caja_zonaVO.get_10();
					d[15] =  _caja_zonaVO.get_5();
					d[16] =  _caja_zonaVO.get_2();
					d[17] =  _caja_zonaVO.get_1();
					d[18] =  _caja_zonaVO.get_0_50();
					d[19] =  _caja_zonaVO.get_0_25();
					
					UsuariosVO uVO = controladorUsuario.buscarUsuario_porID(_caja_zonaVO.getId_usuario());
					
					if(uVO!=null)
					
						d[20] =  uVO.getNombre();
					
					SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
					String fec_reg = ft.format(_caja_zonaVO.getFecha_registro());
					d[21] =  fec_reg;
					d[22] =  _caja_zonaVO.getHora_registro();
										
					t_model_zonas.addRow(d);
					cantidad += 1;
									
					Object d_impresion []= new Object[7];
					d_impresion[0] =_caja_zonaVO.getId_zona();
					d_impresion[1] =  _caja_zonaVO.getIngresos();
					d_impresion[2] =  _caja_zonaVO.getFaltante();
					d_impresion[3] =  _caja_zonaVO.getSobrante();
					d_impresion[4]= _caja_zonaVO.getMonto_ideal();
					d_impresion[5]= _caja_zonaVO.getEfectividad();
					d_impresion[6] = _caja_zonaVO.getObservaciones();
					
					t_model_zonas_impresion.addRow(d_impresion);
			}
					
		}
		
		tabla_zonas.setModel(t_model_zonas);
		tabla_zonas_impresion.setModel(t_model_zonas_impresion);
		//alto_filas();
		System.out.println(tabla_zonas.getRowCount() + "////////////////////////////////////////////"); 
		tabla_zonas.getPreferredScrollableViewportSize().setSize(1000,
				tabla_zonas.getRowHeight() * cantidad);
		
		 centrar_columnas(tabla_zonas);
		
		pCaja_zonas.updateUI();
		
		pIntegra.updateUI();
		
	}
	public void iniciarResumen(java.sql.Date hoy){
		
		limpiar(tabla_resumen);
		limpiar(tabla_resumen_impresion);
		
		int cantidad = 0;
		
		CajaDiariaTotalVO cVO = controladorCaja.buscarCajaDiariaTotal_porFecha(hoy);
		
		if(cVO!=null){
				
				actualizar.setEnabled(false);
			
				Object d [] = new Object [20];
				
				SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
				String fec_reg = ft.format(cVO.getFecha_registro());
				d[0] =  fec_reg;
			
				d[1] =  cVO.getIngresos();
				d[2]= cVO.getEgresos();
				d[3] =  cVO.getSobrante();
				d[4] =  cVO.getFaltante();
				d[5] = cVO.getObservaciones();
				d[6] =  cVO.get_1000();
				d[7] =  cVO.get_500();
				d[8] =  cVO.get_200();
				d[9] =  cVO.get_100();
				d[10] = cVO.get_50();
				d[11] =  cVO.get_20();
				d[12] =  cVO.get_10();
				d[13] =  cVO.get_5();
				d[14] =  cVO.get_2();
				d[15] =  cVO.get_1();
				d[16] =  cVO.get_0_50();
				d[17] =  cVO.get_0_25();
				
				UsuariosVO uVO = controladorUsuario.buscarUsuario_porID(cVO.getId_usuario());
				
				if(uVO!=null)
					
					d[18] =  uVO.getNombre();
				
				d[19] =  cVO.getHora_registro();
				
				
				t_model_resumen.addRow(d);
				cantidad += 1;

				System.out.println("Ingresos " + ingresos + " egresos " + egresos);
				
				Object d_impresion [] = new Object [6];
				d_impresion[0] =  cVO.getIngresos();//ingresos;
				d_impresion[1]= cVO.getEgresos();//egresos;
				d_impresion[2]= cVO.getIngresos() - cVO.getEgresos();//saldo_dia;
				d_impresion[3] =  cVO.getSobrante();
				d_impresion[4] =  cVO.getFaltante();
				d_impresion[5] = cVO.getObservaciones();
				
				t_model_resumen_impresion.addRow(d_impresion);
			
		}
		else actualizar.setEnabled(true);
		
		
		pResumen_cierre.updateUI();
		
		pIntegra.updateUI();
		
	}
	
	public void iniciarC_despacho(java.sql.Date hoy){
		 
		limpiar(tabla_despacho);
		
		int cantidad = 0;
	
		ArrayList<Despacho_diarioVO > ar = controladorCaja.buscarCaja_despacho(hoy);
		
		if(ar!=null){
			
			for(Despacho_diarioVO dpVO : ar){
				
				SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
				String date1 =  new String(sf.format(dpVO.getFecha_registro()));
				
				Object [] o = new Object[10];
				
				o[0] = dpVO.getN_pedido();
				
				ClienteVO clienteVO = _controladorCliente.buscarCliente_porNPedido(dpVO.getN_pedido());
				
				if(clienteVO!=null){
					
			
						o[1] = clienteVO.getNombre() + " " + clienteVO.getApellido();
					
		
				}
				
				o[2] = dpVO.getEntrega();
				o[3] = dpVO.getNombre();
				o[4] = dpVO.getEstado();
				o[5] = dpVO.getMonto();
				o[6] = dpVO.getObservaciones();
				
				short id = (short) dpVO.getId_usuario();
				
				UsuariosVO uVO = controladorUsuario.buscarUsuario_porID(id);
				
				if(uVO!=null)
				
					o[7] = uVO.getNombre();
				
				
				o[8] = date1;
				o[9] = dpVO.getHora_registro();
				
				t_model_despacho.addRow(o);
				cantidad += 1;
			}
		}
		
		tabla_despacho.setModel(t_model_despacho);
		//alto_filas();
		tabla_despacho.getPreferredScrollableViewportSize().setSize(750,
				tabla_despacho.getRowHeight() * cantidad);
		
		 centrar_columnas(tabla_despacho);
		
		pCaja_despacho.updateUI();
		
		pIntegra.updateUI();
		
	}
	
	public void iniciarC_prestamos(java.sql.Date hoy){
		 
		limpiar(tabla_prestamos);
		
		int cantidad = 0;
	
		ArrayList<Despacho_diarioVO > ar = controladorCaja.buscarCaja_despacho(hoy);
		
		if(ar!=null){
			
			for(Despacho_diarioVO dpVO : ar){
				
				SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
				String date1 =  new String(sf.format(dpVO.getFecha_registro()));
				
				Object [] o = new Object[10];
				
				o[0] = dpVO.getN_pedido();
				
				ArrayList<Object []> ar_cliente = _controladorCliente.buscarClientes_porPedido(dpVO.getN_pedido());
				
				if(ar_cliente!=null){
					
					for(Object [] ob: ar_cliente){
						
						ClienteVO cVO = (ClienteVO)ob[0];
						o[1] = cVO.getNombre() + " " + cVO.getApellido();
					}
		
				}
				
				o[2] = dpVO.getEntrega();
				o[3] = dpVO.getNombre();
				o[4] = dpVO.getEstado();
				
				o[6] = dpVO.getObservaciones();
				
				short id = (short) dpVO.getId_usuario();
				
				UsuariosVO uVO = controladorUsuario.buscarUsuario_porID(id);
				
				if(uVO!=null)
				
					o[7] = uVO.getNombre();
				
				o[8] = date1;
				o[9] = dpVO.getHora_registro();
				
				PedidosVO pVO = _controladorPedido.buscarPedido_porNpedido(dpVO.getN_pedido());
				
				String descripcion = "";
				
				if(pVO!=null){
					
					ArrayList<Pedido_articuloVO> ar_pedido = _controladorPedido.
							buscarArticulos_porPedido(pVO.getN_pedido(), true);
					
					//if(apVO!=null) lDescripcion.setText("$" + Double.toString(apVO.getMonto()));
					
					if(ar_pedido!=null){
						
						for(Pedido_articuloVO paVO : ar_pedido){
							
							ArticulosVO aVO = _controladorArticulo.
									buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
							
							ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
							
							if(apVO!=null && dpVO.getEstado().equals("entregado")){
								
					
								o[5] = apVO.getMonto();
								
								t_model_prestamos.addRow(o);
								cantidad += 1;
							}
							
						}
						
					
					}
					
					
				}	
				
			}
		}
		
		tabla_prestamos.setModel(t_model_prestamos);
		//alto_filas();
		tabla_prestamos.getPreferredScrollableViewportSize().setSize(750,
				tabla_prestamos.getRowHeight() * cantidad);
		
		 centrar_columnas(tabla_prestamos);
		
		pCaja_prestamos.updateUI();
		
		pIntegra.updateUI();
		
	}
	
	public void iniciarC_sueldos(java.sql.Date hoy){
		 
		limpiar(tabla_sueldos);
		
		int cantidad = 0;
		
		ArrayList<SueldosVO > ar = controladorCaja.buscarCaja_sueldos(hoy);
		
		if(ar!=null){
			
			for(SueldosVO sVO : ar){
				
				SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
				String date1 =  new String(sf.format(sVO.getFecha_registro()));
				
				Object [] o = new Object[7];
				
				o[0] = sVO.getCodig_sueldo();	
				
				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(
						Short.toString(sVO.getId_empleado()));
				
				if(eVO!=null){
					
					o[1] = eVO.getNombre() + " " + eVO.getApellido();
	
					o[2] = sVO.getMonto();
					o[3] = sVO.getConcepto();
				
					short id = (short) sVO.getId_usuario();
				
					UsuariosVO uVO = controladorUsuario.buscarUsuario_porID(id);
				
					if(uVO!=null)
				
						o[4] = uVO.getNombre();
					
					o[5] = date1;
					o[6] = sVO.getHora_registro();
				
					t_model_sueldos.addRow(o);
					cantidad += 1;
				}
			}
		}
		
		tabla_sueldos.setModel(t_model_sueldos);
		//alto_filas();
		tabla_sueldos.getPreferredScrollableViewportSize().setSize(750,
				tabla_sueldos.getRowHeight() * cantidad);
		
		 centrar_columnas(tabla_sueldos);
		
		pCaja_sueldos.updateUI();
		
		pIntegra.updateUI();
		
	}
	
	public void iniciarC_interna(java.sql.Date hoy){
		 
		limpiar(tabla_interno);
		
		int cantidad = 0;
	
		ArrayList<Caja_internaVO > ar = controladorCaja.buscarCaja_interna(hoy);
		
		if(ar!=null){
			
			for(Caja_internaVO ciVO: ar){
			
					SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
					String date1 =  new String(sf.format(ciVO.getFecha_registro()));
				
					Object d [] = new Object [8];
				
					d[0] = ciVO.getNumero();
					
					if(controladorMotivoCaja.buscarMotivo_internaUsuario(Short.toString(ciVO.getId_motivo()))!=null){
						
						Motivo_caja_internaVO mVO = controladorMotivoCaja.buscarMotivo_internaUsuario(
								Short.toString(ciVO.getId_motivo()));
						
						d[1]= mVO.getDescripcion();
					}
					
					//if(controladorDetalle_in.buscarDetalle_internoUsuario(Short.toString(
							//ciVO.getId_detalle()))!=null){
						
						//Detalle_motivo_internoVO dVO= controladorDetalle_in.buscarDetalle_internoUsuario(Short.toString(
								//ciVO.getId_detalle()));
						//d[2]= dVO.getDescripcion();
					d[2]= ciVO.getDetalle();
					//}	
					
					d[3]=ciVO.getMonto_ingreso();
					d[4]=ciVO.getMonto_egreso();
					
					short id = (short) ciVO.getId_usuario();
					
					UsuariosVO uVO = controladorUsuario.buscarUsuario_porID(id);
					
					if(uVO!=null)
					
						d[5] = uVO.getNombre();
						
					d[6] = date1;
					d[7] = ciVO.getHora_registro();
					
							
					t_model_interno.addRow(d);
					cantidad += 1;
									
			}
					
		}
		
		tabla_interno.setModel(t_model_interno);
		//alto_filas();
		System.out.println(tabla_asistencia.getRowCount() + "////////////////////////////////////////////"); 
		tabla_interno.getPreferredScrollableViewportSize().setSize(750,
				tabla_interno.getRowHeight() * cantidad);
		 centrar_columnas(tabla_interno);
		//resizeColumns();
		/*pCaja_inasistencia.add(scr_asistencia, BorderLayout.CENTER);
		scr_asistencia.setViewportView(tabla_asistencia);*/
		pCaja_internos.updateUI();
		 //scr_asistencia.setViewportView(tabla_asistencia);
		pIntegra.updateUI();
		
	}
	
	public void iniciarC_proveedores(java.sql.Date hoy){
		 
		limpiar(tabla_proveedores);
		
		int cantidad = 0;
	
		ArrayList<Caja_proveedoresVO > ar = controladorCaja.buscarCaja_proveedores(hoy);
		
		if(ar!=null){
			
			for(Caja_proveedoresVO cpVO: ar){
			
					SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
					String date1 =  new String(sf.format(cpVO.getFecha_registro()));
				
					Object d [] = new Object [7];
				
					d[0] = cpVO.getNumero();
					
					if(controladorProveedores.buscarProveedorUsuario(Short.toString(cpVO.getId_proveedor()))!=null){
						
						ProveedoresVO pVO = controladorProveedores.buscarProveedorUsuario(
								Short.toString(cpVO.getId_proveedor()));
						
						d[1]= pVO.getNombre();
					}
			
					d[2]= cpVO.getDetalle();
				
					
					d[3]=cpVO.getMonto();
					
					short id = (short) cpVO.getId_usuario();
					
					UsuariosVO uVO = controladorUsuario.buscarUsuario_porID(id);
					
					if(uVO!=null)
					
						d[4] = uVO.getNombre();
						
					d[5] = date1;
					d[6] = cpVO.getHora_registro();
					
							
					t_model_proveedores.addRow(d);
					cantidad += 1;
									
			}
					
		}
		
		tabla_proveedores.setModel(t_model_proveedores);
		//alto_filas();
		System.out.println(tabla_asistencia.getRowCount() + "////////////////////////////////////////////"); 
		tabla_proveedores.getPreferredScrollableViewportSize().setSize(750,
				tabla_proveedores.getRowHeight() * cantidad);
		 centrar_columnas(tabla_proveedores);
		//resizeColumns();
		/*pCaja_inasistencia.add(scr_asistencia, BorderLayout.CENTER);
		scr_asistencia.setViewportView(tabla_asistencia);*/
		pCaja_proveedores.updateUI();
		 //scr_asistencia.setViewportView(tabla_asistencia);
		pIntegra.updateUI();
		
	}
	
	public void iniciarC_monedas(java.sql.Date hoy){
		 
		limpiar(tabla_monedas);
		
		int cantidad = 0;
		double acu_ingreso_dia = 0;
		double acu_egreso_dia = 0;
		
		Object d [] = new Object [4];
		
		//ArrayList<Monedas_ingresosVO > ar_in = controladorCaja.buscarCaja_monedas_ingreso(hoy);
		
		CajaDiariaTotalVO cdtVO = controladorCaja.buscarCajaDiariaTotal_porFecha(hoy);
		
		if(cdtVO!=null){
			
				SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
				String date1 =  new String(sf.format(cdtVO.getFecha_registro()));
				
				d[0] = date1;	
				
				//acu_ingreso_dia += mVO.getMonto_ingreso();	
				acu_ingreso_dia = (cdtVO.get_2()*2) + cdtVO.get_1() + (cdtVO.get_0_50() * 0.5) + 
						(cdtVO.get_0_25()*0.25);
							
		}
		System.out.println("Ingreso monedas cierre " + acu_ingreso_dia);
		d[1] = acu_ingreso_dia;
		
		ArrayList<Monedas_egresosVO > ar_e = controladorCaja.buscarCaja_monedas_egreso(hoy);
		
		if(ar_e!=null){
			
			for(Monedas_egresosVO mVO: ar_e){
				
				acu_egreso_dia += mVO.getMonto_egreso();	
									
			}
					
		}
		
		d[2] = acu_egreso_dia;
		
		double acumulado_in_total = controladorCaja.buscarCaja_monedasAcumulado_ingreso(hoy);
		double acumulado_eg_total = controladorCaja.buscarCaja_monedasAcumulado_egreso(hoy);
		
		d[3] = acumulado_in_total - acumulado_eg_total;
		
		t_model_monedas.addRow(d);
		cantidad += 1;
		
		tabla_monedas.setModel(t_model_monedas);
		//alto_filas();
		System.out.println(tabla_asistencia.getRowCount() + "////////////////////////////////////////////"); 
		tabla_monedas.getPreferredScrollableViewportSize().setSize(750,
				tabla_monedas.getRowHeight() * cantidad);
		 centrar_columnas(tabla_monedas);
		//resizeColumns();
		/*pCaja_inasistencia.add(scr_asistencia, BorderLayout.CENTER);
		scr_asistencia.setViewportView(tabla_asistencia);*/
		pCaja_monedas.updateUI();
		 //scr_asistencia.setViewportView(tabla_asistencia);
		pIntegra.updateUI();
		
	}
	
	public void iniciarC_saldo(java.sql.Date fecha){
		
		
		if(controladorCaja.comprobar_duplicados(fecha)){
			
			this.setAllPanelEnabled(false);
			
		}
		else
			this.setAllPanelEnabled(true);
		
		ingresos = controladorCaja.ingresosCajaDiariaTotal(fecha);
		egresos = controladorCaja.egresosCajaDiariaTotal(fecha);
		
		ingresosL.setText("Ingresos: $" + Double.toString(ingresos));
		egresosL.setText("Egresos: $" + Double.toString(egresos));
		
		saldo_dia = ingresos - egresos;
		saldo_diaL.setText("Saldo: $");
		saldo_dia_doubleL.setText(Double.toString(saldo_dia));
		
		if(saldo_dia > 0) saldo_dia_doubleL.setForeground(new Color(39, 174, 96));
		if(saldo_dia < 0) saldo_dia_doubleL.setForeground(new Color(231, 76, 60));
		
		System.out.println("ingresos dia" + ingresos);
	}
	
	public void iniciar_busqueda(ArrayList<Object []> ar){
		 
		//limpiar();
		
		int cantidad = 0;
	
		if(ar!=null){
	
		}
		pIntegra.updateUI();
		
		//scr.setViewportView(pImpresion);
		
		
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
	    /*int tW = 750;
	    TableColumn column;
	    TableColumnModel jTableColumnModel = tabla.getColumnModel();
	    int cantCols = jTableColumnModel.getColumnCount();
	    for (int i = 0; i < cantCols; i++) {
	        column = jTableColumnModel.getColumn(i);
	        int pWidth = Math.round(columnWidthPercentage[i] * tW);
	        column.setPreferredWidth(pWidth);
	    }*/
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
		
		public void setControladorMonedas(ControladorMonedas _controladorMonedas){
			
			this._controladorMonedas = _controladorMonedas;
		}
		
		public void setVistaCombo(VistaNewObjetoVenta vista_combo){
			
			this.vista_combo = vista_combo;
		}
		
		public void setVistaPrestamo(VistaPrestamo vista_prestamo){
			
			this.vista_prestamo = vista_prestamo;
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
		
		public void setControladorCobrador(ControladorCobrador _controladorCobrador){
			
			this.controladorCobrador = _controladorCobrador;
		}
		
		public void setControladorAdministrativo(ControladorAdministrativo _controladorAdministrativo){
	
			this.controladorAdministrativo = _controladorAdministrativo;
		}
		public void setControladorJefeCalle(ControladorJefeCalle _controladorJefe_calle){
	
			this.controladorJefe_calle = _controladorJefe_calle;
		}
		
		public void setControladorCaja(ControladorCaja controladorCaja){
			
			this.controladorCaja = controladorCaja;
		}
		
		public void setControladorMotivoCaja(ControladorMotivoCaja controladorMotivoCaja){
			
			this.controladorMotivoCaja = controladorMotivoCaja;
		}
		
		public void setControladorUsuario(ControladorUsuario controladorUsuario){
			
			this.controladorUsuario = controladorUsuario;
		}
		
		public void setControladorSueldos(ControladorSueldos controladorSueldos){
			
			this.controladorSueldos = controladorSueldos;
		}
		
		public void setControladorEmpleado(ControladorEmpleado controladorEmpleado){
			
			this.controladorEmpleado = controladorEmpleado;
		}
		public void setControladorDespacho_diario(ControladorDespacho_diario controlador_dd){
			
			this._controlador_dd = controlador_dd;
		}
		
		public void setControladorDetalle_interno(ControladorDetalle_interno controladorDetalle_in){
			
			this.controladorDetalle_in = controladorDetalle_in;
		}
		
		public void setControladorProveedores(ControladorProveedores controladorProveedores){
			
			this.controladorProveedores = controladorProveedores;
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
		
		
		public Pago_diarioVO getPago_diarioVO(){
			
			return _pgVO;
		}
		
		public JLabel getEmpleadoL(){
			
			return empleadoL;
		}
		
		public JTextField getEmpleadoTF(){
			
			return this.buscar_empleadoTF;
		}
		
		public JLabel getEmpleado_sueldoL(){
			
			return empleado_sueldoL;
		}
		
		public JTextField getEmpleado_sueldoTF(){
			
			return this.buscar_empleado_sueldoTF;
		}
		
		public JLabel getMotivoL(){
			
			return motivoL;
		}
		
		public JTextField getMotivoTF(){
			
			return this.buscar_motivoTF;
		}
		
		public JLabel getMotivo_inL(){
			
			return motivo_inL;
		}
		
		public JTextField getMotivo_inTF(){
			
			return this.buscar_motivo_inTF;
		}
		
		public JLabel getDetalleL(){
			
			return detalleL;
		}
		
		public JTextField getDetalleTF(){
			
			return this.buscar_detalleTF;
		}
		
		public JLabel getProveedorL(){
			
			return this.proveedorL;
		}
		
		public JTextField getProveedorTF(){
			
			return this.buscar_proveedorTF;
		}
		
		public double getEgresosCajaDiaria(){
			
			return egresos;
		}
		
		public double getIngresosCajaDiaria(){
			
			return ingresos;
		}
		
		public double getSaldo_dia(){
			
			return saldo_dia;
		}
		
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		this._busqueda_entities.setPanel("vista_caja");
		
		if(e.getSource()==this.buscar_empleadoB){
			
			
			controladorEmpleado.buscarEmpleadosAll();
			_busqueda_entities.setTipoBusqueda(12);
			controladorEmpleado.mostrarBusquedaEntities("Buscar empleado");
			
		}
		
		if(e.getSource()==buscar_empleado_sueldoB){
			
			controladorEmpleado.buscarEmpleadosAll();
			controladorEmpleado.mostrarBusquedaEntities("Buscar empleado");
		}
		
		if(e.getSource()==buscar_motivoB){
			
			controladorMotivoCaja.buscarMotivo_inasistenciaAll();
			controladorMotivoCaja.mostrarBusquedaEntities("Buscar motivo");
		}
		
		if(e.getSource()==buscar_motivo_inB){
			
			controladorMotivoCaja.buscarMotivo_internoAll();
			controladorMotivoCaja.mostrarBusquedaEntities("Buscar motivo");
		}
		
		if(e.getSource()==buscar_detalleB){
			
			if(controladorDetalle_in.buscarDetalle_internoAll(buscar_motivo_inTF.getText()))
					controladorDetalle_in.mostrarBusquedaEntities("Buscar detalle");
		}
		
		if(e.getSource()==buscar_proveedorB){
			
			controladorProveedores.buscarProveedorAll();
			controladorProveedores.mostrarBusquedaEntities("Buscar proveedor");
		}
		
		if(e.getSource()==buscar_detalle_provB){
			
			controladorDetalle_prov.buscarDetalle_proveedorAll();
			controladorDetalle_prov.mostrarBusquedaEntities("Buscar detalle");
		}
		
		if(e.getSource()==agregar_inasistencia){
			
			if(controladorCaja.ingresoUsuario(ar)){
				
				if(Integer.parseInt(buscar_empleadoTF.getText())!=33){
					
					Caja_inasistenciaVO cVO = new Caja_inasistenciaVO();
					
					cVO.setId_empleado(Short.parseShort(buscar_empleadoTF.getText()));
					
					cVO.setId_motivo(Short.parseShort(buscar_motivoTF.getText()));
					
					Date d = new Date();
					
					java.sql.Date hoy = new java.sql.Date(d.getTime());
					java.sql.Time hora = new java.sql.Time(d.getTime());
					
					cVO.setId_usuario(Principal._usuario.getId_usuario());
					
					cVO.setFecha_registro(hoy);
					cVO.setHora_registro(hora);
					
					int res = controladorCaja.ingresarInasistencia(cVO);
					
					if(res > 0){
						
						Mensajes.getMensaje_altaExitosoGenerico();
						limpiar(tabla_asistencia);
						iniciarC_asistencia(hoy);
					}
					else Mensajes.getMensaje_altaErrorGenerico();
					
				}
				else Mensajes.getMensaje_altaErrorGenerico();
				
				
			}
			else Mensajes.getMensaje_altaErrorGenerico();
		}
		
		if(e.getSource()==agregar_sueldo){
			
			if(controladorCaja.ingresoUsuario_sueldo(buscar_empleado_sueldoTF, montoTF)){
				
				SueldosVO sVO = new SueldosVO();
				
				sVO.setId_empleado(Short.parseShort(buscar_empleado_sueldoTF.getText()));
				sVO.setMonto(Double.parseDouble(montoTF.getText()));
				
				if(salarioOP.isSelected()) sVO.setConcepto("Salario");
				if(valeOP.isSelected()) sVO.setConcepto("Vale");
				if(comisionOP.isSelected()) sVO.setConcepto("Comisión");
				if(premio_efecOP.isSelected()) sVO.setConcepto("Premio");
				if(feriadoOP.isSelected()) sVO.setConcepto("Feriado");
					
				Date d = new Date();
				
				java.sql.Date hoy = new java.sql.Date(d.getTime());
				java.sql.Time hora = new java.sql.Time(d.getTime());
				
				sVO.setId_usuario(Principal._usuario.getId_usuario());
				
				sVO.setFecha_registro(hoy);
				sVO.setHora_registro(hora);
				
				int res = controladorCaja.ingresarSueldos(sVO);
				
				if(res > 0){
					
					Mensajes.getMensaje_altaExitosoGenerico();
					limpiar(tabla_sueldos);
					iniciarC_sueldos(hoy);
					iniciarC_saldo(hoy);
				}
				else Mensajes.getMensaje_altaErrorGenerico();
			}
			else Mensajes.getMensaje_altaErrorGenerico();
		}
		
		if(e.getSource()==agregar_interno){
			
			if(controladorCaja.ingresoUsuario_interno(buscar_motivo_inTF, buscar_detalleTF, monto_internoTF)){
				
				Caja_internaVO cVO = new Caja_internaVO();
				
				cVO.setId_motivo(Short.parseShort(this.buscar_motivo_inTF.getText()));
				cVO.setDetalle(buscar_detalleTF.getText());
				
				Date d = new Date();
				
				java.sql.Date hoy = new java.sql.Date(d.getTime());
				java.sql.Time hora = new java.sql.Time(d.getTime());
				
				if(controladorMotivoCaja.
						buscarMotivo_internaUsuario(buscar_motivo_inTF.getText()).getIngreso()){
					
					cVO.setMonto_ingreso(Double.parseDouble(monto_internoTF.getText()));
					cVO.setMonto_egreso(0);
					
					Monedas_egresosVO mVO = new Monedas_egresosVO();
					
					mVO.setFecha_registro(hoy);
					mVO.setMonto_egreso(cVO.getMonto_ingreso());
					
					if(cVO.getId_motivo() == 2){
						
						int res = controladorCaja.insertEgreso_monedas(mVO);
						
						if(res>0) System.out.println("insert egreso moneda");
						else System.out.println("no egreso monedas");
					}
				}		
				else{
					
					cVO.setMonto_egreso(Double.parseDouble(monto_internoTF.getText()));
					cVO.setMonto_ingreso(0);
						
				}
			
				cVO.setId_usuario(Principal._usuario.getId_usuario());
				
				cVO.setFecha_registro(hoy);
				cVO.setHora_registro(hora);
				
				int res = controladorCaja.ingresarCaja_interna(cVO);
				
				if(res > 0){
					
					Mensajes.getMensaje_altaExitosoGenerico();
					limpiar(this.tabla_interno);
					
					iniciarC_interna(hoy);
					iniciarC_saldo(hoy);
					
					if(cVO.getId_motivo()==2){
						
						limpiar(this.tabla_monedas);
						iniciarC_monedas(hoy);
					}
						
				}
				else Mensajes.getMensaje_altaErrorGenerico();
					
			}
			else Mensajes.getMensaje_altaErrorGenerico();
		}
		
		if(e.getSource()==agregar_pago_proveedor){
			
			if(controladorCaja.ingresoUsuario_proveedor(buscar_proveedorTF, this.detalle_proveedoresTF,
					monto_proveedorTF)){
				
				Caja_proveedoresVO cVO = new Caja_proveedoresVO();
				
				cVO.setId_proveedor(Short.parseShort(this.buscar_proveedorTF.getText()));
				cVO.setDetalle(this.detalle_proveedoresTF.getText());
					
				cVO.setMonto(Double.parseDouble(monto_proveedorTF.getText()));	
				
				Date d = new Date();
				
				java.sql.Date hoy = new java.sql.Date(d.getTime());
				java.sql.Time hora = new java.sql.Time(d.getTime());
				
				cVO.setId_usuario(Principal._usuario.getId_usuario());
				
				cVO.setFecha_registro(hoy);
				cVO.setHora_registro(hora);
				
				int res = controladorCaja.ingresarCaja_proveedores(cVO);
				
				if(res > 0){
					
					Mensajes.getMensaje_altaExitosoGenerico();
					limpiar(this.tabla_proveedores);
					iniciarC_proveedores(hoy);
					iniciarC_saldo(hoy);
				}
				else Mensajes.getMensaje_altaErrorGenerico();
					
			}
			else Mensajes.getMensaje_altaErrorGenerico();
		}
		
		if(e.getSource()==eliminar_inasistencia){
			
			int [] ar = tabla_asistencia.getSelectedRows();	
			
			if(ar.length==0) Mensajes.getMensaje_sinFilasSeleccionadas();
			
			for(int i = 0; i < ar.length; i++){
						
				int opcion = Mensajes.getMensaje_confirmacion_anulacion_generico();
				
				if(opcion == JOptionPane.YES_OPTION){
					
					short id_empleado = Short.parseShort(tabla_asistencia.getModel().getValueAt(ar[i], 0).toString());
					
					String puesto = tabla_asistencia.getModel().getValueAt(ar[i], 1).toString();
					
					Date d = new Date();
					
					java.sql.Date hoy = new java.sql.Date(d.getTime());
					
					int res = controladorCaja.eliminarInasistencia(id_empleado, hoy);
					
					if(res > 0){
						
						Mensajes.getMensaje_bajaExitosa_generico();
						limpiar(tabla_asistencia);
						iniciarC_asistencia(hoy);
						this.pCaja_inasistencia.updateUI();
						this.scr_asistencia.setViewportView(tabla_asistencia);
						
					}
					else Mensajes.getMensaje_bajaError_generico();
				}
			}
		}
		
		if(e.getSource()==eliminar_pago_proveedor){
			
			int [] ar = tabla_proveedores.getSelectedRows();	
			
			if(ar.length==0) Mensajes.getMensaje_sinFilasSeleccionadas();
			
			for(int i = 0; i < ar.length; i++){
						
				int opcion = Mensajes.getMensaje_confirmacion_anulacion_generico();
				
				if(opcion == JOptionPane.YES_OPTION){
					
					short numero_pago = Short.parseShort(tabla_proveedores.getModel().getValueAt(ar[i], 0).toString());
					
					Date d = new Date();
					
					java.sql.Date hoy = new java.sql.Date(d.getTime());
					
					int res = controladorCaja.eliminarPagoProveedor(numero_pago);
					
					if(res > 0){
						
						Mensajes.getMensaje_bajaExitosa_generico();
						limpiar(tabla_proveedores);
						iniciarC_proveedores(hoy);
						iniciarC_saldo(hoy);
						this.pCaja_proveedores.updateUI();
						this.scr_proveedores.setViewportView(tabla_proveedores);
						
					}
					else Mensajes.getMensaje_bajaError_generico();
				}
			}
		}
		
		if(e.getSource()==eliminar_sueldo){
			
			int [] ar = tabla_sueldos.getSelectedRows();	
			
			if(ar.length==0) Mensajes.getMensaje_sinFilasSeleccionadas();
			
			for(int i = 0; i < ar.length; i++){
						
				int opcion = Mensajes.getMensaje_confirmacion_anulacion_generico();
				
				if(opcion == JOptionPane.YES_OPTION){
					
					int numero_pago = Short.parseShort(tabla_sueldos.getModel().getValueAt(ar[i], 0).toString());
					
					Date d = new Date();
					
					java.sql.Date hoy = new java.sql.Date(d.getTime());
					
					int res = controladorCaja.eliminarPagoSueldo(numero_pago);
					
					if(res > 0){
						
						Mensajes.getMensaje_bajaExitosa_generico();
						limpiar(tabla_sueldos);
						iniciarC_sueldos(hoy);
						iniciarC_saldo(hoy);
						this.pCaja_sueldos.updateUI();
						this.scr_sueldos.setViewportView(tabla_sueldos);
						
					}
					else Mensajes.getMensaje_bajaError_generico();
				}
			}
		}
		
		if(e.getSource()==this.eliminar_interno){
			
			int [] ar = tabla_interno.getSelectedRows();	
			
			if(ar.length==0) Mensajes.getMensaje_sinFilasSeleccionadas();
			
			for(int i = 0; i < ar.length; i++){
						
				int opcion = Mensajes.getMensaje_confirmacion_anulacion_generico();
				
				if(opcion == JOptionPane.YES_OPTION){
					
					int numero = Short.parseShort(tabla_interno.getModel().getValueAt(ar[i], 0).toString());
					
					Date d = new Date();
	
					java.sql.Date hoy = new java.sql.Date(d.getTime());
					
					if(tabla_interno.getModel().getValueAt(ar[i], 1).toString().
							equals("Cambio de monedas")){
						
						int res_m =controladorCaja.eliminarEgreso_moneda(hoy);
						
						if(res_m > 0){
							
							System.out.println("moneda_egreso eliminado");
							limpiar(tabla_monedas);
							iniciarC_monedas(hoy);
						}
					}
					
					int res = controladorCaja.eliminar_interna(numero);
					
					if(res > 0){
						
						Mensajes.getMensaje_bajaExitosa_generico();
						limpiar(tabla_interno);
						iniciarC_interna(hoy);
						iniciarC_saldo(hoy);
						this.pCaja_internos.updateUI();
						this.scr_interno.setViewportView(tabla_interno);
						
					}
					else Mensajes.getMensaje_bajaError_generico();
				}
			}
		}
		
		if(e.getSource()==actualizar){
			
			Date d = new Date();
			java.sql.Date hoy = new java.sql.Date(d.getTime());
			
			//if(controladorCaja.buscarCajaDiariaTotal_porFecha(hoy)==null){*/
				
				iniciarC_AltasBajas(hoy);
				iniciarC_zonas(hoy);
				iniciarC_despacho(hoy);
				
				iniciarC_sueldos(hoy);
				iniciarC_interna(hoy);
				iniciarC_proveedores(hoy);
				iniciarC_monedas(hoy);
				iniciarResumen(hoy);
				iniciarC_saldo(hoy);
				
			//}
				
		}
		
		if(e.getSource()==guardar){
				
			
				/*String fecha_ingresoS =fecha_ingreso.getJFormattedTextField().getText();
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
				Date dateFN = new Date();
				try {
					dateFN = format.parse(fecha_ingresoS);
				} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
				java.sql.Date fecha_ingresoDate = new java.sql.Date(dateFN.getTime());
					
				_pgVO.setFecha(fecha_ingresoDate);*/

			Date d = new Date();
			java.sql.Date hoy = new java.sql.Date(d.getTime());
			
				ArrayList<Despacho_diarioVO> ar = _controlador_dd.buscarDespachos_porFecha(hoy);
				
				boolean enviado = false;
				
				if(ar!=null){
					
					for(Despacho_diarioVO dVO : ar){
						
						if(dVO.getEstado().equals("enviado")) enviado = true;
					}
				}
			
				if(!enviado)
				
					instanciar();
				
				else Mensajes.getMensaje_despacho_sinResolucion();
				
		}
		
		
		
		if(e.getSource()==imprimir){
			
			
			try {
				 
				SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
				Date d=null;
				try {
					d = ft.parse(fecha_busqueda.getJFormattedTextField().getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date fecha = new java.sql.Date(d.getTime());
				 
               PrintPane_caja printPane = new 
               		PrintPane_caja(t_model_asistencia_impresion, t_model_altasbajas, t_model_zonas_impresion,
               				t_model_despacho,
               				t_model_sueldos,t_model_interno,t_model_proveedores,t_model_monedas,
               				t_model_resumen_impresion,fecha);

               PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
               aset.add(MediaSizeName.ISO_A4);
               aset.add(new PrinterResolution(300, 300, PrinterResolution.DPI));
               aset.add(DialogTypeSelection.NATIVE);
               
               PrinterJob pj = PrinterJob.getPrinterJob();
               pj.setPrintable(printPane);

               PageFormat pf = pj.defaultPage();
               
               pf.setOrientation(PageFormat.LANDSCAPE);

               if (pj.printDialog(aset)) {
                   try {
                       pj.print(aset);
                   } catch (PrinterException ex) {
                       ex.printStackTrace();
                   }
               }
           } catch (IOException ex) {
               ex.printStackTrace();
           }

			
			
					// looping to the next Printable...
			
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
		
		if(e.getSource()== ver_fb){
			
			if(controladorCaja.ingresoFecha_usuario(fecha_busqueda.getJFormattedTextField().getText())){
				
				limpiar_lt();
				
				SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
				Date d=null;
				try {
					d = ft.parse(fecha_busqueda.getJFormattedTextField().getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date fecha = new java.sql.Date(d.getTime());
				
				setLabelFecha(fecha);
				
				LocalDate localDate = fecha.toLocalDate();
				LocalDate today = LocalDate.now( ZoneOffset.systemDefault() ); 
				
				
				if(controladorCaja.comprobar_duplicados(fecha) || localDate.isEqual(today)){
					
					this.iniciarC_asistencia(fecha);
					this.iniciarC_AltasBajas(fecha);
					this.iniciarC_zonas(fecha);
					this.iniciarC_despacho(fecha);
					this.iniciarC_prestamos(fecha);
					this.iniciarC_sueldos(fecha);
					this.iniciarC_interna(fecha);
					this.iniciarC_proveedores(fecha);
					this.iniciarC_monedas(fecha);
					this.iniciarC_saldo(fecha);
					this.iniciarResumen(fecha);
					
				}
				else {
					
					Mensajes.getMensaje_noExisteCajaError();
					Date hoy = new Date();
					java.sql.Date hoysql = new java.sql.Date(hoy.getTime());
					modelFB.setValue(hoysql);
					modelFB.setSelected(true);
					setLabelFecha(hoysql);
				}
			}
				
		}
		
		if(e.getSource()==modificar){
			
			
			
		}
		
		if(e.getSource()==cancelar){
			
			/*consulta = true;
			 canEdit [5]= false;
			 t_model.isCellEditable(tabla.getSelectedRow(), 5);
			habilita_datos(false, false, false, false, false, false,false,true,false,false, true);*/
		}
	}
	
	
	public void instanciar(){
		
		VistaVerificacionIngresos vap = new VistaVerificacionIngresos("caja_diaria_total");
		vap.setPanelCliente("Caja diaria");
		
		 ControladorCliente _controladorCliente = new ControladorCliente();
			
			ControladorPedidos _controladorPedido = new ControladorPedidos();
			ControladorArticulo _controladorArticulo = new ControladorArticulo();
			ControladorCombo _controladorCombo = new ControladorCombo();
			ControladorPagoDiario _controladorPagoDiario = new ControladorPagoDiario();
			ControladorPrestamo _controladorPrestamo = new ControladorPrestamo();
			ControladorCajaZona _controladorCaja_zona = new ControladorCajaZona();
			ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
			LogicaEmpleado logica_empleado = new LogicaEmpleado();
			LogicaCliente _logica_cliente = new LogicaCliente();
			LogicaPedido _logica_pedido = new LogicaPedido();
			LogicaArticulo _logica_articulo = new LogicaArticulo();
			LogicaCombo _logica_combo = new LogicaCombo();
			LogicaPrestamo _logica_prestamo = new LogicaPrestamo();
			LogicaPagoDiario _logica_pagoDiario = new LogicaPagoDiario();
			LogicaCajaZona _logicaCaja_zona = new LogicaCajaZona();
			
			BusquedaEntities busqueda_entities = new BusquedaEntities();
			
			VistaPrestamo vista_prestamo = new VistaPrestamo();
			
			controladorEmpleado.setLogicaEmpleado(logica_empleado);
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
			
			
			busqueda_entities.setControladorPedido(_controladorPedido);
			busqueda_entities.setControladorCombo(_controladorCombo);
			busqueda_entities.setControladorPrestamo(_controladorPrestamo);
			busqueda_entities.setControladorEmpleado(controladorEmpleado);
			
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
		vap.setVistaCaja(this);
		vap.setControladorCajaZona(_controladorCaja_zona);
		vap.setControladorCaja(controladorCaja);
		//busqueda_entities.setVistaAltaPedido(vap);
		//bh.setVistaAltaPedido(vap);
		//bh.setVistaBuscarCliente(this);
		
		vap.setVisible(true);
		vap.getMil().requestFocusInWindow();
	}
	
	
	private void centrar_columnas(JTable tabla){
	
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla.getColumnCount(); i ++)
			
			tabla.getColumnModel().getColumn(i).setCellRenderer(cent);
		
	}	
	
	private void alto_filas(){
		
		/*for(int i = 0; i < tabla.getRowCount(); i++){
			
			tabla.setRowHeight(i, 20);
		}*/
	}
	
	private String [] getColumnas_asistencia(){
		
		String columna [] = new String []{"ID", "Cargo", "Nombre", "Motivo"};
		
		return columna;
	}
	private String [] getColumnas_altasbajas(){
		
		String columna [] = new String []{"N°pedido", "Detalle", "Cliente", "Situación"};
		
		return columna;
	}
	
	private String [] getColumnas_zonas(){
		
		String columna [] = new String []{"Zona", "Detalle", "Ingresos", "Faltante", "Sobrante", "Monto ideal", 
				"Efectividad", "Observaciones",
				"$1000", "$500", "$200", "$100", "$50", "$20", "$10", "$5", "$2", "$1", "$0.50", "$0.25", "Usuario",
				 "Fecha_registro", "Hora_registro"};
		
		return columna;
	}
	private String [] getColumnas_zonas_impresion(){
		
		String columna [] = new String []{"Zona", "Ingresos", "Faltante", "Sobrante", "Monto ideal", 
				"Efectividad", "Observaciones"};
		
		return columna;
	}
	private String [] getColumnas_resumen(){
		
		String columna [] = new String []{"Fecha", "Ingresos", "Egresos", "Sobrante", "Faltante", 
				"Observaciones", 
				"$1000", "$500", "$200", "$100", "$50", "$20", "$10", "$5", "$2", "$1", "$0.50", "$0.25",
				"Usuario", "Hora_registro"};
		
		return columna;
	}
	private String [] getColumnas_resumen_impresion(){
		
		String columna [] = new String []{"Ingresos", "Egresos", "Saldo","Sobrante", "Faltante", 
				"Observaciones"};
		
		return columna;
	}
	
	private String [] getColumnas_despacho(){
		
		String columna [] = new String []{"N pedido", "Cliente", "Entrega", "Detalle", "Estado", "Ingresos", "Observaciones",
				 "Usuario",
				"Fecha","Hora_registro"};
		
		return columna;
	}
	
	private String [] getColumnas_prestamos(){
		
		String columna [] = new String []{"N pedido", "Cliente", "Entrega", "Detalle", "Estado", "Egresos", "Observaciones",
				 "Usuario",
				"Fecha","Hora_registro"};
		
		return columna;
	}
	
	private String [] getColumnas_interna(){
		
		String columna [] = new String []{"N°", "Motivo", "Detalle", "Ingresos", "Egresos", "Usuario",
				"Fecha registro","Hora registro"};
		
		return columna;
	}
	
	private String [] getColumnas_sueldos(){
		
		String columna [] = new String []{"N°", "Nombre", "Monto", "Concepto", "Usuario",
				"Fecha registro","Hora registro"};
		
		return columna;
	}
	
	private String [] getColumnas_proveedores(){
		
		String columna [] = new String []{"N°","Proveedor", "Detalle", "Monto", "Usuario",
				"Fecha registro","Hora registro"};
		
		return columna;
	}
	
	private String [] getColumnas(){
		
		String columna [] = new String []{"ID", "Cargo", "Nombre", "Motivo"};
		
		return columna;
	}
	
	private String [] getColumnas_monedas(){
		
		String columna [] = new String []{"Fecha", "Ingreso", "Egreso", "Acumulado"};
		
		return columna;
	}
	
	
	
	public void habilita_datos(boolean zonaTF, boolean buscar_zona, boolean fecha_ingreso, 
			boolean fecha_busqueda, boolean ver_fi, boolean ver_fb, boolean guardar,
			boolean modificar, boolean cancelar, boolean cambiar_orden, boolean imprimir, boolean nuevo_comando){
		
		
		this.fecha_ingreso.getComponent(1).setEnabled(fecha_ingreso);
		this.fecha_busqueda.getComponent(1).setEnabled(fecha_busqueda);
		this.ver_fi.setEnabled(ver_fi);
		this.ver_fb.setEnabled(ver_fb);
		
		this.guardar.setEnabled(guardar);
		this.modificar.setEnabled(modificar);
		this.cancelar.setEnabled(cancelar);
		this.imprimir.setEnabled(imprimir);

	}
	
	 public void limpiar(JTable tabla){
	    	
	    	if(tabla.getRowCount() > 0){
				
				DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
				
				int contFila = tabla.getRowCount();
				
				for(int i = 0; i < contFila; i++)
				
					modelo.removeRow(0);
			}
	    	//obL.setText("");
	    	//observaciones.setText("");
	  }
	
	 public  void limpiar_lt(){
		 
		 buscar_empleadoTF.setText("");
		 buscar_empleado_sueldoTF.setText("");
		 montoTF.setText("");
		 buscar_motivoTF.setText("");
		 empleadoL.setText("");
		 empleado_sueldoL.setText("");
		 motivoL.setText("");
		 motivo_inL.setText("");
		 buscar_motivo_inTF.setText("");
		 buscar_detalleTF.setText("");
		 motivo_internoL.setText("");
		 detalleL.setText("");
		 monto_internoTF.setText("");
		 buscar_proveedorTF.setText("");
		 proveedorL.setText("");
		 detalle_proveedoresL.setText("");
		 detalle_proveedoresTF.setText("");
		 monto_proveedorTF.setText("");
		 
	 }
	 
	class CustomJToolTip extends JToolTip {

	    public CustomJToolTip(JComponent component) {
	        super();
	        setComponent(component);
	        setBackground(Color.white);
	        setForeground(Color.BLACK);
	    }
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
	
	public void setAllPanelEnabled(boolean enabled){
		
		guardar.setEnabled(enabled);
		setPanelEnabled(pCaja_inasistencia, enabled);
		setPanelEnabled(pCaja_altasbajas, enabled);
		setPanelEnabled(pCaja_zonas, enabled);
		setPanelEnabled(pCaja_despacho, enabled);
		setPanelEnabled(pCaja_prestamos, enabled);
		setPanelEnabled(pCaja_internos, enabled);
		setPanelEnabled(pCaja_sueldos, enabled);
		setPanelEnabled(pCaja_proveedores, enabled);
		setPanelEnabled(pCaja_monedas, enabled);
		setPanelEnabled(pResumen_cierre, enabled);
		
	}
	
	public void setLabelFecha(java.sql.Date fecha){
		
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
	
	
}


