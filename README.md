变更
------------

* 将biz.admin下面的产品类目service移动到biz.user下，在web.admin下增加了biz。user的依赖。原因是user也需要用产品类目，如果biz中各自写的话，service方法就会重复很多。

todo list
------------
* form表单中校验点统一增加字符长度等控制

xhm的review意见
-----------

* queryCategory(Map param)：参数不要用map，构造一个queryObject去查询。如果用Map，传递的参数我需要到ibatis的sql文件中去看才知道该put什么，会为后期二次开发可能会埋雷的.dao和service中都不提倡。。
* 列表查询一般会对应count查总数方法，看需要是否增加。
* Y和N，is_deleted用的是小写，而is_material用的是大写，需要统一。
* 做列表显示的时候，如果操作麻烦的话前面的序号可以不需要。demo中的内容只是草稿，需要我们做的时候优化的。
* 分页原理明天给我介绍下吧。



