/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alvaro.atestados.util;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author paulohenrique
 */
public class GerenciadorDownload {

    public static void writeBytes(File fileDownload, String nomeArquivo, MimeType mimeType, boolean download) throws IOException {
        byte[] readFileToByteArray = FileUtils.readFileToByteArray(fileDownload);
        fileDownload.delete();
        writeBytes(readFileToByteArray, nomeArquivo, mimeType, download);
    }

    /**
     * Escreve os bytes de um arquivo no response.
     *
     * @param arquivoByte bytes pdf
     * @param nomeArquivo Nome de saída do arquivo.
     * @param mimeType
     * @param download
     * @throws java.io.IOException
     */
    public static void writeBytes(byte[] arquivoByte, String nomeArquivo, MimeType mimeType, boolean download) throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        // Obtem o HttpServletResponse, objeto responsável pela resposta do
        // servidor ao browser
        HttpServletResponse response = (HttpServletResponse) fc
                .getExternalContext().getResponse();
        // Limpa o buffer do response
        response.reset();
        // Seta o tipo de conteudo no cabecalho da resposta atravez do mimeType.
        response.setContentType(mimeType.mimeType);
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Connection", "Keep-Alive");
        //System.out.println("Set character encoding");
        // Seta o tamanho do conteudo no cabecalho da resposta
        response.setContentLength(arquivoByte.length);
        //Configura header.
        response.setHeader("Content-disposition", createValueHeader(nomeArquivo, mimeType, download));
        // Envia o conteudo do arquivo para o response
        response.getOutputStream().write(arquivoByte);
        // Descarrega o conteudo do stream, forçando a escrita de qualquer byte
        // ainda em buffer
        response.getOutputStream().flush();
        // Fecha o stream, liberando seus recursos
        response.getOutputStream().close();
        // Sinaliza ao JSF que a resposta HTTP para este pedido já foi gerada
        fc.responseComplete();

    }

    public void writeBase64String(String base64String, String nomeArquivo, MimeType mimeType, boolean download) throws IOException {
        byte[] arquivoByte = Base64.getDecoder().decode(base64String); // Converter de base64 para bytes
        writeBytes(arquivoByte, nomeArquivo, mimeType, download);
    }

    /**
     * Cria o value header atravez de um mimeType.
     *
     * @param mimeType
     * @param download
     * @return valueHeader criado attachment se for um download ou inline para
     * abrir no próprio navegador.
     */
    private static String createValueHeader(String nomeArquivo, MimeType mimeType, boolean download) {
        StringBuilder valueHeader = new StringBuilder();
        String initHeader = download ? "attachment; filename=" : "inline; filename=";
        valueHeader.append(initHeader);
        //caracteres inválidos como vírgula, acentos pode atrapalhar o download, então substituo tudo com sublinhado
        valueHeader.append(nomeArquivo.replaceAll("[^a-zA-Z0-9.-]", "_"));
        valueHeader.append(mimeType.extensao);
        return valueHeader.toString();
    }

    public static enum MimeType {

        PDF("Adobe Portable Document Format", "application/pdf", ".pdf"),
        XLS("Microsoft Excel", "application/vnd.ms-excel", ".xls"),
        XML("XML - Extensible Markup Language", "application/xml", ".xml"),
        RAR("RAR Archive", "application/x-rar-compressed", ".rar"),
        TXT("Text File", "text/plain", ".txt"),
        PFX("Arquivo pfx", "application/x-pkcs12", ".pfx"),
        CSV("Microsoft Excel", "application/vnd.ms-excel", ".csv"),
        DOC("Microsoft Word", "application/msword", ".doc"),
        DOCX("Microsoft Word", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".docx"),
        HTML("HyperText Markup Language", "text/html", ".html"),
        XHTML("XHTML", "application/xhtml+xml", ".xhtml");

        private String descricao;
        private String mimeType;
        private String extensao;

        private MimeType(String descricao, String mimeType, String extensao) {
            this.descricao = descricao;
            this.mimeType = mimeType;
            this.extensao = extensao;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public String getMimeType() {
            return mimeType;
        }

        public void setMimeType(String mimeType) {
            this.mimeType = mimeType;
        }

        public String getExtensao() {
            return extensao;
        }

        public void setExtensao(String extensao) {
            this.extensao = extensao;
        }

        /**
         *
         * @param mimeType mime type sem ponto ex:(txt)
         * @return
         */
        public static MimeType localiza(String mimeType) {
            switch (mimeType) {
                case "xls":
                case "xlsx":
                case "XLS":
                case "XLSX":
                    return MimeType.XLS;
                case "doc":
                case "DOC":
                    return MimeType.DOC;
                case "docx":
                case "DOCX":
                    return MimeType.DOCX;
                case "txt":
                case "TXT":
                    return MimeType.TXT;
                case "xml":
                case "XML":
                    return MimeType.XML;
                case "rar":
                case "RAR":
                    return MimeType.XML;
                case "pfx":
                case "PFX":
                    return MimeType.XML;
                case "csv":
                case "CSV":
                    return MimeType.XML;
                case "html":
                case "HTML":
                    return MimeType.HTML;
                case "xhtml":
                case "XHTML":
                    return MimeType.XHTML;
                default:
                    return MimeType.PDF;
            }
        }
    }
}
