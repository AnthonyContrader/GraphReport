package it.contrader.view.tag;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.TagDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class TagReadView extends AbstractView{
	private Request request;
	private final String mode = "READ";

	public TagReadView() {
	}
	
	@Override
	public void showResults(Request request) {
		if (request != null) {
			@SuppressWarnings("unchecked")
			List <TagDTO> listatag = (List <TagDTO>) request.get("tag");
			for(TagDTO tag : listatag) {
				System.out.printf("%10d %15s\n",tag.getId(),tag.getNomeTag());
			}
			MainDispatcher.getInstance().callView("Tag", null);
		}
	}

	@Override
	public void showOptions() {
		System.out.println("Lista Tag disponibili: ");
		System.out.printf("%10s %15s\n","id","nometag");
	}

	@Override
	public void submit() {
		request = new Request();
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Tag", "doControl", request);
	}

}
