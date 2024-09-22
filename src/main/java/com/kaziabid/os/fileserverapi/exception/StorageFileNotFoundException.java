package com.kaziabid.os.fileserverapi.exception;

/**
 * @author Kazi
 */
public class StorageFileNotFoundException extends StorageException {

    /**
     * 
     */
    private static final long serialVersionUID = -2019006705503214581L;

    /**
     * 
     */
    public StorageFileNotFoundException() {
	// TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public StorageFileNotFoundException(String message) {
	super(message);
	// TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public StorageFileNotFoundException(Throwable cause) {
	super(cause);
	// TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public StorageFileNotFoundException(String message, Throwable cause) {
	super(message, cause);
	// TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public StorageFileNotFoundException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
	// TODO Auto-generated constructor stub
    }

}
