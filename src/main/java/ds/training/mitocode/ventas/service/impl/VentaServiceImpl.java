package ds.training.mitocode.ventas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ds.training.mitocode.ventas.model.Venta;
import ds.training.mitocode.ventas.repo.IVentaRepo;
import ds.training.mitocode.ventas.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService {
	@Autowired
	private IVentaRepo repo;

	@Override
	public Venta registrar(Venta venta) {
		vincularDetalles(venta);
		return repo.save(venta);
	}

	@Override
	public Venta actualizar(Venta venta) {
		vincularDetalles(venta);
		return repo.save(venta);
	}

	@Override
	public List<Venta> listar() {
		List<Venta> ventas = repo.findAll(Sort.by(Sort.Order.asc("idVenta")));
		ventas.forEach(this::vincularDetalles);
		return repo.findAll();
	}

	@Override
	public Venta obtener(Integer id) {
		Venta venta = repo.findById(id).orElse(null);
		if (venta != null) {
			vincularDetalles(venta);
		}
		return venta;
	}

	@Override
	public boolean eliminar(Integer id) {
		boolean done = true;
		try {
			repo.deleteById(id);
		} catch (Exception ex) {
			done = false;
		}
		return done;
	}
	
	private void vincularDetalles(Venta venta) {
		venta.getDetalle().forEach(detalle -> detalle.setVenta(venta));
	}
}
