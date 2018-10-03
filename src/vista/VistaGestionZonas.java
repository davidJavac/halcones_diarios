package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controlador.ControladorArticulo;
import controlador.ControladorCajaZona;
import controlador.ControladorCliente;
import controlador.ControladorCobrador;
import controlador.ControladorCombo;
import controlador.ControladorDespacho_diario;
import controlador.ControladorDomicilioComercial;
import controlador.ControladorDomicilioParticular;
import controlador.ControladorEmpleado;
import controlador.ControladorJefeCalle;
import controlador.ControladorLocalidad;
import controlador.ControladorMonto_trasladado;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import controlador.ControladorPremios;
import controlador.ControladorPrestamo;
import controlador.ControladorRefinanciacion_ex;
import controlador.ControladorRefinanciacion_in;
import controlador.ControladorUsuario;
import controlador.ControladorVendedor;
import controlador.ControladorVentas;
import controlador.ControladorZona;
import modelo.LogicaArticulo;
import modelo.LogicaCajaZona;
import modelo.LogicaCliente;
import modelo.LogicaCobrador;
import modelo.LogicaCombo;
import modelo.LogicaDespacho;
import modelo.LogicaDomicilioComercial;
import modelo.LogicaDomicilioParticular;
import modelo.LogicaEmpleado;
import modelo.LogicaJefe_calle;
import modelo.LogicaLocalidad;
import modelo.LogicaMonto_trasladado;
import modelo.LogicaPagoDiario;
import modelo.LogicaPedido;
import modelo.LogicaPremio;
import modelo.LogicaPrestamo;
import modelo.LogicaRefinanciacion_ex;
import modelo.LogicaRefinanciacion_in;
import modelo.LogicaUsuario;
import modelo.LogicaVendedor;
import modelo.LogicaVenta;
import modelo.LogicaZona;
import modelo.Mensajes;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.LocalidadVO;
import modelo_vo.ZonaVO;
import modelo_vo.Zona_localidadVO;
import vista.VistaBuscarArticulos.CustomJToolTip;

public class VistaGestionZonas extends JInternalFrame implements ActionListener{
	
	private JTable tabla = new JTable();
	private DefaultTableModel t_model;
	private JScrollPane scr = new JScrollPane();
	
	private static JComponent panel; 
	private JPanel pZona;
	private JPanel pComandos;
	
	private ControladorZona _controladorZona;
	private ControladorLocalidad controladorLocalidad;
	private ControladorEmpleado controladorEmpleado;
	private ControladorCobrador controladorCobrador;
	private BusquedaEntities _busqueda_entities;
	
	private JButton guardar = new JButton("Guardar");
	
	private JComboBox comboBox; 
	
    private boolean[] canEdit= new boolean[]{
            false, true
    };
    
	public VistaGestionZonas(Window ventana, String titulo){
			//super(ventana,titulo, JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
			super("Gestion de zonas", true, true, true, true);
		guardar.addActionListener(this);
		
		setLayout(new BorderLayout());
		
		t_model = new DefaultTableModel(null, getColumnas()){
			
			 public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit[columnIndex];
	            }
		};
		
		this.setSize(new Dimension(450,300));
		
		//tabla  = new JTable(new MyTableModel());
		
		tabla.setModel(t_model);
		
		tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tabla.setFont(new Font("Arial", Font.PLAIN, 16));
		tabla.setRowHeight(30);
		
		scr.setViewportView(tabla);
		
		pComandos = new JPanel();
		
		pComandos.add(guardar);
		
		this.add(pComandos, BorderLayout.PAGE_START);
		
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
		
		TableColumn cobrador = tabla.getColumnModel().getColumn(1);
		 /*DefaultTableCellRenderer renderer =
	                new DefaultTableCellRenderer();
	        renderer.setToolTipText("Click para ver cobradores");
	        cobrador.setCellRenderer(renderer);*/
		cobrador.setCellEditor(new DefaultCellEditor(comboBox));
		this.add(scr, BorderLayout.CENTER);
		
		centrar_columnas(tabla);

	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		_busqueda_entities.setPanel("vista_gestion_zona");	
		
