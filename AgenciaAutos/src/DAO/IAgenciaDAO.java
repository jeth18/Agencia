/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import model.Agencia;

/**
 *
 * @author JET
 */
public interface IAgenciaDAO {
    List<Agencia> getAllAgencias();
    void agregarAgencia(Agencia agencia);
    void eliminarAgencia(String RFC);
    void actualizarAgencia(Agencia agencia);
}
