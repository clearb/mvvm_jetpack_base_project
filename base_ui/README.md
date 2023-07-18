base_ui module 作为整个项目的资源提供者
所有项目的 styles color dimens strings values 图片 以及 drawable 文件能放这个地方的尽量放这个地方
小型的自定义view 也可以放在该项目中，大的自定义view 应该单独添加为一个module

这样写好处是
1、避免重复引入资源文件导致安装包变大
2、修改替换资源文件时好找
3、国际化时只需要考虑这个module 就行
    