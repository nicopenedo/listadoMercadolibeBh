package bh.services.prompreciomarcasml.services;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bh.services.prompreciomarcasml.respose.RespApiMl;

@Service	
public class PromPrecioMarcasMLServiceImpl implements IPromPrecioMarcasMLService{
	@Autowired
	private RestTemplate clienteRest;
	
	@Async
    @Override
	public CompletableFuture<RespApiMl> getResultadosML(String categoria, Integer offset) {
		String url = "https://api.mercadolibre.com/sites/MLA/search?category="+categoria+"&offset="+offset;
		RespApiMl resultados = clienteRest.getForObject(url, RespApiMl.class);
		return CompletableFuture.completedFuture(resultados);
	}


}
