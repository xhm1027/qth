/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xhm.longxin.qth.web.user.common;

import static com.alibaba.citrus.util.ObjectUtil.*;

import java.io.Serializable;

public class QthUser implements Serializable {
    private static final long                      serialVersionUID = -7507510429755782596L;
    private static final ThreadLocal<QthUser> userHolder       = new ThreadLocal<QthUser>();
    private Long  userId;
    private String 	userName;
    private String 	userType;

    public static final QthUser getCurrentUser() {
    	QthUser qthUser = userHolder.get();
    	return qthUser;
    }

    public static final void setCurrentUser(QthUser user) {
        userHolder.set(user);
    }

    /** 创建匿名用户。 */
    public QthUser() {
    }

    /** 创建用户。 */
    public QthUser(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return userId;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void upgrade(Long userId,String userName, String userType) {
//        assertTrue(!hasLoggedIn(), ExceptionType.ILLEGAL_STATE, "already logged in");

//        userId = assertNotNull(userId, "no user id");

        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.setCurrentUser(this);
    }

    public boolean hasLoggedIn() {
        return userId != null;
    }

    @Override
    public String toString() {
        return "QthUser[" + defaultIfNull(userName, "anonymous") + ", type=" + defaultIfNull(userType, "anonymous") + "]";
    }
}
