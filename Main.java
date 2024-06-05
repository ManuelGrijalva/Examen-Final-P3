package Vista;

import Modelo.Contacto;
import Servicio.Agenda;
import java.time.LocalDate;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Mostrar contactos");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Guardar agenda");
            System.out.println("6. Cargar agenda");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {

                case 1:
                    System.out.print("Ingrese nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese teléfono: ");
                    long telefono = scanner.nextLong();
                    scanner.nextLine(); // Limpiar buffer
                    System.out.print("Ingrese correo electrónico: ");
                    String correo = scanner.nextLine();
                    System.out.print("Ingrese fecha de nacimiento (AAAA-MM-DD): ");
                    LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine());
                    agenda.agregarContacto(nombre, telefono, correo, fechaNacimiento);
                    break;
                case 2:
                    agenda.mostrarContactos();
                    break;
                case 3:
                    System.out.print("Ingrese nombre (o deje en blanco para omitir): ");
                    String nombreBusqueda = scanner.nextLine();
                    nombreBusqueda = nombreBusqueda.isEmpty() ? null : nombreBusqueda;
                    System.out.print("Ingrese teléfono (o 0 para omitir): ");
                    long telefonoBusqueda = scanner.nextLong();
                    scanner.nextLine(); // Limpiar buffer
                    Long telefonoBusquedaObj = telefonoBusqueda == 0 ? null : telefonoBusqueda;
                    System.out.print("Ingrese correo electrónico (o deje en blanco para omitir): ");
                    String correoBusqueda = scanner.nextLine();
                    correoBusqueda = correoBusqueda.isEmpty() ? null : correoBusqueda;
                    Contacto contacto = agenda.buscarContacto(nombreBusqueda, telefonoBusquedaObj, correoBusqueda);
                    if (contacto != null) {
                        System.out.println("Nombre: " + contacto.getNombre() +
                                ", Teléfono: " + contacto.getTelefono() +
                                ", Correo Electrónico: " + contacto.getCorreoElectronico() +
                                ", Fecha de Nacimiento: " + contacto.getFechaNacimiento());
                    } else {
                        System.out.println("Contacto no encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el nombre del contacto a eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    agenda.eliminarContacto(nombreEliminar);
                    break;
                case 5:
                    System.out.print("Ingrese el nombre del archivo para guardar: ");
                    String archivoGuardar = scanner.nextLine();
                    try {
                        agenda.guardarAgenda(archivoGuardar);
                        System.out.println("Agenda guardada en " + archivoGuardar);
                    } catch (IOException e) {
                        System.err.println("Error al guardar la agenda: " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.print("Ingrese el nombre del archivo para cargar: ");
                    String archivoCargar = scanner.nextLine();
                    try {
                        agenda.cargarAgenda(archivoCargar);
                        System.out.println("Agenda cargada desde " + archivoCargar);
                    } catch (IOException | ClassNotFoundException e) {
                        System.err.println("Error al cargar la agenda: " + e.getMessage());
                    }
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción del 1 al 7.");
                    break;
            }
        }

        scanner.close();
    }
}

