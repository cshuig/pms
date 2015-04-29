$(function() {
    var form = $("#useradd_form");
    $("#useradd_btn").on("click", function() {
        subflag = true;
        str = "";
        var userName = $("input[name='userName']").val();
        var name = $("input[name='name']").val();
        var deptId = $("select[name='departmentId']").val();
        var mobile = $("input[name='mobile']").val();
        var email = $("input[name='email']").val();
        var roleId = $("select[name='roleId']").val();
        if (subflag) {
            form.submit();
        } else {
            alert(str);
        }
    });

    var flag = $("input[name='flag']").val();
    if (flag == 1) {
        msgv = "新建用户成功！请登录注册时填写的邮箱，接收初始登录密码。如有问题，请联系系统管理员！";
        url = "list";
        $.layer({
            shade : [ 0.5, '#000' ],
            area : [ 'auto', 'auto' ],
            dialog : {
                msg : msgv,
                btns : 1,
                type : 4,
                btn : [ '确定' ]
            // yes : function() {
            // layer.msg('ok', 1, 13);
            // }
            }
        });
    }
})
var subflag = true;// 表单提交标识
var str = "";
function illegal(src) {
    subflag = false;
    str += src + '\n';
}
