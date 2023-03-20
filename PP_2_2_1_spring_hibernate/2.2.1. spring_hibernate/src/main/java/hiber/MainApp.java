package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      Car car1 = new Car("model1", 11);
      Car car2 = new Car("model2", 22);
      Car car3 = new Car("model3", 33);
      User user5 = new User("User5", "Lastname5", "user5@mail.ru");
      User user6 = new User("User6", "Lastname6", "user6@mail.ru");
      User user7 = new User("User7", "Lastname7", "user7@mail.ru");
      user5.setCar(car1);
      user6.setCar(car2);
      user7.setCar(car3);
      car1.setUser(user5);
      car2.setUser(user6);
      car3.setUser(user7);
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);
      userService.add(user5);
      userService.add(user6);
      userService.add(user7);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car ="+user.getCar());
         System.out.println();
      }

      System.out.println(userService.carsUser("model1", 11).getFirstName());
      context.close();
   }
}
