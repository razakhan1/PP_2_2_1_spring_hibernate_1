package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }


   @Override
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User carsUser(String model, int series) {
      try(Session session = sessionFactory.openSession()) {
         String hql = "select u from User u where u.car.model = ?1 and u.car.series = ?2";
         Query<User> query = session.createQuery(hql, User.class);
         query.setParameter(1, model);
         query.setParameter(2, series);
         return query.uniqueResult();
      }
   }
}
