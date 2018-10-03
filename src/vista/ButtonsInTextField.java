package vista;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

class ButtonsInTextField {

    JPanel gui = new JPanel(new GridBagLayout());
    JTextField textField;
    JButton b;
    
    ButtonsInTextField(int cols) {
        JPanel textFieldWithButtonsPanel = new JPanel(/*new FlowLayout(
                SwingConstants.LEADING, 5, 1)*/);
        textField = new JTextField(cols);
        textField.setFont(new Font("Arial", Font.PLAIN, 18));
        textFieldWithButtonsPanel.add(textField);

        addButtonToPanel(textFieldWithButtonsPanel, 8);
       /* addButtonToPanel(textFieldWithButtonsPanel, 16);
        addButtonToPanel(textFieldWithButtonsPanel, 24);*/

        // WARNING:  Not sensitive to PLAF change!
        textFieldWithButtonsPanel.setBackground(textField.getBackground());
        textFieldWithButtonsPanel.setBorder(textField.getBorder());
        textField.setBorder(null);
        // END WARNING:  

        gui.add(textFieldWithButtonsPanel);
    }

    private final void addButtonToPanel(JPanel panel, int height) {
        /*BufferedImage bi = new BufferedImage(
                // find the size of an icon from the system, 
                // this is just a guess
                24, height, BufferedImage.TYPE_INT_RGB);*/
        b = new JButton("prueba"/*new ImageIcon(bi)*/);
        b.setContentAreaFilled(false);
        //b.setBorderPainted(false);
        b.setMargin(new Insets(0,0,0,0));
        b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ventanaprueba v = new ventanaprueba();
				v.setVisible(true);
			}
        	
        	
        });
        panel.add(b);
    }

    public final JComponent getGui() {
        return gui;
    }

    public final JTextField getField() {
        return textField;
    }
    
    class ventanaprueba extends JFrame{
    	
    	public ventanaprueba(){
    		
    		this.setSize(200, 300);
    		this.setUndecorated(true);
    		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
    		this.setLocation(b.getLocationOnScreen().x - textField.getWidth(),
    				(b.getLocationOnScreen().y + textField.getHeight()));
    	}
    	
    }

   /* public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                ButtonsInTextField bitf = new ButtonsInTextField(20);
                JOptionPane.showMessageDialog(null, bitf.getGui());
            }
        };
        // Swing GUIs should be created and updated on the EDT
        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency
        SwingUtilities.invokeLater(r);
    }*/
    
}