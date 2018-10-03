package modelo;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JTextField;

import controlador.ControladorArticulo;
import controlador.ControladorCajaZona;
import controlador.ControladorCobrador;
import controlador.ControladorEmpleado;
import controlador.ControladorPedidos;
import controlador.ControladorUsuario;
import controlador.ControladorVendedor;
import controlador.ControladorVendedor_ph;
import controlador.ControladorVentas;
import controlador.ControladorZona;
import modelo_dao.ArticuloDAO;
import modelo_dao.Caja_zonaDAO;
import modelo_dao.CambioPlanDAO;
import modelo_dao.EmpleadoDAO;
import modelo_dao.RetiroDAO;
import modelo_dao.VentaDAO;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Caja_zonasVO;
import modelo_vo.Cambio_planVO;
import modelo_vo.CobradorVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.Motivo_generalVO;
import modelo_vo.PedidosVO;
import modelo_vo.RetirosVO;
import modelo_vo.UsuariosVO;
import modelo_vo.VendedorVO;
import modelo_vo.Vendedores_padre_hijoVO;
import modelo_vo.VentasVO;
import modelo_vo.ZonaVO;
import vista.BusquedaEntities;

public class LogicaPremio {

	ControladorArticulo _controladorArticulo;
	private ControladorZona controladorZona;
	private ControladorCajaZona controladorCaja_zona;
	private ControladorEmpleado controladorEmpleado;
	private ControladorVentas controladorVentas;
	private ControladorCobrador controladorCobrador;
	private ControladorVendedor controladorVendedor;
	private ControladorPedidos controladorPedido;
	private ControladorUsuario controladorUsuario = new ControladorUsuario();
	private LogicaUsuario logica_usuario = new LogicaUsuario();
	private ControladorVendedor_ph controladorVendedor_ph= new ControladorVendedor_ph();
	private LogicaVendedor_ph logica_vendedor_ph = new LogicaVendedor_ph();
	BusquedaEntities busqueda_entities;
	private int numero;
	private double numero_double;
	
