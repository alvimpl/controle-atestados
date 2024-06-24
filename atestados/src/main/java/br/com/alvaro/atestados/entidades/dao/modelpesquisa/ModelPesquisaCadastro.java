/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.alvaro.atestados.entidades.dao.modelpesquisa;

import java.util.Date;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MÁRCIA  // INDENTAR
 */
@Getter
@Setter
public class ModelPesquisaCadastro extends ModelPesquisa {

    private long id;
    private String nome;
    private String cpf;
    private Date dtNasc;
    private String logradouro;
    private int numero;
    private String cep;
    @Column(length = 10)
    private String campo10;
    private String country;
    private String city;

}
