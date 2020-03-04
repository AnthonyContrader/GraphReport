package it.contrader.view.tag;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class TagInsertView extends AbstractView{
	
	private Request request;

	private String nome;
	private final String mode = "INSERT";

	public TagInsertView() {
	}
	
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Inserimento andato a buon fine.\n");
			MainDispatcher.getInstance().callView("Tag", null);
		}
	}

	@Override
	public void showOptions() {
			System.out.println("Inserisci nome del tag:");
			nome = getInput();
	}
	
	@Override
	public void submit() {
		request = new Request();
		request.put("username", nome);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Tag", "doControl", request);
	}

}
