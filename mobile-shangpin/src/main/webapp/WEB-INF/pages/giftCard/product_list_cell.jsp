<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

	<c:forEach var="giftCard" items="${giftCardList }">
                  <div class="list_box">
                      <a href="${ctx }/giftCard/cardDetail?productNo=${giftCard.productId}&type=${giftCardProductList.type}&picNo=${giftCard.picNo}">
                        <img src="${cdn:js(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${fn:replace(giftCard.pic,'-10-10','-600-758') }" />
                        <div class="li_text">
                          <h5>${giftCard.brandNameCN }</h5>
                          <p>${giftCard.productName }</p>
                          <span>
                              <strong class="red">&yen;${giftCard.marketPrice }</strong>
                          </span>
                        </div>
                      </a>
                  </div>
	</c:forEach>
	

