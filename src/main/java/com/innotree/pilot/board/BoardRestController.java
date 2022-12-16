package com.innotree.pilot.board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BoardRestController {

//    @Autowired
//    private BoardRepository boardRepository;
//
//    @GetMapping("/getBoard/{BoarderType}")
//    public String getBoarderType(@PathVariable(name = "BoarderType") BoarderType boarderType) {
//        List<Board> listBoarderType = boardRepository.findByBoarderType(boarderType);
//        String result = listBoarderType.stream().map(String::valueOf).collect(Collectors.joining());
//        String result2 = StringUtils.join(listBoarderType, ",");
//        return result2;
//    }
}
