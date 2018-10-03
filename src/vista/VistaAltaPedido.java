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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controlador.ControladorArticulo;
import controlador.ControladorCliente;
import controlador.ControladorCombo;
import controlador.ControladorDomicilioComercial;
import controlador.ControladorDomicilioParticular;
import controlador.ControladorLocalidad;
import controlador.ControladorMonto_trasladado;
import controlador.ControladorObservaciones;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorPlan;
import controlador.ControladorPrestamo;
import controlador.ControladorRefinanciacion_ex;
import controlador.ControladorRefinanciacion_in;
import controlador.ControladorVendedor;
import controlador.ControladorZona;
import controlador.Principal;
import modelo.CifradoAction;
import modelo.DescifradoAction;
import modelo.LogicaPedido;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.Combinacion_diasVO;
import modelo_vo.Despacho_diarioVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.Historial_planesVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.PlanesVO;
import modelo_vo.Refinanciacion_exVO;
import modelo_vo.Refinanciacion_inVO;
import modelo_vo.UsuariosVO;
import vista.VistaBuscarPedidos_porClientes.CustomJToolTip;
import vista.VistaNewObjetoVenta.FocoListener;

public class VistaAltaPedido extends JDialog implements ActionListener{

	private DefaultTableModel tModel;
	private JTable tabla;
	private JScrollPane scr;
	
	private static VistaBuscarCliente vista_buscar_cliente;
	
	private int dni_anterior;
	
	private JPanel pArticulo1;
	
	private JButton salir = new JButton("Salir");
	private JButton cargar = new JButton("Guardar");
	
	private JButton buscar_articulo1 = new JButton("...");
	private JButton buscar_dias_cobranza = new JButton("...");
	
	private JTextField articulo1TF;
	
	private JTextField nombreTF = new JTextField(10);
	private JTextField descripcionTF= new JTextField(20);
	private JTextField fechaTF= new JTextField(10);
	private JTextField acumuladoTF= new JTextField(10);
	private JTextField cuotaTF;
	private JTextField diasTF;
	
	private JLabel lArticulo1;

	private JTextField creditoTF;
	private JTextField dias_cobranzaTF;
	private JPanel pDias_cobranza;
	private JPanel pTabla = new JPanel();
	private int opcion_articulo;

	private JLabel  lDescripcion, lDias_cobranza;
	
	private ArrayList<JLabel> stringTF;
	private ArrayList<JTextField> intTF;

	private int contador_codigos = 0;
	
	private ControladorVendedor _controladorVendedor;
	private ControladorZona _controladorZona;
	private ControladorLocalidad _controladorLocalidad;
	private ControladorPedidos _controladorPedido;
	private ControladorPlan _controladorPlan;
	private ControladorArticulo _controladorArticulo;
	private ControladorPagoDiario _controladorPagoDiario;
	private ControladorCliente _controladorCliente;
	private ControladorRefinanciacion_ex _controladorRef_ex;
	private ControladorRefinanciacion_in _controladorRef_in;
	private ControladorMonto_trasladado _controladorMonto_t;
	private ControladorObservaciones _controladorObservaciones;
	private ControladorPrestamo _controladorPrestamo;
	private BusquedaEntities _busqueda_entities;
	private VistaBuscarPedidos_porClientes vp;
	private static VistaPrincipal _vista_principal;
	private VistaNewObjetoVenta vista_combo;
	private VistaPrestamo vista_prestamo;

	//private Barra_herramientasPedidos bhp2;
	//Barra_herramientasPedidos_interna bhp = new Barra_herramientasPedidos_interna();
	private JButton guardar = new JButton("Guardar")/*{
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };*/;

	private JButton cancelar = new JButton();
	private JButton agregar = new JButton("Agregar");
	private JButton quitar = new JButton("Quitar");
	
	private JCheckBox ch_lunes = new JCheckBox("l");
	private JCheckBox ch_martes = new JCheckBox("m");
	private JCheckBox ch_miercoles = new JCheckBox("M");
	private JCheckBox ch_jueves = new JCheckBox("j");
	private JCheckBox ch_viernes = new JCheckBox("v");
	private JCheckBox ch_sabado = new JCheckBox("s");
	private JCheckBox ch_todos = new JCheckBox("Todos");
	private JPanel pDias_check = new JPanel();
	private ArrayList<JCheckBox> ar_check = new ArrayList<JCheckBox>();
	
