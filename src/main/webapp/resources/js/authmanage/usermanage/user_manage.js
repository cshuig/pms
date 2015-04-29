$(function() {
    $(".btn-new").on("click", function() {
        window.location.href = 'add';
    });
    $("button[name='btn-searchUser']").on(
            "click",
            function() {
                var userName = $("input[name='userName']").val();
                var roleName = $("input[name='roleName']").val();
                window.location.href = 'list?userName='
                        + encodeURI(encodeURI(userName)) + "&roleName="
                        + encodeURI(encodeURI(roleName));
            });
    $("button[name='btn-clear']").on("click", function() {
        $("input[name='userName']").val('');
        $("input[name='roleName']").val('');
    });
    empPassReset();
    empDelete();
    alertUpdateOk();
})

function empPassReset() {
    $("a[status='emp-pass-reset']").on("click", function() {
        var userId = $(this).siblings("input:last").val();
        $.ajax({
            type : "post",
            url : "resetPassword",
            data : {
                userId : userId
            },
            dataType : "json",
            success : function(data) {
                if (data.success == 1) {
                    $.layer({
                        shade : [ 0.5, '#000' ],
                        area : [ 'auto', 'auto' ],
                        dialog : {
                            msg : '重置密码邮件已发送！',
                            btns : 1,
                            type : 4,
                            btn : [ '确定' ]
                        }
                    });
                } else {
                    layer.msg('删除失败', 1, 13);
                }
            }
        });
    });
}
function empDelete() {
    $("a[status='emp-delete']").on("click", function() {
        var userId = $(this).siblings("input:last").val();
        $.layer({
            shade : [ 0.5, '#000' ],
            area : [ 'auto', 'auto' ],
            dialog : {
                msg : '是否确认删除用户？',
                btns : 2,
                type : 4,
                btn : [ '确定', '取消' ],
                yes : function() {
                    $.ajax({
                        type : "post",
                        url : "delete",
                        data : {
                            userId : userId
                        },
                        dataType : "json",
                        success : function(data) {
                            if (data.success == 1) {
                                $.layer({
                                    shade : [ 0.5, '#000' ],
                                    area : [ 'auto', 'auto' ],
                                    dialog : {
                                        msg : '删除用户成功!',
                                        btns : 1,
                                        type : 4,
                                        btn : [ '确定' ],
                                        yes : function() {
                                            window.location.href = 'list';
                                        }
                                    }
                                });
                            } else {
                                layer.msg('删除失败', 1, 13);
                            }
                        }
                    });

                }
            }
        });
    })
}
function alertUpdateOk() {
    var flag = $("input[name='flag']").val();
    if (flag == 'update') {
        msgv = "修改用户成功！";
        $.layer({
            shade : [ 0.5, '#000' ],
            area : [ 'auto', 'auto' ],
            dialog : {
                msg : msgv,
                btns : 1,
                type : 4,
                btn : [ '确定' ]
            }
        });
    }
}
