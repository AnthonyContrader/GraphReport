package it.contrader.view.dato;


import it.contrader.controller.Request;
import it.contrader.view.AbstractView;
import it.contrader.main.MainDispatcher;

public class DatoAdminView extends AbstractView{

	private String choice;
	
	private Request request;
	
	@Override
	public void showResults(Request request) {
		
	}

	@Override
	public void showOptions() {
		System.out.println("-------------MENU DATASET ADMIN------------\n");
		System.out.println("[T]Visualizza Tutti [U]Visualizza per Utente");
		System.out.println("[B]Back [E]Esci");
		
		choice = this.getInput();

	}

	@Override
	public void submit() {
		
		request = new Request();
		
		this.request.put("choice", choice);
		this.request.put("usertype", "Admin");
		
		
		switch (choice) {
		
		case "t":
			this.request.put("mode", "GETCHOICE");
			MainDispatcher.getInstance().callAction("Dato", "doControl", request);
			break;
			
		case "u":
			this.request.put("choice", "l");
			this.request.put("mode", "GETCHOICE");
			MainDispatcher.getInstance().callAction("Dato", "doControl", request);
			break;
			
		case "b":
			this.request.put("mode", "GETCHOICE");
			MainDispatcher.getInstance().callAction("Dato", "doControl", request);
			break;
			
		case "e":
			this.request.put("mode", "GETCHOICE");
			MainDispatcher.getInstance().callAction("Login", "doControl", request);
			break;
		

		default:
			MainDispatcher.getInstance().callAction("Dato", "doControl", request);
		}
	}

}
