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
public class Pais {
    private int idPais;
    private String nombre;
    private String nacionalidad;
    private String continente;
    private String bandera;
    
    public Pais(){
        
    }

    public Pais(int idPais, String nombre, String nacionalidad, String continente, String bandera) {
        this.idPais = idPais;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.continente = continente;
        this.bandera = bandera;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }
}
