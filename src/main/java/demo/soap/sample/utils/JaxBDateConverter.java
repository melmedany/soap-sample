package demo.soap.sample.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author medany
 */

public class JaxBDateConverter extends XmlAdapter<String, Calendar> {
	private final Format FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Calendar unmarshal(String value) {
		return javax.xml.bind.DatatypeConverter.parseDateTime(value);
	}

	@Override
	public String marshal(Calendar value) {
		return FORMATTER.format(value.getTime());
	}
}