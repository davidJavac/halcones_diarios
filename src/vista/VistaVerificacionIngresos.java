package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorArticulo;
import controlador.ControladorCaja;
import controlador.ControladorCajaZona;
import controlador.ControladorCombo;
import controlador.ControladorLocalidad;
import controlador.ControladorMonedas;
import controlador.ControladorMonto_trasladado;
import controlador.ControladorObservaciones;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorPrestamo;
import controlador.ControladorRefinanciacion_ex;
import controlador.ControladorRefinanciacion_in;
import controlador.ControladorVendedor;
import controlador.ControladorZona;
import controlador.Principal;
import modelo.LogicaPedido;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.CajaDiariaTotalVO;
import modelo_vo.Caja_zonasVO;
import modelo_vo.ClienteVO;
import modelo_vo.Combinacion_diasVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.Monedas_ingresosVO;
import modelo_vo.PedidosVO;
import modelo_vo.UsuariosVO;

//public class VistaVerificacionIngresos extends JDialog implements ActionListener{
public class VistaVerificacionIngresos extends JFrame implements ActionListener{	
	
	private String operacion;
	private VistaBuscarCliente vista_buscar_cliente;
	private JTextField _1000TF;
	private JTextField _500TF;
	private JTextField _200TF;
	private JTextField _100TF;
	private JTextField _50TF;
	private JTextField _20TF;
	private JTextField _10TF;
	private JTextField _5TF;
	private JTextField _2TF;
	private JTextField _1TF;
	private JTextField _050TF;
	private JTextField _025TF;
	
	private JLabel totalL;
	private JLabel total_planillaL;
	private JLabel sobranteL;
	private JLabel faltanteL;
	
	private double total;
	private double faltante;
	private double sobrante;
	
	private double total_sistema;
	
	private JPanel pDias_cobranza;

	private JPanel pIntegra_monto;
	private JPanel pMonto;
	
	JComponent labelsAndFields;
	
	private ArrayList<JTextField> stringTF;
	private ArrayList<JTextField> int_doubleTF;
	
	private JButton cargar = new JButton("Guardar");
	
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
	private ControladorCajaZona _controladorCajaZona;
	private ControladorMonedas controladorMonedas;
	private ControladorCaja controladorCaja;
	private BusquedaEntities _busqueda_entities;
	private VistaBuscarPedidos_porClientes vp;
	private static VistaPrincipal _vista_principal;
	private VistaNewObjetoVenta vista_combo;
	private VistaPrestamo vista_prestamo;
	private VistaIngresos vi;
	private VistaCaja vista_caja;

	private JButton guardar = new JButton("Guardar");

	private JButton cancelar = new JButton("Cancelar");
	
	private JPanel pBarra;
	
	private JPanel pIntegra;
	private JPanel pNorte;
	private  JComponent panel;
	
	private JPanel pObservaciones;
	private JTextArea observacionesTA;
	
	private JLabel titulo_barra;
	
	private JScrollPane scr;
	
	public static boolean confirmacion =false;
	
