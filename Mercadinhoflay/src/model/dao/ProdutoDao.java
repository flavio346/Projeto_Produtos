/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produto;

/**
 *
 * @author flavio
 */
public class ProdutoDao {
    public void create(Produto p){

            Connection con = conection.ConnectionFactory.getConnection();
            PreparedStatement stmt = null;

                
                try {
                    stmt = con.prepareStatement("INSERT INTO produto(descricao,qtd,preco)VALUES(?,?,?)");
                  
                    stmt.setString(1, p.getDescricao());
                    stmt.setInt(2, p.getQtd());
                    stmt.setDouble(3, p.getPreco());
                    
                    
                    stmt.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
                } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null, "Erro ao Salvar "+ex);
    }finally{
                    ConnectionFactory.closedConnection(con,stmt);
                } 
    
}
    public List<Produto> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQtd(rs.getInt("qtd"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sinto Muito Ocorreu um ERRO!!!!");
        }finally{
            ConnectionFactory.closedConnection(con, stmt, rs);
        }
        return produtos;
        
        
    }

       public void update(Produto p){

            Connection con = conection.ConnectionFactory.getConnection();
            PreparedStatement stmt = null;

                
                try {
                    stmt = con.prepareStatement("UPDATE produto SET descricao = ?,qtd = ?,preco = ? WHERE id = ?");
                  
                    stmt.setString(1, p.getDescricao());
                    stmt.setInt(2, p.getQtd());
                    stmt.setDouble(3, p.getPreco());
                    stmt.setInt(4, p.getId());
                    
                    
                    stmt.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
                } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null, "Erro ao Atualizar "+ex);
    }finally{
                    ConnectionFactory.closedConnection(con,stmt);
                } 
    
}
       
       public void delete(Produto p){

            Connection con = conection.ConnectionFactory.getConnection();
            PreparedStatement stmt = null;

                
                try {
                    stmt = con.prepareStatement("DELETE FROM produto WHERE id = ?");
                  
                    stmt.setInt(1, p.getId());
                    
                    
                    stmt.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Excluido com Sucesso");
                } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null, "Erro ao Excluir "+ex);
        }       finally{
                    ConnectionFactory.closedConnection(con,stmt);
                } 
    
}
        public List<Produto> readForDesc(String desc){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ?");
            stmt.setString(1,"%"+desc+"%");
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQtd(rs.getInt("qtd"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sinto Muito Ocorreu um ERRO!!!!");
        }finally{
            ConnectionFactory.closedConnection(con, stmt, rs);
        }
        return produtos;
        
        
    }
}
    
 

