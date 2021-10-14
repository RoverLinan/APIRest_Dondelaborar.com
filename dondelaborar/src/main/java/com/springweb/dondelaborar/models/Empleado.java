package com.springweb.dondelaborar.models;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;


@Entity
@Table(name = "empleados")
public class Empleado{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = true)
    private int id;

	@NotEmpty(message = "Su NOMBRE es obligatorio")
	private String nombre;

	@NotEmpty(message = "Su APELLIDO es obligatorio")
	private String apellidos;

	@NotEmpty(message = "Su DNI es obligatorio")
	private String dni;

	private String domicilio;
	private String telefono;
	private int genero;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
	private Ubicacion ubicacion;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private Usuario usuario;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "perfil_id", referencedColumnName = "id")
	private PerfilEmpleado perfil;
	

	@OneToMany(mappedBy = "empleado")
	private Collection<Postulacion> postulaciones;


	@Column(name = "fechaNacimiento")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public PerfilEmpleado getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilEmpleado perfil) {
		this.perfil = perfil;
	}
	public Date getFechanacimiento() {
		return fechaNacimiento;
	}
	public void setFechanacimiento(Date fechanacimiento) {
		this.fechaNacimiento = fechanacimiento;
	}
	
	public int getGenero() {
		return genero;
	}
	public void setGenero(int genero) {
		this.genero = genero;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	@Override
	public String toString() {
		return "Empleado [apellidos=" + apellidos + ", dni=" + dni + ", domicilio=" + domicilio + ", fechaNacimiento="
				+ fechaNacimiento + ", genero=" + genero + ", id=" + id + ", nombre=" + nombre + ", perfil=" + perfil
				+ ", postulaciones=" + postulaciones + ", telefono=" + telefono + ", ubicacion=" + ubicacion
				+ ", usuario=" + usuario + "]";
	}

  


	
}
