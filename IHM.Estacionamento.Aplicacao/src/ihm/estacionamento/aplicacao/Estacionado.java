/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.estacionamento.aplicacao;

import java.util.Date;
import java.util.List;

/**
 *
 * @author rodrigo
 */
public class Estacionado implements Entidade{
    private int id;
    private Date entrada;
    private Date saida;
    private double valor;
    private Veiculo veiculo;

    public Estacionado(int id, Date entrada, Date saida, double valor, Veiculo veiculo) {
        this.id = id;
        this.entrada = entrada;
        this.saida = saida;
        this.valor = valor;
        this.veiculo = veiculo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }    
    
}
