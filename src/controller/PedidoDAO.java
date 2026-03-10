/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import model.Pedido;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.ModuloConexao;
import model.Cliente;


/**
 *
 * @author vitor
 */
public class PedidoDAO {
    
    private Connection con;
    
    public PedidoDAO(){
        this.con = ModuloConexao.conectar();
    }
    
    
    public List<Cliente> listarClienteNome(String nome) {
        try {

            //1 passo criar a lista
            List<Cliente> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
           String sql ="select id_cliente as id, nome, telefone from clientes where nome like ?";
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente obj = new Cliente();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setTelefone(rs.getString("fone"));
                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }
    
    public void adicionarPedido(Pedido obj) {

        try {
            //1 passo - criar o sql
            String sql = "insert into pedidos(id_cliente, nome_cliente, status, valor_total, observacoes ) values(?,?,?,?,?)";
            //2 passo o conectar o banco de dados e organizar o comando sql
            con = ModuloConexao.conectar();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId_Cliente());
            stmt.setString(2, obj.getNome_Cliente());
            stmt.setString(3, obj.getStatus());
            stmt.setDouble(4, obj.getValor());
            stmt.setString(5, obj.getObservacoes());
            
            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso!!");

        }catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    
    public void excluirPedido(Pedido obj) throws SQLException{

        try{
        String sql = "delete from pedidos where id_pedido =?";
        //2 passo o conectar o banco de dados e organizar o comando sql
            con = ModuloConexao.conectar();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
                        
            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Pedido excluido com sucesso!!");
            
        } catch (SQLIntegrityConstraintViolationException e1) {
            JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login.");
        
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    
    public void alterarPedido(Pedido obj) {
        try {
            //1 passo - criar o sql
            String sql = "UPDATE pedidos SET status=?, valor_total=?, observacoes=? WHERE id_pedido=?";
            //2 passo o conectar o banco de dados e organizar o comando sql
            con = ModuloConexao.conectar();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getStatus());
            stmt.setDouble(2, obj.getValor());
            stmt.setString(3, obj.getObservacoes());
            stmt.setInt(4, obj.getId());
            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!!");
        
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
            
            
            
    public Pedido listarPedidoId(int id) {
        try {
            //1 passo - criar o sql , organizar e executar.
            String sql = "select * from pedidos where id_pedido = ?";
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Pedido pedido = new Pedido();
            if(rs.next()){
            Cliente obj = new Cliente();
            obj.setId(rs.getInt("id_cliente"));
            pedido = new Pedido();
            
            pedido.setId(rs.getInt("id_pedido"));
            pedido.setId_Cliente(rs.getInt("id_cliente"));
            pedido.setNome_Cliente(rs.getString("nome_cliente"));
            pedido.setData(rs.getDate("data_pedido"));
            pedido.setStatus(rs.getString("status"));
            pedido.setValor(rs.getDouble("valor_total"));
            pedido.setObservacoes(rs.getString("observacoes"));
            
            
            }
            
            return pedido;
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }
    
}
