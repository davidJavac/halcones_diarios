package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListView extends JPanel
	implements ListSelectionListener{

	private JList lista;
	private JScrollPane scr;
	private DefaultListModel listaModel;
	private JButton buscar;
	private JButton aceptar;
	private JTextField campo;
	
	public ListView(){
		
		super(new BorderLayout());

        listaModel = new DefaultListModel();
        
        listaModel.addElement("Jane Doe");
        listaModel.addElement("John Smith");
        listaModel.addElement("Kathy Green");
        
        lista = new JList(listaModel);

        //Create the list and put it in a scroll pane.
        lista = new JList(listaModel);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setSelectedIndex(0);
        lista.addListSelectionListener(this);
        lista.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(lista);

        buscar = new JButton("Buscar");
        aceptar = new JButton("Aceptar");
        campo = new JTextField(10);
        
 
        BuscarListener _buscarListener = new BuscarListener(buscar);
      // buscar.setActionCommand(hireString);
        buscar.addActionListener(_buscarListener);
        buscar.setEnabled(false);

       // aceptar.setActionCommand(fireString);
        aceptar.addActionListener(new AceptarListener());

        /*employeeName = new JTextField(10);
        employeeName.addActionListener(hireListener);
        employeeName.getDocument().addDocumentListener(hireListener);*/
        String name = listaModel.getElementAt(
                              lista.getSelectedIndex()).toString();

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        buttonPane.add(buscar);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(campo);
        buttonPane.add(aceptar);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
		
	}
	
	 class AceptarListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            //This method can be called only if
	            //there's a valid selection
	            //so go ahead and remove whatever's selected.
	            int index = lista.getSelectedIndex();
	            listaModel.remove(index);

	            int size = listaModel.getSize();

	            if (size == 0) { //Nobody's left, disable firing.
	                //fireButton.setEnabled(false);

	            } else { //Select an index.
	                if (index == listaModel.getSize()) {
	                    //removed item in last position
	                    index--;
	                }

	                lista.setSelectedIndex(index);
	                lista.ensureIndexIsVisible(index);
	            }
	        }
	    }

	    //This listener is shared by the text field and the hire button.
	    class BuscarListener implements ActionListener {
	        private boolean alreadyEnabled = false;
	        private JButton button;

	        public BuscarListener(JButton button) {
	            this.button = button;
	        }

	        //Required by ActionListener.
	        public void actionPerformed(ActionEvent e) {
	            String name = campo.getText();

	            //User didn't type in a unique name...
	            if (name.equals("") || alreadyInList(name)) {
	                Toolkit.getDefaultToolkit().beep();
	                campo.requestFocusInWindow();
	                campo.selectAll();
	                return;
	            }

	            int index = lista.getSelectedIndex(); //get selected index
	            if (index == -1) { //no selection, so insert at beginning
	                index = 0;
	            } else {           //add after the selected item
	                index++;
	            }

	            //listaModel.insertElementAt(employeeName.getText(), index);
	            //If we just wanted to add to the end, we'd do this:
	            //listModel.addElement(employeeName.getText());

	            //Reset the text field.
	           /* employeeName.requestFocusInWindow();
	            employeeName.setText("");*/

	            //Select the new item and make it visible.
	            lista.setSelectedIndex(index);
	            lista.ensureIndexIsVisible(index);
	        }

	        //This method tests for string equality. You could certainly
	        //get more sophisticated about the algorithm.  For example,
	        //you might want to ignore white space and capitalization.
	        protected boolean alreadyInList(String name) {
	            return listaModel.contains(name);
	        }

	        //Required by DocumentListener.
	        /*public void insertUpdate(DocumentEvent e) {
	            enableButton();
	        }

	        //Required by DocumentListener.
	        public void removeUpdate(DocumentEvent e) {
	            handleEmptyTextField(e);
	        }

	        //Required by DocumentListener.
	        public void changedUpdate(DocumentEvent e) {
	            if (!handleEmptyTextField(e)) {
	                enableButton();
	            }
	        }*/
	        private void enableButton() {
	            if (!alreadyEnabled) {
	                button.setEnabled(true);
	            }
	        }

	        private boolean handleEmptyTextField(DocumentEvent e) {
	            if (e.getDocument().getLength() <= 0) {
	                button.setEnabled(false);
	                alreadyEnabled = false;
	                return true;
	            }
	            return false;
	        }

			/*public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}*/
	    }

	    //This method is required by ListSelectionListener.
	    public void valueChanged(ListSelectionEvent e) {
	        if (e.getValueIsAdjusting() == false) {

	            if (lista.getSelectedIndex() == -1) {
	            //No selection, disable fire button.
	                //fireButton.setEnabled(false);

	            } else {
	            //Selection, enable the fire button.
	               // fireButton.setEnabled(true);
	            }
	        }
	    }

	    /**
	     * Create the GUI and show it.  For thread safety,
	     * this method should be invoked from the
	     * event-dispatching thread.
	     */
	    private static void crear_mostrar_ventana(String titulo) {
	        //Create and set up the window.
	        JFrame frame = new JFrame(titulo);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Create and set up the content pane.
	        JComponent newContentPane = new ListView();
	        newContentPane.setOpaque(true); //content panes must be opaque
	        frame.setContentPane(newContentPane);

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	crear_mostrar_ventana("Busqueda de zonas");
	            }
	        });
	    }
	
	public void setLista(Object [] o){
			
		
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setLayoutOrientation(JList.HORIZONTAL_WRAP	);
		lista.setVisibleRowCount(-1);
		scr = new JScrollPane(lista);
		scr.setPreferredSize(new Dimension(250, 80));
	}

}
