/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.Estudio;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Raúl
 */
@Controller
public class controladorEstudio {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaEstudio.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar() {
        mav.addObject(new Estudio());
        mav.setViewName("altaEstudio");
        return mav;
    }

    String nombre;

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaEstudio.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(Estudio e, HttpServletRequest request) {
        nombre = request.getParameter("nombre");
        String estudio = "SELECT * FROM EstudioAnimacion WHERE Nombre LIKE '" + nombre + "'";
        datos = this.jdbc.queryForList(estudio);

        if (datos.isEmpty()) {
            String sql = "INSERT INTO EstudioAnimacion (Nombre, Fundacion, Imagen) values (?,?,?)";
            this.jdbc.update(sql, e.getNombre(), e.getFundacion(), e.getImagen());
            return new ModelAndView("redirect:/altaEstudio.htm");
        }
        return new ModelAndView("redirect:/listaEstudio.htm");

    }

    //METODO PARA LISTA
    List datos;
    int idEstudio;

    @RequestMapping("listaEstudio.htm")
    public ModelAndView Listar() {
        String estudio = "SELECT * FROM Vista_Estudio\n";
        datos = this.jdbc.queryForList(estudio);
        mav.addObject("Lista", datos);

        //Estudios
        String sql6 = "SELECT COUNT(idEstudio) AS Estudio FROM EstudioAnimacion";
        datos = this.jdbc.queryForList(sql6);
        mav.addObject("ListaEstudio", datos);

        mav.setViewName("listaEstudio");
        return mav;
    }

    //Vista 2
    @RequestMapping("listaEstudio2.htm")
    public ModelAndView Listar1() {
        String estudio = "SELECT * FROM Vista_Estudio\n";
        datos = this.jdbc.queryForList(estudio);
        mav.addObject("Lista", datos);

        mav.setViewName("listaEstudio2");
        return mav;
    }

    @RequestMapping(value = "buscarEstudio.htm", method = RequestMethod.GET)
    public ModelAndView Buscar(HttpServletRequest request) {
        idEstudio = Integer.parseInt(request.getParameter("idEstudio"));
        String anime = "SELECT * FROM Vista_TemporadasAnime WHERE idEstudio = " + idEstudio + " ORDER BY Inicio";
        datos = this.jdbc.queryForList(anime);
        mav.addObject("Lista", datos);

        //Temporada
        String estudio = "SELECT Nombre AS Estudio FROM EstudioAnimacion WHERE idEstudio = " + idEstudio;
        datos = this.jdbc.queryForList(estudio);
        mav.addObject("Estudio", datos);

        //Conteo
        String conteo = "SELECT idEstudio, COUNT(idTemporada) AS Conteo FROM Temporadas_Anime WHERE idEstudio = " + idEstudio;
        datos = this.jdbc.queryForList(conteo);
        mav.addObject("Conteo", datos);

        mav.setViewName("buscarEstudio");
        return mav;
    }

    @RequestMapping(value = "buscarEstudio2.htm", method = RequestMethod.GET)
    public ModelAndView Buscar1(HttpServletRequest request) {
        idEstudio = Integer.parseInt(request.getParameter("idEstudio"));
        String anime = "SELECT * FROM Vista_TemporadasAnime WHERE idEstudio = " + idEstudio + " AND Duration <= '00:59:00' ORDER BY Inicio";
        datos = this.jdbc.queryForList(anime);
        mav.addObject("Lista", datos);

        //Peliculas
        String panime = "SELECT * FROM Vista_TemporadasAnime WHERE idEstudio = " + idEstudio + " AND Duration > '00:59:00' ORDER BY Inicio";
        datos = this.jdbc.queryForList(panime);
        mav.addObject("Lista1", datos);

        //Nombre Estudio
        String estudio = "SELECT idEstudio, Nombre AS Estudio FROM EstudioAnimacion WHERE idEstudio = " + idEstudio;
        datos = this.jdbc.queryForList(estudio);
        mav.addObject("Estudio", datos);

        //Conteo Animes
        String conteo = "SELECT COUNT(idTemporada) AS Conteo FROM Temporadas_Anime WHERE Duracion <= '00:59:00' AND idEstudio = " + idEstudio;
        datos = this.jdbc.queryForList(conteo);
        mav.addObject("Conteo", datos);
        
        //Conteo Animes
        String Pelicula = "SELECT COUNT(idTemporada) AS Conteo FROM Temporadas_Anime WHERE Duracion > '00:59:00' AND idEstudio = " + idEstudio;
        datos = this.jdbc.queryForList(Pelicula);
        mav.addObject("Pelicula", datos);

        mav.setViewName("buscarEstudio2");
        return mav;
    }

    //Editar
    @RequestMapping(value = "editarEstudio.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar(HttpServletRequest request) {
        idEstudio = Integer.parseInt(request.getParameter("idEstudio"));
        String sql = "SELECT * FROM EstudioAnimacion WHERE idEstudio = " + idEstudio;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);
        mav.setViewName("editarEstudio");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarEstudio.htm", method = RequestMethod.POST)
    public ModelAndView Editar(Estudio e) {
        String sql = "UPDATE EstudioAnimacion SET Nombre=?, Fundacion=?, Imagen=? WHERE idEstudio=?";
        this.jdbc.update(sql, e.getNombre(), e.getFundacion(), e.getImagen(), idEstudio);
        return new ModelAndView("redirect:/listaEstudio.htm");
    }

    //METODO PARA ELIMINAR
    @RequestMapping(value = "eliminarEstudio.htm")
    public ModelAndView Eliminar(HttpServletRequest request) {
        idEstudio = Integer.parseInt(request.getParameter("idEstudio"));
        String sql = "DELETE FROM EstudioAnimacion WHERE idEstudio=" + idEstudio;
        this.jdbc.update(sql);
        return new ModelAndView("redirect:/listaEstudio.htm");
    }
}
