package cn.sysu.comm.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.JdbcUtils;
import cn.sysu.comm.dao.ArticleMapper;
import cn.sysu.comm.dao.ArticleMapperImpl;
import cn.sysu.comm.dao.CommentMapper;
import cn.sysu.comm.dao.CommentMapperImpl;
import cn.sysu.comm.entity.Article;

public class ArticleService {
	ArticleMapper articleDao = new ArticleMapperImpl();
	CommentMapper commentDao = new CommentMapperImpl();

	public void add(Article article) {
		try {
			JdbcUtils.beginTransaction();
			articleDao.addArticle(article);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try{
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {// 一般不会到这一步
				throw new RuntimeException(e);
			}
		}
	}

	public void update(Article article) {
		try {
			JdbcUtils.beginTransaction();
			articleDao.updateArticle(article);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try{
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {// 一般不会到这一步
				throw new RuntimeException(e);
			}
		}
	}

	public boolean delete(int art_id, String userid) {
		if(articleDao.findArticleById(art_id).getAuthorId().equals(userid)
		    || userid.equalsIgnoreCase("admin")){
			try {
				JdbcUtils.beginTransaction();
				articleDao.deleteArticle(art_id);
				JdbcUtils.commitTransaction();
				return true;
			} catch (SQLException e) {
				try{
					JdbcUtils.rollbackTransaction();
					return false;
				} catch (SQLException e1) {// 一般不会到这一步
					throw new RuntimeException(e);
				}
			}
		} else {
			return false;
		}
	}
	

	public Article findArticle(int art_id) {
		Article article = articleDao.findArticleById(art_id);
		article.setComments(commentDao.loadComments(art_id));
		return article;
	}
	

	public int findLastInsertArticleId() {
		return articleDao.findLastInsert().getArt_id();
	}


	public List<Article> findByKeywords(String key) {
		
		List<Article> contentList = articleDao.findArticleByContent(key);
		List<Article> nameList = articleDao.findArticleByName(key);

		contentList.removeAll(nameList);// 去重
		nameList.addAll(contentList);// 求并集
		return nameList;
	}
	public List<Article> getArticlesWithPages(int size) {
		return articleDao.findArticlesWithPage(size);
	}
}
