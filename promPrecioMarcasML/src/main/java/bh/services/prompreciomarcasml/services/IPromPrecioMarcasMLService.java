package bh.services.prompreciomarcasml.services;

import bh.services.prompreciomarcasml.respose.Response;

public interface IPromPrecioMarcasMLService {
    public Response genPromPrecioPorMarca(String categoria) throws Exception;
}
