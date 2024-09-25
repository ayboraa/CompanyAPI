package org.company.api.service;


import org.company.api.common.CommentId;
import org.company.api.entity.CommentEntity;
import org.company.api.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;


    public CommentEntity saveComment(Comment comment){
        CommentEntity entity = new CommentEntity(comment.getId().uuid(), comment.getContent(), comment.getAuthorId().uuid());
        commentRepository.save(entity);
        return entity;
    }


    public Optional<CommentEntity> findCommentById(CommentId id) {
        return commentRepository.findById(id.uuid());
    }

}
