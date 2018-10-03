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
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controlador.ControladorArticulo;
import controlador.ControladorCliente;
import controlador.ControladorDomicilioComercial;
import controlador.ControladorDomicilioParticular;
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
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.LocalidadVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.UsuariosVO;
import modelo_vo.VerazVO;
import vista.VistaClientesAtrasados.CustomJToolTip;

public class VistaVeraz extends JDialog implements ActionListener{
	
	private JTextField dniTF = new JTextField(10);
	private JTextField dni_conyugueTF = new JTextField(10);
	private JTextField dcTF = new JTextField(15);
	private JTextField dpTF = new JTextField(15);

	private JTextField localidad_dcTF = new JTextField(2);
	private JTextField localidad_dpTF = new JTextField(2);
		
	private JLabel lLoc_dc = new JLabel();
	private JLabel lLoc_dp = new JLabel();
	
	private BusquedaEntities be;
	
	private ControladorCliente controladorCliente;
	private ControladorUsuario _controladorUsuario;
	private ControladorLocalidad _controladorLocalidad;
	private ControladorDomicilioComercial cdc;
	private ControladorDomicilioParticular cdp;
	
	private static  VistaPrincipal _vista_principal;
	
	private JPanel pVeraz = new JPanel();
	private JPanel pComandos = new PanelGraduado(new Color(234, 234, 234), new Color(150, 150, 150));
	private JScrollPane scr = new JScrollPane();

	private JButton ver_dni = new JButton("Buscar");
	private JButton ver_dni_c = new JButton("Buscar");
	private JButton ver_dc = new JButton("Buscar");
	private JButton ver_dp = new JButton("Buscar");

	private JButton buscar_localidad_dc = new JButton("...");
	private JButton buscar_localidad_dp = new JButton("...");
	
	 private JButton imprimir = new JButton(){
	        //override the JButtons createToolTip method
	        @Override
	        public JToolTip createToolTip() {
	            return (new CustomJToolTip(this));
	        }
	    };;
	
	private JTable tabla = new JTable();    
	private DefaultTableModel t_model;    
	
	private boolean[] canEdit_clientes= new boolean[]{
            false, false, false, false, false, false
    };
	
	private static VistaPrincipal vp;
	
	private float[] columnWidthPercentage = {5.0f,10.0f, 10.0f, 10.0f, 10.0f,15.0f, 15.0f, 15.0f,5.0f,2.5f,2.5f};
	
	public VistaVeraz(){
		super(_vista_principal, "Clientes en veraz", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		
		System.out.println("probando pull");
		
		 this.setLayout(new BorderLayout());
		
		 ver_dni.addActionListener(this);
		 ver_dni_c.addActionListener(this);
		 ver_dc.addActionListener(this);
		 ver_dp.addActionListener(this);
		 buscar_localidad_dc.addActionListener(this);
		 buscar_localidad_dp.addActionListener(this);
		 
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     //setSize(dim.width*70/100, dim.height*70/100);
	     setSize(dim.width*90/100, 500);
	     setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	     this.setResizable(false);
		
	     tabla.getTableHeader().setReorderingAllowed(false);
		    tabla.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 12));
	     
