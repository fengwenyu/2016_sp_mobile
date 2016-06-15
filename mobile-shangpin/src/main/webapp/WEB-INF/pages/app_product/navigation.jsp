<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<div class="product_info">
        <div class="content_info">
          <c:choose>
          	<c:when test="${type=='1'}">
          	   <div class="content_detail content_list">
	            <ul class="info_base">
		             <c:forEach var="info" items="${productDetail.basic.info }">
		             <c:choose>
			              <c:when test="${fn:indexOf(info,':')>-1 }">
				             <li><span><strong>${fn:substring(info,0,fn:indexOf(info,":")) }</strong>：</span>${fn:substring(info,fn:indexOf(info,":")+1,fn:length(info)) }&nbsp;</li>
				         </c:when>
			            <c:when test="${fn:indexOf(info,'：')>-1 }">
			              <li><span><strong>${fn:substring(info,0,fn:indexOf(info,"：")) }</strong>：</span>${fn:substring(info,fn:indexOf(info,"：")+1,fn:length(info)) }&nbsp;</li>
			            </c:when>
			          	<c:otherwise>
			          	 <li><span><strong>${fn:substring(info,0,fn:indexOf(info,":")) }</strong>：</span>${fn:substring(info,fn:indexOf(info,":")+1,fn:length(info)) }&nbsp;</li>
			          	</c:otherwise>
		             </c:choose>
		           	</c:forEach>
	            </ul>
	       	   ${productDetail.basic.recommend }
	       	   <div>
		      		<div>
			    	<c:forEach var="pic" items="${productDetail.basic.allPics}">
					          <img src="${fn:replace(pic,'-10-10','-600-758')}">
			        </c:forEach>
		   		   </div>
    			</div>
	          </div>
	         </c:when>
          	<c:when test="${type=='2'}">
          	   <div class="content_size content_list nobottom" id="tabs_txt0">
          	   <c:if test="${fn:length(productSize.sizeContent) > 0 }">
          	   <c:set var="zero" value="true" />
          	      ${productSize.sizeContent}
          	   </c:if>
          	   <c:choose>
	          	 <c:when test="${productSize!=null }">
			          	<c:if test="${fn:length(productSize.productDetailSize.table) > 0  }">
			           <c:forEach var="group" items="${groups}">
			            <table>
				            	<c:forEach var="size" items="${productSize.productDetailSize.table }" varStatus="status" >
					            	<c:choose>
					            		<c:when test="${status.index==0 }">
					            		 <thead>
							                <tr>
							                  <td class="left">${size.name } </td>
							                   <c:set var="len" value="0" />
							                  <c:forEach var="tableValue" items="${size.value }" begin="${group * 4}" end="${group + 3*(group+1)}">
							                   	 <td>${tableValue }</td>
							                   	  <c:set var="len" value="${len*1+1 }" />
							                  </c:forEach>
							                  <c:if test="${len*1 ==1  }">
								                  <td> </td>
								                  <td></td>
								                  <td></td>
							                  </c:if>
							                   <c:if test="${len*1 == 2  }">
								                  <td></td>
								                  <td></td>
							                  </c:if>
							                   <c:if test="${len*1 == 3  }">
								                  <td></td>
							                  </c:if>
							                </tr>
							              </thead> 
					            		</c:when>
					            		<c:otherwise>
					            		  <tbody>
					               			 <tr>  
							                 <td class="left">${size.name }</td>
							                 <c:set var="len1" value="0" />
							                  <c:forEach var="tableValue" items="${size.value }" begin="${group * 4}" end="${group + 3*(group+1)}">
							                   	 <td>${tableValue }</td>
							                   	 <c:set var="len1" value="${len1*1+1 }" />
							                  </c:forEach>
							                
							                  <c:if test="${len1*1 ==1  }">
								                  <td></td>
								                  <td></td>
								                  <td></td>
							                  </c:if>
							                   <c:if test="${len1*1 == 2  }">
								                  <td></td>
								                  <td></td>
							                  </c:if>
							                   <c:if test="${len1*1 == 3  }">
								                  <td></td>
							                  </c:if>
					               			 </tr>
					             			 </tbody>
					            		</c:otherwise>
					            	</c:choose>
					            
					            	
			            	</c:forEach>
			            	</table>
			            </c:forEach>
						 
					</c:if>	
				
					<c:if test="${fn:length(productSize.property)>0}">
					<c:set var="second" value="true" />
			            <div class="commodity_index">
			              <h6>商品指数</h6>
			              <c:forEach var="property" items="${productSize.property }">
				              <dl>
				                <dt>${fn:substring(property.title,0,fn:indexOf(property.title,":")) }：</dt>
				                <dd>
				                    <ul>
				                    
				                      <c:forEach var="propertyValue" items="${property.values }" varStatus="status">
					                       
					                        <c:choose>
					                        	<c:when test="${property.current==status.index }">
					                        	<li class="curr">
					                        	</c:when>
					                        	<c:otherwise>
					                        	<li>
					                        	</c:otherwise>
					                        </c:choose>
						                            <i></i>
						                            <span>${propertyValue }</span>
					                      		</li>
					                     
				                        </c:forEach>
				                    </ul>
				                </dd>
				              </dl>
			              </c:forEach>
			            </div>
			        </c:if>
			     
			        <c:if test="${productSize.modelClothPic!=null&&productSize.modelClothPic!=''}">
				        <c:set var="thrid" value="true" />
				       	 <img alt="尺寸测量方法" src="${fn:replace(productSize.modelClothPic,'-10-10','-350-280')}">
			        </c:if>
			        
			        <c:if test="${!zero&&!first&&!second&&!thrid}">
				       	<div class="no_size">
	                		<p>暂无尺码说明</p>
	             		</div>
			        </c:if>
		 
		       	</c:when>
		       	<c:otherwise>
		       	 <div class="no_comment" style="background-image:none;"><p style="padding-top:120px;">暂无尺码</p></div>
		       	</c:otherwise>
	       	</c:choose>
	     
	        </div>
	           
          </c:when>
          <c:when test="${type=='3'}">
            <div class="content_rand content_list nobottom" id="tabs_txt1" >
        	 ${productTemplate.html }
         	 </div>
          </c:when>
           <c:when test="${type=='6'}">
	             <div class="content_size content_list">
		       	   <c:choose>
		       	   <c:when test="${productDetail.basic.recommend==''||productDetail.basic.recommend==null}">
		       	    	<c:forEach var="pic" items="${productDetail.basic.allPics}"  varStatus="status">
		       	   		<c:choose>
		       	   			<c:when test="${status.index*1<3 }">
		       	   			<img src="${fn:replace(pic,'-10-10','-600-758')}" >
		       	   			</c:when>
		       	   			<c:otherwise>
		       	   				<img lazy="${fn:replace(pic,'-10-10','-600-758')}" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" >
		       	   			</c:otherwise>
		       	   		</c:choose>
				     </c:forEach>
		       	   </c:when>
		       	   <c:otherwise>
				        ${productDetail.basic.recommend }
		       	        <div>
			      		<div>
				    	<c:forEach var="pic" items="${productDetail.basic.allPics}">
				    	          <img lazy="${fn:replace(pic,'-10-10','-600-758')}" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" >
				        </c:forEach>
			   		   </div>
	    			</div>
		       	   </c:otherwise>
		       	   </c:choose>
		       </div>  	 
          </c:when>
          <c:otherwise></c:otherwise>
       </c:choose>
	</div>
 
