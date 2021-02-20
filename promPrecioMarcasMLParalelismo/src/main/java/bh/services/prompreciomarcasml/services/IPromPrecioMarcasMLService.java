package bh.services.prompreciomarcasml.services;

import java.util.concurrent.CompletableFuture;

import bh.services.prompreciomarcasml.respose.RespApiMl;

public interface IPromPrecioMarcasMLService {
    public CompletableFuture<RespApiMl> getResultadosML(String categoria, Integer offset);
}
