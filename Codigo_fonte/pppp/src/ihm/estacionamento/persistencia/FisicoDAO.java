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
public class FisicoDAO extends DAOGenerico<Fisico> implements FisicoRepositorio {

    public FisicoDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into cliente_fisico(cliente_fk, cpf) values(?, ?)";
    }

    @Override
    protected String getConsultaUpdate() {
        return "update cliente_fisico set cliente_fk = ?, cpf = ?";
    }

    @Override
    protected String getConsultaDelete() {
        return "delete from cliente_fisico where cliente_fk = ?";
    }

    @Override
    protected String getConsultaAbrir() {
        return "select clientes.id, clientes.nome, clientes.endereco, clientes.telefone,"
                + " cliente_fisico.cpf from clientes join cliente_fisico on clientes.id = cliente_fisico.cliente_fk"
                + " where clientes.id = ?";
    }

    @Override
    protected String getConsultaBuscar() {
        return "select clientes.id, clientes.nome, clientes.endereco, clientes.telefone,"
                + " cliente_fisico.cpf, cliente_fisico.cliente_fk from clientes join cliente_fisico on clientes.id = cliente_fisico.cliente_fk ";
    }

    @Override
    protected String getConsultaId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setBuscaFiltros(Fisico filtro) {
        if (filtro.getCpf() != null && !filtro.getCpf().isEmpty()) {
            this.adicionaFiltro("cliente_fisico.cpf", filtro.getCpf());
        }

        if (filtro.getId() > 0) {
            this.adicionaFiltro("cliente_fisico.cliente_fk", filtro.getId());
        }

        if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
            this.adicionaFiltro("clientes.nome", filtro.getNome());
        }

        if (filtro.getEndereco() != null && !filtro.getEndereco().isEmpty()) {
            this.adicionaFiltro("clientes.endereco", filtro.getEndereco());
        }

        if (filtro.getTipo() != null) {
            this.adicionaFiltro("clientes.tipo", filtro.getTipo().getId());
        }

        if (filtro.getTelefone() != null && filtro.getTelefone().isEmpty()) {
            this.adicionaFiltro("clientes.telefone", filtro.getTelefone());
        }
    }

    @Override
    protected void setParametros(PreparedStatement sql, Fisico obj) {
        try {
            sql.setInt(1, obj.getId());
            sql.setString(2, obj.getCpf());

        } catch (SQLException ex) {
            Logger.getLogger(FisicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Fisico setDados(ResultSet resultado) {
        try {
            Fisico obj = new Fisico();
            obj.setCpf(resultado.getString("cliente_fisico.cpf"));
            obj.setEndereco(resultado.getString("clientes.endereco"));
            obj.setId(resultado.getInt("clientes.id"));
            obj.setNome(resultado.getString("clientes.nome"));
            obj.setTelefone(resultado.getString("clientes.telefone"));

            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(FisicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean Salvar(Fisico obj) {
        Cliente cliente = new Cliente();
        cliente.setEndereco(obj.getEndereco());
        cliente.setNome(obj.getNome());
        cliente.setTelefone(obj.getTelefone());
        cliente.setTipo(TipoCliente.FISICO);
        cliente.setId(obj.getId());

        try {
            ClienteDAO c = new ClienteDAO();
            if (!c.Salvar(cliente)) {
                return false;
            }

            try {
                PreparedStatement sql = null;

                if (obj.getId() == 0) {
                    obj.setId(c.BuscarUltimoId());
                    sql = conexao.prepareStatement(getConsultaInsert());
                } else {
                    sql = conexao.prepareStatement(getConsultaUpdate());
                }

                setParametros(sql, obj);

                if (sql.executeUpdate() > 0) {
                    return true;
                } else {
                    return false;
                }

            } catch (Exception ex) {
                return false;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FisicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FisicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean Apagar(Fisico obj) {
        Cliente cliente = new Cliente();
        cliente.setEndereco(obj.getEndereco());
        cliente.setNome(obj.getNome());
        cliente.setTelefone(obj.getTelefone());
        cliente.setTipo(TipoCliente.FISICO);
        cliente.setId(obj.getId());

        try {
            try {
                PreparedStatement sql = conexao.prepareStatement(getConsultaDelete());

                sql.setInt(1, obj.getId());

                if (sql.executeUpdate() <= 0) {
                    return false;
                }

            } catch (Exception ex) {
                return false;
            }
            
            ClienteDAO c = new ClienteDAO();
            if (c.Apagar(cliente)) {
                obj = null;
                return true;
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FisicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FisicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;

    }
}
