package it.contrader.controller;

import java.util.List;

import it.contrader.dto.DatoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.DatoService;

/**
 *Si osservi che nel Controller compaiono solo oggetti del DTO e non del Model!
 */
public class DatoController implements Controller {

	/**
	 * definisce il pacchetto di vista dato.
	 */
	private static String sub_package = "dato.";
	
	private DatoService datoService;
	/**
	 * Costruisce un oggetto di tipo DatoService per poterne usare i metodi
	 */
	public DatoController() {
		this.datoService = new DatoService();
	}
	
	
	
	/**
	 * Metodo dell'interfaccia Controller. Estrae dalla request la mode
	 * (che riceve dalle view specifiche e pu� essere la richesta di una 
	 * scelta da parte dell'utente "GETCHOICE") e la scelta dell'utente.
	 * 
	 * Se la modalit� corrisponde ad una CRUD il controller chiama i service,
	 * altrimenti rimanda alla View della CRUD per richiedere i parametri
	 */
	@Override
	public void doControl(Request request) {
		
		//Estrae dalla request mode e choice
		String mode = (String) request.get("mode");
		
		String choice = (String) request.get("choice");

		//Definisce i campi della classe (serviranno sempre, tanto vale definirli una sola volta)
		int id;
		int utente;
		String area;
		String tag;
		float valore;

		switch (mode) {
		
		// Arriva qui dalla DatoReadView. Invoca il Service con il parametro id e invia alla DatoReadView uno user da mostrare 
		case "READ":
			id = Integer.parseInt(request.get("id").toString());
			DatoDTO datoDTO = datoService.read(id);
			request.put("dato", datoDTO);
			MainDispatcher.getInstance().callView(sub_package + "DatoRead", request);
			break;
		
		// Arriva qui dalla DatoInsertView. Estrae i parametri da inserire e chiama il service per inserire un dato con questi parametri
		case "INSERT":
			tag = request.get("tag").toString();
			area = request.get("area").toString();
			utente = Integer.parseInt(request.get("utente").toString());
			valore = Float.parseFloat(request.get("valore").toString());
			
			//costruisce l'oggetto dato da inserire
			DatoDTO datotoinsert = new DatoDTO(utente, area, tag, valore);
			//invoca il service
			datoService.insert(datotoinsert);
			request = new Request();
			request.put("mode", "mode");
			//Rimanda alla view con la risposta
			MainDispatcher.getInstance().callView(sub_package + "DataInsert", request);
			break;
		
		// Arriva qui dalla DatoDeleteView. Estrae l'id del dato da cancellare e lo passa al Service
		case "DELETE":
			id = Integer.parseInt(request.get("id").toString());
			//Qui chiama il service
			datoService.delete(id);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "DatoDelete", request);
			break;
		
		// Arriva qui dalla DatoUpdateView
		case "UPDATE":
			id = Integer.parseInt(request.get("id").toString());
			utente = Integer.parseInt(request.get("utente").toString());
			tag = request.get("tag").toString();
			area = request.get("area").toString();
			valore = Float.parseFloat(request.get("area").toString());
			DatoDTO datotoupdate = new DatoDTO(utente, area, tag, valore);
			datotoupdate.setId(id);
			datoService.update(datotoupdate);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "DatoUpdate", request);
			break;
			
		//Arriva qui dalla DatoView Invoca il Service e invia alla DatoView il risultato da mostrare 
		case "USERLIST":
			List<DatoDTO> datiDTO = datoService.getAll();
			//Impacchetta la request con la lista dei dati
			request.put("dati", datiDTO);
			MainDispatcher.getInstance().callView("Dato", request);
			break;
			
		//Esegue uno switch sulla base del comando inserito del dato e reindirizza tramite il Dispatcher alla View specifica per ogni operazione
		//con REQUEST NULL (vedi una View specifica)
		case "GETCHOICE":
					
					//toUpperCase() mette in maiuscolo la scelta
			switch (choice.toUpperCase()) {
			
			case "D":
				MainDispatcher.getInstance().callView(sub_package + "DatoUser", null);
				break;
			
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "DatoRead", null);
				break;
				
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "DatoInsert", null);
				break;
				
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "DatoUpdate", null);
				break;
				
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "DatoDelete", null);
				break;
				
			case "E":
				MainDispatcher.getInstance().callView("Login", null);
				break;

			case "B":
				MainDispatcher.getInstance().callView("HomeUser", null);
				break;
				
			default:
				System.out.println("Scelta non consentita!");
				MainDispatcher.getInstance().callView("HomeUser", null);
			}
			
		default:
			System.out.println("Errore imprevisto!");
			MainDispatcher.getInstance().callView("Login", null);
		}
	}
}
