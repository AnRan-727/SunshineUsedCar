
// js 转换时间
function getDateTime(time, status){
    var date = new Date(time);
    var d;
    if(status==1){
        d = date.getFullYear() + "-" + (Number(date.getMonth())+Number(1)) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
    }else if(status==2){
        d = date.getFullYear() + "-" + (Number(date.getMonth())+Number(1)) + "-" + date.getDate();
    }
    return d;
}