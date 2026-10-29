package com.app.ecom.user;
import org.springframework.data.jpa.repository.JpaRepository;
// Provides CRUD operations (save, findById, findAll, etc.) for User, backed by Spring Data JPA

public interface UserRepository extends JpaRepository<User, Long> {//Give Entity and PRIMARY KEY Type

}

