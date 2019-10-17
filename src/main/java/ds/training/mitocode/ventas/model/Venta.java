package ds.training.mitocode.ventas.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "mst_venta")
public class Venta extends AbstractModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_id_venta")
	private Integer idVenta;
	
	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_persona", nullable = false,
			foreignKey = @ForeignKey(name = "fk_venta_persona"))
	private Persona persona;
	
	@Min(value = 0, message = "El valor del importe debe ser positivo")
	@Column(name = "importe", nullable = false, precision = 2)
	private double importe;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "venta", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<DetalleVenta> detalle;

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public List<DetalleVenta> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleVenta> detalle) {
		this.detalle = detalle;
	}

	@Override
	@JsonIgnore
	public Integer getId() {
		return idVenta;
	}
}
