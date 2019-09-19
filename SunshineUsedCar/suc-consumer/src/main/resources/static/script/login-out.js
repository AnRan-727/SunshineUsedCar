/*注册跳转加验证  */
function zhuche() {

    var phone = $("#phone").val();
    if (!(/^1[34578]\d{9}$/.test(phone))) {
        $("#tishi").html("手机号码有误，请重填");
        return false;
    } else {
        $("#tishi").html("");
    }

    var yanzhengma =$("#yanzhengma").val;
    if(yanzhengma ==''){
        $("#tishi").html("验证码不能为空");
    }else {
        $("#tishi").html("");
    }
    if(yanzhengma != 235235){
        $("#tishi").html("验证码错误");
    }else {
        $("#tishi").html("");
    }

    var username = $("#zhuchename").val();
    if (username == null || username == '') {
        $("#tishi").html("姓名不能为空");
        return false;
    } else {
        $("#tishi").html("");
    }

    var sex = $('input:radio[name="userGender"]:checked').val();
    if (sex == null) {
        $("#tishi").html("性别不能为空，请您选择");
        return false;
    } else {
        $("#tishi").html("");
    }

    var password = $("#zhuchepassword").val();
    if (password.length < 4) {
        $("#tishi").html("密码不能小于四位，请重新输入");
        return false;
    } else {
        $("#tishi").html("");
    }
    var data = $("#zhucheform").serialize();

    $.post("/carUser/doZhucheController",data,function (result) {

        if(result == "注册失败!"){
            $("#tishi").html(result);
        }else{
            result = JSON.parse(result);
            console.log(result);
            if(result[0].errorcode =='0000'){
                //获取到服务器端给客户端的token
                var token=result[0].data.token;
                //获取生成token的时间
                var genTime=result[0].data.genTime;
                document.cookie="token="+token;
                document.cookie="genTime="+genTime;
                console.log(token);
                $(".p_closed").click();
                $(".top-r").html("您好:<a href='/carUser/toCarUserInfo?userID="+result[1].userID+"' class='carUserName' style='color: #0c7fd1;'>"+result[1].realName+"</a><a href='/carUser/logout'>注销</a>");

            }else {
                $("#tishi").html("登录失败");
            }
        }
    })
}
/*登录跳转和验证  */
function denglu() {
    var loginUsername = $("#loginUsername").val();
    var loginPassword = $("#loginPassword").val();
    if (loginUsername == null || loginUsername == '') {
        $("#logintishi").html("用户名不能为空，请重新输入");
        return false;
    } else {
        $("#logintishi").html("");
    }
    if (loginPassword.length < 4) {
        $("#logintishi").html("密码不能小于四位，请重新输入");
        return false;
    } else {
        $("#logintishi").html("");
    }
    var data=$("#loginform").serialize();
    $.post('/carUser/login',data,function (result) {
        console.log(result);

        if(result[0].errorcode =='0000'){
            //获取到服务器端给客户端的token
            var token=result[0].data.token;
            //获取生成token的时间
            var genTime=result[0].data.genTime;
            $(".p_closed").click();
            document.cookie="token="+token;
            document.cookie="genTime="+genTime;
            $(".top-r").html("您好:<a href='/carUser/toCarUserInfo?userID="+result[1].userID+"' class='carUserName' style='color: #0c7fd1;'>"+result[1].realName+"</a><a href='/carUser/logout'>注销</a>");

        }else {
            $("#logintishi").html("登录失败!");
        }
    });
}
/*手机ajax验证  */
function Userphone(){
    var phone = $("#phone").val();
    $.post("/carUser/ajaxUserphoneController", {
        "phone" : phone
    }, function(returnData) {
        if (!returnData) {
            $("#tishi").html("手机号可以注册！");
        } else {
            $("#tishi").html("手机号已注册，不能重复注册！");
        }
    })
}
function focusHtml(){
    $("#tishi").html("");
}
/*以下为动态验证码模块*/

$(function(){
    var show_num = [];
    draw(show_num);
    $("#canvas").on('click',function(){
        draw(show_num);
    })
    $(".fM").on('click',function(){
        var val = $("input[name='verify']").val().toLowerCase();
        var num = show_num.join("");
        if(val==''){
            $("#tishi").html("请输入验证码");
        }else if(val == num){
            zhuche();
            $(".input-val").val('');
            // draw(show_num);
        }else{
            $("#tishi").html('验证码错误！请重新输入！');
            $(".input-val").val('');
            // draw(show_num);
        }
    })
})
//生成并渲染出验证码图形
function draw(show_num) {
    var canvas_width=$('#canvas').width();
    var canvas_height=$('#canvas').height();
    var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
    var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
    canvas.width = canvas_width;
    canvas.height = canvas_height;
    var sCode = "a,b,c,d,e,f,g,h,i,j,k,m,n,p,q,r,s,t,u,v,w,x,y,z,A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
    var aCode = sCode.split(",");
    var aLength = aCode.length;//获取到数组的长度
    for (var i = 0; i < 4; i++) { //这里的for循环可以控制验证码位数（如果想显示6位数，4改成6即可）
        var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
        // var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
        var deg = Math.random() - 0.5; //产生一个随机弧度
        var txt = aCode[j];//得到随机的一个内容
        show_num[i] = txt.toLowerCase();
        var x = 10 + i * 20;//文字在canvas上的x坐标
        var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
        context.font = "bold 23px 微软雅黑";
        context.translate(x, y);
        context.rotate(deg);
        context.fillStyle = randomColor();
        context.fillText(txt, 0, 0);
        context.rotate(-deg);
        context.translate(-x, -y);
    }
    for (var i = 0; i <= 5; i++) { //验证码上显示线条
        context.strokeStyle = randomColor();
        context.beginPath();
        context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.stroke();
    }
    for (var i = 0; i <= 30; i++) { //验证码上显示小点
        context.strokeStyle = randomColor();
        context.beginPath();
        var x = Math.random() * canvas_width;
        var y = Math.random() * canvas_height;
        context.moveTo(x, y);
        context.lineTo(x + 1, y + 1);
        context.stroke();
    }
}
//得到随机的颜色值
function randomColor() {
    var r = Math.floor(Math.random() * 256);
    var g = Math.floor(Math.random() * 256);
    var b = Math.floor(Math.random() * 256);
    return "rgb(" + r + "," + g + "," + b + ")";
}