		if(e.getSource() == guardar){
			
			int res =0;
			
			//_controladorZona.deleteAllZonas();
			
			for(int i = 0; i < tabla.getRowCount(); i++){
				
				ZonaVO zVO = new ZonaVO();
				
				zVO.setId_zona(Short.parseShort(tabla.getModel().getValueAt(i, 0).toString()));
				
				String [] cob = tabla.getModel().getValueAt(i, 1).toString().split("-");
				
				zVO.setId_cobrador(Short.parseShort(cob[0]));
				
				System.out.println("zona " + zVO.getId_zona() + "id cobrador " + zVO.getId_cobrador());
				
				res = _controladorZona.modificarZona_cobrador(zVO);
			}
			if(res > 0) Mensajes.getMensaje_altaExitosoGenerico();
			else Mensajes.getMensaje_altaErrorGenerico();
			
		}
		
		
	}
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setControladorZona(ControladorZona _controladorZona){
		
		this._controladorZona = _controladorZona;
	}
	
	public void setControladorLocalidad(ControladorLocalidad _controladorLocalidad){
		
		this.controladorLocalidad = _controladorLocalidad;
	}

	public void setControladorEmpleado(ControladorEmpleado _controladorEmpleado){
		
		this.controladorEmpleado = _controladorEmpleado;
	}
	
	public void setControladorCobrador(ControladorCobrador _controladorCobrador){
		
		this.controladorCobrador = _controladorCobrador;
	}
	
	private void centrar_columnas(JTable tabla){
		
		DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
		
		cent.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i < tabla.getColumnCount(); i ++)
			
			tabla.getColumnModel().getColumn(i).setCellRenderer(cent);
		
	}
	
	public void limpiar(){
		
		if(tabla.getRowCount() > 0){
			
			DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
			
			int contFila = tabla.getRowCount();
			
			for(int i = 0; i < contFila; i++)
			
				modelo.removeRow(0);
		}
	}	
	
	public void iniciar(){
		
		limpiar();
    	ArrayList<ZonaVO> ar = _controladorZona.buscarZonas();
    	if(ar!=null){
    			
    		int i = 0;

    		ArrayList<EmpleadoVO> ar_eVO = controladorEmpleado.
    				buscarEmpleados_porPuesto("Cobrador");
    		
    		if(ar_eVO!=null){
    			
    			for(EmpleadoVO eVO : ar_eVO){
    				
    				String cobradorS = eVO.getId_usuario() + "-" + eVO.getNombre() + " " + 
    						eVO.getApellido();
    				
    				comboBox.addItem(cobradorS);
    				//comboBox.setSelectedIndex(i);
    				i++;
    				
    			}
    			
    		}
    		
    		for(ZonaVO z : ar){
    			
    			Object datos [] = new Object[2];
    			
    			datos[0] = z.getId_zona();
    			
    			EmpleadoVO eVO = controladorEmpleado.
    					buscarEmpleado(Integer.toString(z.getId_cobrador()));
    					
    					if(eVO!=null){
    						
    						String cobradorS = eVO.getId_usuario() + "-" + eVO.getNombre() + " " + 
    								eVO.getApellido();
    						
    						//comboBox.setSelectedIndex(i);
    						datos[1] = cobradorS;
    					}
			
    			t_model.addRow(datos);
    			
    		}
    	}	
				
	}
	
	private String [] getColumnas(){
		
		String columna [] = new String []{"Zona","Cobrador"};
		
		return columna;
	}
	
	class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"Zona", "Cobrador"};
   
        ArrayList<ZonaVO> ar = new ArrayList<ZonaVO>();
        
        public MyTableModel(){
    		
    		
    		
    		
    	}
        

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return ar.size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {

        	limpiar();
        	ar = _controladorZona.buscarZonas();
        	if(ar!=null){
        	
        		switch(col) {
        			
        		case 0: return ar.get(row).getId_zona();
        		case 1:
        			EmpleadoVO eVO = controladorEmpleado.
        				buscarEmpleado(Integer.toString(ar.get(row).getId_cobrador()));
    				
    				if(eVO!=null){
    					
    					String cobradorS = eVO.getId_usuario() + "-" + eVO.getNombre() + " " + 
    							eVO.getApellido();
    					
    					comboBox.addItem(cobradorS);
    					//comboBox.setSelectedIndex(i);
    				}
    				return comboBox;
        		default : return null;	
        			/*int i = 0;
        			
        			for(ZonaVO z : ar){
        				
        				Object datos [] = new Object [2];
        				
        				datos[0] = z.getId_zona();
        				
        				
        				
        				
        				//t_model.addRow(datos);
        				
        				i++;
        			}*/
        		}
        		
        	}
        	return null;

        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
         
           /* data[row][col] = value;
            fireTableCellUpdated(row, col);*/

       
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
