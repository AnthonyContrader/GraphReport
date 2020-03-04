package it.contrader.view.tag;

import it.contrader.controller.Request;
import it.contrader.dto.TagDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class TagReadView extends AbstractView{
	private int id;
	private Request request;
	private final String mode = "READ";

	public TagReadView() {
	}
	
	@Override
	public void showResults(Request request) {
		if (request != null) {
			TagDTO tags = (TagDTO) request.get("tag");
			System.out.println(tags);
			MainDispatcher.getInstance().callView("Tag", null);
		}
	}

	@Override
	public void showOptions() {
		System.out.println("Inserisci l'ID del tag:");
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
