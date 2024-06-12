# 工程简介
```aidl
CREATE DATABASE online_class CHARACTER SET 'utf8';
use online_class
source online_class.sql
```
# 延伸阅读
```aidl
（1）前端使用vant v2
https://vant-contrib.gitee.io/vant/v2/#/zh-CN/home
(2)前后端分离
前端端口：80  后端端口：8089
（3）跨域
https://blog.csdn.net/oYiQiYi1/article/details/123863405
        //表示接受任意域名的请求,也可以指定域名
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));


(4)前后端认证采用jwt
http://www.proyy.com/6962037031799095327.html
        
 //jwt(携带user_id)->拦截器（保存到request属性）-->具体的业务代码（从request属性取出user_id）

(5)将异步调用写成同步的方式
            const result = await getVideoList();
            if (result.data.code == 0) {
                this.videoList = result.data.data;
            }
            
(6)根据变量决定显示Component  :is
                <component :is='item.component'>
                </component>

```

# 考虑增加的功能
```
（1）登录界面增加验证码
（2）购买记录增加删除按钮
```