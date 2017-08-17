var auth = null;
//输入编码-登录数据交互
function register() {
    id = document.getElementById("id").value;
    watermachine = document.getElementById("watermachine").value;
    if(id === ""){
        alert("请输入手机号码！");//未输入编号不跳转
    }
    if(watermachine === ""){
        alert("请输入验证码！");//未输入编号不跳转
    }
    else{
        $.ajax({
            type:'POST',
            url:'/watermachine/testcode',
            data: {
                "id":id,
                "watermachine":watermachine,
                "auth":auth
            },
            dataType:'json',
            success:function(data){
                if (data["testpass"] === "yes"){
                    alert("yes");
                    window.location.href = "http://localhost/watermachine/mainpage";
                }
                else{
                    alert("验证码不正确，请重新输入");
                }
            }
        });

    }
}

function sendMessage() {
    id = document.getElementById("id").value;
    if(id === ""){
        alert("请输入手机号码！");//未输入编号不跳转
    }
    $.ajax({
        type:'GET',
        url:'/watermachine/sendmessage',
        data: {
            "id":id
        },
        dataType:'json',
        success:function(data){
            auth = data["auth"];
            alert(auth);
        }
    });
}