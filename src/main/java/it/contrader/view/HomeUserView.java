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
		System.out.println("[D]Gestisci i DataSet [A]Gestione Area [T]Gestione Tag [P]Gestisci le info personali [E]Esci\n");
		
		choice = this.getInput();

	}

	@Override
	public void submit() {
		
		request = new Request();
		
		this.request.put("choice", choice);
		
		switch (choice.toUpperCase()) {
		
		case "A":
			this.request.put("mode", "GETCHOICE");
        	MainDispatcher.getInstance().callAction("Area", "doControl", request);
			break;
			
		case "T":
			this.request.put("mode", "GETCHOICE");
        	MainDispatcher.getInstance().callAction("Tag", "doControl", request);
			break;
		
		case "D":
			this.request.put("mode", "GETCHOICE");
        	MainDispatcher.getInstance().callAction("Dato", "doControl", request);
			break;
			
		case "P":
			this.request.put("mode", "GETCHOICE");
        	MainDispatcher.getInstance().callAction("Utente", "doControl", request);
			break;

		case "E":
			MainDispatcher.getInstance().callAction("Login", "doControl", null);
			break;

		default:
			System.out.println("\nScelta non consentita!\n");
			MainDispatcher.getInstance().callView("HomeUser", null);
		}
	}

}
