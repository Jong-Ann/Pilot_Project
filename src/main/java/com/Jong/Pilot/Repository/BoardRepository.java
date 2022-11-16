package com.Jong.Pilot.Repository;

import com.Jong.Pilot.Entity.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<Board,Integer> {


}
