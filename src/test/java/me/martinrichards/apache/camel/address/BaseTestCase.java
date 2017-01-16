package me.martinrichards.apache.camel.address;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author martinrichards
 */
public class BaseTestCase {
    protected Reader readFile(String fileName) throws URISyntaxException, FileNotFoundException {
        File file = new File(getURI(fileName));
        FileReader reader = new FileReader(file);
        return IOUtils.toBufferedReader(reader);
    }

    protected URI getURI(String fileName) throws URISyntaxException {
        return getClass().getClassLoader().getResource(fileName).toURI();
    }
}
