import java.util.ArrayList;

/*Ejercicio 3: Sistema de Notificaciones*/
abstract class CanalNotificacion {
    protected String usuario;
    protected String mensaje;

    public CanalNotificacion(String usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public abstract void enviarNotificacion();

    @Override
    public String toString() {
        return "Usuario: " + usuario + ", Mensaje: " + mensaje;
    }
}

interface Personalizable {
    void personalizarMensaje(String nuevoMensaje);
}

class CorreoElectronico extends CanalNotificacion implements Personalizable {
    private String direccionCorreo;

    public CorreoElectronico(String usuario, String mensaje, String direccionCorreo) {
        super(usuario, mensaje);
        this.direccionCorreo = direccionCorreo;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Enviando correo a " + direccionCorreo + " con el mensaje: " + mensaje);
    }

    @Override
    public void personalizarMensaje(String nuevoMensaje) {
        this.mensaje = nuevoMensaje;
    }
}

class MensajeTexto extends CanalNotificacion implements Personalizable {
    private String numeroTelefono;

    public MensajeTexto(String usuario, String mensaje, String numeroTelefono) {
        super(usuario, mensaje);
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Enviando mensaje de texto al número " + numeroTelefono + " con el mensaje: " + mensaje);
    }

    @Override
    public void personalizarMensaje(String nuevoMensaje) {
        this.mensaje = nuevoMensaje;
    }
}

class Notificaciones {
    private ArrayList<CanalNotificacion> canales;

    public Notificaciones() {
        canales = new ArrayList<>();
    }

    public void agregarCanal(CanalNotificacion canal) {
        canales.add(canal);
    }

    public void enviarNotificaciones() {
        for (CanalNotificacion canal : canales) {
            canal.enviarNotificacion();
        }
    }

    public void personalizarMensajes(String nuevoMensaje) {
        for (CanalNotificacion canal : canales) {
            if (canal instanceof Personalizable) {
                ((Personalizable) canal).personalizarMensaje(nuevoMensaje);
            }
        }
    }

    public void mostrarCanales() {
        for (CanalNotificacion canal : canales) {
            System.out.println(canal);
        }
    }
}

public class SistemaNotificaciones {
    public static void main(String[] args) {
        Notificaciones notificaciones = new Notificaciones();

        CanalNotificacion correo = new CorreoElectronico("Juan Pérez", "Bienvenido a nuestro servicio", "juan@gmail.com");
        CanalNotificacion mensajeTexto = new MensajeTexto("Ana Gómez", "Tu pedido está en camino", "123456789");

        notificaciones.agregarCanal(correo);
        notificaciones.agregarCanal(mensajeTexto);

        System.out.println("\n--- Canales de Notificación ---");
        notificaciones.mostrarCanales();

        System.out.println("\n--- Enviando Notificaciones ---");
        notificaciones.enviarNotificaciones();

        System.out.println("\n--- Personalizando Mensajes ---");
        notificaciones.personalizarMensajes("Este es un mensaje actualizado para todos los canales");
        notificaciones.enviarNotificaciones();
    }
}
