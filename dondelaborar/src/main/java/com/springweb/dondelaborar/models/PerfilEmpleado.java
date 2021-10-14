package com.springweb.dondelaborar.models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "perfiles_empleado")
public class PerfilEmpleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = true)
    private int id;

	@Temporal(TemporalType.DATE)
	private Date fecha;
	private String discapacidad;

	@Column(name = "pretencionsalarial")
	private int pretencionSalarial;

	@Column(name = "horaridisponible")
	private String horarioDisponible;

	private boolean disponible;

	@OneToOne
	@JoinColumn(name = "curriculum_id", referencedColumnName = "id")
	private Curriculum curriculum;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getDiscapacidad() {
		return discapacidad;
	}
	public void setDiscapacidad(String discapacidad) {
		this.discapacidad = discapacidad;
	}
	public int getPretencionSalarial() {
		return pretencionSalarial;
	}
	public void setPretencionSalarial(int pretencionSalarial) {
		this.pretencionSalarial = pretencionSalarial;
	}
	public String getHorarioDisponible() {
		return horarioDisponible;
	}
	public void setHorarioDisponible(String horarioDisponible) {
		this.horarioDisponible = horarioDisponible;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}


	public Curriculum getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	

}
