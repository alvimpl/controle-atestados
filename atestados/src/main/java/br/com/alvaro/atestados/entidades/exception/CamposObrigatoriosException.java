/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.alvaro.atestados.entidades.exception;

import java.util.List;

/**
 *
 * @author indentar
 */
public class CamposObrigatoriosException extends Exception {

    private final List<String> camposObrigatorios;

    public CamposObrigatoriosException(List<String> camposObrigatorios, String string) {
        super(string);
        this.camposObrigatorios = camposObrigatorios;
    }

    public List<String> getCamposObrigatorios() {
        return camposObrigatorios;
    }

    public List<String> getCamposFaltantes() {
        return getCamposObrigatorios();
    }
}
