package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

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
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controlador.ControladorArticulo;
import controlador.ControladorCaja;
import controlador.ControladorCliente;
import controlador.ControladorEmpleado;
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
import modelo.LogicaCaja;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.LocalidadVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import vista.VistaGestionProveedores.CustomJToolTip;

public class VistaResumen_semanal extends JInternalFrame implements ActionListener{

	private VistaBuscarCliente vista_buscar_cliente;
	private ControladorVendedor _controladorVendedor;
	private ControladorEmpleado _controladorEmpleado;
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
	private ControladorPrestamo _controladorPrestamo;
	private ControladorUsuario _controladorUsuario;
	private BusquedaEntities _busqueda_entities;
	private VistaBuscarPedidos_porClientes vp;
	private static  VistaPrincipal _vista_principal;
	
	private JPanel pComandos =  new PanelGraduado(new Color(234, 234, 234), new Color(150, 150, 150));
	private JLabel fechaL = new JLabel();
	private JDatePickerImpl fecha_hasta;
	private UtilDateModel modelFH = new UtilDateModel();
	private JFormattedTextField fh;
	private JButton ver_fb = new JButton("Ver");
	
	private JPanel pPagos = new JPanel();
	private JScrollPane scr = new JScrollPane();
	
	 private JButton imprimir = new JButton(){
	        //override the JButtons createToolTip method
	        @Override
	        public JToolTip createToolTip() {
	            return (new CustomJToolTip(this));
	        }
	    };;
	
	private JTable tabla = new JTable();    
	private DefaultTableModel t_model;    
	
	private boolean[] canEdit_pagos= new boolean[]{
            false, false, false, false, false, false,false,false
    };
	
	 private float[] columnWidthPercentage = {20.0f,10.0f, 10.0f, 10.0f, 15.0f,10.0f, 10.0f, 15.0f};
	
	public VistaResumen_semanal(){
		//super(_vista_principal, "Clientes atrasados", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		super("Resumen semanal", true, true, true, true);
		 this.setLayout(new BorderLayout());
		
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     setSize(dim.width*75/100, dim.height*70/100);
	     //setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	     //this.setResizable(false);
		
	     ver_fb.addActionListener(this);
	     
	     setClosable(true); 
	        setIconifiable(true); 
	        setMaximizable(true); 
	        setResizable(true); 
	     
	     tabla.getTableHeader().setReorderingAllowed(false);
		    tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		    tabla.setFont(new Font("Arial", Font.PLAIN, 14));
	     
	     t_model = new DefaultTableModel(null, getColumnas()){
			 
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit_pagos[columnIndex];
	            }
		};
		
		tabla.setModel(t_model);
		tabla.setRowHeight(20);
		modelFH.setSelected(true);
		
		 Date d = new Date();
			java.sql.Date hoy = new java.sql.Date(d.getTime());
		
			this.setLabelFecha(hoy);
			
        //model.setDate(20,04,2014);
  		Properties pr = new Properties();
  		pr.put("text.today", "Today");
  		pr.put("text.month", "Month");
  		pr.put("text.year", "Year");
        JDatePanelImpl datePanelFH= new JDatePanelImpl(modelFH, pr);
       
       
        fecha_hasta = new JDatePickerImpl(datePanelFH, new DateLabelFormatter());
        JPanel p_fb = new JPanel();//PanelGraduado(new Color(234, 234, 234), new Color(150, 150, 150));
        p_fb.setOpaque(false);
        p_fb.add(fecha_hasta);
        p_fb.add(ver_fb);
		
        Border bordefb = BorderFactory.createTitledBorder(null, "Fecha de consulta", 
    	    	TitledBorder.CENTER, TitledBorder.TOP,
    	    	new Font("Arial",Font.PLAIN,16), Color.BLACK);
    	p_fb.setBorder(bordefb);
        
        fh= fecha_hasta.getJFormattedTextField();
		 fh.setFont(new Font("Arial", Font.PLAIN, 18));
	   
		pComandos.setLayout(new BoxLayout(pComandos, BoxLayout.Y_AXIS));
		 
		JPanel pSur = new JPanel();
		pSur.setOpaque(false);
		
		pSur.add(imprimir);
		pSur.add(fechaL);
		pSur.add(p_fb);
		
		BH_resumen bh = new BH_resumen(imprimir);
		
		pComandos.add(bh);
	    pComandos.add(pSur);
	     
