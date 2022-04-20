package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model() {
		corsoDao = new CorsoDAO();
		studenteDao = new StudenteDAO();
	}

	public List<Corso> getTuttiICorsi() {
		return corsoDao.getTuttiICorsi();
	}

	public Studente getStudenteByMatricola(int matricolaNumerica) {
		return studenteDao.getStudenteByMatricola(matricolaNumerica);
	}

	public LinkedList<Studente> getIscrittiCorso(String corso) {
		return studenteDao.getIscrittiCorso(corso);
	}

	public List<Corso> getCorsiStudente(int matricolaNumerica) {
		return corsoDao.getCorsiStudente(matricolaNumerica);
	}

	public boolean isStudenteIscrittoCorso(String corso, int matricolaNumerica) {
		LinkedList<Studente> studenti = getIscrittiCorso(corso);
		for (Studente s : studenti)
			if (s.getMatricola()==matricolaNumerica)
				return true;
		return false;
	}

}
