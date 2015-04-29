$(function(){
    // 日期校验
    
    // 绑定日期控件
    $("#txt-contract-stTm").datepicker({
        'format': 'yyyy/m/dd',
        'autoclose': true
    });
    $("#txt-comtract-edTm").datepicker({
        'format': 'yyyy/mm/dd',
        'autoclose': true
    });
    // 保存(按钮)
    $("#btn-save").on("click", function(){
        $("#new-channel").submit();
    });
});