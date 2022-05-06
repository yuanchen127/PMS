package org.ike.pms.pmsfilesystem.common;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ike
 * @since 2019-02-26 13:03
 */
public class FileUtil {

    public static void deepListDir(File dir, Set<String> dirSet) {
        String[] list = dir.list();
        String lastDir = dir.getAbsolutePath();
        if (list == null || list.length <= 0) {
            dirSet.add(lastDir);
            return;
        }
        for (String name : list) {
            String currentDir = lastDir + File.separator + name;
            File currentFile = new File(currentDir);
            if (currentFile.isFile()) {
                dirSet.add(lastDir);
                continue;
            }
            deepListDir(currentFile, dirSet);
        }
    }

    public static Set<String> listDir(String dirName) {
        Set<String> dirSet = new LinkedHashSet<>();
        File currentDir = new File(dirName);
        String[] dirs = currentDir.list();
        if (dirs != null && dirs.length > 0) {
            for (String dirTemp : dirs) {
                File dir = new File(dirName + File.separator + dirTemp);
                if (dir.isDirectory()) {
                    dirSet.add(dirName.endsWith(File.separator) ? dirName + dirTemp : dirName + File.separator + dirTemp);
                }
            }
        }
        return dirSet;
    }

    public static void deepListFile(File dir, Set<String> fileSet) {
        String[] list = dir.list();
        String lastDir = dir.getAbsolutePath();
        if (list != null && list.length > 0) {
            for (String name : list) {
                String currentDir = lastDir + File.separator + name;
                File currentFile = new File(currentDir);
                if (currentFile.isFile()) {
                    fileSet.add(currentDir);
                    continue;
                }
                deepListFile(currentFile, fileSet);
            }
        }

    }

    public static Set<String> listFile(String dirName) {
        File currentFile = new File(dirName);
        Set<String> fileSet = new LinkedHashSet<>();
        String[] files = currentFile.list();
        if (files != null && files.length > 0) {
            for (String fileTemp : files) {
                File file = new File(dirName + File.separator + fileTemp);
                if (file.isFile()) {
                    fileSet.add(dirName.endsWith(File.separator) ? dirName + fileTemp : dirName + File.separator + fileTemp);
                }
            }
        }
        return fileSet;
    }

    public static boolean copyFileToClipboard(List<File> files) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = new Transferable() {
            DataFlavor[] dataFlavors = new DataFlavor[] { DataFlavor.javaFileListFlavor };

            @Override
            public Object getTransferData(DataFlavor flavor) {
                return files;
            }

            @Override
            public DataFlavor[] getTransferDataFlavors() {
                return dataFlavors;
            }

            @Override
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                for (DataFlavor data:dataFlavors) {
                    if (data.equals(flavor)) {
                        return true;
                    }
                }
                return false;
            }
        };
        clipboard.setContents(contents, null);

        return Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null).isDataFlavorSupported(DataFlavor.javaFileListFlavor);
    }
}
