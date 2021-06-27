package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Adiacenza;
import it.polito.tdp.artsmia.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	
	private Model model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnArtistiConnessi;

    @FXML
    private Button btnCalcolaPercorso;

    @FXML
    private ComboBox<String> boxRuolo;

    @FXML
    private TextField txtArtista;

    @FXML
    private TextArea txtResult;

    @FXML
    void doArtistiConnessi(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Calcola artisti connessi"+"\n");
    	List<Adiacenza> result=this.model.getCoppie();
    	if(result.size()==0) {
    		this.txtResult.appendText("Non ci sono coppie collegate");
    		return;
    	}
    	for(Adiacenza a: result) {
    		this.txtResult.appendText(a.toString()+"\n");
    	}
    	return;
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Calcola percorso"+"\n");
    	String s=this.txtArtista.getText().trim();
    	if(s.isBlank()) {
    		this.txtResult.setText("Nessun id inserito!");
    		return;
    	}
    	Integer id;
    	try {
    		id=Integer.parseInt(s);
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("Quello inserito non è un numero");
    		return;
    	}
    	
    	if(!this.model.esisteId(id)) {
    		this.txtResult.setText("L'id inserito non esiste");
    		return;
    	}
    	
    	List<Integer> result=this.model.avviaRicorsione(id);
    	if(result.size()==0) {
    		this.txtResult.appendText("cammino non esiste");
    		return;
    	}
    	for(Integer i: result) {
    		this.txtResult.appendText(i+"\n");
    	}
    	return;
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Crea grafo"+"\n");
    	String p=this.boxRuolo.getValue();
    	if(p==null) {
    		this.txtResult.setText("Nessuna professione inserita!");
    		return;
    	}
    	
    	String result=this.model.creaGrafo(p);
    	this.txtResult.appendText(result);
    	this.btnArtistiConnessi.setDisable(false);
    	this.btnCalcolaPercorso.setDisable(false);
    }

    public void setModel(Model model) {
    	this.model = model;
    	List<String> professioni=this.model.getP();
    	this.boxRuolo.getItems().addAll(professioni);
    }

    
    @FXML
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnArtistiConnessi != null : "fx:id=\"btnArtistiConnessi\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnCalcolaPercorso != null : "fx:id=\"btnCalcolaPercorso\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert boxRuolo != null : "fx:id=\"boxRuolo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtArtista != null : "fx:id=\"txtArtista\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";
        this.btnArtistiConnessi.setDisable(true);
        this.btnCalcolaPercorso.setDisable(true);

    }
}
