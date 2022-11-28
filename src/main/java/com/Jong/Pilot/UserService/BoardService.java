package com.Jong.Pilot.UserService;

import com.Jong.Pilot.Entity.Board;
import com.Jong.Pilot.Entity.User;
import com.Jong.Pilot.Repository.BoardRepository;
import com.Jong.Pilot.Security.PilotUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> boardList() {
        return (List<Board>) boardRepository.findAll();
    }

    public void boardSave(Board board, @AuthenticationPrincipal PilotUserDetails pilotUserDetails) {
        board.setUser(pilotUserDetails.getUser());
        boardRepository.save(board);
    }

    public Board getBoard(Integer id) {
        return boardRepository.findById(id).get();
    }

    public void deleteBoard(Integer id) {
        boardRepository.deleteById(id);
    }

    public Page<Board> boardPage(Integer boardPageNum){
        Pageable pageable = PageRequest.of(boardPageNum-1,10);
        return boardRepository.findAll(pageable);
    }
}
