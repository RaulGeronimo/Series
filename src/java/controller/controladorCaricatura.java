/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.Caricatura;
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
public class controladorCaricatura {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaCaricatura.htm", method = RequestMethod.GET)
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

        //Director
        String sql3 = "SELECT * from Director ORDER BY NombreArtistico";
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaDirector", datos);

        mav.addObject(new Caricatura());
        mav.setViewName("altaCaricatura");
        return mav;
    }

    String nombre;

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaCaricatura.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(Caricatura c, HttpServletRequest request) {
        String sql = "INSERT INTO Caricatura(Nombre, OtrosNombres, Genero, idProductora, idDistribuidora, idDirector, Portada) VALUES (?,?,?,?,?,?,?)";
        this.jdbc.update(sql, c.getNombre(), c.getOtrosNombres(), c.getGenero(), c.getIdProductora(), c.getIdDistribuidora(), c.getIdDirector(), c.getPortada());
        return new ModelAndView("redirect:/altaCaricatura.htm");
    }

    //METODO PARA LISTA
    int idCaricatura;
    List datos;

    @RequestMapping("listaCaricatura.htm")
    public ModelAndView Listar() {
        String caricaturas = "SELECT * FROM Vista_Caricaturas";
        datos = this.jdbc.queryForList(caricaturas);
        mav.addObject("Lista", datos);

        mav.setViewName("listaCaricatura");
        return mav;
    }

    @RequestMapping("buscaPeliculaCaricatura.htm")
    public ModelAndView Buscar() {
        String caricaturas = "SELECT * FROM Vista_Peliculas WHERE tipo = 'Caricatura'";
        datos = this.jdbc.queryForList(caricaturas);
        mav.addObject("Lista", datos);

        mav.setViewName("buscaPeliculaCaricatura");
        return mav;
    }

    @RequestMapping(value = "buscaTemporadaCaricatura.htm", method = RequestMethod.GET)
    public ModelAndView Buscar(HttpServletRequest request) {
        idCaricatura = Integer.parseInt(request.getParameter("idCaricatura"));
        String temporadas_caricaturas = "SELECT * FROM Vista_TemporadasCaricatura WHERE idCaricatura = " + idCaricatura;
        datos = this.jdbc.queryForList(temporadas_caricaturas);
        mav.addObject("Lista", datos);

        //Nombre de la Caricatura
        String sql2 = "SELECT idCaricatura, Nombre from Caricatura WHERE idCaricatura = " + idCaricatura;
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("Nombre", datos);

        mav.setViewName("buscaTemporadaCaricatura");
        return mav;
    }

    @RequestMapping(value = "buscaTemporadaCaricatura2.htm", method = RequestMethod.GET)
    public ModelAndView Buscar2(HttpServletRequest request) {
        idCaricatura = Integer.parseInt(request.getParameter("idCaricatura"));
        String temporadas_caricaturas = "SELECT * FROM Vista_TemporadasCaricatura WHERE idCaricatura = " + idCaricatura + " ORDER BY Inicio";
        datos = this.jdbc.queryForList(temporadas_caricaturas);
        mav.addObject("Lista", datos);

        //Nombre de la Caricatura
        String caricaturas = "SELECT * FROM Vista_Caricaturas WHERE idCaricatura = " + idCaricatura;
        datos = this.jdbc.queryForList(caricaturas);
        mav.addObject("Nombre", datos);

        mav.setViewName("buscaTemporadaCaricatura2");
        return mav;
    }

    @RequestMapping(value = "editarCaricatura.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar(HttpServletRequest request) {
        idCaricatura = Integer.parseInt(request.getParameter("idCaricatura"));
        String sql = "SELECT * from Caricatura WHERE idCaricatura=" + idCaricatura;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);

        //Productora
        String sq = "SELECT * from Productora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sq);
        mav.addObject("ListaProductora", datos);

        //Distribuidora
        String s = "SELECT * from Distribuidora ORDER BY Nombre";
        datos = this.jdbc.queryForList(s);
        mav.addObject("ListaDistribuidora", datos);

        //Director
        String sql4 = "SELECT * from Director ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql4);
        mav.addObject("ListaDirector", datos);

        // Genero
        String sql1 = "SELECT * from Genero ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("ListaGenero", datos);

        //Select
        String sql3 = "SELECT\n"
                + "Caricatura.idCaricatura,\n"
                + "Caricatura.OtrosNombres AS NombreSecundario,\n"
                + "Genero.Nombre AS Genero,\n"
                + "Productora.Nombre AS Productora,\n"
                + "Distribuidora.Nombre AS Distribuidora,\n"
                + "Director.NombreArtistico AS Director\n"
                + "FROM Caricatura\n"
                + "INNER JOIN Genero\n"
                + "ON Caricatura.Genero = Genero.idGenero\n"
                + "INNER JOIN Productora\n"
                + "ON Caricatura.idProductora = Productora.idProductora\n"
                + "INNER JOIN Productora AS Distribuidora\n"
                + "ON Caricatura.idDistribuidora = Distribuidora.idProductora\n"
                + "INNER JOIN Director\n"
                + "ON Caricatura.idDirector = Director.idDirector\n"
                + "AND Caricatura.idCaricatura = " + idCaricatura;
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaCaricatura", datos);

        mav.setViewName("editarCaricatura");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarCaricatura.htm", method = RequestMethod.POST)
    public ModelAndView Editar(Caricatura c) {
        String sql = "UPDATE Caricatura SET Nombre=?, OtrosNombres=?, Genero=?, idProductora=?, idDistribuidora=?, idDirector=?, Portada=? WHERE idCaricatura=?";
        this.jdbc.update(sql, c.getNombre(), c.getOtrosNombres(), c.getGenero(), c.getIdProductora(), c.getIdDistribuidora(), c.getIdDirector(), c.getPortada(), idCaricatura);
        return new ModelAndView("redirect:/listaCaricatura.htm");
    }

    //METODO PARA ELIMINAR
    @RequestMapping(value = "eliminarCaricatura.htm")
    public ModelAndView Eliminar(HttpServletRequest request) {
        idCaricatura = Integer.parseInt(request.getParameter("idCaricatura"));
        String sql = "DELETE from Caricatura WHERE idCaricatura=" + idCaricatura;
        this.jdbc.update(sql);
        return new ModelAndView("redirect:/listaCaricatura.htm");
    }
}
