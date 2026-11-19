package negocio;

import enums.TipoRelacion;
import modelo.Arista;
import modelo.Persona;
import modelo.Vertice;

import java.util.HashMap;
import java.util.Map;

public class RedSocial {
    private Map<String, Vertice> grafo = new HashMap<>();

    public void agregarUsuario(String nombre) {
        String clave = nombre.toLowerCase();
        if (!grafo.containsKey(clave)) {
            grafo.put(clave, new Vertice(new Persona(nombre)));
        }
    }

    public void agregarRelacion(String origen, String destino, TipoRelacion tipo) {
        Vertice vOrigen = grafo.get(origen.toLowerCase());
        Vertice vDestino = grafo.get(destino.toLowerCase());

        if (vOrigen == null || vDestino == null) {
            System.out.println("Error: Uno de los usuarios no existe.");
            return;
        }

        // Regla: No se permiten amistades de una persona consigo misma
        if (origen.equalsIgnoreCase(destino)) {
            System.out.println("Regla violada: " + origen + " no puede tener relacion consigo mismo.");
            return;
        }

        if (tipo == TipoRelacion.AMISTAD) {
            // Regla: Una amistad funciona en ambos sentidos
            agregarAristaSiNoExiste(vOrigen, vDestino.getPersona(), TipoRelacion.AMISTAD);
            agregarAristaSiNoExiste(vDestino, vOrigen.getPersona(), TipoRelacion.AMISTAD);
        } else if (tipo == TipoRelacion.SEGUIMIENTO) {
            // Regla: Un seguimiento funciona en una sola direccion
            agregarAristaSiNoExiste(vOrigen, vDestino.getPersona(), TipoRelacion.SEGUIMIENTO);
        }
    }

    private void agregarAristaSiNoExiste(Vertice origen, Persona destino, TipoRelacion tipo) {
        boolean existe = origen.getAristas().stream()
                .anyMatch(a -> a.getDestino().equals(destino) && a.getTipo() == tipo);
        if (!existe) {
            origen.getAristas().add(new Arista(destino, tipo));
        }
    }

    public void eliminarRelacion(String origen, String destino, TipoRelacion tipo) {
        Vertice vOrigen = grafo.get(origen.toLowerCase());
        Vertice vDestino = grafo.get(destino.toLowerCase());
        if (vOrigen == null || vDestino == null) return;

        vOrigen.getAristas().removeIf(a -> a.getDestino().getNombre().equalsIgnoreCase(destino) && a.getTipo() == tipo);

        // Si es amistad, eliminar tambien en sentido contrario
        if (tipo == TipoRelacion.AMISTAD) {
            vDestino.getAristas().removeIf(a -> a.getDestino().getNombre().equalsIgnoreCase(origen) && a.getTipo() == tipo);
        }
    }

    // Regla: Al eliminar un usuario, deben desaparecer todas sus conexiones
    public void eliminarUsuario(String nombre) {
        String clave = nombre.toLowerCase();
        if (!grafo.containsKey(clave)) return;

        // 1. Eliminar todas las aristas entrantes hacia este usuario en los demas vertices
        for (Vertice v : grafo.values()) {
            v.getAristas().removeIf(a -> a.getDestino().getNombre().equalsIgnoreCase(nombre));
        }

        // 2. Eliminar el vertice del grafo
        grafo.remove(clave);
    }

    public void mostrarRed() {
        System.out.println("=== ESTADO ACTUAL DE LA RED SOCIAL ===");
        for (Vertice v : grafo.values()) {
            System.out.print(v.getPersona().getNombre() + " -> ");
            if (v.getAristas().isEmpty()) {
                System.out.println("Sin conexiones");
            } else {
                System.out.println(v.getAristas());
            }
        }
        System.out.println("======================================\n");
    }
}