package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.*;

import it.polito.tdp.model.Dictionary;
import it.polito.tdp.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxLingua;

    @FXML
    private TextArea txtDaCorreggere;

    @FXML
    private Button spellCheckUtton;

    @FXML
    private TextArea txtCorretto;

    @FXML
    private Label laelErrori;

    @FXML
    private Button cleartextUtton;

    @FXML
    private Label laelStatus;

    @FXML
    void doActivation(ActionEvent event) {
    	
    	model.loadDictionary();
    	txtDaCorreggere.clear();
    	
    	txtDaCorreggere.setDisable(false);
      	spellCheckUtton.setDisable(false);
    	cleartextUtton.setDisable(false);
    }

    @FXML
    void doClearText(ActionEvent event) {
    	txtDaCorreggere.clear();
    	txtCorretto.clear();
    	laelStatus.setText("Spell check status:");
    	laelErrori.setText("num errori:");
    	txtDaCorreggere.setDisable(true);
    	txtCorretto.setDisable(true);
      	spellCheckUtton.setDisable(true);
    	cleartextUtton.setDisable(true);
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	List<String> testo = new LinkedList<>();
    	
    	model.loadDictionary();
    	if(txtDaCorreggere.getText().isEmpty()) {
    		txtCorretto.appendText("Scrivi qualcosa!");
    	}
    	
        String input = txtDaCorreggere.getText().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
        String array[]  = input.split(" ");
        for(int i=0; i<array.length; i++) {
        	testo.add(i, array[i]);
        }
        List<RichWord> testoErr = new LinkedList<>(model.spellCheckText(testo));
        
        String s ="";
        for(RichWord r: testoErr)
        	s+=r;
        
        txtCorretto.appendText(s);
        laelErrori.setText("errori trovati: "+ testoErr.size());
    }

    @FXML
    void initialize() {
        assert boxLingua != null : "fx:id=\"boxLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDaCorreggere != null : "fx:id=\"txtDaCorreggere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert spellCheckUtton != null : "fx:id=\"spellCheckUtton\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorretto != null : "fx:id=\"txtCorretto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert laelErrori != null : "fx:id=\"laelErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cleartextUtton != null : "fx:id=\"cleartextUtton\" was not injected: check your FXML file 'Scene.fxml'.";
        assert laelStatus != null : "fx:id=\"laelStatus\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Dictionary model) {
    	
    	txtDaCorreggere.setDisable(true);
    	txtDaCorreggere.setText("Selezionare una lingua");
    	
    	txtCorretto.setDisable(true);
    	boxLingua.getItems().addAll("English","Italiano");
    	
    	spellCheckUtton.setDisable(true);
    	cleartextUtton.setDisable(true);
    	
    	this.model = model;
    }
}


