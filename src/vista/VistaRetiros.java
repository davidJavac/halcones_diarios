package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorArticulo;
import controlador.ControladorCambio_plan;
import controlador.ControladorCombo;
import controlador.ControladorDA;
import controlador.ControladorObservaciones;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorRetiros;
import controlador.Principal;
import modelo.LogicaArticulo;
import modelo.LogicaCambio_plan;
import modelo.LogicaCombo;
import modelo.LogicaDA;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Cambio_planVO;
import modelo_vo.DAVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.ObservacionesVO;
import modelo_vo.RetirosVO;

public class VistaRetiros extends JInternalFrame implements ActionListener{

	private JTable tabla = new JTable();
	private DefaultTableModel t_model; ;
	private JScrollPane scr;
	private JPanel pTabla = new JPanel();
	private JTable tabla_retirados = new JTable();
	private DefaultTableModel t_model_retirados ;
	private JScrollPane scr_retirados;
	private JPanel pTabla_retirados = new JPanel();
	private JPanel pIntegra = new JPanel();
	private JPanel pComandos = new JPanel();
	private JPanel pComandos_retirados = new JPanel();
	private JPanel pDA = new JPanel();
	private JPanel pPlan = new JPanel();
	
	private JTextField diasTF = new JTextField(5);
	private JTextField cuotaTF = new JTextField(5);
	private JTextField porcentajeTF = new JTextField(5);
	
	private JRadioButton da_si = new JRadioButton("Si");
	private JRadioButton da_no = new JRadioButton("No");
	private ButtonGroup grupo_da = new ButtonGroup();
	
	private JButton retirar = new JButton("Retirar");
	private JButton cancelar_retiro = new JButton("Cancelar retiro");
	private JButton salir = new JButton("Salir");
	private JTextArea observaciones; 
	private JLabel obL = new JLabel();
	private JLabel cobradorL = new JLabel();
	private JLabel clienteL = new  JLabel();
	private JLabel observacionesL = new  JLabel();
	
	private JPanel pObservaciones = new JPanel();
	private static VistaBuscarPedidos_porClientes _vista_buscarPedido_cliente;
	private ControladorPagoDiario _controladorPagoDiario;
	private ControladorArticulo _controladorArticulo;
	private ControladorDA _controladorDA;
	private ControladorCombo _controladorCombo;
	private ControladorRetiros _controladorRetiro;
	private ControladorPedidos controladorPedidos;
	private ControladorObservaciones controladorObservaciones;
	private BusquedaEntities be;

	private ArticulosVO nuevoArticuloVO = new ArticulosVO();
	
	private ArrayList<Pedido_articuloVO> arPedido_articulo = new ArrayList<Pedido_articuloVO>();
	
	private VistaBuscarCliente vista_buscar_cliente;
	
	 private boolean[] canEdit_retiro= new boolean[]{
	            false, false,false, false,false
	    };
	
