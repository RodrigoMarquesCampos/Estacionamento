/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.estacionamento.aplicacao;

import java.util.Objects;

/**
 *
 * @author Rodrigo
 */
public class Cliente implements Entidade{
    private TipoCliente tipo;
    protected String nome;
    protected String telefone;
    protected int id;
    protected String endereco;
    
    public Cliente() {
        super();
    }
    
    
    
    public Cliente(String nome, String telefone, int id, String endereco, TipoCliente tipo) {
        this.nome = nome;
        this.telefone = telefone;
        this.id = id;
        this.endereco = endereco;
        this.tipo = tipo;
    }
 

    public TipoCliente getTipo() {
        return tipo;
    }


    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
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
    
    

    /**
     *
     * @param nome
     * @throws ViolacaoRegrasNegocioException
     */
    public void setNomee(String nome) throws ViolacaoRegrasNegocioException{
        if(nome == null)
            throw new ViolacaoRegrasNegocioException("O nome do Cliente não pode ficar vazio!"); 
        
        char c;
        int cont = 0;
            
        //Verificação de numeros e/ou caracteres especiais
        for(int i =0; i<nome.length(); i++){
            c = nome.charAt(i);
            
            if(((c >= 60) && (c <= 90)) || ((c >= 97) && (c<= 122)) || (c == 'á')
            || (c == 'â') || (c == 'ã') || (c == 'Á') || (c == 'Â') || (c == 'Ã')
            || (c == 'ê') || (c == 'é') || (c == 'É') || (c == 'Ê') || (c == 'ó')
            || (c == 'ô') || (c == 'õ') || (c == 'Ó') || (c == 'Ô') || (c == 'Õ') || (c == ' ')){
              cont++; 
            }
        }
        if(cont != nome.length())
            throw new ViolacaoRegrasNegocioException("O nome do Cliente não pode ter numeros e/ou caracteres especiais!");
        
        this.nome = nome;
        
    }


    @Override
    public String toString() {
        return this.nome;
    }

 
    
    
        
}
