package com.yevheniir.hwtp.repository;

import com.yevheniir.hwtp.model.Image;
import com.yevheniir.hwtp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findById(String id);
}
