package modelo;

import enums.TipoRelacion;

public class Arista {
    private Persona destino;
    private TipoRelacion tipo;

    public Arista(Persona destino, TipoRelacion tipo) {
        this.destino = destino;
        this.tipo = tipo;
    }

    public Persona getDestino() {
        return destino;
    }

    public TipoRelacion getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return destino.getNombre() + " [" + tipo + "]";
    }
}