	     t_model = new DefaultTableModel(null, getColumnas()){
			 
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit_clientes[columnIndex];
	            }
		};
	     
		imprimir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				}
			
		});
		
		tabla.setModel(t_model);
		 centrar_columnas(tabla);
			//ancho_columnas();
		 resizeColumns();
	     //pClientes_atrasados.setLayout(new BoxLayout(pPedidos_historicos, BoxLayout.Y_AXIS))
	     
	     pVeraz.add(tabla);
	     
	    // pClientes_atrasados.setPreferredSize(new Dimension(850, 600));
	    // scr.setPreferredSize(new Dimension(850, 600));
	     scr.setViewportView(tabla);
	    // scr.add(pPedidos_historicos);
	     
	    // BH_clientesAtrasados bh = new BH_clientesAtrasados(imprimir);
	     
	     JPanel pDni = new JPanel();
	     JPanel pDni_conyugue = new JPanel();
	     JPanel pDc = new JPanel();
	     JPanel pDp = new JPanel();
	     JPanel pDc_sur= new JPanel();
	     JPanel pDp_sur = new JPanel();
	     JPanel pDc_integra = new JPanel();
	     JPanel pDp_integra = new JPanel();
	     
	     pDc_integra.setLayout(new BoxLayout(pDc_integra, BoxLayout.Y_AXIS));
	     pDp_integra.setLayout(new BoxLayout(pDp_integra, BoxLayout.Y_AXIS));
	     
	     Border borde_dni = BorderFactory.createTitledBorder(null, "DNI",
	    		 TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 12));
	     Border borde_dni_c = BorderFactory.createTitledBorder(null, "DNI conyugue",
	    		 TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 12));
	     Border borde_dp = BorderFactory.createTitledBorder(null, "Domicilio comercial",
	    		 TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 12));
	     Border borde_dc = BorderFactory.createTitledBorder(null, "Domicilio particular",
	    		 TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 12));
	     
	     pDni.setBorder(borde_dni);
	     pDni_conyugue.setBorder(borde_dni_c);
	     pDc.setBorder(borde_dp);
	     pDp.setBorder(borde_dc);
	     pDni.setOpaque(false);
	     pDni_conyugue.setOpaque(false);
	     pDc.setOpaque(false);
	     pDp.setOpaque(false);
	     pDc_integra.setOpaque(false);
	     pDp_integra.setOpaque(false);
	     
	     JPanel pLoc_dc = new JPanel();
	     JPanel pLoc_dp = new JPanel();
	     
	     GridLayout gl = new GridLayout(0,2);
	     pLoc_dc.setLayout(gl);
	     pLoc_dp.setLayout(gl);
	     
	     pLoc_dc.add(localidad_dcTF);
	     pLoc_dc.add(buscar_localidad_dc);
	     pLoc_dp.add(localidad_dpTF);
	     pLoc_dp.add(buscar_localidad_dp);
	     
	     pDni.add(dniTF);
	     pDni.add(ver_dni);

	     pDni_conyugue.add(dni_conyugueTF);
	     pDni_conyugue.add(ver_dni_c);
	     
	     pDc.add(dcTF);
	     pDc.add(pLoc_dc);
	     pDc.add(lLoc_dc);
	   //  pDc_integra.add(pDc);
	     pDc.add(ver_dc);

	     pDp.add(dpTF);
	     pDp.add(pLoc_dp);
	     pDp.add(lLoc_dp);
	     
	     //pDp_integra.add(pDp);
	     pDp.add(ver_dp);
	     
	     localidad_dpTF.addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					localidad_dpTF.setBackground(new Color(183, 242, 113));
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(_controladorLocalidad.buscarLocalidadUsuario(localidad_dpTF.getText())!=null)
						
						lLoc_dp.setText(_controladorLocalidad.buscarLocalidadUsuario(localidad_dpTF.getText()));
					else{
						
						lLoc_dp.setText("");
						localidad_dpTF.setText("");
					}
						
					
					localidad_dpTF.setBackground(new Color(255, 255, 255));
				}
	  			
	  			
	  		});
	     localidad_dcTF.addFocusListener(new FocusListener(){
	    	 
	    	 @Override
	    	 public void focusGained(FocusEvent e) {
	    		 // TODO Auto-generated method stub
	    		 localidad_dcTF.setBackground(new Color(183, 242, 113));
	    	 }
	    	 
	    	 @Override
	    	 public void focusLost(FocusEvent e) {
	    		 // TODO Auto-generated method stub
	    		 if(_controladorLocalidad.buscarLocalidadUsuario(localidad_dcTF.getText())!=null)
	    			 
	    			 lLoc_dc.setText(_controladorLocalidad.buscarLocalidadUsuario(localidad_dcTF.getText()));
	    		 else{
	    			 
	    			 lLoc_dc.setText("");
	    			 localidad_dcTF.setText("");
	    		 }
	    		 
	    		 
	    		 localidad_dcTF.setBackground(new Color(255, 255, 255));
	    	 }
	    	 
	    	 
	     });
	     
	     pComandos.add(pDni);
	     pComandos.add(pDni_conyugue);
	     pComandos.add(pDp);
	     pComandos.add(pDc);
	       
	     JScrollPane scr_comandos = new JScrollPane();
	     scr_comandos.setViewportView(pComandos);
	     
	     add(scr_comandos, BorderLayout.PAGE_START);
	     add(scr, BorderLayout.CENTER);
	    System.out.println();
	}
	
	private void resizeColumns() {
	    int tW = 1300;
	    TableColumn column;
	    TableColumnModel jTableColumnModel = tabla.getColumnModel();
	    int cantCols = jTableColumnModel.getColumnCount();
	    for (int i = 0; i < cantCols; i++) {
	        column = jTableColumnModel.getColumn(i);
	        int pWidth = Math.round(columnWidthPercentage[i] * tW);
	        column.setPreferredWidth(pWidth);
	    }
	}
	
	public void iniciar(VerazVO vVO){
		
		int cantidad = 0;
		
		if(vVO!=null){
			
			ArrayList<DomicilioComercialVO> ar = cdc
					.buscarDomicilioComercial2(Integer.toString(vVO.getDni()));
			
			for(DomicilioComercialVO dcVO : ar){
				
				SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
				String fecha = f.format(vVO.getFecha_registro());
				
				Object [] datos = new Object[11];
				
				datos[0] = vVO.getNregistro();
				datos[1] = vVO.getDni();
				
				ClienteVO cVO= controladorCliente.buscarCliente(Integer.toString(vVO.getDni()));
					
				datos[2] = cVO.getNombre() + " " + cVO.getApellido();
	
				datos[3] = cVO.getDni_conyugue();
				datos[4] = cVO.getNombre_conyugue() + " " + cVO.getApellido_conyugue();
				datos[5] = dcVO.getDomicilio();
				
				DomicilioParticularVO dpVO = cdp.buscarDomicilioParticular(Integer.toString(cVO.getDni()));
				
				if(dpVO!=null){
					
					datos[6] = dpVO.getDomicilio(); 
					
				}
							
				datos[7] = vVO.getObservacion();
				
				UsuariosVO uVO = _controladorUsuario.buscarUsuario_porID((short) vVO.getId_usuario());
				
				datos[8] = uVO.getNombre();
		
				datos[9] =fecha;
				datos[10] = vVO.getHora_registro();
				
				t_model.addRow(datos);
				
			}
		}
		
		//alto_filas();
		tabla.getPreferredScrollableViewportSize().setSize(1250,
				tabla.getRowHeight() * cantidad);
		
		
		pVeraz.updateUI();
		
		
	}
	
	private void ancho_columnas(){
		
		for(int i = 0; i < tabla.getColumnCount(); i++){
			
			if(i == 5) tabla.getColumnModel().getColumn(i).setPreferredWidth(200);
			else if(i==2 || i==3 || i==4)tabla.getColumnModel().getColumn(i).setPreferredWidth(150);
			else tabla.getColumnModel().getColumn(i).setPreferredWidth(50);
		}
	
	}
	
	
	private void centrar_columnas(JTable tabla){
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla.getColumnCount(); i ++)
			
			tabla.getColumnModel().getColumn(i).setCellRenderer(cent);
		
	}	
	
	    
	    public void setVistaPrincipal(VistaPrincipal _vista_principal){
			
			this._vista_principal = _vista_principal;
		}
	    
		
	    public void setBusquedaEntities(BusquedaEntities be){
	    	
	    	this.be = be;
	    }
		public void setControladorCliente(ControladorCliente controladorCliente){
			
			this.controladorCliente = controladorCliente;
		}
		
		public void setControladorUsuario(ControladorUsuario _controladorUsuario){
			
			this._controladorUsuario = _controladorUsuario;
		}
		public void setControladorLocalidad(ControladorLocalidad _controladorLocalidad){
			
			this._controladorLocalidad = _controladorLocalidad;
		}
		public void setControladorDomicilioComercial(ControladorDomicilioComercial cdc){
			
			this.cdc = cdc;
		}
		public void setControladorDomicilioParticular(ControladorDomicilioParticular cdp){
			
			this.cdp = cdp;
		}
		
		private String [] getColumnas(){
			
			String columna [] = new String []{ "Nregistro", "DNI", "Cliente","DNI conyugue",
					"Conyugue","Domicilio comercial",
					"Domicilio particular", "Observacion",
					"Usuario", "Fecha registro", 
					"Hora registro" };
			
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

		public JTextField getLoc_dcTF(){
			
			return localidad_dcTF;
		}
		public JTextField getLoc_dpTF(){
			
			return localidad_dpTF;
		}
		
		public JLabel getLoc_dcL(){
			
			return lLoc_dc;
		}
		public JLabel getLoc_dpL(){
			
			return lLoc_dp;
		}
		
		public void limpiar(){
	    	
	    	if(tabla.getRowCount() > 0){
				
				DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
				
				int contFila = tabla.getRowCount();
				
				for(int i = 0; i < contFila; i++)
				
					modelo.removeRow(0);
			}
	    	
	    }
		
		public boolean validarInt(String val_string){
			
			try{
				
				Integer.parseInt(val_string);
				
				return true;
				
			}catch(NumberFormatException e){
				
				return false;
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			be.setPanel("vista_veraz");
			
			if(e.getSource()==ver_dni){
				
				if(validarInt(dniTF.getText())){
					
					limpiar();
					
					VerazVO vVO = controladorCliente.comprobarVeraz_porDni(Integer.parseInt(dniTF.getText()));
					
					if(vVO!=null){
						
						iniciar(vVO);
					}
				}
				else Mensajes.getMensaje_altaErrorGenerico();
				
				
			}
			if(e.getSource()==ver_dni_c){
				
				if(validarInt(dni_conyugueTF.getText())){
					
					limpiar();
					ClienteVO cVO = new ClienteVO();
					cVO.setDni(Integer.parseInt(dni_conyugueTF.getText()));
					
					VerazVO vVO = controladorCliente.
							comprobarVeraz_porDni_conyugue(cVO);
					
					if(vVO!=null){
						
						iniciar(vVO);
					}
				}
				else Mensajes.getMensaje_altaErrorGenerico();
				
			}
			if(e.getSource()==ver_dc){
				
				if(dcTF.getText().length() <= 100 && !localidad_dcTF.getText().equals("")){
					
					limpiar();
					
					DomicilioComercialVO dcVO = new DomicilioComercialVO();
					
					dcVO.setDomicilio(dcTF.getText());
					dcVO.setId_localidad(Short.parseShort(localidad_dcTF.getText()));
					VerazVO vVO = controladorCliente.comprobarVeraz_porDom_com(dcVO);
					
					if(vVO!=null){
						
						iniciar(vVO);
					}
					
				}
				else Mensajes.getMensaje_altaErrorGenerico();
				
			}
			if(e.getSource()==ver_dp){
				
				if(dpTF.getText().length() <= 100 && !localidad_dpTF.getText().equals("")){
					
					limpiar();
					
					DomicilioParticularVO dpVO = new DomicilioParticularVO();
					
					dpVO.setDomicilio(dpTF.getText());
					dpVO.setId_localidad(Short.parseShort(localidad_dpTF.getText()));
					VerazVO vVO = controladorCliente.comprobarVeraz_porDom_part(dpVO);
					
					if(vVO!=null){
						
						iniciar(vVO);
					}
					
				}
				else Mensajes.getMensaje_altaErrorGenerico();

				
			}
			if(e.getSource()==buscar_localidad_dc){
				
				be.setTipoBusqueda(3);
				_controladorLocalidad.buscarLocalidadAll();
				_controladorLocalidad.mostrarBusquedaEntities();
				
			}
			if(e.getSource()==buscar_localidad_dp){
				
				be.setTipoBusqueda(2);
				_controladorLocalidad.buscarLocalidadAll();
				_controladorLocalidad.mostrarBusquedaEntities();
				
			}
			
		}
}
