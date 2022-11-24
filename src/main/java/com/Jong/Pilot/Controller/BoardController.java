package com.Jong.Pilot.Controller;


import com.Jong.Pilot.Entity.Board;
import com.Jong.Pilot.Entity.User;
import com.Jong.Pilot.Repository.BoardRepository;
import com.Jong.Pilot.Security.PilotUserDetails;
import com.Jong.Pilot.UserService.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String boardSave(Board board, @AuthenticationPrincipal PilotUserDetails pilotUserDetails){
        boardService.boardSave(board,pilotUserDetails);
        return "redirect:/board";
    }

    @GetMapping("/board/delete/{boardId}")
    public String boardDelete(@PathVariable(name = "boardId") Integer id){
        boardService.deleteBoard(id);
        return "redirect:/board";
    }

    @GetMapping("/board/{boardId}")
    public String boardDetails(@PathVariable(name = "boardId") Integer id,Model model){
        Board board = boardService.getBoard(id);
        model.addAttribute("board",board);
        return "BoardDetailPage";
    }

    @GetMapping("/board/edit/{boardId}")
    public String boardEdit(@PathVariable(name = "boardId") Integer id,Model model){
        Board board = boardService.getBoard(id);
        model.addAttribute("board",board);
        return "CreateBoardPage";
    }

}
