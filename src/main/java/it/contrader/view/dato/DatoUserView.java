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
		System.out.println("[L]Leggi [N]Aggiungi nuovo set [M]Modifica  [C]Cancella ");
		System.out.println("[B]Back [E]Esci");
		
		choice = this.getInput();

	}

	@Override
	public void submit() {
		
		request = new Request();
		
		this.request.put("choice", choice);
		this.request.put("usertype", "User");
		this.request.put("mode", "GETCHOICE");
		
		if(this.choice.equalsIgnoreCase("T") || this.choice.equalsIgnoreCase("U") || this.choice.equalsIgnoreCase("B") || this.choice.equalsIgnoreCase("E"))
			MainDispatcher.getInstance().callAction("Dato", "doControl", request);
		else {
			System.out.println("\nScelta non consentita!\n");
			MainDispatcher.getInstance().callView("DatoUser", null);
			}
		
	}

}
