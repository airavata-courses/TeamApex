package edu.sga.apex.rest.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystemException;

/**
 * The Class FileUtil.
 * 
 * @author Gourav Shenoy
 */
public class FileUtil {

	/**
	 * Creates the temp file.
	 *
	 * @param inputStream the input stream
	 * @param fileName the file name
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String createTempFile(InputStream inputStream, String fileName)
			throws Exception {
		OutputStream outputStream = null;
		String outFileName = null;

		try {
			/* read inputstream & write to temp file */
			File file = File.createTempFile(fileName,
					String.valueOf(System.currentTimeMillis()));
			outputStream = new FileOutputStream(file);

			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

			/* get name of temp file */
			outFileName = file.getName();
		} 
		catch (IOException ex) {
			System.err.println("Error creating temp file: " + ex);
			throw new FileSystemException("Error creating temp file: " + ex);
		} 
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					System.err.println("Failed to close InputStream: " + ex);
					throw new IOException("Failed to close InputStream: " + ex);
				}
			}
			if (outputStream != null) {
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException ex) {
					System.err.println("Failed to close OutputStream: " + ex);
					throw new IOException("Failed to close OutputStream: " + ex);
				}
			}
		}

		/* return path of temp file */
		return outFileName;
	}
}
