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
public class TemporadasAnime {

    Date date = new java.util.Date();
    SimpleDateFormat simpleDate = new java.text.SimpleDateFormat("yyyy-mm-dd");
    SimpleDateFormat simpleHora = new java.text.SimpleDateFormat("HH:mm:ss");

    private int idTemporada;
    private int idAnime;
    private String nombre;
    private String OtroNombre;
    private int capitulos;
    private String duracion = simpleHora.format(date);
    private String idioma;
    private int idTemporadaEmision;
    private String fechaInicio = simpleDate.format(date);
    private String fechaFin = simpleDate.format(date);
    private int idEstudio;
    private double calificacion;
    private String Portada;
    
    public TemporadasAnime(){
        
    }

    public TemporadasAnime(int idTemporada, int idAnime, String nombre, String OtroNombre, int capitulos, String idioma, int idTemporadaEmision, int idEstudio, double calificacion, String Portada) {
        this.idTemporada = idTemporada;
        this.idAnime = idAnime;
        this.nombre = nombre;
        this.OtroNombre = OtroNombre;
        this.capitulos = capitulos;
        this.idioma = idioma;
        this.idTemporadaEmision = idTemporadaEmision;
        this.idEstudio = idEstudio;
        this.calificacion = calificacion;
        this.Portada = Portada;
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

    public int getIdAnime() {
        return idAnime;
    }

    public void setIdAnime(int idAnime) {
        this.idAnime = idAnime;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOtroNombre() {
        return OtroNombre;
    }

    public void setOtroNombre(String OtroNombre) {
        this.OtroNombre = OtroNombre;
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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getIdTemporadaEmision() {
        return idTemporadaEmision;
    }

    public void setIdTemporadaEmision(int idTemporadaEmision) {
        this.idTemporadaEmision = idTemporadaEmision;
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

    public int getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(int idEstudio) {
        this.idEstudio = idEstudio;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getPortada() {
        return Portada;
    }

    public void setPortada(String Portada) {
        this.Portada = Portada;
    }
    
}
