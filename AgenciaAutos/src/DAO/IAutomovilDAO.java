/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import model.Automovil;

/**
 *
 * @author JET
 */
public interface IAutomovilDAO {
    List<Automovil> getAllAutomoviles();
    void agregarAutomovil(Automovil auto);
    void eliminarAutomovil(int codigo);
    void actualizarAutomovil(Automovil auto);
    Automovil obtenerAutomovil(int codigo);
}
