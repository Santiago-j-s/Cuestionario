package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FormPreguntas {
	@FXML private TextArea enunciado;
	
	@FXML private TextField respuesta;
	@FXML private TextField opcion1;
	@FXML private TextField opcion2;
	
	@FXML private Button siguiente;
	@FXML private Button finalizar;
	
	private final String savePath = Paths.get("/home/santiago/git/SolitarioMonedas/juego/src/recursos/preguntas").toString();
	
	private FxModeloPregunta modelo;
	private List<String> modelos;
	
	public FormPreguntas() {}
	
	public FxModeloPregunta crearModelo() {
		modelo = new FxModeloPregunta();
		modelo.bind(
			enunciado.textProperty(),
			respuesta.textProperty(),
			opcion1.textProperty(),
			opcion2.textProperty()
		);
		
		return modelo;
	}
	
	public void vaciarCampos() {
		this.enunciado.textProperty().set("");
		this.respuesta.textProperty().set("");
		this.opcion1.textProperty().set("");
		this.opcion2.textProperty().set("");
	}
	
	public void initialize() {
		modelo = crearModelo();
		modelos = new ArrayList<String>();
	}
	
	@FXML
	public void siguiente(ActionEvent e) {
		modelos.add(modelo.toJson());
		System.out.println(modelo.toJson());
		vaciarCampos();
		modelo = crearModelo();
	}
	
	private String modelosJson() {
		String json = "[";
		for (int i = 0; i < modelos.size(); i++) {
			json += modelos.get(i);
			if(i < modelos.size() - 1) {
				json += ",";
			} 
		}
		json += "]";
		
		System.out.println(json);
		return json;
	}
	
	private BufferedWriter createWriter(Path path) throws IOException {
		return new BufferedWriter(new FileWriter(new File(path.toString())));
	}
	
	private void writeToFile(Path path, String text) {
		try (BufferedWriter bw = createWriter(path)) {
			bw.write(text);
		} catch (IOException error) {
			error.printStackTrace();
		}
	}
	
	private Path obtenerPath(String categoria) {
		return Paths.get(savePath, categoria.concat(".json"));
	}
	
	@FXML
	public void finalizar(ActionEvent e) {
		if(!modelos.contains(this.enunciado)) {
			modelos.add(modelo.toJson());
		}
		
		String json = modelosJson();
		writeToFile(obtenerPath("prueba"), json);
		
	}
}
