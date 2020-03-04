package it.contrader.view.area;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;


public class AreaUpdateView extends AbstractView {
	private Request request;

	private int id;
	private String nome;
	private final String mode = "UPDATE";

	public AreaUpdateView() {
	}

	/**
	 * Se la request non è nulla (ovvero se si arriva dalla mode UPDATE del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Modifica andata a buon fine.\n");
			MainDispatcher.getInstance().callView("Area", null);
		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'area da modificare
	 */
	@Override
	public void showOptions() {
		try {
			System.out.println("Inserisci id dell'area:");
			id = Integer.parseInt(getInput());
			System.out.println("Inserisci nome dell'area:");
			nome = getInput();
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
		request.put("nome", nome);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Area", "doControl", request);
	}

}
