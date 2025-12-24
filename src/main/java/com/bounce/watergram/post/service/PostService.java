package com.bounce.watergram.post.service;

import com.bounce.watergram.comment.domains.Comment;
import com.bounce.watergram.comment.dto.CommentDetail;
import com.bounce.watergram.comment.service.CommentService;
import com.bounce.watergram.common.FileManager;
import com.bounce.watergram.like.service.LikeService;
import com.bounce.watergram.post.domain.Post;
import com.bounce.watergram.post.dto.PostDetail;
import com.bounce.watergram.post.repository.PostRepository;
import com.bounce.watergram.user.domain.User;
import com.bounce.watergram.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor // 필수 멤버변수 를 생성자를 통해 대응
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final LikeService likeService;
    private final CommentService commentService;

    public PostService(PostRepository postRepository, UserService userService, LikeService likeService, CommentService commentService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.likeService = likeService;
        this.commentService = commentService;
    }

    public boolean createPost(
            long userId
            , String contents
            , MultipartFile imageFile) {

//        String imagePath = FileManager.saveFile(userId, imageFile);
        String imagePath = null;

//       이미지 파일이 존재할 경우에만 이미지 저장 수행
        if (imageFile != null && !imageFile.isEmpty()) {
            imagePath = FileManager.saveFile(userId, imageFile);
        }

        Post post = Post.builder()
                .userId(userId)
                .contents(contents)
                .imagePath(imagePath)
                .build();

        try {
            postRepository.save(post);
        } catch (DataAccessException e) {
            return false;
        }

        return true;
    }

    public List<PostDetail> getPostList(long userId) {

        List<Post> postList = postRepository.findAll(Sort.by("id").descending());

        List<PostDetail> postDetailList = new ArrayList<>();
        for(Post post:postList){
            // Post -> PostDetail
            // 1 + N 문제 : cache

            User user = userService.getUserById(post.getUserId());

            // 1. 여기서 유저 정보가 잘 나오는지 확인
            System.out.println(">>> 유저 객체 정보: " + user);
            System.out.println(">>> 로그인 아이디 값: " + user.getLoginId());

            List<CommentDetail> commentList = commentService.getCommentList(post.getId());

            // 2. 댓글 리스트의 첫 번째 항목 아이디 확인 (리스트가 비어있지 않다면)
            if (!commentList.isEmpty()) {
                System.out.println(">>> 첫번째 댓글 작성자: " + commentList.get(0).getLoginId());
            }

            int likeCount = likeService.countByPostId(post.getId());
            boolean isLike = likeService.isLikeByPostAndUserId(post.getId(), userId);

            PostDetail postDetail = PostDetail.builder()
                    .id(post.getId())
                    .contents(post.getContents())
                    .imagePath(post.getImagePath())
                    .userId(post.getUserId())
                    .loginId(user.getLoginId())
                    .likeCount(likeCount)
                    .isLike(isLike)
                    .commentList(commentList)
                    .build();

            postDetailList.add(postDetail);
        }
        return postDetailList;
    }

    @Transactional
    public boolean deletePost(long id, long userId) {

        Optional<Post> optionalPost = postRepository.findById(id);

        if(optionalPost.isPresent()) {
            try {
                Post post = optionalPost.get();

                if(post.getUserId() != userId) {
                    return false;
                }

                likeService.deleteLikeByPostId(post.getId());
                commentService.deleteCommentByPostId(post.getId());

                postRepository.delete(optionalPost.get());

                FileManager.removeFile(post.getImagePath());

            } catch (DataAccessException e) {
                return false;
            }

        } else {
            return false;
        }
        return true;
    }

}
