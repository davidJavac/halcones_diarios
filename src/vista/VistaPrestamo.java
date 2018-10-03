package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
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
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.Blob;

import controlador.ControladorArticulo;
import controlador.ControladorCombo;
import controlador.ControladorPrestamo;
import modelo.LogicaCombo;
import modelo.LogicaPrestamo;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Pedido_articuloVO;
import vista.VistaNewObjetoVenta.FocoListener;

public class VistaPrestamo extends JFrame implements ActionListener{

	private JPanel pBarra;
	private JPanel pDatospersonales;
	private JPanel pDomPart;
	private JPanel pDomCom;
	private JPanel pIntegra;
	private JPanel pNorte;
	private static JComponent panel; 
	private JPanel pPedidos;
	private JPanel pHistorial_pedidos;
	private JPanel pIntegra_pedidos;
	
	private JPanel pPrestamo;

	private JButton salir = new JButton("Salir");
	private JButton guardar = new JButton("Guardar");
	
	private JButton buscar_prestamo = new JButton("...");
	
	private JTextField prestamoTF;

	private int opcion_articulo;
	private int opcion_prestamo;
	
	private JTextField cuotaTF;
	private JTextField diasTF;
	
	private JLabel lPrestamo;
	
	private JLabel titulo_barra;
	
	private ArrayList<JTextField> intTF;
	private ArrayList<JTextField> intPlanTF;
	
	private ControladorArticulo _controladorArticulo;
	private ControladorPrestamo _controladorPrestamo;
	private BusquedaEntities _busqueda_entities;
	private VistaBuscarPedidos_porClientes vpc;
	
	private String cliente;
	
