package hiber.model;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "cars")
//@Component
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private User user; //Bi-directional One-to-One

    public Car() {
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public User getUser() {
        return user;
    }

    public User setUser(User user) {
        this.user = user;
        return user;
    }

    @Override
    public String toString() {
        if (user == null) {
            return "Car{" +
                    "id=" + id +
                    ", model='" + model + '\'' +
                    ", series='" + series + '\'' +
                    ", user=null" +
                    '}';
        }
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", series='" + series + '\'' +
                ", user='" + user.getFirstName() + ' ' + user.getLastName() + '\'' +
                '}';
    }
}