
// js 转换时间
function getDateTime(time, status){
    // time = Number(time)-Number(480000);

    var date = new Date(time);
    var d;
    if(status==1){
        d = date.getFullYear() + "-" + (Number(date.getMonth())+Number(1)) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
    }else if(status==2){
        d = date.getFullYear() + "-" + (Number(date.getMonth())+Number(1)) + "-" + date.getDate();
    }
    return d;
}


function getTimesLoad(timestamp,status) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    if(status=="ymd"){
        return Y+M+D;
    }else if(status=="hms"){
        return Y+M+D+h+m+s;
    }
}