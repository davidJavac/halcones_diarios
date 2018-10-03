package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;

import controlador.ControladorArticulo;
import controlador.ControladorCliente;
import controlador.ControladorCombo;
import controlador.ControladorLocalidad;
import controlador.ControladorPrestamo;
import controlador.ControladorVendedor;
import controlador.ControladorZona;
import controlador.Principal;
import modelo.LogicaCombo;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
//import vista.BusquedaEntities.EnterAction;
import modelo_vo.ArticulosVO;
import modelo_vo.Combinacion_diasVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.SeccionVO;
import vista.VistaNewObjetoVenta.FocoListener;

public class VistaNewObjetoVenta extends JInternalFrame implements ActionListener{

	private JPanel pIntegra;
	private JPanel pNorte;
	private JPanel pOpciones;
	private JPanel pPlan;
	private static JComponent panel; 
	private JPanel pNuevo_articulo;
	private JPanel pNuevo_prestamo;
	private JTextField seccionTF = new JTextField();
	private JButton buscar_seccion = new JButton("...");
	
	private JRadioButton articuloOP = new JRadioButton("Artículo");
	private JRadioButton prestamoOP = new JRadioButton("ArtículoP");
	private ButtonGroup grupoObjetoVenta;
	
	private JButton salir = new JButton("Salir");
	private JButton guardar = new JButton("Guardar");
	
	private int opcion_articulo;
	
	private JTextField diasTF= new JTextField(5);
	private JTextField cuotaTF= new JTextField(5);
	
	private JTextField nombreTF = new JTextField(20);
	private JTextField descripcionTF= new JTextField(20);
	private JTextField monto_prestamoTF= new JTextField(5);
	
	private JLabel lCombinaciones;
	private JLabel lSeccion = new JLabel();
	
	private JLabel titulo_barra;

	private ArrayList<JTextField> intTF_articulo;
	private ArrayList<JTextField> doubleTF_prestamo;
	private ArrayList<JTextField> intPlanTF;
	
	private ControladorArticulo _controladorArticulo;
	private ControladorPrestamo _controladorPrestamo;
	private BusquedaEntities _busqueda_entities;
	private static VistaBuscarPedidos_porClientes vpc;
	
	
	
	private String cliente;
	
