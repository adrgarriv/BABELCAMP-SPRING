package converters;

import dto.ClienteDto;
import dto.CuentaDto;
import dto.MovimientoDto;

import model.Cliente;
import model.Cuenta;
import model.Movimiento;


public interface ConversorEntityDto 
{
	public ClienteDto clienteToDto(Cliente c);
	public Cliente dtoToCliente(ClienteDto dto);
	public CuentaDto cuentaToDto(Cuenta c);
	public Cuenta dtoToCuenta(CuentaDto dto);
	public MovimientoDto movimientoToDto(Movimiento movimiento);
	public Movimiento dtoToMovimiento(MovimientoDto dto);
}
