package Service.Tools;

import Pojo.DataContent;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTools implements Runnable{
    private DataContent dataContent;
    private String logPath = "\\Log.txt";

    //在当前线程每一次执行之前需要调用进行数据初始化
    public DownloadTools SetDataContent(String downloadUrl,String saveFileHeader){
        this.dataContent = new DataContent(downloadUrl,saveFileHeader);
        return this;
    }

    //这个目前大概率用不上
    public DownloadTools SetDataContent(DataContent dataContent){
        this.dataContent = dataContent;
        return this;
    }

    //检查是否为有效连接
    private boolean checkConnection(){
        if(dataContent == null || dataContent.getDownloadUrl().isEmpty()){
            return false;
        }
        try{
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(dataContent.getDownloadUrl()).openConnection();
            httpURLConnection.setConnectTimeout( 3* 1000);
            httpURLConnection.connect();
            return httpURLConnection.getResponseCode() == 200 ;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //检查文件是否存在，其实也可以不用加入
    private boolean checkFileExits(){
        if(dataContent == null || dataContent.getDictPath().isEmpty()){
            return false;
        }
        File dictfile = new File(dataContent.getDictPath());
        if(!dictfile.exists()){
            dictfile.mkdirs();
            return true;
        }
        File target = new File(dataContent.getSaveFilePath());
        return !target.exists();
    }

    @Override
    public void run() {
        if(checkFileExits() && checkConnection()){
            download();
        }
    }

    //下载
    private void download(){
        try{
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(dataContent.getDownloadUrl()).openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(dataContent.getSaveFilePath());
            byte[] data = new byte[inputStream.available()];
            int length;
            while((length = inputStream.read(data))!= -1){
                fileOutputStream.write(data);
            }
            inputStream.close();
            fileOutputStream.close();

            writeLog(true);
        }
        catch(Exception e){
            e.printStackTrace();
            writeLog(false);
        }
    }

    //写log
    private void writeLog(boolean flag){
        try{
            File file = new File(dataContent.getSavePathHeader()+logPath);
            if(!file.exists()){
                file.createNewFile();
            }
            String content = flag? "URL: "+dataContent.getDownloadUrl()+" is download success \r\n":"Fail to download URL: "+dataContent.getDownloadUrl()+"\r\n";
            new FileTools().GetFileTools(file,content).write();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
