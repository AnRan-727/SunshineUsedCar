window.onload=pinpaiUL();

function pinpaiUL(){
    $.post("/index/queryVehicle",function(result){
        //获取品牌的Map集合 并遍历
        var pinpai = result[1];
        $.each(pinpai,function (key,value) {
            //遍历Map中的value>>List集合
            //把首字母品牌放进去

            $("#youDian").append("<dl class='fore"+ key +"'><dt><a href='javascript:'>"+key+"</a></dt><dd id='dd"+key+"'></dd></dl>");

            $.each(value,function (i,val) {
                console.log("val>>"+val);
                //把8个品牌放到ul里
                if(val.vehicleID <= 8){
                    $("#pinPaiUL").append("<li><a href='javascript:'>"+val.vehicleName+"</a></li>");
                }
                $("#dd"+key).append("<em><a href=''>"+val.vehicleName+"</a></em>");
            })
        })
    })
}
