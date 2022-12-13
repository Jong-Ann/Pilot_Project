package com.innotree.pilot.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Integer> {

    @Query("select b from Board b where b.boarderType = 'NOTICE'")
    Page<Board> findNOTICEBoard(Pageable pageable);
    @Query("select b from Board b where b.boarderType = 'QNA'")
    Page<Board> findQNABoard(Pageable pageable);
    @Query("select b from Board b where b.boarderType = 'FAQ'")
    Page<Board> findFAQBoard(Pageable pageable);


}
