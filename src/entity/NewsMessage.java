package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class NewsMessage extends BaseMessage{
	@XStreamAlias("ArticleCount")
	private String articleCount;
	private List<Article> articles = new ArrayList();
	protected String getArticleCount() {
		return articleCount;
	}
	protected void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}
	protected List<Article> getArticles() {
		return articles;
	}
	protected void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	public NewsMessage(Map<String, String> requestMap, String articleCount,
			List<Article> articles) {
		super(requestMap);
		this.setMsgType("news");
		this.articleCount = articleCount;
		this.articles = articles;
	}
	
}
