package it.contrader.controller;

import java.util.List;

import it.contrader.dto.AreaDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.AreaService;

/**
 * 
 * @author Vittorio
 *
 *Si osservi che nel Controller compaiono solo oggetti del DTO e non del Model!
 */
public class AreaController implements Controller {

	/**
	 * definisce il pacchetto di vista area.
	 */
	private static String sub_package = "area.";
	
	private AreaService areaService;
	/**
	 * Costruisce un oggetto di tipo AreaService per poterne usare i metodi
	 */
	public AreaController() {
		this.areaService = new AreaService();
	}
	
	
	
	/**
	 * Metodo dell'interfaccia Controller. Estrae dalla request la mode
	 * (che riceve dalle view specifiche e può essere la richesta di una 
	 * scelta da parte dell'utente "GETCHOICE") e la scelta dell'utente.
	 * 
	 * Se la modalità corrisponde ad una CRUD il controller chiama i service,
	 * altrimenti rimanda alla View della CRUD per richiedere i parametri
	 */
	@Override
	public void doControl(Request request) {
		
		//Estrae dalla request mode e choice
		String mode = (String) request.get("mode");
		
		String choice = (String) request.get("choice");

		//Definisce i campi della classe (serviranno sempre, tanto vale definirli una sola volta)
		int id;
		String nome;

		switch (mode) {
		
		// Arriva qui dalla UserReadView. Invoca il Service con il parametro id e invia alla UserReadView uno user da mostrare 
		case "READ":
			List <AreaDTO> areaDTO = areaService.getAll();
			request.put("area", areaDTO);
			MainDispatcher.getInstance().callView(sub_package + "AreaRead", request);
			break;
		
		// Arriva qui dalla AreaInsertView. Estrae i parametri da inserire e chiama il service per inserire un'area con questi parametri
		case "INSERT":
			nome = request.get("nome").toString();
			
			//costruisce l'oggetto area da inserire
			AreaDTO areatoinsert = new AreaDTO(nome);
			//invoca il service
			areaService.insert(areatoinsert);
			request = new Request();
			request.put("mode", "mode");
			//Rimanda alla view con la risposta
			MainDispatcher.getInstance().callView(sub_package + "AreaInsert", request);
			break;
		
		// Arriva qui dalla UserDeleteView. Estrae l'id dell'utente da cancellare e lo passa al Service
		case "DELETE":
			id = Integer.parseInt(request.get("id").toString());
			//Qui chiama il service
			areaService.delete(id);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "AreaDelete", request);
			break;
		
		// Arriva qui dalla UserUpdateView
		case "UPDATE":
			id = Integer.parseInt(request.get("id").toString());
			nome = request.get("nome").toString();
			AreaDTO areatoupdate = new AreaDTO(nome);
			areatoupdate.setId(id);
			areaService.update(areatoupdate);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "AreaUpdate", request);
			break;
			
		//Arriva qui dalla UserView Invoca il Service e invia alla UserView il risultato da mostrare 
		case "AREALIST":
			List<AreaDTO> areeDTO = areaService.getAll();
			request.put("aree", areeDTO);
			MainDispatcher.getInstance().callView("Area", request);
			break;
			
		//Esegue uno switch sulla base del comando inserito dall'utente e reindirizza tramite il Dispatcher alla View specifica per ogni operazione
		//con REQUEST NULL (vedi una View specifica)
		case "GETCHOICE":
					
					//toUpperCase() mette in maiuscolo la scelta
			switch (choice.toUpperCase()) {
			
			case "ADMIN":
				MainDispatcher.getInstance().callView("AdminArea", null);
				break;
			
			case "D":
				MainDispatcher.getInstance().callView(sub_package + "AreaRead", null);
				break;
			
			case "A":
				MainDispatcher.getInstance().callView("UserArea", null);
				break;
				
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "AreaInsert", null);
				break;
				
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "AreaUpdate", null);
				break;
				
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "AreaDelete", null);
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
				MainDispatcher.getInstance().callView(request.get("usertype").toString()+"Area", null);
			}
			
		default:
			MainDispatcher.getInstance().callView(request.get("usertype").toString()+"Area", null);
		}
	}
}
