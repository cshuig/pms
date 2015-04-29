$(function() {
    // 总个数
    var items = $("#items").val();
    // 每页显示个数
    var itemsOnPage = $("#items-on-page").val();
    var url = $("#paging-url").val();
    $(function() {
        $("#pagination").pagination(
                {
                    // 总个数
                    items : items,
                    // 每页个数
                    itemsOnPage : itemsOnPage,
                    // 指定css样式
                    cssStyle : 'light-theme',
                    // 展示页码的数量
                    displayedPages : 10,
                    // 边界展示页码的个数
                    edges : 4,
                    prevText : "上一页",
                    nextText : "下一页",
                    onPageClick : function(pageNumber, event) {
                        // 发送ajax请求
                        $.ajax({
                            url : url,
                            type : "post",
                            data : {
                                // 当前页码
                                page : pageNumber,
                                // 每页显示个数
                                pageSize : $(this).itemsOnPage,
                            },
                            dataType : "json",
                            success : function(data) {
                                // 总搜索结果集发生改变
                                if (data.page.totalRecord != items) {
                                    // 重置分页按钮
                                    $("#pagination").pagination("updateItems",
                                            data.page.totalRecord);
                                }
                                // 展示搜索结果集
                                var list = data.page.results;
                                var pagination = $("#pagination-table");
                                pagination.find("tbody tr").remove();
                                for (var i = 0; i < list.length; i++) {
                                    var item = list[i];
                                    var model = $("#pagination-table-tr-model")
                                            .clone().removeClass("hidden");
                                    for ( var key in item) {
                                        model.find("[name='" + key + "']")
                                                .text(item[key]);
                                    }
                                    model.find("input[name='userId']").val(
                                            item.userId);
                                    model.find("a[status='emp-view']").attr(
                                            "href",
                                            "show?userId=" + item.userId);
                                    model.find("a[status='emp-update']").attr(
                                            "href",
                                            "update?userId=" + item.userId);
                                    pagination.append(model);
                                    empPassReset();
                                    empDelete();
                                }
                            }
                        });
                    },
                });
    });
})