package com.example.toy.src.comment.repository;

import com.example.toy.src.comment.dto.GetAllCommentDto;
import com.example.toy.src.comment.dto.GetCommentResDto;
import com.example.toy.src.comment.entity.Comment;
import com.example.toy.src.comment.entity.QComment;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
public class CommentRepositorySupportImpl
    extends QuerydslRepositorySupport implements CommentRepositorySupport {

    private JdbcTemplate jdbcTemplate;
    private final JPAQueryFactory jpaQueryFactory;
    QComment comment = QComment.comment;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public CommentRepositorySupportImpl(final JPAQueryFactory jpaQueryFactory) {
        super(Comment.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
//
//    @Override
    public List<GetCommentResDto> findCommentByUserIdx(Long userIdx) {
        return jpaQueryFactory
                .select((Projections.constructor(GetCommentResDto.class,
                        comment.idx, comment.content, comment.isBlind
                , comment.post.post_idx, comment.status, comment.userIdx )))
                .from(comment)
                .where(comment.userIdx.eq(userIdx))
                .fetch();
    }

//    @Override
    public List<GetAllCommentDto> findAllByPostIdx(Long post_idx) {
        return jdbcTemplate.query(
                "select * from comment where post_idx = ? " +
                        "ORDER BY CASE WHEN parent_cmt_idx = 0 " +
                        "THEN cmt_idx ELSE parent_cmt_idx END, seq"
                ,(rs, rowNum) -> new GetAllCommentDto(
                        rs.getLong("cmt_idx"),
                        rs.getString("cmt_content"),
                        rs.getLong("user_idx"),
                        rs.getLong("post_idx"),
                        rs.getLong("parent_cmt_idx"),
                        rs.getLong("seq"),
                        rs.getByte("is_blind"),
                        rs.getByte("status")), post_idx);

//        return jpaQueryFactory
//                .select(
//                        (Projections.constructor(GetAllCommentDto.class,
//                        comment.idx, comment.content, comment.userIdx, comment.post.post_idx, comment.parentCmtIdx,
//                                comment.seq, comment.isBlind, comment.status )))
//                .from(comment)
//                .orderBy(new CaseBuilder()
//                        .when(comment.parentCmtIdx.eq(0L))
//                        .then(comment.idx).otherwise(comment.parentCmtIdx)
//                )
//                .where(comment.post.post_idx.eq(post_idx))
//                .fetch();

    }

    public Long findLatestSeq(Long parentCmtIdx) {

        Long seq = jdbcTemplate.queryForObject( "select IFNULL(max(seq) + 1, 0) as seq from comment where parent_cmt_idx = ?", Long.class, parentCmtIdx);
        log.info("seq: {}", seq);
        return seq;
//        return jpaQueryFactory
//                .select(comment.seq.max())
//                .from(comment)
//                .where(comment.parentCmtIdx.eq(parentCmtIdx))
//                .fetchOne();
    }
}
//  select IFNULL(max(seq), 0) as seq from comment where parent_cmt_idx = 3;