import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
    @org.junit.Test
    public void Run1(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("G:\\demo.jpg"));
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://www.qqma.com/imgpic2/cpimagenew/2018/4/5/6e1de60ce43d4bf4b9671d7661024e7a.jpg").openConnection();
//            httpURLConnection.setConnectTimeout(3*1000);
//          httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] data = new byte[inputStream.available()];
            int length;
            while((length = inputStream.read(data)) != -1){
                fileOutputStream.write(data);
            }
            inputStream.close();
            fileOutputStream.close();

//            System.out.println(httpURLConnection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void Run2(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("G:\\04d5ea186.f5709.json")));
            StringBuffer stringBuffer = new StringBuffer();
            String length;
            while((length = bufferedReader.readLine())!= null){
                stringBuffer.append(length);
            }
            System.out.println(stringBuffer.toString().equals(GetNetContent()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private String GetNetContent(){
        try{
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://xcx.youletd.com/xcx/wechat/sniperbullet/v112/res/import/04/04d5ea186.f5709.json").openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String length;
            while((length = bufferedReader.readLine())!= null){
                stringBuffer.append(length);
            }
            return stringBuffer.toString();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
