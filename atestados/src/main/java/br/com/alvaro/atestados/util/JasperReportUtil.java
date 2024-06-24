/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.alvaro.atestados.util;

import br.com.alvaro.atestados.exception.ExceptionBancoDeDados;
import br.com.alvaro.atestados.exception.ReportException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;

/**
 *
 * @author Marlucio
 */
public class JasperReportUtil {

    public JasperPrint gerarJasperPrintByConnection(HashMap parametrosRelatorio, File fileJasper) throws ReportException, ExceptionBancoDeDados {

        JasperReport report;
        JasperPrint print;
        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessao();

            Connection connection = getConnection();
            report = (JasperReport) JRLoader.loadObject(fileJasper);

            print = JasperFillManager.fillReport(report, parametrosRelatorio, connection);
            //print = JasperFillManager.fillReport();
            sessao.getTransaction().commit();
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException | JRException ex) {
            throw new ReportException("Erro ao gerar relatório.", ex);
        } finally {
            if (sessao != null) {
                trataSessao(sessao);
            }
        }
        return print;

    }

    public JasperPrint gerarJasperPrintByList(HashMap parametrosRelatorio, File fileJasper, List<Object> dataList) throws ReportException, ExceptionBancoDeDados {

        JasperReport report;
        JasperPrint print;

        try {

            report = (JasperReport) JRLoader.loadObject(fileJasper);
            JRDataSource dataSource = new JRBeanCollectionDataSource(dataList);

            print = JasperFillManager.fillReport(report, parametrosRelatorio, dataSource);
            //print = JasperFillManager.fillReport();

        } catch (JRException ex) {
            throw new ReportException("Erro ao gerar relatório.", ex);
        } catch (Exception ex) {
            throw new ReportException("Erro ao gerar relatório.", ex);
        }
        return print;

    }

    private Connection getConnection() throws ExceptionBancoDeDados {
        Connection connection = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            //Recupera a conexão com o banco de dados  
            SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor) session.getSessionFactory();
            ConnectionProvider connectionProvider = sessionFactoryImplementation.getConnectionProvider();

            connection = connectionProvider.getConnection();
            // connection = session.getSessionFactory;
        } catch (HibernateException | SQLException e) {

            throw new ExceptionBancoDeDados("Erro ao obter conexão com banco de dados para geração do relatório.", e);
        }
        return connection;
    }

    private List<Connection> getConnectionList(List<Connection> connectionList) {
        return connectionList;
    }

    public static void trataSessao(Session sessao) throws ExceptionBancoDeDados {
        try {
            if (sessao.isOpen()) {
                sessao.close();
            }
        } catch (HibernateException e) {
            throw new ExceptionBancoDeDados("Erro ao fechar sessão com o banco de dados.", e);
        }
    }

    public byte[] toByteArray(List<JasperPrint> jasperPrintList) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
        //isso fará com que um marcador no PDF exportado para cada um dos relatórios
        exporter.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS, Boolean.TRUE);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
        try {
            exporter.exportReport();
        } catch (JRException ex) {
            throw new ReportException("Erro na conversão do relatório em bytes.", ex);
        }
        return baos.toByteArray();
    }

    private byte[] exportPdf(JasperPrint jasperPrint) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
        //  exporter.setParameter(JRExporterParameter.PROPERTY_CHARACTER_ENCODING,"UTF-8");
        System.out.println("set JRExporterParameter.CHARACTER_ENCODING, \"UTF-8\" **********************************************************");
        try {
            exporter.exportReport();
        } catch (JRException ex) {
            throw new ReportException("Erro na conversão do relatório em bytes.", ex);
        }
        return baos.toByteArray();
    }

    public byte[] toByteArray(JasperPrint jasperPrint) {
        try {
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException ex) {
            throw new ReportException("Erro na conversão do relatório em bytes.", ex);
        }
    }

    public void jasperViewer(JasperPrint jasperPrint) {
        JasperViewer.viewReport(jasperPrint);
    }
}
