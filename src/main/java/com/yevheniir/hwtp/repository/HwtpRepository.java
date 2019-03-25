package com.yevheniir.hwtp.repository;

import com.yevheniir.hwtp.model.Stuff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HwtpRepository extends JpaRepository<Stuff, Long> {
}
