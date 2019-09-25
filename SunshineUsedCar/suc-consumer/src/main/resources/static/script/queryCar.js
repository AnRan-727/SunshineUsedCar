var pinpaiId,/*品牌id*/
    chexiId,/*车系id*/
    cheling,/*车龄*/
    chexingId,/*车型id*/
    zuoweiId,/*座位id*/
    licheng,/*里程数*/
    biansuxiangId,/*变速箱id*/
    pailiang,/*排量*/
    paifangId,/*排放标准id*/
    ranyouId,/*燃油id*/
    yanseId,/*颜色id*/
    guobieId,/*国别id*/
    zuigaojiage,/*最高价*/
    zuidijiage,/*最低价*/
    superValue,/*超值*/
    urgentSale,/*急售*/
    newCar,/*准新车*/
    transitive,/*可迁全国*/
    pageNum,/*页数*/
    paixu;/*排序*/

/*$("#chaozhi").toggle(function () {
    superValue = 1 ;
    ajaxQuery();
},function () {
    superValue = 0 ;
    ajaxQuery();
})
$("#jishou").toggle(function () {
    urgentSale = 1 ;
    ajaxQuery();
},function () {
    urgentSale = 0 ;
    ajaxQuery();
})
$("#zhunxinche").toggle(function () {
    newCar = 1 ;
    ajaxQuery();
},function () {
    newCar = 0 ;
    ajaxQuery();
})
$("#keqianquanguo").toggle(function () {
    transitive = 1 ;
    ajaxQuery();
},function () {
    transitive = 0 ;
    ajaxQuery();
})
$("#MoRenPX").click(function () {
    paixu = 0;
    ajaxQuery();
})
$("#ZuiXinPX").click(function () {
    paixu = 2;
    ajaxQuery();
})
$("#JiaGePX").toggle(function () {
    paixu = 3;
    ajaxQuery();
},function () {
    paixu = 4;
    ajaxQuery();
})
$("#CheLingPX").toggle(function () {
    paixu = 5;
    ajaxQuery();
},function () {
    paixu = 6;
    ajaxQuery();
})
$("#LiChePX").toggle(function () {
    paixu = 7;
    ajaxQuery();
},function () {
    paixu = 8;
    ajaxQuery();
})

$("#PinPaiBuXian").click(function () {
    pinpaiId = null;
    chexiId = null;
    $("#pp").remove();
    $("#cx").remove();
    ajaxQuery();
    /!*品牌*!/
    $.post("/index/queryVehicle",function(result){
        $("#CheXiBuXian").siblings().html();
        //获取车系的List集合
        var chexi = result[0];
        $.each(chexi,function (index,data) {
            if(index <= 18){
                $("#CheXiBuXian").after("<a href='javascript:' onclick='CheXi("+data.vehicleID+")'>"+data.vehicleName+"</a>");
            }
        })
    })
})
$("#CheXiBuXian").click(function () {
    chexiId = null;
    $("#cx").remove();
    ajaxQuery();
    $.post("/index/queryVehicle",function(result){
        $("#CheXiBuXian").siblings().html();
        //获取车系的List集合
        var chexi = result[0];
        $.each(chexi,function (index,data) {
            if(index <= 18){
                $("#CheXiBuXian").after("<a href='javascript:' onclick='CheXi("+data.vehicleID+")'>"+data.vehicleName+"</a>");
            }
        })
    })
})
$("#JiaGeBuXian").click(function () {
    zuigaojiage = null;
    $("#jg").remove();
    ajaxQuery();
})
$("#CheLingBuXian").click(function () {
    cheling = null;
    $("#cl").remove();
    ajaxQuery();
})
$("#CheXingBuXian").click(function () {
    chexingId = null;
    $("#cxx").remove();
    ajaxQuery();
})
$("#ZuoWeiBuXian").click(function () {
    zuoweiId = null;
    $("#zw").remove();
    ajaxQuery();
})
$("#LiChengBuXian").click(function () {
    licheng = null;
    $("#lc").remove();
    ajaxQuery();
})
$("#BianXuBuXian").click(function () {
    biansuxiangId = null;
    $("#bs").remove();
    ajaxQuery();
})
$("#PaiLianBuXian").click(function () {
    pailiang = null;
    $("#pl").remove();
    ajaxQuery();
})
$("#PaiFangBuXian").click(function () {
    paifangId = null;
    $("#pf").remove();
    ajaxQuery();
})
$("#RanYouBuXian").click(function () {
    ranyouId = null;
    $("#ry").remove();
    ajaxQuery();
})
$("#YanSeBuXian").click(function () {
    yanseId = null;
    $("#ys").remove();
    ajaxQuery();
})
$("#GuoBieBuXian").click(function () {
    guobieId = null;
    $("#gb").remove();
    ajaxQuery();
})*/
/*$("a[no]").click(function () {
    var no = $("a[no]").attr("no");
    if(no <= 0){
        no = 1;
    }
    var span = $("span[no]").attr("no");
    if(no >= span){
        no = span;
    }
    pageNum = no;
    ajaxQuery();
})*/
function PinPai(i) {
    $("#pp").remove();
    pinpaiId = i;
    var text = $("a[onclick='PinPai("+i+")']:first").text();
    $("#DianQianShaiXuan").after("<a href='javascript:' id='pp' class='sel-a'>"+text+"</a>");
    ajaxQuery();
    queryCheXi(i);
}
function CheXi(i){
    $("#cx").remove();
    chexiId = i;
    var text = $("a[onclick='CheXi("+i+")']").text();
    $("#DianQianShaiXuan").after("<a href='javascript:' id='cx' class='sel-a'>"+text+"</a>");
    ajaxQuery();
}
function CheXing(i){
    $("#cxx").remove();
    chexingId = i;
    var text = $("a[onclick='CheXing("+i+")']").text();
    $("#DianQianShaiXuan").after("<a href='javascript:' id='cxx' class='sel-a'>"+text+"</a>");
    ajaxQuery();
}
function ZuoWei(i){
    $("#zw").remove();
    zuoweiId = i;
    var text = $("a[onclick='ZuoWei("+i+")']").text();
    $("#DianQianShaiXuan").after("<a href='javascript:' id='zw' class='sel-a'>"+text+"</a>");
    ajaxQuery();
}
function BianSu(i){
    $("#bs").remove();
    biansuxiangId = i;
    var text = $("a[onclick='BianSu("+i+")']").text();
    $("#DianQianShaiXuan").after("<a href='javascript:' id='bs' class='sel-a'>"+text+"</a>");
    ajaxQuery();
}
function PaiFang(i){
    $("#pf").remove();
    paifangId = i;
    var text = $("a[onclick='PaiFang("+i+")']").text();
    $("#DianQianShaiXuan").after("<a href='javascript:' id='pf' class='sel-a'>"+text+"</a>");
    ajaxQuery();
}
function RanYou(i){
    $("#ry").remove();
    ranyouId = i;
    var text = $("a[onclick='RanYou("+i+")']").text();
    $("#DianQianShaiXuan").after("<a href='javascript:' id='ry' class='sel-a'>"+text+"</a>");
    ajaxQuery();
}
function YanSe(i){
    $("#ys").remove();
    yanseId = i;
    var text = $("a[onclick='YanSe("+i+")']").text();
    $("#DianQianShaiXuan").after("<a href='javascript:' id='ys' class='sel-a'>"+text+"</a>");
    ajaxQuery();
}
function GuoBie(i){
    $("#gb").remove();
    guobieId = i;
    var text = $("a[onclick='GuoBie("+i+")']").text();
    $("#DianQianShaiXuan").after("<a href='javascript:' id='gb' class='sel-a'>"+text+"</a>");
    ajaxQuery();
}
function CheLing(i){
    $("#cl").remove();
    cheling = i;
    var text = $("a[onclick='CheLing("+i+")']").text();
    $("#DianQianShaiXuan").after("<a href='javascript:' id='cl' class='sel-a'>"+text+"</a>");
    ajaxQuery();
}
function PaiLiang(i){
    $("#pl").remove();
    pailiang = i;
    var text = $("a[onclick='PaiLiang("+i+")']").text();
    $("#DianQianShaiXuan").after("<a href='javascript:' id='pl' class='sel-a'>"+text+"</a>");
    ajaxQuery();
}
function JiaGe(a,b) {
    $("#jg").remove();
    zuidijiage = a;
    zuigaojiage = b;
    var text = $("a[onclick='JiaGe("+a+","+b+")']").text();
    $("#DianQianShaiXuan").after("<a href='javascript:' id='jg' class='sel-a'>"+text+"</a>");
    ajaxQuery();
}



