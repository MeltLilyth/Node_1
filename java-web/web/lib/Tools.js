// 案例url：https://xcx.youletd.com/xcx/wechat/sniperbullet/v112/res/import/04/04d5ea186.f5709.json
window.RequestTools = {
    prefix:"https://xcx.youletd.com/xcx/wechat/sniperbullet/v112/",
    /**
     * 参数解释
     * @param {*} midfix url的中间部分[包括md5 part] ,案例：res/import/04/04d5ea186.f5709
     * @param {*} suffix url后缀，目前所知有.png .jpg .mp3 .json[有的资源中可能有.plist，不知道什么东西目前不在考虑范围内]
     */
    sendRequest:function(midfix,suffix){
        let url = this.prefix + midfix + suffix;
        this.download(url).then(()=>{
            console.log("URL: "+url+" is download success");
        }).catch(()=>{
            console.log("URL: "+url+" is download faild");
        });
    },
    download:function(requestUrl){
        return new Promise(function(reject,resolve){
            var xhr = new XMLHttpRequest();
            xhr.open("GET","http://localhost:8081/download.do?url="+requestUrl,true);
            xhr.send();
        });
    }  
};