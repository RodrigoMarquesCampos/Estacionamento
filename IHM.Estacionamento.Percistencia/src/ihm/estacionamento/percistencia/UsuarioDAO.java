/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.estacionamento.percistencia;

import ihm.estacionamento.aplicacao.TipoUsuario;
import ihm.estacionamento.aplicacao.Usuario;
import ihm.estacionamento.aplicacao.UsuarioRepositorio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo
 */
public class UsuarioDAO extends DAOGenerico<Usuario> implements UsuarioRepositorio{
    
     public UsuarioDAO() throws ClassNotFoundException, SQLException {
        super();
    }
    

    @Override
    protected String getConsultaInsert() {
        return "insert into Usuarios(nome, login, senha, nivel) values ('?', '?', '?', '?');"; // nome, login, senha, tipo
    }

    @Override
    protected String getConsultaUpdate() {
        return " update Usuarios set login = '?', senha = '?', nome = '?', nivel = ? where id = ?;"; //login, senha, nome, detipo, id
    }

    @Override
    protected String getConsultaDelete() {
        return "delete from Usuarios where id = ?;";
    }

    @Override
    protected String getConsultaAbrir() {
        return "select id, nome, login, senha, nivel from Usuarios where id = ?";
    }

    @Override
    protected String getConsultaBuscar() {
        return "select id, nome, login, senha, nivel from Usuarios";
    }

    @Override
    protected void setBuscaFiltros(Usuario filtro) {
        if(filtro.getId() > 0)
           this.adicionaFiltro("id", filtro.getId());
        
        if(filtro.getNome() != null && !filtro.getNome().isEmpty()){
            this.adicionaFiltro("nome", filtro.getNome());
        }
        
        if(filtro.getLogin() != null && !filtro.getLogin().isEmpty()){
            this.adicionaFiltro("login", filtro.getLogin());
        }
        
        if(filtro.getSenha() != null && !filtro.getSenha().isEmpty()){
            this.adicionaFiltro("senha", filtro.getSenha());
        }
        
        if(filtro.getTipo() != null)
            this.adicionaFiltro("tipo", filtro.getTipo().getId());
    }

    @Override
    protected void setParametros(PreparedStatement sql, Usuario obj) {
        
        try{
            sql.setString(1, obj.getNome());
            String x = obj.getNome();
            sql.setString(2, obj.getLogin());
            sql.setString(3, obj.getSenha());
            sql.setInt(4, obj.getTipo().getId());
            
            if(obj.getId() > 0){
                sql.setInt(5, obj.getId());
            }
        }catch(SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Usuario setDados(ResultSet resultado) {
        try{
            Usuario obj = new Usuario();
            obj.setNomee(resultado.getString("nome"));
            obj.setLogin(resultado.getString("login"));
            obj.setSenha(resultado.getString("senha"));
            obj.setId(resultado.getInt("id"));
            obj.setTipo(TipoUsuario.Abrir(resultado.getInt("nivel")));
            
            return obj;
        }catch(Exception ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    protected String getConsultaId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
