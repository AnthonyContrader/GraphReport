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
	}

	/**
	 * Chiede all'utente un input (lettera da tastiera) per la choice (vedi AreaController). 
	 * Mette la modalità GETCHOICE nella mode.
	 */
	@Override
	public void showOptions() {
		System.out.println("--------------MENU AREE------------:");
		System.out.println(" Scegli l'operazione da effettuare:  ");
		System.out.println("[D]isponibili [I]nserisci [B]ack [E]sci");

		this.choice = getInput();
		
	}
	
	/**
	 * Impacchetta la request e la manda allo AreaController.
	 */
	@Override
	public void submit() {
		if(this.choice.equalsIgnoreCase("D") || this.choice.equalsIgnoreCase("I") || this.choice.equalsIgnoreCase("B") || this.choice.equalsIgnoreCase("E")) {
			request = new Request();
			this.request.put("choice", choice);
			this.request.put("mode", "GETCHOICE");
			this.request.put("usertype", "User");
			MainDispatcher.getInstance().callAction("Area", "doControl", this.request);
		}else {
			System.out.println("\nScelta non consentita!\n");
			MainDispatcher.getInstance().callView("UserArea", null);
		}

		
	}

}
