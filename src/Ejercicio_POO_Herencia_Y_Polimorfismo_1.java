import java.util.ArrayList;
import java.util.List;

// Clase Abstracta Vuelo
abstract class Vuelo {
    protected int numeroVuelo;
    protected String origen;
    protected String destino;
    protected String fecha;

    public Vuelo(int numeroVuelo, String origen, String destino, String fecha) {
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
    }

    public abstract double calcularPrecio();

    @Override
    public String toString() {
        return "Vuelo #" + numeroVuelo + ", Origen: " + origen + ", Destino: " + destino + ", Fecha: " + fecha;
    }
}

// Interfaz Promocionable
interface Promocionable {
    void aplicarPromocion(double descuento);
}

// Clase VueloRegular
class VueloRegular extends Vuelo implements Promocionable {
    private int numeroAsientos;
    private double precioBase;

    public VueloRegular(int numeroVuelo, String origen, String destino, String fecha, int numeroAsientos, double precioBase) {
        super(numeroVuelo, origen, destino, fecha);
        this.numeroAsientos = numeroAsientos;
        this.precioBase = precioBase;
    }

    @Override
    public double calcularPrecio() {
        return numeroAsientos * precioBase;
    }

    @Override
    public void aplicarPromocion(double descuento) {
        precioBase -= precioBase * descuento / 100.0;
    }

    @Override
    public String toString() {
        return super.toString() + ", Vuelo Regular, Asientos: " + numeroAsientos + ", Precio Total: $" + calcularPrecio();
    }
}

// Clase VueloCharter
class VueloCharter extends Vuelo implements Promocionable {
    private double precioTotal;

    public VueloCharter(int numeroVuelo, String origen, String destino, String fecha, double precioTotal) {
        super(numeroVuelo, origen, destino, fecha);
        this.precioTotal = precioTotal;
    }

    @Override
    public double calcularPrecio() {
        return precioTotal;
    }

    @Override
    public void aplicarPromocion(double descuento) {
        precioTotal -= precioTotal * descuento / 100.0;
    }

    @Override
    public String toString() {
        return super.toString() + ", Vuelo Charter, Precio Total: $" + calcularPrecio();
    }
}

// Clase Reservas
class Reservas {
    private List<Vuelo> vuelos;

    public Reservas() {
        this.vuelos = new ArrayList<>();
    }

    public void agregarVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
    }

    public double calcularTotalReservas() {
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
            System.out.println(vuelo);
        }
    }
}

// Clase SistemaReservas
public class Ejercicio_POO_Herencia_Y_Polimorfismo_1 {
    public static void main(String[] args) {
        // Crear el sistema de reservas
        Reservas reservas = new Reservas();

        // Crear vuelos regulares y charter
        VueloRegular vuelo1 = new VueloRegular(101, "Buenos Aires", "Córdoba", "2024-11-20", 100, 500);
        VueloCharter vuelo2 = new VueloCharter(202, "Mendoza", "Santiago", "2024-12-15", 20000);

        // Agregar vuelos al sistema de reservas
        reservas.agregarVuelo(vuelo1);
        reservas.agregarVuelo(vuelo2);

        // Mostrar vuelos antes de aplicar promociones
        System.out.println("Vuelos antes de aplicar promociones:");
        reservas.mostrarVuelos();

        // Aplicar promociones a todos los vuelos promocionables
        reservas.aplicarPromociones(10); // Aplicar un 10% de descuento
        System.out.println("\nVuelos después de aplicar promociones:");
        reservas.mostrarVuelos();

        // Calcular y mostrar el precio total de las reservas
        System.out.println("\nPrecio total de las reservas: $" + reservas.calcularTotalReservas());
    }
}
