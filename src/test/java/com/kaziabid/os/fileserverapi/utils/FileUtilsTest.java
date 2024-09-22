package com.kaziabid.os.fileserverapi.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kaziabid.os.fileserverapi.utils.FileUtils.FileSizeFormat;

/**
 * @author Kazi
 */
class FileUtilsTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.kaziabid.os.fileserverapi.utils.FileUtils#calculateFileSize(long, com.kaziabid.os.fileserverapi.utils.FileUtils.FileSizeFormat)}.
     */
    @Test
    void whenFileSizeFormatGiBAndOutputLargeThenTestProperValue() {
	long fileSize = 12345678910l;
	long expected = fileSize / 1024 / 1024 / 1024;
	long actualResult = FileUtils.calculateFileSize(fileSize, FileSizeFormat.GiB);
	assertEquals(expected, actualResult);
    }

}
