package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudenteByMatricola(int matricolaNumerica) {
		
		final String sql = "select matricola, cognome, nome, CDS "
				+ "from studente "
				+ "where matricola = ?";

		Studente studente = null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricolaNumerica);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");

				System.out.println(matricola + " " + cognome + " " + nome + " " + CDS);

				studente = new Studente(matricola, cognome, nome, CDS);
			}

			conn.close();
			
			return studente;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	public LinkedList<Studente> getIscrittiCorso(String corso) {
		
		final String sql = "select s.matricola, cognome, s.nome, CDS "
				+ "from studente s, iscrizione i, corso c "
				+ "where s.matricola = i.matricola and i.codins = c.codins and c.nome = ?";

		LinkedList<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("s.matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");

				System.out.println(matricola + " " + cognome + " " + nome + " " + CDS);
				
				Studente s = new Studente(matricola, cognome, nome, CDS);
				studenti.add(s);
			}

			conn.close();
			
			return studenti;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

}
