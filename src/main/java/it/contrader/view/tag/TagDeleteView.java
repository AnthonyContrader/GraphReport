package it.contrader.view.tag;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class TagDeleteView  extends AbstractView{

	private Request request;
	private int id;
	private final String mode = "DELETE";

	public TagDeleteView() {
	}

	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Cancellazione andata a buon fine.\n");
			MainDispatcher.getInstance().callView("Tag", null);
		}
	}
	
	@Override
	public void showOptions() {
			System.out.println("Inserisci id del tag:");
			id = Integer.parseInt(getInput());
	}

	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Tag", "doControl", request);
	}
}
