package com.Jong.pilot.controller;


import com.Jong.pilot.filecontroller.FileService;
import com.Jong.pilot.entity.Board;
import com.Jong.pilot.repository.BoardRepository;
import com.Jong.pilot.security.PilotUserDetails;
import com.Jong.pilot.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    public String viewBoard(Model model) {

        List<Board> boardList = boardService.boardList();
        model.addAttribute("boardList", boardList);
        return "BoardPage";

    }

    @GetMapping("/boardCreate")
    public String boardCreate(Model model) {
        Board board = new Board();
        model.addAttribute("board", board);
        return "CreateBoardPage";

    }

    @PostMapping("/board/save-board")
    public String boardSave(Board board, @RequestParam("image") MultipartFile multipartFile, @AuthenticationPrincipal PilotUserDetails pilotUserDetails) throws Exception {
        if (!multipartFile.isEmpty()) {

            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            System.out.println(multipartFile);
            board.setPhotos(fileName);
            Board boardSave = boardService.boardSave(board, pilotUserDetails);
            String uploadDir = "board-photos/" + boardSave.getId();
            FileService.saveFile(uploadDir, fileName, multipartFile);
        }
        return "redirect:/board/page-board/1";
    }


    @GetMapping("/board/delete-board/{boardId}")
    public String boardDelete(@PathVariable(name = "boardId") Integer id) {
        boardService.deleteBoard(id);
        return "redirect:/board/page-board/1";
    }

    @GetMapping("/board/{boardId}")
    public String boardDetails(@PathVariable(name = "boardId") Integer id, Model model) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);
        model.addAttribute("id", board.getId());
        return "BoardDetailPage";
    }

    @GetMapping("/board/edit-board/{boardId}")
    public String boardEdit(@PathVariable(name = "boardId") Integer id, Model model) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);
        model.addAttribute("id", board.getId());
        return "CreateBoardPage";
    }
    @GetMapping("/board/page-board/{pageNumber}")
    public String boardPage(@PathVariable(name = "pageNumber") Integer pageNumber, Model model) {

        Page<Board> boardList = boardService.boardPage(pageNumber);
        List<Board> boardContents = boardList.getContent();
        model.addAttribute("boardList", boardContents);
        model.addAttribute("totalPages", boardList.getTotalPages());
        model.addAttribute("pageNumber", pageNumber);
        return "BoardPage";
    }

}
