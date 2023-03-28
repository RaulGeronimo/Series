/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.Director;
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
public class controladorDirector {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    /////////////////////////ACTIVO
    @RequestMapping(value = "altaDirector.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregarD() {
        //Pais
        String sql = "SELECT idPais, Nombre, Nacionalidad, CONCAT_WS(' / ', Nombre, Nacionalidad) AS Pais FROM Pais ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaPais", datos);

        mav.addObject(new Director());
        mav.setViewName("altaDirector");
        return mav;
    }

    String nombre;

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaDirector.htm", method = RequestMethod.POST)
    public ModelAndView AgregarD(Director d, HttpServletRequest request) {
        nombre = request.getParameter("nombre");
        String caricatura = "SELECT * FROM Director WHERE NombreArtistico LIKE '" + nombre + "'";
        datos = this.jdbc.queryForList(caricatura);

        if (datos.isEmpty()) {
            String sql = "INSERT INTO Director(Nombre, Apellidos, NombreArtistico, FechaNacimiento, Sexo, Estatura, Nacionalidad) values (?,?,?,?,?,?,?)";
            this.jdbc.update(sql, d.getNombre(), d.getApellidos(), d.getNombreDirector(), d.getFechaNacimiento(), d.getSexo(), d.getEstatura(), d.getNacionalidad());
            return new ModelAndView("redirect:/altaDirector.htm");
        }
        return new ModelAndView("redirect:/listaDirector.htm");

    }

    //INACTIVO
    @RequestMapping(value = "altaDirectorInactivo.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar() {
        //Pais
        String sql = "SELECT idPais, Nombre, Nacionalidad, CONCAT_WS(' / ', Nombre, Nacionalidad) AS Pais FROM Pais ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaPais", datos);

        mav.addObject(new Director());
        mav.setViewName("altaDirectorInactivo");
        return mav;
    }

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaDirectorInactivo.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(Director d, HttpServletRequest request) {
        nombre = request.getParameter("nombre");
        String caricatura = "SELECT * FROM Director WHERE NombreArtistico LIKE '" + nombre + "'";
        datos = this.jdbc.queryForList(caricatura);

        if (datos.isEmpty()) {
            String sql = "INSERT INTO Director(Nombre, Apellidos, NombreArtistico, FechaNacimiento, FechaDefuncion, Sexo, Estatura, Nacionalidad) values (?,?,?,?,?,?,?,?)";
            this.jdbc.update(sql, d.getNombre(), d.getApellidos(), d.getNombreDirector(), d.getFechaNacimiento(), d.getFechaDefuncion(), d.getSexo(), d.getEstatura(), d.getNacionalidad());
            return new ModelAndView("redirect:/altaDirector.htm");
        }
        return new ModelAndView("redirect:/listaDirector.htm");
    }

    //METODO PARA LISTA
    int idDirector;
    List datos;

    @RequestMapping("listaDirector.htm")
    public ModelAndView Listar() {
        String director = "SELECT * FROM Vista_Directores\n";
        datos = this.jdbc.queryForList(director);
        mav.addObject("Lista", datos);

        mav.setViewName("listaDirector");
        return mav;
    }

/////ACTIVO
    @RequestMapping(value = "editarDirector.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar(HttpServletRequest request) {
        //SELECT
        String sql1 = "SELECT \n"
                + "Director.idDirector, \n"
                + "IF(Director.Sexo = 'H', 'Hombre', 'Mujer') AS Sexo,\n"
                + "CONCAT_WS(' / ', Pais.Nombre, Pais.Nacionalidad) AS Pais \n"
                + "FROM Director \n"
                + "INNER JOIN Pais \n"
                + "ON Director.Nacionalidad = Pais.idPais \n"
                + "AND Director.idDirector = " + idDirector;
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("ListaDirector", datos);

        //Pais
        String sql0 = "SELECT idPais, Nombre, Nacionalidad, CONCAT_WS(' / ', Nombre, Nacionalidad) AS Pais FROM Pais ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql0);
        mav.addObject("ListaPais", datos);

        //Editar
        idDirector = Integer.parseInt(request.getParameter("idDirector"));
        String sql = "SELECT * FROM Director WHERE idDirector=" + idDirector;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);

        mav.setViewName("editarDirector");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarDirector.htm", method = RequestMethod.POST)
    public ModelAndView Editar(Director d) {
        String sql = "UPDATE Director SET Nombre=?, Apellidos=?, NombreArtistico=?, FechaNacimiento=?, Sexo=?, Estatura=?, Nacionalidad=? WHERE idDirector=?";
        this.jdbc.update(sql, d.getNombre(), d.getApellidos(), d.getNombreDirector(), d.getFechaNacimiento(), d.getSexo(), d.getEstatura(), d.getNacionalidad(), idDirector);
        return new ModelAndView("redirect:/listaDirector.htm");
    }

////INACTIVO
    @RequestMapping(value = "editarDirectorInactivo.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditard(HttpServletRequest request) {
        //SELECT
        String sql1 = "SELECT \n"
                + "Director.idDirector, \n"
                + "IF(Director.Sexo = 'H', 'Hombre', 'Mujer') AS Sexo,\n"
                + "CONCAT_WS(' / ', Pais.Nombre, Pais.Nacionalidad) AS Pais \n"
                + "FROM Director \n"
                + "INNER JOIN Pais \n"
                + "ON Director.Nacionalidad = Pais.idPais \n"
                + "AND Director.idDirector = " + idDirector;
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("ListaDirector", datos);

        //Pais
        String sql0 = "SELECT idPais, Nombre, Nacionalidad, CONCAT_WS(' / ', Nombre, Nacionalidad) AS Pais FROM Pais ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql0);
        mav.addObject("ListaPais", datos);

        //Editar
        idDirector = Integer.parseInt(request.getParameter("idDirector"));
        String sql = "SELECT * FROM Director WHERE idDirector=" + idDirector;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);

        mav.setViewName("editarDirectorInactivo");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarDirectorInactivo.htm", method = RequestMethod.POST)
    public ModelAndView Editard(Director d) {
        String sql = "UPDATE Director SET Nombre=?, Apellidos=?, NombreArtistico=?, FechaNacimiento=?, FechaDefuncion=?, Sexo=?, Estatura=?, Nacionalidad=? WHERE idDirector=?";
        this.jdbc.update(sql, d.getNombre(), d.getApellidos(), d.getNombreDirector(), d.getFechaNacimiento(), d.getFechaDefuncion(), d.getSexo(), d.getEstatura(), d.getNacionalidad(), idDirector);
        return new ModelAndView("redirect:/listaDirector.htm");
    }

    //METODO PARA ELIMINAR
    @RequestMapping(value = "eliminarDirector.htm")
    public ModelAndView Eliminar(HttpServletRequest request) {
        idDirector = Integer.parseInt(request.getParameter("idDirector"));
        String sql = "DELETE FROM Director WHERE idDirector=" + idDirector;
        this.jdbc.update(sql);
        return new ModelAndView("redirect:/listaDirector.htm");
    }
}
