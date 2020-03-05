package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.AreaDTO;
import it.contrader.main.MainDispatcher;


/**
 * 
 *
 * Si osservi che alla View arrivano solo oggetti di tipo DTO!
 */
public class UserAreaView extends AbstractView {

	private Request request;
	private String choice;

	public UserAreaView() {
		
	}

	/**
	 * Mostra la lista delle aree
	 */
	@Override
	public void showResults(Request request) {
		if (request != null) {
			System.out.println("\n------------------- Gestione Aree ----------------\n");
			System.out.println("ID\tNome");
			System.out.println("----------------------------------------------------\n");
			
			@SuppressWarnings("unchecked")
			List<AreaDTO> area = (List<AreaDTO>) request.get("area");
			for (AreaDTO a: area)
				System.out.println(a);
			System.out.println();
		}
	}

	/**
	 * Chiede all'utente un input (lettera da tastiera) per la choice (vedi AreaController). 
	 * Mette la modalità GETCHOICE nella mode.
	 */
	@Override
	public void showOptions() {
		System.out.println("----------MENU AREE----------:");
		System.out.println("          Scegli l'operazione da effettuare:");
		System.out.println("[D]isponibili [I]nserisci [B]ack [E]sci");

		this.choice = getInput();

		
	}
	
	/**
	 * Impacchetta la request e la manda allo AreaController.
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("choice", choice);
		request.put("mode", "GETCHOICE");
		MainDispatcher.getInstance().callAction("Area", "doControl", this.request);
	}

}
