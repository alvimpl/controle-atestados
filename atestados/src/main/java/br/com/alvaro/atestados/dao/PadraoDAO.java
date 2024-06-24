/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.alvaro.atestados.dao;
import br.com.alvaro.atestados.exception.ExceptionBancoDeDados;
import br.com.alvaro.atestados.exception.ExceptionUtil;
import br.com.alvaro.atestados.util.HibernateUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.NonNull;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 * @author paulo henrique / INDENTAR
 */
public class PadraoDAO {

    /**
     * atributos que são utilizados para armazenar informações sobre erros
     * ocorridos durante as operações com o banco de dados
     */
    private static String mensagemErro;
    private static int codigoErro;

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public int getCodigoErro() {
        return codigoErro;
    }

    public void salvar(Object bean) throws ExceptionBancoDeDados {
        Session sessao = getSession();
        sessao.beginTransaction();
        try {
            sessao.save(bean);
            sessao.getTransaction().commit();
        } catch (JDBCException e) {
            throw new ExceptionBancoDeDados(msgErroJdbc(e));
        } finally {
            try {
                if (sessao.isOpen()) {
                    sessao.close();
                }
            } catch (HibernateException e) {
                throw new ExceptionBancoDeDados("Erro ao fechar operação de salvar. \n" + e.getMessage());
            }
        }
    }

    public void salvarOuAtualizar(Object bean) throws ExceptionBancoDeDados {
        Session sessao = getSession();
        sessao.beginTransaction();
        try {
            sessao.saveOrUpdate(bean);
            sessao.getTransaction().commit();
        } catch (JDBCException e) {
            throw new ExceptionBancoDeDados(msgErroJdbc(e));
        } finally {

            try {
                if (sessao.isOpen()) {
                    sessao.close();
                }

            } catch (HibernateException e) {
                throw new ExceptionBancoDeDados("Erro ao fechar operação de salvar. \n" + e.getMessage());
            }
        }
    }

    public void excluir(Object bean) throws ExceptionBancoDeDados {
        Session sessao = getSession();
        sessao.beginTransaction();

        try {
            sessao.delete(bean);
            sessao.getTransaction().commit();

        } catch (JDBCException e) {
            throw new ExceptionBancoDeDados(msgErroJdbc(e));

        } catch (HibernateException e) {
            if (e instanceof StaleObjectStateException) {
                throw new ExceptionBancoDeDados("Registro já foi excluído por outro usuário/transação ", e);
            }
            throw new ExceptionBancoDeDados(e.getMessage(), e);
        } finally {

            try {
                if (sessao.isOpen()) {
                    sessao.close();
                }

            } catch (HibernateException e) {
                throw new ExceptionBancoDeDados("Erro ao fechar operação de excluir. \n" + e.getMessage());
            }
        }
    }

    public void atualizar(Object bean) throws ExceptionBancoDeDados {
        Session sessao = getSession();
        sessao.beginTransaction();

        try {
            if (bean != null) {
                sessao.update(bean);
            } else {
                throw new IllegalArgumentException("O objeto a ser atualizado não pode ser nulo.");
            }

            sessao.getTransaction().commit();

        } catch (JDBCException e) {
            throw new ExceptionBancoDeDados(msgErroJdbc(e));
        } finally {
            try {
                if (sessao.isOpen()) {
                    sessao.close();
                }
            } catch (HibernateException e) {
                throw new ExceptionBancoDeDados("Erro ao fechar operação de atualizar. \n" + e.getMessage());
            }
        }
    }

    public static List listarPorQuery(String hql) throws ExceptionBancoDeDados {
        Session sessao = getSession();
        sessao.beginTransaction();
        List listResult = new ArrayList<>();
        try {
            Query query = sessao.createQuery(hql);
            listResult = query.list();
            sessao.getTransaction().commit();

        } catch (JDBCException e) {
            throw new ExceptionBancoDeDados(msgErroJdbc(e));
        } finally {

            try {
                if (sessao.isOpen()) {
                    sessao.close();
                }

            } catch (HibernateException e) {
                throw new ExceptionBancoDeDados("Erro ao fechar operação de listar. \n" + e.getMessage());
            }
        }
        return listResult;
    }

    /**
     *
     * @param hql
     * @param parametros
     * @return
     * @throws ExceptionBancoDeDados
     */
    public List listarPorQueryParam(String hql, @NonNull HashMap<String, Object> parametros) throws ExceptionBancoDeDados {
        Session sessao = getSession();
        sessao.beginTransaction();
        List listResult = new ArrayList<>();
        try {
            Query query = sessao.createQuery(hql);

            parametros.forEach((k, v) -> {
                if (v instanceof List) {
                    query.setParameterList(k, (List) v);
                } else {
                    query.setParameter(k, v);
                }
            });
            listResult = query.list();
            sessao.getTransaction().commit();

        } catch (JDBCException e) {
            throw new ExceptionBancoDeDados(msgErroJdbc(e));
        } finally {

            try {
                if (sessao.isOpen()) {
                    sessao.close();
                }

            } catch (HibernateException e) {
                throw new ExceptionBancoDeDados("Erro ao fechar operação de atualizar. \n" + e.getMessage());
            }
        }
        return listResult;
    }

