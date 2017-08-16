
function settime() {
    $("#lefttime").html(timeform(lefttime));
    lefttime--;
    if (lefttime != -1){
        setTimeout(function() { settime() },1000);
    }
}

//时间显示处理函数
function timeform(time){
    min = parseInt(time/60);
    sec =  parseInt(time) - min*60;
    if(min < 10){
        min = "0" + min;
    }
    if(sec < 10){
        sec = "0" + sec;
    }
    value = min + ":" + sec;
    return value;
}