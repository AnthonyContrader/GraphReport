package it.contrader.view.area;

import it.contrader.controller.Request;

import it.contrader.dto.AreaDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

/**
 * 
 *
 *Si osservi che alla View arrivano solo oggetti di tipo DTO!
 */
public class AreaReadView extends AbstractView {

	private int id;
	private Request request;
	private final String mode = "READ";

	public AreaReadView() {
	}

	/**
	 * Se la request è null (ovvero quando arriva dal controller con mode GETCHOICE e choice L 
	 * il metodo è vuoto.
	 * 
	 * Altrimenti se arriva con un'area nella request (ovvero quando arriva
	 * dal controller con mode READ) mostra l'area. In questo caso torna alla AreaView senza eseguire
	 * gli altri due metodi
	 */
	@Override
	public void showResults(Request request) {
		if (request != null) {
			AreaDTO area = (AreaDTO) request.get("area");
			System.out.println(area);
			MainDispatcher.getInstance().callView("Area", null);
		}
	}

	
	/**
	 * chiede all'utente di inserire l'id dell'area da leggere
	 */
	@Override
	public void showOptions() {
		System.out.println("Inserisci l'ID dell'area:");
		id = Integer.parseInt(getInput());
	}

	/**
	 * impacchetta una request con l'id dell'area da leggere e la manda al controller tramite il Dispatcher
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Area", "doControl", request);
	}

}
