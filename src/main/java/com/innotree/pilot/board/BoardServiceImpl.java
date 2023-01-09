package com.innotree.pilot.board;

import com.innotree.pilot.reply.Reply;
import com.innotree.pilot.reply.ReplyRepository;
import com.innotree.pilot.security.PilotUserDetails;
import com.innotree.pilot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Service
public class BoardServiceImpl implements BoardService {



    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Override
    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    @Override
    @Transactional
    public Board boardSave(Board board, PilotUserDetails pilotUserDetails) {
        board.setUser(pilotUserDetails.getUser());
        return boardRepository.save(board);
    }

    @Override
    public Page<Board> noneWordBoardPageSort(Integer boardPageNum,String orderValue ,String orderDirection) {
        Sort orders = Sort.by(orderValue);
        if (orderDirection.equals("descending")) {
            orders = orders.descending();
        }
        Pageable pageable = PageRequest.of(boardPageNum - 1, 10 , orders);
        return boardRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Board boardEditSave(Board board, User user) {
        board.setUser(user);
        return boardRepository.save(board);
    }

    @Override
    @Transactional(readOnly = true)
    public Board getBoard(Integer id) {
        return boardRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteBoard(Integer id) {
        boardRepository.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Board> boardPage(Integer boardPageNum, String word, String orderValue, String orderDirection) {
        Sort orders = Sort.by(orderValue);
        if (word != null) {
            if (orderDirection.equals("descending")) {
                orders = orders.descending();
            }
            Pageable pageable = PageRequest.of(boardPageNum - 1, 10 , orders);
            return boardRepository.findContentsAndTitle(word, pageable);
        } else {
            Pageable pageable = PageRequest.of(boardPageNum - 1, 10 , orders);
            return boardRepository.findAll(pageable);
        }
    }

    @Override
    public Page<Board> boardPage(Integer boardPageNum) {
        Pageable pageable = PageRequest.of(boardPageNum - 1, 10);
        return boardRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reply> replyList() {
        return replyRepository.findAll();
    }

    @Override
    @Transactional
    public Reply saveReply(Reply reply,int boardId,PilotUserDetails pilotUserDetails) {
        Board board = boardRepository.findById(boardId).get();
        reply.setBoard(board);
        reply.setUser(pilotUserDetails.getUser());
        Reply saveReply = replyRepository.save(reply);
        return saveReply;
    }
    @Override
    @Transactional
    public Page<Board> boarderTypePage(Integer boardPageNum,BoarderType boarderType,String orderValue, String orderDirection) {
        Sort orders = Sort.by(orderValue);
        if (orderDirection.equals("descending")) {
            orders = orders.descending();
        }
        Pageable pageable = PageRequest.of(boardPageNum - 1, 4 , orders);
        return boardRepository.findByBoarderType(boarderType,pageable);
    }

    @Override
    @Transactional
    public void deleteReply(Integer replyId) {
        replyRepository.deleteById(replyId);
    }

    @Override
    public void deleteReplyAll() {
        replyRepository.deleteAll();
    }

    @Deprecated
    @Override
    public List<Board> noticePage(Integer boardPageNum) {
//        Pageable pageable = PageRequest.of(boardPageNum - 1, 4);
        return boardRepository.findNOTICEBoard();
    }
    @Deprecated
    @Override
    public List<Board> faqPage(Integer boardPageNum) {
//        Pageable pageable = PageRequest.of(boardPageNum - 1, 10);
        return boardRepository.findFAQBoard();
    }
    @Deprecated
    @Override
    public List<Board> qnaPage(Integer boardPageNum) {
//        Pageable pageable = PageRequest.of(boardPageNum - 1, 10);
        return boardRepository.findQNABoard();
    }



}

