/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.alvaro.atestados.exception;

/**
 * @author Paulo Henrique  // INDENTAR
 */
public class ExceptionUtil extends Exception{
    public ExceptionUtil() {
        // default
    }

    public ExceptionUtil(String message) {
        super(message);
    }

   public ExceptionUtil(Throwable cause) {
        super(cause);
    }

    public ExceptionUtil(String message, Throwable cause) {
        super(message, cause);
    }
}
