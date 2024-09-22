package com.kaziabid.os.fileserverapi.utils;

/**
 * @author Kazi
 */
public class FileUtils {

    public static long calculateFileSize(long fileSizeBytes, FileSizeFormat format) {
	long output = 0;
	if (fileSizeBytes > 0) {
	    output = switch (format) {
	    case KiB -> {
		yield fileSizeBytes / 1024;
	    }
	    case KB -> {
		yield fileSizeBytes / 1000;
	    }
	    case MiB -> {
		yield fileSizeBytes / 1024 / 1024;
	    }
	    case MB -> {
		yield fileSizeBytes / 1000 / 1000;
	    }
	    case GiB -> {
		yield fileSizeBytes / 1024 / 1024 / 1024;
	    }
	    case GB -> {
		yield fileSizeBytes / 1000 / 1000 / 1000;
	    }
	    case TiB -> {
		yield fileSizeBytes / 1024 / 1024 / 1024 / 1024;
	    }
	    case TB -> {
		yield fileSizeBytes / 1000 / 1000 / 1000 / 1024;
	    }
	    default -> throw new IllegalArgumentException("Unexpected value-> " + format);
	    };
	}
	return output;
    }

    public static enum FileSizeFormat {
	KiB, KB, MiB, MB, GiB, GB, TiB, TB
    }
}
