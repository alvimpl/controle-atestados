/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.alvaro.atestados.entidades.exception;

/**
 *
 * @author indentar
 */
public class CadastroDuplicadoException extends Exception {

    private final Object cadDuplicado;

    public CadastroDuplicadoException(Object cadDuplicado, String string) {
        super(string);
        this.cadDuplicado = cadDuplicado;
    }

    public Object getCadDuplicado() {
        return cadDuplicado;
    }

}
