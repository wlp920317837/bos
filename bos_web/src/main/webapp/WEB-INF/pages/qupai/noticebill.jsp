<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>业务通知单</title>
    <!-- 导入jquery核心类库 -->
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <!-- 导入easyui类库 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/css/default.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
    <script
            src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
            type="text/javascript"></script>
    <script type="text/javascript">

        function doRepeat() {
            //获取数据表格中所有选择的行
            var rows = $("#grid").datagrid("getSelections");
            if (rows.length == 0) {
                $.messager.alert("提示信息", "请选择工单!", "warning");
            } else {
                $.messager.confirm("删除确认", "您确定要追单吗", function (r) {
                    if (r) {
                        var ids = "";
                        var arrayId = new Array();
                        //获取所有选择取派员的id
                        for (var i = 0; i < rows.length; i++) {
                            var staff = rows[i];//json对象
                            var id = staff.id;
                            arrayId.push(id);
                        }
                        ids = arrayId.join(",");
                        //发送普通请求
                        location.href = "workbillAction_repeat?ids=" + ids;
                    }
                });
            }
        }

        function doCancel() {
            //获取数据表格中所有选择的行
            var rows = $("#grid").datagrid("getSelections");
            if (rows.length == 0) {
                $.messager.alert("提示信息", "请选择工单!", "warning");
            } else {
                $.messager.confirm("删除确认", "您确定要销单吗", function (r) {
                    if (r) {
                        var ids = "";
                        var arrayId = new Array();
                        //获取所有选择取派员的id
                        for (var i = 0; i < rows.length; i++) {
                            var staff = rows[i];//json对象
                            var id = staff.id;
                            arrayId.push(id);
                        }
                        ids = arrayId.join(",");
                        //发送普通请求
                        location.href = "workbillAction_cancel?ids=" + ids;
                    }
                });
            }
        }

        function doSearch() {
            $('#searchWindow').window("open");
        }

        //工具栏
        var toolbar = [{
            id: 'button-search',
            text: '查询',
            iconCls: 'icon-search',
            handler: doSearch
        }, {
            id: 'button-repeat',
            text: '追单',
            iconCls: 'icon-redo',
            handler: doRepeat
        }, {
            id: 'button-cancel',
            text: '销单',
            iconCls: 'icon-cancel',
            handler: doCancel
        }];
        // 定义列
        var columns = [[{
            field: 'id',
            checkbox: true,
        }, {
            field: 'noticebill.id',
            title: '通知单号',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                if (row.noticebill != null) {
                    return row.noticebill.id;
                } else {
                    return "暂无数据";
                }
            }
        }, {
            field: 'type',
            title: '工单类型',
            width: 120,
            align: 'center'
        }, {
            field: 'pickstate',
            title: '取件状态',
            width: 120,
            align: 'center'
        }, {
            field: 'buildtimeString',
            title: '工单生成时间',
            width: 120,
            align: 'center'
        }, {
            field: 'attachbilltimes',
            title: '追单次数',
            width: 120,
            align: 'center'
        }, {
            field: 'staff.name',
            title: '取派员',
            width: 100,
            align: 'center',
            formatter: function (data, row, index) {
                if (row.staff != null) {
                    return row.staff.name;
                } else {
                    return "暂无数据";
                }
            }
        }, {
            field: 'staff.telephone',
            title: '联系方式',
            width: 100,
            align: 'center',
            formatter: function (data, row, index) {
                if (row.staff != null) {
                    return row.staff.telephone;
                } else {
                    return "暂无数据";
                }
            }
        }]];

        $(function () {
            // 先将body隐藏，再显示，不会出现页面刷新效果
            $("body").css({visibility: "visible"});

            // 收派标准数据表格
            $('#grid').datagrid({
                iconCls: 'icon-forward',
                fit: true,
                border: true,
                rownumbers: true,
                striped: true,
                pageList: [3, 5, 10],
                pagination: true,
                toolbar: toolbar,
                url: "workbillAction_pageQuery.action",
                idField: 'id',
                columns: columns,
                onDblClickRow: doDblClickRow
            });

            // 查询分区
            $('#searchWindow').window({
                title: '查询分区',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });

            //表单数据序列化方法
            $.fn.serializeJson = function () {
                var serializeObj = {};
                var array = this.serializeArray();
                $(array).each(
                    function () {
                        if (serializeObj[this.name]) {
                            if ($.isArray(serializeObj[this.name])) {
                                serializeObj[this.name].push(this.value);
                            } else {
                                serializeObj[this.name] = [
                                    serializeObj[this.name], this.value];
                            }
                        } else {
                            serializeObj[this.name] = this.value;
                        }
                    });
                return serializeObj;
            };

            $("#btn").click(function () {
                var p = $("#searchForm").serializeJson();
                //调用数据表格load方法，重新发送一次ajax请求，并添加参数
                $("#grid").datagrid("load", p);
                $("#searchForm").get(0).reset();// 重置查询表单
                $("#searchWindow").window("close"); // 关闭窗口
            });
        });

        function doDblClickRow() {
            alert("双击表格数据...");
        }
    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
    <table id="grid"></table>
</div>

<!-- 查询分区 -->
<div class="easyui-window" title="查询窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false"
     style="top:20px;left:200px">
    <div style="overflow:auto;padding:5px;" border="false">
        <form id="searchForm">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">查询条件</td>
                </tr>
                <tr>
                    <td>取派员电话</td>
                    <td><input type="text" name="staff.telephone" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>取派员</td>
                    <td><input type="text" name="staff.id" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>