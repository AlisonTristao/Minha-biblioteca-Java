/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package script;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Aliso
 */
public class ConnectionFactory {
    public static Connection getConnection() {
        
        //dados da conexão
        String url = "jdbc:mysql://ip:porta/nome do BD";
        String usuario = "root";
        String senha = "123";
        
        try {
            //drive sql
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //retorna a conexão
            return (Connection) DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException ex) {
            //erro
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
