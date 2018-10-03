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
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controlador.ControladorObservaciones;
import controlador.ControladorPagoDiario;
import controlador.Principal;
import modelo.Mensajes;
import modelo_vo.ObservacionesVO;
import vista.VistaClientesAtrasados.CustomJToolTip;

public class Vista_pagos_porPedido extends JInternalFrame implements ActionListener{

	private JTable tabla = new JTable();
	private DefaultTableModel t_model; ;
	private JScrollPane scr;
	private JPanel pTabla = new JPanel();
	private JPanel pComandos = new JPanel();
	private JButton guardar = new JButton("Guardar");
	private JButton salir = new JButton("Salir");
	private JTextArea observaciones; 
	private JLabel obL = new JLabel();
	private JLabel cobradorL = new JLabel();
	private JLabel clienteL = new  JLabel();
	
	private static  VistaBuscarPedidos_porClientes _vista_buscarPedido_cliente;
	private ControladorPagoDiario _controladorPagoDiario;
	
	private VistaBuscarCliente vista_buscar_cliente;
	
	 private JButton imprimir = new JButton(){
	        //override the JButtons createToolTip method
	        @Override
	        public JToolTip createToolTip() {
	            return (new CustomJToolTip(this));
	        }
	    };;
	
	private boolean[] canEdit= new boolean[]{
            false, false, false, false, false, false
    };
	
	 private float[] columnWidthPercentage =  {10.0f,30.0f, 15.0f, 15.0f, 15.0f,15.0f};
	
	public Vista_pagos_porPedido(){
		//super(_vista_buscarPedido_cliente, "Seguimiento de pagos", 
			//	JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		super("Seguimiento de pagos", true, true, true, true);
		guardar.addActionListener(this);
		salir.addActionListener(this);
		observaciones = new JTextArea(2,30);
    	observaciones.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	observaciones.setLineWrap(true);
		
		this.setLayout(new BorderLayout());
		
		// this.setTitle("Pagos de pedido");
	        //Create and set up the content pane.
	        //JComponent newContentPane = new BusquedaEntities();
	       // newContentPane.setOpaque(true); //content panes must be opaque
	       // frame.setContentPane(newContentPane);
	        

	        //Display the window.
	       
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        this.setSize(dim.width*50/100, dim.height*70/100);
	       // this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	       // frame.getContentPane().add(scr); 
	    
	        	
	       // if(_vista_buscarPedido_cliente.isShowing()) _vista_buscarPedido_cliente.setEnabled(false);
	        
	        //scr.setViewportView(tabla);
	        this.setFocusable(true);
	        this.setResizable(false);
	        //this.setLocationRelativeTo(null);
			//frame.pack();
	        setClosable(true); 
	        setIconifiable(true); 
	        setMaximizable(true); 
	       
	        //_vista_principal.setEnabled(false);
	        
	        t_model = new DefaultTableModel(null, getColumnas()){
	        	
	        	public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit[columnIndex];
	            }
	        };
			tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
			tabla.getTableHeader().setReorderingAllowed(false);
			tabla.setModel(t_model);
			
			pTabla.setBackground(Color.white);
	        
	        scr = new JScrollPane();//(pTabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			//JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        //scr.add(tabla);
	        scr.setViewportView(tabla);
	        pTabla.add(scr);
	        pTabla.setPreferredSize(new Dimension(dim.width*45/100,2000));
	        scr.setPreferredSize(new Dimension(dim.width*45/100, 700));
	        
	        pComandos.add(cobradorL);
	        //pComandos.add(observaciones);
	        //pComandos.add(guardar);
	        //pComandos.add(salir);
	        
	        imprimir.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
						
					try {
						 
						 Date d = new Date();
						 java.sql.Date hoy = new java.sql.Date(d.getTime());
						 
		                PrintPanePagos printPane = new 
		                		PrintPanePagos(t_model, hoy);

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
				
			});
	        
	        BH_seguimientoPago bh = new BH_seguimientoPago(imprimir);
	        
	        JPanel pIntegra_norte = new JPanel();
	        
	        pIntegra_norte.setLayout(new BoxLayout(pIntegra_norte, BoxLayout.Y_AXIS));
	        pIntegra_norte.add(bh);
	        pIntegra_norte.add(pComandos);
	        
	        this.add(pIntegra_norte, BorderLayout.PAGE_START);
	        this.add(pTabla, BorderLayout.CENTER);
//	        pTabla.updateUI();
	}
	
	public void iniciar(ArrayList<Object []> ar){
		 
		
		/*this. addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
               
            	//_vista_buscarPedido_cliente.setEnabled(true);
                e.getWindow().dispose();
            }
           
        });*/
		
		
		int cantidad = 0;
		
		//tabla  = new JTable();
		
		
		if(ar!=null){
			
			cobradorL.setText("Cobrador: " + ar.get(0)[3] + " " + ar.get(0)[4]);
			cobradorL.setFont(new Font("Arial", Font.PLAIN, 16));
			
			for(Object [] datos : ar){
				
				SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
				
				String date2 =  new String(sf.format(datos[6]));
				
				Object [] o = new Object[6];
				o[0] = datos[0];
				o[1] = datos[1];
				o[2] = datos[2];
				o[3] = datos[5];
				o[4] = date2;
				o[5] = datos[7];
				
				t_model.addRow(o);
				cantidad += 1;
			}
		}
		
		
		/*tabla.getPreferredScrollableViewportSize().setSize(900,
				tabla.getRowHeight() * cantidad);*/
	
		//ancho_columnas();
		resizeColumns();
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla.getColumnCount(); i ++)
			
			tabla.getColumnModel().getColumn(i).setCellRenderer(cent);
		
		//pTabla.add(tabla);
		
		
		//this.add(pComandos, BorderLayout.SOUTH);
		this.setVisible(true);
		
	}
	
	private void ancho_columnas(){
		
		for(int i = 0; i < tabla.getColumnCount(); i++){
			
			if(i == 2) tabla.getColumnModel().getColumn(i).setPreferredWidth(250);
			else tabla.getColumnModel().getColumn(i).setPreferredWidth(50);
		}
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
    
    public void setControladorPagoDiario(ControladorPagoDiario _controladorPagoDiario){
    	
    	this._controladorPagoDiario = _controladorPagoDiario;
    }
   
    
    public void limpiar(){
    	
    	if(tabla.getRowCount() > 0){
			
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			
			int contFila = tabla.getRowCount();
			
			for(int i = 0; i < contFila; i++)
			
				modelo.removeRow(0);
		}
    	obL.setText("");
    	observaciones.setText("");
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
	
	
	class CustomJToolTip extends JToolTip {

	    public CustomJToolTip(JComponent component) {
	        super();
	        setComponent(component);
	        setBackground(Color.white);
	        setForeground(Color.BLACK);
	    }
	}
}
