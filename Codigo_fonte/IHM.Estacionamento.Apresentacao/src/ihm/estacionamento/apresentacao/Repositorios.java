/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.estacionamento.apresentacao;

import ihm.estacionamento.aplicacao.ClienteRepositorio;
import ihm.estacionamento.aplicacao.Repositorio;
import ihm.estacionamento.aplicacao.UsuarioRepositorio;
import java.util.logging.Logger;
import ihm.estacionamento.persistencia.ClienteDAO;
import ihm.estacionamento.persistencia.UsuarioDAO;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author Rodrigo
 */
public class Repositorios {
    static ClienteRepositorio clienteDAO = null;
    
    public static ClienteRepositorio getClienteRepositorio(){
        if(clienteDAO == null)
            try{
                clienteDAO = new ClienteDAO();
            }catch(ClassNotFoundException ex){
                Logger.getLogger(Repositorios.class.getName()).log(Level.SEVERE, null, ex);
            }catch(SQLException ex){
                Logger.getLogger(Repositorios.class.getName()).log(Level.SEVERE, null, ex);
            }
        return clienteDAO;
    } 

    public static UsuarioRepositorio usuarioDAO = null;
    
    public static UsuarioRepositorio getUsuarioRepositorio(){
        if(usuarioDAO == null)
            try{
                usuarioDAO = new UsuarioDAO(); 
            }catch(ClassNotFoundException ex){
                Logger.getLogger(Repositorios.class.getName()).log(Level.SEVERE, null, ex); 
            }catch(SQLException ex){
                Logger.getLogger(Repositorios.class.getName()).log(Level.SEVERE, null, ex);
            }
        return usuarioDAO;
    }

    
}
