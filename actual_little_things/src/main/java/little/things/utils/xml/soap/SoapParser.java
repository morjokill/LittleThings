package little.things.utils.xml.soap;

import com.google.common.base.Strings;

import javax.xml.bind.ValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoapParser {
    private static final Pattern SOAP_MESSAGE_PATTERN = Pattern.compile("<[\\w-]*?:?Envelope.*Envelope>", Pattern.DOTALL);

    public static String getSoapMessageFromString(String xml) throws ValidationException {
        if (xml == null) {
            throw new ValidationException("Could not load xml from string. Given string is NULL");
        }

        Matcher matcher = SOAP_MESSAGE_PATTERN.matcher(xml);
        if (matcher.find()) {
            String soapPart = matcher.group();
            if (!Strings.isNullOrEmpty(soapPart)) {
                return soapPart;
            } else {
                throw new ValidationException("Could not load xml from string. Given string is not an soap message");
            }
        } else {
            throw new ValidationException("Could not load xml from string. Given string is not an soap message");
        }
    }
}
