

// 加载数据字典下拉框
function loadSelect(url,code, e){
    $.get(url,"code="+code,function(result){
        e.append("<option value='-1'>--请选择--</option>");
        $.each(result, function (index,item) {
            e.append("<option value='"+item.valueId+"'>"+item.valueName+"</option>");
        })
    })
}

// 加载普通
function loadH(url,e){
    $.post(url,function(result){
        e.append("<option value='-1'>--请选择--</option>");
        $.each(result, function (index,item) {
            e.append("<option value='"+item.mrId+"'>"+item.mrName+"</option>");
        })
    })
}

// 加载普通
function loadHXP(url,e,id,name){
    $.post(url,function(result){
        e.append("<option value='-1'>--请选择--</option>");
        $.each(result, function (index,item) {
            e.append("<option value='"+item[id]+"'>"+item[name]+"</option>");
        })
    })
}


// 加载列表
function loadMembers(FormId,url,pageNumController,pageNum,strSZ,entitySZ){
    // 表单序列化
    var data = $("#"+FormId).serialize();
    $.post(url,data+"&"+pageNumController+"="+pageNum,function (result) {

        if(result.list!=""){
        var list = result.list;

        // 加载分页
        pageInfoUtil(result);
        $("#listContainer").html("");

        var html = "";
        html +=  "<table class='table table-striped table-hover'>"
            +"<tr class='info'>"
        for(var i = 0; i<strSZ.length; i++){
            html+= "<th>"+strSZ[i]+"</th>";
        }
        html+="</tr>";
        $.each(list,function (index,item) {
            html += "<tr >"
                var id;
                var name;
                for(var a = 0; a<entitySZ.length; a++){
                    var attr = entitySZ[a];
                    id = item[entitySZ[0]];
                    name = item[entitySZ[1]];
                    html+="<td>"+item[attr]+"</td>";
                }
                html+="<td class='col-md-1 '>"
                +"<a href='javascript:void(0)' onclick='detailInformation("+id+")'><span class='glyphicon glyphicon-eye-open ' title='查看' aria-hidden='true'></span></a> "
                +"<a href='javascript:void(0)' onclick='updateInformation("+id+")'><span class='glyphicon glyphicon-pencil ' title='编辑' aria-hidden='true'></span></a> "
                +"<a href='javascript:void(0)' onclick='deleteInformation("+id+",\""+name+"\")'><span class='glyphicon glyphicon-trash' title='删除' aria-hidden='true'></span></a> "
                +"</td>"
                +"</tr>";
        })
        html += "</table>";
        $("#listContainer").html(html);
        }else{
            $("#listContainer").html("<h3 align='center' style='color: red'><span class='glyphicon glyphicon-warning-sign' aria-hidden='true'></span> 没有数据！</h3>");
            $("ul[id='fengye']").html("");
        }
    })
}




// 加载分页
function pageInfoUtil(result) {

    $("ul[id='fengye']").html("");
    var pagePre = 1;
    if(result.pageNumber<=1){
        pagePre = 1;
    }else{
        pagePre = Number(result.pageNumber)-1;
    }
    var pageNext;
    if(result.pageNumber>=result.pageCount){
        pageNext = result.pageCount;
    }else{
        pageNext = Number(result.pageNumber)+1;
    }
    var pageHtml = "";
    pageHtml+="<li no='"+result.pageNumber+"'>"
        +"<a href='javascript:void(0)' onclick='clickPage("+pagePre+")'  aria-label='Previous' >"
        +"<span aria-hidden='true'>&laquo;</span>"
        +"</a>"
        +"</li>";

    $.each(result.navigatepageNums,function(index,item) {
        if(result.pageNumber==item||result.pageCount==1){
            pageHtml+="<li class='active' th:no='"+item+"'><a href='javascript:void(0)' onclick='clickPage("+item+")' >"+item+"</a></li>";
        }else{
            pageHtml+="<li class='' th:no='"+item+"'><a href='javascript:void(0)' onclick='clickPage("+item+")'>"+item+"</a></li>";
        }
    })
    pageHtml+="<li th:no='"+pageNext+"'><a href='javascript:void(0)' aria-label='Next' onclick='clickPage("+pageNext+")'>"
        +"<span aria-hidden='true'>&raquo;</span>"
        +"</a>"
        +"</li>";
    $("ul[id='fengye']").html(pageHtml);

}