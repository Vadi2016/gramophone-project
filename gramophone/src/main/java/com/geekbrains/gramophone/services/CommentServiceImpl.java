package com.geekbrains.gramophone.services;

import com.geekbrains.gramophone.entities.Comment;
import com.geekbrains.gramophone.entities.Genre;
import com.geekbrains.gramophone.entities.Track;
import com.geekbrains.gramophone.entities.User;
import com.geekbrains.gramophone.repositories.CommentRepository;
import com.geekbrains.gramophone.repositories.GenreRepository;
import com.geekbrains.gramophone.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public boolean save(Comment comment) {
        commentRepository.save(comment);
        return true;
    }

    @Override
    public Page<Comment> getCommentsWithPagingAndFiltering(int pageNumber, int pageSize) {
        return commentRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public List<Comment> findByUserAndTrack(User user, Track track) {
        return commentRepository.findByUserAndTrack(user, track);
    }

    @Override
    public List<Comment> findByTrack(Track track) {
        return commentRepository.findByTrack(track);
    }
}
