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
public class Anime {
    private int idAnime;
    private String nombre;
    private String otroNombre;
    private String genero;
    
    public Anime(){
        
    }

    public Anime(int idAnime, String nombre, String otroNombre, String genero) {
        this.idAnime = idAnime;
        this.nombre = nombre;
        this.otroNombre = otroNombre;
        this.genero = genero;
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
        return otroNombre;
    }

    public void setOtroNombre(String otroNombre) {
        this.otroNombre = otroNombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
