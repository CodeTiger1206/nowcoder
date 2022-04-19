package com.nowcoder.community;

import java.io.IOException;

/**
 * @Program: community
 * @Auther: ldh
 * @Date: 2022/4/15
 * @Description:
 */
public class WkTest {

    public static void main(String[] args) {
        try {
            String cmd = "/usr/local/bin/wkhtmltoimage --quality 75 https://www.nowcoder.com /Users/liudonghui/IdeaProjects/community/data/wk-img/nowcoder.png";
            Process exec = Runtime.getRuntime().exec(cmd);
            if (exec.waitFor()==0)
                System.out.println("ok!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}