	public boolean validarInt(String val_string){
		
		try{
			
			numero = Integer.parseInt(val_string);
			
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
	
	
	
	public ArrayList<Object []> validarCalculoPremio(java.sql.Date desde, java.sql.Date hasta){
		
		ArrayList<Object []> ar = new ArrayList<Object []>(); 
		
		ArrayList<ZonaVO> ar_zonas = controladorZona.buscarZonas();
		
		Caja_zonaDAO czDAO = new Caja_zonaDAO();
		
		/*CharSequence castDesde = "";
		
		castDesde = fecha.toString();
		
		LocalDate hasta = LocalDate.parse(castDesde);
		
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
		
		java.sql.Date lunes = Date.valueOf(hasta);*/
		
		for(ZonaVO zVO : ar_zonas){
			
			Object d [] = new Object [5];
			
			ArrayList<Caja_zonasVO> arcz = null;
			try {
				arcz = czDAO.caja_zonaEntreFechas(zVO.getId_zona(),
						desde, hasta);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			float acumulado_mt = 0;
			float acumulado_cobranza = 0;
			float efectividad = 0;
			
			if(arcz!=null){
				
				for(Caja_zonasVO czVO : arcz){
					
					acumulado_mt += czVO.getMonto_ideal();
					acumulado_cobranza += (float) czVO.getIngresos();
				}
				
				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.toString(zVO.getId_cobrador()));
				
				if(eVO!=null)
				
					d[0] = eVO.getNombre() + " " + eVO.getApellido()+ "(" + eVO.getId_usuario() + ")";
				
				d[1] = acumulado_mt;
				d[2] = acumulado_cobranza;
				
				if(acumulado_mt != 0)
					efectividad = acumulado_cobranza * 100 / acumulado_mt;
				
				d[3] = efectividad;
				
				CobradorVO cobradorVO = controladorCobrador.buscarCobrador_porID(eVO.getId_usuario());
				
				if(efectividad >= 94) d[4] = cobradorVO.getPremio() * acumulado_cobranza / 100;
				else d[4] = 0;
				
				ar.add(d);
			}
				
		}
		
		return ar;
	}
	
	public ArrayList<Object []> validarCalculoComision(java.sql.Date desde, java.sql.Date hasta){
		
		ArrayList<Object []> ar = new ArrayList<Object []>(); 
		
		ArrayList<ZonaVO> ar_zonas = controladorZona.buscarZonas();
		
		Caja_zonaDAO czDAO = new Caja_zonaDAO();
		
		/*CharSequence castDesde = "";
		
		castDesde = fecha.toString();
		
		LocalDate hasta = LocalDate.parse(castDesde);
		
		switch(hasta.getDayOfWeek().name()){
				
			case "FRIDAY": hasta = hasta.plusDays(-6);
			break;
			case "THURSDAY": hasta = hasta.plusDays(-5);
			break;
			case "WEDNESDAY": hasta = hasta.plusDays(-4);
			break;
			case "TUESDAY": hasta = hasta.plusDays(-3);
			break;
			case "MONDAY": hasta = hasta.plusDays(-2);
			break;
		}
		
		java.sql.Date sabado = Date.valueOf(hasta);*/
		
		VentaDAO vDAO = new VentaDAO();
		RetiroDAO rDAO = new RetiroDAO();
		CambioPlanDAO cDAO = new CambioPlanDAO();
		EmpleadoDAO eDAO = new EmpleadoDAO();
		
		ArrayList<EmpleadoVO> ar_empleados = null;
		try {
			ar_empleados = eDAO.buscarEmpleados_porPuesto("vendedor");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<VentasVO> ar_ventas = null;
		try {
			ar_ventas = vDAO.buscarVentas_entreFechas(desde, hasta);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<RetirosVO> ar_retiros = null;
		try {
			ar_retiros = rDAO.buscarRetiros_entreFechas(desde, hasta);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<Cambio_planVO> ar_cambio = null;
		try {
			ar_cambio = cDAO.buscarCambio_entreFechas(desde, hasta);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ar_empleados!=null){
			
			for(EmpleadoVO empleadoVO : ar_empleados){
				
				Object d [] = new Object [7];
				
				float comision = 0;
				float comision_total = 0;
				float descuento = 0;
				float ventas_articulos = 0;
				float ventas_prestamos = 0;
				int n_retiros = 0;
				int n_ventas = 0;
					
				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.toString(empleadoVO.getId_usuario()));
				
				if(eVO!=null && eVO.getId_usuario()!=33){ //codigo de halcones diarios
					
					d[0] = eVO.getNombre() + " " + eVO.getApellido()+ "(" + eVO.getId_usuario() + ")";
					
					if(ar_ventas!=null){
						
						for(VentasVO vVO : ar_ventas){
							
							if(vVO.getId_vendedor() == empleadoVO.getId_usuario()){
								
								n_ventas++;
								
								boolean venta_p = false;
								boolean venta_ar = false;
								
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
												
							}
										
						}
					}
					
					d[1] = n_ventas;
					d[2] = n_retiros;
						
					d[3] = ventas_articulos + ventas_prestamos;
					
					VendedorVO vendedorVO = controladorVendedor.buscarVendedor_porID(eVO.getId_usuario());
					controladorVendedor_ph.setLogicaVendedor_ph(logica_vendedor_ph);
					
					ArrayList<Vendedores_padre_hijoVO> arVendedor_padres = controladorVendedor_ph.
							buscarVendedoresPadres_porIdHijo(eVO.getId_usuario());
					
					if(arVendedor_padres != null)
						
						comision = (float) ((vendedorVO.getComision() - 1) * ventas_articulos / 100 + 
								(vendedorVO.getComision_prestamo() - 1) * ventas_prestamos / 100);
					else
						
						comision = (float) (vendedorVO.getComision() * ventas_articulos / 100 + 
								vendedorVO.getComision_prestamo() * ventas_prestamos / 100);
					
					
					ArrayList<Vendedores_padre_hijoVO> arVendedor_hijos = controladorVendedor_ph.
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
					
					d[4] = comision;
					
					if(ar_cambio!=null){
						
						for(Cambio_planVO cVO : ar_cambio){
							
							if(cVO.getId_vendedor() == empleadoVO.getId_usuario()){
							
								
								/*RetirosVO rVO = null;
								
								try {
									rVO = rDAO.buscarRetiro_porNpedido_hora(cVO.getN_pedido(),
											cVO.getHora_registro());
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}*/
								
								/*descuento += art_anteriorVO.getDias()*art_anteriorVO.getCuota_diaria() -
										art_posteriorVO.getDias()*art_posteriorVO.getCuota_diaria();*/
								PedidosVO pVO = controladorPedido
										.buscarPedido_porNpedido(cVO.getN_pedido());
								
								
								 descuento += cVO.getCredito_anterior() - pVO.getDias() * 
										 pVO.getCuota_diaria();
								  
							}
								
								
						}
					}
					
					float descuento_com = (float) (vendedorVO.getComision() * descuento / 100);
					System.out.println("comision "+  vendedorVO.getComision() + " descuento " + descuento +
							 " descuento com " + descuento_com);
					//if(desc_inc > 0) d[5] = - desc_inc_com;
					//else  d[5] = desc_inc_com;
					if(descuento_com!=0) d[5] = -descuento_com;
					else d[5] = descuento_com;
					
					comision_total = comision - descuento_com;
					
					d[6] = comision_total;
					
					ar.add(d);
				}
					
				
			}
					
		}
		
		return ar;
	}
	
	public ArrayList<Object []> busquedaVentas(java.sql.Date desde, java.sql.Date hasta){
		
		ArrayList<Object []> ar = new ArrayList<Object []>(); 
		
		VentaDAO vDAO = new VentaDAO();
		
		/*CharSequence castDesde = "";
		
		castDesde = fecha.toString();
		
		LocalDate hasta = LocalDate.parse(castDesde);
		
		switch(hasta.getDayOfWeek().name()){
			
		case "FRIDAY": hasta = hasta.plusDays(-6);
		break;
		case "THURSDAY": hasta = hasta.plusDays(-5);
		break;
		case "WEDNESDAY": hasta = hasta.plusDays(-4);
		break;
		case "TUESDAY": hasta = hasta.plusDays(-3);
		break;
		case "MONDAY": hasta = hasta.plusDays(-2);
		break;
		}
		
		java.sql.Date sabado = Date.valueOf(hasta);*/
		
		ArrayList<VentasVO> ar_ventas = null;
		try {
			ar_ventas = vDAO.buscarVentas_entreFechas(desde, hasta);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		if(ar_ventas!=null){
		
		for(VentasVO vVO : ar_ventas){
			
			Object d [] = new Object [8];
			
				d[0] = vVO.getN_venta();
					
				
				d[1] = vVO.getN_pedido();
				String descripcion = "";

				ArrayList<Pedido_articuloVO> ar_pedido = controladorPedido.
						buscarArticulos_porPedido(vVO.getN_pedido(), true);
				
				
				if(ar_pedido!=null){
						
					for(Pedido_articuloVO paVO : ar_pedido){
						
						ArticulosVO aVO = _controladorArticulo.
								buscarArticuloUsuario(Integer.toString(paVO.getCodigo_articulo()));
						
						ArticulosPVO apVO = _controladorArticulo.buscarArticulo_enPrestamo(aVO.getCodigo());
						
						if(apVO!=null){
							
							descripcion = descripcion + " " + "Prestamo(" + apVO.getCodigo() + 
									")$" + Double.toString(apVO.getMonto());
						}
						else
							descripcion = descripcion + " " + aVO.getNombre()+
									"(" + aVO.getCodigo() + ")";
					}
					
					
				}
				
				d[2] = descripcion;
				
				d[3] = vVO.getCredito();
				
				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.toString(vVO.getId_vendedor()));
				
				//System.out.println("empleado nombre " + eVO.getNombre() + " idvendedor" + vVO.getId_vendedor());
				
				if(eVO!=null)
				
					d[4] = eVO.getNombre() + " " + eVO.getApellido() + "(" + eVO.getId_usuario() + ")";
				
				controladorUsuario.setLogicaUsuario(logica_usuario);
				
				UsuariosVO uVO = controladorUsuario.buscarUsuario_porID(vVO.getId_usuario());
				
				d[5] = uVO.getNombre();
				
				SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
				
				String fec = new String(f.format(vVO.getFecha_registro()));
				
				d[6] = fec;
				d[7] = vVO.getHora_registro();
				ar.add(d);
			}
				
		}
		
		return ar;
	}
	
	public ArrayList<Object []> busquedaRetiros(java.sql.Date desde, java.sql.Date hasta){
		
		ArrayList<Object []> ar = new ArrayList<Object []>(); 
		
		RetiroDAO rDAO = new RetiroDAO();
		
		/*CharSequence castDesde = "";
		
		castDesde = fecha.toString();
		
		LocalDate hasta = LocalDate.parse(castDesde);
		
		switch(hasta.getDayOfWeek().name()){
		
		case "FRIDAY": hasta = hasta.plusDays(-6);
		break;
		case "THURSDAY": hasta = hasta.plusDays(-5);
		break;
		case "WEDNESDAY": hasta = hasta.plusDays(-4);
		break;
		case "TUESDAY": hasta = hasta.plusDays(-3);
		break;
		case "MONDAY": hasta = hasta.plusDays(-2);
		break;
		}
		
		java.sql.Date sabado = Date.valueOf(hasta);*/
		
		ArrayList<RetirosVO> ar_retiros = null;
		try {
			ar_retiros = rDAO.buscarRetiros_entreFechas(desde, hasta);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ar_retiros!=null){
		
		for(RetirosVO rVO : ar_retiros){
			
			Object d [] = new Object [9];
			
				d[0] = rVO.getN_retiro();	
				
				d[1] = rVO.getN_pedido();

				ArticulosVO artVO = _controladorArticulo.buscarArticulo_porCodigo(rVO.getCodigo());
				
				d[2] = artVO.getNombre()+ "(" + artVO.getCodigo() + ")";
				
				ArrayList<Pedido_articuloVO> ar_pa = controladorPedido.
						buscarArticulos_porPedido(rVO.getN_pedido(), false);
				
				if(ar_pa!=null){
					
					for(Pedido_articuloVO pVO : ar_pa){
						
						if(rVO.getId_pedido_articulo()==pVO.getId()){
							
							d[3] = pVO.getDias() * pVO.getCuota();
						}
					}
				}
				
				
				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.toString(rVO.getId_vendedor()));
				
				d[4] = eVO.getNombre() + " " + eVO.getApellido()+ "(" + eVO.getId_usuario() + ")";
				
				d[5] = rVO.getObservaciones();
				
				controladorUsuario.setLogicaUsuario(logica_usuario);
				
				UsuariosVO uVO = controladorUsuario.buscarUsuario_porID(rVO.getId_usuario());
				
				d[6] = uVO.getNombre();
				
				SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
				
				String fec = new String(f.format(rVO.getFecha_registro()));
				
				d[7] = fec;
				d[8] = rVO.getHora_registro();
				ar.add(d);
			}
				
		}
		
		return ar;
	}
	
	public ArrayList<Object []> busquedaCambioPlan(java.sql.Date desde, java.sql.Date hasta){
		
		ArrayList<Object []> ar = new ArrayList<Object []>(); 
		
		CambioPlanDAO cDAO = new CambioPlanDAO();
		
		/*CharSequence castDesde = "";
		
		castDesde = fecha.toString();
		
		LocalDate hasta = LocalDate.parse(castDesde);
		
		switch(hasta.getDayOfWeek().name()){
		
		case "FRIDAY": hasta = hasta.plusDays(-6);
		break;
		case "THURSDAY": hasta = hasta.plusDays(-5);
		break;
		case "WEDNESDAY": hasta = hasta.plusDays(-4);
		break;
		case "TUESDAY": hasta = hasta.plusDays(-3);
		break;
		case "MONDAY": hasta = hasta.plusDays(-2);
		break;
		}
		
		java.sql.Date sabado = Date.valueOf(hasta);*/
		
		ArrayList<Cambio_planVO> ar_cambio = null;
		try {
			ar_cambio = cDAO.buscarCambio_entreFechas(desde, hasta);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ar_cambio!=null){
		
		for(Cambio_planVO cVO : ar_cambio){
			
			Object d [] = new Object [10];
			
				d[0] = cVO.getN_cambio();	
				
				d[1] = cVO.getN_pedido();

				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.toString(cVO.getId_vendedor()));
				
				d[2] = eVO.getNombre() + " " + eVO.getApellido()+ "(" + eVO.getId_usuario() + ")";
				
				d[3] = cVO.getPlan_anterior();
				
				d[4] = cVO.getPlan_posterior();

				d[5] = cVO.getCredito_anterior();
				
				d[6] = cVO.getCredito_posterior();
				
				controladorUsuario.setLogicaUsuario(logica_usuario);
				
				UsuariosVO uVO = controladorUsuario.buscarUsuario_porID(cVO.getId_usuario());
				
				d[7] = uVO.getNombre();
				
				SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
				
				String fec = new String(f.format(cVO.getFecha_registro()));
				
				d[8] = fec;
				d[9] = cVO.getHora_registro();
				ar.add(d);
			}
				
		}
		
		return ar;
	}
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
		
	}
	
	public void setControladorArticulo(ControladorArticulo _controladorArticulo){
		
		this._controladorArticulo = _controladorArticulo;
		
	}
	
	public void setControladorVentas(ControladorVentas _controladorVentas){
		
		this.controladorVentas = _controladorVentas;
		
	}
	
	public void setControladorEmpleado(ControladorEmpleado _controladorEmpleado){
		
		this.controladorEmpleado = _controladorEmpleado;
		
	}
	
	public void setControladorCobrador(ControladorCobrador _controladorCobrador){
		
		this.controladorCobrador = _controladorCobrador;
		
	}

	public void setControladorVendedor(ControladorVendedor _controladorVendedor){
		
		this.controladorVendedor = _controladorVendedor;
		
	}
	
	public void setControladorPedido(ControladorPedidos _controladorPedido){
		
		this.controladorPedido = _controladorPedido;
		
	}
	
	
	public void setControladorZona(ControladorZona _controladorZona){
		
		this.controladorZona = _controladorZona ;
		
	}
	
	public void setControladorCajaZona(ControladorCajaZona _controladorCaja_zona){
		
		this.controladorCaja_zona = _controladorCaja_zona ;
		
	}
	
}
