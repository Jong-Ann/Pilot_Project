package com.innotree.pilot.board;


import com.innotree.pilot.Response.Message;
import com.innotree.pilot.Response.StatusEnum;
import com.innotree.pilot.file.FileService;
import com.innotree.pilot.security.PilotUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BoardRestController {


    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;


    @PostMapping("/board/save-board/test")
    public ResponseEntity<?> boardSave(@RequestBody Board board, @RequestParam(name = "image") MultipartFile multipartFile, @AuthenticationPrincipal PilotUserDetails pilotUserDetails) throws Exception {
        System.out.println("작동합니다." );
        System.out.println("board : " + board );
        System.out.println("file : " + multipartFile );
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println(multipartFile);
        System.out.println(fileName);
        String uploadDir = "board-photos/";
        boardService.boardSave(board,pilotUserDetails);
        FileService.saveFile(uploadDir,fileName,multipartFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/board/all/1")
    @ResponseBody
    public ResponseEntity<?> BoardAll(){
        List<Board> boardList = boardRepository.findAll();
        List<ResponseBoard> result = boardList.stream()
                .map(ResponseBoard::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/board/NOTICE/{pageNumber}")
    @ResponseBody
    public ResponseEntity<?> BoardNOTICE(@PathVariable(name = "pageNumber") Integer pageNumber){
        Page<Board> boardList = boardService.noticePage(pageNumber);
        List<ResponseBoard> result = boardList.stream()
        .map(ResponseBoard::from)
        .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/board/FAQ/{pageNumber}")
    @ResponseBody
    public ResponseEntity<?> BoardFAQ(@PathVariable(name = "pageNumber") Integer pageNumber){
        Page<Board> boardList = boardService.faqPage(pageNumber);
        List<ResponseBoard> result = boardList.stream()
                .map(ResponseBoard::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/board/QNA/{pageNumber}")
    @ResponseBody
    public ResponseEntity<?> BoardQNA(@PathVariable(name = "pageNumber") Integer pageNumber){
        Page<Board> boardList = boardService.qnaPage(pageNumber);
        List<ResponseBoard> result = boardList.stream()
                .map(ResponseBoard::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

}
