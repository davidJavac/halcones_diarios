package modelo_vo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlador.Principal;

//import Main.MenuPrincipal;

public class Restore {

	
	
	static BufferedReader bf;
	
	static String dbName = "halcones_diarios_new";
	String dbUser = "hdEnterprise";
	String dbPass = "h15diarioStart";
	public static boolean realizar_restore(String path) {
	    try {

	       //forma de llevar backup a path de proyecto
	        CodeSource codeSource = Principal.class.getProtectionDomain().getCodeSource();
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
	        f1.mkdir();
	              
	        //forma de realizar backup mediante jfilechooser
	        String savePath = path;
	        

	        //comando cmd
        /*		String executeCmd =		"C:\\wamp64\\bin\\mysql\\mysql5.7.19\\bin\\mysqldump "
	        		+ "-h 192.168.1.101 -P 3306 -u " +
	        	
	        dbUser + " -p" + dbPass + " " + dbName;*/
	        		/*String  [] executeCmd = new String []{"C:\\wamp64\\bin\\mysql\\mysql5.7.19\\bin\\mysql "
	        + "-u root -p " + dbName + " < " + savePath };*/
	        		//C:\\wamp64\\bin\\mysql\\mysql5.7.19\\bin\\
	        		String []executeCmd = {"C:\\wamp64\\bin\\mysql\\mysql5.7.19\\bin\\mysql "
	       				+ "-u root -p -h localhost halcones_diarios_new < " + savePath};
	        		
	        		
	        		//String  [] executeCmd = new String []{"C:\\wamp64\\bin\\mysql\\mysql5.7.19\\bin\\mysql"};
	        		/*String executeCmd =		"C:\\wamp64\\bin\\mysql\\mysql5.7.19\\bin\\mysqldump "
	        				+ "-uroot - p halcones_diarios";*/
	        				
	        
	        		
	        		
	       /* String executeCmd = "mysqldump -uroot" + dbUser + " -p" +  
	        		" --database halcones_diarios -r " + savePath;*/
	        /*String s = new File("mysqldump.exe").getAbsolutePath();
	        
	        String executeCmd = "s "
	        		+ " --user=" +
	        dbUser + " --password=" + dbPass + " " + dbName;*/
	     
	        
	        		

	       
	        Process runtimeProcess = null;
	        
	        PrintWriter p = null;
	        
			try {
				//FileInputStream fis = new FileInputStream(savePath);
				
				runtimeProcess = Runtime.getRuntime().exec(executeCmd);
				
				/*OutputStream os = runtimeProcess.getOutputStream();
		        
		        String linea;
		        
		        byte [] buffer = new byte[10000000];
		        
		        int leido = fis.read(buffer);
		 
		        while(leido > 0){
		        	
		        	os.write(buffer, 0, leido);
		        	leido = fis.read(buffer);
		        }
		        
		        os.flush();
		        os.close();
		        
		        fis.close();*/
		      
		        
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int processComplete = runtimeProcess.waitFor();
	        
			
	        //si back se carga correctamente processComplete = 0
	       if (processComplete == 0) 

	        	return true;
	            
	        	
	    } catch (InterruptedException ex) {
	        JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
	    }
	    
	    return false;
	}
	
	public boolean restoreDatabase(String filepath) throws Exception {
	    String comando = "C:\\wamp64\\bin\\mysql\\mysql5.7.19\\bin\\mysql " + dbName + " --host=localhost --port=3306" 
	            + " --user=root --password= "
	            + " < " + filepath;
	    File f = new File("restore.bat");
	    FileOutputStream fos = new FileOutputStream(f);
	    fos.write(comando.getBytes());
	    fos.close();
	    Process run = Runtime.getRuntime().exec("cmd /C start restore.bat ");
	    
	    int processComplete = run.waitFor();
	    
	    
	    //si back se carga correctamente processComplete = 0
	    if (processComplete == 0) 
	    	
	    	return true;
	 
	    else return false;

	}
	
}

