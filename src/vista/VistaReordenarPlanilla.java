package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolTip;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controlador.ControladorArticulo;
import controlador.ControladorCliente;
import controlador.ControladorCombo;
import controlador.ControladorDespacho_diario;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorPrestamo;
import controlador.Principal;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.Despacho_diarioVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.ObservacionesVO;
import modelo_vo.PedidosVO;
import vista.VistaIngresos.CustomJToolTip;

public class VistaReordenarPlanilla extends JDialog implements ActionListener{

	private JTable tabla_clientes = new JTable();
	private DefaultTableModel t_model_clientes;
	private JScrollPane scr;
	private JPanel pIntegra = new JPanel();
	private JPanel pTabla = new JPanel();
	private JPanel pComandos = new JPanel();
	private JButton guardar = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };
    
    private JButton arriba = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };
    
    private JButton abajo = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };
	
	private VistaBuscarPedidos_porClientes _vista_buscarPedido_cliente;
	private ControladorPagoDiario _controladorPagoDiario;
	private ControladorArticulo _controladorArticulo;
	private ControladorCombo _controladorCombo;
	private ControladorPrestamo _controladorPrestamo;
	private ControladorDespacho_diario cdp;
	private ControladorPedidos _controladorPedido;
	private ControladorCliente _controladorCliente;
	private static VistaPrincipal _vista_principal;
	
	private VistaBuscarCliente vista_buscar_cliente;
	
	private boolean[] canEdit= new boolean[]{
            false, false, false,false
    };
	
	public VistaReordenarPlanilla(){
		super(_vista_principal, "Reordenar planilla", Dialog.ModalityType.APPLICATION_MODAL);
		
		guardar.addActionListener(this);
		arriba.addActionListener(this);
		abajo.addActionListener(this);
		
		//arriba.addMouseListener(new MouseCustom());
		
			pIntegra.setLayout(new BorderLayout());
	        
			pIntegra.setBorder(new EmptyBorder(10,10,10,10));
	        //Display the window.
	       
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        this.setSize(dim.width*80/100, dim.height*80/100);
	        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	    
	        this.setFocusable(true);
	        this.setResizable(false);
	        this.setLocationRelativeTo(null);
	        
	        tabla_clientes.getTableHeader().setReorderingAllowed(false);
	      
	        tabla_clientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        
	        BH_reordenar bh = new BH_reordenar(guardar, arriba, abajo);
	        pTabla.add(tabla_clientes);
	        
			scr = new JScrollPane(pTabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			scr.setViewportView(tabla_clientes);
	          
			pIntegra.add(bh, BorderLayout.NORTH);
			pIntegra.add(scr, BorderLayout.CENTER);
			
			this.add(pIntegra);
	       
	}
	
	public void iniciar(ArrayList<DomicilioComercialVO> ar){
		 
		_vista_principal.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		int cantidad = 0;
		//pIntegra.remove(pIntegra_pendientes);
		//tabla  = new JTable();
		t_model_clientes = new DefaultTableModel(null, getColumnas1()){
			
			public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
		};
		tabla_clientes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		
		pTabla.setBackground(Color.white);
		
		boolean comprobacion;
		
		if(ar!=null){
		
			for(DomicilioComercialVO dc : ar){
				
				ClienteVO cVO = _controladorCliente.buscarCliente(Integer.toString(dc.getDni()));
				
				ArrayList<PedidosVO> ar_pedidos= _controladorPedido.buscarPedidos_porCliente(cVO.getDni());

				//ArrayList<PedidosVO> ar_pedidos = _controladorPedido.buscarPedidos_porIdc(dc.getIdc());
				
				comprobacion = false;
				
				if(ar_pedidos!=null){
					
					for(PedidosVO pVO : ar_pedidos){
						
						if(!pVO.getEstado_pedido().equals("pendiente entrega") &&
								pVO.getIdc()==dc.getIdc()) comprobacion = true;
					}
						
				}
				
				if(comprobacion){
					
					Object [] obj = new Object[4];
					obj[0] = dc.getN_orden_planilla();
					obj[1] = cVO.getNombre() + " " + cVO.getApellido();
					obj[2] = dc.getIdc();
					obj[3] = dc.getDomicilio();
					
					t_model_clientes.addRow(obj);
				}
				
			}
		}
		tabla_clientes.setRowHeight(25);
		
		tabla_clientes.setFont(new Font("Arial", Font.PLAIN, 16));
		
		tabla_clientes.setModel(t_model_clientes);
		
		TableColumnModel tcm = tabla_clientes.getColumnModel();
		tcm.removeColumn( tcm.getColumn(2));
	
		tabla_clientes.getPreferredScrollableViewportSize().setSize(900,
				tabla_clientes.getRowHeight() * cantidad);
		
		//tabla_pendientes.setRowSelectionAllowed(true);
		//tabla_pendientes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	
		ancho_columnas();
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla_clientes.getColumnCount(); i ++)
			
			tabla_clientes.getColumnModel().getColumn(i).setCellRenderer(cent);
		
		pIntegra.updateUI();
		
		_vista_principal.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			
	}
	
	
	
	
	
	
	private void ancho_columnas(){
		
		for(int i = 0; i < tabla_clientes.getColumnCount(); i++){
			
			if(i == 1 || i== 2) tabla_clientes.getColumnModel().getColumn(i).setPreferredWidth(250);
			else tabla_clientes.getColumnModel().getColumn(i).setPreferredWidth(30);
		}

	}
	
	private String [] getColumnas1(){
		
		String columna [] = new String []{"N orden", "Cliente", "IDC","Domicilio comercial"};
		
		return columna;
	}
	
	public JTable getTabla_cliente(){
		
		return tabla_clientes;
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
    
    public void setControladorCliente(ControladorCliente controladorCliente){
    	
    	this._controladorCliente = controladorCliente;
    }
 
    public void setControladorCombo(ControladorCombo _controladorCombo){
 	
    	this._controladorCombo = _controladorCombo;
    }
 
    public void setControladorPrestamo(ControladorPrestamo _controladorPrestamo){
 	
    	this._controladorPrestamo = _controladorPrestamo;
    }
    
    public void setControladorDespacho_diario(ControladorDespacho_diario cdp){
     	
    	this.cdp = cdp;
    }
   
    public void setControladorPedidos(ControladorPedidos controladorPedidos){
     	
    	this._controladorPedido = controladorPedidos;
    }
    
    public void limpiar(){
    	
    	if(tabla_clientes.getRowCount() > 0){
			
			DefaultTableModel modelo = (DefaultTableModel) tabla_clientes.getModel();
			
			int contFila = tabla_clientes.getRowCount();
			
			for(int i = 0; i < contFila; i++)
			
				modelo.removeRow(0);
		}
    	
    }

    volatile private boolean mouseDown = false;
    volatile private boolean isRunning = false;

    class MouseCustom implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			if (e.getButton() == MouseEvent.BUTTON1) {
	            mouseDown = true;
	            initThread();
	        }
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			 if (e.getButton() == MouseEvent.BUTTON1) {
		            mouseDown = false;
		        }
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    	
    }
    
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = true;
            initThread();
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = false;
        }
    }

    private synchronized boolean checkAndMark() {
        if (isRunning) return false;
        isRunning = true;
        return true;
    }
    private void initThread() {
        if (checkAndMark()) {
            new Thread() {
                public void run() {
                    do {
                    	
                    	moveUpwards();
                        //do something
                    } while (mouseDown);
                    isRunning = false;
                }
            }.start();
        }
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		
		if(e.getSource() == guardar){
			
			//_controladorMonto_t.altaMonto_t_usuario(ar, observaciones);
			if(tabla_clientes.getRowCount() > 0){
				
				ArrayList<DomicilioComercialVO> ar = new ArrayList<DomicilioComercialVO>();
				DomicilioComercialVO dcVO = new DomicilioComercialVO();
				
				int res = 0;
				
				for(int i = 0; i < tabla_clientes.getRowCount(); i ++){
					
					dcVO.setIdc(Integer.parseInt(tabla_clientes.getModel().getValueAt(i, 2).toString()));
					dcVO.setN_orden_planilla(Short.parseShort(tabla_clientes.getModel().getValueAt(i, 0).toString()));
					res = _controladorCliente.updateReordenar_planilla(dcVO);
				}
						
				if(res > 0 ) Mensajes.getMensaje_modificacionExitosa();
				else Mensajes.getMensaje_modificacion_sinExito();
			}
				
		}
		
		if(e.getSource()==arriba){
			// mouseDown = true;
	          //  initThread();
			moveUpwards();
		}
		
		if(e.getSource()==abajo){
			
			moveDownwards();
		}
	}
	
	public void moveUpwards()
	{
	    moveRowBy(-1);
	}

	public void moveDownwards()
	{
	    moveRowBy(1);
	}

	private void moveRowBy(int by)
	{
	    DefaultTableModel model = (DefaultTableModel) tabla_clientes.getModel();
	    int[] rows = tabla_clientes.getSelectedRows();
	    
	    if(rows.length==0) return;
	    
	    int destination = rows[0] + by;
	    int rowCount = model.getRowCount();

	    if (destination < 0 || destination >= rowCount)
	    {
	        return;
	    }

	    model.moveRow(rows[0], rows[rows.length - 1], destination);
	    tabla_clientes.setRowSelectionInterval(rows[0] + by, rows[rows.length - 1] + by);
	    calculo_orden();
	}
	
	private void calculo_orden(){
		
		for(int i = 0; i < tabla_clientes.getRowCount(); i++){
			
			tabla_clientes.getModel().setValueAt(i + 1, i , 0);
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
