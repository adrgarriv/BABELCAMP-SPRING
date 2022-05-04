package service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import converters.ConversorEntityDto;
import dao.CuentasDao;
import dao.MovimientosDao;
import dto.CuentaDto;
import dto.MovimientoDto;
import model.Cuenta;
import model.Movimiento;
@Service
public class CajeroServiceImpl implements CajeroService 
{
	@Autowired
	CuentasDao cuentasDao;
	@Autowired
	MovimientosDao movimientosDao;
	@Autowired
	ConversorEntityDto conversor;
	
	@Override
	public CuentaDto validarCuenta(int idCuenta) 
	{
		Optional<Cuenta> opt = cuentasDao.findById(idCuenta);
		if(opt.isPresent())
		{
			System.out.println("La cuenta existe");
			return conversor.cuentaToDto(opt.get());
		}
		else 
		{
			System.out.println("La cuenta no existe");
			return null;
		}
	}

	@Override
	public boolean ingresarDinero(int idCuenta, double cantidad) 
	{
		Optional<Cuenta> c = cuentasDao.findById(idCuenta);
		if(c.isPresent())
		{
			System.out.println("La cuenta existe y procedemos a ingresar el dinero");
			Cuenta cuenta = c.get();
			Movimiento m = new Movimiento( new Date(),cantidad,"ingreso",cuenta);
			cuenta.setSaldo(cantidad+cuenta.getSaldo());
			cuentasDao.save(cuenta);
			movimientosDao.save(m);
			return true;
		}
		else
		{
			System.out.println("La cuenta no existe");
			return false;
		}

	}


	@Override
	public boolean sacarDinero(int idCuenta, double cantidad) 
	{
		Optional<Cuenta> c = cuentasDao.findById(idCuenta);
		if(c.isPresent())
		{
			System.out.println("La cuenta existe. Vamos a comprobar el saldo");
			Cuenta cuenta = c.get();
			if(cantidad < cuenta.getSaldo())
			{
				System.out.println("El saldo es mayor que la cantidad a retirar");
				Movimiento m = new Movimiento( new Date(),cantidad,"extraccion",cuenta);
				cuenta.setSaldo(cuenta.getSaldo()-cantidad);
				cuentasDao.save(cuenta);
				movimientosDao.save(m);
				return true;
			}
			else
			{
				System.out.println("El saldo es menor que la cantidad a retirar");
				return false;
			}
			
		}
		else
		{
			return false;
		}


	}

	@Override
	public boolean transferencia(int idCuentaOrigen, int idCuentaDestino, double cantidad) 
	{
		if(sacarDinero(idCuentaOrigen,cantidad))
		{
			return ingresarDinero(idCuentaDestino,cantidad);
		}
		else
		{
			System.out.println("La cuenta no existe");
			return false;
		}

	}

	@Override
	public List<MovimientoDto> consultarMovimientos(int idCuenta, Date desde, Date hasta) 
	{
		if(cuentasDao.findById(idCuenta).isPresent())
		{
			return movimientosDao.findByIdCuentaAndFechaBetween(idCuenta, desde, hasta).stream().map(m->conversor.movimientoToDto(m)).collect(Collectors.toList());
		}
		else
		{
			System.out.println("La cuenta no existe");
			return null;
		}
	}

}
