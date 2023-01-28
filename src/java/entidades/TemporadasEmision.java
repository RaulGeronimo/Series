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
public class TemporadasEmision {
    Date date = new java.util.Date();
    SimpleDateFormat simpleDate = new java.text.SimpleDateFormat("yyyy-mm-dd");
    SimpleDateFormat simpleHora = new java.text.SimpleDateFormat("HH:mm:ss");
    
    private int idTemporada;
    private String nombre;
    private String fechaInicio = simpleDate.format(date);
    private String fechaFin = simpleDate.format(date);
    
    public TemporadasEmision(){
        
    }

    public TemporadasEmision(int idTemporada, String nombre) {
        this.idTemporada = idTemporada;
        this.nombre = nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