	//public VistaVerificacionIngresos(){
	    //super(_vista_principal, "Verificacion de ingresos", Dialog.ModalityType.APPLICATION_MODAL);
	public VistaVerificacionIngresos(String operacion){
	     
		this.operacion = operacion;
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     setSize(dim.width*35/100, dim.height*90/100);
	     setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	     this.setResizable(false);
	     //this.setTitle("Nuevo pedido");
	     
	     
		guardar.addActionListener(this);
	
		cancelar.addActionListener(this);
		//baja.addActionListener(this);
		
		setLayout(new BorderLayout());
		
		pBarra = new JPanel();
	
		pBarra.setLayout(new BorderLayout());
		pBarra.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		pBarra.setBackground(new Color(234, 242, 248));
		
		GridLayout gl = new GridLayout(0,2);
  		
		pIntegra = new JPanel();
		pNorte = new JPanel();
		//pNorte.setLayout(new BorderLayout());
		pIntegra.setLayout(new BoxLayout(pIntegra, 	BoxLayout.Y_AXIS));
		
		//_controladorZona
		
		cargar.addActionListener(this);
       
	   _1000TF = new JTextField(5);
		_500TF= new JTextField(5);
		_200TF= new JTextField(5);
		_100TF= new JTextField(5);
		_50TF= new JTextField(5);
		_20TF= new JTextField(5);
		_10TF= new JTextField(5);
		_5TF= new JTextField(5);
		_2TF= new JTextField(5);
		_1TF= new JTextField(5);
	    _050TF= new JTextField(5);
		_025TF= new JTextField(5);
		
		
		
		_1000TF.setName("_1000TF");
		_500TF.setName("_500TF");
		_200TF.setName("_200TF");
		_100TF.setName("_100TF");
		_50TF.setName("_50TF");
		_20TF.setName("_20TF");
		_10TF.setName("_10TF");
		_5TF.setName("_5TF");
		_2TF.setName("_2TF");
		_1TF.setName("_1TF");
	    _050TF.setName("_050TF");
		_025TF.setName("_025TF");
		
		//_1000TF.setBackground(new Color(183, 242, 113));
		
		totalL = new JLabel();
		total_planillaL = new JLabel();
		sobranteL = new JLabel();
		faltanteL = new JLabel();
  
		
		//if(operacion.equals("caja_zona")){
			
			JComponent[] components1 = {
		               
		    		   _1000TF,
		    			_500TF,
		    			_200TF,
		    			_100TF,
		    			_50TF,
		    			_20TF,
		    			_10TF,
		    			_5TF,
		    			_2TF,
		    			_1TF,
		    		    _050TF,
		    			_025TF,
		        		totalL,
		        		total_planillaL,
		        		sobranteL,
		        		faltanteL
		            };
				
			String []  labels1  = {
						 
						 "$1000", "$500","$200","$100","$50","$20","$10",
						 "$5", "$2","$1","$0.50","$0.25","Total", "Total en sistema", "Sobrante", "Faltante"		
				 };
			
			labelsAndFields = getTwoColumnLayout(labels1,components1, "");
		//}
		/*if(operacion.equals("caja_diaria_total")){
			
			JComponent[] components2= {
		               
		    		   _1000TF,
		    			_500TF,
		    			_200TF,
		    			_100TF,
		    			_50TF,
		    			_20TF,
		    			_10TF,
		    			_5TF,
		    			_2TF,
		    			_1TF,
		    		    _050TF,
		    			_025TF,
		        		totalL
		      
		            };
				
			String [] labels2  = {
						 
						 "$1000", "$500","$200","$100","$50","$20","$10",
						 "$5", "$2","$1","$0.50","$0.25","Total"	
				 };
			
			labelsAndFields = getTwoColumnLayout(labels2,components2, "");
			
		}*/
       
        
		stringTF = new ArrayList<JTextField>();
		
		int_doubleTF = new ArrayList<JTextField>();
		
		int_doubleTF.add(_1000TF);
		int_doubleTF.add(_500TF);
		int_doubleTF.add(_200TF);
		int_doubleTF.add(_100TF);
		int_doubleTF.add(_50TF);
		int_doubleTF.add(_20TF);
		int_doubleTF.add(_10TF);
		int_doubleTF.add(_5TF);
		int_doubleTF.add(_2TF);
		int_doubleTF.add(_1TF);
		int_doubleTF.add(_050TF);
		int_doubleTF.add(_025TF);
		
		Font fuenteB = new Font("Verdana", Font.PLAIN, 20);
		
		titulo_barra = new JLabel("cliente");
		
		titulo_barra.setFont(fuenteB);
		
		pBarra.add(titulo_barra);
		
		pNorte.add(guardar);
		pNorte.add(cancelar);
		
		this.add(pNorte, BorderLayout.NORTH);
		
		Container contentPane = this.getContentPane();
	
	    scr = new JScrollPane(labelsAndFields,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	    		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
	    observacionesTA = new JTextArea(5, 30);
	    observacionesTA.setLineWrap(true);
	    observacionesTA.setFont(new Font("Arial", Font.PLAIN, 14));
	    pObservaciones = new JPanel();
	    
	    Border borde0 = BorderFactory.createTitledBorder(null, "Observaciones", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.PLAIN,16), Color.BLACK);
	    pObservaciones.setBorder(borde0);
	    pObservaciones.add(observacionesTA);
	    
	    this.add(scr, BorderLayout.CENTER);
	    this.add(pObservaciones, BorderLayout.SOUTH);
	    
	    //limpiar();
	    formato_disable();
	    _1000TF.requestFocusInWindow();
	}
	

