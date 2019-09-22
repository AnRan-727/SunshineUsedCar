function paged(data){
    /*layui.use(['laypage', 'layer'], function() {
        var laypage = layui.laypage
            , layer = layui.layer;
        laypage.render({
            elem: 'page'
            ,count: 70 //数据总数
            ,jump: function(obj){
                console.log(obj)
            }
        });
    })*/
    layui.use(['laydate','element','laypage','layer'], function() {
        $ = layui.jquery;//jquery
        laydate = layui.laydate;//日期插件
        lement = layui.element();//面包导航
        laypage = layui.laypage;//分页
        layer = layui.layer;//弹出层
        //以上模块根据需要引入
        laypage({
            cont: 'page',
            curr:data.pageNumber
            ,pages: data.pageCount
            ,first: 1
            ,limit: 2
            ,last: data.pageCount
            ,prev: '<em><</em>'
            ,next: '<em>></em>'
            ,jump : function(obj, first) {
                console.log(obj)
                alert("pageCount::"+data.pageCount)
                var currPage =obj.curr;  //这里是后台返回给前端的当前页数
                if (!first) {
                    getListData(currPage);
                    /*layer.msg('第' + obj.curr + '页', {
                        offset : 'b'
                    });*/
                }
            }
        });

    })
    }
