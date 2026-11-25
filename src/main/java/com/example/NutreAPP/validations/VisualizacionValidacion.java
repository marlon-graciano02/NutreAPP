package com.example.NutreAPP.validations;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Usuario;
import com.example.NutreAPP.models.Video;
import com.example.NutreAPP.models.Visualizacion;
import com.example.NutreAPP.repositories.IUsuarioRepositorio;
import com.example.NutreAPP.repositories.IVideoRepositorio;

@Component
public class VisualizacionValidacion {

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    private IVideoRepositorio videoRepositorio;

    public void validarVisualizacion(Visualizacion datos) {
        if (datos == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los datos de la visualización son obligatorios");
        }

        // V1. La fecha de visualizacion es obligatoria -> 400
        LocalDate fecha = datos.getFecha_visualizacion();
        if (fecha == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de visualización es obligatoria");
        }

        // V2. La fecha NO puede ser futura -> 400
        if (fecha.isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de visualización no puede ser futura");
        }

        // V3. El campo completado es obligatorio (true o false, no null) -> 400
        if (datos.getCompletado() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo completado es obligatorio");
        }

        // V4. El usuario y el video deben EXISTIR en la BD -> 404
        Usuario usuario = datos.getUsuario();
        UUID usuarioId = (usuario != null) ? usuario.getId() : null;
        if (usuarioId == null || !this.usuarioRepositorio.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe en la base de datos");
        }

        Video video = datos.getVideo();
        UUID videoId = (video != null) ? video.getId() : null;
        if (videoId == null || !this.videoRepositorio.existsById(videoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El video no existe en la base de datos");
        }
    }
}