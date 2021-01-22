package com.ruet_cse_1503050.ragib.droidshell;

public class CommandResult {
    private boolean successful;
    private String stdOut;
    private String stdErr;

    public CommandResult(boolean successful, String stdOut, String stdErr) {
        this.successful = successful;
        this.stdOut = stdOut;
        this.stdErr = stdErr;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getStdOut() {
        return stdOut;
    }

    public void setStdOut(String stdOut) {
        this.stdOut = stdOut;
    }

    public String getStdErr() {
        return stdErr;
    }

    public void setStdErr(String stdErr) {
        this.stdErr = stdErr;
    }
}
