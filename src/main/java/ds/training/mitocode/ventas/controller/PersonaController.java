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

import ds.training.mitocode.ventas.model.Persona;
import ds.training.mitocode.ventas.service.IPersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController extends AbstractController {
	@Autowired
	private IPersonaService service;
	
	@GetMapping
	public ResponseEntity<List<Persona>> listar() {
		List<Persona> lista = service.listar();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Persona> obtener(@PathVariable("id") Integer id) {
		Persona pers = getImpl(id);
		return ResponseEntity.ok(pers);
	}
	
	@PostMapping
	public ResponseEntity<?> registrar(@Valid @RequestBody Persona pers) {
		verificarNoExiste(pers);
		pers = service.registrar(pers);
		URI location = obtenerRuta("/{id}", pers.getIdPersona()).toUri();
		return ResponseEntity.created(location).body(pers);
	}
	
	@PutMapping
	public ResponseEntity<Persona> actualizar(@Valid @RequestBody Persona pers) {
		getImpl(pers.getIdPersona());
		pers = service.actualizar(pers);
		return ResponseEntity.ok(pers);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) {
		getImpl(id);
		service.eliminar(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	private boolean verificarNoExiste(Persona pers) {
		/*if (pers.getIdPersona() != null) {
			pers = service.obtener(pers.getIdPersona());
			if (pers != null && pers.getIdPersona() != null) {
				throw new ModelAlreadyExistsException("Ya existe una persona con ID " + pers.getIdPersona()).ofType(Persona.class);
			}
		}
		return true;*/
		return verificarNoExiste(service, pers, Persona.class, "una persona");
	}
	
	private Persona getImpl(Integer id) {
		/*if (id == null) {
			throw new ModelNotFoundException("No se ha provisto de un ID para encontrar la persona").ofType(Persona.class);
		}
		Persona pers = service.obtener(id);
		if (pers == null || pers.getIdPersona() == null) {
			throw new ModelNotFoundException("No se ha encontrado una persona con ID " + id).ofType(Persona.class);
		}
		return pers;*/
		return getImpl(service, id, Persona.class, "una persona");
	}
}
