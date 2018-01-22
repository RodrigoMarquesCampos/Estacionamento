/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.estacionamento.persistencia;

import ihm.estacionamento.aplicacao.ClienteRepositorio;
import ihm.estacionamento.aplicacao.Cliente;
import ihm.estacionamento.aplicacao.TipoCliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigo
 */
public class ClienteDAO extends DAOGenerico<Cliente> implements ClienteRepositorio {
  
    public ClienteDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into clientes(nome, telefone, endereco) values(?, ?, ?)";
    }

    @Override
    protected String getConsultaUpdate() {
        return "update clientes set nome =  ?, telefone = ?, endereco = ? where id = ?";
    }

    @Override
    protected String getConsultaDelete() {
        return "delete from clientes where id = ?";
    }

    @Override
    protected String getConsultaAbrir() {
        return "select id, nome, telefone, endereco from clientes where id = ?";
    }

    @Override
    protected String getConsultaBuscar() {
        return "select id, nome, telefone, endereco from clientes";
    }

    @Override
    protected String getConsultaId() {
        return "select max(id) from clientes";
    }

    @Override
    protected void setBuscaFiltros(Cliente filtro) {
        if(filtro.getId() > 0)
            this.adicionaFiltro("id", 0);
        
        if(filtro.getNome() != null && !filtro.getNome().isEmpty())
            this.adicionaFiltro("nome", filtro.getNome());
        
        if(filtro.getEndereco() != null && !filtro.getEndereco().isEmpty())
            this.adicionaFiltro("endereco", filtro.getEndereco());
        
        if(filtro.getTelefone() != null && !filtro.getTelefone().isEmpty())
            this.adicionaFiltro("telefone", filtro.getTelefone());
    }

    @Override
    protected void setParametros(PreparedStatement sql, Cliente obj) {
        try{
            sql.setString(1, obj.getNome());
            sql.setString(2, obj.getTelefone());
            sql.setString(3, obj.getEndereco());
           // sql.setInt(4, obj.getTipo().getId());
            
            if(obj.getId() > 0)
                sql.setInt(4, obj.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Cliente setDados(ResultSet resultado) {
        try{
            Cliente obj = new Cliente();
            
            obj.setNome(resultado.getString("nome"));
            obj.setTelefone(resultado.getString("telefone"));
            obj.setEndereco(resultado.getString("endereco"));
            obj.setTipo(TipoCliente.Abrir(resultado.getInt("id")));
            
            return obj;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}