package com.meeno.framework.tree;

/**
 * Used example:
 * pageContext.setAttribute ("JavaScriptEncoder", new com.huatek.cq.util.JavaScriptEncoder ());
 * <c:out value="${JavaScriptEncoder[student.name]}"/>
 *
 */
public class HTMLEncoder extends Encoder {
	private static final long serialVersionUID = 1L;

	public static final HTMLEncoder INSTANCE = new HTMLEncoder();

	private static final String[] __ESCAPES = new String[64];
	static {
		__ESCAPES['<'] = "&lt;";
		__ESCAPES['>'] = "&gt;";
		__ESCAPES['&'] = "&amp;";
	}

	public String encode(Object o) {
		String s = (o == null) ? "" : o.toString();
		StringBuffer r = new StringBuffer();
		char[] chars = s.toCharArray();
		int i = 0, j = 0;
		try {
			do {
				char c = chars[i++];
				String escape = (c < 64) ? __ESCAPES[c] : null;
				if (escape != null) {
					r.append(chars, j, i - j - 1).append(escape);
					j = i;
				}
			} while (true);
		} catch (IndexOutOfBoundsException ex) {
			r.append(chars, j, chars.length - j);
		}
		return r.toString();
	}
}
