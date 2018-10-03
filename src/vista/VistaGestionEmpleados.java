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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controlador.ControladorArticulo;
import controlador.ControladorCambio_plan;
import controlador.ControladorCliente;
import controlador.ControladorCobrador;
import controlador.ControladorCombo;
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
import controlador.ControladorRetiros;
import controlador.ControladorUsuario;
import controlador.ControladorVendedor;
import controlador.ControladorVendedor_ph;
import controlador.ControladorZona;
import modelo.CifradoAction;
import modelo.DescifradoAction;
import modelo.LogicaArticulo;
import modelo.LogicaCambio_plan;
import modelo.LogicaCombo;
import modelo.LogicaEmpleado;
import modelo.LogicaPedido;
import modelo.LogicaRetiro;
import modelo.LogicaVendedor;
import modelo.LogicaVendedor_ph;
import modelo.Mensajes;
import modelo_dao.Refinanciacion_exDAO;
import modelo_dao.Refinanciacion_inDAO;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.CobradorVO;
import modelo_vo.Combinacion_diasVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.Refinanciacion_exVO;
import modelo_vo.Refinanciacion_inVO;
import modelo_vo.UsuariosVO;
import modelo_vo.VendedorVO;
import modelo_vo.Vendedores_padre_hijoVO;
import modelo_vo.VentasVO;
import modelo_vo.ZonaVO;
import vista.VistaBuscarPedidos_porClientes.CustomJToolTip;

public class VistaGestionEmpleados extends JInternalFrame implements ActionListener {
	
	private JScrollPane scr;
	private JComponent panel = new PanelGraduado(new Color(235, 245, 251), new Color(214, 234, 248)  );
	private JTextField idTF  = new JTextField(5);
	private JTextField dniTF= new JTextField(15);
	private JTextField nombreTF= new JTextField(15);
	private JTextField apellidoTF= new JTextField(15);
	private JTextField salario_semanalTF= new JTextField(5);
	private JTextField calleTF= new JTextField(15);
	private JTextField numeroTF= new JTextField(5);
	private JTextField localidadTF= new JTextField(15);
	private JTextField telefonoTF= new JTextField(15);
	private JTextField emailTF= new JTextField(15);
	private JTextField porcentaje_premioTF= new JTextField(5);
	private JTextField comision_prestamoTF= new JTextField(5);
	private JTextField auto_modeloTF= new JTextField(15);
	private JTextField patenteTF= new JTextField(15);
	private JTextField id_phTF= new JTextField(5);

	private JDatePickerImpl fecha_nacimiento;
	private JDatePickerImpl fecha_alta;
	
	JComponent labelsAndFields;
	
	private ArrayList<JTextField> empleadoTF;
	private ArrayList<JTextField> cobradorTF;
	private ArrayList<JTextField> vendedorTF;

	private ArrayList<JDatePickerImpl> array_datepicker;
	
	private JButton ver_dependencia = new JButton("Ver");
	private JButton buscar_empleado = new JButton("...");
	//JPanel pOpcion_vendedor_ph = new JPanel();
	
	private JComboBox puestoCB = new JComboBox();
	private JComboBox zonaCB = new JComboBox();
	
	private ControladorVendedor _controladorVendedor;
	private ControladorVendedor_ph _controladorVendedor_ph = new ControladorVendedor_ph();
	private LogicaVendedor_ph logica_vendedor_ph = new LogicaVendedor_ph();
	private ControladorCobrador _controladorCobrador;
	private ControladorZona _controladorZona;
	private ControladorLocalidad _controladorLocalidad;
	private ControladorPedidos _controladorPedido;
	private ControladorArticulo _controladorArticulo;
	private ControladorPagoDiario _controladorPagoDiario;
	private ControladorUsuario _controladorUsuario;
	private ControladorEmpleado controladorEmpleado;
	private BusquedaEntities _busqueda_entities;
	private VistaBuscarPedidos_porClientes vp;
	private   VistaPrincipal _vista_principal;

