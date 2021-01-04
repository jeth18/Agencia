/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import model.Proveedor;

/**
 *
 * @author JET
 */
public interface IProveedorDAO {
    List<Proveedor> getAllProveedores();
    void agregarProveedor(Proveedor proveedor);
    void eliminarProveedor(int codigo);
    void actualizarProveedor(Proveedor proveedor);
    Proveedor obtenerProveedor(int codigo);
}
