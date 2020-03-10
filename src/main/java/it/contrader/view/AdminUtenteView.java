package it.contrader.view;
import java.util.List;
import it.contrader.controller.Request;
import it.contrader.dto.UtenteDTO;
import it.contrader.main.MainDispatcher;

public class AdminUtenteView extends AbstractView {


	private Request request;
	private String choice;

	public AdminUtenteView() {
		
	}

	/**
	 * Mostra la lista utenti
	 */
	@Override
	public void showResults(Request request) {
		if (request != null) {
			System.out.println("\n------------------- Visualizza profilo utente ----------------\n");
			System.out.println("ID\tNome\tCognome\tEmail\\tCitta\\tNazione\\tIdUser");
			System.out.println("----------------------------------------------------\n");
			
			@SuppressWarnings("unchecked")
			List<UtenteDTO> utenti = (List<UtenteDTO>) request.get("utenti");
			for (UtenteDTO u: utenti)
				System.out.println(u);
			System.out.println();
		}
	}

	/**
	 * Chiede all'utente un input (lettera da tastiera) per la choice (vedi UtenteController). 
	 * Mette la modalità GETCHOICE nella mode.
	 */
	@Override
	public void showOptions(){
		System.out.println("---------MENU ANAGRAFICA UTENTI ADMIN----------:");
		System.out.println("       Scegli l'operazione da effettuare:");
		System.out.println("       [L]eggi [C]ancella [B]ack [E]sci");

		this.choice = getInput();
		if(this.choice.equalsIgnoreCase("ADMIN") || this.choice.equalsIgnoreCase("I") || this.choice.equalsIgnoreCase("M")
				|| this.choice.equalsIgnoreCase("P") || this.choice.equalsIgnoreCase("X")) {
			System.out.println("\nScelta non consentita!\n");
			MainDispatcher.getInstance().callView("AdminUtente", null);
		}
		if(this.choice.equalsIgnoreCase("B")) {
			this.choice = "X";
		}
		
	}
	
	/**
	 * Impacchetta la request e la manda allo UtenteController.
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("choice", choice);
		request.put("mode", "GETCHOICE");
		this.request.put("usertype", "Admin");
		MainDispatcher.getInstance().callAction("Utente", "doControl", this.request);
	}

}
