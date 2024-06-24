package br.com.alvaro.atestados.util;

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package br.indentar.com.projetoestudo.util;
//
//import com.sun.xml.internal.ws.util.UtilException;
//import java.sql.Connection;
//import java.util.HashMap;
//import javax.faces.context.FacesContext;
//import org.primefaces.model.StreamedContent;
//
//
//
//
///**
// *
// * @author user
// */
//public class RelatorioUtil {
//public static final int RELATORIO_PDF = 1;
//public static final int RELATORIO_EXCEL = 2;
//public static final int RELATORIO_HTML = 3;
//public static final int RELATORIO_PLANILHA_OPEN_OFFICE = 4;
//
//public StreamedContent geraRelatorio(HashMap parametrosRelatorio, String nomeRelatorioioJasper,
//        String nomeRelatorioSaida, int TipoRelatorio) throws UtilException {
//        StreamedContent arquivoRetorno = null;
//        try {
//            FacesContext context = FacesContext.getCurrentInstance();
//            Connection conexao = this.getConexao();
//            String caminhoRelatorio = context.getExeternalContext().getRealPath("relatorios");
//            String caminhoArquivoJasper = caminhoRelatorio + file.separator + nomeRelatorioJasper + ".jasper";
//            String caminhoArquivoRelatorio = null;
//            JasperReport impressoraJasper = JasperFillManager.fillReport(relatorioJasper)
//            
//        }
//        return this;
//}
//
//}
