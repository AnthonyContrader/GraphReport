package it.contrader.view.utente;

import it.contrader.controller.Request;

import it.contrader.dto.UtenteDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

/**
 * 
 * @author Vittorio
 *
 *Si osservi che alla View arrivano solo oggetti di tipo DTO!
 */
public class UtenteReadView extends AbstractView {

	private int iduser;
	private Request request;
	private final String mode = "READ";

	public UtenteReadView() {
	}

	/**
	 * Se la request è null (ovvero quando arriva dal controller con mode GETCHOICE e choice L 
	 * il metodo è vuoto.
	 * 
	 * Altrimenti se arriva con uno user nella request (ovvero quando arriva
	 * dal controller con mode READ) mostra lo user. In questo caso torna alla UserView senza eseguire
	 * gli altri due metodi
	 */
	@Override
	public void showResults(Request request) {
		if (request != null) {
			UtenteDTO utente = (UtenteDTO) request.get("utente");
			if(utente.getId()==null)
				System.out.println("\n le informazioni dell'utente non sono presenti \n");
			else
				System.out.println(utente);
			MainDispatcher.getInstance().callView(request.get("usertype").toString()+"Utente", null);
			//nella view per aggiungere il tipo utente +request.get("usertype").toString()
		}
	}

	
	/**
	 * chiede all'utente di inserire l'id dell'utente da leggere
	 */
	@Override
	public void showOptions() {
		System.out.println("Inserisci l'ID dell'utente:");
		iduser = Integer.parseInt(getInput());
	}

	/**
	 * impacchetta una request con l'id dell'utente da leggere e la manda al controller tramite il Dispatcher
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("iduser", iduser);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Utente", "doControl", request);
	}

}
