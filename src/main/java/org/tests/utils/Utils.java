package org.tests.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * author: Irina Benediktovich - http://plus.google.com/+IrinaBenediktovich
 */
public class Utils {

	public static final String SYS_SEPARATOR = System.getProperty("line.separator");

	public static List<String> readLines(String s) {
		BufferedReader br = new BufferedReader(new StringReader(s));
		List<String> result = new ArrayList<String>();
		try {
			String str = br.readLine();
			while (str != null) {
				result.add(str);
				Thread.yield();
				str = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
		close(br);
		return result;
	}

	public static String[] readStrings(String s) {
		List<String> list = readLines(s);
		return (String[]) list.toArray(new String[list.size()]);
	}

	public static String convertArray2String(String[] text) {
		String result = "";
		for (String str : text) {
			if (result.length() > 0) {
				result += SYS_SEPARATOR;
			}
			result += str;
		}
		return result;
	}

	public static void close(Reader r) {
		try {
			r.close();
		} catch (Exception e) {
		}
	}

	/**
	 * Reads stream line by line until line equal to @match is found. This line is not included
	 * in the returned value.
	 *
	 * @param r     input
	 * @param match
	 * @return contain of input stream until @match occured
	 * @throws IOException
	 */
	public static StringBuffer readUntilMatch(BufferedReader r, String match) throws IOException {
		StringBuffer sb = new StringBuffer();
		String inputLine;
		boolean matchFound = false;
		while (((inputLine = r.readLine()) != null) && !matchFound) {
			if (sb.length() != 0) {
				if (sb.equals(match)) {
					matchFound = true;
				} else {
					sb.append(SYS_SEPARATOR);
				}
			}
			sb.append(inputLine);
		}
		return sb;
	}

	public static String readString(BufferedReader r) throws IOException {
		StringBuffer sb = new StringBuffer();
		String inputLine;
		while ((inputLine = r.readLine()) != null) {
			if (sb.length() != 0) {
				sb.append(SYS_SEPARATOR);
			}
			sb.append(inputLine);
		}
		return sb.toString();
	}

	public static String getCommaSeparatedStrings(Collection<String> list) {
		return getXSeparatedObjects(list, ",");
	}

	public static String getXSeparatedObjects(Collection list, String separator) {
		String result = "";
		for (Object o : list) {
			if (o != null) {
				if (result.length() != 0) {
					result += separator;
				}
				result += o.toString();
			}
		}
		return result;
	}

	/**
	 * Saves data from input stream into file, assuming that all parent directories
	 * already exist.
	 *
	 * @param isr
	 * @param fileName
	 */
	public static void saveDataToFile(InputStreamReader isr, String fileName) {
		BufferedReader in = new BufferedReader(isr);

		String inputLine;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));
			while ((inputLine = in.readLine()) != null) {
				bw.write(inputLine);
			}
			in.close();
			bw.close();
		} catch (IOException e) {
			System.err.println("error writing " + fileName);
			e.printStackTrace();
		}
	}

	/**
	 * Saves lines into file, creating all needed parent directories
	 *
	 * @param lines
	 * @param fileName
	 */
	public static void saveDataToFile(Collection<String> lines, String fileName) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				File parent = file.getParentFile();
				if (parent != null && !parent.exists()) {
					parent.mkdirs();
				}
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
			for (String line : lines) {
				bw.write(line + SYS_SEPARATOR);
			}
			bw.close();
		} catch (IOException e) {
			System.err.println("error writing " + fileName);
			e.printStackTrace();
		}
	}

	/**
	 * Two string values are identical if they are both null or they are equal.
	 *
	 * @param str1 value 1
	 * @param str2 value 2
	 * @return true if both values are null, or values are equal (case sensitive)
	 */
	public static boolean identical(String str1, String str2) {
		if (str1 == null && str2 == null) {
			return true;
		}

		return (str1 != null && str2 != null && str1.equals(str2));
	}


	/**
	 * Compares given values to find those one which is more "informative".
	 * Returns:
	 * a>b  : +1
	 * a=b  :  0
	 * a<b  : -1
	 * <p/>
	 * <p/>
	 * If both values are null, it means that they are equal.
	 * If one value is null, then other value is more informative.
	 * If both values are not null, then they are compared by their values based on priority list:
	 * first value in list has the lowest priority.
	 * If both values are not found in list, then first is greater
	 * <p/>
	 * So, the common ruler of comparing string values are:
	 * null = null
	 * null < not null
	 * value from priorities list < value not in priorities list
	 * priorities.get(i-1) < priorities.get(i)
	 *
	 * @param a
	 * @param b
	 * @param priorities first value has lowest priority
	 * @return
	 */
	public static int compareValues(String a, String b, ArrayList<String> priorities) {
		if (identical(a, b)) {
			return 0;   // a=b
		}

		if (a == null) {
			return -1;  // a<b
		}

		if (b == null) {
			return 1;   // a>b
		}

		// Need to compare values. Doing this by
		// assigning a and b their priorities. If value is in the list, then prioprity is index,
		//  otherwise priority is equal to size of the list.
		int priorA = priorities.indexOf(a);
		if (priorA < 0)
			priorA = priorities.size() + 1; // priorA must be > priorB, if both are not in list

		int priorB = priorities.indexOf(b);
		if (priorB < 0)
			priorB = priorities.size();

		return priorA == priorB ? 0 : (priorA > priorB ? 1 : -1);
	}
}


