package com.innotree.pilot.board;

import com.innotree.pilot.reply.Reply;
import com.innotree.pilot.reply.ReplyService;
import com.innotree.pilot.user.User;
import com.innotree.pilot.security.PilotUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface  BoardService extends ReplyService {

    List<Board> boardList();

    Board boardSave(Board board, @AuthenticationPrincipal PilotUserDetails pilotUserDetails);

    Board boardEditSave(Board board, User user);

    Board getBoard(Integer id);

    void deleteBoard(Integer id);

    Page<Board> boardPage(Integer boardPageNum);


    @Override
    List<Reply> replyList();

    @Override
    Reply saveReply(Reply reply, int boardId, PilotUserDetails pilotUserDetails);


}
