package com.Jong.Pilot.Controller;


import com.Jong.Pilot.Entity.Board;
import com.Jong.Pilot.Repository.BoardRepository;
import com.Jong.Pilot.UserService.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {



    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    public String viewBoard(Model model){

        List<Board> boardList = boardService.boardList();
        model.addAttribute("boardList",boardList);
        return "BoardPage";
    }

    @GetMapping("/boardCreate")
    public String boardCreate(Model model){
        Board board = new Board();
        model.addAttribute("board",board);
        return "CreateBoardPage";

    }

    @PostMapping("/board/save")
    public String boardSave(Board board){
        boardService.boardSave(board);
        return "redirect:/board";
    }
}
