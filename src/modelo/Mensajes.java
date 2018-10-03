package modelo;

import java.awt.Component;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Mensajes {

	public static void getMensaje_vacio(){
		
		 JOptionPane.showMessageDialog(null, "No puedes dejar un campo vacio",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_no_entero(){
		
		JOptionPane.showMessageDialog(null, "Todo campo numérico solo puede contener números y no puede"
				+ " exceder el rango 0-100000000",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}

	public static void getMensaje_observaciones(){
		
		JOptionPane.showMessageDialog(null, "La observación debe respetar el rango de 30 a 200 caracteres",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_errorIngresoPlan(Component c){
		
		JOptionPane.showMessageDialog(c, "Existe un error en el ingreso del plan",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_errorIngresoLocalidad(){
		
		JOptionPane.showMessageDialog(null, "No hay una zona seleccionada",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_excede(){
		
		JOptionPane.showMessageDialog(null, "Ningun campo puede exceder los 30 caracteres",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_error_email(){
		
		JOptionPane.showMessageDialog(null, "Error en el ingreso del email",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_error_fecha(){
		
		JOptionPane.showMessageDialog(null, "Error en la fecha ingresada",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	public static void getMensaje_error_fecha_premios(){
		
		JOptionPane.showMessageDialog(null, "La fecha desde solo puede ser un día lunes",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	public static void getMensaje_error_fecha_comision(){
		
		JOptionPane.showMessageDialog(null, "La fecha desde solo puede ser un día sábado",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_relacionVeraz_dni_con(){
		
		JOptionPane.showMessageDialog(null, "Existe relacion del cliente en la tabla de Veraz:"
				+ "Dni conyugue",
				"Error", JOptionPane.WARNING_MESSAGE, null);
	}
	public static void getMensaje_relacionVeraz_dom_part(){
		
		JOptionPane.showMessageDialog(null, "Existe relacion del cliente en la tabla de Veraz:"
				+ "Domicilio particular",
				"Advertencia", JOptionPane.WARNING_MESSAGE, null);
	}
	public static void getMensaje_relacionVeraz_dom_com(){
		
		JOptionPane.showMessageDialog(null, "Existe relacion del cliente en la tabla de Veraz:"
				+ "Domicilio comercial",
				"Advertencia", JOptionPane.WARNING_MESSAGE, null);
	}
	public static void getMensaje_clienteEnVeraz(){
		
		JOptionPane.showMessageDialog(null, "El cliente exite en la tabla de Veraz",
				"Advertencia", JOptionPane.WARNING_MESSAGE, null);
	}
	
	public static void getMensaje_clienteExiste(){
		
		JOptionPane.showMessageDialog(null, "El cliente que desea ingresar ya esta registrado en el sistema.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_clienteNoExiste(){
		
		JOptionPane.showMessageDialog(null, "El DNI ingresado no esta registrado en el sistema.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_empleadoNoExiste(){
		
		JOptionPane.showMessageDialog(null, "El ID ingresado no esta registrado en el sistema.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_pedidoNoExiste(){
		
		JOptionPane.showMessageDialog(null, "El N°pedido ingresado no esta registrado en el sistema.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_usuarioNoExiste(){
		
		JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_contrasenaNoExiste(){
		
		JOptionPane.showMessageDialog(null, "Contraseña incorrecta.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_altaExitoso(){
		
		JOptionPane.showMessageDialog(null, "El cliente ha sido ingresado con exito.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_altaExitosoGenerico(){
		
		JOptionPane.showMessageDialog(null, "Ingreso exitoso.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	public static void getMensaje_retiroRechazado(){
		
		JOptionPane.showMessageDialog(null, "El retiro ha sido rechazado.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_modificacionExitosa(){
		
		JOptionPane.showMessageDialog(null, "Los cambios han sido realizados con exito.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_modificacion_sinExito(){
		
		JOptionPane.showMessageDialog(null, "Error: los cambios no se han realizado",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	public static void getMensaje_cobrador_sinzona(){
		
		JOptionPane.showMessageDialog(null, "Advertencia: existen cobradores sin una zona asignada",
				"Advertencia", JOptionPane.WARNING_MESSAGE, null);
	}
	
	public static int getMensaje_confirmacion_baja(){
		
		return JOptionPane.showConfirmDialog(null, "¿Confirma que desea dar baja al cliente?", "Confirmacion",
				JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
	}
	
	 public static int getMensaje_confirmacion_anulacion(){
		
		return JOptionPane.showConfirmDialog(null, "¿Confirma que desea anular el pedido?", "Confirmacion",
				JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
	}
	 public static int getMensaje_confirmacion_anulacion_retiro(){
		 
		 return JOptionPane.showConfirmDialog(null, "¿Confirma que desea anular el retiro?", "Confirmacion",
				 JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
	 }
	 public static int getMensaje_confirmacion_reasignar_plan(int n_plan){
		 
		 return JOptionPane.showConfirmDialog(null, "¿Confirma que desea reasignar el plan N°" + n_plan +
				 "?", "Confirmacion",
				 JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
	 }
	 public static int getMensaje_confirmacion_rechazar_cambioPlan(int n_cambio){
		 
		 return JOptionPane.showConfirmDialog(null, "¿Confirma que desea rechazar el cambio de plan N°"
		 		+ n_cambio+ "?", "Confirmacion",
				 JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
	 }
	 
	 public static int getMensaje_confirmacion_anulacion_generico(){
			
			return JOptionPane.showConfirmDialog(null, "¿Confirma la anulación?", "Confirmacion",
					JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
		}
	 public static int getMensaje_confirmacion_altaCliente(){
		 
		 return JOptionPane.showConfirmDialog(null, "¿Confirma el alta del cliente?", "Confirmacion",
				 JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
	 }
	 public static int getMensaje_confirmacion_habilitacionCliente(){
		 
		 return JOptionPane.showConfirmDialog(null, "¿Confirma la habilitación del cliente?", "Confirmacion",
				 JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
	 }
	 
	 public static int getMensaje_confirmacion_generico(){
			
			return JOptionPane.showConfirmDialog(null, "¿Confirma el ingreso de los datos?", "Confirmacion",
					JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
		}
	 
	 public static int getMensaje_confirmacion_retiro(){
			
			return JOptionPane.showConfirmDialog(null, "¿Confirma el retiro de los artículos "
					+ "seleccionados?", "Confirmacion",
					JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
		}
	 
	 public static int getMensaje_confirmacion_nuevoPlan(){
			
			return JOptionPane.showConfirmDialog(null, "¿Confirma el nuevo plan de pago?", "Confirmacion",
					JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
		}
	
	public static void getMensaje_bajaExitosa(){
		
		JOptionPane.showMessageDialog(null, "El cliente ha sido dado de baja.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_bajaEmpleadoExitosa(){
		
		JOptionPane.showMessageDialog(null, "El empleado ha sido dado de baja.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_retiroExitoso(){
		
		JOptionPane.showMessageDialog(null, "Los artículos seleccionados fueron retirados del plan.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_bajaExitosa_generico(){
		
		JOptionPane.showMessageDialog(null, "El registro ha sido eliminado.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_anulacionExitosa(){
		
		JOptionPane.showMessageDialog(null, "Anulacion exitosa.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_encriptacionError(){
		
		JOptionPane.showMessageDialog(null, "Error en la encriptación",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	public static void getMensaje_despacho_sinResolucion(){
		
		JOptionPane.showMessageDialog(null, "No puede cerrar la caja del dia porque hay pedidos sin resolucion "
				+ " en el despacho",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	public static void getMensaje_encriptacionExitosa(){
		
		JOptionPane.showMessageDialog(null, "Tabla de prestamos encriptada.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	public static void getMensaje_cumpleaños(String nombre, String fecha){
	
		Image nuev = null;
		
		try {
			 nuev = ImageIO.read(Mensajes.class.getResource("/cumple3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final ImageIcon icon = new ImageIcon(nuev);
		
		JOptionPane.showMessageDialog(null, "Hoy: " + fecha + " es cumpleaños de: "
				+ nombre,
				"Informacion", JOptionPane.INFORMATION_MESSAGE, icon);
	}
	public static void getMensaje_desencriptacionError(){
		
		JOptionPane.showMessageDialog(null, "Error en la desencriptación",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_desencriptacionExitosa(){
		
		JOptionPane.showMessageDialog(null, "Tabla de prestamos desencriptada.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	
	public static void getMensajeImpresion_completa(){
		
		JOptionPane.showMessageDialog(null, "Impresion completa",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	public static void getMensajeImpresion_cancelada(){
		
		JOptionPane.showMessageDialog(null, "Impresion cancelada",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	public static void getMensajeFinalizacionPedido(int n_pedido){
		
		JOptionPane.showMessageDialog(null, "Pedido N° " + n_pedido + " finalizado.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_sinPendientes(){
		
		JOptionPane.showMessageDialog(null, "No hay pedidos pendientes seleccionados.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_sinDespacho(){
		
		JOptionPane.showMessageDialog(null, "No hay pedidos en el despacho.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_sinFilasSeleccionadas(){
		
		JOptionPane.showMessageDialog(null, "No hay filas seleccionadas.",
				"Informacion", JOptionPane.INFORMATION_MESSAGE, null);
	}
	
	public static void getMensaje_fecha_ingreso(){
		
		JOptionPane.showMessageDialog(null, "La fecha de ingreso no corresponde al dia de hoy",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_anulacionError(){
		
		JOptionPane.showMessageDialog(null, "Error en la anulacion",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_noExisteCajaError(){
		
		JOptionPane.showMessageDialog(null, "No hay un registro de caja diaria para la fecha indicada",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_pagoduplicadoError(){
		
		JOptionPane.showMessageDialog(null, "Ya existen ingresos asignados a la fecha indicada. Para realizar cambios "
				+ "puede hacer una consulta por fecha.",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_pago_noregistradoError(){
		
		JOptionPane.showMessageDialog(null, "No existen pagos realizados en la fecha indicada",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	 public static int getMensaje_despachoDuplicado(int n_pedido){
			
			return JOptionPane.showConfirmDialog(null, "Ya existe un despacho asignado al pedido " + n_pedido + 
					" en la fecha asignada ¿Desea editar el estado del mismo?", "Confirmacion",
					JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
		}
	
	public static void getMensaje_importeError(){
		
		JOptionPane.showMessageDialog(null, "Los campos de importe no pueden estar vacios, contener valores negativos,"
		+ " valores no numéricos, ni exceder el limite de 100000",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_bajaError(){
		
		JOptionPane.showMessageDialog(null, "Error en la baja del cliente",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	

	public static void getMensaje_bajaError_generico(){
		
		JOptionPane.showMessageDialog(null, "Error en la baja del registro",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}	
	
	public static void getMensaje_ingresoCajazonaError(){
		
		JOptionPane.showMessageDialog(null, "Error en los ingresos de la caja zona",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_cobranzaRealizadaError(){
		
		JOptionPane.showMessageDialog(null, "No es posible confirmar una entrega antes que la caja, correspondiente a"
				+ " la zona asociada al pedido, halla registrado ingresos del día",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_impresionError(){
		
		JOptionPane.showMessageDialog(null, "No hay pedidos enviados en despacho para imprimir",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}

	public static void getMensaje_modificacionError(){
		
		JOptionPane.showMessageDialog(null, "El dni ingresado ya esta registrado en el sistema",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_altaError(){
		
		JOptionPane.showMessageDialog(null, "Error en el ingreso del cliente",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_repite(){
		
		JOptionPane.showMessageDialog(null, "Registros duplicados",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_altaErrorGenerico(){
		
		JOptionPane.showMessageDialog(null, "Error en el ingreso",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_conexionError(){
		
		JOptionPane.showMessageDialog(null, "Error en la conexion a la base de datos",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static int getMensaje_advertenciacliente(String modulo){
		
		int opcion = JOptionPane.showConfirmDialog(null, "Confirma que desea cerrar el módulo " + modulo + "?", "Advertencia", JOptionPane.YES_NO_OPTION);
		
		return opcion;
	}
	
	public static void getMensaje_limiteArticulos(){
		
		JOptionPane.showMessageDialog(null, "Error: el límite de artículos por pedido es de 5 unidades",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	public static void getMensaje_errorCambioArticulo(){
		
		JOptionPane.showMessageDialog(null, "Existen retiros en los cambios realizados",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	public static void getMensaje_altaMontoError(){
		
		JOptionPane.showMessageDialog(null, "Error: el monto que intenta trasladar ya esta realizado",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_montoError(){
		
		JOptionPane.showMessageDialog(null, "El monto que desea trasladar no esta disponible en el pedido origen",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_agregarLocalidadError(){
		
		JOptionPane.showMessageDialog(null, "El límite de localidades por zona es 4",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void getMensaje_agregarLocalidadError_duplicado(){
		
		JOptionPane.showMessageDialog(null, "La localidad seleccionada ya se encuentra asignada a la zona",
				"Error", JOptionPane.ERROR_MESSAGE, null);
	}
	
}
