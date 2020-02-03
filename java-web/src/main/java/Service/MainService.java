package Service;

import Service.Tools.DownloadTools;
import Service.Tools.FileTools;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class MainService {
    private final String rawsTxtPath = "";
    private final String jsonTxtPath = "";
    private final String saveFileHeader = "";

    private File file;

    private String[] suffixs = {".mp3_",".png_",".jpg_"};

    private File Init(boolean flag){
        return new FileTools().create(flag,flag? jsonTxtPath:rawsTxtPath);
    }

    //处理写入请求
    public void writeFileAction(boolean flag,String content){
        this.file = this.Init(flag);
        if(flag){
            //写入json文件
            content = content+".json_";
            new FileTools().GetFileTools(file,content).write();
        }
        else{
            //写入raws文件信息
            for(String suffix : suffixs){
                new FileTools().GetFileTools(file,content+suffix).write();
            }
        }
    }

    //处理下载请求
    public void downloadAction(boolean flag){
        this.file = Init(flag);
        List<String> urlList = new FileTools().GetFileTools(file).read();
        for(String url:urlList){
            if(url != null && !url.isEmpty()){
                new DownloadTools().SetDataContent(url,saveFileHeader).run();
            }
        }
    }
}
