package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
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
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.LocalidadVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import vista.VistaGestionProveedores.CustomJToolTip;

public class VistaClientesAtrasados extends JInternalFrame  implements  
PropertyChangeListener{

	private JProgressBar progressBar;
 
    private TableSwingWorker task;
	
	private VistaBuscarCliente vista_buscar_cliente;
	private ControladorVendedor _controladorVendedor;
	private ControladorZona _controladorZona;
	private ControladorLocalidad _controladorLocalidad;
	private ControladorPedidos _controladorPedido;
	private ControladorArticulo _controladorArticulo;
	private ControladorCliente controladorCliente;
	private ControladorDomicilioComercial controladorDomCom;
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
	
	private JPanel pClientes_atrasados = new JPanel();
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
	
	private boolean[] canEdit_clientes= new boolean[]{
            false, false, false, false, false, false,false,false
    };
	
	private int cont = 0;
	
	 private float[] columnWidthPercentage = {20.0f,5.0f, 5.0f, 40.0f, 5.0f,5.0f, 10.0f, 10.0f};
	
	public VistaClientesAtrasados(){
		//super(_vista_principal, "Clientes atrasados", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		super("Clientes atrasados", true, true, true, true);
		 this.setLayout(new BorderLayout());
		
	     Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	     setSize(dim.width*70/100, dim.height*70/100);
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
	     
		imprimir.addActionListener(new ActionListener(){

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
			
		});
		
	     //pClientes_atrasados.setLayout(new BoxLayout(pPedidos_historicos, BoxLayout.Y_AXIS));
	     pClientes_atrasados.setName("pClientes_atrasados");
	     
	     pClientes_atrasados.add(tabla);
	     
	    // pClientes_atrasados.setPreferredSize(new Dimension(850, 600));
	    // scr.setPreferredSize(new Dimension(850, 600));
	     scr.setViewportView(tabla);
	    // scr.add(pPedidos_historicos);
	     
	     BH_clientesAtrasados bh = new BH_clientesAtrasados(imprimir);
	     
	     JPanel pProgress = new JPanel();
	     progressBar = new  JProgressBar(0,100);
	     progressBar.setValue(0);
	        progressBar.setStringPainted(true);
	     pProgress.add(progressBar);
	     
	     add(bh, BorderLayout.PAGE_START);
	     add(scr, BorderLayout.CENTER);
	     add(pProgress, BorderLayout.PAGE_END);
	    
	}
	
	public void iniciar(){
		
		//ArrayList<PedidosVO> ar = _controladorPedido.buscarPedidosAll();
		
		int cantidad = 0;
		
		//if(ar!=null){
		
			  task = new TableSwingWorker(t_model/*, ar*/);
		       task.addPropertyChangeListener(this);
		       task.execute();
			
			/*for(PedidosVO pVO : ar){
					
				if(pVO.getDias_mora() >= 7){
					
					ArrayList<Object []> ar_ob = controladorCliente.
							buscarClientes_porPedido(pVO.getN_pedido());
					Object [] datos = new Object[8];
					
					for(Object [] o : ar_ob){
						
						ClienteVO cVO = (ClienteVO)o[0];
						DomicilioParticularVO dpVO = (DomicilioParticularVO)o[1];
						DomicilioComercialVO dcVO = (DomicilioComercialVO) o[2];
						LocalidadVO lVO = (LocalidadVO)o[3];
						
						
						datos[0] = cVO.getNombre() + " " + cVO.getApellido();
						datos[1] = cVO.getId_zona();
						datos[2] = pVO.getN_pedido();
						
						ArrayList<Pedido_articuloVO> ar_pedido = _controladorPedido.
								buscarArticulos_porPedido(pVO.getN_pedido(), true);
						
						String descripcion = "";
						
						if(ar_pedido!=null){
							
							
							for(Pedido_articuloVO paVO : ar_pedido){
								
								ArticulosVO aVO = _controladorArticulo.
										buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
								
								ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
								
								if(apVO!=null){
									
										descripcion = descripcion + " " + "Prestamo(" + apVO.getCodigo() + 
												")$" + Double.toString(apVO.getMonto());
										
								}
								else
									descripcion = descripcion + " " + aVO.getNombre()+
											"(" + aVO.getCodigo() + ")";
							}	
			
						}
						
						datos[3] = descripcion;
						datos[4] = pVO.getDias_mora();
						datos[5] = pVO.getEstado_deuda();
						datos[6] = cVO.getTelefono_linea();
						datos[7] = cVO.getTelefono_movil();
						
					}
					
					t_model.addRow(datos);
				}
				
				
			}*/
		//}
		
		tabla.setModel(t_model);
		//alto_filas();
		tabla.getPreferredScrollableViewportSize().setSize(800,
				tabla.getRowHeight() * cantidad);
		
		 centrar_columnas(tabla);
		//ancho_columnas();
		 resizeColumns();
		pClientes_atrasados.updateUI();
		
		
	}
	
	class TableSwingWorker extends
    	SwingWorker<DefaultTableModel, Object[] > {
    		private final DefaultTableModel tableModel;
    		private ArrayList<PedidosVO> ar;
    		public TableSwingWorker(DefaultTableModel tableModel/*, ArrayList<PedidosVO> ar*/) {
    			this.tableModel = tableModel;
    			//this.ar = ar;
    		}

    		@Override
    		protected DefaultTableModel doInBackground() throws Exception {
    		
    			_vista_principal.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    			
    			 Random random = new Random();
    	          int progress = 0;
    	            //Initialize progress property.
    	         setProgress(0);
    			
    	         ArrayList<PedidosVO> ar = _controladorPedido.buscarPedidosAll();
    	    		
    	         ArrayList<PedidosVO> ar_mora = new ArrayList<PedidosVO>();
    	         
    	         for(PedidosVO pVO : ar){
    	        	 
    	        	 if(pVO.getDias_mora()>=7){
    	        		 
    	        		 ar_mora.add(pVO);
    	        	 }
    	         }
    	         
    	    	int cantidad = 0;
    	    		
    	    	 if(ar!=null){
    	    			 
    	    			 for(PedidosVO pVO : ar_mora){
    	 					
    	    	    			cantidad++;
    	    	    			
    	    	    			//if(pVO.getDias_mora() >= 7){
    	    					
    	    	    				DomicilioComercialVO dc = controladorDomCom.
    	    	    						buscarDomicilioComercial_porIdc(pVO.getIdc());
    	    	    			
    	    	    				//ArrayList<Object []> ar_ob = controladorCliente.
    	    						//	buscarClientes_porPedido(pVO.getN_pedido());
    	    	    				
    	    	    				if(dc!=null){
    	    	    					
    	    	    					System.out.println(dc.getDomicilio());
    	    	    					
    	    	    					Object [] datos = new Object[8];
        	    						
        	    	    					ClienteVO cVO = controladorCliente.buscarCliente
        	    	    							(Integer.toString(dc.getDni()));
        	    	    					
        	    	    					LocalidadVO lVO = _controladorLocalidad.buscarLocalidad
        	    	    							(Integer.toString(dc.getId_localidad()));
        	    						
        	    						
        	    	    					datos[0] = cVO.getNombre() + " " + cVO.getApellido();
        	    	    					datos[1] = dc.getId_zona();
        	    	    					datos[2] = pVO.getN_pedido();
        	    						
        	    	    					ArrayList<Pedido_articuloVO> ar_pedido = _controladorPedido.
        	    								buscarArticulos_porPedido(pVO.getN_pedido(), true);
        	    						
        	    	    					String descripcion = "";
        	    						
        	    	    					if(ar_pedido!=null){
        	    							
        	    							
        	    	    						for(Pedido_articuloVO paVO : ar_pedido){
        	    								
        	    	    							ArticulosVO aVO = _controladorArticulo.
        	    										buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
        	    								
        	    	    							ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
        	    								
        	    	    							if(apVO!=null){
        	    									
        	    	    								descripcion = descripcion + " " + "Prestamo(" + apVO.getCodigo() + 
        	    												")$" + Double.toString(apVO.getMonto());
        	    										
        	    	    							}
        	    								/*else if(paVO.getCantidad()>1)
        	    									descripcion = descripcion + " " + aVO.getNombre() +
        	    									"(" + aVO.getCodigo() + ")" + "x" + paVO.getCantidad();*/
        	    	    							else
        	    	    								descripcion = descripcion + " " + aVO.getNombre()+
        	    											"(" + aVO.getCodigo() + ")";
        	    	    						}	
        	    			
        	    	    					}
        	    						
        	    	    					datos[3] = descripcion;
        	    	    					datos[4] = pVO.getDias_mora();
        	    	    					datos[5] = pVO.getEstado_deuda();
        	    	    					datos[6] = cVO.getTelefono_linea();
        	    	    					datos[7] = cVO.getTelefono_movil();
        	    						
        	    			
        	    					//Sleep for up to one second.
        	    					
        	    	    				publish((Object[]) datos);
        	    	    			//}
        	    	    			try {
        	    	    				Thread.sleep(random.nextInt(1000));
        	    	    			} catch (InterruptedException ignore) {}
        	    	    			//Make random progress.
        	    	    			progress += random.nextInt(10);
        	    	    			setProgress(Math.min(progress, 100));	
    	    	    				}
    	    	    			
    	    	    	}
    	    	}
    	    	
    	    	 _vista_principal.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    	    	 
    			return tableModel;
    			
    		}

    		@Override
    		protected void process(List<Object []> chunks) {
    			for (Object[] row : chunks) {
    				tableModel.addRow(row);
    			}
    		}
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
		
		public void setControladorMonto_trasladado(ControladorMonto_trasladado _controladorMonto_t){
			
			this._controladorMonto_t = _controladorMonto_t;
		}
		
		public void setControladorObservaciones(ControladorObservaciones _controladorObservaciones){
			
			this._controladorObservaciones = _controladorObservaciones;
		}
		
		public void setControladorCliente(ControladorCliente controladorCliente){
			
			this.controladorCliente = controladorCliente;
		}
		public void setControladorDomicilioComercial(ControladorDomicilioComercial controladorDomCom){
			
			this.controladorDomCom = controladorDomCom;
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
			
			String columna [] = new String []{ "Cliente", "Zona", "Npedido", "Detalle", "Dias mora", "Monto", 
					"Teléfono1", "Teléfono2"};
			
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
		
		class CustomJToolTip extends JToolTip {

		    public CustomJToolTip(JComponent component) {
		        super();
		        setComponent(component);
		        setBackground(Color.white);
		        setForeground(Color.BLACK);
		    }
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			// TODO Auto-generated method stub
			 if ("progress" == evt.getPropertyName()) {
				 
		            int progress = (Integer) evt.getNewValue();
		            progressBar.setValue(progress);
		           
		           
		        } 
		}
}
