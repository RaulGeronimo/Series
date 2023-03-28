/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.TemporadasAnime;
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
public class controladorTemporadasAnime {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaTemporadaAnime.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar() {
        //Temporada Emision
        String sql = "SELECT *, REPLACE(Nombre, \"Otonio\", \"Otoño\") AS Emision from Temporadas_Emision ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaEmision", datos);

        //Anime
        String sql1 = "SELECT * from Anime ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("ListaAnime", datos);

        //Estudio de Animacion
        String sql2 = "SELECT * from EstudioAnimacion ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("ListaEstudio", datos);

        mav.addObject(new TemporadasAnime());
        mav.setViewName("altaTemporadaAnime");
        return mav;
    }

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaTemporadaAnime.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(TemporadasAnime t) {
        String sql = "INSERT INTO Temporadas_Anime(idAnime, Nombre, OtrosNombres, Capitulos, Duracion, Idioma, idTemporadaEmision, FechaInicio, FechaFin, idEstudio, Calificacion, Portada) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbc.update(sql, t.getIdAnime(), t.getNombre(), t.getOtroNombre(), t.getCapitulos(), t.getDuracion(), t.getIdioma(), t.getIdTemporadaEmision(), t.getFechaInicio(), t.getFechaFin(), t.getIdEstudio(), t.getCalificacion(), t.getPortada());
        return new ModelAndView("redirect:/altaTemporadaAnime.htm");
    }

    //Emision
    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaTemporadaAnimeEmision.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregarEmision() {
        //Temporada Emision
        String sql = "SELECT *, REPLACE(Nombre, \"Otonio\", \"Otoño\") AS Emision from Temporadas_Emision ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaEmision", datos);

        //Anime
        String sql1 = "SELECT * from Anime ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("ListaAnime", datos);

        //Estudio de Animacion
        String sql2 = "SELECT * from EstudioAnimacion ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("ListaEstudio", datos);

        mav.addObject(new TemporadasAnime());
        mav.setViewName("altaTemporadaAnimeEmision");
        return mav;
    }

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaTemporadaAnimeEmision.htm", method = RequestMethod.POST)
    public ModelAndView AgregarEmision(TemporadasAnime t) {
        String sql = "INSERT INTO Temporadas_Anime(idAnime, Nombre, OtrosNombres, Capitulos, Duracion, Idioma, idTemporadaEmision, FechaInicio, idEstudio, Portada) VALUES (?,?,?,?,?,?,?,?,?,?)";
        this.jdbc.update(sql, t.getIdAnime(), t.getNombre(), t.getOtroNombre(), t.getCapitulos(), t.getDuracion(), t.getIdioma(), t.getIdTemporadaEmision(), t.getFechaInicio(), t.getIdEstudio(), t.getPortada());
        return new ModelAndView("redirect:/altaTemporadaAnime.htm");
    }

    //METODO PARA LISTA
    int idTemporada;
    List datos;

    @RequestMapping("listaTemporadaAnime.htm")
    public ModelAndView Listar() {
        String anime = "SELECT * FROM Vista_TemporadasAnime";
        datos = this.jdbc.queryForList(anime);
        mav.addObject("Lista", datos);

        mav.setViewName("listaTemporadaAnime");
        return mav;
    }
