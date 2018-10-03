package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controlador.ControladorCliente;
import controlador.ControladorDespacho_diario;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorUsuario;
import controlador.Principal;
import modelo.Mensajes;
import modelo_vo.ClienteVO;
import modelo_vo.Despacho_diarioVO;
import modelo_vo.ObservacionesVO;
import modelo_vo.PedidosVO;
import modelo_vo.UsuariosVO;

public class VistaHistorial_despacho extends JInternalFrame implements ActionListener{

	private JTable tabla_despacho = new JTable();
	private DefaultTableModel t_model_despacho ;
	private JScrollPane scr;
	private JPanel pTabla = new JPanel();
	private JPanel pComandos = new PanelGraduado(new Color(234, 234, 234), new Color(150, 150, 150));
	private JButton guardar = new JButton("Guardar");
	private JButton salir = new JButton("Salir");
	private JTextArea observaciones; 
	private JLabel obL = new JLabel();
	private JLabel cobradorL = new JLabel();
	private JLabel clienteL = new  JLabel();

	private  VistaBuscarPedidos_porClientes _vista_buscarPedido_cliente;
	private ControladorPagoDiario _controladorPagoDiario;
	private ControladorCliente _controladorCliente;
	private ControladorUsuario _controladorUsuario;
	private ControladorPedidos _controladorPedido;
	private ControladorDespacho_diario _controlador_dd;
	
	private static VistaDespacho_diario vdd;
	
	private VistaBuscarCliente vista_buscar_cliente;
	
	 private boolean[] canEdit_despacho= new boolean[]{
	            false, false, false, false, false, false, false, false, false
	    };
	
	 private JDatePickerImpl fecha_hasta;
		private UtilDateModel modelFH = new UtilDateModel();
		private JFormattedTextField fh;
		private JDatePickerImpl fecha_desde;
		private UtilDateModel modelFD = new UtilDateModel();
		private JFormattedTextField fd;
		private JButton ver_fb = new JButton("Ver");
	 
	public VistaHistorial_despacho(){
		//super(vdd, "Historial de despachos", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		super("Historial de despachos", true, true, true, true);
		observaciones = new JTextArea(2,30);
    	observaciones.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	observaciones.setLineWrap(true);
		
    	ver_fb.addActionListener(this);
    	
		this.setLayout(new BorderLayout());
	       
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        this.setSize(dim.width*80/100, dim.height*80/100);
	       // this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	       // frame.getContentPane().add(scr); 
	    
	        	
	       // if(_vista_buscarPedido_cliente.isShowing()) _vista_buscarPedido_cliente.setEnabled(false);
	        
	        //scr.setViewportView(tabla);
	        this.setFocusable(true);
	        this.setResizable(false);
	        setClosable(true); 
	        setIconifiable(true); 
	        setMaximizable(true); 
	        //this.setLocationRelativeTo(null);
	        
	        t_model_despacho = new DefaultTableModel(null, getColumnas2()){
				 
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit_despacho[columnIndex];
	            }
	        };
	        
	        tabla_despacho.setModel(t_model_despacho);
	        tabla_despacho.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
	        
	       
		
			ancho_columnas();
			
			DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
			
			cent.setHorizontalAlignment(SwingConstants.CENTER);
			
			for(int i = 0; i < tabla_despacho.getColumnCount(); i ++)
				
				tabla_despacho.getColumnModel().getColumn(i).setCellRenderer(cent);
	        
	        scr = new JScrollPane();
	        scr.setViewportView(tabla_despacho);
	        
	        Date d = new Date();
			java.sql.Date hoy = new java.sql.Date(d.getTime());

			JPanel p_desde = new JPanel();
		
	    	Border bordefb = BorderFactory.createTitledBorder(null, "Desde", 
	    	    	TitledBorder.CENTER, TitledBorder.TOP,
	    	    	new Font("Arial",Font.PLAIN,16), Color.BLACK);
	    	p_desde.setBorder(bordefb);
	    	JPanel p_hasta = new JPanel();
	    	
	    	Border bordehasta = BorderFactory.createTitledBorder(null, "Hasta", 
	    			TitledBorder.CENTER, TitledBorder.TOP,
	    			new Font("Arial",Font.PLAIN,16), Color.BLACK);
	    	p_hasta.setBorder(bordehasta);
			
	    	p_desde.setOpaque(false);
	    	p_hasta.setOpaque(false);
	    	
	    	modelFH.setSelected(true);
	    	modelFD.setValue(hoy);
	    	modelFD.setSelected(true);
	    
	  		Properties pr = new Properties();
	  		pr.put("text.today", "Today");
	  		pr.put("text.month", "Month");
	  		pr.put("text.year", "Year");
	        JDatePanelImpl datePanelFH= new JDatePanelImpl(modelFH, pr);
	        JDatePanelImpl datePanelFD = new JDatePanelImpl(modelFD, pr);
	        
	       
	        fecha_hasta = new JDatePickerImpl(datePanelFH, new DateLabelFormatter());
	        fecha_desde = new JDatePickerImpl(datePanelFD, new DateLabelFormatter());
			
	        
	        p_desde.add(fecha_desde);
	        p_hasta.add(fecha_hasta);
	        
			
			 fh= fecha_hasta.getJFormattedTextField();
			 fh.setFont(new Font("Arial", Font.PLAIN, 18));
		   
