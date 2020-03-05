package it.contrader.view.dato;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class DatoInsertView extends AbstractView{
	private Request request;

	private int idUtente;
	private String area;
	private String tag;
	private float valore;
	
	private final String mode = "INSERT";

	public DatoInsertView() {
	}
	
	/**
	 * Se la request non è nulla (ovvero se si arriva dalla mode INSERT del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Inserimento andato a buon fine.\n");
			MainDispatcher.getInstance().callView("dato.DatoUser", null);
		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da inserire
	 */
	@Override
	public void showOptions() {
		
			System.out.println("Inserisci id dell'utente:");
			idUtente = Integer.parseInt(getInput());
			System.out.println("Inserisci area del DataSet:");
			area = getInput();			
			System.out.println("Inserisci tag:");
			tag = getInput();
			System.out.println("Inserisci valore:");
			valore = Float.parseFloat(getInput());
	}


	/**
	 * Impacchetta la request con i dati inseriti nel metodo showOption()
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("idUtente", idUtente);
		request.put("area", area);
		request.put("tag", tag);
		request.put("valore", valore);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Dato", "doControl", request);
	}


}
