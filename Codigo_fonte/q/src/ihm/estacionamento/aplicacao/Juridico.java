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
public class Juridico extends Cliente{
    private String cnpj;

    public Juridico(String cnpj) {
        this.cnpj = cnpj;
    }

    public Juridico(String cnpj, String nome, String telefone, int id, String endereco, TipoCliente tipo) {
        super(nome, telefone, id, endereco, tipo);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Juridico() {
    }
    
    
}
