function isShow4(tab2_id,div2_id,t_Style,ss)    //
{
   for(var i = 0;i < 4;i++)
   {
      document.getElementById("divs2_"+i).style.display="none";
      document.getElementById("tabs2_"+i).className="li";
          queryCar(i);
   }

   document.getElementById(div2_id).style.display=t_Style;
   document.getElementById(tab2_id).className="hover";
   document.getElementById('11').innerHTML=ss;


}
function queryCar(i){
    var zuiXinShangJia = 0;
    var newCar = 0;
    var urgentSale = 0;
    var superValue = 0;
      if(i == 0){
          zuiXinShangJia = 1;
          newCar = 0;
          urgentSale = 0;
          superValue = 0;
      }else if(i == 1){
          zuiXinShangJia = 0;
          newCar = 0;
          urgentSale = 1;
          superValue = 0;
      }else if(i == 2){
          zuiXinShangJia = 0;
          newCar = 0;
          urgentSale = 0;
          superValue = 1;
      }else if(i == 3){
          zuiXinShangJia = 0;
          newCar = 1;
          urgentSale = 0;
          superValue = 0;
      }else{
          zuiXinShangJia = 0;
          newCar = 0;
          urgentSale = 0;
          superValue = 0;
      }

    $.post("/index/queryCar",
        "zuiXinShangJia="+zuiXinShangJia+"&newCar="+newCar+"&urgentSale="+urgentSale+"&superValue="+superValue,
        function (result) {
            $("#divs2_"+i+" .carList").empty();
            $.each(result,function (a,val) {
               if( a < 8){
                   var time = gettime(val.placingTime);
                   $("#divs2_"+i+" .carList").append("<li><div class='list-infoBox'> <a title='"+val.model+"' target='_blank' class='imgtype' href='/index/queryCarInfo?carId="+val.carID+"'> <img width='290' height='194' src='/img/car/"+val.carPicturesURL+"' alt='"+val.model+"'> </a>" +
                       "            <p class='infoBox'> <a  title='"+val.model+"' href='infor.html' target='_blank' class='info-title'>"+val.model+"</a> </p>" +
                       "            <p class='fc-gray'> <span class='tag-gray'>"+val.topCity+"</span> <span class=''>"+time+"上牌</span> <em class='shuxian'>|</em> 行驶"+val.mileage+" </p>" +
                       "            <p class='priType-s'> <span> <i class='fc-org priType'> "+val.presentPrice+"万 </i> </span> <s>"+val.originalPrice+"万</s> </p>" +
                       "          </div></li>");
               }
            })
    })
}
//时间转换
function gettime(t){
    var _time=new Date(t);
    var   year=_time.getFullYear();//2017
    var   month=_time.getMonth()+1;//7
    /*var   date=_time.getDate();//10
    var   hour=_time.getHours();//10
    var   minute=_time.getMinutes();//56
    var   second=_time.getSeconds();//15*/
    return   year+"年"+month+"月"/*+date+"日   "+hour+":"+minute+":"+second*/;//这里自己按自己需要的格式拼接
}
