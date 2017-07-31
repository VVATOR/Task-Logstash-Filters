package com.epam.training;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.epam.training.model.RowLogger;

public class Runner {

	private static final String DASH = "-";
	private static final String CHARACTERS_NUMBER = "0123456789";
	private static final Logger LOG = Logger.getLogger(Runner.class);
	private static final int HEX_STRING_LENGTH = 32;
	private static final String HEX_CHARACTERS = "0123456789abcdef";
	private static final String SPACE = " ";
	private static final String QUOTE = "\"";
	private static final String DELIMITER_KEY_VALUE = "=";

	enum Message {
		ERROR("error"), CORRECT("correct");
		private String stringVersion;

		private Message(String stringVersion) {
			this.stringVersion = stringVersion;
		}

		public String getStringVersion() {
			return stringVersion;
		}
	}

	enum Source {
		DATABASE("DataBase"), LOG("Log"), SOCKET("Socket");

		private String stringVersion;

		private Source(String stringVersion) {
			this.stringVersion = stringVersion;
		}

		public String getStringVersion() {
			return stringVersion;
		}
	}

	public static void main(String[] args) {

		Random randomCountRecord = new Random();
		int countLogMessages = 5 + randomCountRecord.nextInt(10);
		for (int i = 0; i < countLogMessages; i++) {

			Map<String, String> kv = new TreeMap<String, String>();
			kv.put("app_name", "application-name");
			kv.put("app_version", "1.0.0-SNAPSHOT");
			kv.put("hostname", "localhost");

			Random randomCountAdditionalKeyValue = new Random();
			int countKeyValue = randomCountAdditionalKeyValue.nextInt(10);
			for (int n = 0; n < countKeyValue; n++) {
				kv.put("key" + n, "value" + n);
			}

			Random randomErrorOrCorrect = new Random();
			Random randomSource = new Random();

			RowLogger row = new RowLogger(generateRandomMessageIdString(),
					generateRandomString(HEX_CHARACTERS, HEX_STRING_LENGTH), 
					keyValueToString(kv),
					generateRandomString(HEX_CHARACTERS, HEX_STRING_LENGTH),
					Source.values()[randomSource.nextInt(3)].getStringVersion(),
					"message " + Message.values()[randomErrorOrCorrect.nextInt(2)].getStringVersion() + " here");
			LOG.info(row);
		}
	}

	private static String generateRandomMessageIdString() {
		StringBuffer result = new StringBuffer();
		result.append(generateRandomString(CHARACTERS_NUMBER, 2)).append(DASH)
				.append(generateRandomString(CHARACTERS_NUMBER, 2)).append(DASH)
				.append(generateRandomString(CHARACTERS_NUMBER, 4));
		return result.toString();
	}

	private static String keyValueToString(Map<String, String> kv) {
		StringBuffer result = new StringBuffer("");
		for (Entry<String, String> entry : kv.entrySet()) {
			result.append(SPACE).append(entry.getKey()).append(DELIMITER_KEY_VALUE).append(QUOTE)
					.append(entry.getValue()).append(QUOTE);
		}
		return result.toString();
	}

	public static String generateRandomString(final String characters, final int length) {
		Random rng = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		return new String(text);
	}
}
