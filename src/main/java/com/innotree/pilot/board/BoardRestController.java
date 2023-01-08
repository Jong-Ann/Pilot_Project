package com.innotree.pilot.board;


import com.innotree.pilot.Response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BoardRestController {


    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/testGet")
    @ResponseBody
    public ResponseEntity<?> BoardTest(){
        List<Board> boardList = boardRepository.findAll();
        List<ResponseBoard> result = boardList.stream()
        .map(ResponseBoard::from)
        .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
