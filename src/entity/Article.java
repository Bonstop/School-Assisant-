package entity;

public class Article {
	private String title;
	private String description;
	private String picUrl;
	private String url;
	protected String getTitle() {
		return title;
	}
	protected void setTitle(String title) {
		this.title = title;
	}
	protected String getDescription() {
		return description;
	}
	protected void setDescription(String description) {
		this.description = description;
	}
	protected String getPicUrl() {
		return picUrl;
	}
	protected void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	protected String getUrl() {
		return url;
	}
	protected void setUrl(String url) {
		this.url = url;
	}
	
	public Article(String title, String description, String picUrl, String url) {
		super();
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
	}
	
}
