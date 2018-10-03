package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.omg.Messaging.SyncScopeHelper;

import controlador.ControladorArticulo;
import controlador.ControladorCobrador;
import controlador.ControladorEmpleado;
import controlador.ControladorLocalidad;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorProveedores;
import controlador.ControladorUsuario;
import controlador.ControladorVendedor;
import controlador.ControladorVendedor_ph;
import controlador.ControladorZona;
import modelo.LogicaEmpleado;
import modelo.LogicaVendedor;
import modelo.LogicaVendedor_ph;
import modelo.Mensajes;
import modelo_vo.CobradorVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.ProveedoresVO;
import modelo_vo.VendedorVO;
import modelo_vo.Vendedores_padre_hijoVO;
import vista.VistaGestionEmpleados.CustomJToolTip;

public class VistaGestionProveedores extends JInternalFrame implements ActionListener{

	private JScrollPane scr;
	private JComponent panel = new PanelGraduado(new Color(235, 245, 251), new Color(214, 234, 248)  );
	private JTextField idTF  = new JTextField(5);
	private JTextField nombreTF= new JTextField(15);
	private JTextField razon_socialTF= new JTextField(15);
	private JTextField domicilioTF= new JTextField(15);
	private JTextField ciudadTF= new JTextField(15);
	private JTextField cuitTF= new JTextField(15);
	private JTextField telefonoTF= new JTextField(15);
	private JTextField emailTF= new JTextField(15);

	private JDatePickerImpl fecha_alta;
	
	JComponent labelsAndFields;
	
	private ArrayList<JTextField> ar_provTF;

	private ArrayList<JDatePickerImpl> array_datepicker;

	private JButton buscar_proveedor = new JButton("...");
	
	private ControladorProveedores _controladorProveedor;
	private ControladorLocalidad _controladorLocalidad;
	private ControladorPedidos _controladorPedido;
	private ControladorArticulo _controladorArticulo;
	private ControladorUsuario _controladorUsuario;
	private ControladorEmpleado controladorEmpleado;
	private BusquedaEntities _busqueda_entities;
	private static  VistaPrincipal _vista_principal;

	private boolean editar = false;
	private boolean guardar_bool = false;
	private ProveedoresVO proveedorVO;
	
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
    
    private JButton nuevo_proveedor = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
    
	private JPanel pIntegra;
	
	UtilDateModel modelFalta = new UtilDateModel();
	/*Barra_herramientasPedidos bhp2 = new Barra_herramientasPedidos(guardar, modificar, salir, ref_in, ref_ex,
			cancelar, anular);*/
	
