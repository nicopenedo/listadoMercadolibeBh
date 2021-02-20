package bh.services.prompreciomarcasml.utils;

import java.text.DecimalFormat;
import java.util.List;

public class Utils {
	static DecimalFormat df = new DecimalFormat("#.##");
	
	public static Double promedio(List<Integer> listaPrecios) {
		Integer sumatoria = 0;
		for (Integer precio : listaPrecios) {
			sumatoria+=precio;
		}
		String doubleFormateado = df.format(sumatoria.doubleValue()/listaPrecios.size());
		String doubleConPunto = doubleFormateado.replaceAll(",", ".");
		 return Double.valueOf(doubleConPunto);
	}
}
