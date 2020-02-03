//导入对象
var fs = require("fs");
var request = require("request");
var http = require("http");
var path = require("path");

//创建文件夹，判断是否下载，连接是否畅通，下载功能 
//测试使用的url： https://xcx.youletd.com/xcx/wechat/sniperbullet/v112/res/import/04/04d5ea186.f5709.json

var fileTools = { 
    isStartDownload:function(fileUrl){
        var urlList = fileUrl.split("/");
        if(!fs.existsSync(path.join(__dirname,"/res")) || !fs.existsSync(path.join(__dirname,"/res/"+urlList[urlList.length-3])) || !fs.existsSync(path.join(__dirname,"./res/"+urlList[urlList.length-3]+"/"+urlList[urlList.length-2]))){
            return false;
        }
        else{
            return fs.existsSync(path.join(__dirname,"/res/"+urlList[urlList.length-3]+"/"+urlList[urlList.length-2]+"/"+urlList[urlList.length-1]));
        }
    },
    //创建文件夹
    createDir:function(fileUrl){        
        var urlList = fileUrl.split("/");
        if(!fs.existsSync(path.join(__dirname,"/res"))){
            fs.mkdirSync(path.join(__dirname,"/res"));    
        }
        if(!fs.existsSync(path.join(__dirname,"/res/"+urlList[urlList.length-3]))){
            fs.mkdirSync(path.join(__dirname,"/res/"+urlList[urlList.length-3]));
        }
        if(!fs.existsSync(path.join(__dirname,"/res/"+urlList[urlList.length-3]+"/"+urlList[urlList.length-2]))){
            fs.mkdirSync(path.join(__dirname,"/res/"+urlList[urlList.length-3]+"/"+urlList[urlList.length-2]));
        }
        this.download(fileUrl,fileUrl.split("/"));
    },

    //下载文本 -- 测试结果无问题[成功] -- 图片需要待定验证
    //2.1更新测试结果--可以下载本地服务器的图片
    download:function(fileUrl,urlList){
        console.log("start download");
        var savePath = path.join(__dirname,"/res/"+urlList[urlList.length-3]+"/"+urlList[urlList.length-2]+"/"+urlList[urlList.length-1]);
        var writeSteam = fs.createWriteStream(savePath);
        request(fileUrl).pipe(writeSteam).on("error",()=>{
            if(fs.existsSync(path)){
                fs.unlinkSync(path);
            }
        }).on("finish",()=>{
            //下载成功之后判断是不是404---如果是404请求会下载完整的404 h5页面代码，只需要判断404字段即可
            //主要是实在不想在前端用jquery来写url判断，这样导入的东西太多了
            let data = "";
            fs.createReadStream(savePath).on("data",function(str){
            data+=str;
            }).on("end",function(){
                if(data.includes("404")){
                    s.unlinkSync(savePath);
                    console.log("Bad Quest,Invaild URL: "+fileUrl+",  and download file has been deleted");
                }
                else{
                    console.log("Vaild URL: "+fileUrl+" , file is download success and SavePath: "+savePath);
                }
            });
        });    
    }
}

module.exports = fileTools;
