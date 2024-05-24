package com.aluracursos.literaturachallenge.services;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class <T> clase);
}
