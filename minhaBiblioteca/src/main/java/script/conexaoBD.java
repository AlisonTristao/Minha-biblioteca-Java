/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package script;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aliso
 */
public class conexaoBD {
    
    //inserir no BD e retorna o cod
    //se der errado volta -1
    public int inserir(Object obj){//inserir no bd
        Connection conn = null;
        try {//tenta inserir no BD
            conn = new ConnectionFactory().getConnection();
            //dados a serem inseridos
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement
            ("INSERT INTO tabela (coluna1, coluna2) " //colunas
                + "VALUES (?, ?)"); //valores
                
            //dados do seu objeto com os dados da tabela
            pstmt.setString(1, "dado1");
            pstmt.setString(2, "dado2");
                
            //executa
            pstmt.execute();
            
            //retorna o cod se for autoincrement
            int key = -1;
            /*//codigo q retorna o codigo da chave primaria gerado
            com.mysql.jdbc.ResultSet rs = (com.mysql.jdbc.ResultSet) pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {//se inseriu ele retorna a key
                key = (int) rs.getInt(1);
                //o cod tem q ser a coluna 1 (tentei digitar "cod" e bugou)
            }*/
            
            //fecha conexão
            conn.close();
            return key;//retorna o cod            
        } catch (SQLException ex) {
            //mesagem de erro
            /*JOptionPane.showMessageDialog(null, 
                "Houve um problema!", //mensagem
                "Erro", // titulo da janela 
                JOptionPane.ERROR_MESSAGE);
            */
            ex.printStackTrace();
            return -1;
        }
    }
    
    //exclui do BD e retorna se deu certo
    public boolean excluir(int cod){//excluir
        java.sql.Connection conn = null;
        try {//tenta conectar com o bd
            conn = new ConnectionFactory().getConnection();
            java.sql.PreparedStatement pstmt = conn.prepareStatement
            ("DELETE FROM tabela "
                + "WHERE Cod=?"); 
            
                pstmt.setInt(1, cod);
                
            //executa
            pstmt.execute();

            //fecha conexão
            conn.close();
            return true;
        } catch (SQLException ex) {
            //mesagem de erro
            /*JOptionPane.showMessageDialog(null, 
                "Houve um problema!", //mensagem
                "Erro", // titulo da janela 
                JOptionPane.ERROR_MESSAGE);
            */
            ex.printStackTrace();

           //retorna falso
           return false;
        } 
    }
    
    //altera e retorna se deu certo
    public boolean alterar(Object obj){//altera
        java.sql.Connection conn = null;
        try {//tenta conectar com o bd
            conn = new ConnectionFactory().getConnection();
            java.sql.PreparedStatement pstmt = conn.prepareStatement
            ("UPDATE tabela SET "
                + "coluna1 = ?, coluna2 =? "
                + "WHERE Cod=?");
                //valores
                pstmt.setString(1, "dado1");
                pstmt.setString(2, "dado2");
                
                pstmt.setInt(1, 1);
                
            //executa
            pstmt.executeUpdate();
            
            //fecha conexão
            conn.close();
            return true;
        } catch (SQLException ex) {
            //mesagem de erro
            /*JOptionPane.showMessageDialog(null, 
                "Houve um problema!", //mensagem
                "Erro", // titulo da janela 
                JOptionPane.ERROR_MESSAGE);
            */
            ex.printStackTrace();
            return false;
        }
    }
    
    //busca algo no BD
    public Object buscar(int cod){//busca 
        java.sql.Connection conn = null;
        try {//tenta conectar com o bd
            conn = new ConnectionFactory().getConnection();
            java.sql.PreparedStatement pstmt = conn.prepareStatement
            ("SELECT * FROM tabela "
                + "WHERE cod=?");
            
            //cod do cliente
            pstmt.setInt(1, cod);
            ResultSet rs = pstmt.executeQuery(); 
            
            if (rs.next()) {//se tiver um resultado 
                //dados do que buscou
                //obj.setDado(rs.getString("coluna1"));
            }
            
            //fecha conexão
            conn.close();
            //*return obj;//retorna o obj   
        } catch (SQLException ex) {
            //mensagem de erro
            /*JOptionPane.showMessageDialog(null, 
                "Houve um problema!", //mensagem
                "Erro", // titulo da janela 
                JOptionPane.ERROR_MESSAGE);
            */
            ex.printStackTrace();
        }
        return null;
    }
    
    //busca uma lista no BD
    public List<Object> listar(){//lista 
        java.sql.Connection conn = null;
        List<Object> lista = new ArrayList<>();//cria uma lista vazia
        try {//tenta conectar com o bd        
            conn = new ConnectionFactory().getConnection();
            java.sql.PreparedStatement pstmt = conn.prepareStatement
            ("SELECT * FROM tabela ORDER BY coluna ASC");
                ResultSet rs = pstmt.executeQuery();//busca
            
            while (rs.next()) {            
                //enquanto tiver linhas na tabela ele preenche a lista com elas
                    
                //dados do que buscou
                //obj.setDado(rs.getString("coluna1"));
                    
                //adiciona cada linha do bd na lista
                //*lista.add(obj);
            }
            
            //fecha conexão
            conn.close();
        } catch (SQLException ex) {  
            //mesagem de erro
            /*JOptionPane.showMessageDialog(null, 
                "Houve um problema na listagem dos clientes!", //mensagem
                "Erro", // titulo da janela 
                JOptionPane.ERROR_MESSAGE);
            */
            ex.printStackTrace();
        }

        return lista;//retorna a lista
    }
}
