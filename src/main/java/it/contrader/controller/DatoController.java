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
		int utente;
		String area;
		String tag;
		float valore;
		
		switch (mode) {
		
		// Arriva qui dalla DatoReadView. Invoca il Service con il parametro id e invia alla DatoReadView uno user da mostrare 
		case "READ":
			utente = Integer.parseInt(request.get("idUtente").toString());
			List<DatoDTO> datoDTO = datoService.getAllByUtente(utente);
			request.put("dato", datoDTO);
			MainDispatcher.getInstance().callView(sub_package + "DatoRead", request);
			break;
			
		case "READALL":
			List<DatoDTO> datiDTO = datoService.getAll();
			request.put("dato", datiDTO);
			MainDispatcher.getInstance().callView(sub_package + "DatoReadAll", request);
			break;
		
		// Arriva qui dalla DatoInsertView. Estrae i parametri da inserire e chiama il service per inserire un dato con questi parametri
		case "INSERT":
			tag = request.get("tag").toString();
			area = request.get("area").toString();
			utente = Integer.parseInt(request.get("idUtente").toString());
			valore = Float.parseFloat(request.get("valore").toString());
			
			//costruisce l'oggetto dato da inserire
			DatoDTO datotoinsert = new DatoDTO(utente, area, tag, valore);
			//invoca il service
			request = new Request();
			request.put("mode", "mode");
			datoService.insert(datotoinsert);
			//Rimanda alla view con la risposta
			MainDispatcher.getInstance().callView(sub_package + "DatoInsert", request);
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
			utente = Integer.parseInt(request.get("idUtente").toString());
			tag = request.get("tag").toString();
			area = request.get("area").toString();
			valore = Float.parseFloat(request.get("valore").toString());
			DatoDTO datotoupdate = new DatoDTO(utente, area, tag, valore);
			datotoupdate.setId(id);
			datoService.update(datotoupdate);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "DatoUpdate", request);
			break;
			
		//Arriva qui dalla DatoView Invoca il Service e invia alla DatoView il risultato da mostrare 
		//case "USERLIST":
		//	List<DatoDTO> datiDTO = datoService.getAll();
			//Impacchetta la request con la lista dei dati
		//	request.put("dati", datiDTO);
		//	MainDispatcher.getInstance().callView("Dato", request);
		//	break;
			
		//Esegue uno switch sulla base del comando inserito del dato e reindirizza tramite il Dispatcher alla View specifica per ogni operazione
		//con REQUEST NULL (vedi una View specifica)
		case "GETCHOICE":
					
					//toUpperCase() mette in maiuscolo la scelta
			switch (choice.toUpperCase()) {
			case "ADMIN":
				MainDispatcher.getInstance().callView(sub_package + "DatoAdmin", null);
				break;
				
			case "D":
				MainDispatcher.getInstance().callView(sub_package + "DatoUser", null);
				break;
			
			case "N":
				MainDispatcher.getInstance().callView(sub_package + "DatoInsert", null);
				break;
				
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "DatoRead", null);
				break;
				
			case "T":
				MainDispatcher.getInstance().callView(sub_package + "DatoReadAll", null);
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
				MainDispatcher.getInstance().callView("Home"+request.get("usertype").toString(), null);
				break;
				
			default:
				System.out.println("\nScelta non consentita!\n");
				MainDispatcher.getInstance().callView(sub_package+"Dato"+request.get("usertype").toString(), null);
			}
			
		default:
			MainDispatcher.getInstance().callView(sub_package+"Dato"+request.get("usertype").toString(), null);
		}
		
	}
}
