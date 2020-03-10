package it.contrader.controller;

import java.util.List;

import it.contrader.dto.UtenteDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.UtenteService;

/**
 * 
 * @author Vittorio
 *
 *Si osservi che nel Controller compaiono solo oggetti del DTO e non del Model!
 */
public class UtenteController implements Controller {

	/**
	 * definisce il pacchetto di vista user.
	 */
	private static String sub_package = "utente.";
	
	private UtenteService utenteService;
	/**
	 * Costruisce un oggetto di tipo UserService per poterne usare i metodi
	 */
	public UtenteController() {
		this.utenteService = new UtenteService();
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
		String cognome;
		String email;
		String citta;
		String nazione;
		int iduser;

		switch (mode) {
		
		// Arriva qui dalla UserReadView. Invoca il Service con il parametro id e invia alla UserReadView uno user da mostrare 
		case "READ":
			//id = Integer.parseInt(request.get("id").toString());
			iduser = Integer.parseInt(request.get("iduser").toString());
			UtenteDTO utenteDTO = utenteService.read(iduser);
			request.put("utente", utenteDTO);
			MainDispatcher.getInstance().callView(sub_package + "UtenteRead", request);
			break;
		
		// Arriva qui dalla UserInsertView. Estrae i parametri da inserire e chiama il service per inserire uno user con questi parametri
		case "INSERT":
			nome = request.get("nome").toString();
			cognome = request.get("cognome").toString();
			email = request.get("email").toString();
			citta = request.get("citta").toString();
			nazione = request.get("nazione").toString();
			iduser = Integer.parseInt(request.get("iduser").toString());		
			//costruisce l'oggetto user da inserire
			UtenteDTO utentetoinsert = new UtenteDTO(nome,cognome,email,citta,nazione,iduser);
			//invoca il service
			utenteService.insert(utentetoinsert);
			request = new Request();
			request.put("mode", "mode");
			//Rimanda alla view con la risposta
			MainDispatcher.getInstance().callView(sub_package + "UtenteInsert", request);
			break;
		
		// Arriva qui dalla UserDeleteView. Estrae l'id dell'utente da cancellare e lo passa al Service
		case "DELETE":
			id = Integer.parseInt(request.get("id").toString());
			//Qui chiama il service
			utenteService.delete(id);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "UtenteDelete", request);
			break;
		
		// Arriva qui dalla UserUpdateView
		case "UPDATE":
			iduser = Integer.parseInt(request.get("iduser").toString());
			nome = request.get("nome").toString();
			cognome = request.get("cognome").toString();
			email = request.get("email").toString();
			citta = request.get("citta").toString();
			nazione = request.get("nazione").toString();
			UtenteDTO utentetoupdate = new UtenteDTO(nome,cognome,email,citta,nazione,iduser);
			utenteService.update(utentetoupdate);
			
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "UtenteUpdate", request);
			break;
			
		//Arriva qui dalla UserView Invoca il Service e invia alla UserView il risultato da mostrare 
		case "UTENTELIST":
			List<UtenteDTO> utentiDTO = utenteService.getAll();
			//Impacchetta la request con la lista degli user
			request.put("utenti", utentiDTO);
			MainDispatcher.getInstance().callView("Utente", request);
			break;
			
		//Esegue uno switch sulla base del comando inserito dall'utente e reindirizza tramite il Dispatcher alla View specifica per ogni operazione
		//con REQUEST NULL (vedi una View specifica)
		case "GETCHOICE":
					
					//toUpperCase() mette in maiuscolo la scelta
			switch (choice.toUpperCase()) {
			
			case "L":
				MainDispatcher.getInstance().callView(sub_package + "UtenteRead", null);
				break;
				
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "UtenteInsert", null);
				break;
				
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "UtenteUpdate", null);
				break;
				
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "UtenteDelete", null);
				break;
				
			case "P":
				MainDispatcher.getInstance().callView("Utente", null);
				break;
				
			case "E":
				MainDispatcher.getInstance().callView("Login", null);
				break;

			case "B":
				MainDispatcher.getInstance().callView("HomeUser", null);
				break;
			case "X":
				MainDispatcher.getInstance().callView("HomeAdmin", null);
				break;
			case "ADMIN":
				MainDispatcher.getInstance().callView("AdminUtente", null);
				break;
			default:
				System.out.println("\nScelta non consentita!\n");
				MainDispatcher.getInstance().callView(request.get("usertype")+"Utente", null);
			}
			
		default:
			System.out.println("\nScelta non consentita!\n");
			MainDispatcher.getInstance().callView(request.get("usertype")+"Utente", null);
		}
	}
}
