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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controlador.ControladorObservaciones;
import controlador.ControladorUsuario;
import controlador.Principal;
import modelo.LogicaUsuario;
import modelo.Mensajes;
import modelo_vo.ObservacionesVO;
import modelo_vo.Observaciones_clienteVO;
import modelo_vo.UsuariosVO;

public class VistaObservacionesCliente extends JDialog implements ActionListener{

	private JPanel pIntegra_general = new PanelGraduado(new Color(232, 248, 245), new Color(214, 234, 248));
	private JScrollPane scr;
	private JPanel pTabla = new JPanel();
	private JPanel pComandos = new JPanel();
	private JButton guardar = new JButton("Guardar");
	private JTextArea observaciones; 
	private JLabel obL = new JLabel();
	
	private static VistaPrincipal  vp;
	private ControladorUsuario _controladorUsuario;
	private ControladorObservaciones _controladorObservaciones;
	private VistaBuscarCliente vista_buscar_cliente;
	 static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     static double width = screenSize.getWidth();
	 double height = screenSize.getHeight();
	
	public VistaObservacionesCliente(){
		
		super(vp,
				"Observaciones", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		guardar.addActionListener(this);
		
		observaciones = new JTextArea(2,30);
    	observaciones.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	observaciones.setLineWrap(true);
    	observaciones.setWrapStyleWord(true);
		
		this.setLayout(new BorderLayout());
	       
		
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	       // this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	        this.setSize(dim.width*40/100, dim.height*60/100);
	        this.setFocusable(true);
	        this.setResizable(false);
	       // this.setLocationRelativeTo(null);
			
	       // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		     //setSize(dim.width*70/100, dim.height*70/100);
		     setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	        
	        pIntegra_general.setLayout(new BoxLayout(pIntegra_general, BoxLayout.Y_AXIS));
	        //pIntegra_general.setPreferredSize(new Dimension(450,1000));
	        pIntegra_general.setBorder(new EmptyBorder(20,20,20,20));
	        
			scr = new JScrollPane();
			
			scr.setViewportView(pIntegra_general);
			scr.setPreferredSize(new Dimension(450,600));
		
			
			obL.setText("Ingresar");
			
			pComandos.add(obL);
			pComandos.add(observaciones);
			pComandos.add(guardar);
			//pComandos.add(salir);
			
			this.add(scr, BorderLayout.CENTER);
			this.add(pComandos, BorderLayout.SOUTH);
	}
	
	public void iniciar(ArrayList<Observaciones_clienteVO> ar){
		
		if(ar!=null){
			
			for(Observaciones_clienteVO o: ar){
				
				SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
				String date =  new String(sf.format(o.getFecha_registro()));
		
				JPanel pIntegra = new JPanel();
				JPanel pDetalle = new JPanel();
				JPanel pObservacion = new JPanel();
				JPanel pComandos = new JPanel();
				
				JScrollPane scrIntegra = new JScrollPane();
				
				pIntegra.setOpaque(false);
				pDetalle.setOpaque(false);
				pObservacion.setOpaque(false);
				
				pIntegra.setLayout(new BorderLayout());
				//pIntegra.setPreferredSize(new Dimension(450,450));
				//scrIntegra.setViewportView(pIntegra);
				
				JTextArea observacionTA = new JTextArea(100,30);
				JScrollPane scr = new JScrollPane();
				scr.setViewportView(observacionTA);
				scr.setPreferredSize(new Dimension(300,70));
				JButton editar = new JButton("Editar");
				JButton guardar_edicion = new JButton("Guardar");
				String detalle = new String();
				JLabel lDetalle = new JLabel();
				guardar_edicion.setEnabled(false);
				
				editar.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						observacionTA.setEnabled(true);
						guardar_edicion.setEnabled(true);
						editar.setEnabled(false);
						
					}
					
					
				});
				guardar_edicion.addActionListener(new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						if( _controladorObservaciones.validarObservaciones(observacionTA)){
							
							Observaciones_clienteVO obVO = new Observaciones_clienteVO();
							
							obVO.setId(vista_buscar_cliente.getClienteVO().getDni());
							
							Date d = new java.util.Date();
							java.sql.Date fecha_registro = new java.sql.Date(d.getTime());
							java.sql.Time hora_registro = new java.sql.Time(d.getTime());
							
							obVO.setFecha_registro(fecha_registro);
							obVO.setHora_registro(hora_registro);
							obVO.setId_usuario(Principal._usuario.getId_usuario());
							obVO.setObservacion(observacionTA.getText());
							
							
							int res = _controladorObservaciones.
									modificarObservacionCliente(o.getId_observacion(), obVO);
							
							if(res > 0){
								
								Mensajes.getMensaje_altaExitosoGenerico();
								//observacionTA.setText("");
								pIntegra_general.removeAll();
								iniciar( _controladorObservaciones.buscarObservacionesCliente(obVO.getId()));
							}
							else Mensajes.getMensaje_altaErrorGenerico();
						}
					
						observacionTA.setEnabled(false);
						guardar_edicion.setEnabled(false);
						editar.setEnabled(true);
					}
					
					
				});
				
				observacionTA.setFont(new Font("Arial", Font.PLAIN, 14));
				observacionTA.setEnabled(false);
				observacionTA.setDisabledTextColor(new Color(113, 125, 126));
				
				UsuariosVO uVO = _controladorUsuario.buscarUsuario_porID(o.getId_usuario());
				String usuario = "";
				
				if(uVO!=null)
					usuario = uVO.getNombre(); 
				
				observacionTA.setText(o.getObservacion());
				observacionTA.setWrapStyleWord(true);
				observacionTA.setLineWrap(true);
				
				detalle = "<html>Dni cliente" + o.getDni() + "<br/>" +
							"N°Observacion " + o.getId_observacion() + "<br/>" + 
							"Usuario "  + usuario + "<br/>" + 
							"Hora registro " + o.getHora_registro();
				 
				lDetalle.setText(detalle);
				
				pDetalle.add(lDetalle);
				pObservacion.add(scr);
				pComandos.add(editar);
				pComandos.add(guardar_edicion);
				
				pIntegra.add(pComandos, BorderLayout.PAGE_END);
				pIntegra.add(pDetalle, BorderLayout.WEST);
				pIntegra.add(pObservacion, BorderLayout.CENTER);
			
				
				String fecha = "Fecha de registro " + date;
				
		    	Border borde = BorderFactory.createTitledBorder(null, fecha, 
		    	    	TitledBorder.CENTER, TitledBorder.TOP,
		    	    	new Font("Arial",Font.PLAIN,16), Color.BLACK);
		    	pIntegra.setBorder(borde);
				
		    	pIntegra.setMaximumSize
				 (new Dimension(Integer.MAX_VALUE, pIntegra.getMinimumSize().height));
		    	
				pIntegra_general.add(pIntegra);
				pIntegra_general.add(Box.createRigidArea(new Dimension(0,10)));
				pIntegra_general.updateUI();
				
			}
		}
			
	}
	
	
	private String [] getColumnas(){
		
		String columna [] = new String []{"N pedido", "N observacion", "Observación", "Usuario registro",
										"Fecha registro", "Hora registro"};
		
		return columna;
	}
    
    public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
    	
    	this.vista_buscar_cliente = vista_buscar_cliente;
    }
    
    public void setControladorUsuario(ControladorUsuario _controladorUsuario){
    	
    	this._controladorUsuario = _controladorUsuario;
    }
    
    public void setControladorObservaciones(ControladorObservaciones _controladorObservaciones){
    	
    	this._controladorObservaciones = _controladorObservaciones;
    }
 
    public void setVistaPrincipal(VistaPrincipal vp){
    	
    	this.vp = vp;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == guardar){
			
			//_controladorMonto_t.altaMonto_t_usuario(ar, observaciones);
		
			if(_controladorObservaciones.validarObservaciones(observaciones)){
				
				Observaciones_clienteVO obVO = new Observaciones_clienteVO();
				
				//obVO.setId(vista_buscar_cliente.getClienteVO().getDni());
				
				Date d = new java.util.Date();
				java.sql.Date fecha_registro = new java.sql.Date(d.getTime());
				java.sql.Time hora_registro = new java.sql.Time(d.getTime());
				
				obVO.setDni(vista_buscar_cliente.getClienteVO().getDni());
				obVO.setFecha_registro(fecha_registro);
				obVO.setHora_registro(hora_registro);
				obVO.setId_usuario(Principal._usuario.getId_usuario());
				obVO.setObservacion(observaciones.getText());
				
				int res = _controladorObservaciones.altaObservacionCliente(obVO);
				
				if(res > 0){
					
					Mensajes.getMensaje_altaExitosoGenerico();
					pIntegra_general.removeAll();
					iniciar( _controladorObservaciones.buscarObservacionesCliente
					(vista_buscar_cliente.getClienteVO().getDni()));
				}
				else Mensajes.getMensaje_altaErrorGenerico();
			}
			
				
	}
	}
}
