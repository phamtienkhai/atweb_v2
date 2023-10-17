package kma.atweb.vn.project;

import com.mysql.cj.jdbc.Driver;
import kma.atweb.vn.project.entity.Account;
import kma.atweb.vn.project.entity.Order;
import kma.atweb.vn.project.model.AccountInfo;
import kma.atweb.vn.project.model.OrderInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.List;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws Exception {
//        Properties properties = new Properties();
//
//        // See: application.properties
//        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
//        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
//        properties.put("current_session_context_class", //
//                env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
//        properties.put("hibernate.dll-auto", env.getProperty("spring.jpa.hibernate.dll-auto"));
//        properties.put("generate-ddl", env.getProperty("spring.jpa.generate-ddl"));
//
//        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//
//        // Package contain entity classes
//        factoryBean.setPackagesToScan(new String[] { "" });
//        factoryBean.setDataSource(dataSource);
//        factoryBean.setHibernateProperties(properties);
//        factoryBean.afterPropertiesSet();
//        //
//        SessionFactory sf = factoryBean.getObject();

//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
//        dataSource.setUsername("sa");
//        dataSource.setPassword("sa");

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(Driver.class);
        dataSource.setUrl("jdbc:mysql://192.168.12.6:3306/data-web?useSSL=true");
        dataSource.setUsername("user_01");
        dataSource.setPassword("abc123#A");

        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(
                new String[] { "kma.atweb.vn.project.entity.Account", "kma.atweb.vn.project.entity.Order", "kma.atweb.vn.project.entity.OrderDetail", "kma.atweb.vn.project.entity.Product" });

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        sessionFactory.setHibernateProperties(hibernateProperties);
        sessionFactory.afterPropertiesSet();

        SessionFactory sf = sessionFactory.getObject();
        //sf.openSession();

        String sql = "Select new " + AccountInfo.class.getName()//
                + "(acc.userName, acc.email, acc.address, acc.fullName, "
                + " acc.age, acc.active, acc.encryptedPassword) " + " from "
                + Account.class.getName() + " acc "//
                + " where acc.User_Role = :user_role;";
        sql = "Select new " + OrderInfo.class.getName()//
                + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
                + " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone, ord.status) " + " from "
                + Order.class.getName() + " ord "//
                //+ " order by ord.orderNum desc";
                + " order by ord.orderNum desc";

//        Session session = sf.getCurrentSession();
        Session session = sf.openSession();
        System.out.println("session: " + session.isOpen());
        Query<OrderInfo> query = session.createQuery(sql);
        //query.setParameter("User_Role", "ROLE_EMPLOYEE");
        List<OrderInfo> accountInfos = query.getResultList();
        System.out.println(accountInfos.size());
    }
}