	public VistaGestionProveedores(){
		//super(_vista_principal, "Gestión de proveedores", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
	     //add(pIntegra_pedidos, BorderLayout.SOUTH);
	     super("Gestión de proveedores", true, true, true, true);
		 setSize(500, 450);
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     //setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	     this.setResizable(false);
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	     this.setFocusable(true);
	        setClosable(true); 
	        setIconifiable(true); 
	        setMaximizable(true); 
	     
	     buscar.addActionListener(this);
		modificar.addActionListener(this);
		guardar.addActionListener(this);
		//anular.addActionListener(this);
		
		cancelar.addActionListener(this);
		nuevo.addActionListener(this);
		nuevo_proveedor.addActionListener(this);
		buscar_proveedor.addActionListener(this);
		
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
  		
		pIntegra = new JPanel();
		pIntegra.setLayout(new BoxLayout(pIntegra, 	BoxLayout.Y_AXIS));
  	
  		Properties p = new Properties();
  		p.put("text.today", "Today");
  		p.put("text.month", "Month");
  		p.put("text.year", "Year");
        JDatePanelImpl datePanelFalta = new JDatePanelImpl(modelFalta, p);
        fecha_alta = new JDatePickerImpl(datePanelFalta, new DateLabelFormatter());
        
        JPanel pId = new JPanel();
        pId.setLayout(gl);
        
        pId.add(idTF);
        pId.add(this.buscar_proveedor);
        pId.setOpaque(false);
        
   
        
       JComponent[] components = {
               
               pId,
               nombreTF,
               razon_socialTF,
               domicilioTF,
               ciudadTF,
               cuitTF,
               telefonoTF,
               emailTF,
               fecha_alta,
        		
            };
		
		 String labels [] = {
				 
				 "ID", "Nombre", "Razón social",
					"Domicilio",  "Ciudad","Cuit", "Teléfono", "Email",
					 "Fecha alta"
		 };
	
		 JFormattedTextField fa = fecha_alta.getJFormattedTextField();
		 fa.setFont(new Font("Arial", Font.PLAIN, 18));
	
		ar_provTF = new ArrayList<JTextField>();
		
		
		ar_provTF.add(idTF);
		ar_provTF.add(nombreTF);
		ar_provTF.add(razon_socialTF);
		ar_provTF.add(domicilioTF);
		ar_provTF.add(ciudadTF);
		ar_provTF.add(cuitTF);
		ar_provTF.add(telefonoTF);
		ar_provTF.add(emailTF);
		ar_provTF.add(fecha_alta.getJFormattedTextField());

		
		
		
		
		array_datepicker = new ArrayList<JDatePickerImpl>();
		array_datepicker.add(fecha_alta);

		Container contentPane = this.getContentPane();
		
		BH_proveedores bh = new BH_proveedores(guardar, modificar,cancelar,buscar, nuevo, nuevo_proveedor);
		
	    contentPane.add(bh, BorderLayout.NORTH);
	  
	    setPanel("Gestion de proveedores");
	    
		labelsAndFields = getTwoColumnLayout(labels,components, "Gestion de proveedores");
		
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
 
    
    public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
    
    
    public void setVistaPrincipal(VistaPrincipal _vista_principal){
		
		this._vista_principal = _vista_principal;
	}

	public void setControladorProveedores(ControladorProveedores _controladorProveedor){
		
		this._controladorProveedor = _controladorProveedor;
	}

	public void setControladorLocalidad(ControladorLocalidad _controladorLocalidad){
		
		this._controladorLocalidad = _controladorLocalidad;
	}
	
	public void setControladorPedido(ControladorPedidos _controladorPedido){
		
		this._controladorPedido = _controladorPedido;
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
	
	public ArrayList<JTextField> getArrayProveedores(){
		
		return ar_provTF;
	}
	
	public ArrayList<JDatePickerImpl> getArrayDatePicker(){
		
		return array_datepicker;
	}
	
	public JTextField getProveedorTF(){
		
		return idTF;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
	    _busqueda_entities.setPanel("vista_gestion_empleado");	
	    
	    if(e.getSource()==buscar){
	    	
	    	editar = false;
	    	guardar_bool = false;
	    	
	    	ProveedoresVO pVO = _controladorProveedor.buscarProveedorUsuario(idTF.getText());
	    	
	    
	    	if(pVO!=null){
	    		
	    		proveedorVO = pVO;
	    		
	    		habilita_datosProveedor(false,false, false,false,false, false, false, false, false,
						false,false, 
						true,false, true, true, true);
	    		
	    	
	    		
	    		iniciar(pVO);
	    		
	    	}
	    	else Mensajes.getMensaje_empleadoNoExiste();
	    }
	    
		if(e.getSource() == buscar_proveedor){
			
			_busqueda_entities.setPanel("vista_gestion_proveedores");
			
			idTF.requestFocus();
			_controladorProveedor.buscarProveedorAll();
			_controladorProveedor.mostrarBusquedaEntities("Busqueda de proveedor");
			
			//articuloTF.requestFocus();
			//comboTF.requestFocus();
			
		}
		
		if(e.getSource()==nuevo){
			
			editar = false;
			guardar_bool = false;
			limpiar();
		}
		
		if(e.getSource()==nuevo_proveedor){
			
			editar = false;
			guardar_bool = true;
			limpiar();

			habilita_datosProveedor(false,false, true,true,true, true, true, true, true,
					true,true, 
					false,true, false, false, false);
			
		}
		
		if(e.getSource() == modificar){
			
			editar = true;
			guardar_bool = false;
			
			habilita_datosProveedor(false,false, true,true,true, true, true, true, true,
					true,true, 
					false,true, false, false, false);
		
			idTF.requestFocus();
		}
		
		if(e.getSource() == guardar){
			
			if(_controladorProveedor.modificar_altaProveedorUsuario(ar_provTF, editar)){
				
					
					int res = 0;
					
					//if(puestoCB.getSelectedIndex()==0){
						
						ProveedoresVO proveedorVO = new ProveedoresVO();
						
						if(editar)
						
							proveedorVO.setId_proveedores(Short.parseShort(idTF.getText()));
						
						proveedorVO.setNombre(nombreTF.getText());
						proveedorVO.setRazon_social(razon_socialTF.getText());
						proveedorVO.setDomicilio(domicilioTF.getText());
						proveedorVO.setCiudad(ciudadTF.getText());
						proveedorVO.setCuit(cuitTF.getText());
						proveedorVO.setTelefono(telefonoTF.getText());
						proveedorVO.setEmail(emailTF.getText());
						
						String fecha_altaS =fecha_alta.getJFormattedTextField().getText();
						DateFormat format_alta = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
						Date dateFA = new Date();
						try {
							dateFA = format_alta.parse(fecha_altaS);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						java.sql.Date fecha_altaDate = new java.sql.Date(dateFA.getTime());
						
						proveedorVO.setFecha_alta(fecha_altaDate);
						
						CobradorVO cobradorVO = new CobradorVO();
				
						if(editar){
							
							res = _controladorProveedor.modificarProveedor(proveedorVO);
						
						}
						else if(guardar_bool) {
					
							res = _controladorProveedor.nuevoProveedor(proveedorVO);
					
						}
						if(res > 0){
							
							if(guardar_bool){
								
								Mensajes.getMensaje_altaExitosoGenerico();
								limpiar();
			
							}
							else{
								Mensajes.getMensaje_modificacionExitosa();
								
								habilita_datosProveedor(true,true, false,false,false, false, false, false, false,
										false,false, 
										false,false, true, true, true);
								
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
		
		if(e.getSource() == cancelar){
			
			System.out.println("guardar bool " + guardar_bool + " editar " + editar);
			
			if(guardar_bool) limpiar();
			else if(editar){
				
				editar = false;
				
				habilita_datosProveedor(true,true, false,false,false, false, false, false, false,
						false,false, 
						true,false, true, true, true);
				
				if(proveedorVO!=null)
					
					iniciar(proveedorVO);
			}
			
			editar = false;
			guardar_bool = false;
	
			
		}
		
	}
	
	public void limpiar(){
		
		for(JTextField tf : getArrayProveedores()){
			
			tf.setText("");
		}
		
		habilita_datosProveedor(true,true, false,false,false, false, false, false, false,
				false,false, 
				false,false, true, true, true);
	}
	
	public void formato_disable(){
		
		for(JTextField tf : getArrayProveedores()){
			
			tf.setDisabledTextColor(new Color(113,125,126));
			//tf.setFont(new Font("Arial", Font.BOLD, 24));
		}
		
	}
	
	public void setControladorPedidos(ControladorPedidos _controladorPedido){
		
		this._controladorPedido = _controladorPedido;
	}
	
	public void iniciar(ProveedoresVO pVO){
		
		
		formato_disable();
		//idTF.setText(eVO.get);
		
		nombreTF.setText(pVO.getNombre());
		razon_socialTF.setText(pVO.getRazon_social());
		domicilioTF.setText(pVO.getDomicilio());
		ciudadTF.setText(pVO.getCiudad());
		cuitTF.setText(pVO.getCuit());
		telefonoTF.setText(pVO.getTelefono());
		emailTF.setText(pVO.getEmail());
		
		modelFalta.setValue(pVO.getFecha_alta());

		
	}
	
	public void habilita_datosProveedor(boolean id, boolean buscar_proveedor,
			boolean nombre,boolean razon_social,
			boolean domicilio,boolean ciudad,boolean cuit, boolean telefono,boolean email, boolean fecha_alta,
			boolean guardar, 
			boolean modificar, boolean cancelar, boolean buscar,
		    boolean nuevo, boolean nuevo_proveedor){
		
		idTF.setEnabled(id);
		this.buscar_proveedor.setEnabled(buscar_proveedor);
		nombreTF.setEnabled(nombre);
		razon_socialTF.setEnabled(razon_social);
		domicilioTF.setEnabled(domicilio);
		ciudadTF.setEnabled(ciudad);
		cuitTF.setEnabled(cuit);
		telefonoTF.setEnabled(telefono);
		emailTF.setEnabled(email);
		this.fecha_alta.getComponent(1).setEnabled(fecha_alta);
		
		this.guardar.setEnabled(guardar);
		this.modificar.setEnabled(modificar);
		this.cancelar.setEnabled(cancelar);
		this.buscar.setEnabled(buscar);
		this.nuevo.setEnabled(nuevo);
		this.nuevo_proveedor.setEnabled(nuevo_proveedor);
		
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
