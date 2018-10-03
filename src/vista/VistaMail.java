package vista;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controlador.ControladorArticulo;
import controlador.ControladorCliente;
import controlador.ControladorDomicilioComercial;
import controlador.ControladorLocalidad;
import controlador.ControladorMonto_trasladado;
import controlador.ControladorObservaciones;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorPrestamo;
import controlador.ControladorRefinanciacion_ex;
import controlador.ControladorRefinanciacion_in;
import controlador.ControladorUsuario;
import controlador.ControladorVendedor;
import controlador.ControladorZona;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.ComercioVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.LocalidadVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import vista.VistaGestionProveedores.CustomJToolTip;

public class VistaMail extends JInternalFrame implements ActionListener{

	private VistaBuscarCliente vista_buscar_cliente;
	private ControladorVendedor _controladorVendedor;
	private ControladorZona _controladorZona;
	private ControladorLocalidad _controladorLocalidad;
	private ControladorPedidos _controladorPedido;
	private ControladorArticulo _controladorArticulo;
	private ControladorCliente controladorCliente;
	private ControladorPagoDiario _controladorPagoDiario;
	private ControladorRefinanciacion_ex _controladorRef_ex;
	private ControladorRefinanciacion_in _controladorRef_in;
	private ControladorMonto_trasladado _controladorMonto_t;
	private ControladorObservaciones _controladorObservaciones;
	private ControladorDomicilioComercial _controladorDomCom;
	private ControladorPrestamo _controladorPrestamo;
	private ControladorUsuario _controladorUsuario;
	private BusquedaEntities _busqueda_entities;
	private VistaBuscarPedidos_porClientes vp;
	private static  VistaPrincipal _vista_principal;
	
	private JPanel pClientes_mail = new JPanel();
	private JScrollPane scr = new JScrollPane();
	
	 /*private JButton buscar = new JButton(){
	        //override the JButtons createToolTip method
	        @Override
	        public JToolTip createToolTip() {
	            return (new CustomJToolTip(this));
	        }
	    };;*/
	private JButton buscar = new JButton("Buscar");
	private JButton buscar_comercio = new JButton("...");
	private JButton buscar_comercio2 = new JButton("...");
	private JButton buscar_comercio3 = new JButton("...");
	private JButton enviar = new JButton("enviar seleccionados");
	private JButton enviar_todos = new JButton("enviar todos");
	private JButton exportar_select = new JButton("exportar seleccionados");
	private JButton exportar_todos = new JButton("exportar todos");
	private JTable tabla = new JTable();    
	private DefaultTableModel t_model;
	private JTextField efectividad = new JTextField(5);
	private JTextField asunto = new JTextField(20);
	private JTextField comercioTF;
	private JTextField comercio2TF;
	private JTextField comercio3TF;
	private JTextArea mensajeTA = new JTextArea(5,60);
	private JComboBox comercios = new JComboBox();
	//private JComboBox estados = new JComboBox();
	private JCheckBox exCB = new JCheckBox();
	private JCheckBox operandoCB = new JCheckBox();
	private JCheckBox nuevoCB = new JCheckBox();
	private JCheckBox todosCB = new JCheckBox();
	private JLabel lComercio, lComercio2, lComercio3;
	private JPanel pId_comercio, pId_comercio2, pId_comercio3;
	private JPanel pMail = new JPanel();
	
	private int opcion_comercio = 0;
	
	private boolean[] canEdit_clientes= new boolean[]{
            false, false, false, false, false, false,false
    };
	
	 private float[] columnWidthPercentage = {25.0f, 25.0f, 15.0f, 10.0f,10, 25.0f};
	
