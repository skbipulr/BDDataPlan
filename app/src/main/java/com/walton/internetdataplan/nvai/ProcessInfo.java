package com.walton.internetdataplan.nvai;

/**
 * Created by Faruq on 12/19/2016.
 */


public class ProcessInfo{

    /**
     * The user id of this process.
     */
    public String uid;

    /** The name of the process that this object is associated with. */
    public String processName;

    /** The pid of this process; 0 if none. */
    public int pid;

    /**  Occupied memory B. */
    public long memory;

    /**  Occupied CPU. */
    public String cpu;

    /**  The state of the process, where S represents sleep, R that is running, Z represents a dead state, N said the process priority value is negative. */
    public String status;

    /**  The number of threads currently in use. */
    public String threadsCount;

    /**
     * Instantiates a new ab process info.
     */
    public ProcessInfo() {
        super();
    }

    /**
     * Instantiates a new ab process info.
     *
     * @param processName the process name
     * @param pid the pid
     */
    public ProcessInfo(String processName, int pid) {
        super();
        this.processName = processName;
        this.pid = pid;
    }


}

