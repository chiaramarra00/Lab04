/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="chcCompleta"
    private CheckBox chcCompleta; // Value injected by FXMLLoader

    @FXML // fx:id="cmbCorsi"
    private ComboBox<String> cmbCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML // fx:id="txtStudente"
    private TextField txtStudente; // Value injected by FXMLLoader

	private Model model;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	String matricola = txtStudente.getText();
    	int matricolaNumerica;
    	try {
    		matricolaNumerica = Integer.parseInt(matricola);
    	} catch (NumberFormatException e) {
    		e.printStackTrace();
    		txtRisultato.setText("Inserire un numero di matricola.");
    		return;
    	}
    	txtRisultato.clear();
    	if (model.getStudenteByMatricola(matricolaNumerica)==null) {
    		txtRisultato.setText("Studente non presente nel database.");
    		return;
    	}
    	txtRisultato.clear();	
    	List<Corso> corsi = model.getCorsiStudente(matricolaNumerica);
    	for (Corso c : corsi)
    		txtRisultato.appendText(c.toString()+"\n");

    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	String corso = cmbCorsi.getValue();
    	if (corso==null) {
    		txtRisultato.setText("Selezionare un corso.");
    		return;
    	}
    	txtRisultato.clear();
    	LinkedList<Studente> iscritti = model.getIscrittiCorso(corso);
    	for (Studente s : iscritti)
    		txtRisultato.appendText(s.toString()+"\n");
    }

    @FXML
    void doCompletaNomeCognome(ActionEvent event) {
    	txtRisultato.setText("");
    	String matricola = txtStudente.getText();
    	int matricolaNumerica = 0;
    	try {
    		matricolaNumerica = Integer.parseInt(matricola);
    	} catch (NumberFormatException e) {
    		txtRisultato.setText("Inserire un numero di matricola.");
    		chcCompleta.setSelected(false);
    		e.printStackTrace();
    	}
    	Studente studente = model.getStudenteByMatricola(matricolaNumerica);
    	txtNome.setText(studente.getNome());
    	txtCognome.setText(studente.getCognome());
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	String corso = cmbCorsi.getValue();
    	if (corso==null) {
    		txtRisultato.setText("Selezionare un corso.");
    		return;
    	}
    	txtRisultato.clear();
    	String matricola = txtStudente.getText();
    	int matricolaNumerica;
    	try {
    		matricolaNumerica = Integer.parseInt(matricola);
    	} catch (NumberFormatException e) {
    		e.printStackTrace();
    		txtRisultato.setText("Inserire un numero di matricola.");
    		return;
    	}
    	txtRisultato.clear();
    	if (model.getStudenteByMatricola(matricolaNumerica)==null) {
    		txtRisultato.setText("Studente non presente nel database.");
    		return;
    	}
    	txtRisultato.clear();
    	boolean trovato = false;
    	trovato = model.isStudenteIscrittoCorso(corso, matricolaNumerica);
    	if (trovato == true)
    		txtRisultato.setText("Studente iscritto a questo corso.");
    	else
    		txtRisultato.setText("Studente non iscritto a questo corso.");

    }

    @FXML
    void doReset(ActionEvent event) {
    	cmbCorsi.setValue("");
    	txtStudente.clear();
    	chcCompleta.setSelected(false);
    	txtNome.clear();
    	txtCognome.clear();
    	txtRisultato.clear();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert chcCompleta != null : "fx:id=\"chcCompleta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStudente != null : "fx:id=\"txtStudente\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		
		this.model = model;
		
		for (Corso c : model.getTuttiICorsi())
			cmbCorsi.getItems().add(c.getNome());
		
	}

}
