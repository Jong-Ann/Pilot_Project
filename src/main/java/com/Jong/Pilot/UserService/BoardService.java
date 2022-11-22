package com.Jong.Pilot.UserService;

import com.Jong.Pilot.Entity.Board;
import com.Jong.Pilot.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> boardList(){
        return (List<Board>) boardRepository.findAll();
    }

    public void boardSave(Board board){
        boardRepository.save(board);
    }
}
