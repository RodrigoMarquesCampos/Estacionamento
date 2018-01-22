/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.estacionamento.aplicacao;

/**
 *
 * @author Rodrigo
 */
public class Usuario implements Entidade{
    private String senha;
    private String login;
    private TipoUsuario tipo;
    private int id = 0;
    private String nome;

    public Usuario() {
        
    }

    public Usuario(String senha, String login, String nome, TipoUsuario tipo) {
        this.nome = nome;
        this.senha = senha;
        this.login = login;
        this.tipo = tipo;
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public String getLogin() {
        return login;
    }

    public void setNomee(String nome)throws ViolacaoRegrasNegocioException{
        if(nome == null)
            throw new ViolacaoRegrasNegocioException("O nome do Cliente não pode ficar vazio!"); 
        
        char c;
        int cont = 0;
            
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
            throw new ViolacaoRegrasNegocioException("O nome do Usuario não pode ter numeros e/ou caracteres especiais!");
  
        this.nome = nome;
    }
    
    public void setSenha(String senha) throws ViolacaoRegrasNegocioException{
        if(senha.length() < 6)
            throw new ViolacaoRegrasNegocioException("A senha deve ter no minimo 6 caracteres!");
            
        this.senha = senha;
    }
    
    public void setSenhaa(String senha){
        this.senha = senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Usuario(int id, String nome, String senha, String login) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.login = login;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
}
