$(function() {
    var form = $("#form-update-passwd");
    $("#btn-update-passwd").on("click", function() {
        subflag = true;
        var oldPassword = $("input[name='oldPassword']").val();
        var newPassword = $("input[name='newPassword']").val();
        var newPassword2 = $("input[name='newPassword2']").val();
        var verificationCode = $("input[name='verificationCode']").val();
        if (newPassword != newPassword2) {
            subflag = false;
            alert("密码确认与新密码不一致！");
            return false;
        }
        if (subflag) {
            form.submit();
        } else {
            return;
        }
    });
    var flag = $("input[name='flag']").val();
    if (flag == "success") {
        $.layer({
            shade : [ 0.5, '#000' ],
            area : [ 'auto', 'auto' ],
            dialog : {
                msg : "密码修改成功！",
                btns : 1,
                type : 4,
                btn : [ '确定' ]
            }
        });
    } else if (flag == "error") {
        $.layer({
            shade : [ 0.5, '#000' ],
            area : [ 'auto', 'auto' ],
            dialog : {
                msg : "旧密码不存在，请重新输入！",
                btns : 1,
                type : 4,
                btn : [ '确定' ]
            }
        });
    }
})
var subflag = true;