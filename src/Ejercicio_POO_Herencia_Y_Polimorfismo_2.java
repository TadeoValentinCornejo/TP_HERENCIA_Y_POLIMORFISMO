import java.util.ArrayList;

// Clase abstracta MetodoPago
abstract class MetodoPago {
    protected String titular;
    protected int numero;

    public MetodoPago(String titular, int numero) {
        this.titular = titular;
        this.numero = numero;
    }

    public abstract void realizarPago();

    public String getTitular() {
        return titular;
    }

    public int getNumero() {
        return numero;
    }
}

// Interfaz Cancelable
interface Cancelable {
    void cancelarPago();
}

// Clase TarjetaCredito
class TarjetaCredito extends MetodoPago implements Cancelable {
    private String fechaExpiracion;
    private int codigoSeguridad;

    public TarjetaCredito(String titular, int numero, String fechaExpiracion, int codigoSeguridad) {
        super(titular, numero);
        this.fechaExpiracion = fechaExpiracion;
        this.codigoSeguridad = codigoSeguridad;
    }

    @Override
    public void realizarPago() {
        System.out.println("Pago realizado con tarjeta de crédito del titular: " + titular);
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago con tarjeta de crédito cancelado para el titular: " + titular);
    }

    @Override
    public String toString() {
        return "TarjetaCredito [titular=" + titular + ", numero=" + numero + ", fechaExpiracion=" + fechaExpiracion
                + ", codigoSeguridad=" + codigoSeguridad + "]";
    }
}

// Clase PayPal
class PayPal extends MetodoPago implements Cancelable {
    private String correoElectronico;

    public PayPal(String titular, int numero, String correoElectronico) {
        super(titular, numero);
        this.correoElectronico = correoElectronico;
    }

    @Override
    public void realizarPago() {
        System.out.println("Pago realizado con PayPal del titular: " + titular + " usando el correo: " + correoElectronico);
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago con PayPal cancelado para el titular: " + titular);
    }

    @Override
    public String toString() {
        return "PayPal [titular=" + titular + ", numero=" + numero + ", correoElectronico=" + correoElectronico + "]";
    }
}

// Clase Pagos
class Pagos {
    private ArrayList<MetodoPago> metodosPago;

    public Pagos() {
        metodosPago = new ArrayList<>();
    }

    public void agregarMetodo(MetodoPago metodo) {
        metodosPago.add(metodo);
        System.out.println("Método de pago agregado: " + metodo.getTitular());
    }

    public void realizarPagos() {
        for (MetodoPago metodo : metodosPago) {
            metodo.realizarPago();
        }
    }

    public void cancelarPagos() {
        for (MetodoPago metodo : metodosPago) {
            if (metodo instanceof Cancelable) {
                ((Cancelable) metodo).cancelarPago();
            }
        }
    }

    public void mostrarPagos() {
        for (MetodoPago metodo : metodosPago) {
            System.out.println(metodo);
        }
    }
}

// Clase SistemaDePagos
public class Ejercicio_POO_Herencia_Y_Polimorfismo_2 {
    public static void main(String[] args) {
        Pagos sistemaPagos = new Pagos();

        TarjetaCredito tarjeta = new TarjetaCredito("Juan Pérez", 12345678, "12/26", 123);
        PayPal paypal = new PayPal("María López", 87654321, "maria.lopez@example.com");

        sistemaPagos.agregarMetodo(tarjeta);
        sistemaPagos.agregarMetodo(paypal);

        System.out.println("\n--- Realizando pagos ---");
        sistemaPagos.realizarPagos();

        System.out.println("\n--- Cancelando pagos ---");
        sistemaPagos.cancelarPagos();

        System.out.println("\n--- Mostrando métodos de pago ---");
        sistemaPagos.mostrarPagos();
    }
}
