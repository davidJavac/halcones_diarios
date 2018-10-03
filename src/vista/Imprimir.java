package vista;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JPanel;

public class Imprimir {

	
	public static void printWork(JPanel p)
	{
	    PrinterJob pj = PrinterJob.getPrinterJob();
	    pj.setJobName(" Opt De Solver Printing ");

	    pj.setPrintable(new Printable()
	    {
	        @Override
	        public int print(Graphics pg, PageFormat pf, int pageNum)
	        {
	            if(pageNum > 0)
	                return Printable.NO_SUCH_PAGE;

	            Graphics2D g2 = (Graphics2D)pg;
	            g2.translate(pf.getImageableX(), pf.getImageableY());
	            p.paintAll(g2);
	            /* I've tried the following commented codes but they don't work */
	            //MainAppPanel.this.printAll(g2);
	            //MainAppPanel.this.print(g2);
	            //MainAppPanel.this.print(g2);
	            return Printable.PAGE_EXISTS;
	        }
	    });
	    if(pj.printDialog() == false)
	        return;
	    try
	    {
	        pj.print();
	    }
	    catch(PrinterException xcp)
	    {
	        xcp.printStackTrace(System.err);
	    }
	}
}
