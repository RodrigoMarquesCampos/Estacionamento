/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.estacionamento.percistencia;

import ihm.estacionamento.aplicacao.ClienteRepositorio;
import ihm.estacionamento.aplicacao.Cliente;
import java.sql.SQLException;

/**
 *
 * @author rodrigo
 */
public abstract class ClienteDAO extends DAOGenerico<Cliente> implements ClienteRepositorio {
  
    public ClienteDAO() throws ClassNotFoundException, SQLException {
        super();
    }
    
    protected String GetConsultaInsert(){
        return "instert into Usuarios(nome, telefone, endereco) values('?','?','?')";
    }
    
    protected String GetConsultaUpdate(){
        return "update Clientes set nome =  '?', telefone = '?', endereco = '?' where id = ?";
    }
    
    protected String GetConsultaDelete(){
        return "delete from Clientes where id = ?";
    }
    
    protected String GetConsultaBuscar(){
        return "select id, nome, telefone, endereco from Clientes";
    }
    
    protected String GetConsultaAbrir(){
        return "select id, nome, telefone, endereco from Clientes where id = ?";
    }
    
    protected Void SetBuscaFiltro(Cliente filtro){
        if(filtro.getId() > 0)
            this.adicionaFiltro("id", 0);
        
        if(filtro.getNome() != null && !filtro.getNome().isEmpty())
    }
    
}