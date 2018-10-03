package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePickerImpl;

import controlador.ControladorCliente;
import controlador.ControladorUsuario;
import controlador.Principal;
import modelo.LogicaUsuario;
import modelo.Mensajes;
import modelo_vo.ContrasenasVO;
import modelo_vo.UsuariosVO;

public class VistaCambioContrasena extends JDialog implements ActionListener{
	
	private JPasswordField contraseñaTF;
	private JPasswordField confirmar_contraseñaTF;
	private JPasswordField contraseña_inTF;
	private JPasswordField confirmar_contraseña_inTF;
	
	private JPanel pPrincipal;
	private JPanel pSur;
	
	private JButton guardar;
	private JButton cancelar;
	
	private ControladorUsuario _controladorUsuario;
	private static VistaPrincipal _vistaPrincipal;
	
	private Principal _principal;
	
	private ArrayList<JTextField> arrayTF;
	private ArrayList<JTextField> array_inTF;
	
	private JCheckBox conCH = new JCheckBox();
	private JCheckBox con_inCH = new JCheckBox();
	
	private static String key = "654321";
	private boolean permiso;
	
	private String tipo;
	
	public VistaCambioContrasena(){
		super(_vistaPrincipal, "Ingrese contraseña", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		
		this.setSize(550, 250);
		
		permiso = false;
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		this.setResizable(false);
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//this.setTitle("Ingreso al sistema");
	
		/*setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);*/
		JPanel pIcono = new JPanel();
		JPanel pContrasena = new JPanel();
		JPanel pUsuario = new JPanel();
		
		JLabel lUs = new JLabel();
		JLabel lCont = new JLabel();
		
		pIcono.setLayout(new BorderLayout());
		pIcono.setOpaque(false);
		
		lUs.setText("Usuario");
		lCont.setText("Contraseña");
		
		//pContrasena.setLayout(gl);

		contraseñaTF = new JPasswordField(15);		
		confirmar_contraseñaTF = new JPasswordField(15);		
		contraseña_inTF = new JPasswordField(15);		
		confirmar_contraseña_inTF = new JPasswordField(15);		
		
		conCH.setText("Cambiar contraseña");
		con_inCH.setText("Cambiar contraseña interna");
		conCH.setOpaque(false);
		con_inCH.setOpaque(false);
		/*pUsuario.add(lUs);
		pUsuario.add(usuarioTF);
		pContrasena.add(lCont);
	    pContrasena.add(contraseñaTF);*/
	    
	    pUsuario.setOpaque(false);
	    pContrasena.setOpaque(false);
	    
		try {
			Image im = ImageIO.read(getClass().getResource("/contrasena.png"));
			JLabel im_contr = new JLabel(new ImageIcon(im));
			
			//pIcono.add(im_contr, BorderLayout.WEST);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		JComponent[] components = {
	              
				//usuarioTF,
				contraseñaTF,
				confirmar_contraseñaTF,
				contraseña_inTF,
				confirmar_contraseña_inTF
            };
		
		 String labels [] = {
				 
				"Nueva contraseña",
				"Confirmar contraseña",
				"Nueva contraseña interna",
				"Confirmar contraseña interna"
				
		 };
		 
		 pPrincipal = new PanelGraduado(new Color(248, 249, 249), new Color(202, 207, 210));
//		 pPrincipal.setLayout(new BorderLayout());
		 pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.Y_AXIS));
		 
		 pSur = new JPanel();
		 
		 guardar = new JButton("Guardar");
		 cancelar = new JButton("Cancelar");
		 guardar.addActionListener(this);
		 cancelar.addActionListener(this);
		 
		 
		 JPanel pOpciones = new JPanel();
		 
		 pOpciones.setOpaque(false);
		 pOpciones.add(conCH);
		 pOpciones.add(con_inCH);
		 
		 pSur.add(guardar);
		//pSur.add(cancelar);
		 
		 JComponent labelsAndFields = getTwoColumnLayout(labels,components);
		 
		 pIcono.add(labelsAndFields);
		 
		 pPrincipal.add(pIcono);
		 //pPrincipal.add(labelsAndFields, BorderLayout.CENTER);
		 //pPrincipal.add(pSur, BorderLayout.SOUTH);
		 
