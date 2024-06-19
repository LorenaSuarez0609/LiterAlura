package com.aluracursos.literalura.service;

public interface ICovierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
