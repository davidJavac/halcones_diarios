package vista;
//package chuidiang.ejemplos.JScrollPane; 

import javax.swing.*; 

public class PruebaScroll {

	/* 
	 * PruebaJScrollPane.java 
	 * 
	 * Created on 15 de abril de 2005, 17:26 
	 */

	     
	    /** Creates a new instance of PruebaJScrollPane */ 
	    public PruebaScroll() { 
	         
	        // La ventana 
	        JFrame ventana = new JFrame("Imagen"); 
	         
	        // El panel de scroll 
	        JScrollPane scroll = new JScrollPane(); 
	         
	        // La etiqueta. 
	        JLabel etiqueta = new JLabel(); 
	         
	        // Se carga la imagen, con path absoluto para evitar problemas y debe 
	        // ser un gif. 
	        Icon imagen = new ImageIcon ( 
	            "C:/Users/david/Desktop/fondo_inicio2.jpg"); 
	         
	        // Se mete la imagen en el label 
	        etiqueta.setIcon (imagen); 
	         
	        // Se mete el scroll en la ventana 
	        ventana.getContentPane().add(scroll); 
	         
	        // Se mete el label en el scroll 
	        scroll.setViewportView(etiqueta); 
	         
	        // Y se visualiza todo. 
	        ventana.pack(); 
	        ventana.setVisible(true); 
	    } 
	     
	    /** 
	     * Programa principal. Instancia un PruebaJScrollPane 
	     * @param args the command line arguments 
	     */ 
	    public static void main(String[] args) { 
	        new PruebaScroll(); 
	    } 
	     
	}

