package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controlador.ControladorArticulo;
import controlador.ControladorCajaZona;
import controlador.ControladorCliente;
import controlador.ControladorCobrador;
import controlador.ControladorCombo;
import controlador.ControladorDespacho_diario;
import controlador.ControladorDomicilioComercial;
import controlador.ControladorDomicilioParticular;
import controlador.ControladorEmpleado;
import controlador.ControladorJefeCalle;
import controlador.ControladorLocalidad;
import controlador.ControladorMonto_trasladado;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorPremios;
import controlador.ControladorPrestamo;
import controlador.ControladorRefinanciacion_ex;
import controlador.ControladorRefinanciacion_in;
import controlador.ControladorUsuario;
import controlador.ControladorVendedor;
import controlador.ControladorVentas;
import controlador.ControladorZona;
import modelo.LogicaArticulo;
import modelo.LogicaCajaZona;
import modelo.LogicaCliente;
import modelo.LogicaCobrador;
import modelo.LogicaCombo;
import modelo.LogicaDespacho;
import modelo.LogicaDomicilioComercial;
import modelo.LogicaDomicilioParticular;
import modelo.LogicaEmpleado;
import modelo.LogicaJefe_calle;
import modelo.LogicaLocalidad;
import modelo.LogicaMonto_trasladado;
import modelo.LogicaPagoDiario;
import modelo.LogicaPedido;
import modelo.LogicaPremio;
import modelo.LogicaPrestamo;
import modelo.LogicaRefinanciacion_ex;
import modelo.LogicaRefinanciacion_in;
import modelo.LogicaUsuario;
import modelo.LogicaVendedor;
import modelo.LogicaVenta;
import modelo.LogicaZona;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.SeccionVO;
import vista.VistaBuscarPedidos_porClientes.CustomJToolTip;
import vista.VistaNewObjetoVenta.FocoListener;

public class VistaBuscarArticulos extends JInternalFrame implements ActionListener{

	private JPanel pIntegra;
	private JPanel pNorte;
	private JPanel pPlan;
	private static JComponent panel; 
	private JPanel pNuevo_articulo;
	
	private int opcion_articulo;
	
	private JTextField articuloTF = new JTextField(10);
	private JTextField nombreTF = new JTextField(20);
	/*private JTextField diasTF = new JTextField(5);
	private JTextField cuotaTF = new JTextField(5);*/
	private JTextArea descripcionTF= new JTextArea(3,20);
	
	private JLabel lCombinaciones;
	
	private JLabel titulo_barra;
	
	private ControladorArticulo _controladorArticulo;
	private ControladorCombo _controladorCombo;
	private ControladorPrestamo _controladorPrestamo;
	private BusquedaEntities _busqueda_entities;
	private static VistaBuscarPedidos_porClientes vpc;
	private VistaPrincipal vp;
	
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
    
    private JButton buscar_articulo = new JButton("...");
    private JButton crear_articulo = new JButton("Crear");
    private JButton cancelar = new JButton("Cancelar");
    private JTextField seccionTF = new JTextField();
	private JButton buscar_seccion = new JButton("...");
	JPanel pSeccion = new JPanel();
	JPanel pIntegra_seccion = new JPanel();
	private JLabel lSeccion = new JLabel();
	private JPanel pArticulo;
    
	private String cliente;
	
