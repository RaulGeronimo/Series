/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Raúl
 */
public class Series {
    private int idSerie;
    private String nombre;
    private String otrosNombres;
    private int genero;
    private int idProductora;
    private int idDistribuidora;
    private int idDirector;
    private String Portada;
    
    public Series(){
        
    }

    public Series(int idSerie, String nombre, String otrosNombres, int genero, int idProductora, int idDistribuidora, int idDirector, String Portada) {
        this.idSerie = idSerie;
        this.nombre = nombre;
        this.otrosNombres = otrosNombres;
        this.genero = genero;
        this.idProductora = idProductora;
        this.idDistribuidora = idDistribuidora;
        this.idDirector = idDirector;
        this.Portada = Portada;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
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

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
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
