package cn.sysu.comm.dao;

import java.util.List;

import cn.sysu.comm.entity.Article;
import cn.sysu.comm.entity.Question;

/*
author: kingchiu
 */


public interface QuestionMapper {
	public List<Question> findQuestionByName(String question);
	public List<Question> findQuestionByContent(String content);
	public List<Question> findQuestionsByAuthorId(String authorId);
	public Question findQuetionById(int quesId);
	/*
	 * 增加问题
	 * 传入参数为一个Question对象
	 */
	public void addQuestion(Question question);
	/*
	 * 通过quesId删除问题
	 */
	public void deleteQuestion(int quesId);
	/*
	 * 更新文章
	 * 传入参数为一个Question问题
	 */
	public void updateQuestion(Question question);
	public int findLastInsert();
	public List<Question> findQuestionsWithPage(int size);
}
