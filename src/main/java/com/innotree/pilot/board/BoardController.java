package com.innotree.pilot.board;


import com.innotree.pilot.file.FileService;
import com.innotree.pilot.reply.Reply;
import com.innotree.pilot.security.PilotUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
            redirectAttributes.addFlashAttribute("message", "글 " + saveBoard.getId() + " 생성되었습니다.");
        }
        return "redirect:/board/page-board/1";
    }
//    @PutMapping("/board/save-board")
//    public String boardEditSave(Board board, @RequestParam("image") MultipartFile multipartFile, @AuthenticationPrincipal PilotUserDetails pilotUserDetails,RedirectAttributes redirectAttributes) throws Exception {
//        if (!multipartFile.isEmpty()) {
//            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//            System.out.println(multipartFile);
//            board.setPhotos(fileName);
//            Board editBoard = boardService.boardSave(board, pilotUserDetails);
//            String uploadDir = "board-photos/";
//            FileService.saveFile(uploadDir, fileName, multipartFile);
//            redirectAttributes.addFlashAttribute("message", "글번호 :" + editBoard.getId() + "가 수정되었습니다.");
//        }
//        return "redirect:/board/page-board/1";
//    }
    @DeleteMapping("/board/delete-board/{boardId}")
    public String boardDelete(@PathVariable(name = "boardId") Integer id) {
        boardService.deleteBoard(id);
        return "redirect:/board/page-board/1";
    }

    @GetMapping("/board/{boardId}")
    public String boardDetails(@PathVariable(name = "boardId") Integer id, Model model ,Reply reply) {
        Board board = boardService.getBoard(id);
        List<Reply> replyList = board.getReplyList();
        model.addAttribute("board", board);
        model.addAttribute("id", board.getId());
        model.addAttribute("reply", reply);
        model.addAttribute("replyList", replyList);
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
    public String boardPage(@PathVariable(name = "pageNumber") Integer pageNumber, Model model) {
        Page<Board> boardList = boardService.boardPage(pageNumber);
        List<Board> boardContents = boardList.getContent();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DAY_OF_MONTH,-7);
        Date nowDay = cal.getTime();
        model.addAttribute("nowDay", nowDay);
        model.addAttribute("boarderType", BoarderType.values());
        model.addAttribute("boardList", boardContents);
        model.addAttribute("totalPages", boardList.getTotalPages());
        model.addAttribute("pageNumber", pageNumber);
        return "board-page";
    }

    @GetMapping("/board/notice-board/{pageNumber}")
    public String noticePage(Model model,@PathVariable(name = "pageNumber") Integer pageNumber) {
        Pageable pageable = PageRequest.of(0, 4);
        Page<Board> pageBoard = boardRepository.findNOTICEBoard(pageable);
        List<Board> boardList = pageBoard.getContent();
        BoarderType NoticeBoarderType= BoarderType.NOTICE;
        model.addAttribute("NoticeBoarderType", NoticeBoarderType);
        model.addAttribute("boardList", boardList);
        model.addAttribute("totalPages", pageBoard.getTotalPages());
        model.addAttribute("pageNumber", pageNumber);
        return "board-page";
    }

    @GetMapping("/board/faq-board/{pageNumber}")
    public String faqPage(Model model,@PathVariable(name = "pageNumber") Integer pageNumber) {
        Pageable pageable = PageRequest.of(0, 4);
        Page<Board> pageBoard = boardRepository.findFAQBoard(pageable);
        List<Board> boardList = pageBoard.getContent();
        BoarderType FAQBoarderType= BoarderType.FAQ;
        model.addAttribute("FAQBoarderType", FAQBoarderType);
        model.addAttribute("boardList", boardList);
        model.addAttribute("totalPages", pageBoard.getTotalPages());
        model.addAttribute("pageNumber", pageNumber);
        return "board-page";
    }
    @GetMapping("/board/qna-board/{pageNumber}")
    public String qnaPage(Model model,@PathVariable(name = "pageNumber") Integer pageNumber) {
        Pageable pageable = PageRequest.of(0, 6);
        Page<Board> pageBoard = boardRepository.findQNABoard(pageable);
        List<Board> boardList = pageBoard.getContent();
        BoarderType QNABoarderType= BoarderType.QNA;
        model.addAttribute("QNABoarderType", QNABoarderType);
        model.addAttribute("boardList", boardList);
        model.addAttribute("totalPages", pageBoard.getTotalPages());
        model.addAttribute("pageNumber", pageNumber);
        return "board-page";
    }

}
