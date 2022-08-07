package com.example.blog_application.repositories;

import com.example.blog_application.entities.Category;
import com.example.blog_application.entities.Post;
import com.example.blog_application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    @Query("select  p from Post p where p.postTitle like :key")
    List<Post> searchByTitle(@Param("key")String title);
}
