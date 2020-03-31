# tang4j

#### 介绍
1.tang4j-common 通用工具类

2.tang4j-core 通用接口类

3.tang4j-facade 扩展接口类

4.tang4j-service 服务接口类

5.tang4j-web restful接口

#### 软件架构
软件架构说明

#####1.必须的配置类
web安全配置类 WebSecurityConfig
用户身份权限认证类 MyUserDetailService
资源权限认证器 MyAccessDecisionManager
请求过滤类 MyFilterSecurityInterceptor
加载资源与权限的关系 MyInvocationSecurityMetadataSourceService
#####2.可选的处理类
权限不足处理类 MyAccessDeniedHandler
异常处理类 MyAuthenticationException
登录成功后处理类 MyAuthenticationSuccessHandler
登录失败后处理类 MyAuthenticationFailureHandler
退出系统成功后处理类 MyLogoutSuccessHandler
登录验证（比如校验验证码） MyUsernamePasswordAuthenticationFilter
认证失败处理类 MyAuthenticationEntryPointHandler
#####3.整合jwt 需要的类
jwt 工具类 提供校验token 、生成token、根据token获取用户等方法 JwtTokenUtil
用户信息 JWTUserDetails
JWTUserDetailsFactory
对请求的token进行校验 JwtAuthenticationTokenFilter
MyAccessDecisionManager 资源权限认证器 认证用户是否拥有所请求资源的权限

------------------------------------

1.IAccessDecisionManager 
资源权限认证器 认证用户是否拥有所请求资源的权限

2.IInvocationSecurityMetadataSourceService 
加载资源与权限的对应关系

3.IFilterSecurityInterceptor 
请求过滤


#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
=======
# tang4j

#### 介绍
1.tang4j-common 通用工具类

2.tang4j-core 通用接口类

3.tang4j-facade 扩展接口类

4.tang4j-service 服务接口类

5.tang4j-web restful接口

#### 软件架构
软件架构说明


#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)

