package Sesion_05_Reto_01;

abstract class UnidadEmergencia {
    protected String nombre;

    public UnidadEmergencia(String nombre) {
        this.nombre = nombre;
    }

    public abstract void responder();

    public void activarUnidad() {
        System.out.println("🚨 Activando unidad: " + nombre);
    }
}

// Componentes mediante composición
class SistemaGPS {
    public void localizar() {
        System.out.println("📍 GPS: Ubicación actual detectada.");
    }
}

class Sirena {
    public void activarSirena() {
        System.out.println("🔊 Sirena: Activada.");
    }
}

class Operador {
    private String nombre;
    private String emoji;

    public Operador(String nombre, String emoji) {
        this.nombre = nombre;
        this.emoji = emoji;
    }

    public void reportarse() {
        System.out.println(emoji + " Operador " + nombre + " reportándose.");
    }
}