package service;

import java.util.Date;
import java.util.List;

import dto.CuentaDto;
import dto.MovimientoDto;

public interface CajeroService 
{
	CuentaDto validarCuenta(int idCuenta);
	boolean ingresarDinero(int idCuenta, double cantidad);
	boolean sacarDinero(int idCuenta, double cantidad);
	boolean transferencia(int idCuentaOrigen, int idCuentaDestino, double cantidad);
	List<MovimientoDto> consultarMovimientos(int idCuenta,Date desde, Date hasta);
}
