变更
------------

* 将biz.admin下面的产品类目service移动到biz.user下，在web.admin下增加了biz。user的依赖。原因是user也需要用产品类目，如果biz中各自写的话，service方法就会重复很多。
* UserDao中增加了一个getUserByEmail的方法，不过实现还没写，直接返回了null;

todo list
------------
* form表单中校验点统一增加字符长度等控制

xhm的记录事宜
-----------

* Y和N，is_deleted用的是小写，而is_material用的是大写，需要统一。
* 做列表显示的时候，如果操作麻烦的话前面的序号可以不需要。demo中的内容只是草稿，需要我们做的时候优化的。



