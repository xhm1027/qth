变更
------------

* 将biz.admin下面的产品类目service移动到biz.user下，在web.admin下增加了biz。user的依赖。原因是user也需要用产品类目，如果biz中各自写的话，service方法就会重复很多。

todo list
------------
* form表单中校验点统一增加字符长度等控制；
* UserDao中增加了一个getUserByEmail的方法，不过实现还没写，直接返回了null;

xhm的记录事宜
-----------

* screen的vm中不需要再加html及body标签了，jquery。js的引用也在layout/default。vm中加了
* userDao的updateUser操作有对应的test用例可以参照吗?



