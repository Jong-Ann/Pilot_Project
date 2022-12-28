package com.innotree.pilot.test;

import com.innotree.pilot.board.Board;
import com.innotree.pilot.board.BoardRepository;
import com.innotree.pilot.board.BoardService;
import com.innotree.pilot.board.BoarderType;
import com.innotree.pilot.reply.Reply;
import com.innotree.pilot.user.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import java.util.Iterator;
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
        Pageable pageable = PageRequest.of(0, 4);
        String title = "이선희";
        Page<Board> board = boardRepository.findContentsAndTitle(title,pageable);
        board.getContent();
        System.out.println("board = " + board.getContent());
    }

    @Test
    public void getBoardReplyTest(){
        Integer id = 66;
        Board listboard = boardRepository.findById(id).get();
        List<Reply> list = listboard.getReplyList();
        int replyCount = list.size();
        System.out.println(replyCount);
    }

    @Test
    public void setBoardPhotoNull() {
        Integer id = 21;
        Board board = boardRepository.findById(id).get();
        board.setImageNull();
        System.out.println(board.getPhotos());
    }

    @Test
    public void boardPageableSortTest() {

        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(0, 4, sort);

        Page<Board> boardIterator = boardRepository.findAll(pageable);

        for (Board board : boardIterator) {
            System.out.println("board = " + board.getId());
        }
    }

}