		 //add(pPrincipal);
		 
//		 pPrincipal.add(pUsuario);
	//	 pPrincipal.add(pContrasena);
		 pPrincipal.add(pOpciones);
		 pPrincipal.add(pSur);
		 
		 add(pPrincipal);
		 
		 arrayTF = new ArrayList<JTextField>();
		 array_inTF = new ArrayList<JTextField>();
		
		 arrayTF.add(contraseñaTF);
		 arrayTF.add(confirmar_contraseñaTF);
		 array_inTF.add(contraseña_inTF);
		 array_inTF.add(confirmar_contraseña_inTF);
		 
		 if(Principal._usuario.getPermiso()!=1){
			 con_inCH.setEnabled(false);
		 }
		 contraseñaTF.setEnabled(false);
		 confirmar_contraseñaTF.setEnabled(false);
		 contraseña_inTF.setEnabled(false);
		 confirmar_contraseña_inTF.setEnabled(false);
		 
		 conCH.addItemListener(new ItemListener(){
			 
			 @Override
			 public void itemStateChanged(ItemEvent e) {
				 // TODO Auto-generated method stub
				 
				 Object source = e.getItemSelectable();
				 if(conCH.isSelected()){
					 contraseñaTF.setEnabled(true);
					 confirmar_contraseñaTF.setEnabled(true);
					 
					 
				 }
				 else{
					 
					 contraseñaTF.setEnabled(false);
					 confirmar_contraseñaTF.setEnabled(false);
					 
				 }
			 }
			 
			 
		 });
		 con_inCH.addItemListener(new ItemListener(){

				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					
					Object source = e.getItemSelectable();
					if(con_inCH.isSelected()){
						contraseña_inTF.setEnabled(true);
						confirmar_contraseña_inTF.setEnabled(true);
						
						
					}
					else{
						
						contraseña_inTF.setEnabled(false);
						confirmar_contraseña_inTF.setEnabled(false);
								
					}
				}
		    	   
		    	  
		       });
		       
		 
	}
	
	public ArrayList<JTextField> getArrayTF(){
		
		return arrayTF;
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
        JComponent panel =  new JPanel();
        panel.setOpaque(false);
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
        	
        	label.setFont(new Font("Arial", Font.PLAIN, 18));
            yLabelGroup.addComponent(label);
        }
        
        for (Component field : fields) {
        	field.setFont(new Font("Arial", Font.PLAIN, 16));
            yFieldGroup.addComponent(field, p, p, p);
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
		if(e.getSource() == guardar){
			
			if(_controladorUsuario.validarNuevaContrasena(arrayTF,array_inTF, Principal._usuario)){
				
				String conv_password = new String(contraseñaTF.getPassword());
				String conv_password_in = new String(contraseña_inTF.getPassword());
				
				ContrasenasVO cVO = new ContrasenasVO();
				
				cVO.setDescripcion(tipo);
				
				cVO.setContrasena(conv_password);
				
				System.out.println(conv_password);
				
				System.out.println("sin error");
				
				int res = 0;
				int res_in = 0;
				
				if(conCH.isSelected()){
					
					try {
						res = _controladorUsuario.nueva_contrasena(Principal._usuario, conv_password,
								key);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(res > 0){
						
						Mensajes.getMensaje_modificacionExitosa();
					}
					else
						Mensajes.getMensaje_modificacion_sinExito();
				}
				
				
				if(con_inCH.isSelected()){
					
						try {
							res_in =_controladorUsuario.nueva_contrasena_interna(conv_password_in,
									key);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(res_in > 0){
							
							Mensajes.getMensaje_modificacionExitosa();
						}
						else
							Mensajes.getMensaje_modificacion_sinExito();
						
				}
				
			}
			else Mensajes.getMensaje_altaErrorGenerico();
				
		}
	
	}
	
	public boolean getPermiso(){
		
		return permiso;
	}
	
	public void setPrincipal(Principal _principal){
		
		this._principal = _principal;
	}
	
	public void setVistaPrincipal(VistaPrincipal _vistaPrincipal){
		
		this._vistaPrincipal = _vistaPrincipal;
	}
	
	public void setControladorUsuario(ControladorUsuario _controladorUsuario){
		
		this._controladorUsuario = _controladorUsuario;
	}
}
