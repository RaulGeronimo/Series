/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.Pelicula;
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
public class controladorPelicula {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    String nombre;

    //PELICULA NORMAL
    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaPelicula.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar() {
        //Productora
        String sql = "SELECT * from Productora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaProductora", datos);

        //Distribuidora
        String sq = "SELECT * from Distribuidora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sq);
        mav.addObject("ListaDistribuidora", datos);

        // Genero
        String sql1 = "SELECT * from Genero ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("ListaGenero", datos);

        // Clasificacion
        String sql2 = "SELECT * from Clasificacion ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("ListaClasificacion", datos);

        //Director
        String sql3 = "SELECT * from Director ORDER BY NombreArtistico";
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaDirector", datos);

        mav.addObject(new Pelicula());
        mav.setViewName("altaPelicula");
        return mav;
    }

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaPelicula.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(Pelicula p, HttpServletRequest request) {
        nombre = request.getParameter("nombre");
        String estudio = "SELECT * FROM Peliculas WHERE Nombre LIKE '" + nombre + "'";
        datos = this.jdbc.queryForList(estudio);

        if (datos.isEmpty()) {
            String sql = "INSERT INTO Peliculas(Nombre, OtrosNombres, idProductora, idDistribuidora, Duracion, Genero, Tipo, Clasificacion, Estreno, EstrenoMexico, Calificacion, idDirector, Portada) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            this.jdbc.update(sql, p.getNombre(), p.getOtrosNombres(), p.getIdProductora(), p.getIdDistribuidora(), p.getDuracion(), p.getGenero(), p.getTipo(), p.getClasificacion(), p.getEstreno(), p.getEstrenoMexico(), p.getCalificacion(), p.getIdDirector(), p.getPortada());
            return new ModelAndView("redirect:/altaPelicula.htm");
        }
        return new ModelAndView("redirect:/listaPelicula.htm");

    }

    //PELICULA POR ESTRENAR
    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaPelicula1.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar1() {
        //Productora
        String sql = "SELECT * from Productora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaProductora", datos);

        //Distribuidora
        String sq = "SELECT * from Distribuidora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sq);
        mav.addObject("ListaDistribuidora", datos);

        // Genero
        String sql1 = "SELECT * from Genero ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("ListaGenero", datos);

        //Director
        String sql3 = "SELECT * from Director ORDER BY NombreArtistico";
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaDirector", datos);

        mav.addObject(new Pelicula());
        mav.setViewName("altaPelicula1");
        return mav;
    }

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaPelicula1.htm", method = RequestMethod.POST)
    public ModelAndView Agregar1(Pelicula p, HttpServletRequest request) {
        nombre = request.getParameter("nombre");
        String estudio = "SELECT * FROM Peliculas WHERE Nombre LIKE '" + nombre + "'";
        datos = this.jdbc.queryForList(estudio);

        if (datos.isEmpty()) {
            String sql = "INSERT INTO Peliculas(Nombre, OtrosNombres, idProductora, idDistribuidora, Genero, Tipo, Clasificacion, idDirector, Portada) VALUES (?,?,?,?,?,?,?,?,?)";
            this.jdbc.update(sql, p.getNombre(), p.getOtrosNombres(), p.getIdProductora(), p.getIdDistribuidora(), p.getGenero(), p.getTipo(), p.getClasificacion(), p.getIdDirector(), p.getPortada());
            return new ModelAndView("redirect:/altaPelicula.htm");
        }
        return new ModelAndView("redirect:/listaPelicula.htm");

    }

    //METODO PARA LISTA
    int idPelicula;
    List datos;

    @RequestMapping("listaPelicula.htm")
    public ModelAndView Listar() {
        String peliculas = "SELECT * FROM Vista_Peliculas";
        datos = this.jdbc.queryForList(peliculas);
        mav.addObject("Lista", datos);

        mav.setViewName("listaPelicula");
        return mav;
    }

    //Peliculas de Anime
    @RequestMapping("buscarPelicula.htm")
    public ModelAndView Buscar() {
        String peliculas = "SELECT * FROM Vista_Peliculas WHERE tipo = 'Anime'";
        datos = this.jdbc.queryForList(peliculas);
        mav.addObject("Lista", datos);

        mav.setViewName("buscarPelicula");
        return mav;
    }
    
    //Peliculas de Anime
    @RequestMapping("buscaPeliculaAnime.htm")
    public ModelAndView PeliculaAnime() {
        String panime = "SELECT * FROM Vista_TemporadasAnime WHERE Duration > '00:59:00' ORDER BY Inicio";
        datos = this.jdbc.queryForList(panime);
        mav.addObject("Lista", datos);

        mav.setViewName("buscaPeliculaAnime");
        return mav;
    }

    @RequestMapping(value = "editarPelicula.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar(HttpServletRequest request) {
        idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
        String sql = "SELECT * from Peliculas WHERE idPelicula=" + idPelicula;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);

        //Productora
        String sql1 = "SELECT * from Productora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("ListaProductora", datos);

        //Distribuidora
        String sq2 = "SELECT * from Distribuidora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sq2);
        mav.addObject("ListaDistribuidora", datos);

        // Genero
        String sql3 = "SELECT * from Genero ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaGenero", datos);

        // Clasificacion
        String sql4 = "SELECT * from Clasificacion ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql4);
        mav.addObject("ListaClasificacion", datos);

        //Director
        String sql5 = "SELECT * from Director ORDER BY NombreArtistico";
        datos = this.jdbc.queryForList(sql5);
        mav.addObject("ListaDirector", datos);

        //Select
        String sql6 = "SELECT\n"
                + "Peliculas.idPelicula,\n"
                + "REPLACE(Peliculas.OtrosNombres, 'Ã±', 'ñ') AS OtrosNombres,\n"
                + "Productora.Nombre AS Productora,\n"
                + "Distribuidora.Nombre AS Distribuidora,\n"
                + "Genero.Nombre AS Genero,\n"
                + "Clasificacion.Nombre AS Clasificacion,\n"
                + "Director.NombreArtistico AS Director\n"
                + "FROM Peliculas\n"
                + "INNER JOIN Productora\n"
                + "ON Productora.idProductora = Peliculas.idProductora\n"
                + "INNER JOIN Distribuidora\n"
                + "ON Distribuidora.idDistribuidora = Peliculas.idDistribuidora\n"
                + "INNER JOIN Genero\n"
                + "ON Genero.idGenero = Peliculas.Genero\n"
                + "INNER JOIN Clasificacion\n"
                + "ON Clasificacion.idClasificacion = Peliculas.Clasificacion\n"
                + "INNER JOIN Director\n"
                + "ON Peliculas.idDirector = Director.idDirector\n"
                + "AND Peliculas.idPelicula =" + idPelicula;
        datos = this.jdbc.queryForList(sql6);
        mav.addObject("ListaPeliculas", datos);

        mav.setViewName("editarPelicula");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarPelicula.htm", method = RequestMethod.POST)
    public ModelAndView Editar(Pelicula p) {
        String sql = "UPDATE Peliculas SET Nombre=?, OtrosNombres=?, idProductora=?, idDistribuidora=?, Duracion=?, Genero=?, Tipo=?, Clasificacion=?, Estreno=?, EstrenoMexico=?, Calificacion=?, idDirector=?, Portada=? WHERE idPelicula=?";
        this.jdbc.update(sql, p.getNombre(), p.getOtrosNombres(), p.getIdProductora(), p.getIdDistribuidora(), p.getDuracion(), p.getGenero(), p.getTipo(), p.getClasificacion(), p.getEstreno(), p.getEstrenoMexico(), p.getCalificacion(), p.getIdDirector(), p.getPortada(), idPelicula);
        return new ModelAndView("redirect:/listaPelicula.htm");
    }

    //METODO PARA ELIMINAR
    @RequestMapping(value = "eliminarPelicula.htm")
    public ModelAndView Eliminar(HttpServletRequest request) {
        idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
        String sql = "DELETE from Peliculas WHERE idPelicula=" + idPelicula;
        this.jdbc.update(sql);
        return new ModelAndView("redirect:/listaPelicula.htm");
    }
}
