package ru.mai.course.demo.Client;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clients", schema = "public", catalog = "avia_ticekts")
public class Client {


    private Integer id;
    private String name;
    private String surname;
    private String series;
    private String num;
    private String phoneNumber;
    private Integer flight;
    private Integer seat;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "series")
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Basic
    @Column(name = "num")
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "flight")
    public Integer getFlight() {
            return flight;
    }

    public void setFlight(Integer flight) {
        try {
            this.flight = flight;
        }catch(Exception e){
            System.out.println("Error blyat");
        }
    }

    @Basic
    @Column(name = "seat")
    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", series='" + series + '\'' +
                ", num='" + num + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", flight=" + flight +
                ", seat=" + seat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client that = (Client) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(series, that.series) &&
                Objects.equals(num, that.num) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(flight, that.flight) &&
                Objects.equals(seat, that.seat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, series, num, phoneNumber, flight, seat);
    }

}