	public void setPanelCliente(String cliente){
		
		Border borde0 = BorderFactory.createTitledBorder(null, cliente, 
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
					
					total = _controladorPagoDiario.calcular_ingresos(int_doubleTF);
					
					totalL.setText("$" + Double.toString(total));
					
					total_sistema = 0;
					
					if(operacion.equals("caja_zona")){
						
						total_sistema = _controladorPagoDiario.calcular_ingresos_planilla(vi.getTabla());
						total_planillaL.setText("$" + Double.toString(total_sistema));
					}
					if(operacion.equals("caja_diaria_total")){
						
						total_sistema = vista_caja.getSaldo_dia();
						total_planillaL.setText("$" + Double.toString(total_sistema));
						
					}	
						double diferencia = total - total_sistema;
					
						if(diferencia > 0 ){
							
							sobranteL.setText("$" + Double.toString(diferencia));
							sobrante = diferencia;
							faltante = 0;
							sobranteL.setForeground(new Color(231, 76, 60));
							faltanteL.setText("$0");
							faltanteL.setForeground(new Color(39, 174, 96));
						}
						else if(diferencia < 0 )
						{
							faltante = diferencia;
							sobrante = 0;
							faltanteL.setText("$" + Double.toString(diferencia));
							faltanteL.setForeground(new Color(231, 76, 60));
							sobranteL.setForeground(new Color(39, 174, 96));
							sobranteL.setText("$0");
						}
						else{
							sobrante = 0;
							faltante = 0;
							sobranteL.setForeground(new Color(39, 174, 96));
							sobranteL.setText("$0");
							faltanteL.setText("$0");
							faltanteL.setForeground(new Color(39, 174, 96));
						}
					//}
					
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
	
	public void setControladorMonedas(ControladorMonedas _controladorMonedas){
		
		this.controladorMonedas = _controladorMonedas;
	}
	
	public void setControladorCaja(ControladorCaja _controladorCaja){
		
		this.controladorCaja = _controladorCaja;
	}
	
	
	public void setControladorObservaciones(ControladorObservaciones _controladorObservaciones){
		
		this._controladorObservaciones = _controladorObservaciones;
	}
	
	public void setVistaCombo(VistaNewObjetoVenta vista_combo){
		
		this.vista_combo = vista_combo;
	}
	
	public void setVistaCaja(VistaCaja vista_caja){
		
		this.vista_caja = vista_caja;
	}
	
	
	public void setVistaPrestamo(VistaPrestamo vista_prestamo){
		
		this.vista_prestamo = vista_prestamo;
	}
	
	public ArrayList<JTextField> getArrayString(){
		
		return stringTF;
	}
	
	public ArrayList<JTextField> getArrayInt(){
		
		return int_doubleTF;
	}
	
	public JPanel getpIntegra_monto(){
		
		return pIntegra_monto;
	}
	
	public JTextField getMil(){
		
		return _1000TF;
	}
	
	public void limpiar(){
		
		for(JTextField tf : getArrayString()){
			
			tf.setText("");
		}
		
		for(JTextField tf : getArrayInt()){
			
			tf.setText("");
		}
		
		totalL.setText("");
		total_planillaL.setText(""); 
		sobranteL.setText(""); 
		faltanteL.setText("");
		
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

	}
	
	public void setControladorPedidos(ControladorPedidos _controladorPedido){
		
		this._controladorPedido = _controladorPedido;
	}
	
	public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
		
		this.vista_buscar_cliente = vista_buscar_cliente;
	}
	
	public void setVistaBuscarPedidos_porCliente(VistaBuscarPedidos_porClientes vpc){
		
		this.vp = vpc;
	}
	
	public void setControladorCajaZona(ControladorCajaZona _controladorCaja_zona){
		
		this._controladorCajaZona = _controladorCaja_zona;
	}
	
	public void setVistaIngresos(VistaIngresos vi){
		
		this.vi = vi;
	}

	class CustomJToolTip extends JToolTip {

	    public CustomJToolTip(JComponent component) {
	        super();
	        setComponent(component);
	        setBackground(Color.white);
	        setForeground(Color.BLACK);
	    }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==cancelar){
			
			this.dispose();
		}
		
		if(e.getSource()==guardar){
			
			switch(operacion){
			
			case "caja_zona":
				
				if(_controladorCajaZona.ingresoUsuario(int_doubleTF)){
					
					Caja_zonasVO _caja_zonaVO = new Caja_zonasVO();
					Monedas_ingresosVO mVO = new Monedas_ingresosVO();
					double ingreso_monedas = 0;
					
					_caja_zonaVO.set_1000(Short.parseShort(_1000TF.getText()));
					_caja_zonaVO.set_500(Short.parseShort(_500TF.getText()));
					_caja_zonaVO.set_200(Short.parseShort(_200TF.getText()));
					_caja_zonaVO.set_100(Short.parseShort(_100TF.getText()));
					_caja_zonaVO.set_50(Short.parseShort(_50TF.getText()));
					_caja_zonaVO.set_20(Short.parseShort(_20TF.getText()));
					_caja_zonaVO.set_10(Short.parseShort(_10TF.getText()));
					_caja_zonaVO.set_5(Short.parseShort(_5TF.getText()));
					_caja_zonaVO.set_2(Short.parseShort(_2TF.getText()));
					_caja_zonaVO.set_1(Short.parseShort(_1TF.getText()));
					_caja_zonaVO.set_0_50(Short.parseShort(_050TF.getText()));
					_caja_zonaVO.set_0_25(Short.parseShort(_025TF.getText()));
					
					ingreso_monedas = (Short.parseShort(_2TF.getText()) * 2)+
								(Short.parseShort(_1TF.getText()) * 1)+
									(Short.parseShort(_050TF.getText()) * 0.5)+
											(Short.parseShort(_025TF.getText()) * 0.25);
					System.out.println(ingreso_monedas);
					short i = 1;
					_caja_zonaVO.setDetalle("Ingreso de zonas");
					_caja_zonaVO.setId_usuario(Principal._usuario.getId_usuario());
					_caja_zonaVO.setId_zona(vi.getZonaVO().getId_zona());
					
					SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
					
					Date d = null;
					
					try {
						d = f.parse(vi.getFecha_ingreso().getJFormattedTextField().getText());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					java.sql.Date date = new java.sql.Date(d.getTime());
					
					Date dt = new Date();
					java.sql.Time time = new java.sql.Time(dt.getTime());
					
					mVO.setFecha_registro(date);
					mVO.setId_zona(vi.getZonaVO().getId_zona());
					mVO.setMonto_ingreso(ingreso_monedas);
					
					_caja_zonaVO.setFecha_registro(date);
					_caja_zonaVO.setHora_registro(time);
					
					_caja_zonaVO.setIngresos(total_sistema);
					_caja_zonaVO.setIFaltante(faltante);
					_caja_zonaVO.setSobrante(sobrante);
					_caja_zonaVO.setMonto_ideal(this.vi.getMonto_ideal());
					
					if(vi.getMonto_ideal()!=0)
						_caja_zonaVO.setEfectividad(total_sistema*100/this.vi.getMonto_ideal());
					
					_caja_zonaVO.setObservaciones(observacionesTA.getText());
					
					if(_controladorCajaZona.comprobar_duplicados(_caja_zonaVO)){
						
						Mensajes.getMensaje_pagoduplicadoError();
					}
					else{
						
						int res = Mensajes.getMensaje_confirmacion_generico();
						
						if(res == JOptionPane.YES_OPTION){
							
							confirmacion = true;
							vi.cargaPagoDiarioVO(vi.getFecha_ingreso());
							this.dispose();
							int resultado = _controladorCajaZona.ingresarCaja_zona( _caja_zonaVO);
							
							int resultado_monedas = controladorCaja.insertIngreso_monedas(mVO);
							
							if(resultado == 0) Mensajes.getMensaje_ingresoCajazonaError(); 
							else Mensajes.getMensaje_altaExitosoGenerico();
						}
						else confirmacion = false;
					}
		
				}
				else
					Mensajes.getMensaje_altaErrorGenerico();
					
				break;
				
			case"caja_diaria_total":
				
				if(controladorCaja.ingresoCaja_diariaUsuario(int_doubleTF)){
					
					CajaDiariaTotalVO _cajaVO = new CajaDiariaTotalVO();
							
					_cajaVO.set_1000(Short.parseShort(_1000TF.getText()));
					_cajaVO.set_500(Short.parseShort(_500TF.getText()));
					_cajaVO.set_200(Short.parseShort(_200TF.getText()));
					_cajaVO.set_100(Short.parseShort(_100TF.getText()));
					_cajaVO.set_50(Short.parseShort(_50TF.getText()));
					_cajaVO.set_20(Short.parseShort(_20TF.getText()));
					_cajaVO.set_10(Short.parseShort(_10TF.getText()));
					_cajaVO.set_5(Short.parseShort(_5TF.getText()));
					_cajaVO.set_2(Short.parseShort(_2TF.getText()));
					_cajaVO.set_1(Short.parseShort(_1TF.getText()));
					_cajaVO.set_0_50(Short.parseShort(_050TF.getText()));
					_cajaVO.set_0_25(Short.parseShort(_025TF.getText()));
					
					_cajaVO.setId_usuario(Principal._usuario.getId_usuario());
					
					double monedas_ingreso = _cajaVO.get_2()*2 + _cajaVO.get_1() *1 + 
							_cajaVO.get_0_50() *0.5 + _cajaVO.get_0_25()*0.25;
					
					Date d = new Date();
					java.sql.Date date = new java.sql.Date(d.getTime());
					java.sql.Time time = new java.sql.Time(d.getTime());
					
					_cajaVO.setFecha_registro(date);
					_cajaVO.setHora_registro(time);
					
					_cajaVO.setIngresos(vista_caja.getIngresosCajaDiaria());
					_cajaVO.setEgresos(vista_caja.getEgresosCajaDiaria() + monedas_ingreso);
					_cajaVO.setFaltante(faltante);
					_cajaVO.setSobrante(sobrante);
					_cajaVO.setObservaciones(observacionesTA.getText());
					
					if(controladorCaja.comprobar_duplicados(_cajaVO.getFecha_registro())){
						
						Mensajes.getMensaje_pagoduplicadoError();
					}
					else{
						
						int res = Mensajes.getMensaje_confirmacion_generico();
						
						if(res == JOptionPane.YES_OPTION){
							
							confirmacion = true;
							
							vista_caja.setAllPanelEnabled(false);
							
							this.dispose();
							int resultado = controladorCaja.ingresarCajaDiariaTotal( _cajaVO);
							
							if(resultado == 0) Mensajes.getMensaje_ingresoCajazonaError();
							else{
								vista_caja.iniciarResumen(date);
								vista_caja.iniciarC_AltasBajas(date);
								vista_caja.iniciarC_zonas(date);
								vista_caja.iniciarC_despacho(date);
								//vc.iniciarC_prestamos(hoy);
								vista_caja.iniciarC_sueldos(date);
								vista_caja.iniciarC_interna(date);
								vista_caja.iniciarC_proveedores(date);
								vista_caja.iniciarC_monedas(date);
								vista_caja.iniciarResumen(date);
								vista_caja.iniciarC_saldo(date);
								Mensajes.getMensaje_altaExitosoGenerico();
							}
						}
						else confirmacion = false;
					}
		
				}
				else
					Mensajes.getMensaje_altaErrorGenerico();
						
				break;
			}
			
			
		}
		
	}

	
}
