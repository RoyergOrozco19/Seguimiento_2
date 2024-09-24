package org.example.aplication.service;
import org.example.domain.Cita;


import java.util.List;

public interface CitaService {
    List<Cita> findAll();
    Cita findBynumeroCita(int numeroCita);
    void save(Cita citaMedica);
    void update(Cita citaMedica);
    void delete(int numeroCita);
}

