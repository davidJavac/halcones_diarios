package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controlador.ControladorObservaciones;
import controlador.Principal;
import modelo.LogicaObservaciones;
import modelo_vo.Observaciones_clienteVO;

public class VistaObservacionesBaja extends JDialog implements ActionListener{

	private static VistaPrincipal vp;
	
	private JPanel pComandos = new JPanel();
	private JButton guardar = new JButton("Guardar");
	private JTextArea observaciones = new JTextArea();
	private ControladorObservaciones co = new ControladorObservaciones();
	private LogicaObservaciones lo = new LogicaObservaciones();
	private Observaciones_clienteVO ocVO = new Observaciones_clienteVO();
	
	public VistaObservacionesBaja(){
		super(vp, "Observaciones", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		
		guardar.addActionListener(this);
		this.setLayout(new BorderLayout());
		
		this.setSize(250, 200);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setLocation(dim.width/2-this.getSize().width/2, 
	        		dim.height/2-this.getSize().height/2);
	      
	    this.setFocusable(true);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    
		observaciones.setLineWrap(true);
		observaciones.setWrapStyleWord(true);
		
		pComandos.add(guardar);
		
		add(pComandos, BorderLayout.PAGE_START);
		add(observaciones, BorderLayout.CENTER);
		
		co.setLogicaObservaciones(lo);
		
	}
	
	public void setVistaPrincipal(VistaPrincipal vp){
		
		this.vp = vp;
	}
	
	public Observaciones_clienteVO getObservacionesClienteVO(){
		
		return ocVO;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==guardar){
			
			if(co.validarObservacionesBajaCliente(observaciones)){
				
				Date d = new Date();
				
				java.sql.Date hoy = new java.sql.Date(d.getTime());
				java.sql.Time hora = new java.sql.Time(d.getTime());
					
				ocVO.setObservacion(observaciones.getText());
				ocVO.setId_usuario(Principal._usuario.getId_usuario());
				ocVO.setFecha_registro(hoy);
				ocVO.setHora_registro(hora);
				
				this.dispose();
			}
			
		}
	}
	
}
