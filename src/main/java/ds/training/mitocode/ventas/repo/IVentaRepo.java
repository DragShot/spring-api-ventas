package ds.training.mitocode.ventas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ds.training.mitocode.ventas.model.Venta;

public interface IVentaRepo extends JpaRepository<Venta, Integer> {
	
}
