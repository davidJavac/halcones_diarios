package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class TestPrinting {

    public static void printComponent(JComponent comp, boolean fill) throws PrinterException {
        PrinterJob pjob = PrinterJob.getPrinterJob();
       /* PageFormat pf = pjob.defaultPage();
        pf.setOrientation(PageFormat.LANDSCAPE);*/

       /* Paper paper = new Paper();
      
        paper.setSize(8.3 * 72, 11.7 * 72);
        paper.setImageableArea(50, 50, 500, 500);
        
        PageFormat pf = new PageFormat();
        pf.setPaper(paper);
        //pf.setOrientation(PageFormat.LANDSCAPE);
        pf.setOrientation(PageFormat.PORTRAIT);
        
        PageFormat postformat = pjob.pageDialog(pf);
        if (pf != postformat) {*/
            //Set print component
            pjob.setPrintable(new Imprime(comp, fill));
            if (pjob.printDialog()) {
                pjob.print();
            }
            
            
      //  }    
    }
    

    public static void printComponentToFile(Component comp, boolean fill) throws PrinterException {    
        Paper paper = new Paper();
        //paper.setSize(8.3 * 72, 11.7 * 72);
        //paper.setImageableArea(18, 18, 595, 783);
        paper.setSize(8.3 * 72, 11.7 * 72);
        paper.setImageableArea(50, 50, 500, 500);
        
        PageFormat pf = new PageFormat();
        pf.setPaper(paper);
        //pf.setOrientation(PageFormat.LANDSCAPE);
        pf.setOrientation(PageFormat.PORTRAIT);
        
        BufferedImage img = new BufferedImage(
                        (int) Math.round(pf.getWidth()),
                        (int) Math.round(pf.getHeight()),
                        BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fill(new Rectangle(0, 0, img.getWidth(), img.getHeight()));
        ComponentPrinter cp = new ComponentPrinter(comp, fill);
        try {
            cp.print(g2d, pf, 0);
        } finally {
            g2d.dispose();
        }

        try {
            ImageIO.write(img, "png", new File("Page10-" + (fill ? "Filled" : "") + ".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }        
    }
    
    public static class Imprime implements Printable {

        private Component comp;
        private boolean fill;

        public Imprime(Component comp, boolean fill) {
            this.comp = comp;
            this.fill = fill;
        }
        
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
       	 
            if (pageIndex == 0)
              return Printable.NO_SUCH_PAGE;
         
            Graphics2D g2d = (Graphics2D)graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
         
            // set dimension to scale panel to print
            g2d.scale( 0.70, 0.70);
         
            comp.printAll(graphics);
         
            return Printable.PAGE_EXISTS;
        }
    }
    
    public static class ComponentPrinter implements Printable {

        private Component comp;
        private boolean fill;

        public ComponentPrinter(Component comp, boolean fill) {
            this.comp = comp;
            this.fill = fill;
        }
        
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
       	 
            if (pageIndex == 0)
              return NO_SUCH_PAGE;
         
            Graphics2D g2d = (Graphics2D)graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
         
            // set dimension to scale panel to print
            g2d.scale( 0.70, 0.70);
         
            comp.printAll(graphics);
         
            return PAGE_EXISTS;
        }
       /* public int print(Graphics g, PageFormat format, int page_index) throws PrinterException {

            if (page_index > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2 = (Graphics2D) g;
            g2.translate(format.getImageableX(), format.getImageableY());

            double width = (int) Math.floor(format.getImageableWidth());
            double height = (int) Math.floor(format.getImageableHeight());

            if (!fill) {

                width = Math.min(width, comp.getPreferredSize().width);
                height = Math.min(height, comp.getPreferredSize().height);

            }

            comp.setBounds(0, 0, (int) Math.floor(width), (int) Math.floor(height));
            if (comp.getParent() == null) {
                comp.addNotify();
            }
            comp.validate();
            comp.doLayout();
            comp.printAll(g2);
            if (comp.getParent() != null) {
                comp.removeNotify();
            }

            return Printable.PAGE_EXISTS;
        }*/

    }

}