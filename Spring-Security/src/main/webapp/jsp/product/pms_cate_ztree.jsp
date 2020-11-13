<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>ztree分类展示</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/ztree/css/metroStyle/metroStyle.css">
		<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/ztree/zTreeStyle/zTreeStyle.css">--%>

	</head>
	<body class="layui-layout-body">

		<div>
			<ul id="treeDemo" class="ztree"></ul>
		</div>


		<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
		<script src="${pageContext.request.contextPath}/ztree/js/jquery-1.4.4.min.js"></script>
		<script src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.all.js"></script>
		<script>
            $(function () {
                var settings = {
                    data: {
                        simpleData: {
                            enable: true,  //true , false 分别表示 使用  不使用 简单数据模式
                        }
                    },
                    edit: {
                        enable: true,
                        editNameSelectAll: true,
                        showRemoveBtn: showRemoveBtn,
                        showRenameBtn: showRenameBtn
                    },
                    callback: {
                        beforeDrag: beforeDrag,
                        beforeEditName: beforeEditName,
                        beforeRemove: beforeRemove,
                        beforeRename: beforeRename,
                        onRemove: onRemove,
                        onRename: onRename
                    }
                };
				$.ajax({
					url:"${pageContext.request.contextPath}/category/getZTreeNodes",
					success: function (data) {
                        zTreeObj = $.fn.zTree.init($("#treeDemo"), settings, data); //初始化树
                    }
				});
                var log, className = "dark";
                function beforeDrag(treeId, treeNodes) {
                    return false;
                }
                function beforeEditName(treeId, treeNode) {
                    className = (className === "dark" ? "":"dark");
                    showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
                    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                    zTree.selectNode(treeNode);
                    setTimeout(function() {
                        if (confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？")) {
                            setTimeout(function() {
                                zTree.editName(treeNode);
                            }, 0);
                        }
                    }, 0);
                    return false;
                }
                function beforeRemove(treeId, treeNode) {
                    className = (className === "dark" ? "":"dark");
                    showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
                    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                    zTree.selectNode(treeNode);
                    return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
                }
                function onRemove(e, treeId, treeNode) {
                    showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
                }
                function beforeRename(treeId, treeNode, newName, isCancel) {
                    className = (className === "dark" ? "":"dark");
                    showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
                    if (newName.length == 0) {
                        setTimeout(function() {
                            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                            zTree.cancelEditName();
                            alert("节点名称不能为空.");
                        }, 0);
                        return false;
                    }
                    return true;
                }
                function onRename(e, treeId, treeNode, isCancel) {
                    showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
                }
                // 是否显示删除按钮 最有一级才显示删除按钮
                function showRemoveBtn(treeId, treeNode) {
                    return !treeNode.isFirstNode;
                }

                function showRenameBtn(treeId, treeNode) {
                    return !treeNode.isLastNode;
                }
                function showLog(str) {
                    if (!log) log = $("#log");
                    log.append("<li class='"+className+"'>"+str+"</li>");
                    if(log.children("li").length > 8) {
                        log.get(0).removeChild(log.children("li")[0]);
                    }
                }
                function getTime() {
                    var now= new Date(),
                        h=now.getHours(),
                        m=now.getMinutes(),
                        s=now.getSeconds(),
                        ms=now.getMilliseconds();
                    return (h+":"+m+":"+s+ " " +ms);
                }

                var newCount = 1;
                function addHoverDom(treeId, treeNode) {
                    var sObj = $("#" + treeNode.tId + "_span");
                    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
                    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                        + "' title='add node' onfocus='this.blur();'></span>";
                    sObj.after(addStr);
                    var btn = $("#addBtn_"+treeNode.tId);
                    if (btn) btn.bind("click", function(){
                        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                        zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
                        return false;
                    });
                };
                function removeHoverDom(treeId, treeNode) {
                    $("#addBtn_"+treeNode.tId).unbind().remove();
                };
                function selectAll() {
                    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                    zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
                }
            })
		</script>
	</body>
</html>