package com.yevheniir.hwtp.repository;

import com.yevheniir.hwtp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
