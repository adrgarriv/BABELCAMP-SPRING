package converters;

import org.springframework.stereotype.Component;

import dto.ClienteDto;
import dto.CuentaDto;
import dto.MovimientoDto;
import model.Cliente;
import model.Cuenta;
import model.Movimiento;

@Component
public class ConversorEntityDtoImpl implements ConversorEntityDto {

	@Override
	public ClienteDto clienteToDto(Cliente c) 
	{
		return new ClienteDto(c.getDni(),c.getNombre(),c.getDireccion(),c.getTelefono());
	}

	@Override
	public Cliente dtoToCliente(ClienteDto dto) 
	{
		return new Cliente(dto.getDni(),dto.getNombre(),dto.getDireccion(),dto.getTelefono());
	}

	@Override
	public CuentaDto cuentaToDto(Cuenta c) 
	{
		return new CuentaDto(c.getNumeroCuenta(), c.getSaldo(), c.getTipocuenta());
	}

	@Override
	public Cuenta dtoToCuenta(CuentaDto dto) 
	{
		return new Cuenta(dto.getNumeroCuenta(), dto.getSaldo(), dto.getTipocuenta());
	}

	@Override
	public MovimientoDto movimientoToDto(Movimiento movimiento) 
	{
		return new MovimientoDto(movimiento.getIdMovimiento(), movimiento.getFecha(), movimiento.getCantidad(), movimiento.getOperacion(), new CuentaDto(movimiento.getCuenta().getNumeroCuenta(),movimiento.getCuenta().getSaldo(),movimiento.getCuenta().getTipocuenta()));
	}

	@Override
	public Movimiento dtoToMovimiento(MovimientoDto dto) 
	{
		return new Movimiento(dto.getIdMovimiento(), dto.getFecha(), dto.getCantidad(), dto.getOperacion(),dtoToCuenta(dto.getCuenta()));
	}

	

}
