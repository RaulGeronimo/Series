/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.TemporadasCaricatura;
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
public class controladorTemporadasCaricatura {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    
    ////////////////////FINALIZADO
    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaTemporadaCaricatura.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar() {
        //Serie
        String sql = "SELECT *, REPLACE(Caricatura.OtrosNombres, 'Ã±', 'ñ') AS OtrosNombres from Caricatura ORDER BY OtrosNombres";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaCaricatura", datos);

        mav.addObject(new TemporadasCaricatura());
        mav.setViewName("altaTemporadaCaricatura");
        return mav;
    }

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaTemporadaCaricatura.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(TemporadasCaricatura t) {
        String sql = "INSERT INTO Temporadas_Caricatura(idCaricatura, Nombre, Capitulos, Duracion, Calificacion, FechaInicio, FechaFin) VALUES (?,?,?,?,?,?,?)";
        this.jdbc.update(sql, t.getIdCaricatura(), t.getNombre(), t.getCapitulos(), t.getDuracion(), t.getCalificacion(), t.getFechaInicio(), t.getFechaFin());
        return new ModelAndView("redirect:/altaTemporadaCaricatura.htm");
    }
    
    
    ////////////////////EMISION
    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaTemporadaCaricaturaEmision.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar1() {
        //Serie
        String sql = "SELECT *, REPLACE(Caricatura.OtrosNombres, 'Ã±', 'ñ') AS OtrosNombres from Caricatura ORDER BY OtrosNombres";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaCaricatura", datos);

        mav.addObject(new TemporadasCaricatura());
        mav.setViewName("altaTemporadaCaricaturaEmision");
        return mav;
    }

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaTemporadaCaricaturaEmision.htm", method = RequestMethod.POST)
    public ModelAndView Agregar1(TemporadasCaricatura t) {
        String sql = "INSERT INTO Temporadas_Caricatura(idCaricatura, Nombre, Capitulos, Duracion, FechaInicio) VALUES (?,?,?,?,?)";
        this.jdbc.update(sql, t.getIdCaricatura(), t.getNombre(), t.getCapitulos(), t.getDuracion(), t.getFechaInicio());
        return new ModelAndView("redirect:/altaTemporadaCaricatura.htm");
    }

    //METODO PARA LISTA
    int idTemporada;
    List datos;

    @RequestMapping("listaTemporadaCaricatura.htm")
    public ModelAndView Listar() {
        String temporadas_caricaturas = "SELECT * FROM Vista_TemporadasCaricatura";
        datos = this.jdbc.queryForList(temporadas_caricaturas);
        mav.addObject("Lista", datos);

        mav.setViewName("listaTemporadaCaricatura");
        return mav;
    }

////////////FINALIZADO
    @RequestMapping(value = "editarTemporadaCaricatura.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar(HttpServletRequest request) {
        idTemporada = Integer.parseInt(request.getParameter("idTemporada"));
        String sql = "SELECT * from Temporadas_Caricatura WHERE idTemporada=" + idTemporada;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);

        //Caricatura
        String sql2 = "SELECT * from Caricatura ORDER BY OtrosNombres";
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("ListaCaricatura", datos);

        //Select
        String sql3 = "SELECT\n"
                + "Temporadas_Caricatura.idTemporada,\n"
                + "CONCAT_WS(' - ', Caricatura.Nombre, Caricatura.OtrosNombres) AS Caricatura\n"
                + "FROM Temporadas_Caricatura\n"
                + "INNER JOIN Caricatura\n"
                + "ON Temporadas_Caricatura.idCaricatura = Caricatura.idCaricatura\n"
                + "AND idTemporada = " + idTemporada;
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaTemporada", datos);

        mav.setViewName("editarTemporadaCaricatura");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarTemporadaCaricatura.htm", method = RequestMethod.POST)
    public ModelAndView Editar(TemporadasCaricatura t) {
        String sql = "UPDATE Temporadas_Caricatura SET idCaricatura=?, Nombre=?, Capitulos=?, Duracion=?, Calificacion=?, FechaInicio=?, FechaFin=? WHERE idTemporada=?";
        this.jdbc.update(sql, t.getIdCaricatura(), t.getNombre(), t.getCapitulos(), t.getDuracion(), t.getCalificacion(), t.getFechaInicio(), t.getFechaFin(), idTemporada);
        return new ModelAndView("redirect:/listaTemporadaCaricatura.htm");
    }
    
    
    
    ////////////Emision
    @RequestMapping(value = "editarTemporadaCaricaturaEmision.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar1(HttpServletRequest request) {
        idTemporada = Integer.parseInt(request.getParameter("idTemporada"));
        String sql = "SELECT * from Temporadas_Caricatura WHERE idTemporada=" + idTemporada;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);

        //Caricatura
        String sql2 = "SELECT * from Caricatura ORDER BY OtrosNombres";
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("ListaCaricatura", datos);

        //Select
        String sql3 = "SELECT\n"
                + "Temporadas_Caricatura.idTemporada,\n"
                + "CONCAT_WS(' - ', Caricatura.Nombre, Caricatura.OtrosNombres) AS Caricatura\n"
                + "FROM Temporadas_Caricatura\n"
                + "INNER JOIN Caricatura\n"
                + "ON Temporadas_Caricatura.idCaricatura = Caricatura.idCaricatura\n"
                + "AND idTemporada = " + idTemporada;
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaTemporada", datos);

        mav.setViewName("editarTemporadaCaricaturaEmision");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarTemporadaCaricaturaEmision.htm", method = RequestMethod.POST)
    public ModelAndView Editar1(TemporadasCaricatura t) {
        String sql = "UPDATE Temporadas_Caricatura SET idCaricatura=?, Nombre=?, Capitulos=?, Duracion=?,  FechaInicio=? WHERE idTemporada=?";
        this.jdbc.update(sql, t.getIdCaricatura(), t.getNombre(), t.getCapitulos(), t.getDuracion(), t.getFechaInicio(), idTemporada);
        return new ModelAndView("redirect:/listaTemporadaCaricatura.htm");
    }

    //METODO PARA ELIMINAR
    @RequestMapping(value = "eliminarTemporadaCaricatura.htm")
    public ModelAndView Eliminar(HttpServletRequest request) {
        idTemporada = Integer.parseInt(request.getParameter("idTemporada"));
        String sql = "DELETE from Temporadas_Caricatura WHERE idTemporada=" + idTemporada;
        this.jdbc.update(sql);
        return new ModelAndView("redirect:/listaTemporadaCaricatura.htm");
    }
}
