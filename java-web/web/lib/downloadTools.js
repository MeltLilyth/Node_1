var http = require('http');
var url = require('url');
var util = require('util');


http.createServer(function(req,res){
    // 允许跨域请求
    res.header("Access-Control-Allow-Origin","*");
    res.header("Access-Control-Allow-Headers","content-type");
    res.header("Access-Control-Allow-Methods","DELETE,PUT,POST,GET,OPTIONS");
    

    // res.end(util.inspect(url.parse(req.url, true)));
    res.end();
}).listen(8089);

