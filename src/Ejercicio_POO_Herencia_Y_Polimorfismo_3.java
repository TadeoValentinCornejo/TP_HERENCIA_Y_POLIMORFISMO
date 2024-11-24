import java.util.ArrayList;

// Clase abstracta CanalNotificacion
abstract class CanalNotificacion {
    protected String usuario;
    protected String mensaje;

    public CanalNotificacion(String usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public abstract void enviarNotificacion();
}

// Interfaz Personalizable
interface Personalizable {
    void personalizarMensaje(String nuevoMensaje);
}

// Clase CorreoElectronico
class CorreoElectronico extends CanalNotificacion implements Personalizable {
    private String direccionCorreo;

    public CorreoElectronico(String usuario, String mensaje, String direccionCorreo) {
        super(usuario, mensaje);
        this.direccionCorreo = direccionCorreo;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Enviando correo a " + direccionCorreo + " para " + usuario + ": " + mensaje);
    }

    @Override
    public void personalizarMensaje(String nuevoMensaje) {
        this.mensaje = nuevoMensaje;
    }

    public String getDireccionCorreo() {
        return direccionCorreo;
    }
}

// Clase MensajeTexto
class MensajeTexto extends CanalNotificacion implements Personalizable {
    private String numeroTelefono;

    public MensajeTexto(String usuario, String mensaje, String numeroTelefono) {
        super(usuario, mensaje);
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Enviando mensaje de texto a " + numeroTelefono + " para " + usuario + ": " + mensaje);
    }

    @Override
    public void personalizarMensaje(String nuevoMensaje) {
        this.mensaje = nuevoMensaje;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }
}

// Clase Notificaciones
class Notificaciones {
    private ArrayList<CanalNotificacion> canales;

    public Notificaciones() {
        this.canales = new ArrayList<>();
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
            if (canal instanceof CorreoElectronico) {
                CorreoElectronico correo = (CorreoElectronico) canal;
                System.out.println("Canal: Correo Electrónico, Dirección: " + correo.getDireccionCorreo());
            } else if (canal instanceof MensajeTexto) {
                MensajeTexto mensaje = (MensajeTexto) canal;
                System.out.println("Canal: Mensaje de Texto, Número: " + mensaje.getNumeroTelefono());
            }
        }
    }
}

// Clase principal SistemaNotificaciones
public class Ejercicio_POO_Herencia_Y_Polimorfismo_3 {
    public static void main(String[] args) {
        // Crear el sistema de notificaciones
        Notificaciones notificaciones = new Notificaciones();

        // Crear canales de notificación
        CanalNotificacion correo = new CorreoElectronico("Juan", "Hola Juan, tienes un nuevo mensaje.", "juan@example.com");
        CanalNotificacion mensaje = new MensajeTexto("Maria", "Hola Maria, recuerda tu cita mañana.", "123456789");

        // Agregar canales
        notificaciones.agregarCanal(correo);
        notificaciones.agregarCanal(mensaje);

        // Enviar notificaciones
        System.out.println("Enviando notificaciones:");
        notificaciones.enviarNotificaciones();

        // Personalizar mensajes
        System.out.println("\nPersonalizando mensajes:");
        notificaciones.personalizarMensajes("Este es un mensaje personalizado.");

        // Enviar notificaciones personalizadas
        System.out.println("\nEnviando notificaciones personalizadas:");
        notificaciones.enviarNotificaciones();

        // Mostrar canales
        System.out.println("\nLista de canales:");
        notificaciones.mostrarCanales();
    }
}
