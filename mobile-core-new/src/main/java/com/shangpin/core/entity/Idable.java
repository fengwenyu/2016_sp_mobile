/**
 * <pre>
 * Date:			2013年10月17日
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Description:
 * </pre>
 **/
 
package com.shangpin.core.entity;

import java.io.Serializable;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * @since   2013年10月17日 下午4:13:13 
 */

public interface Idable<T extends Serializable> {
	public T getId();

	public void setId(T id);
}
