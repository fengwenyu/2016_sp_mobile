package com.shangpin.core.entity;

// Generated 2014-5-26 18:24:56 

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Weixinquestions 
 */
@Entity
@Table(name = "weixinquestions")
public class WeixinQuestions implements java.io.Serializable {

    private static final long serialVersionUID = -2629462510216355951L;
    private Long id;
    private String issueId;
    private String questionId;
    private String questionNextId;
    private String questionBody;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private String option6;
    private String option7;
    private Integer score;
    private Integer isFinalReply;
    private Date createTime;
    private String reserve0;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private String reserve4;
    private String reserve5;

    public WeixinQuestions() {
    }

    public WeixinQuestions(String issueId, String questionId, String questionNextId, String questionBody, String option1, String option2, String option3, String option4,
            String option5, String option6, String option7, Integer score, Integer isFinalReply, Date createTime, String reserve0, String reserve1, String reserve2,
            String reserve3, String reserve4, String reserve5) {
        this.issueId = issueId;
        this.questionId = questionId;
        this.questionNextId = questionNextId;
        this.questionBody = questionBody;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.option6 = option6;
        this.option7 = option7;
        this.score = score;
        this.isFinalReply = isFinalReply;
        this.createTime = createTime;
        this.reserve0 = reserve0;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
        this.reserve3 = reserve3;
        this.reserve4 = reserve4;
        this.reserve5 = reserve5;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "issueId", length = 20)
    public String getIssueId() {
        return this.issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    @Column(name = "questionId", length = 20)
    public String getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Column(name = "questionNextId", length = 20)
    public String getQuestionNextId() {
        return this.questionNextId;
    }

    public void setQuestionNextId(String questionNextId) {
        this.questionNextId = questionNextId;
    }

    @Column(name = "questionBody", length = 5000)
    public String getQuestionBody() {
        return this.questionBody;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    @Column(name = "option1", length = 100)
    public String getOption1() {
        return this.option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    @Column(name = "option2", length = 100)
    public String getOption2() {
        return this.option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    @Column(name = "option3", length = 100)
    public String getOption3() {
        return this.option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    @Column(name = "option4", length = 100)
    public String getOption4() {
        return this.option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    @Column(name = "option5", length = 100)
    public String getOption5() {
        return this.option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    @Column(name = "option6", length = 100)
    public String getOption6() {
        return this.option6;
    }

    public void setOption6(String option6) {
        this.option6 = option6;
    }

    @Column(name = "option7", length = 100)
    public String getOption7() {
        return this.option7;
    }

    public void setOption7(String option7) {
        this.option7 = option7;
    }

    @Column(name = "score")
    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Column(name = "isFinalReply")
    public Integer getIsFinalReply() {
        return this.isFinalReply;
    }

    public void setIsFinalReply(Integer isFinalReply) {
        this.isFinalReply = isFinalReply;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createTime", length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "reserve0")
    public String getReserve0() {
        return this.reserve0;
    }

    public void setReserve0(String reserve0) {
        this.reserve0 = reserve0;
    }

    @Column(name = "reserve1")
    public String getReserve1() {
        return this.reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    @Column(name = "reserve2")
    public String getReserve2() {
        return this.reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    @Column(name = "reserve3")
    public String getReserve3() {
        return this.reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    @Column(name = "reserve4")
    public String getReserve4() {
        return this.reserve4;
    }

    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }

    @Column(name = "reserve5")
    public String getReserve5() {
        return this.reserve5;
    }

    public void setReserve5(String reserve5) {
        this.reserve5 = reserve5;
    }

}
