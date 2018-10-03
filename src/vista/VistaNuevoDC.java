package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controlador.ControladorDomicilioComercial;
import controlador.ControladorEmpleado;
import controlador.ControladorLocalidad;
import controlador.ControladorZona;
import modelo.LogicaCliente;
import modelo.LogicaDomicilioComercial;
import modelo.Mensajes;
import modelo_vo.ComercioVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.ZonaVO;

public class VistaNuevoDC extends JDialog implements ActionListener{

	private BusquedaEntities _busqueda_entities;
	private  VistaBuscarCliente vbc;
	private static VistaPrincipal vp;
	
	private JSpinner spinnerDesde;
	private JSpinner spinnerHasta;
	private  JSpinner.DateEditor deD;
	private JSpinner.DateEditor deH;
	private Horario desdeH;
	private Horario hastaH;
	
	private JTextField id_zonaTF;
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
	
	private ControladorDomicilioComercial _controladorDomCom;
	
	private JButton guardar = new JButton("Guardar");
	private JButton buscar_zona = new JButton("...");
	private JButton buscar_localidadCom = new JButton("...");
	private JButton buscar_comercio = new JButton("...");
	private ControladorZona _controladorZona;
	private ControladorEmpleado controladorEmpleado;
	private ControladorLocalidad _controladorLocalidad;
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static double width = screenSize.getWidth();
    double height = screenSize.getHeight();
    

    private JPanel pId_zona;
    private JPanel pId_vendedor;
    private JPanel pDomCom;
    
	private JPanel pAlquilaCom;
	private JPanel pHorario_atencion;
	
	private JPanel pId_localidadCom;
	private JPanel pId_comercio;
	private JLabel  lLocalidadCom, lCobradorZona,lComercio;
	
	UtilDateModel modelAC = new UtilDateModel();
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setVistaBuscarCliente(VistaBuscarCliente vbc){
		
		this.vbc = vbc;
	}
	public void setVistaPrincipal(VistaPrincipal vp){
		
		this.vp = vp;
	}
	
	public void setControladorDomicilioComercial(ControladorDomicilioComercial _controladorDomCom){
		
		this._controladorDomCom = _controladorDomCom;
	}
	public void setControladorEmpleado (ControladorEmpleado _controladorEmpleado){
		
		this.controladorEmpleado = _controladorEmpleado;
	}
	public void setControladorZona(ControladorZona _controladorZona){
		
		this._controladorZona = _controladorZona;
	}
	public void setControladorLocalidad(ControladorLocalidad _controladorLocalidad){
		
		this._controladorLocalidad = _controladorLocalidad;
	}
	
	private ArrayList<JTextField> stringTFDomCom;
	private ArrayList<JTextField> intTFDomCom;
	
	public VistaNuevoDC(){
		
		super(vp, "Nuevo domicilio comercial", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		
		this.setLayout(new BorderLayout());
		
		this.setSize(500, 600);
		 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
	     setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	     this.setResizable(false);
	     
	     this.setResizable(false);
	     this.setTitle("Nuevo domicilio comercial");
		
		buscar_zona.addActionListener(this);
		guardar.addActionListener(this);
		
		buscar_localidadCom.addActionListener(this);
		
		buscar_comercio.addActionListener(this);
		
		
		pDomCom = new JPanel();
		
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
		pId_localidadCom = new JPanel();
  		//buscar_zona.setPreferredSize(new Dimension(2, 2));
  		pId_zona.setLayout(gl);
  		pId_zona.add(id_zonaTF);
  		pId_zona.add(buscar_zona);
  		
  		lLocalidadCom = new JLabel();
  		
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
		
  		pId_comercio = new JPanel();
  		lComercio = new JLabel();
  		
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
  		pId_comercio.setLayout(gl);
  		pId_comercio.add(comercioTF);
  		pId_comercio.add(buscar_comercio);
  		
  		Properties p = new Properties();
  		p.put("text.today", "Today");
  		p.put("text.month", "Month");
  		p.put("text.year", "Year");
        
        JDatePanelImpl datePanelAC = new JDatePanelImpl(modelAC, p);
		
        Date date = new Date();
        SpinnerDateModel smD = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        SpinnerDateModel smH = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        
        desdeH = new Horario(smD, "Desde");
        hastaH = new Horario(smH, "Hasta");
  		
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
		 
		 JFormattedTextField antCom = antiguedadComercialTF.getJFormattedTextField();
		 antCom.setFont(new Font("Arial", Font.PLAIN, (int)width/90));
		 
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
			
			pHorario_atencion.add(desdeH);
	  		pHorario_atencion.add(hastaH);
	  		
	  		stringTFDomCom = new ArrayList<JTextField>();
			
			stringTFDomCom.add(domicilio_comTF);
			stringTFDomCom.add(entre_calle1ComercialTF);
			stringTFDomCom.add(entre_calle2ComercialTF);
			stringTFDomCom.add(barrioComercialTF);
			//stringTFDomCom.add(comercioTF);
			
			intTFDomCom = new ArrayList<JTextField>();
		
			intTFDomCom.add(id_localidadComercialTF);
			intTFDomCom.add(cpComercialTF);
		
			 JComponent labelsAndFields_dom_com = getTwoColumnLayout(dom_com_labels, dom_com_components);
			 
			 JPanel pComando = new JPanel();
			 pComando.add(guardar);
			 
			 this.add(pComando, BorderLayout.PAGE_START);
			 this.add(labelsAndFields_dom_com, BorderLayout.CENTER);
			
		 
	}
	
	public JTextField getId_zonaTF(){
		
		return id_zonaTF;
	}
	public JTextField getId_localidadTF(){
		
		return id_localidadComercialTF;
	}
	public JTextField getId_comercioTF(){
		
		return comercioTF;
	}
	
	public JLabel getLLocalidadCom(){
		
		return lLocalidadCom;
	}
	public JLabel getLComercio(){
		
		return lComercio;
	}
	public JLabel getLCobrador(){
		
		return lCobradorZona;
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
        
        Font fuente_titulo = new Font("Arial", Font.BOLD, 20);
  
        Border borde2 = BorderFactory.createTitledBorder(null, "Domicilio comercial", 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,(int) width/90), Color.BLACK);
		panel.setBorder(borde2);
        
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
        	//label.setFont(new Font("Arial", Font.PLAIN, 20));
            yLabelGroup.addComponent(label);
        }
        
