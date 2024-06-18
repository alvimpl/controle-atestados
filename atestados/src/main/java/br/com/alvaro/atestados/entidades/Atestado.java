/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.alvaro.atestados.entidades;
import java.util.Date;

/**
 *
 * @author Alvaro
 */
public class Atestado {
    Pessoa funcionario;
    Pessoa medicoAtestado;
    int crmMedico;
    Cid cid;
    Base base;
    Date dataAtestado;
    Date dataRetorno;
    int diasAfastamento; // ver se será útil
    boolean cat;
    String tipoCat; // ver cadastros pre definidos
}
