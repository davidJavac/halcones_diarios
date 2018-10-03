package modelo;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import controlador.ControladorArticulo;
import controlador.ControladorCaja;
import controlador.ControladorCobrador;
import controlador.ControladorEmpleado;
import controlador.ControladorPedidos;
import controlador.ControladorVendedor;
import controlador.ControladorVendedor_ph;
import controlador.ControladorZona;
import modelo_dao.Caja_zonaDAO;
import modelo_dao.CambioPlanDAO;
import modelo_dao.ClienteDAO;
import modelo_dao.EmpleadoDAO;
import modelo_dao.Jefe_calleDAO;
import modelo_dao.RetiroDAO;
import modelo_dao.VentaDAO;
import modelo_dao.ZonaDAO;
import modelo_vo.ArticulosVO;
import modelo_vo.Caja_inasistenciaVO;
import modelo_vo.Caja_zonasVO;
import modelo_vo.Cambio_planVO;
import modelo_vo.ClienteVO;
import modelo_vo.CobradorVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.RetirosVO;
import modelo_vo.SueldosVO;
import modelo_vo.VendedorVO;
import modelo_vo.Vendedores_padre_hijoVO;
import modelo_vo.VentasVO;
import modelo_vo.ZonaVO;
import vista.BusquedaEntities;

public class LogicaEmpleado {

	ControladorZona _controladorZona;
	private ControladorEmpleado _controladorEmpleado;
	private ControladorVendedor _controladorVendedor;
	private ControladorVendedor_ph _controladorVendedor_ph = new ControladorVendedor_ph();
	private LogicaVendedor_ph _logica_vendedor_ph = new LogicaVendedor_ph();
	