////////////FINALIZADO
    @RequestMapping(value = "editarTemporadaAnime.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar(HttpServletRequest request) {
        idTemporada = Integer.parseInt(request.getParameter("idTemporada"));
        String sql = "SELECT * from Temporadas_Anime WHERE idTemporada=" + idTemporada;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);

        //Temporada Emision
        String sqla = "SELECT *, REPLACE(Nombre, \"Otonio\", \"Otoño\") AS Emision from Temporadas_Emision ORDER BY Nombre";
        datos = this.jdbc.queryForList(sqla);
        mav.addObject("ListaEmision", datos);

        //Anime
        String sql1 = "SELECT * from Anime ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("ListaAnime", datos);

        //Estudio de Animacion
        String sql2 = "SELECT * from EstudioAnimacion ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("ListaEstudio", datos);

        //Select
        String sql3 = "SELECT\n"
                + "Temporadas_Anime.idTemporada,\n"
                + "TRIM(Anime.Nombre) AS Anime,\n"
                + "REPLACE(Temporadas_Emision.Nombre, \"Otonio\", \"Otoño\") AS Emision,\n"
                + "EstudioAnimacion.Nombre AS Estudio\n"
                + "FROM Temporadas_Anime\n"
                + "INNER JOIN Anime\n"
                + "ON Temporadas_Anime.idAnime = Anime.idAnime\n"
                + "INNER JOIN Temporadas_Emision\n"
                + "ON Temporadas_Anime.idTemporadaEmision = Temporadas_Emision.idTemporada\n"
                + "INNER JOIN EstudioAnimacion\n"
                + "ON Temporadas_Anime.idEstudio = EstudioAnimacion.idEstudio\n"
                + "AND Temporadas_Anime.idTemporada = " + idTemporada;
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaTemporada", datos);

        mav.setViewName("editarTemporadaAnime");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarTemporadaAnime.htm", method = RequestMethod.POST)
    public ModelAndView Editar(TemporadasAnime t) {
        String sql = "UPDATE Temporadas_Anime SET idAnime=?, Nombre=?, OtrosNombres=?, Capitulos=?, Duracion=?, Idioma=?, idTemporadaEmision=?, FechaInicio=?, FechaFin=?, idEstudio=?, Calificacion=?, Portada=? WHERE idTemporada=?";
        this.jdbc.update(sql, t.getIdAnime(), t.getNombre(), t.getOtroNombre(), t.getCapitulos(), t.getDuracion(), t.getIdioma(), t.getIdTemporadaEmision(), t.getFechaInicio(), t.getFechaFin(), t.getIdEstudio(), t.getCalificacion(), t.getPortada(), idTemporada);
        return new ModelAndView("redirect:/listaTemporadaAnime.htm");
    }

    ////////////EMISION
    @RequestMapping(value = "editarTemporadaAnimeEmision.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar1(HttpServletRequest request) {
        idTemporada = Integer.parseInt(request.getParameter("idTemporada"));
        String sql = "SELECT * from Temporadas_Anime WHERE idTemporada=" + idTemporada;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);

        //Temporada Emision
        String sqla = "SELECT *, REPLACE(Nombre, \"Otonio\", \"Otoño\") AS Emision from Temporadas_Emision ORDER BY Nombre";
        datos = this.jdbc.queryForList(sqla);
        mav.addObject("ListaEmision", datos);

        //Anime
        String sql1 = "SELECT * from Anime ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("ListaAnime", datos);

        //Estudio de Animacion
        String sql2 = "SELECT * from EstudioAnimacion ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("ListaEstudio", datos);

        //Select
        String sql3 = "SELECT\n"
                + "Temporadas_Anime.idTemporada,\n"
                + "TRIM(Anime.Nombre) AS Anime,\n"
                + "REPLACE(Temporadas_Emision.Nombre, \"Otonio\", \"Otoño\") AS Emision,\n"
                + "EstudioAnimacion.Nombre AS Estudio\n"
                + "FROM Temporadas_Anime\n"
                + "INNER JOIN Anime\n"
                + "ON Temporadas_Anime.idAnime = Anime.idAnime\n"
                + "INNER JOIN Temporadas_Emision\n"
                + "ON Temporadas_Anime.idTemporadaEmision = Temporadas_Emision.idTemporada\n"
                + "INNER JOIN EstudioAnimacion\n"
                + "ON Temporadas_Anime.idEstudio = EstudioAnimacion.idEstudio\n"
                + "AND Temporadas_Anime.idTemporada = " + idTemporada;
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaTemporada", datos);

        mav.setViewName("editarTemporadaAnimeEmision");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarTemporadaAnimeEmision.htm", method = RequestMethod.POST)
    public ModelAndView Editar1(TemporadasAnime t) {
        String sql = "UPDATE Temporadas_Anime SET idAnime=?, Nombre=?, OtrosNombres=?, Capitulos=?, Duracion=?, Idioma=?, idTemporadaEmision=?, FechaInicio=?, idEstudio=?, Portada=? WHERE idTemporada=?";
        this.jdbc.update(sql, t.getIdAnime(), t.getNombre(), t.getOtroNombre(), t.getCapitulos(), t.getDuracion(), t.getIdioma(), t.getIdTemporadaEmision(), t.getFechaInicio(), t.getIdEstudio(),  t.getPortada(), idTemporada);
        return new ModelAndView("redirect:/listaTemporadaAnime.htm");
    }
    
    
    //METODO PARA ELIMINAR
    @RequestMapping(value = "eliminarTemporadaAnime.htm")
    public ModelAndView Eliminar(HttpServletRequest request) {
        idTemporada = Integer.parseInt(request.getParameter("idTemporada"));
        String sql = "DELETE from Temporadas_Anime WHERE idTemporada=" + idTemporada;
        this.jdbc.update(sql);
        return new ModelAndView("redirect:/listaTemporadaAnime.htm");
    }
}
