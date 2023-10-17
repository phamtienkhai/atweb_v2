package kma.atweb.vn.project.dao;

import kma.atweb.vn.project.entity.Order;
import kma.atweb.vn.project.model.AccountInfo;
import kma.atweb.vn.project.model.OrderInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import kma.atweb.vn.project.entity.Account;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class AccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Account findAccount(String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(Account.class, userName);
    }

    public boolean saveAccount(Account account) {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            session.saveOrUpdate(account);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public List<AccountInfo> findAccounts(String role) {
        String sql = "Select new " + AccountInfo.class.getName()//
                + "(acc.userName, acc.email, acc.address, acc.fullName, "
                + " acc.age, acc.active, acc.encryptedPassword, acc.userRole) " + " from "
                + Account.class.getName() + " acc "//
                + " where acc.role = :role ";

        Session session = this.sessionFactory.getCurrentSession();
        Query<AccountInfo> query = session.createQuery(sql, AccountInfo.class);
        query.setParameter("role", role);
        return query.getResultList();
    }
}
