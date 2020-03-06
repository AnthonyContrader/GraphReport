package it.contrader.view.tag;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class TagUpdateView extends AbstractView{
	
	private Request request;

	private int id;
	private String nome;
	private final String mode = "UPDATE";

	public TagUpdateView() {
	}

	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Modifica andata a buon fine.\n");
			MainDispatcher.getInstance().callView("Tag", null);
		}
	}

	@Override
	public void showOptions() {
		try {
			System.out.println("Inserisci id del Tag:");
			id = Integer.parseInt(getInput());
			System.out.println("Inserisci il nome del Tag:");
			nome = getInput();
		} catch (Exception e) {

		}
	}

	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("nome", nome);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Tag", "doControl", request);
	}

}
