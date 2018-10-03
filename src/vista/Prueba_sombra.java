package vista;

import java.awt.Color;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.border.DropShadowBorder;

public class Prueba_sombra {

	public class Canvas extends JXPanel{

	    public Canvas(){
	        DropShadowBorder shadow = new DropShadowBorder();
	        shadow.setShadowColor(Color.BLACK);
	        shadow.setShowLeftShadow(true);
	        shadow.setShowRightShadow(true);
	        shadow.setShowBottomShadow(true);
	        shadow.setShowTopShadow(true);
	        this.setBorder(shadow);
	    }
	}
}
