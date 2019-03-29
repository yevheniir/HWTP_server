package com.yevheniir.hwtp.repository;

import com.yevheniir.hwtp.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
    File findById(String id);
}
