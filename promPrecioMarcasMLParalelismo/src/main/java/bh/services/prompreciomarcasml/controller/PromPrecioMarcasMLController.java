package bh.services.prompreciomarcasml.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import bh.services.prompreciomarcasml.respose.Attribute;
import bh.services.prompreciomarcasml.respose.RespApiMl;
import bh.services.prompreciomarcasml.respose.Response;
import bh.services.prompreciomarcasml.respose.Result;
import bh.services.prompreciomarcasml.services.IPromPrecioMarcasMLService;
import bh.services.prompreciomarcasml.utils.Utils;

@RestController
public class PromPrecioMarcasMLController {

	@Autowired
	private IPromPrecioMarcasMLService PromPrecioMarcasMLService;

	@GetMapping(
			value = {"/examen/prompreciomarca/categoria","/examen/prompreciomarca/categoria/{categoria}"}, 
			produces = "application/json")
	public Response  genPromPrecioPorMarca(@PathVariable(required = false) String categoria) throws Exception {
		if(categoria==null) {
			categoria = "MLA1763"; //setea la categoria moto en el caso de que no se le pase una categoria en el path
		}
		Integer offset = 0;
		HashMap<String,List<Integer>> marcasArs =new HashMap<String,List<Integer>>( );
		HashMap<String,List<Integer>> marcasUsd =new HashMap<String,List<Integer>>( );
		List<CompletableFuture<RespApiMl>> completableFutureAux = new ArrayList<CompletableFuture<RespApiMl>> ();
		while(offset < 900) {
			completableFutureAux.add(PromPrecioMarcasMLService.getResultadosML(categoria, offset));
			offset+=50;
		}
		int hilosCompletados=0;
		while(hilosCompletados<completableFutureAux.size()){
			hilosCompletados=0;
			for(CompletableFuture<RespApiMl> resultado:completableFutureAux){
				if(resultado.isDone()){
					hilosCompletados++;
					/*try {
						// con esto corroboramos cuales son los resultados obtenidos y cuales faltan segun el offset seteado cada hilo
						System.out.println(resultado.get().getPaging().getOffset());
					} catch (Exception e) {
						e.printStackTrace();
					}*/
				}
			}
		}

		for(CompletableFuture<RespApiMl> resultado:completableFutureAux) {
			for (Result res : resultado.get().getResults()) {
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
		}
		// recorremos las marcas, generamos el promedio y devolvemos el response
		HashMap<String, Double> promedioARS = new HashMap<String, Double>();
		HashMap<String, Double> promedioUSD = new HashMap<String,Double>();
		marcasArs.forEach((key,value) ->  promedioARS.put(key==null?"No definida":key,Utils.promedio(value)));
		marcasUsd.forEach((key,value) ->  promedioUSD.put(key==null?"No definida":key,Utils.promedio(value)));
		Response respuesta = new Response(promedioARS,promedioUSD);
		System.out.println(respuesta.toString());
		return respuesta;
	}
}
