import java.io.*;

public class Test {


    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\hezu.csv"));//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用，如果需要，注释掉
            String line = null;
            int count = 0;
            String path = "C:\\Users\\Administrator\\Desktop\\h\\";

            while ((line = reader.readLine()) != null) {
                if (line.length() > 10) {
                    String name = path + (count++) + ".txt";
                    writeTxt(name, line);

                }
            }
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeTxt(String txtPath, String content) {
        OutputStreamWriter oStreamWriter = null;
        File file = new File(txtPath);
        try {
            if (file.exists()) {
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }

            oStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
            oStreamWriter.append(content);
            oStreamWriter.flush();
            oStreamWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
