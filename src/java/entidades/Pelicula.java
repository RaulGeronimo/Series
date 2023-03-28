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
public class Pelicula {

    Date date = new java.util.Date();
    SimpleDateFormat simpleDate = new java.text.SimpleDateFormat("yyyy-mm-dd");
    SimpleDateFormat simpleHora = new java.text.SimpleDateFormat("HH:mm:ss");

    private int idPelicula;
    private String nombre;
    private String otrosNombres;
    private int idProductora;
    private int idDistribuidora;
    private String duracion = simpleHora.format(date);
    private int genero;
    private String tipo;
    private int clasificacion;
    private String estreno = simpleDate.format(date);
    private String estrenoMexico = simpleDate.format(date);
    private double calificacion;
    private int idDirector;
    private String Portada;

    public Pelicula() {

    }

    public Pelicula(int idPelicula, String nombre, String otrosNombres, int idProductora, int idDistribuidora, int genero, String tipo, int clasificacion, double calificacion, int idDirector, String Portada) {
        this.idPelicula = idPelicula;
        this.nombre = nombre;
        this.otrosNombres = otrosNombres;
        this.idProductora = idProductora;
        this.idDistribuidora = idDistribuidora;
        this.genero = genero;
        this.tipo = tipo;
        this.clasificacion = clasificacion;
        this.calificacion = calificacion;
        this.idDirector = idDirector;
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

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOtrosNombres() {
        return otrosNombres;
    }

    public void setOtrosNombres(String otrosNombres) {
        this.otrosNombres = otrosNombres;
    }

    public int getIdProductora() {
        return idProductora;
    }

    public void setIdProductora(int idProductora) {
        this.idProductora = idProductora;
    }

    public int getIdDistribuidora() {
        return idDistribuidora;
    }

    public void setIdDistribuidora(int idDistribuidora) {
        this.idDistribuidora = idDistribuidora;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getEstreno() {
        return estreno;
    }

    public void setEstreno(String estreno) {
        this.estreno = estreno;
    }

    public String getEstrenoMexico() {
        return estrenoMexico;
    }

    public void setEstrenoMexico(String estrenoMexico) {
        this.estrenoMexico = estrenoMexico;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public String getPortada() {
        return Portada;
    }

    public void setPortada(String Portada) {
        this.Portada = Portada;
    }
   
}
