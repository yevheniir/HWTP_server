package com.yevheniir.hwtp.repository;

import com.yevheniir.hwtp.model.Stuff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HwtpRepository extends JpaRepository<Stuff, Long> {
    @Query("from Stuff where archive=false")
    List<Stuff> findAllUnarchived();
}
