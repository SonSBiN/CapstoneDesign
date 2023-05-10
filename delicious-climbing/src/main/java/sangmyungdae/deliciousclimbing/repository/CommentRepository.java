package sangmyungdae.deliciousclimbing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sangmyungdae.deliciousclimbing.domain.entity.TbComment;
import sangmyungdae.deliciousclimbing.domain.entity.TbPost;

import java.util.List;

public interface CommentRepository extends JpaRepository<TbComment, Long> {

    // postId로 생성날짜 기준 오름차순 정렬하여 댓글 리스트 조회
    List<TbComment> findByPost_IdOrderByCreatedAt(Long PostId);

    List<TbComment> findByPostOrderByCreatedAt(TbPost post);
}