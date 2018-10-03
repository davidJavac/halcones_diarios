package vista;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.JPanel;
import javax.swing.JTable;

public class Impresion implements Printable{
private JTable comp;
private double escalaX,escalaY;

public Impresion(JTable p,double escalax, double escalay){
	comp=p;
	escalaX = escalax;
	escalaY = escalay;
}

public int print(Graphics g, PageFormat pf, int page){
	if (page > 0) {
		return NO_SUCH_PAGE;
	}
	Graphics2D g2d = (Graphics2D)g;
	g2d.translate(pf.getImageableX(), pf.getImageableY());
	g2d.scale(escalaX, escalaY);
	comp.printAll(g2d);
	return PAGE_EXISTS;
}

}
