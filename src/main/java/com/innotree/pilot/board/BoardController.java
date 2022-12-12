package com.innotree.pilot.board;


import com.innotree.pilot.file.FileService;
import com.innotree.pilot.security.PilotUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;


    @GetMapping("/board")
    public String viewBoard(Model model) {
        List<BoarderType> boarderTypes = new ArrayList<>();
        List<Board> boardList = boardService.boardList();
        model.addAttribute("boardList", boardList);
        return "board-page";

    }

    @GetMapping("/testPost")
    public String postTest(String id,String pwd, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("pwd", pwd);

        return "test";
    }

    @GetMapping("/board-create")
    public String boardCreate(Model model) {
        Board board = new Board();
        model.addAttribute("board", board);
        model.addAttribute("boarderType", BoarderType.values());
        return "create-board-page";
    }

    @PostMapping("/board/save-board")
    public String boardSave(Board board, @RequestParam("image") MultipartFile multipartFile, @AuthenticationPrincipal PilotUserDetails pilotUserDetails, RedirectAttributes redirectAttributes) throws Exception {
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            System.out.println(multipartFile);
            board.setPhotos(fileName);
            Board saveBoard = boardService.boardSave(board, pilotUserDetails);
            System.out.println(saveBoard.getBoarderType());
            String uploadDir = "board-photos/";
            FileService.saveFile(uploadDir, fileName, multipartFile);
            redirectAttributes.addFlashAttribute("message", "글번호 :" + saveBoard.getId() + "이 생성되었습니다.");
        }
        return "redirect:/board/page-board/1";
    }
    @PutMapping("/board/save-board")
    public String boardEditSave(Board board, @RequestParam("image") MultipartFile multipartFile, @AuthenticationPrincipal PilotUserDetails pilotUserDetails) throws Exception {
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            System.out.println(multipartFile);
            board.setPhotos(fileName);
            boardService.boardSave(board, pilotUserDetails);
            String uploadDir = "board-photos/";
            FileService.saveFile(uploadDir, fileName, multipartFile);
        }
        return "redirect:/board/page-board/1";
    }

    @DeleteMapping("/board/delete-board/{boardId}")
    public String boardDelete(@PathVariable(name = "boardId") Integer id) {
        boardService.deleteBoard(id);
        return "redirect:/board/page-board/1";
    }

    @GetMapping("/board/{boardId}")
    public String boardDetails(@PathVariable(name = "boardId") Integer id, Model model) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);
        model.addAttribute("id", board.getId());
        return "board-detail-page";
    }

    @GetMapping("/board/edit-board/{boardId}")
    public String boardEdit(@PathVariable(name = "boardId") Integer id, Model model) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);
        model.addAttribute("id", board.getId());
        model.addAttribute("boarderType", BoarderType.values());
        return "create-board-page";
    }
    @GetMapping("/board/page-board/{pageNumber}")
    public String boardPage(@PathVariable(name = "pageNumber") Integer pageNumber, Model model ,@Param(value = "search") String search) {

        Page<Board> boardList = boardService.boardPage(pageNumber);
        List<Board> boardContents = boardList.getContent();
        model.addAttribute("boarderType", BoarderType.values());
        model.addAttribute("boardList", boardContents);
        model.addAttribute("totalPages", boardList.getTotalPages());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("search", search);
        return "board-page";
    }

    @GetMapping("/board/")
    public String noticePage(Model model,@RequestParam(name = "boarderType") BoarderType boarderType) {
        List<Board> noticeBoard = boardRepository.findBoardListByBoarderType(BoarderType.Notice);
        model.addAttribute("noticeBoard", noticeBoard);
        return "board-page";
    }
    @GetMapping("/board/qna-board")
    public String qnaPage(Model model) {
        return "test";
    }
    @GetMapping("/board/faq-board")
    public String faqPage(Model model) {
        return "test";
    }

}
