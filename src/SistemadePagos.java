import java.util.ArrayList;

/*Ejercicio 2: Sistema de Pagos*/
abstract class MetodoPago {
    protected String titular;
    protected String numero;

    public MetodoPago(String titular, String numero) {
        this.titular = titular;
        this.numero = numero;
    }

    public abstract void realizarPago(double monto);

    @Override
    public String toString() {
        return "Titular: " + titular + ", Número: " + numero;
    }
}

interface Cancelable {
    void cancelarPago();
}

class TarjetaCredito extends MetodoPago implements Cancelable {
    private String fechaExpiracion;
    private String codigoSeguridad;

    public TarjetaCredito(String titular, String numero, String fechaExpiracion, String codigoSeguridad) {
        super(titular, numero);
        this.fechaExpiracion = fechaExpiracion;
        this.codigoSeguridad = codigoSeguridad;
    }

    @Override
    public void realizarPago(double monto) {
        System.out.println("Pago realizado con tarjeta de crédito. Monto: $" + monto);
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago cancelado en tarjeta de crédito.");
    }
}

class PayPal extends MetodoPago implements Cancelable {
    private String correoElectronico;

    public PayPal(String titular, String numero, String correoElectronico) {
        super(titular, numero);
        this.correoElectronico = correoElectronico;
    }

    @Override
    public void realizarPago(double monto) {
        System.out.println("Pago realizado con PayPal. Monto: $" + monto);
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago cancelado en PayPal.");
    }
}

class Pagos {
    private ArrayList<MetodoPago> metodosPago;

    public Pagos() {
        metodosPago = new ArrayList<>();
    }

    public void agregarMetodoPago(MetodoPago metodoPago) {
        metodosPago.add(metodoPago);
    }

    public void realizarPagos(double monto) {
        for (MetodoPago metodoPago : metodosPago) {
            metodoPago.realizarPago(monto);
        }
    }

    public void cancelarPagos() {
        for (MetodoPago metodoPago : metodosPago) {
            if (metodoPago instanceof Cancelable) {
                ((Cancelable) metodoPago).cancelarPago();
            }
        }
    }

    public void mostrarMetodosPago() {
        for (MetodoPago metodoPago : metodosPago) {
            System.out.println(metodoPago);
        }
    }
}

public class SistemadePagos {
    public static void main(String[] args) {
        Pagos pagos = new Pagos();

        MetodoPago tarjeta = new TarjetaCredito("Juan Pérez", "1234-5678-9012-3456", "12/25", "123");
        MetodoPago paypal = new PayPal("Ana Gómez", "N/A", "ana@gmail.com");

        pagos.agregarMetodoPago(tarjeta);
        pagos.agregarMetodoPago(paypal);

        pagos.mostrarMetodosPago();

        System.out.println("\nRealizando pagos:");
        pagos.realizarPagos(1000);

        System.out.println("\nCancelando pagos:");
        pagos.cancelarPagos();
    }
}
