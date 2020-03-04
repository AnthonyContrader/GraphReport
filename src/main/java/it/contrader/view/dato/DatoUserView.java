package it.contrader.view.dato;


import it.contrader.controller.Request;
import it.contrader.view.AbstractView;
import it.contrader.main.MainDispatcher;

public class DatoUserView extends AbstractView{

	private String choice;
	
	private Request request;
	
	@Override
	public void showResults(Request request) {
		
	}

	@Override
	public void showOptions() {
		System.out.println("-------------MENU DATASET------------\n");
		System.out.println("[L]Leggi [N]Aggiungi nuovo set [D]Aggiungui dati a set esistente");
		System.out.println("[M]Modifica  [C]Cancella [B]Back [E]Esci");
		
		choice = this.getInput();

	}

	@Override
	public void submit() {
		
		request = new Request();
		
		this.request.put("choice", choice);
		
		switch (choice) {
		
		case "d":
			this.request.put("mode", "GETCHOICE");
        	MainDispatcher.getInstance().callAction("Dato", "doControl", request);
			break;
			
		case "p":
			this.request.put("mode", "GETCHOICE");
        	MainDispatcher.getInstance().callAction("Utente", "doControl", request);
			break;

		case "e":
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
			break;

		default:
			System.out.println("Scelta non consentita!");
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
		}
	}

}
