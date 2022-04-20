package it.polito.tdp.lab04.model;

public class Corso {
	
	private String codins; 
	private int numeroCrediti; 
	private String nome;
	private int periodoDidattico;

	public Corso(String codins, int numeroCrediti, String nome, int periodoDidattico) {
		super();
		this.codins = codins;
		this.numeroCrediti = numeroCrediti;
		this.nome = nome;
		this.periodoDidattico = periodoDidattico;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return codins + "\t" + numeroCrediti + "\t" + nome + "\t" + periodoDidattico;
	}

}
