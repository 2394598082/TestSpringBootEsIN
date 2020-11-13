<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>layout 后台大布局 - Layui</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo">layui 后台布局</div>
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<ul class="layui-nav layui-layout-left">
			<li class="layui-nav-item"><a href="">控制台</a></li>
			<li class="layui-nav-item"><a href="">商品管理</a></li>
			<li class="layui-nav-item"><a href="">用户</a></li>
			<li class="layui-nav-item">
				<a href="javascript:;">其它系统</a>
				<dl class="layui-nav-child">
					<dd><a href="">邮件管理</a></dd>
					<dd><a href="">消息管理</a></dd>
					<dd><a href="">授权管理</a></dd>
				</dl>
			</li>
		</ul>
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
					贤心
				</a>
				<dl class="layui-nav-child">
					<dd><a href="">基本资料</a></dd>
					<dd><a href="">安全设置</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item"><a href="">退了</a></li>
		</ul>
	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree" id="my_menu" lay-filter="my_menu">


			</ul>
		</div>
	</div>

	<div class="layui-body">
		<!-- 内容主体区域 -->
		<div style="padding: 15px;">
			<div class="layui-tab layui-tab-brief layui-tab-card" lay-allowClose="true"
				 lay-filter="myTab">
				<ul class="layui-tab-title">
					<li class="layui-this">欢迎页</li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">欢迎使用..xxxx</div>
				</div>
			</div>
		</div>
	</div>

	<div class="layui-footer">
		<!-- 底部固定区域 -->
		© layui.com - 底部固定区域
	</div>



</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script>
	function addTabs(menuName,menuUrl,menuId){
        layui.use('element', function(){
            var element = layui.element;
            console.log("${pageContext.request.contextPath}")
			// 打开选项卡
			// title 二级菜单的名字
			// id 二级菜单的id
			// content 需要引入一个新的页面（jsp页面） iframe标签 需要参数是页面的路径
            element.tabAdd('myTab', {
                title: menuName,
				// src 要引入页面的路径
				content: '<iframe src="${pageContext.request.contextPath}/'+menuUrl+'" width="100%" height="100%"></iframe>',
				id: menuId
            });

            element.tabChange('myTab', menuId);
        });
	}


	// 1.获取后台数据
	// 2.遍历拼接一级菜单的html
	// 3.将html写入页面中
	$(function () {
		$.ajax({
			url:"${pageContext.request.contextPath}/bz-menu/selectTree",
			success:function (data) {
                // 2.遍历拼接一级菜单的html
				var context = "";
				// each 遍历 参数1 被遍历的数据
				// 参数2 匿名函数 函数参数位置1 下标 位置2 被遍历的元素
				$.each(data,function (index1,menu1) {
					// 拼接一级菜单 前标签
					// 删除 layui-nav-itemed 就可以自动闭合
					context += "\t<li class=\"layui-nav-item\"><a class=\"\" href=\"javascript:;\">\n";
					// 一级菜单的名字
					context += menu1.name+"</a>";
					// 拼接二级菜单内容
					$.each(menu1.bzMenuList,function (index2, menu2) {
						// 拼接二级前标签
						// \ 转义符号 在js中 单引号和双引号 是有语法含义的 如果不做任何处理会被当做语法的一部分解析
						// 如果需要在js中写入一个字符串类型的引号 需要添加转义符号 \" \' 此时引号会被当做字符串
						context += "<dl class=\"layui-nav-child\"><dd><a href=\"javascript:;\" " +
							"onclick='addTabs(\"" + menu2.name + "\",\"" +menu2.url + "\"," + menu2.menuId +
							")'>";
                        // 二级菜单的名字
						context += menu2.name;
                        // 二级菜单后标签
						context += "</a></dd></dl>";
                    })
					// 后标签
					context += "</li>";
                });

				// 3.将生成的Html写入菜单中
				$("#my_menu").html(context);

				// 强制layui渲染菜单 更新渲染
                layui.use('element', function(){
                    var element = layui.element;
                    element.render('nav', 'my_menu')
                });

            }
		})
    })




    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>