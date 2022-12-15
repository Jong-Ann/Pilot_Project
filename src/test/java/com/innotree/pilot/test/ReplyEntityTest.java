package com.innotree.pilot.test;

import com.innotree.pilot.board.Board;
import com.innotree.pilot.reply.Reply;
import com.innotree.pilot.reply.ReplyRepository;
import com.innotree.pilot.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class ReplyEntityTest {

    @Autowired
    private ReplyRepository repo;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void createReplyTest() {
        Board board = entityManager.find(Board.class, 34);
        User user = entityManager.find(User.class, 2);
        Reply reply = new Reply("안녕하세요 테스트입니다.",board ,user);
        Reply saveReply = repo.save(reply);
        assertThat(saveReply.getId()).isGreaterThan(0);
    }

    @Test
    public void createReplyEveryTest() {
        Board board = entityManager.find(Board.class, 34);
        User user = entityManager.find(User.class, 3);
        Reply reply1 = new Reply("노래 추천하자면 이선희의 그중에 그대를 만나 추천합니다.",board ,user);
        Reply reply2 = new Reply("그리고 한동근 노래도 좋아요~",board,user);

        repo.saveAll(List.of(reply1,reply2));
    }
}
