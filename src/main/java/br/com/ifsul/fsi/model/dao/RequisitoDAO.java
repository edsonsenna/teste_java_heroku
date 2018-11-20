package br.com.ifsul.fsi.model.dao;

import br.com.ifsul.fsi.model.entity.Requisito;
import br.com.ifsul.fsi.model.DAOImpl;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

public class RequisitoDAO extends DAOImpl<Requisito, Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    final static Logger logger = Logger.getLogger(RequisitoDAO.class);

    private static RequisitoDAO INSTANCE;

    private RequisitoDAO() {

    }

    public synchronized static RequisitoDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RequisitoDAO();
        }
        return INSTANCE;
    }
    
    public List<Requisito> getResquisitoPorData(Date dateRequisitoInicial, Date dateRequisitoFinal){
        EntityManager em = this.getEntityManager();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT R FROM RESQUISITO R")
                .append(" WHERE R.data")
                .append(" BETWEEN :dateRequisitoInicial AND :dateRequisitoFinal")
                .append("ORDER BY R.data ASC");
        
        TypedQuery<Requisito> q = em
                .createQuery(sb.toString(), Requisito.class);
        
        q.setParameter("dateRequisitoInicial", dateRequisitoInicial);
        q.setParameter("dateRequisitoFinal", dateRequisitoFinal);
        
        List<Requisito> list = q.getResultList();
        
        return list;
        
    }

}
