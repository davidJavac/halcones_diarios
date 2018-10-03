package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;

public class ListenerFormato {

	public static ActionListener getEntrada_sig(final AbstractButton c) {
        ActionListener accion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	c.setContentAreaFilled(true);
            	
            	c.setBackground(new Color(250, 215, 160));
		   
		    	c.setBorder(BorderFactory.createLineBorder(new Color(235, 152, 78)));
            }
        };
        return accion;
    }
	
	public static ActionListener getSalida_sig(final AbstractButton c) {
        ActionListener accion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	c.setContentAreaFilled(false);
		    	
		    	c.setBorder(BorderFactory.createLineBorder(new Color(97, 106, 107)));
            }
        };
        return accion;
    }
	
	public static ActionListener getEntrada_atr(final AbstractButton c) {
        ActionListener accion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	c.setContentAreaFilled(true);
		   
            	c.setBackground(new Color(250, 215, 160));
            	
            	c.setBorder(BorderFactory.createLineBorder(new Color(235, 152, 78)));
            }
        };
        return accion;
    }
	
	public static ActionListener getSalida_atr(final AbstractButton c) {
        ActionListener accion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	c.setContentAreaFilled(false);
		    	
            	c.setBorder(BorderFactory.createLineBorder(new Color(174, 214, 241)));
            }
        };
        return accion;
    }
	
	public static ActionListener getEntrada_eliminar(final AbstractButton c) {
        ActionListener accion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	c.setContentAreaFilled(true);
		   
            	c.setBackground(new Color(250, 215, 160));
            	
            	c.setBorder(BorderFactory.createLineBorder(new Color(235, 152, 78)));
            }
        };
        return accion;
    }
	
	public static ActionListener getSalida_eliminar(final AbstractButton c) {
        ActionListener accion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	c.setContentAreaFilled(false);
		    	
            	c.setBorder(BorderFactory.createLineBorder(new Color(234, 242, 248)));
            }
        };
        return accion;
    }
	
}
