package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolTip;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

/*import Main.MenuPrincipal;
import Main.MenuPrincipal.Arbol;
import Main.MenuPrincipal.Arbol.Render;
import Modelo.Backup;
import Modelo.Barra_herramientas;
import Modelo.Cartas;
import Modelo.Menu_formato;*/
import controlador.ControladorCliente;
import controlador.ControladorDomicilioComercial;
import controlador.ControladorDomicilioParticular;
import controlador.ControladorEmpleado;
import controlador.ControladorLocalidad;
import controlador.ControladorUsuario;
import controlador.Principal;
import modelo.LogicaCliente;
import modelo.LogicaDomicilioComercial;
import modelo.LogicaDomicilioParticular;
import modelo.LogicaEmpleado;
import modelo.LogicaLocalidad;
import modelo.LogicaUsuario;
import modelo.Mensajes;
import modelo_vo.Backup;
import modelo_vo.Restore;
import vista.VistaBuscarCliente.CustomJToolTip;

public class VistaPrincipal extends JFrame {

	private ControladorCliente _controladorCliente;
	private ControladorDomicilioParticular _controladorDomPart;
	private ControladorDomicilioComercial _controladorDomCom;
	private VistaBuscarCliente vista_buscar_cliente;
	private VistaAltaCliente vista_alta_cliente;
	private VistaBuscarUsuario vista_buscar_usuario;
	private Barra_herramientas bh_principal;
	private VistaDespacho_diario dp;
	
	private JPanel panel, panel_pres, pIntegra, pBarra;
	private JLabel lUsuario;
	private Backup bp;
	private Restore rt;
	
	public  Cartas panel_cartas;
	
	public JTabbedPane pestañas;
	
	public Pestaña _pestaña;
	
	private Panel_fondo _panel_fondo;
	private Principal _principal;
	
	private JDesktopPane desktop;
	
