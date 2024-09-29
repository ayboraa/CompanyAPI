package org.company.api.service.impl;

import org.company.api.common.CommentId;
import org.company.api.common.MemberId;
import org.company.api.entity.CommentEntity;
import org.company.api.service.Comment;
import org.company.api.service.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper implements Mapper<Comment, CommentEntity> {
    @Override
    public CommentEntity toEntity(Comment comment) {
        return new CommentEntity(comment.getId().uuid(), comment.getContent(), comment.getAuthorId().uuid());
    }

    @Override
    public Comment toDTO(CommentEntity entity) {
        return new Comment(new CommentId(entity.getId()), new MemberId(entity.getAuthorId()), entity.getContent());
    }

    @Override
    public List<Comment> toDTOList(List<CommentEntity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }
}
