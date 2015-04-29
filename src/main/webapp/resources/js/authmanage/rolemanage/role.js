$(function() {
    var ctxPath = $("#ctxPath").val();
    $("#roleadd_btn").on("click", function() {
        // var pIds = new Array();
        var pIds = "";
        $("input[name='privilege']:checked").each(function() {
            // pIds.push(this.value);
            pIds += this.value + ",";
        });

        $.ajax({
            url : ctxPath + "/role/add",
            type : "POST",
            data : {
                roleName : $("#rolename").val(),
                privilegeIds : pIds
            },
            dataType : 'json',
            success : function(data) {
                if (data.result > 0) {
                    $.layer({
                        shade : [ 0.5, '#000' ],
                        area : [ 'auto', 'auto' ],
                        dialog : {
                            msg : '新增角色成功!',
                            btns : 1,
                            type : 4,
                            btn : [ '确定' ],
                            yes : function() {
                                location = ctxPath + "/role/list";
                            },
                        }
                    });
                }
            }
        });
    });

    $("#roleupdate_btn").on("click", function() {
        // var pIds = new Array();
        var pIds = "";
        $("input[name='privilege']:checked").each(function() {
            // pIds.push(this.value);
            pIds += this.value + ",";
        });

        $.ajax({
            url : "update",
            type : "POST",
            data : {
                roleName : $("#roleName").text(),
                roleId : $("#roleId").val(),
                privilegeIds : pIds
            },
            dataType : 'json',
            success : function(data) {
                if (data.result > 0) {
                    $.layer({
                        shade : [ 0.5, '#000' ],
                        area : [ 'auto', 'auto' ],
                        border : [ 0 ],
                        dialog : {
                            msg : '修改角色成功!',
                            btns : 1,
                            type : 4,
                            btn : [ '确定' ],
                            yes : function() {
                                location = ctxPath + "/role/list";
                            },
                        }
                    });
                }
            }
        });
    });

    $("#cancel_btn").on("click", function() {
        location = ctxPath + "/role/list";
    })

    $(".btn-new").on("click", function() {
        window.location.href = ctxPath + '/role/add';
    });

    $("input[id^=int-toggle-data-]").each(function() {
        $(this).click(function() {
            var id = this.id.lastIndexOf('-');
            var tid = "toggle" + this.id.substring(id);
            $("#" + tid).toggle();
            $("fieldset[id^=field-" + tid + "-]").each(function() {
                $(this).toggle();
            })
        })
    });

    $("input[id^=int-menu]").each(function() {
        var mid = this.id.lastIndexOf('-');
        mid = "toggle" + this.id.substring(mid);
        $(this).click(function() {
            $("#" + mid).toggle();
        })
    })
})

var ifdel = function(roleId) {
    $.layer({
        shade : [ 0.5, '#000' ],
        area : [ 'auto', 'auto' ],
        dialog : {
            msg : '是否确认删除角色？',
            btns : 2,
            type : 4,
            btn : [ '确定', '取消' ],
            yes : function() {
                delRole(roleId);
            },
            no : function() {
            }
        }
    });
}

var delRole = function(roleId) {
    var ctxPath = $("#ctxPath").val();
    var url = ctxPath + "/role/delete?roleId=" + roleId;
    $.ajax({
        url : url,
        type : "DELETE",
        dataType : 'json',
        success : function(data) {
            if (data.result > 0) {
                $.layer({
                    shade : [ 0.5, '#000' ],
                    area : [ 'auto', 'auto' ],
                    dialog : {
                        msg : '删除角色成功!',
                        btns : 1,
                        type : 4,
                        btn : [ '确定' ],
                        yes : function() {
                            location = ctxPath + "/role/list";
                        },
                    }
                });
            }
        }
    });
}
