package pl.pollubmy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollubmy.server.entity.ForumPost;
import pl.pollubmy.server.entity.ForumPostRating;
import pl.pollubmy.server.entity.User;
import pl.pollubmy.server.entity.dto.ForumPostDTO;
import pl.pollubmy.server.entity.dto.ForumPostDTOConverter;
import pl.pollubmy.server.entity.tool.CopyPropertiesTool;
import pl.pollubmy.server.exceptions.ForumPostNotFoundException;
import pl.pollubmy.server.exceptions.UserNotFoundException;
import pl.pollubmy.server.exceptions.WrongRatingException;
import pl.pollubmy.server.repository.ForumPostRatingRepository;
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
    private final ForumPostRatingRepository forumPostRatingRepository;

    @Autowired
    public ForumPostService(UserRepository userRepository, ForumPostRepository forumPostRepository, ForumPostRatingRepository forumPostRatingRepository) {
        this.userRepository = userRepository;
        this.forumPostRepository = forumPostRepository;
        this.forumPostRatingRepository = forumPostRatingRepository;
    }

    public void addNewPost(ForumPost newPostBody, String userLogin) {
        User foundUser = checkIfUserExist(userLogin);
        addPost(newPostBody, foundUser);
    }

    public List<ForumPostDTO> getAllPost(String userLogin) {
        User foundUser = checkIfUserExist(userLogin);
        List<ForumPost> allForumPosts = this.forumPostRepository.findAll().stream().filter(ForumPost::isActive).collect(Collectors.toList());
        checkIfPostsExist(allForumPosts);
        return convertPostToPostDTO(allForumPosts, foundUser);
    }

    public List<ForumPostDTO> getMyPost(String userLogin) {
        User foundUser = checkIfUserExist(userLogin);
        List<ForumPost> userPosts = checkIfUserHasPosts(foundUser);
        return convertPostToPostDTO(userPosts, foundUser);
    }

    public void deactivatePost(String userLogin, String deactivatePostId) {
        User user = checkIfUserExist(userLogin);
        ForumPost postToDeactivate = checkIfPostExist(deactivatePostId);
        checkIfItUserPost(user, postToDeactivate);
        postToDeactivate.setActive(false);
        this.forumPostRepository.save(postToDeactivate);
    }

    public ForumPostDTO updatePost(String userLogin, ForumPostDTO updatePost) {

        if (updatePost.getForumPostId() == null || updatePost.getForumPostId().isEmpty()) {
            throw new ForumPostNotFoundException("Forum postDTO must have ID");
        }

        User user = checkIfUserExist(userLogin);
        ForumPost postToUpdate = checkIfPostExist(updatePost.getForumPostId());
        checkIfItUserPost(user, postToUpdate);
        CopyPropertiesTool.copyNonNullProperties(updatePost, postToUpdate);
        this.forumPostRepository.save(postToUpdate);
        return updatePost;
    }

    public String ratePost(String userLogin, String ratingPostId, String rate) {
        ForumPost postRating = checkIfPostExist(ratingPostId);
        User userWhichRate = checkIfUserExist(userLogin);
        if (!checkIfUserRatedOnThisPost(userWhichRate, postRating, rate)) {
            ForumPostRating forumPostRating = new ForumPostRating(userWhichRate, postRating, rate);
            forumPostRating.getUserIdFk().setForumPostRating(forumPostRating);
            forumPostRating.getForumPostIdFk().getForumPostRatings().add(forumPostRating);
            this.forumPostRatingRepository.save(forumPostRating);
        }
        doRate(postRating, rate);
        return getVoteSign(userWhichRate, postRating);
    }

    private String getVoteSign(User userWhichRate, ForumPost postRating) {
        Optional<ForumPostRating> forumPostRating = this.forumPostRatingRepository.findByForumPostIdFkAndUserIdFk(postRating, userWhichRate);
        if (forumPostRating.isPresent()) {
            return forumPostRating.get().getSign();
        }
        return "";
    }

    private void doRate(ForumPost postRating, String rate) {
        Integer postPoints = postRating.getPoints();
        postRating.setPoints(changePoints(rate, postPoints));
        this.forumPostRepository.save(postRating);
    }

    private boolean checkIfUserRatedOnThisPost(User userWhichRate, ForumPost postRating, String rate) {
        Optional<ForumPostRating> forumPostRating = this.forumPostRatingRepository.findByForumPostIdFkAndUserIdFk(postRating, userWhichRate);
        if (forumPostRating.isPresent()) {
            if (forumPostRating.get().getSign().equals(rate)) {
                throw new WrongRatingException("User voted on this post");
            } else {
                this.forumPostRatingRepository.delete(forumPostRating.get());
                return true;
            }
        }
        return false;
    }

    private Integer changePoints(String rate, Integer postPoints) {
        if (rate.equals("minus")) postPoints--;
        if (rate.equals("plus")) postPoints++;

        return postPoints;
    }

    private ForumPost checkIfPostExist(String postId) {
        Optional<ForumPost> post = this.forumPostRepository.findById(postId);
        if (!post.isPresent()) throw new ForumPostNotFoundException("Post not found");
        return post.get();
    }

    private void checkIfItUserPost(User user, ForumPost forumPost) {
        if (!(user.getUserId().equals(forumPost.getUserIdFk().getUserId())))
            throw new ForumPostNotFoundException("This post doesn't belong to this user");
    }

    private List<ForumPostDTO> convertPostToPostDTO(List<ForumPost> allForumPosts, User userLogged) {

        ArrayList<ForumPostDTO> allForumPostsToReturn = new ArrayList<>();

        for (ForumPost fr : allForumPosts) {
            ForumPostDTO forumPostDTO = ForumPostDTOConverter.toDTO(fr, userLogged);
            allForumPostsToReturn.add(forumPostDTO);
        }
        return allForumPostsToReturn;
    }

    private void checkIfPostsExist(List<ForumPost> allForumPosts) {
        if (allForumPosts.isEmpty()) throw new ForumPostNotFoundException("Posts not found");
    }

    private User checkIfUserExist(String login) {
        Optional<User> user = this.userRepository.findByLogin(login);

        if (!user.isPresent()) throw new UserNotFoundException("User with this login not found");
        return user.get();
    }

    private void addPost(ForumPost newPostBody, User user) {
        newPostBody.setUserIdFk(user);
        user.getForumPosts().add(newPostBody);
        this.userRepository.save(user);
    }

    private List<ForumPost> checkIfUserHasPosts(User user) {
        List<ForumPost> userPosts = this.forumPostRepository.findAll().stream()
                .filter(post -> post.getUserIdFk().getUserId().equals(user.getUserId()))
                .filter(ForumPost::isActive)
                .collect(Collectors.toList());
        if (userPosts.isEmpty()) throw new ForumPostNotFoundException("User's posts not found");
        return userPosts;
    }
}
