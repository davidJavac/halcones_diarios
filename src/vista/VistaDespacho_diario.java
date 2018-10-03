package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.SynchronousQueue;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controlador.ControladorArticulo;
import controlador.ControladorCliente;
import controlador.ControladorCobrador;
import controlador.ControladorCombo;
import controlador.ControladorDespacho_diario;
import controlador.ControladorDomicilioComercial;
import controlador.ControladorJefeCalle;
import controlador.ControladorLocalidad;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorPrestamo;
import controlador.ControladorUsuario;
import controlador.Principal;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.CobradorVO;
import modelo_vo.Combinacion_diasVO;
import modelo_vo.ComercioVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.Despacho_diarioVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.LocalidadVO;
import modelo_vo.ObservacionesVO;
import modelo_vo.PedidosVO;
import modelo_vo.Refinanciacion_exVO;
import modelo_vo.Refinanciacion_inVO;
import modelo_vo.UsuariosVO;
import vista.VistaIngresos.CustomJToolTip;

public class VistaDespacho_diario extends JInternalFrame implements ActionListener {
	
	
	private JTable tabla_pendientes;
	private JTable tabla_despacho = new JTable();
	private DefaultTableModel t_model_pendientes;
	private DefaultTableModel t_model_despacho;
	private JTable tabla_impresion = new JTable();
	private DefaultTableModel t_model_impresion;
	private Object objeto_ar [] = new Object[10];
	private JScrollPane scr_pendiente;
	private JScrollPane scr_despacho;
	private JPanel pIntegra = new JPanel();
	private JPanel pIntegra_pendientes = new JPanel();
	private JPanel pIntegra_despacho = new JPanel();
	private JPanel pTabla_despacho = new JPanel();
	private JPanel pTabla_pendiente = new JPanel();
	private JPanel pComandos_pendientes = new JPanel();
	private JPanel pComandos_despacho = new JPanel();
	private JButton guardar = new JButton("Guardar");
	private JButton salir = new JButton("Salir");
	private JButton enviar = new JButton("Enviar");
	private JButton rechazar = new JButton("Rechazado");
	private JButton diferido = new JButton("Diferido");
	private JButton entrega = new JButton("Entregado");
	private JButton resolucion = new JButton("Resolución");
	private JButton buscar_jefecalle = new JButton("...");
	private JButton buscar_cobrador = new JButton("...");
	private JTextArea observaciones; 
	private JTextField jefecalleTF;
	private JTextField cobradorTF;
	private JLabel obL = new JLabel();
	private JLabel cobradorL = new JLabel();
	private JLabel clienteL = new  JLabel();
	
	private JLabel entregaL = new JLabel();
	
	private VistaBuscarPedidos_porClientes _vista_buscarPedido_cliente;
	private ControladorPagoDiario _controladorPagoDiario;
	private ControladorArticulo _controladorArticulo;
	private ControladorCombo _controladorCombo;
	private ControladorPrestamo _controladorPrestamo;
	private ControladorCliente _controladorCliente;
	private ControladorUsuario _controladorUsuario;
	private ControladorDespacho_diario cdp;
	private ControladorPedidos _controladorPedido;
	private ControladorJefeCalle controladorJefe_calle;
	private ControladorCobrador controladorCobrador;
	private ControladorDomicilioComercial controladorDomCom;
	private ControladorLocalidad controladorLocalidad;
	private BusquedaEntities be;
	private static VistaPrincipal _vista_principal;
	
	
	
	private int cantidad;
	
	private boolean pendiente_enDespacho = false;
	
	private ArrayList<JTextField> ar = new ArrayList<JTextField>();
	
	private VistaBuscarCliente vista_buscar_cliente;
	
	  private JButton imprimir = new JButton(){
	        //override the JButtons createToolTip method
	        @Override
	        public JToolTip createToolTip() {
	            return (new CustomJToolTip(this));
	        }
	    };
	    
	    private JButton historial = new JButton(){
	        //override the JButtons createToolTip method
	        @Override
	        public JToolTip createToolTip() {
	            return (new CustomJToolTip(this));
	        }
	    };
	    
	    private boolean[] canEdit_pendientes= new boolean[]{
	            false, false, false, false, false, false, false
	    };
	    
	    private boolean[] canEdit_despacho= new boolean[]{
	            false, false, false, false, false, false, false, false, false
	    };
	    
	    private float[] columnWidthPercentage = {5.0f, 15.0f, 10.0f, 20.0f, 10.0f,20.0f, 5.0f, 5.0f, 5.0f, 5.0f};
	
