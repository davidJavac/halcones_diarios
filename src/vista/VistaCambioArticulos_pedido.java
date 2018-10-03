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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controlador.ControladorArticulo;
import controlador.ControladorCambio_plan;
import controlador.ControladorCliente;
import controlador.ControladorDA;
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
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Cambio_planVO;
import modelo_vo.ClienteVO;
import modelo_vo.Combinacion_diasVO;
import modelo_vo.DAVO;
import modelo_vo.Historial_planesVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.PlanesVO;
import modelo_vo.UsuariosVO;
import vista.VistaAltaPedido.FocoListener;

public class VistaCambioArticulos_pedido extends JInternalFrame implements ActionListener{

	private DefaultTableModel tModel;
	private JTable tabla;
	private JScrollPane scr;
	private JScrollPane scr_historial = new JScrollPane();
	
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
	private JTextField cuotaTF;
	private JTextField diasTF;
	
	private JLabel lArticulo1;

	private JTextField creditoTF;
	private JTextField dias_cobranzaTF;
	private JPanel pDias_cobranza;
	private JPanel pTabla = new JPanel();
	private int opcion_articulo;

	private JLabel  lDescripcion;
	
	private ArrayList<JLabel> stringTF;
	private ArrayList<JTextField> intTF;
	
	private ArrayList<Pedido_articuloVO> arpa_or = new ArrayList<Pedido_articuloVO>();
	private ArrayList<Pedido_articuloVO> arpa_nuevos = new ArrayList<Pedido_articuloVO>();

	private int contador_codigos = 0;
	
	private ControladorVendedor _controladorVendedor;
	private ControladorDA _controladorDA;
	private ControladorZona _controladorZona;
	private ControladorLocalidad _controladorLocalidad;
	private ControladorPedidos _controladorPedido;
	private ControladorPlan _controladorPlan;
	private ControladorArticulo _controladorArticulo;
	private ControladorPagoDiario _controladorPagoDiario;
	private ControladorCliente _controladorCliente;
	private ControladorCambio_plan ccp;
	private ControladorRefinanciacion_ex _controladorRef_ex;
	private ControladorRefinanciacion_in _controladorRef_in;
	private ControladorMonto_trasladado _controladorMonto_t;
	private ControladorObservaciones _controladorObservaciones;
	private ControladorPrestamo _controladorPrestamo;
	private BusquedaEntities _busqueda_entities;
	private static VistaBuscarPedidos_porClientes vp;
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
	
	private JRadioButton da_si = new JRadioButton("Si");
	private JRadioButton da_no = new JRadioButton("No");
	private ButtonGroup grupo_da = new ButtonGroup();
	private JTextField porcentaje_daTF = new JTextField(5);
	
	private JPanel pIntegra;
	private JPanel pBarra;
	private JPanel pNorte;
	private JPanel pDA;
	private JPanel pHistorial_planes = new JPanel();
	
	private JLabel titulo_barra;
	
	private String cliente;
	
	private PedidosVO _pedidoVO;
	private UsuariosVO _usuariosVO;
	private ClienteVO aux_cliente;
	
	private JCheckBox correcionCH = new JCheckBox("Correción");
	
	private boolean[] canEdit= new boolean[]{
            false,false, false, true, true
    };
	
