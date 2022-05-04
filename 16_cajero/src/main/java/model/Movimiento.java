package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "movimientos")
public class Movimiento 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMovimiento;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date fecha;
	private double cantidad;
	private String operacion;
	@ManyToOne()
	@JoinColumn(name = "idCuenta", referencedColumnName = "numeroCuenta")
	Cuenta cuenta;
	
	
	public Movimiento(Date fecha, double cantidad, String operacion, Cuenta cuenta) {
		super();
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.operacion = operacion;
		this.cuenta = cuenta;
	}
}
