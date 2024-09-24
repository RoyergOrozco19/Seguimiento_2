package org.example.aplication.service;
import org.example.domain.Cita;
import org.example.interfaces.CitaRepository;

import java.util.List;

public class CitaServiceImpl implements CitaService{
    private final CitaRepository citaRepository;

    public CitaServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public List<Cita> findAll() {
        return CitaRepository.findAll();
    }

    @Override
    public Cita findBynumeroCita(int numeroCita) {
        return null;
    }

    @Override
    public Cita findById(int id) {
        return CitaRepository.findBynumeroCita(numeroCita);
    }

    @Override
    public void save(Cita citaMedica) {
        validarCita(citaMedica);
        citaRepository.save(citaMedica);
    }

    @Override
    public void update(Cita citaMedica) {
        validarCita(citaMedica);
        citaRepository.update(citaMedica);
    }

    @Override
    public void delete(int numeroCita) {
        citaRepository.delete(numeroCita);
    }

    private void validarCita(Cita citaMedica) {
        if (citaMedica.getPaciente().isEmpty()) {
            throw new IllegalArgumentException("El paciente no puede ser vacío");
        }
        if (citaMedica.getNumeroCita()== Cita.getNumeroCita()) {
            throw new IllegalArgumentException("La edad del paciente debe ser mayor a cero");
        }
    }


}

