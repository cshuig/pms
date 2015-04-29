$(function() {
    var form = $("#user_update");
    $("#btn-user-update").on("click", function() {
        flag = true;
        str = "";
        var userName = $("input[name='userName']").val();
        var name = $("input[name='name']").val();
        var deptId = $("select[name='departmentId']").val();
        var mobile = $("input[name='mobile']").val();
        var email = $("input[name='email']").val();
        var roleId = $("select[name='roleId']").val();
        if (flag) {
            form.submit();
        } else {
            alert(str);
        }
    });
    $("#btn-user-back").on("click", function() {
        window.location.href = 'list';
    })
})

var flag = true;// 表单提交标识
var str = "";
function illegal(src) {
    flag = false;
    str += src + '\n';
}
