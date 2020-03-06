package it.contrader.controller;

import java.util.List;

import it.contrader.dto.TagDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.TagService;

public class TagController implements Controller {

	private static String sub_package = "tag.";
	
	private TagService tagService;

	public TagController() {
		this.tagService = new TagService();
	}
	
	@Override
	public void doControl(Request request) {
		String mode = (String) request.get("mode");
		String choice = (String) request.get("choice");
		int id;
		String nome;

		switch (mode) {
			case "READ":
				List <TagDTO> tagDTO = tagService.getAll();
				request.put("tag", tagDTO);
				MainDispatcher.getInstance().callView(sub_package + "TagRead", request);
				break;
			case "INSERT":
				nome = request.get("nome").toString();
				TagDTO tagtoinsert = new TagDTO(nome);
				tagService.insert(tagtoinsert);
				request = new Request();
				request.put("mode", "mode");
				MainDispatcher.getInstance().callView(sub_package + "TagInsert", request);
				break;
			case "DELETE":
				id = Integer.parseInt(request.get("id").toString());
				tagService.delete(id);
				request = new Request();
				request.put("mode", "mode");
				MainDispatcher.getInstance().callView(sub_package + "TagDelete", request);
				break;
			case "UPDATE":
				id = Integer.parseInt(request.get("id").toString());
				nome = request.get("nome").toString();
				TagDTO tagtoupdate = new TagDTO(nome);
				tagtoupdate.setId(id);
				tagService.update(tagtoupdate);
				request = new Request();
				request.put("mode", "mode");
				MainDispatcher.getInstance().callView(sub_package + "TagUpdate", request);
				break;
			case "USERLIST":
				List<TagDTO> tagsDTO = tagService.getAll();
				request.put("tag", tagsDTO);
				MainDispatcher.getInstance().callView("tag", request);
				break;
			case "GETCHOICE":
				switch (choice.toUpperCase()) {
				case "ADMIN":
					MainDispatcher.getInstance().callView("AdminTag", null);
					break;
				case "T":
					MainDispatcher.getInstance().callView("UserTag", null);
					break;
				case "D":
					MainDispatcher.getInstance().callView(sub_package + "TagRead", null);
					break;
				case "I":
					MainDispatcher.getInstance().callView(sub_package + "TagInsert", null);
					break;
				case "M":
					MainDispatcher.getInstance().callView(sub_package + "TagUpdate", null);
					break;
				case "C":
					MainDispatcher.getInstance().callView(sub_package + "TagDelete", null);
					break;
				case "E":
					MainDispatcher.getInstance().callView("Login", null);
					break;
				case "B":
					MainDispatcher.getInstance().callView("HomeUser", null);
					break;
				case "H":
					MainDispatcher.getInstance().callView("HomeAdmin", null);
					break;
				default:
					System.out.println("Scelta non consentita!");
					MainDispatcher.getInstance().callView(request.get("usertype").toString()+"Tag", null);
				}
			default:
				MainDispatcher.getInstance().callView(request.get("usertype").toString()+"Tag", null);
		}
	}

}
