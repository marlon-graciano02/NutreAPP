package com.example.NutreAPP.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Usuario;
import com.example.NutreAPP.models.Video;
import com.example.NutreAPP.repositories.IVideoRepositorio;

@Service 
public class VideoServicio {
    //metodo para buscar por id

    //metodo para buscar todos

    //metodo para buscar para modificar

    //metodo para eliminar

    // metodo para buscar por titulo

    // metodo para buscar por descripcion

    // metodo para buscar por url
    @Autowired 
    private IVideoRepositorio repositorio;

    public Video guardar(Video datos){
       return  this.repositorio.save(datos);
    }

    //Metodo para buscar por ID
      public Video buscarPorId(UUID id){
        Optional<Video> videoBuscado =this.repositorio.findById(id);
        if(! videoBuscado.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"id no encontrado");

        }else{
            return videoBuscado.get();
        }
    }

    //Metodo para buscar todos

    public List<Video> buscarTodos(){
        return this.repositorio.findAll();
    }

     public Video modificar(UUID id, Video datos){


        Optional<Video> videoBuscado =this.repositorio.findById(id);
        if(! videoBuscado.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"id no encontrado");

        }else{
            //RECARGAR LA INFO QUE EXISTE ACTUALMENTE EN BD
            Video videoEncontrado=videoBuscado.get();


            // Espacio para modificar solo las propiedades permitidas
            videoEncontrado.setTitulo(datos.getTitulo());
            videoEncontrado.setCategoria(datos.getCategoria());
           
            //Espacio para volver a guardar el usuario que se acaba de modificar

          return   this.repositorio.save(videoEncontrado);
        }
        
}

 //Metodo para eliminar
    public boolean eliminar(UUID id){
        Optional<Video>videoBuscado =this.repositorio.findById(id);
        if (! videoBuscado.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"id no encontrado");
            
        }else{
            this.repositorio.deleteById(id);
            return true;
        }
    }

    





}
