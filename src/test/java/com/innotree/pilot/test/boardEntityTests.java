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

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class boardEntityTests {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private EntityManager entityManager;
    @Test
    public void firstBoardTest(){
        User user = entityManager.find(User.class,1);
        Board testBoard = new Board("secondTitle", "secondContents" ,user);
        Board saveTestBoard = boardRepository.save(testBoard);
        assertThat(saveTestBoard.getId()).isGreaterThan(0);
        System.out.println(saveTestBoard);
    }
    @Test
    public void anotherUserBoardTest(){
        User secondUser = entityManager.find(User.class,2);
        Board testBoard = new Board("anotherUserTest","anotherContents",secondUser);
        Board saveTestBoard = boardRepository.save(testBoard);
    }
    @Test
    public void queryTest() {

        BoarderType boarderType = BoarderType.NOTICE;
        Pageable pageable = PageRequest.of(0, 4);
        Page<Board> listBoarderType = boardRepository.findByBoarderType(boarderType,pageable);

        for (Board board : listBoarderType) {
            System.out.println(board.getContents());
        }
    }
    @Test
    public void searchTest() {
        String title = "이선희";
        Page<Board> board = boardRepository.findContents(title);
        System.out.println("board = " + board);
    }
}
