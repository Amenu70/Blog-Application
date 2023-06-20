package cs544.Service;

import cs544.DTO.PostDTO;

import java.util.List;

public interface IPostService {
    List<PostDTO> getAllPosts();

    PostDTO savePost(Integer userId, PostDTO postDto);

    PostDTO editPost(Integer id, PostDTO postDto);

    void deletePost(Integer id);

    PostDTO readSinglePost(Integer id);

}
