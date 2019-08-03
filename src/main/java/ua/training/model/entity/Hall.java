package ua.training.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="\"rows\"")
    private int rows;

    @Column(name="\"columns\"")
    private int columns;


}
