package com.innotree.pilot.board;

import com.innotree.pilot.reply.Reply;
import com.innotree.pilot.reply.ReplyService;
import com.innotree.pilot.user.User;
import com.innotree.pilot.security.PilotUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface  BoardService extends ReplyService {

    List<Board> boardList();

    Board boardSave(Board board, @AuthenticationPrincipal PilotUserDetails pilotUserDetails);

    Board boardEditSave(Board board, User user);

    Board getBoard(Integer id);

    void deleteBoard(Integer id);

    void deleteReplyAll();

    Page<Board> boardPage(Integer boardPageNum,String word, String orderValue , String orderDirection);

    Page<Board> boardPage(Integer boardPageNum);

    Page<Board> noneWordBoardPageSort(Integer boardPageNum, String orderValue ,String orderDirection);


    Page<Board> boarderTypePage(Integer boardPageNum,BoarderType boarderType,String orderValue, String orderDirection);

    @Override
    List<Reply> replyList();

    @Override
    Reply saveReply(Reply reply, int boardId, PilotUserDetails pilotUserDetails);

    @Deprecated
    Page<Board> noticePage(Integer boardPageNum);
    @Deprecated
    Page<Board> faqPage(Integer boardPageNum);
    @Deprecated
    Page<Board> qnaPage(Integer boardPageNum);


}
