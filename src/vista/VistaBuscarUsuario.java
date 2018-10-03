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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import modelo_vo.UsuariosVO;

public class VistaBuscarUsuario extends JDialog implements ActionListener{
	

	private JTextField usuarioTF;
	private JPasswordField contraseñaTF;
	
	private JPanel pPrincipal;
	private JPanel pSur;
	
	private JButton acceder;
	private JButton salir;
	
	private ControladorUsuario _controladorUsuario;
	private static VistaPrincipal _vistaPrincipal;
	
	private Principal _principal;
	
	private ArrayList<JTextField> arrayTF;
	
	private static String key = "654321";
	
	public VistaBuscarUsuario(){
		super(_vistaPrincipal, "Ingreso al sistema", JDialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		
		this.setSize(500, 150);
		
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
		usuarioTF = new JTextField(15);
		contraseñaTF = new JPasswordField(15);		
		
		
		/*pUsuario.add(lUs);
		pUsuario.add(usuarioTF);
		pContrasena.add(lCont);
	    pContrasena.add(contraseñaTF);*/
	    
	    pUsuario.setOpaque(false);
	    pContrasena.setOpaque(false);
	    
		try {
			Image im = ImageIO.read(getClass().getResource("/contrasena.png"));
			JLabel im_contr = new JLabel(new ImageIcon(im));
			
			pIcono.add(im_contr, BorderLayout.WEST);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		JComponent[] components = {
	              
				usuarioTF,
				contraseñaTF
            };
		
		 String labels [] = {
				 
				"Usuario", "Contraseña"
				
		 };
		 
		 pPrincipal = new PanelGraduado(new Color(214, 234, 248), new Color(93, 173, 226));
//		 pPrincipal.setLayout(new BorderLayout());
		 pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.Y_AXIS));
		 
		 pSur = new JPanel();
		 
		 acceder = new JButton("Acceder");
		 salir = new JButton("Salir");
		 acceder.addActionListener(this);
		 salir.addActionListener(this);
		 
		 pSur.add(acceder);
		 pSur.add(salir);
		 
		 JComponent labelsAndFields = getTwoColumnLayout(labels,components);
		 
		 pIcono.add(labelsAndFields);
		 
		 pPrincipal.add(pIcono);
		 //pPrincipal.add(labelsAndFields, BorderLayout.CENTER);
		 //pPrincipal.add(pSur, BorderLayout.SOUTH);
		 
		 //add(pPrincipal);
		 
//		 pPrincipal.add(pUsuario);
	//	 pPrincipal.add(pContrasena);
		 pPrincipal.add(pSur);
		 
		 add(pPrincipal);
		 
		 arrayTF = new ArrayList<JTextField>();
		 arrayTF.add(usuarioTF);
		 arrayTF.add(contraseñaTF);
		 
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
		if(e.getSource() == acceder){
			
			boolean valbusqueda = false;
			
			valbusqueda = _controladorUsuario.buscarUsuario(this);
			
			UsuariosVO _usuarioVO = new UsuariosVO();
			UsuariosVO _usuarioVOresultado = new UsuariosVO();
			
			if(valbusqueda){
				
				String conv_password = new String(contraseñaTF.getPassword());
				_usuarioVO.setNombre(usuarioTF.getText());
				_usuarioVO.setContraseña(conv_password);
				
				System.out.println(conv_password);
				
				_usuarioVOresultado = _controladorUsuario.buscar(_usuarioVO, key);
				
				System.out.println("sin error");
				
				if(/*!LogicaUsuario.validarBusqueda*/_usuarioVOresultado==null)
					Mensajes.getMensaje_usuarioNoExiste();
				else {
					
					//_vistaPrincipal.setEnabled(true);
					Principal._usuario.setId_usuario(_usuarioVOresultado.getId_usuario());
					Principal._usuario.setNombre(_usuarioVOresultado.getNombre());
					Principal._usuario.setContraseña(_usuarioVOresultado.getContraseña());
					Principal._usuario.setEstado(_usuarioVOresultado.getEstado());
					Principal._usuario.setPermiso(_usuarioVOresultado.getPermiso());
					JLabel lUsuario = _vistaPrincipal.getLabelUsuario();
					
					lUsuario.setText("Usuario " + Principal._usuario.getNombre());
					this.dispose();
					
					
					
				}
			}
		}
		
		if(e.getSource() == salir) System.exit(0);
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
