/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.estacionamento.persistencia;

import ihm.estacionamento.aplicacao.Cliente;
import ihm.estacionamento.aplicacao.Juridico;
import ihm.estacionamento.aplicacao.JuridicoRepositorio;
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
public class JuridicoDAO extends DAOGenerico<Juridico> implements JuridicoRepositorio {

    public JuridicoDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    protected String getConsultaInsert() {
        return "insert into cliente_juridico(cliente_fk, cnpj) values(?, ?)";
    }

    @Override
    protected String getConsultaUpdate() {
        return "update cliente_juridico set cliente_fk = ?, cnpj = ?";
    }

    @Override
    protected String getConsultaDelete() {
        return "delete from cliente_juridico where cliente_fk = ?";
    }

    @Override
    protected String getConsultaAbrir() {
        return "select clientes.id, clientes.nome, clientes.endereco, clientes.telefone,"
                + " cliente_juridico.cnpj from clientes join cliente_juridico on clientes.id = cliente_juridico.cliente_fk"
                + " where clientes.id = ?";
    }

    @Override
    protected String getConsultaBuscar() {
        return "select clientes.id, clientes.nome, clientes.endereco, clientes.telefone,"
                + " cliente_juridico.cnpj, cliente_juridico.cliente_fk from clientes join cliente_juridico on clientes.id = cliente_juridico.cliente_fk ";
    }

    @Override
    protected String getConsultaId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void setBuscaFiltros(Juridico filtro) {
        if (filtro.getCnpj() != null && !filtro.getCnpj().isEmpty()) {
            this.adicionaFiltro("cliente_juridico.cnpj", filtro.getCnpj());
        }

        if (filtro.getId() > 0) {
            this.adicionaFiltro("cliente_juridico.cliente_fk", filtro.getId());
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
    protected void setParametros(PreparedStatement sql, Juridico obj) {
        try {
            sql.setInt(1, obj.getId());
            sql.setString(2, obj.getCnpj());

        } catch (SQLException ex) {
            Logger.getLogger(JuridicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Juridico setDados(ResultSet resultado) {
        try {
            Juridico obj = new Juridico();
            obj.setCnpj(resultado.getString("cliente_Juridico.cnpj"));
            obj.setEndereco(resultado.getString("clientes.endereco"));
            obj.setId(resultado.getInt("clientes.id"));
            obj.setNome(resultado.getString("clientes.nome"));
            obj.setTelefone(resultado.getString("clientes.telefone"));

            return obj;
        } catch (SQLException ex) {
            Logger.getLogger(JuridicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean Salvar(Juridico obj) {
        Cliente cliente = new Cliente();
        cliente.setEndereco(obj.getEndereco());
        cliente.setNome(obj.getNome());
        cliente.setTelefone(obj.getTelefone());
        cliente.setTipo(TipoCliente.JURIDICO);
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
            Logger.getLogger(JuridicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JuridicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean Apagar(Juridico obj) {
        Cliente cliente = new Cliente();
        cliente.setEndereco(obj.getEndereco());
        cliente.setNome(obj.getNome());
        cliente.setTelefone(obj.getTelefone());
        cliente.setTipo(TipoCliente.JURIDICO);
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
            Logger.getLogger(JuridicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JuridicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;

    }
}
