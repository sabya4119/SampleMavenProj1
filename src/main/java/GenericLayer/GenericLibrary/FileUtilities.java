package GenericLayer.GenericLibrary;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.*;
import java.util.Arrays;

public interface FileUtilities {

    /**
     * Returns the cannonical path of specified file as expected to be in the Data folder.
     *
     * @param filename - filename (no directory just the filename)
     */
    public static String getDataFile(String filename) throws IOException {
        File dataDir = new File(getCWD(), ".\\src\\test\\resources\\TestData");

        if (!dataDir.exists()) {
            String msg = String.format("Required directory '%s' not found!", dataDir.getAbsolutePath());

            throw new IllegalStateException(msg);
        }

        return (new File(dataDir, filename)).getCanonicalPath();
    }

    /**
     * Returns the current working directory
     */
    public static File getCWD() {
        return new File(System.getProperty("user.dir"));
    }

    /**
     * Returns the cannonical path of specified file if expected to be in sub folders of the Data folder.
     *
     * @param filename - filename (no directory just the filename)
     */
    public static String getDataFile(String dirPath, String filename) throws IOException {
        File dataDir = new File(getCWD(), ".\\src\\test\\resources\\TestData\\" + dirPath);

        if (!dataDir.exists()) {
            String msg = String.format("Required directory '%s' not found!", dataDir.getAbsolutePath());

            throw new IllegalStateException(msg);
        }

        return (new File(dataDir, filename)).getCanonicalPath();
    }

    public static void createDirectory(String createDirectoryName) {

        File dir = new File(createDirectoryName);
        if (!dir.exists())
            dir.mkdir();
    }

    public static void deleteDirectory(String deleteDirectoryName) throws FileNotFoundException, IOException {
        File deleteDirectory = new File(deleteDirectoryName);
        deleteDirectory.delete();

    }

    /*
     * Copy to new file to different location and delete the original file
     *
     */
    public static void copyFile(File fromFileInfo, File toFileInfo) throws FileNotFoundException, IOException {
        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(toFileInfo);        // open this first, in case we don't have write permissions
            input = new FileInputStream(fromFileInfo);

            final int NO_OFFSET = 0;
            int length;
            byte[] buffer = new byte[10240];
            while ((length = input.read(buffer)) != -1) {
                output.write(buffer, NO_OFFSET, length);
            }

        } finally {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.flush();
                output.getFD().sync();
                output.close();

            }

        }

    }


    public static String getFileNamebysubString(String curDirectoryName, String containString) throws FileNotFoundException, IOException {
        String retString = "";

        for (File file : listFiles(curDirectoryName)) {

            if (file.isFile()) {
                //	System.out.println(file.getAbsolutePath()+"---> Seaching");
                if (file.getName().contains(containString)) {
                    retString = file.getName();
                    break;
                }
            }

        }

        return retString;

    }


    public static File[] listFiles(String directoryName) {

        File directory = new File(directoryName);

        File[] fList = directory.listFiles();

        return fList;

    }

    public static File getTheNewestFile(File dir, String ext) {
        File theNewestFile = null;
//        File dir = new File(filePath);
        FileFilter fileFilter = new WildcardFileFilter("*." + ext);
        File[] files = dir.listFiles(fileFilter);

        if (files.length > 0) {
            /** The newest file comes first **/
            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            theNewestFile = files[0];
        }

        return theNewestFile;
    }

}
