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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorArticulo;
import controlador.ControladorCambio_plan;
import controlador.ControladorCombo;
import controlador.ControladorDA;
import controlador.ControladorEmpleado;
import controlador.ControladorObservaciones;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorRetiros;
import controlador.ControladorVendedor;
import controlador.ControladorVendedor_ph;
import controlador.Principal;
import modelo.LogicaArticulo;
import modelo.LogicaCambio_plan;
import modelo.LogicaCombo;
import modelo.LogicaDA;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.ObservacionesVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.RetirosVO;
import modelo_vo.Vendedores_padre_hijoVO;

public class VistaDependenciaVendedor extends JInternalFrame implements ActionListener{

	private JTable tabla = new JTable();
	private JScrollPane scr;
	private JPanel pTabla = new JPanel();
	private JPanel pComandos = new JPanel();
	private JButton eliminar = new JButton("Eliminar");
	private JButton guardar = new JButton("Guardar");
	private JButton buscar_vendedor = new JButton("...");

	private JRadioButton vendedor_padre = new JRadioButton("Padre");
	private JRadioButton vendedor_hijo = new JRadioButton("Hijo");
	private ButtonGroup grupo_vendedor = new ButtonGroup();
	
	private JLabel vendedorL = new JLabel();
	
	private JTextField vendedorTF = new JTextField(5);
	
	private JPanel pNueva_dependencia = new JPanel();
	private  VistaGestionEmpleados vge;
	private ControladorVendedor controladorVendedor;
	private ControladorVendedor_ph controladorVendedor_ph;
	private ControladorEmpleado controladorEmpleado;
	
	private BusquedaEntities be;
	
	 private boolean[] canEdit_dependencia= new boolean[]{
	            false, false,false,false
	    };
	
