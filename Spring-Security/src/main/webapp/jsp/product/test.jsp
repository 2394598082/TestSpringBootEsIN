<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>级联下拉</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cascader/cascader.css">

	</head>
	<body class="layui-layout-body">

		<div class="layui-form-item">
			<label class="layui-form-label">选择框</label>
			<div class="layui-input-block">
				<input type="text" id="a" class="layui-input" readonly="readonly">
			</div>
		</div>
		
		<img src="https://bzmall-layui.oss-cn-beijing.aliyuncs.com/brand/9c36.jp">


		<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
		<script src="${pageContext.request.contextPath}/ztree/js/jquery-1.4.4.min.js"></script>
		<script>
           $(function () {
			   $.ajax({
				   url:"${pageContext.request.contextPath}/category/getCascaderNodeVos",
				   success:function (data) {
                       layui.config({
                           base: "${pageContext.request.contextPath}/layui/lay/mymodules/"
                       }).use(['form',"jquery","cascader","form"], function(){
                           var $ = layui.jquery;
                           var cascader = layui.cascader;

                           var cas=cascader({
                               elem: "#a",
                               // url 和 data二选一
                               data: data,
                               // 远程请求数据的地址
                               // url: ,
                               // 只显示最后一级菜单
                               showLastLevels: true,

                               success: function (valData,labelData) {
                                   console.log(valData,labelData);
                               }
                           });

                       });
                   }
			   })
           })
		</script>
	</body>
</html>