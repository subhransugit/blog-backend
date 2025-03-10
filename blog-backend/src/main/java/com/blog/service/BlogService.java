package com.blog.service;

import com.blog.model.Blog;
import com.blog.model.User;
import com.blog.repository.BlogRepository;
import com.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }
    public Blog createBlog(Blog blog,String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        blog.setAuthor(user);
        return blogRepository.save(blog);
    }

    public Blog updateBlog(Long id,Blog updatedBlog,String username) {

        Blog existingBlog = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
        existingBlog.setTitle(updatedBlog.getTitle());
        existingBlog.setContent(updatedBlog.getContent());
        if(!existingBlog.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You are not authorized to update this blog");
        }
        return blogRepository.save(existingBlog);
    }
    public List<Blog> getBlogsByAuthorId(Long authorId) {
        return blogRepository.findByAuthorId(authorId);
    }

    public void deleteBlog(Long id, String username) {
        Blog existingBlog = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
        if (!existingBlog.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You are not authorized to delete this blog");
        }
        blogRepository.delete(existingBlog);
    }


    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
    }
}
