/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.alvaro.atestados.exception;

/**
 *
 * @author Marlucio  // INDENTAR
 */
public class ExceptionBancoDeDados extends Exception{

    public ExceptionBancoDeDados(String string) {
        super(string);
    }

    public ExceptionBancoDeDados(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ExceptionBancoDeDados(Throwable thrwbl) {
        super(thrwbl);
    }

    public ExceptionBancoDeDados(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