	public VistaRetiros(){
		//super(_vista_buscarPedido_cliente, "Retiros", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		super("Retiros", true, true, true, true);
		retirar.addActionListener(this);
		cancelar_retiro.addActionListener(this);
		salir.addActionListener(this);
		observaciones = new JTextArea(2,20);
    	observaciones.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	observaciones.setLineWrap(true);
		
		pIntegra.setLayout(new BoxLayout(pIntegra, BoxLayout.Y_AXIS));
	   
	       
		 this.setSize(500, 600);
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	       // this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	       // frame.getContentPane().add(scr); 
	    
	        	
	       // if(_vista_buscarPedido_cliente.isShowing()) _vista_buscarPedido_cliente.setEnabled(false);
	        
	        //scr.setViewportView(tabla);
	        this.setFocusable(true);
	        this.setResizable(false);
	       // this.setLocationRelativeTo(null);
	        
	        setClosable(true); 
	        setIconifiable(true); 
	        setMaximizable(true); 
	        
	    	pTabla.setBackground(Color.white);
	        
	        t_model = new DefaultTableModel(null, getColumnas()){
	        	
	        	 public boolean isCellEditable(int rowIndex, int columnIndex) {
		                return canEdit_retiro[columnIndex];
		            }
	        };
			tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
	        tabla.getTableHeader().setReorderingAllowed(false);
	        scr = new JScrollPane();//(pTabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			//JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    
	        scr.setViewportView(tabla);
	        scr.setPreferredSize(new Dimension(450,150));

	        pTabla_retirados.setBackground(Color.white);
	        
	        t_model_retirados = new DefaultTableModel(null, getColumnas()){
	        	
	        	public boolean isCellEditable(int rowIndex, int columnIndex) {
	        		return canEdit_retiro[columnIndex];
	        	}
	        };
	        tabla_retirados.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
	        tabla_retirados.getTableHeader().setReorderingAllowed(false);
	        tabla_retirados.setModel(t_model_retirados);
	        scr_retirados = new JScrollPane();//(pTabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	        //JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        scr_retirados.setViewportView(tabla_retirados);
	        scr_retirados.setPreferredSize(new Dimension(450,150));
	       // pTabla.add(scr);
	        pTabla.updateUI();
	        
	        //pTabla_retirados.add(tabla_retirados);
	        
	        pComandos.add(retirar);
	        pComandos_retirados.add(cancelar_retiro);
	        pComandos.setBackground(Color.white);
	        
	        JPanel pIntegra_cancel_retiros = new JPanel(); 
	        
	        pIntegra_cancel_retiros.setBackground(Color.WHITE);
	        
	        Border borde_da = BorderFactory.createTitledBorder(null, "Descuento administrativo", 
	       	    	TitledBorder.CENTER, TitledBorder.TOP,
	       	    	new Font("Arial",Font.PLAIN,14), Color.BLACK);
	           pDA.setBorder(borde_da);
	        Border borde_plan = BorderFactory.createTitledBorder(null, "Plan", 
	        		   TitledBorder.CENTER, TitledBorder.TOP,
	        		   new Font("Arial",Font.PLAIN,14), Color.BLACK);
	           pPlan.setBorder(borde_plan);
	           Border borde_cancel = BorderFactory.createTitledBorder(null, "Articulos retirados", 
	        		   TitledBorder.CENTER, TitledBorder.TOP,
	        		   new Font("Arial",Font.PLAIN,14), Color.BLACK);
	           pIntegra_cancel_retiros.setBorder(borde_cancel);
	        
	        JLabel lDias = new JLabel();   
	        JLabel lCuota = new JLabel();   
	           
	        lDias.setText("Dias");
	        lCuota.setText("Cuota");
	        
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
	  		porcentajeTF.addFocusListener(new FocusListener(){
	  			
	  			@Override
	  			public void focusGained(FocusEvent e) {
	  				// TODO Auto-generated method stub
	  				porcentajeTF.setBackground(new Color(183, 242, 113));
	  			}
	  			
	  			@Override
	  			public void focusLost(FocusEvent e) {
	  				// TODO Auto-generated method stub
	  				
	  				//actualizarCredito();
	  				
	  				porcentajeTF.setBackground(new Color(255, 255, 255));
	  				
	  			}
	  			
	  			
	  		});
	        
	        pPlan.add(lDias);
	        pPlan.add(diasTF);
	        pPlan.add(lCuota);
	        pPlan.add(cuotaTF);
	        
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
						
						porcentajeTF.setEnabled(true);
					}
				}
		    	
		    	
		    });
		    da_no.addActionListener(new ActionListener(){
		    	
		    	@Override
		    	public void actionPerformed(ActionEvent e) {
		    		// TODO Auto-generated method stub
		    		if(da_no.isSelected()){
		    			
		    			porcentajeTF.setText("");
		    			porcentajeTF.setEnabled(false);
		    		}
		    	}
		    	
		    	
		    });
	        
	        pDA.add(da_si);
	        pDA.add(da_no);
	        JLabel lPorcentaje = new JLabel();
	        lPorcentaje.setText("Porcentaje");
	        pDA.add(lPorcentaje);
	        pDA.add(porcentajeTF);
	        
			observacionesL.setText("Observaciones");
			JScrollPane scr_ob = new JScrollPane();
			scr_ob.setViewportView(observaciones);
			
			pObservaciones.add(observacionesL);
			pObservaciones.add(scr_ob);
			
			pPlan.setBackground(Color.WHITE);
			pDA.setBackground(Color.WHITE);
			pObservaciones.setBackground(Color.WHITE);
			
			pIntegra_cancel_retiros.setLayout(new BorderLayout());
			pIntegra_cancel_retiros.add(pComandos_retirados, BorderLayout.PAGE_START);
			pIntegra_cancel_retiros.add(scr_retirados, BorderLayout.CENTER);
			
			pIntegra.add(pComandos);
			pIntegra.add(scr);
			pIntegra.add(pPlan);
			pIntegra.add(pDA);
			pIntegra.add(pObservaciones);
			pIntegra.add(pIntegra_cancel_retiros);
		
			this.add(pIntegra);
			//frame.pack();
	       
	        //_vista_principal.setEnabled(false);
	}
	
	public void iniciar(PedidosVO pedidoVO){
		
		limpiar();
		
		int cantidad = 0;
		
		if(pedidoVO!=null){
	
			ArrayList<Pedido_articuloVO> ar_pedidos = controladorPedidos.
					buscarArticulos_porPedido(_vista_buscarPedido_cliente.getPedidosVO().getN_pedido(), true);
			
			if(ar_pedidos!=null){
				
				for(Pedido_articuloVO paVO : ar_pedidos){
					
					
					Object [] datos = new Object[5];
										
					ArticulosVO aVO = _controladorArticulo.
							buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
			
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
					
					datos[3] = paVO.getDias();
					datos[4] = paVO.getCuota();
					
					t_model.addRow(datos);
					
				}
				
				
			}
			ArrayList<Pedido_articuloVO> ar_pedidos_retirados = controladorPedidos.
					buscarArticulos_porPedido(_vista_buscarPedido_cliente.getPedidosVO().getN_pedido(), false);
			
			if(ar_pedidos_retirados!=null){
				
				for(Pedido_articuloVO paVO : ar_pedidos_retirados){
					
					
					Object [] datos = new Object[5];
					
					ArticulosVO aVO = _controladorArticulo.
							buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
					
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
					
					datos[3] = paVO.getDias();
					datos[4] = paVO.getCuota();
					
					RetirosVO rVO = _controladorRetiro.buscarRetiro_porId_pa(paVO.getId());
					
					if(rVO!=null)
					
						t_model_retirados.addRow(datos);
					
				}
				
				
			}
			
		}
		
		tabla.setModel(t_model);
		
		tabla.getPreferredScrollableViewportSize().setSize(900,
				tabla.getRowHeight() * cantidad);
	
		ancho_columnas();
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla.getColumnCount(); i ++){
			
			tabla.getColumnModel().getColumn(i).setCellRenderer(cent);
			tabla_retirados.getColumnModel().getColumn(i).setCellRenderer(cent);
			
		}
			
		
		//pComandos.add(cobradorL);
		
		this.setVisible(true);
		
	}
	
	private void ancho_columnas(){
		
		for(int i = 0; i < tabla.getColumnCount(); i++){
			
			if(i == 2) {
				
				tabla.getColumnModel().getColumn(i).setPreferredWidth(250);
				tabla_retirados.getColumnModel().getColumn(i).setPreferredWidth(250);
			}
			else{
				
				tabla.getColumnModel().getColumn(i).setPreferredWidth(50);
				tabla_retirados.getColumnModel().getColumn(i).setPreferredWidth(50);
			}
		}
	}
	
	private String [] getColumnas(){
		
		String columna [] = new String []{"Id","Código", "Descripcion", "Días", "Cuota"};
		
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
    
    public void setControladorArticulo(ControladorArticulo _controladorArticulo){
    	
    	this._controladorArticulo = _controladorArticulo;
    }
    
    public void setControladorCombo(ControladorCombo _controladorCombo){
    	
    	this._controladorCombo = _controladorCombo;
    }
    
    public void setControladorRetiros(ControladorRetiros _controladorRetiro){
    	
    	this._controladorRetiro = _controladorRetiro;
    }
    
    public void setControladorPedidos(ControladorPedidos _controladorPedido){
    	
    	this.controladorPedidos = _controladorPedido;
    }
    public void setControladorDA(ControladorDA _controladorDA){
    	
    	this._controladorDA = _controladorDA;
    }
   
    public void setControladorObservaciones(ControladorObservaciones _controladorObservaciones){
    	
    	this.controladorObservaciones = _controladorObservaciones;
    }
    public void setNuevoArticulo_plan(ArticulosVO nuevoArticuloVO){
    	
    	this.nuevoArticuloVO = nuevoArticuloVO;
    }
    
    public void setBusquedaEntities(BusquedaEntities be){
    	
    	this.be = be;
    }
    
    public void limpiar(){
    	
    	if(tabla.getRowCount() > 0){
			
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			
			int contFila = tabla.getRowCount();
			
			for(int i = 0; i < contFila; i++)
			
				modelo.removeRow(0);
		}
    	if(tabla_retirados.getRowCount() > 0){
    		
    		DefaultTableModel modelo = (DefaultTableModel) tabla_retirados.getModel();
    		
    		int contFila = tabla_retirados.getRowCount();
    		
    		for(int i = 0; i < contFila; i++)
    			
    			modelo.removeRow(0);
    	}
    	obL.setText("");
    	observaciones.setText("");
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==cancelar_retiro){
			
			if(tabla_retirados.getSelectedRows().length > 0){
				
				int opcion = Mensajes.getMensaje_confirmacion_anulacion_retiro();
				
				if(opcion==JOptionPane.YES_OPTION){

					int res = 0;
					
					int [] ar = tabla_retirados.getSelectedRows();
					
					for(int i = 0; i < ar.length; i++){
						
						int id_pa = Integer.parseInt(tabla_retirados.getModel().getValueAt(ar[i], 0).toString());
						
						RetirosVO rVO = _controladorRetiro.buscarRetiro_porId_pa(id_pa);
						
						if(rVO!=null)
							
							res = _controladorRetiro.deleteRetiro(rVO);
						
						controladorPedidos.update_estadoPedido_articulo(id_pa, true);
						
					}
					
					if(res > 0){
						
						Mensajes.getMensaje_retiroRechazado();
						iniciar(_vista_buscarPedido_cliente.getPedidosVO());
					}
					else Mensajes.getMensaje_modificacion_sinExito();
					
					
				}
				
			}
			else Mensajes.getMensaje_sinFilasSeleccionadas();
		}
		
		if(e.getSource() == retirar){
			if(tabla.getRowCount()==tabla.getSelectedRowCount()){
				
				da_si.setSelected(false);
				da_no.setSelected(true);
			}
			
			if(_controladorRetiro.validarRetiroUsuario
					(tabla, diasTF, cuotaTF, porcentajeTF, da_si, observaciones)){
				
				int confirmacion = Mensajes.getMensaje_confirmacion_retiro();
				
				if(confirmacion == JOptionPane.YES_OPTION){
					
					Date d = new java.util.Date();
					java.sql.Date fecha_registro = new java.sql.Date(d.getTime());
					java.sql.Time hora_registro = new java.sql.Time(d.getTime());
					
					boolean baja = false;
					
					ArticulosVO artVO = null;
					
					short id_vendedor = vista_buscar_cliente.getClienteVO().getId_vendedor();
					int res = 0;
					int res_p = 0;

					int dias = Integer.parseInt(diasTF.getText());
					int cuota = Integer.parseInt(cuotaTF.getText());
					
					if(tabla.getRowCount()==tabla.getSelectedRowCount()){
						
						baja = true;
						
						
						for(int i = 0; i < tabla.getSelectedRowCount(); i++){
							
							RetirosVO rVO = new RetirosVO();
							
							rVO.setId_pedido_articulo(Integer.parseInt(tabla.getModel().getValueAt(i, 0).toString()));
							rVO.setN_pedido(_vista_buscarPedido_cliente.getPedidosVO().getN_pedido());
							rVO.setCodigo(Integer.parseInt(tabla.getModel().getValueAt(i, 1).toString()));
							System.out.println("codigo pedido" + tabla.getModel().getValueAt(i, 1).toString());
							//rVO.setCodigo(Integer.parseInt(tabla.getModel().getValueAt(i, 0).toString()));
							rVO.setId_vendedor(id_vendedor);
							rVO.setFecha_registro(fecha_registro);
							rVO.setHora_registro(hora_registro);
							rVO.setId_usuario(Principal._usuario.getId_usuario());
							rVO.setObservaciones(observaciones.getText());
							
							res = _controladorRetiro.insertRetiro(rVO, dias, cuota,"baja");
							res_p = controladorPedidos.deletePedido_articulo(rVO.getId_pedido_articulo());
									//rVO.getN_pedido(), rVO.getCodigo());
						}	
						
						ObservacionesVO obVO = new ObservacionesVO();
						
						obVO.setId(_vista_buscarPedido_cliente
								.getPedidosVO().getN_pedido());
						
						obVO.setFecha_registro(fecha_registro);
						obVO.setHora_registro(hora_registro);
						obVO.setId_usuario(Principal._usuario.getId_usuario());
						obVO.setObservacion( observaciones.getText());
						
						int res_ob = controladorObservaciones.altaObservacionPedido(obVO);
						
					}
					else{/* if(tabla.getRowCount() > tabla.getSelectedRowCount() 
							&& tabla.getSelectedRowCount() > 0){
						
						int [] array_int = tabla.getSelectedRows();
						
						int fila = 0;
						
						if(tabla.getRowCount() - tabla.getSelectedRowCount() == 1){
							
							boolean fila_no_select;
							
							for(int i = 0; i < tabla.getRowCount(); i++){
								
								fila_no_select = true;
								
								for(int j = 0; j < tabla.getSelectedRowCount(); j++){
									
									System.out.println("fila seleccionada " + array_int[j]);
									
									if(i== array_int[j]) fila_no_select = false;
								}
								
								if(fila_no_select) fila = i; 
								
							}
							
							artVO = _controladorArticulo.
									buscarArticulo_porCodigo(Integer.parseInt(tabla.getModel()
											.getValueAt(fila, 0).toString()));
							
							for(int i = 0; i < tabla.getSelectedRowCount(); i++){
								
								RetirosVO rVO = new RetirosVO();
								
								rVO.setN_pedido(_vista_buscarPedido_cliente.getPedidosVO().getN_pedido());
								rVO.setCodigo(Integer.parseInt(tabla.getModel().getValueAt(array_int[i], 0).toString()));
								rVO.setId_vendedor(id_vendedor);
								rVO.setFecha_registro(fecha_registro);
								rVO.setHora_registro(hora_registro);
								rVO.setId_usuario(Principal._usuario.getId_usuario());
								rVO.setObservaciones(observaciones.getText());
								
								res = _controladorRetiro.insertRetiro(rVO, "activo");
								
							}
							
						}
						else{*/
							
							int [] array_int = tabla.getSelectedRows();
							
							for(int i = 0; i < tabla.getSelectedRowCount(); i++){
								
								RetirosVO rVO = new RetirosVO();
								
								rVO.setId_pedido_articulo(Integer.parseInt
										(tabla.getModel().getValueAt(array_int[i], 0).toString()));
								rVO.setN_pedido(_vista_buscarPedido_cliente.getPedidosVO().getN_pedido());
								rVO.setCodigo(Integer.parseInt(tabla.getModel().
										getValueAt(array_int[i], 1).toString()));
								rVO.setId_vendedor(id_vendedor);
								rVO.setFecha_registro(fecha_registro);
								rVO.setHora_registro(hora_registro);
								rVO.setId_usuario(Principal._usuario.getId_usuario());
								rVO.setObservaciones(observaciones.getText());
								
								res = _controladorRetiro.insertRetiro(rVO,dias, cuota, "activo");
								
								ArrayList<Pedido_articuloVO> ar_pedidos = controladorPedidos.
										buscarArticulos_porPedido(rVO.getN_pedido(), true);
								
								if(ar_pedidos!=null){
									
									for(Pedido_articuloVO paVO : ar_pedidos){
										
										if(paVO.getId()==Integer.parseInt(tabla.
												getModel().getValueAt(array_int[i], 0).toString())){
											
											/*if(paVO.getCantidad()>1){
												
												res_p = controladorPedidos.quitarCantidadPedido_articulo
														(rVO.getN_pedido(),
																rVO.getCodigo(), 1);
												
											}	
											else{*/
												System.out.println("(vistaretiro)pedido retirado codigo " + paVO.getCodigo_articulo()+
														" " + paVO.getDias() + " " + paVO.getCuota());
												arPedido_articulo.add(paVO);
												res_p = controladorPedidos.deletePedido_articulo(paVO.getId());
												
											//}
										}
										
									}
									
								}
									
								
							}
							
							ObservacionesVO obVO = new ObservacionesVO();
							
							obVO.setId(_vista_buscarPedido_cliente
									.getPedidosVO().getN_pedido());
							
							obVO.setFecha_registro(fecha_registro);
							obVO.setHora_registro(hora_registro);
							obVO.setId_usuario(Principal._usuario.getId_usuario());
							obVO.setObservacion( observaciones.getText());
							
							int res_ob = controladorObservaciones.altaObservacionPedido(obVO);
							
							DAVO dVO = new DAVO();
							
							dVO.setNpedido(_vista_buscarPedido_cliente.getPedidosVO().getN_pedido());
							
							System.out.println(porcentajeTF.getText());
							
							if(porcentajeTF.getText().equals("")){
								
								dVO.setMonto(0);
							}
							else{
								
								double monto = _controladorDA.calculoDescuento(
										arPedido_articulo,_vista_buscarPedido_cliente.getPedido_originalVO(),
										Double.parseDouble(porcentajeTF.getText()));	
								dVO.setMonto(monto);
							}
							
							_controladorDA.insertDA(dVO);
						
					}
					
					if(res > 0){
						
						Mensajes.getMensaje_retiroExitoso();
						
						if(baja) _vista_buscarPedido_cliente.dispose();
						
						
						PedidosVO pVO = controladorPedidos.buscarPedido_porNpedido(
								_vista_buscarPedido_cliente.getPedidosVO().getN_pedido());
						
						controladorPedidos.logicaGeneral(pVO);
						
						_vista_buscarPedido_cliente.mostrarPedido(pVO);	
						this.dispose();
						
					}
					else Mensajes.getMensaje_altaErrorGenerico();
				}
				
			}
			else Mensajes.getMensaje_altaErrorGenerico();
			
					
		}
	}
}
