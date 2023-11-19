package Laboratorio_4;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    private static final String USUARIOS_CSV = "usuarios.csv";
    // private static final String PRESTAMOS_CSV = "prestamos.csv";

    private static List<Usuario> usuarios;
    private static List<Prestamo> prestamos;
    private static Usuario usuarioActual;

    public static void main(String[] args) {
        usuarios = cargarUsuariosDesdeCSV();
        prestamos = cargarPrestamosDesdeCSV();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Biblioteca App ===");
            System.out.println("1. Modo Registro");
            System.out.println("2. Ingresar/Salir");
            System.out.println("3. Modo Selección");
            System.out.println("4. Modo Préstamo");
            System.out.println("5. Modo Perfil");
            System.out.println("0. Salir");
            System.out.print("Ingrese la opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()

            switch (opcion) {
                case 1:
                    modoRegistro(scanner);
                    break;
                case 2:
                    ingresarSalir(scanner);
                    break;
                case 3:
                    modoSeleccion(scanner);
                    break;
                case 4:
                    modoPrestamo(scanner);
                    break;
                case 5:
                    modoPerfil(scanner);
                    break;
                case 0:
                    guardarUsuariosEnCSV(usuarios);
                    guardarPrestamosEnCSV(prestamos);
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private static void modoRegistro(Scanner scanner) {
        System.out.println("=== Modo Registro ===");
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contraseña = scanner.nextLine();

        Usuario usuarioExistente = buscarUsuario(nombreUsuario);
        if (usuarioExistente != null) {
            System.out.println("El usuario ya existe. Inicie sesión o elija otro nombre de usuario.");
            return;
        }

        System.out.println("Seleccione plan:");
        System.out.println("1. Base (Gratis)");
        System.out.println("2. Premium (Pagado)");
        int planSeleccionado = scanner.nextInt();

        String plan;
        switch (planSeleccionado) {
            case 1:
                plan = "Base";
                break;
            case 2:
                plan = "Premium";
                break;
            default:
                System.out.println("Opción no válida. Seleccionado automáticamente el plan Base.");
                plan = "Base";
        }

        Usuario nuevoUsuario = new Usuario(nombreUsuario, contraseña, plan);
        usuarios.add(nuevoUsuario);
        usuarioActual = nuevoUsuario;

        System.out.println("¡Registro exitoso!");
    }

    private static Usuario buscarUsuario(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    private static void ingresarSalir(Scanner scanner) {
        if (usuarioActual != null) {
            System.out.println("¿Desea salir? (S/N)");
            String respuesta = scanner.nextLine().toUpperCase();
            if (respuesta.equals("S")) {
                usuarioActual = null;
                System.out.println("Se ha cerrado la sesión.");
            }
        } else {
            System.out.println("=== Ingresar ===");
            System.out.print("Ingrese nombre de usuario: ");
            String nombreUsuario = scanner.nextLine();
            System.out.print("Ingrese contraseña: ");
            String contraseña = scanner.nextLine();

            Usuario usuario = buscarUsuario(nombreUsuario);

            if (usuario != null && usuario.getContraseña().equals(contraseña)) {
                usuarioActual = usuario;
                System.out.println("¡Bienvenido, " + usuario.getNombre() + "!");
            } else {
                System.out.println("Credenciales incorrectas. Inténtelo de nuevo.");
            }
        }
    }

    private static void modoSeleccion(Scanner scanner) {
        if (usuarioActual == null) {
            System.out.println("Debe iniciar sesión para acceder al Modo Selección.");
            return;
        }

        System.out.println("=== Modo Selección ===");
        System.out.println("1. Agregar Libro");
        System.out.println("2. Agregar Revista");
        System.out.println("3. Vaciar Lista");
        System.out.print("Ingrese la opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de nextInt()

        switch (opcion) {
            case 1:
                agregarLibro(scanner);
                break;
            case 2:
                agregarRevista(scanner);
                break;
            case 3:
                vaciarLista();
                break;
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
        }
    }

    private static void agregarLibro(Scanner scanner) {
        System.out.print("Ingrese nombre del libro: ");
        String nombreLibro = scanner.nextLine();
        System.out.print("Ingrese cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de nextInt()

        Producto libro = new Producto(nombreLibro, "Libro", cantidad, 0.0);
        usuarioActual.getSeleccion().add(libro);

        System.out.println("Libro agregado a la selección.");
    }

    private static void agregarRevista(Scanner scanner) {
        System.out.print("Ingrese nombre de la revista: ");
        String nombreRevista = scanner.nextLine();
        System.out.print("Ingrese cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de nextInt()

        Producto revista = new Producto(nombreRevista, "Revista", cantidad, 0.0);
        usuarioActual.getSeleccion().add(revista);

        System.out.println("Revista agregada a la selección.");
    }

    private static void vaciarLista() {
        usuarioActual.getSeleccion().clear();
        System.out.println("La lista de selección se ha vaciado.");
    }

    private static void modoPrestamo(Scanner scanner) {
        if (usuarioActual == null) {
            System.out.println("Debe iniciar sesión para acceder al Modo Préstamo.");
            return;
        }

        // Implementar lógica para el Modo Préstamo
    }

    private static void modoPerfil(Scanner scanner) {
        if (usuarioActual == null) {
            System.out.println("Debe iniciar sesión para acceder al Modo Perfil.");
            return;
        }

        System.out.println("=== Modo Perfil ===");
        System.out.println("1. Modificar Tipo de Cliente");
        System.out.println("2. Aplicar Cupón de 15 días adicionales");
        System.out.println("3. Cambiar Contraseña");
        System.out.print("Ingrese la opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de nextInt()

        switch (opcion) {
            case 1:
                modificarTipoCliente(scanner);
                break;
            case 2:
                aplicarCupon();
                break;
            case 3:
                cambiarContraseña(scanner);
                break;
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
        }
    }

    private static void modificarTipoCliente(Scanner scanner) {
        System.out.println("Tipo de Cliente actual: " + usuarioActual.getPlan());
        System.out.println("Seleccione nuevo plan:");
        System.out.println("1. Base (Gratis)");
        System.out.println("2. Premium (Pagado)");
        int nuevoPlan = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea después de nextInt()

        switch (nuevoPlan) {
            case 1:
                usuarioActual.setPlan("Base");
                break;
            case 2:
                usuarioActual.setPlan("Premium");
                break;
            default:
                System.out.println("Opción no válida. No se realizaron cambios en el plan.");
        }
    }

    private static void aplicarCupon() {
        // Implementar lógica para aplicar un cupón de 15 días adicionales
    }

    private static void cambiarContraseña(Scanner scanner) {
        System.out.print("Ingrese nueva contraseña: ");
        String nuevaContraseña = scanner.nextLine();
        usuarioActual.setContraseña(nuevaContraseña);
        System.out.println("Contraseña cambiada exitosamente.");
    }

    private static List<Usuario> cargarUsuariosDesdeCSV() {
        List<Usuario> usuarios = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(USUARIOS_CSV))) {
            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                Usuario usuario = new Usuario(nextRecord[0], nextRecord[1], nextRecord[2]);
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    private static void guardarUsuariosEnCSV(List<Usuario> usuarios) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(USUARIOS_CSV))) {
            for (Usuario usuario : usuarios) {
                String[] record = { usuario.getNombre(), usuario.getContraseña(), usuario.getPlan() };
                writer.writeNext(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Prestamo> cargarPrestamosDesdeCSV() {
        List<Prestamo> prestamos = new ArrayList<>();
        // Implementar la carga de préstamos desde el CSV
        return prestamos;
    }

    private static void guardarPrestamosEnCSV(List<Prestamo> prestamos) {
        // Implementar la escritura de préstamos en el CSV
    }
}