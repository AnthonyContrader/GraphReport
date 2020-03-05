package it.contrader.view.dato;

import it.contrader.controller.Request;

import it.contrader.dto.DatoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import java.util.List;

/**
 *Si osservi che alla View arrivano solo oggetti di tipo DTO!
 */
public class DatoReadView extends AbstractView {

	private int idUtente;
	private Request request;
	private final String mode = "READ";

	public DatoReadView() {
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
			List<DatoDTO> dati = (List<DatoDTO>) request.get("dato");
			for(DatoDTO dato : dati) {
				System.out.println(dato);
			}
			MainDispatcher.getInstance().callView("dato.DatoUser", null);
		}
	}

	
	/**
	 * chiede all'utente di inserire l'id dell'utente da leggere
	 */
	@Override
	public void showOptions() {
		System.out.println("Inserisci l'ID dell'utente:");
		idUtente = Integer.parseInt(getInput());
	}

	/**
	 * impacchetta una request con l'id dell'utente da leggere e la manda al controller tramite il Dispatcher
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("idUtente", idUtente);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Dato", "doControl", request);
	}

}