	private boolean editar = false;
	private boolean guardar_bool = false;
	private boolean estado = true;
	private EmpleadoVO empleadoVO;
	
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
	
	private JButton buscar = new JButton(){
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
    
    private JButton nuevo = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
    
    private JButton nuevo_empleado = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
    
	private JPanel pIntegra;
	
	UtilDateModel modelFnacimiento = new UtilDateModel();
	UtilDateModel modelFalta = new UtilDateModel();
	/*Barra_herramientasPedidos bhp2 = new Barra_herramientasPedidos(guardar, modificar, salir, ref_in, ref_ex,
			cancelar, anular);*/
	
	public VistaGestionEmpleados(){
		//super(_vista_principal, "Gestión de empleados", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
	     //add(pIntegra_pedidos, BorderLayout.SOUTH);
	     super("Gestión de empleados", true, true, true, true);
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     setSize(dim.width*40/100, dim.height*75/100);
	     //setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	     this.setResizable(false);
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	     this.setFocusable(true);
	        setClosable(true); 
	        setIconifiable(true); 
	        setMaximizable(true); 
	     
	     buscar.addActionListener(this);
		buscar_empleado.addActionListener(this);
		ver_dependencia.addActionListener(this);
		modificar.addActionListener(this);
		guardar.addActionListener(this);
		anular.addActionListener(this);
		
		cancelar.addActionListener(this);
		nuevo.addActionListener(this);
		nuevo_empleado.addActionListener(this);
		
		setLayout(new BorderLayout());
		
		GridLayout gl = new GridLayout(0,2);
  		
  		idTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				idTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
				idTF.setBackground(new Color(255, 255, 255));
				
			}
  			
  			
  		});
  		
  		puestoCB.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if((puestoCB.getSelectedIndex()==2 || 
						puestoCB.getSelectedIndex()==3 || 
						puestoCB.getSelectedIndex()==4 ||
						puestoCB.getSelectedIndex()==5) && (editar || guardar_bool)){
					
					habilita_datosEmpleado(false,false, true,true,true, true,true, true, true, true,
							true, true,false,false,false,false,false,false,true, true,true,false,
							true,false, false, false, false);
				}
				if(puestoCB.getSelectedIndex()==0 && (editar || guardar_bool)){
					
					habilita_datosEmpleado(false,false, true,true,true, true,true, true, true, true,
							true, true,true,false,false,false,true,true,true, true,true,false,
							true,false, false, false, false);
				}
				if(puestoCB.getSelectedIndex()==1 && (editar || guardar_bool)){
					
					habilita_datosEmpleado(false,false, true,true,true, true,true, true, true, true,
							true, true,true,true,true,false,false,false,true, true,true,false,
							true,false, false, false, false);
				}
			}
  			
  			
  		});
  		
		pIntegra = new JPanel();
		pIntegra.setLayout(new BoxLayout(pIntegra, 	BoxLayout.Y_AXIS));
  	
  		Properties p = new Properties();
  		p.put("text.today", "Today");
  		p.put("text.month", "Month");
  		p.put("text.year", "Year");
        JDatePanelImpl datePanelFnacimiento = new JDatePanelImpl(modelFnacimiento, p);
        JDatePanelImpl datePanelFalta = new JDatePanelImpl(modelFalta, p);
     
        fecha_nacimiento = new JDatePickerImpl(datePanelFnacimiento, new DateLabelFormatter());
        fecha_alta = new JDatePickerImpl(datePanelFalta, new DateLabelFormatter());
 
        cargarComboPuesto();
        cargarComboZona();
        
        JPanel pId = new JPanel();
        pId.setLayout(gl);
        
        pId.add(idTF);
        pId.add(this.buscar_empleado);
        pId.setOpaque(false);
        
   
        
       JComponent[] components = {
               
               pId,
               dniTF,
               puestoCB,
               nombreTF,
               apellidoTF,
               salario_semanalTF,
               calleTF,
               numeroTF,
               localidadTF,
               telefonoTF,
               emailTF,
               porcentaje_premioTF,
               comision_prestamoTF,
               ver_dependencia,
               zonaCB,
               auto_modeloTF,
               patenteTF,
               fecha_nacimiento,
               fecha_alta,
        		
            };
		
		 String labels [] = {
				 
				 "ID", "Dni", "Puesto", "Nombre", "Apellido","Salario semanal",
					"Calle",  "Numero","Localidad", "Teléfono", "Email",
					"%Premio/Comisión","%ComisionP","Dependencia vendedor","Zona",
					"Auto modelo","Patente", "Fecha nacimiento", "Fecha alta"
		 };
		
		 JFormattedTextField fn = fecha_nacimiento.getJFormattedTextField();
		 JFormattedTextField fa = fecha_alta.getJFormattedTextField();
		 fn.setFont(new Font("Arial", Font.PLAIN, 18));
		 fa.setFont(new Font("Arial", Font.PLAIN, 18));
	
		empleadoTF = new ArrayList<JTextField>();
		
		
		empleadoTF.add(idTF);
		empleadoTF.add(dniTF);
		empleadoTF.add(nombreTF);
		empleadoTF.add(apellidoTF);
		empleadoTF.add(salario_semanalTF);
		empleadoTF.add(calleTF);
		empleadoTF.add(numeroTF);
		empleadoTF.add(localidadTF);
		empleadoTF.add(telefonoTF);
		empleadoTF.add(emailTF);
		empleadoTF.add(fecha_nacimiento.getJFormattedTextField());
		empleadoTF.add(fecha_alta.getJFormattedTextField());
	
		cobradorTF =  new ArrayList<JTextField>();
		
		cobradorTF.add(porcentaje_premioTF);
		cobradorTF.add(auto_modeloTF);
		cobradorTF.add(patenteTF);
		
		vendedorTF = new ArrayList<JTextField>();
		
		vendedorTF.add(porcentaje_premioTF);
		vendedorTF.add(comision_prestamoTF);
		vendedorTF.add(id_phTF);
		
		array_datepicker = new ArrayList<JDatePickerImpl>();
		
		array_datepicker.add(fecha_nacimiento);
		array_datepicker.add(fecha_alta);

		Container contentPane = this.getContentPane();
		
		BH_empleado bh = new BH_empleado(guardar, modificar,cancelar,buscar, anular, nuevo, nuevo_empleado);
		
	    contentPane.add(bh, BorderLayout.NORTH);
	  
	    setPanel("Gestion de empleados");
	    
		labelsAndFields = getTwoColumnLayout(labels,components, "Gestion de empleados");
		
	    scr = new JScrollPane(labelsAndFields,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	    		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	   
	    this.add(scr);
	    
	    limpiar();
		
	}
	

	public void setPanel(String titulo){
		
		Border borde0 = BorderFactory.createTitledBorder(null, titulo, 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,20), Color.BLACK);
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
        //panel = new JPanel();
       
        
        Font fuente_titulo = new Font("Arial", Font.BOLD, 20);
        
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
        	
        	label.setFont(new Font("Arial", Font.PLAIN, 18));
            yLabelGroup.addComponent(label);
           
        }
        
        for (Component field : fields) {
        	field.setFont(new Font("Arial", Font.PLAIN, 18));
        	if(field instanceof JButton || field instanceof JComboBox) 
        		field.setFont(new Font("Arial", Font.PLAIN, 14));
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
					if(!(field instanceof JButton|| field instanceof JComboBox)) 
						field.setBackground(new Color(183, 242, 113));
				
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(!(field instanceof JButton|| field instanceof JComboBox)) 
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
    
    public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vp){
		
		this.vp = vp;
	}
    
    public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
    
    
    public void setVistaPrincipal(VistaPrincipal _vista_principal){
		
		this._vista_principal = _vista_principal;
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
	
	public void setControladorCobrador(ControladorCobrador _controladorCobrador){
		
		this._controladorCobrador = _controladorCobrador;
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
	
	public void setControladorPrest(ControladorArticulo _controladorArticulo){
		
		this._controladorArticulo = _controladorArticulo;
	}

	public void setControladorUsuario(ControladorUsuario _controladorUsuario){
		
		this._controladorUsuario = _controladorUsuario;
	}
	public void setControladorEmpleado(ControladorEmpleado controladorEmpleado){
		
		this.controladorEmpleado = controladorEmpleado;
	}
	
	public ArrayList<JTextField> getArrayEmpleado(){
		
		return empleadoTF;
	}
	
	public ArrayList<JDatePickerImpl> getArrayDatePicker(){
		
		return array_datepicker;
	}


	public ArrayList<JTextField> getArrayCobrador(){
		
		return cobradorTF;
	}
	
	public ArrayList<JTextField> getArrayVendedor(){
		
		return vendedorTF;
	}
	
	public JTextField getEmpleadoTF(){
		
		return idTF;
	}
	
	public EmpleadoVO getEmpleadoVO(){
		
		return empleadoVO;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
	    _busqueda_entities.setPanel("vista_gestion_empleado");	
		
	    if(e.getSource()==ver_dependencia){
	    	
	    	ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
	    	ControladorVendedor controladorVendedor = new ControladorVendedor();
	    	ControladorVendedor_ph controladorVendedor_ph = new ControladorVendedor_ph();
	    	BusquedaEntities be = new BusquedaEntities();
	    	LogicaEmpleado le = new LogicaEmpleado();
	    	LogicaVendedor lv = new LogicaVendedor();
	    	LogicaVendedor_ph lv_ph = new LogicaVendedor_ph();
	    	
	    	controladorEmpleado.setLogicaEmpleado(le);
	    	controladorEmpleado.setBusquedaEntities(be);
	    	
	    	controladorVendedor.setLogicaVendedor(lv);
	    	controladorVendedor.setBusquedaEntities(be);
	    	controladorVendedor_ph.setLogicaVendedor_ph(lv_ph);
	    	controladorVendedor_ph.setBusquedaEntities(be);
	    	le.setBusquedaEntities(be);
	    	lv.setBusquedaEntities(be);
	    	lv_ph.setBusquedaEntities(be);
	    	
	    	VistaDependenciaVendedor vd = new VistaDependenciaVendedor();
	    	be.setVistaDependenciaVendedor(vd);
	    	
	    	_vista_principal.getDesktop().add(vd);
	    	vd.setControladorEmpleado(controladorEmpleado);
	    	vd.setControladorVendedor_ph(controladorVendedor_ph);
	    	vd.setBusquedaEntities(be);
	    	vd.setControladorVendedor(controladorVendedor);
	    	vd.setVistaGestionEmpleados(this);
	    	
	    	vd.iniciar(empleadoVO);
	    	vd.setVisible(true);
	    	
	    }
	    
	    if(e.getSource()==buscar){
	    	
	    	editar = false;
	    	guardar_bool = false;
	    	
	    	EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(idTF.getText());
	    	
	    
	    	if(eVO!=null ){
	    		
	    		empleadoVO = eVO;
	    		
	    		if(eVO.getId_usuario()!=33){//codigo halcones diarios
	    			
	    			habilita_datosEmpleado(false,false, false,false,false, false,false, false, false, false,
	    					false, false,false,false,false,false,false,false,false, false,false,true, 
	    					false,true, true, true, true);
	    			
	    		}
	    		
	    		iniciar(eVO);
	    		
	    	}
	    	else Mensajes.getMensaje_empleadoNoExiste();
	    }
	    
		if(e.getSource() == buscar_empleado){
			
			idTF.requestFocus();
			controladorEmpleado.buscarEmpleadosAll();
			controladorEmpleado.mostrarBusquedaEntities("Busqueda de empleados");
			
			//articuloTF.requestFocus();
			//comboTF.requestFocus();
			
		}
		
		if(e.getSource()==nuevo){
			
			editar = false;
			guardar_bool = false;
			limpiar();
		}
		
		if(e.getSource()==nuevo_empleado){
			
			editar = false;
			guardar_bool = true;
			limpiar();

			habilita_datosEmpleado(false,true, true,true,true, true,true, true, true, true,
					true, true,false,false,false,false,false,false,true, true,true,false, 
					true,false, false, false, false);
			
			puestoCB.setSelectedIndex(0);
		}
		
		if(e.getSource() == modificar){
			
			editar = true;
			guardar_bool = false;
			
			if(puestoCB.getSelectedIndex()==2 || 
					puestoCB.getSelectedIndex()==3|| 
					puestoCB.getSelectedIndex()==4 ||
					puestoCB.getSelectedIndex()==5){
				
				habilita_datosEmpleado(false,false, true,true,true, true,true, true, true, true,
						true, true,false,false,false,false,false,false,true, true,true,false,
						true,false, false, false, true);
			}
			if(puestoCB.getSelectedIndex()==0){
				
				habilita_datosEmpleado(false,false, true,true,true, true,true, true, true, true,
						true, true,true,false,false,false,true,true,true, true,true,false,
						true,false, false, false,true);
			}
			if(puestoCB.getSelectedIndex()==1){
				
				habilita_datosEmpleado(false,false, true,true,true, true,true, true, true, true,
						true, true,true,true,true,false,true,true,true, true,true,false,
						true,false, false, false, true);
			}
			
			//habilita_datosEmpleado(false,false, true,true,true, true, true, true, true,
				//	true, true,true,true,true,true,true,true,true,true, true,true,false, true,false, false, true);
		
			
			
			idTF.requestFocus();
		}
		
		if(e.getSource() == guardar){
			
			if(controladorEmpleado.modificar_nuevoEmpleadoUsuario(empleadoTF, cobradorTF, vendedorTF, 
					 puestoCB, editar)){
				
					
					int res = 0;
					
					//if(puestoCB.getSelectedIndex()==0){
						
						EmpleadoVO empleadoVO = new EmpleadoVO();
						
						if(editar)
						
							empleadoVO.setId_usuario(Short.parseShort(idTF.getText()));
						
						empleadoVO.setDni(Integer.parseInt(dniTF.getText()));
						
						switch(puestoCB.getSelectedIndex()){
						
						case 0:
							empleadoVO.setPuesto("cobrador");
							break;
						case 1:
							empleadoVO.setPuesto("vendedor");
							break;
						case 2:
							empleadoVO.setPuesto("administrativo");
							break;
						case 3:
							empleadoVO.setPuesto("jefe_calle");
							break;
						case 4:
							empleadoVO.setPuesto("gestor");
							break;
						case 5:
							empleadoVO.setPuesto("maestranza");
							break;
						}
						
						empleadoVO.setNombre(nombreTF.getText());
						empleadoVO.setApellido(apellidoTF.getText());
						empleadoVO.setSalario_semanal(Double.parseDouble(salario_semanalTF.getText()));
						
						empleadoVO.setCalle(calleTF.getText());
						empleadoVO.setNumero(Integer.parseInt(numeroTF.getText()));
						empleadoVO.setLocalidad(localidadTF.getText());
						empleadoVO.setTelefono(telefonoTF.getText());
						empleadoVO.setEmail(emailTF.getText());
						
						empleadoVO.setEstado(estado);
						
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
						
						String fecha_altaS =fecha_alta.getJFormattedTextField().getText();
						DateFormat format_alta = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
						Date dateFA = new Date();
						try {
							dateFA = format.parse(fecha_altaS);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						java.sql.Date fecha_altaDate = new java.sql.Date(dateFA.getTime());
						
						empleadoVO.setFecha_nacimiento(fecha_nacimientoDate);
						empleadoVO.setFecha_alta(fecha_altaDate);
						
						CobradorVO cobradorVO = new CobradorVO();
						
						cobradorVO.setPremio(Double.parseDouble(porcentaje_premioTF.getText()));
						cobradorVO.setAuto_modelo(auto_modeloTF.getText());
						cobradorVO.setPatente(patenteTF.getText());
						
						VendedorVO vendedorVO = new VendedorVO();
						
						vendedorVO.setComision(Double.parseDouble(porcentaje_premioTF.getText()));
						vendedorVO.setComision_prestamo(Double.parseDouble(comision_prestamoTF.getText()));
				
						if(editar){
							
							res = controladorEmpleado.modificarEmpleado(empleadoVO, cobradorVO,
								vendedorVO);
						
						}
						else if(guardar_bool) {
					
							res = controladorEmpleado.nuevoEmpleado(empleadoVO, cobradorVO,
									vendedorVO);
					
						}
						if(res >= 0){
							
							Mensajes.getMensaje_modificacionExitosa();
							if(guardar_bool) limpiar();
							else{
								
								habilita_datosEmpleado(true,true, false,false,false,false, false, false, false, false,
										false, false,false,false,false,false,false,false,false, false,false,true, 
										false,true, true, true, true);
								
							}
						}
						else
						{
							
							Mensajes.getMensaje_modificacion_sinExito();
						}
			}
			else{
				
				Mensajes.getMensaje_altaErrorGenerico();
			}
		}
		
		if(e.getSource() == anular){
			
			
			int opcion = Mensajes.getMensaje_confirmacion_anulacion_generico();
			
			if(opcion == JOptionPane.YES_OPTION){
				
				int res = 0;
				
				if(empleadoVO!=null){
					
					short i = 0;
					
					res = controladorEmpleado.modificarEstadoEmpleado(empleadoVO.getId_usuario(), i);
					
				}
				
				
				if(res > 0 ){
					
					Mensajes.getMensaje_bajaEmpleadoExitosa();
					limpiar();
				}
				else Mensajes.getMensaje_anulacionError();
			}
						
		}
		
		if(e.getSource() == cancelar){
			
			System.out.println("guardar bool " + guardar_bool + " editar " + editar);
			
			if(guardar_bool) limpiar();
			else if(editar){
				
				editar = false;
				
				habilita_datosEmpleado(false,false, false,false,false, false,false, false, false, false,
						false, false,false,false,false,false,false,false,false, false,false,true, 
						false,true, true, true, true);
				
				if(empleadoVO!=null)
					
					iniciar(empleadoVO);
			}
			
			editar = false;
			guardar_bool = false;
	
			
		}
		
	}
	
	public void limpiar(){
		
		for(JTextField tf : getArrayEmpleado()){
			
			tf.setText("");
		}
		
		for(JTextField tf : getArrayCobrador()){
			
			tf.setText("");
		}

		for(JTextField tf : getArrayVendedor()){
			
			tf.setText("");
		}
		
		habilita_datosEmpleado(true,true, false,false,false, false, false, false,false, false,
				false, false,false,false,false,false,false,false,false, false,false,false, 
				false,true, false, true, true);
	}
	
	public void formato_disable(){
		
		for(JTextField tf : getArrayEmpleado()){
			
			tf.setDisabledTextColor(new Color(113,125,126));
			//tf.setFont(new Font("Arial", Font.BOLD, 24));
		}
		
		for(JTextField tf : getArrayCobrador()){
			
			tf.setDisabledTextColor(new Color(113,125,126));
			//tf.setFont(new Font("Arial", Font.BOLD, 24));
		}
		
		for(JTextField tf : getArrayVendedor()){
			
			tf.setDisabledTextColor(new Color(113,125,126));
			//tf.setFont(new Font("Arial", Font.BOLD, 24));
		}

	}
	
	public void setControladorPedidos(ControladorPedidos _controladorPedido){
		
		this._controladorPedido = _controladorPedido;
	}
	
	public void iniciar(EmpleadoVO eVO){
		
		formato_disable();
		//idTF.setText(eVO.get);
		
		dniTF.setText(Integer.toString(eVO.getDni()));
		
		switch(eVO.getPuesto()){
		
		case "cobrador" :
				
				puestoCB.setSelectedIndex(0);
			break;
		case "vendedor" :
			
			puestoCB.setSelectedIndex(1);
			break;
		case "administrativo" :
			
			puestoCB.setSelectedIndex(2);
			break;
		case "jefe_calle" :
			
			puestoCB.setSelectedIndex(3);
			break;
		case "gestor" :
			
			puestoCB.setSelectedIndex(4);
			break;
		case "maestranza" :
			
			puestoCB.setSelectedIndex(5);
			break;
		}
		
		nombreTF.setText(eVO.getNombre());
		apellidoTF.setText(eVO.getApellido());
		salario_semanalTF.setText(Double.toString(eVO.getSalario_semanal()));
		calleTF.setText(eVO.getCalle());
		numeroTF.setText(Integer.toString(eVO.getNumero()));
		localidadTF.setText(eVO.getLocalidad());
		telefonoTF.setText(eVO.getTelefono());
		emailTF.setText(eVO.getEmail());
		
		VendedorVO vendedorVO = null;
		
		if(eVO.getPuesto().equals("vendedor")){
			
			vendedorVO = _controladorVendedor.buscarVendedor_porID(eVO.getId_usuario());
			
			porcentaje_premioTF.setText(Double.toString(vendedorVO.getComision()));
			comision_prestamoTF.setText(Double.toString(vendedorVO.getComision_prestamo()));
			
		}
		else{
			
			porcentaje_premioTF.setText("0");
			comision_prestamoTF.setText("0");
			
		}
		
		CobradorVO cobradorVO = null;
		
		if(eVO.getPuesto().equals("cobrador")){
			
			cobradorVO = _controladorCobrador.buscarCobrador_porID(eVO.getId_usuario());
			
			ZonaVO zVO = _controladorZona.buscarZona_porId_cobrador(cobradorVO.getId_cobrador());
			
			if(zVO!=null){
				
				
				switch(zVO.getId_zona()){
				
				case 1 :
					
					zonaCB.setSelectedIndex(0);
					break;
				case 2 :
					
					zonaCB.setSelectedIndex(1);
					break;
				case 3 :
					
					zonaCB.setSelectedIndex(2);
					break;
				case 4:
					
					zonaCB.setSelectedIndex(3);
					break;
				case 5 :
					
					zonaCB.setSelectedIndex(4);
					break;
				case 6:
					
					zonaCB.setSelectedIndex(5);
					break;
				}
				
			}
			
			porcentaje_premioTF.setText(Double.toString(cobradorVO.getPremio()));
			comision_prestamoTF.setText("0");
			
			auto_modeloTF.setText(cobradorVO.getAuto_modelo());
			patenteTF.setText(cobradorVO.getPatente());
		}
		else{
			
			auto_modeloTF.setText("");
			patenteTF.setText("");

			
		}
		
		_controladorVendedor_ph.setLogicaVendedor_ph(logica_vendedor_ph);
		
		ArrayList<Vendedores_padre_hijoVO> arVendedor_padres = _controladorVendedor_ph.
				buscarVendedoresPadres_porIdHijo(eVO.getId_usuario());
		ArrayList<Vendedores_padre_hijoVO> arVendedor_hijos = _controladorVendedor_ph.
				buscarVendedoresHijos_porIdPadre(eVO.getId_usuario());
		
		/*if(arVendedor_padres != null){
			
			vendedor_hijo.setSelected(true);
			
			for(Vendedores_padre_hijoVO vph : arVendedor_padres){
				
				id_phTF.setText(Integer.toString(vph.getId_padre()));
				
				EmpleadoVO empVO = controladorEmpleado.buscarEmpleado(Integer.toString(vph.getId_padre()));
				
				vendedor_phL.setText(empVO.getNombre() + " " + empVO.getApellido());
			}
				
			
		}
		else if(arVendedor_hijos!=null){
				
			vendedor_padre.setSelected(true);
			for(Vendedores_padre_hijoVO v_hijoVO : arVendedor_hijos){
				
				id_phTF.setText(Integer.toString(v_hijoVO.getId_hijo()));
				
				EmpleadoVO empVO = controladorEmpleado.buscarEmpleado(Integer.toString(v_hijoVO.getId_hijo()));
				
				vendedor_phL.setText(empVO.getNombre() + " " + empVO.getApellido());
				
			}
					
			
		}
		else{
			vendedor_ninguna.setSelected(true);
			
			id_phTF.setText("");
			vendedor_phL.setText("");
			
		}*/
		
		modelFnacimiento.setValue(eVO.getFecha_nacimiento());
		modelFalta.setValue(eVO.getFecha_alta());

		
	}
	
	public void habilita_datosEmpleado(boolean id, boolean buscar_empleado, boolean dni,boolean puesto,
			boolean nombre,boolean apellido, boolean salario_semanal,
			boolean calle,
			boolean numero,boolean localidad,boolean telefono,boolean email, boolean por_premio,
			boolean comision_prestamo, 
			boolean ver_dependencia, boolean zona,
			boolean auto_modelo,boolean patente, boolean fecha_nacimiento,boolean fecha_alta,
			boolean guardar, 
			boolean modificar, boolean cancelar, boolean buscar,
			boolean anular, boolean nuevo_empleado, boolean nuevo){
		
		idTF.setEnabled(id);
		this.buscar_empleado.setEnabled(buscar_empleado);
		dniTF.setEnabled(dni);
		puestoCB.setEnabled(puesto);
		nombreTF.setEnabled(nombre);
		apellidoTF.setEnabled(apellido);
		salario_semanalTF.setEnabled(salario_semanal);
		calleTF.setEnabled(calle);
		numeroTF.setEnabled(numero);
		localidadTF.setEnabled(localidad);
		telefonoTF.setEnabled(telefono);
		emailTF.setEnabled(email);
		porcentaje_premioTF.setEnabled(por_premio);
		comision_prestamoTF.setEnabled(comision_prestamo);
		this.ver_dependencia.setEnabled(ver_dependencia);
		zonaCB.setEnabled(zona);
		auto_modeloTF.setEnabled(auto_modelo);
		patenteTF.setEnabled(patente);
		this.fecha_nacimiento.getComponent(1).setEnabled(fecha_nacimiento);
		this.fecha_alta.getComponent(1).setEnabled(fecha_alta);
		
		this.guardar.setEnabled(guardar);
		
		this.modificar.setEnabled(modificar);
		this.cancelar.setEnabled(cancelar);
		this.buscar.setEnabled(buscar);
		this.anular.setEnabled(anular);
		this.nuevo.setEnabled(nuevo);
		this.nuevo_empleado.setEnabled(nuevo_empleado);
		
	}
	
	public void cargarComboPuesto(){
		
		String puestos [] = {"Cobrador", "Vendedor", "Administrativo", "Jefe de calle",
				"Gestor", "Maestranza"};
		
		for(String s : puestos) puestoCB.addItem(s);
	}
	public void cargarComboZona(){
		
		String zonas [] = {"1","2","3","4","5","6"};
		
		for(String s : zonas) zonaCB.addItem(s);
	}
	
	void setPanelEnabled(JComponent panel, Boolean isEnabled) {
	    panel.setEnabled(isEnabled);

	    Component[] components = panel.getComponents();

	    for(int i = 0; i < components.length; i++) {
	        if(components[i].getClass().getName() == "javax.swing.JComponent") {
	            setPanelEnabled((JComponent) components[i], isEnabled);
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
