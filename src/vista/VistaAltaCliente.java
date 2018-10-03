package vista;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ControladorCliente;
import controlador.ControladorDomicilioComercial;
import controlador.ControladorDomicilioParticular;
import controlador.ControladorEmpleado;
import controlador.ControladorLocalidad;
import controlador.ControladorVendedor;
import controlador.ControladorZona;
import controlador.Principal;
import modelo.LogicaCliente;
import modelo.LogicaDomicilioComercial;
import modelo.LogicaDomicilioParticular;
import modelo.Mensajes;
import modelo_vo.ClienteVO;
import modelo_vo.ComercioVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.VerazVO;
import modelo_vo.ZonaVO;

import org.jdatepicker.util.*;
import org.jdatepicker.*;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class VistaAltaCliente extends JPanel implements ActionListener{

	private JTextField dniTF;
	private JTextField nombreTF;
	private JTextField apellidoTF;
	private JTextField nacionalidadTF;
	private JTextField dni_conyugueTF;
	private JTextField nombre_conyugueTF;
	private JTextField apellido_conyugueTF;
	private JTextField telefono_conyugueTF;
	private JTextField email_conyugueTF;
	private JRadioButton soltero;
	private JRadioButton casado;
	private ButtonGroup grupoEstadoCivil;
	private JDatePickerImpl fecha_nacimiento;
	private JTextField telefono_movilTF;
	private JTextField telefono_lineaTF;
	private JTextField emailTF;
	private JTextField id_vendedorTF;
	private JTextField id_zonaTF;
	
	private JTextField domicilio_partTF;
	private JTextField entre_calle1TF;
	private JTextField entre_calle2TF;
	private JRadioButton siDpto;
	private JRadioButton noDpto;
	private ButtonGroup grupoDpto;
	private JTextField pisoTF;
	private JTextField barrioTF;
	private JTextField cpTF;
	private JTextField id_localidadTF;
	private JDatePickerImpl antiguedadTF;
	private JRadioButton propio;
	private JRadioButton alquila;
	private ButtonGroup grupoPropioAlquila;
	
	private JTextField domicilio_comTF;
	private JTextField entre_calle1ComercialTF;
	private JTextField entre_calle2ComercialTF;
	private JTextField barrioComercialTF;
	private JTextField cpComercialTF;
	private JTextField id_localidadComercialTF;
	private JDatePickerImpl antiguedadComercialTF;
	private JTextField comercioTF;
	private JRadioButton propioComercial;
	private JRadioButton alquilaComercial;
	private ButtonGroup grupoPropioAlquilaComercial;
	private Horario spinnerDesde;
	private Horario spinnerHasta;
	private  JSpinner.DateEditor deD;
	private JSpinner.DateEditor deH;
	ButtonsInTextField bitf;
	private ArrayList<JTextField> stringTF;
	private ArrayList<JTextField> intTF;
	private ArrayList<JTextField> stringTFDomPart;
	private ArrayList<JTextField> intTFDomPart;
	private ArrayList<JTextField> stringTFDomCom;
	private ArrayList<JTextField> intTFDomCom;
	private ArrayList<JTextField> ar_conyugue = new ArrayList<JTextField>();
	private ArrayList<JDatePickerImpl> array_datepicker;
	
	private JButton cargar = new JButton("Guardar");
	private JButton buscar_zona = new JButton("...");
	private JButton buscar_localidadPart = new JButton("...");
	private JButton buscar_localidadCom = new JButton("...");
	private JButton buscar_vendedor = new JButton("...");
	private JButton buscar_comercio = new JButton("...");
	private ControladorCliente _controladorCliente;
	private ControladorDomicilioParticular _controladorDomPart;
	private ControladorDomicilioComercial _controladorDomCom;
	private ControladorVendedor _controladorVendedor;
	private ControladorZona _controladorZona;
	private ControladorLocalidad _controladorLocalidad;
	private ControladorEmpleado controladorEmpleado;
	private BusquedaEntities _busqueda_entities;
	private Pestaña _pestaña;
	
	private VistaPrincipal _vista_principal;
	
	private Cartas panel_cartas;
	
	private JPanel pBarra;
	private JPanel pDatospersonales;
	private JPanel pDomPart;
	private JPanel pDomCom;
	private JPanel pIntegra;
	private JPanel pNorte;
	
	private JPanel pEstadocivil;
	private JPanel pDpto;
	private JPanel pAlquilaPart;
	private JPanel pAlquilaCom;
	private JPanel pHorario_atencion;
	private JPanel pId_zona;
	private JPanel pId_localidadPart;
	private JPanel pId_localidadCom;
	private JPanel pId_vendedor;
	private JPanel pId_comercio;
	
	private JLabel titulo_barra;
	
	private JLabel lLocalidadPart, lLocalidadCom, lCobradorZona, lVendedor, lComercio;
	
	private static int contador_panel;
	
	public static boolean open = false;
	
	 static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     static double width = screenSize.getWidth();
     double height = screenSize.getHeight();
	
	public VistaAltaCliente(){
		
		setLayout(new BorderLayout());
		
		pBarra = new JPanel();
		pDatospersonales = new JPanel();
		pDomPart = new JPanel();
		pDomCom = new JPanel();
		pBarra.setLayout(new BorderLayout());
		pBarra.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		pBarra.setBackground(new Color(234, 242, 248));
		pDatospersonales.setLayout(new BorderLayout());
		
		GridLayout gl = new GridLayout(0,2);
  		
  		id_zonaTF = new JTextField(5);
  		id_zonaTF.setFont(new Font("Arial", Font.PLAIN, 16));
  		lCobradorZona = new JLabel();
  		
  		id_zonaTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				id_zonaTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(_controladorZona.buscarZonaUsuario(id_zonaTF.getText())!=null){
					
					ZonaVO zVO = _controladorZona.buscarZonaUsuario(id_zonaTF.getText());
    				
    				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.
    						toString(zVO.getId_cobrador()));
    				
    				lCobradorZona.setText(eVO.getNombre() + " " + eVO.getApellido());
				}
					
					
				else{
					lCobradorZona.setText("");
					id_zonaTF.setText("");
				}
					
				
				id_zonaTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
  		pId_zona = new JPanel();
		pId_localidadPart = new JPanel();
		pId_localidadCom = new JPanel();
		pId_vendedor = new JPanel();
  		//buscar_zona.setPreferredSize(new Dimension(2, 2));
  		pId_zona.setLayout(gl);
  		pId_zona.add(id_zonaTF);
  		pId_zona.add(buscar_zona);
  		
  		lLocalidadPart = new JLabel();
  		lLocalidadCom = new JLabel();
  		
  		id_localidadTF = new JTextField(5);
  		id_localidadTF.setFont(new Font("Arial", Font.PLAIN, 16));
  		
  		id_localidadTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				id_localidadTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(_controladorLocalidad.buscarLocalidadUsuario(id_localidadTF.getText())!=null)
					
					lLocalidadPart.setText(_controladorLocalidad.buscarLocalidadUsuario(id_localidadTF.getText()));
				else{
					
					lLocalidadPart.setText("");
					id_localidadTF.setText("");
				}
					
				
				id_localidadTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
  		
  		//buscar_zona.setPreferredSize(new Dimension(2, 2));
  		pId_localidadPart.setLayout(gl);
  		pId_localidadPart.add(id_localidadTF);
  		pId_localidadPart.add(buscar_localidadPart);
  		//pId_localidadPart.add(lLocalidadPart);
  		
  		id_localidadComercialTF = new JTextField(5);
  		id_localidadComercialTF.setFont(new Font("Arial", Font.PLAIN, 16));
  		
  		id_localidadComercialTF.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				id_localidadComercialTF.setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(_controladorLocalidad.buscarLocalidadUsuario(id_localidadComercialTF.getText())!=null)
					
					lLocalidadCom.setText(_controladorLocalidad.buscarLocalidadUsuario
							(id_localidadComercialTF.getText()));
				else{
					
					lLocalidadCom.setText("");
					id_localidadComercialTF.setText("");
				}
					
				
				id_localidadComercialTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
  		//buscar_zona.setPreferredSize(new Dimension(2, 2));
  		
  		//pId_localidadCom.add(lLocalidadCom);
  		pId_localidadCom.setLayout(gl);
  		pId_localidadCom.add(id_localidadComercialTF);
  		pId_localidadCom.add(buscar_localidadCom);
  		
  		lVendedor = new JLabel();
  		
  		id_vendedorTF = new JTextField(5);
  		id_vendedorTF .setFont(new Font("Arial", Font.PLAIN, 16));
  		
  		id_vendedorTF .addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				id_vendedorTF .setBackground(new Color(183, 242, 113));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(_controladorVendedor.buscarVendedorUsuario(id_vendedorTF.getText())!=null)
					
					lVendedor.setText(_controladorVendedor.buscarVendedorUsuario
							(id_vendedorTF.getText()));
				else{
					
					lVendedor.setText("");
					id_vendedorTF.setText("");
				}
					
				
				id_vendedorTF.setBackground(new Color(255, 255, 255));
			}
  			
  			
  		});
  		comercioTF = new JTextField(5);
  		comercioTF.setFont(new Font("Arial", Font.PLAIN, 16));
  		comercioTF .addFocusListener(new FocusListener(){
  			
  			@Override
  			public void focusGained(FocusEvent e) {
  				// TODO Auto-generated method stub
  				comercioTF .setBackground(new Color(183, 242, 113));
  			}
  			
  			@Override
  			public void focusLost(FocusEvent e) {
  				// TODO Auto-generated method stub
  				if(_controladorDomCom.buscarComercio(comercioTF.getText())!=null){
  					
  					ComercioVO cVO = _controladorDomCom.buscarComercio(comercioTF.getText());
  					lComercio.setText(cVO.getDescripcion());
  					
  				}
  					
  				else{
  					
  					lComercio.setText("");
  					comercioTF.setText("");
  				}
  				
  				
  				comercioTF.setBackground(new Color(255, 255, 255));
  			}
  			
  			
  		});
  		//buscar_zona.setPreferredSize(new Dimension(2, 2));
  		pId_vendedor.setLayout(gl);
  		pId_vendedor.add(id_vendedorTF);
  		pId_vendedor.add(buscar_vendedor);

  		pId_comercio = new JPanel();
  		
  		pId_comercio.setLayout(gl);
  		lComercio = new JLabel();
  		pId_comercio.add(comercioTF);
  		pId_comercio.add(buscar_comercio);
  		
		pIntegra = new JPanel();
		pNorte = new JPanel();
		
		pIntegra.setLayout(new BoxLayout(pIntegra, 	BoxLayout.Y_AXIS));
		
		//_controladorZona
		
		cargar.addActionListener(this);
		buscar_zona.addActionListener(this);
		buscar_localidadPart.addActionListener(this);
		buscar_localidadCom.addActionListener(this);
		buscar_vendedor.addActionListener(this);
		buscar_comercio.addActionListener(this);
		
		UtilDateModel modelFN = new UtilDateModel();
		UtilDateModel modelAP = new UtilDateModel();
		UtilDateModel modelAC = new UtilDateModel();
        //model.setDate(20,04,2014);
  		Properties p = new Properties();
  		p.put("text.today", "Today");
  		p.put("text.month", "Month");
  		p.put("text.year", "Year");
        JDatePanelImpl datePanelFN = new JDatePanelImpl(modelFN, p);
        JDatePanelImpl datePanelAP = new JDatePanelImpl(modelAP, p);
        JDatePanelImpl datePanelAC = new JDatePanelImpl(modelAC, p);
		
        Date date = new Date();
        SpinnerDateModel smD = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        SpinnerDateModel smH = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        
        spinnerDesde = new Horario(smD, "Desde");
        
        spinnerHasta = new Horario(smH, "Hasta");
        
        
		 JComponent[] components = {
              
                 dniTF = new JTextField(15),
         		nombreTF = new JTextField(15),
         		apellidoTF = new JTextField(15),
         		nacionalidadTF = new JTextField(15),
         		pEstadocivil = new JPanel(),
         		dni_conyugueTF = new JTextField(15),
         		nombre_conyugueTF = new JTextField(15),
         		apellido_conyugueTF = new JTextField(15),
         		telefono_conyugueTF = new JTextField(15),
         		email_conyugueTF = new JTextField(15),
         		fecha_nacimiento = new JDatePickerImpl(datePanelFN, new DateLabelFormatter()),
         		  		
         		telefono_movilTF = new JTextField(15),
         		telefono_lineaTF = new JTextField(15),
         		emailTF = new JTextField(15),
         		pId_vendedor,
         		lVendedor,
         		
         		
         		
             };
		
		 String labels [] = {
				 
				"DNI", "Nombre", "Apellido", "Nacionalidad","Estado civil",
				"DNI conyugue", "Nombre coyugue", "Apellido conyugue","Teléfono conyugue",
				"Email conyugue",
				"Fecha de nacimiento",
				 "Telefono 1", "Teléfono 2",
				"Email", "Vendedor",""
		 };
		 
		 JComponent[] dom_part_components = {
	              
				 domicilio_partTF = new JTextField(15),
			  		entre_calle1TF = new JTextField(15),
			  		entre_calle2TF = new JTextField(15),
			  		pDpto = new JPanel(),
			  		pisoTF = new JTextField(15),
			  		barrioTF = new JTextField(15),
			  		cpTF = new JTextField(15),
			  		pId_localidadPart,
			  		lLocalidadPart,
			  		antiguedadTF = new JDatePickerImpl(datePanelAP, new DateLabelFormatter()),
			  		pAlquilaPart = new JPanel()
         		
         		
             };
		
		 String dom_part_labels [] = {
				 
				"Domicilio", "Entre calle1", "Entre calle2",
				"Dpto",
				"Piso", "Barrio", "CP",
				 "Localidad","", "Antiguedad", "Propio"	
		 };
		 
		 bitf = new ButtonsInTextField(2);
		 
		 JComponent[] dom_com_components = {
	              
				 pId_zona,
				 lCobradorZona,
				 domicilio_comTF = new JTextField(15),
			  		entre_calle1ComercialTF = new JTextField(15),
			  		entre_calle2ComercialTF = new JTextField(15),
			  		barrioComercialTF = new JTextField(15),
			  		cpComercialTF = new JTextField(15),
			  		pId_localidadCom,
			  		lLocalidadCom,
			  		antiguedadComercialTF =  new JDatePickerImpl(datePanelAC, new DateLabelFormatter()),
			  		pId_comercio,
			  		lComercio,
			  		pAlquilaCom = new JPanel(),
			  		pHorario_atencion = new JPanel(),
			  		//bitf.getGui()
         		  		
             };
		
		 String dom_com_labels [] = {
				 
				  "Zona", "Cobrador", "Domicilio", "Entre calle1", "Entre calle2",
				 "Barrio", "CP",
				 "Localidad","", "Antiguedad", "Comercio","", "Propio", "Hr atención"	
		 };
		 
		 JFormattedTextField fn = fecha_nacimiento.getJFormattedTextField();
		 fn.setFont(new Font("Arial", Font.PLAIN, 18));
		 JFormattedTextField antPart = antiguedadTF.getJFormattedTextField();
		 antPart.setFont(new Font("Arial", Font.PLAIN, 18));
		 JFormattedTextField antCom = antiguedadComercialTF.getJFormattedTextField();
		 antCom.setFont(new Font("Arial", Font.PLAIN, 18));
		 
		 
	     siDpto = new JRadioButton("Si");
	     noDpto = new JRadioButton("No");
	     siDpto.setFont(new Font("Arial", Font.PLAIN, 16));
	     noDpto.setFont(new Font("Arial", Font.PLAIN, 16));
	     noDpto.setSelected(true);
	     grupoDpto = new ButtonGroup();
	     grupoDpto.add(noDpto);
	     grupoDpto.add(siDpto);
	     pDpto.add(noDpto);
	     pDpto.add(siDpto);
	     
  		 propio = new JRadioButton("Si");
  		 alquila = new JRadioButton("No");
  		 propio.setFont(new Font("Arial", Font.PLAIN, 16));
	     alquila.setFont(new Font("Arial", Font.PLAIN, 16));
	     propio.setSelected(true);
  		 grupoPropioAlquila = new ButtonGroup();
  		 grupoPropioAlquila.add(propio);
  		 grupoPropioAlquila.add(alquila);
  		 pAlquilaPart.add(propio);
  		 pAlquilaPart.add(alquila);
  		
  		
  		propioComercial = new JRadioButton("Si");
  		alquilaComercial = new JRadioButton("No");
  		propioComercial.setFont(new Font("Arial", Font.PLAIN, 16));
	    alquilaComercial.setFont(new Font("Arial", Font.PLAIN, 16));
	    propioComercial.setSelected(true);
		grupoPropioAlquilaComercial = new ButtonGroup();
		grupoPropioAlquilaComercial.add(propioComercial);
		grupoPropioAlquilaComercial.add(alquilaComercial);
		pAlquilaCom.add(propioComercial);
		pAlquilaCom.add(alquilaComercial);

  		soltero = new JRadioButton("Soltero");
        casado = new JRadioButton("Casado");
        soltero.setFont(new Font("Arial", Font.PLAIN, 16));
        soltero.setSelected(true);
        casado.setFont(new Font("Arial", Font.PLAIN, 16));
        grupoEstadoCivil = new ButtonGroup();
  		grupoEstadoCivil.add(soltero);
  		grupoEstadoCivil.add(casado);
  		
  		dni_conyugueTF.setEnabled(false);
  		nombre_conyugueTF.setEnabled(false);
  		apellido_conyugueTF.setEnabled(false);
  		telefono_conyugueTF.setEnabled(false);
  		email_conyugueTF.setEnabled(false);
  		
  		soltero.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(soltero.isSelected()){
					
					dni_conyugueTF.setEnabled(false);
					nombre_conyugueTF.setEnabled(false);
					apellido_conyugueTF.setEnabled(false);
					telefono_conyugueTF.setEnabled(false);
					email_conyugueTF.setEnabled(false);
				}
			}
  			
  		});
  		casado.addActionListener(new ActionListener(){
  			
  			@Override
  			public void actionPerformed(ActionEvent e) {
  				// TODO Auto-generated method stub
  				
  				if(casado.isSelected()){
  					
  					dni_conyugueTF.setEnabled(true);
  					nombre_conyugueTF.setEnabled(true);
  					apellido_conyugueTF.setEnabled(true);
  					telefono_conyugueTF.setEnabled(true);
  					email_conyugueTF.setEnabled(true);
  				}
  			}
  			
  		});
  		
  		pEstadocivil.add(soltero);
  		pEstadocivil.add(casado);
  		
  		pHorario_atencion.add(spinnerDesde);
  		pHorario_atencion.add(spinnerHasta);
  		
  		
  		
  		pisoTF.setEditable(false);
  		siDpto.addActionListener(this);
		noDpto.addActionListener(this);
        
		stringTF = new ArrayList<JTextField>();
		
		stringTF.add(nombreTF);
		stringTF.add(apellidoTF);
		stringTF.add(nacionalidadTF);
		//stringTF.add(nombre_conyugueTF);
		//stringTF.add(apellido_conyugueTF);
		stringTF.add(telefono_movilTF);
		stringTF.add(telefono_lineaTF);
		stringTF.add(emailTF);
		stringTF.add(fecha_nacimiento.getJFormattedTextField());
		stringTF.add(antiguedadTF.getJFormattedTextField());
		stringTF.add(antiguedadComercialTF.getJFormattedTextField());
		
		intTF = new ArrayList<JTextField>();
		
		intTF.add(dniTF);
		//intTF.add(dni_conyugueTF);
		intTF.add(id_zonaTF);
		intTF.add(id_vendedorTF);
		
		stringTFDomPart = new ArrayList<JTextField>();
		
		stringTFDomPart.add(domicilio_partTF);
		stringTFDomPart.add(entre_calle1TF);
		stringTFDomPart.add(entre_calle2TF);
		stringTFDomPart.add(barrioTF);
		
		intTFDomPart = new ArrayList<JTextField>();
		
		//intTFDomPart.add(pisoTF);
		intTFDomPart.add(id_localidadTF);
		intTFDomPart.add(cpTF);
		
		stringTFDomCom = new ArrayList<JTextField>();
		
		stringTFDomCom.add(domicilio_comTF);
		stringTFDomCom.add(entre_calle1ComercialTF);
		stringTFDomCom.add(entre_calle2ComercialTF);
		stringTFDomCom.add(barrioComercialTF);
		//stringTFDomCom.add(comercioTF);
		
		intTFDomCom = new ArrayList<JTextField>();
		
		intTFDomCom.add(id_localidadComercialTF);
		intTFDomCom.add(cpComercialTF);
		intTFDomCom.add(comercioTF);
		
		ar_conyugue.add(dni_conyugueTF);
		ar_conyugue.add(nombre_conyugueTF);
		ar_conyugue.add(apellido_conyugueTF);
		ar_conyugue.add(telefono_conyugueTF);
		ar_conyugue.add(email_conyugueTF);
		
		
		array_datepicker = new ArrayList<JDatePickerImpl>();
		
		array_datepicker.add(fecha_nacimiento);
		array_datepicker.add(antiguedadTF);
		array_datepicker.add(antiguedadComercialTF);
		
		titulo_barra = new JLabel("Alta cliente");
		
		Font fuenteB = new Font("Verdana", Font.PLAIN, 20);
		
		titulo_barra.setFont(fuenteB);
		
		pBarra.add(titulo_barra);
		
		pNorte.add(cargar);
		//add(pDatospersonales, BorderLayout.WEST);
		
		contador_panel = 0;
		
		JPanel pDatos = new JPanel();
		GridLayout gl_datos = new GridLayout(1,3);
		pDatos.setLayout(gl_datos);
	    JComponent labelsAndFields = getTwoColumnLayout(labels,components);
		
		JComponent labelsAndFields_dom_part = getTwoColumnLayout(dom_part_labels, dom_part_components);
		
		JComponent labelsAndFields_dom_com = 
				 getTwoColumnLayout(dom_com_labels, dom_com_components);
		 
		 pDatos.add(labelsAndFields);
		 pDatos.add(labelsAndFields_dom_part);
		 pDatos.add(labelsAndFields_dom_com);
		 
		 add(pDatos, BorderLayout.CENTER);
		 
		 pIntegra.add(pBarra);
		 pIntegra.add(pNorte);
		 
		 add(pIntegra, BorderLayout.NORTH);
	
		 //this.setPreferredSize(new Dimension(dim.width*70/100,dim.height*80/100));
		 
	}

	
	public static JComponent getTwoColumnLayout(
            JLabel[] labels,
            JComponent[] fields,
            boolean addMnemonics) {
        if (labels.length != fields.length) {
            String s = labels.length + " labels supplied for "
                    + fields.length + " fields!";
            throw new IllegalArgumentException(s);
        }
        JComponent panel = new JPanel();
        
        Font fuente_titulo = new Font("Arial", Font.BOLD, 16);
       
  
        switch(contador_panel){
        
        	case 0: Border borde0 = BorderFactory.createTitledBorder(null, "Datos personales", 
        			TitledBorder.CENTER, TitledBorder.TOP,
        			new Font("Arial",Font.BOLD,(int) width/90), Color.BLACK);
        			panel.setBorder(borde0);
        			
        	break;		
        			
        	case 1: Border borde1 = BorderFactory.createTitledBorder(null, "Domicilio particular", 
        			TitledBorder.CENTER, TitledBorder.TOP,
        			new Font("Arial",Font.BOLD,(int) width/90), Color.BLACK);
			panel.setBorder(borde1);
			
			break;
			
        	case 2: Border borde2 = BorderFactory.createTitledBorder(null, "Domicilio comercial", 
        			TitledBorder.CENTER, TitledBorder.TOP,
        			new Font("Arial",Font.BOLD,(int) width/90), Color.BLACK);
			panel.setBorder(borde2);
			
			break;
        }
        
        contador_panel++;
        
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        panel.setBackground(new Color(255,255,255));
        // Turn on automatically adding gaps between components
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true); 
        // Create a sequential group for the horizontal axis.
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        
        hGroup.addContainerGap(20, 70);
       
        GroupLayout.Group yLabelGroup = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
        hGroup.addGroup(yLabelGroup);
        GroupLayout.Group yFieldGroup = layout.createParallelGroup();
        hGroup.addGroup(yFieldGroup);
        layout.setHorizontalGroup(hGroup);
        
        hGroup.addContainerGap(20, 70);
        // Create a sequential group for the vertical axis.
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        layout.setVerticalGroup(vGroup);

        
        int p = GroupLayout.PREFERRED_SIZE;
        // add the components to the groups
        for (JLabel label : labels) {
        	
        	label.setFont(new Font("Arial", Font.PLAIN, (int) width/90));
            yLabelGroup.addComponent(label);
        }
        
        for (Component field : fields) {
        	field.setFont(new Font("Arial", Font.PLAIN, (int) width/90));
            yFieldGroup.addComponent(field, p, p, p);
            
            field.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if (e.getClickCount() == 2 && !e.isConsumed()) {
				
					     //handle double click event.
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
            	
            	
            });
            
            field.addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					field.setBackground(new Color(183, 242, 113));
				
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					field.setBackground(new Color(255,255,255));
					
				}
            	
            	
            });
        }
        for (int ii = 0; ii < labels.length; ii++) {
            vGroup.addGroup(layout.createParallelGroup().
                    addComponent(labels[ii]).
                    addComponent(fields[ii], p, p, p));
        }

        if (addMnemonics) {
            addMnemonics(labels, fields);
        }

        return panel;
    }

    private final static void addMnemonics(
            JLabel[] labels,
            JComponent[] fields) {
        Map<Character, Object> m = new HashMap<Character, Object>();
        for (int ii = 0; ii < labels.length; ii++) {
            labels[ii].setLabelFor(fields[ii]);
            String lwr = labels[ii].getText().toLowerCase();
            for (int jj = 0; jj < lwr.length(); jj++) {
                char ch = lwr.charAt(jj);
                if (m.get(ch) == null && Character.isLetterOrDigit(ch)) {
                    m.put(ch, ch);
                    labels[ii].setDisplayedMnemonic(ch);
                    break;
                }
            }
        }
    }

    /**
     * Provides a JPanel with two columns (labels & fields) laid out using
     * GroupLayout. The arrays must be of equal size.
     *
     * @param labelStrings Strings that will be used for labels.
     * @param fields The corresponding fields.
     * @return JComponent A JPanel with two columns of the components provided.
     */
    public static JComponent getTwoColumnLayout(
            String[] labelStrings,
            JComponent[] fields) {
        JLabel[] labels = new JLabel[labelStrings.length];
        for (int ii = 0; ii < labels.length; ii++) {
            labels[ii] = new JLabel(labelStrings[ii]);
        }
        return getTwoColumnLayout(labels, fields);
    }

    /**
     * Provides a JPanel with two columns (labels & fields) laid out using
     * GroupLayout. The arrays must be of equal size.
     *
     * @param labels The first column contains labels.
     * @param fields The last column contains fields.
     * @return JComponent A JPanel with two columns of the components provided.
     */
    public static JComponent getTwoColumnLayout(
            JLabel[] labels,
            JComponent[] fields) {
        return getTwoColumnLayout(labels, fields, true);
    }

    public static String getProperty(String name) {
        return name + ": \t"
                + System.getProperty(name)
                + System.getProperty("line.separator");
    }
	
    public void setCarta(Cartas panel_cartas){
		
		this.panel_cartas = panel_cartas;
	}
    
    public void setPestaña(Pestaña _pestaña){
    	
    	this._pestaña = _pestaña;
    }
    
    public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
    
    
    public void setVistaPrincipal(VistaPrincipal _vista_principal){
		
		this._vista_principal = _vista_principal;
	}
    
	public void setControladorCliente(ControladorCliente _controladorCliente){
		
		this._controladorCliente = _controladorCliente;
	}
	
	public void setControladorDomicilioParticular(ControladorDomicilioParticular _controladorDomPart){
		
		this._controladorDomPart = _controladorDomPart;
	}
	
	public void setControladorDomicilioComercial(ControladorDomicilioComercial _controladorDomCom){
		
		this._controladorDomCom = _controladorDomCom;
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
	
	public void setControladorEmpleado(ControladorEmpleado controladorEmpleado){
		
		this.controladorEmpleado = controladorEmpleado;
	}
	
	public JTextField getEmail(){
		
		return emailTF;
	}
	
	public ArrayList<JDatePickerImpl> getArrayDatePicker(){
		
		return array_datepicker;
	}
	
	public ArrayList<JTextField> getArrayString(){
		
		return stringTF;
	}
	
	public ArrayList<JTextField> getArrayInt(){
		
		return intTF;
	}
	
	public ArrayList<JTextField> getArrayStringDomPart(){
		
		return stringTFDomPart;
	}
	
	public ArrayList<JTextField> getArrayIntDomPart(){
		
		return intTFDomPart;
	}
	
	public ArrayList<JTextField> getArrayStringDomCom(){
		
		return stringTFDomCom;
	}
	
	public ArrayList<JTextField> getArrayIntDomCom(){
		
		return intTFDomCom;
	}
	public ArrayList<JTextField> getArrayConyugue(){
		
		return ar_conyugue;
	}
	
	/*public DefaultTableModel getTabla(){
		
		return zonaCombo;
	}*/
	
	public JTextField getZonaTF(){
		
		return id_zonaTF;
	}
	
	public JTextField getLocalidadPartTF(){
		
		return id_localidadTF;
	}
	
	public JTextField getLocalidadComTF(){
	
		return id_localidadComercialTF;
	}
	
	public JLabel getLocalidadPartL(){
		
		return lLocalidadPart;
	}
	
	public JLabel getLocalidadComL(){
		
		return lLocalidadCom;
	}
	
	public JLabel getCobradorZonaL(){
		
		return lCobradorZona;
	}
	
	public JTextField getVendedorTF(){
		
		return id_vendedorTF;
	}
	
	public JLabel getVendedorL(){
		
		return lVendedor;
	}
	public JTextField getComercioTF(){
		
		return comercioTF;
	}
	
	public JLabel getComercioL(){
		
		return lComercio;
	}

	public JRadioButton getSoltero(){
		
		return soltero;
	}
	public JRadioButton getCasado(){
		
		return casado;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		_busqueda_entities.setPanel("vista_alta_cliente");	
		
		
		if(e.getSource() == siDpto){
			
			pisoTF.setEditable(true);
			intTFDomPart.add(pisoTF);
		}
		
		if(e.getSource() == noDpto){
			
			pisoTF.setText("");
			pisoTF.setEditable(false);
			intTFDomPart.remove(pisoTF);
		}
		
		if(e.getSource() == buscar_zona){
			
			_controladorZona.buscarZonaAll();
			_controladorZona.mostrarBusquedaEntities();
		}
		
		if(e.getSource() == buscar_localidadPart){
			
			_controladorLocalidad.buscarLocalidadAll();
			_controladorLocalidad.mostrarBusquedaEntities();
			_busqueda_entities.setTipoBusqueda(2);
		}
		
		if(e.getSource() == buscar_localidadCom){
			
			_controladorLocalidad.buscarLocalidadAll();
			_controladorLocalidad.mostrarBusquedaEntities();
			_busqueda_entities.setTipoBusqueda(3);
		}
		
		if(e.getSource() == buscar_vendedor){
			
			_controladorVendedor.buscarVendedorAll();
			_controladorVendedor.mostrarBusquedaEntities();
			_busqueda_entities.setTipoBusqueda(4);
		}
		if(e.getSource() == buscar_comercio){
			
			_controladorDomCom.buscarComercioAll();
			_controladorDomCom.mostrarBusquedaEntities("Buscar comercio");
			_busqueda_entities.setTipoBusqueda(21);
		}
		
		if(e.getSource()==cargar){
	
			_controladorCliente.altaClienteUsuario(this);
			_controladorDomPart.altaDomicilioParticularUsuario(this);
			_controladorDomCom.altaDomicilioComercialUsuario(this);
			
			if(LogicaCliente.vacio) Mensajes.getMensaje_vacio();
			else{		
				if(LogicaCliente.no_entero) Mensajes.getMensaje_no_entero();
				if(LogicaCliente.excede_caracteres) Mensajes.getMensaje_excede();
				if(LogicaCliente.error_email) Mensajes.getMensaje_error_email();
				if(LogicaCliente.error_fecha) Mensajes.getMensaje_error_fecha();
			}
			
			
			if(!LogicaCliente.validaraltaUsuario){
				
				System.out.println("Error de usuario, no accede a bd");
					
			}
			else{
				
				System.out.println("Sin error, accede a validacion sistema");
				
				VerazVO vVO = new VerazVO();
				ClienteVO _clienteVO = new ClienteVO();
				DomicilioParticularVO dpVO = new DomicilioParticularVO();
				DomicilioComercialVO dcVO = new DomicilioComercialVO();
				
				Date d = new java.util.Date();
				java.sql.Date fecha_registro = new java.sql.Date(d.getTime());
				java.sql.Time hora_registro = new java.sql.Time(d.getTime());
				
				String fecha_nacimientoS =fecha_nacimiento.getJFormattedTextField().getText();
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
				Date dateFN = new Date();
				try {
					dateFN = format.parse(fecha_nacimientoS);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date fecha_nacimientoDate = new java.sql.Date(dateFN.getTime());
				/*DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
				LocalDate fecha_nacimiento_date = LocalDate.parse(fecha_nacimientoS, formato);*/
				
				String antiguedadS =antiguedadTF.getJFormattedTextField().getText();
				DateFormat formatA = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
				Date dateAnt = new Date();
				try {
					dateAnt = formatA.parse(antiguedadS);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date antiguedadDate = new java.sql.Date(dateAnt.getTime());
				/*DateTimeFormatter formatoAnt = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
				LocalDate antiguedad_date = LocalDate.parse(antiguedadS, formatoAnt);*/
				
				String antiguedad_comercialS =antiguedadComercialTF.getJFormattedTextField().getText();
				DateFormat formatAC = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
				Date dateAntC = new Date();
				try {
					dateAntC = formatAC.parse(antiguedad_comercialS);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date antiguedad_comercialDate = new java.sql.Date(dateAntC.getTime());
				
				/*DateTimeFormatter formatoAntCom = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
				LocalDate antiguedad_comercial_date = LocalDate.parse(antiguedad_comercialS, formatoAntCom);*/
				
				_clienteVO.setDni(Integer.parseInt(dniTF.getText()));
				_clienteVO.setNombre(nombreTF.getText());
				_clienteVO.setApellido(apellidoTF.getText());
				_clienteVO.setNacionalidad(nacionalidadTF.getText());
				if(soltero.isSelected()) {
					
					_clienteVO.setEstado_civil(true);
					/*_clienteVO.setDni_conyugue();
					_clienteVO.setNombre_conyugue(nombre_conyugueTF.getText());
					_clienteVO.setApellido_conyugue(apellido_conyugueTF.getText());*/
				}
				else{
					
					_clienteVO.setEstado_civil(false);		
					_clienteVO.setDni_conyugue(Integer.parseInt(dni_conyugueTF.getText()));
					_clienteVO.setNombre_conyugue(nombre_conyugueTF.getText());
					_clienteVO.setApellido_conyugue(apellido_conyugueTF.getText());
				}
				_clienteVO.setTelefono_movil(telefono_movilTF.getText());
				_clienteVO.setTelefono_linea(telefono_lineaTF.getText());
				_clienteVO.setEmail(emailTF.getText());
				_clienteVO.setId_vendedor(Short.parseShort(id_vendedorTF.getText()));
				_clienteVO.setN_orden_planilla((short) 1);
				_clienteVO.setEstado("Revision");			
				_clienteVO.setFecha_nacimiento(fecha_nacimientoDate);
				_clienteVO.setFecha_alta(fecha_registro);
				_clienteVO.setId_usuario(Principal._usuario.getId_usuario());
				_clienteVO.setFecha_registro(fecha_registro);
				_clienteVO.setHora_registro(hora_registro);
				
				dpVO.setDni(Integer.parseInt(dniTF.getText()));
				dpVO.setDomicilio(domicilio_partTF.getText());
				dpVO.setEntre_calle1(entre_calle1TF.getText());
				dpVO.setEntre_calle2(entre_calle2TF.getText());
				if(siDpto.isSelected()){
					
					dpVO.setDpto(true);
					dpVO.setPiso(Short.parseShort(pisoTF.getText()));
				}
				else dpVO.setDpto(false);		
				
				dpVO.setBarrio(barrioTF.getText());
				dpVO.setCp(Integer.parseInt(cpTF.getText()));
				dpVO.setId_localidad(Short.parseShort(id_localidadTF.getText()));
				dpVO.setAntiguedad(antiguedadDate);
				if(propio.isSelected())dpVO.setPropio(true);
				else dpVO.setPropio(false);	
				
				dcVO.setId_zona(Short.parseShort(id_zonaTF.getText()));
				dcVO.setDni(Integer.parseInt(dniTF.getText()));
				dcVO.setDomicilio(domicilio_comTF.getText());
				dcVO.setEntre_calle1(entre_calle1ComercialTF.getText());
				dcVO.setEntre_calle2(entre_calle2ComercialTF.getText());
				dcVO.setBarrio(barrioComercialTF.getText());
				dcVO.setCp(Integer.parseInt(cpComercialTF.getText()));
				dcVO.setId_localidad(Short.parseShort(id_localidadComercialTF.getText()));
				dcVO.setN_orden_planilla(1);
				dcVO.setAntiguedad(antiguedad_comercialDate);
				dcVO.setComercio(Integer.parseInt(comercioTF.getText()));
				if(propioComercial.isSelected())dcVO.setPropio(true);
				else dcVO.setPropio(false);	
			
				
				dcVO.setHorario_atencion(Horario.getHorario(spinnerDesde, spinnerHasta));
				
				//VerazVO verazVO = _controladorCliente.comprobarRelacion_enVeraz(_clienteVO, dpVO, dcVO);
			
				boolean relacion_veraz = false;
				
				VerazVO veraz_dni_conVO = _controladorCliente.comprobarVeraz_porDni_conyugue(_clienteVO);
				VerazVO veraz_dom_partVO = _controladorCliente.
						comprobarVeraz_porDom_part(dpVO);
				VerazVO veraz_dom_comVO = _controladorCliente.
						comprobarVeraz_porDom_com(dcVO);
				
				if(veraz_dni_conVO!=null){
				
					relacion_veraz = true;
					
					Mensajes.getMensaje_relacionVeraz_dni_con();
				}
				if(veraz_dom_partVO!=null){
					
					relacion_veraz = true;
					
					Mensajes.getMensaje_relacionVeraz_dom_part();
				}
				if(veraz_dom_comVO!=null){
					
					relacion_veraz = true;
					
					Mensajes.getMensaje_relacionVeraz_dom_com();
				}
				
				if(_controladorCliente.comprobarVeraz_porDni(_clienteVO.getDni())!=null){
					
					Mensajes.getMensaje_clienteEnVeraz();
					
				}
				else{
					
					System.out.println("cliente no figura en veraz continua validacion...");
					
					int confirmacion = JOptionPane.YES_OPTION;
					
					if(relacion_veraz){
						
						confirmacion = Mensajes.getMensaje_confirmacion_altaCliente();
						
					}
					
					if(confirmacion == JOptionPane.YES_OPTION){
						
						_controladorCliente.altaCliente(_clienteVO);
						_controladorDomPart.altaDomicilioParticular(dpVO);
						_controladorDomCom.altaDomicilioComercial(dcVO);
						
						
						if(LogicaCliente.validaralta) System.out.println("hola");
						
						if(LogicaCliente.resultado_insertCliente > 0 && 
								LogicaDomicilioParticular.resultado_insertDomPart > 0 &&  
								LogicaDomicilioComercial.resultado_insertDomCom > 0 &&
								LogicaCliente.validaralta){
							
							_controladorCliente.updateEstado("nuevo", _clienteVO.getDni());
							
							
							Mensajes.getMensaje_altaExitoso();
							
							_pestaña.remove(_pestaña.getSelectedComponent());
							
						}
						
						else if(LogicaCliente.validaralta) Mensajes.getMensaje_altaError();
						
					}
					
					
				}
					
			}
		}
	}
	
	 public class MenuPane extends JPanel {

	        public MenuPane(JTextField tf, JButton bt) {
	            setLayout(new GridBagLayout());

	            GridBagConstraints gbc = new GridBagConstraints();
	            gbc.gridx = 10;
	            gbc.gridy = 10;
	            gbc.fill = GridBagConstraints.VERTICAL;
	            gbc.ipadx = 0;
	            gbc.ipady = 0;

	            
	            gbc.gridy++;
	            gbc.gridwidth = 5;
	            gbc.anchor = GridBagConstraints.EAST;
	            add(tf, gbc);
	           
	            gbc.gridy++;
	            gbc.gridwidth = 1;
	            gbc.anchor = GridBagConstraints.WEST;
	            add(bt, gbc);
	        }
	 }
	 
	 public void limpiar(){
		 
		 for(JTextField tf : getArrayString())
				tf.setText("");
			
			for(JTextField tf : getArrayInt())
				tf.setText("");     
			
			for(JTextField tf : getArrayIntDomCom())
				tf.setText("");
			
			for(JTextField tf : getArrayIntDomPart())
				tf.setText("");  
		      
			for(JTextField tf : getArrayStringDomCom())
				tf.setText(""); 
			
			for(JTextField tf : getArrayStringDomPart())
				tf.setText(""); 
	 }
	
}
