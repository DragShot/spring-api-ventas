package ds.training.mitocode.ventas.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import ds.training.mitocode.ventas.exception.ModelAlreadyExistsException;
import ds.training.mitocode.ventas.exception.ModelNotFoundException;
import ds.training.mitocode.ventas.model.AbstractModel;
import ds.training.mitocode.ventas.service.ICRUD;

public abstract class AbstractController {
	
	protected <E extends AbstractModel> boolean verificarNoExiste(ICRUD<E> service, E obj, Class<E> type, String subject) {
		if (obj.getId() != null) {
			obj = service.obtener(obj.getId());
			if (obj != null && obj.getId() != null) {
				throw new ModelAlreadyExistsException("Ya existe " + subject + " con ID " + obj.getId()).ofType(type);
			}
		}
		return true;
	}
	
	protected <E extends AbstractModel> E getImpl(ICRUD<E> service, Integer id, Class<E> type, String subject) {
		if (id == null) {
			throw new ModelNotFoundException("No se ha provisto de un ID para encontrar " + subject).ofType(type);
		}
		E obj = service.obtener(id);
		if (obj == null || obj.getId() == null) {
			throw new ModelNotFoundException("No se ha encontrado " + subject + " con ID " + id).ofType(type);
		}
		return obj;
	}
	
	protected UriComponents obtenerRuta(String path, Object... params) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path(path).buildAndExpand(params);
	}

}
