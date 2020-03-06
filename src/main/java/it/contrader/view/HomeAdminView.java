/**
 * Manage a Business Owner view
 */

package it.contrader.view;


/**
 * Per Ulteriori dettagli vedi Guida sez 9 View.
 * Per la descrizione dei metodi vedi l'interfaccia View in questo pacchetto.
 */
import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class HomeAdminView extends AbstractView {

    private String choice;
    
	private Request request;

	/**
	 * Se la request non è nulla mostra un messaggio di benvenuto
	 */
    public void showResults(Request request) {
    	if(request!=null) {
    	System.out.println("\n Benvenuto in SAMPLE PROJECT "+request.get("username").toString() + "\n");
    	}
    }


    /**
     * Chiede all'utente di effettuare una scelta (da console)
     */
    public void showOptions() {
        System.out.println("-------------MENU ADMIN------------:");
        System.out.println(" Seleziona cosa vuoi gestire:");
        System.out.println("[U]tenti [I]Info Utenti [A]Gestione Aree");
        System.out.println("[T]Gestione Tag [D]Gestione DataSet [E]sci");
        //Il metodo che salva l'input nella stringa choice.
        //getInput() è definito in AbstractView.
        choice = this.getInput();
    }

    /**
     * Impacchetta una request (in base alla scelta sarà diversa) che invia ai controller tramite il
     * Dispatcher
     */
    public void submit() {    
    	//crea una nuova Request (vedi classe Request)
    	request = new Request();
    	this.request.put("choice", "admin");
        switch (choice) {
        case "u":
        	this.request.put("mode", "USERLIST");
        	MainDispatcher.getInstance().callAction("User", "doControl", request);
        	break;
        	
        case "i":
        	this.request.put("mode", "GETCHOICE");
        	MainDispatcher.getInstance().callAction("Utente", "doControl", request);
        	break;
        	
        case "a":
        	this.request.put("mode", "GETCHOICE");
        	MainDispatcher.getInstance().callAction("Area", "doControl", request);
        	break;
        	
        case "t":
        	this.request.put("mode", "GETCHOICE");
        	MainDispatcher.getInstance().callAction("Tag", "doControl", request);
        	break;
        	
        case "d":
        	this.request.put("mode", "GETCHOICE");
        	MainDispatcher.getInstance().callAction("Dato", "doControl", request);
        	break;
        	
        case "e":
        	MainDispatcher.getInstance().callAction("Login", "doControl", null);
        	break;
        default:
        	
            System.out.println("\nScelta non consentita!\n");
        	MainDispatcher.getInstance().callView("HomeAdmin", null);
        }
    }
}
