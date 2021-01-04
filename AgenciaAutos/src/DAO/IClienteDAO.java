/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import model.Cliente;

/**
 *
 * @author JET
 */
public interface IClienteDAO {
    List<Cliente> getAllClientes();
    void agregarCliente(Cliente cliente);
    void eliminarCliente(int codigo);
    void actualizarCliente(Cliente cliente);
    Cliente obtenerCliente(int codigo);
}
