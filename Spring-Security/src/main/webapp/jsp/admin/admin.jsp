<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>管理员展示</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
	</head>
	<body class="layui-layout-body">
	<div class="layui-row" style="padding-top: 20px;padding-left: 20px">

		<script type="text/html" id="toolbarDemo">
			<div class="layui-btn-container">
				<%--
					标签式授权
					<sec:authorize access="hasAuthority('admin:add')"> 具有权限标签中的html才能够正常展示

				--%>
				<sec:authorize access="@ss.hasAnyPermission('admin:add')">
					<button class="layui-btn layui-btn-sm" lay-event="addData" onclick="addLayer()">添加</button>
				</sec:authorize>
				<button class="layui-btn layui-btn-sm" lay-event="getCheckLength">删除选中</button>
			</div>
		</script>

		<script type="text/html" id="barDemo">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</script>


		<script type="text/html" id="checkboxTpl">
			<input type="checkbox" name="showStatus" value="{{d.showStatus}}" title="显示" lay-filter="lockDemo" {{ d.showStatus ==1 ? 'checked' : '' }}>
		</script>

		<table class="layui-hide" id="test"></table>

		<!--            添加表单-->
		<script type="text/html" id="addForm">
			<div style="padding-top: 20px;padding-right: 40px">
				<form action="" class="layui-form">
					<div class="layui-form-item">
						<lable class="layui-form-label">用户名</lable>
						<div class="layui-input-block"><input type="text" class="layui-input" name="username" lay-verify="required"></div>
					</div>
					<div class="layui-form-item">
						<lable class="layui-form-label">密码</lable>
						<div class="layui-input-block"><input type="password" class="layui-input" name="password" lay-verify="required"></div>
					</div>
				</form>

				<div style="padding-left: 80px;padding-top: 10px" class="layui-upload">
					<button type="button" class="layui-btn" id="test1">上传图片</button>
					<div class="layui-upload-list">
						<img class="layui-upload-img" id="demo1">
						<p id="demoText"></p>
					</div>
				</div>
			</div>

		</script>


	</div>



		<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
		<script>
			layui.use('table', function(){
				var table = layui.table;

				table.render({
					elem: '#test',
					url: '${pageContext.request.contextPath}/admin/selectAdminBySize',
					cellMinWidth: 80,
					toolbar: '#toolbarDemo',
					cols: [[
						{type:'checkbox'},
						{field:'id', title: 'id', sort: true},
						{field:'username', title: '用户名'},
						{field:'password', title: '密码'},
						{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
					]],
					page: true
				});

			});

			// 打开添加对话框
			//执行弹出弹出层的代码
			function addLayer(){
				layui.use(["layer","form"],function(){
					layui.use(["layer", "form"], function () {
						var form = layui.form;
						var layer = layui.layer;
						var $ = layui.jquery;

						layer.open({
							title:"添加",
							type:1,
							btn:["添加","取消"],
							shade: 0.2,
							maxmin:true,
							shadeClose: true,
							area: ['500px', '600px'],
							content:$("#addForm").html(),

							success:function(layerobj,index){
								console.log(layerobj);
								console.log(index);

								form.on("submit(go)",function(data){
									console.log(data);
									//发送ajax请求，如果成功关闭弹出层

									layer.close(index);
									return false;//禁用同步的表单提交
								});

								$("#add-form-cancel").click(function () {
									layer.close(index);
								});
							}
						})
					});


				})
			}
		</script>
	</body>
</html>