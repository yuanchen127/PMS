package org.ike.pms.pmsfilesystem.service;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.*;
import java.util.List;

/**
 * @author ike
 * @since 2019-02-27 15:11
 */
@Slf4j
public class CopyTest {
    public static void main(String[] args) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    }

    public static void paste(String dirPath) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable t = clipboard.getContents(null); // 获取粘贴板内数据传输对象
        DataFlavor dataFlavors = DataFlavor.javaFileListFlavor;// 数据对象类型
        if (t.isDataFlavorSupported(dataFlavors)) {// 类型是否匹配为文件
            try {
                List<File> filelist = (List<File>) t.getTransferData(dataFlavors);// 拿出粘贴板内文件对象列表
                for (int i = 0; i < filelist.size(); i++) { // 遍历文件列表并复制
                    File file = filelist.get(i);
                    File tagFile = new File(dirPath + File.separator
                            + file.getName());
                    if (file.getPath().equals(dirPath + file.getName()))
                        return;// 判断粘贴板内文件与当前粘贴目录的文件是否存在是一个文件
                    if (tagFile.exists()) {// 文件存在？
                        if (JOptionPane.showConfirmDialog(null,"file exist,cover it?", "Note",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            tagFile.delete();
                            tagFile = new File(dirPath + File.separator+ file.getName());
                            copyFolder(file.getAbsolutePath(),tagFile.getAbsolutePath());
                        }
                    } else {
                        copyFolder(file.getAbsolutePath(),tagFile.getAbsolutePath());
                    }
                }
            } catch (UnsupportedFlavorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void copyFolder(String sourceFolder, String destinationFolder) throws Exception {
        try {
            if(new File(sourceFolder).isFile()) {
                new File(destinationFolder).getParentFile().mkdirs();
            } else {
                new File(destinationFolder).mkdirs(); // 如果文件夹不存在 则建立新文件夹
            }
            File a = new File(sourceFolder);
            if (a.isFile()) {
                copy(destinationFolder, a,0);//0表示粘贴的是文件
                return;
            }
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (sourceFolder.endsWith(File.separator)) {
                    temp = new File(sourceFolder + file[i]);
                } else {
                    temp = new File(sourceFolder + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    copy(destinationFolder, temp,1);//1表示粘贴的是目录
                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(sourceFolder + "/" + file[i], destinationFolder + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            throw new Exception("复制整个文件夹内容操作出错", e);
        }
    }

    private static void copyFile(List<File> files) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        Transferable contents = new Transferable() {
            DataFlavor[] dataFlavors = new DataFlavor[] { DataFlavor.javaFileListFlavor };

            @Override
            public Object getTransferData(DataFlavor flavor)
                    throws UnsupportedFlavorException, IOException {
//                List<File> files = new ArrayList<>();
//                files.add(new File("E:\\test.txt"));
                return files;
            }

            @Override
            public DataFlavor[] getTransferDataFlavors() {
                return dataFlavors;
            }

            @Override
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                for (int i = 0; i < dataFlavors.length; i++) {
                    if (dataFlavors[i].equals(flavor)) {
                        return true;
                    }
                }
                return false;
            }
        };

        clipboard.setContents(contents, null);
    }


    private static void copy(String destinationFolder, File temp,int flag)
            throws FileNotFoundException, IOException {
        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            input = new FileInputStream(temp);
            output = null;
            if (flag == 0) {
                output = new FileOutputStream(destinationFolder);
            } else {
                output = new FileOutputStream(destinationFolder + "/" + (temp.getName()).toString());
            }
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }
            output.flush();
        } catch (Exception e) {
        } finally {
            output.close();
            input.close();
        }
    }
}
