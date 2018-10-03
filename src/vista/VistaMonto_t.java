package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controlador.ControladorMonto_trasladado;
import controlador.ControladorPedidos;
import controlador.ControladorRefinanciacion_in;
import controlador.Principal;
import modelo.LogicaMonto_trasladado;
import modelo.LogicaRefinanciacion_in;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.Monto_trasladadoVO;
import modelo_vo.PedidosVO;
import modelo_vo.Refinanciacion_inVO;

public class VistaMonto_t extends JInternalFrame implements ActionListener{

private JPanel pRef, pSur;
	
	private JTextField pedido, monto;
	private JTextArea observaciones;
	private JLabel cuotaL, diasL;
	private JButton salir = new JButton("Salir");
	private JButton guardar = new JButton("Guardar");
	private ArrayList<JTextField> ar = new ArrayList<JTextField>();
	
	private VistaBuscarPedidos_porClientes vpc;
	private VistaBuscarCliente vista_buscar_cliente;
	private ControladorRefinanciacion_in _controladorRef_in;
	private LogicaRefinanciacion_in _logica_ref_in;
	private JButton b;
	private JButton buscar_pedido = new JButton("...");
	private JPanel pPedido;
	
	private ControladorPedidos _controladorPedido;
	private ControladorMonto_trasladado _controladorMonto_t;
	private LogicaMonto_trasladado _logicaMonto_t;
	private BusquedaEntities _busqueda_entities;
	
	private static VistaPrincipal vp;
	
	private JScrollPane scr;
	
