package Pojo;

//数据存储，同时进行单个文件的数据拼接
public class DataContent {
    private String downloadUrl;
    private String savePathHeader;
    private String dictPath;
    private String saveFilePath;

    public DataContent(String downloadUrl,String savePathHeader){
        if(downloadUrl == null || downloadUrl.isEmpty() || savePathHeader == null || savePathHeader.isEmpty()){
            return;
        }
        String[] parts = downloadUrl.split("/");
        this.dictPath = savePathHeader + "\\" + parts[parts.length - 3] + "\\" + parts[parts.length - 2];
        this.saveFilePath = this.dictPath +"\\"+parts[parts.length-1];
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getSavePathHeader() {
        return savePathHeader;
    }

    public String getDictPath() {
        return dictPath;
    }

    public String getSaveFilePath() {
        return saveFilePath;
    }

    @Override
    public String toString() {
        return "DataContent{" +
                "downloadUrl='" + downloadUrl + '\'' +
                ", savePathHeader='" + savePathHeader + '\'' +
                ", dictPath='" + dictPath + '\'' +
                ", saveFilePath='" + saveFilePath + '\'' +
                '}';
    }
}
