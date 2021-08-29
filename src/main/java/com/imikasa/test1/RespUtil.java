package com.imikasa.test1;
import java.io.*;

public class RespUtil {
    public static String doResp(String content_type, boolean isDoc, File file,OutputStream out) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("HTTP/1.1 200 OK \n");
        result.append("Content-Type:"+content_type+" \n");
        result.append("\r\n");
        OutputStreamWriter ous = new OutputStreamWriter(out,"UTF-8");
        if(isDoc){
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String len;
            StringBuilder stringBuilder = new StringBuilder();
            while((len = br.readLine()) != null){
                stringBuilder.append(len).append("\n");
            }
            result.append(stringBuilder.toString());
            br.close();
        }else{
            out.write(result.toString().getBytes());
            FileInputStream fileInputStream = new FileInputStream(file);
            int len;
            byte[] buffer = new byte[1024];
            while((len = fileInputStream.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            ous.flush();
            ous.close();
            fileInputStream.close();
            return "pic is over.....";
        }
        ous.write(result.toString());
        ous.flush();
        ous.close();
        return "doc is over....";
    }
}
