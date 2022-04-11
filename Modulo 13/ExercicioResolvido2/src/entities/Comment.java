package entities;

public class Comment {
	
	public String text;
	
	public Comment() {}
	
	public Comment(String text) {
		setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
