package vista;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class Horario extends JSpinner{

	private static  JSpinner.DateEditor deD;

	
	public Horario(SpinnerDateModel sm, String titulo){
		
		super(sm);
        deD = new JSpinner.DateEditor(this, "HH:mm");
        deD.getTextField().setEditable( false );
        this.setEditor(deD);
        JFormattedTextField jftf =  ((DefaultEditor) deD).getTextField();
        jftf.setFont(new Font("Arial", Font.PLAIN, 18));
        Border bordeD = BorderFactory.createTitledBorder(null, titulo, 
    			TitledBorder.CENTER, TitledBorder.TOP,
    			new Font("Arial",Font.BOLD,14), Color.BLACK);
        jftf.setBorder(bordeD);
      
	}
	
	public void setDisableText(Color c){
		
		JTextField tf1 = ((JSpinner.DefaultEditor) this.getEditor()).getTextField();
        tf1.setDisabledTextColor(c);
	}
	
	public void setHorarioDesde(String h){
		
		String [] horario = new String [2];
		 
		 horario = h.split("-");
		 
		 String [] desde = new String [2];
		 desde = horario[0].split(":");
		 
		 String horaD = desde[0];
		 String minD = desde[1];
		
		 
		 Calendar calD = Calendar.getInstance();
		 calD.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaD));
		 calD.set(Calendar.MINUTE, Integer.parseInt(minD));

		 Date dateD = calD.getTime();
		 this.setValue(dateD);
	}
	
	public void setHorarioHasta(String h){
		
		String [] horario = new String [2];
		 
		 horario = h.split("-");
		 
		 String [] hasta = new String [2];
		 hasta = horario[1].split(":");
		 
		 String horaH = hasta[0];
		 String minH = hasta[1];
		
		 
		 Calendar calH = Calendar.getInstance();
		 calH.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaH));
		 calH.set(Calendar.MINUTE, Integer.parseInt(minH));

		 Date dateH = calH.getTime();
		 this.setValue(dateH);
	}
	
	public static String getHorario(Horario desdeH, Horario hastaH){
		
		String horario_atencion = deD.getFormat().format(desdeH.getValue()) + "-" + 
				deD.getFormat().format(hastaH.getValue());
		
		return horario_atencion;
	}
	
}
