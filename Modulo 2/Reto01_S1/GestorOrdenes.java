import java.util.ArrayList;
import java.util.List;

abstract class OrdenProduccion {
    protected String codigo;
    protected int cantidad;

    public OrdenProduccion(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    public abstract void mostrarResumen();
}

class OrdenMasa extends OrdenProduccion {
    public OrdenMasa(String codigo, int cantidad) {
        super(codigo, cantidad);
    }

    @Override
    public void mostrarResumen() {
        System.out.println("🔧 OrdenMasa - Código: " + codigo + " - Cantidad: " + cantidad);
    }
}

class OrdenPersonalizada extends OrdenProduccion {
    private String cliente;

    public OrdenPersonalizada(String codigo, int cantidad, String cliente) {
        super(codigo, cantidad);
        this.cliente = cliente;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("🛠️ OrdenPersonalizada - Código: " + codigo
                + " - Cantidad: " + cantidad + " - Cliente: " + cliente);
    }
}

class OrdenPrototipo extends OrdenProduccion {
    private String faseDesarrollo;

    public OrdenPrototipo(String codigo, int cantidad, String faseDesarrollo) {
        super(codigo, cantidad);
        this.faseDesarrollo = faseDesarrollo;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("🧪 OrdenPrototipo - Código: " + codigo
                + " - Cantidad: " + cantidad + " - Fase: " + faseDesarrollo);
    }
}

public class GestorOrdenes {

    // Método genérico para mostrar cualquier tipo de orden
    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        System.out.println("\n📋 Órdenes registradas:");
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
        }
    }

    // Método para procesar órdenes personalizadas
    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        System.out.println("\n💰 Procesando órdenes personalizadas...");
        for (Object item : lista) {
            if (item instanceof OrdenPersonalizada) {
                OrdenPersonalizada orden = (OrdenPersonalizada) item;
                System.out.println("✅ Orden " + orden.codigo +
                        " ajustada con costo adicional de $" + costoAdicional);
            }
        }
    }

    // Desafío adicional: Contador de tipos de órdenes
    public static void contarOrdenes(List<? extends OrdenProduccion> lista) {
        int masa = 0, personalizada = 0, prototipo = 0;

        for (OrdenProduccion orden : lista) {
            if (orden instanceof OrdenMasa) masa++;
            else if (orden instanceof OrdenPersonalizada) personalizada++;
            else if (orden instanceof OrdenPrototipo) prototipo++;
        }

        System.out.println("\n📊 Resumen total de órdenes:");
        System.out.println("🔧 Producción en masa: " + masa);
        System.out.println("🛠️ Personalizadas: " + personalizada);
        System.out.println("🧪 Prototipos: " + prototipo);
    }

    public static void main(String[] args) {
        // Crear órdenes de muestra
        List<OrdenMasa> masas = new ArrayList<>();
        masas.add(new OrdenMasa("A123", 500));
        masas.add(new OrdenMasa("A124", 750));

        List<OrdenPersonalizada> personalizadas = new ArrayList<>();
        personalizadas.add(new OrdenPersonalizada("P456", 100, "ClienteX"));
        personalizadas.add(new OrdenPersonalizada("P789", 150, "ClienteY"));

        List<OrdenPrototipo> prototipos = new ArrayList<>();
        prototipos.add(new OrdenPrototipo("T789", 10, "Diseño"));
        prototipos.add(new OrdenPrototipo("T790", 5, "Pruebas"));

        // Lista combinada para el contador
        List<OrdenProduccion> todas = new ArrayList<>();
        todas.addAll(masas);
        todas.addAll(personalizadas);
        todas.addAll(prototipos);

        // Probar métodos
        mostrarOrdenes(masas);
        mostrarOrdenes(personalizadas);
        mostrarOrdenes(prototipos);

        procesarPersonalizadas(personalizadas, 200);
        procesarPersonalizadas(todas, 200); // Lista más genérica

        contarOrdenes(todas);
    }
}