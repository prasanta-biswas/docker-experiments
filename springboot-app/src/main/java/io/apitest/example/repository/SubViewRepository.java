package io.apitest.example.repository;

import io.apitest.example.model.SubView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by prasantabiswas on 28/06/18.
 */

@Repository
public interface SubViewRepository extends JpaRepository<SubView,Long>{

}
