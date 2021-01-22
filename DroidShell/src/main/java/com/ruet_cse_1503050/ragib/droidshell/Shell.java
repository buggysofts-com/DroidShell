package com.ruet_cse_1503050.ragib.droidshell;

import com.ruet_cse_1503050.ragib.droidshell.utils.CommonUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public class Shell {

    public static CommandResult run(boolean runAsRoot, String execPath, String... command) {

        CommandResult result = new CommandResult(false, "", "");

        Process process = null;
        BufferedInputStream cmdIS = null;
        BufferedOutputStream cmdOS = null;
        BufferedInputStream errIS = null;

        try {

            process = Runtime.getRuntime().exec(runAsRoot ? "su" : "sh");

            cmdIS = new BufferedInputStream(process.getInputStream());
            cmdOS = new BufferedOutputStream(process.getOutputStream());
            errIS = new BufferedInputStream(process.getErrorStream());

            StringBuilder commandBuilder = new StringBuilder(0);
            for (int i = 0; i < command.length; ++i) {
                commandBuilder.append(execPath != null ? "'" + execPath + "'" + " " : null).append(command[i]).append("\n");
            }
            cmdOS.write((commandBuilder.toString()).getBytes());
            cmdOS.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (cmdOS != null) {
                cmdOS.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (cmdIS != null) {
                byte[] data = CommonUtils.ReadStream(cmdIS, false);
                if (data != null) {
                    result.setStdOut(new String(data));
                }
                cmdIS.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (errIS != null) {
                byte[] data = CommonUtils.ReadStream(errIS, false);
                if (data != null) {
                    result.setStdErr(new String(data));
                }
                errIS.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (process != null) {
                result.setSuccessful(process.waitFor() == 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
