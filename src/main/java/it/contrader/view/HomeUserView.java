package it.contrader.view;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class HomeUserView extends AbstractView{

	private String choice;
	
	private Request request;
	
	@Override
	public void showResults(Request request) {
		
	}

	@Override
	public void showOptions() {
		System.out.println("-------------MENU UTENTE------------\n");
		System.out.println("[D]Gestisci i DataSet [A]Visualizza Area [T]Visualizza Tag [P]Gestisci le info personali [E]Esci\n");
		
		choice = this.getInput();

	}

	@Override
	public void submit() {
		
		request = new Request();
		
		this.request.put("choice", choice);
		
		switch (choice) {
		
		case "a":
			this.request.put("mode", "GETCHOICE");
        	MainDispatcher.getInstance().callAction("Area", "doControl", request);
			break;
			
		case "t":
			this.request.put("mode", "GETCHOICE");
        	MainDispatcher.getInstance().callAction("Tag", "doControl", request);
			break;
		
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
