package com.Jong.Pilot.Entity;

import com.Jong.Pilot.Repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class BoardEntityTests {


    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void firstBoardTest(){

        Board testBoard = new Board("testTitle", "testContents");
        Board SaveTestBoard = boardRepository.save(testBoard);
        assertThat(SaveTestBoard.getId()).isGreaterThan(0);
        System.out.println(SaveTestBoard);
    }

}
