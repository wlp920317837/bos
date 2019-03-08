<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
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
        function doAdd() {
            $('#addStaffWindow').window("open");
        }

        function doView() {
            $("#searchForm").form('reset');
            $('#searchWindow').window("open");
        }

        function doDelete() {
            //获取数据表格中所有选中的行，返回数组对象
            var rows = $("#grid").datagrid("getSelections");
            if (rows.length == 0) {
                //没有选中记录，弹出提示
                $.messager.alert("提示信息", "请选择需要删除的取派员！", "warning");
            } else {
                //选中了取派员,弹出确认框
                $.messager.confirm("删除确认", "你确定要删除选中的取派员吗？", function (r) {
                    if (r) {
                        var array = new Array();
                        //确定,发送请求
                        //获取所有选中的取派员的id
                        for (var i = 0; i < rows.length; i++) {
                            var staff = rows[i];//json对象
                            var id = staff.id;
                            array.push(id);
                        }
                        var ids = array.join(",");//1,2,3,4,5
                        location.href = "staffAction_deleteBatch.action?ids=" + ids;
                    }
                });
            }
        }

        function doRestore() {
            //获取数据表格中所有选中的行，返回数组对象
            var rows = $("#grid").datagrid("getSelections");
            if (rows.length == 0) {
                //没有选中记录，弹出提示
                $.messager.alert("提示信息", "请选择需要还原的取派员！", "warning");
            } else {
                //选中了取派员,弹出确认框
                $.messager.confirm("还原确认", "你确定要还原选中的取派员吗？", function (r) {
                    if (r) {
                        var array = new Array();
                        //确定,发送请求
                        //获取所有选中的取派员的id
                        for (var i = 0; i < rows.length; i++) {
                            var staff = rows[i];//json对象
                            var id = staff.id;
                            array.push(id);
                        }
                        var ids = array.join(",");//1,2,3,4,5
                        location.href = "staffAction_restoreBatch.action?ids=" + ids;
                    }
                });
            }
        }

        //工具栏
        var toolbar = [{
            id: 'button-view',
            text: '查询',
            iconCls: 'icon-search',
            handler: doView
        }, {
            id: 'button-add',
            text: '增加',
            iconCls: 'icon-add',
            handler: doAdd
        }, {
            id: 'button-delete',
            text: '作废',
            iconCls: 'icon-cancel',
            handler: doDelete
        }, {
            id: 'button-save',
            text: '还原',
            iconCls: 'icon-save',
            handler: doRestore
        }];
        // 定义列
        var columns = [[{
            field: 'id',
            checkbox: true,
        }, {
            field: 'name',
            title: '姓名',
            width: 120,
            align: 'center'
        }, {
            field: 'telephone',
            title: '手机号',
            width: 120,
            align: 'center'
        }, {
            field: 'haspda',
            title: '是否有PDA',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                if (data == "1") {
                    return "有";
                } else {
                    return "无";
                }
            }
        }, {
            field: 'deltag',
            title: '是否作废',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                if (data == "0") {
                    return "正常使用"
                } else {
                    return "已作废";
                }
            }
        }, {
            field: 'standard',
            title: '取派标准',
            width: 120,
            align: 'center'
        }, {
            field: 'station',
            title: '所谓单位',
            width: 200,
            align: 'center'
        }]];

        $(function () {
            // 先将body隐藏，再显示，不会出现页面刷新效果
            $("body").css({visibility: "visible"});

            // 取派员信息表格
            $('#grid').datagrid({
                iconCls: 'icon-forward',
                fit: true,
                border: false,
                rownumbers: true,
                striped: true,
                pageList: [3, 5, 10],
                pagination: true,
                toolbar: toolbar,
                url: "staffAction_pageQuery.action",
                idField: 'id',
                columns: columns,
                onDblClickRow: doDblClickRow
            });

            // 添加取派员窗口
            $('#addStaffWindow').window({
                title: '添加取派员',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });

            // 添加取派员窗口
            $('#editStaffWindow').window({
                title: '修改取派员',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
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

            //定义一个工具方法，用于将指定的form表单中所有的输入项转为json数据{key:value,key:value}
            $.fn.serializeJson = function () {
                var serializeObj = {};
                var array = this.serializeArray();
                $(array).each(function () {
                    if (serializeObj[this.name]) {
                        if ($.isArray(serializeObj[this.name])) {
                            serializeObj[this.name].push(this.value);
                        } else {
                            serializeObj[this.name] = [serializeObj[this.name], this.value];
                        }
                    } else {
                        serializeObj[this.name] = this.value;
                    }
                });
                return serializeObj;
            };

            $("#btn").click(function () {
                //将指定的form表单中所有的输入项转为json数据{key:value,key:value}
                var p = $("#searchForm").serializeJson();
                console.info(p);
                //调用数据表格的load方法，重新发送一次ajax请求，并且提交参数
                $("#grid").datagrid("load", p);
                //关闭查询窗口
                $("#searchWindow").window("close");
            });

            //为保存按钮绑定事件
            $("#save").click(function () {
                //表单校验，如果通过，提交表单
                var v = $("#addForm").form("validate");
                if (v) {
                    $("#addForm").submit();
                }
            });

            //为修改保存按钮绑定事件
            $("#saveEdit").click(function () {
                //表单校验，如果通过，提交表单
                var v = $("#editForm").form("validate");
                if (v) {
                    $("#editForm").submit();
                }
            });

            var reg = /^1[3|4|5|7|8][0-9]{9}$/;
            //扩展手机号校验规则
            $.extend($.fn.validatebox.defaults.rules, {
                telephone: {
                    validator: function (value, param) {
                        return reg.test(value);
                    },
                    message: '手机号输入有误！'
                }
            });
        });

        function doDblClickRow(rowIndex, rowData) {
            //打开修改取派员窗口
            $('#editStaffWindow').window("open");
            //使用form表单对象的load方法回显数据
            $('#editForm').form("load", rowData);
        }


    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
    <table id="grid"></table>
</div>
<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
        </div>
    </div>

    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="addForm" method="post" action="staffAction_add.action">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">收派员信息</td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>手机</td>
                    <td><input type="text" name="telephone" class="easyui-validatebox"
                               data-options="validType:'telephone'" required="true"/></td>
                </tr>
                <tr>
                    <td>单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="checkbox" name="haspda" value="1"/>
                        是否有PDA
                    </td>
                </tr>
                <tr>
                    <td>取派标准</td>
                    <td>
                        <input type="text" name="standard" class="easyui-validatebox" required="true"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

<div class="easyui-window" title="对收派员进行添加或者修改" id="editStaffWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="saveEdit" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
        </div>
    </div>
    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="editForm" method="post" action="staffAction_edit.action">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">收派员信息
                        <input name="id" type="hidden">
                    </td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>手机</td>
                    <td><input type="text" name="telephone" class="easyui-validatebox"
                               data-options="validType:'telephone'" required="true"/></td>
                </tr>
                <tr>
                    <td>单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="checkbox" name="haspda" value="1"/>
                        是否有PDA
                    </td>
                </tr>
                <tr>
                    <td>取派标准</td>
                    <td>
                        <input type="text" name="standard" class="easyui-validatebox" required="true"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<!-- 查询分区 -->
<div class="easyui-window" title="查询分区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false"
     style="top:20px;left:200px">
    <div style="overflow:auto;padding:5px;" border="false">
        <form id="searchForm">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">查询条件</td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name"/></td>
                </tr>
                <tr>
                    <td>手机号</td>
                    <td><input type="text" name="telephone"/></td>
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