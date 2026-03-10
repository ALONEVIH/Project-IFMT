/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author vitor
 */
public class Pedido {
    
    private int id;
    private String nome_Cliente;
    private int id_Cliente;
    private Cliente cliente;
    private String status;
    private double valor;
    private String observacoes;
    private java.sql.Date data;
    
    
    public Pedido(){   
    }
    
    public Pedido(String status, double valor, String observacoes, int id){
        this.id = id;
        this.status = status;
        this.valor = valor;
        this.observacoes = observacoes;
    }
    
    public Pedido(int id, int id_Cliente, String nome_Cliente,  String status, double valor, String observacoes) {
        this.id = id;
        this.nome_Cliente = nome_Cliente;
        this.id_Cliente = id_Cliente;
        this.status = status;
        this.valor = valor;
        this.observacoes = observacoes;
    }
    
    public Pedido(String nome_Cliente, int id_Cliente,  String status, double valor, String observacoes) {
        this.nome_Cliente = nome_Cliente;
        this.id_Cliente = id_Cliente;
        this.status = status;
        this.valor = valor;
        this.observacoes = observacoes;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_Cliente() {
        return nome_Cliente;
    }

    public void setNome_Cliente(String nome_Cliente) {
        this.nome_Cliente = nome_Cliente;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }
    
    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(java.sql.Date data) {
        this.data = data;
    }
    
  
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    
}
