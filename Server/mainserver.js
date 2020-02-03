var express = require("express");
var url = require("url");
var fileTools = require("./File");
var downloadTools = require("./File");
var app = express();

app.get("/download.do",function(req,res){
    //允许跨域
    res.header("Access-Control-Allow-Origin","*");
    res.header("Access-Control-Allow-Headers","content-type");
    res.header("Access-Control-Allow-Methods","DELETE,PUT,POST,GET,OPTIONS");

    var datalist = url.parse(req.url,true).query;
    if(datalist["url"] != undefined){
        let resourceUrl = datalist["url"];
        if(!fileTools.isStartDownload(resourceUrl)){
            fileTools.createDir(resourceUrl);
        } 
    }
    res.end();
});

var server = app.listen(8081,function(){
    console.log("server is already start, PORT:8081");
});
