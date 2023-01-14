package com.innotree.pilot.board;


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
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "board-photos/";
        boardService.boardSave(board, pilotUserDetails);
        FileService.saveFile(uploadDir, fileName, multipartFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/board/all/1")
    @ResponseBody
    public ResponseEntity<?> BoardAll() {
        List<Board> boardList = boardRepository.findAll();
        List<ResponseBoard> result = boardList.stream()
                .map(ResponseBoard::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/board/{boarderType}/1")
    @ResponseBody
    public ResponseEntity<?> BoarderTypeAll(@PathVariable(name = "boarderType") BoarderType boarderType){
        List<Board> boardList = boardRepository.findByBoarderType(boarderType);
        List<ResponseBoard> result = boardList.stream()
                .map(ResponseBoard::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
