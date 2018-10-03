package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class PrintPanePagos  extends JPanel implements Printable {

	 private JTable table;
	    
	    private float[] columnWidthPercentage =  {10.0f,45.0f, 15.0f, 10.0f, 10.0f,10.0f};

	    public PrintPanePagos(TableModel model, java.sql.Date fecha) throws IOException {
	        setLayout(new GridBagLayout());
	        //BufferedImage logo = ImageIO.read(...);
	        JLabel header = new JLabel();
	        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
	        String d = ft.format(fecha);
	        header.setText("Seguimiento de pagos                     Fecha: " + d);
	        header.setFont(header.getFont().deriveFont(Font.BOLD, 14f));
	        header.setVerticalAlignment(JLabel.TOP);

	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridx = 0;
	        gbc.gridy = 0;
	        gbc.weightx = 1;
	        gbc.gridwidth = GridBagConstraints.REMAINDER;
	        gbc.fill = GridBagConstraints.HORIZONTAL;

	        add(header, gbc);

	        table = new JTable(model);
	        JTableHeader tableHeader = table.getTableHeader();
	        tableHeader.setFont(new Font("Arial", Font.PLAIN, 10));
	        gbc.gridy++;
	     
	        add(tableHeader, gbc);

	        gbc.gridy++; 
	        gbc.fill = GridBagConstraints.BOTH;
	             
	        DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
			
			cent.setHorizontalAlignment(SwingConstants.CENTER);
			
			for(int i = 0; i < table.getColumnCount(); i ++)
				
				table.getColumnModel().getColumn(i).setCellRenderer(cent);
	        
	        table.setFont(table.getFont().deriveFont(Font.PLAIN, 8));
	        //this.resizeColumns();
	        
	        add(table/*new JTable(model)*/, gbc);
	        setBackground(Color.WHITE);
	        this.setFont(new Font("Arial", Font.PLAIN, 8));
	    }

	    private int lastPage = 0;
	    private double yOffset;

	    @Override
	    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
	        int result = NO_SUCH_PAGE;

	        String name = "I be mighty!";
	        String page = Integer.toString(pageIndex);

	        Paper paper = new Paper();
		      
	        paper.setSize(8.3 * 72, 11.7 * 72);
	        paper.setImageableArea(50, 50, 1000, 700);
	       
	        pageFormat.setPaper(paper);
	        
	        double height = pageFormat.getImageableHeight();
	        double width = pageFormat.getImageableWidth();

	        System.out.println("Page = " + width + "x" + height);

	        JTableHeader tableHeader = table.getTableHeader();
	        Dimension size = tableHeader.getPreferredSize();
	        tableHeader.setPreferredSize(new Dimension((int) width, size.height));
	        tableHeader.setSize(table.getPreferredSize());

	        size = table.getPreferredSize();
	        table.setPreferredSize(new Dimension((int) width, size.height));
	        table.setSize(table.getPreferredSize());
	       

	        size = getPreferredSize();
	        setSize((int)width, size.height);
	        invalidate();
	        doLayout();

	        resizeColumns();
	        
	        table.doLayout();
	        tableHeader.doLayout();

	        if (lastPage != pageIndex) {
	            lastPage = pageIndex;
	            yOffset = height * pageIndex;
	            if (yOffset > getHeight()) {
	                yOffset = -1;
	            }
	        }

	        if (yOffset >= 0) {
	            Graphics2D g2d = (Graphics2D) graphics.create();

	            g2d.translate((int) pageFormat.getImageableX(),
	                    (int) pageFormat.getImageableY());

	            g2d.translate(0, -yOffset);
	            printAll(g2d);

	            g2d.dispose();

	            result = PAGE_EXISTS;
	            System.out.println("Print page " + pageIndex);
	        }
	        return result;
	    }
	    
	    
	    
	    private void resizeColumns() {
		    int tW = table.getWidth();
		    TableColumn column;
		    TableColumnModel jTableColumnModel = table.getColumnModel();
		    int cantCols = jTableColumnModel.getColumnCount();
		    for (int i = 0; i < cantCols; i++) {
		        column = jTableColumnModel.getColumn(i);
		        int pWidth = Math.round(columnWidthPercentage[i] * tW);
		        column.setPreferredWidth(pWidth);
		    }
		}
	
}

