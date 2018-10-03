package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JToolTip;

public class Barra_herramientasPedidos extends JPanel{

	private VistaBuscarPedidos_porClientes vpc;
	
	private JButton guardar/* = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };*/;
	private JButton modificar/* = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };*/;
	private JButton anular;
	private JButton cancelar/* = new JButton()*/;
	private JButton salir/* = new JButton()*/;
	private JButton ref_in/* = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };*/;
	private JButton ref_ex/* = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };*/;
	private JButton monto_trasladado/* = new JButton(){
        //override the JButtons createToolTip method
        @Override
        public JToolTip createToolTip() {
            return (new CustomJToolTip(this));
        }
    };*/;
	
	private JButton observaciones;
	private JButton pagos;
	private JButton retiros;
	private JButton cambio_plan;
	
	public JButton getGuardarB(){
		
		return guardar;
	}
	
	public JButton getModificarB(){
		
		return modificar;
	}

	public JButton getCancelarB(){
	
		return cancelar;
	}

	public JButton getSalirB(){
	
		return salir;
	}

	public JButton getRef_inB(){
	
		return ref_in;
	}

	public JButton getRef_exB(){
	
		return ref_ex;
	}
	
	public JButton getMonto_tB(){
		
		return monto_trasladado;
	}
	
	public JButton getObservaciones(){
		
		return observaciones;
	}
	
	public JButton getPagos(){
		
		return pagos;
	}
	
	public JButton getRetiros(){
		
		return retiros;
	}
	
	public JButton getCambio_plan(){
		
		return cambio_plan;
	}
	
	public void setVistaBuscarPedidos_porClientes(VistaBuscarPedidos_porClientes vpc){
		
		this.vpc = vpc;
	}
	
	public Barra_herramientasPedidos(JButton guardar, JButton modificar, JButton salir, JButton ref_in,
			JButton ref_ex, JButton cancelar, JButton anular, JButton monto_trasladado, JButton observaciones,
			JButton pagos, JButton retiros, JButton cambio_plan) {
		super(new BorderLayout());

		this.guardar = guardar;
		this.modificar = modificar;
		this.salir = salir;
		this.ref_in = ref_in;
		this.ref_ex = ref_ex;
		this.cancelar = cancelar;
		this.anular = anular;
		this.monto_trasladado = monto_trasladado;
		this.observaciones = observaciones;
		this.pagos = pagos;
		this.retiros = retiros;
		this.cambio_plan = cambio_plan;
		
		JToolBar barra = new JToolBar();
		
		JScrollPane src = new JScrollPane();
		
		agrega_botones(barra);

		//setPreferredSize(new Dimension(1000, 130));
		add(barra, BorderLayout.PAGE_START);
		
		add(src, BorderLayout.CENTER);
	}
	
	public void habilitaBotones(boolean guardar, boolean modificar, boolean salir, boolean ref_in,
			boolean ref_ex, boolean cancelar, boolean anular, boolean monto_trasladado, boolean observaciones,
	boolean pagos, boolean retiros, boolean cambio_plan) {

		this.guardar.setEnabled(guardar);
		this.modificar.setEnabled(modificar);
		this.salir.setEnabled(salir);
		this.ref_in.setEnabled(ref_in);
		this.ref_ex.setEnabled(ref_ex);
		this.cancelar.setEnabled(cancelar);
		this.anular.setEnabled(anular);
		this.monto_trasladado.setEnabled(monto_trasladado);
		this.observaciones.setEnabled(observaciones);
		this.pagos.setEnabled(pagos);
		this.retiros.setEnabled(retiros);
		this.cambio_plan.setEnabled(cambio_plan);
	}
	
	protected void agrega_botones(JToolBar barra) {
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/save1.png"));
		    
		    guardar.setIcon(new ImageIcon(nuev));
		    guardar.setToolTipText("Guardar");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/edit1.png"));
		    
		    modificar.setIcon(new ImageIcon(nuev));
		    modificar.setToolTipText("Editar");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
	
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/ref_in2.png"));
		    
		    ref_in.setIcon(new ImageIcon(nuev));
		    ref_in.setToolTipText("Establecer refinanciación interna");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/ref7.png"));
		    
		    ref_ex.setIcon(new ImageIcon(nuev));
		    ref_ex.setToolTipText("Establecer refinanciación");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/new_user2.png"));
		    
		    //cancelar.setIcon(new ImageIcon(nuev));
		    cancelar.setText("Cancelar");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/close_red.png"));
		    
		    anular.setIcon(new ImageIcon(nuev));
		    anular.setToolTipText("Anular pedido");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/mt.png"));
		    
		    monto_trasladado.setIcon(new ImageIcon(nuev));
		    monto_trasladado.setToolTipText("Trasladar monto");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/ojo.png"));
		    
		    observaciones.setIcon(new ImageIcon(nuev));
		    observaciones.setToolTipText("Observaciones");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/seguimiento_pagos.png"));
		    
		    pagos.setIcon(new ImageIcon(nuev));
		    pagos.setToolTipText("Seguimiento de pagos");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/return3.png"));
		    
		    retiros.setIcon(new ImageIcon(nuev));
		    retiros.setToolTipText("Retiro");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/plan2.png"));
		    
		    cambio_plan.setIcon(new ImageIcon(nuev));
		    cambio_plan.setToolTipText("Cambio de plan");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		
		try {
			  
		    Image nuev = ImageIO.read(getClass().getResource("/new_user2.png"));
		    
		    //salir.setIcon(new ImageIcon(nuev));
		    salir.setText("Salir");
		    
		  } catch (Exception ex) {
			  
		    System.out.println(ex);
		    
		  }
		

		
	    barra.add(guardar);
	    barra.add(modificar);
	    barra.add(ref_in);
	    barra.add(ref_ex);
	    barra.add(monto_trasladado);
	    barra.add(observaciones);
	    barra.add(pagos);
	    barra.add(anular);
	    barra.add(retiros);
	    barra.add(cambio_plan);
	    barra.add(cancelar);    
	    
	}

	
	
	class CustomJToolTip extends JToolTip {

	    public CustomJToolTip(JComponent component) {
	        super();
	        setComponent(component);
	        setBackground(Color.white);
	        setForeground(Color.BLACK);
	    }
	}
	
}