	public VistaDespacho_diario(){
		//super(_vista_principal, "Despacho diario", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		super("Despacho diario", true, true, true);
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setLayout(new BorderLayout());
		JFrame.setDefaultLookAndFeelDecorated(true);
		buscar_jefecalle.setFocusable(false);
		buscar_cobrador.setFocusable(false);
		
		imprimir.addActionListener(this);
		historial.addActionListener(this);
		guardar.addActionListener(this);
		salir.addActionListener(this);
		enviar.addActionListener(this);
		rechazar.addActionListener(this);
		diferido.addActionListener(this);
		entrega.addActionListener(this);
		resolucion.addActionListener(this);
		buscar_jefecalle.addActionListener(this);
		buscar_cobrador.addActionListener(this);
		observaciones = new JTextArea(2,30);
    	observaciones.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	observaciones.setLineWrap(true);
		
		pIntegra.setLayout(new BoxLayout(pIntegra, BoxLayout.Y_AXIS));
		
		pIntegra_pendientes.setLayout(new BorderLayout());
		pIntegra_despacho.setLayout(new BorderLayout());
		
		Border borde_pendiente = BorderFactory.createTitledBorder(null, "Pedidos pendientes de entrega", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.PLAIN,16), Color.BLACK);
		pIntegra_pendientes.setBorder(borde_pendiente);
		
