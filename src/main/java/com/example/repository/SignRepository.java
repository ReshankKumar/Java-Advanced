package com.example.repository;

import com.example.entity.SignIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignRepository extends JpaRepository<SignIn,Long> {
}
