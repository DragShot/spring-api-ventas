package ds.training.mitocode.ventas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.training.mitocode.ventas.model.Venta;
import ds.training.mitocode.ventas.service.IVentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController extends AbstractController {
	@Autowired
	private IVentaService service;
	
	@GetMapping
	public ResponseEntity<List<Venta>> listar() {
		List<Venta> lista = service.listar();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Venta> obtener(@PathVariable("id") Integer id) {
		Venta venta = getImpl(id);
		return ResponseEntity.ok(venta);
	}
	
	@PostMapping
	public ResponseEntity<?> registrar(@Valid @RequestBody Venta venta) {
		verificarNoExiste(venta);
		venta = service.actualizar(venta);
		URI location = obtenerRuta("/{id}", venta.getIdVenta()).toUri();
		return ResponseEntity.created(location).body(venta);
	}
	
	@PutMapping
	public ResponseEntity<Venta> actualizar(@Valid @RequestBody Venta venta) {
		getImpl(venta.getIdVenta());
		service.actualizar(venta);
		return ResponseEntity.ok(venta);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) {
		getImpl(id);
		service.eliminar(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	private boolean verificarNoExiste(Venta venta) {
		return verificarNoExiste(service, venta, Venta.class, "una venta");
	}
	
	private Venta getImpl(Integer id) {
		return getImpl(service, id, Venta.class, "una venta");
	}
}
