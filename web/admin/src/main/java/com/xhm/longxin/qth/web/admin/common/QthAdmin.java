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

package com.xhm.longxin.qth.web.admin.common;

import static com.alibaba.citrus.util.ObjectUtil.*;

import java.io.Serializable;

public class QthAdmin implements Serializable {
    private static final long                      serialVersionUID = -7507510429733333544L;
    private static final ThreadLocal<QthAdmin> adminHolder       = new ThreadLocal<QthAdmin>();
    private String  userId;
    private String 	userName;

    public static final QthAdmin getCurrentUser() {
    	QthAdmin qthAdmin = adminHolder.get();
    	return qthAdmin;
    }

    public static final void setCurrentUser(QthAdmin user) {
    	adminHolder.set(user);
    }


    public QthAdmin() {
    }

    
    public QthAdmin(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return userId;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void upgrade(){
        this.setCurrentUser(this);
    }

    public void upgrade(String userId,String userName) {
//        assertTrue(!hasLoggedIn(), ExceptionType.ILLEGAL_STATE, "already logged in");

//        userId = assertNotNull(userId, "no user id");

        this.userId = userId;
        this.userName = userName;
        this.setCurrentUser(this);
    }

    public boolean hasLoggedIn() {
        boolean result =  userId != null;
        return result;
    }

    @Override
    public String toString() {
        return "qthAdmin[" + defaultIfNull(userName, "anonymous")  + "]";
    }
}
