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
public class TemporadasCaricatura {
    Date date = new java.util.Date();
    SimpleDateFormat simpleDate = new java.text.SimpleDateFormat("yyyy-mm-dd");
    SimpleDateFormat simpleHora = new java.text.SimpleDateFormat("HH:mm:ss");

    private int idTemporada;
    private int idCaricatura;
    private String nombre;
    private int capitulos;
    private String duracion = simpleHora.format(date);
    private double calificacion;
    private String fechaInicio = simpleDate.format(date);
    private String fechaFin = simpleDate.format(date);
    
    public TemporadasCaricatura(){
        
    }

    public TemporadasCaricatura(int idTemporada, int idCaricatura, String nombre, int capitulos, double calificacion) {
        this.idTemporada = idTemporada;
        this.idCaricatura = idCaricatura;
        this.nombre = nombre;
        this.capitulos = capitulos;
        this.calificacion = calificacion;
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

    public SimpleDateFormat getSimpleHora() {
        return simpleHora;
    }

    public void setSimpleHora(SimpleDateFormat simpleHora) {
        this.simpleHora = simpleHora;
    }

    public int getIdTemporada() {
        return idTemporada;
    }

    public void setIdTemporada(int idTemporada) {
        this.idTemporada = idTemporada;
    }

    public int getIdCaricatura() {
        return idCaricatura;
    }

    public void setIdCaricatura(int idCaricatura) {
        this.idCaricatura = idCaricatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(int capitulos) {
        this.capitulos = capitulos;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    
}
