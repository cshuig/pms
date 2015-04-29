$(function(){
    // 日期校验
    
    // 添加(按钮)
    // 调整(按钮)
    // 打印(按钮)
    // 调账(按钮)
    // 保存(按钮)
    // 返回(按钮)
    
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