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

        System.out.println();
        System.out.println("Update data in database");

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        User user5 = new User("User5", "NoCar", "user5@mail.ru");
        User user6 = new User("User6", "Lastname6", "user6@mail.ru");

        Car car1 = new Car("GAZ", 101);
        Car car2 = new Car("UAZ", 102);
        Car car3 = new Car("KAMAZ", 103);
        Car car4 = new Car("ZIL", 104);
        Car car5 = new Car("LoneCar", 105);
        Car car6 = new Car("IJ", 106);

        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2.setCar(car2).setUser(user2));
        userService.add(user3.setCar(car3).setUser(user3));
        userService.add(user4.setCar(car4).setUser(user4));
        userService.add(user5);
        userService.add(car5);

        // Dependency via service
        userService.add(user6, car6);


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
        List<User> usersWithCar = userService.getUserByModelAndSeries("ZIL", 104);
        System.out.println();
        if (usersWithCar.isEmpty()) {
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
