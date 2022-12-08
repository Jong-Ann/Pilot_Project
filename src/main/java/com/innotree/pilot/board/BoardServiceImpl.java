package com.innotree.pilot.board;

import com.innotree.pilot.security.PilotUserDetails;
import com.innotree.pilot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

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
    @Transactional
    public Board boardEditSave(Board board, User user) {
        board.setUser(user);
        return boardRepository.save(board);
    }

    @Override
    public Board getBoard(Integer id) {
        return boardRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteBoard(Integer id) {
        boardRepository.deleteById(id);
    }

    @Override
    public Page<Board> boardPage(Integer boardPageNum) {
        Pageable pageable = PageRequest.of(boardPageNum - 1, 10);
        return boardRepository.findAll(pageable);
    }

}

