package com.walton.internetdataplan.nvai;

import android.graphics.drawable.Drawable;

/**
 * Created by Faruq on 12/19/2016.
 */


public class AppProcessInfo  {

    /**
     * The app name.
     */
    public String appName;

    /**
     * The name of the process that this object is associated with.
     */
    public String processName;

    /**
     * The pid of this process; 0 if none.
     */
    public int pid;

    /**
     * The user id of this process.
     */
    public int uid;

    /**
     * The icon.
     */
    public Drawable icon;

    /**
     * Occupied memory.
     */
    public long memory;

    /**
     * Occupied memory.
     */
    public String cpu;

    /**
     * The state of the process, where S represents sleep, R that is running, Z represents a dead state, N said the process priority value is negative.
     */
    public String status;

    /**
     * The number of threads currently in use.
     */
    public String threadsCount;


    public boolean checked=true;

    /**
     * Whether the system process.
     */
    public boolean isSystem;

    /**
     * Instantiates a new ab process info.
     */
    public AppProcessInfo() {
        super();
    }

    /**
     * Instantiates a new ab process info.
     *
     * @param processName the process name
     * @param pid         the pid
     * @param uid         the uid
     */
    public AppProcessInfo(String processName, int pid, int uid) {

        this.processName = processName;
        this.pid = pid;
        this.uid = uid;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */


}

