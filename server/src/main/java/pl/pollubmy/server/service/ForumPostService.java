package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.ForumPost;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.entity.dto.ForumPostDTO;
import pl.pollubmy.server.entity.dto.ForumPostDTOConverter;
import pl.pollubmy.server.exceptions.ForumPostNotFoundException;
import pl.pollubmy.server.exceptions.UserNotFoundException;
import pl.pollubmy.server.repository.ForumPostRepository;
import pl.pollubmy.server.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForumPostService {

    private final UserRepository userRepository;
    private final ForumPostRepository forumPostRepository;

    @Autowired
    public ForumPostService(UserRepository userRepository, ForumPostRepository forumPostRepository) {
        this.userRepository = userRepository;
        this.forumPostRepository = forumPostRepository;
    }

    public void addNewPost(ForumPost newPostBody, String userLogin) {
        Optional<User> user = this.userRepository.findByLogin(userLogin);
        checkIfUserFound(user);
        addPost(newPostBody, user.get());
    }

    public List<ForumPostDTO> getAllPost() {
        List<ForumPost> allForumPosts = this.forumPostRepository.findAll().stream().filter(ForumPost::isActive).collect(Collectors.toList());
        checkIfPostsExist(allForumPosts);
        return convertPostToPostDTO(allForumPosts);
    }

    public List<ForumPostDTO> getMyPost(String userLogin) {
        Optional<User> user = this.userRepository.findByLogin(userLogin);
        User foundUser = checkIfUserFound(user);
        //return checkIfUserHasPostsAndGetIfExists(user.get());
        List userPosts= checkIfUserHasPosts(foundUser);
        return convertPostToPostDTO(userPosts);
    }

    public void deactivatePost(String deactivatePostId) {
        Optional<ForumPost> postToDeactivate = this.forumPostRepository.findById(deactivatePostId);
        if (!postToDeactivate.isPresent()) throw new ForumPostNotFoundException("Post not found");
        postToDeactivate.get().setActive(false);
        this.forumPostRepository.save(postToDeactivate.get());
    }


    private List<ForumPostDTO> convertPostToPostDTO(List<ForumPost> allForumPosts) {

        ArrayList<ForumPostDTO> allForumPostsToReturn = new ArrayList<>();

        for (ForumPost fr : allForumPosts) {
            ForumPostDTO forumPostDTO = ForumPostDTOConverter.toDTO(fr.getUserIdFk(), fr);
            allForumPostsToReturn.add(forumPostDTO);
        }
        return allForumPostsToReturn;
    }

    private void checkIfPostsExist(List<ForumPost> allForumPosts) {
        if (allForumPosts.isEmpty()) throw new ForumPostNotFoundException("Posts not found");
    }

    private User checkIfUserFound(Optional<User> user) {
        if (!user.isPresent()) {
            throw new UserNotFoundException("User with this login not found");
        }
        return user.get();
    }

    private void addPost(ForumPost newPostBody, User user) {
        newPostBody.setUserIdFk(user);
        user.getForumPosts().add(newPostBody);
        this.userRepository.save(user);
    }

    private List checkIfUserHasPosts(User user) {
        List<ForumPost> userPosts = this.forumPostRepository.findAll().stream()
                .filter(post -> post.getUserIdFk().getUserId().equals(user.getUserId()))
                .filter(ForumPost::isActive)
                .collect(Collectors.toList());
        if (userPosts.isEmpty()) throw new ForumPostNotFoundException("User's posts not found");
        return userPosts;
    }
}
