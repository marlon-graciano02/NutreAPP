package com.example.NutreAPP.validations;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Video;
import com.example.NutreAPP.repositories.IVideoRepositorio;

@Component
public class VideoValidacion {

    @Autowired
    private IVideoRepositorio videoRepositorio;

    public void validarDatosVideo(Video datos) {
        if (datos == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los datos del video son obligatorios");
        }

        // V1. Titulo obligatorio y entre 5 y 120 caracteres -> 400
        String titulo = datos.getTitulo();
        if (titulo == null || titulo.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El título es obligatorio");
        }
        int longitudTitulo = titulo.trim().length();
        if (longitudTitulo < 5 || longitudTitulo > 120) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El título debe tener entre 5 y 120 caracteres");
        }

        // V2. La URL es obligatoria y debe empezar por "http://" o "https://" -> 400
        String url = datos.getUrl();
        if (url == null || url.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La URL es obligatoria");
        }
        String urlLimpia = url.trim();
        if (!urlLimpia.startsWith("http://") && !urlLimpia.startsWith("https://")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La URL debe empezar por \"http://\" o \"https://\"");
        }

        // V3. La categoria es obligatoria (no puede llegar null) -> 400
        if (datos.getCategoria() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La categoría es obligatoria");
        }

        // V4. La descripcion es opcional, pero si viene no puede superar 500 caracteres -> 400
        String descripcion = datos.getDescripcion();
        if (descripcion != null && descripcion.length() > 500) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La descripción no puede superar los 500 caracteres");
        }
    }

    // V5. No pueden existir dos videos con la misma URL -> 409
    public void validarVideoNuevo(Video datos) {
        validarDatosVideo(datos);

        if (this.videoRepositorio.findByUrl(datos.getUrl().trim()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un video registrado con esa URL");
        }
    }

    public void validarVideoExistente(UUID id, Video datos) {
        validarDatosVideo(datos);
    }
}