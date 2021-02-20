package bh.services.prompreciomarcasml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import bh.services.prompreciomarcasml.respose.Response;
import bh.services.prompreciomarcasml.services.IPromPrecioMarcasMLService;

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
		Response respuesta = PromPrecioMarcasMLService.genPromPrecioPorMarca(categoria);
		System.out.println(respuesta.toString());
		return respuesta;
	}
}
