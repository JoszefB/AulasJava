package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
	
	private static SimpleDateFormat sdf;
	
	private Date moment;
	private String title;
	private String content;
	private Integer likes;
	
	private List<Comment> comments;
	
	public Post() {
		sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		comments = new ArrayList<>();
	}
	
	public Post(Date moment, String title, String content, Integer likes) {
		setMoment(moment);
		setTitle(title);
		setContent(content);
		setLikes(likes);
		sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		comments = new ArrayList<>();
	}

	public static SimpleDateFormat getSdf() {
		return sdf;
	}

	public static void setSdf(SimpleDateFormat sdf) {
		Post.sdf = sdf;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	
	public List<Comment> getComments(){
		return comments;
	}
	
	public void addComments(Comment comment){
		comments.add(comment);
	}
	
	public void removeComment(Comment comment) {
		comments.remove(comment);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(title + "\n");
		sb.append(likes);
		sb.append(" Likes - ");
		sb.append(sdf.format(moment) + "\n");
		sb.append(content + "\n");
		sb.append("Comments:\n");
		for(Comment c: comments) {
			sb.append(c.getText() + "\n");
		}
		return sb.toString();
	}

}
