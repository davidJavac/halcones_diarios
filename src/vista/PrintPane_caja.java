package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;



public class PrintPane_caja extends JPanel implements Printable {

    private JTable table_asistencia;
    private JTable table_altasbajas;
    private JTable table_zonas;
    private JTable table_despacho;
    private JTable table_remuneraciones;
    private JTable table_internos;
    private JTable table_proveedores;
    private JTable table_monedas;
    private JTable table_cierre;
    
    private ArrayList<JTable> ar_tables = new ArrayList<JTable>();
    
    private float[] columnWidthPercentage = {5.0f, 15.0f, 10.0f, 20.0f, 10.0f,20.0f, 5.0f, 5.0f, 5.0f, 5.0f};

    public PrintPane_caja(TableModel model_asistencia, TableModel model_altasbajas,TableModel model_zonas,
    		TableModel model_despacho,
    		TableModel model_remuneraciones, TableModel model_internos, TableModel model_proveedores,
    		TableModel model_monedas, TableModel model_cierre,
    		java.sql.Date fecha) throws IOException {
        setLayout(new GridBagLayout());
        //BufferedImage logo = ImageIO.read(...);
        JLabel header_asistencia = new JLabel();
        JLabel header_altasbajas = new JLabel();
        JLabel header_zonas = new JLabel();
        JLabel header_despacho = new JLabel();
        JLabel header_remuneraciones = new JLabel();
        JLabel header_interno = new JLabel();
        JLabel header_proveedores = new JLabel();
        JLabel header_monedas = new JLabel();
        JLabel header_cierre = new JLabel();
        JLabel header_fecha = new JLabel();
        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        String d = ft.format(fecha);
        header_fecha.setText("Caja diaria        Fecha " + d);
        header_asistencia.setText("Inasistencias");
        header_asistencia.setFont(header_asistencia.getFont().deriveFont(Font.BOLD, 12f));
        header_asistencia.setVerticalAlignment(JLabel.TOP);
        header_altasbajas.setText("Altas/Bajas");
        header_altasbajas.setFont(header_altasbajas.getFont().deriveFont(Font.BOLD, 12f));
        header_altasbajas.setVerticalAlignment(JLabel.TOP);
        header_zonas.setText("Zonas");
        header_zonas.setFont(header_zonas.getFont().deriveFont(Font.BOLD, 12f));
        header_zonas.setVerticalAlignment(JLabel.TOP);
        header_despacho.setText("Despacho");
        header_despacho.setFont(header_despacho.getFont().deriveFont(Font.BOLD, 12f));
        header_despacho.setVerticalAlignment(JLabel.TOP);
        header_remuneraciones.setText("Remuneraciones");
        header_remuneraciones.setFont(header_remuneraciones.getFont().deriveFont(Font.BOLD, 12f));
        header_remuneraciones.setVerticalAlignment(JLabel.TOP);
        header_interno.setText("Movimientos internos");
        header_interno.setFont(header_interno.getFont().deriveFont(Font.BOLD, 12f));
        header_interno.setVerticalAlignment(JLabel.TOP);
        header_proveedores.setText("Proveedores");
        header_proveedores.setFont(header_proveedores.getFont().deriveFont(Font.BOLD, 12f));
        header_proveedores.setVerticalAlignment(JLabel.TOP);
        header_monedas.setText("Monedas");
        header_monedas.setFont(header_monedas.getFont().deriveFont(Font.BOLD, 12f));
        header_monedas.setVerticalAlignment(JLabel.TOP);
        header_cierre.setText("Resumen de cierre");
        header_cierre.setFont(header_cierre.getFont().deriveFont(Font.BOLD, 12f));
        header_cierre.setVerticalAlignment(JLabel.TOP);

        table_asistencia = new JTable(model_asistencia);
        table_asistencia.setShowVerticalLines(false);
        //Color lineColor = new Color(122, 138, 153); //(122, 138, 153) is the RGB color of the lines, at least in my computer/OS/L&F...
        //table_asistencia.setBorder(BorderFactory.createLineBorder(lineColor));
        JTableHeader tableHeader_asistencia = table_asistencia.getTableHeader();
        tableHeader_asistencia.setFont(new Font("Arial", Font.PLAIN, 10));
        ar_tables.add(table_asistencia);
        
        table_altasbajas = new JTable(model_altasbajas);
        table_altasbajas.setShowVerticalLines(false);
        JTableHeader tableHeader_altasbajas = table_altasbajas.getTableHeader();
        tableHeader_altasbajas.setFont(new Font("Arial", Font.PLAIN, 10));
        ar_tables.add(table_altasbajas);

        table_zonas = new JTable(model_zonas);
        table_zonas.setShowVerticalLines(false);
        JTableHeader tableHeader_zonas = table_zonas.getTableHeader();
        tableHeader_zonas.setFont(new Font("Arial", Font.PLAIN, 10));
        ar_tables.add(table_zonas);
        
        table_despacho = new JTable(model_despacho);
        table_despacho.setShowVerticalLines(false);
        JTableHeader tableHeader_despacho = table_despacho.getTableHeader();
        tableHeader_despacho.setFont(new Font("Arial", Font.PLAIN, 10));
        ar_tables.add(table_despacho);

        table_remuneraciones = new JTable(model_remuneraciones);
        table_remuneraciones.setShowVerticalLines(false);
        JTableHeader tableHeader_remuneraciones = table_remuneraciones.getTableHeader();
        tableHeader_remuneraciones.setFont(new Font("Arial", Font.PLAIN, 10));
        ar_tables.add(table_remuneraciones);

        table_internos = new JTable(model_internos);
        table_internos.setShowVerticalLines(false);
        JTableHeader tableHeader_interno = table_internos.getTableHeader();
        tableHeader_interno.setFont(new Font("Arial", Font.PLAIN, 10));
        ar_tables.add(table_internos);

        table_proveedores = new JTable(model_proveedores);
        table_proveedores.setShowVerticalLines(false);
        JTableHeader tableHeader_proveedores = table_proveedores.getTableHeader();
        tableHeader_proveedores.setFont(new Font("Arial", Font.PLAIN, 10));
        ar_tables.add(table_proveedores);

        table_monedas = new JTable(model_monedas);
        table_monedas.setShowVerticalLines(false);
        JTableHeader tableHeader_monedas = table_monedas.getTableHeader();
        tableHeader_monedas.setFont(new Font("Arial", Font.PLAIN, 10));
        ar_tables.add(table_monedas);

        table_cierre = new JTable(model_cierre);
        table_cierre.setShowVerticalLines(false);
        JTableHeader tableHeader_cierre = table_cierre.getTableHeader();
        tableHeader_cierre.setFont(new Font("Arial", Font.PLAIN, 10));
        ar_tables.add(table_cierre);

        DefaultTableCellRenderer cent = new DefaultTableCellRenderer();
        
        cent.setHorizontalAlignment(SwingConstants.CENTER);
        
        for(int i = 0; i < table_asistencia.getColumnCount(); i ++){
        	
        	table_asistencia.getColumnModel().getColumn(i).setCellRenderer(cent);
        	
        }
        for(int i = 0; i < table_altasbajas.getColumnCount(); i ++){
        	
        	table_altasbajas.getColumnModel().getColumn(i).setCellRenderer(cent);
        	
        }
        for(int i = 0; i < table_zonas.getColumnCount(); i ++){
        	
        	table_zonas.getColumnModel().getColumn(i).setCellRenderer(cent);
        	
        }
        for(int i = 0; i < table_despacho.getColumnCount(); i ++){
        	
        	table_despacho.getColumnModel().getColumn(i).setCellRenderer(cent);
        	
        }
        for(int i = 0; i < table_remuneraciones.getColumnCount(); i ++){
        	
        	table_remuneraciones.getColumnModel().getColumn(i).setCellRenderer(cent);
        	
        }
        for(int i = 0; i < table_internos.getColumnCount(); i ++){
        	
        	table_internos.getColumnModel().getColumn(i).setCellRenderer(cent);
        	
        }
        for(int i = 0; i < table_proveedores.getColumnCount(); i ++){
        	
        	table_proveedores.getColumnModel().getColumn(i).setCellRenderer(cent);
        	
        }
        for(int i = 0; i < table_monedas.getColumnCount(); i ++){
        	
        	table_monedas.getColumnModel().getColumn(i).setCellRenderer(cent);
        	
        }
        for(int i = 0; i < table_cierre.getColumnCount(); i ++){
        	
        	table_cierre.getColumnModel().getColumn(i).setCellRenderer(cent);
        	
        }

        GridBagConstraints gbc = new GridBagConstraints();
       
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(header_fecha, gbc);
        
        gbc.gridy++;
        
        add(header_asistencia, gbc);

        gbc.gridy++;
     
        add(tableHeader_asistencia, gbc);
        
        gbc.gridy++; 
        gbc.fill = GridBagConstraints.BOTH;
        
        add(table_asistencia/*new JTable(model)*/, gbc);

        
        gbc.gridy++;
        add(header_altasbajas, gbc);
        
        gbc.gridy++;
        
        add(tableHeader_altasbajas, gbc);
        
        gbc.gridy++; 
        gbc.fill = GridBagConstraints.BOTH;
        
        add(table_altasbajas/*new JTable(model)*/, gbc);
        
        
        gbc.gridy++;
        add(header_zonas, gbc);
        
        gbc.gridy++;
        
        add(tableHeader_zonas, gbc);
        
        gbc.gridy++; 
        gbc.fill = GridBagConstraints.BOTH;
        
        add(table_zonas/*new JTable(model)*/, gbc);
        
        
        gbc.gridy++;
        add(header_despacho, gbc);
        
        gbc.gridy++;
        
        add(tableHeader_despacho, gbc);

        gbc.gridy++; 
        gbc.fill = GridBagConstraints.BOTH;
   
        add(table_despacho/*new JTable(model)*/, gbc);

        
        gbc.gridy++;
        add(header_remuneraciones, gbc);
        
        gbc.gridy++;
        
        add(tableHeader_remuneraciones, gbc);
        
        gbc.gridy++; 
        gbc.fill = GridBagConstraints.BOTH;
        
        add(table_remuneraciones/*new JTable(model)*/, gbc);
       

        gbc.gridy++;
        add(header_interno, gbc);
        
        gbc.gridy++;
        
        add(tableHeader_interno, gbc);
        
        gbc.gridy++; 
        gbc.fill = GridBagConstraints.BOTH;
        
        add(table_internos/*new JTable(model)*/, gbc);
        

        gbc.gridy++;
        add(header_proveedores, gbc);
        
        gbc.gridy++;
        
        add(tableHeader_proveedores, gbc);
        
        gbc.gridy++; 
        gbc.fill = GridBagConstraints.BOTH;
        
        add(table_proveedores/*new JTable(model)*/, gbc);

        
        gbc.gridy++;
        add(header_monedas, gbc);
        
        gbc.gridy++;
        
        add(tableHeader_monedas, gbc);
        
        gbc.gridy++; 
        gbc.fill = GridBagConstraints.BOTH;
        
        add(table_monedas/*new JTable(model)*/, gbc);

        
        gbc.gridy++;
        add(header_cierre, gbc);
        
        gbc.gridy++;
        
        add(tableHeader_cierre, gbc);
        
        gbc.gridy++; 
        gbc.fill = GridBagConstraints.BOTH;
        
        add(table_cierre/*new JTable(model)*/, gbc);
        
        
     
        table_asistencia.setFont(table_asistencia.getFont().deriveFont(Font.PLAIN, 8));
        table_altasbajas.setFont(table_altasbajas.getFont().deriveFont(Font.PLAIN, 8));
        table_zonas.setFont(table_zonas.getFont().deriveFont(Font.PLAIN, 8));
        table_despacho.setFont(table_despacho.getFont().deriveFont(Font.PLAIN, 8));
        table_remuneraciones.setFont(table_remuneraciones.getFont().deriveFont(Font.PLAIN, 8));
        table_internos.setFont(table_internos.getFont().deriveFont(Font.PLAIN, 8));
        table_proveedores.setFont(table_proveedores.getFont().deriveFont(Font.PLAIN, 8));
        table_monedas.setFont(table_monedas.getFont().deriveFont(Font.PLAIN, 8));
        table_cierre.setFont(table_cierre.getFont().deriveFont(Font.PLAIN, 8));
        //this.resizeColumns();
        
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
       // paper.setSize(8.5 * 72, 13.0 * 72);
       //paper.setImageableArea(50, 50, paper.getImageableWidth()*1.1, paper.getImageableHeight()*1.1);
        //paper.setImageableArea(50, 50, paper.getImageableWidth(), paper.getImageableHeight());
        
        int cont = 0;
        
        for(JTable t : ar_tables){
        	
        	cont += t.getRowCount()+2;
        }
        
        
        paper.setImageableArea(50, 50, 530, 720);
       
        pageFormat.setPaper(paper);
        
        double height = pageFormat.getImageableHeight();
        double width = pageFormat.getImageableWidth();

        System.out.println("Page = " + width + "x" + height);
        
        JTableHeader tableHeader = table_asistencia.getTableHeader();
        Dimension size = tableHeader.getPreferredSize();
        tableHeader.setPreferredSize(new Dimension((int) width, size.height));
        tableHeader.setSize(table_asistencia.getPreferredSize());
        size = table_asistencia.getPreferredSize();
        table_asistencia.setPreferredSize(new Dimension((int) width, size.height));
        table_asistencia.setSize(table_asistencia.getPreferredSize());

        
        JTableHeader tableHeader_altasbajas = table_altasbajas.getTableHeader();
        size = tableHeader_altasbajas.getPreferredSize();
        tableHeader_altasbajas.setPreferredSize(new Dimension((int) width, size.height));
        tableHeader_altasbajas.setSize(table_altasbajas.getPreferredSize());    
        size = table_altasbajas.getPreferredSize();
        table_altasbajas.setPreferredSize(new Dimension((int) width, size.height));
        table_altasbajas.setSize(table_altasbajas.getPreferredSize());

        JTableHeader tableHeader_zonas = table_zonas.getTableHeader();
        size = tableHeader_zonas.getPreferredSize();
        tableHeader_zonas.setPreferredSize(new Dimension((int) width, size.height));
        tableHeader_zonas.setSize(table_zonas.getPreferredSize());    
        size = table_zonas.getPreferredSize();
        table_zonas.setPreferredSize(new Dimension((int) width, size.height));
        table_zonas.setSize(table_zonas.getPreferredSize());
        
        
        JTableHeader tableHeader_despacho = table_despacho.getTableHeader();
        size = tableHeader_despacho.getPreferredSize();
        tableHeader_despacho.setPreferredSize(new Dimension((int) width, size.height));
        tableHeader_despacho.setSize(table_despacho.getPreferredSize());
        size = table_despacho.getPreferredSize();
        table_despacho.setPreferredSize(new Dimension((int) width, size.height));
        table_despacho.setSize(table_despacho.getPreferredSize());

        JTableHeader tableHeader_remuneraciones = table_remuneraciones.getTableHeader();
        size = tableHeader_remuneraciones.getPreferredSize();
        tableHeader_remuneraciones.setPreferredSize(new Dimension((int) width, size.height));
        tableHeader_remuneraciones.setSize(table_remuneraciones.getPreferredSize());
        size = table_remuneraciones.getPreferredSize();
        table_remuneraciones.setPreferredSize(new Dimension((int) width, size.height));
        table_remuneraciones.setSize(table_remuneraciones.getPreferredSize());

        JTableHeader tableHeader_internos = table_internos.getTableHeader();
        size = tableHeader_internos.getPreferredSize();
        tableHeader_internos.setPreferredSize(new Dimension((int) width, size.height));
        tableHeader_internos.setSize(table_internos.getPreferredSize());
        size = table_internos.getPreferredSize();
        table_internos.setPreferredSize(new Dimension((int) width, size.height));
        table_internos.setSize(table_internos.getPreferredSize());

        JTableHeader tableHeader_proveedores = table_proveedores.getTableHeader();
        size = tableHeader_proveedores.getPreferredSize();
        tableHeader_proveedores.setPreferredSize(new Dimension((int) width, size.height));
        tableHeader_proveedores.setSize(table_proveedores.getPreferredSize());
        size = table_proveedores.getPreferredSize();
        table_proveedores.setPreferredSize(new Dimension((int) width, size.height));
        table_proveedores.setSize(table_proveedores.getPreferredSize());

        JTableHeader tableHeader_monedas = table_monedas.getTableHeader();
        size = tableHeader_monedas.getPreferredSize();
        tableHeader_monedas.setPreferredSize(new Dimension((int) width, size.height));
        tableHeader_monedas.setSize(table_monedas.getPreferredSize());
        size = table_monedas.getPreferredSize();
        table_monedas.setPreferredSize(new Dimension((int) width, size.height));
        table_monedas.setSize(table_monedas.getPreferredSize());

        JTableHeader tableHeader_cierre = table_cierre.getTableHeader();
        size = tableHeader_cierre.getPreferredSize();
        tableHeader_cierre.setPreferredSize(new Dimension((int) width, size.height));
        tableHeader_cierre.setSize(table_cierre.getPreferredSize());
        size = table_cierre.getPreferredSize();
        table_cierre.setPreferredSize(new Dimension((int) width, size.height));
        table_cierre.setSize(table_cierre.getPreferredSize());

        size = getPreferredSize();
        setSize((int)width, size.height);
        invalidate();
        doLayout();

       // resizeColumns();
        
        table_asistencia.doLayout();
        tableHeader.doLayout();
        table_altasbajas.doLayout();
        tableHeader_altasbajas.doLayout();
        table_zonas.doLayout();
        tableHeader_zonas.doLayout();
        table_despacho.doLayout();
        tableHeader_despacho.doLayout();
        table_remuneraciones.doLayout();
        tableHeader_remuneraciones.doLayout();
        table_internos.doLayout();
        tableHeader_internos.doLayout();
        table_proveedores.doLayout();
        tableHeader_proveedores.doLayout();
        table_monedas.doLayout();
        tableHeader_monedas.doLayout();
        table_cierre.doLayout();
        tableHeader_cierre.doLayout();

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
            System.out.println();
        }
        return result;
    }
    
    
    
    private void resizeColumns() {
	   /* int tW = table.getWidth();
	    TableColumn column;
	    TableColumnModel jTableColumnModel = table.getColumnModel();
	    int cantCols = jTableColumnModel.getColumnCount();
	    for (int i = 0; i < cantCols; i++) {
	        column = jTableColumnModel.getColumn(i);
	        int pWidth = Math.round(columnWidthPercentage[i] * tW);
	        column.setPreferredWidth(pWidth);
	    }*/
	}

}

