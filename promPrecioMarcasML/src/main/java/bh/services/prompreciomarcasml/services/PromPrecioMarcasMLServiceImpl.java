package bh.services.prompreciomarcasml.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bh.services.prompreciomarcasml.respose.Attribute;
import bh.services.prompreciomarcasml.respose.RespApiMl;
import bh.services.prompreciomarcasml.respose.Response;
import bh.services.prompreciomarcasml.respose.Result;
import bh.services.prompreciomarcasml.utils.Utils;

@Service	
public class PromPrecioMarcasMLServiceImpl implements IPromPrecioMarcasMLService{
	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	public Response genPromPrecioPorMarca(String categoria) throws Exception {
		Integer offset = 0;
		HashMap<String,List<Integer>> marcasArs =new HashMap<String,List<Integer>>( );
		HashMap<String,List<Integer>> marcasUsd =new HashMap<String,List<Integer>>( );
		while(offset < 900) {
			//traemos los resultados para el current offset
			RespApiMl respuesta = getResultados(categoria, offset);
			for (Result res : respuesta.getResults()) {
				if(res.getCondition().equals("new")) {
					for (Attribute attr : res.getAttributes()) {
						if(attr.getId().equals("BRAND")) {
							String marca = attr.getValue_name();
							if(res.getCurrency_id().equals("ARS")) {
								if(marcasArs.containsKey(marca)) {
									marcasArs.get(marca).add(res.getPrice());
								}else {
									List<Integer> precio = new ArrayList<Integer>() {{add(res.getPrice());}};
									marcasArs.put(marca, precio);
								}
							}else {
								if(marcasUsd.containsKey(marca)) {
									marcasUsd.get(marca).add(res.getPrice());
								}
								else {
									List<Integer> precio = new ArrayList<Integer>() {{add(res.getPrice());}};
									marcasUsd.put(marca, precio);
								}
							}
							break;
						}
						
					}
				}
			}
			offset+=50;
		}
		// recorremos las marcas, generamos el promedio y devolvemos el response
		HashMap<String, Double> promedioARS = new HashMap<String, Double>();
		HashMap<String, Double> promedioUSD = new HashMap<String,Double>();
		marcasArs.forEach((key,value) -> promedioARS.put(key==null?"No definida":key,Utils.promedio(value)));
		marcasUsd.forEach((key,value) -> promedioUSD.put(key==null?"No definida":key,Utils.promedio(value)));
		Response respuesta = new Response(promedioARS,promedioUSD);
		return respuesta;
	}
	
	private RespApiMl getResultados(String categoria, Integer offset) {
		String url = "https://api.mercadolibre.com/sites/MLA/search?category="+categoria+"&offset="+offset;
		RespApiMl resultados = clienteRest.getForObject(url, RespApiMl.class);
		return resultados;
	}

}
