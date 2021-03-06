package org.kefirsf.bb;

/**
 * @author kefir
 */
public class UrlCollection {
    public static final String[] VALID = new String[]{
            "http://example.com",
            "http://john.smith@example.com",
            "http://john.smith:pa55w0rd@example.com",
            "http://john.smith:pa55w0rd@example.com/",
            "http://john.smith:pa55w0rd@example.com/home/web",
            "http://john.smith:pa55w0rd@example.com/home/web?",
            "http://john.smith:pa55w0rd@example.com/home/web?key=value",
            "http://john.smith:pa55w0rd@example.com/home/web?key1=value1&key2=value2",
            "http://john.smith:pa55w0rd@example.com/home/web?key1=value1&key2=value2#",
            "http://john.smith:pa55w0rd@example.com/home/web?key1=value1&key2=value2#anchor",
            "https://example.com",
            "ftp://example.com",
            "mailto:john.smith@example.com",
            "http://foo.com/blah_blah",
            "http://foo.com/blah_blah/",
            "http://foo.com/blah_blah_(wikipedia)",
            "http://foo.com/blah_blah_(wikipedia)_(again)",
            "http://www.example.com/wpstyle/?p=364",
            "https://www.example.com/foo/?bar=baz&inga=42&quux",
            "http://userid:password@example.com:8080",
            "http://userid:password@example.com:8080/",
            "http://userid@example.com",
            "http://userid@example.com/",
            "http://userid@example.com:8080",
            "http://userid@example.com:8080/",
            "http://userid:password@example.com",
            "http://userid:password@example.com/",
            "http://142.42.1.1/",
            "http://142.42.1.1:8080/",
            "http://foo.com/blah_(wikipedia)#cite-1",
            "http://foo.com/blah_(wikipedia)_blah#cite-1",
            "http://foo.com/(something)?after=parens",
            "http://code.google.com/events/#&product=browser",
            "http://j.mp",
            "ftp://foo.bar/baz",
            "http://foo.bar/?q=Test%20URL-encoded%20stuff",
            "http://foo.bar/?q=Test+URL-encoded+stuff",
            "http://-.~_!$&'()*+,;=:%40:80%2f::::::@example.com",
            "http://1337.net",
            "http://a.b-c.de",
            "http://223.255.255.254",
            "http://kefirsf.org/kefirbb/ava.jpg",
            "http://kefirsf.org/kefirbb/.././ava.jpg",
            "https://www.youtube.com/watch?v=tvVA-vB7-mU",
            "http://www.youtube.com/embed/AuG9i5cwGW0?rel=0;autoplay=1",
            "http://example.com/dashed-path/index.html",
            "http://example.com/path/dashed-index.html"
    };

    public static final String[] INVALID = new String[]{
            "http://",
            "http://.",
            "http://..",
            "http://../",
            "http://?",
            "http://??",
            "http://??/",
            "http://#",
            "http://##",
            "http://##/",
            "//",
            "//a",
            "///a",
            "///",
            "http:///a",
            "foo.com",
            "rdar://1234",
            "h://test",
            "http:// shouldfail.com",
            ":// should fail",
            "ftps://foo.bar/",
            "http://-error-.invalid/",
            "http://-a.b.co",
            "http://.www.foo.bar/",
            "http://.www.foo.bar./"
    };
    public static final String[] LOCAL = new String[]{
            "/", "/home", "/home/", "/home/web", "/home/web/", "/home/web/index.html",
            "./", "./home", "./home/", "./home/web", "./home/web/", "./home/web/index.html",
            "../", "../home", "../home/", "../home/web", "../home/web/", "../home/web/index.html",
            "./..", "./../", "./../home", "./../home/", "./../home/web", "./../home/web/",
            "./../home/web/index.html", "../../home/../web/index.html"
    };

    public static final String[] VALID_EMAIL = new String[]{
            "john.smith@example.com",
            "john.smith@example.com?subject=Subject%20of%20a%20letter"
    };

    public static final String[] INVALID_EMAIL = new String[]{
            "john.smith.example.com",
            "john.smith.example.com?subject=Subject%20of%20a%20letter.",
            "john.smith@"
    };
}