	public VistaBuscarArticulos(/*Window ventana, String titulo*/){
			//super(ventana,titulo, JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
			super("Gestion de artículos", true, true, true, true);
			
		guardar.addActionListener(this);
		modificar.addActionListener(this);
		buscar_articulo.addActionListener(this);
		crear_articulo.addActionListener(this);
		cancelar.addActionListener(this);
		buscar_seccion.addActionListener(this);
		
		setLayout(new BorderLayout());
	
		GridLayout gl = new GridLayout(0,2);
  		
		articuloTF = new JTextField(5);
  		articuloTF.setFont(new Font("Arial", Font.BOLD, 16));
  		/*diasTF = new JTextField(5);
  		diasTF.setFont(new Font("Arial", Font.BOLD, 16));
  		cuotaTF = new JTextField(5);
  		cuotaTF.setFont(new Font("Arial", Font.BOLD, 16));*/
  		/*nombreTF.setFont(new Font("Arial", Font.PLAIN, 16));
  		descripcionTF.setFont(new Font("Arial", Font.PLAIN, 16));*/
  		
  		articuloTF.setDisabledTextColor(new Color(113,125,126));
  		nombreTF.setDisabledTextColor(new Color(113,125,126));
  		descripcionTF.setDisabledTextColor(new Color(113,125,126));
  		/*diasTF.setDisabledTextColor(new Color(113,125,126));
  		cuotaTF.setDisabledTextColor(new Color(113,125,126));*/
  		
  		descripcionTF.setBorder(BorderFactory.createLineBorder(Color.BLACK));
  		descripcionTF.setLineWrap(true);
  		
  		articuloTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				articuloTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
					
				actualizarArticulo(articuloTF.getText());
				articuloTF.setBackground(new Color(255, 255, 255));
				
			}
  			
  			
  		});
  		
  		seccionTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				seccionTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
				seccionTF.setBackground(new Color(255, 255, 255));
				SeccionVO sVO = _controladorArticulo.buscarSeccion(seccionTF.getText());
				if(sVO!=null){
					
					seccionTF.setText(Integer.toString(sVO.getCodigo()));
					lSeccion.setText(sVO.getDescripcion());
					
				}
				else{
					
					seccionTF.setText("");
					lSeccion.setText("");
				}
					
				
			}
  			
  			
  		});
  		
  		pArticulo = new JPanel();	
  		pArticulo.setLayout(gl);
  		pArticulo.add(articuloTF);
  		buscar_articulo.setFocusable(false);
  		JPanel pArt2 = new JPanel();
  		pArt2.setLayout(gl);
  		pArt2.add(buscar_articulo);
  		pArt2.add(crear_articulo);
  		pArticulo.add(pArt2);
  	
		pIntegra = new JPanel();
		pNorte = new JPanel();//PanelGraduado(new Color(214, 234, 248), new Color(93, 173, 226));
		
		pIntegra.setBackground(Color.white);
		pIntegra.setLayout(new BoxLayout(pIntegra, 	BoxLayout.Y_AXIS));
		
		//_controladorZona
		Border borde0 = BorderFactory.createTitledBorder(null, "Gestión de artículos", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,16), Color.BLACK);
    			//panel.setBorder(borde0);
    			
		pIntegra.setBorder(BorderFactory.createCompoundBorder(
    					new EmptyBorder(10, 10, 10, 10), borde0));
		
		
		 
		 pIntegra_seccion.setLayout(gl);
		 
		 pIntegra_seccion.add(seccionTF);
		 pIntegra_seccion.add(buscar_seccion);
		 pSeccion.add(pIntegra_seccion);
		 pSeccion.add(lSeccion);
		 pSeccion.setBackground(Color.WHITE);
		
		 JComponent[] components = {
              
         		pArticulo,
         		nombreTF,
         		pSeccion
             };
		
		 String labels [] = {
				 
				 "Artículo","Nombre","Sección"
				 			 
		 };
			
		Border borde_com = BorderFactory.createTitledBorder(null, "", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,14), Color.BLACK);
    			//panel.setBorder(borde0);
    			
		pNorte.setBorder(BorderFactory.createCompoundBorder(
    					new EmptyBorder(10, 10, 10, 10), borde_com));
	
		//pNorte.add(pOpciones);
		pNorte.add(guardar);
		//pNorte.add(salir);
		 
		 pNuevo_articulo = new JPanel();
		 
		 Border borde2 = BorderFactory.createTitledBorder(null, "Nuevo artículo", 
	    			TitledBorder.CENTER, TitledBorder.TOP,
	    			new Font("Arial",Font.BOLD,14), Color.BLACK);
	    			//panel.setBorder(borde0);
	    			
			pNuevo_articulo.setBorder(BorderFactory.createCompoundBorder(
	    					new EmptyBorder(10, 10, 10, 10), borde2));
		 
		 pNuevo_articulo.setBackground(Color.white);	
			
		 JLabel nombreL = new JLabel();
		 JLabel descripcionL = new JLabel();
		 
		 nombreL.setText("Nombre");
		 descripcionL.setText("Descripcion");
		 
		 //intTF_articulo.add(descripcionTF);
			
		 JComponent labelsAndFields_art = getTwoColumnLayout(labels,components);
		
		 setPanelTitulo(labelsAndFields_art, "Consulta de artículos");
		 
		 //pIntegra.add(labelsAndFields_art);
		 
		 /*JScrollPane scr = new JScrollPane(labelsAndFields, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);*/
		 
		 //pIntegra.add(labelsAndFields_art);
		 
		 BH_buscarArticulo bd = new BH_buscarArticulo(guardar, modificar, cancelar);
		 
		 add(bd, BorderLayout.PAGE_START);
		 add(labelsAndFields_art, BorderLayout.CENTER);
			
		 /*KeyboardFocusManager.getCurrentKeyboardFocusManager()
         .addPropertyChangeListener("focusOwner", 
        		 	new PropertyChangeListener() {


        	 	public void propertyChange(PropertyChangeEvent evt) {
        	 			if (!(evt.getNewValue() instanceof JComponent)) {
        	 					return;
        	 			}
        	 			JComponent focused = (JComponent) evt.getNewValue();
        	 			if (labelsAndFields_art.isAncestorOf(focused)) {
        	 			
        	 				labelsAndFields_art.scrollRectToVisible(focused.getBounds());
        	 			}
        	 	}

         });
		 
		 addComponentListener( new WindowAdapter() {
			    public void windowOpened( WindowEvent e ){
			       articuloTF.requestFocus();
			    }
			}); */
		 
	     //add(pIntegra_pedidos, BorderLayout.SOUTH);
	     
		 setSize(450, 300);
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     //setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	     //this.setResizable(false);
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	     
	    /* this. addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	               
	            	limpiar();
	                e.getWindow().dispose();
	            }
	           
	        });*/
	        
	        this.setFocusable(true);
	        this.setResizable(false);
	        setClosable(true); 
	        setIconifiable(true); 
	        setMaximizable(true); 
	      
			//this.setLocationRelativeTo(null);
	     
			 setPanelEnabled(labelsAndFields_art, true);
			 
			 habilita_datosPedidos(true,false,false,false,
						 false,true, false);
		
			 descripcionTF.setFont(new Font("Arial", Font.BOLD, 14));
			 
			 JPanel pDescripcion = new JPanel();
			 JLabel lDescripcion = new JLabel();
			 lDescripcion.setText("Descripcion");
			 lDescripcion.setFont(new Font("Arial", Font.BOLD, 16));
			 pDescripcion.add(lDescripcion);
			 pDescripcion.add(descripcionTF);
			 
			 this.add(pDescripcion, BorderLayout.PAGE_END);
	}
	
	public void setPanelTitulo(JComponent p, String titulo){
		
		Border borde0 = BorderFactory.createTitledBorder(null, titulo, 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,18), Color.BLACK);
    			p.setBorder(borde0);
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
        JPanel panel = new JPanel();
        
        Font fuente_titulo = new Font("Arial", Font.BOLD, 14);
  
 
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
        	
        	label.setFont(new Font("Arial", Font.BOLD, 16));
            yLabelGroup.addComponent(label);
        }
        
        for (Component field : fields) {
        	field.setFont(new Font("Arial", Font.BOLD, 16));
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		_busqueda_entities.setPanel("vista_gestion_articulo");	
		
		if(e.getSource()==crear_articulo){
			
			VistaNewObjetoVenta vnov = new VistaNewObjetoVenta(/*this, "Nuevo artículo"*/);
			VistaPremiosComisiones vista_premios_comisiones = new VistaPremiosComisiones();
			 VistaDespacho_diario dp = new VistaDespacho_diario();
			 ControladorCliente _controladorCliente = new ControladorCliente();
				ControladorDomicilioParticular _controladorDomPart = new ControladorDomicilioParticular();
				ControladorDomicilioComercial _controladorDomCom = new ControladorDomicilioComercial();
				ControladorUsuario _controladorUsuario = new ControladorUsuario();
				ControladorZona _controladorZona = new ControladorZona();
				ControladorCajaZona _controladorCajaZona = new ControladorCajaZona();
				LogicaCajaZona logica_caja_zona = new LogicaCajaZona();
				ControladorLocalidad _controladorLocalidad = new ControladorLocalidad();
				ControladorVendedor _controladorVendedor = new ControladorVendedor();
				ControladorPedidos _controladorPedido = new ControladorPedidos();
				ControladorPagoDiario _controladorPagoDiario = new ControladorPagoDiario();
				ControladorArticulo _controladorArticulo = new ControladorArticulo();
				ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
				 ControladorPrestamo _controladorPrestamo = new ControladorPrestamo();
				 LogicaPrestamo logica_prestamo = new LogicaPrestamo();
				ControladorCombo _controladorCombo = new ControladorCombo();
				ControladorRefinanciacion_ex _controladorRef_ex = new ControladorRefinanciacion_ex();
				ControladorRefinanciacion_in _controladorRef_in = new ControladorRefinanciacion_in();
				ControladorMonto_trasladado _controladorMonto_t = new ControladorMonto_trasladado();
				ControladorDespacho_diario cdp = new ControladorDespacho_diario();
				ControladorCobrador controladorCobrador = new ControladorCobrador();
				ControladorJefeCalle cjc = new ControladorJefeCalle();
				ControladorPremios controladorPremio = new ControladorPremios();
				ControladorVentas controladorVentas = new ControladorVentas();
				LogicaVenta logica_ventas = new LogicaVenta();
				LogicaPremio logica_premio = new LogicaPremio();
				LogicaEmpleado logica_empleado = new LogicaEmpleado();
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
				LogicaJefe_calle log_jc = new LogicaJefe_calle();
				LogicaCobrador log_cob = new LogicaCobrador();
				VistaAltaCliente vista_alta_cliente = new VistaAltaCliente();
				VistaBuscarCliente vista_buscar_cliente = new VistaBuscarCliente();
				VistaIngresos vista_ingresos = new VistaIngresos();
				VistaBuscarUsuario _vista_buscar_usuario = new VistaBuscarUsuario();
				BusquedaEntities busqueda_entities = new BusquedaEntities();
				busqueda_entities.setVistaNewObjetoVenta(vnov);
				//Barra_herramientasPedidos bhp = new Barra_herramientasPedidos();
				VistaBuscarPedidos_porClientes vpc = new VistaBuscarPedidos_porClientes();
				VistaRefinanciacion vf = new VistaRefinanciacion();
				VistaRefinanciacion_in vf_in = new VistaRefinanciacion_in();
				VistaMonto_t mt = new VistaMonto_t();
				
				vnov.setControladorArticulo(_controladorArticulo);
				vnov.setBusquedaEntities(busqueda_entities);
				
				vnov.setControladorPrestamo(_controladorPrestamo);
				_controladorPrestamo.setLogicaPrestamo(logica_prestamo);
		
				_vista_buscar_usuario.setControladorUsuario(_controladorUsuario);
				_controladorUsuario.setVistaBuscarUsuario(_vista_buscar_usuario);
				_controladorUsuario.setLogicaUsuario(_logicaUsuario);
				_logicaUsuario.setControladorUsuario(_controladorUsuario);
				
				_logica_cliente.setControladorCliente(_controladorCliente);
				_logica_cliente.setBusquedaEntities(busqueda_entities);
				_logica_cliente.setVistaBuscarCliente(vista_buscar_cliente);
				_logica_dom_part.setControladorDomicilioParticular(_controladorDomPart);
				_logica_dom_part.setVistaBuscarCliente(vista_buscar_cliente);
				_logica_dom_com.setControladorDomicilioComercial(_controladorDomCom);
				_logica_dom_com.setVistaBuscarCliente(vista_buscar_cliente);
				_logica_articulo.setBusquedaEntities(busqueda_entities);
				_logica_articulo.setControladorArticulo(_controladorArticulo);
				_logica_combo.setBusquedaEntities(busqueda_entities);
				
				log_cob.setBusquedaEntities(busqueda_entities);
				log_jc.setBusquedaEntities(busqueda_entities);
				
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
				controladorPremio.setLogicaPremio(logica_premio);
				_controladorCajaZona.setLogicaCaja(logica_caja_zona);
				_controladorZona.setLogicaZona(_logica_zona);
				controladorEmpleado.setLogicaEmpleado(logica_empleado);
				controladorVentas.setLogicaVenta(logica_ventas);
				
				logica_premio.setControladorArticulo(_controladorArticulo);
				logica_premio.setControladorCajaZona(_controladorCajaZona);
				logica_premio.setControladorEmpleado(controladorEmpleado);
				logica_premio.setControladorZona(_controladorZona);
				
				controladorCobrador.setLogicaCobrador(log_cob);
				controladorCobrador.setBusquedaEntities(busqueda_entities);
				cjc.setLogicaJefe_calle(log_jc);
				cjc.setBusquedaEntities(busqueda_entities);
				
				cdp.setLogicaDespacho(log_despacho);
				_logica_pedido.setControladorPedido(_controladorPedido);
				_logica_pedido.setControladorPagoDiario(_controladorPagoDiario);
				_logica_pedido.setBusquedaEntities(busqueda_entities);
				_logica_pedido.setVistaBuscarPedidos_porClientes(vpc);
				_logica_pedido.setControladorDespacho_diario(cdp);
				vista_premios_comisiones.setControladorPedidos(_controladorPedido);
				vista_premios_comisiones.setControladorCombo(_controladorCombo);
				vista_premios_comisiones.setControladorArticulo(_controladorArticulo);
				vista_premios_comisiones.setControladorPrestamo(_controladorPrestamo);
				vista_premios_comisiones.setControladorDespacho_diario(cdp);
				vista_premios_comisiones.setControladorPagoDiario(_controladorPagoDiario);
				vista_premios_comisiones.setControladorCliente(_controladorCliente);
				vista_premios_comisiones.setBusquedaEntities(busqueda_entities);
				vista_premios_comisiones.setControladorCobrador(controladorCobrador);
				vista_premios_comisiones.setControladorJefe_calle(cjc);
				vista_premios_comisiones.setControladorCajaZona(_controladorCajaZona);
				vista_premios_comisiones.setControladorVentas(controladorVentas);
			busqueda_entities.setVistaDespacho_diario(dp);
			busqueda_entities.setControladorCobrador(controladorCobrador);
			busqueda_entities.setControladorJefeCalle(cjc);
			busqueda_entities.setVistaNewObjetoVenta(vnov);
			
			vp.getDesktop().add(vnov);
			vnov.setVisible(true);
			
		}
		
		if(e.getSource() == guardar){
			
			if(_controladorArticulo.modificarArticuloUsuario(nombreTF, descripcionTF,seccionTF)){
				
				ArticulosVO artVO = new ArticulosVO();
				
				artVO.setCodigo(Integer.parseInt(articuloTF.getText()));
				artVO.setNombre(nombreTF.getText());
				artVO.setDescripcion(descripcionTF.getText());
				artVO.setSeccion(Integer.parseInt(seccionTF.getText()));
				
				int res = _controladorArticulo.modificarArticulo(artVO);
				
				if(res > 0){
					
					Mensajes.getMensaje_modificacionExitosa();
					
				}
				else {
					
					Mensajes.getMensaje_modificacion_sinExito();
					 actualizarArticulo(articuloTF.getText());
				}
				
				habilita_datosPedidos(true,false,false,false,
						 false,true, false);
				
			}
			else Mensajes.getMensaje_altaErrorGenerico();
		}
		
		if(e.getSource()==modificar){
			
			 habilita_datosPedidos(false,true,true,true,
					  true,false, true);
			
		}
		
		if(e.getSource() == buscar_articulo){
			articuloTF.requestFocus();
			//_controladorArticulo.buscarArticuloAll();
			_busqueda_entities.setTipoBusqueda(7);
			_busqueda_entities.setTablaModel();
			_controladorArticulo.mostrarBusquedaEntities();
			//articuloTF.requestFocus();
			//comboTF.requestFocus();
			
		}
		
		if(e.getSource()==buscar_seccion){
			
			_controladorArticulo.buscarSeccionAll();
			_controladorArticulo.mostrarBusquedaEntities();
			_busqueda_entities.setTipoBusqueda(22);
		}
		
		if(e.getSource()==cancelar){
			
			 habilita_datosPedidos(true,false,false,false,
						 false,true, false);
			 
			 actualizarArticulo(articuloTF.getText());
		}
		
		
	}
	
	public void actualizarArticulo(String codigo){
		
		if(_controladorArticulo.buscarArticuloUsuario(codigo)!=null){
			
			ArticulosVO _articulosVO = _controladorArticulo.buscarArticuloUsuario(codigo);
			
			ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(_articulosVO.getCodigo());
			
			if(apVO!=null){
				
				nombreTF.setText("Prestamo ");
				descripcionTF.setText("$" + Double.toString(apVO.getMonto()));
			}
			else{
				nombreTF.setText(_articulosVO.getNombre());
				
				descripcionTF.setText(_articulosVO.getDescripcion());
				
			}
			
			SeccionVO sVO = _controladorArticulo.buscarSeccion(Integer.
					toString(_articulosVO.getSeccion()));
			
			if(sVO!=null){
				
				seccionTF.setText(Integer.toString(sVO.getCodigo()));
				lSeccion.setText(sVO.getDescripcion());
				
			}
				
		}
				
		else{
			articuloTF.setText("");
			descripcionTF.setText("");
			nombreTF.setText("");
			
			seccionTF.setText("");
			lSeccion.setText("");
		}
	}

	public int getOpcion_articulo(){
		
		return opcion_articulo;
	}

	public JTextField getArticuloTF(){
		
		return articuloTF;
	}
	
	public JTextField getNombreTF(){
		
		return nombreTF;
	}
	
	public JButton getBuscar_articuloB(){
		
		return buscar_articulo;
	}
	
	public void setControladorCombo(ControladorCombo _controladorCombo){
		
		this._controladorCombo = _controladorCombo;
	}
	
	public void setControladorArticulo(ControladorArticulo _controladorArticulo){
		
		this._controladorArticulo = _controladorArticulo;
	}
	
	public void setControladorPrestamo(ControladorPrestamo _controladorPrestamo){
		
		this._controladorPrestamo = _controladorPrestamo;
	}
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vpc){
		
		this.vpc = vpc;
	}
	public void setVistaPrincipal(VistaPrincipal vp){
		
		this.vp = vp;
	}
	
	public JTextField getSeccionTF(){
		
		return seccionTF;
	}
	
	public JLabel getSeccionL(){
		
		return lSeccion;
	}
	
	
	class FocoListener implements FocusListener{

		private JTextField tf;
		private JLabel l;
		
		public FocoListener(JTextField tf, JLabel l){
			
			this.tf = tf;
			this.l = l;
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			tf.setBackground(new Color(183, 242, 113));
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			/*if(_controladorArticulo.buscarArticuloUsuario(tf.getText())!=null){
				
				ArticulosVO _articulosVO = _controladorArticulo.buscarArticuloUsuario(tf.getText());
				
				l.setText(_articulosVO.getNombre());
			}
					
			else{
				l.setText("");
				tf.setText("");
			}*/
				
			
			tf.setBackground(new Color(255, 255, 255));
		}
			
		
		
	}
	
	public void limpiar(){
		
		
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
	
	public void habilita_datosPedidos(boolean articulo,
			boolean nombre,boolean descripcion,boolean seccion,
			 boolean b_guardar,
			boolean b_modificar, boolean cancelar){
		
		articuloTF.setEnabled(articulo);
		nombreTF.setEnabled(nombre);
		descripcionTF.setEnabled(descripcion);
		setPanelEnabled(pIntegra_seccion, seccion);
		guardar.setEnabled(b_guardar);
		modificar.setEnabled(b_modificar);
		this.cancelar.setEnabled(cancelar);
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
