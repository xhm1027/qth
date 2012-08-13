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

import static com.alibaba.citrus.turbine.util.TurbineUtil.getTurbineRunData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.Valve;
import com.alibaba.citrus.turbine.TurbineRunData;

public class QthUserSessionValve  implements Valve  {
    private static final Logger log = LoggerFactory.getLogger(QthUserSessionValve.class);
    
    @Autowired
    private HttpServletRequest request;

    public void invoke(PipelineContext pipelineContext) throws Exception {
    	TurbineRunData rundata = getTurbineRunData(request);
    	 HttpSession session = rundata.getRequest().getSession();
         QthUser user = null;

         try {
             user = (QthUser) session.getAttribute(UserConstant.QTH_USER_SESSION_KEY);
         } catch (Exception e) {
             log.warn("Failed to read qthUser from session: " + UserConstant.QTH_USER_SESSION_KEY, e);
         }

         if (user == null) {
             // 创建匿名用户
             user = new QthUser();
             session.setAttribute(UserConstant.QTH_USER_SESSION_KEY, user);
         }

         // 将user设置到rundata中      PetstoreUser.setCurrentUser(user);
         user.upgrade();
        pipelineContext.invokeNext(); // 别忘了调用后序的valves
    }
}