	public VistaPrestamo(){
			
		guardar.addActionListener(this);
		salir.addActionListener(this);
		
		setLayout(new BorderLayout());
		
		pBarra = new JPanel();
		
		pBarra.setLayout(new BorderLayout());
		pBarra.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		pBarra.setBackground(new Color(234, 242, 248));
	
		GridLayout gl = new GridLayout(0,2);
  		
  		prestamoTF = new JTextField(5);
  		prestamoTF.setFont(new Font("Arial", Font.PLAIN, 16));

  		lPrestamo = new JLabel();
  		
  		pPrestamo = new JPanel();
		
  		pPrestamo.setLayout(gl);
  		pPrestamo.add(prestamoTF);
  		buscar_prestamo.setFocusable(false);
  		pPrestamo.add(buscar_prestamo);
  		
  		//prestamoTF.addFocusListener(new FocoListener(prestamoTF, lPrestamo));
  			
		pIntegra = new JPanel();
		pNorte = new JPanel();
		
		pIntegra.setLayout(new BoxLayout(pIntegra, 	BoxLayout.Y_AXIS));
		
		//_controladorZona
		
		buscar_prestamo.addActionListener(this);
  		
		 JComponent[] components = {
              
         		prestamoTF,
         		lPrestamo,
         		diasTF = new JTextField(5),
         		cuotaTF = new JTextField(5)
         			
             };
		
		 String labels [] = {
				 
				 "","","Días", "Cuotas"
				 			 
		 };
		 
		intTF = new ArrayList<JTextField>();
		
		intTF.add(prestamoTF);;
		intTF.add(diasTF);
		intTF.add(cuotaTF);
		
		titulo_barra = new JLabel("");
		
		Font fuenteB = new Font("Verdana", Font.PLAIN, 20);
		
		titulo_barra.setFont(fuenteB);
		
		pBarra.add(titulo_barra);
		
		pNorte.add(guardar);
		//pNorte.add(salir);
		
	    JComponent labelsAndFields = getTwoColumnLayout(labels,components);
	   
	    //JComponent labelsAndFields_lateral = getTwoColumnLayout(labelsL,componentsL);
	    
		//add(labelsAndFields, BorderLayout.CENTER);
		//add(labelsAndFields_lateral, BorderLayout.EAST);
		 
		 pIntegra.add(pBarra);
		 pIntegra.add(pNorte);
		 
		 JPanel pCombo = new JPanel();
		 
		// pCombo.add(labelsAndFields);
		 
		 JScrollPane scr = new JScrollPane(labelsAndFields, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		 
		 add(pIntegra, BorderLayout.NORTH);
		 add(scr, BorderLayout.CENTER);
			
		 KeyboardFocusManager.getCurrentKeyboardFocusManager()
         .addPropertyChangeListener("focusOwner", 
        		 	new PropertyChangeListener() {


        	 	public void propertyChange(PropertyChangeEvent evt) {
        	 			if (!(evt.getNewValue() instanceof JComponent)) {
        	 					return;
        	 			}
        	 			JComponent focused = (JComponent) evt.getNewValue();
        	 			if (labelsAndFields.isAncestorOf(focused)) {
        	 			
        	 				labelsAndFields.scrollRectToVisible(focused.getBounds());
        	 			}
        	 	}

         });
		 
		 addWindowListener( new WindowAdapter() {
			    public void windowOpened( WindowEvent e ){
			        prestamoTF.requestFocus();
			    }
			}); 
		 
	     //add(pIntegra_pedidos, BorderLayout.SOUTH);
	     
		 setSize(300, 220);
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	    
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	     
	     this. addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	               
	            	limpiar();
	                e.getWindow().dispose();
	            }
	           
	        });
	        
	        this.setFocusable(true);
	        this.setResizable(false);
			this.setLocationRelativeTo(null);
		
	}
	
	public void setPanelCliente(String cliente){
		
		Border borde0 = BorderFactory.createTitledBorder(null, cliente, 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,20), Color.BLACK);
    			panel.setBorder(borde0);
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
        panel = new JPanel();
        
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
        	
        	label.setFont(new Font("Arial", Font.PLAIN, 24));
            yLabelGroup.addComponent(label);
        }
        
        for (Component field : fields) {
        	field.setFont(new Font("Arial", Font.PLAIN, 20));
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
		
		_busqueda_entities.setPanel("vista_combo");	
		
		/*if(e.getSource() == buscar_prestamo){
			
			opcion_articulo = 1;
			_controladorArticulo.buscarArticuloAll();
			_controladorArticulo.mostrarBusquedaEntities();
			_busqueda_entities.setTipoBusqueda(7);
		}*/
	
		
		if(e.getSource() == salir){
			
			//vpc.setEnabled(true);
			
			 limpiar();
			
			this.dispose();
		}
		
		if(e.getSource() == guardar){
			
			_controladorPrestamo.nuevoPrestamoUsuario(intTF);
			
			if(!LogicaPrestamo.validarNuevoUsuario){
				
				if(LogicaPrestamo.vacio) Mensajes.getMensaje_vacio();
				else{
					
					if(LogicaPrestamo.excede_caracteres) Mensajes.getMensaje_excede();
					if(LogicaPrestamo.no_entero) Mensajes.getMensaje_no_entero();
				}
			}
			else{
				
				ArticulosPVO _prestamoVO =  new ArticulosPVO();
				
				
				/*_prestamoVO.setNombre("Prestamo");
				_prestamoVO.setDescripcion(Double.parseDouble(prestamoTF.getText()));
				_prestamoVO.setDias(Short.parseShort(diasTF.getText()));
				_prestamoVO.setCuota(Integer.parseInt(cuotaTF.getText()));*/
				
				int res = _controladorPrestamo.nuevoPrestamo(_prestamoVO);
				//int res = _controladorPrestamo.encriptar(_prestamoVO, vpc.key);
				if(res > 0){
					
					Mensajes.getMensaje_altaExitosoGenerico();
					
					limpiar();
					
					vpc.setEnabled(true);
					
					this.dispose();
				}
				else Mensajes.getMensaje_altaErrorGenerico();
				
				
			}
			
			
			
		}
		
	}
	
	public JTextField getPrestamo(){
		
		return prestamoTF;
	}
	
	public JLabel getPrestamoL(){
		
		return lPrestamo;
	}
	

	public int getOpcion_articulo(){
		
		return opcion_articulo;
	}

	
	public void setControladorPrestamo(ControladorPrestamo _controladorPrestamo){
		
		this._controladorPrestamo = _controladorPrestamo;
	}
	
	public void setControladorArticulo(ControladorArticulo _controladorArticulo){
		
		this._controladorArticulo = _controladorArticulo;
	}
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vpc){
		
		this.vpc = vpc;
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
			if(_controladorPrestamo.buscarPrestamoUsuario(tf.getText())!=null){
				
				ArticulosPVO _prestamoVO = _controladorPrestamo.buscarPrestamoUsuario(tf.getText());
				
				//l.setText(Double.toString(_prestamoVO.getDescripcion()));
			}
					
			else{
				l.setText("");
				tf.setText("");
			}
				
			
			tf.setBackground(new Color(255, 255, 255));
		}
			
		
		
	}
	
	public void limpiar(){
		
		for(JTextField tf : intTF) tf.setText("");
		
		lPrestamo.setText("");
		
	}
	
}
