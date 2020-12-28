package ru.mai.course.demo.Client;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Board {
    private int id;
    private int seatsNumber;
    private String destination;
    private String startPoint;
    private String boardIndex;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "seats_number", nullable = false)
    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    @Basic
    @Column(name = "destination", nullable = true, length = -1)
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Basic
    @Column(name = "start_point", nullable = true, length = -1)
    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    @Basic
    @Column(name = "board_index", nullable = false, length = 40)
    public String getBoardIndex() {
        return boardIndex;
    }

    public void setBoardIndex(String boardIndex) {
        this.boardIndex = boardIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return id == board.id && seatsNumber == board.seatsNumber && Objects.equals(destination, board.destination) && Objects.equals(startPoint, board.startPoint) && Objects.equals(boardIndex, board.boardIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seatsNumber, destination, startPoint, boardIndex);
    }
}
