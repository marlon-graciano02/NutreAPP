package com.example.NutreAPP.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Usuario;
import com.example.NutreAPP.repositories.IUsuarioRepositorio;

@Service 
public class UsuarioServicio {

    //metodo para buscar por id

    //metodo para buscar todos

    //metodo para buscar para modificar

    //metodo para eliminar

    //metodo para buscar por depto y rol

    //metodo para buscar por correo 

    // metodo para buscar por rol 

    // metodo para verificar por correo

    // Para cocinar necesito las recetas 

    // Para programar los servicios necesito los repositorios


    //INYECCION DE DEPENDENCIAS
    @Autowired 
    private IUsuarioRepositorio repositorio;

    //metodo para guardar un usuario 
    public Usuario guardar(Usuario datos){
        return  this.repositorio.save(datos);
    }

    //Metodo para buscar por ID
      public Usuario buscarPorId(UUID id){
        Optional<Usuario> usuarioBuscado =this.repositorio.findById(id);
        if(! usuarioBuscado.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"id no encontrado");

        }else{
            return usuarioBuscado.get();
        }
    }

    //Metodo para buscar todos

    public List<Usuario> buscarTodos(){
        return this.repositorio.findAll();
    }
    
    // Metodo para actualizar (modificar)

    public Usuario modificar(UUID id, Usuario datos){


        Optional<Usuario> usuarioBuscado =this.repositorio.findById(id);
        if(! usuarioBuscado.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"id no encontrado");

        }else{
            //RECARGAR LA INFO QUE EXISTE ACTUALMENTE EN BD
            Usuario usuarioEncontrado=usuarioBuscado.get();


            // Espacio para modificar solo las propiedades permitidas
            usuarioEncontrado.setNombre(datos.getNombre());
            usuarioEncontrado.setRol(datos.getRol());
           
            //Espacio para volver a guardar el usuario que se acaba de modificar

           return  this.repositorio.save(usuarioEncontrado);
        }
        
}  
    //Metodo para eliminar
    public boolean eliminar(UUID id){
        Optional<Usuario>usuarioBuscado =this.repositorio.findById(id);
        if (! usuarioBuscado.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"id no encontrado");
            
        }else{
            this.repositorio.deleteById(id);
            return true;
        }
    }

    
}
