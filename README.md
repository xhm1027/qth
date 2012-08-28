变更
------------

* 将biz.admin下面的产品类目service移动到biz.user下，在web.admin下增加了biz。user的依赖。原因是user也需要用产品类目，如果biz中各自写的话，service方法就会重复很多。=----ok
* 增加了antx配置项，上传文件的保存路径
* 修改了sql语句：BuyProduct.QUERY_PRODUCT

todo list
------------
* form表单中校验点统一增加字符长度等控制；----已完成
* UserDao中增加了一个getUserByEmail的方法，不过实现还没写，直接返回了null;----已完成

xhm的记录事宜
-----------

* screen的vm中不需要再加html及body标签了，jquery。js的引用也在layout/default。vm中加了----ok
* userDao的updateUser操作有对应的test用例可以参照吗?----已经增加了示例，在UserDaoTest类中，顺便使用时帮我验证一下完整逻辑是否有遗漏。
* 列表显示的页面中采用post方式看下能不能做到，类似“http://ic.qth.com/admin/listUser.htm?name=&company=&status=&userLevel=&page=1&pageSize=20”这种感觉可能会有问题，可以放在9月中旬以后再优化----好的，到时候看看类似页面如果需要优化的话，如何做优化；因为这里只是查询，不涉及修改数据库，所以用query参数我觉得问题不大
* buyProduct的isSale是干嘛的？----这个字段应该是没用的了，因为之前设计成一张表时，用来区分该产品是卖的还是买的，现在表分成两张了，这个字段可以去掉了。
* attachment的key是干什么用的？---备用的，现在用来标识附件的来源，比如来自buyProduct表还是saleProduct表，否则id有可能会是重的，用key作区分。也可用来区分图片封面之类的
* 销售产品的添加和查看页面 100% ----新增销售产品时，类别下拉框为什么没有显示？因为该用户感兴趣的销售产品类别没有
* 采购产品的添加和查看页面 100%
* 编辑销售产品页面 100%

zhangren记录事宜
-----------
* 收件箱，发件箱列表完成，查看消息详情，完成！
* 8.25待完成：消息发送，回复功能，消息删除功能！
* 8.26待完成：管理员站内信功能！
* 我要采购功能完成100%
* 我要销售功能完成80%，待完善：查询结果分页的div显示问题；点击了排序时图标显示问题；附件图片显示到采购中的图片
