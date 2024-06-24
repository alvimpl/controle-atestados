/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alvaro.atestados.util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Framework hibernate permite mapear objetos java para banco de dados e fazer
 * operaçoes CRUD (create, read, update, delete) usando queries tipo sql
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static String url;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            /**
             * configura arquivo hubernate.cfg.xml
             */
            System.out.println("Carregando banco de dados com hibernate xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Criado servico de registro");
            String property = configuration.getProperties().getProperty("hibernate.connection.url");
            url = property;
            System.out.println("Propriedade url conection: " + property);
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            /**
             * Cria o serviço de registro e aplica as configuraçoes
             * sessionFactory recebe a configuraçao inicial do registro de
             * servico
             */
//            String property = configuration.getProperties().getProperty("hibernate.connection.url");
//           System.out.println("Propriedade url conection: " + property);
            System.out.println("Sessão construída.");

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
            /**
             * cria um erro se o codigo acima nao funcionar e cria uma exeçao
             * falha de iniciaçizaçao
             */
        }
    }

    public static SessionFactory getSessionFactory() {
        // if(sessionFactory.isClosed()){
        // sessionFactory.openSession();}
        return sessionFactory;
    }

    /**
     * retorna a classe acima
     *
     * @return
     */
    public static Session getSessao() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public static String getUrl() {
        return url;
    }
    
    
    /**
     * retorna a sessao atual
     */
}
