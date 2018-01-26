/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.st.app.config;

import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * 
 * @author Administrador Gio
 */
public class InstanceRunningChecker {

    private static final int PORT = 9999;
    private static ServerSocket socket;

    public static void isRunning() {
	try {
	    socket = new ServerSocket(PORT, 0,
		    InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 }));

	} catch (BindException e) {
	    System.exit(0);
	} catch (IOException e) {
	    System.err.println("Error inesperado.");
	    e.printStackTrace();
	    // System.exit(2);
	}
    }
}
