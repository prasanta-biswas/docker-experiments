package io.apitest.example.repository;

import io.apitest.example.model.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by prasantabiswas on 28/06/18.
 */

@Repository
public interface ViewRepository extends JpaRepository<View,Long>{

}
