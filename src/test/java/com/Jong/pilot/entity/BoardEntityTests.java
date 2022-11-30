package com.Jong.pilot.entity;

import com.Jong.pilot.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class BoardEntityTests {


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

}
