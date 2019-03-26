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
    i.on('tool(SellerTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            // del(data,data.id,data.name);
        } else if (obj.event === 'edit') {

            getSeller(data, data.id);
        } else if (obj.event === 'recover') {
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

    //监听提交
    form.on('submit(userSubmit)', function (data) {
        // TODO 校验
        formSubmit(data);
        return false;
    });
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

function getSeller(obj, id) {
    //回显数据
    $.get("/crs/seller/getSeller.do", {"id": id}, function (data) {
        if (data.msg == "ok" && data.seller != null) {
            $("#id").val(data.seller.id == null ? '' : data.seller.id);
            //$("#version").val(data.seller.version==null?'':data.seller.version);
            $("#name").val(data.seller.name == null ? '' : data.seller.name);
            $("#mobile").val(data.seller.mobile == null ? '' : data.seller.mobile);
            $("#type").val(data.seller.type == null ? '' : data.seller.type);
            $("#lnglat").val(data.p.longitude + "," + data.p.latitude);
            $("#lng").val(data.p.longitude);
            $("#lat").val(data.p.latitude);
            $("#address").val(data.p.address);
            $("#pickerInput").val("");
            dw();
             //location.reload();
            open(id, "编辑商家");

        } else {
            //弹出错误提示
            layer.alert(data.msg, function () {
                layer.closeAll();
                //location.reload();
            });
        }
    });
    //dw();
}

function open(id, title) {
    //layer.init();
    if (id == null || id == "") {
        $("#id").val("");
    }

    layer.open({
        type: 1,
        title: title,
        fixed: false,
        resize: false,
        shadeClose: true,
        area: ['800px'],
        content: $('#setUser'),
        end: function () {
            location.reload();
            clean();

        }
    });
}

//提交表单
function formSubmit(obj) {
    if ($("#id").val() != null && $("#id").val() != "") {
        layer.confirm('是否对商家进行修改？', {
            btn: ['确认', '返回'] //按钮
        }, function () {
            layer.closeAll();
            submitAjax(obj);

        }, function () {
            layer.closeAll();//关闭所有弹框
           // submitAjax(obj);
        });

    }
    if ($("#id").val() == null || $("#id").val() == "") {
        layer.confirm('是否对新增商家？', {
            btn: ['确认', '返回'] //按钮
        }, function () {
            layer.closeAll();
            submitAjax(obj);

        }, function () {
            layer.closeAll();//关闭所有弹框
            // submitAjax(obj);
        });

    }
}

function submitAjax(obj) {
    $.ajax({
        type: "POST",
        data: $("#sellerForm").serialize(),
        url: "/crs/seller/editSeller.do",
        success: function (data) {
            if (data == "ok") {
                layer.alert("操作成功", function () {
                    layer.closeAll();
                    //$("#id").val("");
                    //加载页面
                    load(obj);
                    //location.reload();
                });
            } else {
                layer.alert(data, function () {
                    layer.closeAll();
                    //加载load方法
                    load(obj);//自定义
                    //location.reload();
                });
            }

        },
        error: function () {
            layer.alert("操作请求错误，请您稍后再试", function () {
                layer.closeAll();
                //加载load方法
                load(obj);//自定义
                //location.reload();
            });
        }
    });
}

function clean() {
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

function dw() {

    AMapUI.loadUI(['misc/PositionPicker','misc/PoiPicker'], function(PositionPicker,PoiPicker) {
        var map = new AMap.Map('container', {
            zoom:15,
            resizeEnable: true,
            scrollWheel: true
        });
        var poiPicker = new PoiPicker({
            //city:'北京',
            input: 'pickerInput'
        });

        //初始化poiPicker
        poiPickerReady(poiPicker,map);

        var lng = $("#lng").val();
        var lat = $("#lat").val();
        if (lng!=""&&lat!="") {
            map.panTo([lng,lat]);
        }

        var positionPicker = new PositionPicker({
            mode: 'dragMap',
            map: map
        });
        // //监听poi选中信息
        // poiPicker.on('poiPicked', function(poiResult) {
        //     //用户选中的poi点信息\
        //     alert(poiResult.toString())
        //
        // });


        // 拖拽选址

        positionPicker.on('success', function(positionResult) {
            document.getElementById('lnglat').value = positionResult.position;
            document.getElementById('address').value = positionResult.address;
            // document.getElementById('nearestJunction').value = positionResult.nearestJunction;
            document.getElementById('nearestRoad').value = positionResult.nearestRoad;
            document.getElementById('nearestPOI').value = positionResult.nearestPOI;
            document.getElementById('city').value = positionResult.regeocode.addressComponent.city;
            document.getElementById('province').value = positionResult.regeocode.addressComponent.province;
            document.getElementById('region').value = positionResult.regeocode.addressComponent.district;
            document.getElementById('street').value = positionResult.regeocode.addressComponent.street;
            document.getElementById('streetNumber').value = positionResult.regeocode.addressComponent.streetNumber;
        });
        positionPicker.on('fail', function(positionResult) {
            document.getElementById('lnglat').value = '';
            document.getElementById('address').value = '';
            // document.getElementById('nearestJunction').value = '';
            document.getElementById('nearestRoad').value = '';
            document.getElementById('nearestPOI').value = '';
        });
        var onModeChange = function(e) {
            positionPicker.setMode(e.target.value)
        }
        positionPicker.start();
        map.panBy(0, 1);
        // map.addControl(new AMap.ToolBar({
        //     liteStyle: true
        // }))
    });
}

function poiPickerReady(poiPicker,map) {

    window.poiPicker = poiPicker;

    var marker = new AMap.Marker();

    // var infoWindow = new AMap.InfoWindow({
    //     offset: new AMap.Pixel(0, -20)
    // });

    //选取了某个POI
    poiPicker.on('poiPicked', function(poiResult) {

        var source = poiResult.source,
            poi = poiResult.item,
            info = {
                source: source,
                id: poi.id,
                name: poi.name,
                location: poi.location.toString(),
                address: poi.address
            };

        marker.setMap(map);
        //map.panTo([poi.location.R,poi.location.P]);
        // map.panTo

        marker.setPosition(poi.location);
        map.panTo([poi.location.lng,poi.location.lat]);
        map.setCenter(marker.getPosition());


        // infoWindow.setMap(map);
        //  infoWindow.setPosition(poi.location);
        // // infoWindow.setPosition(marker.getPosition());
        // infoWindow.setContent('POI信息: <pre>' + JSON.stringify(info, null, 2) + '</pre>');
        // // infoWindow.open(map, marker.getPosition());
        // infoWindow.open(map, poi.location);

    });

    // poiPicker.onCityReady(function() {
    //     //poiPicker.suggest('美食');
    // });
}

//开通用户
function addSeller(){
    dw();
    open(null,"新增商家");
}
