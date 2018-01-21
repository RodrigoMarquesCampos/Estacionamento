/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.estacionamento.aplicacao;

/**
 *
 * @author rodrigo
 */
public class Fisico extends Cliente{
    private String cpf;

    public Fisico(String cpf) {
        this.cpf = cpf;
    }

    public Fisico(String cpf, String nome, String telefone, int id, String endereco, TipoCliente tipo) {
        super(nome, telefone, id, endereco, tipo);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    
    
}
