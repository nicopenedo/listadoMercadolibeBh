package bh.services.prompreciomarcasml.respose;

import java.util.HashMap;

public class Response {
	private HashMap<String, Double> promedioPrecioPorMarcaARS;
	private HashMap<String, Double> promedioPrecioPorMarcaUSD;
	
	public Response(HashMap<String, Double> promedioPrecioPorMarcaARS,
			HashMap<String, Double> promedioPrecioPorMarcaUSD) {
		super();
		this.promedioPrecioPorMarcaARS = promedioPrecioPorMarcaARS;
		this.promedioPrecioPorMarcaUSD = promedioPrecioPorMarcaUSD;
	}

	public HashMap<String, Double> getPromedioPrecioPorMarcaARS() {
		return promedioPrecioPorMarcaARS;
	}
	public void setPromedioPrecioPorMarcaARS(HashMap<String, Double> promedioPrecioPorMarcaARS) {
		this.promedioPrecioPorMarcaARS = promedioPrecioPorMarcaARS;
	}
	public HashMap<String, Double> getPromedioPrecioPorMarcaUSD() {
		return promedioPrecioPorMarcaUSD;
	}
	public void setPromedioPrecioPorMarcaUSD(HashMap<String, Double> promedioPrecioPorMarcaUSD) {
		this.promedioPrecioPorMarcaUSD = promedioPrecioPorMarcaUSD;
	}

	@Override
	public String toString() {
		return "Resultado en consola del examen:\npromedioPrecioPorMarcaARS=" + promedioPrecioPorMarcaARS.toString() + ",\npromedioPrecioPorMarcaUSD="
				+ promedioPrecioPorMarcaUSD;
	}
}
