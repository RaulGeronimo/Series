/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author raul0
 */
public class conexionBD {
    public DriverManagerDataSource Conectar(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        
        dataSource.setUrl("jdbc:mysql://localhost:3306/Series?sessionVariables=LC_TIME_NAMES='es_MX'");
        dataSource.setUsername("root");
        //dataSource.setPassword("1829301");
        return dataSource;
    }
}
