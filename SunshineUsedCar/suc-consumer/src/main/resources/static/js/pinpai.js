function pinpaiUL(){
    $.post("/index/queryVehicle",function(result){
        //获取品牌的Map集合 并遍历
        var pinpai = result[1];
        $.each(pinpai,function (key,value) {
            //遍历Map中的value>>List集合
            //把首字母品牌放进去
            var fore = 1;
            $("#youDian").append("<dl class='fore"+ fore++ +"'><dt><a href='javascript:'>"+key+"</a></dt></dl>");
            $.each(value,function (i,val) {
                console.log("val>>"+val);
                //把8个品牌放到ul里
                if(val.vehicleID <= 8){
                    $("#pinPaiUL").append("<li><a href='javascript:'>"+val.vehicleName+"</a></li>");
                }

                $(".fore"+fore).append("<dd><em><a href=''>"+val.vehicleName+"</a></em></dd>");
            })
        })
    })
}



function pinpaiUL(){
    alert("aaa");
    $.post("/index/queryVehicle",function(result){

        //获取品牌的Map集合 并遍历
        var pinpai = result[1];
        $.each(pinpai,function (key,value) {
            //遍历Map中的value>>List集合
            //把首字母品牌放进去
            var fore = 0;
            $.each(value,function (i,val) {
                console.log("val>>"+val);
                //把8个品牌放到ul里
                if(val.vehicleID <= 8){
                    $("#pinPaiUL").append("<li><a href='javascript:'>"+val.vehicleName+"</a></li>");
                }

                $("#youDian").append("<dl class='fore"+ fore++ +"'><dt><a href='javascript:'>"+key+"</a></dt>" +

                    "                <dd><em><a href=''>"+val.vehicleName+"</a></em></dd>" +
                    "              </dl>");
            })
        })
    })
}
