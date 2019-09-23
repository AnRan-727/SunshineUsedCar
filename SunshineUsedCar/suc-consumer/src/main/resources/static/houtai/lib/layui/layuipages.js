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
function Cookie(key,value)
{
    this.key=key;
    if(value!=null)
    {
        this.value=escape(value);
    }
    this.expiresTime=null;
    this.domain=null;
    this.path="/";
    this.secure=null;
}
Cookie.prototype.setValue=function(value){this.value=escape(value);}
Cookie.prototype.getValue=function(){return (this.value);}

Cookie.prototype.setExpiresTime=function(time){this.expiresTime=time;}
Cookie.prototype.getExpiresTime=function(){return this.expiresTime;}

Cookie.prototype.setDomain=function(domain){this.domain=domain;}
Cookie.prototype.getDomain=function(){return this.domain;}

Cookie.prototype.setPath=function(path){this.path=path;}
Cookie.prototype.getPath=function(){return this.path;}

Cookie.prototype.Write=function(v)
{
    if(v!=null)
    {
        this.setValue(v);
    }
    var ck=this.key+"="+this.value;
    if(this.expiresTime!=null)
    {
        try
        {
            ck+=";expires="+this.expiresTime.toUTCString();;
        }
        catch(err)
        {
            alert("expiresTime参数错误");
        }
    }
    if(this.domain!=null)
    {
        ck+=";domain="+this.domain;
    }
    if(this.path!=null)
    {
        ck+=";path="+this.path;
    }
    if(this.secure!=null)
    {
        ck+=";secure";
    }
    document.cookie=ck;
}
Cookie.prototype.Read=function()
{
    try
    {
        var cks=document.cookie.split("; ");
        var i=0;
        for(i=0;i <cks.length;i++)
        {
            var ck=cks[i];
            var fields=ck.split("=");
            if(fields[0]==this.key)
            {
                this.value=fields[1];
                return (this.value);
            }
        }
        return null;
    }
    catch(err)
    {
        alert("cookie读取错误");
        return null;
    }
}