package com.shangpin.wireless.api.domain;

import java.util.Date;

/**
 * 微信的活动的题目表
 * 
 * @Author: wangwenguan
 * @CreateDate: 2013-07-28
 */
public class WeixinQuestions implements java.io.Serializable {

	private static final long serialVersionUID = 100L;
	private long id;
	private String issueId; // 活动期号
	private String questionId; // 问题编号
	private String questionNextId; // 下一问题的编号(性格测试类：因答题顺序是跳跃的，为必填项；其他类型可不填，默认为下一编号)
	private String questionBody; // 问题内容
	private String option1; // 选项1 // 最大支持7个备选项
	private String option2; // 选项2
	private String option3; // 选项3
	private String option4; // 选项4
	private String option5; // 选项5
	private String option6; // 选项6
	private String option7; // 选项7
	private int score; // 分值
	private int isFinalReply; // 是否为最终回复内容。最终回复时，显示内容为最终结果，无需用户选择，本次答题结束(可将查询成绩指令在此告知用户)
	private Date createTime; // 创建时间
	private String reserve0;// 备用字段
	private String reserve1;// 备用字段
	private String reserve2;// 备用字段
	private String reserve3;// 备用字段
	private String reserve4;// 备用字段
	private String reserve5;// 备用字段

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIssueId() {
		return issueId;
	}

	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionNextId() {
		return questionNextId;
	}

	public void setQuestionNextId(String questionNextId) {
		this.questionNextId = questionNextId;
	}

	public String getQuestionBody() {
		return questionBody;
	}

	public void setQuestionBody(String questionBody) {
		this.questionBody = questionBody;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getOption5() {
		return option5;
	}

	public void setOption5(String option5) {
		this.option5 = option5;
	}

	public String getOption6() {
		return option6;
	}

	public void setOption6(String option6) {
		this.option6 = option6;
	}

	public String getOption7() {
		return option7;
	}

	public void setOption7(String option7) {
		this.option7 = option7;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getIsFinalReply() {
		return isFinalReply;
	}

	public void setIsFinalReply(int isFinalReply) {
		this.isFinalReply = isFinalReply;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getReserve0() {
		return reserve0;
	}

	public void setReserve0(String reserve0) {
		this.reserve0 = reserve0;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

	public void setId(long id) {
		this.id = id;
	}

}
