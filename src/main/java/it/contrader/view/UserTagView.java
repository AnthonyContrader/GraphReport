package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.TagDTO;
import it.contrader.main.MainDispatcher;

public class UserTagView extends AbstractView{

	private Request request;
	private String choice;
	
	public UserTagView() {
	}

	@Override
	public void showResults(Request request) {
		if (request != null) {
			System.out.println("\n------------------- TAG ----------------\n");
			System.out.println("ID\tNome TAG");
			System.out.println("----------------------------------------------------\n");
			
			@SuppressWarnings("unchecked")
			List<TagDTO> tags = (List<TagDTO>) request.get("Tag");
			for (TagDTO u: tags)
				System.out.println(u);
			System.out.println();
		}
	}

	@Override
	public void showOptions() {
		System.out.println("\n-------------MENU UTENTE------------\n");
		System.out.println("          Scegli l'operazione da effettuare:");
		System.out.println("[D]isponibili [I]nserisci [B]ack [E]sci");
		this.choice = getInput();
	}

	@Override
	public void submit() {
		request = new Request();
		request.put("choice", choice);
		request.put("mode", "GETCHOICE");
		MainDispatcher.getInstance().callAction("Tag", "doControl", this.request);
	}
}
