/** layuiAdmin.std-v1.2.1 LPPL License By http://www.layui.com/admin/ */
;layui.define(["table", "form"], function (e) {
    var t = layui.$, i = layui.table, form = layui.form;
    layui.form;
    tableIns = i.render({
        elem: "#LAY-user-manage",
        url: "/crs/seller/sellerList.do",
        method: 'POST',//默认：get请求
        page: true,
        response: {
            statusName: 'code', //数据状态的字段名称，默认：code
            statusCode: 200, //成功的状态码，默认：0
            // countName: 'totals', //数据总数的字段名称，默认：count
            dataName: 'sellerList' //数据列表的字段名称，默认：data
        },
        cols: [[
            {type: "checkbox", fixed: "left"},
            {field: "id", width: 100, title: "ID", sort: !0},
            {field: "name", title: "商家名称", minWidth: 100}, // {field: "avatar", title: "头像", width: 100, templet: "#imgTpl"},
            {field: "mobile", title: "手机"},
            {field: "userName", title: "用户名称"},
            {field: "type", title: "类型"},
            {field: "is_work", title: "营业", templet: '#workTpl'},
            {field: "is_del", title: "激活", templet: '#delTpl'},
            // {field: "jointime", title: "加入时间", sort: !0},
            {fixed: 'right', title: '操作', width: 140, align: 'center', toolbar: '#optBar'}
            // {title: "操作", width: 150, align: "center", fixed: "right"}
        ]],
        page: !0,
        limit: 30,
        height: "full-220",
        text: "对不起，加载出现异常！"
    });

    //监听工具条
    i.on('tool(SellerTable)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
           // del(data,data.id,data.name);
        } else if(obj.event === 'edit'){
            //编辑
            getSeller(data,data.id);
        } else if(obj.event === 'recover'){
            //恢复
            //recoverUser(data,data.id);
        }
    });
    //监听废弃操作
    form.on('switch(delTpl)', function (obj) {
        //console.log(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
        var data = obj.data;
        setDel(obj, this.value, this.name, obj.elem.checked);
    });

    //设置用户是否离职
    function setDel(obj, id, name, checked) {
        var name = name;
        var is_del = checked ? 1 : 0;
        var isDel = checked ? "激活" : "废弃";
        //是否离职
        layer.confirm('您确定要把用户：' + name + '设置为' + isDel + '状态吗？', {
            btn: ['确认', '返回'] //按钮
        }, function () {
            $.ajax({
                url: "/crs/seller/setIsDel.do",       //要处理的页面
                type: "POST",               //提交方式
                data: {"id": id, "is_del": is_del, "name": name},
                dataType: "text",          //返回的数据类型，TEXT字符串 JSON返回JSON XML返回XML；dataType中T要大写！！
                success: function (data) {          //回调函数，data为形参，是从login-cl.php页面返回的值
                    if (data == "ok") {
                        //回调弹框
                        layer.alert("操作成功！", function () {
                            layer.closeAll();
                            //加载load方法
                            load(obj);
                        });
                    } else {
                        layer.alert(data, function () {
                            layer.closeAll();
                            //加载load方法
                            load(obj);//自定义
                        });
                    }
                }
            });
        }, function () {
            layer.closeAll();
            //加载load方法
            load(obj);
        });
    }

    //监听营业操作
    form.on('switch(workTpl)', function (obj) {
        //console.log(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
        var data = obj.data;
        setwork(obj, this.value, this.name, obj.elem.checked);
    });

    //设置用户是否离职
    function setwork(obj, id, name, checked) {
        var name = name;
        var is_work = checked ? 1 : 0;
        var isWork = checked ? "营业" : "打烊";
        //是否离职
        layer.confirm('您确定要把用户：' + name + '设置为' + isWork + '状态吗？', {
            btn: ['确认', '返回'] //按钮
        }, function () {
            $.ajax({
                url: "/crs/seller/setIsWork.do",       //要处理的页面
                type: "POST",               //提交方式
                data: {"id": id, "is_work": is_work, "name": name},
                dataType: "text",          //返回的数据类型，TEXT字符串 JSON返回JSON XML返回XML；dataType中T要大写！！
                success: function (data) {          //回调函数，data为形参，是从login-cl.php页面返回的值
                    if (data == "ok") {
                        //回调弹框
                        layer.alert("操作成功！", function () {
                            layer.closeAll();
                            //加载load方法
                            load(obj);
                        });
                    } else {
                        layer.alert(data, function () {
                            layer.closeAll();
                            //加载load方法
                            load(obj);//自定义
                        });
                    }
                }
            });
        }, function () {
            layer.closeAll();
            //加载load方法
            load(obj);
        });
    }
});

function  getSeller(obj,id) {
    //回显数据
    $.get("/crs/seller/getSeller.do",{"id":id},function(data){
            if(data.msg=="ok" && data.seller!=null){
                $("#id").val(data.seller.id==null?'':data.seller.id);
                //$("#version").val(data.seller.version==null?'':data.seller.version);
                $("#name").val(data.seller.name==null?'':data.seller.name);
                $("#mobile").val(data.seller.mobile==null?'':data.seller.mobile);
                $("#type").val(data.seller.type==null?'':data.seller.type);
                //显示角色数据
                // $("#roleDiv").empty();
                // $.each(data.roles, function (index, item) {
                //     var roleInput=$("<input type='checkbox' name='roleId' value="+item.id+" title="+item.roleName+" lay-skin='primary'/>");
                //     var div=$("<div class='layui-unselect layui-form-checkbox' lay-skin='primary'>" +
                //         "<span>"+item.roleName+"</span><i class='layui-icon'>&#xe626;</i>" +
                //         "</div>");
                //     if(existRole!='' && existRole.indexOf(item.id)>=0){
                //         roleInput=$("<input type='checkbox' name='roleId' value="+item.id+" title="+item.roleName+" lay-skin='primary' checked='checked'/>");
                //         div=$("<div class='layui-unselect layui-form-checkbox  layui-form-checked' lay-skin='primary'>" +
                //             "<span>"+item.roleName+"</span><i class='layui-icon'>&#xe627;</i>" +
                //             "</div>");
                //     }
                //     $("#roleDiv").append(roleInput).append(div);
                // });
                open(id,"编辑商家");
                //重新渲染下form表单中的复选框 否则复选框选中效果无效
                // layui.form.render();
                //layui.form.render('checkbox');
            }else{
                //弹出错误提示
                layer.alert(data.msg,function () {
                    layer.closeAll();
                });
            }
    });
}
function open(id,title){
    if(id==null || id==""){
        $("#id").val("");
    }
    layer.open({
        type:1,
        title: title,
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['550px'],
        content:$('#setUser'),
        end:function(){
            clean();
        }
    });
}

function clean(){
    $("#name").val("");
    $("#mobile").val("");
    $("#type").val("");
   // $("#password").val("");
}
function load(obj) {
    //重新加载table
    tableIns.reload({
        where: obj.field
    });
}