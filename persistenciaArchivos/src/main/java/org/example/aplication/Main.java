package org.example.aplication;

import org.example.aplication.service.PacienteService;
import org.example.aplication.service.PacienteServiceImpl;
import org.example.domain.Paciente;
import org.example.infraestructure.repository.PacienteRepositoryImpl;
import org.example.interfaces.PacienteRepository;

import javax.swing.*;
import java.util.List;

public class Main {
    private static final PacienteService pacienteService;

    static {
        PacienteRepository pacienteRepository = new PacienteRepositoryImpl();
        pacienteService = new PacienteServiceImpl(pacienteRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            String input = JOptionPane.showInputDialog(null, "1. Listar pacientes\n2. Crear paciente\n3. Actualizar paciente\n4. Eliminar paciente\n5. Salir\nSeleccione una opción:");

            int opcion = Integer.parseInt(input);
            switch (opcion) {
                case 1:
                    listarPacientes();
                    break;
                case 2:
                    crearPaciente();
                    break;
                case 3:
                    actualizarPaciente();
                    break;
                case 4:
                    eliminarpaciente();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void listarPacientes() {
        List<Paciente> pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            System.out.println("Listado de pacientes:");
            for (Paciente paciente : pacientes) {
                System.out.println(paciente);
            }
        }
    }

    private static void crearPaciente() {
        System.out.print("Ingrese el id del paciente: ");
        int id  = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la edad del paciente: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Paciente paciente = new Paciente(idPaciente, nombre, apellido, edad, genero, direccion, telefono);
        paciente.setId(id);
        paciente.setNombre(nombre);
        paciente.setEdad(edad);
        

        try {
            pacienteService.save(paciente);
            System.out.println("paciente creado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void actualizarPaciente() {
        System.out.print("Ingrese el ID del paciente a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Paciente paciente = pacienteService.findById(id);
        if (paciente == null) {
            System.out.println("No se encontró el paciente con ID " + id);
            return;
        }
        String cambiarNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del paciente (dejar en blanco para no cambiar): ");
        String nombre = cambiarNombre;
        if (!nombre.isEmpty()) {
            paciente.setNombre(nombre);
        }

        System.out.print("Ingrese la edad del paciente (0 para no cambiar): ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        if (edad > 0) {
            paciente.setEdad(edad);
        }

        try {
            pacienteService.update(paciente);
            System.out.println("paciente actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarpaciente() {
        System.out.print("Ingrese el ID del paciente a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Paciente paciente = pacienteService.findById(id);
        if (paciente == null) {
            System.out.println("No se encontró el paciente con ID " + id);
            return;
        }


        pacienteService.delete(id);
        System.out.println("paciente eliminado exitosamente.");
    }
}

