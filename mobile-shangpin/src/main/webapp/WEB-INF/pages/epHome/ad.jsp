<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<c:forEach var="adver" items="${advers}">
	<c:choose>
		<c:when test="${adver.type == '1'}">
			<c:if test="${fn:length(adver.list) > 0}">
				<c:forEach var="ad" items="${adver.list}">
					<div class="one-list">
						<c:choose>
							<c:when test="${ad.type == '0'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=${ad.avderName}&activityid=${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a>
									</c:when>
									<c:otherwise>
										<a href="${ctx}/subject/product/list?topicId=${ad.refContent}&postArea=2"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '3'}">
								<c:choose>
									<c:when test="${checkAPP}">
									
										<a href="shangpinapp://phone.shangpin/actiongocatelist?title=${ad.avderName}&filters=category_${ad.refContent}&categoryid=${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a>
									</c:when>
									<c:otherwise>
										<a href="${ctx}/category/product/list?categoryNo=${ad.refContent}&postArea=2"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '2'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<a href="shangpinapp://phone.shangpin/actiongobrandlist?title=${ad.avderName}&filters=brand_${ad.refContent}&brandid=${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a>
									</c:when>
									<c:otherwise>
										<a href="{ctx}/brand/product/list?brandNo=${ad.refContent}&postArea=2"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '4'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<a href="shangpinapp://phone.shangpin/actiongodetail?title=${ad.avderName}&productid=${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a>
									</c:when>
									<c:otherwise>
										<a href="${ctx}/product/detail?productNo=${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '1'}">
								<a href="${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a>
							</c:when>
							<c:when test="${ad.type == '5'}">
								<a href="${ctx}/meet/${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a>
							</c:when>
							<c:when test="${ad.type == '14'}">
								<c:choose>
									<c:when test="${checkAPP&&isTag=='1'}">
										<a href="shangpinapp://phone.shangpin/actiongotaglist?title=${ad.avderName}&tagid=${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a>
									</c:when>
									<c:otherwise>
										<a href="${ctx }/lable/product/list?tagId=${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a>
									</c:otherwise>
								</c:choose>
							</c:when>
						</c:choose>
					</div>
				</c:forEach>
			</c:if>
		</c:when>
		<c:when test="${adver.type == '2'}">
			<c:if test="${fn:length(adver.list) > 0}">
				<ul class="two-list clr">
					<c:forEach var="ad" items="${adver.list}">
						<c:choose>
							<c:when test="${ad.type == '0'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=${ad.avderName}&activityid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/subject/product/list?topicId=${ad.refContent}&postArea=2"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '3'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongocatelist?title=${ad.avderName}&filters=category_${ad.refContent}&categoryid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/category/product/list?categoryNo=${ad.refContent}&postArea=2"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '2'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongobrandlist?title=${ad.avderName}&filters=brand_${ad.refContent}&brandid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/brand/product/list?brandNo=${ad.refContent}&postArea=2"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '4'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=${ad.avderName}&productid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/product/detail?productNo=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '1'}">
								<li><a href="${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
							</c:when>
							<c:when test="${ad.type == '5'}">
								<li><a href="${ctx}/meet/${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
							</c:when>
							<c:when test="${ad.type == '14'}">
								<c:choose>
									<c:when test="${checkAPP&&isTag=='1'}">
										<li><a href="shangpinapp://phone.shangpin/actiongotaglist?title=${ad.avderName}&tagid=${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx }/lable/product/list?tagId=${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
						</c:choose>
					</c:forEach>
				</ul>
			</c:if>
		</c:when>
		<c:when test="${adver.type == '3'}">
			<c:if test="${fn:length(adver.list) > 0}">
				<ul class="three-list clr">
					<c:forEach var="ad" items="${adver.list}">
					  <c:choose>
							<c:when test="${ad.type == '0'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=${ad.avderName}&activityid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/subject/product/list?topicId=${ad.refContent}&postArea=2"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '3'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongocatelist?title=${ad.avderName}&filters=category_${ad.refContent}&categoryid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/category/product/list?categoryNo=${ad.refContent}&postArea=2"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '2'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongobrandlist?title=${ad.avderName}&filters=brand_${ad.refContent}&brandid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/brand/product/list?brandNo=${ad.refContent}&postArea=2"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '4'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=${ad.avderName}&productid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/product/detail?productNo=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '1'}">
								<li><a href="${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
							</c:when>
							<c:when test="${ad.type == '5'}">
								<li><a href="${ctx}/meet/${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
							</c:when>
							<c:when test="${ad.type == '14'}">
								<c:choose>
									<c:when test="${checkAPP&&isTag=='1'}">
										<li><a href="shangpinapp://phone.shangpin/actiongotaglist?title=${ad.avderName}&tagid=${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx }/lable/product/list?tagId=${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
						</c:choose>
				</c:forEach>
				</ul>
			</c:if>
		</c:when>
		<c:when test="${adver.type == '4'}">
			<c:if test="${fn:length(adver.list) > 0}">
				<ul class="four-list clr">
					<c:forEach var="ad" items="${adver.list}">
						<c:choose>
							<c:when test="${ad.type == '0'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=${ad.avderName}&activityid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/subject/product/list?topicId=${ad.refContent}&postArea=2"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '3'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongocatelist?title=${ad.avderName}&filters=category_${ad.refContent}&categoryid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/category/product/list?categoryNo=${ad.refContent}&postArea=2"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '2'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongobrandlist?title=${ad.avderName}&filters=brand_${ad.refContent}&brandid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/brand/product/list?brandNo=${ad.refContent}&postArea=2"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '4'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=${ad.avderName}&productid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/product/detail?productNo=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '1'}">
								<li><a href="${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
							</c:when>
							<c:when test="${ad.type == '5'}">
								<li><a href="${ctx}/meet/${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
							</c:when>
								<c:when test="${ad.type == '14'}">
								<c:choose>
									<c:when test="${checkAPP&&isTag=='1'}">
										<li><a href="shangpinapp://phone.shangpin/actiongotaglist?title=${ad.avderName}&tagid=${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx }/lable/product/list?tagId=${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
						</c:choose>
					</c:forEach>
				</ul>
			</c:if>
		</c:when>
		<c:when test="${adver.type == '5'}">
			<c:if test="${fn:length(adver.list) > 0}">
				<div class="one-two-list">
					<c:choose>
						<c:when test="${adver.list[0].type == '0'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=${adver.list[0].avderName}&activityid=${adver.list[0].refContent}" class="left-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/subject/product/list?topicId=${adver.list[0].refContent}&postArea=2" class="left-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[0].type == '3'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongocatelist?title=${adver.list[0].avderName}&filters=category_${adver.list[0].refContent}&categoryid=${adver.list[0].refContent}" class="left-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/category/product/list?categoryNo=${adver.list[0].refContent}&postArea=2" class="left-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[0].type == '2'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongobrandlist?title=${adver.list[0].avderName}&filters=brand_${adver.list[0].refContent}&brandid=${adver.list[0].refContent}" class="left-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/brand/product/list?brandNo=${adver.list[0].refContent}&postArea=2" class="left-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[0].type == '4'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongodetail?title=${adver.list[0].avderName}&productid=${adver.list[0].refContent}" class="left-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/product/detail?productNo=${adver.list[0].refContent}&postArea=2" class="left-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[0].type == '1'}">
							<a href="${adver.list[0].refContent}" class="left-floor"><img alt="" src="${adver.list[0].picUrl}"/></a>
						</c:when>
						<c:when test="${adver.list[0].type == '5'}">
							<a href="${ctx}/meet/${adver.list[0].refContent}" class="left-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
						</c:when>
						<c:when test="${adver.list[0].type == '14'}">
								<c:choose>
									<c:when test="${checkAPP&&isTag=='1'}">
										<a href="shangpinapp://phone.shangpin/actiongotaglist?title=${adver.list[0].avderName}&tagid=${adver.list[0].refContent}" class="left-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}" width="100%"></a>
									</c:when>
									<c:otherwise>
										<a href="${ctx }/lable/product/list?tagId=${adver.list[0].refContent}" class="left-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}" width="100%"></a>
									</c:otherwise>
								</c:choose>
						</c:when>
					</c:choose>
					
					<c:choose>
						<c:when test="${adver.list[1].type == '0'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=${adver.list[1].avderName}&activityid=${adver.list[1].refContent}" class="right-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/subject/product/list?topicId=${adver.list[1].refContent}&postArea=2" class="right-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[1].type == '3'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongocatelist?title=${adver.list[1].avderName}&filters=category_${adver.list[1].refContent}&categoryid=${adver.list[1].refContent}" class="right-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/category/product/list?categoryNo=${adver.list[1].refContent}&postArea=2" class="right-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[1].type == '2'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongobrandlist?title=${adver.list[1].avderName}&filters=brand_${adver.list[1].refContent}&brandid=${adver.list[1].refContent}" class="right-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/brand/product/list?brandNo=${adver.list[1].refContent}&postArea=2" class="right-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[1].type == '4'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongodetail?title=${adver.list[1].avderName}&productid=${adver.list[1].refContent}" class="right-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/product/detail?productNo=${adver.list[1].refContent}&postArea=2" class="right-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[1].type == '1'}">
							<a href="${adver.list[1].refContent}" class="right-up-floor"><img alt="" src="${adver.list[1].picUrl}"/></a>
						</c:when>
						<c:when test="${adver.list[1].type == '5'}">
							<a href="${ctx}/meet/${adver.list[1].refContent}" class="right-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
						</c:when>
						<c:when test="${adver.list[1].type == '14'}">
								<c:choose>
									<c:when test="${checkAPP&&isTag=='1'}">
										<a href="shangpinapp://phone.shangpin/actiongotaglist?title=${adver.list[1].avderName}&tagid=${adver.list[1].refContent}" class="right-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}" width="100%"></a>
									</c:when>
									<c:otherwise>
										<a href="${ctx }/lable/product/list?tagId=${adver.list[1].refContent}" class="right-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}" width="100%"></a>
									</c:otherwise>
								</c:choose>
						</c:when>
					</c:choose>
					
					<c:choose>
						<c:when test="${adver.list[2].type == '0'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=${adver.list[2].avderName}&activityid=${adver.list[2].refContent}" class="right-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/subject/product/list?topicId=${adver.list[2].refContent}&postArea=2" class="right-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[2].type == '3'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongocatelist?title=${adver.list[2].avderName}&filters=category_${adver.list[2].refContent}&categoryid=${adver.list[2].refContent}" class="right-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/category/product/list?categoryNo=${adver.list[2].refContent}&postArea=2" class="right-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[2].type == '2'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongobrandlist?title=${adver.list[2].avderName}&filters=brand_${adver.list[2].refContent}&brandid=${adver.list[2].refContent}" class="right-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/brand/product/list?brandNo=${adver.list[2].refContent}&postArea=2" class="right-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[2].type == '4'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongodetail?title=${adver.list[2].avderName}&productid=${adver.list[2].refContent}" class="right-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/product/detail?productNo=${adver.list[2].refContent}&postArea=2" class="right-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[2].type == '1'}">
							<a href="${adver.list[2].refContent}" class="right-down-floor"><img alt="" src="${adver.list[2].picUrl}"/></a>
						</c:when>
						<c:when test="${adver.list[2].type == '5'}">
							<a href="${ctx}/meet/${adver.list[2].refContent}" class="right-down-floor"><img alt="" src="${adver.list[2].picUrl}"/></a>
						</c:when>
						<c:when test="${adver.list[2].type == '14'}">
								<c:choose>
									<c:when test="${checkAPP&&isTag=='1'}">
										<a href="shangpinapp://phone.shangpin/actiongotaglist?title=${adver.list[2].avderName}&tagid=${adver.list[2].refContent}" class="right-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}" width="100%"></a>
									</c:when>
									<c:otherwise>
										<a href="${ctx }/lable/product/list?tagId=${adver.list[2].refContent}" class="right-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}" width="100%"></a>
									</c:otherwise>
								</c:choose>
						</c:when>
					</c:choose>
				</div>
			</c:if>
		</c:when>
		<c:when test="${adver.type == '6'}">
			<c:if test="${fn:length(adver.list) > 0}">
				<div class="two-one-list">
					<c:choose>
						<c:when test="${adver.list[0].type == '0'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=${adver.list[0].avderName}&activityid=${adver.list[0].refContent}" class="left-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/subject/product/list?topicId=${adver.list[0].refContent}&postArea=2" class="left-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[0].type == '3'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongocatelist?title=${adver.list[0].avderName}&filters=category_${adver.list[0].refContent}&categoryid=${adver.list[0].refContent}" class="left-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/category/product/list?categoryNo=${adver.list[0].refContent}&postArea=2" class="left-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[0].type == '2'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongobrandlist?title=${adver.list[0].avderName}&filters=brand_${adver.list[0].refContent}&brandid=${adver.list[0].refContent}" class="left-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/brand/product/list?brandNo=${adver.list[0].refContent}&postArea=2" class="left-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[0].type == '4'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongodetail?title=${adver.list[0].avderName}&productid=${adver.list[0].refContent}" class="left-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/product/detail?productNo=${adver.list[0].refContent}&postArea=2" class="left-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[0].type == '1'}">
							<a href="${adver.list[0].refContent}" class="left-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
						</c:when>
						<c:when test="${adver.list[0].type == '5'}">
							<a href="${ctx}/meet/${adver.list[0].refContent}" class="left-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}"/></a>
						</c:when>
						<c:when test="${adver.list[0].type == '14'}">
								<c:choose>
									<c:when test="${checkAPP&&isTag=='1'}">
										<a href="shangpinapp://phone.shangpin/actiongotaglist?title=${adver.list[0].avderName}&tagid=${adver.list[0].refContent}" class="left-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}" width="100%"></a>
									</c:when>
									<c:otherwise>
										<a href="${ctx }/lable/product/list?tagId=${adver.list[0].refContent}" class="left-up-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[0].picUrl}" width="100%"></a>
									</c:otherwise>
								</c:choose>
						</c:when>
					</c:choose>
					
					<c:choose>
						<c:when test="${adver.list[2].type == '0'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=${adver.list[2].avderName}&activityid=${adver.list[2].refContent}" class="right-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/subject/product/list?topicId=${adver.list[2].refContent}&postArea=2" class="right-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[2].type == '3'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongocatelist?title=${adver.list[2].avderName}&filters=category_${adver.list[2].refContent}&categoryid=${adver.list[2].refContent}" class="right-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/category/product/list?categoryNo=${adver.list[2].refContent}&postArea=2" class="right-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[2].type == '2'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongobrandlist?title=${adver.list[2].avderName}&filters=brand_${adver.list[2].refContent}&brandid=${adver.list[2].refContent}" class="right-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/brand/product/list?brandNo=${adver.list[2].refContent}&postArea=2" class="right-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[2].type == '4'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongodetail?title=${adver.list[2].avderName}&productid=${adver.list[2].refContent}" class="right-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/product/detail?productNo=${adver.list[2].refContent}&postArea=2" class="right-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[2].type == '1'}">
							<a href="${adver.list[2].refContent}" class="right-floor"><img alt="" src="${adver.list[2].picUrl}"/></a>
						</c:when>
						<c:when test="${adver.list[2].type == '5'}">
							<a href="${ctx}/meet/${adver.list[2].refContent}" class="right-floor"><img alt="" src="${adver.list[2].picUrl}"/></a>
						</c:when>
						<c:when test="${adver.list[2].type == '14'}">
								<c:choose>
									<c:when test="${checkAPP&&isTag=='1'}">
										<a href="shangpinapp://phone.shangpin/actiongotaglist?title=${adver.list[2].avderName}&tagid=${adver.list[2].refContent}" class="right-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}" width="100%"></a>
									</c:when>
									<c:otherwise>
										<a href="${ctx }/lable/product/list?tagId=${adver.list[2].refContent}" class="right-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[2].picUrl}" width="100%"></a>
									</c:otherwise>
								</c:choose>
						</c:when>
					</c:choose>
					
					<c:choose>
						<c:when test="${adver.list[1].type == '0'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=${adver.list[1].avderName}&activityid=${adver.list[1].refContent}" class="left-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/subject/product/list?topicId=${adver.list[1].refContent}&postArea=2" class="left-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[1].type == '3'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongocatelist?title=${adver.list[1].avderName}&filters=category_${adver.list[1].refContent}&categoryid=${adver.list[1].refContent}" class="left-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/category/product/list?categoryNo=${adver.list[1].refContent}&postArea=2" class="left-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[1].type == '2'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongobrandlist?title=${adver.list[1].avderName}&filters=brand_${adver.list[1].refContent}&brandid=${adver.list[1].refContent}" class="left-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/brand/product/list?brandNo=${adver.list[1].refContent}&postArea=2" class="left-down-floor"><img alt="" src="${adver.list[1].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[1].type == '4'}">
							<c:choose>
								<c:when test="${checkAPP}">
									<a href="shangpinapp://phone.shangpin/actiongodetail?title=${adver.list[1].avderName}&productid=${adver.list[2].refContent}" class="left-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
								</c:when>
								<c:otherwise>
									<a href="${ctx}/product/detail?productNo=${adver.list[1].refContent}&postArea=2" class="left-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${adver.list[1].type == '1'}">
							<a href="${adver.list[1].refContent}" class="left-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
						</c:when>
						<c:when test="${adver.list[1].type == '5'}">
							<a href="${ctx}/meet/${adver.list[1].refContent}" class="left-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}"/></a>
						</c:when>
						<c:when test="${adver.list[1].type == '14'}">
								<c:choose>
									<c:when test="${checkAPP&&isTag=='1'}">
										<a href="shangpinapp://phone.shangpin/actiongotaglist?title=${adver.list[1].avderName}&tagid=${adver.list[1].refContent}" class="left-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}" width="100%"></a>
									</c:when>
									<c:otherwise>
										<a href="${ctx }/lable/product/list?tagId=${adver.list[1].refContent}" class="left-down-floor"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${adver.list[1].picUrl}" width="100%"></a>
									</c:otherwise>
								</c:choose>
						</c:when>
					</c:choose>
				</div>
			</c:if>
		</c:when>
		<c:when test="${adver.type == '7'}">
			<c:if test="${fn:length(adver.list) > 0}">
				<ul class="five-list clr">
					<c:forEach var="ad" items="${adver.list}">
						<c:choose>
							<c:when test="${ad.type == '0'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongoactivitylist?title=${ad.avderName}&activityid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/subject/product/list?topicId=${ad.refContent}&postArea=2"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '3'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongocatelist?title=${ad.avderName}&filters=category_${ad.refContent}&categoryid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/category/product/list?categoryNo=${ad.refContent}&postArea=2"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '2'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongobrandlist?title=${ad.avderName}&filters=brand_${ad.refContent}&brandid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/brand/product/list?brandNo=${ad.refContent}&postArea=2"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '4'}">
								<c:choose>
									<c:when test="${checkAPP}">
										<li><a href="shangpinapp://phone.shangpin/actiongodetail?title=${ad.avderName}&productid=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx}/product/detail?productNo=${ad.refContent}"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${ad.type == '1'}">
								<li><a href="${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
							</c:when>
							<c:when test="${ad.type == '5'}">
								<li><a href="${ctx}/meet/${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
							</c:when>
								<c:when test="${ad.type == '14'}">
								<c:choose>
									<c:when test="${checkAPP&&isTag=='1'}">
										<li><a href="shangpinapp://phone.shangpin/actiongotaglist?title=${ad.avderName}&tagid=${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${ctx }/lable/product/list?tagId=${ad.refContent}"><img alt="" src="${cdn:pic(pageContext.request)}/styles/shangpin/images/e.gif" lazy="${ad.picUrl}" width="100%"></a></li>
									</c:otherwise>
								</c:choose>
							</c:when>
						</c:choose>
					</c:forEach>
				</ul>
			</c:if>
		</c:when>
		<c:otherwise>
			
		</c:otherwise>
	</c:choose>
</c:forEach>

