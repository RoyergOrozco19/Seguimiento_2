package org.example.infraestructure.repository;
import org.example.domain.Cita;
import org.example.interfaces.CitaRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CItaRepositoryImpl implements CitaRepository {

    private static final String FILE_NAME = "citas.dat";
    @Override
    public List<Cita> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Cita>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }



    @Override
    public Cita findBynumeroCita(int numeroCita) {
        return findAll().stream()
                .filter(p -> p.getPaciente().getEdad() == numeroCita)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Cita citaMedica) {
        List<Cita> productos = findAll();
        productos.add(citaMedica);
        saveAll ((citaMedica);
    }


    @Override
    public void update(Cita citaMedica) {
        List<Cita> productos = findAll();
        productos = productos.stream()
                .map(p -> p.getNumeroCita() == Cita.getNumeroCita() ? citaMedica : p)
                .collect(Collectors.toList());
        saveAll(productos);
    }

    @Override
    public void delete(int numeroCita) {
        List<Cita> productos = findAll();
        productos = productos.stream()
                .filter(p -> p.getNumeroCita() != numeroCita )
                .collect(Collectors.toList());
        saveAll(productos);
    }

    private void saveAll(List<Cita> productos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