	public VistaNewObjetoVenta(/*VistaBuscarArticulo vba, String titulo*/){
			//super(vba,titulo, JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		super("Nuevo artículo", true, true, true, true);
		
		guardar.addActionListener(this);
		salir.addActionListener(this);
		buscar_seccion.addActionListener(this);
		
		setLayout(new BorderLayout());
	
		GridLayout gl = new GridLayout(0,2);
		
		monto_prestamoTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				monto_prestamoTF.setBackground(new Color(183, 242, 113));
			
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				monto_prestamoTF.setBackground(new Color(255,255,255));
				
			}
        	
        	
        });
  		

		pIntegra = new JPanel();
		pNorte = new JPanel();//PanelGraduado(new Color(214, 234, 248), new Color(93, 173, 226));
		
		pIntegra.setBackground(Color.white);
		pIntegra.setLayout(new BoxLayout(pIntegra, 	BoxLayout.Y_AXIS));
		
		//_controladorZona
		Border borde0 = BorderFactory.createTitledBorder(null, "Nuevo", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,20), Color.BLACK);
    			//panel.setBorder(borde0);
    			
		pIntegra.setBorder(BorderFactory.createCompoundBorder(
    					new EmptyBorder(10, 10, 10, 10), borde0));
		
  	
		intTF_articulo = new ArrayList<JTextField>();
		doubleTF_prestamo = new ArrayList<JTextField>();
		intPlanTF = new ArrayList<JTextField>();
		
		grupoObjetoVenta = new ButtonGroup();
		
		grupoObjetoVenta.add(articuloOP);
		grupoObjetoVenta.add(prestamoOP);
		
		pOpciones = new JPanel();
		
		Border borde1 = BorderFactory.createTitledBorder(null, "Seleccionar", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,14), Color.BLACK);
    			//panel.setBorder(borde0);
    			
		pOpciones.setBorder(BorderFactory.createCompoundBorder(
    					new EmptyBorder(10, 10, 10, 10), borde1));
		
		/*pOpciones.add(articuloOP);
		pOpciones.add(comboOP);
		pOpciones.add(prestamoOP);*/
		pOpciones.setOpaque(true);
		
		Border borde_com = BorderFactory.createTitledBorder(null, "", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,14), Color.BLACK);
    			//panel.setBorder(borde0);
    			
		pNorte.setBorder(BorderFactory.createCompoundBorder(
    					new EmptyBorder(10, 10, 10, 10), borde_com));
		
		articuloOP.setContentAreaFilled(false);
		prestamoOP.setContentAreaFilled(false);
		
		articuloOP.setSelected(true);
		
		pNorte.add(articuloOP);
		pNorte.add(prestamoOP);
		//pNorte.add(pOpciones);
		pNorte.add(guardar);
		 
		 pIntegra.add(pNorte);
		 
		 pPlan = new JPanel();
		 
		 Border borde_plan = BorderFactory.createTitledBorder(null, "Plan", 
	    			TitledBorder.CENTER, TitledBorder.TOP,
	    			new Font("Arial",Font.BOLD,14), Color.BLACK);
	    			//panel.setBorder(borde0);
	    			
		 pPlan.setBorder(BorderFactory.createCompoundBorder(
	    					new EmptyBorder(10, 10, 10, 10), borde_plan));
		 
		 pPlan.setBackground(Color.WHITE);
		 
		 JLabel diasL = new JLabel();
		 JLabel cuotaL = new JLabel();
		 
		 diasL.setText("Días");
		 cuotaL.setText("Cuota");
		 
		 diasTF.addFocusListener(new FocoListener(diasTF));
		 cuotaTF.addFocusListener(new FocoListener(cuotaTF));
		 
		 pPlan.add(diasL);
		 pPlan.add(diasTF);
		 pPlan.add(cuotaL);
		 pPlan.add(cuotaTF);
		 
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
		 JLabel seccionL = new JLabel();
		 
		 nombreL.setText("Nombre");
		 descripcionL.setText("Descripcion");
		 seccionL.setText("Sección");
		 
		
		 
		 intTF_articulo.add(nombreTF);
		 intTF_articulo.add(descripcionTF);
		
		 JPanel pSeccion = new JPanel();
		 JPanel pIntegra_seccion = new JPanel();
		 pIntegra_seccion.setLayout(gl);
		 
		 pIntegra_seccion.add(seccionTF);
		 pIntegra_seccion.add(buscar_seccion);
		 pSeccion.add(pIntegra_seccion);
		 pSeccion.add(lSeccion);
		 pSeccion.setBackground(Color.WHITE);
		 
		 JComponent[] components_art = {
	              
				 nombreTF,
				 descripcionTF,
				 pSeccion,
	         			
	             };
			
			 String labels_art [] = {
					 
					 "Nombre", "Descripción", "Sección"
					 
					 
			 };
			
		 JComponent labelsAndFields_art = getTwoColumnLayout(labels_art,components_art);
		 
		 setPanelTitulo(labelsAndFields_art, "Artículo");
		 
		 articuloOP.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(articuloOP.isSelected()){
						
						setPanelEnabled(labelsAndFields_art, true);
						setPanelEnabled(pIntegra_seccion, true);
						setPanelEnabled(pNuevo_prestamo, false);
						
						nombreTF.requestFocus();
					}
				}
				
				
			});
		 
		 prestamoOP.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(prestamoOP.isSelected()){
						
						setPanelEnabled(pNuevo_prestamo, true);
						setPanelEnabled(labelsAndFields_art, false);
						setPanelEnabled(pIntegra_seccion, false);
						
						monto_prestamoTF.requestFocus();
					}
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
		// pIntegra.add(pNuevo_articulo);
		 
		 JPanel pCombo = new JPanel();
		 
		// pCombo.add(labelsAndFields);
		 
		// pIntegra.add(pPlan);
		 
		 pIntegra.add(labelsAndFields_art);
		 
		 pNuevo_prestamo = new JPanel();
		 
		 Border borde3 = BorderFactory.createTitledBorder(null, "ArtículoP", 
	    			TitledBorder.CENTER, TitledBorder.TOP,
	    			new Font("Arial",Font.BOLD,14), Color.BLACK);
	    			//panel.setBorder(borde0);
	    			
			pNuevo_prestamo.setBorder(BorderFactory.createCompoundBorder(
	    					new EmptyBorder(10, 10, 10, 10), borde3));
		 
		 pNuevo_prestamo.setBackground(Color.WHITE);	
			
		 JLabel montoL = new JLabel();
		
		 montoL.setText("Monto");
		 
		 pNuevo_prestamo.add(montoL);
		 pNuevo_prestamo.add(monto_prestamoTF);
		 
		 doubleTF_prestamo.add(monto_prestamoTF);
		 
		 pIntegra.add(pNuevo_prestamo);
		 
		 add(pNorte, BorderLayout.PAGE_START);
		 add(pIntegra, BorderLayout.CENTER);
		 
		 /*addWindowListener( new WindowAdapter() {
			    public void windowOpened( WindowEvent e ){
			       nombreTF.requestFocus();
			    }
			});*/ 
		 
	     //add(pIntegra_pedidos, BorderLayout.SOUTH);
	     
		 setSize(450, 350);
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    // setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
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
			//this.setLocationRelativeTo(null);
			 setClosable(true); 
		        setIconifiable(true); 
		        setMaximizable(true);  
	     
			 setPanelEnabled(labelsAndFields_art, true);
			 setPanelEnabled(pNuevo_prestamo, false);
		
	}
	
	public void setPanelTitulo(JComponent p, String titulo){
		
		Border borde0 = BorderFactory.createTitledBorder(null, titulo, 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,14), Color.BLACK);
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
        	
        	label.setFont(new Font("Arial", Font.BOLD, 12));
            yLabelGroup.addComponent(label);
        }
        
        for (Component field : fields) {
        	field.setFont(new Font("Arial", Font.BOLD, 12));
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
		
		_busqueda_entities.setPanel("vista_nuevo_objeto_venta");	
		
		if(e.getSource()==buscar_seccion){
			
			_controladorArticulo.buscarSeccionAll();
			_controladorArticulo.mostrarBusquedaEntities();
			_busqueda_entities.setTipoBusqueda(22);
		}
		
		if(e.getSource() == salir){
			
			//vpc.setEnabled(true);
			
			 limpiar();
			
			this.dispose();
		}
		
		if(e.getSource() == guardar){
			
			boolean validar = true;
			double monto;
			
			if(articuloOP.isSelected()){
				
				if(!_controladorArticulo.nuevoArticuloUsuario(intTF_articulo, seccionTF))
					 validar = false;
			}
			
			if(prestamoOP.isSelected()){
				
				if(!_controladorPrestamo.nuevoPrestamoUsuario(doubleTF_prestamo)){
				
					 validar = false;
				}
			}
			
			if(validar){
				
				Pedido_articuloVO _comboVO =  new Pedido_articuloVO();
				
				ArticulosVO artVO = new ArticulosVO();
				
				int res = 0;	
					
				if(articuloOP.isSelected()){
					
					artVO.setNombre(nombreTF.getText());
					artVO.setDescripcion(descripcionTF.getText());
					artVO.setSeccion(Integer.parseInt(seccionTF.getText()));
					
					if(artVO.getSeccion()==1) artVO.setCodigo_prefijo("C");
					if(artVO.getSeccion()==2) artVO.setCodigo_prefijo("H");
					
					monto = 0;
					
					res = _controladorArticulo.insertNuevo_articulo("articulo",
							artVO.getNombre(),artVO.getDescripcion(),artVO.getSeccion(), monto);
							 
				}
					
				if(prestamoOP.isSelected()){
					
					artVO.setNombre("ArticuloP");
					artVO.setDescripcion("ArticuloP");
					artVO.setSeccion(2);

					artVO.setCodigo_prefijo("P");
					
					 monto = Double.parseDouble(monto_prestamoTF.getText());
					
					res = _controladorArticulo.insertNuevo_articulo("articulop",
							artVO.getNombre(),artVO.getDescripcion(),artVO.getSeccion(), monto);
				}
				
				if(res >= 0){
					
					Mensajes.getMensaje_altaExitosoGenerico();
					
					limpiar();
					
					this.dispose();
				}
				else Mensajes.getMensaje_altaErrorGenerico();
				
				
			}
			else Mensajes.getMensaje_altaErrorGenerico();
			
			
		}
		
	}

	public int getOpcion_articulo(){
		
		return opcion_articulo;
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
	
	public JTextField getSeccionTF(){
		
		return seccionTF;
	}
	
	public JLabel getSeccionL(){
		
		return lSeccion;
	}
	
	
	class FocoListener implements FocusListener{

		private JTextField tf;
		
		public FocoListener(JTextField tf){
			
			this.tf = tf;
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			tf.setBackground(new Color(183, 242, 113));
			
		}

		@Override
		public void focusLost(FocusEvent e) {
		
			
			tf.setBackground(new Color(255, 255, 255));
		}
				
	}
	
	public void limpiar(){
		
		for(JTextField tf : intTF_articulo) tf.setText("");
		for(JTextField tf : doubleTF_prestamo) tf.setText("");
		for(JTextField tf : intPlanTF) tf.setText("");
		
		monto_prestamoTF.setText("");
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
    
}
