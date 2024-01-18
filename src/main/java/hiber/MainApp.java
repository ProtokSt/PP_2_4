package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        System.out.println();
        System.out.println("Update data in database");
        userService.add(new User("User1", "Lastname1", "user1@mail.ru"), new Car("GAZ", 101));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"), new Car("UAZ", 102));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"), new Car("KAMAZ", 103));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"), new Car("ZIL", 104));
        userService.add(new User("User5", "NoCar", "user5@mail.ru"));
        userService.add(new Car("LoneCar", 333));
        userService.add(new User("User6", "SameCar", "user6@mail.ru"), new Car("ZIL", 104));

        System.out.println();
        System.out.println("Read data from database");
        List<User> users = userService.listUsers();
        System.out.println();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println();
        List<Car> cars = userService.listCars();
        System.out.println();
        for (Car car : cars) {
            System.out.println(car);
        }

        System.out.println();
        System.out.println("Search data with params");
        List<User> usersWithCar = userService.getUsersWithCar("ZIL", 104);
        System.out.println();
        if (usersWithCar.size() == 0) {
            System.out.println("Data with such params not found");
        } else {
            for (User user : usersWithCar) {
                System.out.println(user);
            }
        }
        System.out.println();

        context.close();
    }
}
