package org.ike.pms.api.essay;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import io.cloudsoft.winrm4j.client.WinRmClientContext;
import io.cloudsoft.winrm4j.winrm.WinRmTool;
import io.cloudsoft.winrm4j.winrm.WinRmToolResponse;
import org.apache.http.client.config.AuthSchemes;

import java.io.*;
import java.util.Map;

public class commonUtil {

    public static void main(String[] args) {

    }

    /**
     * 远程访问windows
     * @param hostname 主机访问域名或IP地址
     * @param username 主机访问用户名
     * @param password 主机访问密码
     */
    public static void windows(String hostname, String username, String password) {
        WinRmClientContext context = WinRmClientContext.newInstance();
        WinRmTool.Builder builder = WinRmTool.Builder.builder(hostname, username, password);
        builder.setAuthenticationScheme(AuthSchemes.NTLM);
        builder.port(5985);
        builder.useHttps(false);

        builder.context(context);
        WinRmTool tool =  builder.build();
        tool.setOperationTimeout(5000L);
        System.out.println("========");
        String command = "dir";
        WinRmToolResponse resp = tool.executeCommand(command);
        System.out.println(resp.getStatusCode());
        String out = resp.getStdOut();
        System.out.println(out);
        context.shutdown();
    }

    /**
     * ssh远程连接linux系统
     * @param hostname 主机访问域名或IP地址
     * @param username 主机访问用户名
     * @param password 主机访问密码
     * @throws IOException
     */
    public static void ssh(String hostname, String username, String password) throws IOException {
        try {
            Connection conn = new Connection(hostname);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if (!isAuthenticated) {
                throw new IOException("Authentication failed");
            }
            Session sess = conn.openSession();
            sess.requestPTY("bash");
            sess.startShell();
            InputStream stdout = new StreamGobbler(sess.getStdout());
            InputStream stderr = new StreamGobbler(sess.getStderr());
            BufferedReader stdoutReader = new BufferedReader(
                    new InputStreamReader(stdout));
            BufferedReader stderrReader = new BufferedReader(
                    new InputStreamReader(stderr));
            BufferedReader inputReader = new BufferedReader(
                    new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(sess.getStdin());
            String temp = "";
            while (!temp.equals("exit")) {
                System.out.print("[root@vmone ~]#");
                temp = inputReader.readLine();
                out.println(temp);
                out.flush();
                String line;
                while ((line = stdoutReader.readLine()) != null) {
//                    if (line.length() == 0) {// line等于null从来不会发生，导致程序卡在这里
//                        continue;
//                    } else{
//                        System.out.println(line);
//                    }
                    if(line.length()!=0) System.out.println(line);
                }
                System.out.println("Here is the output from stderr:");
                while (true) {
                    line = stderrReader.readLine();
                    if (line == null)
                        break;
                    System.out.println(line);
                }
            }
            System.out.println("ExitCode: " + sess.getExitStatus());
            sess.close();
            conn.close();
            System.out.println("close connection");
        } catch (IOException e) {
            e.printStackTrace(System.err);
            System.exit(2);
        }
    }

    /**
     * 执行cmd命令
     * @param cmd cmd命令
     * @throws IOException IO异常
     */
    public static void cmd(String cmd) throws IOException {
        Process p = Runtime.getRuntime().exec(cmd);
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "GB2312"));
        String result;
        while ((result = br.readLine()) != null) {
            System.out.println(result);
        }
    }

    /**
     * 获取计算机的系统信息
     */
    public static void environment() {
        Map<String, String> env = System.getenv();
        for (Map.Entry entry : env.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * 测试String的常量与变量的内存地址
     */
    public static void testFinalString() {
        String str = "abc";
        String str1 = "ab" + "c";
        System.out.println(str == str1);

        String str2 = "ab";
        String str3 = str2 + "c";
        System.out.println(str == str3);

        final String str4 = "ab";
        String str5 = str4 + "c";
        System.out.println(str == str5);
    }
}