    /**
     *
     * @param hql
     * @param key parametro key
     * @param value parametro value
     * @return
     * @throws ExceptionUtil
     */
    public Object pesquisarPorQuery(String hql, String key, String value) throws ExceptionUtil {
        Session sessao = getSession();
        sessao.beginTransaction();
        Object retorno = null;
        try {
            Query query = sessao.createQuery(hql);
            query.setParameter(key, value);
            query.setMaxResults(1);
            retorno = query.uniqueResult();
            sessao.getTransaction().commit();

        } catch (JDBCException e) {
            throw new ExceptionUtil(msgErroJdbc(e));
        } finally {

            try {
                if (sessao.isOpen()) {
                    sessao.close();
                }

            } catch (HibernateException e) {
                throw new ExceptionUtil("Erro ao fechar operação de atualizar. \n" + e.getMessage());
            }
        }
        return retorno;
    }

    public Object salvarCadastro(Object entidade) throws ExceptionUtil {
        Session sessao = getSession();
        sessao.beginTransaction();

        try {
            sessao.save(entidade);
            sessao.getTransaction().commit();
            return entidade;

        } catch (Exception e) {
            Logger.getLogger(PadraoDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new ExceptionUtil("Erro ao salvar a entidade.", e);
        } finally {
            try {
                if (sessao.isOpen()) {
                    sessao.close();
                }
            } catch (HibernateException e) {
                throw new ExceptionUtil("Erro ao fechar operação de salvar. \n" + e.getMessage());
            }
        }
    }

    public Object pesquisarPorQueryParam(String hql, @NonNull HashMap<String, Object> parametros) throws ExceptionBancoDeDados {
        Session sessao = getSession();
        sessao.beginTransaction();
        Object retorno = null;
        try {
            Query query = sessao.createQuery(hql);

            parametros.forEach((k, v) -> {
                if (v instanceof List) {
                    query.setParameterList(k, (List) v);
                } else {
                    query.setParameter(k, v);
                }
            });
            query.setMaxResults(1);

            retorno = query.uniqueResult();
            sessao.getTransaction().commit();

        } catch (JDBCException e) {
            throw new ExceptionBancoDeDados(msgErroJdbc(e));
        } catch (Exception e) {
            throw new ExceptionBancoDeDados(e.getMessage());
        } finally {
            try {
                if (sessao.isOpen()) {
                    sessao.close();
                }

            } catch (HibernateException e) {
                throw new ExceptionBancoDeDados("Erro ao fechar operação de atualizar. \n" + e.getMessage());
            }
        }
        return retorno;
    }

    public void refresh(Object Objeto) throws ExceptionBancoDeDados {
        Session sessao = getSession();
        Transaction transaction = sessao.beginTransaction();

        try {
            sessao.refresh(Objeto);
            transaction.commit();

        } catch (JDBCException e) {
            throw new ExceptionBancoDeDados(msgErroJdbc(e));
        }
    }

    public Object carregar(Class<?> bean, long id) throws ExceptionBancoDeDados {
        Object retorno = null;
        Session sessao = getSession();
        Transaction transaction = sessao.beginTransaction();

        try {
            retorno = sessao.get(bean, id);
            transaction.commit();

        } catch (JDBCException e) {
            throw new ExceptionBancoDeDados(msgErroJdbc(e));
        }
        return retorno;
    }

    public List<?> listarTodosObjetos(Class<?> bean) {
        List<Object> retorno;
        Session sessao = getSession();
        sessao.beginTransaction();
        Criteria criterio = sessao.createCriteria(bean);
        retorno = criterio.list();
        sessao.getTransaction().commit();
        return retorno;
    }

    public List<?> listarTodosObjetos(Class<?> bean, String order) {
        List<Object> retorno;
        Session sessao = getSession();
        sessao.beginTransaction();
        Criteria criterio = sessao.createCriteria(bean);
        criterio.addOrder(Order.asc(order));
        retorno = criterio.list();
        sessao.getTransaction().commit();
        return retorno;
    }

    public static Session getSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    /**
     * Identifica erro do jdbc e retorna mensagem de erro
     *
     * @param e
     * @return mensagem de erro
     */
    protected static String msgErroJdbc(JDBCException e) {
        codigoErro = e.getErrorCode();
        mensagemErro = "Código Erro: " + codigoErro + "\n" + e.getMessage() + "\n" + e.getSQLException();
        switch (codigoErro) {
            case 1062:
                return "Erro ao salvar, verifique duplicidade de cadastro.\n" + mensagemErro;
            case 1451:
                return "Erro ao excluir, verifique se está sendo usado.\n" + mensagemErro;
            case 1452:
                return "Erro nas definições das tabelas.Ou não foi enviada alguma chave\n" + mensagemErro;
            default:
                return "Erro ao salvar.\n" + mensagemErro;
        }
    }

    public void fecharSessao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