		Border borde_despacho = BorderFactory.createTitledBorder(null, "Despacho diario", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.PLAIN,16), Color.BLACK);
		pIntegra_despacho.setBorder(borde_despacho);
	        
		pIntegra.setBorder(new EmptyBorder(10,10,10,10));
	        //Display the window.
	     
		tabla_pendientes = new JTable()/*{
			
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
	        {
	            Component c = super.prepareRenderer(renderer, row, column);
	            
	            if(row ==cantidad && pendiente_enDespacho){
	            	
	            	
	                    c.setBackground(new Color(183, 242, 113));//Set Background
	                    /*JTextField textField = new JTextField();
	                    textField.setFont(new Font("Arial", Font.PLAIN, 18));
	                    textField.setBorder(new LineBorder(Color.BLACK));
	                    DefaultCellEditor dce = new DefaultCellEditor( textField );
	                    tabla_pendientes.getColumnModel().getColumn(column).setCellEditor(dce);
	                   
	            	
	            }
	            else{    
	 	           c.setBackground(tabla_pendientes.getBackground());
	 	       }
	            
	            

	            return c;
	        }
		}*/;
		
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	       // Dimension dim2 = _vista_principal.getDesktop().getPreferredSize();
	        this.setSize(dim.width*80/100, dim.height*80/100);
	        //this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	       // frame.getContentPane().add(scr); 
	    
	        	
	       // if(_vista_buscarPedido_cliente.isShowing()) _vista_buscarPedido_cliente.setEnabled(false);
	        
	        //scr.setViewportView(tabla);
	        this.setFocusable(true);
	        //this.setResizable(false);
	       // this.setLocationRelativeTo(null);
	        
	        setClosable(true); 
	        setIconifiable(true); 
	        setMaximizable(true); 
	        setResizable(true); 
	        
	        tabla_pendientes.getTableHeader().setReorderingAllowed(false);
	        tabla_despacho.getTableHeader().setReorderingAllowed(false);
	        
	        
			scr_despacho = new JScrollPane();
			scr_despacho.add(tabla_despacho);
			pTabla_despacho.add(scr_despacho);
			scr_despacho.setViewportView(tabla_despacho);
	        
	        scr_pendiente = new JScrollPane();
			scr_pendiente.add(tabla_pendientes);
			pTabla_pendiente.add(scr_pendiente);
			scr_pendiente.setViewportView(tabla_pendientes);
			
			jefecalleTF = new JTextField(5);
			cobradorTF = new JTextField(5);
			
			pComandos_pendientes.add(enviar);
			
			GridLayout gl = new GridLayout(0,2);
			
			JPanel p = new JPanel();
			JPanel p2 = new JPanel();
			
			p.setLayout(gl);
			p2.setLayout(gl);
			JLabel jcL = new JLabel();
			jcL.setText("Jefe de calle");
			JLabel cobradorL = new JLabel();
			cobradorL.setText("Cobrador");
			
			p.add(jefecalleTF);
			p.add(buscar_jefecalle);
			p2.add(cobradorTF);
			p2.add(buscar_cobrador);
			
			JPanel pIntegraP = new JPanel();
			pIntegraP.setLayout(new BoxLayout(pIntegraP, BoxLayout.Y_AXIS));
			JPanel pIntegra_pend = new JPanel();
			
			Border bordee = BorderFactory.createTitledBorder(null, "Entrega", 
	    			TitledBorder.CENTER, TitledBorder.TOP,
	    			new Font("Arial",Font.PLAIN,16), Color.BLACK);
			pIntegra_pend.setBorder(bordee);
			
			pIntegra_pend.add(jcL);
			pIntegra_pend.add(p);
			pIntegra_pend.add(cobradorL);
			pIntegra_pend.add(p2);
			
			pIntegraP.add(pIntegra_pend);
			//pIntegraP.add(entregaL);
			
			
			pComandos_pendientes.add(pIntegraP);
			pComandos_pendientes.add(entregaL);
			
			
			
			jefecalleTF.addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					jefecalleTF.setBackground(new Color(183, 242, 113));
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(controladorJefe_calle.buscarJefeUsuario(jefecalleTF.getText())!=null){
						
						entregaL.setText(controladorJefe_calle.buscarJefeUsuario(jefecalleTF.getText()));
						cobradorTF.setText("");
					}
						
						
					else{
						
						jefecalleTF.setText("");
					}
					
					if(cobradorTF.getText().equals("") && jefecalleTF.getText().equals(""))
						entregaL.setText("");
					jefecalleTF.setBackground(new Color(255, 255, 255));
				}
				
				
			});
			
			cobradorTF.addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					cobradorTF.setBackground(new Color(183, 242, 113));
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					
					if(controladorCobrador.buscarCobradorUsuario(cobradorTF.getText())!=null){
						
						entregaL.setText(controladorCobrador.buscarCobradorUsuario(cobradorTF.getText()));
						jefecalleTF.setText("");
					}
							
					else{
						
						cobradorTF.setText("");
					}
					if(cobradorTF.getText().equals("") && jefecalleTF.getText().equals(""))
						entregaL.setText("");
					
					cobradorTF.setBackground(new Color(255, 255, 255));
				}
				
				
			});
			
			BH_despacho bd = new BH_despacho(imprimir, historial);
			
			this.add(bd, BorderLayout.PAGE_START);
			JScrollPane scr_pend = new JScrollPane();
			JScrollPane scr_desp = new JScrollPane();
			
			
			
			pIntegra_pendientes.add(pComandos_pendientes, BorderLayout.NORTH);
			pIntegra_pendientes.add(pTabla_pendiente, BorderLayout.CENTER);	
			
			scr_pend.setViewportView(pIntegra_pendientes);
			
			pIntegra.add(scr_pend);
			
			pIntegra_despacho.add(pComandos_despacho, BorderLayout.NORTH);
			pIntegra_despacho.add(pTabla_despacho, BorderLayout.CENTER);
			
			scr_desp.setViewportView(pIntegra_despacho);
			
			pIntegra.add(scr_desp);
			//frame.pack();
			
			ar.add(jefecalleTF);
			ar.add(cobradorTF);
			
	       this.add(pIntegra, BorderLayout.CENTER);
	        //_vista_principal.setEnabled(false);
	}
	
	public void iniciar_pendiente(ArrayList<PedidosVO> ar){
		 
		cantidad = 0;
		//pIntegra.remove(pIntegra_pendientes);
		//tabla  = new JTable();
		//t_model_pendientes = new DefaultTableModel(null, getColumnas1());
		t_model_pendientes = new DefaultTableModel(null, getColumnas1()){
		 
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit_pendientes[columnIndex];
        }
    	};
		tabla_pendientes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
	
		
		pTabla_pendiente.setBackground(Color.white);
		
		boolean comprobacion = false;
		
		if(ar!=null){
		
			for(PedidosVO  pVO : ar){
					
				System.out.println(pVO.getN_pedido() + "pedido/////////////////////////////////////////////////////");
				
				comprobacion = _controladorPedido.verificarPedido_enDespacho(pVO.getN_pedido());
				
				if(!comprobacion){
					
					//pendiente_enDespacho = true;
					
					SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
					String date1 =  new String(sf.format(pVO.getFecha_registro()));
					
					Object [] o = new Object[7];
					o[0] = pVO.getN_pedido();
					
					String nombre = "";
						
					ArrayList<Pedido_articuloVO> ar_pedido = _controladorPedido.
							buscarArticulos_porPedido(pVO.getN_pedido(), true);
					
					//if(apVO!=null) lDescripcion.setText("$" + Double.toString(apVO.getMonto()));
					
					if(ar_pedido!=null){
						
						for(Pedido_articuloVO paVO : ar_pedido){
							
							ArticulosVO aVO = _controladorArticulo.
									buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
							
							ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
							
							if(apVO!=null){
								
								nombre = "Prestamo(" + apVO.getCodigo() + 
										")$" + Double.toString(apVO.getMonto());
							}
							/*else if(paVO.getCantidad()>1)
								nombre = nombre  + aVO.getNombre() +
								"(" + aVO.getCodigo() + ")" + "x" + paVO.getCantidad() + " ";*/
							else
								nombre = nombre  + aVO.getNombre()+
										"(" + aVO.getCodigo() + ")" + " ";
						}
						
					}
					
						o[2] = pVO.getDias();
						o[3] = pVO.getCuota_diaria();
					
					
					o[1] = nombre;
					
					UsuariosVO uVO = _controladorUsuario.buscarUsuario_porID((short)pVO.getId_usuario());
					
					if(uVO!=null)
					
						o[4] = uVO.getNombre();
					
					o[5] = date1;
					o[6] = pVO.getHora_registro();
					
					
					
					t_model_pendientes.addRow(o);
					cantidad += 1;
				}
				
						
				
			}
		}
		
		tabla_pendientes.setModel(t_model_pendientes);
		
		tabla_pendientes.getPreferredScrollableViewportSize().setSize(900,
				tabla_pendientes.getRowHeight() * cantidad);
		
		tabla_pendientes.setRowSelectionAllowed(true);
		tabla_pendientes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	
		ancho_columnas();
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla_pendientes.getColumnCount(); i ++)
			
			tabla_pendientes.getColumnModel().getColumn(i).setCellRenderer(cent);
		
		
		pIntegra.updateUI();
		
	}
	
	public void iniciar_despacho(ArrayList<Despacho_diarioVO> ar){
		 
		limpiar_despacho();
		//this.setVisible(true)
		//pIntegra.remove(pIntegra_despacho);
		cantidad = 0;
		
		//tabla  = new JTable();
		
		//t_model_despacho = new DefaultTableModel(null, getColumnas2());
		t_model_despacho = new DefaultTableModel(null, getColumnas2()){
			 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit_despacho[columnIndex];
            }
        };
		
		tabla_despacho.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		
		//tabla_despacho.setRowSelectionAllowed(true);
		//tabla_despacho.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		pTabla_despacho.setBackground(Color.white);
		
		if(ar!=null){
						
			for(Despacho_diarioVO dpVO : ar){
				
				SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
				String date1 =  new String(sf.format(dpVO.getFecha_registro()));
				
				Object [] o = new Object[10];
				
				o[0] = dpVO.getN_pedido();
				
				ClienteVO clienteVO = _controladorCliente.buscarCliente_porNPedido(dpVO.getN_pedido());
				
				if(clienteVO!=null){
					
					o[1] = clienteVO.getNombre() + " " + clienteVO.getApellido();
					
				}
				
				o[2] = dpVO.getEntrega();
				o[3] = dpVO.getNombre();
				o[4] = dpVO.getEstado();
				o[5] = dpVO.getMonto();
				o[6] = dpVO.getObservaciones();
				
				short id_u = (short) dpVO.getId_usuario();
				
				UsuariosVO uVO = _controladorUsuario.buscarUsuario_porID(id_u);
				
				if(uVO!=null){
					
					o[7] = uVO.getNombre();
					
				}
			
				o[8] = date1;
				o[9] = dpVO.getHora_registro();
				
				t_model_despacho.addRow(o);
				cantidad += 1;
			}
		}
		
		tabla_despacho.setModel(t_model_despacho);
		
		tabla_despacho.getPreferredScrollableViewportSize().setSize(900,
				tabla_despacho.getRowHeight() * cantidad);
	
		ancho_columnas();
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla_despacho.getColumnCount(); i ++)
			
			tabla_despacho.getColumnModel().getColumn(i).setCellRenderer(cent);
		
		//pTabla.add(tabla);
		
		

		pComandos_despacho.add(resolucion);
		
		/*pIntegra_despacho.add(pComandos_despacho, BorderLayout.NORTH);
		pIntegra_despacho.add(pTabla_despacho, BorderLayout.CENTER);
		pIntegra.add(pIntegra_despacho);*/
		pTabla_despacho.updateUI();
		
	}
	
	public void cargar_despacho(JTable tabla_pendientes){
		
		int array [] = tabla_pendientes.getSelectedRows();
		
		System.out.println(array.length);
		
		Despacho_diarioVO dpVO = new Despacho_diarioVO();
		
		Date hoy = new Date();
		java.sql.Date hoysql = new java.sql.Date(hoy.getTime());
		java.sql.Time hora = new java.sql.Time(hoy.getTime());
		
		boolean resultado = true;
		boolean duplicado = false;
		
		//for(int i = 0; i < array.length; i++){
		  for(int i : array){
			  
			System.out.println("fila seleccionada " + i);  
			System.out.println("dentro de for array pendientes ****************************************");  
			  
			dpVO.setN_pedido(Integer.parseInt(tabla_pendientes.getModel().getValueAt(i, 0).toString()));
			dpVO.setEntrega(entregaL.getText());
			dpVO.setNombre(tabla_pendientes.getModel().getValueAt(i, 1).toString());
			dpVO.setEstado("enviado");
			dpVO.setMonto(0);
			dpVO.setObservaciones("");
			dpVO.setId_usuario(Principal._usuario.getId_usuario());
			dpVO.setFecha_registro(hoysql);
			dpVO.setHora_registro(hora);
			
			int n_pedido = Integer.parseInt(tabla_pendientes.getModel().getValueAt(i, 0).toString());
			
			if(cdp.comprobar_despachoDuplicado(n_pedido, hoysql)){
				
				duplicado = true;
				
				int opcion = Mensajes.getMensaje_despachoDuplicado(dpVO.getN_pedido());
				
				if(opcion==JOptionPane.YES_OPTION){
					
					VistaResolucionDespacho vrd = new VistaResolucionDespacho(dpVO);
					_vista_principal.getDesktop().add(vrd);
					vrd.setVistaDespacho_diario(this);
					vrd.setControladorDespacho_diario(cdp);
					vrd.setControladorPedidos(_controladorPedido);
					vrd.setControladorArticulo(_controladorArticulo);
					vrd.setControladorCliente(_controladorCliente);
					vrd.setVisible(true);
				}
			}
			else{
				//pIntegra.removeAll();
				int res = cdp.insertDespacho(dpVO);
				if(res == 0){
					
					resultado = false;		
				}
				
			}	
			
		}	
		
		if(!duplicado){
			
			if(resultado){
				
				iniciar_despacho(cdp.buscarDespachos_porFecha(hoysql));
				iniciar_pendiente(_controladorPedido.buscarPedidos_porEstado("pendiente entrega"));
				//pIntegra_pendientes.remove(pTabla_pendiente);
				//pIntegra_despacho.remove(pTabla_despacho);
				
				Mensajes.getMensaje_altaExitosoGenerico();
			}
			else{
				
				Mensajes.getMensaje_altaErrorGenerico();
			}
		}	
		
		pIntegra.updateUI();
	}
	
	private void resizeColumns() {
	    int tW = 1000;
	    TableColumn column;
	    TableColumnModel jTableColumnModel = tabla_impresion.getColumnModel();
	    int cantCols = jTableColumnModel.getColumnCount();
	    for (int i = 0; i < cantCols; i++) {
	        column = jTableColumnModel.getColumn(i);
	        int pWidth = Math.round(columnWidthPercentage[i] * tW);
	        column.setPreferredWidth(pWidth);
	    }
	}
	
	
	public void resolucion_despacho(JTable tabla_despachos){
		
		int array [] = tabla_despachos.getSelectedRows();
		
		System.out.println(array.length);
		
		
		Date hoy = new Date();
		java.sql.Date hoysql = new java.sql.Date(hoy.getTime());
		java.sql.Time hora = new java.sql.Time(hoy.getTime());
		
		  for(int i : array){
			  Despacho_diarioVO dpVO = new Despacho_diarioVO();
			  
			dpVO.setN_pedido(Integer.parseInt(tabla_despachos.getModel().getValueAt(i, 0).toString()));
			System.out.println("Pedido en for resolucion " + dpVO.getN_pedido());
			dpVO.setEntrega(entregaL.getText());
			dpVO.setNombre(tabla_despachos.getModel().getValueAt(i, 3).toString());
			dpVO.setEstado(tabla_despachos.getModel().getValueAt(i, 4).toString());
			dpVO.setMonto(Double.parseDouble(tabla_despachos.getModel().getValueAt(i, 5).toString()));
			dpVO.setObservaciones(tabla_despachos.getModel().getValueAt(i, 6).toString());
			dpVO.setId_usuario(Principal._usuario.getId_usuario());
			dpVO.setFecha_registro(hoysql);
			dpVO.setHora_registro(hora);
			
			VistaResolucionDespacho vrd = new VistaResolucionDespacho(dpVO);
			_vista_principal.getDesktop().add(vrd);
			vrd.setVistaDespacho_diario(this);
			vrd.setControladorDespacho_diario(cdp);
			vrd.setControladorPedidos(_controladorPedido);
			vrd.setControladorArticulo(_controladorArticulo);
			vrd.setControladorCliente(_controladorCliente);
			vrd.setVisible(true);
			System.out.println("fin de for");
			
		}	
		
	}
	
	private void ancho_columnas(){
		
		for(int i = 0; i < tabla_pendientes.getColumnCount(); i++){
			
			if(i == 1) tabla_pendientes.getColumnModel().getColumn(i).setPreferredWidth(250);
			else tabla_pendientes.getColumnModel().getColumn(i).setPreferredWidth(50);
		}
		
		for(int i = 0; i < tabla_despacho.getColumnCount(); i++){
			
			if(i == 3) tabla_despacho.getColumnModel().getColumn(i).setPreferredWidth(250);
			else tabla_despacho.getColumnModel().getColumn(i).setPreferredWidth(50);
		}
	}
	
	private String [] getColumnas1(){
		
		String columna [] = new String []{"N pedido", "Detalle", "Dias",
										"Cuota", "Usuario", "Fecha registro", "Hora registro"};
		
		return columna;
	}
	
	private String [] getColumnas2(){
		
		String columna [] = new String []{"N pedido", "Cliente", "Entrega", "Detalle", "Estado",
										"Monto", "Observaciones", "Usuario", "Fecha registro", "Hora registro"};
		
		return columna;
	}
	
	private String [] getColumnas_impresion(){
		
		String columna [] = new String []{"N°", "Articulo", "Entrega", "Cliente", "Tel.",
								"Domicilio","Com.", "Plan",
										 "Monto", "Resultado"};
		
		return columna;
	}
	
	public JTable getTabla_pendiente(){
		
		return tabla_pendientes;
	}
	
	public JTable getTabla_despacho(){
		
		return tabla_despacho;
	}
	
	public JTextField getCobradorTF(){
		
		return cobradorTF;
	}
	
	public JTextField getJefe_calleTF(){
		
		return jefecalleTF;
	}
	
	public JLabel getEntregaL(){
		
		return entregaL;
	}
	
    public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vpc){
    	
    	this._vista_buscarPedido_cliente = vpc;
    }
    
    public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
    	
    	this.vista_buscar_cliente = vista_buscar_cliente;
    }
    
    public void setVistaPrincipal(VistaPrincipal vista_principal){
    	
    	this._vista_principal = vista_principal;
    }
    
    public void setControladorPagoDiario(ControladorPagoDiario _controladorPagoDiario){
    	
    	this._controladorPagoDiario = _controladorPagoDiario;
    }
    
    public void setControladorArticulo(ControladorArticulo _controladorArticulo){
    	
    	this._controladorArticulo = _controladorArticulo;
    }
 
    public void setControladorCombo(ControladorCombo _controladorCombo){
 	
    	this._controladorCombo = _controladorCombo;
    }
 
    public void setControladorPrestamo(ControladorPrestamo _controladorPrestamo){
 	
    	this._controladorPrestamo = _controladorPrestamo;
    }
    
    public void setControladorCobrador(ControladorCobrador controladorCobrador){
     	
    	this.controladorCobrador = controladorCobrador;
    }
    public void setControladorUsuario(ControladorUsuario controladorUsuario){
    	
    	this._controladorUsuario = controladorUsuario;
    }
    
    public void setControladorJefe_calle(ControladorJefeCalle controladorJefe_calle){
     	
    	this.controladorJefe_calle = controladorJefe_calle;
    }
    
    public void setControladorDespacho_diario(ControladorDespacho_diario cdp){
     	
    	this.cdp = cdp;
    }
    public void setControladorDomicilioComercial(ControladorDomicilioComercial controladorDomCom){
    	
    	this.controladorDomCom = controladorDomCom;
    }
    public void setControladorLocalidad(ControladorLocalidad controladorLocalidad){
    	
    	this.controladorLocalidad = controladorLocalidad;
    }
   
    public void setControladorPedidos(ControladorPedidos controladorPedidos){
     	
    	this._controladorPedido = controladorPedidos;
    }
    
	public void setControladorCliente(ControladorCliente controladorCliente){
     	
    	this._controladorCliente = controladorCliente;
    }
	
	public void setBusquedaEntities(BusquedaEntities be){
		
		this.be = be;
	}
    
    public void limpiar_pendiente(){
    	
    	if(tabla_pendientes.getRowCount() > 0){
			
			DefaultTableModel modelo = (DefaultTableModel) tabla_pendientes.getModel();
			
			int contFila = tabla_pendientes.getRowCount();
			
			for(int i = 0; i < contFila; i++)
			
				modelo.removeRow(0);
		}
    	
    }
    
    public void limpiar_despacho(){
    	
    	if(tabla_despacho.getRowCount() > 0){
			
			DefaultTableModel modelo = (DefaultTableModel) tabla_despacho.getModel();
			
			int contFila = tabla_despacho.getRowCount();
			
			for(int i = 0; i < contFila; i++)
			
				modelo.removeRow(0);
		}
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		this.be.setPanel("vista_despacho");
		
		if(e.getSource()==buscar_jefecalle){
			
			be.setTipoBusqueda(11);
			controladorJefe_calle.buscarJefe_calleAll();
			controladorJefe_calle.mostrarBusquedaEntities("Busqueda de Jefe de calle");
			
		}
		
		if(e.getSource()==buscar_cobrador){
			be.setTipoBusqueda(12);
			controladorCobrador.buscarCobradorAll();
			controladorCobrador.mostrarBusquedaEntities("Busqueda de cobrador");
			
		}	
		
		if(e.getSource()==enviar){
			//pIntegra.remove(pIntegra_despacho);
			
			if(tabla_pendientes.getSelectedRows().length > 0){
				
				if(cdp.ingresoUsuario(ar)) cargar_despacho(tabla_pendientes);
				
			}
				
			else
				Mensajes.getMensaje_sinPendientes();
		}
		
		if(e.getSource()==resolucion){
			
			if(tabla_despacho.getRowCount() > 0)
				resolucion_despacho(tabla_despacho);
			else
				Mensajes.getMensaje_sinDespacho();
		}
		
		if(e.getSource() == salir){
			
			_vista_buscarPedido_cliente.setEnabled(true);
			
			this.dispose();
		}
		
		if(e.getSource() == guardar){
			
			//_controladorMonto_t.altaMonto_t_usuario(ar, observaciones);
		
			ObservacionesVO obVO = new ObservacionesVO();
			
			obVO.setId(_vista_buscarPedido_cliente.getPedidosVO().getN_pedido());
			
			Date d = new java.util.Date();
			java.sql.Date fecha_registro = new java.sql.Date(d.getTime());
			java.sql.Time hora_registro = new java.sql.Time(d.getTime());
			
			obVO.setFecha_registro(fecha_registro);
			obVO.setHora_registro(hora_registro);
			obVO.setId_usuario(Principal._usuario.getId_usuario());
			obVO.setObservacion(observaciones.getText());
			
			/*int res = _controladorObservaciones.altaObservacionPedido(obVO);
			
			if(res > 0){
				
				Mensajes.getMensaje_altaExitosoGenerico();
			}
			else Mensajes.getMensaje_altaErrorGenerico();*/
				
	}
		
		if(e.getSource()==historial){
			
			VistaHistorial_despacho vhd = new VistaHistorial_despacho();
			_vista_principal.getDesktop().add(vhd);
			vhd.setVistaDespacho_diario(this);
			vhd.setControladorDespacho_diario(cdp);
			vhd.setControladorPedidos(_controladorPedido);
			vhd.setControladorUsuario(_controladorUsuario);
			vhd.setControladorCliente(_controladorCliente);
			vhd.setVisible(true);
		}
		
		if(e.getSource()==imprimir){
			
			boolean imprime = false;
			
			//resizeColumns();
			
			if(tabla_despacho.getRowCount() > 0){
				
				t_model_impresion = new DefaultTableModel(null, getColumnas_impresion());
				
				int cantidad = 0;
				
				for(int i = 0; i < tabla_despacho.getRowCount(); i++){
				
					if(tabla_despacho.getModel().getValueAt(i, 4).toString().equals("enviado")){
						
						imprime = true;
						
						PedidosVO pVO = _controladorPedido.buscarPedido_porNpedido(
								Integer.parseInt(tabla_despacho.getModel().getValueAt(i, 0).toString()));
						
						String nombre = "";
						
						ArrayList<Pedido_articuloVO> ar_pedido = _controladorPedido.
								buscarArticulos_porPedido(pVO.getN_pedido(), true);
						
						//if(apVO!=null) lDescripcion.setText("$" + Double.toString(apVO.getMonto()));
						
						ArrayList<String> ar_articulos = new ArrayList<String>();
						
						int cont_art = 0;
						
						if(ar_pedido!=null){
							
							for(Pedido_articuloVO paVO : ar_pedido){
								
								ArticulosVO aVO = _controladorArticulo.
										buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
								
								ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
								
								if(apVO!=null){
									
									nombre = "Prestamo(" + apVO.getCodigo() + 
											")$" + Double.toString(apVO.getMonto());

									ar_articulos.add(nombre);
								}
								/*else if(paVO.getCantidad()>1){
									
//									nombre = nombre + " " + aVO.getNombre() +
	//										"(" + aVO.getCodigo() + ")" + "x" + paVO.getCantidad() + " ";
									nombre = aVO.getNombre() +
											"(" + aVO.getCodigo() + ")" + "x" + paVO.getCantidad() + " ";
									
									ar_articulos.add(nombre);
								}*/
								else{
									
									//nombre = nombre + " " + aVO.getNombre()+
										//	"(" + aVO.getCodigo() + ")" + " ";
									nombre = aVO.getNombre()+
										"(" + aVO.getCodigo() + ")" + " ";
									
									ar_articulos.add(nombre);
								}
								
								if(cont_art==0)
								
									objeto_ar[1] = nombre;
								
								cont_art++;
							}
				
						}
						
			
						objeto_ar[7] = pVO.getDias() + "x" + pVO.getCuota_diaria();
		
						objeto_ar[0] = pVO.getN_pedido();
						
					
						objeto_ar[2] = tabla_despacho.getModel().getValueAt(i, 2).toString();
						
						DomicilioComercialVO dc = controladorDomCom.buscarDomicilioComercial_porIdc(pVO.getIdc());
						
						//ArrayList<Object []> ar = _controladorCliente.buscarClientes_porPedido(pVO.getN_pedido());
						
						if(dc!=null){
							
							//for(Object [] o: ar){

								ClienteVO cVO = _controladorCliente.buscarCliente(Integer.toString(dc.getDni()));
	
								LocalidadVO lVO = controladorLocalidad.buscarLocalidad
										(Integer.toString(dc.getId_localidad()));
									
	
								
								String direccion = dc.getDomicilio() + "  " + lVO.getLocalidad();
								
								String estado = "";
								
								switch(cVO.getEstado()){
									
								case "operando": estado = "O";
								break;
								case "ex": estado = "E";
								break;
								case "nuevo": estado = "N";
								break;
								case "baja": estado = "B";
								break;

								}
										
								objeto_ar[3]= cVO.getNombre() + " " + cVO.getApellido() + "(" + estado + ")";
								
								objeto_ar[4] = cVO.getTelefono_linea();
								objeto_ar[5] = direccion;
								
								ComercioVO comVO = null;
								
								if(controladorDomCom.buscarComercio(Integer.toString(dc.getComercio()))!=null){
				  					
				  					comVO = controladorDomCom.buscarComercio(Integer.toString(dc.getComercio()));
				  					
				  				}
								
								objeto_ar[6] = comVO.getDescripcion();
											
						}
						
//						objeto_ar[9] = "Enviado"; 
						objeto_ar[8] = "";
						objeto_ar[9] = "";
							
						t_model_impresion.addRow(objeto_ar);
						
						for(int j = 1;j < ar_articulos.size();j++){
							
							Object [] o = new Object[10];
							o[1] = ar_articulos.get(j);
							
							t_model_impresion.addRow(o);
							
						}
						
						Object [] o = new Object[10];
						o[0] = "Notas";
						
						t_model_impresion.addRow(o);
						//t_model_impresion.addRow(new Object[]{Boolean.FALSE,null,null,null});
					}
						
				}	
			}
			if(imprime){
				
				 try {
					 
					 Date d = new Date();
					 java.sql.Date hoy = new java.sql.Date(d.getTime());
					 
	                PrintPaneDespacho printPane = new PrintPaneDespacho(t_model_impresion, hoy);

	                PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
	                aset.add(MediaSizeName.ISO_A4);
//	                aset.add(new PrinterResolution(300, 300, PrinterResolution.DPI));
	                aset.add(new PrinterResolution(300, 300, PrinterResolution.DPI));
	                aset.add(DialogTypeSelection.NATIVE);
	                
	                PrinterJob pj = PrinterJob.getPrinterJob();
	                pj.setPrintable(printPane);

	                PageFormat pf = pj.defaultPage();
	                
	                pf.setOrientation(PageFormat.LANDSCAPE);

	                if (pj.printDialog(aset)) {
	                    try {
	                        pj.print(aset);
	                    } catch (PrinterException ex) {
	                        ex.printStackTrace();
	                    }
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
			}
			else Mensajes.getMensaje_impresionError();
			
		}
	}
	
	public void tabla_impresa(ArrayList<Object[]> ar){
		
		int cantidad = 0;
		
		
		
		
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
