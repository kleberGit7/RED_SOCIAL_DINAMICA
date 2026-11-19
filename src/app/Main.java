package app;

import enums.TipoRelacion;
import negocio.RedSocial;

public class Main {
    public static void main(String[] args) {
        RedSocial red = new RedSocial();

        // PASO 1: Crear cuatro usuarios: Ana, Beto, Carlos y Dani
        red.agregarUsuario("Ana");
        red.agregarUsuario("Beto");
        red.agregarUsuario("Carlos");
        red.agregarUsuario("Dani");

        // PASO 2: Establecer amistades (bidireccionales) y seguimientos (unidireccionales)
        red.agregarRelacion("Ana", "Beto", TipoRelacion.AMISTAD);      // Ana <-> Beto
        red.agregarRelacion("Beto", "Carlos", TipoRelacion.AMISTAD);   // Beto <-> Carlos
        red.agregarRelacion("Ana", "Carlos", TipoRelacion.SEGUIMIENTO);// Ana -> Carlos
        red.agregarRelacion("Carlos", "Dani", TipoRelacion.SEGUIMIENTO);// Carlos -> Dani
        red.agregarRelacion("Dani", "Ana", TipoRelacion.SEGUIMIENTO);  // Dani -> Ana

        // Prueba de regla: No se permiten amistades consigo mismo
        red.agregarRelacion("Ana", "Ana", TipoRelacion.AMISTAD);

        System.out.println("--- TRAS PASO 2 (RELACIONES INICIALES) ---");
        red.mostrarRed();

        // PASO 3: Modificar relaciones (ajusta estas lineas segun lo que dicto tu moderador)
        red.eliminarRelacion("Ana", "Beto", TipoRelacion.AMISTAD);     // Se rompe amistad Ana <-> Beto
        red.agregarRelacion("Beto", "Dani", TipoRelacion.AMISTAD);     // Nueva amistad Beto <-> Dani
        red.agregarRelacion("Beto", "Ana", TipoRelacion.SEGUIMIENTO);  // Beto ahora sigue a Ana

        System.out.println("--- TRAS PASO 3 (MODIFICACIONES) ---");
        red.mostrarRed();

        // PASO 4: Eliminar a Carlos y todas sus conexiones
        red.eliminarUsuario("Carlos");

        // PASO 5: Mostrar esquema final de la red social
        System.out.println("--- TRAS PASO 4 Y 5 (RED FINAL SIN CARLOS) ---");
        red.mostrarRed();
    }
}