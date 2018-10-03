package modelo_vo;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.Arrays;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlador.Principal;

//import Main.MenuPrincipal;

public class Backup {

	static InputStream ins;
	
	static BufferedReader bf;
	
	public static boolean realizar_backup(String path) {
	    try {

	       //forma de llevar backup a path de proyecto
	        /*CodeSource codeSource = Principal.class.getProtectionDomain().getCodeSource();
	        File jarFile = null;
			try {
				jarFile = new File(codeSource.getLocation().toURI().getPath());
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        String jarDir = jarFile.getParentFile().getPath();
	        
	        String folderPath = jarDir + "\\backup";

	        //crea carpeta
	        File f1 = new File(folderPath);
	        f1.mkdir();*/
	              
	        //forma de realizar backup mediante jfilechooser
	        String savePath = path;
	        
	        String dbName = "halcones_diarios_a";
	        String dbUser = "hdEnterprise";
	        String dbPass = "h15diarioStart";
	        /*String dbName = "halcones_diarios_a";
	        String dbUser = "root";
	        String dbPass = "";*/

	        Map<String, String> env = System.getenv();
	        final String LOCATION = env.get("mysql");
	        
	        //comando cmd
//        		String executeCmd =		"C:\\wamp64\\bin\\mysql\\mysql5.7.19\\bin\\mysqldump "
        			//	String executeCmd =		"mysqldump "
        				//+ "-h localhost -P 3306 -u "
        				String executeCmd =	 "mysqldump -h 192.168.0.100 -P 3306 -u " 
	        	
	        + dbUser + " -p" + dbPass + " " + dbName + " --routines";
        		
        				
        				
			try {
				Process runtimeProcess = null;
				
				PrintWriter p = null;
				File bp = new File(savePath);
				
				FileWriter fw = new FileWriter(bp);
				
				runtimeProcess = Runtime.getRuntime().
						//exec(new String [] {"cmd.exe", "/c", executeCmd});
				exec(executeCmd);
				
				ins = runtimeProcess.getInputStream();
		        
		        bf = new BufferedReader(new InputStreamReader(ins));
		        
		        String linea;
		        
		        
		        p = new PrintWriter(fw);
		     
		        
		        while((linea = bf.readLine()) != null){
		        	
		        	p.println(linea + "\n");
		        	System.out.println(linea);
		        }
		        
		        ins.close();
		        
		        bf.close();
		        
		        p.close();
		        
		        int processComplete = runtimeProcess.waitFor();
		        
		        
		        //si back se carga correctamente processComplete = 0
		        if (processComplete == 0) 
		        	
		        	return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	            
	        	
	    } catch (InterruptedException ex) {
	        JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
	    }
	    
	    return false;
	}
	
}
