/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author User
 */
public class Automovil {
    private int codigo;
    private String marca;
    private String modelo;
    private Date fecha;
    private double costo;
    private String tipo;
    private String motor;
    private String AgenciaRFC;
    private int ProveedorCodigo;

    public Automovil() {
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getAgenciaRFC() {
        return AgenciaRFC;
    }

    public void setAgenciaRFC(String AgenciaRFC) {
        this.AgenciaRFC = AgenciaRFC;
    }

    public int getProveedorCodigo() {
        return ProveedorCodigo;
    }

    public void setProveedorCodigo(int ProveedorCodigo) {
        this.ProveedorCodigo = ProveedorCodigo;
    }

    @Override
    public String toString() {
        return "Automovil{" + "codigo=" + codigo + ", marca=" + marca + ", modelo=" + modelo + ", fecha=" + fecha + ", costo=" + costo + ", tipo=" + tipo + ", motor=" + motor + ", AgenciaRFC=" + AgenciaRFC + ", ProveedorCodigo=" + ProveedorCodigo + '}';
    }
    
    
    
}