/*根据品牌查车系*/
function queryCheXi(i) {
    $.post("/index/queryVehicleByPinPai","id="+i,function (result) {
        $(".chexiClass").remove();
        $.each(result,function (index,data) {
            $("#CheXiBuXian").after("<a href='javascript:' class='chexiClass' onclick='CheXi("+data.vehicleID+")'>"+data.vehicleName+"</a>");
        })
    })
}
function ajaxQuery(){
    $.ajax({
        type:'post',
        url:'/index/ajaxQueryAllCar',
        data:{
            "pinpaiId":pinpaiId,/*品牌id*/
            "chexiId":chexiId,/*车系id*/
            "cheling":cheling,/*车龄*/
            "chexingId":chexingId,/*车型id*/
            "zuoweiId":zuoweiId,/*座位id*/
            "licheng":licheng,/*里程数*/
            "biansuxiangId":biansuxiangId,/*变速箱id*/
            "pailiang":pailiang,/*排量*/
            "paifangId":paifangId,/*排放标准id*/
            "ranyouId":ranyouId,/*燃油id*/
            "yanseId":yanseId,/*颜色id*/
            "guobieId":guobieId,/*国别id*/
            "zuigaojiage":zuigaojiage,/*最高价*/
            "zuidijiage":zuidijiage,/*最低价*/
            "superValue":superValue,/*超值*/
            "urgentSale":urgentSale,/*急售*/
            "newCar":newCar,/*准新车*/
            "transitive":transitive,/*可迁全国*/
            "paixu":paixu,
            "pageNum":pageNum
        },
        success:function(result){

            $("#carList").html("");
            $(".shuliang").html(result.pageTotal);
            $.each(result.list,function (index,val) {
                var sv = val.superValue;
                var us = val.urgentSale;
                var nw = val.newCar;
                var sv2 = "",us2 = "",nw2 = "";
                if(sv == 1){
                    sv2 = "<em class='tag-yellow'>超值</em>";
                }
                if(us == 1){
                    us2 = "<em class='tag-red'>急售</em>";
                }
                if(nw == 1){
                    nw2 = "<em class='tag-green'>准新车</em>";
                }
                $("#carList").append("<li>" +
                    "      <div class='list-infoBox'> <a title='"+val.model+"'   class='imgtype' href='/index/queryCarInfo?carId="+val.carID+"'> <img width='290' height='194' src='/img/car/"+val.carPicturesURL+"' alt='"+val.model+"'> </a>" +
                    "        <p class='infoBox'> <a  title='"+val.topCity+"' href='infor.html'   class='info-title'>"+val.model+"</a> </p>" +
                    "        <p class='fc-gray'> <span class='tag-gray'>"+val.topCity+"</span> <span class=''>"+val.placingTime+"上牌</span> <em class='shuxian'>|</em> 行驶"+val.mileage+" </p>" +
                    "        <p class='priType-s'> "+us2+" "+nw2+" "+sv2+" <span> <i class='fc-org priType'> "+val.presentPrice+"万 </i> </span> <s>"+val.originalPrice+"万</s> </p>" +
                    "      </div>" +
                    "    </li>");
            })
            var a = result.pageNumber - 1;
            var b = result.pageNumber + 1;
            $(".pages").html("    <a href='javascript:void(0)' no='1' onclick='fenye(1)' class='fenye'>首页</a>" +
                "    <a href='javascript:void(0)' no='"+ a +"' onclick='fenye("+a+")' class='fenye'>上一页</a>" +
                "    第<span>"+result.pageNumber+"</span>页/共<span no='"+result.pageCount+"'>"+result.pageCount+"</span>页" +
                "    <a href='javascript:void(0)' no='"+ b +"' onclick='fenye("+b+")' class='fenye'>下一页</a>" +
                "    <a href='javascript:void(0)' no='"+result.pageCount+"' onclick='fenye("+result.pageCount+")' class='fenye'>尾页</a>");
        }
    })
}

function fenye(e){

    if(e <= 0){
        e=1;
    }
    var span = $("span[no]").attr("no");
    if(e >= span){
        e = span;
    }
    pageNum = e;
    ajaxQuery();


}




