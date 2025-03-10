package com.blog.controller;

import com.blog.model.Blog;
import com.blog.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // Get all blogs (public or secured as needed)
    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    // Get a specific blog by ID
    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        Blog blog = blogService.getBlogById(id);
        return ResponseEntity.ok(blog);
    }

    // Create a new blog (requires valid JWT token)
    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog, Authentication authentication) {
        String username = authentication.getName();
        Blog createdBlog = blogService.createBlog(blog, username);
        return ResponseEntity.ok(createdBlog);
    }

    // Update a blog (requires valid JWT token and author ownership)
    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog blog, Authentication authentication) {
        String username = authentication.getName();
        Blog updatedBlog = blogService.updateBlog(id, blog, username);
        return ResponseEntity.ok(updatedBlog);
    }

    // Delete a blog (requires valid JWT token and author ownership)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        blogService.deleteBlog(id, username);
        return ResponseEntity.noContent().build();
    }
}
