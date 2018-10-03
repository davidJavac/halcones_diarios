package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controlador.ControladorPedidos;
import controlador.ControladorRefinanciacion_ex;
import controlador.ControladorRefinanciacion_in;
import controlador.Principal;
import modelo.LogicaRefinanciacion_ex;
import modelo.LogicaRefinanciacion_in;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.Refinanciacion_exVO;
import modelo_vo.Refinanciacion_inVO;
import modelo_vo.UsuariosVO;

public class VistaRefinanciacion_in extends JInternalFrame implements ActionListener{

	private JPanel pRef, pSur;
	
	private JTextField cuota, dias;
	private JLabel cuotaL, diasL;
	private JButton salir = new JButton("Salir");
	private JButton guardar = new JButton("Guardar");
	private ArrayList<JTextField> ar = new ArrayList<JTextField>();
	
	private  VistaBuscarPedidos_porClientes vpc;
	private VistaBuscarCliente vista_buscar_cliente;
	private ControladorRefinanciacion_in _controladorRef_in;
	private ControladorPedidos _controladorPedido;
	private LogicaRefinanciacion_in _logica_ref_in;
	private JButton b;
	
    public VistaRefinanciacion_in(){
    	//super(null, "Refinanciación interna", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
    	//b= vpc.getJButtonRef_ex();
    	super("Refinanciación interna", true ,true ,true, true);
    	salir.addActionListener(this);
    	guardar.addActionListener(this);
    	
    	this.setLayout(new BorderLayout());
        this.setFocusable(true);
        this.setResizable(false);
        setClosable(true); 
        setIconifiable(true); 
        setMaximizable(true); 
    	this.setSize(250, 250);
    	//this.setUndecorated(true);
    	//getRootPane().setWindowDecorationStyle(JRootPane.NONE);
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
       // this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    	//getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(113,125,126)));
    	
    	pRef = new JPanel();
    	pSur = new JPanel();
    	
    	cuotaL = new JLabel("Cuota");
    	diasL = new JLabel("Días");
    	cuota = new JTextField(5);
    	dias = new JTextField(5);
    	
    	pRef.add(cuotaL);
    	pRef.add(cuota);
    	pRef.add(diasL);
    	pRef.add(dias);
    
    	ar.add(cuota);
    	ar.add(dias);
    	
    	JComponent[] components = {
                
    			dias,
                cuota
         		
         	
             };
		
		 String labels [] = {
				 
				  "Días", "Cuota"
		 };
    	
    	JComponent labelsAndFields = getTwoColumnLayout(labels,components);
    	
    	pSur.add(guardar);
    	//pSur.add(salir);
    	
    	this.add(labelsAndFields, BorderLayout.CENTER);
    	this.add(pSur, BorderLayout.SOUTH);   
    
    	
    }

    public void setLocacion(JButton b){
    	
    	this.b = b;
    	
    	this.setLocation(b.getLocationOnScreen().x/* - textField.getWidth()*/,
    			(b.getLocationOnScreen().y + b.getHeight()/* + textField.getHeight()*/));
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == salir){
			
			vpc.setEnabled(true);
			
			this.dispose();
		}
		
		if(e.getSource() == guardar){
			
			vpc.setRef_in_boolean(true);
				
				_controladorRef_in.altaRefinanciacion_in(ar);
				
				if(LogicaRefinanciacion_in.validarNuevoRef_inUsuario){
						
						Refinanciacion_inVO refVO = new Refinanciacion_inVO();
						
						refVO.setN_pedido(vpc.getPedidosVO().getN_pedido());
						refVO.setDias(Short.parseShort(dias.getText()));
						refVO.setCuota_diaria(Double.parseDouble(cuota.getText()));
						
						int res_insert = _controladorRef_in.nuevaRefinanciacion(refVO);
						
						if(res_insert > 0){
								
								PedidosVO pVO = _controladorPedido.buscarPedido_porNpedido(
										vpc.getPedidosVO().getN_pedido());
								
								_controladorPedido.logicaGeneral(pVO);
								
								vpc.mostrarPedido(pVO);
								
							vpc.setEnabled(true);
							this.dispose();
							
							Mensajes.getMensaje_altaExitosoGenerico();
							
							vpc.getRefinanciacion_inL().setText("Ref.interna");
							
							
						}
						else Mensajes.getMensaje_altaErrorGenerico();
					}
				
			
		}
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
        
        Font fuente_titulo = new Font("Arial", Font.BOLD, 20);
        
        Border borde0 = BorderFactory.createTitledBorder(null, "Refinanciar pedido", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,14), Color.BLACK);
    			//panel.setBorder(borde0);
    			
        panel.setBorder(BorderFactory.createCompoundBorder(
    					new EmptyBorder(10, 10, 10, 10), borde0));
        
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
        	
        	label.setFont(new Font("Arial", Font.PLAIN, 14));
            yLabelGroup.addComponent(label);
        }
        
        for (Component field : fields) {
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
    
    public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vpc){
    	
    	this.vpc = vpc;
    }
    
    public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
    	
    	this.vista_buscar_cliente = vista_buscar_cliente;
    }
    
    public void setControladorRefinanciacion_in(ControladorRefinanciacion_in _controladorRef_ex){
    	
    	this._controladorRef_in = _controladorRef_ex;
    }
    
    public void setControladorPedidos(ControladorPedidos _controladorPedidos){
    	
    	this._controladorPedido = _controladorPedidos;
    }
    
    public void setLogicaRefinanciacion_in(LogicaRefinanciacion_in _logica_ref_in){
    	
    	this._logica_ref_in = _logica_ref_in;
    }
    
    public void setJButtonRef(JButton ref_in){
    	
    	this.b = ref_in;
    }
    
    public void limpiar(){
    	
    	for(JTextField tf : ar){
    		
    		tf.setText("");
    	}
    }
	
}
