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
    // 查询(按钮)
    $("#btn-search").on("click", function(){
        // TODO 校验
//        $("form").submit();
        ajaxAction(1, 10);type="text"
    });
    // 清空(按钮)
    $("#btn-search-for-page").on("click", function(){
        $("[id^='txt-']").val("");
        $("[id^='sel-']").val("");
    });
    
    // 分页插件
    totalRecord = $("#total-record").val();
    $("#pagination").pagination({
        items: $("#total-record").val(), // 总个数
        itemsOnPage: $("#page-limit").val(), // 每页个数
        cssStyle: 'light-theme', // 指定css样式
        displayedPages: 9, // 展示页码的数量
        edges: 3, // 边界展示页码的个数
        prevText: "上一页",
        nextText: "下一页",
        onPageClick: function(pageNumber, event){
            // TODO 校验
            // 发送ajax请求
            ajaxAction(pageNumber, $(this).itemsOnPage);
        },
    });
});

var ajaxAction = function(pageNumber, itemsOnPage){
    $.ajax({
        url: "/pms/channel/list",
        type: "post",
        data: {
            pageStart: pageNumber, // 当前页码
            pageLimit: itemsOnPage, // 每页显示个数
            status: $("#sel-status").val(), // 账户状态
            type: $("#sel-type").val(), // 公司性质
            accountId: $("#txt-id").val(), // 公司账户
            companyName: $("#txt-company-name").val(), // 公司名称
            contractId: $("#txt-company-id").val(), // 合同号
            contractStartTime: $("#txt-contract-stTm").val(), // 合同开始日
            contractEndTime: $("#txt-comtract-edTm").val(), // 合同结束日
        },
        dataType: "json",
        success: function(data) {
             // 总搜索结果集发生改变
            if (data.totalRecord != $("#total-record").val()) {
                // 重置分页按钮
                $("#pagination").pagination("updateItems", data.totalRecord);
                $("#total-record").val(data.totalRecord);
            }
            
            // 展示搜索结果集
            var list = data.results;
            var pagination = $("#pagination-table");
            pagination.find("tbody tr").remove();
            for (var i = 0; i < list.length; i++) {
                var item = list[i];
                var model = $("#pagination-table-tr-model").clone().removeClass("hidden");
                for (var key in item) {
                    if (key == "id") {
                        model.find("a").attr("href", "detail?id=" + item[key]);
                    }
                    model.find("[name='" + key + "']").text(item[key]);
                }
                pagination.append(model);
            }
        }
    });
}
