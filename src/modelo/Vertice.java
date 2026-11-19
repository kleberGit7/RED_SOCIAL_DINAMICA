package modelo;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private Persona persona;
    private List<Arista> aristas;

    public Vertice(Persona persona) {
        this.persona = persona;
        this.aristas = new ArrayList<>();
    }

    public Persona getPersona() {
        return persona;
    }

    public List<Arista> getAristas() {
        return aristas;
    }
}