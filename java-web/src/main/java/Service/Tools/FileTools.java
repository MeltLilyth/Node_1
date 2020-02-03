package Service.Tools;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class FileTools {
    private String content;
    private File file;

    //用于读文件或者其他操作
    public FileTools GetFileTools(File file){
        this.file = file;
        return this;
    }

    //用于写文件
    public FileTools GetFileTools(File file,String content){
        this.file = file;
        this.content = content;
        return this;
    }

    //写文件的进程(主要是用于请求数据输入，如果是写log的话可以单独调用)
    public void StartThread(){
        if(content != null || !content.isEmpty() && file != null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    write();
                }
            }).run();
        }
    }

    //创建文件
    public File create(boolean flag,String path){
        File file = new File(path);
        if(!file.exists()){
            try {
                if(flag) {
                    file.createNewFile();
                }
                else {
                    file.mkdirs();
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return file;
    }

    //写文件
    public void write(){
        try{
            if(file != null){
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(this.content);
                bufferedWriter.close();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //读文件
    public List<String> read(){
        if(file != null){
            StringBuffer stringBuffer = new StringBuffer();
            try{
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String length;
                while((length = bufferedReader.readLine())!= null){
                    stringBuffer.append(length);
                }
                bufferedReader.close();
            }
            catch(Exception e){
                e.printStackTrace();
                return null;
            }
            return Arrays.asList(stringBuffer.toString().split("_"));
        }
        return null;
    }
}
