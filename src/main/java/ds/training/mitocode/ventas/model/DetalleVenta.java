package ds.training.mitocode.ventas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "det_venta")
public class DetalleVenta extends AbstractModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_id_det_venta")
	private Integer idDetalleVenta;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_venta", nullable = false,
			foreignKey = @ForeignKey(name = "fk_detventa_venta"))
	private Venta venta;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_producto", nullable = false,
			foreignKey = @ForeignKey(name = "fk_detventa_producto"))
	private Producto producto;
	
	@Min(value = 0, message="La cantidad debe ser un número positivo")
	@Column(name = "cantidad", nullable = false)
	private int cantidad;

	public Integer getIdDetalleVenta() {
		return idDetalleVenta;
	}

	public void setIdDetalleVenta(Integer idDetalleVenta) {
		this.idDetalleVenta = idDetalleVenta;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	@JsonIgnore
	public Integer getId() {
		return idDetalleVenta;
	}
}
