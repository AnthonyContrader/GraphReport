package it.contrader.view.dato;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;


public class DatoUpdateView extends AbstractView {
	private Request request;

	private int id;
	private int idUtente;
	private String tag;
	private String area;
	private float valore;
	private final String mode = "UPDATE";

	public DatoUpdateView() {
	}

	/**
	 * Se la request non è nulla (ovvero se si arriva dalla mode UPDATE del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Modifica andata a buon fine.\n");
			MainDispatcher.getInstance().callView("dato.DatoUser", null);
		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da modificare
	 */
	@Override
	public void showOptions() {
		try {
			System.out.println("Inserisci id del DataSet:");
			id = Integer.parseInt(getInput());
			System.out.println("Inserisci id dell'utente:");
			idUtente = Integer.parseInt(getInput());
			System.out.println("Inserisci nuova area:");
			area = getInput();
			System.out.println("Inserisci nuovo tag:");
			tag = getInput();
			System.out.println("Inserisci nuovo valore:");
			valore = Float.parseFloat(getInput().toString());
		} catch (Exception e) {

		}
	}


	/**
	 * Impacchetta la request con i dati inseriti nel metodo showOption()
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("idUtente", idUtente);
		request.put("area", area);
		request.put("tag", tag);
		request.put("valore", valore);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Dato", "doControl", request);
	}

}
