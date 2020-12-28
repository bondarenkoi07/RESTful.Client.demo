package ru.mai.course.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mai.course.demo.Client.Board;
import ru.mai.course.demo.repo.BoardDAO;

import java.util.ArrayList;

@Service
public class BoardService {

    private final BoardDAO boardDAO;

    @Autowired
    public BoardService(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    public ArrayList<Board> read(){
        return (ArrayList<Board>) boardDAO.findAll();
    }

    public  Board readById(int id) throws Exception {
        return boardDAO.findById(id).orElseThrow(()-> new Exception("Board not found!"));
    }
}
