/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.estacionamento.aplicacao;

import java.util.Date;

/**
 *
 * @author rodrigo
 */
public class Transacao implements Entidade{
    private double valor;
    private Date data;
    private StatusTransacao status;
    private int id;

    public Transacao(double valor, Date data, StatusTransacao status, int id) {
        this.valor = valor;
        this.data = data;
        this.status = status;
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public void setStatus(StatusTransacao status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
