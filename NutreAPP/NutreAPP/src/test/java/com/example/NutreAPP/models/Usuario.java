package com.example.NutreAPP.models;

import java.util.UUID;

import com.example.NutreAPP.models.helpers.Departamento;
import com.example.NutreAPP.models.helpers.Rol;

public class Usuario {

   private UUID id;
   private String nombre;
   private String correo;
   private Rol rol;
   private Departamento departamento;
   private String contraseña_hash;
   public Usuario() {
   }
   public Usuario(UUID id, String nombre, String correo, Rol rol, Departamento departamento, String contraseña_hash) {
    this.id = id;
    this.nombre = nombre;
    this.correo = correo;
    this.rol = rol;
    this.departamento = departamento;
    this.contraseña_hash = contraseña_hash;
   }
   public UUID getId() {
    return id;
   }
   public void setId(UUID id) {
    this.id = id;
   }
   public String getNombre() {
    return nombre;
   }
   public void setNombre(String nombre) {
    this.nombre = nombre;
   }
   public String getCorreo() {
    return correo;
   }
   public void setCorreo(String correo) {
    this.correo = correo;
   }
   public Rol getRol() {
    return rol;
   }
   public void setRol(Rol rol) {
    this.rol = rol;
   }
   public Departamento getDepartamento() {
    return departamento;
   }
   public void setDepartamento(Departamento departamento) {
    this.departamento = departamento;
   }
   public String getContraseña_hash() {
    return contraseña_hash;
   }
   public void setContraseña_hash(String contraseña_hash) {
    this.contraseña_hash = contraseña_hash;
   }

   


}
