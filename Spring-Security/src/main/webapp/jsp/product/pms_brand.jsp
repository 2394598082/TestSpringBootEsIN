<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>管理员展示</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cascader/cascader.css">
</head>
<body class="layui-layout-body">
<div class="layui-row" style="padding-top: 20px;padding-left: 20px">

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="addData" onclick="addLayer()">添加</button>
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
    <form action="hhh" lay-filter="addForm" id="addForm" class="layui-form"
          style="display:none;padding:20px 20px 0 0" method="post">
        <div class="layui-form-item">
            <lable class="layui-form-label">名称</lable>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="name">
            </div>

        </div>
        <div class="layui-form-item">
            <lable class="layui-form-label">首字母</lable>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="firstLetter">
            </div>
        </div>

        <div class="layui-form-item">
            <lable class="layui-form-label">排序</lable>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="sort">
            </div>
        </div>

        <div class="layui-form-item">
            <lable class="layui-form-label">是否显示</lable>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="showStatus">
            </div>
        </div>

        <div class="layui-form-item">
            <lable class="layui-form-label">品牌logo</lable>
            <input type="hidden" id="logo" name="logo">
            <div class="layui-upload">
                <button type="button" class="layui-btn" id="test1">品牌logo</button>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" width="400px" id="demo1">
                    <p id="demoText"></p>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <lable class="layui-form-label">品牌描述</lable>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="brandStory">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">所属分类</label>
            <div class="layui-input-block">
                <input type="text" id="a" name="categoryName" class="layui-input" readonly="readonly">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="addSubmit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

</div>


<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>

    layui.use('upload', function(){
        var $ = layui.jquery
            ,upload = layui.upload;
        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1',
            url: '${pageContext.request.contextPath}/oss/ossUpload',
            before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                console.info(res)
                //上传成功
                if(res.code == 0){
                    $("#logo").val(res.url)
                }

            }
        });
    });
        // 打开添加对话框
    //执行弹出弹出层的代码
    function addLayer(){
        layui.use(["layer","form",'table'],function(){
            layui.use(["layer", "form"], function () {
                var form = layui.form;
                var layer = layui.layer;
                var table = layui.table;
                var $ = layui.jquery;
                layer.open({
                    type:1,
                    area: ['600px','700px'],
                    maxmin: true,
                    content:$("#addForm")
                });

                //------------ 添加功能 start--------------------------
                form.on('submit(addSubmit)',function () {
                    $.ajax({
                        url:'${pageContext.request.contextPath}/brand/saveBrand',
                        data:$("#addForm").serialize(),
                        dataType:'json',
                        success:function (result) {
                            if(result.code == 0){
                                layer.closeAll();//添加成功关闭弹框
                                table.reload('test');
                                $("#addForm")[0].reset();  //清空输入框
                            }
                        }
                    })
                    return false;//阻止表单提交
                })
                //------------ 添加功能 end  --------------------------

            });


        })
    }

    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#test',
            url: '${pageContext.request.contextPath}/brand/selectBrandBySize',
            cellMinWidth: 80,
            toolbar: '#toolbarDemo',
            cols: [[
                {type:'checkbox'},
                {field:'brandId', title: '品牌id', sort: true},
                {field:'name', title: '名称'},
                {field:'firstLetter', title: '首字母'},
                {field:'sort', title: '排序', sort: true},
                {field:'showStatus', title: '是否显示',templet: '#checkboxTpl', unresize: true},
                {field:'logo', title: '品牌logo'},
                {field:'categoryName', title: '所属分类'},
                {field:'brandStory', title: '品牌描述', align: 'right'},
                {fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]],
            page: true
        });

    });


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
                        data:data,
                        showLastLevels: true,
                        success: function (valData,labelData) {
                            // 获取最后一个节点下标
                            console.log(valData[valData.length-1]);
                            console.info(labelData)
                        }
                    });


                });
            }
        })
    })


</script>
</body>
</html>