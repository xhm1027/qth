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
* 列表显示的页面中采用post方式看下能不能做到，类似“http://ic.qth.com/admin/listUser.htm?name=&company=&status=&userLevel=&page=1&pageSize=20”这种感觉可能会有问题，可以放在9月中旬以后再优化
* buyProduct的isSale是干嘛的？

zhangren记录事宜
-----------
* 收件箱，发件箱列表完成，查看消息详情，完成！
* 8.25待完成：消息发送，回复功能，消息删除功能！
* 8.26待完成：管理员站内信功能！