        for (Component field : fields) {
        	field.setFont(new Font("Arial", Font.PLAIN, (int) width/90));
        	//field.setFont(new Font("Arial", Font.PLAIN, 20));
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		_busqueda_entities.setPanel("vista_nuevo_dc");
		
		if(e.getSource() == buscar_zona){
			
			_controladorZona.buscarZonaAll();
			_controladorZona.mostrarBusquedaEntities();
		}
		
		
		
		if(e.getSource() == buscar_localidadCom){
			
			_busqueda_entities.setTipoBusqueda(3);
			_controladorLocalidad.buscarLocalidadAll();
			_controladorLocalidad.mostrarBusquedaEntities();
		}
		
		if(e.getSource() == buscar_comercio){
			
			_controladorDomCom.buscarComercioAll();
			_controladorDomCom.mostrarBusquedaEntities("Buscar comercio");
			_busqueda_entities.setTipoBusqueda(21);
		}
		
		if(e.getSource()==guardar){
			
			if(_controladorDomCom.validarAltaUsuario(stringTFDomCom, intTFDomCom, 
					antiguedadComercialTF)){
				
				System.out.println("no problems");
				
				DomicilioComercialVO dcVO = new DomicilioComercialVO();
				
				dcVO.setId_zona(Short.parseShort(id_zonaTF.getText()));
				dcVO.setDni(vbc.getClienteVO().getDni());
				dcVO.setDomicilio(domicilio_comTF.getText());
				dcVO.setEntre_calle1(entre_calle1ComercialTF.getText());
				dcVO.setEntre_calle2(entre_calle2ComercialTF.getText());
				dcVO.setBarrio(barrioComercialTF.getText());
				dcVO.setId_localidad(Short.parseShort(id_localidadComercialTF.getText()));
				dcVO.setN_orden_planilla(1);
				dcVO.setCp(Integer.parseInt(cpComercialTF.getText()));
				
				String antiguedadComS =antiguedadComercialTF.getJFormattedTextField().getText();
				DateFormat formatCom = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
				Date dateCom = new Date();
				try {
					dateCom = formatCom.parse(antiguedadComS);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date antiguedadComDate = new java.sql.Date(dateCom.getTime());
				
				dcVO.setAntiguedad(antiguedadComDate);
				dcVO.setComercio(Integer.parseInt(comercioTF.getText()));
				
				if(propioComercial.isSelected())
					
					dcVO.setPropio(true);
				else
					dcVO.setPropio(false);
				
				dcVO.setHorario_atencion(Horario.getHorario(desdeH, hastaH));
				
				LogicaCliente.validaralta = true;
				
				_controladorDomCom.altaDomicilioComercial(dcVO);
				
				if(LogicaDomicilioComercial.resultado_insertDomCom > 0){
					
					Mensajes.getMensaje_altaExitosoGenerico();
					this.dispose();
				}
				else
					Mensajes.getMensaje_altaErrorGenerico();
			}
			else
				Mensajes.getMensaje_altaErrorGenerico();
		}
	}
}
