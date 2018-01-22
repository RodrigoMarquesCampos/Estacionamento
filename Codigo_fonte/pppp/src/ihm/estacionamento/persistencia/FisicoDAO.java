/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.estacionamento.persistencia;

import ihm.estacionamento.aplicacao.Cliente;
import ihm.estacionamento.aplicacao.Fisico;
import ihm.estacionamento.aplicacao.FisicoRepositorio;
import ihm.estacionamento.aplicacao.TipoCliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo
 */
public class FisicoDAO extends DAOGenerico<Fisico> implements FisicoRepositorio{
    
    public FisicoDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into cliente_fisico(cliente_fk, cpf) values('?', '?')";
    }

    @Override
    protected String getConsultaUpdate() {
        return "update cliente_fisico set cliente_fk = ?, cpf = '?'";
    }

    @Override
    protected String getConsultaDelete() {
        return "delete from cliente_fisico where cliente_fk = ?";
    }

    @Override
    protected String getConsultaAbrir() {
        return "select clientes.id, clientes.nome, clientes.endereco, clientes.telefone,"
            + " cliente_fisico.cpf from clientes join cliente_fisico on clientes.id = cliente_fisico.cliente_fk"
            + " where cliente.id = ?";
    }

    @Override
    protected String getConsultaBuscar() {
        return "select clientes.id, clientes.nome, clientes.endereco, clientes.telefone,"
            + " cliente_fisico.cpf from clientes join cliente_fisico on clientes.id = cliente_fisico.cliente_fk ";
    }

    @Override
    protected String getConsultaId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setBuscaFiltros(Fisico filtro) {
        if(!filtro.getCpf().isEmpty() && filtro.getCpf() != null)
            this.adicionaFiltro("cpf", filtro.getCpf());
        
        if(filtro.getId()> 0)
            this.adicionaFiltro("cliente_fk", filtro.getId());
        
        if(filtro.getNome() != null && !filtro.getNome().isEmpty())
            this.adicionaFiltro("cliente.nome", filtro.getNome());
        
        if(filtro.getEndereco() != null && !filtro.getEndereco().isEmpty())
            this.adicionaFiltro("cliente.endereco", filtro.getEndereco());
        
        if(filtro.getTipo() != null)
            this.adicionaFiltro("cliente.tipo", filtro.getTipo().getId());
        
        if(filtro.getTelefone() != null && filtro.getTelefone().isEmpty())
            this.adicionaFiltro("cliente.telefone", filtro.getTelefone());                
    }

    @Override
    protected void setParametros(PreparedStatement sql, Fisico obj) {
        try{
            sql.setString(1, obj.getCpf());
            
            if(obj.getId() > 0)
                sql.setInt(2, obj.getId());
      
        } catch (SQLException ex) {
            Logger.getLogger(FisicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Fisico setDados(ResultSet resultado) {
        try{
            Fisico obj = new Fisico();
            obj.setCpf(resultado.getString("cliente_fisico.cpf"));
            obj.setEndereco(resultado.getString("clientes.endereco"));
            obj.setId(resultado.getInt("cliente_fisico.cliente_fk"));
            obj.setNome(resultado.getString("clientes.nome"));
            obj.setTelefone(resultado.getString("clientes.telefone"));
            
            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(FisicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean SalvarC(Fisico obj) {
        Cliente cliente = new Cliente();
        cliente.setEndereco(obj.getEndereco());
        cliente.setNome(obj.getNome());
        cliente.setTelefone(obj.getTelefone());
        cliente.setTipo(TipoCliente.FISICO);
        
        try {
            ClienteDAO c = new ClienteDAO();
            if(!c.Salvar(cliente))
                return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FisicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FisicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        if(Salvar(obj))
            return true;
        
       return false;
    }
    
}