	public VistaMail(){
		//super(_vista_principal, "Clientes atrasados", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		super("Envio de emails", true, true, true, true);
		 this.setLayout(new BorderLayout());
		
		 buscar.addActionListener(this);
		 buscar_comercio.addActionListener(this);
		 buscar_comercio2.addActionListener(this);
		 buscar_comercio3.addActionListener(this);
		 enviar.addActionListener(this);
		 enviar_todos.addActionListener(this);
		 exportar_select.addActionListener(this);
		 exportar_todos.addActionListener(this);
		 
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     setSize(dim.width*80/100, dim.height*60/100);
	     //setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	     //this.setResizable(false);
		
	     setClosable(true); 
	        setIconifiable(true); 
	        setMaximizable(true); 
	        setResizable(true); 
	     
	     tabla.getTableHeader().setReorderingAllowed(false);
		    tabla.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 12));
	     
	     t_model = new DefaultTableModel(null, getColumnas()){
			 
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit_clientes[columnIndex];
	            }
		};
		tabla.setModel(t_model);
		this.resizeColumns();
		
		mensajeTA.setLineWrap(true);
		/*imprimir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					
				try {
					 
					 Date d = new Date();
					 java.sql.Date hoy = new java.sql.Date(d.getTime());
					 
	                PrintPaneClientes_atrasados printPane = new 
	                		PrintPaneClientes_atrasados(t_model, hoy);

	                PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
	                aset.add(MediaSizeName.ISO_A4);
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
			
		});*/
		
	     //pClientes_atrasados.setLayout(new BoxLayout(pPedidos_historicos, BoxLayout.Y_AXIS));
	     
	     
	    // pClientes_atrasados.setPreferredSize(new Dimension(850, 600));
		
		pId_comercio = new JPanel();
		pId_comercio2 = new JPanel();
		pId_comercio3 = new JPanel();
  		lComercio = new JLabel();
  		lComercio2 = new JLabel();
  		lComercio3 = new JLabel();
  		
  		comercioTF = new JTextField(2);
  		comercioTF.setFont(new Font("Arial", Font.PLAIN, 16));
  		comercioTF .addFocusListener(new FocusListener(){
  			
  			@Override
  			public void focusGained(FocusEvent e) {
  				// TODO Auto-generated method stub
  				comercioTF .setBackground(new Color(183, 242, 113));
  			}
  			
  			@Override
  			public void focusLost(FocusEvent e) {
  				// TODO Auto-generated method stub
  				if(_controladorDomCom.buscarComercio(comercioTF.getText())!=null){
  					
  					ComercioVO cVO = _controladorDomCom.buscarComercio(comercioTF.getText());
  					lComercio.setText(cVO.getDescripcion());
  					
  				}
  					
  				else{
  					
  					lComercio.setText("");
  					comercioTF.setText("");
  				}
  				
  				
  				comercioTF.setBackground(new Color(255, 255, 255));
  			}
  			
  			
  		});
  		comercio2TF = new JTextField(2);
  		comercio2TF.setFont(new Font("Arial", Font.PLAIN, 16));
  		comercio2TF .addFocusListener(new FocusListener(){
  			
  			@Override
  			public void focusGained(FocusEvent e) {
  				// TODO Auto-generated method stub
  				comercio2TF .setBackground(new Color(183, 242, 113));
  			}
  			
  			@Override
  			public void focusLost(FocusEvent e) {
  				// TODO Auto-generated method stub
  				if(_controladorDomCom.buscarComercio(comercio2TF.getText())!=null){
  					
  					ComercioVO cVO = _controladorDomCom.buscarComercio(comercio2TF.getText());
  					lComercio2.setText(cVO.getDescripcion());
  					
  				}
  				
  				else{
  					
  					lComercio2.setText("");
  					comercio2TF.setText("");
  				}
  				
  				
  				comercio2TF.setBackground(new Color(255, 255, 255));
  			}
  			
  			
  		});
  		comercio3TF = new JTextField(2);
  		comercio3TF.setFont(new Font("Arial", Font.PLAIN, 16));
  		comercio3TF .addFocusListener(new FocusListener(){
  			
  			@Override
  			public void focusGained(FocusEvent e) {
  				// TODO Auto-generated method stub
  				comercio3TF .setBackground(new Color(183, 242, 113));
  			}
  			
  			@Override
  			public void focusLost(FocusEvent e) {
  				// TODO Auto-generated method stub
  				if(_controladorDomCom.buscarComercio(comercio3TF.getText())!=null){
  					
  					ComercioVO cVO = _controladorDomCom.buscarComercio(comercio3TF.getText());
  					lComercio3.setText(cVO.getDescripcion());
  					
  				}
  				
  				else{
  					
  					lComercio3.setText("");
  					comercio3TF.setText("");
  				}
  				
  				
  				comercio3TF.setBackground(new Color(255, 255, 255));
  			}
  			
  			
  		});
  		
  		GridLayout gl = new GridLayout(0,2);
  		pId_comercio.setLayout(gl);
  		pId_comercio2.setLayout(gl);
  		pId_comercio3.setLayout(gl);
  		
  		pId_comercio.add(comercioTF);
  		pId_comercio.add(buscar_comercio);
		
  		pId_comercio2.add(comercio2TF);
  		pId_comercio2.add(buscar_comercio2);
  		
  		pId_comercio3.add(comercio3TF);
  		pId_comercio3.add(buscar_comercio3);
  		
  		JPanel pIntegra_comercio = new JPanel();
  		JPanel pIntegra_estados = new JPanel();
  		
  		todosCB.addItemListener(new ItemListener(){

  			@Override
  			public void itemStateChanged(ItemEvent e) {
  				// TODO Auto-generated method stub
  				
  				Object source = e.getItemSelectable();
  				if(todosCB.isSelected()){
  					
  					exCB.setSelected(true);
  					operandoCB.setSelected(true);
  					nuevoCB.setSelected(true);
  					
  					
  				}
  				else{
  					
  					exCB.setSelected(false);
  					operandoCB.setSelected(false);
  					nuevoCB.setSelected(false);  					
  				}
  			}
  	    	   
  	    	  
  	       });
  		
  		exCB.setText("ex");
  		operandoCB.setText("operando");
  		nuevoCB.setText("nuevo");
  		todosCB.setText("todos");
  		pIntegra_estados.add(exCB);
  		pIntegra_estados.add(operandoCB);
  		pIntegra_estados.add(nuevoCB);
  		//pIntegra_estados.add(todosCB);
  		pIntegra_estados.setOpaque(false);
  		Border borde_comercio = BorderFactory.createTitledBorder(null, "Comercio", 
  	   	    	TitledBorder.CENTER, TitledBorder.TOP,
  	   	    	new Font("Arial",Font.PLAIN,14), Color.BLACK);
  	       pIntegra_comercio.setBorder(borde_comercio);
  	       Border borde_estado = BorderFactory.createTitledBorder(null, "Estado", 
  	    		   TitledBorder.CENTER, TitledBorder.TOP,
  	    		   new Font("Arial",Font.PLAIN,14), Color.BLACK);
  	       pIntegra_estados.setBorder(borde_estado);
  		
  	      pIntegra_comercio.add(pId_comercio); 
  	      pIntegra_comercio.add(lComercio); 
  	      pIntegra_comercio.add(pId_comercio2); 
  	      pIntegra_comercio.add(lComercio2); 
  	      pIntegra_comercio.add(pId_comercio3); 
  	      pIntegra_comercio.add(lComercio3);
  	      
  	      pIntegra_comercio.setOpaque(false); 
	     scr.setPreferredSize(new Dimension(850, 250));
	     scr.setViewportView(tabla);
	    // scr.add(pPedidos_historicos);
	     pClientes_mail.add(scr);
	     pClientes_mail.setBackground(Color.WHITE);
	     //cargarComboEstados();
	     
	     JPanel pComandos = new PanelGraduado(new Color(248, 249, 249), new Color(229, 231, 233));
	     
	     pComandos.setPreferredSize(new Dimension(1200,100));
	     
	     final DecimalFormat df = new DecimalFormat("#.##");
	  
	        final DoubleJSlider slider = new DoubleJSlider(0, 100, 0, 1);
	        slider.addChangeListener(new ChangeListener(){
	            @Override
	            public void stateChanged(ChangeEvent e) {
	                efectividad.setText(df.format(slider.getScaledValue()));
	            }
	        });
	        efectividad.addKeyListener(new KeyAdapter(){
	            @Override
	            public void keyReleased(KeyEvent ke) {
	                String typed = efectividad.getText();
	                slider.setValue(0);
	                if(!typed.matches("\\d+(\\.\\d*)?")) {
	                    return;
	                }
	                double value = Double.parseDouble(typed)*slider.scale;
	                slider.setValue((int)value);
	            }
	        });
	     
	        efectividad.setFont(new Font("Arial", Font.BOLD, 12));
	     
	     JLabel lEfectividad = new JLabel();
	     lEfectividad.setText("Efectividad desde...");
	     //pComandos.add(lEfectividad);
	     
	     JPanel pEfec = new JPanel();
	     //pEfec.setBackground(Color.white);
	     pEfec.setOpaque(false);
	     pEfec.setLayout(new BoxLayout(pEfec, BoxLayout.Y_AXIS));
	     
	     pEfec.add(efectividad);
	     pEfec.add(slider);
	     
	     Border borde_efec = BorderFactory.createTitledBorder(null, "Efectividad desde...", 
	  	   	    	TitledBorder.CENTER, TitledBorder.TOP,
	  	   	    	new Font("Arial",Font.PLAIN,14), Color.BLACK);
	     pEfec.setBorder(borde_efec);
	     
	     pComandos.add(pEfec);
	     pComandos.add(pIntegra_comercio);
	     pComandos.add(pIntegra_estados);
	     pComandos.add(buscar);
	     
	     pMail.setLayout(new BoxLayout(pMail, BoxLayout.Y_AXIS));
	     
	     pMail.setBackground(Color.WHITE);
	     
	    /* Border borde_mail = BorderFactory.createTitledBorder(null, "Redacción de email", 
	    		   TitledBorder.CENTER, TitledBorder.TOP,
	    		   new Font("Arial",Font.PLAIN,14), Color.BLACK);
	       pMail.setBorder(borde_mail);*/
	     
	     JPanel pAsunto = new JPanel();
	     Border borde_asunto = BorderFactory.createTitledBorder(null, "Asunto", 
	    		   TitledBorder.CENTER, TitledBorder.TOP,
	    		   new Font("Arial",Font.PLAIN,14), Color.BLACK);
	       pAsunto.setBorder(borde_asunto);
	       
	       pAsunto.add(asunto);
	     
	     //pMail.add(pAsunto);
	     
	     JPanel pMensaje = new JPanel();
	     //pMensaje.setLayout(new BorderLayout());
	     Border borde_mensaje = BorderFactory.createTitledBorder(null, "Mensaje", 
	    		   TitledBorder.CENTER, TitledBorder.TOP,
	    		   new Font("Arial",Font.PLAIN,14), Color.BLACK);
	       pMensaje.setBorder(borde_mensaje);
	       
	       JScrollPane scr_m = new JScrollPane();
	       scr_m.setViewportView(mensajeTA);
	      
	       //pMensaje.add(pAsunto, BorderLayout.WEST);
	       pMensaje.add(scr_m);
	       
	     //pMail.add(pMensaje);
	     
	     pAsunto.setOpaque(false);
	     pMensaje.setOpaque(false);
	     
	     JPanel pComando_mail = new JPanel();
	     pComando_mail.add(enviar);
	     pComando_mail.add(enviar_todos);
	    

	     JPanel pComando_export = new JPanel();
	     pComando_export.add(exportar_select);
	     pComando_export.add(exportar_todos);
	     
	     pMail.add(pComando_export);
	     
	     JScrollPane scr_comandos = new JScrollPane();
	     scr_comandos.setPreferredSize(new Dimension(600,100));
	     
	     scr_comandos.setViewportView(pComandos);
	     
	     add(scr_comandos, BorderLayout.PAGE_START);
	     add(pClientes_mail, BorderLayout.CENTER);
	     add(pMail, BorderLayout.SOUTH);
	}
	
	public void iniciar(){
		
		limpiar(tabla);
		
		String ex ="";
		String operando ="";
		String nuevo ="";
		
			if(exCB.isSelected()) ex = "ex";
			if(operandoCB.isSelected()) operando = "operando";
			if(nuevoCB.isSelected()) nuevo = "nuevo";
		
		
		ArrayList<ClienteVO> ar = controladorCliente.buscarClienteFiltros(efectividad, comercioTF,
				comercio2TF, comercio3TF,
				ex, operando, nuevo);
		
		int cantidad = 0;
		
		if(ar!=null){
			
			for(ClienteVO cVO : ar){
				
				ArrayList<DomicilioComercialVO>  dVO = _controladorDomCom.
						buscarDomicilioComercial2(Integer.toString(cVO.getDni()));
				ComercioVO comVO = null;
				if(dVO!=null){
					
					comVO = _controladorDomCom.buscarComercio(Integer.toString(dVO.get(0).getComercio()));
					
				}
					
				Object [] datos = new Object[6];
						
				datos[0] = cVO.getDni();
				datos[1] = cVO.getNombre() + " " + cVO.getApellido();
				datos[2] = cVO.getEstado();
				if(comVO!=null)
					datos[3] = comVO.getDescripcion();  
				datos[4] = cVO.getEfectividad();
				datos[5] = cVO.getEmail();
					
				
				t_model.addRow(datos);
			}
					
		}
				
		//alto_filas();
		tabla.getPreferredScrollableViewportSize().setSize(800,
				tabla.getRowHeight() * cantidad);
		
		 centrar_columnas(tabla);
		//ancho_columnas();
		pClientes_mail.updateUI();
		
		
	}
	
	 public void limpiar(JTable tabla){
	    	
	    	if(tabla.getRowCount() > 0){
				
				DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
				
				int contFila = tabla.getRowCount();
				
				for(int i = 0; i < contFila; i++)
				
					modelo.removeRow(0);
			}
	    	//obL.setText("");
	    	//observaciones.setText("");
	  }
	
	 /*public void cargarComboEstados(){
			
			String est [] = {"ex", "operando", "nuevo", "baja"};
			
			for(String s : est) estados.addItem(s);
		}*/
	 
	 public void cargarComboComercios(){
		 
		 ArrayList<String> ar = controladorCliente.buscarComercios(); 
		 
		 if(ar!=null){
			 
			 String com [] = new String[ar.size()];
			 
			 for(int i = 0; i < com.length; i++){
				 
				 com[i] = ar.get(i);
				 System.out.println(com[i]);
			 }
			 
			 for(String s : com) comercios.addItem(s);
			 
		 }
		 else System.out.println("array null");
	 }
	 
	private void ancho_columnas(){
		
		for(int i = 0; i < tabla.getColumnCount(); i++){
			
			if(i == 2) tabla.getColumnModel().getColumn(i).setPreferredWidth(250);
			else if(i==1 || i==3)tabla.getColumnModel().getColumn(i).setPreferredWidth(50);
			else tabla.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
	
	}
	
	
	private void centrar_columnas(JTable tabla){
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla.getColumnCount(); i ++)
			
			tabla.getColumnModel().getColumn(i).setCellRenderer(cent);
		
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
		
		public void setControladorRefinanciacion_ex(ControladorRefinanciacion_ex _controladorRef_ex){
			
			this._controladorRef_ex = _controladorRef_ex;
		}
		
		public void setControladorRefinanciacion_in(ControladorRefinanciacion_in _controladorRef_in){
			
			this._controladorRef_in = _controladorRef_in;
		}
		public void setControladorDomicilioComercial(ControladorDomicilioComercial _controladorDomCom){
			
			this._controladorDomCom = _controladorDomCom;
		}
		
		public void setControladorMonto_trasladado(ControladorMonto_trasladado _controladorMonto_t){
			
			this._controladorMonto_t = _controladorMonto_t;
		}
		
		public void setControladorObservaciones(ControladorObservaciones _controladorObservaciones){
			
			this._controladorObservaciones = _controladorObservaciones;
		}
		
		public void setControladorCliente(ControladorCliente controladorCliente){
			
			this.controladorCliente = controladorCliente;
		}
		
		public void setControladorUsuario(ControladorUsuario _controladorUsuario){
			
			this._controladorUsuario = _controladorUsuario;
		}
		
		public void setControladorPedidos(ControladorPedidos _controladorPedido){
			
			this._controladorPedido = _controladorPedido;
		}
		
		public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
			
			this.vista_buscar_cliente = vista_buscar_cliente;
		}
	
		public JTextField getComercioTF(){
			
			return comercioTF;
		}
		
		public JLabel getComercioL(){
			
			return lComercio;
		}
		public JTextField getComercio2TF(){
			
			return comercio2TF;
		}
		
		public JLabel getComercio2L(){
			
			return lComercio2;
		}
		public JTextField getComercio3TF(){
			
			return comercio3TF;
		}
		
		public JLabel getComercio3L(){
			
			return lComercio3;
		}
		
		public int getOpcion_comercio(){
			
			return opcion_comercio;
		}
		
		private String [] getColumnas(){
			
			String columna [] = new String []{ "DNI", "Cliente","Estado", "Comercio", "Efectividad de pago", "Email"};
			
			return columna;
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
			_busqueda_entities.setPanel("vista_mail");
			
			if(e.getSource()==buscar_comercio){
				
				opcion_comercio = 1;
				
				_controladorDomCom.buscarComercioAll();
				_controladorDomCom.mostrarBusquedaEntities("Buscar comercio");
				_busqueda_entities.setTipoBusqueda(21);
			}
			if(e.getSource()==buscar_comercio2){
				
				opcion_comercio = 2;
				_controladorDomCom.buscarComercioAll();
				_controladorDomCom.mostrarBusquedaEntities("Buscar comercio");
				_busqueda_entities.setTipoBusqueda(21);
			}
			if(e.getSource()==buscar_comercio3){
				
				opcion_comercio = 3;
				_controladorDomCom.buscarComercioAll();
				_controladorDomCom.mostrarBusquedaEntities("Buscar comercio");
				_busqueda_entities.setTipoBusqueda(21);
			}
			if(e.getSource()==buscar){
				
				iniciar();
			}
			
			if(e.getSource()==enviar){
				
				if(tabla.getSelectedRowCount()>0){
					
					ventana_path();
					
					/*String path = SendMail.ventana_path();
					
					SendMail.enviar(tabla, asunto.getText(), mensajeTA.getText(), path);*/
					
					//SendMail.enviar(tabla, asunto.getText(), mensajeTA.getText(), path);
				}
				else Mensajes.getMensaje_sinFilasSeleccionadas();
			}
			if(e.getSource()==exportar_select){
				
				if(tabla.getSelectedRowCount()>0){
					
					ventana_path();
					
					/*String path = SendMail.ventana_path();
					
					SendMail.enviar(tabla, asunto.getText(), mensajeTA.getText(), path);*/
					
					//SendMail.enviar(tabla, asunto.getText(), mensajeTA.getText(), path);
				}
				else Mensajes.getMensaje_sinFilasSeleccionadas();
			}
			if(e.getSource()==exportar_todos){
					
					if(tabla.getRowCount()>0){
						
						tabla.setRowSelectionInterval(0, tabla.getRowCount()-1);
						ventana_path();
						
					}
								
			}
			
		}
		
		private void ventana_path(){
			
			boolean res = false;
			
			JFileChooser chooser = new JFileChooser();
	        FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        		"TEXT FILES", "txt", "text");
	        chooser.setFileFilter(filter);
	        int returnVal = chooser.showSaveDialog(this);
	        
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	        	
	        	try {
	        	res = crearArchivo_mail(chooser.getSelectedFile().getAbsolutePath());
					//res = rt.realizar_restore(chooser.getSelectedFile().getAbsolutePath());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	  			 
	            if(res) JOptionPane.showMessageDialog(this, "Archivo guardado exitosamente"); 
	            
	            else JOptionPane.showMessageDialog(this, "Error al guardar el archivo");
	        }
	       				
		}
		
		public boolean crearArchivo_mail(String path){
			
			try{
				int [] ar =  tabla.getSelectedRows();
				
				for(int i = 0; i < tabla.getSelectedRowCount(); i++){
					
					FileWriter fw = new FileWriter(path, true);
					
					BufferedWriter escribir = new BufferedWriter(fw);
					
					String linea = tabla.getModel().getValueAt(ar[i], 5).toString() + ";";
					
					escribir.write(linea);
					
					escribir.newLine();
					
					escribir.close();
					
				}
				
				return true;
				
			}
			catch(IOException e){
				
				e.printStackTrace();;
			}
			return false;
		}
		
		 private void resizeColumns() {
			    int tW = tabla.getWidth();
			    TableColumn column;
			    TableColumnModel jTableColumnModel = tabla.getColumnModel();
			    int cantCols = jTableColumnModel.getColumnCount();
			    for (int i = 0; i < cantCols; i++) {
			        column = jTableColumnModel.getColumn(i);
			        int pWidth = Math.round(columnWidthPercentage[i] * tW);
			        column.setPreferredWidth(pWidth);
			    }
			}
		 
		 class DoubleJSlider extends JSlider {

			    final int scale;

			    public DoubleJSlider(int min, int max, int value, int scale) {
			        super(min, max, value);
			        this.scale = scale;
			    }

			    public double getScaledValue() {
			        return ((double)super.getValue()) / this.scale;
			    }
			}
}
