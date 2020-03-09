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
		
		if(this.choice.equalsIgnoreCase("L") || this.choice.equalsIgnoreCase("N") || this.choice.equalsIgnoreCase("M") || this.choice.equalsIgnoreCase("C") || this.choice.equalsIgnoreCase("B") || this.choice.equalsIgnoreCase("E")) {
			this.request.put("choice", choice);
			this.request.put("usertype", "User");
			this.request.put("mode", "GETCHOICE");
			MainDispatcher.getInstance().callAction("Dato", "doControl", request);
		}else {
			System.out.println("\nScelta non consentita!\n");
			MainDispatcher.getInstance().callView("dato.DatoUser", null);
			}
		
	}

}
