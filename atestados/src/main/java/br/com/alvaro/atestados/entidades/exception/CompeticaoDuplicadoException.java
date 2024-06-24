/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.alvaro.atestados.entidades.exception;

/**
 *
 * @author Anne  // INDENTAR
 */
public class CompeticaoDuplicadoException extends Exception {
    private final Object compDuplicado;
    
    public CompeticaoDuplicadoException(Object compDuplicado, String string){
        super(string);
        this.compDuplicado = compDuplicado;
    }
    public Object getCompDuplicado(){
        return compDuplicado;
    }
    
}