	private JComboBox idcCB = new JComboBox();
	private DefaultComboBoxModel cb_model = new DefaultComboBoxModel();
	
	private JPanel pIntegra;
	private JPanel pBarra;
	private JPanel pIdc;
	private JPanel pNorte;
	private JPanel pPlan_dc;
	
	private JLabel titulo_barra;
	
	private String cliente;
	
	private PedidosVO _pedidoVO;
	private UsuariosVO _usuariosVO;
	private ClienteVO aux_cliente;
	
	private boolean[] canEdit= new boolean[]{
            false, false, true, true
    };
	
	public VistaAltaPedido(){
	    //super(vista_buscar_cliente,"", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
	    super(_vista_principal, "Nuevo pedido", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
	     
		 setSize(500, 480);
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	     this.setResizable(false);

		guardar.addActionListener(this);
	
		cancelar.addActionListener(this);
		agregar.addActionListener(this);
		quitar.addActionListener(this);
		
		pBarra = new JPanel();
		
		GridLayout gl = new GridLayout(0,2);
  		
		articulo1TF = new JTextField(5);
  		articulo1TF.setFont(new Font("Arial", Font.PLAIN, 16));

  		lArticulo1 = new JLabel();
  		
  		pArticulo1 = new JPanel();
		
  		pArticulo1.setLayout(gl);
  		pArticulo1.add(articulo1TF);
  		buscar_articulo1.setFocusable(false);
  		pArticulo1.add(buscar_articulo1);
  		
  		
  		articulo1TF.addFocusListener(new FocoListener(articulo1TF));
  		
  		tabla = new JTable();
  		
  		 tModel = new DefaultTableModel(null, getColumnas()){
			 
             public boolean isCellEditable(int rowIndex, int columnIndex) {
                 return canEdit[columnIndex];
             }
 	    };
  		
 	    tabla.setModel(tModel);
 	    tabla.getTableHeader().setReorderingAllowed(false);
 	    tabla.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
	    tabla.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
	    tabla.setFont(new Font("Arial", Font.PLAIN, 12));	
	    
	    centrar_columnas(tabla);
	    
 	    scr = new JScrollPane();
 	    
 	    scr.setViewportView(tabla);
  		scr.setPreferredSize(new Dimension(450,120));
 	    
  		 diasTF = new JTextField(5);
         cuotaTF = new JTextField(5);
         diasTF.setFont(new Font("Arial",Font.BOLD,16));
         cuotaTF.setFont(new Font("Arial",Font.BOLD,16));
           
         creditoTF = new JTextField(15);
         creditoTF.setEnabled(false);
  		
  		diasTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				diasTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
				//actualizarCredito();
				diasTF.setBackground(new Color(255, 255, 255));
				
			}
  			
  			
  		});
  		
  		cuotaTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				cuotaTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
				//actualizarCredito();
				
				cuotaTF.setBackground(new Color(255, 255, 255));
				
			}
  			
  			
  		});
  		
  		articulo1TF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				articulo1TF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
				if(_controladorArticulo.buscarArticuloUsuario(articulo1TF.getText())!=null){
					
					ArticulosVO _articulosVO = _controladorArticulo.buscarArticuloUsuario(articulo1TF.getText());
						
					ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(_articulosVO.getCodigo());
					
				
					if(apVO!=null) lArticulo1.setText("Prestamo $"
							+ Double.toString(apVO.getMonto()));
					else
						lArticulo1.setText(_articulosVO.getNombre());			
					
				}
						
				else{
					lArticulo1.setText("");
					articulo1TF.setText("");
					
				}
					
				articulo1TF.setBackground(new Color(255, 255, 255));
				
			}
  			
  			
  		});
  		
		pDias_cobranza = new JPanel();
  		
		pIntegra = new JPanel();
		pNorte = new JPanel();
		pIdc = new JPanel();
		pPlan_dc = new JPanel();
		//pNorte.setLayout(new BorderLayout());
		pIntegra.setLayout(new BoxLayout(pIntegra, 	BoxLayout.Y_AXIS));
		pNorte.setLayout(new BoxLayout(pNorte, 	BoxLayout.Y_AXIS));
		
		//_controladorZona
		
		cargar.addActionListener(this);
		buscar_dias_cobranza.addActionListener(this);
		buscar_articulo1.addActionListener(this);
		
		lDias_cobranza = new JLabel();
		
		dias_cobranzaTF = new JTextField();
		pDias_cobranza.setLayout(gl);
  		pDias_cobranza.add(dias_cobranzaTF);
  		pDias_cobranza.add(buscar_dias_cobranza);
  		pDias_cobranza.add(lDias_cobranza);
  		
  		dias_cobranzaTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				dias_cobranzaTF.setBackground(new Color(183, 242, 113));
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(_controladorPedido.buscarCombinacion(dias_cobranzaTF.getText())!=null){
					
					Combinacion_diasVO _combinacion_diasVO = _controladorPedido.buscarCombinacion
							(dias_cobranzaTF.getText());
					
					String dias_cobranzaS = "";
					
					if(_combinacion_diasVO.getLunes()) dias_cobranzaS = dias_cobranzaS + " Lunes "; 
					if(_combinacion_diasVO.getMartes()) dias_cobranzaS = dias_cobranzaS + " Martes ";
					if(_combinacion_diasVO.getMiercoles()) dias_cobranzaS = dias_cobranzaS + " Miércoles ";
					if(_combinacion_diasVO.getJueves()) dias_cobranzaS = dias_cobranzaS + " Jueves ";
					if(_combinacion_diasVO.getViernes()) dias_cobranzaS = dias_cobranzaS + " Viernes ";
					if(_combinacion_diasVO.getSabado()) dias_cobranzaS = dias_cobranzaS + " Sábado ";
					
					lDias_cobranza.setText(dias_cobranzaS);
				}
					
					
				else{
					lDias_cobranza.setText("");
					dias_cobranzaTF.setText("");
				}
					
				
				dias_cobranzaTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
        
       JPanel pPlanIntegra = new JPanel();
       
       JLabel diasL = new JLabel("Días");
       JLabel cuotaL = new JLabel("Cuota");
       diasL.setFont(new Font("Arial", Font.BOLD, 12));
       cuotaL.setFont(new Font("Arial", Font.BOLD, 12));
       
       pPlanIntegra.add(diasL);
       pPlanIntegra.add(diasTF);
       pPlanIntegra.add(cuotaL);
       pPlanIntegra.add(cuotaTF);
   
       pPlanIntegra.setBackground(Color.white);

       pPlan_dc.setBackground(Color.white);
       pNorte.setBackground(Color.white);
       pIdc.setBackground(Color.white);
    	
       JLabel lPlan = new JLabel();
       lPlan.setText("Plan");
       JLabel lDias_c= new JLabel();
       lDias_c.setText("Dias cobranza");
       
      /* pPlan_dc.add(lDias_c);
       pPlan_dc.add(pDias_cobranza);
       pPlan_dc.add(lDias_cobranza);*/
       
       ch_lunes.setBackground(Color.white);
       ch_martes.setBackground(Color.white);
       ch_miercoles.setBackground(Color.white);
       ch_jueves.setBackground(Color.white);
       ch_viernes.setBackground(Color.white);
       ch_sabado.setBackground(Color.white);
       ch_todos.setBackground(Color.white);
       
       ar_check.add(ch_lunes);
       ar_check.add(ch_martes);
       ar_check.add(ch_miercoles);
       ar_check.add(ch_jueves);
       ar_check.add(ch_viernes);
       ar_check.add(ch_sabado);
       
       ch_todos.addItemListener(new ItemListener(){

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			
			Object source = e.getItemSelectable();
			if(ch_todos.isSelected()){
				ch_lunes.setSelected(true);
				ch_martes.setSelected(true);
				ch_miercoles.setSelected(true);
				ch_jueves.setSelected(true);
				ch_viernes.setSelected(true);
				ch_sabado.setSelected(true);
				
				
			}
			else{
				
				ch_lunes.setSelected(false);
				ch_martes.setSelected(false);
				ch_miercoles.setSelected(false);
				ch_jueves.setSelected(false);
				ch_viernes.setSelected(false);
				ch_sabado.setSelected(false);
				
			}
		}
    	   
    	  
       });
       
       pPlan_dc.add(ch_lunes);
       pPlan_dc.add(ch_martes);
       pPlan_dc.add(ch_miercoles);
       pPlan_dc.add(ch_jueves);
       pPlan_dc.add(ch_viernes);
       pPlan_dc.add(ch_sabado);
       pPlan_dc.add(ch_todos);
		
       Border borde_idc = BorderFactory.createTitledBorder(null, "Seleccionar IDC", 
    		   TitledBorder.CENTER, TitledBorder.TOP,
    		   new Font("Arial",Font.PLAIN,14), Color.BLACK);
       pIdc.setBorder(borde_idc);
       Border borde_articulo = BorderFactory.createTitledBorder(null, "Seleccionar artículo", 
   	    	TitledBorder.CENTER, TitledBorder.TOP,
   	    	new Font("Arial",Font.PLAIN,14), Color.BLACK);
       pNorte.setBorder(borde_articulo);
       Border borde_plan = BorderFactory.createTitledBorder(null, "Plan de pago", 
    		   TitledBorder.CENTER, TitledBorder.TOP,
    		   new Font("Arial",Font.PLAIN,14), Color.BLACK);
       pPlanIntegra.setBorder(borde_plan);
       Border borde_dc = BorderFactory.createTitledBorder(null, "Días de cobranza", 
    		   TitledBorder.CENTER, TitledBorder.TOP,
    		   new Font("Arial",Font.PLAIN,14), Color.BLACK);
       pPlan_dc.setBorder(borde_dc);
       
		pBarra.add(guardar);
		
		JPanel pNorte1 = new JPanel(); 
		JPanel pNorte2 = new JPanel(); 
		pNorte1.setBackground(Color.white);
		pNorte2.setBackground(Color.white);
		
		pNorte1.add(pArticulo1);
		pNorte1.add(agregar);
		pNorte1.add(quitar);
		pNorte2.add(lArticulo1);
	
		pIdc.add(idcCB);
		
		pNorte.add(pNorte1);
		pNorte.add(pNorte2);
		
		pTabla.setBackground(Color.white);
		
	    pTabla.add(scr);
		
	    pIntegra.add(pBarra);
	    pIntegra.add(pIdc);
	    pIntegra.add(pNorte);
	    pIntegra.add(pTabla);
	    pIntegra.add(pPlanIntegra);
	    pIntegra.add(pPlan_dc);
	    
	    JPanel pMigracion = new JPanel();
	    JLabel fec = new JLabel();
	    JLabel acu = new JLabel();
	    
	    fec.setText("fecha");
	    acu.setText("acumulado");
	    
	    pMigracion.add(fec);
	    pMigracion.add(fechaTF);
	    pMigracion.add(acu);
	    pMigracion.add(acumuladoTF);
	    
	   // pIntegra.add(pMigracion);
	    
	    this.add(pIntegra);
	    
	    limpiar();
	    formato_disable();
		
	}
    
	public void cargar_idcCB(ArrayList<DomicilioComercialVO> ar){
		
		
		for(DomicilioComercialVO dc : ar){
			
			idcCB.addItem(dc.getIdc());
		}
		idcCB.setSelectedIndex(0);
		
	}
	
    public void setPedidosVO(PedidosVO _pedidosVO){
    	
    	this._pedidoVO = _pedidosVO;
    }
    
    public PedidosVO getPedidosVO(){
    	
    	return _pedidoVO;
    }
    
    public void setUsuarioVO(UsuariosVO uVO){
    	
    	this._usuariosVO = uVO;
    }
    
    public UsuariosVO getUsuarioVO(){
    	
    	return this._usuariosVO;
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

	public void setControladorPlan(ControladorPlan _controladorPlan){
		
		this._controladorPlan = _controladorPlan;
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
	
	public void setControladorCliente(ControladorCliente _controladorCliente){
		
		this._controladorCliente = _controladorCliente;
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
	
	public void setVistaCombo(VistaNewObjetoVenta vista_combo){
		
		this.vista_combo = vista_combo;
	}
	
	public void setVistaPrestamo(VistaPrestamo vista_prestamo){
		
		this.vista_prestamo = vista_prestamo;
	}
	
	public JTextField getCombinacion_diasTF(){
		
		return dias_cobranzaTF;
	}
	
	public JLabel getCombinacion_diasL(){
		
		return lDias_cobranza;
	}
	
	public ArrayList<JLabel> getArrayString(){
		
		return stringTF;
	}
	
	public JTextField getCuotaTF(){
		
		return cuotaTF;
	}
	
	public JTextField getDiasTF(){
		
		return diasTF;
	}
	
	public int getDni_anterior(){
		
		return dni_anterior;
	}
	
	public ArrayList<JTextField> getArrayInt(){
		
		return intTF;
	}
	
	public JTextField getArt1(){
		
		return articulo1TF;
	}
	
	public JLabel getArt1L(){
		
		return lArticulo1;
	}
	
	public int getOpcion_articulo(){
		
		return opcion_articulo;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
	    _busqueda_entities.setPanel("vista_alta_pedido");	
		
		if(e.getSource() == buscar_articulo1){
			
			opcion_articulo = 1;
			_busqueda_entities.setControladorArticulo(_controladorArticulo);
			//_controladorArticulo.buscarArticuloAll();
			_busqueda_entities.setTipoBusqueda(7);
			_busqueda_entities.setTablaModel();
			_controladorArticulo.mostrarBusquedaEntities();
		}
		
		if(e.getSource() == buscar_dias_cobranza){
			
			_controladorPedido.buscarCombinacionAll();
			_controladorPedido.mostrarBusquedaEntities();
			_busqueda_entities.setTipoBusqueda(6);
		}	
		
		if(e.getSource()==agregar){
			
			//if(tabla.getRowCount() < 5){
				
				if(articulo1TF.getText().equals("")){
					
					Mensajes.getMensaje_altaErrorGenerico();
				}
				else{
					
					Object [] datos = new Object[2];
					
					ArticulosVO artVO = _controladorArticulo.
							buscarArticulo_porCodigo(Integer.parseInt(articulo1TF.getText()));
					
					if(artVO!=null){
						
						datos [0] = artVO.getCodigo();
						datos [1] = artVO.getNombre();
						/*datos [2] = artVO.getDias();
						datos [3] = artVO.getCuota();*/
						
						tModel.addRow(datos);
					}
					
					
				}
				
			//}
			//else Mensajes.getMensaje_limiteArticulos();
			
			
		}
		
		if(e.getSource()==quitar){
			
			if(tabla.getSelectedRowCount()>0){
				
				int filas = tabla.getSelectedRows().length;
				
				for(int i = 0; i < filas; i++){
					
					tModel.removeRow(tabla.getSelectedRow());
						
				}
				
			}
			else Mensajes.getMensaje_sinFilasSeleccionadas();
		}
		
		if(e.getSource() == guardar){
			
			if(!_controladorPedido.validarPedido_usuario(diasTF, cuotaTF, ar_check, tabla)
					|| tabla.getRowCount() == 0){
				
				Mensajes.getMensaje_altaErrorGenerico();
					
			}
			else{
				
				System.out.println("Sin error, accede a validacion sistema");
				
				PedidosVO _pedidoVO = new PedidosVO();
				
				int idc = Integer.parseInt(String.valueOf(idcCB.getSelectedItem()));
				
				_pedidoVO.setIdc(idc);
				
				_pedidoVO.setDni(vista_buscar_cliente.getClienteVO().getDni());
				
				_pedidoVO.setEstado_pedido("pendiente entrega");
				
				//_pedidoVO.setId_combinacion(Short.parseShort(dias_cobranzaTF.getText()));
				
				Date d = new java.util.Date();
				java.sql.Date fecha_registro = new java.sql.Date(d.getTime());
				java.sql.Time hora_registro = new java.sql.Time(d.getTime());
				
				short i = 1;
				_pedidoVO.setId_usuario(Principal._usuario.getId_usuario());
				_pedidoVO.setFecha_registro(fecha_registro);
				_pedidoVO.setHora_registro(hora_registro);
		
				_pedidoVO.setDias(Integer.parseInt(diasTF.getText()));
				_pedidoVO.setCuota_diaria(Double.parseDouble(cuotaTF.getText()));
			
				int lunes = 0;
				int martes = 0;
				int miercoles = 0;
				int jueves = 0;
				int viernes = 0;
				int sabado = 0;
				
				
				
				int cod1 = 0;
				int cod2 = 0;
				int cod3 = 0;
				int cod4 = 0;
				int cod5 = 0;
				
				int cant_filas = tabla.getRowCount();
				
				if(cant_filas >= 1) cod1 = Integer.parseInt(tabla.getModel().getValueAt(0, 0).toString());
				if(cant_filas >= 2) cod2 = Integer.parseInt(tabla.getModel().getValueAt(1, 0).toString());
				if(cant_filas >= 3) cod3 = Integer.parseInt(tabla.getModel().getValueAt(2, 0).toString());
				if(cant_filas >= 4) cod4 = Integer.parseInt(tabla.getModel().getValueAt(3, 0).toString());
				if(cant_filas == 5) cod5 = Integer.parseInt(tabla.getModel().getValueAt(4, 0).toString());
				
				int id;
				/*res = _controladorPedido.nuevoPedido(_pedidoVO, cod1, cod2, cod3, cod4, cod5,
						lunes,martes,miercoles,jueves,viernes,sabado);*/
				
				id = _controladorPedido.nuevoPedido_mejorado(_pedidoVO);
				
				PlanesVO planVO = new PlanesVO();
				
				planVO.setN_pedido(id);
				
				
				if(id > 0){
				
					try {
						int n_plan = _controladorPlan.insertPlan(planVO);
				
						ArrayList<Pedido_articuloVO> arpa_nuevos = new ArrayList<Pedido_articuloVO>();
						
						String descripcion = "";
						
						for( i = 0; i < tabla.getRowCount(); i++){
							
							Pedido_articuloVO pVO = new Pedido_articuloVO();
							
							pVO.setN_pedido(id);
							pVO.setCodigo_articulo(Integer.
									parseInt(tabla.getModel().getValueAt(i, 0).toString()));
							pVO.setDias(Integer.
									parseInt(tabla.getModel().getValueAt(i, 2).toString()));
							pVO.setCuota(Double.
									parseDouble(tabla.getModel().getValueAt(i, 3).toString()));
							
							arpa_nuevos.add(pVO);
							
							descripcion = descripcion + " " + tabla.getModel().getValueAt(i, 1).toString() + 
									"(" + tabla.getModel().getValueAt(i, 0).toString() + ")";//carga de pedidos
							
						}
						
						Date dia = new Date();
						java.sql.Time t = new java.sql.Time(dia.getTime());
						java.sql.Date fecha = new java.sql.Date(dia.getTime());
						
						Despacho_diarioVO dVO = new Despacho_diarioVO();
						dVO.setN_pedido(id);
						dVO.setEntrega("HALCONES DIARIOS");
						dVO.setNombre(descripcion);
						dVO.setEstado("entregado");
						dVO.setMonto(0);
						dVO.setObservaciones("");
						dVO.setId_usuario((short)1);
						dVO.setFecha_registro(fecha);
						dVO.setHora_registro(t);
						
						//_controladorPedido.insertDespachoMigracion(dVO);
						//_controladorPedido.insertAcumuladoMigracion(id, acumuladoTF);
				
						
						int id_pa = 0;
						
						for(Pedido_articuloVO pVO : arpa_nuevos){
							
							id_pa = _controladorPedido.insertPedido_articulo(pVO.getN_pedido(),
									pVO.getCodigo_articulo(), pVO.getDias(), pVO.getCuota());
							
							System.out.println("id pedido articulo generado " + id_pa);
							
							Historial_planesVO hVO = new Historial_planesVO();
							
							hVO.setId(id_pa);
							hVO.setN_plan(n_plan);
							hVO.setN_pedido(pVO.getN_pedido());
							hVO.setDias(pVO.getDias());
							hVO.setCuota(pVO.getCuota());
							hVO.setCodigo_articulo(pVO.getCodigo_articulo());
							
							_controladorPlan.insertHistorial_planes(hVO);
						
						}
						
						if(ch_lunes.isSelected()){
					
							lunes = 1;
					
							_controladorPedido.insertPedido_dias(id, lunes);
						}
						
						if(ch_martes.isSelected()){
							
							martes = 2;
							_controladorPedido.insertPedido_dias(id, martes);
						}
						
						if(ch_miercoles.isSelected()){
							
							miercoles = 3;
							_controladorPedido.insertPedido_dias(id, miercoles);
						}
				
						if(ch_jueves.isSelected()){
							
							jueves = 4;
							_controladorPedido.insertPedido_dias(id, jueves);

						}
				
						if(ch_viernes.isSelected()){
							
							viernes = 5;
							_controladorPedido.insertPedido_dias(id, viernes);
						}
				
						if(ch_sabado.isSelected()){
							
							sabado = 6;
							_controladorPedido.insertPedido_dias(id, sabado);
						}
				
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					/*ArrayList<Integer> ar = new ArrayList<Integer>();
					
					for(i = 0; i < tabla.getRowCount(); i++){
						
						ar.add(Integer.parseInt(tabla.getModel().getValueAt(i, 0).toString()));
					}
					
					ArrayList<Pedido_articuloVO> arPaVO = _controladorPedido.logicaArticulos_a_pedido(ar);*/
					
					Mensajes.getMensaje_altaExitosoGenerico();
					
					vista_buscar_cliente.getpPedidos().removeAll();
					//vista_buscar_cliente.buscar_cliente();
					
					vista_buscar_cliente.buscarPedido(vista_buscar_cliente.getClienteVO(),
							vista_buscar_cliente.getpPedidos());
					
					vista_buscar_cliente.updateUI();
					
				}
				else Mensajes.getMensaje_altaErrorGenerico();
					
				this.dispose();
			}
				
		}
	
					
	}
	
	public void limpiar(){
	
		diasTF.setText("");
		cuotaTF.setText("");
		creditoTF.setText(""); 
		dias_cobranzaTF.setText("");
		lDias_cobranza.setText("");
		lArticulo1.setText("");
	
	}
	
	private void centrar_columnas(JTable tabla){
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla.getColumnCount(); i ++)
			
			tabla.getColumnModel().getColumn(i).setCellRenderer(cent);
		
	}	
	
	private String [] getColumnas(){
		
		String columna [] = new String []{"Código", "Nombre", "Dias", "Cuota"};
		
		return columna;
	}
	
	public void formato_disable(){
		
		/*for(JTextField tf : getArrayInt()){
			
			tf.setDisabledTextColor(new Color(113,125,126));
			creditoTF.setDisabledTextColor(new Color(113,125,126));
			//tf.setFont(new Font("Arial", Font.BOLD, 24));
		}*/

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
	
	public void actualizarCredito(){
		
		contador_codigos = 0;
		
		for(JTextField tf : intTF){
			
			if(!tf.getText().equals("")){
				
				contador_codigos++;
		
			}
			
		}
		
		/*if(_controladorPedido.validarPlan_pedido(diasTF, cuotaTF)){
		
			double credito = Double.parseDouble(diasTF.getText()) * Double.parseDouble(cuotaTF.getText());
			
			creditoTF.setText(Double.toString(credito));
		
		}
				
		else{

			diasTF.setText("");
			cuotaTF.setText("");
			creditoTF.setText("");
		}*/
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

	class CustomJToolTip extends JToolTip {

	    public CustomJToolTip(JComponent component) {
	        super();
	        setComponent(component);
	        setBackground(Color.white);
	        setForeground(Color.BLACK);
	    }
	}
	
}