    public VistaMonto_t(){
    	//super(vp, "Monto trasladado", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
    	//b= vpc.getJButtonRef_ex();
    	super("Monto trasladado", true, true ,true, true);
    	salir.addActionListener(this);
    	guardar.addActionListener(this);
    	buscar_pedido.addActionListener(this);
    	
    	this.setLayout(new BorderLayout());
        this.setFocusable(true);
        this.setResizable(false);
        setClosable(true); 
        setIconifiable(true); 
        setMaximizable(true); 
    	this.setSize(300, 300);
    	//this.setUndecorated(true);
    	//getRootPane().setWindowDecorationStyle(JRootPane.NONE);
    	/*this.setLocation(b.getLocationOnScreen().x,
    			(b.getLocationOnScreen().y + b.getHeight()));*/
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    	
    	GridLayout gl = new GridLayout(0,2);
    	
    	pRef = new JPanel();
    	pSur = new JPanel();
    	
    	pPedido = new JPanel();
    	
    	JPanel pAux = new JPanel();
    	JLabel lAux = new JLabel(" ");
    	//lAux.setSize(1	, 1);
    	
    	pedido = new JTextField(3);
    	monto = new JTextField(3);
    	observaciones = new JTextArea(5,10);
    	observaciones.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	observaciones.setLineWrap(true);
    	
    	pedido.setBackground(new Color(183, 242, 113));
    	
    	pedido.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				pedido.setBackground(new Color(183, 242, 113));
			
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				pedido.setBackground(new Color(255,255,255));
				
			}
     	
     	
    	});
    	
    	scr = new JScrollPane(observaciones, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
    			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	
    	pPedido.setLayout(gl);
    	
    	pAux.setLayout(gl);
    	pAux.add(buscar_pedido);
    	pAux.add(lAux);
    	
    	
    	pPedido.add(pedido);
    	pPedido.add(buscar_pedido);
    
    	ar.add(pedido);
    	ar.add(monto);
    	
    	JComponent[] components = {
                
    			pPedido,
               monto,
               scr
         		
         	
             };
		
		 String labels [] = {
				 
				  "de pedido...", "Monto", "Observaciones"
		 };
    	
    	JComponent labelsAndFields = getTwoColumnLayout(labels,components);
    	
    	pSur.add(guardar);
    	pSur.add(salir);
    	
    	this.add(labelsAndFields, BorderLayout.CENTER);
    	this.add(pSur, BorderLayout.SOUTH);   
    
    	 /*this. addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	               
	            	limpiar();
	                e.getWindow().dispose();
	            }
	           
	        });*/
	        
			//this.setLocationRelativeTo(null);
    	
    }

    public void setLocacion(JButton b){
    	
    	this.b = b;
    	
    	this.setLocation(b.getLocationOnScreen().x/* - textField.getWidth()*/,
    			(b.getLocationOnScreen().y + b.getHeight()/* + textField.getHeight()*/));
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		_busqueda_entities.setPanel("vista_monto");	
		
		if(e.getSource() == salir){
			
			//vpc.setEnabled(true);
			
			this.dispose();
		}
		
		if(e.getSource() == buscar_pedido){
			
			//his.setEnabled(false);
			
			_controladorPedido.buscarPedidosAll_porCliente(vista_buscar_cliente.getClienteVO().getDni());
			_controladorPedido.mostrarBusquedaEntities_pedidos_porCliente();
			_busqueda_entities.setTipoBusqueda(9);
		}
		
		if(e.getSource() == guardar){
			
				_controladorMonto_t.altaMonto_t_usuario(ar, observaciones);
				
				if(LogicaMonto_trasladado.validarNuevoMt_Usuario){
					
					
					PedidosVO _p_orVO = _controladorPedido.buscarPedido_porNpedido
							(Integer.parseInt(pedido.getText()));
					PedidosVO _p_destVO = _controladorPedido.buscarPedido_porNpedido
							(vpc.getPedidosVO().getN_pedido());
					
					if(_p_orVO!=null && _p_orVO.getN_pedido() != _p_destVO.getN_pedido()){
						
						if(_controladorPedido.verificar_facturacion(_p_orVO, Double.parseDouble(monto.getText()))){
							
							Monto_trasladadoVO mtVO = new Monto_trasladadoVO();
							
							mtVO.setN_pedido_origen(Integer.parseInt(pedido.getText()));
							mtVO.setN_pedido_destino(vpc.getPedidosVO().getN_pedido());
							mtVO.setMonto(Double.parseDouble(monto.getText()));
							mtVO.setObservaciones(observaciones.getText());
							mtVO.setEstado("activo");
							mtVO.setId_usuario(Principal._usuario.getId_usuario());
							
							Date d = new java.util.Date();
							java.sql.Date fecha_registro = new java.sql.Date(d.getTime());
							java.sql.Time hora_registro = new java.sql.Time(d.getTime());
							
							mtVO.setFecha(fecha_registro);
							mtVO.setHora(hora_registro);
							
							if(_controladorMonto_t.comprobar_monto_t(mtVO)){
							
								Mensajes.getMensaje_altaMontoError();
								
							}	
							else{
								
								System.out.println("Pedido antes de insert" + vpc.getPedidosVO().getN_pedido());
								int res_insert = _controladorMonto_t.nuevoMonto(mtVO);
								
								if(res_insert > 0){
									
								
										_controladorPedido.logicaGeneral(vpc.getPedidosVO());
										
										vpc.mostrarPedido(vpc.getPedidosVO());
										//vpc.visualizarNuevoArticulo(articuloVO);
									
									_controladorPedido.mostrarDetalleMonto(_p_destVO);
									
									
									//vpc.setEnabled(true);
									this.dispose();
									
									Mensajes.getMensaje_altaExitosoGenerico();
									
									
								}
								else Mensajes.getMensaje_altaErrorGenerico();
								
							}
									
						}
						else Mensajes.getMensaje_montoError();
						
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
        
        Border borde0 = BorderFactory.createTitledBorder(null, "Trasladar monto", 
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
    
    public JTextField getPedidoTF(){
    	
    	return pedido;
    }
    
    public JTextField getMontoTF(){
    	
    	return monto;
    }
    
    public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vpc){
    	
    	this.vpc = vpc;
    }
    
    public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
    	
    	this.vista_buscar_cliente = vista_buscar_cliente;
    }
    public void setVistaPrincipal(VistaPrincipal vp){
    	
    	this.vp = vp;
    }
    
    public void setControladorRefinanciacion_in(ControladorRefinanciacion_in _controladorRef_ex){
    	
    	this._controladorRef_in = _controladorRef_ex;
    }
    
    public void setLogicaRefinanciacion_in(LogicaRefinanciacion_in _logica_ref_in){
    	
    	this._logica_ref_in = _logica_ref_in;
    }
    
    public void setControladorMonto_trasladado(ControladorMonto_trasladado _controladorMonto_t){
    	
    	this._controladorMonto_t = _controladorMonto_t;
    }
    
    public void setLogicaMonto_trasladado(LogicaMonto_trasladado _logicaMonto_t){
    	
    	this._logicaMonto_t = _logicaMonto_t;
    }
    
    public void setJButtonRef(JButton ref_in){
    	
    	this.b = ref_in;
    }
    
    public void setControladorPedidos(ControladorPedidos _controladorPedido){
    	
    	this._controladorPedido = _controladorPedido;
    }
    
    public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
    	
    	this._busqueda_entities = _busqueda_entities;
    }
    
    public void limpiar(){
    	
    	for(JTextField tf : ar){
    		
    		tf.setText("");
    	}
    	
    	observaciones.setText("");
    }
	
}