	public VistaCambioArticulos_pedido(){
	    //super(vista_buscar_cliente,"", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
	    //super(vp, "Cambio de articulos", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
	     super("Cambio de artículos", true, true, true ,true);
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     setSize(dim.width*35/100, dim.height*80/100);
	     //setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	     
	     this.setLayout(new GridLayout(2,1));
	     this.setResizable(false);
	        this.setFocusable(true);
	        setClosable(true); 
	        setIconifiable(true); 
	        setMaximizable(true); 
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
  		
  		tabla = new JTable(){
  			
  			/* public void changeSelection( int row, int col, boolean toggle, boolean expand ) {
			        if( col  == 3 || col == 4) { // here you set your own rules
			            super.changeSelection( row, col, toggle, expand );
			        }
			    }
			 
			 public void focusEditedCell()*/
			   {
			     /*  Component c = tabla.getEditorComponent();
			       if (c != null)
			       {
			          c.requestFocusInWindow();
			       }*/
			   }
			
			/*public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
	            {
	                Component c = super.prepareRenderer(renderer, row, column);
	                
	                if(column==tabla.getSelectedColumn() && row ==tabla.getSelectedRow()){
	                	
                         c.setBackground(new Color(183, 242, 113));//Set Background
                         JTextField textField = new JTextField();
                         textField.setFont(new Font("Arial", Font.PLAIN, 12));
                         textField.setBorder(new LineBorder(Color.BLACK));
                         DefaultCellEditor dce = new DefaultCellEditor( textField );
                         tabla.getColumnModel().getColumn(column).setCellEditor(dce);
                        
	                }
	                else{    
	     	           c.setBackground(tabla.getBackground());
	     	       }
	                
	                return c;
	            }*/
  		};
  		
  		 tModel = new DefaultTableModel(null, getColumnas()){
			 
             public boolean isCellEditable(int rowIndex, int columnIndex) {
                 return canEdit[columnIndex];
             }
 	    };
  		
 	   /* canEdit [3]= true;
		canEdit [4]= true;
		tModel.isCellEditable(tabla.getRowCount()-1, 3);
		tModel.isCellEditable(tabla.getRowCount()-1, 4);*/
 	    
 	    tabla.setModel(tModel);
 	    tabla.getTableHeader().setReorderingAllowed(false);
 	   tabla.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
	    tabla.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));
	    tabla.setFont(new Font("Arial", Font.PLAIN, 12));	
	    
	    centrar_columnas(tabla);
	    
 	    scr = new JScrollPane();
 	    
 	    scr.setViewportView(tabla);
  		scr.setPreferredSize(new Dimension(450,120));
  		//pHistorial_planes.setPreferredSize(new Dimension(450,250));
 	    
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
  		
  		porcentaje_daTF.addFocusListener(new FocusListener(){
  			
  			@Override
  			public void focusGained(FocusEvent e) {
  				// TODO Auto-generated method stub
  				porcentaje_daTF.setBackground(new Color(183, 242, 113));
  			}
  			
  			@Override
  			public void focusLost(FocusEvent e) {
  				// TODO Auto-generated method stub
  				
  				//actualizarCredito();
  				
  				porcentaje_daTF.setBackground(new Color(255, 255, 255));
  				
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
		pDA = new JPanel();
		//pNorte.setLayout(new BorderLayout());
		pIntegra.setLayout(new BoxLayout(pIntegra, 	BoxLayout.Y_AXIS));
		pNorte.setLayout(new BoxLayout(pNorte, 	BoxLayout.Y_AXIS));
		pHistorial_planes.setLayout(new BoxLayout(pHistorial_planes, BoxLayout.Y_AXIS));
		
		//_controladorZona
		
		cargar.addActionListener(this);
		buscar_dias_cobranza.addActionListener(this);
		buscar_articulo1.addActionListener(this);
        
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

       pDA.setBackground(Color.white);
       pHistorial_planes.setBackground(Color.white);
       pNorte.setBackground(Color.white);
    	
       JLabel lPlan = new JLabel();
       lPlan.setText("Plan");
		
       Border borde_articulo = BorderFactory.createTitledBorder(null, "Seleccionar artículo", 
   	    	TitledBorder.CENTER, TitledBorder.TOP,
   	    	new Font("Arial",Font.PLAIN,14), Color.BLACK);
       pNorte.setBorder(borde_articulo);
       Border borde_plan = BorderFactory.createTitledBorder(null, "Plan de pago", 
    		   TitledBorder.CENTER, TitledBorder.TOP,
    		   new Font("Arial",Font.PLAIN,14), Color.BLACK);
       pPlanIntegra.setBorder(borde_plan);
       Border borde_dc = BorderFactory.createTitledBorder(null, "Descuento administrativo", 
    		   TitledBorder.CENTER, TitledBorder.TOP,
    		   new Font("Arial",Font.PLAIN,14), Color.BLACK);
       pDA.setBorder(borde_dc);
       Border borde_hp = BorderFactory.createTitledBorder(null, "Historial de planes", 
    		   TitledBorder.CENTER, TitledBorder.TOP,
    		   new Font("Arial",Font.PLAIN,14), Color.BLACK);
       pHistorial_planes.setBorder(borde_hp);
       
		pBarra.add(guardar);
		pBarra.add(correcionCH);
		
		JPanel pNorte1 = new JPanel(); 
		JPanel pNorte2 = new JPanel(); 
		pNorte1.setBackground(Color.white);
		pNorte2.setBackground(Color.white);
		
		pNorte1.add(pArticulo1);
		pNorte1.add(agregar);
		pNorte1.add(quitar);
		pNorte2.add(lArticulo1);
	
		pNorte.add(pNorte1);
		pNorte.add(pNorte2);
		
		pTabla.setBackground(Color.white);
		
	    pTabla.add(scr);
		
	    JLabel lda = new JLabel();
	    lda.setText("Porcentaje");
	    
	    grupo_da.add(da_si);
	    grupo_da.add(da_no);
	    
	    da_si.setBackground(Color.white);
	    da_no.setBackground(Color.white);
	    
	    da_si.setSelected(true);
	    
	    da_si.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(da_si.isSelected()){
					
					porcentaje_daTF.setEnabled(true);
				}
			}
	    	
	    	
	    });
	    da_no.addActionListener(new ActionListener(){
	    	
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		// TODO Auto-generated method stub
	    		if(da_no.isSelected()){
	    			
	    			porcentaje_daTF.setText("");
	    			porcentaje_daTF.setEnabled(false);
	    		}
	    	}
	    	
	    	
	    });
	    
	    correcionCH.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					
					da_no.setSelected(true);
					porcentaje_daTF.setText("");
	    			porcentaje_daTF.setEnabled(false);
				}
				else{
					
					da_si.setSelected(true);
					porcentaje_daTF.setEnabled(true);
					
				}
			}
	    	  	
	    });
	    
	    pDA.add(da_si);
	    pDA.add(da_no);
	    pDA.add(lda);
	    pDA.add(porcentaje_daTF);
	    
	    scr_historial.setViewportView(pHistorial_planes);
	    
	    pIntegra.add(pBarra);
	    pIntegra.add(pNorte);
	    pIntegra.add(pTabla);
	    pIntegra.add(pPlanIntegra);
	    pIntegra.add(pDA);
	   // pIntegra.add(scr_historial);
	    
	    this.add(pIntegra);
	    this.add(scr_historial);
	    
	    limpiar();
	    formato_disable();
		
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
	public void setControladorCambio_plan(ControladorCambio_plan ccp){
		
		this.ccp = ccp;
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
	
	    _busqueda_entities.setPanel("vista_cambiar_articulo_pedido");	
		
		if(e.getSource() == buscar_articulo1){
			
			opcion_articulo = 1;
			_busqueda_entities.setControladorArticulo(_controladorArticulo);
			_controladorArticulo.buscarArticuloAll();
			_controladorArticulo.mostrarBusquedaEntities();
			_busqueda_entities.setTipoBusqueda(7);
		}
		
		if(e.getSource()==agregar){
			
			//if(tabla.getRowCount() < 5){
				
				if(articulo1TF.getText().equals("")){
					
					Mensajes.getMensaje_altaErrorGenerico();
				}
				else{
					
					Object [] datos = new Object[3];
					
					ArticulosVO artVO = _controladorArticulo.
							buscarArticulo_porCodigo(Integer.parseInt(articulo1TF.getText()));
					
					if(artVO!=null){
						datos[0]="";
						datos [1] = artVO.getCodigo();
						datos [2] = artVO.getNombre();
						//datos[2] = artVO.getSeccion();
						tModel.addRow(datos);
					}

					
				}
				
			/*}
			else Mensajes.getMensaje_limiteArticulos();*/
			
			
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
			
			arpa_nuevos.clear();
			
			if(!_controladorPedido.validarCambioArticulo_pedido(diasTF, cuotaTF, da_si, porcentaje_daTF,
					tabla)
					|| tabla.getRowCount() == 0){
				
				Mensajes.getMensaje_altaErrorGenerico();
					
			}
			else{
				
				//ArrayList<Pedido_articuloVO> arpa_nuevosVO = new ArrayList<Pedido_articuloVO>();
				
				for(int i = 0; i < tabla.getRowCount(); i++){
					
					Pedido_articuloVO paVO = new Pedido_articuloVO();
					paVO.setCodigo_articulo(Integer.
							parseInt(tabla.getModel().getValueAt(i, 1).toString()));
					paVO.setDias(Integer.
							parseInt(tabla.getModel().getValueAt(i, 3).toString()));
					paVO.setCuota(Double.
							parseDouble(tabla.getModel().getValueAt(i, 4).toString()));
					arpa_nuevos.add(paVO);
//					ar_cod_nuevos.add();
				}
				
				/*boolean cod_igual = false;
				
				int cont_coincidencias = 0;
				
				ArrayList<Integer> ar_cod_aux_nuevo = new ArrayList<Integer>();
				ArrayList<Integer> ar_cod_aux_or = new ArrayList<Integer>();
				
				if(ar_cod_nuevos.size() == ar_cod_or.size()){
					
					Object[] sorted_cod_or = ar_cod_or.stream().
							sorted(Integer::compareTo).toArray();
					Object[] sorted_cod_nuevos = ar_cod_nuevos.stream().
							sorted(Integer::compareTo).toArray();
					
					for(int i = 0; i < sorted_cod_nuevos.length; i++){
						
						ar_cod_aux_nuevo.add((Integer) sorted_cod_nuevos[i]);
					}
					for(int i = 0; i < sorted_cod_or.length; i++){
						
						ar_cod_aux_or.add((Integer) sorted_cod_or[i]);
					}
					
					for(int i = 0; i < sorted_cod_nuevos.length; i++){
							System.out.println("cod nuevo" + sorted_cod_nuevos[i] + " cod or " +
									sorted_cod_or[i]);
							if(sorted_cod_nuevos[i]==sorted_cod_or[i]){
								
								cont_coincidencias++;
								cod_igual = true;
							}
							else cod_igual = false;
								
					}
				}*/
				
				if(arpa_nuevos.size() >= arpa_or.size() || correcionCH.isSelected()/* && 
						cont_coincidencias!=ar_cod_nuevos.size()*/){
					
					ArrayList<Integer> ar_cod_cambiados = new ArrayList<Integer>();
					
					boolean cod_cambiado;
					
					ArrayList<Pedido_articuloVO> arpaVO = _controladorPedido.
							buscarArticulos_porPedido(vp.getPedidosVO().getN_pedido(), true);
					
					ArrayList<Pedido_articuloVO> arpa_cambiadosVO = new ArrayList<Pedido_articuloVO>(); 
					
					/*if(arpaVO!=null){
						
						for(Pedido_articuloVO paVO : arpa_or){
							
							cod_cambiado = true;
							
							for(Pedido_articuloVO panVO : arpa_nuevos){
								
								if(paVO.getCodigo_articulo()==panVO.getCodigo_articulo() &&
										paVO.getDias()==panVO.getDias()&&
										paVO.getCuota()==panVO.getCuota()){
									
									arpa_nuevos.remove(panVO);
									cod_cambiado = false;
									break;
								}
								
							}
							if(cod_cambiado)arpa_cambiadosVO.add(paVO);
						}
					}*/
					
					
					/*for(Integer i : ar_cod_aux_or){
						
						cod_cambiado = true;
						
						for(Integer j : ar_cod_aux_nuevo){
							
							if(i==j) {
								
								ar_cod_aux_nuevo.remove(j);
								cod_cambiado = false;
								break;
							}
						}
						if(cod_cambiado) ar_cod_cambiados.add(i);
					}*/
					
					java.util.Date d = new java.util.Date();
					java.sql.Date hoy = new java.sql.Date(d.getTime());
					java.sql.Time hora = new java.sql.Time(d.getTime());
					
					Cambio_planVO cVO = new Cambio_planVO();
					
					cVO.setN_pedido(vp.getPedidosVO().getN_pedido());
					cVO.setId_vendedor(vista_buscar_cliente.getClienteVO().getId_vendedor());
					
					PlanesVO planVO = new PlanesVO();
					planVO.setN_pedido(vp.getPedidosVO().getN_pedido());
					
					int n_plan = _controladorPlan.insertPlan(planVO);
					
					for(Pedido_articuloVO pVO : arpa_or){
						
//						_controladorPedido.eliminarPedido_articulo(pVO.getId());
						_controladorPedido.deletePedido_articulo(pVO.getId());
						
						if(this.correcionCH.isSelected()){
							
							_controladorPedido.eliminarPedido_articulo(pVO.getId());
							_controladorPlan.deleteHistorial_planes(pVO.getId());
							//_controladorPlan.deletePlan(pVO.getId());
						}
					}
					for(Pedido_articuloVO pVO : arpa_nuevos){
						
						int id_pa = _controladorPedido.insertPedido_articulo(vp.getPedidosVO().getN_pedido(),
								pVO.getCodigo_articulo(), pVO.getDias(), pVO.getCuota());
						
						
						Historial_planesVO hVO = new Historial_planesVO();
						
						hVO.setId(id_pa);
						hVO.setN_plan(n_plan);
						hVO.setN_pedido(planVO.getN_pedido());
						hVO.setDias(pVO.getDias());
						hVO.setCuota(pVO.getCuota());
						hVO.setCodigo_articulo(pVO.getCodigo_articulo());
						
						_controladorPlan.insertHistorial_planes(hVO);
						
					}
					
					/*int codigo = 0;
					
					for(int i = 0; i < tabla.getRowCount(); i++){
						codigo = Integer.parseInt(tabla.getModel().getValueAt(i, 1).toString());
						_controladorPedido.insertPedido_articulo(cVO.getN_pedido(), codigo,
								Integer.parseInt(tabla.getModel().getValueAt(i, 3).toString()),
								Double.parseDouble(tabla.getModel().getValueAt(i, 4).toString()));
						
					}*/
					
					ArrayList<Pedido_articuloVO> ar_pedido = _controladorPedido.
							buscarArticulos_porPedido(vp.getPedidosVO().getN_pedido(), true);
					
					String plan_posterior = "";
					
					if(ar_pedido!=null){
						
						for(Pedido_articuloVO paVO : ar_pedido){
							
							ArticulosVO aVO = _controladorArticulo.
									buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
							
							ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
							
							if(apVO!=null){
								
								plan_posterior = plan_posterior + "" + "Prestamo(" + apVO.getCodigo() + 
										")$" + Double.toString(apVO.getMonto()) + "[" + paVO.getDias() + "x" +
										paVO.getCuota() + "]";
							}
							/*else if(paVO.getCantidad()>1)
								plan_posterior = plan_posterior + " " + aVO.getNombre() +
								"(" + aVO.getCodigo() + ")" + "x" + paVO.getCantidad();*/
							else
								plan_posterior = plan_posterior + " " + aVO.getNombre()+
										"(" + aVO.getCodigo() + ")" + "[" + paVO.getDias() + "x" +
												paVO.getCuota() + "]";
						}
						
					}
					
					int dias = Integer.parseInt(diasTF.getText());
					double cuota = Double.parseDouble(cuotaTF.getText());
					
					plan_posterior = plan_posterior + " #" + dias + "x" + 
					cuota;
					
					cVO.setPlan_anterior(vp.getPlan_original());
					cVO.setPlan_posterior(plan_posterior);
					
					cVO.setCredito_anterior(vp.getPedido_originalVO().getDias() * 
							vp.getPedido_originalVO().getCuota_diaria());
					cVO.setCredito_posterior(Integer.parseInt(diasTF.getText()) * 
							Double.parseDouble(cuotaTF.getText()));
					
					cVO.setId_usuario(Principal._usuario.getId_usuario());
					cVO.setFecha_registro(hoy);
					cVO.setHora_registro(hora);
					
					DAVO dVO = new DAVO();
					
					dVO.setNpedido(cVO.getN_pedido());
					
					System.out.println(porcentaje_daTF.getText());
					
					if(porcentaje_daTF.getText().equals("")){
						
						dVO.setMonto(0);
					}
					else{
						
						double monto = _controladorDA.calculoDescuento(
								arpa_cambiadosVO,vp.getPedido_originalVO(),
								Double.parseDouble(porcentaje_daTF.getText()));	
						dVO.setMonto(monto);
					}
					
					if(!correcionCH.isSelected()){
						
						int res = ccp.establecerNuevoPlan( cVO, dias, cuota, dVO);
						
						if(res >= 0) {
							
							PedidosVO pVO = _controladorPedido.buscarPedido_porNpedido(
									vp.getPedidosVO().getN_pedido());
							
							_controladorPedido.logicaGeneral(pVO);
							vp.setPlan_original(pVO);
							vp.mostrarPedido(pVO);
							this.dispose();
							Mensajes.getMensaje_altaExitosoGenerico();
							
						}
				
					}
					else{
						
						int res = _controladorPedido.
								cambiarPlan_pedido(dias, cuota, vp.getPedidosVO().getN_pedido());
						
						if(res > 0){
							
							Mensajes.getMensaje_modificacionExitosa();
						}
						else Mensajes.getMensaje_modificacion_sinExito();
					}
					
				}
				else Mensajes.getMensaje_errorCambioArticulo();	
				//this.dispose();
			}
				
		}
					
	}
	
	public void iniciar(PedidosVO pedidoVO){
		
		limpiar_tabla(tabla);
		
		int cantidad = 0;
		
		if(pedidoVO!=null){
	
			ArrayList<Pedido_articuloVO> ar_pedidos = _controladorPedido.
					buscarArticulos_porPedido(vp.getPedidosVO().getN_pedido(), true);
			
			if(ar_pedidos!=null){
				
				for(Pedido_articuloVO paVO : ar_pedidos){
					
					
					Object [] datos = new Object[5];
										
					ArticulosVO aVO = _controladorArticulo.
							buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
					
					arpa_or.add(paVO);
					datos[0] = paVO.getId();
					datos [1] = aVO.getCodigo();
					
					if(aVO.getNombre().equals("Prestamo")){
						
						ArticulosPVO apVO = _controladorArticulo.
								buscarArticulo_enPrestamo(paVO.getCodigo_articulo());
					
						if(apVO!=null)
						
							datos [2] = aVO.getNombre() + "$ "+ Double.toString(apVO.getMonto());
					}
					else
						datos [2] = aVO.getNombre();
					
					datos [3] = paVO.getDias();
					datos[4] = paVO.getCuota();
					
					tModel.addRow(datos);
					
					/*if(paVO.getCantidad()>1){
						
						for(int i = 1; i < paVO.getCantidad(); i++){
							
							Object [] datos2 = new Object[4];
							
							ar_cod_or.add(aVO.getCodigo());
							datos2 [0] = aVO.getCodigo();
							datos2 [1] = aVO.getNombre();
							datos2 [2] = aVO.getDias();
							datos2 [3] = aVO.getCuota();
			
							tModel.addRow(datos2);
							
						}
							
					}*/
				}
				
				
			}
			
			
			
		}
		
		ArrayList<PlanesVO> ar_planes = _controladorPlan.
				buscarPlanes_porPedido(pedidoVO.getN_pedido());
		
		pHistorial_planes.removeAll();
		pHistorial_planes.updateUI();
		if(ar_planes!=null){
			
			for(PlanesVO pVO : ar_planes){
				
				System.out.println("Plan " + pVO.getN_plan());
				
				
				JScrollPane scr = new JScrollPane();
				JTable t = new JTable();
				DefaultTableModel dt = new DefaultTableModel(null,getColumnas_historial());
				JButton reasignar = new JButton("Reasignar plan");
				
				 t.getTableHeader().setReorderingAllowed(false);
			 	   t.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
				    t.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 12));
				    t.setFont(new Font("Arial", Font.PLAIN, 12));	
				
				JPanel pPanel = new JPanel();
				
				
				JPanel pComandos = new JPanel();
				pPanel.setLayout(new BorderLayout());
				pComandos.add(reasignar);
				pPanel.add(pComandos, BorderLayout.PAGE_START);
				pPanel.add(scr, BorderLayout.CENTER);
				pPanel.setBackground(Color.white);
				
				Border borde = BorderFactory.createLineBorder(Color.black);
				pPanel.setBorder(borde);
				
				t.setModel(dt);
				scr.setViewportView(t);
				scr.setPreferredSize(new Dimension(450,120));
				pPanel.setPreferredSize(new Dimension(450,150));
				
				pHistorial_planes.add(pPanel);
				pHistorial_planes.add(Box.createRigidArea(new Dimension(0,10)));
				
				
				reasignar.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						int res = 0;
						
						int opcion = Mensajes.getMensaje_confirmacion_reasignar_plan(pVO.getN_plan());
						
						if(opcion==JOptionPane.YES_OPTION){
							
							for(int i = 0; i < t.getRowCount(); i++){
								
								res = _controladorPedido.
										update_estadoPedido_articulo(
												Integer.parseInt(t.getModel().getValueAt(i,0).toString()), true);
							}
							for(int i = 0; i < tabla.getRowCount(); i++){
								
								res = _controladorPedido.
										update_estadoPedido_articulo(
												Integer.parseInt(tabla.getModel().getValueAt(i,0).toString()),false);
								
							}
							
							if(res > 0 ){
								
								Mensajes.getMensaje_modificacionExitosa();
								
								iniciar(vp.getPedidosVO());
								vp.mostrarPedido(vp.getPedidosVO());
							}
							else Mensajes.getMensaje_modificacion_sinExito();
							
						}
						
						
					}
					
					
				});
				
				ArrayList<Historial_planesVO> ar_historial = _controladorPlan.
						buscarHistorial_planes_porNplan(pVO.getN_plan());
				
				if(ar_historial!=null){
					
					for(Historial_planesVO hVO : ar_historial){
						
						System.out.println("historial nplan " + hVO.getN_plan());
						
						Object [] datos = new Object[7];
						
						datos[0] = hVO.getId();
						datos[1] = hVO.getN_plan();
						datos[2] = hVO.getN_pedido();
						datos[3] = hVO.getCodigo_articulo();
						
						ArticulosVO aVO = _controladorArticulo.
								buscarArticuloUsuario(Integer.toString(hVO.getCodigo_articulo()));
						
						if(aVO.getNombre().equals("Prestamo")){
							
							ArticulosPVO apVO = _controladorArticulo.
									buscarArticulo_enPrestamo(hVO.getCodigo_articulo());
						
							if(apVO!=null)
							
								datos [4] = aVO.getNombre() + "$ "+ Double.toString(apVO.getMonto());
						}
						else
							datos [4] = aVO.getNombre();
						
						datos[5] = hVO.getDias();
						datos[6] = hVO.getCuota();
						
						dt.addRow(datos);
						
					}
				}
				/*pPanel.setMaximumSize
				(new Dimension(Integer.MAX_VALUE, pPanel.getMinimumSize().height));*/
			}
			
			
			
		}
		
		tabla.setModel(tModel);
		
		tabla.getPreferredScrollableViewportSize().setSize(900,
				tabla.getRowHeight() * cantidad);
	
		//ancho_columnas();
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla.getColumnCount(); i ++)
			
			tabla.getColumnModel().getColumn(i).setCellRenderer(cent);
		
		//pComandos.add(cobradorL);
		
		this.setVisible(true);
		
	}
	
	 public void limpiar_tabla(JTable tabla){
	    	
	    	if(tabla.getRowCount() > 0){
				
				DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
				
				int contFila = tabla.getRowCount();
				
				for(int i = 0; i < contFila; i++)
				
					modelo.removeRow(0);
			}
	    	
	 }
	
	public void limpiar(){
	
		diasTF.setText("");
		cuotaTF.setText("");
		creditoTF.setText(""); 
		porcentaje_daTF.setText("");
		lArticulo1.setText("");
	
	}
	
	private void centrar_columnas(JTable tabla){
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla.getColumnCount(); i ++)
			
			tabla.getColumnModel().getColumn(i).setCellRenderer(cent);
		
	}	
	
	private String [] getColumnas(){
		
		String columna [] = new String []{"Id", "Código", "Nombre", "Dias", "Cuota"};
		
		return columna;
	}
	private String [] getColumnas_historial(){
		
		String columna [] = new String []{"Id","N plan","N pedido", "Código", "Nombre", "Dias", "Cuota"};
		
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
	public void setControladorDA(ControladorDA _controladorDA){
		
		this._controladorDA = _controladorDA;
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
