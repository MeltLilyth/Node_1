var express = require("express");
var url = require("url");

var fileTools = require("./File");
var app = express();

//分支请求
app.get("/download.do",function(req,res){
    //允许跨域
    res.header("Access-Control-Allow-Origin","*");
    res.header("Access-Control-Allow-Headers","content-type");
    res.header("Access-Control-Allow-Methods","DELETE,PUT,POST,GET,OPTIONS");

    var datalist = url.parse(req.url,true).query;
    //请求格式：xhr.open("GET","http://localhost:8081/download.do?prefix=***&midfix=*****")
    
    res.end();
});

//测试json文件下载
app.get("/TestConnect.do",function(req,res){
    res.header("Access-Control-Allow-Origin","*");
    res.header("Access-Control-Allow-Headers","content-type");
    res.header("Access-Control-Allow-Methods","DELETE,PUT,POST,GET,OPTIONS");

    var testUrl = "https://xcx.youletd.com/xcx/wechat/sniperbullet/v112/res/import/04/04d5ea186.f5709.json"; //有效url
    var testUrl1 = "https://xcx.youletd.com/xcx/wechat/sniperbullet/v112/res/import/04/04d5ea187.f5709.json";//无效url
    
    fileTools.isConnectSuccess(testUrl,testUrl.split("/"));
    fileTools.isConnectSuccess(testUrl1,testUrl1.split("/"));
});

// 测试下载图片
app.get("/TestDownloadPic.do",function(req,res){
    res.header("Access-Control-Allow-Origin","*");
    res.header("Access-Control-Allow-Headers","content-type");
    res.header("Access-Control-Allow-Methods","DELETE,PUT,POST,GET,OPTIONS");

    let picUrl = "http://localhost:8080/Aimer_war_exploded/lib/77875196_p0.jpg";
    if(!fileTools.isStartDownload(picUrl)){
        fileTools.downloadJson(picUrl,picUrl.split("/"));
    }
});

//创建服务器 -- 默认是8080端口,如果开启其他服务器不能使用8080
//关闭服务器的需要在管理器中关闭node相关的进程
var server = app.listen(8081,function(){
    console.log("Server is already start, Port:8081");
});