	private JButton backup = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };;
    private JButton contrasena = new JButton(){
    	//override the JButtons createToolTip method
    	@Override
    	public JToolTip createToolTip() {
    		return (new CustomJToolTip(this));
    	}
    };;
	
 // Specify the look and feel to use by defining the LOOKANDFEEL constant
    // Valid values are: null (use the default), "Metal", "System", "Motif",
    // and "GTK"
    final static String LOOKANDFEEL = "Metal";
    
    // If you choose the Metal L&F, you can also choose a theme.
    // Specify the theme to use by defining the THEME constant
    // Valid values are: "DefaultMetal", "Ocean",  and "Test"
    final static String THEME = "Tema";
    
	public VistaPrincipal(){
		
		//JFrame.setDefaultLookAndFeelDecorated(true);
		setTitle("Halcones Diarios");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds(0,0,screenSize.width, screenSize.height);
		
		setResizable(false);
		
		setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		 desktop = new JDesktopPane();
		 desktop = new javax.swing.JDesktopPane() {
			    private Image image;
			    {
			        try {
			            image = ImageIO.read(this.getClass().getResource("/fondo_inicio2.jpg"));
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			    }

			    @Override
			    protected void paintComponent(Graphics g) {
			        super.paintComponent(g);
			        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			    }
			};
		 
		 desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		 //this.setContentPane(desktop);
		
		/*Image imagen = null;
		try {
			imagen = ImageIO.read(new FileInputStream("imagenes/nudo_icono.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(imagen!=null)
		
			this.setIconImage(imagen);*/
		
		/*pBarra = new JPanel();
		
		//panel_cartas = new Cartas();
		
		panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		pBarra.setLayout(new BorderLayout());
		
		pBarra.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
		pBarra.setBackground(new Color(234, 242, 248));
		
		Font fuenteB = new Font("Verdana", Font.PLAIN, 20);
		
		JLabel titulo = new JLabel("Sistema de gestión");
		
		titulo.setFont(fuenteB);
		
		titulo.setForeground(new Color(0, 0 ,0));
		
		Arbol _arbol = new Arbol();
		
		//bh = new Barra_herramientas();
		
		Container contentPane = this.getContentPane();
		
	    contentPane.add(bh, BorderLayout.NORTH);
		
		pBarra.add(titulo);
		
		panel.add(pBarra, BorderLayout.NORTH);
		
		panel.add(_arbol, BorderLayout.WEST);
		
		bp = new Backup();
		
		backup = new JButton("Realizar backup");
		
		backup.addActionListener(new ActionListener(){

   			@Override
   				public void actionPerformed(ActionEvent e) {
   				// TODO Auto-generated method stub
   				
   					ventana_path();
   				}
   				
   			});
		
		panel.add(backup, BorderLayout.SOUTH);
		
		JScrollPane src = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JSplitPane pane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, 
                panel, panel_cartas );
		
		pane.setDividerLocation(270);
		
		add(pane);
		
		//panel_cartas.setVistaAltaCliente(vista_alta_cliente);
		
		bh.setCartas(panel_cartas);
		
		//Menu_formato mf = new Menu_formato();
		
		//this.setJMenuBar(mf);
	
		setVisible(true);*/
	}
	
	public void iniciar(){
		
		//this.initLookAndFeel();
		
		//this.initlf();
		
		Image abierto = null;
        Image cerrado = null;
        Image hoja = null;
        
        try {
			abierto = ImageIO.read(getClass().getResource("/open_folder2.png"));
			cerrado = ImageIO.read(getClass().getResource("/closed_folder2.png"));
			hoja = ImageIO.read(getClass().getResource("/hoja3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UIManager.put("Tree.closedIcon", new ImageIcon(cerrado));
		UIManager.put("Tree.openIcon", new ImageIcon(abierto));
		UIManager.put("Tree.leafIcon", new ImageIcon(hoja));
		
		pBarra = new JPanel();
		
		//panel_cartas = new Cartas();
		
		panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		pBarra.setLayout(new BorderLayout());
		
		pBarra.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
		pBarra.setBackground(new Color(234, 242, 248));
		
		Font fuenteB = new Font("Verdana", Font.PLAIN, 20);
		
		JLabel titulo = new JLabel("Sistema de gestión");
		
		titulo.setFont(fuenteB);
		
		titulo.setForeground(new Color(0, 0 ,0));
		
		Arbol _arbol = new Arbol();
		
		//bh = new Barra_herramientas();
		
		Container contentPane = this.getContentPane();
		
	    //contentPane.add(bh, BorderLayout.NORTH);
		
	    bh_principal.setVistaPrincipal(this);
	    
		pBarra.add(titulo, BorderLayout.NORTH);
		lUsuario = new JLabel();
		lUsuario.setFont(new Font("Arial", Font.BOLD, 14));
		
		panel.add(pBarra, BorderLayout.NORTH);
		
		panel.add(_arbol, BorderLayout.WEST);
		
		
		bp = new Backup();
		rt = new Restore();
		
		backup.addActionListener(new ActionListener(){

   			@Override
   				public void actionPerformed(ActionEvent e) {
   				// TODO Auto-generated method stub
   				
   					ventana_path();
   				}
   				
   			});
		contrasena.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VistaCambioContrasena vcc = new VistaCambioContrasena();
				ControladorUsuario cu = new ControladorUsuario();
				LogicaUsuario lu = new LogicaUsuario();
				cu.setLogicaUsuario(lu);
				
				vcc.setControladorUsuario(cu);
				vcc.setVisible(true);
				//ventana_path();
			}
			
		});
		
		_panel_fondo = new Panel_fondo();
		
		JPanel pIntegraSur = new JPanel();
		pIntegraSur.setLayout(new BoxLayout(pIntegraSur, BoxLayout.Y_AXIS));
		pIntegraSur.setOpaque(false);
		
		BH_backup bh = new BH_backup(backup, contrasena);
		
		bh.setVistaPricipal(this);
		
		JPanel pUsuario = new JPanel();
		pUsuario.setBackground(new Color(234, 250, 241));
		pUsuario.add(lUsuario);
		pIntegraSur.add(pUsuario);
		pIntegraSur.add(bh);
		
		panel.add(pIntegraSur, BorderLayout.SOUTH);
		
		JScrollPane src = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		//JSplitPane pane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, 
        //        panel, panel_cartas );
		
		//pestañas.addTab("Inicio", null, _panel_fondo, "Inicio del sistema");
		JPanel pIntegraDesktop = new JPanel();
		pIntegraDesktop.setLayout(new BorderLayout());
		
		pIntegraDesktop.add(bh_principal, BorderLayout.PAGE_START);
		pIntegraDesktop.add(desktop, BorderLayout.CENTER);
		
		JScrollPane scrpane = new JScrollPane(pIntegraDesktop);
		
		_pestaña.agregar("Inicio", scrpane);
		
		JSplitPane pane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, 
                panel, _pestaña );
		
		/*Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		  
		pane.setMinimumSize( 
	    		new Dimension(dim.width*5/100, dim.height*5/100));
	    pane.setMaximumSize( 
	    		new Dimension(dim.width*10/100, dim.height*10/100)); 
		  
	    pane.setPreferredSize(getPreferredSize());*/
		pane.setMinimumSize(new Dimension(500,800));
		pane.setMaximumSize(new Dimension(800,800));
		//pane.setPreferredSize(getPreferredSize());
		pane.setDividerLocation(270);
		
		this.add(pane);
		//this.setContentPane(desktop);
		//panel_cartas.setVistaAltaCliente(vista_alta_cliente);
		
		//bh.setCartas(panel_cartas);
		
		//Menu_formato mf = new Menu_formato();
		
		//this.setJMenuBar(mf);
	
		//this.pack();
		setVisible(true);
		//this.pack();
		
	}
	
	public  void setPrincipal(Principal _principal){
		
		this._principal = _principal;
	}
	
	
	public void setBarra_herramientas(Barra_herramientas bh){
		
		this.bh_principal = bh;
	}
	
	public void setControladorCliente(ControladorCliente _controladorCliente){
		
		this._controladorCliente = _controladorCliente;
	}
	
	public void setControladorDomicilioParticular(ControladorDomicilioParticular _controladorDomPart){
		
		this._controladorDomPart = _controladorDomPart;
	}

	public void setControladorDomicilioComercial(ControladorDomicilioComercial _controladorDomCom){
	
		this._controladorDomCom = _controladorDomCom;
	}
	
	public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
		
		this.vista_buscar_cliente = vista_buscar_cliente;
		//add(vista_buscar_cliente);
	}
	
	public void setVistaBuscarUsuario(VistaBuscarUsuario vista_buscar_usuario){
		
		this.vista_buscar_usuario = vista_buscar_usuario;
		
	}
	
	public void setVistaAltaCliente(VistaAltaCliente vista_alta_cliente){
		
		this.vista_alta_cliente = vista_alta_cliente;
		
	}
	
	public void setVistaDespacho_diario(VistaDespacho_diario dp){
		
		this.dp = dp;
		
	}

	public JLabel getLabelUsuario(){
		
		return lUsuario;
	}
	
	private void ventana_path(){
		
		boolean res = false;
		
		JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        		"Archivo SQL", "sql");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	
        	try {
        	res = bp.realizar_backup(chooser.getSelectedFile().getAbsolutePath());
				//res = rt.realizar_restore(chooser.getSelectedFile().getAbsolutePath());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			 
            if(res) JOptionPane.showMessageDialog(this, "Base de datos guardada exitosamete"); 
            
            else JOptionPane.showMessageDialog(this, "Error en la copia de base de datos");
        }
       				
	}
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

		MenuPrincipal miMenu = new MenuPrincipal();
		
		miMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		miMenu.setVisible(true);
		
	}*/
	
	class Arbol extends JPanel{
		
		private JTree arb;
		
		DefaultMutableTreeNode n1 = null;
	    DefaultMutableTreeNode n2 = null;
	    DefaultMutableTreeNode n3 = null; 
	    DefaultMutableTreeNode n4 = null;

	    Object obj = null;
	    
		public Arbol() {
		  
			setLayout(new BorderLayout());
			
			setPreferredSize(new Dimension(1450, 800));
			
			setBackground(new Color(234, 250, 241));
			
		    DefaultMutableTreeNode topR =
		        new DefaultMutableTreeNode("Inicio");
		 
		    crear_nodos(topR);
		    arb = new JTree(topR);
		
		    arb.setCellRenderer(new Render());
		    
		    arb.setFont(new Font("Verdana", Font.PLAIN, 16));
		    
		    arb.getSelectionModel().setSelectionMode
		    (TreeSelectionModel.SINGLE_TREE_SELECTION);
		    
		    arb.addMouseListener(new MouseAdapter(){
		    	
		    	 public void mousePressed(MouseEvent mouseEvent) {
				        JTree arbol =(JTree) mouseEvent.getSource();
				        Point point = mouseEvent.getPoint();
				        //int row = arbol.rowAtPoint(point);
				        if (mouseEvent.getClickCount() == 2) {
				        	
				        	DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				                       arb.getLastSelectedPathComponent();
				                if (node == null) return;
				                Object nodeInfo = node.getUserObject();
				                
				                int i = 0;
				                
				                switch(nodeInfo.toString()){
				                
				                	case "Alta cliente":
				                	break;
				                	
				                	case "Busqueda cliente":
				                		
				                		JScrollPane scroll = new JScrollPane
				                					(_principal.getComponente("vista_buscar_cliente"), 
				                							JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				                							JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
										
										_pestaña.agregar("Buscar cliente", scroll);
										i = _pestaña.getTabCount();
										_pestaña.setSelectedIndex(i - 1);
				                		
					                break;
					                
				                	case "Ingresos":
				                		
				                		JScrollPane scroll2 = new JScrollPane
				                					(_principal.getComponente("vista_ingresos"), 
				                							JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				                							JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
										
										_pestaña.agregar("Ingresos", scroll2);
										i = _pestaña.getTabCount();
										_pestaña.setSelectedIndex(i - 1);
				                		
					                break;
					                
				                	case "Gestión de Caja":
				                		
				                		VistaCaja vc = (VistaCaja) _principal.getComponente("vista_caja");
				                		JScrollPane scroll3 = new JScrollPane
				                					(vc, 
				                							JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				                							JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				                		
				                		Date d = new Date();
				                		java.sql.Date hoy = new java.sql.Date(d.getTime());
				                		
										_pestaña.agregar("Gestión de Caja", scroll3);
										i = _pestaña.getTabCount();
										_pestaña.setSelectedIndex(i - 1);
										vc.iniciarC_asistencia(hoy);
										
										CharSequence dia = hoy.toString();
										
										LocalDate l = LocalDate.parse(dia);
										
										l = l.plusDays(-1);
										
										java.sql.Date hoy_mas = java.sql.Date.valueOf(l);
										
										vc.iniciarC_AltasBajas(hoy);
										vc.iniciarC_zonas(hoy);
										vc.iniciarC_despacho(hoy);
										//vc.iniciarC_prestamos(hoy);
										vc.iniciarC_sueldos(hoy);
										vc.iniciarC_interna(hoy);
										vc.iniciarC_proveedores(hoy);
										vc.iniciarC_monedas(hoy);
										vc.iniciarResumen(hoy);
										vc.iniciarC_saldo(hoy);
				                		
					                break;
					                
				                	case"Veraz":
				                		VistaVeraz vv = (VistaVeraz) _principal.getComponente("vista_veraz");
	
				                				ControladorCliente cc = new ControladorCliente();
				                				ControladorLocalidad cl = new ControladorLocalidad();
				                				ControladorDomicilioComercial cdc = new 
				                								ControladorDomicilioComercial();
				                				ControladorDomicilioParticular cdp = new 
				                						ControladorDomicilioParticular();
				                				ControladorUsuario cu = new ControladorUsuario();
				                				LogicaDomicilioComercial ldc = new LogicaDomicilioComercial();
				                				LogicaDomicilioParticular ldp = new LogicaDomicilioParticular();
				                				LogicaCliente lc = new LogicaCliente();
				                				LogicaLocalidad ll = new LogicaLocalidad();
				                				LogicaUsuario lu = new LogicaUsuario();
				                				
				                				
				                				BusquedaEntities be = new BusquedaEntities();
				                			
				                				ll.setBusquedaEntities(be);
				                				cl.setLogicaLocalidad(ll);
				                				cl.setBusquedaEntities(be);
				                				be.setVistaVeraz(vv);
				                				cdp.setLogicaDomicilioParticular(ldp);
				                				cdc.setLogicaDomicilioComercial(ldc);
				                				cc.setLogicaCliente(lc);
				                				cu.setLogicaUsuario(lu);
				                				//vv.setVistaPrincipal(this);
				                				vv.setControladorCliente(cc);
				                				vv.setControladorUsuario(cu);
				                				vv.setControladorDomicilioComercial(cdc);
				                				vv.setControladorDomicilioParticular(cdp);
				                				vv.setBusquedaEntities(be);
				                				vv.setControladorLocalidad(cl);
				                				
				                				vv.setVisible(true);
				                		break;
				                	case"Notificaciones":
				                		VistaNotificaciones vn = new VistaNotificaciones();
				                		
				                		ControladorCliente ccliente = new ControladorCliente();
				                		ControladorEmpleado ce = new ControladorEmpleado();
				                		ControladorUsuario cusuario = new ControladorUsuario();
				                		LogicaCliente lcliente = new LogicaCliente();
				                		LogicaEmpleado lempleado = new LogicaEmpleado();
				                		LogicaUsuario lusuario = new LogicaUsuario();
				                		
				                		ccliente.setLogicaCliente(lcliente);
				                		cusuario.setLogicaUsuario(lusuario);
				                		//vv.setVistaPrincipal(this);
				                		vn.setControladorCliente(ccliente);
				                		vn.setControladorUsuario(cusuario);
				                		ce.setLogicaEmpleado(lempleado);
				                		vn.setControladorEmpleado(ce);
				                		vn.iniciar();
				                		 
				                		VistaPrincipal vp = 
				                				(VistaPrincipal)_principal.getComponente("vista_principal");
				                		
				                		vn.setVistaPrincipal(vp);
				                		vp.getDesktop().add(vn);
				                		vn.setVisible(true);
				                		
				                		break;
				                }
				                
				        }
				        	
		    	}}
		    );
		    
		    /*arb.addTreeSelectionListener(new TreeSelectionListener(){
		    	
				@Override
				public void valueChanged(TreeSelectionEvent e){
					// TODO Auto-generated method stu	
					if(e.isAddedPath())
					
						obj = e.getNewLeadSelectionPath().getLastPathComponent();
					
					switch(obj.toString()){
					
						case "Inicio": 
						try {
							panel_cartas.getCarta("inicio");
						} catch (SQLException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
				
						break;
					
						case "Efectivo": try {
							panel_cartas.getCarta("efectivo");
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						break;
						
						case "Tarjeta": try {
							panel_cartas.getCarta("tarjeta");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
						
						case "Alta producto": try {
							panel_cartas.getCarta("alta_prod");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
						
						case "Alta cliente": try {
							panel_cartas.getCarta("alta_clien");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
						
						case "Baja producto":try {
							panel_cartas.getCarta("baja_prod");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
						
						case "Baja cliente": try {
							panel_cartas.getCarta("baja_clien");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
						
						case "Precio": try {
							panel_cartas.getCarta("cambio_prec");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
						
						case "Nombre": try {
							panel_cartas.getCarta("nombre_art");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
						
						case "Descripción": try {
							panel_cartas.getCarta("cambio_desc");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
						
						case "Dirección": 
							
						try {
							panel_cartas.getCarta("cambio_dir");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
						
						case "Teléfono móvil": try {
							panel_cartas.getCarta("cambio_tel");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						break;
						
						case "Lista productos": try {
							panel_cartas.getCarta("lista_compl");
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						break;
						
						case "Código": try {
							panel_cartas.getCarta("art");
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						break;
						
						case "Faltante": try {
							panel_cartas.getCarta("_faltante");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						break;
						
						case "Busqueda cliente": //try {
							//panel_cartas.getCarta("_busqueda_cliente");
						//} catch (SQLException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						//}
							//if(!VistaBuscarCliente.open){
								
								JScrollPane scroll = new JScrollPane(_principal.getComponente("vista_buscar_cliente"));
							
								_pestaña.agregar("Buscar cliente", scroll);
								int i = _pestaña.getTabCount();
								_pestaña.setSelectedIndex(i - 1);
								//VistaBuscarCliente.open = true;
							/*}
							else{
								
								int opcion = Mensajes.getMensaje_advertenciacliente("Buscar cliente");
								
								if(opcion == JOptionPane.YES_OPTION){
									
									vista_buscar_cliente.limpiar();
									int index = tab_nombre("Buscar cliente", _pestaña);
									
									if(index != -1) _pestaña.setSelectedIndex(index);
									
								}
								
								
							}

						break;
						
						case "Por nombre y apellido": try {
							panel_cartas.getCarta("cons_nombre");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						break;
						
						case "Por dirección": try {
							panel_cartas.getCarta("cons_direccion");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						break;
						
						case "Lista clientes": try {
							panel_cartas.getCarta("lista_compl_clientes");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						break;
						
						case "Número de pedido": try {
							panel_cartas.getCarta("num_pedido");
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						break;
						
						case "Fecha": try {
							panel_cartas.getCarta("cons_fecha");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						break;
					}
				
				}
		    	
		    	
		    });*/
		    
		    arb.setOpaque(false);
		  
		    
		    add(arb, BorderLayout.WEST);
		    
		}

		private void crear_nodos(DefaultMutableTreeNode topR) {
			
		      
		    n1 = new DefaultMutableTreeNode("Registraciones");
		    topR.add(n1);
		     
		    n2 = new DefaultMutableTreeNode("Ingresos");
		    n1.add(n2);
		    
		    n3 = new DefaultMutableTreeNode("Ingresos");
		    n2.add(n3);
		    
		  /*  n3 = new DefaultMutableTreeNode("Tarjeta");
		    n2.add(n3);
		    
		    n2 = new DefaultMutableTreeNode("Altas");
		    n1.add(n2);
		    
		    n3 = new DefaultMutableTreeNode("Alta producto");
		    n2.add(n3);
		  
		    n3 = new DefaultMutableTreeNode("Alta empleado");
		    n2.add(n3);
		    
		    n3 = new DefaultMutableTreeNode("Alta zona");
		    n2.add(n3);
		    
		    n3 = new DefaultMutableTreeNode("Alta proveedor");
		    n2.add(n3);*/
		    
		    n1 = new DefaultMutableTreeNode("Consultas");
		    topR.add(n1);
		     
		   /* n2 = new DefaultMutableTreeNode("Producto");
		    n1.add(n2);
		    
		    n3 = new DefaultMutableTreeNode("Busqueda personalizada");
		    n2.add(n3); 
		    
		    n3 = new DefaultMutableTreeNode("Lista productos");
		    n2.add(n3);
		    
		    n3 = new DefaultMutableTreeNode("Faltante");*/
		    n2.add(n3);
		    
		    n2 = new DefaultMutableTreeNode("Cliente");
		    n1.add(n2);
		    
		    n3 = new DefaultMutableTreeNode("Busqueda cliente");
		    n2.add(n3);
		    
		   /* n3 = new DefaultMutableTreeNode("Lista clientes");
		    n2.add(n3);
		    
		    n3 = new DefaultMutableTreeNode("Clientes atrasados");
		    n2.add(n3);
		      
		    n2 = new DefaultMutableTreeNode("Pedido");
		    n1.add(n2);
		    
		    n3 = new DefaultMutableTreeNode("Busqueda personalizada");
		    n2.add(n3); */
		    
		    n2 = new DefaultMutableTreeNode("Gestión de Caja");
		    n1.add(n2);
		       
		    n2 = new DefaultMutableTreeNode("Veraz");
		    n1.add(n2);

		    n2 = new DefaultMutableTreeNode("Notificaciones");
		    n1.add(n2);
		    
		  /*  n2 = new DefaultMutableTreeNode("Zona");
		    n1.add(n2);
		    
		    n2 = new DefaultMutableTreeNode("Empleado");
		    n1.add(n2);*/
		}
		
		public class Render extends DefaultTreeCellRenderer {

			private ImageIcon iconoAbierto = new ImageIcon("open_folder.png");
			
			private ImageIcon iconoCerrado = new ImageIcon("closed_folder.png");
			
			
		    @Override
		    public Color getBackgroundNonSelectionColor() {
		        return (null);
		    }

		    @Override
		    public Color getBackgroundSelectionColor() {
		        return Color.GREEN;
		    }

		    @Override
		    public Color getBackground() {
		        return (null);
		    }

		    @Override
		    public Component getTreeCellRendererComponent(final JTree tree, final Object value, 
		    	final boolean sel, final boolean expanded, final boolean leaf, final int row,
		    	final boolean hasFocus) {
		        final Component ret = super.getTreeCellRendererComponent(tree, value, sel,
		        		expanded, leaf, row, hasFocus);

		        /*Image abierto = null;
		        Image cerrado = null;
		        
		        try {
					abierto = ImageIO.read(getClass().getResource("/open_folder.png"));
					cerrado = ImageIO.read(getClass().getResource("/closed_folder.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        if(sel){
		        	
		        	this.setIcon(new ImageIcon(abierto));
		        	
		        }
		        else{
		        	
		        	this.setIcon(new ImageIcon(cerrado));
		        }*/
		        
		        final DefaultMutableTreeNode node = ((DefaultMutableTreeNode) (value));
		        this.setText(value.toString());
		        return ret;
		    }
		}
		
		class Render2 extends JPanel implements TreeCellRenderer{

			private ImageIcon iconoAbierto = new ImageIcon("open_folder.png");
			
			private ImageIcon iconoCerrado = new ImageIcon("closed_folder.png");
			
			@Override
			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
					boolean expanded, boolean leaf, int row, boolean hasFocus) {
				// TODO Auto-generated method stub
				
				if(expanded)
					
					System.out.println("expanded");
				
				return this;
			}
			
			
		}

		
	}
	
	public void setCartas(Cartas panel_cartas){
		
		this.panel_cartas = panel_cartas;
	}
	
	public void setPestañas(JTabbedPane pestañas){
		
		this.pestañas = pestañas;
	}
	
	public void setPestaña(Pestaña pestaña){
		
		this._pestaña = pestaña;
	}
	
	public JDesktopPane getDesktop(){
		
		return desktop;
	}
	
	public int tab_nombre(String title, JTabbedPane tab)  
	{
	  int tabCount = tab.getTabCount();
	  for (int i=0; i < tabCount; i++) 
	  {
	    String tabTitle = tab.getTitleAt(i);
	    if (tabTitle.equals(title)) return i;
	  }
	  return -1;
	}
	
	class CustomJToolTip extends JToolTip {

	    public CustomJToolTip(JComponent component) {
	        super();
	        setComponent(component);
	        setBackground(Color.white);
	        setForeground(Color.BLACK);
	    }
	}

	public void initlf(){
		
		 SynthLookAndFeel laf = new SynthLookAndFeel();
   	  try {
//		laf.load(VistaPrincipal.class.getResourceAsStream("estilo_button.xml"), VistaPrincipal.class);
		try {
			laf.load(new URL("C:\\Users\\david\\workspace\\Halcones_diarios1a\\src\\estilo_button.xml"));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			UIManager.setLookAndFeel(laf);
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	
	private static void initLookAndFeel() {
        String lookAndFeel = null;
       
        if (LOOKANDFEEL != null) {
            if (LOOKANDFEEL.equals("Metal")) {
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
              //  an alternative way to set the Metal L&F is to replace the 
              // previous line with:
              // lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
                
            }
            
            else if (LOOKANDFEEL.equals("System")) {
                lookAndFeel = UIManager.getSystemLookAndFeelClassName();
            } 
            
            else if (LOOKANDFEEL.equals("Motif")) {
                lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            } 
            
            else if (LOOKANDFEEL.equals("GTK")) { 
                lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            } 
            
            else {
                System.err.println("Unexpected value of LOOKANDFEEL specified: "
                                   + LOOKANDFEEL);
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            }

            try {
            	
            	
            	
                UIManager.setLookAndFeel(lookAndFeel);
                
                // If L&F = "Metal", set the theme
                
                if (LOOKANDFEEL.equals("Metal")) {
                  if (THEME.equals("DefaultMetal"))
                     MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
                  else if (THEME.equals("Ocean"))
                     MetalLookAndFeel.setCurrentTheme(new OceanTheme());
                  else
                     MetalLookAndFeel.setCurrentTheme(new TemaLyF());
                     
                  UIManager.setLookAndFeel(new MetalLookAndFeel()); 
                }	
                	
                	
                  
                
            } 
            
            catch (ClassNotFoundException e) {
                System.err.println("Couldn't find class for specified look and feel:"
                                   + lookAndFeel);
                System.err.println("Did you include the L&F library in the class path?");
                System.err.println("Using the default look and feel.");
            } 
            
            catch (UnsupportedLookAndFeelException e) {
                System.err.println("Can't use the specified look and feel ("
                                   + lookAndFeel
                                   + ") on this platform.");
                System.err.println("Using the default look and feel.");
            } 
            
            catch (Exception e) {
                System.err.println("Couldn't get specified look and feel ("
                                   + lookAndFeel
                                   + "), for some reason.");
                System.err.println("Using the default look and feel.");
                e.printStackTrace();
            }
        }
    }
	
	
}
