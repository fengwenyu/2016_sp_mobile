package com.shangpin.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.ITokenDAO;
import com.shangpin.core.entity.Token;
import com.shangpin.core.service.ITokenService;

@Service
@Transactional
public class TokenServiceImpl implements ITokenService {
    protected final Log log = LogFactory.getLog(TokenServiceImpl.class);
    @Autowired
    private ITokenDAO dao;

    @Override
    public Token add(Token token) {
        return dao.save(token);
    }

    @Override
    public Token modify(Token token) {
        return dao.save(token);
    }

    @Override
    public void deleteById(Long id) {
        dao.delete(id);
    }

    @Override
    public Token findById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public Token findByStatus(String status) {
        List<Token> list = dao.findByStatus(status);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public synchronized void resetTokenStatus() {
        try {
            Token token = new Token();
            List<Token> list = dao.findAll();
            if (list != null && list.size() > 0) {
                token = (Token) list.get(0);
                token.setStatus("normal");
                token.setLastUpdated(new Date());
                modify(token);
                // 保存日志
                StringBuffer sb = new StringBuffer();
                sb.append("服务器:").append("XXX")// 待修改SERVER_IP
                        .append(" 于").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("重置了Token的争抢！");
                log.info(sb.toString());
            }
        } catch (Exception e) {
            log.error("resetTokenStatus :", e);
        }
    }

    @Override
    public Boolean isInvalidCode(String code) {
        List<Token> list = dao.findByCodeAndEfftimeGtNow(code);
        if (list != null && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
