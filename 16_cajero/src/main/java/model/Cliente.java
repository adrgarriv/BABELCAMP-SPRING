package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "clientes")
public class Cliente 
{
	@Id
	private int dni;
	private String nombre;
	private String direccion;
	private int telefono;
	@ManyToMany()
	@JsonIgnore
	@JoinTable(name="titulares", joinColumns = @JoinColumn(name="dni", referencedColumnName="dni"), inverseJoinColumns = @JoinColumn(name="idCuenta", referencedColumnName="numeroCuenta"))
	private List<Cuenta> cuentas;
	
	public Cliente(int dni, String nombre, String direccion, int telefono) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}
}
