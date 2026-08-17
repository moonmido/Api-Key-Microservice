package com.Api_Key_demo.Api_Key_demo.Repositories;

import com.Api_Key_demo.Api_Key_demo.Models.MyApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyApiRepo extends JpaRepository<MyApi,Long> {
}
