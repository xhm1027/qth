变更
------------

* 将biz.admin下面的产品类目service移动到biz.user下，在web.admin下增加了biz。user的依赖。原因是user也需要用产品类目，如果biz中各自写的话，service方法就会重复很多。=----ok

todo list
------------
* form表单中校验点统一增加字符长度等控制；----已完成
* UserDao中增加了一个getUserByEmail的方法，不过实现还没写，直接返回了null;----已完成

xhm的记录事宜
-----------

* screen的vm中不需要再加html及body标签了，jquery。js的引用也在layout/default。vm中加了----ok
* userDao的updateUser操作有对应的test用例可以参照吗?----已经增加了示例，在UserDaoTest类中，顺便使用时帮我验证一下完整逻辑是否有遗漏。

zhangren记录事宜
-----------
* UserService已实现重置密码及发邮件的功能。发送邮件的地址，密码及端口等信息在antx中配置即可。用户没有邮箱时，不发送邮件！
qth.admin.email.server.host=smtp.163.com
qth.admin.email.server.port=25
qth.admin.email.server.username=zhangrencnic@163.com
qth.admin.email.server.password=xxxxxxxxx