			 fd= fecha_desde.getJFormattedTextField();
			 fd.setFont(new Font("Arial", Font.PLAIN, 18));
			 
		   //pComandos.add(fechaL);
		   pComandos.add(p_desde);
		   pComandos.add(p_hasta);
		   pComandos.add(this.ver_fb);
	        
		   add(pComandos, BorderLayout.PAGE_START);
		   
	        add(scr, BorderLayout.CENTER);
			//frame.pack();
	       
	        //_vista_principal.setEnabled(false);
	}
	
	public void iniciar(java.sql.Date desde, java.sql.Date hasta){
		 
		ArrayList<Despacho_diarioVO> ar = _controlador_dd.buscarDespacho_entreFechas(desde, hasta);
		
		limpiar_despacho();
		//this.setVisible(true)
		//pIntegra.remove(pIntegra_despacho);
		int cantidad = 0;
		
		//tabla  = new JTable();
		
		//t_model_despacho = new DefaultTableModel(null, getColumnas2());
		
		
		
		//tabla_despacho.setRowSelectionAllowed(true);
		//tabla_despacho.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		//pTabla_despacho.setBackground(Color.white);
		
		if(ar!=null){
						
			for(Despacho_diarioVO dpVO : ar){
				
				SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
				String date1 =  new String(sf.format(dpVO.getFecha_registro()));
				
				Object [] o = new Object[10];
				
				o[0] = dpVO.getN_pedido();
				
				PedidosVO pVO = _controladorPedido.buscarPedido_porNpedido(dpVO.getN_pedido());
				
				ClienteVO cVO = _controladorCliente.buscarCliente
						(Integer.toString(pVO.getDni()));
				
				if(cVO!=null){
				
					o[1] = cVO.getNombre() + " " + cVO.getApellido();
					
				}
				
				o[2] = dpVO.getEntrega();
				o[3] = dpVO.getNombre();
				o[4] = dpVO.getEstado();
				o[5] = dpVO.getMonto();
				o[6] = dpVO.getObservaciones();
				
				UsuariosVO uVO = _controladorUsuario.buscarUsuario_porID((short)dpVO.getId_usuario());
				
				if(uVO!=null)
				
					o[7] = uVO.getNombre();
				
				o[8] = date1;
				o[9] = dpVO.getHora_registro();
				
				t_model_despacho.addRow(o);
				cantidad += 1;
			}
		}
		
		 tabla_despacho.getPreferredScrollableViewportSize().setSize(900,
					tabla_despacho.getRowHeight() * cantidad);
		
		
		//pTabla_despacho.updateUI();
		
	}
	
	private void ancho_columnas(){
		
		for(int i = 0; i < tabla_despacho.getColumnCount(); i++){
			
			if(i == 3) tabla_despacho.getColumnModel().getColumn(i).setPreferredWidth(250);
			else tabla_despacho.getColumnModel().getColumn(i).setPreferredWidth(50);
		}
	}
	
	private String [] getColumnas2(){
		
		String columna [] = new String []{"N pedido", "Cliente", "Entrega", "Detalle", "Estado",
										"Monto", "Observaciones", "Usuario", "Fecha registro", "Hora registro"};
		
		return columna;
	}
	
	private String [] getColumnas(){
		
		String columna [] = new String []{"N pedido", "Artículo", "Importe",
									 "Usuario", "Fecha registro", "Hora registro"};
		
		return columna;
	}
	
    public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vpc){
    	
    	this._vista_buscarPedido_cliente = vpc;
    }
    
    public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
    	
    	this.vista_buscar_cliente = vista_buscar_cliente;
    }
    
    public void setVistaDespacho_diario(VistaDespacho_diario vdd){
    	
    	this.vdd = vdd;
    }
    
    public void setControladorPagoDiario(ControladorPagoDiario _controladorPagoDiario){
    	
    	this._controladorPagoDiario = _controladorPagoDiario;
    }
    
    public void setControladorCliente(ControladorCliente _controladorCliente){
    	
    	this._controladorCliente = _controladorCliente;
    }
    public void setControladorDespacho_diario(ControladorDespacho_diario _controlador_dd){
    	
    	this._controlador_dd = _controlador_dd;
    }
    public void setControladorPedidos(ControladorPedidos _controladorPedido){
    	
    	this._controladorPedido = _controladorPedido;
    }
    public void setControladorUsuario(ControladorUsuario _controladorUsuario){
    	
    	this._controladorUsuario = _controladorUsuario;
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
		
		if(e.getSource()==ver_fb){
			
			if(!fecha_desde.getJFormattedTextField().getText().equals("") &&
					!fecha_hasta.getJFormattedTextField().getText().equals("")){
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				SimpleDateFormat ft_desde = new SimpleDateFormat("dd-MM-yyyy");
				Date d_desde=null;
				try {
					d_desde = ft_desde.parse(fecha_desde.getJFormattedTextField().getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date fecha_desde = new java.sql.Date(d_desde.getTime());
				
				SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
				Date d=null;
				try {
					d = ft.parse(fecha_hasta.getJFormattedTextField().getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date fecha_hasta = new java.sql.Date(d.getTime());
				
				this.iniciar(fecha_desde, fecha_hasta);
			}
			else Mensajes.getMensaje_altaErrorGenerico();
			
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
}