	public VistaDependenciaVendedor(){
		//super(vge, "Dependencias vendedor", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		super("Dependencias vendedor", true, true, true);
		eliminar.addActionListener(this);
		guardar.addActionListener(this);
		buscar_vendedor.addActionListener(this);
		
		
		this.setLayout(new BorderLayout());
	   
	       
		 this.setSize(450, 350);
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	   
	        this.setFocusable(true);
	        this.setResizable(false);
	       // this.setLocationRelativeTo(null);
	        
	    	pTabla.setBackground(Color.white);
	        
			tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
	        
	        scr = new JScrollPane();//(pTabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			//JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    
	        
	        
	        scr.setViewportView(tabla);
	        scr.setPreferredSize(new Dimension(400,150));
	       // pTabla.add(scr);
	        pTabla.updateUI();
	        
	        vendedorTF.addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					vendedorTF.setBackground(new Color(183, 242, 113));
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					
					EmpleadoVO empleadoVO = controladorEmpleado.
							buscarEmpleado(vendedorTF.getText());
					
					if(empleadoVO !=null){
						
						if(empleadoVO.getPuesto().equals("vendedor")){
							
							vendedorTF.setText(Integer.toString(empleadoVO.getId_usuario()));
							vendedorL.setText(empleadoVO.getNombre() + " " + empleadoVO.getApellido());
							
						}
						else{
							
							vendedorL.setText("");
							vendedorTF.setText("");
							
						}
						
					}
					else{
						
						vendedorL.setText("");
						vendedorTF.setText("");
						
					}
					
					vendedorTF.setBackground(new Color(255, 255, 255));
					
				}
	  			
	  			
	  		});
	        
	        pTabla.add(scr);
	        
	        pComandos.add(eliminar);
	        //pComandos.setBackground(Color.white);
	        
	        this.add(pComandos, BorderLayout.NORTH);
			this.add(pTabla, BorderLayout.CENTER);
			
			grupo_vendedor.add(vendedor_padre);
		    grupo_vendedor.add(vendedor_hijo);
		    pNueva_dependencia.setLayout(new BoxLayout(pNueva_dependencia, BoxLayout.Y_AXIS));
		    
		    JPanel pDep_norte = new JPanel();
		    JPanel pDep_sur = new JPanel();
		    pDep_norte.add(vendedor_padre);
		    pDep_norte.add(vendedor_hijo);
		    JLabel en_relacion = new JLabel();
		    en_relacion.setText("en relación a...");
		    pDep_norte.add(en_relacion);
		    
			GridLayout gl = new GridLayout(0,2);
			JPanel pBuscar_dependencia = new JPanel();
			
			pBuscar_dependencia.setLayout(gl);
			
			pBuscar_dependencia.add(vendedorTF);
			pBuscar_dependencia.add(buscar_vendedor);
			pDep_norte.add(pBuscar_dependencia);
			pDep_norte.add(vendedorL);
			
			pDep_sur.add(guardar);
			
			pNueva_dependencia.add(pDep_norte);
			pNueva_dependencia.add(pDep_sur);
			
			vendedor_padre.setSelected(true);
			
			Border borde0 = BorderFactory.createTitledBorder(null, "Nueva dependencia", 
	    			TitledBorder.CENTER, TitledBorder.TOP,
	    			new Font("Arial",Font.PLAIN,16), Color.BLACK);
	    			//panel.setBorder(borde0);
	    			
			pNueva_dependencia.setBorder(BorderFactory.createCompoundBorder(
	    					new EmptyBorder(10, 10, 10, 10), borde0));
			
			this.add(pNueva_dependencia, BorderLayout.PAGE_END);
			
	}
	
	public void iniciar(EmpleadoVO eVO){
		
		/*this. addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
               
            	//_vista_buscarPedido_cliente.setEnabled(true);
                e.getWindow().dispose();
            }
           
        });*/
		
		limpiar();
		
		int cantidad = 0;
		 DefaultTableModel t_model = new DefaultTableModel(null, getColumnas()){
        	
       	 public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit_dependencia[columnIndex];
	            }
       };;
		
		if(eVO!=null){
	
			
			ArrayList<Vendedores_padre_hijoVO> arVendedor_padres = controladorVendedor_ph.
					buscarVendedoresPadres_porIdHijo(eVO.getId_usuario());
			
			ArrayList<Vendedores_padre_hijoVO> arVendedor_hijos = controladorVendedor_ph.
					buscarVendedoresHijos_porIdPadre(eVO.getId_usuario());
			
			if(arVendedor_hijos!=null){
				
				for(Vendedores_padre_hijoVO vphVO : arVendedor_hijos){
						
					Object [] datos = new Object[4];
						
					EmpleadoVO vpadre_VO = controladorEmpleado.
							buscarEmpleado(Integer.toString(vphVO.getId_padre()));
					EmpleadoVO vhijo_VO = controladorEmpleado.
							buscarEmpleado(Integer.toString(vphVO.getId_hijo()));
			
					datos [0] = vphVO.getId_padre();
					datos [1] = vphVO.getId_hijo();
					datos [2] = vpadre_VO.getNombre() + " " + vpadre_VO.getApellido() + 
							"(" + vpadre_VO.getId_usuario() + ")";
					datos [3] = vhijo_VO.getNombre() + " " + vhijo_VO.getApellido() + 
							"(" + vhijo_VO.getId_usuario() + ")";
					
					t_model.addRow(datos);
					
				}
				
				
			}
			if(arVendedor_padres!=null){
				
				for(Vendedores_padre_hijoVO vphVO : arVendedor_padres){
					
					Object [] datos = new Object[4];
					
					EmpleadoVO vpadre_VO = controladorEmpleado.
							buscarEmpleado(Integer.toString(vphVO.getId_padre()));
					EmpleadoVO vhijo_VO = controladorEmpleado.
							buscarEmpleado(Integer.toString(vphVO.getId_hijo()));
					
					datos [0] = vphVO.getId_padre();
					datos [1] = vphVO.getId_hijo();
					datos [2] = vpadre_VO.getNombre() + " " + vpadre_VO.getApellido() + 
							"(" + vpadre_VO.getId_usuario() + ")";
					datos [3] = vhijo_VO.getNombre() + " " + vhijo_VO.getApellido() + 
							"(" + vhijo_VO.getId_usuario() + ")";
					
					t_model.addRow(datos);
					
				}
				
			}
			
		}
		
		tabla.setModel(t_model);
		
		tabla.removeColumn(tabla.getColumnModel().getColumn(0));
		tabla.removeColumn(tabla.getColumnModel().getColumn(0));
		
		tabla.getPreferredScrollableViewportSize().setSize(900,
				tabla.getRowHeight() * cantidad);
	
		//ancho_columnas();
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla.getColumnCount(); i ++)
			
			tabla.getColumnModel().getColumn(i).setCellRenderer(cent);
		

		
	}
	
	private void ancho_columnas(){
		
		for(int i = 0; i < tabla.getColumnCount(); i++){
			
			if(i == 2) tabla.getColumnModel().getColumn(i).setPreferredWidth(250);
			else tabla.getColumnModel().getColumn(i).setPreferredWidth(50);
		}
	}
	
	private String [] getColumnas(){
		
		String columna [] = new String []{"id_padre", "id_hijo", "Vendedor padre", "Vendedor hijo"};
		
		return columna;
	}
	
    public void setVistaGestionEmpleados(VistaGestionEmpleados vge){
    	
    	this.vge = vge;
    }
   
    public void setControladorEmpleado(ControladorEmpleado _controladorEmpleado){
    	
    	this.controladorEmpleado = _controladorEmpleado;
    }
    public void setControladorVendedor_ph(ControladorVendedor_ph _controladorVendedor_ph){
    	
    	this.controladorVendedor_ph = _controladorVendedor_ph;
    }
    public void setControladorVendedor(ControladorVendedor _controladorVendedor){
    	
    	this.controladorVendedor = _controladorVendedor;
    }
  
    public void setBusquedaEntities(BusquedaEntities be){
    	
    	this.be = be;
    }
  
    public JTextField getVendedorTF(){
    	
    	return vendedorTF;
    }
    public JLabel getVendedorL(){
    	
    	return vendedorL;
    }
    
    public void limpiar(){
    	
    	if(tabla.getRowCount() > 0){
			
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			
			int contFila = tabla.getRowCount();
			
			for(int i = 0; i < contFila; i++)
			
				modelo.removeRow(0);
		}
    	vendedorL.setText("");
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == eliminar){
			
			int res = 0;
			
			int [] array_int = tabla.getSelectedRows();
			
			for(int i = 0; i < tabla.getRowCount(); i++){
				
				Vendedores_padre_hijoVO vphVO = new Vendedores_padre_hijoVO();
				
				for(int fila : array_int){
					
					if(i== fila){
						
						vphVO.setId_padre(Integer.parseInt(tabla.getModel().getValueAt(fila, 0).toString()));
						vphVO.setId_hijo(Integer.parseInt(tabla.getModel().getValueAt(fila, 1).toString()));
						
						res = controladorVendedor_ph.deleteVendedor_ph(vphVO);
					}
				}
				
				
			}
			
			if(res > 0){
				
				Mensajes.getMensaje_bajaExitosa_generico();
				iniciar(vge.getEmpleadoVO());
				pTabla.updateUI();

			}
			else{
				
				Mensajes.getMensaje_bajaError_generico();
			}
				
		}
		if(e.getSource() == buscar_vendedor){
			
			controladorEmpleado.buscarEmpleadosAll_porPuesto("vendedor");
			controladorEmpleado.mostrarBusquedaEntities("Buscar vendedor");
			
		}
		if(e.getSource() == guardar){
			
			int res = 0;
			
			if(vge.getEmpleadoVO()!=null){
				
				if(controladorVendedor_ph.nuevaDependenciaUsuario(vendedorTF)){
					
					Vendedores_padre_hijoVO vphVO = new Vendedores_padre_hijoVO();
					
					
					if(vendedor_padre.isSelected()){
						
						vphVO.setId_padre(vge.getEmpleadoVO().getId_usuario());
						vphVO.setId_hijo(Integer.parseInt(vendedorTF.getText()));
						
					}
					
					if(vendedor_hijo.isSelected()){
						
						vphVO.setId_hijo(vge.getEmpleadoVO().getId_usuario());
						vphVO.setId_padre(Integer.parseInt(vendedorTF.getText()));
						
					}
					
					res = controladorVendedor_ph.nuevaDependencia(vphVO);
					
					if(res > 0){
						
						Mensajes.getMensaje_altaExitosoGenerico();
						
						iniciar(vge.getEmpleadoVO());
						pTabla.updateUI();
					}
					else Mensajes.getMensaje_altaErrorGenerico();
					
				}
				else Mensajes.getMensaje_altaErrorGenerico();
				
				
			}
			
			
		}
	}
	
}
