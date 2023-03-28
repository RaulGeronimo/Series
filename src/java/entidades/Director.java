/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Raúl
 */
public class Director {

    Date date = new java.util.Date();
    SimpleDateFormat simpleDate = new java.text.SimpleDateFormat("yyyy-mm-dd");

    private int idDirector;
    private String nombre;
    private String apellidos;
    private String nombreDirector;
    private String fechaNacimiento = simpleDate.format(date);
    private String fechaDefuncion = simpleDate.format(date);
    private String sexo;
    private double estatura;
    private int nacionalidad;
    
    public Director(){
        
    }

    public Director(int idDirector, String nombre, String apellidos, String nombreDirector, String sexo, double estatura, int nacionalidad) {
        this.idDirector = idDirector;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombreDirector = nombreDirector;
        this.sexo = sexo;
        this.estatura = estatura;
        this.nacionalidad = nacionalidad;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SimpleDateFormat getSimpleDate() {
        return simpleDate;
    }

    public void setSimpleDate(SimpleDateFormat simpleDate) {
        this.simpleDate = simpleDate;
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(String fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public int getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(int nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
