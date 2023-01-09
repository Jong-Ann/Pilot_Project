package com.innotree.pilot.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board,Integer> {
    @Deprecated
    @Query("select b from Board b where b.boarderType = 'NOTICE'")
    List<Board> findNOTICEBoard();

    @Deprecated
    @Query("select b from Board b where b.boarderType = 'QNA'")
    List<Board> findQNABoard();
    @Deprecated
    @Query("select b from Board b where b.boarderType = 'FAQ'")
    List<Board> findFAQBoard();

    Page<Board> findByBoarderType(BoarderType boarderType,Pageable pageable);
    @Query("select b from Board b WHERE b.contents LIKE %?1% or b.title LIKE %?1%")
    Page<Board> findContentsAndTitle(String search, Pageable pageable);


}
