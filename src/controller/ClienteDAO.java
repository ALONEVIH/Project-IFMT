/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

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
import model.Pedido;
import model.WebServiceCep;



/**
 *
 * @author vitor
 */
public class ClienteDAO {
    
    private Connection con;
    
    public ClienteDAO(){
        this.con = ModuloConexao.conectar();
    }
    
     public void adicionarCliente(Cliente obj) {
        
        try {
            //1 passo - criar o sql
        String sql = "insert into clientes(nome, documento, tipo_cliente, telefone, email, logradouro, numero, complemento, bairro, "
                + "cidade, estado, cep, observacoes) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //2 passo o conectar o banco de dados e organizar o comando sql
            con = ModuloConexao.conectar();
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getDocumento());
                stmt.setString(3, obj.getTipo_Cliente());
                stmt.setString(4, obj.getTelefone());
                stmt.setString(5, obj.getEmail());
                stmt.setString(6, obj.getLogradouro());
                stmt.setString(7, obj.getNumero());
                stmt.setString(8, obj.getComplemento());
                stmt.setString(9, obj.getBairro());
                stmt.setString(10, obj.getCidade());
                stmt.setString(11, obj.getEstado());
                stmt.setString(12, obj.getCep());
                stmt.setString(13, obj.getObservacoes());
                
                //3 passo - executar o comando sql
                stmt.execute();
            }
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso"
                    + ""
                    + ""
                    + "o com sucesso!!");
            
        } catch (SQLIntegrityConstraintViolationException e1) {
            JOptionPane.showMessageDialog(null, "CPF"
                    + " em uso.\nEscolha outro login.");
        
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
     
     
    public void alterarCliente(Cliente obj) {
        
        try {
            //1 passo - criar o sql
        String sql = "update clientes set nome=?, documento=?, tipo_cliente=?, telefone=?, email=?, logradouro=?, numero=?, complemento=?, bairro=?, "
                + "cidade=?, estado=?, cep=?, observacoes=?  where id_cliente=?";
        //2 passo o conectar o banco de dados e organizar o comando sql
            con = ModuloConexao.conectar();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getDocumento());
            stmt.setString(3, obj.getTipo_Cliente());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getEmail());
            stmt.setString(6, obj.getLogradouro());
            stmt.setString(7, obj.getNumero());
            stmt.setString(8, obj.getComplemento());
            stmt.setString(9, obj.getBairro());
            stmt.setString(10, obj.getCidade());
            stmt.setString(11, obj.getEstado());
            stmt.setString(12, obj.getCep());
            stmt.setString(13, obj.getObservacoes());
            stmt.setInt(14, obj.getId());
                        
            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!!");
            
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
    
    
    public void excluirCliente(Cliente obj){
        
    try {
            //1 passo - criar o sql
        String sql = "delete from clientes where id_cliente =?";
        //2 passo o conectar o banco de dados e organizar o comando sql
            con = ModuloConexao.conectar();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
                        
            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!!");
            
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
     
     
     public List<Cliente> listarCliente() {
        try {

            //1 passo criar a lista
            List<Cliente> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from clientes";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente obj = new Cliente();

                obj.setId(rs.getInt("id_cliente"));
                obj.setNome(rs.getString("nome"));
                obj.setDocumento(rs.getString("documento"));
                obj.setTipo_Cliente(rs.getString("tipo_cliente"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setEmail(rs.getString("email"));
                obj.setLogradouro(rs.getString("logradouro"));
                obj.setNumero(rs.getString("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                obj.setCep(rs.getString("cep"));
                obj.setDataCadastro(rs.getDate("data_cadastro"));
                obj.setObservacoes(rs.getString("observacoes"));
                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

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
                obj.setTelefone(rs.getString("telefone"));
                lista.add(obj);
                Pedido pedido = new Pedido();
                pedido.setCliente(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    } 
    
    public List<Cliente> listarClienteConsultaNome(String nome) {
        try {

            //1 passo criar a lista
            List<Cliente> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql ="select id_cliente as id, nome, documento, tipo_cliente, telefone, email, logradouro, numero, complemento, bairro, cidade, estado from clientes where nome like ?";
            PreparedStatement stmt;
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setDocumento(rs.getString("documento"));
                obj.setTipo_Cliente(rs.getString("tipo_cliente"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setEmail(rs.getString("email"));
                obj.setLogradouro(rs.getString("logradouro"));
                obj.setNumero(rs.getString("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                lista.add(obj);
                
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    } 
     
	
    public Cliente buscaCep(String cep) {
       
        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
       

        Cliente obj = new Cliente();

        if (webServiceCep.wasSuccessful()) {
            obj.setLogradouro(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setEstado(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }

    }

    
}
