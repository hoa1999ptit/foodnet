/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodnet.foodnetclient.gui;

import foodnet.foodnetclient.entities.AuthEntity;
import foodnet.foodnetclient.entities.Comment;
import foodnet.foodnetclient.entities.Post;
import foodnet.foodnetclient.entities.PostLike;
import foodnet.foodnetclient.gui.utils.FieldUtils;
import static foodnet.foodnetclient.http.Request.path;
import foodnet.foodnetclient.http.RequestException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Kushtrim Hajrizi
 */
public class ReadPostController extends DynamicController implements Initializable {

    @FXML
    private Label titleLabel;
    @FXML
    private Label contentLabel;
    @FXML
    private TextArea commentTextArea;
    @FXML
    private ListView<CommentListItem> commentsListView;
    @FXML
    private Button likesBtn;
    @FXML
    private Label likesLabel;
    
    private Post post;
    private int likes;
    private boolean liked;
    @FXML
    private Button commentBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @Override
    public void init() {
        post = context.getPost();
        loadPostData();

        if (context.getAuthEntity().getType() == AuthEntity.BUSINESS) {
            likesBtn.setVisible(false);
            commentTextArea.setVisible(false);
            commentBtn.setVisible(false);
        } else {
            checkIfLiked();
        }
    }
    
    private void loadPostData() {
        try {
            Post newPost = request.get(
                    Post.class, 
                    path("(businesses)/{}/(posts)/{}", post.getBiznesId(), post.getPostId()), 
                    null
            );
            post = newPost;
        } catch (RequestException ex) {
            Alerts.info("Could not get post from the server");
            return;
        }
        titleLabel.setText(post.getTitle());
        contentLabel.setText(post.getContent());
        
        likes = post.getPostLikeList().size();
        likesLabel.setText("Likes: " + likes);
        
        ObservableList<CommentListItem> cliList = commentsListView.getItems();
        cliList.clear();
        for (Comment comment: post.getCommentList())
            if (comment.getUserId() != null)
                cliList.add(new CommentListItem(comment));
    }
    
    private void checkIfLiked() {
        Integer userId = context.getId();
        for (PostLike pl: post.getPostLikeList()) {
            if (userId.equals(pl.getUserId())) {
                liked = true;
                likesBtn.setText("Dislike");
                return;
            }
        }
    }

    @FXML
    private void onComment(ActionEvent event) {
        if (FieldUtils.fieldsEmpty(commentTextArea)) {
            Alerts.info("Write a comment first, then send it.");
        } else {
            Comment comment = new Comment(commentTextArea.getText());
            comment.setUserIdInteger(context.getId());
            try {
                request.create(
                        Comment.class, 
                        comment, 
                        path("(businesses)/{}/(posts)/{}/(comments)", post.getBiznesId(), post.getPostId())
                );
                commentTextArea.setText("");
                loadPostData();
                Alerts.info("Comment created successfully!");
            } catch (RequestException ex) {
                Alerts.info("Could not create comment: " + ex.getMessage());
            }
        }
    }

    @FXML
    private void onLike(ActionEvent event) {
        try {
            if (liked) {
                request.delete(
                        PostLike.class, path("(users)/{}/(posts)/{}/(likes)", context.getId(), post.getPostId())
                );
                liked = false; likesBtn.setText("Like");
            } else {
                request.create(
                        PostLike.class, new PostLike(), path("(users)/{}/(posts)/{}/(likes)", context.getId(), post.getPostId())
                );
                liked = true; likesBtn.setText("Dislike");
            }
            
            loadPostData();
        } catch (RequestException ex) {
            Alerts.info("Could not process request: " + ex.getMessage());
        }
    }
    
    private class CommentListItem extends BorderPane {
    
        private Comment comment;
        private Button deleteBtn;

        public CommentListItem(Comment comment) {
               
            this.comment = comment;
            if (comment.getUserId().getUserId() == context.getId() && 
                    context.getAuthEntity().getType() == AuthEntity.USER) {
                deleteBtn = new Button("Delete");
                deleteBtn.setOnAction(event -> deleteComment());
                this.setRight(deleteBtn);
            }
                        
            this.setLeft(new Label(this.comment.getContent()));
        }
        
        private void deleteComment() {
            if (Alerts.confirm()) {
                try {
                    request.delete(
                            Comment.class, 
                            path("(businesses)/{}/(posts)/{}/(comments)/{}", 
                                post.getBiznesId(), post.getPostId(), comment.getCommentId()
                            )
                    );
                } catch (RequestException ex) {
                    System.out.println(ex);
                }

                loadPostData();
            }
        }
        
        public Comment getComment() {
            return comment;
        }
    }
    
}
