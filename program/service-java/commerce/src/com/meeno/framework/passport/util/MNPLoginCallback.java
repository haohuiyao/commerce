package com.meeno.framework.passport.util;

import com.meeno.framework.passport.entity.MNPLoginRecord;
import com.meeno.framework.user.entity.BaseUser;

/**
*
* @description 登录回调
* @time 2017年7月21日 上午11:27:56
* @author Saturn
* @version 1.0
*
*/
public interface MNPLoginCallback {
	
	/**
	 * @Description: 登录成功
	 * @param loginRecord
	 * @param baseUser
	 * @return void
	 * @author Saturn
	 * @date 2017年7月21日上午11:28:33
	 */
	void onSuccess(MNPLoginRecord loginRecord, BaseUser baseUser);
}
