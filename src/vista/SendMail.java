package vista;

import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SendMail{

	final static String username = "david.abramovich84@gmail.com";
	final static String password = "mobyrock";
	
public static void enviar(JTable tabla, String asunto, String mensaje, String path_adjunto){
	
	Properties props = new Properties();
	props.put("mail.smtp.auth", true);
	props.put("mail.smtp.starttls.enable", true);
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
	
	Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	});

	//ScheduledExecutorService wait = Executors.newScheduledThreadPool(3);

	Runnable hilos = new Runnable(){
		
		@Override
		public void run() {
	
    	for(int i = 0; i < /*tabla.getSelectedRows().length*/200; i++){
    		
					// TODO Auto-generated method stub
					
					try{
						
						
						System.out.println("dentro de hilo");
						String cliente = "david";//tabla.getModel().getValueAt(i, 1).toString();
			    		
			    	    Message message = new MimeMessage(session);
			    	    message.setFrom(new InternetAddress("david.abramovich84@gmail.com"));
			    	    message.setRecipients(Message.RecipientType.TO,
			    	            InternetAddress.parse("davidledzappa@gmail.com"));
			    	    message.setSubject(asunto);
			    	    //message.setText("Hola David te adjunto un archivo con ofertas");

			    	    MimeBodyPart messageBodyPart = new MimeBodyPart();
			    	    MimeBodyPart textPart = new MimeBodyPart();
			    	    textPart.setContent("Estimado cliente " + cliente + ", " + mensaje, "text/html; charset=utf-8");
			    	    
			    	    
			    	    Multipart multipart = new MimeMultipart();

			    	    //messageBodyPart = new MimeBodyPart();
			    	    String file = "path of file to be attached";
			    	    String fileName = asunto;
			    	    multipart.addBodyPart(textPart);

			    	    DataSource source = new FileDataSource(path_adjunto);
			    	    // messageBodyPart.setText("Hola David te adjunto un archivo con ofertas");
			    	    if(source!=null){
			    	    	
			    	    	messageBodyPart.setDataHandler(new DataHandler(source));
			    	    	messageBodyPart.setFileName(fileName);
			    	    	multipart.addBodyPart(messageBodyPart);
			    	    	
			    	    }

			    	    message.setContent(multipart);

			    	    System.out.println("Sending");
			    	    
			    	    Transport.send(message);
			    	    //t.close();
			    	    System.out.println("Done");
					}
					catch(MessagingException e){
						
						e.printStackTrace();
					}
					
					
				}
    			
    		// wait.scheduleAtFixedRate(hilos, 0, 1, TimeUnit.SECONDS);
    	}
	};
    	(new Thread(hilos)).start();
    		
    	
  }

public static String ventana_path(){
	
	boolean res = false;
	
	JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
    		"Archivo SQL", "sql");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showOpenDialog(null);
    
    if(returnVal == JFileChooser.APPROVE_OPTION) {
    	
    	try {
    		return chooser.getSelectedFile().getAbsolutePath();
    	//res = bp.realizar_backup(chooser.getSelectedFile().getAbsolutePath());
			//res = rt.realizar_restore(chooser.getSelectedFile().getAbsolutePath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
        //if(res) JOptionPane.showMessageDialog(this, "Base de datos guardada exitosamete"); 
        
        //else JOptionPane.showMessageDialog(this, "Error en la copia de base de datos");
    }
    return "";
   				
}

}
