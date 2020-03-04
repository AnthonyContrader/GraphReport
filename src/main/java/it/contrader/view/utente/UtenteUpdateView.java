package it.contrader.view.utente;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;


public class UtenteUpdateView extends AbstractView {
	private Request request;

	private int id;
	private String nome;
	private String cognome;
	private String email;
	private String citta;
	private String nazione;
	private final String mode = "UPDATE";

	public UtenteUpdateView() {
	}

	/**
	 * Se la request non è nulla (ovvero se si arriva dalla mode UPDATE del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Modifica andata a buon fine.\n");
			MainDispatcher.getInstance().callView("Utente", null);
		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da modificare
	 */
	@Override
	public void showOptions() {
		try {
			System.out.println("Inserisci id dell'utente:");
			id = Integer.parseInt(getInput());
			System.out.println("Inserisci il nome:");
			nome = getInput();
			System.out.println("Inserisci il cognome:");
			cognome = getInput();
			System.out.println("Inserisci l'e-mail:");
			email = getInput();
			System.out.println("Inserisci la città:");
			citta = getInput();
			System.out.println("Inserisci la nazione:");
			nazione = getInput();
		} catch (Exception e) {

		}
	}


	/**
	 * Impacchetta la request con i dati inseriti nel metodo showOption()
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("nome", nome);
		request.put("cognome", cognome);
		request.put("email", email);
		request.put("citta", citta);
		request.put("nazione", nazione);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Utente", "doControl", request);
	}

}
