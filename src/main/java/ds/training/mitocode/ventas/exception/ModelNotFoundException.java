package ds.training.mitocode.ventas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModelNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 5699438878592425676L;
	
	private Class<?> type;

	public ModelNotFoundException() {
		super();
	}

	public ModelNotFoundException(String message) {
		super(message);
	}
	
	public ModelNotFoundException ofType(Class<?> type) {
		this.type = type;
		return this;
	}
	
	public Class<?> getType() {
		return type;
	}
	
	public String getMessage() {
		String msg = super.getMessage();
		if (msg == null || msg.isEmpty()) {
			msg = "No se ha encontrado un elemento de tipo " + type.getSimpleName();
		}
		return msg;
	}
}