		imprimir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					
				try {
					 
					SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
					
					java.util.Date fecha = null;
					try {
						fecha = ft.parse(fecha_hasta.getJFormattedTextField().getText());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					 java.sql.Date hasta = new java.sql.Date(fecha.getTime());
					 
					 String fechaS = ft.format(hasta);
					 
					 boolean completo =  tabla.print(JTable.PrintMode.FIT_WIDTH, 
							 	new MessageFormat("Resumen semanal 					"
							 			+ "Fecha " + fechaS),
								new MessageFormat(" Pag - {0}"),
										true, null, true,null);
						
						if(completo){
							
							Mensajes.getMensajeImpresion_completa();
						}
						else{
							
							Mensajes.getMensajeImpresion_cancelada();
							
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			
		});
		
	     //pClientes_atrasados.setLayout(new BoxLayout(pPedidos_historicos, BoxLayout.Y_AXIS));
	     
	     pPagos.add(tabla);
	     
	    // pClientes_atrasados.setPreferredSize(new Dimension(850, 600));
	    // scr.setPreferredSize(new Dimension(850, 600));
	     scr.setViewportView(tabla);
	    // scr.add(pPedidos_historicos);
	     
	    // BH_clientesAtrasados bh = new BH_clientesAtrasados(imprimir);
	     resizeColumns();
	     add(pComandos, BorderLayout.PAGE_START);
	     add(scr, BorderLayout.CENTER);
	}
	
	public void iniciar_resumen(java.sql.Date hasta){
		 
		limpiar(tabla);
		
		int cantidad = 0;
	
		ArrayList<Object []> ar_ob = _controladorEmpleado.calcularResumen(hasta);
		
		if(ar_ob!=null){
			
			double total_importe = 0;
			
			for(Object [] d: ar_ob){
					
				
					total_importe += Double.parseDouble(d[7].toString());
					t_model.addRow(d);
					cantidad += 1;
									
			}
			
			Object total []= new Object[8];
			total[6] = "Total";
			total[7] = total_importe;
			
			t_model.addRow(total);
					
		}
		
		tabla.getPreferredScrollableViewportSize().setSize(1000,
				tabla.getRowHeight() * cantidad);
		
		 centrar_columnas(tabla);
		
		pPagos.updateUI();
	
		
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
		public void setControladorEmpleado(ControladorEmpleado _controladorEmpleado){
			
			this._controladorEmpleado = _controladorEmpleado;
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
	
		private String [] getColumnas(){
			
			String columna [] = new String []{ "Empleado", "Puesto", "Pago semanal", "Monto por efec.", 
					"Monto por feriado",
					"Inasistencias", 
					"Adelanto", "Importe final"};
			
			return columna;
		}
		
		private void resizeColumns() {
		    int tW = 750;
		    TableColumn column;
		    TableColumnModel jTableColumnModel = tabla.getColumnModel();
		    int cantCols = jTableColumnModel.getColumnCount();
		    for (int i = 0; i < cantCols; i++) {
		        column = jTableColumnModel.getColumn(i);
		        int pWidth = Math.round(columnWidthPercentage[i] * tW);
		        column.setPreferredWidth(pWidth);
		    }
		}
		
		public void setLabelFecha(java.sql.Date fecha){
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
			
			String date = ft.format(fecha).toString();
			String diaS = "";
			//convert String to LocalDate
			LocalDate localDate = LocalDate.parse(date, formatter);
			
			switch(localDate.getDayOfWeek().name()){
			
			case "MONDAY": diaS = "lunes";
			break;
			case"TUESDAY": diaS = "martes";
			break;
			case"WEDNESDAY": diaS = "miércoles";
			break;
			case"THURSDAY": diaS = "jueves";
			break;
			case"FRIDAY": diaS = "viernes";
			break;
			case"SATURDAY": diaS = "sábado";
			break;
			}
		
			fechaL.setFont(new Font("Arial",Font.BOLD,18));
			fechaL.setText("Fecha " + date + " " + diaS);
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
			
			if(e.getSource()== ver_fb){
				
				ControladorCaja controladorCaja = new ControladorCaja();
				LogicaCaja logica_caja = new LogicaCaja();
				controladorCaja.setLogicaCaja(logica_caja);
				
				
				if(controladorCaja.ingresoFecha_usuario(fecha_hasta.getJFormattedTextField().getText())){
					
					SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
					Date d=null;
					try {
						d = ft.parse(fecha_hasta.getJFormattedTextField().getText());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					java.sql.Date fecha = new java.sql.Date(d.getTime());
						
						this.iniciar_resumen(fecha);	
				
				}
					
			}
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
}

