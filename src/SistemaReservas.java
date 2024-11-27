/*Ejercicio 1: Sistema de Reservas de Vuelos*/

import java.util.ArrayList;

abstract class Vuelo {
    protected String numeroVuelo;
    protected String origen;
    protected String destino;
    protected String fecha;

    public Vuelo(String numeroVuelo, String origen, String destino, String fecha) {
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
    }

    public abstract double calcularPrecio();

    @Override
    public String toString() {
        return "Vuelo " + numeroVuelo + " de " + origen + " a " + destino + " el " + fecha;
    }
}

interface Promocionable {
    void aplicarPromocion(double descuento);
}

class VueloRegular extends Vuelo implements Promocionable {
    private int numeroAsientos;
    private double precioPorAsiento;

    public VueloRegular(String numeroVuelo, String origen, String destino, String fecha, int numeroAsientos, double precioPorAsiento) {
        super(numeroVuelo, origen, destino, fecha);
        this.numeroAsientos = numeroAsientos;
        this.precioPorAsiento = precioPorAsiento;
    }

    @Override
    public double calcularPrecio() {
        return numeroAsientos * precioPorAsiento;
    }

    @Override
    public void aplicarPromocion(double descuento) {
        precioPorAsiento -= precioPorAsiento * descuento / 100;
    }
}

class VueloCharter extends Vuelo implements Promocionable {
    private double precioTotal;

    public VueloCharter(String numeroVuelo, String origen, String destino, String fecha, double precioTotal) {
        super(numeroVuelo, origen, destino, fecha);
        this.precioTotal = precioTotal;
    }

    @Override
    public double calcularPrecio() {
        return precioTotal;
    }

    @Override
    public void aplicarPromocion(double descuento) {
        precioTotal -= precioTotal * descuento / 100;
    }
}

class Reservas {
    private ArrayList<Vuelo> vuelos;

    public Reservas() {
        vuelos = new ArrayList<>();
    }

    public void agregarVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
    }

    public double calcularPrecioTotal() {
        double total = 0;
        for (Vuelo vuelo : vuelos) {
            total += vuelo.calcularPrecio();
        }
        return total;
    }

    public void aplicarPromociones(double descuento) {
        for (Vuelo vuelo : vuelos) {
            if (vuelo instanceof Promocionable) {
                ((Promocionable) vuelo).aplicarPromocion(descuento);
            }
        }
    }

    public void mostrarVuelos() {
        for (Vuelo vuelo : vuelos) {
            System.out.println(vuelo + " - Precio: $" + vuelo.calcularPrecio());
        }
    }
}

public class SistemaReservas {
    public static void main(String[] args) {
        Reservas reservas = new Reservas();

        Vuelo vuelo1 = new VueloRegular("101", "Buenos Aires", "Madrid", "2024-12-01", 100, 200);
        Vuelo vuelo2 = new VueloCharter("202", "Barcelona", "París", "2024-12-05", 5000);

        reservas.agregarVuelo(vuelo1);
        reservas.agregarVuelo(vuelo2);

        System.out.println("Antes de aplicar promociones:");
        reservas.mostrarVuelos();

        reservas.aplicarPromociones(10); // Aplicar 10% de descuento

        System.out.println("\nDespués de aplicar promociones:");
        reservas.mostrarVuelos();

        System.out.println("\nPrecio total: $" + reservas.calcularPrecioTotal());
    }
}
