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
public class Productora {
    private int idProductora;
    private String nombre;
    private int genero;
    private String sitio;
    private String imagen;
    
    public Productora(){
        
    }

    public Productora(int idProductora, String nombre, int genero, String sitio, String imagen) {
        this.idProductora = idProductora;
        this.nombre = nombre;
        this.genero = genero;
        this.sitio = sitio;
        this.imagen = imagen;
    }

    public int getIdProductora() {
        return idProductora;
    }

    public void setIdProductora(int idProductora) {
        this.idProductora = idProductora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
