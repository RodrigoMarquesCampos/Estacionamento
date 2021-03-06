/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.estacionamento.aplicacao;

import java.util.List;

/**
 *
 * @author Rodrigo
 * @param <T>
 */
public interface Repositorio <T extends Entidade>{
    
    public boolean Apagar(T obj);
    
    public boolean Salvar(T obj);
    
    public T Abrir(int id);
    
    public List<T> Buscar(T filtro);
}
