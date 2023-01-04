package com.innotree.pilot.board;


import com.innotree.pilot.file.FileService;
import com.innotree.pilot.reply.Reply;
import com.innotree.pilot.security.PilotUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.expression.Dates;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public String postTest(String id, String pwd, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("pwd", pwd);
        return "test";
    }

    @GetMapping("/create-board")
    public String boardCreate(Model model) {
        Board board = new Board();
        model.addAttribute("board", board);
        model.addAttribute("boarderType", BoarderType.values());
        return "create-board-page";
    }

    @PostMapping("/board/save-board")
    public String boardSave(Model model, Board board, @RequestParam("image") MultipartFile multipartFile, @AuthenticationPrincipal PilotUserDetails pilotUserDetails, RedirectAttributes redirectAttributes) throws Exception {
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            System.out.println(multipartFile);
            System.out.println(fileName);
            Date now = Calendar.getInstance().getTime();
            board.setCreationTime(now);
            board.setPhotos(fileName);
            Board saveBoard = boardService.boardSave(board, pilotUserDetails);
            System.out.println(saveBoard.getBoarderType());
            String uploadDir = "board-photos/";
            FileService.saveFile(uploadDir, fileName, multipartFile);
            model.addAttribute(BoarderType.values());
            redirectAttributes.addFlashAttribute("message", "글 " + saveBoard.getId() + " 생성되었습니다.");
        }
        if(multipartFile.isEmpty()) {
            if (board.getPhotos() == null) {
                System.out.println("파일이 없는 상태");
                Date now = Calendar.getInstance().getTime();
                board.setCreationTime(now);
                Board saveBoard = boardService.boardSave(board, pilotUserDetails);
                System.out.println(saveBoard.getBoarderType());
            } else {
                System.out.println("파일이 존재 하는 상태");
                Date now = Calendar.getInstance().getTime();
                board.setPhotos(board.getPhotos());
                board.setCreationTime(now);
                Board saveBoard = boardService.boardSave(board, pilotUserDetails);
            }
        }
        return "redirect:/board/page-board/1" + "?value=id" + "&direction=descending";
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
//    @DeleteMapping("/board/delete-board/{boardId}")
//    public String boardDelete(@PathVariable(name = "boardId") Integer id, RedirectAttributes redirectAttributes) {
//        boardService.deleteBoard(id);
//        redirectAttributes.addFlashAttribute("글" + id + "이 삭제되었습니다.");
//        return "redirect:/board/page-board/1";
//    }

    @GetMapping("/board/delete-board/{boardId}")
    public String boardDelete(@PathVariable(name = "boardId") Integer id, RedirectAttributes redirectAttributes) {
        boardService.deleteBoard(id);
        redirectAttributes.addFlashAttribute("글" + id + "이 삭제되었습니다.");
        return "redirect:/board/page-board/1?value=id&direction=descending";
    }


    @GetMapping("/board/{boardId}")
    public String boardDetails(@PathVariable(name = "boardId") Integer id, Model model, Reply reply) {
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

    @GetMapping("/board/search-board/{pageNumber}")
    public String boardPage(@PathVariable(name = "pageNumber") Integer pageNumber, Model model, @RequestParam("word") String word,@RequestParam("value") String value , @RequestParam("direction") String direction) {
        System.out.println(word);
        System.out.println(value);
        System.out.println(direction);
        Page<Board> boardList = boardService.boardPage(pageNumber, word , value, direction);
        List<Board> boardContents = boardList.getContent();
        String boardValue = "id";
        String boardDirection = "descending";
        model.addAttribute("BoarderType", BoarderType.values());
        model.addAttribute("boardList", boardContents);
        model.addAttribute("totalPages", boardList.getTotalPages());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("word", word);
        model.addAttribute("direction", boardDirection);
        model.addAttribute("value", boardValue);
        return "board-page";
    }

    @GetMapping("/board/page-board/{pageNumber}")
    public String noneWordBoardPage(@PathVariable(name = "pageNumber") Integer pageNumber, Model model ,@Param("value") String value, @Param("direction") String direction) {
        Page<Board> boardList = boardService.noneWordBoardPageSort(pageNumber,value,direction);
        List<Board> boardContents = boardList.getContent();
        String boardValue = "id";
        String boardDirection = "descending";
        model.addAttribute("BoarderType", BoarderType.values());
        model.addAttribute("boardList", boardContents);
        model.addAttribute("totalPages", boardList.getTotalPages());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("direction", boardDirection);
        model.addAttribute("value", boardValue);
        return "board-page";
    }


//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DAY_OF_MONTH,-7);
//        Date nowDay = cal.getTime();
//        model.addAttribute("nowDay", nowDay);

    @GetMapping("/board/page-board/{pageNumber}/{BoarderType}")
    public String getBoarderType(@PathVariable(name = "pageNumber") Integer pageNumber, @PathVariable(name = "BoarderType") BoarderType boarderType, Model model , @Param("value") String value, @Param("direction") String direction) {
        Page<Board> boarderTypePage = boardService.boarderTypePage(pageNumber, boarderType,value,direction);
        List<Board> boardList = boarderTypePage.getContent();
        String boardValue = "id";
        String boardDirection = "descending";
        model.addAttribute("boardList", boardList);
        model.addAttribute("BoarderType", boarderType.values());
        model.addAttribute("selectedBoarderType", boarderType.getName());
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", boarderTypePage.getTotalPages());
        model.addAttribute("direction", boardDirection);
        model.addAttribute("value", boardValue);

        return "board-page-boarderType";

//        String result = listBoarderType.stream().map(String::valueOf).collect(Collectors.joining());
//        String result2 = org.thymeleaf.util.StringUtils.join(listBoarderType, ",");
//        return result2;

    }


//
//    @GetMapping("/board/notice-board/{pageNumber}")
//    public String noticePage(Model model,@PathVariable(name = "pageNumber") Integer pageNumber) {
//        Page<Board> noticePage = boardService.noticePage(pageNumber);
//        List<Board> boardList = noticePage.getContent();
//        BoarderType NoticeBoarderType= BoarderType.NOTICE;
//        model.addAttribute("NoticeBoarderType", NoticeBoarderType);
//        model.addAttribute("boardList", boardList);
//        model.addAttribute("totalPages", noticePage.getTotalPages());
//        model.addAttribute("pageNumber", pageNumber);
//        return "board-page";
//    }
//
//    @GetMapping("/board/faq-board/{pageNumber}")
//    public String faqPage(Model model,@PathVariable(name = "pageNumber") Integer pageNumber) {
//        Page<Board> faqPage = boardService.faqPage(pageNumber);
//        List<Board> boardList = faqPage.getContent();
//        BoarderType FAQBoarderType= BoarderType.FAQ;
//        model.addAttribute("FAQBoarderType", FAQBoarderType);
//        model.addAttribute("boardList", boardList);
//        model.addAttribute("totalPages", faqPage.getTotalPages());
//        model.addAttribute("pageNumber", pageNumber);
//        return "board-page";
//    }
//    @GetMapping("/board/qna-board/{pageNumber}")
//    public String qnaPage(Model model,@PathVariable(name = "pageNumber") Integer pageNumber) {
//        Page<Board> faqPage = boardService.qnaPage(pageNumber);
//        List<Board> boardList = faqPage.getContent();
//        BoarderType QNABoarderType= BoarderType.QNA;
//        model.addAttribute("QNABoarderType", QNABoarderType);
//        model.addAttribute("boardList", boardList);
//        model.addAttribute("totalPages", faqPage.getTotalPages());
//        model.addAttribute("pageNumber", pageNumber);
//        return "board-page";
//    }

}
