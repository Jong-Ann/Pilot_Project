package com.innotree.pilot.test;

import com.innotree.pilot.board.Board;
import com.innotree.pilot.board.BoardRepository;
import com.innotree.pilot.board.BoarderType;
import com.innotree.pilot.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class testFindByQuery {

    @Autowired
    private BoardRepository boardRepository;
//    @Test
//    public void testSearchBoarderType() {
//        Pageable pageable = PageRequest.of(0, 4);
//        Page<Board> findNoticeBoard = boardRepository.findNOTICEBoard(pageable);
//        Page<Board> findQNABoard = boardRepository.findQNABoard(pageable);
//        Page<Board> findFAQBoard = boardRepository.findFAQBoard(pageable);
//        for (Board board : findNoticeBoard) {
//            System.out.println(board.getBoarderType());
//        }
//    }


}
