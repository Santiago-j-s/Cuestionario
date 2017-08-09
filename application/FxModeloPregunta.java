package application;

import com.google.gson.Gson;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pregunta.modelo.Pregunta;

public class FxModeloPregunta {
	
	protected final StringProperty enunciado;
	protected final StringProperty respuesta;
	protected final StringProperty opcion1;
	protected final StringProperty opcion2;
	
	public FxModeloPregunta() {
		this.enunciado = new SimpleStringProperty();
		this.respuesta = new SimpleStringProperty();
		this.opcion1 = new SimpleStringProperty();
		this.opcion2 = new SimpleStringProperty();
	}
  
	public void bind(StringProperty enunciado,
			StringProperty respuesta,
			StringProperty opcion1,
			StringProperty opcion2) {
		this.enunciado.bind(enunciado);
		this.respuesta.bind(respuesta);
		this.opcion1.bind(opcion1);
		this.opcion2.bind(opcion2);
	}
	
	public void unbind() {
		this.enunciado.unbind();
		this.enunciado.unbind();
		this.enunciado.unbind();
		this.enunciado.unbind();
	}
	
	public String toJson() {
		Pregunta pregunta = new Pregunta(
			this.enunciado.get(),
			this.respuesta.get(),
			this.opcion1.get(),
			this.opcion2.get()
		);
		
		return new Gson().toJson(pregunta);
	}
}
