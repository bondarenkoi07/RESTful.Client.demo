package ru.mai.course.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mai.course.demo.Client.Board;

public interface BoardDAO extends JpaRepository<Board,Integer> {
}