	private ControladorArticulo _controladorArticulo;
	private ControladorPedidos controladorPedido;
	private ControladorCaja _controladorCaja;
	private ControladorCobrador _controladorCobrador;
	BusquedaEntities busqueda_entities;
	private short numero_short;
	private int numero_int;
	private double numero_double;
	private static final String EMAIL_PATRON = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public String validarBusquedaUsuario(String id){
		
		EmpleadoDAO eDAO = new EmpleadoDAO();

		String empleado = "";
		
		if(validarShort(id)){
			
			try {
				empleado = eDAO .buscarEmpleadoUsuario(numero_short);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return empleado;
		}
		
		return null;
				
	}
	
	public EmpleadoVO validarBusquedaEmpleado(String id){
		
		EmpleadoDAO eDAO = new EmpleadoDAO();
		
		if(validarShort(id)){
			
			try {
				return eDAO .buscarEmpleado(numero_short);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return null;
				
	}
	
	public boolean modificar_nuevoEmpleadoUsuario(ArrayList<JTextField> empleadoTF,ArrayList<JTextField> cobradorTF,
			ArrayList<JTextField> vendedorTF, JComboBox puestoCB, boolean editar){
		
		int i = 0;
		for(JTextField tf : empleadoTF){
			
			if(i == 0){
				
				if(editar){
					
					if(!validarInt(tf.getText())) {
						
						return false;
					}
					if(numero_int < 0 || numero_int > 100000000) return false;
					
				}
			}
			else if(i == 4){
				
				if(!validarDouble(tf.getText())){
					
					return false;
				}
				if(numero_double < 0 || numero_double > 100000) return false;
				
			}
			else if(i == 1 || i == 6){
				
				if(!validarInt(tf.getText())) {
					
					return false;
				}
				if(numero_int < 0 || numero_int > 100000000) return false;
			}
			else if(i == 9 && validar_email(tf.getText())) return false; 
			else if((tf.getText().equals("")) || tf.getText().length() > 30) {
				
				return false;
			}
			
			i++;
		}
		
		if(puestoCB.getSelectedIndex() == 0){
			
			vendedorTF.get(1).setText("0");
			i = 0;
			for(JTextField tf : cobradorTF){
				
				if(i==0){
					
					if(!validarDouble(tf.getText())) return false;
					if(numero_double < 0 || numero_double > 100) return false;
				}
				else if(tf.getText().equals("")|| tf.getText().length() > 30){
					
				
					return false;
				}
												
				i++;
			}
		}
		
		
		else if(puestoCB.getSelectedIndex() == 1){
			
			i = 0;
			String opcion_vendedor_ph = "";
			
			/*for (Enumeration<AbstractButton> buttons = grupo_vendedor.getElements(); buttons.hasMoreElements();){
				
				AbstractButton button = buttons.nextElement();
				if (button.isSelected()) opcion_vendedor_ph = button.getText();
			}*/
					
			for(JTextField tf : vendedorTF){
				
				if(i==0 || i==1){
					
					if(!validarDouble(tf.getText())) return false;
					if(numero_double < 0 || numero_double > 100) return false;
				}
				/*else if(!opcion_vendedor_ph.equals("Ninguna")){
					
					if(!validarInt(tf.getText())){
					
						return false;
					}
					if(numero_int < 0 || numero_int > 1000) return false;
					
				}*/
				i++;
			}	
			
			
		}
		else {
			
			cobradorTF.get(0).setText("0");
			vendedorTF.get(1).setText("0");
		}
		return true;
	}
	
	public int modificarEmpleado(EmpleadoVO empleadoVO, CobradorVO cobradorVO,
			VendedorVO vendedorVO){
		
		EmpleadoDAO eDAO = new EmpleadoDAO();
			
			try {
				return eDAO .modificarEmpleado(empleadoVO, cobradorVO, vendedorVO);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return 0;
	}
	public int modificarEstadoEmpleado(short id,short estado){
		
		EmpleadoDAO eDAO = new EmpleadoDAO();
		
		try {
			return eDAO .modificarEstadoEmpleado(id, estado);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return 0;
	}
	
	public int nuevoEmpleado(EmpleadoVO empleadoVO, CobradorVO cobradorVO,
			VendedorVO vendedorVO){
		
		EmpleadoDAO eDAO = new EmpleadoDAO();
		
		try {
			return eDAO .nuevoEmpleado(empleadoVO, cobradorVO, vendedorVO);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		return 0;
	}
	
	public ArrayList<EmpleadoVO> comprobarFecha_nacimiento(java.sql.Date hoy){
		
		EmpleadoDAO eDAO = new EmpleadoDAO();
		
		try {
			return eDAO.comprobarFecha_nacimiento(hoy);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void validarBusquedaAll(){
		
		EmpleadoDAO eDAO = new EmpleadoDAO();

		busqueda_entities.setTipoBusqueda(15);
		busqueda_entities.limpiar();
		
		ArrayList<Object[]> ar = null;
		try {
			ar = eDAO.buscarEmpleadoAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if( ar!= null){
				
			for(Object[] o: ar){
					
				busqueda_entities.getTabla().addRow(o);
			}
		}
			
		busqueda_entities.setTablaModel();	
		
	}
	
	public void validarBusquedaAll_porPuesto(String puesto){
		
		EmpleadoDAO eDAO = new EmpleadoDAO();

		busqueda_entities.setTipoBusqueda(19);
		busqueda_entities.limpiar();
		
		ArrayList<EmpleadoVO> ar = null;
		try {
			ar = eDAO.buscarEmpleados_porPuesto(puesto);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if( ar!= null){
				
			for(EmpleadoVO eVO: ar){
					
				Object o [] = new Object[2];
				
				o[0] = eVO.getId_usuario();
				o[1] = eVO.getNombre() + " " + eVO.getApellido();
				
				busqueda_entities.getTabla().addRow(o);
			}
		}
			
		busqueda_entities.setTablaModel();	
		
	}
	public ArrayList<EmpleadoVO> BusquedaAll_porPuesto(String puesto){
		
		EmpleadoDAO eDAO = new EmpleadoDAO();
		
		ArrayList<EmpleadoVO> ar = null;
		try {
			ar = eDAO.buscarEmpleados_porPuesto(puesto);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return ar;
		
	}
	
	public ArrayList<Object []> calcularResumen(java.sql.Date fecha){
		
		ArrayList<Object []> ar = new ArrayList<Object []>(); 
		
		ArrayList<ZonaVO> ar_zonas = _controladorZona.buscarZonas();
		
		Caja_zonaDAO czDAO = new Caja_zonaDAO();
		EmpleadoDAO eDAO = new EmpleadoDAO();
		
		CharSequence castDesde = "";
		
		castDesde = fecha.toString();
		
		LocalDate hasta = LocalDate.parse(castDesde);
		
		ArrayList<EmpleadoVO> ar_empleados_cobradores = null;
		try {
			ar_empleados_cobradores = eDAO.buscarEmpleados_porPuesto("cobrador");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		switch(hasta.getDayOfWeek().name()){
			
			case "SATURDAY": hasta = hasta.plusDays(-5);
			break;
			case "FRIDAY": hasta = hasta.plusDays(-4);
			break;
			case "THURSDAY": hasta = hasta.plusDays(-3);
			break;
			case "WEDNESDAY": hasta = hasta.plusDays(-2);
			break;
			case "TUESDAY": hasta = hasta.plusDays(-1);
			break;
		}
		
		java.sql.Date lunes = Date.valueOf(hasta);
		
		if(ar_empleados_cobradores!=null){
			
			for(EmpleadoVO eVO : ar_empleados_cobradores){
				
				float acumulado_mt = 0;
				float acumulado_cobranza = 0;
				float efectividad = 0;
				double premio = 0;
				Object d [] = new Object [8];
				
				ZonaVO zVO = _controladorZona.buscarZona_porId_cobrador(eVO.getId_usuario());
				
					ArrayList<Caja_zonasVO> arcz = null;
					try {
						if(zVO!=null){
							
							arcz = czDAO.caja_zonaEntreFechas(zVO.getId_zona(),
									lunes, fecha);
							
						}
						else Mensajes.getMensaje_cobrador_sinzona();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(arcz!=null){
						
						for(Caja_zonasVO czVO : arcz){
							
							acumulado_mt += czVO.getMonto_ideal();
							acumulado_cobranza += (float) czVO.getIngresos();
						}
						if(acumulado_mt != 0)
							efectividad = acumulado_cobranza * 100 / acumulado_mt;
						
						CobradorVO cobradorVO = _controladorCobrador.buscarCobrador_porID(eVO.getId_usuario());
						
						
						if(efectividad >= 94) 
							premio = cobradorVO.getPremio() * acumulado_cobranza / 100;
					}	
				
					
					//EmpleadoVO eVO = _controladorEmpleado.buscarEmpleado(Integer.toString(zVO.getId_cobrador()));
					
					
					//if(eVO!=null)
					
						d[0] = eVO.getNombre() + " " + eVO.getApellido()+ "(" + eVO.getId_usuario() + ")";
					
					System.out.println(d[0]);
					d[1] = eVO.getPuesto();
					d[2] = eVO.getSalario_semanal();
					
					
					d[3] = premio;
					
					double feriado = 0;
					double adelanto = 0;
					double salario_diario_desc = eVO.getSalario_semanal()/6;
					
					ArrayList<SueldosVO> arS = _controladorCaja.buscarSueldo_entreFechas(lunes, fecha);
					
					if(arS!=null){
						
						for(SueldosVO sVO : arS){
							
							if(sVO.getConcepto().equals("Feriado") &&
									sVO.getId_empleado()==eVO.getId_usuario()){
								
								feriado += sVO.getMonto();
							}
							if(sVO.getConcepto().equals("Vale") &&
									sVO.getId_empleado()==eVO.getId_usuario()){
								
								adelanto += sVO.getMonto();
							}
						}
					}
					
					d[4] = feriado;
					
					ArrayList<Caja_inasistenciaVO> arC = _controladorCaja.
							buscarCaja_inasistencia_entreFechas(lunes, fecha);
					
					int inasistencias = 0;
					
					if(arC!=null){
						
						for(Caja_inasistenciaVO cVO : arC){
							
							if(cVO.getId_empleado()==eVO.getId_usuario() &&
									(cVO.getId_motivo()==2 || cVO.getId_motivo()==3)){
								
								inasistencias++;
							}
						}
					}
					
					salario_diario_desc *= inasistencias;
					
					d[5] = inasistencias;
					
					d[6] = adelanto;
					
					double importe = 
							Math.round(eVO.getSalario_semanal() + premio + feriado - adelanto - salario_diario_desc);
					
					d[7] = importe;
					
					ar.add(d);
			
			}			
			
		}
		
		
		
		CharSequence castDesde_vendedor = "";
		
		castDesde_vendedor = fecha.toString();
		
		LocalDate hasta_vendedor = LocalDate.parse(castDesde_vendedor);
		
		switch(hasta_vendedor.getDayOfWeek().name()){
		
		case "SATURDAY": hasta_vendedor = hasta_vendedor.plusDays(-7);
		break;
		case "FRIDAY": hasta_vendedor = hasta_vendedor.plusDays(-6);
		break;
		case "THURSDAY": hasta_vendedor = hasta_vendedor.plusDays(-5);
		break;
		case "WEDNESDAY": hasta_vendedor = hasta_vendedor.plusDays(-4);
		break;
		case "TUESDAY": hasta_vendedor = hasta_vendedor.plusDays(-3);
		break;
	}
		
		java.sql.Date sabado = Date.valueOf(hasta_vendedor);
		
		VentaDAO vDAO = new VentaDAO();
		RetiroDAO rDAO = new RetiroDAO();
		CambioPlanDAO cDAO = new CambioPlanDAO();
		
		ArrayList<EmpleadoVO> ar_empleados = null;
		try {
			ar_empleados = eDAO.buscarEmpleados_porPuesto("vendedor");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<VentasVO> ar_ventas = null;
		try {
			ar_ventas = vDAO.buscarVentas_entreFechas(sabado, fecha);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<RetirosVO> ar_retiros = null;
		try {
			ar_retiros = rDAO.buscarRetiros_entreFechas(sabado, fecha);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<Cambio_planVO> ar_cambio = null;
		try {
			ar_cambio = cDAO.buscarCambio_entreFechas(sabado, fecha);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ar_empleados!=null){
			
			for(EmpleadoVO empleadoVO : ar_empleados){
				
				Object d [] = new Object [8];
				
				float comision = 0;
				float comision_total = 0;
				float descuento = 0;
				float ventas_articulos = 0;
				float ventas_prestamos = 0;
				int n_retiros = 0;
				int n_ventas = 0;
					
				EmpleadoVO eVO = _controladorEmpleado.buscarEmpleado(Integer.toString(empleadoVO.getId_usuario()));
				
				if(eVO!=null && eVO.getId_usuario()!=33){ //codigo de halcones diarios
					
					d[0] = eVO.getNombre() + " " + eVO.getApellido()+ "(" + eVO.getId_usuario() + ")";
					
					if(ar_ventas!=null){
						
						for(VentasVO vVO : ar_ventas){
							
							boolean venta_ar = false;
							boolean venta_p = false;
							
							if(vVO.getId_vendedor() == empleadoVO.getId_usuario()){
								
								n_ventas++;
								
								PedidosVO pVO = controladorPedido.buscarPedido_porNpedido(vVO.getN_pedido());
								
								ArrayList<Pedido_articuloVO> arPa = controladorPedido
										.buscarArticulos_porPedido(pVO.getN_pedido(), true);
								
								if(arPa!=null){
									
									for(Pedido_articuloVO paVO : arPa){
										
										if(_controladorArticulo.
												buscarArticulo_enPrestamo(paVO.getCodigo_articulo())!=null){
											
											venta_p = true;
										}
										else 
											venta_ar = true;
									}
									
								}
								
								if(venta_p)ventas_prestamos += pVO.getDias() * pVO.getCuota_diaria();
								
								else if(venta_ar) ventas_articulos += pVO.getDias() * pVO.getCuota_diaria();
							}
										
						}
					}
					
					if(ar_retiros!=null){
						
						for(RetirosVO rVO : ar_retiros){
							
							if(rVO.getId_vendedor() == empleadoVO.getId_usuario()){

								n_retiros++;
								
								//PedidosVO pVO = controladorPedido.buscarPedido_porNpedido(rVO.getN_pedido());
									
								ArrayList<Pedido_articuloVO> ar_pa = controladorPedido.
										buscarArticulos_porPedido(rVO.getN_pedido(), false);
								
								if(ar_pa!=null){
									
									for(Pedido_articuloVO pVO : ar_pa){
										
										if(rVO.getId_pedido_articulo()==pVO.getId()){
											
											descuento += pVO.getDias() * pVO.getCuota();
											
										}
									}
								}
								
								/*ArticulosVO artVO = _controladorArticulo.buscarArticulo_porCodigo(rVO.getCodigo());
								
								if(artVO!=null)
								
									descuento += artVO.getDias() * artVO.getCuota();*/
							
							}
										
						}
					}
					
					d[1] = empleadoVO.getPuesto();
					d[2] = empleadoVO.getSalario_semanal();
						
					d[3] = 0;
					d[4] = 0;
					
					ArrayList<Caja_inasistenciaVO> arC = _controladorCaja.
							buscarCaja_inasistencia_entreFechas(sabado, fecha);
					
					int inasistencias = 0;
					
					if(arC!=null){
						
						for(Caja_inasistenciaVO cVO : arC){
							
							if(cVO.getId_empleado()==empleadoVO.getId_usuario()){
								
								inasistencias++;
							}
						}
					}
					
					d[5] = inasistencias;
					
					float adelanto = 0;
					
					ArrayList<SueldosVO> arS = _controladorCaja.buscarSueldo_entreFechas(sabado, fecha);
					
					if(arS!=null){
						
						for(SueldosVO sVO : arS){
							
							if(sVO.getConcepto().equals("Vale") &&
									sVO.getId_empleado()==empleadoVO.getId_usuario()){
								
								adelanto += sVO.getMonto();
								System.out.println("adelanto vendedor" + adelanto);
							}
						}
					}
					
					d[6] = adelanto;
					
 					VendedorVO vendedorVO = _controladorVendedor.buscarVendedor_porID(eVO.getId_usuario());
 					
					_controladorVendedor_ph.setLogicaVendedor_ph(_logica_vendedor_ph);
					
					ArrayList<Vendedores_padre_hijoVO> arVendedor_padres = _controladorVendedor_ph.
							buscarVendedoresPadres_porIdHijo(eVO.getId_usuario());
					
					if(arVendedor_padres != null)
					
						comision = (float) ((vendedorVO.getComision() - 1) * ventas_articulos / 100 + 
								(vendedorVO.getComision_prestamo() - 1) * ventas_prestamos / 100);
					else
						
						comision = (float) (vendedorVO.getComision() * ventas_articulos / 100 + 
								vendedorVO.getComision_prestamo() * ventas_prestamos / 100);
					
					
					ArrayList<Vendedores_padre_hijoVO> arVendedor_hijos = _controladorVendedor_ph.
							buscarVendedoresHijos_porIdPadre(eVO.getId_usuario());
					
					float ventasHijos_articulos = 0;
					float ventasHijos_prestamos = 0;
					
					if(arVendedor_hijos!=null){
						
						for(Vendedores_padre_hijoVO v_hijoVO : arVendedor_hijos){
							
							if(ar_ventas!=null){
								
								for(VentasVO ventasVO : ar_ventas){
									
									
									if(ventasVO.getId_vendedor() == v_hijoVO.getId_hijo()){
										
										boolean venta_hijo_p = false;
										boolean venta_hijo_ar = false;
										
										PedidosVO pVO = controladorPedido.
												buscarPedido_porNpedido(ventasVO.getN_pedido());
										
										ArrayList<Pedido_articuloVO> arPa = controladorPedido
												.buscarArticulos_porPedido(pVO.getN_pedido(), true);
										
										if(arPa!=null){
											
											for(Pedido_articuloVO paVO : arPa){
												
												if(_controladorArticulo.
														buscarArticulo_enPrestamo(paVO.getCodigo_articulo())!=null){
													
													venta_hijo_p = true;
												}
												else 
													venta_hijo_ar = true;
											}
											
										}
										
										if(venta_hijo_p)ventasHijos_prestamos += 
												pVO.getDias() * pVO.getCuota_diaria();
										
										else if(venta_hijo_ar) ventasHijos_articulos += 
												pVO.getDias() * pVO.getCuota_diaria();
										
									}
												
								}
							}
							
							comision += (float) ((1 * ventasHijos_articulos / 100) + 
									(1 * ventasHijos_prestamos / 100));
							
						}
					}
					
					if(ar_cambio!=null){
						
						for(Cambio_planVO cVO : ar_cambio){
							
							if(cVO.getId_vendedor() == empleadoVO.getId_usuario()){
							
								 descuento += cVO.getCredito_anterior() - cVO.getCredito_posterior();
								  
							}
										
						}
					}
					
					float descuento_com = (float) (vendedorVO.getComision() * descuento / 100);
					System.out.println("comision "+  vendedorVO.getComision() + " descuento " + descuento +
							 " descuento com " + descuento_com);
					
					comision_total = Math.round(comision - descuento_com - adelanto);
					
					d[7] = comision_total;
					
					ar.add(d);
				}
					
				
			}
					
		}
		
		ArrayList<EmpleadoVO> ar_administrativos = null;
		try {
			ar_administrativos = eDAO.buscarEmpleados_porPuesto("administrativo");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ar_administrativos!=null){
			
			for(EmpleadoVO eVO : ar_administrativos){
				
				Object d [] = new Object [8];
					
					if(eVO!=null)
					
						d[0] = eVO.getNombre() + " " + eVO.getApellido()+ "(" + eVO.getId_usuario() + ")";
					
					d[1] = eVO.getPuesto();
					d[2] = eVO.getSalario_semanal();
					
					d[3] = 0;
					
					double feriado = 0;
					double adelanto = 0;
					double salario_diario_desc = eVO.getSalario_semanal()/6;
					
					ArrayList<SueldosVO> arS = _controladorCaja.buscarSueldo_entreFechas(lunes, fecha);
					
					if(arS!=null){
						
						for(SueldosVO sVO : arS){
							
							if(sVO.getConcepto().equals("Feriado") &&
									sVO.getId_empleado()==eVO.getId_usuario()){
								
								feriado += sVO.getMonto();
							}
							if(sVO.getConcepto().equals("Vale") &&
									sVO.getId_empleado()==eVO.getId_usuario()){
								
								adelanto += sVO.getMonto();
							}
						}
					}
					
					d[4] = feriado;
					
					ArrayList<Caja_inasistenciaVO> arC = _controladorCaja.
							buscarCaja_inasistencia_entreFechas(lunes, fecha);
					
					int inasistencias = 0;
					
					if(arC!=null){
						
						for(Caja_inasistenciaVO cVO : arC){
							
							if(cVO.getId_empleado()==eVO.getId_usuario()){
								
								inasistencias++;
							}
						}
					}
					
					salario_diario_desc *= inasistencias;
					
					d[5] = inasistencias;
					
					d[6] = adelanto;
					
					double importe = Math.round(eVO.getSalario_semanal() + feriado - adelanto - salario_diario_desc);
					
					d[7] = importe;
					
					ar.add(d);
				}
			}
		
		ArrayList<EmpleadoVO> ar_gestor = null;
		try {
			ar_gestor = eDAO.buscarEmpleados_porPuesto("gestor");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ar_gestor!=null){
			
			for(EmpleadoVO eVO : ar_gestor){
				
				Object d [] = new Object [8];
					
					if(eVO!=null)
					
						d[0] = eVO.getNombre() + " " + eVO.getApellido()+ "(" + eVO.getId_usuario() + ")";
					
					d[1] = eVO.getPuesto();
					d[2] = eVO.getSalario_semanal();
					
					d[3] = 0;
					
					double feriado = 0;
					double adelanto = 0;
					double salario_diario_desc = eVO.getSalario_semanal()/6;
					
					ArrayList<SueldosVO> arS = _controladorCaja.buscarSueldo_entreFechas(lunes, fecha);
					
					if(arS!=null){
						
						for(SueldosVO sVO : arS){
							
							if(sVO.getConcepto().equals("Feriado") &&
									sVO.getId_empleado()==eVO.getId_usuario()){
								
								feriado += sVO.getMonto();
							}
							if(sVO.getConcepto().equals("Vale") &&
									sVO.getId_empleado()==eVO.getId_usuario()){
								
								adelanto += sVO.getMonto();
							}
						}
					}
					
					d[4] = feriado;
					
					ArrayList<Caja_inasistenciaVO> arC = _controladorCaja.
							buscarCaja_inasistencia_entreFechas(lunes, fecha);
					
					int inasistencias = 0;
					
					if(arC!=null){
						
						for(Caja_inasistenciaVO cVO : arC){
							
							if(cVO.getId_empleado()==eVO.getId_usuario()){
								
								inasistencias++;
							}
						}
					}
					
					salario_diario_desc *= inasistencias;
					
					d[5] = inasistencias;
					
					d[6] = adelanto;
					
					double importe = Math.round(eVO.getSalario_semanal() + feriado - adelanto - salario_diario_desc);
					
					d[7] = importe;
					
					ar.add(d);
				}
			}
		
		ArrayList<EmpleadoVO> ar_jefecalle = null;
		try {
			ar_jefecalle = eDAO.buscarEmpleados_porPuesto("jefe_calle");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ar_jefecalle!=null){
			
			for(EmpleadoVO eVO : ar_jefecalle){
				
				Object d [] = new Object [8];
					
					if(eVO!=null)
					
						d[0] = eVO.getNombre() + " " + eVO.getApellido()+ "(" + eVO.getId_usuario() + ")";
					
					d[1] = eVO.getPuesto();
					d[2] = eVO.getSalario_semanal();
					
					d[3] = 0;
					
					double feriado = 0;
					double adelanto = 0;
					double salario_diario_desc = eVO.getSalario_semanal()/6;
					
					ArrayList<SueldosVO> arS = _controladorCaja.buscarSueldo_entreFechas(lunes, fecha);
					
					if(arS!=null){
						
						for(SueldosVO sVO : arS){
							
							if(sVO.getConcepto().equals("Feriado") &&
									sVO.getId_empleado()==eVO.getId_usuario()){
								
								feriado += sVO.getMonto();
							}
							if(sVO.getConcepto().equals("Vale") &&
									sVO.getId_empleado()==eVO.getId_usuario()){
								
								adelanto += sVO.getMonto();
							}
						}
					}
					
					d[4] = feriado;
					
					ArrayList<Caja_inasistenciaVO> arC = _controladorCaja.
							buscarCaja_inasistencia_entreFechas(lunes, fecha);
					
					int inasistencias = 0;
					
					if(arC!=null){
						
						for(Caja_inasistenciaVO cVO : arC){
							
							if(cVO.getId_empleado()==eVO.getId_usuario()){
								
								inasistencias++;
							}
						}
					}
					
					salario_diario_desc *= inasistencias;
					
					d[5] = inasistencias;
					
					d[6] = adelanto;
					
					double importe = Math.round(eVO.getSalario_semanal() + feriado - adelanto - salario_diario_desc);
					
					d[7] = importe;
					
					ar.add(d);
				}
			}
		
		return ar;
	}
	
	public boolean validarInt(String val_string){
		
		try{
			
			numero_int = Integer.parseInt(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public boolean validarShort(String val_string){
		
		try{
			
			numero_short = Short.parseShort(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public boolean validarDouble(String val_string){
		
		try{
			
			numero_double = Double.parseDouble(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public boolean validar_email(String email){
		
		boolean error = false;
		
		Pattern p = Pattern.compile(EMAIL_PATRON);
		Matcher m = p.matcher(email);
				
		if(!m.matches()) error = true;
		
		return error;
	}
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
		
	}
	
	public void setControladorZona(ControladorZona _controladorZona){
		
		this._controladorZona = _controladorZona ;
		
	}
	public void setControladorEmpleado(ControladorEmpleado _controladorEmpleado){
		
		this._controladorEmpleado = _controladorEmpleado ;
		
	}
	public void setControladorArticulo(ControladorArticulo _controladorArticulo){
		
		this._controladorArticulo = _controladorArticulo ;
		
	}
	public void setControladorPedidos(ControladorPedidos controladorPedido){
		
		this.controladorPedido = controladorPedido ;
		
	}
	
	public void setControladorCobrador(ControladorCobrador _controladorCobrador){
		
		this._controladorCobrador = _controladorCobrador ;
		
	}
	public void setControladorVendedor(ControladorVendedor _controladorVendedor){
		
		this._controladorVendedor = _controladorVendedor ;
		
	}
	public void setControladorCaja(ControladorCaja _controladorCaja){
		
		this._controladorCaja = _controladorCaja ;
		
	}
}
