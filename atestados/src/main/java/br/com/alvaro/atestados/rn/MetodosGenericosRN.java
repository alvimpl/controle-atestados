/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.alvaro.atestados.rn;

import br.com.alvaro.atestados.entidades.exception.CamposObrigatoriosException;

/**
 *
 * @author marlúcio  // INDENTAR
 */
public interface MetodosGenericosRN {

    public void salvar(Object objTransient) throws Exception;

    public void alterar(Object objPersist) throws Exception;

    public void excluir(Object objPersist) throws Exception;

    public void validar(Object object) throws CamposObrigatoriosException;

    public void checkDuplicado(Object object) throws Exception;

}
