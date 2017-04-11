package com.example.repositories;

import com.example.models.gameModels.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tooji on 4/5/2017.
 */
@Repository
public interface GameRepository extends CrudRepository<Game, Long> {


}
