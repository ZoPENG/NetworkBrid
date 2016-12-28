/**
 * Author: Zhixin Wen<wenzhixin2010@gmail.com>,岳正灵
 */
(function ($) {
    //一些提示文字
    $.fn.bootstrapTable.locales['zh-CN'] = {
        formatLoadingMessage: function () {
            return '加载数据中…';
        },
        formatRecordsPerPage: function (pageNumber) {
            return '每页 ' + pageNumber + ' 条';
        },
        formatShowingRows: function (pageFrom, pageTo, totalRows) {
            return "共"+totalRows+"条";
        },
        formatSearch: function () {
            return '搜索';
        },
        formatNoMatches: function () {
            return '没有找到匹配的记录';
        },
        formatPaginationSwitch: function () {
            return '隐藏/显示分页';
        },
        formatRefresh: function () {
            return '刷新';
        },
        formatToggle: function () {
            return '切换';
        },
        formatColumns: function () {
            return '自定义列';
        },
        formatExport: function () {
            return '导出数据';
        },
        formatClearFilters: function () {
            return '清空过滤';
        }
    };
    //表格的初始化参数
    var table_param = {
        sidePagination: 'server',  //表示从服务器获得数据
        pagination: true,  //是否在表格下边显示分页栏
        search: true,    //是否在工具栏显示搜索框
        showRefresh: true,  //是否显示刷新按钮
        showColumns: true, //是否显示一个筛选列的按钮
        pageSize: 10,    //一页显示记录的数量
        pageList: [10,25,50,100]
    };
    //列的初始化参数
    var column_param = {
        searchable: false
    };
    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN'],table_param);
    $.extend($.fn.bootstrapTable.columnDefaults,column_param);
    $.fn.bootstrapTable.defaults.queryParams = queryParams;
})(jQuery);

/**
 * 请求后台时构造请求参数
 */
function queryParams(data){
    var param = {};
    //排序条件
    if(data['sort']!=null){
        param['sort'] = data['sort']+' '+data['order'];
    }
    //开始记录
    if(data['offset']!=null){
        param['start'] = data['offset'];
    }
    //每页最多记录数
    if(data['limit']!=null){
        param['rows'] = data['limit'];
    }
    //搜索条件
    if(data['search']!=null && data['search'].length>0){
        var likes = [];
        for(var i=0;i<this.columns[0].length;i++){
            var column = this.columns[0][i];
            if(column['searchable']){
                var value = {};
                value[column['field']] = '*'+data['search']+'*';
                likes[likes.length] = {'_like':value};
            }
        }
        if(likes.length==1){
            param['query']= encodeURIComponent(JSON.stringify(likes[0]));
        }else if(likes.length>1){
            param['query']= encodeURIComponent(JSON.stringify({"_or":likes}));
        }else {
            console.error("表格中没有可搜索的列");
        }
        param['query_encode'] = true;
    }
    return param;
}
